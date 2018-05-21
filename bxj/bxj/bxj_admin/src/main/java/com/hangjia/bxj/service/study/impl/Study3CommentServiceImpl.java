package com.hangjia.bxj.service.study.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.study.Study3CommentMapper;
import com.hangjia.bxj.model.study.Study3Comment;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.service.study.Study3CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
@Service
public class Study3CommentServiceImpl implements Study3CommentService {
    @Autowired
    Study3CommentMapper study3CommentMapper;

    /**
     * 分页查询
     * @return
     */
    public Result getPageList(BaseCommonQuery query) {
        Result result = new Result();
        int count = study3CommentMapper.selectByCount(query);
        List<Study3Comment> list = study3CommentMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 分页查询
     * @return
     */
    /*public Result getPageListByGoodsId(ShopGoodsPriceQuery query) {
        Result result = new Result();
        int count = study3CommentMapper.selectCountByGoodsId(query);
        List<ShopGoodsPrice> list = study3CommentMapper.selectPageByGoodsId(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }*/

    /**
     * 删除
     * @return
     */
    @MethodLog(remark = "删除文章评论")
    public int delete(Long id) {
        return study3CommentMapper.updateDisableById(id);
    }

    /**
     * 批量删除文章评论
     * @param ids
     * @return
     */
    @MethodLog(remark = "批量删除文章评论")
    public int deleteBat(String ids) {
        int count = 0;
        String [] idArr = ids.split(",");
        for(String id: idArr) {
            count += study3CommentMapper.updateDisableById(Long.valueOf(id));
        }
        return count;
    }
}
