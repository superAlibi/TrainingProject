package com.zhdt.entity;

/**
 * 商品信息实体类
 */
public class GoodType {
	private String tid;// 商品类别id
	private String name;// 商品类别名称

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
