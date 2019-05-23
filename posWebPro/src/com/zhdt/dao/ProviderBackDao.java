package com.zhdt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhdt.entity.ProviderBack;
import com.zhdt.util.DBUtil;

/***
 ** 这是对采购退货信息数据库操作层
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-19 新建
 */
public class ProviderBackDao extends DBUtil {
	ProviderBack prob;

	/**
	 ** 该方法用于添加当个采购退货信息，该方法需要传入一个采购退货信息对象ProviderBack作为参数。该方法会返回一个数字，数字大小代表插入数据条数
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
			System.out.println("ProviderBackDao addProviderBack()消息：修改PreparedStatement sql 语句对象出错！ ");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProviderBackDao addProviderBack()消息：执行PreparedStatement sql 语句对象出错！ ");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * *该方法用于删除单个的采购退货信息，使用该方法需要传入采购退货信息的采购退货id即pbid，通过pbid删除采购退货信息，
	 * *该方法会返回一个数字，代表删除影响数据库的行数，0代表数据库没有受到影响
	 * 
	 */
	public int delProviderBack(int pbid) {
		int i = 0;

		sql = "delete from `provider_back` where `pbid` =?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e) {
			System.out.println("来自ProviderBackDao 消息：delProviderBack()方法修改PreparedStatement sql语句出现错误");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("来自ProviderBackDao 消息：delProviderBack()方法执行PreparedStatement sql语句出现错误");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 * 该方法通过传入的采购退货信息对象更新采购退货信息，该方法会返回一个数字，数字大小代表更新操作影响数据库多少行
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
			System.out.println("ProviderBackDao addProviderBack()消息：修改PreparedStatement sql 语句对象出错！ ");
			e.printStackTrace();
		}
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ProviderBackDao addProviderBack()消息：执行PreparedStatement sql 语句对象出错！ ");
			e.printStackTrace();
		}
		return i;

	}

	/**
	 ** 该方法用于查询所有的采购退货信息，该方法不需要传入参数。该方法会返回一个ProviderBack对象的集合
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
			System.out.println("ProviderBackDao findProviderBack()消息：获取数据库数据为空 ");
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
			System.out.println("来自ProviderBackDao 消息：findProviderBackInfo ()方法出现错误，原因：修改PreparedStatement sql语句对象错误");
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
			System.out.println("ProviderBackDao findProviderBack()消息：获取数据库数据为空 ");
			e.printStackTrace();
		}
		return probs;
	}

	/**
	 ** 该方法用于查询单个的采购退货信息，该方法需要传入采购退货信息的id，即pbid，通过pbid查询单个的采购退货信息,该方法会返回一个ProviderBack
	 * 对象
	 * 
	 */
	public ProviderBack findProviderBack(int pbid) {
		ProviderBack providb = new ProviderBack();
		sql = "select a.`pbid`,a.`cid`,a.`sid`,a.`date`,a.`totalprice`,b.`name` from `provider_back` a left join `custom_info` b on a.`cid`=b.`cid` where `pbid`=?";
		
		try {
			ps = getPs(sql);
			ps.setInt(1, pbid);
		} catch (SQLException e) {
			System.out.println("来自ProviderBackDao 消息：findProviderBack ()方法出现错误，原因：修改PreparedStatement sql语句对象错误");

			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("来自ProviderBackDao 消息：findProviderBack ()方法出现错误，原因：执行PreparedStatement sql语句对象错误");
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
			System.out.println("来自ProviderBackDao 消息：findProviderBack ()方法出现错误，原因：来自数据的数据为空");
			e.printStackTrace();
		}
		return providb;

	}

}
