package com.hangjia.bxj.model.banner.db;

/**
 * 首页豆腐块
 * @author K9999
 *
 */
public class FriendBannerTable extends HomeBannerTable {

	@Override
	public Object getTypeDiscriminateValue() {
		return 6;
	}
	
	private static final long serialVersionUID = -4989757665875900216L;

}
