package com.hangjia.bxj.ucenter.dao;

import java.util.List;

import com.hangjia.bxj.ucenter.model.UserCard;
import com.hangjia.bxj.ucenter.query.UserCardQuery;

public interface UserCardMapper {
	
	/**
	 * 查询申请讲师认证总数
	 * @param query
	 * @return
	 */
	int queryUserCardDataCount(UserCardQuery query);
	
	/**
	 * 查询申请讲师认证总数列表
	 * @param query
	 * @return
	 */
	List<UserCard> queryUserCardDataPage(UserCardQuery query);
	
	/**
	 * 更新认证审核状态
	 * @param userCard
	 * @return
	 */
	int updateUserCardStatus(UserCard userCard);
}
