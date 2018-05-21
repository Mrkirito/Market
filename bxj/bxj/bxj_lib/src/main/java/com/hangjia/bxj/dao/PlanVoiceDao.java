package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.PlanVoice;

public interface PlanVoiceDao {
	/**
	 * 根据用户id 查询录音
	 * @param fid
	 * @return
	 */
	List<PlanVoice> getVoice(Integer fid);
	
	/**
	 * 保存录音
	 * @param planVoice
	 */
	int saveVoice (PlanVoice planVoice);
	
	/**
	 * 删除录音
	 * @param planVoice
	 */
	void deleteVoice (PlanVoice planVoice);
	
	
	/**
	 * 重命名录音
	 * @param planVoice
	 */
	void renameVoice (PlanVoice planVoice);
	
	/**
	 * 主键 获取录音
	 * @param id
	 * @return
	 */
	PlanVoice getPlanVoiceById(Long id);
}
