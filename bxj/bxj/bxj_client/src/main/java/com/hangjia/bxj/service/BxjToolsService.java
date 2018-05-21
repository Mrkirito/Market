package com.hangjia.bxj.service;

/**
 * BXJ工具service
 * 朋友圈
 */
public interface BxjToolsService {
    /**
     * 朋友圈首页数据
     * @param head
     * @param body
     * @return
     */
    Object getFirendCircleIndex(String head, String body);

    Object getFirendCircleIndex_v32(String head, String body);

    Object getFirendCircleIndex_v32_1(String head, String body);

    /**
     * 更新分享数
     * @param head
     * @param body
     * @return
     */
    Object updateShareCount(String head, String body);

    /**
     * 获取轮播图片数组
     * @param head
     * @param body
     * @return
     */
    Object bannerPics(String head, String body);

    /**
     * 获取人气排行所有分类
     * @param head
     * @param body
     * @return
     */
    Object rankCategory(String head, String body);

    /**
     * 获取不同分类的排行数据
     * @param head
     * @param body
     * @return
     */
    Object rankCategoryByQuery(String head, String body);

    Object rankCategoryByQueryByTime(String head, String body);
    /**
     * 更新喜欢数
     * @param head
     * @param body
     * @return
     */
    Object updateLikeCount(String head, String body);
}
