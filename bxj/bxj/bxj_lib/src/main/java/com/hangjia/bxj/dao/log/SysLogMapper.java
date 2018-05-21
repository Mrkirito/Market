package com.hangjia.bxj.dao.log;

import java.util.List;

import com.hangjia.bxj.model.log.SysLogDO;
import com.hangjia.bxj.query.log.SysLogQuery;


/**
 * @author yaoy
 * @since 2016-06-21
 */
public interface SysLogMapper {
	
	int addLog(SysLogDO sysLogDO);
	
	int queryPageDataCount(SysLogQuery query);
	
	List<SysLogDO> queryPageData(SysLogQuery query);
}
