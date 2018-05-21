package com.hangjia.bxj.service.study;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
public interface Study3CommentService {
    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);

    /**
     * 分页查询
     * @return
     */
    //Result getPageListByGoodsId(ShopGoodsPriceQuery query);

    /**
     * 删除
     * @return
     */
    int delete(Long id);

    /**
     * 删除
     * @return
     */
    int deleteBat(String ids);
}
