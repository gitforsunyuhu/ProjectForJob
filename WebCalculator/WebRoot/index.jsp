<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="calculator" class="com.yuhu.Calculator" />
<jsp:setProperty property="first" name="calculator"/>
<jsp:setProperty property="second" name="calculator"/>
<jsp:setProperty property="type" name="calculator"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>web calculator</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	web 计算器
	<br>
	<!-- this is the first version <form action="servlet/CalculateServlet" method="get"> -->
	<form action="" method="get">
		<table>
			<tr>
				<td>输入第一个数：</td>
				<td><input type="text" name="first"></td>
			</tr>
			<tr>
				<td>计算类型：</td>
				<td><input type="radio" name="type" value="1">+<input
					type="radio" name="type" value="2">-<input type="radio"
					name="type" value="3">* <input type="radio" name="type"
					value="4">/</td>
			</tr>
			<tr>
				<td>输入第二个数：</td>
				<td><input type="text" name="second"></td>
			</tr>
			<tr>
				<td ><input type="submit" value="提交"></td>
	</form>
			<!-- this is the first version ${requestScope.answer} -->
			<td>答案是：<jsp:getProperty name="calculator" property="answer" /> </td>
			</tr>
	</table>
</body>
</html>
