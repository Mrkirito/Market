package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.Compensation;

public interface CompensationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Compensation record);

    int insertSelective(Compensation record);

    Compensation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Compensation record);

    int updateByPrimaryKey(Compensation record);
    
    List<Compensation> getCompensationList(Compensation compensation);
	
	int selectCount(Compensation compensation);
}