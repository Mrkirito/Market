package com.hangjia.bxj.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cdn.model.v20141111.PushObjectCacheRequest;
import com.aliyuncs.cdn.model.v20141111.PushObjectCacheResponse;
import com.aliyuncs.cdn.model.v20141111.RefreshObjectCachesRequest;
import com.aliyuncs.cdn.model.v20141111.RefreshObjectCachesResponse;
import com.aliyuncs.exceptions.ClientException;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hangjia.bxj.service.CdnOperateService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CdnOperateServiceImpl implements CdnOperateService {

	private Logger logger = Logger.getLogger(CdnOperateServiceImpl.class);
	private String accessKey;

	private String accessSecret;

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}

	private IAcsClient client = null;
	public void init() throws ClientException {
//      初始化IAcsClient
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
		client = new DefaultAcsClient(profile);
	}


	private Map<String, Object> upCdnCache(String resource, String type) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("code", 1);
		rmap.put("msg", "refresh success");
		RefreshObjectCachesRequest refreshObjectCachesRequest = new RefreshObjectCachesRequest();
		refreshObjectCachesRequest.setObjectPath(resource);
		refreshObjectCachesRequest.setObjectType(type);

		//发起调用
		try {
			RefreshObjectCachesResponse httpResponse = client.getAcsResponse(refreshObjectCachesRequest);
			logger.info("refreshCache.getRequestId:"+httpResponse.getRequestId()+",refreshCache.getRefreshTaskId:"+httpResponse.getRefreshTaskId());
		} catch (Exception e) {
			rmap.put("code", 0);
			rmap.put("msg", "refresh error");
		}
		return rmap;
	}



	public Map<String, Object> upCdnCacheFile(String filePath) {
		return upCdnCache(filePath, "File");
	}

	public Map<String, Object> upCdnCacheDirectory(String path) {
		return upCdnCache(path, "Directory");
	}

	public Map<String, Object> pushCdnCache(String resourcePath) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("code", 1);
		rmap.put("msg", "push cdn success");
		PushObjectCacheRequest pushObjectCacheRequest = new PushObjectCacheRequest();
		pushObjectCacheRequest.setObjectPath(resourcePath);
		//发起调用
		try {
			PushObjectCacheResponse httpResponse = client.getAcsResponse(pushObjectCacheRequest);
			logger.info("PushObjectCacheResponse.getRequestId:"+httpResponse.getRequestId()+",PushObjectCacheResponse.getRefreshTaskId:"+httpResponse.getPushTaskId());
		} catch (Exception e) {
			rmap.put("code", 0);
			rmap.put("msg", "push cdn error");
		}
		return rmap;
	}
}
