package cn.usst.market.mapper;

import cn.usst.market.po.TeacherTextbook;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherTextbookMapper {

	
    public void updateByPrimaryKey(TeacherTextbook record);
    
    public List<TeacherTextbook> selectByCompetitionId(Integer id);
    
    public List<TeacherTextbook> selectByTeacherId(Integer id);
    
    public void deleteByPrimaryKey(Integer id);
    
    public void insertTeacherTextbook(TeacherTextbook record);
    
    public TeacherTextbook selectById(Integer id);
}