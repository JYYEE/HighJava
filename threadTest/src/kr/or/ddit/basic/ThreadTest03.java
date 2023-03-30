package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		//Thread가 실행되는 시간 체크해보기]
		Thread th = new Thread(new MyRunner2());
		
		// 1970년 1월1일0시0분0초(표준시간)으로부터 System.currentTimeMillis() 메서드가 
		// 실행된 시점까지의 경과한 시간을 밀리세컨드(1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		try {
			th.join(); // 현재 위치에서 대상이 되는 쓰레드(변수 th 쓰레드)가 끝날때까지 기다린다.
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		/*
		 * try-catch문 없었을 때는 경과시간이 0
		 * th.join로 쓰레드가 끝날때까지 기다린 후에 시간을 구함. 
		 */
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime));
	}

}

// 인터페이스를 구현하거나 쓰레드를 상속하거나 방법 차이없음.
class MyRunner2 implements Runnable{

	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<=1_000_000_000L;i++) { // 숫자에 3자리마다 콤마찍듯이 언더바 사용가능
			sum +=i;
		}
		System.out.println("합계 : " +sum);
	}
	
}
