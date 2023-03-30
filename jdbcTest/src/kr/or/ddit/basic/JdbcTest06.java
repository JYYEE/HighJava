package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

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
public class JdbcTest06 {
	private Scanner scan = new Scanner(System.in);
	private Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String id;
	private String pass;
	private String name;
	private String tel;
	private String addr;

	public static void main(String[] args) {
		new JdbcTest06().start();
	}

	public void start() {
		while (true) {
			System.out.println("***** 회원 관리 프로그램 *****");
			int choice = displayMenu();
			switch (choice) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				select();
				break;
			case 5:
				updateMember2();
				break;
			case 6:
				updateMember3();
				break;
			case 0:
				System.out.println("작업이 종료되었습니다.");
				return;
			default:
				System.out.println("잘못된 번호 입니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	// 회원 정보를 수정하는 메서드 ==> 입력한 항목만 수정하기
	private void updateMember3() { // 프로젝트때 한 거. 했는데 map이용해서 간단하게 가능. --> 해보기
		int count = 0;
		try {
			do {
				System.out.print("수정할 ID 입력 >> ");
				id = scan.nextLine();
				String sqlDuplicateId = "select count(*) from mymember where mem_id = ?";
				pstmt = conn.prepareStatement(sqlDuplicateId);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
				if (count == 0) {
					System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
					System.out.println();
				}
			} while (count == 0);
			
			HashMap<String, String> updateContent = new HashMap<>();
			updateContent.put("mem_id", id);
			
			
			String sql = "select * from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			pass = rs.getString(2);
			name = rs.getString(3);
			tel = rs.getString(4);
			addr = rs.getString(5);

			System.out.println();
			System.out.println("수정할 내용을 입력하세요(수정할 내용이 없으면 enter를 입력하세요)");
			System.out.print("새로운 비밀번호 >> ");
			String newPass = scan.nextLine();
			if (!newPass.equals("")) {
				updateContent.put("mem_pass", newPass);
				newPass = pass;
			}
			System.out.print("새로운 회원이름 >> ");
			String newName = scan.nextLine();
			if (!newName.equals("")) {
				updateContent.put("mem_name", newName);
				newName = name;
			}
			System.out.print("새로운 전화번호 >> ");
			String newTel = scan.nextLine();
			if (!newTel.equals("")) {
				updateContent.put("mem_tel", newTel);
				newTel = tel;
			}
			System.out.print("새로운 회원주소 >> ");
			String newAddr = scan.nextLine();
			if (!newAddr.equals("")) {
				updateContent.put("mem_addr", newAddr);
				newAddr = addr;
			}

			conn = DBUtil.getConnection();
			String sql2 = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ "where mem_id = ?";
			pstmt = conn.prepareStatement(sql2);
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
		}
	}

	// 회원 정보를 수정하는 메서드 ==> 원하는 항목을 선택해서 수정하기
	private void updateMember2() {// 미완
		int count = 0;
		try {
			do {
				System.out.print("수정할 ID 입력 >> ");
				id = scan.nextLine();
				String sqlDuplicateId = "select count(*) from mymember where mem_id = ?";
				pstmt = conn.prepareStatement(sqlDuplicateId);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
				if (count == 0) {
					System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
					System.out.println();
				}
			} while (count == 0);

			System.out.println("*** 수정할 정보 선택 ***");
			System.out.println("1. 비밀번호");
			System.out.println("2. 이름");
			System.out.println("3. 전화번호");
			System.out.println("4. 주소");
			System.out.println("0. 이전");
			System.out.print("선택 >> ");
			int choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1:
				System.out.print("수정할 비밀번호 입력 >> ");
				String newPass = scan.nextLine();
				String sql = "update mymember set mem_pass = ?";
				pstmt = conn.prepareStatement(sql);

				break;
			case 2:
				System.out.print("수정할 이름 입력 >> ");
				String newName = scan.nextLine();
				break;
			case 3:
				System.out.print("수정할 전화번호 입력 >> ");
				String newTel = scan.nextLine();
				break;
			case 4:
				System.out.print("수정할 주소 입력 >> ");
				String newAddr = scan.nextLine();
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}

			String sqlUpdate = "UPDATE mymember SET mem_pass=?, mem_name=?, mem_tel=?, mem_addr=? where mem_id = ?";
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "의 회원정보가 수정되었습니다.");
				System.out.println();
			} else {
				System.out.println("수정 실패!!!");
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void select() {
		try {
			System.out.println();
			System.out.println("***** 전체 회원 목록 *****");
			System.out.println("====================================================================");
			System.out.println("ID\t비밀번호\t이름\t전화번호\t주소");
			System.out.println("====================================================================");
			String sqlSelect = "select * from mymember";
			pstmt = conn.prepareStatement(sqlSelect);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t"
						+ rs.getString(4) + "\t" + rs.getString(5));
			}
			System.out.println("====================================================================");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update() {
		try {
			// id 존재 유무 확인
			int count = 0;
			do {
				System.out.print("수정할 ID 입력 >> ");
				id = scan.nextLine();
				String sqlDuplicateId = "select count(*) from mymember where mem_id = ?";
				pstmt = conn.prepareStatement(sqlDuplicateId);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
				if (count == 0) {
					System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
					System.out.println();
				}
			} while (count == 0);

			System.out.print("수정할 비밀번호 입력 >> ");
			pass = scan.nextLine();
			System.out.print("수정할 이름 입력 >> ");
			name = scan.nextLine();
			System.out.print("수정할 전화번호 입력 >> ");
			tel = scan.nextLine();
			System.out.print("수정할 주소 입력 >> ");
			addr = scan.nextLine();

			String sqlUpdate = "UPDATE mymember SET mem_pass=?, mem_name=?, mem_tel=?, mem_addr=? where mem_id = ?";
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "의 회원정보가 수정되었습니다.");
				System.out.println();
			} else {
				System.out.println("수정 실패!!!");
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete() {
		try {

			int count = 0;
			do {
				System.out.print("삭제할 ID 입력 >> ");
				id = scan.nextLine();
				String sqlDuplicateId = "select count(*) from mymember where mem_id = ?";
				pstmt = conn.prepareStatement(sqlDuplicateId);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
				if (count == 0) {
					System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
					System.out.println();
				}
			} while (count == 0);

			String sqlDelete = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setString(1, id);
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "의 회원정보가 삭제되었습니다.");
				System.out.println();
			} else {
				System.out.println("삭제 실패!!!");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert() {
		int count = 0;
		try {
			do {
				System.out.print("ID 입력 >> ");
				id = scan.nextLine();
				String sqlDuplicateId = "select count(*) from mymember where mem_id = ?";
				pstmt = conn.prepareStatement(sqlDuplicateId);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count == 1) {
					System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
					System.out.println();
				}
			} while (count == 1);

			System.out.println("사용 가능한 아이디입니다.");
			System.out.print("비밀번호 입력 >> ");
			pass = scan.nextLine();
			System.out.print("이름 입력 >> ");
			name = scan.nextLine();
			System.out.print("전화번호 입력 >> ");
			tel = scan.nextLine();
			System.out.print("주소 입력 >> ");
			addr = scan.nextLine();

			String sqlInsert = "insert INTO mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("회원으로 등록되었습니다.");
				System.out.println();
			} else {
				System.out.println("등록 실패!!!");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int displayMenu() {
		System.out.println("--------------------");
		System.out.println("  1. 자 료 추 가");
		System.out.println("  2. 자 료 삭 제");
		System.out.println("  3. 자료수정 (전체항목수정)");
		System.out.println("  4. 전 체 자 료 출 력");
		System.out.println("  5. 자료수정2 (수정항목선택)");
		System.out.println("  6. 자료수정3 (입력항목만수정)");
		System.out.println("  0. 작 업 끝");
		System.out.println("--------------------");
		System.out.print("선택 >> ");
		return Integer.parseInt(scan.nextLine());
	}

}
