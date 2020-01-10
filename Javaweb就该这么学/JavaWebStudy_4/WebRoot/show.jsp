<!-- 这个是page指令 -->
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!-- 这个是脚本语句 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//获取cookie的值
Cookie cookie[] = request.getCookies();
String user ="";
for(Cookie c: cookie){
	if(c.getName().equals("username")){
		user = c.getValue();
	}
}
if(!user.equals("")){%>
	你好，<%=user %>  <a href="showcart.jsp" >查看我的购物车</a>
<%}else{
	String username = (String)session.getAttribute("username");
	if(username == null){%>
	  <a href="index.jsp">请登录哦</a>
	  <%
	}else{
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>展示页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<h5>欢迎你，<%=username %>商品展示 <a href="showcart.jsp" >查看我的购物车</a></h5>
  	<%
  	}
  	}
  	//连接数据库
  	Connection conn = null;
  	//列数
  	int cells = 4;
  	//行数
  	int rows =2;
  	//当前页数
  	int currentPage = 1;
  	
  	try{
  		Class.forName("com.mysql.jdbc.Driver");
  		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zdmedu","root","root");
  	}catch(ClassNotFoundException e){
  		e.printStackTrace();
  	}
  	//获取商品数据
  	Statement st = conn.createStatement();
  	ResultSet rs1 = st.executeQuery("select name,price,gift,img,info,pid from product");
  	//获取到传进来的参数
  	String nowPageStr = request.getParameter("nowPage");
  	int nowPage = 1;
  	if(nowPageStr == null){
  		nowPage = 1;
  	}else{
  		nowPage = Integer.parseInt(nowPageStr);
  	}
  	//过滤掉之前的商品
  	for(int i=1;i <= (nowPage-1)*(cells*rows); i++){
  		rs1.next();
  	}
  	//显示当前的记录
  	for(int i=1;i<= rows; i++){
  		for(int j=1;j<= cells ;j++){
  			rs1.next();
  			%>
  			<table border="1px">
	  			<tr>
	  			<!-- 名称 -->
	  			<td><%=rs1.getString(1) %></td>
	  			<!-- 单价 -->
	  			<td><%=rs1.getString(2) %></td>
	  			<!-- 礼物 -->
	  			<td><%=rs1.getString(3) %></td>
	  			<!-- 图片 -->
	  			<td><img src="<%=rs1.getString(4) %>" /></td>
	  			<!-- 相关信息 -->
	  			<td><%=rs1.getString(5) %></td>
	  			<!-- 评论 -->
	  			<%
	  			PreparedStatement ps = conn.prepareStatement("select count(pid) from comment where pid=?");
	  			ps.setString(1,rs1.getString(6));
	  			ResultSet rspinglun =ps.executeQuery();
	  			rspinglun.next();
	  			 %>
	  			<td>已经有&nbsp;<%=rspinglun.getInt(1) %>人评论</td>
	  			<td>
	  				<input type="button" name="addToCart" onclick="location.replace('show.jsp?pid=<%=rs1.getString(6)%>&nowPage=<%=nowPage %>');"  value="加入购物车" />
	  				<input type="button" name="buy" value="购买"/>
	  			</td>
	  			</tr>
  			</table>
  			<%
  		}
  		out.print("<br>");
  	}
  	
  	//得到总商品数目
  	ResultSet rs2 = st.executeQuery("select count(pid) from product");
  	rs2.next();
  	int proNumber = rs2.getInt(1);
  	
  	//得到总页数
  	int pageNumber = proNumber/(rows*cells);
  	
  	for(int i=1;i<=pageNumber;i++){
  		out.print("<a href='show.jsp?nowPage="+i+"'>"+i+"</a>");
  	}
  	
  	//获取接入购物车的pid
  	String cartpid = request.getParameter("pid");
  	//如果是第一次进入该网页那么什么都不用做，如果不是进行下一步
	if(cartpid != null){
		if(session.getAttribute("cart")== null){
  		HashMap cart = new HashMap();
  		session.setAttribute("cart", cart);
	  	}
	  	//将当前的pid以及数量存入cart中
	  	HashMap cart = (HashMap)session.getAttribute("cart");
	  	//检查购物车里是否有该商品了
	  	if(cart.get(cartpid) ==null){
	  	 	cart.put(cartpid, 1);
	  	}else{
	  		int count = ((Integer)cart.get(cartpid)).intValue();
	  		cart.put(cartpid, count+1);
	  	}
	  	System.out.println(cart);
	}
  	 %>	
  </body>
</html>