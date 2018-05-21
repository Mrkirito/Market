package com.hangjia.bxj.model.banner.db;

/**
 * 首页 banner。
 * @author K9999
 *
 */
public class HomeBannerTable extends BannerTable {
	
	@Override
	public String getTableName() {
		return "bxj_home_conf";
	}

	@Override
	public String getTypeDiscriminator() {
		return "type";
	}
	
	@Override
	public Object getTypeDiscriminateValue() {
		return 3;
	}
	
	private static final long serialVersionUID = -3293065628991344167L;

}
