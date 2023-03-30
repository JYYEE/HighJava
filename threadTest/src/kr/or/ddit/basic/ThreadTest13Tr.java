package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;
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

public class ThreadTest13Tr {
	public static void main(String[] args) {
		HorseTr[] horseArr = new HorseTr[] { 
				new HorseTr("01번말"), new HorseTr("02번말"), new HorseTr("03번말"),
				new HorseTr("04번말"), new HorseTr("05번말"), new HorseTr("06번말"), 
				new HorseTr("07번말"), new HorseTr("08번말"),
				new HorseTr("09번말"), new HorseTr("10번말") };
		GameState gs = new GameState(horseArr);

		for (HorseTr h : horseArr) {
			h.start();
		}
		gs.start(); // 말들의 현재 위치를 출력하는 쓰레드 시작

		for (HorseTr h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		// 등수의 오름차순으로 정렬하기
		Arrays.sort(horseArr); // 배열 정렬하기
		
		for (HorseTr h : horseArr) {
			System.out.println(h);
		}
	}
}

class HorseTr extends Thread implements Comparable<HorseTr> {
	private String horseName; // 말이름
	private int rank; // 순위
	private int location; // 현재위치
	public static int currentRank = 0; // 말의 등수를 구할 때 사용할 변수

	// 생성자
	public HorseTr(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "는(은) " + rank + "등 입니다.";
	}

	// 등수의 오름차순 정렬의 내부 정렬 기준
	@Override
	public int compareTo(HorseTr horse) {
		return Integer.compare(this.rank, horse.getRank());
	}

	@Override
	public void run() {
		Random rnd = new Random();
		for (int i = 1; i <= 50; i++) {
			this.location = i; // 말의 현재 위치 저장
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 한 마리의 말의 경주가 끝나면 현재의 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
	}

}

/* 경기 중간 중간에 각 말들의 위치를 아래와 같이 출력하는 쓰레드
 * 예시) 
 * 말이름01 : ----->-------------------------------------------- 
 * 말이름02 : ----------->-------------------------------------- 
 * 말이름03 : -->----------------------------------------------- 
 * ... 
 * 말이름10 : ----------------------------->--------------------
 */
class GameState extends Thread {
	private HorseTr[] horseArr; // 경주에 참가한 말들의 정보가 저장될 배열 변수 선언

	// 생성자
	public GameState(HorseTr[] horseArr) {
		super();
		this.horseArr = horseArr;
	}

	@Override
	public void run() {
		while (true) {
			// 모든 말의 경주가 끝났는지 여부 검사
			if (HorseTr.currentRank == horseArr.length) {
				break;
			}
			for(int i=1;i<=15;i++) {
				System.out.println();
			}
			
			for (int i = 0; i < horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");

				for (int j = 1; j <= 50; j++) {
					if (horseArr[i].getLocation() == j) { // 말의 현재위치 찾기
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}