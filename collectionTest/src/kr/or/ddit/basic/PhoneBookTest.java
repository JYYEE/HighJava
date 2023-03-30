package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고 
         Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
         - Map의 구조 : key값은 입력한 '이름'으로 사용하고 
  		                value값은 'Phone클래스의 인스턴스'로 한다.
  		                예) HashMap<String, Phone> 변수명; 		 
  
  		 - 아래의 메뉴를 구현한다.
  		 1. 전화번호 등록
  		 2. 전화번호 수정
  		 3. 전화번호 삭제
  		 4. 전화번호 검색
  		 5. 전화번호 전체 출력
  		 0. 프로그램 종료
  		-----------------------------
 		 
  		 - 삭제, 검색 기능은 '이름'을 입력 받아 처리한다.
  
  실행예시) 
  	-----------------------------
   다음 메뉴를 선택하세요.
  	 1. 전화번호 등록
  	 2. 전화번호 수정
  	 3. 전화번호 삭제
  	 4. 전화번호 검색
  	 5. 전화번호 전체 출력
  	 0. 프로그램 종료
  	-----------------------------
   번호 입력 >> 1
   
   새롭게 등록할 전화번호 정보를 입력하세요.
   이 름 >> 홍길동 ---> 입력할 값
   전화번호 >> 010-1111-1111 ---> 입력할 값
   주 소 >> 대전시 중구 오류동 ---> 입력할 값
 
   '홍길동'씨의 전화번호 정보가 등록되었습니다.
   
   -----------------------------
   다음 메뉴를 선택하세요.
  	 1. 전화번호 등록
  	 2. 전화번호 수정
  	 3. 전화번호 삭제
  	 4. 전화번호 검색
  	 5. 전화번호 전체 출력
  	 0. 프로그램 종료
  	-----------------------------
   번호 입력 >> 1
   
   새롭게 등록할 전화번호 정보를 입력하세요.
   이 름 >> 홍길동 ---> 이미 입력된 값이 중복입력되면
   
   '홍길동'은 이미 등록된 사람입니다.
   
   -----------------------------
   다음 메뉴를 선택하세요.
  	 1. 전화번호 등록
  	 2. 전화번호 수정
  	 3. 전화번호 삭제
  	 4. 전화번호 검색
  	 5. 전화번호 전체 출력
  	 0. 프로그램 종료
  	-----------------------------
   번호 입력 >> 5
   
   ------------------------------------------------------
   번호    이 름     전화번호            주 소
   ------------------------------------------------------
    1 		홍길동	010-1111-1111	대전시 중구 오류동
 	 :		  :			:				:
   ------------------------------------------------------
   출력 완료.
   
   -----------------------------
   다음 메뉴를 선택하세요.
  	 1. 전화번호 등록
  	 2. 전화번호 수정
  	 3. 전화번호 삭제
  	 4. 전화번호 검색
  	 5. 전화번호 전체 출력
  	 0. 프로그램 종료
  	-----------------------------
   번호 입력 >> 0 
   
   프로그램을 종료합니다.
   
 */

public class PhoneBookTest {
	private String name;
	private String tel;
	private String addr;
	private Scanner scan = new Scanner(System.in);
	private HashMap<String, Phone> phoneBook = new HashMap<>();

	public static void main(String[] args) {
		new PhoneBookTest().process();
	}

	public void process() {
		while (true) {
			System.out.println("-----------------------------");
			System.out.println("  ****** PHONE BOOK *******");
			System.out.println("-----------------------------");
			System.out.println(" 다음 메뉴를 선택하세요.");
			System.out.println("  1. 전화번호 등록");
			System.out.println("  2. 전화번호 수정");
			System.out.println("  3. 전화번호 삭제");
			System.out.println("  4. 전화번호 검색");
			System.out.println("  5. 전화번호 전체 출력");
			System.out.println("  0. 프로그램 종료");
			System.out.println("-----------------------------");
			System.out.print("번호입력 >> ");
			int selectMenu = Integer.parseInt(scan.nextLine());
			switch (selectMenu) {
			case 1:
				insertPhone();
				break;
			case 2:
				updatePhone();
				break;
			case 3:
				deletePhone();
				break;
			case 4:
				searchPhone();
				break;
			case 5:
				printPhoneBook();
				break;
			case 0:
				System.out.println();
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력해주세요.");
				break;
			}
		}
	}

