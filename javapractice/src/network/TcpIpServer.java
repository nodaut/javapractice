package network;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try{
			//서버소켓을 생성해 7777번 포트와 bind 시킴
			serverSocket = new ServerSocket(7777);
			
			/*  만약 try-catch문으로 익셉션 처리를 안하면 serverSocket을 close해야 함 근데 serverSocket은 일종의 
				데몬 비슷하게 돌아가니까 close를 안하고 이렇게 IO인터럽트가 오면 닫히게끔 코드를 짜는듯 함 */
			
			System.out.println(getTime()+"Server is ready");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		 while(true){
			 try{
				 System.out.println(getTime()+"waiting connection request...");
				 
				 //요청시간을 5초로 설정. 만약 그동안 접속요청이 없으면 accept()메서드는 SocketTimeoutException을 발생시킴
				 serverSocket.setSoTimeout(10*1000);
				
				 //서버소켓은 클라이언트 요청이 들어올때까지 기다린다. 클라이언트로 부터 연결요청이 오면 클라이언트와 통신할 새로운 소켓을 생성한다.
				 Socket socket = serverSocket.accept();
				 System.out.println(getTime()+ "Connection request detected from "+socket.getInetAddress());
				 
				 //서버의 소켓은 정해진 포트만 사용하지만, 클라이언트의 소켓은 임의의 포트를 선택해 사용함. 클라이언트 소켓이 사용하는 포트번호는 계속 랜덤하게 변할 수 있음
				 System.out.println("getPort(): "+socket.getPort());	//클라이언트의 소켓이 사용한 포트
				 System.out.println("getLocalPort(): "+socket.getLocalPort());	//서버 소켓이 사용한 포트
				 
				 //소켓의 출력스트림을 얻는다.
				 OutputStream out = socket.getOutputStream();
				 DataOutputStream dos = new DataOutputStream(out);
				 //OutputStream은 그냥 길 자체, DataOutputStream은  OutputStream이라는 길 안의 데이터가 다닐수 있는 길
				 
				 //클라이언트(원격) 소켓에 데이터를 보낸다.
				 dos.writeUTF("I'm Server..");
				 System.out.println(getTime()+": message has been sent");
				 
				 //스트림과 소켓을 꼭 닫아줘야 함
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
소켓 통신
------------------------------------------------------------
TCP/IP 통신
	- 전화기 - 1:1 통신
	- 연결지향 통신
	- 신뢰성 있지만 다소 느림
	- Socket, ServerSocket 클래스 사용

	- Process
		1) 서버에서는 ServerSocket은 서버의 특정 포트와 결합(bind)되어 클라이언트의 요청을 기다림
		2) 클라이언트에서는 접속할 서버의 IP주소와 포트번호를 가지고 Socket을 만들어 서버에 연결을 요청함
		3) 서버의 ServerSocket은 클라이언트의 연결요청을 받으면 서버에 새로운 Socket을 생성해 클라이언트 Socket과 연결함
		4) 이제 서버가 3)에서 생성한 Socket과 클라이언트 Socket은 ServerSocket과 관계없이 일대일통신함

		* ServerSocket
			- 일종의 전화교환기 역할
			- 직접 통신하는 소켓을 할당하는 역할

		* Socket 
			- 실제통화하는 전화기 역할
			- 클라이언트와 서버는 각각의 소켓에 1:1연결되어 통신함
			- 입력스트림과 출력스트림을 갖고 있음
				: 이 스트림들은 상대편 Socket과 교차연결되어 데이터를 주고받음

		* port
			- 어떤 프로그램을 사용해야 하는지 알려주는 번호
			- 서버의 소켓은 정해진 포트만 사용하지만, 클라이언트의 소켓은 임의의 포트를 선택해 사용함. 
				: 따라서 클라이언트 소켓이 사용하는 포트번호는 계속 랜덤하게 변할 수 있음

------------------------------------------------------------
UDP 통신
	- 소포 - 1:1, 1:n, n:n 통신
	- 비연결지향 통신
	- 신뢰성 없지만 빠름
	- DatagramSocket,DatagramPacket, MulticastSocket 클래스 사용

	- Process
		* 클라이언트와 서버는 기본적으로 동등한 입장. 따라서 TCP/IP처럼 ServerSocket이 없음

		1) 각각의 서버에서는 2가지 종류의 DatagramPacket을 만듬
			inpacket
				DatagramPacket(byte[] buf, int length)로 생성
					: 다른 서버로부터 데이터를 받아내는 패킷 생성자
			outpacket
				DatagramPacket(byte[] buf, int length, InetAddress address, int port)로 생성
					: 다른 서버로 데이터를 전송하는 패킷 생성자 -> 따라서 ip 및 port를 명시해줘야 한다.
		2) 다른 서버와 통신할 DatagramSocket을 만들고 inpacket은 receive()메서드로, outpacket은 send()메서드로 처리함

	* DatagramSocket
	- 실제 통화하는 전화기 역할 : 데이터를 주고받는 통로역할
	- TCP/IP의 소켓과는 달리 입력스트림과 출력스트림으로 통신하지 않고 send(), receive()메서드를 통해 패킷을 주고받음

	* DatagramPacket
		- 헤더와 실제데이터로 구성됨
		- 데이터는 byte배열로 저장됨
		- 헤더에는 패킷을 보낸 서버의 ip와 port번호가 있음
			: 패킷을 받는서버에서는 receive로 받은 패킷객체의 getAddress(), getPort()메서드로 패킷을 보낸 서버의 ip,port를 알아낼수있다 
------------------------------------------------------------
Refernce
	자바의 정석 959p
============================================================
*/
