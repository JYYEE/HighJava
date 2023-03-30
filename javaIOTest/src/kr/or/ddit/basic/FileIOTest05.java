package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {
	public static void main(String[] args) {
		// 파일의 인코딩 방식을 지정해서 읽어오기
		// 현재 프로그램 설정된 인코딩 방식 파일은 잘 읽어옴. 다르면 깨짐
		// 파일의 인코딩 방식을 프로그램에 설정된 방식으로 바꿔서 읽어오도록 하는 방법
		try {
			//FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			//FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			
			// 인코딩 방식을 지정해서 입출력하는 보조 스트림
			// InputStreamReader  ==> 입력용
			// OutputStreamWriter ==> 출력용
			
			FileInputStream fin = new FileInputStream("d:/d_other/test_ansi.txt");

			// 기본 인코딩 방식으로 읽어온다.
			//InputStreamReader isr = new InputStreamReader(fin);
			
			// 인코딩 방식을 지정해서 읽어오기. 
			// (인코딩 방식을 바꾸는게 아니고 파일에서 지정된 인코딩 방법으로 파일을 읽는 것) 
			// 인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			// 기본 인코딩 방식에서 인코딩 방식을 같이 써줌.
			InputStreamReader isr = new InputStreamReader(fin, "utf-8");
			int c ; 
			while ((c = isr.read()) != -1) {
				System.out.print((char) c);
				
			}
			
			isr.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
