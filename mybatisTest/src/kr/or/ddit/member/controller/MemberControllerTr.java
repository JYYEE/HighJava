package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImplTr;
import kr.or.ddit.vo.MemberVO;


public class MemberControllerTr {
	private Scanner scan;
	private IMemberService service; // Service객체 변수 선언

	// 생성자
	public MemberControllerTr() {
		scan = new Scanner(System.in);
		service = new MemberServiceImplTr(); // Service객체 생성
	}

	public static void main(String[] args) {
		new MemberControllerTr().startMember();
	}

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

	private void displayAllMember() {
		System.out.println("---------------------------------------------------");
		System.out.println("  ID   비밀번호  이 름  전화번호         주 소 ");
		System.out.println("---------------------------------------------------");

		List<MemberVO> memList = service.getAllMember();
		if (memList == null || memList.size() == 0) {
			System.out.println("등록된 회원 정보가 하나도 없습니다...");
		} else {
			// 반복문을 이용하여 출력한다.
			for (MemberVO memVO : memList) {
				String memId = memVO.getMem_id();
				String memPass = memVO.getMem_pass();
				String memName = memVO.getMem_name();
				String memTel = memVO.getMem_tel();
				String memAddr = memVO.getMem_addr();
				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
		}
		System.out.println("---------------------------------------------------");
		System.out.println("출력 끝...");
	}
	
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목 1개만 수정
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = service.getMemberCount(id);
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
		
		// 구성한 데이터를 Map에 추가한다. 
		// Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId) 
		Map<String,String> paramMap = new HashMap<>();  // Map객체 생성
		paramMap.put("field", updateField); // 수정할 컬럼명
		paramMap.put("data", updateData);  // 수정할 데이터
		paramMap.put("memId", id); // 검색할 회원ID
		
		
		int cnt = service.updateMember2(paramMap);
		if (cnt > 0) {
			System.out.println(id + "회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + "회원 정보 수정 실패~~~");
		}
	}
	
	
	// 회원 정보를 수정하는 메서드 ==> 입력한 항목만 수정
	private void updateMember3() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = service.getMemberCount(id);
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
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();
		if (!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.nextLine().trim();
		if (!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		if (!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine().trim();
		if (!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		if(dataMap.size() == 0) {
			System.out.println("수정할 내용을 하나도 입력하지 않았습니다.");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		// Map에 검색할 회원ID 값을 'memId' 키값으로 넣어준다.
		dataMap.put("memId", id);
		int cnt = service.updateMember3(dataMap);
		if (cnt > 0) {
			System.out.println(id + "회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + "회원 정보 수정 실패~~~");
		}

	}

	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = service.getMemberCount(id);
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

		// 입력한 자료를 VO객체에 저장한다.
		MemberVO memVO = new MemberVO(); // VO 객체 생성
		memVO.setMem_id(id);
		memVO.setMem_pass(newPass);
		memVO.setMem_name(newName);
		memVO.setMem_tel(newTel);
		memVO.setMem_addr(newAddr);

		int cnt = service.updateMember(memVO);

		if (cnt > 0) {
			System.out.println(id + "회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + "회원 정보 수정 실패~~~");
		}

	}

	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		do {
			System.out.print("회원 ID >> ");
			id = scan.next();
			count = service.getMemberCount(id);
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

		// 입력한 자료를 VO객체에 담는다.
		MemberVO memVO = new MemberVO();
		memVO.setMem_id(id);
		memVO.setMem_pass(pass);
		memVO.setMem_name(name);
		memVO.setMem_tel(tel);
		memVO.setMem_addr(addr);

		// Service의 insertMember() 메서드를 호출해서 실행한다.
		int cnt = service.insertMember(memVO);
		if (cnt > 0) {
			System.out.println(id + "회원 정보 추가 완료!!!");
		} else {
			System.out.println(id + "회원 정보 추가 실패~~~");
		}

	}

	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int cnt = service.deleteMember(id);

		if (cnt > 0) {
			System.out.println(id + "회원 정보 삭제 완료!!!");
		} else {
			System.out.println(id + "회원 정보 삭제 실패~~~");
		}
	}

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
