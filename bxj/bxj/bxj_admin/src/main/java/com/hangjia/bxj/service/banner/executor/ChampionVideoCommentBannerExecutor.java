package com.hangjia.bxj.service.banner.executor;

import org.springframework.stereotype.Component;

import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.model.banner.db.ChampionVideoCommentBannerTable;

/**
 * 冠军说视频评论位置的广告位banner。
 * @author K9999
 *
 */
@Component
public class ChampionVideoCommentBannerExecutor extends ChampionBannerExecutor {

	@Override
	protected BannerTable getBannerTable() {
		return table;
	}
	
	private static final BannerTable table = new ChampionVideoCommentBannerTable();
	
}
