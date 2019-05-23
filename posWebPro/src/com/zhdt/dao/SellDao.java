package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.SellInfo;
import com.zhdt.util.DBUtil;

/***
 * *这是对用户登录时使用的数据库操作层,仅仅支持pos管理员用户名登录
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-21 新建
 */

public class SellDao extends DBUtil {
	SellInfo sell;

	/**
	 * 该方法用过传入的Sellinfo对象对数据库添加销售信息，该方法会返回一个数据的影响行数，数字大小代表此操作对数据的影响行数
	 * 
	 */
	public int addSell(SellInfo sell) {
		int i = 0;
		sql = "insert into `sell_info` (`cid`,`date`,`totalprice`,`buyer`) values(?,?,?,?)";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sell.getCid());
			ps.setString(2, sell.getDate());
			ps.setDouble(3, sell.getTotalprice());
			ps.setString(4, sell.getBuyer());
		} catch (SQLException e) {
			System.out.println("来自SellDao消息：addSell()方法出错，原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自SellDao消息：addSell()方法出错，原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * 该方法用于删除单个销售信息，该方法要求传入一个销售信息的销售id 即 sid，该方法会返回编一个数字，这个数字的大小代表对数据库的影响行数
	 * 
	 */
	public int delSellInfo(int sid) {
		int i = 0;
		sql = "delete from `sell_info` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			System.out.println("来自SellDao消息：delSellInfo()方法出错，原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自SellDao消息：delSellInfo()方法出错，原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 该方法用于修改单个销售信息，该方法要求传入一个sellInfo对象，通过对象信息对数据库销售信息进行操作；该方法会返回一个数据库的影响行数，用数字大小代表数据库受影响函数
	 */
	public int updateSell(SellInfo sell) {
		int i = 0;
		sql = "update `sell_info` set `cid`=? , `date`=? , `totalprice`=? , `buyer`=? where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sell.getCid());
			ps.setString(2, sell.getDate());
			ps.setDouble(3, sell.getTotalprice());
			ps.setString(4, sell.getBuyer());
			ps.setInt(5, sell.getSid());

		} catch (SQLException e) {
			System.out.println("来自SellDao消息：updateSell()方法出错，原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自SellDao消息：updateSell()方法出错，原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * 该方法会返回数据库的所有销售信息，会返回一个销售信息SellInfo对象的集合
	 * 
	 */
	public List<SellInfo> findSells() {
		List<SellInfo> sells = new ArrayList<>();

		sql = "select a.`sid` , a.`cid`,a.`date`,a.`totalprice`,a.`buyer`,b.`name` from `sell_info` a left join `custom_info` b on a.`cid`=b.`cid` ";
		
		try {
			rs = getRs(sql);
			while (rs.next()) {
				sell = new SellInfo();
				sell.setSid(rs.getInt(1));
				sell.setCid(rs.getInt(2));
				sell.setDate(rs.getString(3));
				sell.setTotalprice(rs.getDouble(4));
				sell.setBuyer(rs.getString(5));
				sell.setCname(rs.getString(6));
				sells.add(sell);
			}
		} catch (SQLException e) {
			System.out.println("来自 sellDao 消息：findSells()方法出错，原因：从数据库获取信息失败");
			e.printStackTrace();
		}
		return sells;

	}

	/**
	 * 该方法会返回数据库的单个销售信息。使用该方法需要传入一个销售信息的销售id即sid；会返回一个有且只有一个销售信息SellInfo对象的集合
	 * 
	 */
	public List<SellInfo> findSell(int sid) {
		List<SellInfo> sells = new ArrayList<>();
		sell = new SellInfo();
		sql = "select a.`sid` , a.`cid`,a.`date`,a.`totalprice`,a.`buyer`,b.`name` from `sell_info` a left join `custom_info` b on a.`cid`=b.`cid` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e1) {
			System.out.println("来自 sellDao 消息：findSells()方法出错，原因：修改PreparedStatement sql 语句出错");
			e1.printStackTrace();
		}
		
		try {
			rs = getRs(ps);
			rs.next();
			sell.setSid(rs.getInt(1));
			sell.setCid(rs.getInt(2));
			sell.setDate(rs.getString(3));
			sell.setTotalprice(rs.getDouble(4));
			sell.setBuyer(rs.getString(5));
			sell.setCname(rs.getString(6));
			sells.add(sell);
		} catch (SQLException e) {
			System.out.println("来自 sellDao 消息：findSells()方法出错，原因：从数据库获取信息失败");
			e.printStackTrace();
		}
		return sells;

	}

	/**
	 * 该方法通过传入的销售信息id即sid查询销售信息，该方法会返回一个SellInfo对象
	 */
	public SellInfo findSellInfo(int sid) {
		sell = new SellInfo();
		sql = "select a.`sid`, a.`cid`,a.`date`,a.`totalprice`,a.`buyer`,b.`name` from `sell_info` a left join `custom_info` b on a.`cid`=b.`cid` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e1) {
			System.out.println("来自 sellDao 消息：findSellInfo()方法出错，原因：修改PreparedStatement sql 语句对象失败");
			e1.printStackTrace();
		}
		
		try {
			rs = getRs(ps);
			rs.next();
			sell.setSid(rs.getInt(1));
			sell.setCid(rs.getInt(2));
			sell.setDate(rs.getString(3));
			sell.setTotalprice(rs.getDouble(4));
			sell.setBuyer(rs.getString(5));
			sell.setCname(rs.getString(6));
		} catch (SQLException e) {
			System.out.println("来自 sellDao 消息：findSellInfo()方法出错，原因：从数据库获取信息失败");
			e.printStackTrace();
		}
		return sell;

	}

}
