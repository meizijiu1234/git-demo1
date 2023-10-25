package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;
import com.po.Welfare;

@Service("IWelfareMapper")
public interface IWelfareMapper {
	public List<Welfare> findAll();
}
