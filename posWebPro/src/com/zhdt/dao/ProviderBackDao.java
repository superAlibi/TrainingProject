package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.ProviderBack;
import com.zhdt.util.DBUtil;

/***
 ** ���ǶԲɹ��˻���Ϣ���ݿ������
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-19 �½�
 */
public class ProviderBackDao extends DBUtil {
	ProviderBack prob;

	/**
	 ** �÷���������ӵ����ɹ��˻���Ϣ���÷�����Ҫ����һ���ɹ��˻���Ϣ����ProviderBack��Ϊ�������÷����᷵��һ�����֣����ִ�С���������������
	 * 
	 */
	public int addProviderBack(ProviderBack prob) {
		sql = "insert into provider_back (`cid`,`sid`,`date`,`totalprice`) values(?,?,?,?)";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setInt(1, prob.getCid());
			ps.setInt(2, prob.getSid());
			ps.setString(3, prob.getDate());
			ps.setDouble(4, prob.getTotalprice());

		} catch (SQLException e) {
			System.out.println("ProviderBackDao addProviderBack()��Ϣ���޸�PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProviderBackDao addProviderBack()��Ϣ��ִ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * *�÷�������ɾ�������Ĳɹ��˻���Ϣ��ʹ�ø÷�����Ҫ����ɹ��˻���Ϣ�Ĳɹ��˻�id��pbid��ͨ��pbidɾ���ɹ��˻���Ϣ��
	 * *�÷����᷵��һ�����֣�����ɾ��Ӱ�����ݿ��������0�������ݿ�û���ܵ�Ӱ��
	 * 
	 */
	public int delProviderBack(int pbid) {
		int i = 0;

		sql = "delete from `provider_back` where `pbid` =?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e) {
			System.out.println("����ProviderBackDao ��Ϣ��delProviderBack()�����޸�PreparedStatement sql�����ִ���");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����ProviderBackDao ��Ϣ��delProviderBack()����ִ��PreparedStatement sql�����ִ���");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * �÷���ͨ������Ĳɹ��˻���Ϣ������²ɹ��˻���Ϣ���÷����᷵��һ�����֣����ִ�С������²���Ӱ�����ݿ������
	 */
	public int updateProviderBack(ProviderBack prob) {
		sql = "update `provider_back` set `cid`=?,`sid`=?,`date`=?,`totalprice`=? where `pbid`=?";
		
		int i = 0;
		try {
			ps = getPs(sql);
			ps.setInt(1, prob.getCid());
			ps.setInt(2, prob.getSid());
			ps.setString(3, prob.getDate());
			ps.setDouble(4, prob.getTotalprice());
			ps.setInt(5, prob.getPbid());
		} catch (SQLException e) {
			System.out.println("ProviderBackDao addProviderBack()��Ϣ���޸�PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProviderBackDao addProviderBack()��Ϣ��ִ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 ** �÷������ڲ�ѯ���еĲɹ��˻���Ϣ���÷�������Ҫ����������÷����᷵��һ��ProviderBack����ļ���
	 * 
	 */
	public List<ProviderBack> findProviderBacks() {
		List<ProviderBack> probs = new ArrayList<>();

		sql = "select a.`pbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `provider_back` a left join `custom_info` b on a.`cid`=b.`cid`";
		
		try {
			rs = getRs(sql);
			while (rs.next()) {
				prob = new ProviderBack();
				prob.setPbid(rs.getInt(1));
				prob.setCid(rs.getInt(2));
				prob.setSid(rs.getInt(3));
				prob.setDate(rs.getString(4));
				prob.setTotalprice(rs.getDouble(5));
				prob.setCname(rs.getString(6));
				probs.add(prob);
			}
		} catch (SQLException e) {
			System.out.println("ProviderBackDao findProviderBack()��Ϣ����ȡ���ݿ�����Ϊ�� ");
			e.printStackTrace();
		}

		return probs;
	}

	public List<ProviderBack> findProviderBackInfo(int pbid) {
		List<ProviderBack> probs = new ArrayList<>();

		sql = "select a.`pbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `provider_back` a left join `custom_info` b on a.`cid`=b.`cid` where `pbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e1) {
			System.out.println("����ProviderBackDao ��Ϣ��findProviderBackInfo ()�������ִ���ԭ���޸�PreparedStatement sql���������");
			e1.printStackTrace();
		}
		
		try {
			rs = getRs(ps);
			if (rs.next()) {
				prob = new ProviderBack();
				prob.setPbid(rs.getInt(1));
				prob.setCid(rs.getInt(2));
				prob.setSid(rs.getInt(3));
				prob.setDate(rs.getString(4));
				prob.setTotalprice(rs.getDouble(5));
				prob.setCname(rs.getString(6));
				probs.add(prob);
			}
		} catch (SQLException e) {
			System.out.println("ProviderBackDao findProviderBack()��Ϣ����ȡ���ݿ�����Ϊ�� ");
			e.printStackTrace();
		}
		return probs;
	}

	/**
	 ** �÷������ڲ�ѯ�����Ĳɹ��˻���Ϣ���÷�����Ҫ����ɹ��˻���Ϣ��id����pbid��ͨ��pbid��ѯ�����Ĳɹ��˻���Ϣ,�÷����᷵��һ��ProviderBack
	 * ����
	 * 
	 */
	public ProviderBack findProviderBack(int pbid) {
		ProviderBack providb = new ProviderBack();
		sql = "select a.`pbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `provider_back` a left join `custom_info` b on a.`cid`=b.`cid` where `pbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e) {
			System.out.println("����ProviderBackDao ��Ϣ��findProviderBack ()�������ִ���ԭ���޸�PreparedStatement sql���������");

			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("����ProviderBackDao ��Ϣ��findProviderBack ()�������ִ���ԭ��ִ��PreparedStatement sql���������");
			e.printStackTrace();
		}
		try {
			rs.next();
			providb.setPbid(rs.getInt(1));
			providb.setCid(rs.getInt(2));
			providb.setSid(rs.getInt(3));
			providb.setDate(rs.getString(4));
			providb.setTotalprice(rs.getDouble(5));
			providb.setCname(rs.getString(6));
		} catch (SQLException e) {
			System.out.println("����ProviderBackDao ��Ϣ��findProviderBack ()�������ִ���ԭ���������ݵ�����Ϊ��");
			e.printStackTrace();
		}
		return providb;

	}

}
