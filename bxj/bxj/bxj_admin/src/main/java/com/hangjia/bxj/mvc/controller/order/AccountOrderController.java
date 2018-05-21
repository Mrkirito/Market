package com.hangjia.bxj.mvc.controller.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.query.order.RewardQuery;
import com.hangjia.bxj.service.order.IAccountOrderService;
import com.hangjia.bxj.ucenter.model.Cash;
import com.hangjia.bxj.ucenter.model.Profit;
import com.hangjia.bxj.ucenter.query.CashQuery;
import com.hangjia.bxj.ucenter.query.ProfitQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/order")
public class AccountOrderController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAccountOrderService accountOrderService;
	
	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("accountProfit.jhtml")
    public String accountProfit() {
        return "order/accountProfit";
    }
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("accountReward.jhtml")
    public String accountReward() {
        return "order/accountReward";
    }
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("accountCash.jhtml")
    public String accountCash() {
        return "order/accountCash";
    }
    
	/**
	 * 查询用户收益列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryAccountProfitList.json")
    public @ResponseBody Result queryAccountProfitList(@ModelAttribute ProfitQuery query) {
    	
    	Result result = new Result();
    	int count = 1;
    	
    	if(null != query && null != query.getDimension() && query.getDimension().intValue() != 0){
    		count = accountOrderService.queryProfitDataCount(query);
    	}
    	if(count > 0){
    		List<Profit> profitList = accountOrderService.queryProfitDataPage(query);
    		result.setModel(profitList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
	/**
	 * 查询用户打赏列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryAccountRewardList.json")
    public @ResponseBody Result queryAccountRewardList(@ModelAttribute RewardQuery query) {
    	
    	Result result = new Result();
    	int count = 1;
    	
    	if(StringUtils.isNotBlank(query.getUserName()) || StringUtils.isNotBlank(query.getNickName())){
    		List<Long> userIds = accountOrderService.queryUserIds(query);
    		if(null != userIds && userIds.size() > 0){
    			query.setUserIds(userIds);
    		} else {
    			count = 0;
    			query.setTotalItem(count);
    	    	result.setQuery(query);
    	        return result;
    		}
    	}
    	
    	if(null != query && null != query.getDimension() && query.getDimension().intValue() != 0){
    		count = accountOrderService.queryRewardDataCount(query);
    	}
    	if(count > 0){
    		List<Reward> profitList = accountOrderService.queryRewardDataPage(query);
    		result.setModel(profitList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
    
	/**
	 * 查询用户提现列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryAccountCashList.json")
    public @ResponseBody Result queryAccountCashList(@ModelAttribute CashQuery query) {
    	
    	Result result = new Result();
    	int count = 1;
    	
    	if(null != query && null != query.getDimension() && query.getDimension().intValue() != 0){
    		count = accountOrderService.queryCashDataCount(query);
    	}
    	if(count > 0){
    		List<Cash> cashList = accountOrderService.queryCashDataPage(query);
    		result.setModel(cashList);
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
    @RequestMapping("passCash.json")
    public @ResponseBody Result passCash(@ModelAttribute Cash cash) {
    	
    	Result result = new Result();
    	cash.setAuditTime(new Date());
    	int update = accountOrderService.passCash(cash);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("审核失败");
    	}
        return result;
    }
    
    /**
	 * 审核不通过
	 * @param cash
	 * @return Result
	 */
    @RequestMapping("failCash.json")
    public @ResponseBody Result failCash(@ModelAttribute Cash cash) {
    	
    	Result result = new Result();
    	cash.setAuditTime(new Date());
    	int update = accountOrderService.failCash(cash);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("审核失败");
    	}
        return result;
    }

	/**
	 * 跳转页面
	 */
	@RequestMapping("/singleOrder/details.jhtml")
	public String singleOrderDetails(){
		return "/singleOrder/details";
	}
}
