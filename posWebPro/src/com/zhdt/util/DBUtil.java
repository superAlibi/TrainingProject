package com.zhdt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * *���������ݿ����Ӻ�jdbc�Ĺ����࣬���ṩconnection PreparedStatement ResultSet Statement ����,
 * *�������ṩ��sql����Լ�update insert delete �� PreparedStatement �� Statement
 * sql������ִ�е����ݿ�ĺ󷵻ص�Ӱ������
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½� 2019-03-21 �޸����쳣�׳���ʽΪ�����׳����޸��˳�close��������Ȩ��Ϊprotected
 *          �����еĳ�Ա�����ķ���Ȩ�޽���
 */
public class DBUtil {

	// �������ݿ��ַ
	private static final String URL = "jdbc:MySQL://localhost:3306/webposproject?useSSL=false&serverTimezone=UTC";
	// ׼�����ݿ���û�������
	private static final String NAME = "root";
	// ׼�����ݿ���û��������
	private static final String PWD = "User_Xu9712";
	// �������ӱ���
	protected String sql;
	private Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
//static {
//    try {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//    } catch (ClassNotFoundException e) {
//        System.out.println("�������ش�����");
//        e.printStackTrace();
//    }
//}
	// ��ȡ���ݿ����ӷ���
	protected DBUtil() {

		try {
		    
			conn = DriverManager.getConnection(URL, NAME, PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * PreparedStatement()���������sql���ת����PreparedStatement sql��䣬������PreparedStatement
	 * sql������ķ���
	 * 
	 * @throws SQLException
	 * 
	 */
	protected PreparedStatement getPs(String sql) throws SQLException {

		return conn.prepareStatement(sql);

	}

	/**
	 * getRs����ļ򵥵�String ����sql��䴫��ת����ResultSet ���� ����ʽ���ؽ�����ķ���
	 * 
	 * @throws SQLException
	 * 
	 */
	protected ResultSet getRs(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);

	}

	/**
	 * getRs�������PreparedStatement sql ������ת����ResultSet ���󷵻ؽ��������ʽ���ؽ��
	 * 
	 * @throws SQLException
	 * 
	 */
	protected ResultSet getRs(PreparedStatement ps) throws SQLException {

		return ps.executeQuery();

	}

	/**
	 * getI()���򵥵�sql��䴫�벢ת����Statement ����ִ�е����ݿ⣬Ȼ�󷵻���Ӱ�������
	 * 
	 * @throws SQLException
	 */
	protected int getI(String sql) throws SQLException {

		Statement sta = conn.createStatement();
		return sta.executeUpdate(sql);

	}

	/**
	 * *������Ҫ�������ݵ�PreparedStatement sql�������벢ִ�е����ݿ⣬Ȼ�󷵻���Ӱ�������
	 * 
	 * @throws SQLException
	 */
	protected int getI(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate();

	}

	/**
	 * close() �÷����Ὣ�жϲ�Ϊ�����еĶ�������Ϊ�գ�����String ���͵Ķ��󣬱���رյĶ����� String sql ����,Conection
	 * conn���� , ResultSet rs����PreparedStatement ps����Statement sta ����
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
			System.out.println("DBUtil close()�رճ���");
		}
	}

}
