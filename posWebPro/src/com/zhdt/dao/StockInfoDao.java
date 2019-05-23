package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.StockDetail;
import com.zhdt.entity.StockInfo;
import com.zhdt.util.DBUtil;

/**
 * *���Ƕ���Ʒ�ɹ���Ϣ�����ݿ�����㣬���ڲ�������Ʒ�ɹ���Ϣ����ɾ�Ĳ�
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-12 �½�
 */

public class StockInfoDao extends DBUtil {
	StockInfo sti;

	/**
	 * addStockInfo()�÷������ڲ��뵥���ɹ���Ϣ���÷���Ҫ����һ��stockInfo���������ӳɹ����򷵻�����˶��������ݵ����֣��������Ϊ�㣬���ʾû�������ݿ��������
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
			System.out.println("StockInfoDao addStockInfo()�����޸�PreparedStatement sql���������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * addStockDetail()����������Ӳɹ���Ϣ����ϸ��Ϣ��Ҫ����һ��stockDetail���󣬽������ڵ�������ӵ����ݿ�
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
			System.out.println("StockInfoDao findStockDetail()�����޸�PreparedStatement sql���������");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao findStockDetail() ִ��PreparedStatement sql���������ݿ�ʧ��");
			e.printStackTrace();
		}

		return i;

	}

	/**
	 * delStocjInfo()��������ɾ���ɹ���Ϣ���÷���Ҫ����ɹ���Ϣ��sid
	 * 
	 */
	public int delStockInfo(int sid) {
		int i = 0;
		sql = "delete from `stock_info` where `sid`=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, sid);

		} catch (SQLException e) {
			System.out.println("StockInfoDao delStockInfo()�����޸�PreparedStatement sql���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao delStockInfo()����ִ��PreparedStatement sql���������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * updateSrockInfo()���ڸ��µ����ɹ���Ϣ���÷���Ҫ����stockInfo�������ڸ��²ɹ���Ϣ
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
			System.out.println("StockInfoDao updateSrockInfo()�����޸�PreparedStatement sql���������");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StockInfoDao updateSrockInfo()����ִ��PreparedStatement sql���������");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * findAllStockInfo()�������ڲ�ѯ���еĲɹ���Ϣ���÷������ô����κβ���
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
			System.out.println("StockInfoDao findAllStockInfo() ��ȡ��Ϣʧ��");
			e.printStackTrace();
		}
		return stis;
	}

	/**
	 * findStockInfo()�������ڲ�ѯ�����ɹ���Ϣ���÷�����ʹ����Ҫ����һ���ɹ�sid��ͨ��sid��ѯ�ɹ���Ϣ���÷����᷵��һ���ɹ���Ϣ��ʵ����stockInfo
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
			System.out.println("StockInfoDao findStockInfo()�����޸�PreparedStatement sql���������");
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
			System.out.println("StockInfoDao findAllStockInfo() ��ȡ��Ϣʧ��");
			e.printStackTrace();
		}

		return sti;
	}

	/**
	 * �˷����ò�ѯ�����ɹ���Ϣ���ϣ� �÷�������Ҫ�ṩ�ɹ�id����sid��ͨ��sid���Ҳɹ���Ϣ���÷����᷵��һ������ֻ����һ��StockInfo����ļ���
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
			System.out.println("StockInfoDao findStockInfo()�����޸�PreparedStatement sql���������");
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
			System.out.println("StockInfoDao findAllStockInfo() ��ȡ��Ϣʧ��");
			e.printStackTrace();
		}

		return stock;

	}
}
