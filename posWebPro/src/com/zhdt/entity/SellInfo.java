package com.zhdt.entity;

/**
 * ������Ϣʵ����
 */
public class SellInfo {
	private int sid;// ������Ϣid
	private int cid;// �ͻ�id
	private String cname;// �ͻ���
	private String date;// ����ʱ��
	private double totalprice;// �����ܼ�
	private String buyer;// ������

	public SellInfo() {
		super();
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
