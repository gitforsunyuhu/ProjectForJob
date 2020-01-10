<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!
    double total=0.0;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的购物车</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script>
	function cut(pid,index){
		var list = document.getElementsByName("count");
		if(list[index].value > 0){
			list[index].value --;
			location.replace("showcart.jsp?pid="+pid+"&count="+(list[index].value));
		}
	}
	function add(pid,index){
		var list = document.getElementsByName("count");
		list[index].value ++;
		location.replace("showcart.jsp?pid="+pid+"&count="+(list[index].value));
	}
	function change(pid,index){
		location.replace("showcart.jsp?pid="+pid+"&count="+(list[index].value));
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
			<td width='8%'>返现</td>
			<td width='8%'>赠送积分</td>
			<td width='9%'>商品数量</td>
			<td width='7%'>删除商品</td>
		</tr>
		<%
  	//和数据库建立连接
  	Connection conn = null;
  	try{
  		Class.forName("com.mysql.jdbc.Driver");
  		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zdmedu","root","root");
	  	//获取商品数据
	  	PreparedStatement pstate = conn.prepareStatement("select name,price,gift,img,info,pid from product where pid=?");
	  	//根据session中的数据查询到相应的商品的详细信息
	  	HashMap cart = (HashMap)session.getAttribute("cart");
	  	
	  	//根据加减按钮改变cart对象中的内容
	  	String cc = request.getParameter("count");
	  	if(cc != null){
	  		String addPid = request.getParameter("pid");
	  		cart.put(addPid, Integer.parseInt(cc));
	  	}
	  	
	  	//处理删除按钮
	  	String del = request.getParameter("del");
	  	if(del != null){
	  		String addPid = request.getParameter("pid");
	  		cart.remove(addPid);
	  	}
	  	ResultSet rs = null;
	  	%>
	  	<script>
	  	var	total =0.0; //汇总金额
	  	</script>
	  	<% 
	  	int count =0;
	  	//判断行数
	  	int index =0;
	  	//判断购物车是否是空的
	  	if(cart == null || cart.isEmpty() ||cart.keySet().isEmpty()){
	  		out.print("啥也没买呀！！");
	  	}else{
		  	for(Object pid:cart.keySet()){
		  		//获取每一个商品的相关信息以及数量等
		  		System.out.println(pstate);
		  		if(pid == null){
		  			cart.remove(pid);
		  			break;
		  		}
		  		System.out.println(" pid:" + pid.toString());
		  		pstate.setString(1, pid.toString());
		  		rs = pstate.executeQuery();
		  		rs.next();
		  		count = Integer.parseInt(cart.get(pid).toString());
		  		if(count == 0){
		  			cart.remove(pid);
		  			continue;
		  		}
		  		%>
	  			<script>
			  		total += <%=(rs.getDouble(2))*count%>;
			  	</script>
		<tr align="center">
			<td><%= pid.toString() %></td>
			<td><img src='<%=rs.getString(4) %>' width='30' ,height='30'
				border='none' align="top" /> <%=rs.getString(1) %></td>
			<td><font color="red">&yen;<%= rs.getString(2) %>
			</font></td>
			<td>&yen;0.00</td>
			<td>0</td>
			<td>
				<a href='javascript:cut(<%=rs.getString(6)%>,<%=index %>);' title='减一'> -</a>
				<input type='text' name='count' onblur='change(<%=rs.getString(6) %>,<%=index %>)' maxlength='4' size="1" value='<%= cart.get(pid)%>' /> 
				<a href='javascript:add(<%=rs.getString(6) %>,<%=index %>)' title='加一'> +</a></td>
			<td><a href='showcart.jsp?pid=<%=rs.getString(6) %>&del=1'>删除</a></td>
		</tr>
		<%index++; %>
		<%}
		}
  	}catch(ClassNotFoundException e){
  		e.printStackTrace();
  	}catch(Exception e){
  		e.printStackTrace();
  	}  	
   %>
		<tr>
			<td colspan="7" align="right">原始金额：￥<script>document.write(total)</script>元 -
				返现：￥0.00元<br />商品总金额：￥<script>document.write(total)</script>元
			</td>
		</tr>
	</table>
</form>
</body>
</html>
