package cn.usst.market.mapper;

import cn.usst.market.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 陈立阳 on 2017/10/15.
 */
public interface MarketingMapper {
    //获取根据竞赛ID公司名称查找

    /**
     * selectByCompetitionId
     *
     * @param competitionId
     * @param quarter
     * @return
     */
    List<BrandOfRival> selectByCompetitionId(int competitionId, int quarter);

    //根据组件编号查询

    /**
     * selectProductDetail
     *
     * @param detailId
     * @return
     */
    ProductInfo selectProductDetail(int detailId);

    //获取根据公司ID和公司名称查找

    /**
     * selectProductDetail1
     *
     * @param competitionId
     * @param companyName
     * @return
     */
    List<BrandOfRival> selectProductDetail1(@Param("comId") int competitionId, @Param("comName") String companyName);
    //根据公司ID和季度查询广告

    /**
     * selectProductByCompanyIdAndQuarter
     *
     * @param companyId
     * @param quarter
     * @return
     */
    List<CompanyAd> selectAdByCompanyIdAndQuarter(@Param("comId") int companyId, @Param("quarter") int quarter);

    //根据产品编号和广告名称查找广告

    /**
     * selectAdByProductIdAndAdName
     *
     * @param productId
     * @param adName
     * @return
     */
    AdPojo selectAdByProductIdAndAdName(@Param("proId") int productId, @Param("adName") String adName);

    //查找广告详情

    /**
     * selectAdDetailById
     *
     * @param id
     * @return
     */
    String selectAdDetailById(int id);

    /**
     * selectMarketShareByCompetitionId
     *
     * @param competitionId
     * @param quarter
     * @return
     */
    List<MarketShare> selectMarketShareByCompetitionId(int competitionId, int quarter);

    /**
     * selectCompanyName
     *
     * @param companyId
     * @return
     */
    String selectCompanyName(int companyId);

    /**
     * selectProductName
     *
     * @param productId
     * @return
     */
    String selectProductName(int productId);

    /**
     * selectProductName1
     *
     * @param companyId
     * @param productId
     * @return
     */
    String selectProductName1(int companyId, int productId);

    /**
     * selectProductPrice
     *
     * @param competitionId
     * @param quarter
     * @return
     */
    List<ProductPrice> selectProductPrice(int competitionId, int quarter);

    /**
     * selectCostOfProduction
     *
     * @param companyId
     * @param productName
     * @return
     */
    int selectCostOfProduction(int companyId, String productName);

    /**
     * selectAdDetailByCompetitionId
     *
     * @param competitionId
     * @param quarter
     * @return
     */
    List<AdDetail> selectAdDetailByCompetitionId(int competitionId, int quarter);

    /**
     * selectMediaInfo
     *
     * @return
     */
    List<MediaInfo> selectMediaInfo();

    /**
     * selectMainMedia
     *
     * @param companyId
     * @return
     */
    List<MainMedia> selectMainMedia(int companyId);

    /**
     * selectAllAdInfo
     *
     * @param companyId
     * @param quarter
     * @return
     */
    List<CheckResult> selectAllAdInfo(int companyId, int quarter);

    /**
     * selectBrandProfitByCompanyId
     *
     * @param companyId
     * @param quarter
     * @return
     */
    List<Profit> selectBrandProfitByCompanyId(int companyId, int quarter);

    /**
     * selectPriceAndSale
     *
     * @param companyId
     * @param quarter
     * @return
     */
    List<PriceSale> selectPriceAndSale(int companyId, int quarter);

    /**
     * selectProductPrice1
     *
     * @param productId
     * @param quarter
     * @return
     */
    String selectProductPrice1(int productId, int quarter);

    /**
     * selectNumOfMediaAd
     *
     * @param productId
     * @param quarter
     * @return
     */
    List<MediaAdNum> selectNumOfMediaAd(int productId, int quarter);

    /**
     * selectMediaCostByMediaId
     *
     * @param mediaId
     * @return
     */
    int selectMediaCostByMediaId(int mediaId);

    /**
     * selectPostOffice
     *
     * @param productId
     * @param quarter
     * @return
     */
    String selectPostOffice(int productId, int quarter);

    int selectCompetitionIdByCompanyId(int companyId);

    List<CompanyProduct> selectProductByCompanyIdAndQuarter(int companyId, int i);

    void deleteAdByIdAndName(int productId, String adName);

    CompanyProductDemand selectCompanyProductDemand(int productId, int i);

    List<ProductPrice> selectProductPrice2(int companyId, int quarter);

    void updateProductPrice(int productId, int i, int quarter);

    /**
     * 向最终检查表中添加数据
     *
     * @param companyId
     * @param productId
     * @param i
     * @param quarter
     */
    void addFinalCheck(int companyId, int productId, int i, int quarter);

    /**
     * 根据产品ID查询到相应的产品信息
     *
     * @param productId
     * @return
     */
    int selectFinalCheckByProductId(Integer productId, Integer quarter);

    int updateFinalCheckPriceAndSale(int companyId, int productId, int i, int quarter);

    void updateFinalCheckPrice(Integer productId, Integer price);

    void addFinalCheckPrice(Integer productId, Integer price, Integer quarter);

    void updateFinalCheckSalesSalary(int companyIdInt, int salary, int quarter);

    void updateFinalCheckWorksSalary(int companyIdInt, int salary, int quarter);

    void updateFinalCheckIsPhy(int companyId, int i, int quarter);

    void updateFinalCheckCapacity(int companyIdInt, int quarter, String operateCapacity);
}
