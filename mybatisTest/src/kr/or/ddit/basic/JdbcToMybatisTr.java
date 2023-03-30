package kr.or.ddit.basic;

import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisSqlSessionFactory;

/*
 * Lprod테이블에 새로운 데이터 추가하기
 * 
 * Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 * Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 * 
 * --> JDBC 예제 중 JdbcTest05.java를 myBatis용으로 변경하시오.
 * --> mapper파일명은 'jdbc-mapper.xml'로 한다.
 */

public class JdbcToMybatisTr {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		Reader rd = null;
		SqlSessionFactory sqlSessionFactory = null;

		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if (rd != null)
				try {
					rd.close();
				} catch (Exception e2) {
				}
		}
		*/
		//----------------------------------------------------------------------
		SqlSession session = null;
		try {
//			session = sqlSessionFactory.openSession();
			// util 파일 생성해서 불러옴.
			session = MybatisSqlSessionFactory.getSqlSession();
			// 제일 큰 값 보다 1 큰 값구하기
			int nextId = session.selectOne("jdbcTr.getNextId");
			
			// Lprod_gu값을 입력받고 중복되면 다시 입력 받기
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.nextLine();

				count = session.selectOne("jdbcTr.getLprodCount", gu);

				if (count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 상품 분류 코드로 다시 입력하세요...");
					System.out.println();
				}
			} while (count > 0 );

			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.nextLine();

			LprodVO lprodVO = new LprodVO();
			lprodVO.setLprod_gu(gu);
			lprodVO.setLprod_id(nextId);
			lprodVO.setLprod_nm(nm);
			
			// insert
			int cnt = session.insert("jdbcTr.insertLprod", lprodVO);
			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패~~~");
			}
		} finally {
			session.commit(); // select에서는 할 필요 없지만, insert, delete, update에서는 필수
			session.close();
		}
		scan.close();
	}
}
