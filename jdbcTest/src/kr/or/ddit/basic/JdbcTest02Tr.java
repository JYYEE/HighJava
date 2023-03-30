package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 문제) 사용자로부터 Lprod_id값을 입력받아
 * 		 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오.
 */

public class JdbcTest02Tr {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Lprod_id값 입력 >> ");
		int num = scan.nextInt();
		
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
			String sql = "select * from lprod where lprod_id >" + num;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("  == SQL문 처리 결과 ==  ");
			while(rs.next()) {
				System.out.println("----------------------------------------");
				System.out.println("Lprod_ID : " + rs.getInt(1));
				System.out.println("Lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("Lprod_nm : " + rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (Exception e2) {}
			if(stmt!=null) try {stmt.close();} catch (Exception e2) {}
			if(conn!=null) try {conn.close();} catch (Exception e2) {}
		}
		scan.close();
	}
}
