package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.CustomInfo;
import com.zhdt.util.DBUtil;

/**
 * *���ǶԿͻ���Ϣ�����ݿ�����㣬���ڲ����Կͻ���Ϣ����ɾ�Ĳ�
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
public class CustomInfoDao extends DBUtil {
	CustomInfo csti = null;

	/**
	 * addCustomInfo()�������ڲ���ͻ���Ϣ���÷���Ҫ����һ��customInfo ����
	 * *�÷����᷵��һ�����֣�������־��ǲ��������Ӱ�����ݿ��������������ص�����Ϊ0������û���κ���Ϣд�뵽���ݿ�
	 * 
	 */
	public int addCustomInfo(CustomInfo cti) {
		int i = 0;
		sql = "INSERT INTO `custom_info` (`name`, `linkman`, `addr`, `tel`, `email`, `remark`, `type`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = getPs(sql);
			ps.setString(1, cti.getName());
			ps.setString(2, cti.getLinkman());
			ps.setString(3, cti.getAddr());
			ps.setString(4, cti.getTel());
			ps.setString(5, cti.getEmail());
			ps.setString(6, cti.getRemark());
			ps.setInt(7, cti.getType());
		} catch (SQLException e) {
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql�������޸Ĵ���");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CustomInfoDao addCustomInfo() PreparedStatement sql���ִ�е����ݿ����");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * delCustomInfo()��������ɾ�������ͻ���Ϣ���÷���Ҫ����ͻ���Ϣ��cid ������cidɾ���ͻ���Ϣ
	 * *�÷����᷵��һ�����֣�������־��ǲ��������Ӱ�����ݿ��������������ص�����Ϊ0������û���κ���Ϣд�뵽���ݿ�
	 */
	public int delCustomInfo(int cid) {
		int i = 0;
		sql = "delete from `custom_info` where `cid` =? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cid);
		} catch (SQLException e) {
			System.out.println("CustomInfoDao delCustomInfo() PreparedStatement sql�������޸Ĵ���");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CustomInfoDao delCustomInfo() PreparedStatement sql���ִ�е����ݿ����");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * updateCustomInfo()�������ڸ��¿ͻ���Ϣ���÷���Ҫ����һ��customInfo����
	 * *�÷����᷵��һ�����֣�������־��ǲ��������Ӱ�����ݿ��������������ص�����Ϊ0������û���κ���Ϣд�뵽���ݿ�
	 */
	public int updateCustomInfo(CustomInfo csti) {
		int i = 0;
		sql = "UPDATE `custom_info` SET `name`=?, `linkman`=?, `addr`=?, `tel`=?, `email`=?, `remark`=?, `type`=?  WHERE cid=?";
		
		try {
			ps = getPs(sql);
			ps.setString(1, csti.getName());
			ps.setString(2, csti.getLinkman());
			ps.setString(3, csti.getAddr());
			ps.setString(4, csti.getTel());
			ps.setString(5, csti.getEmail());
			ps.setString(6, csti.getRemark());
			ps.setInt(7, csti.getType());
			ps.setInt(8, csti.getCid());
		} catch (SQLException e) {
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql�������޸Ĵ���");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CustomInfoDao addCustomInfo() PreparedStatement sql���ִ�е����ݿ����");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * /** findCustom()�������ڲ�ѯһ�����͵Ŀͻ���Ϣ���÷���Ҫ����ͻ�type ��ͨ��type�����ݿ��ѯ�ͻ�����Ϊtype�����пͻ���Ϣ
	 * *������һ��List<customInfo>����
	 */
	public List<CustomInfo> findCustoms(int type) {
		List<CustomInfo> customs = new ArrayList<>();
		sql = "SELECT `cid`,`name`,`linkman`,`addr`,`tel`,`email`,`remark`,`type` FROM custom_info WHERE `type` = ? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, type);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql�������޸Ĵ���");
		}
		
		try {
			rs = getRs(ps);
			while (rs.next()) {
				csti = new CustomInfo();
				csti.setCid(rs.getInt(1));
				csti.setName(rs.getString(2));
				csti.setLinkman(rs.getString(3));
				csti.setAddr(rs.getString(4));
				csti.setTel(rs.getString(5));
				csti.setEmail(rs.getString(6));
				csti.setRemark(rs.getString(7));
				csti.setType(rs.getInt(8));
				customs.add(csti);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CustomInfoDao findCustom()����������ʧ��");
		}
		return customs;
	}

	/**
	 * findCustom()�������ڲ�ѯ�����ͻ���Ϣ���÷���Ҫ����һ���ͻ��Ŀͻ�id �� ��cid��ͨ��cid�����ݿ��ѯ�ͻ���Ϣ
	 * *�����ص���customInfo�ͻ���Ϣ����
	 */
	public CustomInfo findCustom(int cid) {
		csti = new CustomInfo();
		sql = "SELECT `cid`,`name`,`linkman`,`addr`,`tel`,`email`,`remark`,`type` FROM custom_info WHERE `cid` =? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cid);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql�������޸Ĵ���");
		}

		

		try {
			rs = getRs(ps);
			rs.next();
			csti.setCid(rs.getInt(1));
			csti.setName(rs.getString(2));
			csti.setLinkman(rs.getString(3));
			csti.setAddr(rs.getString(4));
			csti.setTel(rs.getString(5));
			csti.setEmail(rs.getString(6));
			csti.setRemark(rs.getString(7));
			csti.setType(rs.getInt(8));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CustomInfoDao findCustom()����������ʧ��");
		}

		return csti;
	}

	/**
	 * �÷������ڲ�ѯ��Ʒ���۹���ĵ���������ʹ�ø÷�����Ҫ����һ���ͻ���Ϣ��id��cid���÷����᷵��һ������ֻ��һ��CustomInfo�ļ���
	 * 
	 */
	public List<CustomInfo> findCustomList(int cid) {
		List<CustomInfo> custom = new ArrayList<>();
		csti = new CustomInfo();
		sql = "SELECT `cid`,`name`,`linkman`,`addr`,`tel`,`email`,`remark`,`type` FROM custom_info WHERE `cid` =? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cid);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql�������޸Ĵ���");
		}

		

		try {
			rs = getRs(ps);
			rs.next();
			csti.setCid(rs.getInt(1));
			csti.setName(rs.getString(2));
			csti.setLinkman(rs.getString(3));
			csti.setAddr(rs.getString(4));
			csti.setTel(rs.getString(5));
			csti.setEmail(rs.getString(6));
			csti.setRemark(rs.getString(7));
			csti.setType(rs.getInt(8));
			custom.add(csti);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CustomInfoDao findCustom()����������ʧ��");
		}

		return custom;
	}

}
