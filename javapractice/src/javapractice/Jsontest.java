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