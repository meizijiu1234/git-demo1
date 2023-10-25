package com.controller;

import com.po.Emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IEmpAction {
	public String save(HttpServletRequest request, HttpServletResponse response, Emp emp);//添加
	public String update(HttpServletRequest request, HttpServletResponse response, Emp emp);//修改
	public String delByEid(HttpServletRequest request, HttpServletResponse response, Integer eid);//删除
	public String findByEid(HttpServletRequest request, HttpServletResponse response, Integer eid);//查询单个
	public String findEmpViewAll(HttpServletRequest request, HttpServletResponse response, Integer views,Integer viewNum);//分页查询信息
	public String doinit(HttpServletRequest request, HttpServletResponse response);//返回页面数据
}
