package com.znb.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znb.cms.dao.mapper.CompensationMapper;
import com.znb.cms.model.mapper.Compensation;
import com.znb.cms.service.ISettlementService;

@Service
public class SettlementServiceImpl implements ISettlementService {
	@Autowired
	private CompensationMapper compensationMapper;
	
	@Override
	public List<Compensation> getCompensationList(Compensation compensation) {
		return compensationMapper.getCompensationList(compensation);
	}

	@Override
	public int selectCount(Compensation compensation) {
		return compensationMapper.selectCount(compensation);
	}

	@Override
	public Compensation getCompensation(Integer id) {
		return compensationMapper.selectByPrimaryKey(id);
	}

}
