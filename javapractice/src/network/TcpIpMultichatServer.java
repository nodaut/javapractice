package network;

import java.io.*;
import java.net.*;
import java.util.*;

public class TcpIpMultichatServer {
	HashMap clients;
	TcpIpMultichatServer(){
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}
	
	public void start(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try{
			serverSocket = new ServerSocket(7777);
			System.out.println("Server started...");
			
			while(true){
				socket = serverSocket.accept();
				System.out.println("["+ socket.getInetAddress()+":"+socket.getPort()+"]"+"connected!");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void sendToAll(String msg){
		Iterator it = clients.keySet().iterator();	//hashmap의 모든key를 가져오고 이들을 순회할 수 있는 iterator 객체를 생성
		
		while(it.hasNext()){
			try{
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			}catch(IOException ie){}
				
			}
		}
	
	class ServerReceiver extends Thread{	//inner class : class안에 메소드와 동등한 위치로 class가 올 수 있음 
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
	
		ServerReceiver(Socket socket){
			this.socket=socket;	//인자로 들어온 소켓을 이 class의 소켓으로 세팅
			try{
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}catch(IOException ie){}
		}
		
		public void run(){
			String name = "";
			try{
				//client가 이 서버에 접속하면 일단clinets hashmap에 push한다.
				name=in.readUTF();
				sendToAll("#"+name+"entered this chat room!");
				clients.put(name, out);
				System.out.println("Current user number is "+ clients.size());
				
				while(in!=null){	//client 소켓과의 inputstream이 끊어 지기 전까지 계속 loop을 돌면서 메세지가 오면 뿌려준다.  
					sendToAll(in.readUTF());
				}
			}catch(IOException ie){
				;//IOException이 와도 무시 
			}finally{
				/*
				 여기에 도달 했다는것은 while(in!=null)이 false가 되었다는 것이고, 이는 client 소켓과의 inputstream이 끊겼다는 것을 의미한다.
				 따라서해당 name을 hashmap에서 remove한다.
				*/
				sendToAll("#"+name+"has outed this chat room!");
				clients.remove(name);
				System.out.println("["+ socket.getInetAddress()+":"+socket.getPort()+"]"+"disconnected!");
				System.out.println("Current user number is "+ clients.size());
			}
			
		}
	}
	
	public static void main(String[] args) {
		new TcpIpMultichatServer().start();

	}

}
