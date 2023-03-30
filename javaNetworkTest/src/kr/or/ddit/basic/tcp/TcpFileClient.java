package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpFileClient {
	public static void main(String[] args) {
		try {
			File file = new File("d:/d_other/펭귄.jpg");
			if(!file.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
				return;
			}
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);
			OutputStream sout = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(sout);
			
			dout.writeUTF(file.getName()); // 제목을 서버로 전송
			
			int data; // file내용을 저장할 변수
			while( (data = bin.read()) != -1) {
				dout.write(data);
			}
			System.out.println("소켓으로 파일 출력 완료");
			dout.flush();
			dout.close();
			bin.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
