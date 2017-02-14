/*
	실행방법 : 이 소스의 컴파일된 패키지(network)가 있는 경로로 이동하여 
	"java network.TcpIpMultichatClient 대화명" 으로 실행
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
				//DataOutStream객체가 없어지기 전까지(접속이 끊기기 전까지) 계속 루프를 돌면서 client 사용자가 타이핑 한것을 DataOutStream 객체에 write함
				while(out!=null)
					out.writeUTF("["+name+"]"+scanner.nextLine());	//while(out!=null)이 false가 되면 DataOutputStream이 끊어진 것
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
			//DataInputStream객체가 없어지기 전까지(접속이 끊기기 전까지) 계속 루프를 돌면서 DataOutStream 객체로 들어오는 데이터를 read함
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
			//소켓을 생성해 ClientSender 및 ClientReceiver쓰레드를 시작함
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
