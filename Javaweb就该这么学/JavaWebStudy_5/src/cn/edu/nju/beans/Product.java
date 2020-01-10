package cn.edu.nju.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.nju.tools.DBTools;

/**
 * 商品的集合类
 * @author lenovo
 * 当前页面,当前页的所有产品信息
 */
public class Product {

	//初始化当前的页面时第一页
	private int nowPage;
	//换成List的原因是因为这样的话顺序就不会改变了
	private ArrayList<ProductBean> proList;
	//购物车列表<pid,product_number>
	private HashMap<String,Integer> cartList = new HashMap<String,Integer>();
	
	//需要删除的pid
	private String id;
	//购物车中需要的total money
	private double totalPrice;
	
	//购物车逻辑
	public void setPid(String pid){
		if(cartList.get(pid)==null){
			cartList.put(pid, 1);
			System.out.println(cartList);
		}else{
			int count = cartList.get(pid);
			cartList.put(pid, count+1);
			System.out.println(cartList);
		}
	}

	public void setId(String id){
		this.id = id;
	}
	
	/*
	 * 直接在输入框中改变数量,!!!记得也要修改价格
	 */
	public void setCount(int count){
		if(id != null){
			if(count == 0){
				cartList.remove(id);
				return;
			}
			Connection conn = DBTools.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			if(cartList != null){
				try {
					pst = conn.prepareStatement("select pid,price from product where pid=?");
					pst.setString(1, id);
					rs = pst.executeQuery();
					if(rs.next()){
						double pre = rs.getDouble(2)*cartList.get(id);
						totalPrice -=pre;
						totalPrice += rs.getDouble(2)*count;
						setTotalPrice(totalPrice);
						cartList.put(id,count);
						System.out.println(cartList);
					}
					pst.setString(1, id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if(rs!=null){
					rs.close();
				}
				if(pst!=null){
					pst.close();
				}
				if(conn !=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * 获取总的金额
	 */
	public double getTotalPrice(){
		return this.totalPrice;
	}
	
	public void setTotalPrice(double totalPrice){
		this.totalPrice = totalPrice;
	}
	
	//获取整个购物车
	public ArrayList<BuyBean> getCartList(){
		double totalPrice = 0.0;
		ArrayList<BuyBean> buy = new ArrayList<BuyBean>();
		//根据当前的cartList中的pid获取到他们的信息
		Connection conn = DBTools.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if(cartList != null){
			setTotalPrice(0.0);
			for(String pid:cartList.keySet()){
				try {
					pst = conn.prepareStatement("select pid,name,price,img from product where pid=?");
					pst.setString(1, pid);
					rs = pst.executeQuery();
					if(rs.next()){
						BuyBean bb = new BuyBean();
						bb.setPid(pid);
						bb.setImg(rs.getString(4));
						bb.setName(rs.getString(2));
						bb.setPrice(rs.getDouble(3));
						bb.setCount(cartList.get(pid));
						totalPrice += bb.getPrice()*bb.getCount();
						setTotalPrice(totalPrice);
						buy.add(bb);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if(rs!=null){
					rs.close();
				}
				if(pst!=null){
					pst.close();
				}
				if(conn !=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return buy; 
		}else{
			setTotalPrice(totalPrice);
			return null;
		}
	}
	
	/**
	 * 处理删除操作
	 * @return
	 */
	public void setDel(String del){
		cartList.remove(del);
		System.out.println(cartList);
	}
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public ArrayList<ProductBean> getProList() {
		proList = new ArrayList<ProductBean>();
		//获取商品信息
		Connection conn = DBTools.getConn();
		try {
			Statement prs = conn.createStatement();
			PreparedStatement pst = conn.prepareStatement("select count(pid) from comment where pid=?");
			ResultSet rs = prs.executeQuery("select * from product");
			ResultSet rs1 = null;
			//所有的页数，页数从0开始这样的话nowPage默认值0就是第一页
			int cell = 3;
			int row = 2;
			if(getNowPage() == 0){
				setNowPage(1);
			}
			//跳过之前的页面
			for(int i=0;i<(getNowPage()-1)*cell*row; i++){
				rs.next();
			}
			for(int i=0;i<cell;i++){
				for(int j=0;j<row;j++){
					//得判断是否满足一页的要求
					if(rs.next()){
						ProductBean pro = new ProductBean();
						pro.setPid(rs.getString(1));
						pro.setName(rs.getString(2));
						pro.setPrice(rs.getDouble(3));
						pro.setGift(rs.getString(4));
						pro.setImg(rs.getString(5));
						pro.setInfo(rs.getString(6));
						pst.setString(1, rs.getString(1));
						rs1 = pst.executeQuery();
						rs1.next();
						pro.setComment(rs1.getInt(1));
						proList.add(pro);
					}else{
						break;
					}
				}
			}
			rs1.close();
			rs.close();
			pst.close();
			prs.close();
			conn.close();
			return proList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proList;
	}
	public void setProList(ArrayList<ProductBean> proList) {
		this.proList = proList;
	}
	/*
	 * 得到总的页数
	 */
	public int getTotalPage() {
		int totalPage=0;
		Connection conn = DBTools.getConn();
		try {
			Statement prs = conn.createStatement();
			ResultSet rs = prs.executeQuery("select count(pid) from product");
			rs.next();
			totalPage = rs.getInt(1)/8 + 1;
			rs.close();
			prs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPage;
	}
}
