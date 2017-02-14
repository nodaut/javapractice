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
		Iterator it = clients.keySet().iterator();	//hashmap�� ���key�� �������� �̵��� ��ȸ�� �� �ִ� iterator ��ü�� ����
		
		while(it.hasNext()){
			try{
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			}catch(IOException ie){}
				
			}
		}
	
	class ServerReceiver extends Thread{	//inner class : class�ȿ� �޼ҵ�� ������ ��ġ�� class�� �� �� ���� 
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
	
		ServerReceiver(Socket socket){
			this.socket=socket;	//���ڷ� ���� ������ �� class�� �������� ����
			try{
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}catch(IOException ie){}
		}
		
		public void run(){
			String name = "";
			try{
				//client�� �� ������ �����ϸ� �ϴ�clinets hashmap�� push�Ѵ�.
				name=in.readUTF();
				sendToAll("#"+name+"entered this chat room!");
				clients.put(name, out);
				System.out.println("Current user number is "+ clients.size());
				
				while(in!=null){	//client ���ϰ��� inputstream�� ���� ���� ������ ��� loop�� ���鼭 �޼����� ���� �ѷ��ش�.  
					sendToAll(in.readUTF());
				}
			}catch(IOException ie){
				;//IOException�� �͵� ���� 
			}finally{
				/*
				 ���⿡ ���� �ߴٴ°��� while(in!=null)�� false�� �Ǿ��ٴ� ���̰�, �̴� client ���ϰ��� inputstream�� ����ٴ� ���� �ǹ��Ѵ�.
				 �����ش� name�� hashmap���� remove�Ѵ�.
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
