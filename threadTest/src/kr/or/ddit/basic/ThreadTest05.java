package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {
	public static void main(String[] args) {
		// 사용자로부터 데이터 입력 받기
		// (스캐너로 받았을 때 문제 발생 가능성 -> 윈도우처럼 창을 띄워서 받기)
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요...");
		// 창에 입력하고 enter 또는 확인 -> 변수 str에 저장됨
		// 취소나 esc 누르면 -> null값 
		// 입력한 모든 값 String으로 저장됨.
		System.out.println("입력한 값 : " + str);
		
		// 카운트 다운
		for(int i=10;i>=1;i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 목표 : 10초안에 입력 하게 만들기
		// 싱글스레드에서는 동시에 작동x ==> 각각 스레드로 작성
		// 
	}
}
