package com.hangjia.bxj.service.banner.executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.model.banner.db.StudyBannerTable;

@Component
public class StudyBannerExecutor extends BasicBannerExecutor {
	
	@Override
	@Value("${banner.study.resourcePath}")
	public void setResourcePath(String resourcePath) {
		super.setResourcePath(resourcePath);
	}
	
	@Override
	@Value("${banner.study.transferPath}")
	public void setTransferPath(String transferPath) {
		super.setTransferPath(transferPath);
	}
	
	@Override
	protected BannerTable getBannerTable() {
		return table;
	}
	
	private static final StudyBannerTable table = new StudyBannerTable();
	
}
