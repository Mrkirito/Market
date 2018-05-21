package com.hangjia.bxj.service.banner.executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.model.banner.db.HomeBannerTable;

@Component
public class HomeBannerExecutor extends BasicBannerExecutor {
	
	@Override
	@Value("${banner.home.resourcePath}")
	public void setResourcePath(String resourcePath) {
		super.setResourcePath(resourcePath);
	}
	
	@Override
	@Value("${banner.home.transferPath}")
	public void setTransferPath(String transferPath) {
		super.setTransferPath(transferPath);
	}
	
	@Override
	protected BannerTable getBannerTable() {
		return table;
	}
	
	private static final HomeBannerTable table = new HomeBannerTable();
	
}
