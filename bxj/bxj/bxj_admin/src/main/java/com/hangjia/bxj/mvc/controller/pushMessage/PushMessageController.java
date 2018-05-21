package com.hangjia.bxj.mvc.controller.pushMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hangjia.bxj.mvc.common.BaseModule;

/** 
* @author  作者 : yaoy
* @date 2016年8月3日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/pushMessage")
public class PushMessageController extends BaseModule {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("addMessage.jhtml")
    public String addMessage() {
        return "pushMessage/addMessage";
    }
    
}
