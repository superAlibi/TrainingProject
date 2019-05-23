package com.zhdt.entity;

/**
 * 客户退货表
 */
public class CustomBack {
	private int cbid;// 退货id
	private int cid;// 退货客户id
	private String cname;// 退货客户名字
	private int sid;// 销售id
	private String date;// 退货时间
	private double totalprice;// 退货总价

	public CustomBack() {
		super();
	}

	public int getCbid() {
		return cbid;
	}

	public void setCbid(int cdid) {
		this.cbid = cdid;
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
