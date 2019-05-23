package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.GoodType;
import com.zhdt.util.DBUtil;

/**
 * *���Ƕ���Ʒ�����Ϣ�����ݿ�����㣬���ڲ�������Ʒ���͵���ɾ�Ĳ�
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
public class goodsTypeDao extends DBUtil {
	/**
	 * goodType�����ӷ��� �� Ҫ����һ����Ʒ���͵�tid �� ����
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
			System.out.println("����޸ĳ���");
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
	 * goodType��ɾ���������÷���Ҫ����һ����Ʒ������Ϣ��tid �� name ����ɾ��������Ϣ��ɾ����Ϣ��᷵��ɾ���˶����е�����
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
			System.out.println("goodTypeDao delGoodsTypeDao()���� PreparedStatement sql ������ִ�г���");
		}
		return i;

	}

	/**
	 * goodType�ĸ��²���
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
			System.out.println("PreparedStatement sql��������޸ĳ���");
		}
		try {
			i = getI(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;

	}

	/**
	 * goodType�Ĳ�ѯ�������᷵��һ��List<goodType>����
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
	 * goodType�Ĳ�ѯ�������÷���Ҫ����һ��goodsType�����������е�����һ����Ϣ���û᷵��һ��ResultSet����
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
			System.out.println("goodsTypeDao ��  selectGoodsType()����ִ�д���");
		}
		return gdt;
	}

	/**
	 * (��������)goodType�Ĳ�ѯ���������������Ҫ������Ʒ������Ϣ��������Ϣ�����о�ȷ��ѯ
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
			System.out.println("goodsTypeDao selectGoodsType()�������� ��û�и���Ʒ�������Ϣ");
		}
		return gdt;

	}

}
