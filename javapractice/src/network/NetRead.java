package network;

import java.io.*;
import java.net.*;

public class NetRead {

	public static void main(String[] args) {
		URL url = null;
		BufferedReader input = null;
		String address = "http://naver.com";
		String line="";
		
		try{
			url = new URL(address);
			
			input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			//input = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));

			while((line=input.readLine())!=null)	//BufferedReader�� ���� �޾ƿ� �����͸� ���پ� �д´�.
				System.out.println(line);
			input.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
/*
-----------------------------------------------
BufferedReader
	- inputstream���� ���� �ؽ�Ʈ �����͸� �о�ö� ��.
		: ���� BufferedReader�� ���ڴ� InputStreamReader ��
	- BufferedReader�� ȿ������ ó���� ���� ���������� ���۸� �����
-----------------------------------------------
url.openStream()�� ����
	- url.openStream()�� 1)�ش� url�� ������ �ϰ�, 2)�� �������� ���� �����͸� ���� �� �ִ� InputStream�� ������
	- url.openStream()�� url.openConnection().getInputStream()�� �������
-----------------------------------------------
*/