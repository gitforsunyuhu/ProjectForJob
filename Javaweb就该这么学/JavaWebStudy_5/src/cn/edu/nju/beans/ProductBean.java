package cn.edu.nju.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cn.edu.nju.tools.DBTools;

/**
 * 商品bean类
 * @author lenovo
 *
 */
public class ProductBean {
	private String pid;
	private String name;
	private double price;
	private String gift;
	private String img;
	private String info;
	private int comment;
	
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	
	/*
	 * 获取当所有的产品信息
	 */
	public List getAllProduct(){
		Connection conn = DBTools.getConn();
		ResultSet rs;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select pid,name,price,gift,info,img from priduct");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List list = new LinkedList();
		return list;
	}
}
