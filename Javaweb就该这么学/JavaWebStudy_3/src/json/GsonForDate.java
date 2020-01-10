package json;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Diaosi;
import bean.DiaosiForGson;

public class GsonForDate {
	public static void main(String[] args){
		File file = new File(GsonForDate.class.getResource("/wangxiaoer.json").getFile());
		String content = null;
		try {
			content = FileUtils.readFileToString(file,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		GsonBuilder gb = new GsonBuilder();
		Gson gs = gb.setDateFormat("yyyy-MM-dd").create();
		DiaosiForGson dfg = gs.fromJson(content, DiaosiForGson.class);
		System.out.println(dfg.getBirthday().toLocaleString());
		
		//gson会自动识别出集合类型
		System.out.println(dfg.getMajor());
		System.out.println(dfg.getMajor().getClass());
		
	}
}
