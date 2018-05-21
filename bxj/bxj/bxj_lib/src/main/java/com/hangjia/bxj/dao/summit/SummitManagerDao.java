package com.hangjia.bxj.dao.summit;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hangjia.bxj.model.summit.ChampionSummitEntity;
import com.hangjia.bxj.model.summit.ChampionSummitListEntry;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.query.summit.SummitManagerQuery;
/**
 * 峰会后台 操作dao
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年7月27日 下午3:56:42
 */
public interface SummitManagerDao {

	Integer saveSummit(ChampionSummitEntity summit);

	int updateSummit(ChampionSummitEntity summit);

	int summitCount(SummitManagerQuery params);

	List<ChampionSummitListEntry> listSummits(SummitManagerQuery params);

	String getSummitName(Integer summitId);
	
	/**
	 * 添加讲师
	 * @param classRoom
	 * @return
	 */
	Integer saveSummitClass(ClassroomEntity classRoom);
	/**
	 * 修改峰会讲师
	 * @param classRoom
	 * @return
	 */
	Integer updateSummitClass(ClassroomEntity classRoom);
	
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
	 * 峰会 讲师总条数
	 * @param params
	 * @return
	 */
	int sumTeachers(SummitManagerQuery params);
	
	/**
	 * 保存讲师图片 
	 * @param classId
	 * @param images
	 * @return
	 */
	int saveTeacherImages(@Param("classRoomId") Integer classId, @Param("images") String[] images);

	/**
	 * 查询图片集合
	 * @param params
	 * @return
	 */
	List<ClassroomEntity> selAllImgs(SummitManagerQuery params);
	/**
	 * 删除ppt图片
	 * @param classId
	 * @return
	 */
	int deleteImgs(@Param("classRoomId") Integer classId);
}
