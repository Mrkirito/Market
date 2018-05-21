package com.hangjia.champion.service;

import com.hangjia.bxj.mvc.AjaxResult;

import java.util.Map;

/**
 * @ClassName: ChampionInviteService
 * @Description: 冠军说邀请业务接口
 * @author: bei.zhang
 * @date: 2016年4月13日 下午1:51:15
 */
public interface ChampionInviteService {

	/**
	 * @Title: inviteUserSuccess
	 * @Description: 邀请好友成功注册
	 * @param inviteUserId
	 * @param beInviteUserId
	 * @return
	 */
	Map<String, String> inviteUserSuccess(Map<String, Object> map, Long inviteUserId, Long voucherId);

}
