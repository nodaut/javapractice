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
	- inputstream���κ��� �����͸� ����Ʈ������ �������� �о��. 
	- ���ϰ��� ����Ʈ ������ 0~225�� ��
		: ���� inputstream�� ���� �����ߴٸ� -1�� ������
-----------------------------------------------
*/