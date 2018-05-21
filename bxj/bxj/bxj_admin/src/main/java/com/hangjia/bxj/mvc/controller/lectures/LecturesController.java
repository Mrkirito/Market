package com.hangjia.bxj.mvc.controller.lectures;

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
import com.hangjia.bxj.service.lectures.ILecturesAuthService;
import com.hangjia.bxj.service.order.IAccountOrderService;
import com.hangjia.bxj.ucenter.model.Cash;
import com.hangjia.bxj.ucenter.model.Profit;
import com.hangjia.bxj.ucenter.model.UserCard;
import com.hangjia.bxj.ucenter.query.CashQuery;
import com.hangjia.bxj.ucenter.query.ProfitQuery;
import com.hangjia.bxj.ucenter.query.UserCardQuery;

/**
* @author  作者 : yaoy
* @date 2016年7月12日 下午2:30:15
* @version 1.0
*/
@Controller
@RequestMapping("/lectures")
public class LecturesController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ILecturesAuthService lecturesAuthService;

	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }

	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("lecturesAuth.jhtml")
    public String accountProfit() {
        return "lectures/lecturesAuth";
    }

	/**
	 * 查询申请讲师认证列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryLecturesAuthList.json")
    public @ResponseBody Result queryLecturesAuthList(@ModelAttribute UserCardQuery query) {

    	Result result = new Result();
    	int	count = lecturesAuthService.queryUserCardDataCount(query);
    	if(count > 0){
    		List<UserCard> userCardList = lecturesAuthService.queryUserCardDataPage(query);
    		result.setModel(userCardList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }

	/**
	 * 审核通过
	 * @param userCard
	 * @return Result
	 */
    @RequestMapping("passAuth.json")
    public @ResponseBody Result passAuth(@ModelAttribute UserCard userCard) {

    	Result result = new Result();
		UserCardQuery query = new UserCardQuery();
		query.setIdCard(userCard.getIdCard());
        query.setRealAuditStatus(2);
        List<UserCard> users = lecturesAuthService.queryUserCardDataPage(query);
        if (users != null && !users.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("审核失败,身份证号" + query.getIdCard() + "身份证已经实名认证，无法重复认证！");
            return result;
        }

    	userCard.setAuditTime(new Date());
    	int update = lecturesAuthService.passAuth(userCard);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("审核失败");
    	}
        return result;
    }

    /**
	 * 审核不通过
	 * @param userCard
	 * @return Result
	 */
    @RequestMapping("failAuth.json")
    public @ResponseBody Result failAuth(@ModelAttribute UserCard userCard) {

    	Result result = new Result();
    	userCard.setAuditTime(new Date());
    	int update = lecturesAuthService.failAuth(userCard);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("审核失败");
    	}
        return result;
    }
}
