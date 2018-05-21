package com.hangjia.bxj.service.knowledge;


import com.hangjia.bxj.model.Knowledge;
import com.hangjia.bxj.vo.Pagination;

/**
 * 知识、学习模块
 * @author K9999
 *
 */
public interface KnowledgeService {

	/**
	 * 查询知识条目列表。
	 * @param page 第几页。
	 * @param limit 最多返回多少条记录。
	 * @return
	 */
	Pagination<Knowledge> findPage(int page, int limit);
	
	/**
	 * 查询所有知识
	 * @param map
	 * @return
	 */
	Pagination<Knowledge> listAllPage(Knowledge know,Integer index,Integer pageSize);
	
	/**
	 * 更新知识 信息
	 * @param know
	 * @return
	 */
	int updateByPK(Knowledge know);
	
	/**
	 * 查询总条数 
	 * @param know
	 * @return
	 */
	int selTotal(Knowledge know);
	
}
