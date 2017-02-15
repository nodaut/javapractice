package network;

import java.io.*;
import java.net.*;

public class UdpClient {
	
	public void start() throws IOException, UnknownHostException{
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");	//로컬호스트의 서버 주소를 리턴함
		DatagramPacket outPacket,inPacket;	//서버로부터 갖고오는 패킷, 서버에 보내는 패킷 객체
		
		//데이터가 저장될 공간으로 byte배열을 생성
		byte[] msg = new byte[100];
		
		//서버로 보내는 패킷 생성 : DatagramPacket(byte[] buf, int length, InetAddress address, int port)
		outPacket = new DatagramPacket(msg,1,serverAddress,7777); //길이가 1인 빈메세지를 보냄. 따라서 이 메시지는 클라이언트의 ip,포트정보만 보내는 용도임.
		inPacket = new DatagramPacket(msg,msg.length);	//서버로부터 갖고오는 패킷 생성 : DatagramPacket(byte[] buf, int length)
		
		datagramSocket.send(outPacket);	//DatagramPacket을 전송한다.
		datagramSocket.receive(inPacket);	//DatagramPacket을 수신한다
		
		System.out.println("Current server time : "+new String (inPacket.getData()));
		datagramSocket.close();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("UDP Client starting...");
			new UdpClient().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
