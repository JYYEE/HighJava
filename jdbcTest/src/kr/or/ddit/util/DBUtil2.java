package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 *  JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로
 *  구성된 class 작성하기 
 *  (dbinfo.properties파일의 내용으로 설정하는 방법)
 */
public class DBUtil2 {
	private static Properties prop; // Properties 객체 변수 선언
	
	
	static { // 초기화 블럭. 클래스를 실행할 때 무조건 실행
		prop = new Properties();
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		
		try {
			fin = new FileInputStream(f);
			prop.load(fin);
			Class.forName(prop.getProperty("driver"));
//			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류... : 드라이버 로딩 실패!!!");
			e.printStackTrace();
		} finally {
			if(fin!= null) try { fin.close();} catch (Exception e2) {}
				
		}
	}
	
	// Connection객체를 반환하는 메서드
	public static Connection  getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
			return DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("user"), prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}
}
