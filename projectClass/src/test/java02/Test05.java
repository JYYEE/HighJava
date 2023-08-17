package test.java02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test05 {
	public static void main(String[] args) throws Exception {
		/*
		 * 읽기 절차 : 읽기 객체 생성 -> 읽기 작업 -> 종료 작업
		 * 쓰기 절차 : 쓰기 객체 생성 -> 쓰기 작업 -> 종료 작업
		 */
		
		String path = "src\\test\\java02\\Test05.java";
		
		//FileReader fileReader = new FileReader(new File(path));
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		while(true) {
			String readLine = br.readLine();
			System.out.println(readLine);
			if(readLine == null) {break;}
		}
		br.close();
	}
}
