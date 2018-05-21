package com.znb.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znb.cms.dao.mapper.IsurantMapper;
import com.znb.cms.model.mapper.Isurant;
import com.znb.cms.service.IIsurantService;


@Service
public class IsurantServiceImpl implements IIsurantService {
	
	@Autowired
	private IsurantMapper isurantMapper;
	
	@Override
	public void updateIsurantMessage(Integer id,String message) {
		Isurant isurant = new Isurant();
		isurant.setId(id);
		isurant.setMessage(message);
		isurantMapper.updateByPrimaryKeySelective(isurant);
	}

}
