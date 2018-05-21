package com.hangjia.bxj.model.banner.db;

public class ChampionVideoCommentBannerTable extends ChampionBannerTable {

	@Override
	public Object getTypeDiscriminateValue() {
		return 2;
	}
	
	private static final long serialVersionUID = -3887341780524103813L;

}
