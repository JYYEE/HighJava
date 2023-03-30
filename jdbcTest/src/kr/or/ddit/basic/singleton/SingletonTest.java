package kr.or.ddit.basic.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		//MySingleton test1 = new MySingleton();
		// ==> 외부 클래스에서 new 명령으로 생성 불가
		
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		// getInstance를 두번 호출했지만 생성자는 1번만 생성 
		// 처음 실행한 MySingleton test2 = MySingleton.getInstance();를 실행할 때 객체가 생성됨
		// 객체를 따로 생성하지 않고 저장된 참조값을 반환
		// 즉, 여러번 호출해도 객체는 1번만 생성되고 다 같은 객체임. 
		System.out.println("test2 ==> "+test2.toString());
		System.out.println("test3 ==> "+test3);
		
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		
		test2.displayTest();
	}
}
