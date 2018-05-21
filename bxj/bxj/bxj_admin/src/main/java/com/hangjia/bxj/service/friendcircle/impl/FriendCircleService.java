package com.hangjia.bxj.service.friendcircle.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.firendcircle.FriendCircleMapper;
import com.hangjia.bxj.dao.HomeConfMapper;
import com.hangjia.bxj.dao.firendcircle.FriendCircleCategoryMapper;
import com.hangjia.bxj.model.firendcircle.FriendCircle;
import com.hangjia.bxj.model.HomeConf;
import com.hangjia.bxj.model.firendcircle.FriendCircleCategory;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.app.FriendCircleQuery;
import com.hangjia.bxj.service.friendcircle.IfriendCircleService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class FriendCircleService implements IfriendCircleService {

	/**
	 * 朋友圈
	 */
	@Autowired
	private FriendCircleMapper circleMapper;

	/**
	 * 首页配置
	 */
	@Autowired
	private HomeConfMapper homeConfMapper;
	
	/**
	 * 分类 
	 */
	@Autowired
	FriendCircleCategoryMapper categoryMapper;

	@Value("${show_path}")
	private String showPath;

	@Override
	public FriendCircle selectByPK(Long id) {
		if (id == null)
			return null;
		FriendCircle circle = circleMapper.selectByPrimaryKey(id);
		if (circle != null) {
			circle.setFilePath(showPath);
		}
		if(circle.getCategoryId()!=null){
		  FriendCircleCategory cateGory= categoryMapper.selectByPrimaryKey(circle.getCategoryId());
		  circle.setCategoryTitle(cateGory.getTitle()); //分类标签名
		}
		
		return circle;
	}

	@Transactional
	@Override
	@MethodLog(remark = "新增朋友圈神器")
	public int insertFriend(FriendCircle FriendCircle) {
		HomeConf homeconfInput = new HomeConf(); // 输入条件首页
		homeconfInput.setTitle("朋友圈神器");
		HomeConf homeconf = homeConfMapper.getHomeConfOne(homeconfInput);
		if (homeconf != null && homeconf.getPageUrl() != null) {
			String pageUrl = homeconf.getPageUrl().substring(0, homeconf.getPageUrl().indexOf('=') + 1);
			// 页面 路径
			homeconf.setPageUrl(pageUrl += System.currentTimeMillis());
			homeconf.setModifyAt(new Date());
			homeConfMapper.updateByPrimaryKeySelective(homeconf);
		}

		return circleMapper.insertSelective(FriendCircle);
	}

	@Transactional
	@Override
	@MethodLog(remark = "更新朋友圈神器状态")
	public int updateByPKey(FriendCircle FriendCircle) {
		return circleMapper.updateByPK(FriendCircle);
	}

	
	@Override
	public List<FriendCircle> queryPageData(FriendCircleQuery query) {
		List<FriendCircle> list = circleMapper.queryPageData(query);
		for (FriendCircle friendCircle : list) {
			friendCircle.setFilePath(showPath);
		}
		return list;
	}

	@Override
	public int queryCount(FriendCircleQuery query) {
		return circleMapper.queryPageDataCount(query);
	}

	@Transactional
	@Override
	@MethodLog(remark = "更新朋友圈神器图文")
	public int updateImgsByPK(FriendCircle FriendCircle) {
		// TODO Auto-generated method stub
		return circleMapper.updateImgsByPK(FriendCircle);
	}

	@Transactional
	@Override
	@MethodLog(remark = "新增朋友圈神器分类")
	public int insertCategory(FriendCircleCategory FriendCategory) {
		return categoryMapper.insertSelective(FriendCategory);
	}

	@Transactional
	@Override
	@MethodLog(remark = "更新朋友圈神器分类")
	public int updateCategory(FriendCircleCategory FriendCategory) {
		return categoryMapper.updateByPrimaryKeySelective(FriendCategory);
	}

	@Override
	public FriendCircleCategory getCategory(FriendCircleQuery queryCategory) {
		 
	 FriendCircleCategory category=	categoryMapper.selectByPrimaryKey(queryCategory.getId());
		if(category!=null){
			category.setFilePath(showPath+category.getPic());
			return category;
		}
		return null;
	}

	@Override
	public int queryCountCateGory(FriendCircleQuery query) {
		return categoryMapper.queryCountCateGory(query);
	}

	@Override
	public List<FriendCircleCategory> selectTypeList(FriendCircleQuery query) {
		List<FriendCircleCategory> list=categoryMapper.selectList(query);
		for (FriendCircleCategory friendCircleCategory : list) {
			friendCircleCategory.setFilePath(showPath+friendCircleCategory.getPic()); //路径 
		}
		return list;
	}
	

}
