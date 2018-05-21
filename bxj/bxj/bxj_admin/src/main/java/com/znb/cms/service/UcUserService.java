package com.znb.cms.service;

import com.hangjia.bxj.query.ucUserCenter.UcUserQuery;
import com.hangjia.bxj.ucenter.model.UcUser;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface UcUserService {
    int queryPageCount(UcUserQuery query);

    List<UcUser> queryPageData(UcUserQuery query);

    void deleteUser(List<String> idList);

    void enableUser(Long id, boolean b);
}
