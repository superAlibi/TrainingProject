package com.zhdt.entity;

/**
 * 销售明细表实体
 */
public class SellDetail {
	private int sdid;// 明细id
	private int sid;// 采购id
	private int gid;// 商品id
	private String gname;// 商品名
	private int amount;// 数量
	private double price;// 单价

	public SellDetail() {
		super();
	}

	public int getSdid() {
		return sdid;
	}

	public void setSdid(int sdid) {
		this.sdid = sdid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
