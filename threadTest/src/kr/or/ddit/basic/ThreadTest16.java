package kr.or.ddit.basic;

// 은행의 입출금을 쓰레드로 처리하는 예제 (동기화 처리 예제)

public class ThreadTest16 {
	// 공통으로 사용할 객체
	private int balance;	// 잔액 저장 변수
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money) {
		balance += money; // 입금 후 잔액 = 현재 잔액 + 입금액
	}
	
	// 출금하는 메서드 (성공 : true, 실패 : false)
	public synchronized boolean withdraw(int money) {
		int temp = 0;
		if(balance>=money) {
			for(int i=1; i<=100000000; i++) { // 시간 지연용
				temp++;
			}
			balance -= money;
			System.out.println("메서드 안에서 balance : " + balance);
			return true;
		} else {
//			System.out.println("출금 실패");
			return false;
		}
	}

	public static void main(String[] args) {
		ThreadTest16 account = new ThreadTest16();
		account.setBalance(10000); // 잔액을 10000원으로 설정
		
		// 익명구현체로 쓰레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				boolean result = account.withdraw(6000); // 6000원 출금하기
				System.out.println("쓰레드에서 result = " + result + ", balance = " + account.getBalance());
			}
		};
		// ------------------------
		
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		
		th1.start();
		th2.start();
	}
}
// 동기화 처리 전 결과 
/*
 * 메서드 안에서 balance : -2000
메서드 안에서 balance : -2000
쓰레드에서 result = true, balance = -2000
쓰레드에서 result = true, balance = -2000

또는 

메서드 안에서 balance : 4000
쓰레드에서 result = true, balance = 4000
메서드 안에서 balance : -2000
쓰레드에서 result = true, balance = -2000
 * 
 */
// 처음에는 잔액이 10000원보다 크니깐 쓰레드 둘 다 if문 조건을 통과
// 또는 하나가 먼저 조건을 통과해도 시간 지연이 있다보니 나머지 쓰레드도 조건을 통과되버림.
// --> 이를 막아주는 것이 동기화 작업

// 동기화 처리 후 결과
/*
 * 메서드 안에서 balance : 4000
쓰레드에서 result = true, balance = 4000
쓰레드에서 result = false, balance = 4000
 */
