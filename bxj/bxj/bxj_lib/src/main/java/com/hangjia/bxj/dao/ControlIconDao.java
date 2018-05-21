package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.Icon;
import com.hangjia.bxj.model.PlanVoice;

public interface ControlIconDao {
	/**
	 * 查询Icon
	 * @param fid
	 * @return
	 */
	List<Icon> getIcon();
}
