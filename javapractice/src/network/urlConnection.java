package network;

import java.net.*;
import java.io.*;

public class urlConnection {

	public static void main(String[] args) {
		String address = "https://www.naver.com/";
		URL url = null;
		
		try {
			url = new URL(address);	
			URLConnection conn = url.openConnection();	//URLConnection Ŭ������ ��ü conn�� ���ڷ� �Ѿ�� URL��ü�� ���� ���������� ��� ����
			System.out.println("conn.toString() : "+conn.toString());
			System.out.println("conn.getUseCaches() : "+conn.getUseCaches());
			System.out.println("conn.getHeaderField(0) : "+conn.getHeaderField(0));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
