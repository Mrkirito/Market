package com.hangjia.bxj.mvc.controller.client;

import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.HomeConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * 首页配置图片部分
 *
 */
@Controller
@RequestMapping("index")
public class HomeConfController {
	private Logger log = LoggerFactory.getLogger(HomeConfController.class);
	@Autowired
	private HomeConfService service;
	
	/**
	 * 首页配置图片查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="home_conf.json", method=RequestMethod.GET)
	public Object getHomeConf(Integer type, BigDecimal h, BigDecimal w, String os, String versionCode) {
		double version = 3.0;
		log.info("os:" + os + "-----" + "versionCode" + versionCode);
		int vCode = Integer.parseInt(versionCode);
		/** 3.0 **/
		//if("android".equals(os) && "11".equals(versionCode) || "iOS".equals(os) && "103".equals(versionCode)) version = 3.0;
		//else if("android".equals(os) && "12".equals(versionCode) || "iOS".equals(os) && "104".equals(versionCode)) version = 3.1;
		if("android".equals(os) && vCode==11 || "iOS".equals(os) && vCode<=104) version = 3.0;
		else if(("android".equals(os) && vCode>=12 && vCode<18) || ("iOS".equals(os) && vCode>=105) && vCode<120) version = 3.1;
		else if("android".equals(os) && vCode==18 || "iOS".equals(os) && vCode==120) version = 3.2;
		else if("android".equals(os) && vCode==19 || ("iOS".equals(os) && vCode>=121&&vCode<124)) version = 3.3;
		else if(("android".equals(os) && vCode>=20 && vCode<=21) || ("iOS".equals(os) && vCode>=124 && vCode<=125)) version = 3.4;
		else if("android".equals(os) && vCode>=22 || "iOS".equals(os) && vCode>=126) version = 3.5;
		return new AjaxResult.success(service.getHomeConf(version, type, h, w, os));
	}

	/**
	 * 初始化广告接口
	 * del Bxj_Response_Map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="init_ad.json", method=RequestMethod.GET)
	public Object getInitAd() {
		return new AjaxResult.success(service.getInitAd());
	}

}
