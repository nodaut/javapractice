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
	
	//JSONArray의 첫번째 요소를 꺼낸 후 JSONObject로 형변환 한 후 get 메서드를 이용해 key-value쌍 중 key가 "name"인 것의 value를 가져온다. 
	System.out.println(((JSONObject)jarr.get(0)).get("name"));
	
	
	JSONParser parser = new JSONParser();
	String sarr = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
	String sobj = "{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}";
	/*
	-----------------------------------------------------------------------------------------
	[string을 JSONArray로 만들기]
		string을 바로(또는 string을  Object형으로 casting한 후) JSONArray로 casting할 수 없다. 
			->string을 올바로 JSONArray형으로 casting하려면 JSONParser의 parse메서드에 통과시켜야 한다.
	-----------------------------------------------------------------------------------------
	*/
	
	//jarr=(JSONArray)(Object)sarr; //=>에러
	try{
		//JSONArray형 string을 parser.parse을 이용해 파싱가능한 object로 만들고 JSONArray로 형변환
		jarr=(JSONArray)(parser.parse(sarr));
		
		//JSONObject형 string을 parser.parse을 이용해 파싱가능한 object로 만들고 JSONObject로 형변환
		jobj=(JSONObject)(parser.parse(sobj));
	}catch(ParseException pe){
		System.out.println("position: " + pe.getPosition());
	}

	System.out.println(jarr.get(1));
	System.out.println(jobj.get("1"));
	System.out.println(((JSONObject)jobj.get("1")).get("2"));
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