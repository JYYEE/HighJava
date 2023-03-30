package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		// 벡터 객체 생성(모든 타입의 데이터 저장가능)
		Vector v1 = new Vector();
		System.out.println("처음 크기 : " + v1.size());
//		벡터는 처음에 크기가 0이었다가 데이터가 추가되면 크기가 변화됨

		// 데이터 추가하기 : add(추가할 데이터) : 새롭게 추가된 메소드
		// 반환값 : 추가성공 (true), 추가실패(false)
		v1.add("aaaa");
		v1.add(new Integer(111)); // 숫자는 객체화 시켜서 저장
		v1.add(123); // 요즘에는 이렇게 써도 자동으로 박싱작업이 이루어짐 : auto boxing(오토박싱), 오토 언박싱이 지원된다.
		v1.add('a'); // 문자열 데이터
		v1.add(true); // 논리형 데이터
		boolean r = v1.add(3.14); // 반환값 확인

		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r);

//		문자와 숫자 데이터 저장이 가능. 숫자를 객체화 시켜서 저장해줘야함
//		일반데이터를 객체화 해주는 것 : wrapper 클래스
//		int형 데이터를 객체화 시킴(포장) (new Integer(숫자))
//		char형 데이터를 객체화 시키는 wrapper 클래스 : Character
//		int, char만 다르고 나머지는 스펠링 같고 맨 앞글자만 대문자로 사용.
//		wrapper 클래스로 포장하는 것을 박싱과정이라고 함.
		
		// 데이터 추가하기2 : addElement(추가할 데이터) : 원래 있던 메소드. add가 추가되고 자주 사용x.
		// ==> 이전 버전의 프로그램과의 호환성을 위해서 남아 있는 메서드
		v1.addElement("CCC");
		
		System.out.println("v1 => " + v1); //v1.toString()이어도 출력됨.
		
		// 데이터 추가하기3 : add(index, 데이터)
		//      ==> 'index'번째에 '데이터'를 끼워 넣는다.
		//      ==> 'index'는 0부터 시작한다.
	    //      ==> 반환값이 없다.
		v1.add(1, "KKK"); // 인덱스 1번에 KKK 넣고 1번부터 그 이후 데이터들은 뒤로 밀려남.
		System.out.println("v1 => " + v1);
		
		// 데이터 꺼내오기 : get(index)
		//      ==> 'index'번째 데이터를 꺼내서 반환한다.
		System.out.println("0번째 데이터 : " + v1.get(0));
		int iTemp = (int) v1.get(2);
		System.out.println("2번째 데이터 : " + iTemp);
//		부모에 해당하는 객체변수에는 자식 데이터 저장 가능. 모든 변수를 저장하려면 object 로 설정.
//		벡터 내부 데이터 저장하는 곳은 object로 되어 있음. 즉, 벡터에 데이터 저장되면서 object로 바뀌어서 저장됨. 
//		자식을 부모로 저장하려면 형변환 되지만 반대로 하려면 자동 형변환 안됨. 
//		따라서 object 타입의 데이터를 꺼내서 자식의 타입으로 저장하니깐 에러뜸 ==> 캐스팅 필요.
//		즉, 벡터 내부 저장된 데이터 타입 : Object, 저장된 데이터 꺼내서 저장할 변수들 타입은 모두 object보다 하위타입. ==> 캐스팅 후 저장
//		출력할 때는 캐스팅 필요x. 변수에 저장할 때는 형변환(캐스팅) 필요.
		
		// 데이터 수정하기 : set(index, 새로운 데이터)
		//  ==> 'index'번째의 데이터를 '새로운 데이터'로 변경한다.
		//  ==> 반환값 : 원래의 데이터
		
		String sTemp = (String) v1.set(0, "zzzz"); // 캐스팅 안하면 에러. 캐스팅 하거나 변수에 저장안하면 에러 안뜸.
		System.out.println("v1 ==> " + v1);
		System.out.println("원래의 데이터 : " + sTemp);
		
		// 데이터 삭제하기1 : remove(index) : 삭제할 데이터의 위치를 알려줌
		//  ==> 'index'번째 데이터를 삭제한다.
		//  ==> 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		
		sTemp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 데이터 : " + sTemp);
		
		// 데이터 삭제하기2 : remove(삭제할 데이터) : 직접적으로 삭제할 데이터를 알려줌
		//  ==> '삭제할 데이터'를 찾아서 삭제한다.
		//  ==> '삭제할 데이터'가 벡터 안에 여러 개 존재하면 이 중 제일 첫번째 자료가 삭제된다.
		//  ==> 반환값 : 삭제 성공(true), 삭제 실패(false)
		//  ==> 삭제할 데이터가 '정수형'이거나 'char'형일 경우에는 반드시 객체로 변환해서 사용해야 한다.
		v1.remove("CCC");
		System.out.println("삭제 후 v1 => " + v1);
		
