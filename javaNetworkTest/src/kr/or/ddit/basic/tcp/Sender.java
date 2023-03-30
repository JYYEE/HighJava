package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

// 이 쓰레드 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당한다.

public class Sender extends Thread {
	// 통신을 연결할 소켓, 메세지를 보내는 보조스트림, 메시지를 보낸 사람 이름, 메세지를 입력받을 스캐너 필요
	private Socket socket;
	private DataOutputStream dout;
	private String name;
	private Scanner scan;
	
	// 생성자
	public Sender(Socket socket) {
		this.socket = socket; // 통신 연결하는 소켓을 초기화
		scan = new Scanner(System.in);
		
		System.out.print("이름 입력 >> ");
		name = scan.nextLine();
		
		// 출력용 스트림 객체 생성
		try {
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void run() {
		while (dout != null) {
			try {
				dout.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
