package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.StockDetail;
import com.zhdt.util.DBUtil;

public class StockDetailDao extends DBUtil {
	private StockDetail stockd;

	/**
	 * �÷������޸����ݿ��е�������1��Stock_info�е��������� 2.good_Info�е�amount(���)��,�޸��������ݱ��Ƿ�ɹ�
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
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getAmount());
			ps.setInt(2, stockd.getGid());

		} catch (SQLException e) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ�����ݱ�Stock_Detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� StockDetail��ӳ���");
			return false;
		}
	}

	/**
	 * �÷���ͨ������Ĳɹ���ϸidɾ���ɹ���ϸ��Ϣ
	 */
	public int delStockDetail(int sdid) {
		int i = 0;
		sql = "delete `stock_detail` where `sbid` = ? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sdid);
		} catch (SQLException e) {
			System.out.println("����StockDetailDao ��Ϣ��delStockDetail()��������ԭ��:�޸�PreparedStatement sql������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����StockDetailDao ��Ϣ��delStockDetail()��������ԭ��:ִ��PreparedStatement sql������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �˷��������޸Ĳɹ���ϸ��Ϣ��
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
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ⣺goods_info����Ʒ����
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, stockd.getAmount());
			ps.setInt(2, stockd.getGid());

		} catch (SQLException e) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��StockDetail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println("����StockDetailDao��Ϣ��addStockDetail()��������ԭ��Goods_info�޸ĳ��� �� StockDetail��ӳ���");
			return false;
		}
	}

	/**
	 * ��Ϊ�вɹ�id���вɹ���ϸid�����Բɹ�id��ɹ���ϸid�ǳɶԴ��ڣ��Ҷ�һ�޶��ģ����Ը÷���ͨ������Ĳɹ�id ��ѯ��Ʒ����
	 */
	public StockDetail findStockDetail(int sid) {
		sql = "select a.`sdid`,a.`sid`,a.`gid`,a.`amount`,a.`price`,b.`name` "
				+ "from `stock_detail` a left join `goods_info` b on a.`gid`=b.`gid` where a.`sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e1) {
			System.out.println("����StockDetailDao��Ϣ��" + "findStockDetails()�������ִ���ԭ���޸�PreparedStatement sql������� ");
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
				System.out.println("StockDetail findStockDetails()��Ϣ����ȡ���ղɹ���ϸ��Ϣ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockd;
	}
}
