package com.zhdt.entity;

/**
 * ��Ʒ��Ϣʵ����
 */
public class GoodType {
	private String tid;// ��Ʒ���id
	private String name;// ��Ʒ�������

	public GoodType() {
		super();
	}

	public GoodType(String tid, String name) {
		super();
		this.tid = tid;
		this.name = name;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
