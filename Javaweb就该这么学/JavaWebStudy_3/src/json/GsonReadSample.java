package json;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import bean.Diaosi;

public class GsonReadSample {
	public static void main(String[] args){
		File file = new File(GsonReadSample.class.getResource("/wangxiaoer.json").getFile());
		String content = null;
		try {
			content = FileUtils.readFileToString(file,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		//反向解析成Diaosi对象
		Diaosi wangxiaoer = gson.fromJson(content, Diaosi.class);
		System.out.println(wangxiaoer);
	}
}
