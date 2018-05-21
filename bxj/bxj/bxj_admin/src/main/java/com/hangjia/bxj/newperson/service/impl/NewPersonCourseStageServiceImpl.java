package com.hangjia.bxj.newperson.service.impl;

import com.hangjia.bxj.newperson.dao.NewPersonCourseStageMapper;
import com.hangjia.bxj.newperson.model.NewPersonCourseStage;
import com.hangjia.bxj.newperson.query.NewPersonCourseStageQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 新人通课程业务实现
 * <p>
 * Created by bei.zhang on 2016/11/25.
 */
@Service
public class NewPersonCourseStageServiceImpl implements NewPersonCourseStageService {

    @Autowired
    private NewPersonCourseStageMapper courseStageMapper;

    /**
     * 查询新人通课程阶段数目
     *
     * @param query
     * @return
     */
    @Override
    public int queryStageDataCount(NewPersonCourseStageQuery query) {
        return courseStageMapper.queryStageDataCount(query);
    }

    /**
     * 分页查询新人通课程阶段
     *
     * @param query
     * @return
     */
    @Override
    public List<NewPersonCourseStage> queryStageDataPage(NewPersonCourseStageQuery query) {
        List<NewPersonCourseStage> list = courseStageMapper.queryStageDataPage(query);
        return list == null ? new ArrayList<NewPersonCourseStage>() : list;
    }

    /**
     * 新增课程阶段
     *
     * @param courseStage
     * @return
     */
    @Override
    public int addCourseStage(NewPersonCourseStage courseStage) {
        return courseStageMapper.insertSelective(courseStage);
    }

    /**
     * 修改课程阶段
     *
     * @param courseStage
     * @return
     */
    @Override
    public int updateCourseStage(NewPersonCourseStage courseStage) {
        return courseStageMapper.updateByPrimaryKeySelective(courseStage);
    }

}
