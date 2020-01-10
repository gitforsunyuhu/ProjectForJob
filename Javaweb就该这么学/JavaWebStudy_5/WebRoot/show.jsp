<!-- 这个是page指令 -->
<%@ page language="java" import="java.util.*,java.sql.*,cn.edu.nju.beans.*"
	pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!-- 将menu.jsp使用jsp标记包含进来 -->
<jsp:include page="menu.jsp" />
<jsp:useBean id="pro" class="cn.edu.nju.beans.Product"  scope="session"/>
<jsp:setProperty property="*" name="pro"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>展示页面</title>
</head>
<body>
	<table border="1px">
		<tr>
		<%
		ArrayList<ProductBean> list = pro.getProList();
		int cell =0;
		//显示当前的记录
		for (ProductBean product: list) {
		cell ++;
		%>
			<!-- 名称 -->
			<td><%=product.getName()%></td>
			<!-- 单价 -->
			<td><%=product.getPrice()%></td>
			<!-- 礼物 -->
			<td><%=product.getGift()%></td>
			<!-- 图片 -->
			<td><img src="<%=product.getImg()%>" /></td>
			<!-- 相关信息 -->
			<td><%=product.getInfo()%></td>
			<!-- 评论 -->
			<td>已经有&nbsp;<%=product.getComment()%>人评论
			</td>
			<td><input type="button" name="addToCart"
				onclick="location.replace('show.jsp?pid=<%=product.getPid() %>&nowPage=<%=pro.getNowPage()%>');"
				value="加入购物车" /> <input type="button" name="buy" value="购买" /></td>
		<%
		if(cell %3 ==0 && cell !=0){%>
			</tr>
		<%}}%>
	</table>
		<%
		//显示分页
		for (int i = 1; i <= pro.getTotalPage(); i++) {
			out.print("<a href='show.jsp?nowPage=" + (i) + "'>" + (i) + "</a>");
		}
	%>
</body>
</html>