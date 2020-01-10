package bean;

import com.google.gson.annotations.SerializedName;

public class Diaosi {

//	@SerializedName(name)
	private String name;
	private String[] major;
	private boolean has_gf;
	private String comment;
	private int age;
	
	private transient String ignore;
	
	public String getIgnore() {
		return ignore;
	}
	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getMajor() {
		return major;
	}
	public void setMajor(String[] major) {
		this.major = major;
	}
	public boolean isHas_gf() {
		return has_gf;
	}
	public void setHas_gf(boolean has_gf) {
		this.has_gf = has_gf;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString(){
		return "NAME:"+ name + ",major:" + major.toString() + ",has_gf:" +has_gf + ",comment:" + comment+ ",age:"+age;
	}
}
