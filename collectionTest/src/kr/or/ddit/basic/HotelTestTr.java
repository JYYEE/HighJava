package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelTestTr {
	private Map<Integer, RoomTr> hotelMap;
	private Scanner scan;

	// 생성자
	public HotelTestTr() {
		hotelMap = new HashMap<>();
		scan = new Scanner(System.in);

		// 객실 초기화하기
		for (int i = 2; i <= 4; i++) {
			String roomType = null;

			switch (i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트룸";
				break;

			}
			for (int j = 1; j <= 9; j++) {
				int roomNum = i * 100 + j;
				hotelMap.put(roomNum, new RoomTr(roomNum, roomType));
			}
		}
	} // 생성자 끝

	public static void main(String[] args) {
		new HotelTestTr().hotelStart();
	}

	public void hotelStart() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				chechkOut();
				break;
			case 3: // 객실상태
				printRoomState();
				break;
			case 4:
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				System.out.println();
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}

	}
	// 객실 상태 출력하기
	private void printRoomState() {
		// 방번호를 오름차수능로 출력하기 위해서 방번호(Map의 key값)만 List에 넣어서 정렬한다.
		ArrayList<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
		Collections.sort(roomNumList); // 정렬
		
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");
		
		//List에서 방 번호를 하나씩 꺼내와 Map에서 해당 방번호에 짝이되는 Room객체를 구해서 출력한다.
		for (Integer roomNum : roomNumList) {
			RoomTr r = hotelMap.get(roomNum);
			System.out.print(r.getNum() +"\t");
			System.out.print(r.getType() +"\t\t");
			
			System.out.println(r.getName() == null ? "-" : r.getName());
			
		}
		System.out.println("----------------------------------------------");
	}

	// 체크아웃 메서드
	private void chechkOut() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int num = scan.nextInt();
		// 입력한 방이 존재하는지 여부 검사
		// (Map의 key값이 방번호이므로 해당 방번호가 key값에 존재하는지 여부 검사)
		if (!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		// 입력한 방번호 객실에 손님이 없는지 여부 검사
		if (hotelMap.get(num).getName() == null) {
			System.out.println(num + "호 객실에 체크인 한 손님이 없습니다...");
			return;
		}
		String name = hotelMap.get(num).getName(); //현재 투숙객 이름 구하기
		
		// 체크아웃 작업은 해당 객실의 투숙객 이름에 null값을 넣어주면 된다.
		hotelMap.get(num).setName(null);
		
		System.out.println(num + "호 객실의 "+ name + "님이 체크 아웃을 완료했습니다.");
	}

	// 체크인 메서드
	private void checkIn() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");

		int num = scan.nextInt();

		// 입력한 방이 존재하는지 여부 검사
		// (Map의 key값이 방번호이므로 해당 방번호가 key값에 존재하는지 여부 검사)
		if (!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		} // 여기 통과하면 호텔 번호 존재
			// 입력한 방번호 객실에 손님이 있는지 여부 검사
		if (hotelMap.get(num).getName() != null) {
			System.out.println(num + "호 객실에 이미 손님이 있습니다...");
			return;
		}
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();

		// 입력받은 투숙객 이름을 해당 객실의 투숙객 명단에 넣는다.
		hotelMap.get(num).setName(name);
		System.out.println(num + "호 객실에 " + name + "씨의 체크인이 완료되었습니다.");

	}

	// 메뉴를 출력하고 사용자가 입력한 작업 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택 >> ");
		return scan.nextInt();
	}
}

class RoomTr {
	private int num; // 방번호
	private String type; // 방 종류
	private String name; // 투숙객 이름

	// 생성자
	// 이름은 나중에 입력되는 값이므로 제외하고 생성자 만듦.
	public RoomTr(int num, String type) {
		super();
		this.num = num;
		this.type = type;
	}

	public RoomTr() {
		super();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoomTr [num=" + num + ", type=" + type + ", name=" + name + "]";
	}

}