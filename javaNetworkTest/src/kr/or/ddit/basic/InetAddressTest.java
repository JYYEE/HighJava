package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com의 IP정보 가져오기
		InetAddress ip = InetAddress.getByName("www.naver.com");
		
		System.out.println("HostName : " + ip.getHostName());
		System.out.println("HostAddress : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());
		System.out.println();
		// 같은 네이버인데 ip주소 다름 ==> naver는 사용자가 많으므로 여러개 ip설정해서 사용
		
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost(); 
		System.out.println("HostName : "+ localIP.getHostName());
		System.out.println("HostAddress : " + localIP.getHostAddress());
		System.out.println();
		
		//IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.google.com");
		for (InetAddress tempIP : ipArr) {
			System.out.println(tempIP.toString());
		}
		// ip주소만 가지고 직접 들어가지 못하도록 막아놓기도 함. (보안관련)
}
}
