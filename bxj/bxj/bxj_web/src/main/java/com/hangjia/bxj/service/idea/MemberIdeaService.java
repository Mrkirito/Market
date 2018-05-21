package com.hangjia.bxj.service.idea;

public interface MemberIdeaService {

	/**
	 * 保存用户意见。
	 * @param text 意见内容。
	 * @param contact 联系方式。
	 * @param userId
	 */
	void save(String text, String contact, Integer userId);
	
}
