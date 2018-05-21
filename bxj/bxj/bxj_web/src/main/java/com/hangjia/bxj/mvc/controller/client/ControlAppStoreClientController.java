package com.hangjia.bxj.mvc.controller.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.ControlAppStoreService;

@Controller
@RequestMapping("control")
public class ControlAppStoreClientController {
	@Autowired
	private ControlAppStoreService controlAppStoreService;
	
	@ResponseBody
	@RequestMapping(value="{key}.json")
	public Object getVoice(@PathVariable String key) {
		return new AjaxResult.success(controlAppStoreService.getResponseParaByKey(key));
	}
}
