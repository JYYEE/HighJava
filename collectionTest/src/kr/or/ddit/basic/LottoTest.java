package kr.or.ddit.basic;

/*
 *로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
  거스름돈도 계산하여 출력한다.)
 * 
 */

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class LottoTest {
	public static void main(String[] args) {
		Random random = new Random();
		HashSet<Integer> set = new HashSet<>();
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("==========================");
			System.out.println("      Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println(" 1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int selectMenu = Integer.parseInt(scanner.nextLine());
			switch (selectMenu) {
			case 1:
				System.out.println("==========================");
				System.out.println(" Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int money = Integer.parseInt(scanner.nextLine());
				int count = money / 1000;
				int remain = money - 1000 * count;
				if (money < 1000) {
					System.out.println();
					System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
					continue;
				} else if (money >= 101000) {
					System.out.println();
					System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
					continue;
				} else {
					System.out.println();
					System.out.println("행운의 로또번호는 아래와 같습니다.");
					for (int i = 1; i <= count; i++) {
						while (set.size() < 6) {
							set.add(random.nextInt(45) + 1);
						}
						System.out.println("로또번호 " + i + " : " + set);
						set.clear();
					}
					System.out.println();
					System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + remain + "원입니다.");
				}
				break;
			case 2:
				System.out.println();
				System.out.println("감사합니다.");
				flag = false;
				break;
			default:
				break;
			}
		}
		scanner.close();
	}
}
