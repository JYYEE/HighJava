package test.java02;

import java.io.File;

/*
 * 현재 작성하고 있는 파일을 읽어서 화면에 출력
 *  - FileInputStream -> 저장시
 *  - FileReader -> 출력시
 */
public class Test02 {
	/*
	 * 이 파일 경로 
	 * 상대 경로 : src/test/java02/Test02.java
	 * 절대 경로 : D:\A_TeachingMaterial\3.HighJava\workspace\test\src\test\java02\Test02.java
	 */
	
	public static void main(String[] args) {
		System.out.println(new File("").getAbsolutePath());
		// D:\A_TeachingMaterial\3.HighJava\workspace\test
		
		File file = new File("src/test/java02/Test02.java");	// file 객체를 생성할 때는 상대경로로 구현
		System.out.println(file.exists());
		System.out.println(file.isFile());
		System.out.println(file.length());	// 파일 크기
		System.out.println(file.getName()); // 파일명
		System.out.println(file.getAbsolutePath());// 절대 경로
		
	}
}
