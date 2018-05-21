package com.hangjia.bxj.service.qixi.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.dao.qixi.QixiGoodsMapper;
import com.hangjia.bxj.model.qixi.QixiGoods;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.service.qixi.QixiGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class QixiGoodsServiceImpl implements QixiGoodsService {
    @Autowired
    QixiGoodsMapper goodsMapper;

    /**
     * 总个数
     * @return
     */
    public int getGoodsCount() {
        return goodsMapper.selectGoodsCount();
    }

    /**
     * 分页列表
     * @return
     */
    public List<QixiGoods> getGoodsList(BaseCommonQuery query) {
        return goodsMapper.selectGoodsList(query);
    }

    /**
     * 详细
     * @param id
     * @return
     */
    public QixiGoods detail(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加
     * @param goods
     * @return
     */
    @Override
    @Transactional
    @MethodLog(remark = "添加新商品")
    public int add(QixiGoods goods) {
        goods.setCreateTime(new Date());
        return goodsMapper.insertSelective(goods);
    }

    /**
     * 更新
     * @param goods
     * @return
     */
    @Override
    @Transactional
    @MethodLog(remark = "更新商品")
    public int update(QixiGoods goods) {
        return goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @Transactional
    @MethodLog(remark = "删除商品")
    public int delete(Long id) {
        QixiGoods goods = new QixiGoods();
        goods.setIsDisplay(false);
        goods.setId(id);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }
}
