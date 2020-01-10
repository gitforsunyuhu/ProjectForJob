package com.wy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			st = DatabaseServlet.getConn().prepareStatement("SELECT * FROM `staff` where name =? and password =?");
			st.setString(1, name);
			st.setString(2, password);
			rs = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean hasUser = false;
		try {
			hasUser = rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(hasUser){
			HttpSession session = request.getSession();
			session.setAttribute("username", name);
			response.sendRedirect("show");
			request.getRequestDispatcher("a").forward(request,response);
		}else{
			response.getWriter().print("用户名或者密码错误！！");
			System.out.println("用户名或者密码错误！！");
		}
	}
	
	@Override
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doPost(arg0,arg1);
	}

}
