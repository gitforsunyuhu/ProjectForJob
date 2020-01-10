<%@page import="cn.edu.nju.tools.DBTools"%>
<%@ page language="java"
	import="java.util.*,java.sql.*,cn.edu.nju.beans.BuyBean"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:useBean id="pro" class="cn.edu.nju.beans.Product" scope="session" />
<jsp:setProperty property="*" name="pro" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>我的购物车</title>

<script>
	function cut(pid, index) {
		var list = document.getElementsByName("count");
		if (list[index].value > 0) {
			list[index].value--;
			location.replace("showcart.jsp?id=" + pid + "&count=" + (list[index].value));
		}else{
			location.replace("showcart.jsp?del=" +pid);
		}
	}
	function add(pid, index) {
		var list = document.getElementsByName("count");
		list[index].value++;
		location.replace("showcart.jsp?id=" + pid + "&count=" + (list[index].value));
	}
	function change(pid, index) {
		location.replace("showcart.jsp?id=" + pid + "&count=" + (list[index].value));
	}
</script>

</head>

<body>
	<form name="f1">
		<table border="1">
			<tr bgColor="00ccff" align="center">
				<td width='7%'>商品编号</td>
				<td>商品名称</td>
				<td width='14%'>京东价</td>
				<td width='9%'>商品数量</td>
				<td width='7%'>删除商品</td>
			</tr>
			<%
				int count = 0;
				//判断行数
				int index = 0;
				//判断购物车是否是空的
				if (pro.getCartList() == null || pro.getCartList().isEmpty()) {
					out.print("啥也没买呀！！");
				} else {
					for (BuyBean bb : pro.getCartList()) {
			%>
			<tr align="center">
				<td><%=bb.getPid()%></td>
				<td><img src='<%=bb.getImg()%>' width='30' ,height='30'
					border='none' align="top" /> <%=bb.getName()%></td>
				<td><font color="red">&yen;<%=bb.getPrice()%>
				</font></td>
				<td><a href='javascript:cut(<%=bb.getPid()%>,<%=index%>);'
					title='减一'> -</a> <input type='text' name='count'
					onblur='change(<%=bb.getPid()%>,<%=index%>)' maxlength='4' size="1"
					value='<%=bb.getCount()%>' /> <a
					href='javascript:add(<%=bb.getPid()%>,<%=index%>)' title='加一'>
						+</a></td>
				<td><a href='showcart.jsp?del=<%=bb.getPid()%>'>删除</a></td>
			</tr>
			<%
				index++;
					}
				}
			%>
			<tr>
				<td colspan="7" align="right">原始金额：￥<%=pro.getTotalPrice()%>元 -
					返现：￥0.00元<br />商品总金额：￥<%=pro.getTotalPrice()%>元
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
