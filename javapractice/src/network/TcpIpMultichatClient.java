/*
	������ : �� �ҽ��� �����ϵ� ��Ű��(network)�� �ִ� ��η� �̵��Ͽ� 
	"java network.TcpIpMultichatClient ��ȭ��" ���� ����
 */

package network;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpIpMultichatClient {

	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		
		ClientSender(Socket socket, String name){
			this.socket=socket;
			try{
				out=new DataOutputStream(socket.getOutputStream());
				this.name = name;
			}catch(IOException e){}
		}
		
		public void run(){
			Scanner scanner = new Scanner(System.in);
			try{
				if(out!=null)
					out.writeUTF(name);
				//DataOutStream��ü�� �������� ������(������ ����� ������) ��� ������ ���鼭 client ����ڰ� Ÿ���� �Ѱ��� DataOutStream ��ü�� write��
				while(out!=null)
					out.writeUTF("["+name+"]"+scanner.nextLine());	//while(out!=null)�� false�� �Ǹ� DataOutputStream�� ������ ��
			}catch(IOException e){}
		}
	}
	
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		
		ClientReceiver(Socket socket){
			this.socket=socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {}
		}
		
		public void run(){
			//DataInputStream��ü�� �������� ������(������ ����� ������) ��� ������ ���鼭 DataOutStream ��ü�� ������ �����͸� read��
			while(in!=null){
				try {
					System.out.println(in.readUTF());
				} catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args) {
		String serverIP="127.0.0.1";
		
		if(args.length!=1){
			System.out.println("[USAGE]\nMove to the path which compiled package of this source(You should see 'network' folder then type below\njava network.TcpIpMultichatClient talkID\nexit..");
			System.exit(0);
		}
		
		try {
			//������ ������ ClientSender �� ClientReceiver�����带 ������
			Socket socket = new Socket(serverIP,7777);
			new ClientSender(socket,args[0]).start();
			new ClientReceiver(socket).start();

		} catch (ConnectException ce) {
			ce.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
