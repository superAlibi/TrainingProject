package com.zhdt.entity;

/**
 * 这是采购信息表的实体
 */
public class StockInfo {
	private int sid;// 采购编号
	private int cid;// 供应商id
	private String cname; //供应商
	private String date;// 采购时间
	private double totalprice;// 采购总价
	private String buyer;// 采购人

	public StockInfo() {
		super();
	}

	public StockInfo(int sid, int cid, String cname, String date, double totalprice, String buyer) {
		super();
		this.sid = sid;
		this.cid = cid;
		this.cname = cname;
		this.date = date;
		this.totalprice = totalprice;
		this.buyer = buyer;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
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

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

}
