package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.StockDetail;
import com.zhdt.entity.StockInfo;
import com.zhdt.util.DBUtil;

/**
 * *这是对商品采购信息的数据库操作层，用于操作对商品采购信息的增删改查
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-12 新建
 */

public class StockInfoDao extends DBUtil {
	StockInfo sti;

	/**
	 * addStockInfo()该方法用于插入单个采购信息，该方法要求传入一个stockInfo对象，如果添加成功，则返回添加了多少条数据的数字，如果返回为零，则表示没有在数据库插入数据
	 */
	public int addStockInfo(StockInfo sti) {
		int i = 0;
		sql = "INSERT INTO `stock_info` (`sid`, `cid`, `date`, `totalprice`, `buyer`) VALUES (?, ?, ?, ?, ?)";

		try {
			ps = getPs(sql);
			ps.setInt(1, sti.getSid());
			ps.setInt(2, sti.getCid());
			ps.setString(3, sti.getDate());
			ps.setDouble(4, sti.getTotalprice());
			ps.setString(5, sti.getBuyer());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao addStockInfo()方法修改PreparedStatement sql语句对象出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * addStockDetail()方法用于添加采购信息的详细信息，要求传入一个stockDetail对象，将对象内的属性添加到数据库
	 */
	public int addStockDetail(StockDetail stockd) {
		// sql = "insert into `stock_detail` (`sdid`,`sid`,`gid`,`amount`,`price` )
		// VALUES (?,?,?,?,?)";
		sql = "insert into `stock_detail` VALUES (?,?,?,?,?)";

		int i = 0;
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getSdid());
			ps.setInt(2, stockd.getSid());
			ps.setInt(3, stockd.getGid());
			ps.setInt(4, stockd.getAmount());
			ps.setDouble(5, stockd.getPrice());
		} catch (SQLException e) {
			System.out.println("StockInfoDao findStockDetail()方法修改PreparedStatement sql语句对象出错");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao findStockDetail() 执行PreparedStatement sql语句对象到数据库失败");
			e.printStackTrace();
		}

		return i;

	}

	/**
	 * delStocjInfo()方法用于删除采购信息，该方法要求传入采购信息的sid
	 * 
	 */
	public int delStockInfo(int sid) {
		int i = 0;
		sql = "delete from `stock_info` where `sid`=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, sid);

		} catch (SQLException e) {
			System.out.println("StockInfoDao delStockInfo()方法修改PreparedStatement sql语句对象出错");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao delStockInfo()方法执行PreparedStatement sql语句对象出错");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * updateSrockInfo()用于更新单个采购信息，该方法要求传入stockInfo对象用于更新采购信息
	 * 
	 */
	public int updateSrockInfo(StockInfo sti) {
		int i = 0;
		sql = "update stock_info set `cid` =? , `date` =?,`totalprice`=? ,`buyer`=? where `sid`=? ";

		try {
			ps = getPs(sql);
			ps.setInt(1, sti.getCid());
			ps.setString(2, sti.getDate());
			ps.setDouble(3, sti.getTotalprice());
			ps.setString(4, sti.getBuyer());
			ps.setInt(5, sti.getSid());

		} catch (SQLException e) {
			System.out.println("StockInfoDao updateSrockInfo()方法修改PreparedStatement sql语句对象出错");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao updateSrockInfo()方法执行PreparedStatement sql语句对象出错");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * findAllStockInfo()方法用于查询所有的采购信息，该方法不用传入任何参数
	 * 
	 */
	public List<StockInfo> findAllStockInfo() {
		sql = "select a.`sid`,a.`cid`,a.`date`,a.`totalprice`,a.`buyer`,b.name cname from `stock_info` a LEFT JOIN `custom_info` b ON a.cid=b.cid";

		List<StockInfo> stis = new ArrayList<>();
		try {
			rs = getRs(sql);
			while (rs.next()) {
				sti = new StockInfo();
				sti.setSid(rs.getInt(1));
				sti.setCid(rs.getInt(2));
				sti.setDate(rs.getString(3));
				sti.setTotalprice(rs.getDouble(4));
				sti.setBuyer(rs.getString(5));
				sti.setCname(rs.getString(6));
				stis.add(sti);
			}
		} catch (SQLException e) {
			System.out.println("StockInfoDao findAllStockInfo() 获取信息失败");
			e.printStackTrace();
		}
		return stis;
	}

	/**
	 * findStockInfo()方法用于查询当个采购信息，该方法的使用需要传入一个采购sid，通过sid查询采购信息，该方法会返回一个采购信息的实体类stockInfo
	 * 
	 */
	public StockInfo findStockInfo(int sid) {
		sql = "select `sid`,`cid`,`date`,`totalprice`,`buyer` from stock_info where sid=?";

		sti = new StockInfo();
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("StockInfoDao findStockInfo()方法修改PreparedStatement sql语句对象出错");
		}

		try {
			rs = getRs(ps);
			rs.next();
			sti.setSid(rs.getInt(1));
			sti.setCid(rs.getInt(2));
			sti.setDate(rs.getString(3));
			sti.setTotalprice(rs.getInt(4));
			sti.setBuyer(rs.getString(5));
		} catch (SQLException e) {
			System.out.println("StockInfoDao findAllStockInfo() 获取信息失败");
			e.printStackTrace();
		}

		return sti;
	}

	/**
	 * 此方法用查询单个采购信息集合， 该方法会需要提供采购id，即sid，通过sid查找采购信息，该方法会返回一个有且只含有一个StockInfo对象的集合
	 */
	public List<StockInfo> findStockInfoList(int sid) {
		List<StockInfo> stock = new ArrayList<>();
		sql = "select `sid`,`cid`,`date`,`totalprice`,`buyer` from stock_info where sid=?";

		sti = new StockInfo();
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("StockInfoDao findStockInfo()方法修改PreparedStatement sql语句对象出错");
		}

		try {
			rs = getRs(ps);
			rs.next();
			sti.setSid(rs.getInt(1));
			sti.setCid(rs.getInt(2));
			sti.setDate(rs.getString(3));
			sti.setTotalprice(rs.getInt(4));
			sti.setBuyer(rs.getString(5));
			stock.add(sti);
		} catch (SQLException e) {
			System.out.println("StockInfoDao findAllStockInfo() 获取信息失败");
			e.printStackTrace();
		}

		return stock;

	}
}
