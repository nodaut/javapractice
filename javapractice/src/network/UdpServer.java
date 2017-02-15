package network;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	
	public void start() throws IOException, UnknownHostException{
		DatagramSocket datagramSocket = new DatagramSocket(7777);	//7777�� ��Ʈ�� ���� ������ ����
		InetAddress clientAddress;	//Ŭ���̾�Ʈ�� ���� �ּ�
		int clientPort;	//Ŭ���̾�Ʈ�� ��Ʈ �ּ�

		DatagramPacket outPacket,inPacket;	//Ŭ���̾�Ʈ�κ��� ������� ��Ŷ, Ŭ���̾�Ʈ�� ������ ��Ŷ ��ü

		
		//�����Ͱ� ����� �������� byte�迭�� ����
		byte[] inMsg = new byte[100];
		byte[] outMsg;

		while(true){
			//�����͸� �����ϱ� ���� ��Ŷ�� ����
			inPacket = new DatagramPacket(inMsg,inMsg.length);	//Ŭ���̾�Ʈ�κ��� ������� ��Ŷ ���� : DatagramPacket(byte[] buf, int length)
			
			//Ŭ���̾�Ʈ�κ��� ��Ŷ�� �ް�(receive) �ش� Ŭ���̾�Ʈ�� ip�ּҿ� ��Ʈ�ּҸ� ��
			datagramSocket.receive(inPacket);
			clientAddress = inPacket.getAddress();
			clientPort = inPacket.getPort();
			
			//����ð��� �޽����� ����
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			outMsg = (sdf.format(new Date())).getBytes();
			
			//������ ���� Ŭ���̾�Ʈ�� ip�ּҿ� ��Ʈ�ּҸ� �������� outMag�� Ŭ���̾�Ʈ���� ����(send)
			outPacket = new DatagramPacket(outMsg,outMsg.length,clientAddress,clientPort);
			datagramSocket.send(outPacket);
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("UDP Server starting...");
			new UdpServer().start();	//udp ������ �����Ų��
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
