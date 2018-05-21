package com.hangjia.bxj.mvc.controller.client;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.model.PlanVoice;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.ControlIconService;
import com.hangjia.bxj.service.PlanVoiceService;
import com.hangjia.bxj.upload.VoiceUploadManager;
import com.hangjia.bxj.util.Utils;


/**
 * 首页icon部分
 * @author K9999
 *
 */
@Controller
@RequestMapping("index")
public class ControlIconClientController {
	@Autowired
	private ControlIconService service;
	
	/**
	 * icon查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="icon.json", method=RequestMethod.GET)
	public Object getIcon(HttpServletRequest request) {
		return new AjaxResult.success(service.getIcon());
	}
	
	
}
