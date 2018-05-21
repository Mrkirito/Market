package com.core.cms.dao.mapper;

import com.core.cms.model.mapper.InsureBook;

import java.util.List;

/**
 * 在线预约表映射
 * Created by Administrator on 2017/7/31.
 */
public interface InsureBookMapper {
    int selectCount(InsureBook insureBook);
    List<InsureBook> selectInsureBook(InsureBook insureBook);
    void delByPrimaryKey(Integer id);
    void update(InsureBook insureBook);
    void insert(InsureBook insureBook);
    List<InsureBook> selectByprimaryKey(Integer id);
}
