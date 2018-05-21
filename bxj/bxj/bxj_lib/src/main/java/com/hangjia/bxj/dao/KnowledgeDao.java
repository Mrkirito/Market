package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.Knowledge;

/**
 * 知识，学习版块
 * @author K9999
 *
 */
public interface KnowledgeDao {
	
	/**
	 * 
	 * @param first 从第几条记录开始。
	 * @param limit 本次查询最多返回多少条记录。
	 * @return
	 */
	List<Knowledge> list(int first, int limit);
	
	/**
	 * 返回总记录数
	 * @return
	 */
	int total(Map<String,Object> map);

	/**
	 * 修改学习 列表 
	 * @param knowledge
	 * @return
	 */
	int updateByPKSelective(Knowledge knowledge);
	
	/**
	 * 查询知识列表 后台
	 * @param map
	 * @return
	 */
	List<Knowledge> konwlist(Map<String,Object> map);
	
	/**
	 * 插入知识
	 * @param knowledge
	 */
	int insert(Knowledge knowledge);
	
	/**
	 * 单个查询
	 * @param knowledge
	 * @return
	 */
	Knowledge selknowByPK(Integer id);
	
	/**
	 * 客户端接口 查询所有知识
	 * @param knowledge
	 * @return
	 */
	List<Knowledge> listAll(Map<String,Object> map);
	

}
