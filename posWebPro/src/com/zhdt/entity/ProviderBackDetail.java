package com.zhdt.entity;

/**
 * 采购退货明细实体
 */
public class ProviderBackDetail {
	private int pbdid;// 退货明细id
	private int pbid;// 退货id
	private int gid;// 商品id
	private String gname;// 商品名称
	private int amount;// 数量
	private double price;// 单价

	public ProviderBackDetail() {
		super();
	}

	public int getPbdid() {
		return pbdid;
	}

	public void setPbdid(int pbdid) {
		this.pbdid = pbdid;
	}

	public int getPbid() {
		return pbid;
	}

	public void setPbid(int pbid) {
		this.pbid = pbid;
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
