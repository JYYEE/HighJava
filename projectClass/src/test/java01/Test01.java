package test.java01;
/**
 *  - Map의 기능을 활용하여 다음과 같은 타입을 만들고자 함.
 * 		=> Map은 Interface -> 이를 상속한 class는 HashMap, TreeMap 
 * 	- 외부에서 객체 생성시 new가 아닌 init() 함수를 이용하여 객체 생성할 수 있도록 설정.
 * 		클래스 명 ParamMap
 * 		ParamMap paramMap = new ParamMap()   x
 * 		ParamMap paramMap = ParamMap.init()  o
 * 		=> 외부에서 사용하기 위해서 static 붙여줘야함
 * 	- Map에서 키는 String만 사용하고자 함.
 * 		paramMap.put("key1", "value1")
 * 		=> generic 타입에 <String, Object> 
 * 	- String getString(String key) 신규 생성하여 만들고자 함.
 * 		String val = paramMap.getString("key1")
 * 		=> 함수 생성
 * 	- Integer getInteger(String key) 해당 키의 값을 Integer로 반환하여 주는 함수
 * 		paramMap.put("key2", 12)
 * 		int val2 = paramMap.getInteger("key2")
 * 		=> 함수 생성
 * 	- 특정한 키를 부여시 동적으로 반환타입을 고려하여 반환하는 함수
 * 		StringBuffer sb = new StringBuffer();
 * 		sb.append("test");
 * 		paramMap.put("key3", sb);
 * 		
 * 		StringBuffer val3 = paramMap.get("key3", StringBuffer.class)
 * 		=> 동적으로 반환하는것 가변인자 이용
 * 		
 *
 */
public class Test01 {	// ParamMap을 잘 만들었는지 test용
	public static void main(String[] args) {
		ParamMap init = ParamMap.init();
		init.put("key1", "value1");
		// init.put(123, 456);
		
		String val1 = init.getString("key1");
		
		StringBuffer sb = new StringBuffer();
		sb.append("test");
		init.put("key3", sb);
		
		StringBuffer stringBuffer = init.get("key3", StringBuffer.class);
		System.out.println(stringBuffer);
		
		ParamMap p1 = ParamMap.init();
		p1.put("test1", "test2");
		init.put("key4", p1);
		ParamMap p11 = init.get("key4", ParamMap.class);
		System.out.println(p11);
		
	}
}
