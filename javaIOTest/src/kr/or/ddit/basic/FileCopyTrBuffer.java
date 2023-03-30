package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 문제)
 * 'd:/d_other' 폴더에 있는 'Penguins.jpg'파일을
 * 'd:/d_other/연습용' 폴더에 '복사본_Penguins.jpg'파일로 복사하는 프로그램을 작성하시오.
 */
public class FileCopyTrBuffer {
	public static void main(String[] args) {
		File file = new File("d:/d_other/Penguins.jpg");
		if(!file.exists()) { // 원본 파일이 없으면...
			System.out.println("복사할 원본 파일 " + file.getName() + "이(가) 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
		File targetDir = new File("d:/d_other/연습용");
		if(!targetDir.exists()) {// 저장할 폴더가 없으면...
			targetDir.mkdirs();
		}
		
		File targetFile = new File(targetDir, "복사본_Penguins.jpg");
		
		// 바이트기반, 문자기반으로 처리할 것인지 결정
		// 문자가 아닌데 문자기반으로 읽으면 잘못해석할 여지가 있음.
		// 복사는 바이트 기반 스트림 이용.
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		try {
			// 원본 파일을 처리할 스트림 객체 생성
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);
			// 대상 파일을 처리할 스트림 객체 생성
			fout = new FileOutputStream(targetFile);
			bout = new BufferedOutputStream(fout);
			System.out.println("복사 시작...");
			
			int data; // 읽어온 데이터를 저장할 변수
			
//			while ((data = fin.read()) != -1) {
//				fout.write(data);
//			}
//			fout.flush();
			while ((data = bin.read()) != -1) {
				bout.write(data);
			}
			bout.flush();
			
			System.out.println("복사 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
//			if(fin != null) try {
//				fin.close();
//			} catch (IOException e2) {
//				// TODO: handle exception
//			}
//			if(fout!=null) try {
//				fout.close();
//			} catch (IOException e2) {
//				// TODO: handle exception
//			}
			if(bin != null) try {
				bin.close();
			} catch (IOException e2) {
				// TODO: handle exception
			}
			if(bout!=null) try {
				bout.close();
			} catch (IOException e2) {
				// TODO: handle exception
			}
		}
	}
}
