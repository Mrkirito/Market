package com.hangjia.bxj.service.banner;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.banner.BannerManagerEntity;
import com.hangjia.bxj.query.banner.BannerManagerQuery;
import com.hangjia.bxj.vo.banner.BannerManagerParameter;

public interface BannerExecutor {

	void saveOrUpdate(BannerManagerParameter params, MultipartFile image);

	Result<List<BannerManagerEntity>> paginationQuery(BannerManagerQuery params);

	void updateDisplayStatus(Integer id, Boolean status);

	BannerManagerEntity findToUpdate(Integer bannerId);

}
