package com.hangjia.bxj.service.banner.executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.model.banner.db.NavBannerTable;

@Component
public class NavBannerExecutor extends BasicBannerExecutor {
	
	@Override
	@Value("${banner.nav.resourcePath}")
	public void setResourcePath(String resourcePath) {
		super.setResourcePath(resourcePath);
	}
	
	@Override
	@Value("${banner.nav.transferPath}")
	public void setTransferPath(String transferPath) {
		super.setTransferPath(transferPath);
	}
	
	@Override
	protected BannerTable getBannerTable() {
		return table;
	}
	
	private static final NavBannerTable table = new NavBannerTable();
	
}
