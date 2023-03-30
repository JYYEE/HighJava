package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		
		try {
			fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			// 버퍼의 크기가 5인 버퍼스트림 객체 생성  
			bout = new BufferedOutputStream(fout, 5);
			
			for (char ch ='1'; ch <='9'; ch++) {
				bout.write(ch);
				// 실행 결과 => 버퍼가 1~5까지 저장되었다가 출력, 6~9까지 저장 => 12345까지만 출력됨.
			}
			// 출력 작업이 끝나면 출력 버퍼에 남아있는 데이터를 강제로 모두 출력시켜줘야 한다. 
			//bout.flush(); // 버퍼가 가득차지 않았더라도 강제로 출력할 수 있게 해줌. // 실행결과 => 1~9까지 출력
			System.out.println("작업 끝...");
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			//if(fout != null) try {fout.close();} catch (IOException e2) {}
			// 보조스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 같이 닫힌다. 
			if(bout != null) try {bout.close();} catch (IOException e2) {}
			// close 에 flush 기능이 내장되어 있어서 남아있는 데이터를 강제로 출력됨. 
			// 그렇더라도 flush를 해주는게 좋음. 
			// close는 작업 끝날때 실행되기때문에 중간에 다른작업이 실행되는 경우가 있기 때문에 버퍼출력작업 끝나고 flush 해주는게 좋음.
		}
	}
}
