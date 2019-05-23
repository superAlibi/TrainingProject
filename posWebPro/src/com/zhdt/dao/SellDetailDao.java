package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.SellDetail;
import com.zhdt.util.DBUtil;

/***
 * *该方法是对销售的详细信息的数据库操作层
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-24 新建
 * 
 */
public class SellDetailDao extends DBUtil {
	SellDetail sed;

	/**
	 * 该方法用于添加商品销售详细信息，使用该方法需要传入一个SellDetail销售详细信息对象，该方法会返回一个Boolean值代表商品销售信息和商品信息是否同时添加或修改成功
	 */
	public boolean addSellDetail(SellDetail selld) {
		int i1 = 0;
		int i2 = 0;
		sql = "insert into `sell_detail` (`sid`,`gid`,`amount`,`price`) values (?,?,?,?)";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, selld.getSid());
			ps.setInt(2, selld.getGid());
			ps.setInt(3, selld.getAmount());
			ps.setDouble(4, selld.getPrice());
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`-? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, selld.getAmount());
			ps.setInt(2, selld.getGid());

		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：数据表Provider_back_detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println("来自SellDetailDao消息：addSellDetail()方法出错。原因：数据表Goods_info修改出错 和 Provider_back_detail添加出错");
			return false;
		}

	}

	/**
	 * 该方法用于删除商品销售详细信息，该方法需要传入一销售id，因为销售信息id与销售详细信息id一一对应且独一无二，所以应该销售信息与销售详细信息同时删除
	 * 
	 */
	public int delsellDetail(int sid) {
		int i = 0;
		sql = "delete `sell_detail` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：delsellDetail()出错。原因：修改PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：delsellDetail()出错。原因：执行PreparedStatement sql 语句对象出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 该方法用于更新销售详细信息，要求传入一销售详细信息的对象(SellDetail)对数据库增删改查，该方法会返回一个Boolean值代表商品销售信息和商品信息是否同时修改成功
	 * 
	 */
	public boolean updateSellDetail(SellDetail selld) {
		int i1 = 0;
		int i2 = 0;
		sql = "update `sell_detail` set `sid`=?,`gid`=?,`amount`=`amount`+?,`price`=? where sdid=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, selld.getSid());
			ps.setInt(2, selld.getGid());
			ps.setInt(3, selld.getAmount());
			ps.setDouble(4, selld.getPrice());
			ps.setInt(5, selld.getSdid());
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`-? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, selld.getAmount());
			ps.setInt(2, selld.getGid());

		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：执行第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：数据表sell_detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println("来自SellDetailDao消息：updateSellDetail()方法出错。原因：数据表Goods_info修改出错 和 sell_detail添加出错");
			return false;
		}

	}

	/**
	 * 该方法通诺传入一个销售信息id查询其销售详细信息，该方法会返回一个销售详细信息对象
	 * 
	 */
	public SellDetail findSellDetail(int sid) {

		sql = "select a.`sdid`,a.`sid`,a.`gid`,a.`amount`,a.`price`,b.`name` from `sell_detail` a left join `goods_info` b on a.`gid`=b.`gid` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：findSellDetail ()出现错误。原因： 修改PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：findSellDetail ()出现错误。原因： 执行PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				sed = new SellDetail();
				sed.setSdid(rs.getInt(1));
				sed.setSid(rs.getInt(2));
				sed.setGid(rs.getInt(3));
				sed.setAmount(rs.getInt(4));
				sed.setPrice(rs.getDouble(5));
				sed.setGname(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println("来自SellDetailDao消息：findSellDetail ()出现错误。原因：获取结果集出现错误");
			e.printStackTrace();
		}
		return sed;
	}
}
