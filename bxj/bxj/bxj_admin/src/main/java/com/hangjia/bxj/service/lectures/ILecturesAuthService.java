package com.hangjia.bxj.service.lectures;

import java.util.List;

import com.hangjia.bxj.ucenter.model.UserCard;
import com.hangjia.bxj.ucenter.query.UserCardQuery;

/**
 * 讲师认证相关
 * @author: yaoy
 * @date: 2016-07-12
 */
public interface ILecturesAuthService {
	
	/**
	 * 查询申请认证讲师总数
	 * @param query
	 * @return
	 */
	int queryUserCardDataCount(UserCardQuery query);
	
	/**
	 * 查询申请认证讲师列表
	 * @param query
	 * @return
	 */
	List<UserCard> queryUserCardDataPage(UserCardQuery query);
	
	/**
	 * 审核通过
	 * @param userCard
	 * @return
	 */
	int passAuth(UserCard userCard);
	
	/**
	 * 审核不通过
	 * @param userCard
	 * @return
	 */
	int failAuth(UserCard userCard);
}
