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
 * չʾ��Ʒ
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
		
		// ��ѯ���ݿ�
		Connection conn=DatabaseServlet.getConn();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from product");
			out.print("<!DOCTYPE html><html><head><meta charset='utf-8'><title>չʾ��Ʒ</title></head><body>");
			out.print("<table width='100%' border='1px'><caption>��Ʒ��Ϣ</caption>");
			out.print("<tr>");
			int count = 0;
			int count2 = 0;
			//ÿ�ĸ���Ʒ��һ����
			while(rs.next()){
				if(count % 4 ==0){
					out.print("</tr>");
				}
				//���ÿһ�еľ�������
				out.print("<td>");
				
				out.print("�˵��������ǣ�"+rs.getString(2)+"<div><img src='"+rs.getString(5) + "'/></div>");
				Statement st1 = conn.createStatement();
				ResultSet rs1 = st1.executeQuery("select count(*) from comment where pid='" + rs.getInt(1) + "'");
				if(rs1.next()){
					out.print("�Ѿ���"+rs1.getInt(1)+ "�������ˣ�");
				}
				if(rs.getInt(4) !=0){
					out.print("����Ʒ���������");
				}
				out.print("</td>");
				count ++;
				//�ٴ��ж��Ƿ�������
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
