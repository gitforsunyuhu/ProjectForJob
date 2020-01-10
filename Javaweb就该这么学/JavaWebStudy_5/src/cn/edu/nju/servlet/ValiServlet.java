package cn.edu.nju.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValiServlet extends HttpServlet{

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
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		
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
		HttpSession session = request.getSession();
		session.setAttribute("vali", newVali);
		//输出到浏览器
		try {
			ImageIO.write(image,"JPEG",response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
