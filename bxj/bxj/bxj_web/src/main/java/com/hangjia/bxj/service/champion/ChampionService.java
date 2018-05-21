package com.hangjia.bxj.service.champion;

import com.hangjia.bxj.BXJException;

/**
 * 冠军论坛
 * @author K9999
 *
 */
public interface ChampionService {

	/**
	 * 根据提取码，获取冠军论坛课件链接地址。
	 * @param code 提取码。
	 * @return
	 * @throws BXJException 提取码不存在，已过期等。
	 */
	String getLocationByCode(String code) throws BXJException;
	
}
