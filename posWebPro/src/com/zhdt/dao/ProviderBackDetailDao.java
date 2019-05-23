package com.zhdt.dao;

import java.sql.SQLException;

import com.zhdt.entity.ProviderBackDetail;
import com.zhdt.util.DBUtil;

/***
 * *该方法是对采购退货的详细信息的数据库操作层
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-24 新建
 * 
 */
public class ProviderBackDetailDao extends DBUtil {
	ProviderBackDetail probd;

	/**
	 * 该方法通过传入的ProviderBackDetail对象添加采购退货详细信息，该方法会返回一个boolean值，表示添加是否成功
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
					"来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(
					"来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, probd.getAmount());
			ps.setInt(2, probd.getGid());

		} catch (SQLException e) {
			System.out.println(
					"来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：数据表Provider_back_detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println(
					"来自ProviderBackDetailDao消息：addProviderBackDetail()方法出错。原因：数据表Goods_info修改出错 和 Provider_back_detail添加出错");
			return false;
		}

	}

	/**
	 * 该方法通过传入的pbdid删除采购退货详细信息，会返回一个数字，数字大小代表删除操作影响数据库的行数
	 * 
	 */
	public int delProviderBackDetail(int pbdid) {
		int i = 0;
		sql = "delete `provider_back_detail` where `pbdid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbdid);
		} catch (SQLException e) {
			System.out.println("来自ProviderBackDetailDao消息：delProviderBackDetail()出现错误。原因： 修改PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自ProviderBackDetailDao消息：delProviderBackDetail()出现错误。原因： 执行PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 该方法通过传入的ProviderBackDetail对象更新数据库的采购退货详细信息，该方法返回Boolean值表示是否修改成功
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
					"来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：修改第一个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i1 = ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println(
					"来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：执行第一个PreparedStatement sql 语句对象出错 ");
			e1.printStackTrace();
		}
		// 操作第二个数据库
		sql = "update `goods_info` set `amount`=`amount`+? where `gid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, probd.getAmount());
			ps.setInt(2, probd.getGid());

		} catch (SQLException e) {
			System.out.println(
					"来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：修改第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}
		try {
			i2 = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(
					"来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：执行第第二个PreparedStatement sql 语句对象出错 ");
			e.printStackTrace();
		}

		if (i1 != 0 && i2 != 0) {
			return true;
		}
		if (i1 == 0 && i2 != 0) {
			System.out.println("来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：数据表Provider_back_detail添加出错");
			return false;
		} else if (i1 != 0 && i2 == 0) {
			System.out.println("来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：数据表Goods_info修改出错");
			return false;
		} else {
			System.out.println(
					"来自ProviderBackDetailDao消息：updateProviderBackDetail()方法出错。原因：数据表Goods_info修改出错 和 Provider_back_detail添加出错");
			return false;
		}

	}

	/**
	 * 该方法通过传入的采购退货id 即 pbid 删除采购退货详细信息，因为采购退货id 与 采购退货详细id 是一一对应，且双双独一无二
	 */
	public ProviderBackDetail findProviderBackDetail(int pbid) {

		sql = "select a.`pbdid`,a.`pbid`,a.`gid`,a.`amount`,a.`price`,b.`name` from `provider_back_detail` a left join `goods_info` b on a.`gid`=b.`gid` where `pbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e) {
			System.out
					.println("来自ProviderBackDetailDao消息：findProviderBackDetail ()出现错误。原因： 修改PreparedStatement sql对象错误");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out
					.println("来自ProviderBackDetailDao消息：findProviderBackDetail ()出现错误。原因： 执行PreparedStatement sql对象错误");
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
			System.out.println("来自ProviderBackDetailDao消息：findProviderBackDetail ()出现错误。原因：获取结果集出现错误");
			e.printStackTrace();
		}
		return probd;
	}
}
