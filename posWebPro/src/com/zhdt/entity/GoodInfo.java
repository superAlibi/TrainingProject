package com.zhdt.entity;

/**
 * ��Ʒ��Ϣʵ����
 */
public class GoodInfo {
	private int gid;// ��Ʒid
	private String name;// ��Ʒ����
	private String tid;// ��Ʒ���id
	private String typeName;// ��Ʒ�������
	private String unit;// ��Ʒ��λ
	private double pin;// ��Ʒ����
	private double pout;// ��Ʒ�ۼ�
	private int amount;// ��Ʒ���

	public GoodInfo() {
		super();
	}

	public GoodInfo(int gid, String name, String tid, String typeName, String unit, double pin, double pout,
			int amount) {
		super();
		this.gid = gid;
		this.name = name;
		this.tid = tid;
		this.typeName = typeName;
		this.unit = unit;
		this.pin = pin;
		this.pout = pout;
		this.amount = amount;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getPin() {
		return pin;
	}

	public void setPin(double pin) {
		this.pin = pin;
	}

	public double getPout() {
		return pout;
	}

	public void setPout(double pout) {
		this.pout = pout;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
