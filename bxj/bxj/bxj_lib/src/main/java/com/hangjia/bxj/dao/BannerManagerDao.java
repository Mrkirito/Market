package com.hangjia.bxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hangjia.bxj.model.banner.BannerManagerEntity;
import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.query.banner.BannerManagerQuery;
import com.hangjia.bxj.vo.banner.BannerManagerParameter;
import com.hangjia.bxj.vo.banner.BannerManagerUpdateDisplayParameter;

public interface BannerManagerDao {

	List<BannerManagerEntity> list(@Param("params") BannerManagerQuery params, @Param("table") BannerTable table);

	int count(@Param("params") BannerManagerQuery params, @Param("table") BannerTable table);

	/**
	 * 
	 * 更新显示状态（切换是否显示）
	 * 
	 * @param bannerManagerUpdateStatusParameter 参数，主要包含 id 和修改的值。
	 * @param table banner 表信息。
	 * 
	 */
	int updateDisplayStatus(@Param("params") BannerManagerUpdateDisplayParameter bannerManagerUpdateStatusParameter, @Param("table") BannerTable table);

	void save(@Param("params") BannerManagerParameter params, @Param("table") BannerTable table);

	int update(@Param("params") BannerManagerParameter params, @Param("table") BannerTable table);

	BannerManagerEntity findToUpdate(@Param("table") BannerTable table, @Param("id") Integer id);
	
}
