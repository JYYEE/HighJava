package test.java03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test01 {
public static void main(String[] args) throws Exception {
		
		String className = "test.java04.MemberVo";
		
		
		
		// String -> MemberVo 객체 
		// String -> Class -> Object(MemberVo)
		
		// String -> MemberVo -> 속성 값을 설정
		// String -> Class -> Object
		{
			MemberVo v = new MemberVo();
			v.setBirthYear(2020);
			System.out.println(v.getBirthYear());
			int year = v.birthYear;
		}
		
		{   // Class -> newInstance() : new MemberVo() 
			Class<?> forName = Class.forName("test.java04.MemberVo"); // MemberVo.class 
			// new MemberVo() 
			Object newInstance = forName.newInstance(); //  new MemberVo() 
			System.out.println(newInstance);
		}
		
		{   // Class -> Constructor -> new Instance() //  new MemberVo("a001", "김은대", 2020)
			Class<?> forName = Class.forName("test.java04.MemberVo"); // MemberVo.class 
			Constructor<?> const1 = forName.getDeclaredConstructor(String.class, String.class, int.class); // public MemberVo(String memId, String memName, int brithYear)
			
			// 객체 
			Object newInstance = const1.newInstance("a001", "김은대", 2020); // new MemberVo("a001", "김은대", 2020)
			System.out.println(newInstance);
			
			
			// Class -> Constructor -> Object
			
			
			// 속성 접근
			// String -> MemberVo -> 속성 값을 조회 (Field) 
			// Class -> Field -> get(), set() 
			Field field1 = forName.getDeclaredField("birthYear");  // public int birthYear;
			Object object = field1.get(newInstance);   
			System.out.println(object);
			
			Field field2 = forName.getDeclaredField("memId");  // public int birthYear;
			field2.setAccessible(true);
			Object object2 = field2.get(newInstance);   
			field2.setAccessible(false);
			System.out.println(object);

			// 함수 접근 
			// Class -> Method -> invoke()
			Method method = forName.getDeclaredMethod("setMember", String.class, String.class, int.class); // setMember(String memId, String memName, int birthYear)
			method.invoke(newInstance, "a002","김은수",2001); // 객체명.함수
			System.out.println(newInstance);
			
			// Annotation
			Deprecated declaredAnnotation = method.getDeclaredAnnotation(Deprecated.class);
			System.out.println(declaredAnnotation);
			
			
		}
		
		
		{
			
		}
		
		
		
		// String -> MemberVo -> 함수실행
		
		
		
		
		
	}
	
	
		
		// String -> MemberVO 객체
		// String -> Class객체 -> Object 객체 생성(MemberVO)
		
		// String -> MemberVO -> 속성 값을 설정
		// String -> Class -> Object
		
		// String -> MemberVO -> 속성 값을 조회
		
		// String -> MemberVO -> 함수 실행
}
