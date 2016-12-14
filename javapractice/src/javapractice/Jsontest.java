package javapractice;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Jsontest {

	public static void main(String[] args) {

	//JSONObject ��ü�� ����� key-value�� put ��
	JSONObject jobj = new JSONObject();
	jobj.put("name","test_name");
	jobj.put("num",new Integer(100));
	System.out.println(jobj);

	//JSONArray ��ü�� ����� JSONObject�� add ��
	JSONArray jarr = new JSONArray();
	jarr.add(jobj);
	System.out.println(jarr);
	
	//JSONArray�� ù��° ��Ҹ� ���� �� JSONObject�� ����ȯ �� �� get �޼��带 �̿��� key-value�� �� key�� "name"�� ���� value�� �����´�. 
	System.out.println(((JSONObject)jarr.get(0)).get("name"));
	
	
	JSONParser parser = new JSONParser();
	String sarr = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
	String sobj = "{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}";
	/*
	-----------------------------------------------------------------------------------------
	[string�� JSONArray�� �����]
		string�� �ٷ�(�Ǵ� string��  Object������ casting�� ��) JSONArray�� casting�� �� ����. 
			->string�� �ùٷ� JSONArray������ casting�Ϸ��� JSONParser�� parse�޼��忡 ������Ѿ� �Ѵ�.
	-----------------------------------------------------------------------------------------
	*/
	
	//jarr=(JSONArray)(Object)sarr; //=>����
	try{
		//JSONArray�� string�� parser.parse�� �̿��� �Ľ̰����� object�� ����� JSONArray�� ����ȯ
		jarr=(JSONArray)(parser.parse(sarr));
		
		//JSONObject�� string�� parser.parse�� �̿��� �Ľ̰����� object�� ����� JSONObject�� ����ȯ
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
		- (Hash)Map�� ����
		- key-value�ֵ��� {}�ȿ� �����
			: key-value �ֳ����� ������ �߿����� ����

	JSON Array --> [ , , , ]
		- Array List�� ����
		- ������ []�ȿ� �����
			: key-value �ֳ����� ������ �߿���!

[reference]
https://www.tutorialspoint.com/json/json_java_example.htm
http://stackoverflow.com/questions/12289844/difference-between-jsonobject-and-jsonarray
-----------------------------------------------------------------------------------------
*/