package com.mapper;

import com.po.Emp;
import com.po.ViewBean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("IEmpMapper")
public interface IEmpMapper {
	public int save(Emp emp);//添加
	public int update(Emp emp);//修改
	public int delByEid(Integer eid);//删除
	public Emp findByEid(Integer eid);//查询单个
	public List<Emp> findEmpViewAll(ViewBean vb);//分页查询信息
	public int findViewMax();//查询总页数
	public int findMaxEid();//查询最大员工编号

}
