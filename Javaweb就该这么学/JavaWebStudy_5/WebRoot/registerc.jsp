<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8" contentType="image/jpeg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="userbean" class="cn.edu.nju.beans.UserBean" />
<jsp:setProperty property="*" name="userbean"/>
 <%
 if(userbean.valiUser(request)){
 	session.setAttribute("username", userbean.getUsername());
 	System.out.print("注册成功！");%>
 	<jsp:forward page="index.jsp" />
 <%}else{
 	System.out.print("注册失败！");%>
	<jsp:forward page="register.jsp" />
 <%} %>

