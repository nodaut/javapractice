package network;

import java.io.*;
import java.net.*;

public class NetWrite {

	public static void main(String[] args) {
		URL url = null;
		InputStream in = null;
		FileOutputStream out = null;
		String address = "http://snu.ac.kr";
		int ch=0;
		
		try{
			url = new URL(address);
			in = url.openStream();
			System.out.println(System.getProperty("user.dir"));
			out=new FileOutputStream("test.html");
			
			while((ch=in.read())!=0){
				System.out.println(ch);
				out.write(ch);
			}
			in.close();
			out.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
/*
-----------------------------------------------
int java.io.InputStream.read()
	- inputstream으로부터 데이터를 바이트단위로 차례차례 읽어옴. 
	- 리턴값은 바이트 값으로 0~225가 됨
		: 만약 inputstream의 끝에 도달했다면 -1을 리턴함
-----------------------------------------------
*/