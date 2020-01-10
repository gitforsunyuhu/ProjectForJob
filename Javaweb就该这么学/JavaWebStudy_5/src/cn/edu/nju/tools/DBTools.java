package cn.edu.nju.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTools {
	
	private final static String username="root";
	private final static String password="root";
	private final static String url="jdbc:mysql://localhost:3306/zdmedu";
	private final static String driver = "com.mysql.jdbc.Driver";
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
