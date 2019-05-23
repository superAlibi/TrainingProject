package Example;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonArray {
	public static void main(String[] args) {
		//创建json数组
		JSONArray ja=new JSONArray();
		for(int i=0;i<10;i++) {
			JSONObject js=new JSONObject();
			js.put("name", "lisi"+i);
			js.put("age", 20);
			ja.add(js);
		}
		System.out.println(ja);
	}
}
