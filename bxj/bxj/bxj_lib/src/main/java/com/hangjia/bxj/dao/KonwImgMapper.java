package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.KonwImg;

public interface KonwImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KonwImg record);

    int insertSelective(KonwImg record);

    KonwImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KonwImg record);

    int updateByPrimaryKey(KonwImg record);
}