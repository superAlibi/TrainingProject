package com.zhdt.entity;

/**
 * �ɹ��˻���ϸʵ��
 */
public class ProviderBackDetail {
	private int pbdid;// �˻���ϸid
	private int pbid;// �˻�id
	private int gid;// ��Ʒid
	private String gname;// ��Ʒ����
	private int amount;// ����
	private double price;// ����

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
