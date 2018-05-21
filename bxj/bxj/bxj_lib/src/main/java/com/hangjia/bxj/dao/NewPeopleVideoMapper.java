package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.NewPeopleVideo;
import com.hangjia.bxj.vo.NewPeoplePassVo;

public interface NewPeopleVideoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(NewPeopleVideo record);

	int insertSelective(NewPeopleVideo record);

	NewPeopleVideo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(NewPeopleVideo record);

	int updateByPrimaryKey(NewPeopleVideo record);

	/**
	 * 获取新人通视频信息
	 * 
	 * @param record
	 *            NewPeopleVideo
	 * @return
	 */
	NewPeoplePassVo selOneNewpeople(Map<String, Object> map);

	/**
	 * 查询所有
	 * 
	 * @param record
	 *            NewPeopleVideo
	 * @return
	 */
	List<NewPeoplePassVo> selListNewpeopleVD(Map<String, Object> map);

	/**
	 * 查询所有
	 * 
	 * @param map
	 * @return
	 */
	List<NewPeoplePassVo> selList(Map<String, Object> map);

	/**
	 * 插入状态信息
	 * 
	 * @param record
	 * @return
	 */
	int insertState(NewPeopleVideo record);

	/**
	 * 更新浏览次数
	 * 
	 * @param record
	 */
	void updateNumAdd(NewPeopleVideo record);
}