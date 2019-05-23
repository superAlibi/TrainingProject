package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.GoodType;
import com.zhdt.util.DBUtil;

/**
 * *这是对商品类别信息的数据库操作层，用于操作对商品类型的增删改查
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
public class goodsTypeDao extends DBUtil {
	/**
	 * goodType的增加方法 ， 要求传入一个商品类型的tid 和 名字
	 */
	GoodType gdt = null;

	public int addGoodsType(String tid, String name) {

		sql = "INSERT INTO `goods_type`  VALUES (?, ?);";
		

		try {
			ps = getPs(sql);
			ps.setString(1, tid);
			ps.setString(2, name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("语句修改出错！");
		}
		int i=0;
		try {
			i = getI(ps);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return i;

	}

	/**
	 * goodType的删除操作，该方法要求传入一个商品类型信息：tid 或 name 即可删除类型信息，删除信息后会返回删除了多少行的数据
	 */
	public int delGoodsType(String info) {

		sql = "DELETE FROM `goods_type` WHERE  tid= ?";
	
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, info);
//			ps.setString(2, info);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodTypeDao delGoodsTypeDao()方法 PreparedStatement sql 语句对象执行出错");
		}
		return i;

	}

	/**
	 * goodType的更新操作
	 */
	public int updateGoodsType(String tid, String newName) {

		sql = "UPDATE `goods_type` SET `name` = ?  WHERE `tid` = ?";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, newName);
			ps.setString(2, tid);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("PreparedStatement sql对象语句修改出错");
		}
		try {
			i = getI(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;

	}

	/**
	 * goodType的查询方法，会返回一个List<goodType>集合
	 */
	public List<GoodType> selectGoodsTypes() {
		sql = "SELECT tid,name FROM goods_type";

		List<GoodType> gdts = new ArrayList<>();
		try {
			rs = getRs(sql);
			while (rs.next()) {
				gdt = new GoodType();
				gdt.setTid(rs.getString(1));
				gdt.setName(rs.getString(2));
				gdts.add(gdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gdts;

	}

	/**
	 * goodType的查询方法，该方法要求传入一个goodsType的两个信心中的任意一个信息，该会返回一个ResultSet对象
	 * 
	 */
	public GoodType selectGoodsType(String info) {

		sql = "SELECT tid,name FROM `goods_type` WHERE  tid= ? or name = ?";
		
		GoodType gdt = null;
		try {
			ps = getPs(sql);
			ps.setString(1, info);
			ps.setString(2, info);
			rs = getRs(ps);
			rs.next();
			gdt = new GoodType();
			gdt.setTid(rs.getString(1));
			gdt.setName(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodsTypeDao 类  selectGoodsType()方法执行错误");
		}
		return gdt;
	}

	/**
	 * (保留方法)goodType的查询操作，这个方法需要传入商品类型信息的两个信息，进行精确查询
	 */
	public GoodType selectGoodsType(String tid, String name) {
		sql = "SELECT tid,name FROM `goods_type` WHERE  tid= ? and name = ?";
		
		gdt = new GoodType();
		try {
			ps = getPs(sql);
			ps.setString(1, tid);
			ps.setString(2, name);
			rs = getRs(ps);
			if (rs.next()) {
				gdt.setTid(rs.getString(1));
				gdt.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodsTypeDao selectGoodsType()方法出错 ：没有改商品种类的信息");
		}
		return gdt;

	}

}
