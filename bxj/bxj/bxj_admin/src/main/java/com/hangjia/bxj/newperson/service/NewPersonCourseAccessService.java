package com.hangjia.bxj.newperson.service;

import com.hangjia.bxj.newperson.model.NewPersonCourseAccessLog;
import com.hangjia.bxj.newperson.query.NewPersonCourseAccessQuery;

import java.util.List;

/**
 * 新人通课程访问业务接口
 * <p>
 * Created by bei.zhang on 2016/11/25.
 */
public interface NewPersonCourseAccessService {

    /**
     * 查询新人通课程访问数目
     *
     * @param query
     * @return
     */
    int queryAccessDataCount(NewPersonCourseAccessQuery query);

    /**
     * 分页查询新人通课程阶段
     *
     * @param query
     * @return
     */
    List<NewPersonCourseAccessLog> queryAccessDataPage(NewPersonCourseAccessQuery query);

}
