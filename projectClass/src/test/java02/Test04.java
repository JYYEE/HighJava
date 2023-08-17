package test.java02;

import java.io.File;
import java.io.FileReader;

public class Test04 {
	public static void main(String[] args) throws Exception {
		/*
		 * 읽기 절차 : 읽기 객체 생성 -> 읽기 작업 -> 종료 작업
		 * 쓰기 절차 : 쓰기 객체 생성 -> 쓰기 작업 -> 종료 작업
		 */
		
		String path = "src\\test\\java02\\Test04.java";
		
		FileReader fileReader = new FileReader(new File(path));
		//new FileReader(path)
		char[] ch = new char[1024]; 
		// 마지막 데이터가 담을 때, 1024데이터가 다 차지 않을 때 잔여 데이터가 남아있는 상태가 됨. 
		while(true) {
			/*
			 * read : 읽힌 글자의 수
			 * ch : 담은 데이터
			 */
			
			int read = fileReader.read(ch);	// char[] 읽어오기
			if(read == -1) {break;}	// 중괄호 꼭 작성
			//System.out.println(read);
			//System.out.println(ch);
			String str = new String(ch, 0, read);	// ch 배열 중 0부터 시작하여 read 숫자 길이만큼 읽기
			// 앞에꺼보다 성능 더 좋음.
			System.out.print(str);
		}
		fileReader.close();
	}
}
