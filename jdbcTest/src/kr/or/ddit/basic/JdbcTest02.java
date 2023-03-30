package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 문제) 사용자로부터 Lprod_id값을 입력받아
 * 		 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오.
 */

public class JdbcTest02 {
	public static void main(String[] args) {
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
			System.out.print("Lprod_id를 입력하세요. >> ");
			int num = scan.nextInt();
//			String sql = "select * from lprod where LPROD_ID>"+num;
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT");
			builder.append("    *");
			builder.append("FROM");
			builder.append("    lprod");
			builder.append("WHERE");
			builder.append("    lprod_id > ?");
			String sql = builder.toString();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, num);
					
//			stmt = conn.createStatement();
			rs = statement.executeQuery(sql);
			System.out.println("  == SQL문 처리 결과 ==  ");
			while(rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2)); 
				System.out.println("lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("-----------------------------------------");
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (Exception e2) {}
			if(statement!=null) try {statement.close();} catch (Exception e2) {}
			if(conn!=null) try {conn.close();} catch (Exception e2) {}
		}
		scan.close();
	}
}
