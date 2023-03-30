package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;

/*
 * 회원을 관리하는 프로그램을 작성하시오. (MYMEMBER 테이블 이용)
 * 
 * 아래의 메뉴를 모두 구현하시오.(CRUD기능 구현하기)
 * 메뉴 예시)
 * --------------------
 * 1. 자료 추가			--> insert(C)
 * 2. 자료 삭제			--> delete(D)
 * 3. 자료 수정			--> update(U)
 * 4. 전체 자료 출력	--> select(R)
 * 0. 작업 끝
 * --------------------
 * 
 * ** 조건 **
 * 1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
 * 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 * 3) 자료 수정에서 '회원ID'는 변경되지 않는다. (나머지는 한꺼번에 변경되도록)
 */
public class JdbcTest06Tr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new JdbcTest06Tr().startMember();
	}

	// 작업을 시작하는 메서드
	public void startMember() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;
			case 3: // 수정
				updateMember();
				break;
			case 4: // 전체 출력
				displayAllMember();
				break;
			case 5: // 수정2
				updateMember2();
				break;
			case 6: // 수정3
				updateMember3();
				break;
			case 0:
				System.out.println("작업을 마칩니다...");
				return;
			default:
				System.out.println("작업번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
	// 회원 정보를 수정하는 메서드 ==> 입력한 항목만 수정하기
	private void updateMember3() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		// key값 : 수정할 컬럼명, value값 : 수정할 데이터값
		// 수정할 데이터값이 있을 때만 Map에 추가된다. 
		Map<String, String> dataMap = new HashMap<>();
		
		scan.nextLine(); // 버퍼 비우기
		System.out.println();
		System.out.println("수정할 내용을 입력하세요(수정할 내용이 없으면 enter를 입력하세요)");
		// 아무것도 입력 안하면 "" 입력됨. 스페이스 공백 들어갈 수 있으니 .trim() 이용
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();
		if(!"".equals(newPass)) {
		dataMap.put("mem_pass", newPass);
		}
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.nextLine().trim();
		if(!"".equals(newName)) {
		dataMap.put("mem_name", newName);
		}		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		if(!"".equals(newTel)) {
		dataMap.put("mem_tel", newTel);
		}
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine().trim();
		if(!"".equals(newAddr)) {
		dataMap.put("mem_addr", newAddr);
		}
		try {
			conn = DBUtil.getConnection();
			
			String temp = ""; // SQL문의 set이후에 수정할 컬럼 설정하는 부분이 저장될 변수
//			for (String fieldName : dataMap.keySet()) {
//				if(!"".equals(temp)) {
//					temp += ", ";
//				}
//				temp += fieldName + " = '" + dataMap.get(fieldName)+"'"; // 주의! 데이터 직접 넣어줄 때에는 ''안에 데이터 포함해줘야함!
//			}
//			String sql = "update mymember set " + temp + "where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
			for (String fieldName : dataMap.keySet()) {
				if(!"".equals(temp)) {
					temp += ", ";
				}
				temp += fieldName + " = ? "; 
			}
			String sql = "update mymember set " + temp + "where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				pstmt.setString(num++, dataMap.get(fieldName));
				// num값 먼저 대입하고 증가
			}
			pstmt.setString(num, id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(id + "회원 정보 수정 완료!!!");
			} else {
				System.out.println(id + "회원 정보 수정 실패~~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 회원 정보를 수정하는 메서드 ==> 원하는 항목을 선택해서 수정하기
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		int num;
		String updateField = null;
		String updateFieldTitle = null;
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("1. 비밀번호     2. 회원이름     3. 전화번호     4. 회원주소");
			System.out.println("------------------------------------------------------------");
			System.out.print("수정 항목 입력 >> ");
			num = scan.nextInt();

			switch (num) {
			case 1:
				updateField = "mem_pass";
				updateFieldTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateFieldTitle = "회원이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateFieldTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateFieldTitle = "회원주소";
				break;
			default:
				System.out.println("수정 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요.");
			}
		} while (num < 1 || num > 4);
		scan.nextLine();// 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateFieldTitle + " >> ");
		String updateData = scan.nextLine();

		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set " + updateField + " = ? where mem_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, id);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("수정 작업 성공!!!");
			} else {
				System.out.println("수정 작업 실패~~~");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.next();
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.next();
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		scan.nextLine(); // 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();

		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ "where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, newName);
			pstmt.setString(3, newTel);
			pstmt.setString(4, newAddr);
			pstmt.setString(5, id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(id + "회원 정보 수정 완료!!!");
			} else {
				System.out.println(id + "회원 정보 수정 실패~~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("회원ID가 " + id + "인 회원 정보 삭제 성공!!!");
			} else {
				System.out.println(id + "회원은 없는 회원이거나 삭제 작업에 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayAllMember() {
		System.out.println("---------------------------------------------------");
		System.out.println("  ID   비밀번호  이 름  전화번호         주 소 ");
		System.out.println("---------------------------------------------------");
		try {
			conn = DBUtil2.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");

				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			if (cnt == 0) {
				System.out.println("등록된 회원 정보가 하나도 없습니다...");
			}
			System.out.println("---------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 회원 정보를 추가(insert)하는 메서드
	// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		do {
			System.out.print("회원 ID >> ");
			id = scan.next();
			count = getMemberCount(id);
			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원 ID 입니다.");
				System.out.println("다른 ID를 입력하세요");
			}

		} while (count > 0);

		System.out.print("비밀번호 >> ");
		String pass = scan.next();
		System.out.print("회원이름 >> ");
		String name = scan.next();
		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(id + "회원 정보 추가 완료!!!");
			} else {
				System.out.println(id + "회원 정보 추가 실패~~~");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	// 회원ID를 매개변수로 받아서 해당 회원 ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0; // 반환값이 저장될 변수 선언
		try {
			conn = DBUtil.getConnection();

			String sql = "select count(*) cnt from mymember where mem_id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return count;
	}

	// 자원을 반납하는 메서드
	private void disConnect() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("  1. 자 료 추 가");
		System.out.println("  2. 자 료 삭 제");
		System.out.println("  3. 자료수정 (전체항목수정)");
		System.out.println("  4. 전 체 자 료 출 력");
		System.out.println("  5. 자료수정2 (수정항목선택)");
		System.out.println("  6. 자료수정3 (입력항목만수정)");
		System.out.println("  0. 작 업 끝");
		System.out.println("-------------------------------");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}
}
