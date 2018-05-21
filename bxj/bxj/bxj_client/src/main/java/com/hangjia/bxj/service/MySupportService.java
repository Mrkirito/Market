package com.hangjia.bxj.service;

/**
 * 我的业务支持接口
 * Created by bei.zhang on 2016/5/23.
 */
public interface MySupportService {

    /**
     * 根据用户ID获取统计信息
     *
     * @return
     */
    Object getStatInfoByUserId(String head, String body);

    /**
     * 获取我的配置信息
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyConfInfo(String head, String body);

    /**
     * 获取我的资料信息
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyInformation(String head, String body);

    /**
     * 获取我的发布信息
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyPublish(String head, String body);

    /**
     * 获取我的关注信息
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyFocus(String head, String body);

    /**
     * 获取我的粉丝
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyFans(String head, String body);

    /**
     * 获取我的声音
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyVoice(String head, String body);

    /**
     * 获取我的打赏
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyEnjoyPlaying(String head, String body);

    /**
     * 获取我的门票
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyEntranceTicket(String head, String body);

    /**
     * 获取我的收益
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyIncome(String head, String body);

    /**
     * 获取我的奖品
     *
     * @param head
     * @param body
     * @return
     */
    Object getMyPrize(String head, String body);

    /**
     * 获取公司和职位
     *
     * @param head
     * @param body
     * @return
     */
    Object getConpanyAndPosition(String head, String body);

    /**
     * 获取从业年限
     *
     * @param head
     * @param body
     * @return
     */
    Object getJobYears(String head, String body);

    /**
     * 修改我的资料
     *
     * @param head
     * @param body
     * @return
     */
    Object saveInformation(String head, String body);

    /**
     * 删除我的声音
     *
     * @param head
     * @param body
     * @return
     */
    Object deleteMyVoice(String head, String body);

    /**
     * 保存讲师封面图片
     *
     * @param head
     * @param body
     * @param bytes
     * @return
     */
    Object saveUserCoverImg(String head, String body, byte[] bytes);

    /**
     * 保存用户语音文件
     *
     * @param head
     * @param body
     * @param bytes1
     * @param bytes2
     * @return
     */
    Object saveUserVoice(String head, String body, byte[] bytes1, byte[] bytes2);

    /**
     * 获取年薪
     *
     * @param head
     * @param body
     * @return
     */
    Object getYearsSalarys(String head, String body);

    /**
     * 保存实名信息
     *
     * @param head
     * @param body
     * @param bytes1
     * @param bytes2
     * @return
     */
    Object saveRealInfo(String head, String body, byte[] bytes1, byte[] bytes2);

    /**
     * 获取用户实名信息
     *
     * @param head
     * @param body
     * @return
     */
    Object getRealInfoByUserId(String head, String body);
}
