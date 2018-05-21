package com.hangjia.bxj.dao.firendcircle;

import java.util.List;

import com.hangjia.bxj.model.firendcircle.FriendCircleCategory;
import com.hangjia.bxj.query.app.FriendCircleQuery;

/**
 * 分类 朋友圈
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年8月19日 下午2:19:35
 */
public interface FriendCircleCategoryMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(FriendCircleCategory record);

    int insertSelective(FriendCircleCategory record);

    FriendCircleCategory selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(FriendCircleCategory record);

    int updateByPrimaryKey(FriendCircleCategory record);
    
    /**
     * 后台分类查询 
     * @return
     */
    List<FriendCircleCategory> selectList(FriendCircleQuery circleQuery);

    /**查询有效的分类列表*/
    List<FriendCircleCategory> selectCategoryList(FriendCircleQuery circleQuery);
    
    /**
     * 分类总数
     * @param query
     * @return
     */
    int queryCountCateGory(FriendCircleQuery query);
}