package com.hangjia.bxj.mvc.controller.prize;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.prize.EggPrizeDO;
import com.hangjia.bxj.model.prize.EggPrizeInDO;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.prize.EggPrizeInQuery;
import com.hangjia.bxj.query.prize.EggPrizeQuery;
import com.hangjia.bxj.service.prize.IEggPrizeService;

/** 
* @author  作者 : yaoy
* @date 2016年7月21日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/eggPrize")
public class EggPrizeController extends BaseModule {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IEggPrizeService eggPrizeService;
	
	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	@InitBinder("updateTime")
    protected void initBinder2(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("eggPrizeAdd.jhtml")
    public String eggPrizeAdd(HttpServletRequest request) {
    	request.setAttribute("in", false);
        return "prize/eggPrizeAdd";
    }
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("eggPrizeList.jhtml")
    public String eggPrizeList() {
        return "prize/eggPrizeList";
    }
    
    /**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("eggPrizeInList.jhtml")
    public String eggPrizeInList() {
        return "prize/eggPrizeInList";
    }
    
    /**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("eggPrizeInAdd.jhtml")
    public String eggPrizeInAdd(HttpServletRequest request) {
    	request.setAttribute("in", true);
        return "prize/eggPrizeAdd";
    }
    
    /**
	 * 保存奖品
	 * @param eggPrizes
	 */
    @RequestMapping("savePrize.json")
    @ResponseBody
    public Result savePrize(@RequestBody List<EggPrizeDO> eggPrizes) {
    	Result result = new Result();
		Map<String, String> resMap = eggPrizeService.batchSaveEggPrize(eggPrizes);
		if(StringUtils.equals(resMap.get("success"), "false")){
			result.setMsg(resMap.get("msg"));
			result.setSuccess(false);
			logger.error(resMap.get("msg"));
		}
        return result;
    }
    
	/**
	 * 查询所有奖品
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryEggPrizeList.json")
    public @ResponseBody Result queryEggPrizeList(@ModelAttribute EggPrizeQuery query) {
    	
    	Result result = new Result();
    	int count = eggPrizeService.queryEggDataCount(query);
    	if(count > 0){
    		List<EggPrizeDO> eggList = eggPrizeService.queryEggDataPage(query);
    		result.setModel(eggList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
	/**
	 * 审核通过
	 * @param cash
	 * @return Result
	 */
    @RequestMapping("upPrize.json")
    public @ResponseBody Result upPrize(@ModelAttribute EggPrizeDO eggPrizeDO) {
    	
    	Result result = new Result();
    	eggPrizeDO.setUpdateTime(new Date());
    	eggPrizeDO.setUpdateName(getLoginUserName());
    	Map<String, String> resMap = eggPrizeService.upPrize(eggPrizeDO);
		if(StringUtils.equals(resMap.get("success"), "false")){
			result.setMsg(resMap.get("msg"));
			result.setSuccess(false);
			logger.error(resMap.get("msg"));
		}
        return result;
    }
    
    /**
	 * 审核不通过
	 * @param cash
	 * @return Result
	 */
    @RequestMapping("downPrize.json")
    public @ResponseBody Result downPrize(@ModelAttribute EggPrizeDO eggPrizeDO) {
    	
    	Result result = new Result();
    	eggPrizeDO.setUpdateTime(new Date());
    	eggPrizeDO.setUpdateName(getLoginUserName());
    	Map<String, String> resMap = eggPrizeService.downPrize(eggPrizeDO);
		if(StringUtils.equals(resMap.get("success"), "false")){
			result.setMsg(resMap.get("msg"));
			result.setSuccess(false);
			logger.error(resMap.get("msg"));
		}
        return result;
    }
    
    /**
	 * 更新砸蛋奖品数据
	 * @param cash
	 * @return Result
	 */
    @RequestMapping("updatePrize.json")
    public @ResponseBody Result updatePrize(@ModelAttribute EggPrizeDO eggPrizeDO) {
    	Result result = new Result();
    	eggPrizeDO.setUpdateTime(new Date());
    	eggPrizeDO.setUpdateName(getLoginUserName());
    	Map<String, String> resMap = eggPrizeService.updatePrize(eggPrizeDO);
		if(StringUtils.equals(resMap.get("success"), "false")){
			result.setMsg(resMap.get("msg"));
			result.setSuccess(false);
			logger.error(resMap.get("msg"));
		}
        return result;
    }
    
	/**
	 * 查询所有奖品增量
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryEggPrizeInList.json")
    public @ResponseBody Result queryEggPrizeInList(@ModelAttribute EggPrizeInQuery query) {
    	
    	Result result = new Result();
    	int count = eggPrizeService.queryEggInDataCount(query);
    	if(count > 0){
    		List<EggPrizeInDO> eggInList = eggPrizeService.queryEggInDataPage(query);
    		result.setModel(eggInList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
    /**
	 * 删除奖品增量
	 * @param query
	 * @return Result
	 */
    @RequestMapping("deletePrizeIn.json")
    public @ResponseBody Result deletePrizeIn(Long id,  @ModelAttribute("updateTime")Date updateTime) {
    	Result result = new Result();
    	int delete = eggPrizeService.deletePrizeIn(id, updateTime);
    	if(delete != 1){
    		result.setSuccess(false);
    		result.setMsg("该记录已经被更新，请刷新后重新操作");
    	}
        return result;
    }
    
    /**
	 * 保存奖品
	 * @param eggPrizesIn
	 */
    @RequestMapping("savePrizeIn.json")
    @ResponseBody
    public Result savePrizeIn(@RequestBody List<EggPrizeInDO> eggPrizesIn) {
    	Result result = new Result();
		Map<String, String> resMap = eggPrizeService.batchSaveEggPrizeIn(eggPrizesIn);
		if(StringUtils.equals(resMap.get("success"), "false")){
			result.setMsg(resMap.get("msg"));
			result.setSuccess(false);
			logger.error(resMap.get("msg"));
		}
        return result;
    }
}
