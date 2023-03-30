package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
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
  		 
  		 - 추가 조건)
  		 1) '6. 전화번호 저장'메뉴를 추가하고 구현한다.
  		 	(저장 파일명은 'phoneData.bin')
  		 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
  		 3) 프로그램을 종료할 때 Map의 데이터의 변화가 있으면 
  		 	(데이터의 추가, 수정, 삭제 작업후 저장하지 않은 상태) 자료를 저장한 후 종료되도록 한다.
  		 
  
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
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;

	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}

	public void phoneBookStart() {
		phoneBookMap = new HashMap<>();
		scan = new Scanner(System.in);

		System.out.println("*************************************");
		System.out.println("    전 화 번 호 관 리 프 로 그 램");
		System.out.println("*************************************");

		if (!(new File("d:/d_other/phoneData.bin").exists())) {
			System.out.println("저장된 데이터가 존재하지 않습니다.");
		} else {
			read();
		}

		boolean flag = false;
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1:
				insert();
				flag = true;
				break;
			case 2:
				update();
				flag = true;
				break;
			case 3:
				delete();
				flag = true;
				break;
			case 4:
				search();
				break;
			case 5:
				displayAll();
				break;
			case 6:
				save();
				flag = false;
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				if (flag == true) {
					save();
				}
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 선택하세요.");
			}
		}
	}

	private void read() {
		try {
			// 입력용
			FileInputStream fin = new FileInputStream("d:/d_other/phoneData.bin");
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream oin = new ObjectInputStream(bin);

			Object obj;

			while ((obj = oin.readObject()) != null) {
				Phone phone = (Phone) obj;
				phoneBookMap.put(phone.getName(), phone);
			}
			oin.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void save() {

		try {
			// 출력용
			FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.bin"); // 기반스트림
			BufferedOutputStream bout = new BufferedOutputStream(fout); // 기반스트림을 돕는 보조스트림
			ObjectOutputStream oout = new ObjectOutputStream(bout); // 보조스트림을 돕는 보조스트림

			// 쓰기 작업(저장 작업)
			for (Phone phone : phoneBookMap.values()) {
				oout.writeObject(phone);
			}

			oout.writeObject(null);
			oout.flush();
			oout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메뉴를 출력하고 사용자가 입력한 작업 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println("-----------------------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 6. 전화번호 저장");
		System.out.println(" 0. 프로그램 종료");
		System.out.println("-----------------------------");
		System.out.print("번호입력 >> ");
		return Integer.parseInt(scan.nextLine());
	}

	/*
	 * Scanner.~~ 사용법 차이 Scanner의 next(), nextInt(), nextDouble() 등... ==> 사이띄기,
	 * Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
	 * 
	 * Scanner의 nextLine() ==> 한 줄 단위로 입력한다. (즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서
	 * Enter키를 뺀 나머지를 반환한다.)
	 * 
	 * - 컴퓨터의 입력 작업은 입력된 데이터를 입력 버퍼에 저장하고 이것을 차례로 꺼내가는 방법으로 처리된다. - 그래서 next(),
	 * nextInt() 등과 같은 메서드를 실행한 후에 nextLine()을 사용할 때는 입력 버퍼를 비워줘야 한다. ==> 방법 :
	 * nextLine()을 한 번 더 사용한다.
	 * 
	 */

	// 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.nextLine();

		// 이미 등록된 사람인지 여부 검사
		if (phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		System.out.print("전화번호 >> ");
		String tel = scan.nextLine();

		System.out.print("주 소 >> ");
		String addr = scan.nextLine();

//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
//		위의 두 줄을 밑의 1줄로 표현 가능
		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + "씨의 전화번호 정보 등록 완료!!");
	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.nextLine();

		// 수정할 대상이 등록되어 있지 않으면 수정 작업을 못한다.
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 전화번호 정보가 등록되지 않은 사람입니다...");
			return;
		}

		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine();

		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine();

		// 같은 key값에 새로운 전화번호 정보를 저장한다. ==> 수정작업
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));

		System.out.println(name + "씨의 전화번호 정보가 변경되었습니다.");

	}

	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.nextLine();

		// 삭제할 대상이 있는지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 전화번호 정보가 등록되지 않은 사람입니다...");
			return;
		}

		phoneBookMap.remove(name);

		System.out.println(name + "씨의 전화번호의 정보가 삭제되었습니다.");
	}

	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.nextLine();
		// 검색할 대상이 있는지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 전화번호 정보가 등록되지 않은 사람입니다...");
			return;
		}

		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("---------------------------------");
		System.out.println("이 름 : " + p.getName());
		System.out.println("전 화 : " + p.getTel());
		System.out.println("주 소 : " + p.getAddr());
		System.out.println("---------------------------------");
		System.out.println();

	}

	// 전화번호 정보 전체를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("번호\t이 름\t전화번호\t주 소");
		System.out.println("------------------------------------------------------");

		Set<String> phoneKeySet = phoneBookMap.keySet();
		if (phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int num = 0;
			for (String name : phoneKeySet) { // Set 개수만큼 반복
				num++;
				Phone p = phoneBookMap.get(name); // value값(PhoneTr객체) 구하기
				System.out.println(num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}
		}
		System.out.println("------------------------------------------------------");
		System.out.println("출력 끝...");
	}
}

// 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스 작성
class Phone implements Serializable {
	private String name;
	private String tel;
	private String addr;

	public Phone() {
	}

	public Phone(String name, String tel, String addr) {
		super();
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
		return "이름 : " + name + ", 번호 : " + tel + ", 주소 : " + addr + "]";
	}

}
