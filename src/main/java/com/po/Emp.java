package com.po;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Emp implements Serializable{
	private Integer eid;
	private String ename;
	private String sex;
	private Date birthday;
	private String address;
	private String picture="default.jpg";
	private Integer depid;
	//临时属性
	private String sdate;
	private String dname;
	private float emoney;

	private String[] weids;//前台获取的福利编号
	private List<Welfare> wfList;//部门名称
	private MultipartFile pic;//Spring文件上传
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Emp(String ename, String sex, Date birthday, String address, String picture, Integer depid) {
		super();
		this.ename = ename;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
		this.picture = picture;
		this.depid = depid;
	}



	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	public String getSdate() {
		sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		return sdate;
	}
	public void setSdate(String sdate) {
		try {
			birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sdate = sdate;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

	public float getEmoney() {
		return emoney;
	}
	public void setEmoney(float emoney) {
		this.emoney = emoney;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	public String[] getWeids() {
		return weids;
	}
	public void setWeids(String[] weids) {
		this.weids = weids;
	}



	public List<Welfare> getWfList() {
		return wfList;
	}



	public void setWfList(List<Welfare> wfList) {
		this.wfList = wfList;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", birthday=" + birthday + ", address="
				+ address + ", picture=" + picture + ", depid=" + depid + ", sdate=" + sdate + ", dname=" + dname
				+ ", emoney=" + emoney + ", weids=" + weids[0] + ", wfList=" + wfList + ", pic=" + pic + "]";
	}











}
