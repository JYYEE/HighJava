package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {
	public static void main(String[] args) {
		// 바이트 기반의 파일 출력용 스트림을 이용해서 파일로 출력하기
		FileOutputStream fout = null; // 파일 출력용 스트림 객체 변수 선언
		try {
			// 방법 - 파일 객체 생성. 기존에 있는 파일로 설정하면 기존 파일의 내용 덮어씀.
			File f = new File("d:/d_other/out.txt");
			fout = new FileOutputStream(f);
			
			for(char ch='A' ; ch<='Z'; ch++) {
				fout.write(ch);
			}
			fout.flush();	// 출력 버퍼에 남아있는 자료를 강제로 출력
			
			System.out.println("작업 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
			if(fout != null) {
				try {
					fout.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
		}
		
	}
}
