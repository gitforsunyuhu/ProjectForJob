package com.wy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 展示产品
 * @author lenovo
 *
 */
public class ShowProduct_1 extends HttpServlet{
	@Override
	public void init(){
	}
	
	@Override
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		arg1.setContentType("text/html;charset=utf8");
		PrintWriter out = arg1.getWriter();
		
		// 查询数据库
		Connection conn=DatabaseServlet.getConn();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from product");
			out.print("<!DOCTYPE html><html><head><meta charset='utf-8'><title>展示商品</title></head><body>");
			out.print("<table width='100%' border='1px'><caption>商品信息</caption>");
			out.print("<tr>");
			int count = 0;
			int count2 = 0;
			//每四个商品换一次行
			while(rs.next()){
				if(count % 4 ==0){
					out.print("</tr>");
				}
				//输出每一列的具体内容
				out.print("<td>");
				
				out.print("此电脑名称是："+rs.getString(2)+"<div><img src='"+rs.getString(5) + "'/></div>");
				Statement st1 = conn.createStatement();
				ResultSet rs1 = st1.executeQuery("select count(*) from comment where pid='" + rs.getInt(1) + "'");
				if(rs1.next()){
					out.print("已经有"+rs1.getInt(1)+ "人评论了！");
				}
				if(rs.getInt(4) !=0){
					out.print("该商品有礼物！！！");
				}
				out.print("</td>");
				count ++;
				//再次判断是否被四整除
				if(count % 4 ==0){
					out.print("<tr>");
				}
				
				count2 ++;
			}
			out.print("</tr><table>");
			out.print("</body>");
			out.print("</html>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException{
		doPost(arg0,arg1);
	}
	
	@Override
	public void destroy(){
	}

}
