package com.zhdt.entity;

/**
 * �ͻ��˻���
 */
public class CustomBack {
	private int cbid;// �˻�id
	private int cid;// �˻��ͻ�id
	private String cname;// �˻��ͻ�����
	private int sid;// ����id
	private String date;// �˻�ʱ��
	private double totalprice;// �˻��ܼ�

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
