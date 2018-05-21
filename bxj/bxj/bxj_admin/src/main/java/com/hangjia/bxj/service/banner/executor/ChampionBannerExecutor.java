package com.hangjia.bxj.service.banner.executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.model.banner.db.ChampionBannerTable;

@Component
public class ChampionBannerExecutor extends BasicBannerExecutor {
	
	@Override
	@Value("${banner.champion.resourcePath}")
	public void setResourcePath(String resourcePath) {
		super.setResourcePath(resourcePath);
	}
	
	@Override
	@Value("${banner.champion.transferPath}")
	public void setTransferPath(String transferPath) {
		super.setTransferPath(transferPath);
	}
	
	@Override
	protected BannerTable getBannerTable() {
		return table;
	}
	
	private static final ChampionBannerTable table = new ChampionBannerTable();
	
}
