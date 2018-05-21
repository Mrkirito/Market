package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.TempMobile;

import java.util.List;

public interface TempMobileMapper {
    int insert(TempMobile record);

    int insertSelective(TempMobile record);

    List<TempMobile> selectAll(TempMobile record);
}