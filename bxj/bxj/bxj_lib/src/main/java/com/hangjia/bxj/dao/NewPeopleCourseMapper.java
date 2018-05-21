package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.NewPeopleCourse;
import com.hangjia.bxj.vo.NewPeoplePassVo;

public interface NewPeopleCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPeopleCourse record);

    int insertSelective(NewPeopleCourse record);

    NewPeopleCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPeopleCourse record);

    int updateByPrimaryKey(NewPeopleCourse record);
    
    /**
     * 查询课件单个真实次数信息 
     * @param id
     * @return
     */
    NewPeopleCourse selTrueCourseInfo(Long id);
    
    /**
     * 查询课件列表 
     * @param record NewPeopleCourse
     * @return
     */
    List<NewPeoplePassVo> selListNewCourse(Map<String,Object> map);
    
    /**
     * 修改课件 播放次数
     * @param map
     * @return
     */
    int updateCountByPK(Map<String,Object> map);
}