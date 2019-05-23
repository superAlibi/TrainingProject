package com.zhdt.entity;

/**
 * 客户退货明细实体
 */
public class CustomBackDetail {
	private int cbdid;// 退货明细id
	private int cbid;// 退货id
	private int gid;// 商品id
	private String gname;// 商品名
	private int amount;// 退货数量
	private double price;// 退款总额

	public CustomBackDetail() {
		super();
	}

	public int getCbdid() {
		return cbdid;
	}

	public void setCbdid(int cbdid) {
		this.cbdid = cbdid;
	}

	public int getCbid() {
		return cbid;
	}

	public void setCbid(int cbid) {
		this.cbid = cbid;
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
