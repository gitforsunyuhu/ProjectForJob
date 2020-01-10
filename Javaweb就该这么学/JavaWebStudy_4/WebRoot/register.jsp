<!-- 这个是page指令 -->
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!-- 这个是脚本语句 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  	<script type="text/javascript">
	function newObject(){
		if(window.XMLHttpRequest){
			req = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			req = new ActiveXObject("Microsoft.XMLHttp");
		}
		
		req.onreadystatechange =press;
		req.open("GET", "valiUser.jsp?username="+f1.username.value, true);
		req.send(null);
	}
	function press() {
		if(req.readyState == 4){
			if(req.status == 200){
				console.log(req.responseText);
			}
		}
	}
	</script>
  	<body>
  		<h5>注册</h5>
  		<form id="f1" action="registerc.jsp" method="POST">
  			用户名<input name="username"	type="text" onBlur="newObject()"/><br>
  			密码<input name="password" type="password"/><br>
 			确认密码<input name="rpassword" type="password" /><br>
  			电子邮箱<input name="email" type="text"/><br>
  			验证码<input name="vali" type="text"/>
  			<img id="im" src="vali.jsp" width="80" height="25"><a href="">看不清</a>
  		    <input type="submit" value="注册	"/>
  		</form>
	</body>
</html>
