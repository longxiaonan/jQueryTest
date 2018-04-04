package guava;

import java.util.Optional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hazelcast.com.eclipsesource.json.JsonArray;

public class test {
	public static void main(String[] args) {
		
		JSONObject jo = new JSONObject();
//		JSONArray ja1 = new JSONArray();
//		ja1.add("dd");
//		jo.put("CC", ja1);
//		System.out.println(jo);
//		JSONArray ja = new JSONArray();
//		ja.add("AA");
//		ja.add("bb");
		
		JSONArray jsonArray = jo.getJSONArray("CC");
		
		JSONArray orElse = Optional.ofNullable(jsonArray).orElse(new JSONArray());
		orElse.add("aa");
		orElse.add("bb");
		orElse.add("aa");
		System.out.println(orElse);
		System.out.println(jo);
		jo.put("CC", orElse);
		System.out.println(jo);
		
	}
}
