package cn.edu.nju.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.tools.DBTools;

public class ValiUserServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf8");
		
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		Connection conn = DBTools.getConn();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("select name from staff where name=?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				out.println("用户名已被使用！");
				System.out.println("用户名已被使用！");
			}else{
				out.println("用户名可用");
				System.out.println("用户名可用");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	
	
}
