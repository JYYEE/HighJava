package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 *  JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로
 *  구성된 class 작성하기 
 */
// 드라이버는 처음 1번 불러오면 됨
// DB와 연결은 그때그때 하는게 좋음
public class DBUtil {
	static { // 초기화 블럭. 클래스를 실행할 때 무조건 실행
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	// Connection객체를 반환하는 메서드
	public static Connection  getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YJY", "java");
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}
}
// 단점 : oracle전용
// oracle이 아닌 다른 프로그램에서도 작동할 수 있는 값들을 자바 소스에 고정해놓으면 수정 어려움
// 별도의 파일로 보관하고 수정시 바로 적용되도록 함. 