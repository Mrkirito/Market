package com.hangjia.bxj.mvc.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baobao.framework.utils.jedis.RedisUtil;
import com.hangjia.bxj.dao.PlanBookMapper;
import com.hangjia.bxj.model.PlanProductMain;
import com.hangjia.bxj.service.CdnOperateService;
import com.hangjia.bxj.util.RedisKeyConstants;


@Controller
@RequestMapping(value = "/refreshcache")
public class RefreshReidsCacheController {	
	@Autowired
	private CdnOperateService cdnOperateService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlanBookMapper dao;	
	private String desM="bxj_app_(_)";
	
	@RequestMapping("/refresh.do")
	private String index(String key,String entre, HttpServletResponse response) {
		response.setHeader("Connection", "Close");
		response.setCharacterEncoding("utf-8");
		if (StringUtils.isNotEmpty(entre) && entre.equals(desM)) {			
			String result = "fail";
			if (key != null && key.trim().length() != 0) {
				String[] keyList = key.split(",");
				redisUtil.delKeys(keyList);
				result = "suceess";
			}
			try {
				response.getWriter().print(key + "===refresh===" + result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;	
	}
	@RequestMapping("/refreshAllProduct.do")
	private String refreshAllProduct(String entre,String pids,HttpServletResponse response) {
		response.setHeader("Connection", "Close");
		response.setCharacterEncoding("utf-8");
		if (StringUtils.isNotEmpty(entre) && entre.equals(desM)) {		
			if(StringUtils.isEmpty(pids)){				
				List<PlanProductMain> list = dao.listPlanProductMains();
				int size = list.size();
				String[] keyList = new String[size];
				for (int i = 0; i < size; i++) {
					keyList[i]=RedisKeyConstants.SinglePlanProductKey+list.get(i).getFid();
				}
				try {
					redisUtil.delKeys(keyList);
					response.getWriter().print("all===refresh===success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				String[] pidArray = pids.split(",");
				String[] keyList = new String[pidArray.length];
				for (int i = 0; i < pidArray.length; i++) {
					keyList[i]=RedisKeyConstants.SinglePlanProductKey+pidArray[i];
				}
				try {
					redisUtil.delKeys(keyList);
					response.getWriter().print("all===refresh===success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}	
	@RequestMapping("/upCdnCacheFile.do")
	@ResponseBody
	public Object upCdnCacheFile(String url, String entre) {
		if (StringUtils.isNotEmpty(entre) && entre.equals(desM)) {
			return cdnOperateService.upCdnCacheFile(url);
		}
		return "fail";
	}

	@RequestMapping("/upCdnCacheDirectory.do")
	@ResponseBody
	public Object upCdnCacheDirectory(String directory, String entre) {
		if (StringUtils.isNotEmpty(entre) && entre.equals(desM)) {
			return cdnOperateService.upCdnCacheDirectory(directory);
		}
		return "fail";
	}
}
