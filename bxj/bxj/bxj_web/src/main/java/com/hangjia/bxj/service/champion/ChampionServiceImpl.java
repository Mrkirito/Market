package com.hangjia.bxj.service.champion;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.dao.ChampionDao;
import com.hangjia.bxj.model.ChampionExtract;

@Service
public class ChampionServiceImpl implements ChampionService {
	
	@Autowired
	private ChampionDao championDao;
	
	@Override
	public String getLocationByCode(String code) throws BXJException {
		ChampionExtract e = championDao.get(code);
		if (e == null) {
			throw new BXJException("提取码不存在");
		}
		
		Date endAt = e.getEndAt();
		Date now = new Date();
		
		if (endAt != null) {
			if (endAt.before(now)) {
				throw new BXJException("提取码已过期");
			}
		}
		
		Date beginAt = e.getBeginAt();
		if (beginAt != null) {
			if (beginAt.after(now)) {
				throw new BXJException("课件提取还未开始，敬请期待");
			}
		}
		
		return e.getLocation();
	}
	
}
