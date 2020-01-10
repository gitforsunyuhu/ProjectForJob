<%@ page language="java" import="java.util.*,java.io.*,javax.imageio.ImageIO,java.awt.*,java.awt.image.*" pageEncoding="utf-8" contentType="image/jpeg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<%!
    // 有10个数字，26个大写字母，26个小写字母
	private String[] ver = new String[62];
	
	/*
	 * 获取四个字符
	 */
	private String getFourChar() {
		Random random = new Random();
		StringBuilder ans = new StringBuilder();
		for(int i=0; i< 4; i++){
			ans.append(ver[random.nextInt(62)]);
		}
		return ans.toString();
	}

 %>
 <%
 		int count =0;
		for(int i=0; i< 10; i++){
			ver[count++] = "" + i;
		}
		for(int i=0; i< 26; i++){
			ver[count++] = new Character((char)(i+97)).toString();
			ver[count++] = new Character((char)(i+65)).toString();
		}
 			//设置输出个格式
		response.setContentType("image/jpeg");
		//在内存中准备一个image
		BufferedImage image = new BufferedImage(50,20,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		//生成图片
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, 50, 20);
		
		//绘制干扰线
		g.setColor(new Color(150,150,150));
		for(int i=0;i<20;i++){
			int x1 = (int)(Math.random()*50);
			int y1 = (int)(Math.random()*20);
			int x2 = (int)(Math.random()*50);
			int y2 = (int)(Math.random()*20);
			g.drawLine(x1, y1, x2, y2);
		}
		
		//随机生成四个字母和数字的组合
		String newVali = getFourChar();
		for(int i=0;i<4;i++){
			g.setColor(new Color((int)(Math.random()*150),(int)(Math.random()*150),(int)(Math.random()*150)));
			g.drawString((newVali.charAt(i)+""),8*i+10, 15);
		}
		g.dispose();
		session = request.getSession();
		session.setAttribute("vali", newVali);
		//输出到浏览器
		out.clear();      //清空缓存的内容
    	out=pageContext.pushBody();  //更新PageContext的out属性的内容
		ImageIO.write(image,"JPEG",response.getOutputStream());
  %>

