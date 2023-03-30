package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		// 리스트 안에 내부정렬 기준 넣기
		ArrayList<Member> memList = new ArrayList<>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("정렬전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");

		Collections.sort(memList);// <Member>는 내부정렬기준이 없어서 sort할 수 없음. ==> 내부 정렬 기준 추가 필요

		System.out.println("정렬후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
		
		
		// 회원 번호를 기준으로 내림차순 정렬하여 출력하시오.
		// ==> 외부 정렬 기준 클래스를 작성하여 처리한다. (클래스 이름 : SortNumDesc)
		
		System.out.println("회원 번호의 내림차순 정렬후...");
		Collections.sort(memList, new SortNumDesc()); // 외부정렬 클래스로 정렬할 때는 (list, 외부정렬) 형태로 작성.01 
		for (Member mem : memList) {
			System.out.println(mem);
		}
	}

}

// Member 클래스의 '회원이름'을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
// ==> Comparable 인터페이스를 구현한다.
class Member implements Comparable<Member> {
	private int num; // 회원번호
	private String name; // 회원이름
	private String tel; // 전화번호

	// 생성자
	public Member(int num, String name, String tel) {
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	// getter, setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	// toString
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {// 비교해야할 기준이 없으면 this. 즉, 생성자와 비교하게 됨
		// 회원이름의 오름차순
		return this.getName().compareTo(mem.getName());
	}
}
// Member의 회원번호(num)의 내림차순으로 정렬하는 외부 정렬 기준 클래스
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		 * if(mem1.getNum() > mem2.getNum()) {// 내림차순 형태
			return -1; // 자리바꾸지 않음
		} else if(mem1.getNum() < mem2.getNum()) {// 오름차순 형태
			return 1;// 자리 바꿈
		} else {
			return 0;
		}
		*/
		
		//Wrapper클래스를 이용하는 방법1
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) *(-1); 
//		compareTo가 기본 오름차순이므로 자리 바꾸게 해주기 위해서 -1을 곱한다.
		
		//Wrapper클래스를 이용하는 방법2
//		return Integer.compare(mem1.getNum(), mem2.getNum())*(-1);
		
		return mem2.getNum() - mem1.getNum() ; //값이 양수일 때만 가능. 내림차순 표현. 
	}
}









//class SortNumDesc implements Comparator<Member>{
//
//	@Override
//	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum()>mem2.getNum()) {
//			return -1;
//		} else if(mem1.getNum()<mem2.getNum()) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//}