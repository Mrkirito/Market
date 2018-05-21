package com.hangjia.bxj.model.banner.db;

public class ChampionBannerTable extends BannerTable {
	
	@Override
	public String getTableName() {
		return "champion_banner";
	}

	@Override
	public String getTypeDiscriminator() {
		return "position";
	}
	
	@Override
	public Object getTypeDiscriminateValue() {
		return 1;
	}
	
	private static final long serialVersionUID = 7833610973893243422L;

}
