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
			}catch(Exception e){}
		}
		
		public void run(){
			Scanner scanner = new Scanner(System.in);
			try{
				if(out!=null) out.writeUTF(name);
				while(out!=null) out.writeUTF("["+name+"]"+scanner.nextLine());	//while(out!=null)이 false가 되면 DataOutputStream이 끊어진 것
			}catch(Exception e){}
		}
	}
	
	static class ClientReceiver extends Thread{
		
	}
	
	public static void main(String[] args) {

	}

}
