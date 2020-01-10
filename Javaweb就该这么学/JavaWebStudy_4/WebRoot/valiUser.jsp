<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//验证是否有用户已经注册了
response.setContentType("text/html");
String username = request.getParameter("username");
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zdmedu","root","root");
PreparedStatement pst = null;
try {
	pst = conn.prepareStatement("select * from staff where name=?");
	pst.setString(1, username);
	ResultSet rs = pst.executeQuery();
	if(rs.next()){
		out.println("用户名已被使用！");
		System.out.println("用户名已被使用！");
	}else{
		out.println("用户名可用");
		//request.getRequestDispatcher("register.jsp").forward(request, response);
		System.out.println("用户名可用");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>