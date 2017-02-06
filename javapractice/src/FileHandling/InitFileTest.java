package FileHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class InitFileTest {

	public static void main(String[] args) {
	
		Properties p = new Properties(); 
		try{
			p.load(new FileInputStream("test.ini"));
			p.setProperty("DBuser", "lee");	//DBuser라는 key의 값으로  lee를 저장
			System.out.println("user = " + p.getProperty("DBuser"));	//DBuser라는 key의 값을 읽어들임
			p.list(System.out);	//파일에 저장된 key-value쌍을 보여줌
			p.store( new FileOutputStream("test.ini"), "test");	//파일 저장 두번째 파라메터는 주석으로 들어갈 string
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	

}
