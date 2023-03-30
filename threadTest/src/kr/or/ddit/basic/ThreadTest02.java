package kr.or.ddit.basic;
//4개의 쓰레드.(작성 쓰레드 + 메인메서드도 포함)
public class ThreadTest02 {
	public static void main(String[] args) {
		/*
		 * 멀티 쓰레드 프로그램
		 * 
		 * Thread를 사용하는 방법
		 * 방법1)
		 * Thread클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start() 메서드를 호출해서 실행한다.
		 */
		MyThread1 th1 = new MyThread1();
		th1.start();
		// run을 호출하지 않고 start를 호출한 이유? 쓰레드를 시작하기 위함. 
		// run을 호출하면 싱글스레드와 같음.(하나의 작업이 끝나고 다른 작업 실행) 
		// start는 멀티스레드가 실행됨.(여러작업이 한꺼번에 수행. 왔다갔다 실행)
		
		/*
		 * 방법2-1)
		 * Runnable인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를 생성한다.
		 * 그리고 이 인스턴스를 Thread객체를 생성할 때 생성자의 인수값으로 넣어서 생성한다.
		 * 이 때 생성된 Thread객체의 start() 메서드를 호출해서 실행한다.
		 */
		MyRunner1 r1 = new MyRunner1();
		Thread th2 = new Thread(r1);
		th2.start();
		
		/*
		 * 방법2-2)
		 * Runnable인터페이스를 익명 구현체로 작성하여 처리하기
		 */
		Runnable r2 = new Runnable() {//익명구현체

			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}				
			}
			
		};
		
		Thread th3 = new Thread(r2);
		th3.start();
	}
	
	
}
// 방법1 ==> Thread를 상속한 class작성하기
class MyThread1 extends Thread{
	@Override
	public void run() {
		// 이 run() 메서드는 쓰레드가 처리할 내용을 기술하는 곳이다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) 메서드 ==> 주어진 시간동안 잠시 멈춘다.
				//			시간은 밀리세컨드 단위를 사용한다.(즉, 1000은 1초를 의미한다.)				
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}

// 방법2-1 ==> Runnable인터페이스를 구현한 class작성하기
class MyRunner1 implements Runnable{

	@Override
	public void run() {
		//쓰레드가 처리할 내용을 이 run()메서드에 기술한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간) 메서드 ==> 주어진 시간동안 잠시 멈춘다.
				//			시간은 밀리세컨드 단위를 사용한다.(즉, 1000은 1초를 의미한다.)				
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	
}
