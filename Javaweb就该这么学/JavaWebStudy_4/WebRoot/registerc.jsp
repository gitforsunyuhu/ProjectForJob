<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8" contentType="image/jpeg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
 <%
 	try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zdmedu","root","root");
	PreparedStatement ps = conn.prepareStatement("insert into staff(name,password) values(?,?)");
	String username = request.getParameter("username");
	ps.setString(1, username);
	ps.setString(2,(String)request.getParameter("password"));
	String valiStr = request.getParameter("vali");
	if(valiStr.equals(session.getAttribute("vali"))){
		if(!ps.execute()){
			session.setAttribute("username", username);
			System.out.print("注册成功！");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}else{
			System.out.print("注册失败！");
			response.sendRedirect("register.jsp");
		}
	}else{
		response.sendRedirect("register.jsp");
	}
}catch(Exception e){
	e.printStackTrace();
}
  %>

