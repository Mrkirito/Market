package com.hangjia.bxj.service.headline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.HeadlineBannerMapper;
import com.hangjia.bxj.dao.HeadlineNewsMapper;
import com.hangjia.bxj.model.Headline;
import com.hangjia.bxj.model.HeadlineBanner;
import com.hangjia.bxj.model.HeadlineNews;
import com.hangjia.bxj.util.DateUtil;

/** 
* @author  作者 : yaoy
* @date 2016年5月11日 下午3:03:49 
* @version 1.0 
*/
@Service
@Transactional(readOnly=true, rollbackFor=Throwable.class)
public class HeadlineServiceImpl implements HeadlineService {

	@Autowired
	private HeadlineBannerMapper headlineBannerMapper;
	
	@Autowired
	private HeadlineNewsMapper headlineNewsMapper;
	
	@Value("${static_path}")
	private String uploadPath;
	
	/**
	 * 查询bannerList
	 */
	@Override
	public List<HeadlineBanner> queryBannerList(Headline record) {
		List<HeadlineBanner> list = new ArrayList<HeadlineBanner>();
		list = headlineBannerMapper.queryBannerList(record);
		if(null != list && list.size() > 0){
			for (HeadlineBanner headlineBanner : list) {
				headlineBanner.setFileUrl(uploadPath + headlineBanner.getImageUrl()); 
			}
		}
		return list;
	}
	/**
	 * 查询普通newsList(分页)
	 */
	@Override
	public List<HeadlineNews> queryNewsList(Headline record) {
		List<HeadlineNews> list = new ArrayList<HeadlineNews>();
		record.setNewsType(1);
		list = headlineNewsMapper.queryNewsList(record);
		if(null != list && list.size() > 0){
			for (HeadlineNews headlineNews : list) {
				if(StringUtils.isNotBlank(headlineNews.getImgUrl())){
					headlineNews.setFileUrl(uploadPath + headlineNews.getImgUrl());
				}
				String timeContext = DateUtil.getTimeContext(headlineNews.getOnlineTime(), new Date());
				headlineNews.setTimeContext(timeContext);
			}
		}
		return list;
	}
	
	/**
	 * 查询置顶newsList(分页)
	 */
	@Override
	public List<HeadlineNews> queryUpNewsList(Headline record) {
		List<HeadlineNews> list = new ArrayList<HeadlineNews>();
		record.setNewsType(2);
		list = headlineNewsMapper.queryNewsList(record);
		if(null != list && list.size() > 0){
			for (HeadlineNews headlineNews : list) {
				if(StringUtils.isNotBlank(headlineNews.getImgUrl())){
					headlineNews.setFileUrl(uploadPath + headlineNews.getImgUrl());
				}
				String timeContext = DateUtil.getTimeContext(headlineNews.getOnlineTime(), new Date());
				headlineNews.setTimeContext(timeContext);
			}
		}
		return list;
	}
	
	/**
	 * 查询新闻详情
	 */
	@Override
	public HeadlineNews selectByPrimaryKey(Long id) {
		HeadlineNews news = headlineNewsMapper.selectByPrimaryKey(id);
		if(null != news && StringUtils.isNotBlank(news.getImgUrl())){
			news.setFileUrl(uploadPath + news.getImgUrl()); 
		}
		return news;
	}
	
}
