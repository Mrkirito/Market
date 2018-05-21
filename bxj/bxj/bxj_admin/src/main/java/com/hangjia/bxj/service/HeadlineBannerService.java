package com.hangjia.bxj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.HeadlineBannerMapper;
import com.hangjia.bxj.dao.HomeConfMapper;
import com.hangjia.bxj.model.HeadlineBanner;
import com.hangjia.bxj.model.HomeConf;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.query.app.HeadlineBannerQuery;
@Service
@Transactional(rollbackFor=Throwable.class)
public class HeadlineBannerService{
	
	@Autowired
	private HeadlineBannerMapper headlineBannerMapper;
	
	@Autowired
	private HomeConfMapper homeConfMapper;
	
	@Value("${show_path}")
	private String uploadPath;
	
	@Value("${toutiao.url}")
	private String toutiaoUrl;
	
	/**
	 * banner上移或者下移
	 * @param query
	 * @return
	 */
	@MethodLog(remark = "更新新闻banner排序")
	public int upOrDownHeadlineBanner(HeadlineBannerQuery query){
		int update = 0;
		
		if(null != query.getUpOrDown()){
			int sort = headlineBannerMapper.querySort(query);
			// 向上
			if(query.getUpOrDown() == 1){
				if(sort != 0){
					sort--;
				}
			// 向下
			} else {
				sort++;
			}
			HeadlineBanner headlineBanner = new HeadlineBanner();
			headlineBanner.setSort(sort);
			headlineBanner.setId(query.getId());
			headlineBanner.setModifyAt(new Date());
			update = headlineBannerMapper.updateByPrimaryKeySelective(headlineBanner);
		}
		return update;
	}
	
	/**
	 * 查询banner列表分页
	 * @param query
	 * @return
	 */
	public List<HeadlineBanner> queryPageData(HeadlineBannerQuery query){
		List<HeadlineBanner> banners = new ArrayList<HeadlineBanner>();
		banners = headlineBannerMapper.queryPageData(query);
		if(null != banners && banners.size() > 0){
			for (HeadlineBanner headlineBanner : banners) {
				if(StringUtils.isNotBlank(headlineBanner.getImageUrl())){
					headlineBanner.setFileUrl(uploadPath + headlineBanner.getImageUrl()); 
				}
			}
		}
		return banners;
	}
	
	/**
	 * 更新banner
	 * @param query
	 * @return
	 */
	@MethodLog(remark = "更新新闻banner")
	public int updateBanner(HeadlineBanner record){
		int update = headlineBannerMapper.updateByPrimaryKeySelective(record);
		// 审核通过
		if(update == 1 && null != record && null != record.getIsDisplay() && record.getIsDisplay().intValue() == 1){
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
	 * 新增banner
	 * @param query
	 * @return
	 */
	@MethodLog(remark = "新增新闻banner")
	public int addBanner(HeadlineBanner record){
		int insert = headlineBannerMapper.insert(record);
		// 审核通过
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
