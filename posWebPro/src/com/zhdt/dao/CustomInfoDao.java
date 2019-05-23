package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.CustomInfo;
import com.zhdt.util.DBUtil;

/**
 * *这是对客户信息的数据库操作层，用于操作对客户信息的增删改查
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
public class CustomInfoDao extends DBUtil {
	CustomInfo csti = null;

	/**
	 * addCustomInfo()方法用于插入客户信息，该方法要求传入一个customInfo 对象
	 * *该方法会返回一个数字，这个数字就是插入的数据影响数据库的行数，如果返回的数字为0，代表没有任何信息写入到数据库
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
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql语句对象修改错误");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CustomInfoDao addCustomInfo() PreparedStatement sql语句执行到数据库错误");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * delCustomInfo()方法用于删除单个客户信息，该方法要求传入客户信息的cid ，根据cid删除客户信息
	 * *该方法会返回一个数字，这个数字就是插入的数据影响数据库的行数，如果返回的数字为0，代表没有任何信息写入到数据库
	 */
	public int delCustomInfo(int cid) {
		int i = 0;
		sql = "delete from `custom_info` where `cid` =? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cid);
		} catch (SQLException e) {
			System.out.println("CustomInfoDao delCustomInfo() PreparedStatement sql语句对象修改错误");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CustomInfoDao delCustomInfo() PreparedStatement sql语句执行到数据库错误");
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * updateCustomInfo()方法用于更新客户信息，该方法要求传入一个customInfo对象
	 * *该方法会返回一个数字，这个数字就是插入的数据影响数据库的行数，如果返回的数字为0，代表没有任何信息写入到数据库
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
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql语句对象修改错误");
			e.printStackTrace();
		}

		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CustomInfoDao addCustomInfo() PreparedStatement sql语句执行到数据库错误");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * /** findCustom()方法用于查询一个类型的客户信息，该方法要求传入客户type ，通过type在数据库查询客户类型为type的所有客户信息
	 * *并返回一个List<customInfo>集合
	 */
	public List<CustomInfo> findCustoms(int type) {
		List<CustomInfo> customs = new ArrayList<>();
		sql = "SELECT `cid`,`name`,`linkman`,`addr`,`tel`,`email`,`remark`,`type` FROM custom_info WHERE `type` = ? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, type);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql语句对象修改错误");
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
			System.out.println("CustomInfoDao findCustom()方法获到数据失败");
		}
		return customs;
	}

	/**
	 * findCustom()方法用于查询单个客户信息，该方法要求传入一个客户的客户id 即 ：cid，通过cid在数据库查询客户信息
	 * *并返回单个customInfo客户信息对象
	 */
	public CustomInfo findCustom(int cid) {
		csti = new CustomInfo();
		sql = "SELECT `cid`,`name`,`linkman`,`addr`,`tel`,`email`,`remark`,`type` FROM custom_info WHERE `cid` =? ";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cid);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql语句对象修改错误");
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
			System.out.println("CustomInfoDao findCustom()方法获到数据失败");
		}

		return csti;
	}

	/**
	 * 该方法用于查询商品销售管理的单独方法，使用该方法需要传入一个客户信息的id即cid，该方法会返回一个有且只有一个CustomInfo的集合
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
			System.out.println("CustomInfoDao findCustom() PreparedStatement sql语句对象修改错误");
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
			System.out.println("CustomInfoDao findCustom()方法获到数据失败");
		}

		return custom;
	}

}
