package com.zhdt.entity;
/**
 * ��Ʒ��Ϣ����Աʵ����
 * 
 * */
public class Admin {
	private String uid;//����Աid
	private String name;//����Ա����
	private String pwd;//����Ա����
	private int level;//����Ա����
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
