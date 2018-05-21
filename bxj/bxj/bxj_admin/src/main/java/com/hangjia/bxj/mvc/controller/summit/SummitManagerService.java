package com.hangjia.bxj.mvc.controller.summit;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.summit.SummitManagerDao;
import com.hangjia.bxj.model.summit.ChampionSummitEntity;
import com.hangjia.bxj.model.summit.ChampionSummitListEntry;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.summit.SummitManagerQuery;

@Service
@Transactional
public class SummitManagerService {
	
	@Autowired
	private SummitManagerDao summitManagerDao; 
	
	/**
	 * 显示路径 
	 */
	@Value("${show_path}")
	private String showPath;
	
	private static final Log log = LogFactory.getLog(SummitManagerService.class);
	
	@MethodLog(remark = "更新峰会信息")
	public Integer saveOrUpdate(ChampionSummitEntity summit) {
		Integer id = summit.getId();
		
		if (id == null) {
			return summitManagerDao.saveSummit(summit);
		} else {
			int count = summitManagerDao.updateSummit(summit);
			if (log.isDebugEnabled()) {
				log.debug("已更新峰会" + count + "条记录");
			}
		}
		return id;
	}
	
	@Transactional(readOnly=true)
	public Result<List<ChampionSummitListEntry>> paginationQuery(SummitManagerQuery params) {
		
		Result<List<ChampionSummitListEntry>> result = new Result<List<ChampionSummitListEntry>>();
		
		int total = summitManagerDao.summitCount(params);
		
		if (total > 0) {
			List<ChampionSummitListEntry> list = summitManagerDao.listSummits(params);
			for (ChampionSummitListEntry championSummitListEntry : list) {
				championSummitListEntry.setImgFileUrl(showPath+championSummitListEntry.getImageUrl());
			}
			result.setModel(list);
		}
		params.setTotalItem(total);
		result.setQuery(params);
		
		return result;
	}
	
}
