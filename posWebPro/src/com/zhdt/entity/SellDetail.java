package com.zhdt.entity;

/**
 * ������ϸ��ʵ��
 */
public class SellDetail {
	private int sdid;// ��ϸid
	private int sid;// �ɹ�id
	private int gid;// ��Ʒid
	private String gname;// ��Ʒ��
	private int amount;// ����
	private double price;// ����

	public SellDetail() {
		super();
	}

	public int getSdid() {
		return sdid;
	}

	public void setSdid(int sdid) {
		this.sdid = sdid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
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
