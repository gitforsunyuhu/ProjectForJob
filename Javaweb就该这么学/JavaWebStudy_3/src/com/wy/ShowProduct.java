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
import javax.servlet.http.HttpSession;

/**
 * 显示商品
 * @author lenovo
 *
 */
public class ShowProduct extends HttpServlet{
	@Override
	public void init(){
	}
	
	@Override
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		arg1.setContentType("text/html;charset=utf8");
		HttpSession session = arg0.getSession();
		String username = (String)session.getAttribute("username");
		
		int currentPage = 0;
		String page = arg0.getParameter("page");
		//第一页
		if(page == null){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(page);
		}
		PrintWriter out = arg1.getWriter();
		
		Connection conn=DatabaseServlet.getConn();
		Statement st = null;
		ResultSet rs = null;
		int i= 0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from product");
			out.print("<!DOCTYPE html><html><head><meta charset='utf-8'><title>展示商品</title></head><body>");
			out.print("欢迎你！" + username);
			out.print("<table width='100%' border='1px'><caption>商品信息</caption>");
			out.print("<tr>");
			
			
			//最后一页
			for(i=0;i< (currentPage-1)*8; i++){
				rs.next();
			}
			//换页
			for(i=0;i< 8;){
				if(!rs.next()){
					break;
				}
				if(i % 4 ==0){
					out.print("</tr>");
				}
				out.print("<td>");
				
				out.print("商品名称："+rs.getString(2)+"<div><img src='"+rs.getString(5) + "'/></div>");
				Statement st1 = conn.createStatement();
				ResultSet rs1 = st1.executeQuery("select count(*) from comment where pid='" + rs.getInt(1) + "'");
				if(rs1.next()){
					out.print("已有"+rs1.getInt(1)+ "人评论！");
				}
				if(rs.getInt(4) !=0){
					out.print("没有礼物！！");
				}
				out.print("</td>");
				i ++;
				if(i % 4 ==0){
					out.print("<tr>");
				}
			}
			out.print("</tr><table>");
			
			//总商品数
			rs = st.executeQuery("select count(pid) from product");
			rs.next();
			int productNumber = rs.getInt(1);
			
			//总页数
			int pageNumber = 0;
			if(productNumber%8 == 0){
				pageNumber = productNumber/8 ;
			}else{
				pageNumber = productNumber/8 + 1;
			}
			
			//显示
			for(i=1;i<=pageNumber ;i++){
				out.print("<a href = 'show?page="+ i+ "'>"+i+"</a>");
			}
			
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
