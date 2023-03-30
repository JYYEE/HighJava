package kr.or.ddit.basic.genericTest;
/*
 * 제네릭 클래스 만드는 방법
 * 형식) 
 * class 클래스명<제네릭타입문자> {
 * 	  제네릭타입문자 변수명;	// 변수 선언에 제네릭을 사용할 경우
 * 	  ...
 * 
 * 	  제네릭타입문자 메서드명(){	// 반환값이 있는 메서드에 제네릭 사용하는 경우
 * 		...
 * 		return 반환값;
 * 	  }
 * 
 * 	  // 메서드의 매개변수에 제네릭을 사용하는 경우
 * 	  void 메서드명(제네릭타입문자 변수명1, ...){
 * 	  ...
 * 	  }
 * }
 * 
 *  - 제네릭 타입 글자 -
 *  
 *  T : Type 
 *  K : Key
 *  V : Value
 *  E : Element(자료구조에 들어가는 데이터)
 */
class MyGeneric<T>{
	private T value; // 변수 선언

	public T getValue() { // 반환값 타입
		return value;
	}

	public void setValue(T value) { // 매개변수
		this.value = value;
	}
}


public class GenericTest {
	public static void main(String[] args) {
		NonGeneric ng1 = new NonGeneric();
		ng1.setValue("가나다라"); // 부모객체에 자식객체 저장하면서 자동형변환

		NonGeneric ng2 = new NonGeneric();
		ng2.setValue(100);
		
		String sTemp = (String) ng1.getValue(); 
		//ng1.getValue()는 object형 객체 -> 자식 객체에 저장할 수 없음 ==> 캐스팅 필요
		int iTemp = (int) ng2.getValue();
		System.out.println("문자열 반환값 : " + sTemp);
		System.out.println("정수형 반환값 : " + iTemp);
		System.out.println();
		
		String sTemp2 = (String) ng2.getValue(); 
		// 컴파일 오류발생x 실행오류발생. int => object => String으로 변환하라는 얘기 
		// ==> 대입할 타입을 지정하면 오류발생x
		
		System.out.println("---------------------------------");
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("안녕하세요");
		
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setValue(500);
		
		
		sTemp = mg1.getValue();
		iTemp = mg2.getValue();
		System.out.println("제네릭 문자열 반환값 : " + sTemp);
		System.out.println("제네릭 정수형 반환값 : " + iTemp);
		
//		String sTemp2 = mg2.getValue(); 컴파일 오류발생  
		
		
	}
}

// 제네릭을 사용하지 않는 class
// 데이터를 꺼내올 때 반드시 형변환 필요
class NonGeneric {
	private Object value; // 종류 상관없이 저장 하고자 타입을 object로 설정.

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}