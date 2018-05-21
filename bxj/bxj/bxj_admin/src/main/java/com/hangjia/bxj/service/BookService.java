package com.hangjia.bxj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.BannerDao;
import com.hangjia.bxj.dao.ControlIconDao;
import com.hangjia.bxj.model.Banner;
import com.hangjia.bxj.model.Icon;

@Service
@Transactional(rollbackFor=Throwable.class)
public class BookService {
	@Autowired
	private BannerDao bannerDao;
	@Autowired
	private ControlIconDao dao;
	
	
	public List<Icon> getIcon() {
		return dao.getIcon();
	}
	
	public List<Banner> getBanner() {
		return bannerDao.list();
	}
}
