package com.hangjia.bxj.vo.banner;

import com.hangjia.bxj.model.banner.BannerManagerEntity;

public class BannerManagerParameter extends BannerManagerEntity {
	
	/**
	 * Banner 显示类型，即 Banner 显示的位置。
	 * <p>
	 * 至 3.0.1 版，分为4类：
	 * 1、首页Banner。
	 * 2、首页大图标按钮。
	 * 3、学习板块Banner。
	 * 4、冠军说Banner。
	 * </p>
	 */
	private String showType;
	
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}
