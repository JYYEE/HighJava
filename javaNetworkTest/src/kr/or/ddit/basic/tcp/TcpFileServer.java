package kr.or.ddit.basic.tcp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	public static void main(String[] args) throws IOException {
		
		File file = new File("d:/d_other/연습용");
		if(!file.exists()) {
			file.mkdirs();
		}
		
		ServerSocket serverSocket = new ServerSocket(7777);
		System.out.println("서버가 준비 중입니다...");
		Socket socket = serverSocket.accept();
		
		InputStream sin = socket.getInputStream();
		DataInputStream din = new DataInputStream(sin);
		
		String fileName = din.readUTF();
		File targetFile = new File(file, fileName);
		
		FileOutputStream fout = new FileOutputStream(targetFile);
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		
		int data;
		while( (data = sin.read()) != -1) {
			bout.write(data);
		}
		System.out.println("서버로 파일 저장 완료");
	
		sin.close();
		bout.flush();
		bout.close();
		serverSocket.close();
	}
}
