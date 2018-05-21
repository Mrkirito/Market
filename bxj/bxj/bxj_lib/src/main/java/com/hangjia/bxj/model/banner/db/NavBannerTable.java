package com.hangjia.bxj.model.banner.db;

/**
 * 首页豆腐块
 * @author K9999
 *
 */
public class NavBannerTable extends HomeBannerTable {

	@Override
	public Object getTypeDiscriminateValue() {
		return 4;
	}
	
	private static final long serialVersionUID = -4989757665875900216L;

}
