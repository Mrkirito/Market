package com.hangjia.bxj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.framework.utils.jedis.RedisUtil;
import com.hangjia.bxj.dao.ControlAppStoreDao;
import com.hangjia.bxj.model.BxjResponse;
import com.hangjia.bxj.util.RedisKeyConstants;
@Service
@Transactional(rollbackFor=Throwable.class)
public class ControlAppStoreService {
	@Autowired
	private ControlAppStoreDao controlAppStoreDao;
	@Autowired
	private RedisUtil redisUtil;
	
	protected Map<String, String> getAllResponseParas() {
		Map<String, String> map = (Map<String, String>) redisUtil.getUnserializeKey(RedisKeyConstants.BXJ_RESPONSE_MAP);
		if(map==null){			
			map=new HashMap<String, String>();
			List<BxjResponse> list = controlAppStoreDao.selectAppStore();
			for (BxjResponse bxjResponse : list) {
				map.put(bxjResponse.getId(), bxjResponse.getContent());
			}
			redisUtil.setSerializeKey(RedisKeyConstants.BXJ_RESPONSE_MAP, map);
		}
		return map;
	}
	
	
	public String getResponseParaByKey(String key) {
		Map<String, String> map =getAllResponseParas();
		return map.get(key);
	}
	
}
