package com.hangjia.bxj.dao.summit;

import java.util.List;


import com.hangjia.bxj.model.summit.ChampionSummitListEntry;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.query.summit.SummitManagerQuery;
/**
 * 峰会前端操作类
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年7月27日 下午3:56:16
 */
public interface SummitShowDao {

	int summitCount(SummitManagerQuery params);

	List<ChampionSummitListEntry> listSummits(SummitManagerQuery params);

	/**
	 * 查询讲师列表
	 * @param params
	 * @return
	 */
	List<ClassroomEntity> listSummitTeachers(SummitManagerQuery params);
	
	/**
	 * 查询 单个讲师
	 * @param params
	 * @return
	 */
	ClassroomEntity selSummitTeachers(SummitManagerQuery params);
	
	/**
	 * 修改峰会讲师
	 * @param classRoom
	 * @return
	 */
	Integer updateSummitClass(ClassroomEntity classRoom);
	
	/**
	 * 峰会 讲师总条数
	 * @param params
	 * @return
	 */
	int sumTeachers(SummitManagerQuery params);
	/**
	 * 查询图片集合
	 * @param params
	 * @return
	 */
	List<ClassroomEntity> selAllImgs(SummitManagerQuery params);

}
