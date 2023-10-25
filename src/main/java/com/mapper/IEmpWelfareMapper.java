package com.mapper;

import com.po.EmpWelfare;
import com.po.Welfare;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IEmpWelfareMapper")
public interface IEmpWelfareMapper {
	/****修改员工福利begin***/
	//根据员工编号删除员工福利
	public boolean delByEid(Integer eid);
	//增加员工福利
	public boolean save(EmpWelfare ew);
	/***修改员工福利end****/
	//根据员工编号查询员工福利的多个福利
	public List<Welfare> findByEid(Integer eid);
}
