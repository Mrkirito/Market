package com.hangjia.bxj.newperson.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.newperson.dao.NewPersonCourseMapper;
import com.hangjia.bxj.newperson.dao.NewPersonCourseQuestionMapper;
import com.hangjia.bxj.newperson.model.NewPersonCourse;
import com.hangjia.bxj.newperson.model.NewPersonCourseQuestion;
import com.hangjia.bxj.newperson.service.NewPersonCourseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
@Service
public class NewPersonCourseQuestionServiceImpl implements NewPersonCourseQuestionService {

    @Autowired
    NewPersonCourseQuestionMapper newPersonCourseQuestionMapper;
    @Autowired
    NewPersonCourseMapper newPersonCourseMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = newPersonCourseQuestionMapper.selectByCount(query);
        List<NewPersonCourseQuestion> list = newPersonCourseQuestionMapper.selectByPage(query);
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
        int count = newPersonCourseQuestionMapper.selectCountByQuery(query);
        List<NewPersonCourseQuestion> list = newPersonCourseQuestionMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public NewPersonCourseQuestion detail(Long id) {
        NewPersonCourseQuestion courseQuestion = newPersonCourseQuestionMapper.selectByPrimaryKey(id);
        NewPersonCourse course = newPersonCourseMapper.selectByPrimaryKey(courseQuestion.getCourseId());
        courseQuestion.setCourseTitle(course.getCourseTitle());
        return courseQuestion;
    }

    /**
     * 添加
     * @param courseQuestion
     * @return
     */
    @MethodLog(remark = "添加课程试题")
    public int add(NewPersonCourseQuestion courseQuestion) {
        return newPersonCourseQuestionMapper.insertSelective(courseQuestion);
    }

    /**
     * 更新
     * @param courseQuestion
     * @return
     */
    @MethodLog(remark = "更新课程试题")
    public int update(NewPersonCourseQuestion courseQuestion) {
        return newPersonCourseQuestionMapper.updateByPrimaryKeySelective(courseQuestion);
    }

    /**
     * 禁用启用
     *
     * @param id
     * @return
     */
    @Transactional
    @MethodLog(remark = "启用或禁用课程试题")
    public int forbid(Long id, Integer status) {
        NewPersonCourseQuestion courseQuestion = new NewPersonCourseQuestion();
        courseQuestion.setId(id);
        courseQuestion.setEnableStatus(status);
        return newPersonCourseQuestionMapper.updateByPrimaryKeySelective(courseQuestion);
    }
}
