package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.TeacherReference;

public interface TeacherReferenceMapper {
	
	public List<TeacherReference> findTeacherReference() throws Exception;
}
