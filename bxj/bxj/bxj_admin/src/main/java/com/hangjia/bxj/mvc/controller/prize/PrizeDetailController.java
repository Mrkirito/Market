package com.hangjia.bxj.mvc.controller.prize;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.framework.support.utility.WebUtility;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.prize.PrizeBonusLog;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.prize.PrizeDetailQuery;
import com.hangjia.bxj.service.prize.IPrizeDetailService;

@Controller
@RequestMapping("/prizeDetail")
public class PrizeDetailController extends BaseModule {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPrizeDetailService prizeDetailService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("prizeDetailList.jhtml")
    public String eggPrizeList() {
        return "prize/prizeDetailList";
    }
    
	/**
	 * 查询所有中奖信息
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryPrizeList.json")
    public @ResponseBody Result queryEggPrizeList(@ModelAttribute PrizeDetailQuery query) {
    	
    	Result result = new Result();
    	int count = prizeDetailService.queryPrizeLogCount(query);
    	if(count > 0){
    		List<PrizeBonusLog> eggList = prizeDetailService.queryPrizeLogList(query);
    		result.setModel(eggList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
	/**
	 * 发货 通过
	 * @param cash
	 * @return Result
	 */
    @RequestMapping("updateLogStatus.json")
    public @ResponseBody Result upPrize( @ModelAttribute PrizeBonusLog prizelog,HttpServletRequest request) {
    	Result result = new Result(); 
    	String ip = WebUtility.getIpAddress(request);
    	prizelog.setIp(ip);
    	int num=prizeDetailService.updatePrizeLogStatus(prizelog);
		if(num>0){
			result.setMsg("发货成功！");
			result.setSuccess(true);
			logger.info("中奖 发货成功 ! id:"+prizelog.getBonusLogId());
		} else {
			result.setMsg("发货失败！");
			result.setSuccess(false);
			logger.error("中奖 发货失败  ! id:"+prizelog.getBonusLogId());
            if(num==-1){
            	result.setMsg("发货失败,发送短信异常！");
            	logger.error("中奖 发货失败 ,发送短信异常!");
			 }else if(num==-2){
				 result.setMsg("发货失败,更新发货状态异常!");
				 logger.error("中奖 发货失败 , 更新发货状态异常! ");
			 }else if(num==0){
				 result.setMsg("发货失败, 发货id为空!");
			 }
		}
        return result;
    }
     
}
