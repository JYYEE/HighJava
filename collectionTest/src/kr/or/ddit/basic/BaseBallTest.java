package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/*
 * 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 *       (컴퓨터의 숫자는 난수를 이용하여 구한다.(1~9 사이의 값 3개, 중복값x)) => Set이용
 *       (스트라이크는 S, 볼은 B로 나타낸다.) => List 이용
 * 예시) 
 * 		컴퓨터 난수 ==> 9 5 7
 * 실행예시)
 * 		숫자 입력 >> 3 5 6 
 * 		3 5 6 ==> 1S 0B 
 * 		숫자 입력 >> 7 8 9 
 * 		7 8 9 ==> 0S 2B
 * 		숫자 입력 >> 9 7 5 
 * 		9 7 5 ==> 1S 2B
 * 		숫자 입력 >> 9 5 7 
 * 		9 5 7 ==> 3S 0B 
 * 
 * 		축하합니다...
 * 		당신은 4번째 만에 맞췄습니다...
 */
public class BaseBallTest {

	public static void main(String[] args) {
		HashSet<Integer> comSet = new HashSet<>();
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		int count =0;
		int strikeCount = 0;
		int ballCount = 0;
		
		while(comSet.size()<3) {
			comSet.add(random.nextInt(9)+1);
		}
//		System.out.println(comSet);
		ArrayList<Integer> comList = new ArrayList<>(comSet);
		Collections.shuffle(comList);
//		System.out.println(comList);
		System.out.println("숫자 야구 게임");
		System.out.println("----------------------");
		while (strikeCount < comList.size()) {
			strikeCount = 0;
			ballCount = 0;
			// -------------------------- 컴퓨터 난수 발생 후 list 저장
			System.out.print("숫자 입력 >> ");
			int num1 = scanner.nextInt();
			int num2 = scanner.nextInt();
			int num3 = scanner.nextInt();
			ArrayList<Integer> userList = new ArrayList<>();
			userList.add(num1);
			userList.add(num2);
			userList.add(num3);
			
			// -------------------------- 사용자가 입력한 숫자 list에 저장
			for (int i = 0; i < comList.size(); i++) {
				for (int j = 0; j < userList.size(); j++) {
					if (i == j && userList.get(i) == comList.get(j)) {
						strikeCount++;

					} else if (i != j && userList.get(j) == comList.get(i)) {
						ballCount++;
					}
				}
			}
//			for(int i = 0; i<comList.size(); i++) {
//				if(userList.get(i) == comList.get(i)) {
//					strikeCount++;
//				} else {
//					for(int j=0; j<userList.size(); j++) {
//						if(i !=j && userList.get(j) == comList.get(i)) {
//							ballCount++;
//						}
//					}
//					
//				}
//			}
			
			System.out.println(userList + " ==> " + strikeCount + "S " + ballCount + "B");
			count++;
//			System.out.println(count);
			userList.clear();
		}
		System.out.println();
		System.out.println("축하합니다.");
		System.out.println("당신은 "+ count+"번째만에 맞췄습니다.");
		scanner.close();
		}
}
