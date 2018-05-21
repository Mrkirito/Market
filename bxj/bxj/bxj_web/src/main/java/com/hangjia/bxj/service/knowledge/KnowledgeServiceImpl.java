package com.hangjia.bxj.service.knowledge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.KnowledgeDao;
import com.hangjia.bxj.model.Knowledge;
import com.hangjia.bxj.vo.Pagination;

@Service
@Transactional(readOnly=true, rollbackFor=Throwable.class)
public class KnowledgeServiceImpl implements KnowledgeService {
	
	@Autowired
	private KnowledgeDao knowledgeDao;

	@Override
	public Pagination<Knowledge> findPage(int page, int limit) {
		Map<String,Object> map =new HashMap<String, Object>();
		int total = knowledgeDao.total(map);
		List<Knowledge> rows;
		if (total > 0) {
			int first = (page - 1) * limit;
			rows = knowledgeDao.list(first, limit);
		} else {
			rows = new LinkedList<Knowledge>();
		}
		return new Pagination<Knowledge>(total, rows);
	}

	@Override
	public Pagination<Knowledge> listAllPage(Knowledge know, Integer index, Integer pageSize) {
		Map<String,Object> map =new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		map.put("noSet", "未设置");
		//map.put("isRead", 0); //未读 0  
		//未读  未设置总条数
		int total = knowledgeDao.total(map);
		//查询所有 
		List<Knowledge> rows=knowledgeDao.listAll(map);
		
		return new Pagination<Knowledge>(total, rows);
	}

	
	@Override
	public int selTotal(Knowledge know) {
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("noSet", "未设置");
		map.put("isRead", know.getIsRead()); //未读 0  
		//未读 总条数
		int total = knowledgeDao.total(map);
		return total;
	}

	@Override
	public int updateByPK(Knowledge know) {
		// TODO Auto-generated method stub
		return	knowledgeDao.updateByPKSelective(know);
	}

	
	 
}
