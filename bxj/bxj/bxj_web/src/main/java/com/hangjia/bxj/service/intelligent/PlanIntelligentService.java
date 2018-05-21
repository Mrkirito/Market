package com.hangjia.bxj.service.intelligent;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.PlanIntelligent;
import com.hangjia.bxj.model.PlanProductCategroy;

public interface PlanIntelligentService {
	List<PlanProductCategroy> getPlanProductCategroyList();	
	Map<String, Object> intelligent(PlanIntelligent planIntelligent);
}
