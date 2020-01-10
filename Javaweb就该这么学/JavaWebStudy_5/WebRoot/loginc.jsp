<!-- 这个是page指令 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf8"%>
<%@ page import="java.sql.*" %>
<!-- 设置用户名和密码进行校验 -->
<jsp:useBean id="ub" class="cn.edu.nju.beans.UserBean" scope="session"/>
<jsp:setProperty property="*" name="ub"/>
<% 
if(ub.vali(response)){
}else{
	out.print("用户名密码不对！！");}
%>
	
