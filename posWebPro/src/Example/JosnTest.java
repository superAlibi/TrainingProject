package Example;

import java.util.HashMap;
import java.util.Map;

//import javax.print.attribute.HashAttributeSet;

import net.sf.json.JSONObject;



public class JosnTest {
	public static void main(String[] args) {
		String name="����";
		//����json����������ʽ
//		String jsonString="{\"name\":\""+name+"\",\"age\":\"����\"}";
//		System.out.println(jsonString);
		//����json����jsonObject   json��������
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name",name);
		jsonObject.put("age", 20);
		jsonObject.put("sex","Ů");
//		System.out.println(jsonObject.toString());
		
		//��json�������map
		JSONObject js1=new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "aa");
		map.put("age", 20);
		js1.putAll(map);
//		System.out.println("map"+js1);
		
		//��java����ת��Ϊjson����
		Student stu=new Student();
		JSONObject stuJson=new JSONObject();
		stuJson.put("name", stu.getName());
		stuJson.put("age", stu.getAge());
		stuJson.put("sex", stu.getSex());
		System.out.println("java����תjson:"+stuJson);
		//ʹ��jar������fromObject    ��javabean ת��Ϊjson
		JSONObject js2=JSONObject.fromObject(stu);
		System.out.println("fromObject����"+js2);
		
		//json����ת��Ϊjavabean
//		Student st=(Student)JSONObject.toBean(jsonObject);
//		System.out.println(st.getName());
	}
}	
