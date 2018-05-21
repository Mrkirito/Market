package com.hangjia.bxj.newperson.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.newperson.dao.NewPersonCoursePptMapper;
import com.hangjia.bxj.newperson.model.NewPersonCoursePpt;
import com.hangjia.bxj.newperson.service.NewPersonPptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
@Service
public class NewPersonPptServiceImpl implements NewPersonPptService {
    @Autowired
    NewPersonCoursePptMapper newPersonCoursePptMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = newPersonCoursePptMapper.selectByCount(query);
        List<NewPersonCoursePpt> list = newPersonCoursePptMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    public NewPersonCoursePpt detail(Long id) {
        NewPersonCoursePpt coursePpt = newPersonCoursePptMapper.selectByPrimaryKey(id);
        return coursePpt;
    }

    /**
     * 添加
     * @param coursePpt
     * @return
     */
    @MethodLog(remark = "添加课程ppt")
    public int add(NewPersonCoursePpt coursePpt) {
        return newPersonCoursePptMapper.insertSelective(coursePpt);
    }

    /**
     * 更新
     * @param coursePpt
     * @return
     */
    @MethodLog(remark = "更新课程ppt")
    public int update(NewPersonCoursePpt coursePpt) {
        return newPersonCoursePptMapper.updateByPrimaryKeySelective(coursePpt);
    }

    /**
     * 禁用启用
     *
     * @param id
     * @return
     */
    @Transactional
    @MethodLog(remark = "启用或禁用课程ppt")
    public int forbid(Long id, Integer status) {
        NewPersonCoursePpt coursePpt = new NewPersonCoursePpt();
        coursePpt.setId(id);
        coursePpt.setEnableStatus(status);
        return newPersonCoursePptMapper.updateByPrimaryKeySelective(coursePpt);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Transactional
    @MethodLog(remark = "删除课程ppt")
    public int delete(Long id) {
        return newPersonCoursePptMapper.deleteByPrimaryKey(id);
    }
}
