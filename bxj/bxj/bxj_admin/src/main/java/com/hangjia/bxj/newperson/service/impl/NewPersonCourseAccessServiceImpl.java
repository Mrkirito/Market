package com.hangjia.bxj.newperson.service.impl;

import com.hangjia.bxj.newperson.dao.NewPersonCourseAccessLogMapper;
import com.hangjia.bxj.newperson.model.NewPersonCourseAccessLog;
import com.hangjia.bxj.newperson.query.NewPersonCourseAccessQuery;
import com.hangjia.bxj.newperson.service.NewPersonCourseAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 新人通课程日志 业务实现
 * <p>
 * Created by Administrator on 2016/12/1.
 */
@Service
public class NewPersonCourseAccessServiceImpl implements NewPersonCourseAccessService {

    @Autowired
    private NewPersonCourseAccessLogMapper courseAccessLogMapper;

    /**
     * 查询新人通课程访问数目
     *
     * @param query
     * @return
     */
    @Override
    public int queryAccessDataCount(NewPersonCourseAccessQuery query) {
        return courseAccessLogMapper.queryAccessDataCount(query);
    }

    /**
     * 分页查询新人通课程阶段
     *
     * @param query
     * @return
     */
    @Override
    public List<NewPersonCourseAccessLog> queryAccessDataPage(NewPersonCourseAccessQuery query) {
        List<NewPersonCourseAccessLog> list = courseAccessLogMapper.queryAccessDataPage(query);
        return list == null ? new ArrayList<NewPersonCourseAccessLog>() : list;
    }
}
