package com.hangjia.bxj.newperson.service.impl;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.newperson.dao.NewPersonCourseTypeMapper;
import com.hangjia.bxj.newperson.model.NewPersonCourseType;
import com.hangjia.bxj.newperson.query.NewPersonCourseTypeQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
@Service
public class NewPersonCourseTypeServiceImpl implements NewPersonCourseTypeService {

    @Autowired
    NewPersonCourseTypeMapper newPersonCourseTypeMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(NewPersonCourseTypeQuery query) {
        Result result = new Result();
        int count = newPersonCourseTypeMapper.selectByCount(query);
        List<NewPersonCourseType> list = newPersonCourseTypeMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    public Result getPageListByQuery(NewPersonCourseTypeQuery query) {
        Result result = new Result();
        int count = newPersonCourseTypeMapper.selectCountByQuery(query);
        List<NewPersonCourseType> list = newPersonCourseTypeMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public NewPersonCourseType detail(Long id) {
        NewPersonCourseType courseType = newPersonCourseTypeMapper.selectByPrimaryKey(id);
        return courseType;
    }

    /**
     * 添加
     * @param courseType
     * @return
     */
    @MethodLog(remark = "添加课程类别")
    public int add(NewPersonCourseType courseType) {
        return newPersonCourseTypeMapper.insertSelective(courseType);
    }

    /**
     * 更新
     * @param courseType
     * @return
     */
    @MethodLog(remark = "更新课程类别")
    public int update(NewPersonCourseType courseType) {
        return newPersonCourseTypeMapper.updateByPrimaryKeySelective(courseType);
    }

    /**
     * 禁用启用
     *
     * @param id
     * @return
     */
    @Transactional
    @MethodLog(remark = "启用或禁用课程类别")
    public int forbid(Long id, Integer status) {
        NewPersonCourseType courseType = new NewPersonCourseType();
        courseType.setId(id);
        courseType.setEnableStatus(status);
        return newPersonCourseTypeMapper.updateByPrimaryKeySelective(courseType);
    }
}
