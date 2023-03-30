package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 * 문제) 
 *  컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 *  컴퓨터의 가위 바위 보는 난수를 이용해서 구하고 
 *  사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력 받는다.
 * 
 *  입력 시간은 5초로 제한하고 카운트 다운을 진행한다. 
 *  5초 안에 입력이 없으면 게임에 진 것으로 처리한다. 
 * 
 *  5초 안에 입력이 완료되면 승패를 구해서 출력한다.
 *   
 * 결과 예시)
 * 1) 5초 안에 입력하지 못했을 경우
 * 	  --- 결과 ---
 * 	  시간 초과로 당신이 졌습니다.
 * 2) 5초 안에 입력했을 경우
 *    --- 결과 ---
 *    컴퓨터 : 가위
 *    사용자 : 바위
 *    결  과 : 당신이 이겼습니다.  
 * 		
 */

public class ThreadTest07Tr {
	public static boolean inputCheck;
	
	public static void main(String[] args) {
		// 메인 메소드도 하나의 스레드이므로 하나로 취급해서 실행
		GameTimer g = new GameTimer();

		// 난수를 이용하여 컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"};
		Random rnd = new Random();
		int index = rnd.nextInt(3);// 0~2사이의 난수 만들기
		String com = data[index];// 컴퓨터의 가위바위보를 정한다.
		
		// 사용자의 가위바위보 입력받기
		g.start();	// 카운트 다운 시작...
		String man;
		do {// 잘못입력했을 때 다시 입력받기
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
			
		} while (!("가위".equals(man) || "바위".equals(man) || "보".equals(man)));// 방법1
//		while (!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man));// 방법2
		
		inputCheck = true;
		
		// 결과를 판정하여 출력하기
		String result = "";
		if(man.equals(com)) {
			result = "비겼습니다.";
		} else if(man.equals("가위")&&com.equals("보") ||
				  man.equals("바위")&&com.equals("가위") ||
				  man.equals("보")&&com.equals("바위")) {
			result = "당신이 이겼습니다.";
		} else {
			result = "당신이 졌습니다.";
		}
		// 결과 판정을 스위치문으로 이용
		switch(man + com) {
		case "가위가위" :
		case "보보" :
		case "바위바위" : result = "비겼습니다."; break;
		case "가위보" :
		case "바위가위" :
		case "보바위" : result = "당신이 이겼습니다."; break;
		default : result = "당신이 졌습니다.";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		}
		
		
		
		
		
		System.out.println("--- 결 과 ---");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("결  과 : " + result);
		
		
	}

}

class GameTimer extends Thread{ // 같은 패키지 내에 같은 이름의 클래스는 중복 사용 불가.
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for(int i=5; i>= 1; i--) {
			if(ThreadTest07Tr.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}// for문 끝
		System.out.println("--- 결 과 ---");
		System.out.println("시간이 초과되어 당신이 졌습니다...");
		System.exit(0);
	}
	
}
