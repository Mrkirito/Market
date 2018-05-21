package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjAppProductData;
import com.hangjia.bxj.query.report.ProductDataReportQuery;
import com.hangjia.bxj.vo.ProductDetailVo;

import java.util.List;

public interface BxjAppProductDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjAppProductData record);

    int insertSelective(BxjAppProductData record);

    BxjAppProductData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjAppProductData record);

    int updateByPrimaryKey(BxjAppProductData record);

    List<BxjAppProductData> queryAppProductDataPage(ProductDataReportQuery query);
    List<BxjAppProductData> queryALLAppProductDataPage();
    int queryAppProductDataCount(ProductDataReportQuery query);

    List<ProductDetailVo> selectProdectDataDetail();
    List<ProductDetailVo> selectXrtProdectDataDetail();
    List<ProductDetailVo> selectKmhProdectDataDetail();

}