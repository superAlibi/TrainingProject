package com.zhdt.entity;

/**
 * 采购退货表实体
 * 
 */
public class ProviderBack {
	private int pbid;// 代表采购退货id
	private int cid;// 退货客户id
	private String cname;
	private int sid;// 销售id
	private String date;// 退货时间
	private double totalprice;// 退货总价

	public ProviderBack() {
		super();
	}

	public int getPbid() {
		return pbid;
	}

	public void setPbid(int pbid) {
		this.pbid = pbid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

}
