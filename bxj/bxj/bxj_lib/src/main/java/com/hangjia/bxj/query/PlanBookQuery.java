package com.hangjia.bxj.query;
import java.util.List;

import com.hangjia.bxj.common.BaseCommonQuery;
public class PlanBookQuery extends BaseCommonQuery {
	private static final long serialVersionUID = 1143514087159007206L;
	private Long userId;
	private List<Long> planIds;
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getPlanIds() {
		return planIds;
	}

	public void setPlanIds(List<Long> planIds) {
		this.planIds = planIds;
	}
	
}