//		v1.remove(123); // 에러 : 123을 데이터로 인식하지 않고 인덱스 번호로 인식해서 존재하는 배열의 크기보다 커서 에러. 
//		v1.remove(new Integer(123)); // 방법1 ==> int로 객체화 시켜서 삭제. 자바 1.9버전부터는 사용 불가.
		v1.remove(Integer.valueOf(123)); // 방법2 
		System.out.println("123 삭제 후 v1 => " + v1); 
		
//		v1.remove('a'); // 에러 : 'a' 문자형이지만 컴퓨터로 들어갈 때는 숫자로 바뀌어서 저장됨. 즉, 데이터 값을 인덱스로 인식해서 배열의 크기보다 커서 에러.
//		v1.remove(new Character('a')); // 방법 1 ==> char로 객체화 시켜서 삭제.
		v1.remove(Character.valueOf('a')); // 방법2
		System.out.println("삭제 후 v1 => " + v1);
		
//		int, char을 제외한 나머지 데이터는 그냥 지울 수 있음.
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
			
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		// 전체 데이터 삭제 : clear();
		v1.clear();
		System.out.println("clear 삭제 후 v1 => " + v1);
		
		//------------------------------------------------------------------------------
		/*
		 * 제네릭 타입(Generic Type) < > : 클래스 내부에서 사용할 데이터 타입을 객체를 생성할 때 외부에서 지정해주는 기법으로
		 * 			객체를 선언할 때 < > 괄호 안에 그 객체의 내부에서 사용할 데이터의 타입을 정해주는 것을 말한다. 
		 * 			- 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다.
		 * 			- 이 때 제네릭으로 선언될 수 있는 데이터 타입은 클래스형이어야 한다.
		 * 			  그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 대체해서 사용해야 한다.
		 * 			- 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다. 
		 */
		
//		< >안에 저장하고 싶은 타입명 적어주기
		Vector<Integer> v2= new Vector<>();// int형 자료만 저장할 수 있는 벡터
		Vector<String> v3 = new Vector<>();// String형 자료만 저장할 수 있는 벡터
		
		v3.add("안녕하세요");
		//v3.add(100); // 오류 : 다른 종류의 데이터를 저장할 수 없다. 
		
		String sTemp2 = v3.get(0);// 벡터 타입을 처음부터 String으로 지정해서 제네릭타입으로 만들어서 캐스팅안해도 오류 안남. 형 변환없이 사용할 수 있다.
		
		Vector<Vector> vv = new Vector<>(); // 2차원의 배열이랑 같은 개념으로 이해.
		Vector<Vector<Vector>> vvv = new Vector(); // 3차원 배열이라 같은 개념으로 이해.
		System.out.println("-----------------------------------------------------------");
		//------------------------------------------------------------------------------
		v3.clear();
		System.out.println("v3의 size : " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		// 데이터 삭제하기3 : removeAll(Collection 객체)
		//  ==> 벡터의 데이터 중에서 'Collection객체'가 가지고 있는 모든 데이터를 삭제한다. 
		//  ==> 반환값 : 삭제 성공(true), 삭제 실패(false)
		
		v3.removeAll(v4); // 의미 : v3의 데이터들 중에서 v4가 가지고 있는 데이터들을 모두 삭제한다. 
		System.out.println("v3 => " + v3);
		System.out.println("-----------------------------------------------------------");
		
		// 벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문 사용)
		
		v3.clear();
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		for(int i=0; i<v3.size(); i++) {
			System.out.println(i + "번째 자료 : "+v3.get(i));
		}
		
		System.out.println("-----------------------------------------------------------");
		
		// 향상된 for문
		for (String str : v3) {
			System.out.println(str);
		}

	}

}
