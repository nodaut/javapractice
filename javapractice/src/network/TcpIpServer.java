package network;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try{
			//���������� ������ 7777�� ��Ʈ�� bind ��Ŵ
			serverSocket = new ServerSocket(7777);
			
			/*  ���� try-catch������ �ͼ��� ó���� ���ϸ� serverSocket�� close�ؾ� �� �ٵ� serverSocket�� ������ 
				���� ����ϰ� ���ư��ϱ� close�� ���ϰ� �̷��� IO���ͷ�Ʈ�� ���� �����Բ� �ڵ带 ¥�µ� �� */
			
			System.out.println(getTime()+"Server is ready");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		 while(true){
			 try{
				 System.out.println(getTime()+"waiting connection request...");
				 
				 //��û�ð��� 5�ʷ� ����. ���� �׵��� ���ӿ�û�� ������ accept()�޼���� SocketTimeoutException�� �߻���Ŵ
				 serverSocket.setSoTimeout(10*1000);
				
				 //���������� Ŭ���̾�Ʈ ��û�� ���ö����� ��ٸ���. Ŭ���̾�Ʈ�� ���� �����û�� ���� Ŭ���̾�Ʈ�� ����� ���ο� ������ �����Ѵ�.
				 Socket socket = serverSocket.accept();
				 System.out.println(getTime()+ "Connection request detected from "+socket.getInetAddress());
				 
				 //������ ������ ������ ��Ʈ�� ���������, Ŭ���̾�Ʈ�� ������ ������ ��Ʈ�� ������ �����. Ŭ���̾�Ʈ ������ ����ϴ� ��Ʈ��ȣ�� ��� �����ϰ� ���� �� ����
				 System.out.println("getPort(): "+socket.getPort());	//Ŭ���̾�Ʈ�� ������ ����� ��Ʈ
				 System.out.println("getLocalPort(): "+socket.getLocalPort());	//���� ������ ����� ��Ʈ
				 
				 //������ ��½�Ʈ���� ��´�.
				 OutputStream out = socket.getOutputStream();
				 DataOutputStream dos = new DataOutputStream(out);
				 //OutputStream�� �׳� �� ��ü, DataOutputStream��  OutputStream�̶�� �� ���� �����Ͱ� �ٴҼ� �ִ� ��
				 
				 //Ŭ���̾�Ʈ(����) ���Ͽ� �����͸� ������.
				 dos.writeUTF("I'm Server..");
				 System.out.println(getTime()+": message has been sent");
				 
				 //��Ʈ���� ������ �� �ݾ���� ��
				 dos.close();
				 socket.close();
			 }catch(SocketTimeoutException e){
				 System.out.println("SocketTimeoutException..");
				 System.exit(0);
			 }
			 catch(IOException e){
				 e.printStackTrace();
			 }
		 }
	}
	static String getTime(){
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}

}

/*
============================================================
���� ���
------------------------------------------------------------
TCP/IP ���
	- ��ȭ�� - 1:1 ���
	- �������� ���
	- �ŷڼ� ������ �ټ� ����
	- Socket, ServerSocket Ŭ���� ���

	- Process
		1) ���������� ServerSocket�� ������ Ư�� ��Ʈ�� ����(bind)�Ǿ� Ŭ���̾�Ʈ�� ��û�� ��ٸ�
		2) Ŭ���̾�Ʈ������ ������ ������ IP�ּҿ� ��Ʈ��ȣ�� ������ Socket�� ����� ������ ������ ��û��
		3) ������ ServerSocket�� Ŭ���̾�Ʈ�� �����û�� ������ ������ ���ο� Socket�� ������ Ŭ���̾�Ʈ Socket�� ������
		4) ���� ������ 3)���� ������ Socket�� Ŭ���̾�Ʈ Socket�� ServerSocket�� ������� �ϴ��������

		* ServerSocket
			- ������ ��ȭ��ȯ�� ����
			- ���� ����ϴ� ������ �Ҵ��ϴ� ����

		* Socket 
			- ������ȭ�ϴ� ��ȭ�� ����
			- Ŭ���̾�Ʈ�� ������ ������ ���Ͽ� 1:1����Ǿ� �����
			- �Է½�Ʈ���� ��½�Ʈ���� ���� ����
				: �� ��Ʈ������ ����� Socket�� ��������Ǿ� �����͸� �ְ����

		* port
			- � ���α׷��� ����ؾ� �ϴ��� �˷��ִ� ��ȣ
			- ������ ������ ������ ��Ʈ�� ���������, Ŭ���̾�Ʈ�� ������ ������ ��Ʈ�� ������ �����. 
				: ���� Ŭ���̾�Ʈ ������ ����ϴ� ��Ʈ��ȣ�� ��� �����ϰ� ���� �� ����

------------------------------------------------------------
UDP ���
	- ���� - 1:1, 1:n, n:n ���
	- �񿬰����� ���
	- �ŷڼ� ������ ����
	- DatagramSocket,DatagramPacket, MulticastSocket Ŭ���� ���

	- Process
		* Ŭ���̾�Ʈ�� ������ �⺻������ ������ ����. ���� TCP/IPó�� ServerSocket�� ����

		1) ������ ���������� 2���� ������ DatagramPacket�� ����
			inpacket
				DatagramPacket(byte[] buf, int length)�� ����
					: �ٸ� �����κ��� �����͸� �޾Ƴ��� ��Ŷ ������
			outpacket
				DatagramPacket(byte[] buf, int length, InetAddress address, int port)�� ����
					: �ٸ� ������ �����͸� �����ϴ� ��Ŷ ������ -> ���� ip �� port�� �������� �Ѵ�.
		2) �ٸ� ������ ����� DatagramSocket�� ����� inpacket�� receive()�޼����, outpacket�� send()�޼���� ó����

	* DatagramSocket
	- ���� ��ȭ�ϴ� ��ȭ�� ���� : �����͸� �ְ�޴� ��ο���
	- TCP/IP�� ���ϰ��� �޸� �Է½�Ʈ���� ��½�Ʈ������ ������� �ʰ� send(), receive()�޼��带 ���� ��Ŷ�� �ְ����

	* DatagramPacket
		- ����� ���������ͷ� ������
		- �����ʹ� byte�迭�� �����
		- ������� ��Ŷ�� ���� ������ ip�� port��ȣ�� ����
			: ��Ŷ�� �޴¼��������� receive�� ���� ��Ŷ��ü�� getAddress(), getPort()�޼���� ��Ŷ�� ���� ������ ip,port�� �˾Ƴ����ִ� 
------------------------------------------------------------
Refernce
	�ڹ��� ���� 959p
============================================================
*/
