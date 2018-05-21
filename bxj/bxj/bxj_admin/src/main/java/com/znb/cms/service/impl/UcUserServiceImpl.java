package com.znb.cms.service.impl;

import com.hangjia.bxj.query.ucUserCenter.UcUserQuery;
import com.hangjia.bxj.ucenter.dao.UcUserMapper;
import com.hangjia.bxj.ucenter.model.UcUser;
import com.znb.cms.service.UcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/3.
 */
@Service("UcUserService")
public class UcUserServiceImpl implements UcUserService{

    @Autowired
    private UcUserMapper ucUserMapper;

    /**
     * 查询总记录数
     * @param query
     * @return
     */
    @Override
    public int queryPageCount(UcUserQuery query) {
        return ucUserMapper.queryPageCount(query);
    }

    /**
     * 分页查询数据
     * @param query
     * @return
     */
    @Override
    public List<UcUser> queryPageData(UcUserQuery query) {
        return ucUserMapper.queryPageData(query);
    }

    @Override
    public void deleteUser(List<String> idList) {
        ucUserMapper.deleteUser(idList);
    }

    @Override
    public void enableUser(Long id, boolean b) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("b", b ? 1 : 0);
        ucUserMapper.enableUser(map);
    }
}
