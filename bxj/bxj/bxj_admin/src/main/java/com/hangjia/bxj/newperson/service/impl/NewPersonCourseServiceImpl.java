package com.hangjia.bxj.newperson.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.newperson.dao.NewPersonCourseMapper;
import com.hangjia.bxj.newperson.dao.NewPersonCourseTypeMapper;
import com.hangjia.bxj.newperson.model.NewPersonCourse;
import com.hangjia.bxj.newperson.model.NewPersonCourseType;
import com.hangjia.bxj.newperson.service.NewPersonCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
@Service
public class NewPersonCourseServiceImpl implements NewPersonCourseService {
    @Autowired
    NewPersonCourseMapper newPersonCourseMapper;
    @Autowired
    NewPersonCourseTypeMapper newPersonCourseTypeMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = newPersonCourseMapper.selectByCount(query);
        List<NewPersonCourse> list = newPersonCourseMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    public Result getPageListByQuery(BaseCommonQuery query) {
        Result result = new Result();
        int count = newPersonCourseMapper.selectCountByQuery(query);
        List<NewPersonCourse> list = newPersonCourseMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public NewPersonCourse detail(Long id) {
        NewPersonCourse course = newPersonCourseMapper.selectByPrimaryKey(id);
        NewPersonCourseType courseType = newPersonCourseTypeMapper.selectByPrimaryKey(course.getCourseTypeId());
        if(null != courseType) course.setCourseTypeName(courseType.getName());
        return course;
    }

    /**
     * 添加
     * @param course
     * @return
     */
    @MethodLog(remark = "添加课程")
    public int add(NewPersonCourse course) {
        return newPersonCourseMapper.insertSelective(course);
    }

    /**
     * 更新
     * @param course
     * @return
     */
    @MethodLog(remark = "更新课程")
    public int update(NewPersonCourse course) {
        return newPersonCourseMapper.updateByPrimaryKeySelective(course);
    }

    /**
     * 禁用启用
     *
     * @param id
     * @return
     */
    @Transactional
    @MethodLog(remark = "启用或禁用课程")
    public int forbid(Long id, Integer status) {
        NewPersonCourse course = new NewPersonCourse();
        course.setId(id);
        course.setEnableStatus(status);
        return newPersonCourseMapper.updateByPrimaryKeySelective(course);
    }
}
