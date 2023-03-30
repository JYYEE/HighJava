package kr.or.ddit.basic;

import java.lang.InterruptedException;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
import java.lang.Thread;

/*
 * Thread의 stop() 메서드를 호출하면 쓰레드가 바로 멈춘다.
 * 이 때 사용하던 자원을 정리하지 못하고 쓰레드가 종료되어 
 * 다른 쓰레드에 영향을 줄 수 있다.
 * 그래서 stop() 메서드는 비추천.
 */
public class ThreadTest11 {
	public static void main(String[] args) {
//		ThreadStopTest1 th1 = new ThreadStopTest1();
//		th1.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		//th1.stop(); // 실행은 되지만 비추천이라 stop에 라인 생김. --> 실행 중에서 멈춤
//		th1.setStop(true);
		
		// interrupt()메서드를 이용한 쓰레드 멈추기
		ThreadStopTest2 th2 = new ThreadStopTest2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
	}
}


// 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while (!stop) {
			System.out.println("쓰레드 실행 중...");
		}
		System.out.println("자원 종료...");
	}
}

// interrupt() 메서드를 이용하여 쓰레드를 멈추게 하는 방법
class ThreadStopTest2 extends Thread{
	@Override
	public void run() {
		// 방법1 ==> interrupt() 메서드와 sleep() 메서드를 이용하는 방법
//		try {
//			while (true) {
//				System.out.println("++++ Thread 실행 중...");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
		
		// 방법2 ==> 
		while (true) {
			System.out.println(" ---------- 실행 중....");
			// interrupt() 메서드가 호출되었는지 검사한다.
			// 검사방법1 ==> Thread의 인스턴스 메서드인 isInterrupted()메서드 이용하기
			// 		- isInterrupted()메서드 ==> interrupt() 메서드가 호출되면 true를 반환한다.
//			if(this.isInterrupted()) {
//				break;
//			}
			
			// 검사방법 2 ==> Thread의 정적(static) 메서드인 interrupted() 메서드 이용하기
			//		- interrupted() 메서드 ==> interrupt() 메서드가 호출되면 true를 반환한다.
			if(Thread.interrupted()) {
				break;
			}
		}
		System.out.println("++++ 자원 정리...");
		System.out.println("++++ 쓰레드 종료...");
	}
	
}