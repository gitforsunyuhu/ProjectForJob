package bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class DiaosiForGson {

//	@SerializedName(name)
	private String name;
	private Set<String> major;
	private boolean has_gf;
	private String comment;
	private int age;
	private Date birthday;
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
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
	public Set<String> getMajor() {
		return major;
	}
	public void setMajor(Set<String> major) {
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
