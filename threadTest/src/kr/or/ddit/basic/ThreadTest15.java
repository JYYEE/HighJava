package kr.or.ddit.basic;

public class ThreadTest15 {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject(); // 공통 객체 생성
		TestThread th1 = new TestThread("1번 쓰레드", sObj); 
		TestThread th2 = new TestThread("2번 쓰레드", sObj); 
		// 똑같은 일을 하는 객체
		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 클래스
class ShareObject{
	private int sum = 0;
	
	// 동기화 처리
	
	//public synchronized void add() { //방법2 : 메서드에 동기화 설정하기
	public  void add() { 
		synchronized (this) { // 방법1 : 동기화 블록으로 설정하기
			
		int n = sum;
		
		sum += 10; // 10증가
		
		//sum = n; 
		// 다른 쓰레드로 넘어가는 경우를 생각해서 쪼개줌
		// 다른 쓰레드로 넘어갈때 중간에 오버랩되서 출력단계에서 값이 왔다갔다 출력됨.
		// synchronized로 락을 걸어주면 메소드 실행 중간에 다른 쓰레드 접근 막아주면서 출력이 값 순서대로 됨.
		// ex) 은행에서 입금, 출금 할 때 사용
		System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}

// 처리할 쓰레드 클래스
class TestThread extends Thread{
	private ShareObject sObj; // ShareObject 객체의 참조값이 저장될 변수
	
	// 생성자
	public TestThread(String name, ShareObject sObj) {
		super(name); // 쓰레드 이름 설정
		this.sObj = sObj;
	}
	@Override
	public void run() {
		for(int i=1; i<=10;i++) {
			sObj.add();
		}
	}
	
	
}