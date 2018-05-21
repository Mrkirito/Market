package com.hangjia.bxj.util;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller向页面以JSON格式返回时写响应流的工具类
 * 
 * @author
 * @version 1.0
 */
public class JsonResponseUtil {
	private Logger logger = LoggerFactory.getLogger(JsonResponseUtil.class);
	
	/**
	 * 
	 *
	 * @param response
	 * @param obj
	 * @param isSuccess void
	 * 2015-1-30  上午11:39:50
	 * 作者：qinpan
	 */
	public void write(HttpServletResponse response, Object obj,boolean isSuccess) {
		response.setHeader("Connection", "Close");
		Map resultMap = new HashMap();
		resultMap.put("isSuccess", isSuccess);
		resultMap.put("result", obj);
		new JsonResponseUtil().write(response, resultMap,isSuccess);
	}



	/**
	 * 将JSON格式的字符串写入响应流
	 * 
	 * @param response
	 * @param jsonStr
	 * @throws IOException
	 */
	public void write(HttpServletResponse response, String jsonStr) {
		doResponse(response, jsonStr);
	}

	/**
	 * 将JSON格式的字符串写入响应流
	 * 
	 * @param response
	 * @param jsonStr
	 */
	private void doResponse(HttpServletResponse response, String jsonStr) {
		try {
			response.getWriter().print(jsonStr);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}
