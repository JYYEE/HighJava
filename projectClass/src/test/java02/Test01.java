package test.java02;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

public class Test01 {
	/*
	 * Q. 자바 클래스 생성
		- 본인이 작성하고 있는 클래스 소스코드를 콘솔화면에 출력
		
		File
		 - 파일 정보 관리
		
		FileInputStream -> BufferedInputStream -> (?) -> 문제점 인코딩 문제
		 - 파일을 읽는데 byte 단위로 읽기
		 - 실제로 파일을 읽는 녀석
		 - 파일 복사에 유용
		
		FileReader -> BufferedReader (버퍼 이용)
		 - 파일을 읽는데 char 단위로 읽기
		 - 파일 출력에 유용
		 
		변환한 바이트 형식으로 다시 변환해야 안 깨지고 출력됨.

	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		{
			Properties properties = System.getProperties();
			Set<Object> keySet = properties.keySet();
			for (Object o : keySet) {
				Object object = properties.get(o);
				System.out.println(o + " : " + object);		// file.encoding설정 : utf-8 확인 
			}
			
			System.exit(0);
		}
		
		
		String data1 = "한글";
		String data2 = "12abc";
		
		/** 
		 * 한글 바이트 수
		 */
		{
			// UTF-8
			// byte[] bytes = data1.getBytes("utf-8");
			 byte[] bytes = data1.getBytes();	// file.encoding 기본값이 utf-8이므로 이렇게 써도 됨
			System.out.println("utf-8 바이트 수 : " +bytes.length);
			String string = new String(bytes);
			System.out.println("바이트 문자열 변환 : " + string);
		}
		
		{
			// EUC-KR
			byte[] bytes = data1.getBytes("EUC-KR");
			System.out.println("EUC-KR 바이트 수 : " + bytes.length);
			String string = new String(bytes);
			System.out.println("바이트 문자열 변환 : " + string);
			String string2 = new String (bytes, "euc-kr");
			System.out.println("바이트 문자열 변환 : " + string2);
		}
	}
}
