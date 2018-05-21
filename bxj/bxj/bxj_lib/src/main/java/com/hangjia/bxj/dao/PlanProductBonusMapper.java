package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanProductBonus;
import com.hangjia.bxj.vo.PlanProductBonusVo;

public interface PlanProductBonusMapper {
    int deleteByPrimaryKey(Long fid);
    
    int deleteByProductId(Integer pid);
    
    int insert(PlanProductBonus record);

    int insertSelective(PlanProductBonus record);

    PlanProductBonus selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(PlanProductBonus record);

    int updateByPrimaryKeyWithBLOBs(PlanProductBonus record);

    int updateByPrimaryKey(PlanProductBonus record);
	/**
	 * 获取分红信息
	 * @param relSon
	 * @return
	 */
	PlanProductBonus getPlanProductBonus(PlanProductBonusVo vo);
}