package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.service.*;

@Service("BizService")
public class BizService {
	@Resource(name="DepService")
	private IDepService depService;
	@Resource(name="EmpService")
	private IEmpService empService;
	@Resource(name="WelfareService")
	private IWelfareService welfareService;
	public IDepService getDepService() {
		return depService;
	}
	public void setDepService(IDepService depService) {
		this.depService = depService;
	}
	public IEmpService getEmpService() {
		return empService;
	}
	public void setEmpService(IEmpService empService) {
		this.empService = empService;
	}
	public IWelfareService getWelfareService() {
		return welfareService;
	}
	public void setWelfareService(IWelfareService welfareService) {
		this.welfareService = welfareService;
	}
	
}
