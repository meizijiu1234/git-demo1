package com.po;

import java.io.Serializable;

public class Salary implements Serializable{
	private Integer sid;
	private Integer eid;
	private float emoney;
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salary(Integer sid, Integer eid, float emoney) {
		super();
		this.sid = sid;
		this.eid = eid;
		this.emoney = emoney;
	}
	//添加专用有参
	public Salary(Integer eid, float emoney) {
		super();
		this.eid = eid;
		this.emoney = emoney;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public float getEmoney() {
		return emoney;
	}
	public void setEmoney(float emoney) {
		this.emoney = emoney;
	}
	@Override
	public String toString() {
		return "Salary [sid=" + sid + ", eid=" + eid + ", emoney=" + emoney + "]";
	}

}
