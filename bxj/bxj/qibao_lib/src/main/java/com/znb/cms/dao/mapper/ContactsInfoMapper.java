package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.ContactsInfo;

public interface ContactsInfoMapper {

    int deleteByPrimaryKey(Integer fid);

    int insert(ContactsInfo record);

    List<ContactsInfo> selectAll();

    int insertSelective(ContactsInfo record);

    ContactsInfo selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(ContactsInfo record);

    int updateByPrimaryKey(ContactsInfo record);
}