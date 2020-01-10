<!-- 这个是page指令 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!-- 这个是脚本语句 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<h5>用户登录</h5>
  		<form action="loginc.jsp" method="POST">
  		用户名：<input name="username"	 type="text"/><br>
  		密码：<input name="password" type="password"/>
  		    <input type="submit" value="登录"/>
  		</form>
  		还没有账号？<a href="register.jsp">点此注册</a>
  </body>
</html>