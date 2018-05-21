package com.hangjia.summit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.summit.ChampionSummitListEntry;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.query.summit.SummitManagerQuery;

@Service
@Transactional
public interface ISummitService {
	/**
	 * 查询峰会
	 * 
	 * @param params
	 * @return
	 */
	Result<List<ChampionSummitListEntry>> querySummitList(SummitManagerQuery params);

	/**
	 * 更新峰会讲师
	 * 
	 * @param classRoom
	 * @return
	 */
	Integer updateTeacher(ClassroomEntity classRoom);

	/**
	 * 查询讲师 列表
	 * 
	 * @param params
	 * @return
	 */
	List<ClassroomEntity> paginationQueryTeachers(SummitManagerQuery params);

	/**
	 * 查询单个 讲师
	 * 
	 * @param params
	 * @return
	 */
	ClassroomEntity queryOneTeachers(SummitManagerQuery params);
	/**
	 * 获取ppt图片
	 * @param params
	 * @return
	 */
	ClassroomEntity getImgArray(SummitManagerQuery params);

}
