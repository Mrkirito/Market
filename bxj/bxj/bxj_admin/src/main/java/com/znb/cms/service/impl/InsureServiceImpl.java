package com.znb.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znb.cms.dao.mapper.InsureMapper;
import com.znb.cms.model.mapper.Insure;
import com.znb.cms.service.IInsureService;

@Service
public class InsureServiceImpl implements IInsureService {
	
	@Autowired
	private InsureMapper insureMapper;
	
	@Override
	public void updateInsureMessage(Integer id,String message) {
		Insure insure = new Insure();
		insure.setId(id);
		insure.setMessage(message);
		insureMapper.updateByPrimaryKeySelective(insure);
	}

}
