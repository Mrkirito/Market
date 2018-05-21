package com.hangjia.friend.service.impl;

import com.hangjia.bxj.dao.firendcircle.FriendCircleMapper;
import com.hangjia.bxj.model.firendcircle.FriendCircle;
import com.hangjia.friend.service.FriendCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/4.
 */
@Service
public class FriendCircleServiceImpl implements FriendCircleService{
    @Autowired
    FriendCircleMapper friendCircleMapper;

    /**
     * 查询详细
     * @param id
     * @return
     */
    public FriendCircle getDetail(Long id) {
        return friendCircleMapper.selectByPrimaryKey(id);
    }
}
