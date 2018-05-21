package com.hangjia.champion.service;

import com.hangjia.bxj.model.ChampionChannel;
import com.hangjia.champion.base.TestBaseService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: ChampionChannelServiceTest
 * @Description: 冠军说频道业务测试
 * @author: bei.zhang
 * @date: 2016年4月13日 下午2:34:37
 */
public class ChampionChannelServiceTest extends TestBaseService {

    @Autowired
    private ChampionChannelService championChannelService;

    @Test
    public void testQueryHeadChampionChannel() {
        try {
            List<ChampionChannel> championChannels = championChannelService.queryHeadChampionChannel();
            Assert.assertEquals(championChannels.size(), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
