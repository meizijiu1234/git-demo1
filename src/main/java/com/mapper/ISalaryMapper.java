package com.mapper;

import org.springframework.stereotype.Service;
import com.po.Salary;
@Service("ISalaryMapper")
public interface ISalaryMapper {
	//添加薪资
	public boolean save(Salary sa);
	//根据员工编号修改薪资
	public boolean updateByEid(Salary sa);//修改
	//根据员工编号删除薪资
	public boolean delByEid(Integer eid);//删除
	//根据员工编号查询薪资
	public Salary findByEid(Integer eid);//查询单个
}
