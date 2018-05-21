package com.hangjia.bxj.mvc.controller.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.UserLecturerRespDto;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.ChampionChannelMapper;
import com.hangjia.bxj.dao.ChampionTagMapper;
import com.hangjia.bxj.dao.prize.EggPrizeMapper;
import com.hangjia.bxj.model.ChampionChannel;
import com.hangjia.bxj.model.ChampionTag;
import com.hangjia.bxj.model.prize.EggPrizeDO;
import com.hangjia.bxj.query.prize.EggPrizeQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月20日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/common")
public class CommonAjax {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChampionChannelMapper championChannelMapper;
	
	@Autowired
	private UserCardSupportService userCardSupportService;
	
	@Autowired
	private ChampionTagMapper championTagMapper;
	
	@Autowired
	private EggPrizeMapper eggPrizeMapper;
	
	/**
	 * 查询所有分类
	 * @param championChannel
	 * @return
	 */
	@RequestMapping("queryChannel.json")
    public @ResponseBody Result queryChannel(@ModelAttribute ChampionChannel championChannel) {
    	Result result = new Result();
    	List<ChampionChannel> channelList = championChannelMapper.selectBySelective(championChannel);
    	result.setModel(channelList);
        return result;
	}
	
	/**
	 * 查询所有讲师
	 * @param name
	 * @return
	 */
	@RequestMapping("queryLectures1.json")
    public @ResponseBody Result queryLectures1(@RequestParam String name) {
    	Result result = new Result();
    	List<UserLecturerRespDto> lecturerList = userCardSupportService.getAllLecturesInfoByLikeName(name);
    	if(null != lecturerList && lecturerList.size() > 0){
    		for (UserLecturerRespDto userLecturerRespDto : lecturerList) {
    			userLecturerRespDto.setName(userLecturerRespDto.getFid() + " : " + userLecturerRespDto.getName());
			}
    	}
    	result.setModel(lecturerList);
        return result;
	}
	
	/**
	 * 查询所有讲师
	 * @param name
	 * @return
	 */
	@RequestMapping("queryLectures2.json")
    public @ResponseBody Result queryLectures2(@RequestParam String name) {
    	Result result = new Result();
    	List<UserLecturerRespDto> lecturerList = userCardSupportService.getAllLecturesInfoByLikeName(name);
    	result.setModel(lecturerList);
        return result;
	}
	
	/**
	 * 查询所有标签
	 * @param name
	 * @return
	 */
	@RequestMapping("queryTags.json")
    public @ResponseBody Result queryTags(@ModelAttribute ChampionTag championTag) {
    	Result result = new Result();
    	List<ChampionTag> tagList = championTagMapper.selectBySelective(championTag);
    	result.setModel(tagList);
        return result;
	}
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("icons.jhtml")
    public String icons() {
        return "common/icons";
    }
    
	/**
	 * 查询所有砸蛋奖品
	 * @param name
	 * @return
	 */
	@RequestMapping("queryAllEggPrizes.json")
    public @ResponseBody Result queryAllEggPrizes(@ModelAttribute EggPrizeQuery query) {
    	Result result = new Result();
    	List<EggPrizeDO> prizeList = eggPrizeMapper.queryAllEggPrizes(query);
    	result.setModel(prizeList);
        return result;
	}
}
