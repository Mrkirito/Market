package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.MyJunior;

/**
 * 我邀请的人
 * @author K9999
 *
 */
public interface MyJuniorsDao {

	int selectMyJuniorCount(Integer userId);

	List<MyJunior> listMyJuniors(Integer userId);

	/**
	 * 保存推荐关系。
	 * @param userId 推荐人（上级）ID。
	 * @param mobile 被推荐人手机号。
	 */
	void saveMyJunior(Integer userId, String mobile);
	
	/**
	 * 激活推荐关系。
	 * 在用户成功登陆后，调用此方法激活。
	 * @param mobile
	 */
	void active(String mobile);

}
