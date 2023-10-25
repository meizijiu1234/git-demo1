package com.service;

import com.po.Emp;
import com.po.ViewBean;

import java.util.List;

public interface IEmpService {
	public boolean save(Emp emp);//添加
	public boolean update(Emp emp);//修改
	public boolean delByEid(Integer eid);//删除
	public Emp findByEid(Integer eid);//查询单个
	public List<Emp> findEmpViewAll(ViewBean vb);//分页查询信息
	public Integer findViewMax();//查询总页数
}
