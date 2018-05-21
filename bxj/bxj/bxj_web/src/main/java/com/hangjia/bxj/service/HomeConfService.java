package com.hangjia.bxj.service;

import com.hangjia.bxj.dao.BxjInitAdInfoMapper;
import com.hangjia.bxj.dao.HomeConfMapper;
import com.hangjia.bxj.model.BxjInitAdInfo;
import com.hangjia.bxj.model.HomeConf;
import com.hangjia.bxj.util.Constants;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.ibaoxianjia.study.service.StudySupportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Throwable.class)
public class HomeConfService {
	@Autowired
	private HomeConfMapper dao;
	@Autowired
	private BxjInitAdInfoMapper bxjInitAdInfoMapper;
	@Autowired
	StudySupportService studySupportService;
	@Autowired
	private ControlAppStoreService appStoreService;

	public Object getHomeConf(double version, Integer androidType, BigDecimal h, BigDecimal w, String os) {
		Map<String, Object> mapPara = new HashMap<String, Object>();
		if(version > 0) mapPara.put("version", version);
		List<HomeConf> list = dao.getHomeConf(mapPara);
		Map<String, List<HomeConf>> map = new HashMap<String, List<HomeConf>>();
		int type = 0;
		List<HomeConf> l = new ArrayList<HomeConf>();
		for(HomeConf hc : list) {
			if(type == 0) {
				l.add(hc);
				type = hc.getType();
				continue;
			}
			if(type == hc.getType()) l.add(hc);
			else {
				map.put("k_" + type, l);
				type = hc.getType();
				l = new ArrayList<HomeConf>();
				l.add(hc);
			}
		}
		map.put("k_" + type, l);

		/** 学习时间戳（红点） **/
		Long time = studySupportService.getMaxPublishTime();
		l = new ArrayList<HomeConf>();
		l.add(new HomeConf(time));
		map.put("k_9", l);
		return map;
	}

	public List<HomeConf> changeUrl (List<HomeConf>l, BigDecimal h, BigDecimal w) {
		List<HomeConf> list = new ArrayList<HomeConf>();
		for(HomeConf obj : l) {
			try {
				if(obj.getType()==3) {
					/**
					 * 几种规格对应
					 * 1.78--1:1.778、1:1.779
					 * 1.644--1:1.644
					 * 1.67--1:1.678、1:1.667、1:1.661 默认规格
					 */
					BigDecimal proportion = h.divide(w, 3, BigDecimal.ROUND_HALF_UP);
					if (proportion.compareTo(new BigDecimal(1.7)) > 0)
						obj.setImageUrl(obj.getImageUrl().substring(0, obj.getImageUrl().length() - 4) + "_"+Constants.PICTURE_MODEL_SMALL+"." + obj.getImageUrl().substring(obj.getImageUrl().length() - 3, obj.getImageUrl().length()));
					else if (proportion.compareTo(new BigDecimal(1.65)) < 0)
						obj.setImageUrl(obj.getImageUrl().substring(0, obj.getImageUrl().length() - 4) + "_"+Constants.PICTURE_MODEL_MIDDLE+"." + obj.getImageUrl().substring(obj.getImageUrl().length() - 3, obj.getImageUrl().length()));
					else
						obj.setImageUrl(obj.getImageUrl().substring(0, obj.getImageUrl().length() - 4) + "_"+Constants.PICTURE_MODEL_BIG+"." + obj.getImageUrl().substring(obj.getImageUrl().length() - 3, obj.getImageUrl().length()));
				}
			} catch (Exception e) {
				//obj.setImageUrl(obj.getImageUrl().substring(0, obj.getImageUrl().length() - 4) + "_"+Constants.PICTURE_MODEL_BIG+"." + obj.getImageUrl().substring(obj.getImageUrl().length() - 3, obj.getImageUrl().length()));
			}
			list.add(obj);
		}
		return list;
	}

	public Object getInitAd() {
		BxjInitAdInfo bxjInitAdInfo = bxjInitAdInfoMapper.selectByPrimaryKey(1l);
		String defaultTheme = appStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_THEME);
		String defaultBannerPlay = appStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_BANNER_PLAY);
		String bxjPay = appStoreService.getResponseParaByKey(RedisKeyConstants.BXJ_PAY);
		bxjInitAdInfo.setBxjPay(bxjPay);
		bxjInitAdInfo.setDefaultTheme(StringUtils.isBlank(defaultTheme) ? 0 : Integer.valueOf(defaultTheme));
		bxjInitAdInfo.setDefaultBannerPlay(StringUtils.isBlank(defaultBannerPlay) ? 0 : Integer.valueOf(defaultBannerPlay));

		return bxjInitAdInfo;
	}
}
