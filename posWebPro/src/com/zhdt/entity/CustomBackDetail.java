package com.zhdt.entity;

/**
 * �ͻ��˻���ϸʵ��
 */
public class CustomBackDetail {
	private int cbdid;// �˻���ϸid
	private int cbid;// �˻�id
	private int gid;// ��Ʒid
	private String gname;// ��Ʒ��
	private int amount;// �˻�����
	private double price;// �˿��ܶ�

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
