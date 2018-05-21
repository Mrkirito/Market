package com.hangjia.bxj.service.banner;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.model.banner.BannerManagerEntity;
import com.hangjia.bxj.query.banner.BannerManagerQuery;
import com.hangjia.bxj.vo.banner.BannerManagerParameter;

@Service
@Transactional
public class BannerManagerService {
	
	@Resource(name="bannerExecutorMap")
	private Map<String, BannerExecutor> executors;
	
	@Transactional(readOnly=true)
	public Object paginationQuery(BannerManagerQuery params) {
		BannerExecutor executor = executors.get(params.getShowType());
		return executor.paginationQuery(params);
	}
	
	public void saveOrUpdate(BannerManagerParameter params, MultipartFile image) {
		BannerExecutor executor = executors.get(params.getShowType());
		executor.saveOrUpdate(params, image);
	}
	
	public void updateDisplayStatus(String showType, Integer id, Boolean status) {
		BannerExecutor executor = executors.get(showType);
		executor.updateDisplayStatus(id, status);
	}

	public BannerManagerEntity findBannerToUpdate(String showType, Integer bannerId) {
		BannerExecutor executor = executors.get(showType);
		return executor.findToUpdate(bannerId);
	}
	
}
