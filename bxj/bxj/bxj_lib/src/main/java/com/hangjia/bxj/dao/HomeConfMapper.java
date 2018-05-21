package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.HomeConf;

import java.util.List;
import java.util.Map;

public interface HomeConfMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(HomeConf record);

    int insertSelective(HomeConf record);

    HomeConf selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(HomeConf record);

    int updateByPrimaryKey(HomeConf record);

    List<HomeConf> getHomeConf(Map<String, Object> map);

    List<HomeConf> getHomeConfByType(Map<String, Object> map);

    /**
     * 获取朋友圈banner
     * @param map
     * @return
     */
    List<HomeConf> getFriendBanner(Map<String, Object> map);

    /**
     * 后台
     * 获取首页 配置 根据title
     * @param record
     * @return
     */
    HomeConf getHomeConfOne(HomeConf record);
}