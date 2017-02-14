package network;

import java.io.*;
import java.net.*;

public class TcpIpClient {

	public static void main(String[] args) {
		
		String serverIP="127.0.0.1";
		try{
			System.out.println("Connecting to server "+serverIP);
			
			//소켓을 생성하여 연결을 요청한다. -> 소켓을 생성하는것=연결하는것
			Socket socket = new Socket(serverIP,7777);
			
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			//소켓으로부터 받은 데이터를 출력한다
			System.out.println("Message recieved from server is : "+dis.readUTF());
			
			//스트림과 소켓을 닫는다
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
