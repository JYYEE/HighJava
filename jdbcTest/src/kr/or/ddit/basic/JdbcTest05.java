package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * Lprod테이블에 새로운 데이터 추가하기
 * 
 * Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 * Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다. 
 */
public class JdbcTest05 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
			String lprodGu;

			while (true) {
				System.out.print("Lprod_gu를 입력 >> ");
				lprodGu = scan.nextLine();
				String sql1 = "select count(*) from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, lprodGu);
				rs = pstmt.executeQuery();
				rs.next();
				if (rs.getInt(1) == 1) {
					System.out.println("이미 존재하는 Lprod_gu입니다. 다시 입력해주세요");
				} else {
					System.out.println("등록 가능한 Lprod_gu입니다.");
					break;
				}
			}
			pstmt.close();
			System.out.print("Lprod_nm를 입력 >> ");
			String lprodNm = scan.nextLine();
			String sql2 = "insert into lprod (LPROD_ID, lprod_gu, lprod_nm) "
					+ " VALUES ((select max(lprod_id)+1 from lprod), ?, ?)";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, lprodGu);
			pstmt.setString(2, lprodNm);
			//int cnt = pstmt.executeUpdate();
			//System.out.println("반환값 : " + cnt);
			System.out.println("정상적으로 등록되었습니다.");
			
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
