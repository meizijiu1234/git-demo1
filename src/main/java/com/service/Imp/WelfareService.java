package com.service.Imp;

import com.po.Welfare;
import com.service.IWelfareService;
import com.util.DaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("WelfareService")
@Transactional
public class WelfareService implements IWelfareService {
	@Resource(name="DaoService")
	private DaoService dao;
	
	public DaoService getDao() {
		return dao;
	}

	public void setDao(DaoService dao) {
		this.dao = dao;
	}

	public List<Welfare> findAll() {
		// TODO Auto-generated method stub
		return dao.getWelfareMapper().findAll();
	}

}
