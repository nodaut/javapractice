package FileHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class InitFileTest {

	public static void main(String[] args) {
	
		Properties p = new Properties(); 
		try{
			p.load(new FileInputStream("test.ini"));
			p.setProperty("DBuser", "lee");	//DBuser��� key�� ������  lee�� ����
			System.out.println("user = " + p.getProperty("DBuser"));	//DBuser��� key�� ���� �о����
			p.list(System.out);	//���Ͽ� ����� key-value���� ������
			p.store( new FileOutputStream("test.ini"), "test");	//���� ���� �ι�° �Ķ���ʹ� �ּ����� �� string
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	

}
