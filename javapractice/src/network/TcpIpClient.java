package network;

import java.io.*;
import java.net.*;

public class TcpIpClient {

	public static void main(String[] args) {
		
		String serverIP="127.0.0.1";
		try{
			System.out.println("Connecting to server "+serverIP);
			
			//������ �����Ͽ� ������ ��û�Ѵ�. -> ������ �����ϴ°�=�����ϴ°�
			Socket socket = new Socket(serverIP,7777);
			
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			//�������κ��� ���� �����͸� ����Ѵ�
			System.out.println("Message recieved from server is : "+dis.readUTF());
			
			//��Ʈ���� ������ �ݴ´�
			dis.close();
			socket.close();
			
			System.out.println("Disconnected to server");
			
		}catch(ConnectException ce){
			ce.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
