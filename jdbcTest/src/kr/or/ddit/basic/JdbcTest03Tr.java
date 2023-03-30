package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) Lprod_id값을 2개를 입력받아서 두 값 중 작은값부터 큰 값사이의 자료들을 출력하시오.

public class JdbcTest03Tr {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 Lprod_id를 입력 >> ");
		int num1 = scan.nextInt();
		System.out.print("두번째 Lprod_id를 입력 >> ");
		int num2 = scan.nextInt();
		
		int max = Math.max(num1, num2);
		int min = Math.min(num1, num2);
		// min, max 구하는 방법 
		// 1. Math클래스 이용
		// 2. if문
		// 3. 삼항 연산자
		//int max = num1>num2 ? num1 : num2;
		
		// DB 작업용 객체 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
			// String sql = "select * from lprod where lprod_id >= " + min +" lprod_id <= " + max;
			String sql = "select * from lprod where lprod_id between " + min +" and " + max;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("  == SQL문 처리 결과 ==  ");
			while(rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt(1));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString(3));
				System.out.println("-----------------------------------");
				
			}
			
			System.out.println();
			System.out.println("출력 끝...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
