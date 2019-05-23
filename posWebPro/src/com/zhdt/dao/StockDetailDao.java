package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.StockDetail;
import com.zhdt.util.DBUtil;

public class StockDetailDao extends DBUtil {
	private StockDetail stockd;

	/**
	 * 该方法会修改数据库中的两个表：1，Stock_info中的所有数据 2.good_Info中的amount(库存)列,修改两个数据表是否成功
	 */
	public boolean addStockDetail(StockDetail stockd) {
		int i1 = 0;
		int i2 = 0;
		sql = "insert into `stock_detail` (`sid`,`gid`,`amount`,`price`) values (?,?,?,?)";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getSid());
			ps.setInt(2, stockd.getGid());
			ps.setInt(3, stockd.getAmount());
			ps.setDouble(4, stockd.getPrice());
		} catch (SQLException e) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getAmount());
			ps.setInt(2, stockd.getGid());

		} catch (SQLException e) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：数据表Stock_Detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：数据表Goods_info修改出错 和 StockDetail添加出错");
			return false;
		}
	}

	/**
	 * 该方法通过传入的采购详细id删除采购详细信息
	 */
	public int delStockDetail(int sdid) {
		int i = 0;
		sql = "delete `stock_detail` where `sbid` = ? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sdid);
		} catch (SQLException e) {
			System.out.println("来自StockDetailDao 消息：delStockDetail()方法出错。原因:修改PreparedStatement sql语句出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自StockDetailDao 消息：delStockDetail()方法出错。原因:执行PreparedStatement sql语句出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 此方法用于修改采购详细信息的
	 */
	public boolean updateStockDetail(StockDetail stockd) {
		int i1 = 0;
		int i2 = 0;
		sql = "update `stock_detail` set `sid`=?,`gid`=?,`amount`=`amount`+?,`price`=? where `sdid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getSid());
			ps.setInt(2, stockd.getGid());
			ps.setInt(3, stockd.getAmount());
			ps.setDouble(4, stockd.getPrice());
			ps.setInt(5, stockd.getSdid());
		} catch (SQLException e) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库：goods_info的商品数量
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getAmount());
			ps.setInt(2, stockd.getGid());

		} catch (SQLException e) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：StockDetail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：Goods_info修改出错");
			return false;
		} else {
			System.out.println("来自StockDetailDao消息：addStockDetail()方法出错。原因：Goods_info修改出错 和 StockDetail添加出错");
			return false;
		}
	}

	/**
	 * 因为有采购id就有采购详细id，所以采购id与采购详细id是成对存在，且独一无二的，所以该方法通过传入的采购id 查询商品详情
	 */
	public StockDetail findStockDetail(int sid) {
		sql = "select a.`sdid`,a.`sid`,a.`gid`,a.`amount`,a.`price`,b.`name` "
				+ "from `stock_detail` a left join `goods_info` b on a.`gid`=b.`gid` where a.`sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e1) {
			System.out.println("来自StockDetailDao消息：" + "findStockDetails()方法出现错误。原因：修改PreparedStatement sql对象出错 ");
			e1.printStackTrace();
		}
		
		try {
			rs = getRs(ps);
			if (rs.next()) {
				stockd = new StockDetail();
				stockd.setSdid(rs.getInt(1));
				stockd.setSid(rs.getInt(2));
				stockd.setGid(rs.getInt(3));
				stockd.setAmount(rs.getInt(4));
				stockd.setPrice(rs.getDouble(5));
				stockd.setGname(rs.getString(6));
			} else
				System.out.println("StockDetail findStockDetails()消息：获取到空采购详细信息");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockd;
	}
}
