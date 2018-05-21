package com.hangjia.bxj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.HeadlineNewsMapper;
import com.hangjia.bxj.dao.HomeConfMapper;
import com.hangjia.bxj.model.HeadlineNews;
import com.hangjia.bxj.model.HomeConf;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.query.app.HeadlineNewsQuery;
@Service
@Transactional(rollbackFor=Throwable.class)
public class HeadlineNewsService{
	
	@Autowired
	private HeadlineNewsMapper headlineNewsMapper;
	
	@Autowired
	private HomeConfMapper homeConfMapper;
	
	@Value("${show_path}")
	private String uploadPath;
	
	@Value("${toutiao.url}")
	private String toutiaoUrl;
	
	/**
	 * News上移或者下移
	 * @param query
	 * @return
	 */
	@MethodLog(remark = "更新新闻排序")
	public int upOrDownHeadlineNews(HeadlineNewsQuery query){
		int update = 0;
		
		if(null != query.getUpOrDown()){
			try {
				int sort = headlineNewsMapper.querySort(query);
				// 向上
				if(query.getUpOrDown() == 1){
					if(sort != 0){
						sort--;
					}
				// 向下
				} else {
					sort++;
				}
				HeadlineNews headlineNews = new HeadlineNews();
				headlineNews.setSort(sort);
				headlineNews.setId(query.getId());
				headlineNews.setUpdateTime(new Date());
				update = headlineNewsMapper.updateByPrimaryKeySelective(headlineNews);
			} catch (Exception e) {
				return 20;
			}
		}
		return update;
	}
	
	/**
	 * 查询头条列表分页
	 * @param query
	 * @return
	 */
	public List<HeadlineNews> queryPageData(HeadlineNewsQuery query){
		List<HeadlineNews> news = new ArrayList<HeadlineNews>();
		news = headlineNewsMapper.queryPageData(query);
		if(null != news && news.size() > 0){
			for (HeadlineNews headlineNews : news) {
				if(StringUtils.isNotBlank(headlineNews.getImgUrl())){
					headlineNews.setFileUrl(uploadPath + headlineNews.getImgUrl()); 
				}
			}
		}
		return news;
	}
	
	/**
	 * 更新头条新闻
	 * @param query
	 * @return
	 */
	@MethodLog(remark = "更新新闻")
	public int updateNews(HeadlineNews record){
		
		int update = headlineNewsMapper.updateByPrimaryKeySelective(record);
		// 审核通过
		if(update == 1 && null != record && null != record.getAuditStatus() && record.getAuditStatus().intValue() == 2){
			HomeConf homeConf = homeConfMapper.selectByPrimaryKey(AdminConstants.HOME_FID4);
			if(null != homeConf){
				String time = DateUtils.getCurrentLongDate();
				homeConf.setPageUrl(toutiaoUrl + "?v=" + time);
				homeConf.setModifyAt(new Date());
				homeConfMapper.updateByPrimaryKeySelective(homeConf);
			}
		}
		return update;
	}
	
	/**
	 * 新增新闻
	 * @param query
	 * @return
	 */
	@MethodLog(remark = "新增新闻")
	public int addNews(HeadlineNews record){
		
		int insert = headlineNewsMapper.insert(record);
		if(insert == 1){
			HomeConf homeConf = homeConfMapper.selectByPrimaryKey(AdminConstants.HOME_FID4);
			if(null != homeConf){
				String time = DateUtils.getCurrentLongDate();
				homeConf.setPageUrl(toutiaoUrl + "?v=" + time);
				homeConf.setModifyAt(new Date());
				homeConfMapper.updateByPrimaryKeySelective(homeConf);
			}
		}
		return insert;
	}
}
