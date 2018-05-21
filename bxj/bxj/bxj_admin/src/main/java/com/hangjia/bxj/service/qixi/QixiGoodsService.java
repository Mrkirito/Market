package com.hangjia.bxj.service.qixi;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.qixi.QixiGoods;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface QixiGoodsService {

    int getGoodsCount();

    List<QixiGoods> getGoodsList(BaseCommonQuery query);

    QixiGoods detail(Long id);

    int add(QixiGoods goods);

    int update(QixiGoods goods);

    int delete(Long id);
}
