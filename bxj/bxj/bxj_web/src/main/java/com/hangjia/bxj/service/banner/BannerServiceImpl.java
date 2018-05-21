package com.hangjia.bxj.service.banner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.BannerDao;
import com.hangjia.bxj.model.Banner;

@Service
@Transactional(readOnly=true)
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private BannerDao bannerDao;

	@Override
	public List<Banner> list() {
		return bannerDao.list();
	}

}
