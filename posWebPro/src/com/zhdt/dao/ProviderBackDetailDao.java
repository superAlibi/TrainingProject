package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.ProviderBackDetail;
import com.zhdt.util.DBUtil;

/***
 * *�÷����ǶԲɹ��˻�����ϸ��Ϣ�����ݿ������
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-24 �½�
 * 
 */
public class ProviderBackDetailDao extends DBUtil {
	ProviderBackDetail probd;

	/**
	 * �÷���ͨ�������ProviderBackDetail������Ӳɹ��˻���ϸ��Ϣ���÷����᷵��һ��booleanֵ����ʾ����Ƿ�ɹ�
	 * 
	 */
	public boolean addProviderBackDetail(ProviderBackDetail probd) {
		int i1 = 0;
		int i2 = 0;
		sql = "insert into `provider_back_detail` (`pbid`,`gid`,`amount`,`price`) values (?,?,?,?)";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, probd.getPbid());
			ps.setInt(2, probd.getGid());
			ps.setInt(3, probd.getAmount());
			ps.setDouble(4, probd.getPrice());
		} catch (SQLException e) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, probd.getAmount());
			ps.setInt(2, probd.getGid());

		} catch (SQLException e) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ�����ݱ�Provider_back_detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��addProviderBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� Provider_back_detail��ӳ���");
			return false;
		}

	}

	/**
	 * �÷���ͨ�������pbdidɾ���ɹ��˻���ϸ��Ϣ���᷵��һ�����֣����ִ�С����ɾ������Ӱ�����ݿ������
	 * 
	 */
	public int delProviderBackDetail(int pbdid) {
		int i = 0;
		sql = "delete `provider_back_detail` where `pbdid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbdid);
		} catch (SQLException e) {
			System.out.println("����ProviderBackDetailDao��Ϣ��delProviderBackDetail()���ִ���ԭ�� �޸�PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����ProviderBackDetailDao��Ϣ��delProviderBackDetail()���ִ���ԭ�� ִ��PreparedStatement sql�������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �÷���ͨ�������ProviderBackDetail����������ݿ�Ĳɹ��˻���ϸ��Ϣ���÷�������Booleanֵ��ʾ�Ƿ��޸ĳɹ�
	 */
	public boolean updateProviderBackDetail(ProviderBackDetail probd) {
		int i1 = 0;
		int i2 = 0;
		sql = "update `provider_back_detail` set `pbid`=?,`gid`=?,`amount`=`amount`+?,`price`=? where pbdid=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, probd.getPbid());
			ps.setInt(2, probd.getGid());
			ps.setInt(3, probd.getAmount());
			ps.setDouble(4, probd.getPrice());
			ps.setInt(5, probd.getPbdid());
		} catch (SQLException e) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, probd.getAmount());
			ps.setInt(2, probd.getGid());

		} catch (SQLException e) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ�����ݱ�Provider_back_detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println(
					"����ProviderBackDetailDao��Ϣ��updateProviderBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� Provider_back_detail��ӳ���");
			return false;
		}

	}

	/**
	 * �÷���ͨ������Ĳɹ��˻�id �� pbid ɾ���ɹ��˻���ϸ��Ϣ����Ϊ�ɹ��˻�id �� �ɹ��˻���ϸid ��һһ��Ӧ����˫˫��һ�޶�
	 */
	public ProviderBackDetail findProviderBackDetail(int pbid) {

		sql = "select a.`pbdid`,a.`pbid`,a.`gid`,a.`amount`,a.`price`,b.`name` from `provider_back_detail` a left join `goods_info` b on a.`gid`=b.`gid` where `pbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e) {
			System.out
					.println("����ProviderBackDetailDao��Ϣ��findProviderBackDetail ()���ִ���ԭ�� �޸�PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out
					.println("����ProviderBackDetailDao��Ϣ��findProviderBackDetail ()���ִ���ԭ�� ִ��PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				probd = new ProviderBackDetail();
				probd.setPbdid(rs.getInt(1));
				probd.setPbid(rs.getInt(2));
				probd.setGid(rs.getInt(3));
				probd.setAmount(rs.getInt(4));
				probd.setPrice(rs.getDouble(5));
				probd.setGname(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println("����ProviderBackDetailDao��Ϣ��findProviderBackDetail ()���ִ���ԭ�򣺻�ȡ��������ִ���");
			e.printStackTrace();
		}
		return probd;
	}
}
