package com.hangjia.champion.service;

import com.baobao.framework.page.Paginator;
import com.hangjia.champion.vo.ChampionVideoVo;

import java.util.List;
import java.util.Map;

/**
 * 保保网络科技-bxj
 * com.hangjia.bxj.service
 * 作者-秦岭(Tain)
 * 说明：
 * 2016/4/19 18:24
 * 2016保保网络-版权所有
 */
public interface ChampionSupportService {

    /**
     * 更新SOLR视频源索引,by ID
     */
    public Map<String, Object> updateVideoIndexById(Long videoId);
    /**
     * 更新SOLR视频源索引
     */
    public Map<String, Object> updateVideoIndexAll();

    /**
     * 删除视频索引
     */
    public Map<String, Object> delVideoIndexById(Long fid);

    /**
     * 查询所有符合条件的产品搜索结果
     * @param paginator
     * @return
     */
    public Map<String, Object> searchVideoList(Paginator paginator, ChampionVideoVo ChampionVideoVo, String key);


}
