package com.zhdt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * *该类是数据库连接和jdbc的工具类，可提供connection PreparedStatement ResultSet Statement 对象,
 * *还可以提供简单sql语句以及update insert delete 的 PreparedStatement 或 Statement
 * sql语句对象执行到数据库的后返回的影响行数
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建 2019-03-21 修改了异常抛出方式为向上抛出，修改了除close方法访问权限为protected
 *          将所有的成员变量的访问权限降低
 */
public class DBUtil {

	// 设置数据库地址
	private static final String URL = "jdbc:MySQL://localhost:3306/webposproject?useSSL=false&serverTimezone=UTC";
	// 准备数据库的用户名变量
	private static final String NAME = "root";
	// 准备数据库的用户密码变量
	private static final String PWD = "User_Xu9712";
	// 设置连接变量
	protected String sql;
	private Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
//static {
//    try {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//    } catch (ClassNotFoundException e) {
//        System.out.println("驱动加载错误啦");
//        e.printStackTrace();
//    }
//}
	// 获取数据库连接方法
	protected DBUtil() {

		try {
		    
			conn = DriverManager.getConnection(URL, NAME, PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * PreparedStatement()将传入的是sql语句转化成PreparedStatement sql语句，并返回PreparedStatement
	 * sql语句对象的方法
	 * 
	 * @throws SQLException
	 * 
	 */
	protected PreparedStatement getPs(String sql) throws SQLException {

		return conn.prepareStatement(sql);

	}

	/**
	 * getRs传入的简单的String 类型sql语句传入转化成ResultSet 对象 的形式返回结果集的方法
	 * 
	 * @throws SQLException
	 * 
	 */
	protected ResultSet getRs(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);

	}

	/**
	 * getRs将传入的PreparedStatement sql 语句对象转化成ResultSet 对象返回结果集的形式返回结果
	 * 
	 * @throws SQLException
	 * 
	 */
	protected ResultSet getRs(PreparedStatement ps) throws SQLException {

		return ps.executeQuery();

	}

	/**
	 * getI()将简单的sql语句传入并转化成Statement 对象执行到数据库，然后返回受影响的行数
	 * 
	 * @throws SQLException
	 */
	protected int getI(String sql) throws SQLException {

		Statement sta = conn.createStatement();
		return sta.executeUpdate(sql);

	}

	/**
	 * *将不需要返回数据的PreparedStatement sql语句对象传入并执行到数据库，然后返回受影响的行数
	 * 
	 * @throws SQLException
	 */
	protected int getI(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate();

	}

	/**
	 * close() 该方法会将判断不为空所有的对象设置为空，包括String 类型的对象，本类关闭的对象有 String sql 对象,Conection
	 * conn对象 , ResultSet rs对象，PreparedStatement ps对象，Statement sta 对象
	 * 
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			if (sql != null)
				sql = null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBUtil close()关闭出错");
		}
	}

}
