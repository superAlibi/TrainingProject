package com.zhdt.entity;

/**
 * ���ǲɹ���Ϣ���ʵ��
 */
public class StockInfo {
	private int sid;// �ɹ����
	private int cid;// ��Ӧ��id
	private String cname; //��Ӧ��
	private String date;// �ɹ�ʱ��
	private double totalprice;// �ɹ��ܼ�
	private String buyer;// �ɹ���

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
