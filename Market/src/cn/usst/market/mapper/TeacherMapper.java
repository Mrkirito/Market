package cn.usst.market.mapper;

import cn.usst.market.po.Teacher;



public interface TeacherMapper {

	public Teacher doTeacherLogin(Teacher record)throws Exception;

    int deleteByPrimaryKey(Integer id);

    void insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);
    
    void updateByPrimaryKeySelective(Teacher record);

    
}