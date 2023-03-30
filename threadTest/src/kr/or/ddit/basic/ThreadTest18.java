package kr.or.ddit.basic;
/*
 * wait(), notify() 메서드를 이용한 두 쓰레드가 번갈아 한번씩 실행되는 예제
 * wait(), notify(), notifyAll() 메서드는 동기화 영역에서만 사용 가능하다.
 */

public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethodA() {
		System.out.println("testMethodA() 메서드 실행중...");
		// notify와 wait는 하나의 메서드 안에서 실행되야함. wait()는 try-catch문 안에 두기
		// wait를 먼저 쓰면 둘 다 waiting pool에 들어갈 수 있음. 
		// 하나는 깨울 수 있게 구상해야함. => notify()를 먼저 진행
		// waiting pool에 마지막 남은 하나의 쓰레드를 깨워줘야 끝나는데 메소드가 남아 있지 않아서 끝나지 않았음
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	public synchronized void testMethodB() {
		System.out.println("testMethodB() 메서드 실행중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

// WorkObject의 testMethodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj; // 객체를 저장할 변수
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.testMethodA();
		}
		// 마지막에 waiting pool에 있는 쓰레드 깨워주기
		// 동기화 블록으로 만들어줘야 에러 안남.
		synchronized (workObj) {
			workObj.notify();
			
		}
	}
}
// WorkObject의 testMethodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj; // 객체를 저장할 변수
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.testMethodB();
		}
		// 마지막에 waiting pool에 있는 쓰레드 깨워주기 
		// 동기화 블록으로 만들어줘야 에러 안남.
		synchronized (workObj) {
			workObj.notify();
			
		}
	}
}