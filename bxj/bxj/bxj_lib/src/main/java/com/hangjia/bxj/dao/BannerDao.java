package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.Banner;

public interface BannerDao {

	/**
	 * 查询有效 Banner。
	 * @return
	 */
	List<Banner> list();
	
}
