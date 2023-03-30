package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {
	private Scanner scan = new Scanner(System.in);
	private HashMap<Integer, Room> hotelRoomMap = new HashMap<>();
	
	
	public HotelTest() {
		for(int i= 200; i<=400; i +=100) {
			for(int j=1; j<=9; j++) {
//				System.out.println(i+j);
				Room room = new Room();
				room.setRoomNum(i+j);
				hotelRoomMap.put(i+j, room);
			}
		}
		for (int roomNum : hotelRoomMap.keySet()) {
			if(roomNum>=201 && roomNum <=209) {
				hotelRoomMap.get(roomNum).setRoomType("싱글룸") ;
			} else if(roomNum>=301 && roomNum <=309) {
				hotelRoomMap.get(roomNum).setRoomType("더블룸") ;
			} else if(roomNum>=401 && roomNum <=409) {
				hotelRoomMap.get(roomNum).setRoomType("스위트룸");
			}
		}
	}

	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}

	public void hotelStart() {
//		System.out.println(hotelRoomMap);
		
	
		System.out.println("*********************************************");
		System.out.println("      호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		
		while (true) {
			int selectMenu = displayMenu();
			switch (selectMenu) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				checkRoom();
				break;
			case 4:
				System.out.println("*********************************************");
				System.out.println("           호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요. ");
			}
		}
	}
	private void checkRoom() {
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	  방 종류     투숙객 이름");
		System.out.println("----------------------------------------------");
		Set<Integer> keySet = hotelRoomMap.keySet();
		ArrayList<Integer> keyList = new ArrayList<>(keySet);
		Collections.sort(keyList);
		for (Integer roomNum : keyList) {
			if(hotelRoomMap.get(roomNum).getCustomerName()==null) {
				System.out.printf("  %d\t  %s\t%s\n",roomNum, hotelRoomMap.get(roomNum).getRoomType(),"-");				
			} else {
				System.out.printf("  %d\t  %s\t%s\n",roomNum, hotelRoomMap.get(roomNum).getRoomType(),hotelRoomMap.get(roomNum).getCustomerName());
				
			}
		}
	}

	private int displayMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("		어떤 업무를 하시겠습니까?");
		System.out.println("-----------------------------------------------------------");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택 >> ");
		return Integer.parseInt(scan.nextLine());
		
	}

	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("		체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int roomNum = Integer.parseInt(scan.nextLine());
		if (!(roomNum >= 201 && roomNum <= 209 || roomNum >= 301 && roomNum <= 309
				|| roomNum >= 401 && roomNum <= 409)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		} else if (hotelRoomMap.get(roomNum).getCustomerName() == null) {
			System.out.println(roomNum + "호 객실에는 체크인 한 손님이 없습니다.");
			return;
		}
		System.out.println();
		System.out.println(roomNum + "호 객실의 "+hotelRoomMap.get(roomNum).getCustomerName() +"님 체크아웃이 완료하였습니다.");
//		hotelRoomMap.remove(roomNum);//이렇게 하면 아예 roomNum없어짐
		hotelRoomMap.get(roomNum).setCustomerName(null);
	}

	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("		체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");
		int roomNum = Integer.parseInt(scan.nextLine()) ;
		if(!(roomNum>=201 && roomNum <=209 ||roomNum>=301 && roomNum <=309 ||roomNum>=401 && roomNum <=409)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		} else if(!(hotelRoomMap.get(roomNum).getCustomerName() == null)) {
			System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			return;
		} 
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String customerName = scan.nextLine();
		hotelRoomMap.get(roomNum).setCustomerName(customerName);
		System.out.println(); 
		System.out.println(customerName + "님 체크인이 완료되었습니다.");
	}

}

class Room {
	private int roomNum;
	private String roomType;
	private String customerName;
	
	
	public Room() {
		if(roomNum>=201 && roomNum <=209) {
			this.roomType = "싱글룸" ;
		} else if(roomNum>=301 && roomNum <=309) {
			this.roomType= "더블룸" ;
		} else if(roomNum>=401 && roomNum <=409) {
			this.roomType = "스위트룸" ;
		}
	}


	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", customerName=" + customerName + "]";
	}

}