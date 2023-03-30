package kr.or.ddit.basic;
/*
* 문제)
* 10마리의 말들이 경주하는 프로그램 작성하기
* 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
* 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
* 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준을 갖고 있다.
* (Comparable 인터페이스 구현)
* 
* 경기 구간은 1~50구간으로 되어 있다. 
* 경기가 끝나면 등수 순으로 출력한다.
* 
* 경기 중간 중간에 각 말들의 위치를 아래와 같이 출력한다.
* (각 말 마다 쓰레드 구현. 현재 위치 출력하는것도 하나의 쓰레드로 따로 만들어야 함)
* 말 쓰레드 10개 출력용 1개 
* 경기 끝나면 등수가 출력되도록
* 
* 실행예시) - 1개가 1구간
* 말이름01 : ----->--------------------------------------------
* 말이름02 : ----------->--------------------------------------
* 말이름03 : -->-----------------------------------------------
* ...
* 말이름10 : ----------------------------->--------------------
*/

public class ThreadTest13 {
	public static void main(String[] args) {
		Horse[] horseList = new Horse[] { new Horse("01"), new Horse("02"), new Horse("03"), new Horse("04"),
				new Horse("05"), new Horse("06"), new Horse("07"), new Horse("08"), new Horse("09"), new Horse("10") };
		for (Horse horse : horseList) {
			horse.start();
			
		}
		for (Horse horse : horseList) {
			try {
				horse.join();
			
			} catch (InterruptedException e) {
			}
		}
		System.out.println("-- 결과 --");
		System.out.println("순위");
	}

}

class Horse extends Thread implements Comparable<Horse> {
	private String hname;
	private int hrank;
	private int hlocation;
	public static int num;

	public Horse(String hname) {
		this.hname = hname;
	}

	public int getHlocation() {
		return hlocation;
	}

	public void setHlocation(int hlocation) {
		this.hlocation = hlocation;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public int getHrank() {
		return hrank;
	}

	public void setHrank(int hrank) {
		this.hrank = hrank;
	}

	@Override
	public int compareTo(Horse horse) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 말이 달린다.


	// 출력용 class PrintLocation extends Thread { private List<Horse> hlist;



}
