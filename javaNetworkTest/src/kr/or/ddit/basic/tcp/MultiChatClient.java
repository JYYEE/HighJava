package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiChatClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 소켓 객체 생성해서 서버에 접속 요청
		Socket socket = new Socket("localhost", 7777);
		System.out.println("서버에 연결되었습니다.");
		// 접속이 완료되면 '대화명'을 입력 받아서 서버로 전송한다.
		MCServer server = new MCServer(socket);
		MCClient client = new MCClient();
		
		server.start();
		client.start();
	}

}

class MCClient extends Thread{
	private String clientId;
	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dout;
	private Scanner scan;

	public MCClient() {
		super();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public MCClient(Socket socket) throws IOException {
		super();
		this.setSocket(socket);

		scan = new Scanner(System.in);
		din = new DataInputStream(socket.getInputStream());
		while(true) {
		System.out.print("대화명 입력 >> ");
		clientId = scan.nextLine();
		dout.writeUTF(clientId); // 대화명 서버로 보냄
		}
		
	}

	public MCClient(String clientId, Socket socket) {
		super();
		this.clientId = clientId;
		this.setSocket(socket);
	}
	@Override
	public void run() {
		while(dout!= null) {
			try {
				dout.writeUTF(clientId + " : " + scan.nextLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		try {
			int data;
			while((data = din.read()) != -1) {
				dout.write(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}