package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.CustomBackDetail;
import com.zhdt.util.DBUtil;

/**
 * �������ڶԿͻ��˻���ϸ�����ݿ������
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-25 �½�
 */
public class CustomBackDetailDao extends DBUtil {
	CustomBackDetail custombd;

	/**
	 * �÷���ͨ�������CustomBackDetail������Ӳɹ��˻���ϸ��Ϣ���÷����᷵��һ��booleanֵ����ʾ����Ƿ�ɹ�
	 * 
	 */
	public boolean addCustomBackDetail(CustomBackDetail custombd) {
		int i1 = 0;
		int i2 = 0;
		sql = "insert into `custom_back_detail` (`cbid`,`gid`,`amount`,`price`) values (?,?,?,?)";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, custombd.getCbid());
			ps.setInt(2, custombd.getGid());
			ps.setInt(3, custombd.getAmount());
			ps.setDouble(4, custombd.getPrice());
		} catch (SQLException e) {
			System.out
					.println("����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out
					.println("����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, custombd.getAmount());
			ps.setInt(2, custombd.getGid());
		} catch (SQLException e) {
			System.out
					.println("����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ�����ݱ�Provider_back_detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��addCustomBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� Provider_back_detail��ӳ���");
			return false;
		}

	}

	/**
	 * �÷���ͨ�������pbdidɾ���ɹ��˻���ϸ��Ϣ���᷵��һ�����֣����ִ�С����ɾ������Ӱ�����ݿ������
	 * 
	 */
	public int delCustomBackDetail(int cbdid) {
		int i = 0;
		sql = "delete `custom_back_detail` where `cbdid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cbdid);
		} catch (SQLException e) {
			System.out.println("����CustomBackDetailDao��Ϣ��delCustomBackDetail()���ִ���ԭ�� �޸�PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����CustomBackDetailDao��Ϣ��delCustomBackDetail()���ִ���ԭ�� ִ��PreparedStatement sql�������");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * �÷���ͨ�������ProviderBackDetail����������ݿ�Ĳɹ��˻���ϸ��Ϣ���÷�������Booleanֵ��ʾ�Ƿ��޸ĳɹ�
	 */
	public boolean updateCustomBackDetail(CustomBackDetail custombd) {
		int i1 = 0;
		int i2 = 0;
		sql = "update `custom_back_detail` set `cbid`=?,`gid`=?,`amount`=`amount`+?,`price`=? where cbdid=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, custombd.getCbid());
			ps.setInt(2, custombd.getGid());
			ps.setInt(3, custombd.getAmount());
			ps.setDouble(4, custombd.getPrice());
			ps.setInt(5, custombd.getCbdid());
		} catch (SQLException e) {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ���޸ĵ�һ��PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ��ִ�е�һ��PreparedStatement sql ��������� ");
			e1.printStackTrace();
		}
		// �����ڶ������ݿ�
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, custombd.getAmount());
			ps.setInt(2, custombd.getGid());

		} catch (SQLException e) {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ���޸ĵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ��ִ�еڵڶ���PreparedStatement sql ��������� ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ�����ݱ�custom_back_detail��ӳ���");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ���");
			return false;
		} else {
			System.out.println(
					"����CustomBackDetailDao��Ϣ��updateCustomBackDetail()��������ԭ�����ݱ�Goods_info�޸ĳ��� �� custom_back_detail��ӳ���");
			return false;
		}

	}

	/**
	 * �÷���ͨ������Ĳɹ��˻�id �� pbid ɾ���ɹ��˻���ϸ��Ϣ����Ϊ�ɹ��˻�id �� �ɹ��˻���ϸid ��һһ��Ӧ����˫˫��һ�޶�
	 */
	public CustomBackDetail findCustomBackDetail(int cbid) {

		sql = "select a.`cbdid`,a.`cbid`,a.`gid`,a.`amount`,a.`price`,b.`name` from `custom_back_detail` a left join `goods_info` b on a.`gid`=b.`gid` where `cbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cbid);
		} catch (SQLException e) {
			System.out.println("����CustomBackDetailDao��Ϣ��findCustomBackDetail ()���ִ���ԭ�� �޸�PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("����CustomBackDetailDao��Ϣ��findCustomBackDetail ()���ִ���ԭ�� ִ��PreparedStatement sql�������");
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				custombd = new CustomBackDetail();
				custombd.setCbdid(rs.getInt(1));
				custombd.setCbid(rs.getInt(2));
				custombd.setGid(rs.getInt(3));
				custombd.setAmount(rs.getInt(4));
				custombd.setPrice(rs.getDouble(5));
				custombd.setGname(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println("����CustomBackDetailDao��Ϣ��findCustomBackDetail ()���ִ���ԭ�򣺻�ȡ��������ִ���");
			e.printStackTrace();
		}
		return custombd;
	}
}
