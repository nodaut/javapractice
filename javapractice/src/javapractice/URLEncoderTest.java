package javapractice;

import java.net.URLEncoder;

public class URLEncoderTest {

	public static void main(String[] args) throws Exception{
		String baseURL="https://search.naver.com/search.naver?where=nexearch&query=";
		String keyword="����ȫ";
		String encodedKeyword=URLEncoder.encode(keyword, "UTF-8");
		System.out.println(baseURL+encodedKeyword);
		
		for(int i=0;i<10;i++){
			;
		}
		
/*		
  		1. URLEncoder��?
  			http://meyerweb.com/eric/tools/dencoder ���� �ϵ�, 
			URLEncoder.encode/decod�� URL�� ���̴� string�� encode/decode��
	
		2. �������̽�
			URL�� base�κа� parameter�κ����� ������ parameter�κ��� URLEncoder�� ���ڵ��ϴ� ������� ���� ���̴µ� ��.
			http://stackoverflow.com/questions/10786042/java-url-encoding-of-query-string-parameters
*/
	}
}
