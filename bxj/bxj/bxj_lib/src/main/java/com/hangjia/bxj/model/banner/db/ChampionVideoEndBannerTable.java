package com.hangjia.bxj.model.banner.db;

public class ChampionVideoEndBannerTable extends ChampionBannerTable {

	@Override
	public Object getTypeDiscriminateValue() {
		return 3;
	}
	
	private static final long serialVersionUID = -1967964961455836291L;

}
