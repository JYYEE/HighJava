package kr.or.ddit.basic;

import java.util.ArrayList;
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

public class ThreadTest07 {

	public static void main(String[] args) {
		UserRsp userRsp = new UserRsp();
		ComRsp comRsp = new ComRsp();
		Count count = new Count();
		comRsp.start();
		userRsp.start();
		count.start();
		try {
			comRsp.join();
			userRsp.join();
			count.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		String result;
		if (ComRsp.com.equals(UserRsp.user)) {
			result = "비겼습니다.";
		} else if(ComRsp.com.equals("가위") && UserRsp.user.equals("보")
				||ComRsp.com.equals("보") && UserRsp.user.equals("바위")
				||ComRsp.com.equals("바위") && UserRsp.user.equals("가위")) {
			result = "컴퓨터가 이겼습니다.";
		} else {
			result = "당신이 이겼습니다.";
		}
		System.out.println("--- 결과 ---");
		System.out.println("컴퓨터 : " + comRsp.com);
		System.out.println("사용자 : " + userRsp.user);
		System.out.println("결  과 : " + result);
	}

}

class UserRsp extends Thread{
	public static String user;
	public static boolean flag;
	@Override
	public void run() {
		user = JOptionPane.showInputDialog("가위 바위 보 중에 하나를 입력하세요.");
		flag = true;
//		System.out.println("사용자 : " + user);
	}
}
class ComRsp extends Thread{
	public static String com;
	@Override
	public void run() {
		ArrayList<String> list = new ArrayList<>();
		list.add("가위");
		list.add("바위");
		list.add("보");
//		String[] list = new String[]{"가위", "바위", "보"}; // 리스트로 바로 정의
		Random rnd = new Random();
		int rndNum = rnd.nextInt(3);
		switch(rndNum) {
		case 0 : 
			com = list.get(0);
			break;
		case 1 : 
			com = list.get(1);
			break;
		case 2 : 
			com = list.get(2);
			break;
		}
//		System.out.println("컴퓨터 : " + com );
	}
}

class Count extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1;i--) {
			if(UserRsp.flag == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("--- 결과 ---");
		System.out.println("시간 초과로 당신은 졌습니다.");
		System.exit(0);
	}
}
//class Result extends Thread{
//	
//	@Override
//	public void run() {
//		String result;
//		if(UserRsp.user == null) {
//			result = "시간 초과로 당신은 졌습니다.";
//		} else if (ComRsp.com.equals(UserRsp.user)) {
//			result = "비겼습니다.";
//		} else if(ComRsp.com.equals("가위") && UserRsp.user.equals("보")
//				||ComRsp.com.equals("보") && UserRsp.user.equals("바위")
//				||ComRsp.com.equals("바위") && UserRsp.user.equals("가위")) {
//			result = "컴퓨터가 이겼습니다.";
//		} else {
//			result = "당신이 이겼습니다.";
//		}
//	}
//}
