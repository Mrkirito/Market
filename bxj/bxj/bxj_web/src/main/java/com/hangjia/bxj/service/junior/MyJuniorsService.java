package com.hangjia.bxj.service.junior;

import java.util.List;

import com.hangjia.bxj.model.MyJunior;

/**
 * 我的晚辈。
 * 我邀请的人，他们注册后即是我的晚辈。
 * 
 * @author K9999
 *
 */
public interface MyJuniorsService {
	
	/**
	 * 查询指定用户的晚辈（邀请关系）数量。
	 * @param userId
	 * @return
	 */
	int showMyJuniorCount(Integer userId);

	/**
	 * 查询指定用户的晚辈（邀请关系）列表。
	 * @param userId
	 * @return
	 */
	List<MyJunior> showMyJuniors(Integer userId);
	
	/**
	 * 激活推荐关系。
	 * 在用户成功登陆后，调用此方法激活。
	 * @param mobile
	 */
	void active(String mobile);

	/**
	 * 注册推荐人和被推荐人的绑定关系。
	 * @param shareId 推荐人（上级）ID。
	 * @param mobile 被推荐人手机号。
	 */
	void regist(Integer shareId, String mobile);

}
