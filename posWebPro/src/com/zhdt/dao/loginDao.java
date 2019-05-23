package com.zhdt.dao;

import java.sql.SQLException;
import com.zhdt.entity.Admin;
import com.zhdt.util.DBUtil;

/***
 * *这是对用户登录时使用的数据库操作层,仅仅支持pos管理员用户名登录
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
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
			System.out.println("sql语句修改失败");
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