	public void insertPhone() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		name = scan.nextLine();
		duplicateName();
		System.out.print("전화번호 >> ");
		tel = scan.nextLine();
		System.out.print("주 소 >> ");
		addr = scan.nextLine();
		phoneBook.put(name, new Phone(name, tel, addr));
		System.out.println();
		System.out.println("'" + name + "' 님의 전화번호 정보가 등록되었습니다.");
		System.out.println();
	}

	public void duplicateName() {
		if (phoneBook.containsKey(name)) {
			System.out.println();
			System.out.println("'" + name + "' 님은 이미 등록된 사람입니다.");
			System.out.println();
			process();
		} else {
			return;
		}
	}

	public void updatePhone() {
		System.out.println();
		System.out.println("수정할 전화번호의 이름을 입력하세요.");
		System.out.print("이 름 >> ");
		name = scan.nextLine();
		if(!phoneBook.containsKey(name)) {
			System.out.println();
			System.out.println("'" +name+"' 님의 정보가 존재하지 않습니다.");
			System.out.println();
			return;
		}
		System.out.println();
		System.out.print("수정할 정보를 입력하세요.");
		System.out.println(" (수정할 정보가 없으면 enter로 넘어가세요.)");
		System.out.println("기존 전화번호 >> " + phoneBook.get(name).getTel());
		System.out.print("수정 전화번호 >> ");
		tel = scan.nextLine();
		if (tel.equals("")) {
			tel = phoneBook.get(name).getTel();
		}
		System.out.println("기존 주소 >> " + phoneBook.get(name).getAddr());
		System.out.print("수정 주소 >> ");
		addr = scan.nextLine();
		if (addr.equals("")) {
			addr = phoneBook.get(name).getAddr();
		}
		phoneBook.put(name, new Phone(name, tel, addr));
		System.out.println();
		System.out.println("'" + name + "' 님의 정보가 수정되었습니다.");
		System.out.println();
	}

	public void deletePhone() {
		System.out.println();
		System.out.println("삭제할 전화번호의 이름을 입력하세요.");
		System.out.print("이 름 >> ");
		name = scan.nextLine();
		if(!phoneBook.containsKey(name)) {
			System.out.println();
			System.out.println("'" +name+"' 님의 정보가 존재하지 않습니다.");
			System.out.println();
			return;
		}
		phoneBook.remove(name);
		System.out.println();
		System.out.println("'" + name + "' 님의 정보가 삭제되었습니다.");
		System.out.println();

	}

	public void searchPhone() {
		System.out.println();
		System.out.println("검색할 전화번호의 이름을 입력하세요.");
		System.out.print("이 름 >> ");
		name = scan.nextLine();
		Phone phone = phoneBook.get(name);
		if(!phoneBook.containsKey(name)) {
			System.out.println();
			System.out.println("'" +name+"' 님의 정보가 존재하지 않습니다.");
			System.out.println();
			return;
			}
		System.out.println();
		System.out.println("'" + name + "' 검색 목록");
		System.out.println("------------------------------------------------------");
		System.out.println(" 번호    이 름     전화번호            주 소");
		System.out.println("------------------------------------------------------");
		System.out.printf("  %d\t%s\t %s\t  %s\n", 1, phone.getName(), phone.getTel(), phone.getAddr());
		System.out.println("------------------------------------------------------");

	}

	public void printPhoneBook() {
		System.out.println();
		System.out.println("전화번호 전체 목록");
		System.out.println("------------------------------------------------------");
		System.out.println(" 번호    이 름     전화번호            주 소");
		System.out.println("------------------------------------------------------");
		Set<String> keySet = phoneBook.keySet();
		Iterator<String> iterator = keySet.iterator();
		int no = 0;
		while (iterator.hasNext()) {
			no++;
			String key = iterator.next();
			Phone phone = phoneBook.get(key);
			System.out.printf("  %d\t%s\t %s\t  %s\n", no, phone.getName(), phone.getTel(), phone.getAddr());
		}
		System.out.println("------------------------------------------------------");
	}

}

//	이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고 
//		 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
class Phone {
	private String name;
	private String tel;
	private String addr;

	public Phone(String name, String tel, String addr) {
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return String.format("%s\t%s\t%\t", name, tel, addr);
	}

}
