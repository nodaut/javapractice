package javapractice;

import java.net.URLEncoder;

public class URLEncoderTest {

	public static void main(String[] args) throws Exception{
		String baseURL="https://search.naver.com/search.naver?where=nexearch&query=";
		String keyword="이재홍";
		String encodedKeyword=URLEncoder.encode(keyword, "UTF-8");
		System.out.println(baseURL+encodedKeyword);
		
		for(int i=0;i<10;i++){
			;
		}
		
/*		
  		1. URLEncoder란?
  			http://meyerweb.com/eric/tools/dencoder 에서 하듯, 
			URLEncoder.encode/decod는 URL에 쓰이는 string을 encode/decode함
	
		2. 유즈케이스
			URL을 base부분과 parameter부분으로 나누고 parameter부분을 URLEncoder로 인코딩하는 방식으로 많이 쓰이는듯 함.
			http://stackoverflow.com/questions/10786042/java-url-encoding-of-query-string-parameters
*/
	}
}
