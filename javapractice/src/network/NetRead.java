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

			while((line=input.readLine())!=null)	//BufferedReader로 부터 받아온 데이터를 한줄씩 읽는다.
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
	- inputstream으로 부터 텍스트 데이터를 읽어올때 씀.
		: 따라서 BufferedReader의 인자는 InputStreamReader 임
	- BufferedReader은 효과적인 처리를 위해 내부적으로 버퍼를 사용함
-----------------------------------------------
url.openStream()의 역할
	- url.openStream()은 1)해당 url에 접속을 하고, 2)이 접속으로 부터 데이터를 읽을 수 있는 InputStream을 리턴함
	- url.openStream()은 url.openConnection().getInputStream()을 축약명령임
-----------------------------------------------
*/