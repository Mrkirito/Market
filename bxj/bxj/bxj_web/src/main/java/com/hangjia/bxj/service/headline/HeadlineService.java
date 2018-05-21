package com.hangjia.bxj.service.headline;

import java.util.List;

import com.hangjia.bxj.model.Headline;
import com.hangjia.bxj.model.HeadlineBanner;
import com.hangjia.bxj.model.HeadlineNews;

/**
 * 头条banner
 * @author yaoy
 *
 */
public interface HeadlineService {

	List<HeadlineBanner> queryBannerList(Headline record);
	
	List<HeadlineNews> queryUpNewsList(Headline record);
	
	List<HeadlineNews> queryNewsList(Headline record);
	
	HeadlineNews selectByPrimaryKey(Long id);
}
