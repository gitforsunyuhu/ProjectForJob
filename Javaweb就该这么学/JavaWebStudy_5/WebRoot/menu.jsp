<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<jsp:useBean id="ub" class="cn.edu.nju.beans.UserBean" scope="session"/>
<%if(ub !=null && ub.getUsername() !=null && !ub.getUsername().equals("")){%>
	你好，<%=ub.getUsername() %><a href="showcart.jsp">查看购物车</a>
<%}else{ %>
	欢迎光临超市！你好！<a href="showcart.jsp">查看购物车</a>
<%}%>