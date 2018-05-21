package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.StaticInfoMapper;
import cn.usst.market.po.AdvertiseInfo;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MediaInfo;
import cn.usst.market.po.PriceInfo;
import cn.usst.market.po.UsageInfo;
import cn.usst.market.service.StaticInfoService;
@Service("staticInfoService")
public class StaticInfoServiceImpl implements StaticInfoService {
	
	@Autowired
	private StaticInfoMapper staticInfoMapper;

	@Override
	public List<UsageInfo> showUsageInfo() {
		// TODO Auto-generated method stub
		return staticInfoMapper.showUsageInfo();
	}

	@Override
	public List<PriceInfo> showPriceInfo() {
		// TODO Auto-generated method stub
		return staticInfoMapper.showPriceInfo();
	}

	@Override
	public List<MediaInfo> showMediaInfo() {
		// TODO Auto-generated method stub
		return staticInfoMapper.showMediaInfo();
	}

	@Override
	public List<AdvertiseInfo> showAdvertiseInfo() {
		// TODO Auto-generated method stub
		return staticInfoMapper.showAdvertiseInfo();
	}

	@Override
	public List<MarketInfo> showMarketInfo(int competitionId) {
		// TODO Auto-generated method stub
		return staticInfoMapper.showMarketInfo(competitionId);
	}
	
}
