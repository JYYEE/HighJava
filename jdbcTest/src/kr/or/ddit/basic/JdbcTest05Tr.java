package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * Lprod테이블에 새로운 데이터 추가하기
 * 
 * Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 * Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다. 
 */
public class JdbcTest05Tr {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
			conn = DBUtil.getConnection(); // catch에 classforname 필요없음
			
			// Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.
			String sql = "select max(lprod_id)+1 maxid from lprod";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); 
			
			int lprodId = 0;
			if(rs.next()) { // 결과값 여러개일 때는 while문, 1개일 때는 if문. 
				//resultSet의 결과를 꺼내올 때는 rs.next()진행된 이후에 가능
				//lprodId = rs.getInt("max(lprod_id)+1"); //alias가 없을 때
				//lprodId = rs.getInt(1); 	  // 
				lprodId = rs.getInt("maxid"); //alias를 잡으면 이용 가능
			}
			// -------------------------------------------------------
			// 입력 받은 Lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			
			String lprodGu; // 상품 분류 코드가 저장될 변수 선언
			int count = 0; // 입력한 상품 분류 코드의 개수가 저장될 변수
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				lprodGu = scan.nextLine();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu= ? ";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprodGu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				if(count == 1) {
					System.out.println("입력한 상품 분류 코드 " + lprodGu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요.");
				}
			} while (count == 1);
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String lprodNm = scan.nextLine();
			
			String sql3 = "insert into lprod (LPROD_ID, lprod_gu, lprod_nm) VALUES ( ?, ?, ?)";
			pstmt = conn.prepareStatement(sql3); // pstmt.close();로 해결.  
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패~~~");
			}

//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try {pstmt.close();} catch (Exception e2) {}
			if(conn!=null) try {conn.close();} catch (Exception e2) {}
		}
		scan.close();
	}
}
