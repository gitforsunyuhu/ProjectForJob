package json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.*;

import bean.Diaosi;

public class JSONObjectSample {

	public static void main(String[] args) {

		/*
		 * 三种创建json对象的方法
		 */
		// JSONObject();
		// createByMap();
		// createByBean();

		/*
		 * 从json文件中读取信息，并快速创建json对象
		 */
		File file = new File(JSONObjectSample.class.getResource("/wangxiaoer.json").getFile());
		try {
			String content = FileUtils.readFileToString(file, "UTF-8");
			JSONObject jsonob = new JSONObject(content);
			if (!jsonob.isNull("name")) {
				System.out.println("姓名：" + jsonob.getString("name"));
			}
			System.out.println("年龄：" + jsonob.getInt("age"));
			JSONArray ja = jsonob.getJSONArray("major");
			for (int i = 0; i < ja.length(); i++) {
				System.out.println("专业-" + (i + 1) + ja.getString(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void JSONObject() {
		Object nullobj = null;

		JSONObject jsonob = new JSONObject();
		jsonob.put("name", "wangxiaoer");
		jsonob.put("age", 24);
		jsonob.put("has_gf", true);
		jsonob.put("car", nullobj);
		jsonob.put("major", new String[] { "理发", "挖掘机" });
		jsonob.put("comment", "这个是一个注释");
		System.out.println(jsonob.toString());
	}

	/*
	 * 使用hashmap生成jsonObject
	 */
	private static void createByMap() {
		Object nullobj = null;

		Map<String, Object> jsonob = new HashMap<String, Object>();

		jsonob.put("name", "wangxiaoer");
		jsonob.put("age", 24);
		jsonob.put("has_gf", true);
		jsonob.put("car", nullobj);
		jsonob.put("major", new String[] { "理发", "挖掘机" });
		jsonob.put("comment", "这个是一个注释");

		JSONObject jsob = new JSONObject(jsonob);

		System.out.println(jsob.toString());
	}

	/**
	 * 通过java bean创建json对象
	 */
	private static void createByBean() {
		Diaosi diaosi = new Diaosi();

		diaosi.setName("wangxiaoer");
		diaosi.setAge(24);
		diaosi.setComment("这个是一个注释");
		diaosi.setHas_gf(true);
		diaosi.setMajor(new String[] { "理发", "挖掘机" });

		JSONObject jsob = new JSONObject(diaosi);

		System.out.println(jsob.toString());
	}

}
