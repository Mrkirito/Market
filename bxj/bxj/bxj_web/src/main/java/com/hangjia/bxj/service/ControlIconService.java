package com.hangjia.bxj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.ControlIconDao;
import com.hangjia.bxj.dao.PlanVoiceDao;
import com.hangjia.bxj.model.PlanVoice;

@Service
@Transactional(rollbackFor=Throwable.class)
public class ControlIconService{
	@Autowired
	private ControlIconDao dao;
	
	
	public Object getIcon() {
		Object result=dao.getIcon();
		return result;
	}
	
}
