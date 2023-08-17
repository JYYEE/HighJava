package test.java02;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test03 {
	public static void main(String[] args) throws Exception {
		/*
		 * 읽기 절차 : 읽기 객체 생성 -> 읽기 작업 -> 종료 작업
		 * 쓰기 절차 : 쓰기 객체 생성 -> 쓰기 작업 -> 종료 작업
		 */
		
		String path = "src\\test\\java02\\Test03.java";
		
		FileReader fileReader = new FileReader(new File(path));
		//new FileReader(path)
		while(true) {
			/*
			 * read : 한글자에 대한 char 데이터
			 */
			int read = fileReader.read();	// 한글자씩 읽어오기
			if(read == -1) {break;}	// 중괄호 꼭 작성
			System.out.print((char)read);
		}
		fileReader.close();// 종료작업 하지 않으면 다음번 읽기 작업에서 에러발생
	}
}
