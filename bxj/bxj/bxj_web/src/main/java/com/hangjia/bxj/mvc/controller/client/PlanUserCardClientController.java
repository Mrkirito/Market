package com.hangjia.bxj.mvc.controller.client;

import com.baobao.sso.client.WebUtils;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.ControlAppStoreService;
import com.hangjia.bxj.service.usercard.UserCardService;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.hangjia.bxj.vo.PlanUserCard;
import com.hangjia.bxj.vo.PlanUserImgVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PlanUserCardClientController
 * @Description: 用户名片管理
 * @author: bei.zhang
 * @date: 2016年4月14日 下午3:57:45
 */
@Controller
@RequestMapping("userCard")
public class PlanUserCardClientController {
	@Autowired
	private UserCardService userCardService;
	@Autowired
	private ControlAppStoreService controlAppStoreService;
//	@Value("${static_path}")
//	private String staticPath;
	@Value("${bxj_path}")
	private String bxjPath;


	/**
	 * @Title: loadUserCard
	 * @Description: 载入用户名片信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadUserCard.json", method = RequestMethod.GET)
	@ResponseBody
	public Object loadUserCard(HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
//		respMap.put("staticPath", staticPath);
		// 公司职位信息
		respMap.put("companys", userCardService.queryAllCompanyAndPosition());
		// 从业年限
		respMap.put("jobYears", userCardService.queryAllJobYears());
		Integer userId = null;
		String phone = "";
		try {
			userId = WebUtils.getMemberId(request);
			phone = WebUtils.getMobile(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		} else {
			respMap.put("isLogin", true);
		}
		// 用户登陆用户名片信息
		PlanUserCard userCard = userCardService.queryPlanUserCardById(userId);
		if (userCard == null) {
			respMap.put("existUserCard", false);
			userCard = new PlanUserCard();
			userCard.setFid(userId);
			userCard.setPhoto(bxjPath + "/hjb_app/planusercard/mecard_default_simple.png");
			userCard.setSex(
					Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_SEX)));
			userCard.setPhone(phone);
			userCard.setModel(
					Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_MODEL)));
			userCard.setCompanyCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY));
			userCard.setJobCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION));
			userCard.setJob(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION_NAME));
			userCard.setCompany(
					controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY_NAME));
			userCard.setRealCertState(false);
		} else {
			respMap.put("existUserCard", true);
			if(StringUtils.isEmpty(userCard.getPhone())){
				userCard.setPhone(phone);
			}
			if(null == userCard.getSex()){
				userCard.setSex(
						Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_SEX)));
			}
			if (null == userCard.getModel()){
				userCard.setModel(
						Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_MODEL)));
			}
			if (StringUtils.isEmpty(userCard.getCompanyCode())){
				userCard.setCompanyCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY));
			}
			if (StringUtils.isEmpty(userCard.getCompany())){
				userCard.setCompany(
						controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY_NAME));
			}
			if (StringUtils.isEmpty(userCard.getJobCode())){
				userCard.setJobCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION));
			}
			if (StringUtils.isEmpty(userCard.getJobCode())){
				userCard.setJob(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION_NAME));
			}
			if (null == userCard.getRealCertState()){
				userCard.setRealCertState(false);
			}
		}
		respMap.put("userCard", userCard);
		return new AjaxResult.success(respMap);
	}

	/**
	 * @Title: saveUserCard
	 * @Description: 新增或修改用户名片
	 * @param planUserCard
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveUserCard.json", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUserCard(PlanUserCard planUserCard, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
//		respMap.put("staticPath", staticPath);
		if (planUserCard.getFid() == null) {
			Integer userId = null;
			try {
				userId = WebUtils.getMemberId(request);
			} catch (Exception e) {
				respMap.put("isLogin", false);
				return new AjaxResult.success(respMap);
			}
			if (userId == null) {
				respMap.put("isLogin", false);
				return new AjaxResult.success(respMap);
			} else {
				respMap.put("isLogin", true);
				planUserCard.setFid(userId);
			}
		}
		PlanUserCard userCard = userCardService.savaPlanUserCard(planUserCard);
		if (userCard == null) {
			return new AjaxResult.error("保存失败！");
		}
		if(StringUtils.isEmpty(userCard.getPhone())){
			userCard.setPhone(WebUtils.getMobile(request));
		}
		respMap.put("userCard", userCard);
		return new AjaxResult.success(respMap);
	}

	@RequestMapping(value = "deleteUserImg.json", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteUserImg(PlanUserImgVo planUserImgVo, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
//		respMap.put("staticPath", staticPath);
		if (planUserImgVo.getUserId() == null) {
			Integer userId = null;
			try {
				userId = WebUtils.getMemberId(request);
			} catch (Exception e) {
				respMap.put("isLogin", false);
				return new AjaxResult.success(respMap);
			}
			if (userId == null) {
				respMap.put("isLogin", false);
				return new AjaxResult.success(respMap);
			} else {
				respMap.put("isLogin", true);
				planUserImgVo.setUserId(userId.longValue());
			}
		}
		PlanUserCard userCard = userCardService.deleteUserImg(planUserImgVo);
		if (userCard == null) {
			return new AjaxResult.error("删除失败！");
		}
		if(StringUtils.isEmpty(userCard.getPhone())){
			userCard.setPhone(WebUtils.getMobile(request));
		}
		respMap.put("userCard", userCard);
		return new AjaxResult.success(respMap);
	}

}
