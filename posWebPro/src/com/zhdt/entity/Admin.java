package com.zhdt.entity;
/**
 * 商品信息管理员实体类
 * 
 * */
public class Admin {
	private String uid;//管理员id
	private String name;//管理员名字
	private String pwd;//管理员密码
	private int level;//管理员级别
	public Admin() {
		
	}
	public Admin(String uid, String name, String pwd, int level) {
		super();
		this.uid = uid;
		this.name = name;
		this.pwd = pwd;
		this.level = level;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
