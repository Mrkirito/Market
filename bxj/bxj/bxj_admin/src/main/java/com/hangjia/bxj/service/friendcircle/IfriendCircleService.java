package com.hangjia.bxj.service.friendcircle;

import java.util.List;

import com.hangjia.bxj.model.firendcircle.FriendCircle;
import com.hangjia.bxj.model.firendcircle.FriendCircleCategory;
import com.hangjia.bxj.query.app.FriendCircleQuery;

public interface IfriendCircleService {

	/**
	 * 查询朋友圈详情
	 * 
	 * @param id
	 * @return
	 */
	FriendCircle selectByPK(Long id);

	/**
	 * 新增
	 * 
	 * @param FriendCircle
	 * @return
	 */
	int insertFriend(FriendCircle FriendCircle);

	/**
	 * 修改
	 * 
	 * @param FriendCircle
	 * @return
	 */
	int updateByPKey(FriendCircle FriendCircle);

	/**
	 * 查询 后台 展示列表
	 * 
	 * @param query
	 * @return
	 */
	List<FriendCircle> queryPageData(FriendCircleQuery query);

	/**
	 * 查询总数
	 * 
	 * @param query
	 * @return
	 */
	int queryCount(FriendCircleQuery query);
	
	/*
	 * 修改 图文 图片路径
	 */
	int updateImgsByPK(FriendCircle FriendCircle);
	
	/**
	 * 新增分类
	 * @param FriendCategory
	 * @return
	 */
	int insertCategory(FriendCircleCategory FriendCategory);
	
	/**
	 * 修改 分类
	 * @param FriendCategory
	 * @return
	 */
	int updateCategory(FriendCircleCategory FriendCategory);
	
	/**
	 * 查询分类
	 * @param FriendCategory
	 * @return
	 */
	FriendCircleCategory getCategory(FriendCircleQuery queryCategory);
	
	/**
	 * 查询分类
	 * @param query
	 * @return
	 */
	List<FriendCircleCategory> selectTypeList(FriendCircleQuery query);
	
	/**
	 * 总条数 
	 * @param query
	 * @return
	 */
	int queryCountCateGory(FriendCircleQuery query);
}
