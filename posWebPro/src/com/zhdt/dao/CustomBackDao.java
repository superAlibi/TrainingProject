package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.CustomBack;
import com.zhdt.util.DBUtil;

/**
 * *这是一个对客户退货信息的数据库操作层
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-21 新建
 */
public class CustomBackDao extends DBUtil {

	CustomBack customb;

	/***
	 * 该方法通过传入的CustommBack 对象提取信息，再将对象的信息插入到数据库，
	 */
	public int addCustomBack(CustomBack customb) {
		int i = 0;
		sql = "insert into `custom_back` (`cid`,`sid`,`date`,`totalprice`) values (?,?,?,?)";

		try {
			ps = getPs(sql);
			ps.setInt(1, customb.getCid());
			ps.setInt(2, customb.getSid());
			ps.setString(3, customb.getDate());
			ps.setDouble(4, customb.getTotalprice());
		} catch (SQLException e) {
			System.out.println("来自CustoBackDao消息：addCustomBack()方法出；原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自CustoBackDao消息：addCustomBack()方法出；原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * *该方法用于删除单个客户退货信息。该方法要求传入一个客户退货信息的id 即
	 * cbid，通过该id删除客户退货信息；该方法会返回一个数字，数字大小代表数据库受影响的行数
	 */
	public int delCustomBack(int cbid) {
		int i = 0;
		sql = "delete from `custom_back` where cbid=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, cbid);
		} catch (SQLException e) {
			System.out.println("来自CustoBackDao消息：delCustomBack()方法出；原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自CustoBackDao消息：delCustomBack()方法出；原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 该方法通过传入一个CustomBack对象更新数据库的客户退货信息，
	 * 
	 */
	public int updateCustomBack(CustomBack customb) {
		int i = 0;
		sql = "update `custom_back` set `cid`=?,`sid`=?,`date`=?,`totalprice`=? where cbid=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, customb.getCid());
			ps.setInt(2, customb.getSid());
			ps.setString(3, customb.getDate());
			ps.setDouble(4, customb.getTotalprice());
			ps.setInt(5, customb.getCbid());
		} catch (SQLException e) {
			System.out.println("来自CustoBackDao消息：addCustomBack()方法出；原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自CustoBackDao消息：addCustomBack()方法出；原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 ** 该方法用于查询所有的客户退货信息，该方法会返回一个customback对象的集合
	 * 
	 */
	public List<CustomBack> findCstomBack() {
		List<CustomBack> custombs = new ArrayList<>();
		sql = "select a.`cbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `custom_back` a left join `custom_info` b on a.`cid`=b.`cid`";

		try {
			rs = getRs(sql);

			while (rs.next()) {
				customb = new CustomBack();
				customb.setCbid(rs.getInt(1));
				customb.setCid(rs.getInt(2));
				customb.setSid(rs.getInt(3));
				customb.setDate(rs.getString(4));
				customb.setTotalprice(rs.getDouble(5));
				customb.setCname(rs.getString(6));
				custombs.add(customb);
			}
		} catch (SQLException e) {
			System.out.println("来自CustomBackDao消息：findCstomBack()方法出现错误。原因：从数据库获取到信息为空");
			e.printStackTrace();
		}
		return custombs;
	}

	/**
	 ** 该方法用于查询所有的客户退货信息，该方法会返回一个customback对象
	 * 
	 */
	public CustomBack findCstomBack(int cbid) {

		sql = "select a.`cbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `custom_back` a left join `custom_info` b on a.`cid`=b.`cid` where cbid=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, cbid);
		} catch (SQLException e1) {
			System.out.println("来自CustomBackDao消息：findCstomBack()方法出现错误。原因：修改PreparedStatement sql 语句对象错误");
			e1.printStackTrace();
		}

		try {
			rs = getRs(ps);
			customb = new CustomBack();
			rs.next();
			customb.setCbid(rs.getInt(1));
			customb.setCid(rs.getInt(2));
			customb.setSid(rs.getInt(3));
			customb.setDate(rs.getString(4));
			customb.setTotalprice(rs.getDouble(5));
			customb.setCname(rs.getString(6));
		} catch (SQLException e) {
			System.out.println("来自CustomBackDao消息：findCstomBack()方法出现错误。原因：从数据库获取到信息为空");
			e.printStackTrace();
		}
		return customb;
	}

}
