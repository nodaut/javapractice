package javapractice;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class Jsontest {

	public static void main(String[] args) {

	//JSONObject 객체를 만들고 key-value를 put 함
	JSONObject jobj = new JSONObject();
	jobj.put("name","test_name");
	jobj.put("num",new Integer(100));
	System.out.println(jobj);

	//JSONArray 객체를 만들고 JSONObject를 add 함
	JSONArray jarr = new JSONArray();
	jarr.add(jobj);
	System.out.println(jarr);
	
	}

}

/*
-----------------------------------------------------------------------------------------
JSON Object vs JSON Array
	JSON Object --> { "":""}
		- (Hash)Map과 같음
		- key-value쌍들이 {}안에 저장됨
			: key-value 쌍끼리의 순서는 중요하지 않음

	JSON Array --> [ , , , ]
		- Array List과 같음
		- 값들은 []안에 저장됨
			: key-value 쌍끼리의 순서는 중요함!

[reference]
https://www.tutorialspoint.com/json/json_java_example.htm
http://stackoverflow.com/questions/12289844/difference-between-jsonobject-and-jsonarray
-----------------------------------------------------------------------------------------
*/