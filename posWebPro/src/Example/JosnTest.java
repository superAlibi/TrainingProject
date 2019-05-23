package Example;

import java.util.HashMap;
import java.util.Map;

//import javax.print.attribute.HashAttributeSet;

import net.sf.json.JSONObject;



public class JosnTest {
	public static void main(String[] args) {
		String name="李四";
		//创建json数据类型样式
//		String jsonString="{\"name\":\""+name+"\",\"age\":\"李四\"}";
//		System.out.println(jsonString);
		//创建json对象jsonObject   json第三方包
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name",name);
		jsonObject.put("age", 20);
		jsonObject.put("sex","女");
//		System.out.println(jsonObject.toString());
		
		//向json对象添加map
		JSONObject js1=new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "aa");
		map.put("age", 20);
		js1.putAll(map);
//		System.out.println("map"+js1);
		
		//把java对象转化为json对象
		Student stu=new Student();
		JSONObject stuJson=new JSONObject();
		stuJson.put("name", stu.getName());
		stuJson.put("age", stu.getAge());
		stuJson.put("sex", stu.getSex());
		System.out.println("java对象转json:"+stuJson);
		//使用jar包方法fromObject    把javabean 转换为json
		JSONObject js2=JSONObject.fromObject(stu);
		System.out.println("fromObject方法"+js2);
		
		//json对象转换为javabean
//		Student st=(Student)JSONObject.toBean(jsonObject);
//		System.out.println(st.getName());
	}
}	
