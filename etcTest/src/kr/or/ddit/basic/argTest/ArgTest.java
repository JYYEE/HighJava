package kr.or.ddit.basic.argTest;

public class ArgTest {
	// 배열을 이용한 메서드 만들기
	public int sumArr(int[] data) {
		int sum = 0;
		for (int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	// 가변형 인수 ==> 메서드의 인수 개수가 호출할 때마다 다를 때 사용
	// - 가변형 인수는 메서드 안에서는 배열로 처리된다. 
	// - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	// - 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 
	//   가변형 인수를 제일 뒤쪽에 배치해야 한다.
	
	// 가변형 인수용 메서드 만들기
	public int sumArg(int...data) { // ... : 가변형 변수로 만들어줌.
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum +=data[i];
		}
		return sum;
	}
	
	public String sumArg2(String name, int...data) { 
		// 가변형 변수를 먼저 써주면 오류발생. 순서 지켜 주기!! 
		// 가변형을 뒤에다 써주는 이유?! 
		// 컴퓨터는 앞에서부터 읽어가므로 가변형이 먼저나오면 모든 데이터를 가변형데이터로 인식할 수 있음.
		// 데이터를 명확하게 구분하기 위해서 가변형 변수를 뒤에 배치
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum +=data[i];
		}
		return name + "씨 점수 : " + sum;
	}
	
	
	
//	private void myMethod(int a) {
//		
//	}
	
	public static void main(String[] args) {
		ArgTest test = new ArgTest();
		
		// 배열 초기화 및 선언
//		int[] nums = {100, 200, 300}; 
		// 변수를 쪼개보면 아래처럼 나옴. 
		int[] nums;
		nums = new int[] {100, 200, 300};
//		int[] nums = new int[] {100, 200, 300}; 이렇게도 쓸 수 있음
		
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] {1, 2, 3, 4, 5})); // 1, 2, 3, 4, 5 배열에 넣기
		
		
//		int b = 100;
//		test.myMethod(b);
//		test.myMethod(200);
		
		System.out.println();
		System.out.println(test.sumArg(100, 200, 300));
		System.out.println(test.sumArg(1,2,3,4,5));
		System.out.println();
		System.out.println(test.sumArg2("홍길동", 90, 80, 95));
	}


}
