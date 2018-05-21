package com.hangjia.bxj.newperson.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.newperson.model.NewPersonCourseStage;
import com.hangjia.bxj.newperson.query.NewPersonCourseStageQuery;

import java.util.List;

/**
 * 新人通课程阶段业务接口
 * <p>
 * Created by bei.zhang on 2016/11/25.
 */
public interface NewPersonCourseStageService {

    /**
     * 查询新人通课程阶段数目
     *
     * @param query
     * @return
     */
    int queryStageDataCount(NewPersonCourseStageQuery query);

    /**
     * 分页查询新人通课程阶段
     *
     * @param query
     * @return
     */
    List<NewPersonCourseStage> queryStageDataPage(NewPersonCourseStageQuery query);

    /**
     * 新增课程阶段
     *
     * @param courseStage
     * @return
     */
    int addCourseStage(NewPersonCourseStage courseStage);

    /**
     * 修改课程阶段
     *
     * @param courseStage
     * @return
     */
    int updateCourseStage(NewPersonCourseStage courseStage);

}
