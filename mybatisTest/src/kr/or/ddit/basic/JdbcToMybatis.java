package kr.or.ddit.basic;

import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

public class JdbcToMybatis {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Reader rd = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession session = null;

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
					// TODO: handle exception
				}
		}
		int cnt = 0;
		String lprodGu;
		try {
			session = sqlSessionFactory.openSession();
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				lprodGu = scan.nextLine();

				// select 분류코드 중복 확인
				cnt = session.selectOne("jdbc.getCntJdbc", lprodGu);

				if (cnt == 1) {
					System.out.println("입력한 상품 분류 코드 " + lprodGu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요.");
				}
			} while (cnt == 1);

			// select id 가져오기
			int lprodId = 0;
			lprodId = session.selectOne("jdbc.getIdJdbc");

			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String lprodNm = scan.nextLine();

			LprodVO lprodVO = new LprodVO();
			lprodVO.setLprod_gu(lprodGu);
			lprodVO.setLprod_id(lprodId);
			lprodVO.setLprod_nm(lprodNm);
			
			// insert
			int insertCnt = session.insert("jdbc.insertJdbc", lprodVO);
			if (insertCnt > 0) {
				System.out.println("insert 작업 성공!!!");
			} else {
				System.out.println("insert 작업 실패~~~");
			}
		} finally {
			session.commit();
			session.close();
		}
		scan.close();
	}
}
