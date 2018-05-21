package com.znb.cms.service.impl;

import com.core.cms.dao.mapper.InsureBookMapper;
import com.core.cms.model.mapper.InsureBook;
import com.znb.cms.service.IInsureBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在线预约book
 * Created by Administrator on 2017/7/31.
 */
@Service
public class InsureBookServiceImpl implements IInsureBookService{
    @Autowired
    private InsureBookMapper insureBookMapper;

    @Override
    public int selectCount(InsureBook insureBook) {
        return insureBookMapper.selectCount(insureBook);
    }

    @Override
    public List<InsureBook> selectInsureBook(InsureBook insureBook) {
        return insureBookMapper.selectInsureBook(insureBook);
    }

    @Override
    public void update(InsureBook insureBook) {
        insureBookMapper.update(insureBook);
    }

    @Override
    public void del(Integer id) {
        insureBookMapper.delByPrimaryKey(id);
    }

    @Override
    public void insert(InsureBook insureBook) {
        insureBookMapper.insert(insureBook);
    }

    @Override
    public List<InsureBook> selectByprimaryKey(Integer id) {
        return insureBookMapper.selectByprimaryKey(id);
    }
}

