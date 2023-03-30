package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/*
 *로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
  거스름돈도 계산하여 출력한다.)
 * 
 */

public class LottoTestTr {
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
//		LottoTestTr test = new LottoTestTr();
//		test.lottoStart();
//		위의 두 줄을 아래 한줄로 표현 가능
		new LottoTestTr().lottoStart();
	}

	public void lottoStart() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println();
				System.out.println("감사합니다.");
//				break; 여기 switch 문만 끝냄
				return; // 메소드를 끝냄
			default:
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("1 또는 2를 입력하세요...");
			}
		}
	}
	// 로또 판매를 처리하는 메서드
	private void buyLotto() {
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 >> ");
		
		int money = scan.nextInt();
		System.out.println();
		if(money<1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			return;
		} else if(money>=101000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			return;
		} 
		// 로또 번호 만들기
		HashSet<Integer> lottoSet = new HashSet<>();
		Random rnd = new Random();
		// 구매할 매수 계산
		int count = money/1000;
		
		for(int i=1; i<=count; i++) {
			while(lottoSet.size()<6) {
				lottoSet.add(rnd.nextInt(45)+1);
			}
			ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
			Collections.sort(lottoList);
			// 처음 6개 뽑은 이후에는 set의 개수가 6개임을 만족했으므로 새롭게 뽑지 않음 ==> clear 필요
			System.out.print("로또번호 " + i+ " : " );
			for(int j=0; j<lottoList.size(); j++) {
				if(j>0) {
					System.out.print(", "); // 쉼표 출력
				}
				System.out.print(lottoList.get(j)); // 
			}
			System.out.println();// 줄바꿈용
			
			lottoSet.clear(); // 리스트는 계속 새롭게 객체를 생성하므로 비워줄 필요 없음.
		} // for(i) 문 끝
		System.out.println("받은 금액은 " + money +"원이고 거스름돈은 " + (money%1000) + "원 입니다.");
			
		
	}
	// 메뉴를 출력하고 사용자가 입력한 값을 반환하는 메서드
	private int displayMenu() {
		System.out.println("==========================");
		System.out.println("      Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("  1. Lotto 구입");
		System.out.println("  2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print(" 메뉴선택 >> ");

		return scan.nextInt();
	}
}
