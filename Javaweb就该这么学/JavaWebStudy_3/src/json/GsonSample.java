package json;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Diaosi;

public class GsonSample {
	
	public static void main(String[] args){
		
		Diaosi diaosi = new Diaosi();
		
		diaosi.setName("wangxiaoer");
		diaosi.setAge(24);
		diaosi.setComment("这个是一个注释");
		diaosi.setHas_gf(true);
		diaosi.setMajor(new String[] { "理发", "挖掘机" });
		diaosi.setIgnore("这个需要被忽略");
		
		GsonBuilder gb = new GsonBuilder();
		//输出的格式是标准的json格式
		//gb.setPrettyPrinting();
		gb.setFieldNamingStrategy(new FieldNamingStrategy() {
			
			/*
			 * 定制自己的策略
			 */
			@Override
			public String translateName(Field f) {
				if(f.getName().equals("name")){
					return "NAME";
				}
				return f.getName();
			}
		});
		
		Gson gson1 = gb.create();
		System.out.println(gson1.toJson(diaosi));
		//Gson gson = new Gson();
		//System.out.println(gson.toJson(diaosi));
	}
}
