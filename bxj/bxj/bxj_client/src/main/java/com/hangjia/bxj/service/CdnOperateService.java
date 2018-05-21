package com.hangjia.bxj.service;

import java.util.Map;

public interface CdnOperateService {

	public Map<String, Object> upCdnCacheFile(String filePath);

	public Map<String, Object> upCdnCacheDirectory(String path);

	public Map<String, Object> pushCdnCache(String resourcePath);


}
