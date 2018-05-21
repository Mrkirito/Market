package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.Suggestion;

public interface SuggestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    Suggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
    
	List<Suggestion> getSuggestions(Suggestion suggestion);

	int getCount(Suggestion suggestion);
}