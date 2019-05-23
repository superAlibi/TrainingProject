package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.SellInfo;
import com.zhdt.util.DBUtil;

/***
 * *���Ƕ��û���¼ʱʹ�õ����ݿ������,����֧��pos����Ա�û�����¼
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-21 �½�
 */

public class SellDao extends DBUtil {
	SellInfo sell;

	/**
	 * �÷����ù������Sellinfo��������ݿ����������Ϣ���÷����᷵��һ�����ݵ�Ӱ�����������ִ�С����˲��������ݵ�Ӱ������
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
			System.out.println("����SellDao��Ϣ��addSell()��������ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����SellDao��Ϣ��addSell()��������ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * �÷�������ɾ������������Ϣ���÷���Ҫ����һ��������Ϣ������id �� sid���÷����᷵�ر�һ�����֣�������ֵĴ�С��������ݿ��Ӱ������
	 * 
	 */
	public int delSellInfo(int sid) {
		int i = 0;
		sql = "delete from `sell_info` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e) {
			System.out.println("����SellDao��Ϣ��delSellInfo()��������ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����SellDao��Ϣ��delSellInfo()��������ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �÷��������޸ĵ���������Ϣ���÷���Ҫ����һ��sellInfo����ͨ��������Ϣ�����ݿ�������Ϣ���в������÷����᷵��һ�����ݿ��Ӱ�������������ִ�С�������ݿ���Ӱ�캯��
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
			System.out.println("����SellDao��Ϣ��updateSell()��������ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����SellDao��Ϣ��updateSell()��������ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * �÷����᷵�����ݿ������������Ϣ���᷵��һ��������ϢSellInfo����ļ���
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
			System.out.println("���� sellDao ��Ϣ��findSells()��������ԭ�򣺴����ݿ��ȡ��Ϣʧ��");
			e.printStackTrace();
		}
		return sells;

	}

	/**
	 * �÷����᷵�����ݿ�ĵ���������Ϣ��ʹ�ø÷�����Ҫ����һ��������Ϣ������id��sid���᷵��һ������ֻ��һ��������ϢSellInfo����ļ���
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
			System.out.println("���� sellDao ��Ϣ��findSells()��������ԭ���޸�PreparedStatement sql ������");
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
			System.out.println("���� sellDao ��Ϣ��findSells()��������ԭ�򣺴����ݿ��ȡ��Ϣʧ��");
			e.printStackTrace();
		}
		return sells;

	}

	/**
	 * �÷���ͨ�������������Ϣid��sid��ѯ������Ϣ���÷����᷵��һ��SellInfo����
	 */
	public SellInfo findSellInfo(int sid) {
		sell = new SellInfo();
		sql = "select a.`sid`, a.`cid`,a.`date`,a.`totalprice`,a.`buyer`,b.`name` from `sell_info` a left join `custom_info` b on a.`cid`=b.`cid` where `sid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, sid);
		} catch (SQLException e1) {
			System.out.println("���� sellDao ��Ϣ��findSellInfo()��������ԭ���޸�PreparedStatement sql ������ʧ��");
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
			System.out.println("���� sellDao ��Ϣ��findSellInfo()��������ԭ�򣺴����ݿ��ȡ��Ϣʧ��");
			e.printStackTrace();
		}
		return sell;

	}

}
