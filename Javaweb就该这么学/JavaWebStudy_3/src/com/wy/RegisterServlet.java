package com.wy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
	}

	@Override
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

		arg1.setContentType("text/html;charset=utf-8");
		String name = arg0.getParameter("username");
		String password = arg0.getParameter("password");
		String rpassword = arg0.getParameter("rpassword");
		String vali = arg0.getParameter("vali");
		HttpSession session = arg0.getSession();
		String backVali = (String) session.getAttribute("vali");
		Connection conn = DatabaseServlet.getConn();
		PreparedStatement st = null;
		if(!vali.equals(backVali)){
			arg1.sendRedirect(getServletContext().getContextPath() + "/register.html");
			System.out.println("验证码不对！！");
		}else{
			try {
				st = conn.prepareStatement("INSERT INTO staff(name,password) VALUES(?,?)");
				st.setString(1, name);
				st.setString(2, password);
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 重定向到登录页
			arg1.sendRedirect(getServletContext().getContextPath() + "/login.html");
			System.out.println("注册成功！");
		}
	}

	@Override
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doPost(arg0, arg1);
	}

	@Override
	public void destroy() {
	}
}
