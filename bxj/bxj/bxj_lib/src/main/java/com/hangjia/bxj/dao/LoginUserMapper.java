package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.LoginUser;

public interface LoginUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginUser record);

    int insertSelective(LoginUser record);

    LoginUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginUser record);

    int updateByPrimaryKey(LoginUser record);
    
    LoginUser selectByUserCode(LoginUser record);
}