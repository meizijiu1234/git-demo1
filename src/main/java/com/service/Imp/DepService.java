package com.service.Imp;

import com.po.Dep;
import com.service.IDepService;
import com.util.DaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("DepService")
@Transactional
public class DepService implements IDepService {
	@Resource(name="DaoService")
	private DaoService dao;
	
	public DaoService getDao() {
		return dao;
	}

	public void setDao(DaoService dao) {
		this.dao = dao;
	}

	public List<Dep> findAll() {
		// TODO Auto-generated method stub
		return dao.getDepMapper().findAll();
	}

}
