package Example;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class jsonExample {
	
	
	public static void main(String[] args) {
		//hashMap 转换成json 对象
		JSONObject json1=new JSONObject();
		Map<String , Object> map1=new HashMap<>();
		map1.put("name","zs");
		map1.put("age", 21);
		json1.putAll(map1);
		System.out.println("json1"+json1);
	}
	
	
}
