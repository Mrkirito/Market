package com.hangjia.bxj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.PlanVoiceDao;
import com.hangjia.bxj.model.PlanVoice;

@Service
@Transactional(rollbackFor=Throwable.class)
public class PlanVoiceService{
	@Autowired
	private PlanVoiceDao dao;
	
	
	public Object getVoice(Integer fid) {
		Object result=dao.getVoice(fid);
		return result;
	}
	
	public int saveVoice(PlanVoice planVoice) {
		return dao.saveVoice(planVoice);
	}
	
	public void deleteVoice(PlanVoice planVoice) {
		dao.deleteVoice(planVoice);
	}
	
	public void renameVoice(PlanVoice planVoice) {
		dao.renameVoice(planVoice);
	}
	
	
}
