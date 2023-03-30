package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 문제)
 * 'd:/d_other' 폴더에 있는 'Penguins.jpg'파일을
 * 'd:/d_other/연습용' 폴더에 '복사본_Penguins.jpg'파일로 복사하는 프로그램을 작성하시오.
 */
public class FileCopy {
	public static void main(String[] args) {
		try {
			File pg = new File("d:/d_other/Penguins.jpg");
			FileInputStream inputStream = new FileInputStream(pg);
			File pgcopy = new File("d:/d_other/연습용/복사본_Penguins.jpg");
			FileOutputStream outputStream = new FileOutputStream(pgcopy);
			int c; 
			while ((c = inputStream.read()) != -1) {
				outputStream.write(c);
			}
			inputStream.close();
			outputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
}
