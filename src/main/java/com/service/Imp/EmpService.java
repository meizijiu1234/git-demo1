package com.service.Imp;

import com.po.*;
import com.service.IEmpService;
import com.util.DaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("EmpService")
@Transactional
public class EmpService implements IEmpService {
	@Resource(name="DaoService")
	private DaoService dao;

	public DaoService getDao() {
		return dao;
	}

	public void setDao(DaoService dao) {
		this.dao = dao;
	}

	public boolean save(Emp emp) {
		if (emp!=null) {
			/*************/
			//1.添加员工数据到员工表
			int code=dao.getEmpMapper().save(emp);
			if (code>0) {
				//获取正在添加的该员工编号
				int maxEid=dao.getEmpMapper().findMaxEid();
				//2.添加该员工的薪资（薪资表）
				Salary sa=new Salary(maxEid,emp.getEmoney());
				dao.getSalaryMapper().save(sa);
				//3.添加该员工对应的福利（员工福利表）
				if (emp.getWeids().length>0) {
					for (String wid : emp.getWeids()) {
						EmpWelfare ewf=new EmpWelfare(maxEid,Integer.parseInt(wid));
						dao.getEmpWelfareMapper().save(ewf);
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean update(Emp emp) {
		//修改emp表记录
		int code=dao.getEmpMapper().update(emp);
		if (code>0) {
			//修改empwelfare表记录(先删再添)
			dao.getEmpWelfareMapper().delByEid(emp.getEid());
			if (emp.getWeids().length>0) {
				for (String wid : emp.getWeids()) {
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),Integer.parseInt(wid));
					dao.getEmpWelfareMapper().save(ewf);
				}
			}
			//修改salary表记录（限制降薪）
			Float emoney=emp.getEmoney();
			if (emoney>=2500) {
				System.out.println("开始修改薪资");
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				dao.getSalaryMapper().updateByEid(sa);
				return true;
			}
		}
		return false;
	}

	public boolean delByEid(Integer eid) {
		//1.删除从表empWelfare记录
		dao.getEmpWelfareMapper().delByEid(eid);
		//2.删除从表salary记录
		dao.getSalaryMapper().delByEid(eid);
		//3.最后删除主表emp记录
		int code=dao.getEmpMapper().delByEid(eid);
		if (code>0) {
			return true;
		}
		return false;
	}

	public Emp findByEid(Integer eid) {
		//员工对象
		Emp oldemp =dao.getEmpMapper().findByEid(eid);
		//查询薪资写入
		oldemp.setEmoney(dao.getSalaryMapper().findByEid(eid).getEmoney());
		/***查询福利写入(包含前端要写入的wfList集合和weids数组)****/
		//获取wfList员工福利集合
		List<Welfare> wfList=dao.getEmpWelfareMapper().findByEid(eid);
		oldemp.setWfList(wfList);
		//获取weids福利编号数组
		String[] weids=new String[wfList.size()];
		//循环写入
		for (int i = 0; i < wfList.size(); i++) {
			//遍历福利集合的每一个名称对应的福利编号，循环写入weids数组
			weids[i]=wfList.get(i).getWid()+"";
		}
		oldemp.setWeids(weids);;
		return oldemp;
	}

	public List<Emp> findEmpViewAll(ViewBean vb) {
		// TODO Auto-generated method stub
		return dao.getEmpMapper().findEmpViewAll(vb);
	}

	public Integer findViewMax() {
		// TODO Auto-generated method stub
		return dao.getEmpMapper().findViewMax();
	}

}
