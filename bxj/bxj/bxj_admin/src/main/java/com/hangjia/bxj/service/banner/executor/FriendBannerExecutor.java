package com.hangjia.bxj.service.banner.executor;

import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.model.banner.db.FriendBannerTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FriendBannerExecutor extends BasicBannerExecutor {
	
	@Override
	@Value("${banner.friend.resourcePath}")
	public void setResourcePath(String resourcePath) {
		super.setResourcePath(resourcePath);
	}
	
	@Override
	@Value("${banner.friend.transferPath}")
	public void setTransferPath(String transferPath) {
		super.setTransferPath(transferPath);
	}
	
	@Override
	protected BannerTable getBannerTable() {
		return table;
	}
	
	private static final FriendBannerTable table = new FriendBannerTable();
	
}
