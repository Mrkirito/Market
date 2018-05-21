package com.hangjia.newpeople.service;

import java.util.List;

import com.hangjia.bxj.vo.NewPeoplePassVo;

public interface InewPeopleService {
	
	/**
	 * 查询新人通 课件列表信息
	 * @param newPeoplePassVo
	 * @param index 开始页码
	 * @param pageSize 页码个数 
	 * @return
	 */
	List<NewPeoplePassVo> selListNewPeoCourse(NewPeoplePassVo newPeoplePassVo,int index,int pageSize);
	
	/**
	 * 查询新人通 视频列表信息
	 * @param newPeoplePassVo
	 * @param index 开始页码
	 * @param pageSize 页码个数 
	 * @return
	 */
	List<NewPeoplePassVo> selListNewPeopleVD(NewPeoplePassVo newPeoplePassVo,int index,int pageSize);
	
	/**
	 * 查询所有 
	 * @param newPeoplePassVo
	 * @return
	 */
	List<NewPeoplePassVo> selList(NewPeoplePassVo newPeoplePassVo);
	/**
	 * 获取浏览次数
	 * @param id
	 * @return
	 */
	NewPeoplePassVo selOneNewpeopleVD(Long id);
	/**
	 * 更新 课件 播放次数 
	 * @param newPeoplePassVo
	 */
	void updateNewPeople(NewPeoplePassVo newPeoplePassVo);
	
	/**
	 * 获取课件信息
	 * @param newPeoplePassVo
	 * @return
	 */
	NewPeoplePassVo selByPKgetCourse(NewPeoplePassVo newPeoplePassVo);
	
	/**
	 * 更新浏览次数
	 * @param id
	 */
	void updateNum(Long id);
}
