package com.hangjia.bxj.dao.study;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.study.Study3Comment;

import java.util.List;

public interface Study3CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Study3Comment record);

    int insertSelective(Study3Comment record);

    Study3Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Study3Comment record);

    int updateByPrimaryKey(Study3Comment record);

    /**************************** extend begin here *******************************/
    /**
     * 总个数
     * @param query
     * @return
     */
    int selectByCount(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<Study3Comment> selectByPage(BaseCommonQuery query);

    /**
     * 删除
     * @return
     */
    int updateDisableById(Long id);
}