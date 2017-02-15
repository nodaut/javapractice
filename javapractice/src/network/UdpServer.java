package network;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	
	public void start() throws IOException, UnknownHostException{
		DatagramSocket datagramSocket = new DatagramSocket(7777);	//7777번 포트를 쓰는 소켓을 생성
		InetAddress clientAddress;	//클라이언트의 서버 주소
		int clientPort;	//클라이언트의 포트 주소

		DatagramPacket outPacket,inPacket;	//클라이언트로부터 갖고오는 패킷, 클라이언트에 보내는 패킷 객체

		
		//데이터가 저장될 공간으로 byte배열을 생성
		byte[] inMsg = new byte[100];
		byte[] outMsg;

		while(true){
			//데이터를 수신하기 위한 패킷을 생성
			inPacket = new DatagramPacket(inMsg,inMsg.length);	//클라이언트로부터 갖고오는 패킷 생성 : DatagramPacket(byte[] buf, int length)
			
			//클라이언트로부터 패킷을 받고(receive) 해당 클라이언트의 ip주소와 포트주소를 얻어냄
			datagramSocket.receive(inPacket);
			clientAddress = inPacket.getAddress();
			clientPort = inPacket.getPort();
			
			//현재시간을 메시지로 세팅
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			outMsg = (sdf.format(new Date())).getBytes();
			
			//위에서 얻은 클라이언트의 ip주소와 포트주소를 바탕으로 outMag를 클라이언트에게 보냄(send)
			outPacket = new DatagramPacket(outMsg,outMsg.length,clientAddress,clientPort);
			datagramSocket.send(outPacket);
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("UDP Server starting...");
			new UdpServer().start();	//udp 서버를 실행시킨다
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
