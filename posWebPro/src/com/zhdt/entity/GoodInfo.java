package com.zhdt.entity;

/**
 * 商品信息实体类
 */
public class GoodInfo {
	private int gid;// 商品id
	private String name;// 商品名称
	private String tid;// 商品类别id
	private String typeName;// 商品类别名称
	private String unit;// 商品单位
	private double pin;// 商品进价
	private double pout;// 商品售价
	private int amount;// 商品库存

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
