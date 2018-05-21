package com.znb.cms.service;

import com.core.cms.model.mapper.InsureBook;

import java.util.List;

/**
 * 在线预约service接口
 * Created by Administrator on 2017/7/31.
 */
public interface IInsureBookService {
    int selectCount(InsureBook insureBook);
    List<InsureBook> selectInsureBook(InsureBook insureBook);
    void update(InsureBook insureBook);
    void del(Integer id);
    void insert(InsureBook insureBook);
    List<InsureBook> selectByprimaryKey(Integer id);
}
