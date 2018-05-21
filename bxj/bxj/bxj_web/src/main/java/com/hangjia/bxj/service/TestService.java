package com.hangjia.bxj.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.framework.utils.jedis.RedisUtil;

import javax.servlet.http.HttpServletRequest;

@Service
public class TestService {

	@Autowired
	private RedisUtil	redisUtil;
	
	
	public Map<String, Object>  getCustomName(HttpServletRequest request) {
		Map<String, Object> rMap = new HashMap<String, Object>();

		String redisKey = "TEST_REDIST_CUSTOM_KEY_OBJ";
		String name = (String) redisUtil.getUnserializeKey(redisKey);
		if(name == null) {
			System.out.println("key init redis!"+System.currentTimeMillis());
			String custom = new String();
			redisUtil.setSerializeKey(redisKey, custom, 30);
			name = custom;
		} else {
			System.out.println("key hits!"+System.currentTimeMillis());
		}

		rMap.put("info", "ok");
		rMap.put("custom", name);
		rMap.put("getLocalName", request.getLocalName());
		rMap.put("getLocalAddr", request.getLocalAddr());
		return rMap;
	}
	
}
