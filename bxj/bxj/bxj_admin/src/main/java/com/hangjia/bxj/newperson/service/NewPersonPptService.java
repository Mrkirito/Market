package com.hangjia.bxj.newperson.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.newperson.model.NewPersonCoursePpt;

/**
 * Created by Administrator on 2016/10/26.
 */
public interface NewPersonPptService {

    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);

    /**
     * 详细
     * @return
     */
    NewPersonCoursePpt detail(Long id);

    /**
     * 添加
     * @param coursePpt
     * @return
     */
    int add(NewPersonCoursePpt coursePpt);

    /**
     * 更新
     * @param coursePpt
     * @return
     */
    int update(NewPersonCoursePpt coursePpt);

    /**
     * 禁用、启用
     *
     * @param id
     * @return
     */
    int forbid(Long id, Integer status);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Long id);
}
