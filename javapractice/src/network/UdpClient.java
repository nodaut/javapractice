package network;

import java.io.*;
import java.net.*;

public class UdpClient {
	
	public void start() throws IOException, UnknownHostException{
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");	//����ȣ��Ʈ�� ���� �ּҸ� ������
		DatagramPacket outPacket,inPacket;	//�����κ��� ������� ��Ŷ, ������ ������ ��Ŷ ��ü
		
		//�����Ͱ� ����� �������� byte�迭�� ����
		byte[] msg = new byte[100];
		
		//������ ������ ��Ŷ ���� : DatagramPacket(byte[] buf, int length, InetAddress address, int port)
		outPacket = new DatagramPacket(msg,1,serverAddress,7777); //���̰� 1�� ��޼����� ����. ���� �� �޽����� Ŭ���̾�Ʈ�� ip,��Ʈ������ ������ �뵵��.
		inPacket = new DatagramPacket(msg,msg.length);	//�����κ��� ������� ��Ŷ ���� : DatagramPacket(byte[] buf, int length)
		
		datagramSocket.send(outPacket);	//DatagramPacket�� �����Ѵ�.
		datagramSocket.receive(inPacket);	//DatagramPacket�� �����Ѵ�
		
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
