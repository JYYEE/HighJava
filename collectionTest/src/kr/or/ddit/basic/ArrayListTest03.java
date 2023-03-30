package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오.
 * 		 (단, 각 별명의 길이는 모두 다르게 입력한다.)
 */

public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> aliasList = new ArrayList<>();
		
		System.out.println("5개의 서로 다른 길이의 별명을 입력하세요.");
		for (int i = 1; i <=5; i++) {
			System.out.println(i + "번재 별명 입력 >> ");
			String alias = scanner.nextLine();
			aliasList.add(alias);
		}
		System.out.println();
		
		// 제일 긴 별명이 저장될 변수를 선언하고 이 변수에 List의 첫번째 데이터로 초기화 한다.
		String maxAlias = aliasList.get(0);
		
		for (int i = 1; i < aliasList.size(); i++) {
			if(maxAlias.length() < aliasList.get(i).length()) {
				maxAlias = aliasList.get(i);
			}
			
		}
		System.out.println("제일 긴 별명 : " + maxAlias);
		
		
		
//		지혜 ver
//		ArrayList<String> list = new ArrayList<>();
//		
//		int maxIndex = 0;
//		int maxLength = 0;
//		
//		for (int i = 0; i < 5; i++) {
//			String string = scanner.nextLine();
//			list.add(string);
//			if(maxLength < list.get(i).length()) {
//				maxLength = list.get(i).length();
//				maxIndex = i;
//			}
//			
//		}
//		System.out.println("제일 긴 별명 : " + list.get(maxIndex));
		scanner.close();
	}
}
