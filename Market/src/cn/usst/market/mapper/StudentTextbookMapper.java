package cn.usst.market.mapper;

import cn.usst.market.po.StudentTextbook;
import cn.usst.market.po.TeacherTextbook;

import java.util.List;

public interface StudentTextbookMapper {

	StudentTextbook selectByPrimaryKey(Integer id);
	
	List<TeacherTextbook> selectTextbookListByStudentId(Integer id);
	
    public void deleteByPrimaryKey(Integer id);
    
    public void deleteByTeacherTextbookId(Integer id);

    public void insert(StudentTextbook record);

    public void insertSelective(StudentTextbook record);

}