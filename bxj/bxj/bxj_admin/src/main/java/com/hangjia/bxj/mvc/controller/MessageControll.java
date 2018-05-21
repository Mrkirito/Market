package com.hangjia.bxj.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.framework.support.utility.Configuration;
import com.ibaoxianjia.message.dto.SmsMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;

@Controller
@RequestMapping("/message")
public class MessageControll {

	@Autowired
	private MessageOpenAPI messageOpenAPI;

	@RequestMapping("/batchSendMsgPage")
	public ModelAndView batchSendMsgPage(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("/message/sendsms");
		return modelAndView;
	}
	
	
	@RequestMapping("/batchSendMsg")
	@ResponseBody
	public Map<String, Object> batchSendMsg(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
			String template = request.getParameter("template");
			String[] tels = request.getParameter("tels").split(",");
			List<String> error=new ArrayList<String>();
			for (String phone : tels) {
				SmsMessageDto smsMessageDto = new SmsMessageDto();
				smsMessageDto.setIp(getRemoteAddr(request));
				smsMessageDto.setSysid(Configuration.getProperty("system", "sysid"));
				smsMessageDto.addPhone(phone);
				smsMessageDto.setSmsCode("BXJ_BATCH_SEND");
				Map<String, String> infoMap = new HashMap<String, String>();
				infoMap.put("content", template);
				smsMessageDto.setSmsInfoMap(infoMap);
				boolean boo = false;
				try {
					boo = messageOpenAPI.sendSms(smsMessageDto);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!boo) {
					error.add(phone);
				}
			}
			boolean hasError=error.size() > 0;
			map.put("hasErrorCount",hasError);
			if(hasError){
				map.put("errorList", listToString(error));				
			}
			map.put("success", true);
		return map;
	}

	private static String getRemoteAddr(HttpServletRequest request) {
		String ip = clean(request.getHeader("X-Forwarded-For"));
		if (ip != null && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = clean(request.getHeader("X-Real-IP"));
		if (ip != null && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	private static String clean(String str) {
		String result = null;
		if (str != null) {
			result = str.trim();
			if (result.isEmpty()) {
				result = null;
			}
		}
		return result;
	}  
    private static String listToString(List<?> list) {  
        StringBuffer sb = new StringBuffer();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                if (list.get(i) == null || list.get(i) == "") {  
                    continue;  
                }else{  
                    sb.append(list.get(i));  
                    sb.append("，");  
                }  
            }  
        }  
        return sb.toString();  
    } 
}
