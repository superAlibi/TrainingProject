package com.zhdt.entity;

/**
 * �ɹ��˻���ʵ��
 * 
 */
public class ProviderBack {
	private int pbid;// ����ɹ��˻�id
	private int cid;// �˻��ͻ�id
	private String cname;
	private int sid;// ����id
	private String date;// �˻�ʱ��
	private double totalprice;// �˻��ܼ�

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
