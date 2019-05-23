package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.GoodInfo;
import com.zhdt.util.DBUtil;

/***
 * *���ǶԲɹ���ϸ��Ϣ�����ݿ�����㣬���ڲ����Բɹ���Ϣ����ɾ�Ĳ�
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-10 �½�
 */

public class GoodDao extends DBUtil {

	GoodInfo goodi;

	/**
	 * �÷������ڲ��뵥����Ʒ��ϸ��Ϣ���÷���Ҫ����һ��goodInfo���� ��Ϊ����
	 */
	public int addGoodsInfo(GoodInfo gdi) {
		sql = "INSERT INTO `goods_info` (`name`, `tid`, `unit`, `pin`, `pout`, `amount`) VALUES (?, ?, ?, ?, ?, ?)";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, gdi.getName());
			ps.setString(2, gdi.getTid());
			ps.setString(3, gdi.getUnit());
			ps.setDouble(4, gdi.getPin());
			ps.setDouble(5, gdi.getPout());
			ps.setInt(6, gdi.getAmount());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(
					"goodsInfoDao insertGoodsInfo() �����޸�PreparedStatement sql��������PreparedStatement sql����ִ�����ݿ����");
		}
		return i;

	}

	/**
	 * selectGoodsInfo()����������Ʒgidɾ��������Ʒ��ϸ��Ϣ
	 */
	public int deleteGoodsInfo(String gid) {

		sql = "delete from goods_info where gid = ?";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, gid);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodsInfoDao sql����޸Ĵ���");
		}

		return i;
	}

	/**
	 * �÷�������Ʒ��ϸ��Ϣ���ݽ����޸� ��Ҫ����һ��goodInfo����
	 */
	public int updateGoodsInfo(GoodInfo gdi) {
		sql = "UPDATE `goods_info` SET `name` = ?, `tid`=?, `unit`= ?,`pin`=?, `pout`=?, `amount`=? WHERE `gid` = ?";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setString(1, gdi.getName());
			ps.setString(2, gdi.getTid());
			ps.setString(3, gdi.getUnit());
			ps.setDouble(4, gdi.getPin());
			ps.setDouble(5, gdi.getPout());
			ps.setInt(6, gdi.getAmount());
			ps.setInt(7, gdi.getGid());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("goodInfoDao updateGoodsInfo()���� �޸�PreparedStatement sql�������");
		}
		return i;
	}

	/**
	 * selectGoodsInfos()�������ڲ�ѯ���е���Ʒ��Ϣ�����᷵��List<goodInfo>���ϣ�ʹ���˶���ѯ"SELECT
	 * a.gid,a.name,a.unit,a.pout,a.amount, b.name typeName FROM T_GOODS_INFO a LEFT
	 * JOIN T_GOODS_TYPE b ON a.tid = b.tid";
	 */
	public List<GoodInfo> findAllGoodsInfo() {
		sql = "SELECT a.gid, a.name, a.tid, a.unit, a.pin, a.pout, a.amount, b.name typeName  FROM `goods_info` a LEFT JOIN `goods_type` b ON a.tid = b.tid";
		
		List<GoodInfo> gdis = new ArrayList<>();
		try {
			rs = getRs(sql);
			while (rs.next()) {
				// �������˳��Ϊsql��䷵��˳��
				goodi = new GoodInfo();
				goodi.setGid(rs.getInt(1));
				goodi.setName(rs.getString(2));
				goodi.setTid(rs.getString(3));
				goodi.setUnit(rs.getString(4));
				goodi.setPin(rs.getDouble(5));
				goodi.setPout(rs.getDouble(6));
				goodi.setAmount(rs.getInt(7));
				goodi.setTypeName(rs.getString(8));
				gdis.add(goodi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gdis;
	}

	/**
	 * �÷���ͨ����ѯָ��gid����Ʒ��Ϣ���᷵������ֻ��һ��goodInfo ����ļ���
	 * 
	 * 
	 */
	public List<GoodInfo> findGoodInfo(int gid) {
		sql = "SELECT a.gid, a.name, a.tid, a.unit, a.pin, a.pout, a.amount, b.name typeName  FROM `goods_info` a LEFT JOIN `goods_type` b ON a.tid = b.tid where gid=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, gid);
		} catch (SQLException e1) {
			System.out.println("����GoodDao��Ϣ��findGoodInfo()��������ԭ���޸�PreparedStatement sql���������  ");
			e1.printStackTrace();
		}
		
		List<GoodInfo> goodList = new ArrayList<>();
		try {
			rs = getRs(ps);
			if (rs.next()) {
				goodi = new GoodInfo();
				goodi.setGid(rs.getInt(1));
				goodi.setName(rs.getString(2));
				goodi.setTid(rs.getString(3));
				goodi.setUnit(rs.getString(4));
				goodi.setPin(rs.getDouble(5));
				goodi.setPout(rs.getDouble(6));
				goodi.setAmount(rs.getInt(7));
				goodi.setTypeName(rs.getString(8));
				goodList.add(goodi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodList;

	}

	/**
	 * selectGoodsInfo�÷����Դ����gid�������ݿ��ѯ�������ص���goodsInfo����
	 */
	public GoodInfo findGoodsInfo(int gid) {

		sql = "SELECT gid,name,tid,unit,pin,pout,amount FROM goods_info where gid=?";
	
		try {
			ps = getPs(sql);
			ps.setInt(1, gid);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("goodsInfoDao selectGoodsInfo()���� PreparedStatement sql�������޸Ĵ���");
		}
		
		goodi = new GoodInfo();
		try {
			rs = getRs(ps);
			goodi = new GoodInfo();
			rs.next();
			goodi.setGid(rs.getInt(1));
			goodi.setName(rs.getString(2));
			goodi.setTid(rs.getString(3));
			goodi.setUnit(rs.getString(4));
			goodi.setPin(rs.getDouble(5));
			goodi.setPout(rs.getDouble(6));
			goodi.setAmount(rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return goodi;

	}

}
