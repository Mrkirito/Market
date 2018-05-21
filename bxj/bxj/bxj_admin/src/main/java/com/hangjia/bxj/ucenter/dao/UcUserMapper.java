package com.hangjia.bxj.ucenter.dao;

import com.hangjia.bxj.query.ucUserCenter.UcUserQuery;
import com.hangjia.bxj.ucenter.model.UcUser;
import com.hangjia.bxj.vo.ucUserCenter.UcUserDTO;

import java.util.List;
import java.util.Map;

public interface UcUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UcUser record);

    int insertSelective(UcUser record);

    UcUser selectByPrimaryKey(Long id);

    UcUser selectByUserName(String username);

    UcUser selectByMobile(String mobile);

    int updateByPrimaryKeySelective(UcUser record);

    int updateByPrimaryKey(UcUser record);

    /**
     * 查询记录总数
     * @param query
     * @return
     */
    int queryPageCount(UcUserQuery query);

    /**
     * 查询分页数据
     * @param query
     * @return
     */
    List<UcUser> queryPageData(UcUserQuery query);

    void deleteUser(List<String> idList);

    void enableUser(Map<String, Object> map);
}