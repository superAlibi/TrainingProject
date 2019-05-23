package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.GoodInfo;
import com.zhdt.util.DBUtil;

/***
 * *这是对采购详细信息的数据库操作层，用于操作对采购信息的增删改查
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-10 新建
 */

public class GoodDao extends DBUtil {

	GoodInfo goodi;

	/**
	 * 该方法用于插入单个商品详细信息，该方法要求传入一个goodInfo对象 作为参数
	 */
	public int addGoodsInfo(GoodInfo gdi) {
		sql = "INSERT INTO `goods_info` (`name`, `tid`, `unit`, `pin`, `pout`, `amount`) VALUES (?, ?, ?, ?, ?, ?)";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, gdi.getName());
			ps.setString(2, gdi.getTid());
			ps.setString(3, gdi.getUnit());
			ps.setDouble(4, gdi.getPin());
			ps.setDouble(5, gdi.getPout());
			ps.setInt(6, gdi.getAmount());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(
					"goodsInfoDao insertGoodsInfo() 方法修改PreparedStatement sql对象错误或PreparedStatement sql对象执行数据库错误");
		}
		return i;

	}

	/**
	 * selectGoodsInfo()方法根据商品gid删除单个商品详细信息
	 */
	public int deleteGoodsInfo(String gid) {

		sql = "delete from goods_info where gid = ?";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, gid);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodsInfoDao sql语句修改错误");
		}

		return i;
	}

	/**
	 * 该方法对商品详细信息数据进行修改 ，要求传入一个goodInfo对象
	 */
	public int updateGoodsInfo(GoodInfo gdi) {
		sql = "UPDATE `goods_info` SET `name` = ?, `tid`=?, `unit`= ?,`pin`=?, `pout`=?, `amount`=? WHERE `gid` = ?";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, gdi.getName());
			ps.setString(2, gdi.getTid());
			ps.setString(3, gdi.getUnit());
			ps.setDouble(4, gdi.getPin());
			ps.setDouble(5, gdi.getPout());
			ps.setInt(6, gdi.getAmount());
			ps.setInt(7, gdi.getGid());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodInfoDao updateGoodsInfo()方法 修改PreparedStatement sql对象出错");
		}
		return i;
	}

	/**
	 * selectGoodsInfos()方法用于查询所有的商品信息，将会返回List<goodInfo>集合，使用了多表查询"SELECT
	 * a.gid,a.name,a.unit,a.pout,a.amount, b.name typeName FROM T_GOODS_INFO a LEFT
	 * JOIN T_GOODS_TYPE b ON a.tid = b.tid";
	 */
	public List<GoodInfo> findAllGoodsInfo() {
		sql = "SELECT a.gid, a.name, a.tid, a.unit, a.pin, a.pout, a.amount, b.name typeName  FROM `goods_info` a LEFT JOIN `goods_type` b ON a.tid = b.tid";
		
		List<GoodInfo> gdis = new ArrayList<>();
		try {
			rs = getRs(sql);
			while (rs.next()) {
				// 以下添加顺序为sql语句返回顺序
				goodi = new GoodInfo();
				goodi.setGid(rs.getInt(1));
				goodi.setName(rs.getString(2));
				goodi.setTid(rs.getString(3));
				goodi.setUnit(rs.getString(4));
				goodi.setPin(rs.getDouble(5));
				goodi.setPout(rs.getDouble(6));
				goodi.setAmount(rs.getInt(7));
				goodi.setTypeName(rs.getString(8));
				gdis.add(goodi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gdis;
	}

	/**
	 * 该方法通过查询指定gid的商品信息，会返回有且只有一个goodInfo 对象的集合
	 * 
	 * 
	 */
	public List<GoodInfo> findGoodInfo(int gid) {
		sql = "SELECT a.gid, a.name, a.tid, a.unit, a.pin, a.pout, a.amount, b.name typeName  FROM `goods_info` a LEFT JOIN `goods_type` b ON a.tid = b.tid where gid=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, gid);
		} catch (SQLException e1) {
			System.out.println("来自GoodDao消息：findGoodInfo()方法出错。原因：修改PreparedStatement sql语句对象出错  ");
			e1.printStackTrace();
		}
		
		List<GoodInfo> goodList = new ArrayList<>();
		try {
			rs = getRs(ps);
			if (rs.next()) {
				goodi = new GoodInfo();
				goodi.setGid(rs.getInt(1));
				goodi.setName(rs.getString(2));
				goodi.setTid(rs.getString(3));
				goodi.setUnit(rs.getString(4));
				goodi.setPin(rs.getDouble(5));
				goodi.setPout(rs.getDouble(6));
				goodi.setAmount(rs.getInt(7));
				goodi.setTypeName(rs.getString(8));
				goodList.add(goodi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodList;

	}

	/**
	 * selectGoodsInfo该方法对传入的gid进行数据库查询，并返回单个goodsInfo对象
	 */
	public GoodInfo findGoodsInfo(int gid) {

		sql = "SELECT gid,name,tid,unit,pin,pout,amount FROM goods_info where gid=?";
	
		try {
			ps = getPs(sql);
			ps.setInt(1, gid);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("goodsInfoDao selectGoodsInfo()方法 PreparedStatement sql语句对象修改错误");
		}
		
		goodi = new GoodInfo();
		try {
			rs = getRs(ps);
			goodi = new GoodInfo();
			rs.next();
			goodi.setGid(rs.getInt(1));
			goodi.setName(rs.getString(2));
			goodi.setTid(rs.getString(3));
			goodi.setUnit(rs.getString(4));
			goodi.setPin(rs.getDouble(5));
			goodi.setPout(rs.getDouble(6));
			goodi.setAmount(rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return goodi;

	}

}
