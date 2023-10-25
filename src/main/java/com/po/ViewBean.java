package com.po;

import java.io.Serializable;
import java.util.List;

public class ViewBean implements Serializable{
	private int page=1;//当前页数最低为1
	private int rows=5;//每页记录数初始值为6条
	private int viewMax;//总页数
	private List<?> viewList;//查询某个表返回的结果集
	public ViewBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViewBean(int page, int rows, int viewMax, List<?> viewList) {
		super();
		this.page = page;
		this.rows = rows;
		this.viewMax = viewMax;
		this.viewList = viewList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getViewMax() {
		return viewMax;
	}
	public void setViewMax(int viewMax) {
		this.viewMax = viewMax;
	}
	public List<?> getViewList() {
		return viewList;
	}
	public void setViewList(List<?> viewList) {
		this.viewList = viewList;
	}
	@Override
	public String toString() {
		return "ViewBean [page=" + page + ", rows=" + rows + ", viewMax=" + viewMax + ", viewList=" + viewList + "]";
	}

}
