package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.SellDetail;
import com.zhdt.util.DBUtil;

/***
 * *�÷����Ƕ����۵���ϸ��Ϣ�����ݿ������
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-24 �½�
 * 
 */
public class SellDetailDao extends DBUtil {
	SellDetail sed;

	/**
	 * �÷������������Ʒ������ϸ��Ϣ��ʹ�ø÷�����Ҫ����һ��SellDetail������ϸ��Ϣ���󣬸÷����᷵��һ��Booleanֵ������Ʒ������Ϣ����Ʒ��Ϣ�Ƿ�ͬʱ��ӻ��޸ĳɹ�
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
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`-? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, selld.getAmount());
			ps.setInt(2, selld.getGid());

		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ�����ݱ�Provider_back_detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println("����SellDetailDao��Ϣ��addSellDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� Provider_back_detail��ӳ���");
			return false;
		}

	}

	/**
	 * �÷�������ɾ����Ʒ������ϸ��Ϣ���÷�����Ҫ����һ����id����Ϊ������Ϣid��������ϸ��Ϣidһһ��Ӧ�Ҷ�һ�޶�������Ӧ��������Ϣ��������ϸ��Ϣͬʱɾ��
	 * 
	 */
	public int delsellDetail(int sid) {
		int i = 0;
		sql = "delete `sell_detail` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��delsellDetail()����ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��delsellDetail()����ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �÷������ڸ���������ϸ��Ϣ��Ҫ����һ������ϸ��Ϣ�Ķ���(SellDetail)�����ݿ���ɾ�Ĳ飬�÷����᷵��һ��Booleanֵ������Ʒ������Ϣ����Ʒ��Ϣ�Ƿ�ͬʱ�޸ĳɹ�
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
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`-? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, selld.getAmount());
			ps.setInt(2, selld.getGid());

		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ��ִ�еڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ�����ݱ�sell_detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println("����SellDetailDao��Ϣ��updateSellDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� sell_detail��ӳ���");
			return false;
		}

	}

	/**
	 * �÷���ͨŵ����һ��������Ϣid��ѯ��������ϸ��Ϣ���÷����᷵��һ��������ϸ��Ϣ����
	 * 
	 */
	public SellDetail findSellDetail(int sid) {

		sql = "select a.`sdid`,a.`sid`,a.`gid`,a.`amount`,a.`price`,b.`name` from `sell_detail` a left join `goods_info` b on a.`gid`=b.`gid` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��findSellDetail ()���ִ���ԭ�� �޸�PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("����SellDetailDao��Ϣ��findSellDetail ()���ִ���ԭ�� ִ��PreparedStatement sql�������");
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
			System.out.println("����SellDetailDao��Ϣ��findSellDetail ()���ִ���ԭ�򣺻�ȡ��������ִ���");
			e.printStackTrace();
		}
		return sed;
	}
}
