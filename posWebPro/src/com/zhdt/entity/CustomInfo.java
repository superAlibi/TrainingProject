package com.zhdt.entity;

/**
 * �ͻ���Ϣʵ����
 */

public class CustomInfo {

	private int cid;// �ͻ�id
	private String name;// ����
	private String linkman;// �ͻ���ϵϲ��
	private String addr;// **
	private String tel;// **
	private String email;// **
	private String remark;// **
	private int type;// 1.�ͻ� 2.��Ӧ��

	public CustomInfo() {
		super();
	}

	public CustomInfo(int cid, String name, String linkman, String addr, String tel, String email, String remark,
			int type) {
		super();
		this.cid = cid;
		this.name = name;
		this.linkman = linkman;
		this.addr = addr;
		this.tel = tel;
		this.email = email;
		this.remark = remark;
		this.type = type;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
