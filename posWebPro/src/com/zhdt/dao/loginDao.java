package com.zhdt.dao;

import java.sql.SQLException;
import com.zhdt.entity.Admin;
import com.zhdt.util.DBUtil;

/***
 * *���Ƕ��û���¼ʱʹ�õ����ݿ������,����֧��pos����Ա�û�����¼
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
public class loginDao extends DBUtil {
	Admin adm;

	public loginDao(String name) {
		sql = "select adminid, name, pwd, level from pos_system_administrators where name =?";
		
		try {
			ps = getPs(sql);
			ps.setString(1, name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql����޸�ʧ��");
		}
		try {
			rs = getRs(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Admin getAdmin() {
		try {
			rs = getRs(ps);
			if (rs.next()) {
				adm = new Admin();
				adm.setUid(rs.getString(1));
				adm.setName(rs.getString(2));
				adm.setPwd(rs.getString(3));
				adm.setLevel(rs.getInt(4));
				return adm;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return null;
	}

}
