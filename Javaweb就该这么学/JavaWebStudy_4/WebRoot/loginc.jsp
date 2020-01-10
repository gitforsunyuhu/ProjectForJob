<!-- 这个是page指令 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf8"%>
<%@ page import="java.sql.*" %>
<!-- 这个是脚本语句 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String username = request.getParameter("username");
String password = request.getParameter("password");
//连接数据库并检验用户名和密码是否正确
try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zdmedu", "root", "root");
	PreparedStatement pst = conn.prepareStatement("select * from staff where name = ? and password = ?");
	pst.setString(1, username);
	pst.setString(2, password);
	
	ResultSet rs = pst.executeQuery();
	if(rs.next()){
		System.out.print("欢迎！！");
		out.print(rs.getString(2) + "欢迎！！！");
		session.setAttribute("username", username);
		//添加cookie
		Cookie cookie = new Cookie("username",username.trim());
		//设置cookie存活时间,以秒为单位
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
		response.sendRedirect("show.jsp");
	}else{
		out.print("用户名密码不对！！");%>
		<jsp:forward page="index.jsp"/>
	<%
	}
}catch(Exception e){
	e.printStackTrace();
}
%>