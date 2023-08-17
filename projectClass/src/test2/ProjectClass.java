package test2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProjectClass {
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

	 */
	public static void main(String[] args) throws IOException {
		ProjectClass projectClass = new ProjectClass();
		System.out.println(projectClass.getClass().getName());
		//projectClass.getClass().
		
		try {
		File file = new File("D:\\A_TeachingMaterial\\3.HighJava\\workspace\\test2\\src\\test2\\ProjectClass.java");
			FileReader fr = new FileReader(file);
			 int cur = 0;
	         while((cur = fr.read()) != -1){
	            System.out.print((char)cur);
	         }
	         fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
