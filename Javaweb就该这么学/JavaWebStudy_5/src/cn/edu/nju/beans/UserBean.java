package cn.edu.nju.beans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.nju.tools.DBTools;

/**
 * userbeans类
 * @author lenovo
 *
 */

public class UserBean {
	private String username;
	private String password;
	private String vali;
	
	public String getVali() {
		return vali;
	}
	public void setVali(String vali) {
		this.vali = vali;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 登录验证
	 * @param response
	 * @return
	 */
	public boolean vali(HttpServletResponse response){
		Connection conn =DBTools.getConn();
		PreparedStatement pst=null;
		ResultSet rs = null ;
		try {
			pst = conn.prepareStatement("select * from staff where name=? and password = ?");
			pst.setString(1, getUsername());
			pst.setString(2, getPassword());
			rs = pst.executeQuery();
			if(rs.next()){
				//添加cookie
				Cookie cookie = new Cookie("username",username.trim());
				//设置cookie存活时间,以秒为单位
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
				response.sendRedirect("show.jsp");
				rs.close();
				pst.close();
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 注册验证
	 * @return
	 */
	public boolean valiUser(HttpServletRequest request){
		
		//检查验证码
		HttpSession session =request.getSession();
		String valiInput = (String) session.getAttribute("vali");
		if(valiInput.equals(getVali())){
			Connection conn =DBTools.getConn();
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement("insert into staff(name,password) value(?,?)");
				System.out.println(getUsername());
				pst.setString(1, new String(getUsername().getBytes("UTF-8")));
				pst.setString(2, getPassword());
				pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
