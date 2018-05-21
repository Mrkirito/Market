package com.hangjia.bxj.service.intelligent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangjia.bxj.dao.PlanIntelligentMapper;
import com.hangjia.bxj.dao.PlanProductCategroyMapper;
import com.hangjia.bxj.model.PlanIntelligent;
import com.hangjia.bxj.model.PlanProductCategroy;

@Service
public class PlanIntelligentServiceImpl implements PlanIntelligentService{
	@Autowired
	private PlanIntelligentMapper planIntelligentMapper;

	@Autowired
	private PlanProductCategroyMapper planProductCategroyMapper;
	
	
	@Override
	public List<PlanProductCategroy> getPlanProductCategroyList() {
		return planProductCategroyMapper.getAllProductCategroys();
	}


	@Override
	public Map<String, Object> intelligent(PlanIntelligent planIntelligent) {
		Map<String, Object> map=new HashMap<String, Object>();
		
		return map;
	}
	
	
	
}
