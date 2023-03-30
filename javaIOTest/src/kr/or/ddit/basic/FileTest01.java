package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {
	public static void main(String[] args) {
		// File객체 만들기 연습

		// 1. new File(String 파일 또는 경로);
		// ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의
		// 구분자는 '\'를 사용하거나 '/'를 사용할 수 있다.

		// File file1 = new File("D:/D_other/test.txt"); // 구분문자를 '/'로 사용한 경우(1개)
		File file1 = new File("D:\\D_other\\test.txt"); // 구분문자를 '\'로 사용한 경우(2개)

		System.out.println("파일명 : " + file1.getName());
		System.out.println("디렉토리? : " + file1.isDirectory());
		System.out.println("파일? : " + file1.isFile());
		System.out.println();

		File file2 = new File("d:/d_other");
		System.out.println("파일명 : " + file2.getName());
		System.out.println("디렉토리? : " + file2.isDirectory());
		System.out.println("파일? : " + file2.isFile());
		System.out.println();

		// 2. new File(File parent, String child)
		// ==> 'parent'디렉토리 안에 있는 'child'파일을 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println("파일명 : " + file3.getName());
		System.out.println("디렉토리? : " + file3.isDirectory());
		System.out.println("파일? : " + file3.isFile());
		System.out.println();

		// 3. new File(String parent, String child)
		// ==> 'parent'디렉토리 안에 있는 'child'파일을 갖는다.
		File file4 = new File("d:d_other", "test.txt");
		System.out.println("파일명 : " + file4.getName());
		System.out.println("디렉토리? : " + file4.isDirectory());
		System.out.println("파일? : " + file4.isFile());
		System.out.println();
		System.out.println("-------------------------------------");

		// 디렉토리(폴더) 만들기
		/*
		 * - mkdir() ==> File객체의 경로 중 마지막 위치의 디렉토리를 만든다. 
		 * 			 ==> 반환값 : 만들기 성공 : true, 만들기 실패 : false
		 * 			 ==> 중간 부분의 경로가 모두 만들어져 있어야 마지막 위치의 경로를 만들 수 있다.
		 * - mkdirs() ==> 중간 부분의 경로가 없으면 중간 부분의 경로도 같이 만들어 준다. 
		 */
		File file5 = new File("d:/d_other/연습용");// 파일, 폴더 둘 다 없어도 파일객체 생성 가능
		System.out.println(file5.getName() + "의 존재 여부 : " + file5.exists());
		if (!file5.exists()) { // 연습용이 없으면 실행
			if (file5.mkdir()) {
				System.out.println(file5.getName() + "만들기 성공!!!");
			} else {
				System.out.println(file5.getName() + "만들기 실패~~~");
			} // 처음에 실행하면서 연습용 폴더가 생성됨. 그래서 처음에는 성공, 그 이후부터는 실패
				// 폴더명 같으면 중복 생성은 안됨.
		}
		System.out.println();
		File file6 = new File("d:/d_other/test/java/src"); 
		//mkdir은 마지막 폴더인 src만 만들수 있음. d_other안에 test 안에 java 폴더는 존재해야지 만들 수 있음.
		// ==> mkdirs 이용.
		if (file6.mkdirs()) {
			System.out.println(file6.getName() + "만들기 성공!!!");
		} else {
			System.out.println(file6.getName() + "만들기 실패~~~");
		} 
	}
}
