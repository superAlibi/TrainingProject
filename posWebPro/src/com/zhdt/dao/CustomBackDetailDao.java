package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.CustomBackDetail;
import com.zhdt.util.DBUtil;

/**
 * 该类用于对客户退货详细的数据库操作层
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-25 新建
 */
public class CustomBackDetailDao extends DBUtil {
	CustomBackDetail custombd;

	/**
	 * 该方法通过传入的CustomBackDetail对象添加采购退货详细信息，该方法会返回一个boolean值，表示添加是否成功
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
					.println("来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out
					.println("来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, custombd.getAmount());
			ps.setInt(2, custombd.getGid());
		} catch (SQLException e) {
			System.out
					.println("来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：数据表Provider_back_detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println(
					"来自CustomBackDetailDao消息：addCustomBackDetail()方法出错。原因：数据表Goods_info修改出错 和 Provider_back_detail添加出错");
			return false;
		}

	}

	/**
	 * 该方法通过传入的pbdid删除采购退货详细信息，会返回一个数字，数字大小代表删除操作影响数据库的行数
	 * 
	 */
	public int delCustomBackDetail(int cbdid) {
		int i = 0;
		sql = "delete `custom_back_detail` where `cbdid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cbdid);
		} catch (SQLException e) {
			System.out.println("来自CustomBackDetailDao消息：delCustomBackDetail()出现错误。原因： 修改PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自CustomBackDetailDao消息：delCustomBackDetail()出现错误。原因： 执行PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 该方法通过传入的ProviderBackDetail对象更新数据库的采购退货详细信息，该方法返回Boolean值表示是否修改成功
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
					"来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(
					"来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, custombd.getAmount());
			ps.setInt(2, custombd.getGid());

		} catch (SQLException e) {
			System.out.println(
					"来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：数据表custom_back_detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println(
					"来自CustomBackDetailDao消息：updateCustomBackDetail()方法出错。原因：数据表Goods_info修改出错 和 custom_back_detail添加出错");
			return false;
		}

	}

	/**
	 * 该方法通过传入的采购退货id 即 pbid 删除采购退货详细信息，因为采购退货id 与 采购退货详细id 是一一对应，且双双独一无二
	 */
	public CustomBackDetail findCustomBackDetail(int cbid) {

		sql = "select a.`cbdid`,a.`cbid`,a.`gid`,a.`amount`,a.`price`,b.`name` from `custom_back_detail` a left join `goods_info` b on a.`gid`=b.`gid` where `cbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, cbid);
		} catch (SQLException e) {
			System.out.println("来自CustomBackDetailDao消息：findCustomBackDetail ()出现错误。原因： 修改PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("来自CustomBackDetailDao消息：findCustomBackDetail ()出现错误。原因： 执行PreparedStatement sql对象错误");
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
			System.out.println("来自CustomBackDetailDao消息：findCustomBackDetail ()出现错误。原因：获取结果集出现错误");
			e.printStackTrace();
		}
		return custombd;
	}
}
