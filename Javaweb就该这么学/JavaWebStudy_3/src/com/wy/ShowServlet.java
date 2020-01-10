package com.wy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowServlet extends HttpServlet {

	@Override
	public void init() {
	}

	@Override
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

		arg1.setContentType("text/html;charset=utf-8");
		String name = arg0.getParameter("username");
		String password = arg0.getParameter("password");
		Connection conn = DatabaseServlet.getConn();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO staff(name,password) VALUES(?,?)");
			st.setString(1, name);
			st.setString(2, password);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 跳转到登录界面
		arg1.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
		System.out.println("注册成功！！！");

	}

	@Override
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doPost(arg0, arg1);
	}

	@Override
	public void destroy() {
	}
}
