package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.MemberIdea;
import com.hangjia.bxj.query.ProductQuery;

public interface MemberIdeaDao {

	/**
	 * 保存客户意见
	 * @param idea
	 */
	void save(MemberIdea idea);
	
	public int getIdeaCount(ProductQuery query);
	
	public List<MemberIdea> getIdeaData(ProductQuery query);
	/**
	 * 
	 * @param idea
	 * @return
	 */
	int updateMember(MemberIdea idea);
}
