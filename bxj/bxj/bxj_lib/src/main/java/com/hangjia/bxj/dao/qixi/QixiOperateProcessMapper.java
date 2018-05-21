package com.hangjia.bxj.dao.qixi;

import com.hangjia.bxj.model.qixi.QixiOperateProcess;

public interface QixiOperateProcessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QixiOperateProcess record);

    int insertSelective(QixiOperateProcess record);

    QixiOperateProcess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QixiOperateProcess record);

    int updateByPrimaryKey(QixiOperateProcess record);
}