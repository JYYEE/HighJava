package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class MultiChatServer {
	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(7777)){
			System.out.println("서버가 준비되었습니다...");
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트와 연결되었습니다...");
			MCServer server = new MCServer(socket);
			MCClient client = new MCClient();
			
			server.start();
			client.start();
		}
	}

}

class MCServer extends Thread {
	private HashMap<String, Socket> clientList;
	private String clientId;
	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dout;
	private Scanner scan;
	
	public HashMap<String, Socket> getClientList() {
		return clientList;
	}

	public void setClientList(HashMap<String, Socket> clientList) {
		this.clientList = clientList;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public DataInputStream getDin() {
		return din;
	}

	public void setDin(DataInputStream din) {
		this.din = din;
	}

	public DataOutputStream getDout() {
		return dout;
	}

	public void setDout(DataOutputStream dout) {
		this.dout = dout;
	}

	public MCServer() {
		super();
	}

	public MCServer(String clientId, Socket socket) {
		super();
		this.clientId = clientId;
		this.socket = socket;
		
	}

	public MCServer(Socket socket) throws IOException {
		this.socket = socket;
		dout = new DataOutputStream(socket.getOutputStream());
		while (clientList.size()>1) {
			String clientId = din.readUTF(); // 대화명 받음
			// id 중복 체크하기 해야함
			if (clientList.containsKey(clientId)) {
				dout.writeUTF("대화명 중복");

			} else {
				dout.writeUTF("OK");
				return;
			}
			clientList.put(clientId, socket);
		}
	}

	@Override
	public void run() {
		while (dout != null) {
			try {
				Set<String> keySet = clientList.keySet();
				Iterator<String> it = keySet.iterator();
				while(it.hasNext()) {
					dout.writeUTF(clientId + " : " + scan.nextLine());										
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
