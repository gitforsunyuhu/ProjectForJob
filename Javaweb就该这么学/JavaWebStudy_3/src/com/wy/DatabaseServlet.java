package com.wy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * @author sunyuhu
 *
 */
public class DatabaseServlet extends HttpServlet{

	private static String username ;
	private static String password;
	private static String url;
	private static String driver;
	
	@Override
	public void init(){
		driver = this.getServletConfig().getInitParameter("driver");
		url = this.getServletConfig().getInitParameter("url");
		username = this.getServletConfig().getInitParameter("username");
		password = this.getServletConfig().getInitParameter("password");
		
		System.out.println("databaseServlet 连接成功！");
	}
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
