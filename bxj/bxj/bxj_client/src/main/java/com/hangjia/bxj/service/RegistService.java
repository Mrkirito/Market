package com.hangjia.bxj.service;

import java.util.Map;

/**
 * 用户送券
 */
public interface RegistService {
    /**
     * 新用送券（登录、邀请、注册)
     *
     * @param userId
     * @return
     */
    public Map<String, Object> getRegistVoucher(Long userId, Integer type);

    /**
     * 根据被邀请人id获取邀请人id
     *
     * @param userId
     * @return
     */
    public Integer getInviteUserIdByBeInviteUserId(Long userId);
}
