package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.CustomBack;
import com.zhdt.util.DBUtil;

/**
 * *����һ���Կͻ��˻���Ϣ�����ݿ������
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-21 �½�
 */
public class CustomBackDao extends DBUtil {

	CustomBack customb;

	/***
	 * �÷���ͨ�������CustommBack ������ȡ��Ϣ���ٽ��������Ϣ���뵽���ݿ⣬
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
			System.out.println("����CustoBackDao��Ϣ��addCustomBack()��������ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����CustoBackDao��Ϣ��addCustomBack()��������ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * *�÷�������ɾ�������ͻ��˻���Ϣ���÷���Ҫ����һ���ͻ��˻���Ϣ��id ��
	 * cbid��ͨ����idɾ���ͻ��˻���Ϣ���÷����᷵��һ�����֣����ִ�С�������ݿ���Ӱ�������
	 */
	public int delCustomBack(int cbid) {
		int i = 0;
		sql = "delete from `custom_back` where cbid=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, cbid);
		} catch (SQLException e) {
			System.out.println("����CustoBackDao��Ϣ��delCustomBack()��������ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����CustoBackDao��Ϣ��delCustomBack()��������ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �÷���ͨ������һ��CustomBack����������ݿ�Ŀͻ��˻���Ϣ��
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
			System.out.println("����CustoBackDao��Ϣ��addCustomBack()��������ԭ���޸�PreparedStatement sql ���������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����CustoBackDao��Ϣ��addCustomBack()��������ԭ��ִ��PreparedStatement sql ���������");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 ** �÷������ڲ�ѯ���еĿͻ��˻���Ϣ���÷����᷵��һ��customback����ļ���
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
			System.out.println("����CustomBackDao��Ϣ��findCstomBack()�������ִ���ԭ�򣺴����ݿ��ȡ����ϢΪ��");
			e.printStackTrace();
		}
		return custombs;
	}

	/**
	 ** �÷������ڲ�ѯ���еĿͻ��˻���Ϣ���÷����᷵��һ��customback����
	 * 
	 */
	public CustomBack findCstomBack(int cbid) {

		sql = "select a.`cbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `custom_back` a left join `custom_info` b on a.`cid`=b.`cid` where cbid=?";

		try {
			ps = getPs(sql);
			ps.setInt(1, cbid);
		} catch (SQLException e1) {
			System.out.println("����CustomBackDao��Ϣ��findCstomBack()�������ִ���ԭ���޸�PreparedStatement sql ���������");
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
			System.out.println("����CustomBackDao��Ϣ��findCstomBack()�������ִ���ԭ�򣺴����ݿ��ȡ����ϢΪ��");
			e.printStackTrace();
		}
		return customb;
	}

}
