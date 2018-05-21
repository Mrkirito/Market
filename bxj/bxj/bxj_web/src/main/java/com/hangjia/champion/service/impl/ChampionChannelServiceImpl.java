package com.hangjia.champion.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hangjia.bxj.util.OrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangjia.bxj.dao.ChampionChannelMapper;
import com.hangjia.bxj.model.ChampionChannel;
import com.hangjia.bxj.vo.OrderData;
import com.hangjia.bxj.vo.OrderMethod;
import com.hangjia.champion.service.ChampionChannelService;

/**
 * @ClassName: ChampionChannelServiceImpl
 * @Description: 冠军说频道业务
 * @author: bei.zhang
 * @date: 2016年4月11日 下午3:41:35
 */
@Service
public class ChampionChannelServiceImpl implements ChampionChannelService {

	private static Logger log = LoggerFactory.getLogger(ChampionChannelServiceImpl.class);

	@Autowired
	private ChampionChannelMapper championChannelMapper;

	@Override
	public List<ChampionChannel> queryHeadChampionChannel() {
		List<OrderData> orderDatas = new ArrayList<OrderData>();
		orderDatas.add(new OrderData(OrderConstants.CHAMPION_CHANNEL_SORT, OrderMethod.ASC));
		List<ChampionChannel> championChannels = new ArrayList<ChampionChannel>();
		ChampionChannel championChannel = new ChampionChannel();
		championChannel.setIsDisplay(true);
		championChannel.setParentId(0L);
		championChannel.setOrderDatas(orderDatas);
		championChannels.addAll(championChannelMapper.selectBySelective(championChannel));
		championChannel = new ChampionChannel();
		championChannel.setIsDisplay(true);
		championChannel.setParentId(13L);
		championChannel.setOrderDatas(orderDatas);
		championChannels.addAll(championChannelMapper.selectBySelective(championChannel));
		return championChannels;
	}

}
