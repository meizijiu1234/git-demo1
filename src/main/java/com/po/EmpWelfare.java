package com.po;

import java.io.Serializable;

public class EmpWelfare implements Serializable{
	private Integer ewid;
	private Integer empid;
	private Integer wid;
	public EmpWelfare() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpWelfare(Integer ewid, Integer empid, Integer wid) {
		super();
		this.ewid = ewid;
		this.empid = empid;
		this.wid = wid;
	}
	public EmpWelfare(Integer empid, Integer wid) {
		super();
		this.empid = empid;
		this.wid = wid;
	}
	public Integer getEwid() {
		return ewid;
	}
	public void setEwid(Integer ewid) {
		this.ewid = ewid;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	@Override
	public String toString() {
		return "Empwelfare [ewid=" + ewid + ", empid=" + empid + ", wid=" + wid + "]";
	}
	
}
