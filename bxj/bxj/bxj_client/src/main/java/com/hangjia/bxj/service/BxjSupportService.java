package com.hangjia.bxj.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baobao.framework.page.Paginator;

/**
 * 用户送券
 */
public interface BxjSupportService {
    /**
     * 查询用户邀请已注册并下载app成功用户
     */
    public Integer queryInviteUser(Long userId);

    /**
     * 查询用户邀请排行榜
     */
    public Paginator queryInviteSortList(Paginator paginator);

    /**
     * 根据活动日期查询用户邀请人数
     *
     * @param userId
     * @param activityCode
     * @param startTime
     * @param endTime
     * @return
     */
    public Integer queryInviteUserByActivityDate(Long userId, String activityCode, Date startTime, Date endTime);


    /**
     * 查询我的消息
     *
     * @param head
     * @param body
     * @return
     */
    Object getMySysMessage(String head, String body);

    /**
     * 查询所有险种分类
     *
     * @return
     */
    Object getProductCategroys(String head, String body);

    /**
     * 公用发送通知
     *
     * @param userIds  收的人ids
     * @param sendId   发送人id
     * @param moduleId 通知模板id
     * @param msgType  消息类型
     * @param args     消息参数
     * @return
     */
    int sendMessage(List<Long> userIds, Long sendId, Integer moduleId, Integer msgType, Object[] args);

    /**
     * 自动推送消息
     *
     * @param map
     */
    void autoPushMessage(Map<String, Object> map);

    /**
     * 验证ip是否存在所属省份
     *
     * @param ip
     * @param province
     * @return
     */
    boolean validateIpProvince(String ip, String province);

    /**
     * 验证APP版本是否更新
     *
     * @return
     */
    Object validateAppVersion(String head, String body);

    /**
     * 获取ip地址信息
     *
     * @return
     */
    Object getIpAddressInfo(String head, String body);

    /**
     * 获取banner信息
     *
     * @return
     */
    Object getBannerInfos(String head, String body);
    /**
     * 获取计划书列表信息
     * @param head
     * @param body
     * @return
     */
    Object getPlanBookInfos(String head, String body);
    /**
     * 我的计划书列表
     * @param head
     * @param body
     * @return
     */
    Object getMyPlanBookInfos(String head, String body);
    /**
     * 我的计划书 删除
     * @return
     */
    Object deleteMyPlanBookInfos(String head, String body);

    /**
     * 获取用户所有视频券
     *
     * @param userId
     * @return
     */
    int getMyVideoVoucherTotal(Long userId);
}
