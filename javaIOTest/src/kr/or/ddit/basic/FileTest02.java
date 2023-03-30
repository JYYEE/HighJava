package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {
	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		
		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "byte(s)");
		System.out.println("path : " + f1.getPath());// 파일 객체 만들 때 쓴 경로 = path 즉, () 안에 쓴 경로 = path
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		// 현재 위치 나타내기
		File f2 = new File("."); // 현재 폴더를 의미. 프로그램이 실행되는 위치
		//File f2 = new File(""); // 현재 폴더를 의미할 때는 경로를 아무것도 안써도 같은 의미
		System.out.println("path : " + f2.getPath());// 파일 객체 만들 때 쓴 경로 = path
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		
		if(f1.isFile()) {
			System.out.println(f1.getName() + "은 파일입니다.");
		} else if(f1.isDirectory()) {
			System.out.println(f1.getName() + "은 디렉토리입니다.");
		} else {
			System.out.println(f1.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		if(f2.isFile()) {
			System.out.println(f2.getName() + "은 파일입니다.");
		} else if(f2.isDirectory()) {
			System.out.println(f2.getName() + "은 디렉토리입니다.");
		} else {
			System.out.println(f2.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		File f3 = new File("d:/d_other/sample.exe"); // 현재 존재하지 않는 파일 지정
		if(f3.isFile()) {
			System.out.println(f3.getName() + "은 파일입니다.");
		} else if(f3.isDirectory()) {
			System.out.println(f3.getName() + "은 디렉토리입니다.");
		} else {
			System.out.println(f3.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		
		
		
		
	}
}
