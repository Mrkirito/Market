package cn.usst.market.controller;

import cn.usst.market.annotation.MethodLog;
import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.mapper.MarketingMapper;
import cn.usst.market.po.*;
import cn.usst.market.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author 陈立阳 Created by 陈立阳 on 2017/10/9.
 */
@Controller
public class MarketingController {

    @Autowired
    private MarketingMapper marketingMapper;

    @Autowired
    private CompanyMapper companyMapper;


    @MethodLog(description = "品牌盈利能力")
    @RequestMapping("/brandProfit.do")
    public ModelAndView brandProfit(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        // 查询上个季度的品牌盈利
        List<Profit> list = marketingMapper.selectBrandProfitByCompanyId(companyId, quarter - 1);
        if (list.size() != 0) {
            for (Profit profit : list) {
                // 根据公司ID和产品ID查询产品名称
                String productName = marketingMapper.selectProductName1(profit.getCompanyId(), profit.getProductId());
                profit.setProductName(productName);
                // 计算某个产品的销售收入
                // 先查询产品售价
                String priceStr = marketingMapper.selectProductPrice1(profit.getProductId(), quarter - 1);
                int price;
                if (priceStr != null) {
                    price = Integer.parseInt(priceStr);
                } else {
                    price = 0;
                }
                // 再查询总销量
                int sale = profit.getSingaporeSale() + profit.getHongkongSale() + profit.getMoscowSale() + profit.getNewdelhiSale() + profit.getOnlineSale();
                profit.setTotalSale(sale);
                //销售收入
                int salesproceeds = price * sale;
                profit.setSalesproceeds(salesproceeds);

                // 查询邮寄返款
                String postOfficeStr = marketingMapper.selectPostOffice(profit.getProductId(), quarter - 1);
                int postOffice;
                if (postOfficeStr != null) {
                    postOffice = Integer.parseInt(postOfficeStr);
                } else {
                    postOffice = 0;
                }
                profit.setPostoffice(postOffice * sale);

                //销货成本
                //根据销量来判断销货成本
                CompanyProduct companyProduct = new CompanyProduct();
                int productCost = marketingMapper.selectCostOfProduction(profit.getCompanyId(), productName);
                int costofselling = companyProduct.getShengChanCost(sale, productCost);
                profit.setCostofselling(costofselling * sale);

                // 计算毛利
                int grossmargin = profit.getSalesproceeds() - profit.getPostoffice() - profit.getCostofselling();
                profit.setGrossmargin(grossmargin);

                //广告投放的费用
                // 根据产品ID查询某个产品在各个媒体上投放的广告的费用
                // 先查询该产品在各个媒体上的投放数量
                List<MediaAdNum> list1 = marketingMapper.selectNumOfMediaAd(profit.getProductId(), quarter - 1);
                if (list1.size() != 0) {
                    int cost = 0;
                    // 在根据mediaId查找某个media上的费用
                    for (MediaAdNum mediaAdNum : list1) {
                        cost += mediaAdNum.getNum() * marketingMapper.selectMediaCostByMediaId(mediaAdNum.getMediaId());
                    }
                    profit.setAdofbrand(cost + 30000);
                } else {
                    profit.setAdofbrand(0);
                }
                // 计算品牌费用
                int costofbrand = profit.getAdofbrand();
                profit.setCostofbrand(costofbrand);

                // 计算利润
                int pro = profit.getGrossmargin() - profit.getCostofbrand();
                profit.setProfit(pro);
                //利润占比
                if (profit.getSalesproceeds() != 0) {
                    String profitMargin = profit.getProfit() * 100 / profit.getSalesproceeds() + "";
                    profit.setProfitMargin(profitMargin);
                } else {
                    profit.setProfitMargin(0 + "");
                }

                //单位利润
                if (sale != 0) {
                    String unitProfit = profit.getProfit() / sale + "";
                    profit.setUnitProfit(unitProfit);
                } else {
                    profit.setUnitProfit(0 + "");
                }
            }
        }
        System.out.println(list);
        modelAndView.addObject("brandList", list);
        modelAndView.setViewName("brandProfit");
        return modelAndView;
    }

    @MethodLog(description = "品牌评价")
    @RequestMapping("/brandEva.do")
    public ModelAndView brandEva(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int competitionId = (int) request.getSession().getAttribute("competitionId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        List<MarketShare> list = marketingMapper.selectMarketShareByCompetitionId(competitionId, quarter - 1);
        List<MarketShare> list1;
        //将公司信息存入hashMap中
        HashMap<String, List<MarketShare>> hashMap = new HashMap();
        if (list.size() != 0) {

            for (MarketShare marketShare : list) {

                list1 = new ArrayList();
                //公司名称
                int companyId = marketShare.getCompanyId();
                String companyName = marketingMapper.selectCompanyName(companyId);
                marketShare.setCompanyName(companyName);

                //品牌名称
                int productId = marketShare.getProductId();
                String productName = marketingMapper.selectProductName(productId);
                marketShare.setProductName(productName);

                //品牌评价
                DecimalFormat df = new DecimalFormat("0.00");
                int sale = marketShare.getSingaporeSale() + marketShare.getHongkongSale() + marketShare.getMoscowSale() + marketShare.getNewdelhiNeed() + marketShare.getOnlineSale();
                CompanyProductDemand companyProductDemand = marketingMapper.selectCompanyProductDemand(productId, quarter - 1);
                if (companyProductDemand != null) {
                    Integer demand = companyProductDemand.getDemand();
                    if (demand == 0) {
                        marketShare.setTotalNeed(0);
                        marketShare.setEva(0);
                    } else {
                        marketShare.setTotalNeed(demand);
                        String eva = df.format((double) demand / (double) sale);
                        marketShare.setEva(Double.parseDouble(eva));
                    }
                } else {
                    marketShare.setTotalNeed(0);
                    marketShare.setEva(0);
                }

                //如果当前遍历的信息的键与添加到hashMap中的相同则添加到该键对应的值中
                if (hashMap.size() != 0 && hashMap.containsKey(companyName)) {
                    hashMap.get(companyName).add(marketShare);
                } else {
                    list1.add(marketShare);
                    hashMap.put(companyName, list1);
                }
            }
        }
        modelAndView.addObject("hashMap", hashMap);
        modelAndView.setViewName("brandEva");

        return modelAndView;
    }

    @MethodLog(description = "竞争对手的品牌")
    @RequestMapping("/brandOfRival.do")
    public ModelAndView brandOfRival(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        // 获取竞赛ID
        int competitionId = (int) request.getSession().getAttribute("competitionId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        System.out.println(competitionId);
        // 根据竞赛ID选择本次竞赛的公司名称和对应的产品名称(当前季度之前的所有产品)
        List<BrandOfRival> brandOfRivals = marketingMapper.selectByCompetitionId(competitionId, 1);
        for (int i = 2; i <= quarter; i++) {
            brandOfRivals.addAll(marketingMapper.selectByCompetitionId(competitionId, i));
        }
        // 新建HashMap和List,hashMap的key为公司名称，value为产品名称和组件详情
        HashMap<String, List<Component>> hashMap = new HashMap<>();
        List<Component> list;
        List<String> list1;
        Component component;
        // 获取组件信息
        for (BrandOfRival brandOfRival : brandOfRivals) {
            component = new Component();
            list = new ArrayList<>();
            list1 = new ArrayList<>();
            // 获取组件编号列表
            String details = brandOfRival.getDetail();
            String[] str = details.split(",");
            for (String str1 : str) {
                // 将编号转换为整数
                int detailId = Integer.parseInt(str1);
                // 根据组件编号得到组件的详细描述
                ProductInfo productInfo = marketingMapper.selectProductDetail(detailId);
                String detail = productInfo.getDetail();
                if (productInfo.getTitle().equals("必备")) {
                    component.setBibei(detail);
                } else if (productInfo.getTitle().equals("运营商")) {
                    component.setYunying(detail);
                } else if (productInfo.getTitle().equals("蓝牙")) {
                    component.setLanya(detail);
                } else if (productInfo.getTitle().equals("屏幕尺寸")) {
                    component.setSize(detail);
                } else if (productInfo.getTitle().equals("触控方式")) {
                    component.setChukong(detail);
                } else if (productInfo.getTitle().equals("处理器速度")) {
                    component.setCpu(detail);
                } else if (productInfo.getTitle().equals("机身容量")) {
                    component.setJishen(detail);
                } else if (productInfo.getTitle().equals("拍照像素")) {
                    component.setXiangsu(detail);
                } else if (productInfo.getTitle().equals("机身特性")) {
                    component.setSpecial(detail);
                } else if (productInfo.getTitle().equals("电池容量")) {
                    component.setDianchi(detail);
                } else if (productInfo.getTitle().equals("其他功能")) {
                    list1.add(detail + "  ");
                    component.setOthers(list1);
                }
            }
            component.setProductName(brandOfRival.getBrandName());
            // 设置当前遍历的公司名为key,然后将信息存入hashMap
            if (hashMap.size() != 0 && hashMap.containsKey(brandOfRival.getCompanyName())) {
                hashMap.get(brandOfRival.getCompanyName()).add(component);
            } else {
                // 添加组件详情到list中
                list.add(component);
                hashMap.put(brandOfRival.getCompanyName(), list);
            }
        }

        modelAndView.addObject("brandDetail", hashMap);
        modelAndView.setViewName("brandOfRival");
        return modelAndView;
    }

    @MethodLog(description = "竞争对手的价格")
    @RequestMapping("/priceOfRival.do")
    public ModelAndView priceOfRival(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int competitionId = (int) request.getSession().getAttribute("competitionId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        //当前季度之前所有竞争对手的价格
        List<ProductPrice> priceList = marketingMapper.selectProductPrice(competitionId, 1);
        for (int i = 2; i <= quarter; i++) {
            priceList.addAll(marketingMapper.selectProductPrice(competitionId, i));
        }
        List<ProductPrice> list;
        HashMap<String, List<ProductPrice>> hashMap = new HashMap<>();
        for (ProductPrice productPrice : priceList) {
            list = new ArrayList<>();

            int companyId = productPrice.getCompanyId();
            String companyName = marketingMapper.selectCompanyName(companyId);
            productPrice.setCompanyName(companyName);

            int productId = productPrice.getProductId();
            String productName = marketingMapper.selectProductName(productId);
            productPrice.setProductName(productName);

            if (productPrice.getPrice() == null) {
                productPrice.setPrice(0);
            }

            if (productPrice.getYouji() == null) {
                productPrice.setYouji(0);
            }
            if (hashMap.size() != 0 && hashMap.containsKey(companyName)) {
                hashMap.get(companyName).add(productPrice);
            } else {
                list.add(productPrice);
                hashMap.put(companyName, list);
            }
        }
        modelAndView.addObject("productPrice", hashMap);
        modelAndView.setViewName("priceOfRival");
        return modelAndView;
    }

    @MethodLog(description = "生产成本")
    @RequestMapping("/priceOfPro.do")
    public ModelAndView priceOfPro(HttpServletRequest request) {
        //获取第二季度的生产成本信息
        ModelAndView modelAndView = new ModelAndView();
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int companyId = (Integer) request.getSession().getAttribute("companyId");
        List<CompanyProduct> companyProducts = marketingMapper.selectProductByCompanyIdAndQuarter(companyId, quarter - 1);
        modelAndView.addObject("companyProducts", companyProducts);
        modelAndView.setViewName("priceOfPro");
        return modelAndView;
    }


    @MethodLog(description = "竞争对手的广告")
    @RequestMapping("/adOfRival1.do")
    public ModelAndView adOfRival1(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        // 定义一个string数组存放广告详情信息
        String[] strings = {"邮寄返款-特价", "高速处理，低任务时耗", "附赠多种应用软件", "市场上最强劲的手机", "市场上最高的数据存储容量", "当地销售及售后服务",
                "更大屏幕，减少视疲劳", "使用方便，设计精简", "图像细致，分辨率高"};
        // 获取竞赛ID
        int competitionId = (int) request.getSession().getAttribute("competitionId");
        //获取季度
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        // 获取产品的广告信息
        List<AdDetail> list = marketingMapper.selectAdDetailByCompetitionId(competitionId, 1);
        for (int i = 2; i <= quarter; i++) {
            list.addAll(marketingMapper.selectAdDetailByCompetitionId(competitionId, i));
        }
        HashMap<String, List<AdDetail>> hashMap = new HashMap<String, List<AdDetail>>();
        List<AdDetail> list1;
        for (AdDetail adDetail : list) {
            list1 = new ArrayList<>();

            int companyId = adDetail.getCompanyId();
            String companyName = marketingMapper.selectCompanyName(companyId);
            adDetail.setCompanyName(companyName);

            String advertiseId = adDetail.getAdvertiseId();
            String[] adIdStr = advertiseId.split(",");
            // 遍历广告详情ID，根据ID查询广告详情
            for (String str : adIdStr) {
                int id = Integer.parseInt(str);
                String detail = marketingMapper.selectAdDetailById(id);
                if (detail.equals(strings[0])) {
                    adDetail.setDetail1(detail);
                } else if (detail.equals(strings[1])) {
                    adDetail.setDetail2(detail);
                } else if (detail.equals(strings[2])) {
                    adDetail.setDetail3(detail);
                } else if (detail.equals(strings[3])) {
                    adDetail.setDetail4(detail);
                } else if (detail.equals(strings[4])) {
                    adDetail.setDetail5(detail);
                } else if (detail.equals(strings[5])) {
                    adDetail.setDetail6(detail);
                } else if (detail.equals(strings[6])) {
                    adDetail.setDetail7(detail);
                } else if (detail.equals(strings[7])) {
                    adDetail.setDetail8(detail);
                } else if (detail.equals(strings[8])) {
                    adDetail.setDetail9(detail);
                }
            }
            if (hashMap.size() != 0 && hashMap.containsKey(companyName)) {
                hashMap.get(companyName).add(adDetail);
            } else {
                list1.add(adDetail);
                hashMap.put(companyName, list1);
            }
        }
        modelAndView.addObject("adDetailInfo", hashMap);
        modelAndView.setViewName("adOfRival");
        return modelAndView;
    }

    @MethodLog(description = "设计广告")
    @RequestMapping(value = "/adDesign.do")
    public ModelAndView adDesign(HttpServletRequest request) throws UnsupportedEncodingException {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        //如果有要删除的广告
        String adName = request.getParameter("adName");
        if (request.getParameter("productId") != null && adName != null) {
            adName = new String(adName.getBytes("iso-8859-1"), "utf-8");
            System.out.println("删除1123");
            int productId = Integer.parseInt(request.getParameter("productId"));
            marketingMapper.deleteAdByIdAndName(productId, adName);
        }
        // 查询当前公司上个季度的所有广告
        List<CompanyAd> companyAds = marketingMapper.selectAdByCompanyIdAndQuarter(companyId, 1);
        for (int i = 2; i <= quarter; i++) {
            companyAds.addAll(marketingMapper.selectAdByCompanyIdAndQuarter(companyId, i));
        }
        // 添加当前季度的广告
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyAds", companyAds);
        modelAndView.setViewName("adDesign");
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("quarter", quarter);
        return modelAndView;
    }

    @MethodLog(description = "查看广告详情")
    @RequestMapping(value = "/showAdDetail.do")
    public ModelAndView showAdDetail(HttpServletRequest request) throws UnsupportedEncodingException {

        ModelAndView modelAndView = new ModelAndView();
        int productId = Integer.parseInt(request.getParameter("productId"));
        String adName = new String(request.getParameter("adName").getBytes("iso-8859-1"), "utf-8");
//        String adName = request.getParameter("adName");
        AdPojo adPojo = marketingMapper.selectAdByProductIdAndAdName(productId, adName);
        System.out.println(adPojo);
        // 获取产品广告详情编号
        String[] adId = adPojo.getAdvertiseId().split(",");
        List<String> list = new ArrayList<>();
        for (String str : adId) {
            int id = Integer.parseInt(str);
            String detail = marketingMapper.selectAdDetailById(id);
            list.add(detail);
        }
        modelAndView.addObject("adInfo", adPojo);
        modelAndView.addObject("detailList", list);
        modelAndView.setViewName("queryAd");
        return modelAndView;
    }

    @MethodLog(description = "核查广告语")
    @RequestMapping(value = "/adCheck.do")
    public ModelAndView adCheck(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        // 查询当前公司当前季度的所有广告名称
        List<CheckResult> list = marketingMapper.selectAllAdInfo(companyId, quarter);
        for (CheckResult checkResult : list) {
            if ("mmp".equals(checkResult.getAdvertiseName())) {
                checkResult.setResult("检查不通过");
            } else {
                checkResult.setResult("检查通过");
            }
        }
        modelAndView.addObject("checkResultList", list);
        modelAndView.setViewName("adCheck");
        return modelAndView;
    }

    @MethodLog(description = "可销售品牌")
    @RequestMapping("/priceAndSale.do")
    public ModelAndView priceAndSale(HttpServletRequest request, int quarter) {
        ModelAndView modelAndView = new ModelAndView();
        int companyId = (Integer) request.getSession().getAttribute("companyId");
        quarter = Integer.parseInt(request.getParameter("quarter"));
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        int currentQuarter=(int)request.getSession().getAttribute("currentQuarter");

        // 查询当前季度价格及销售信息
        List<PriceSale> list = marketingMapper.selectPriceAndSale(companyId, quarter);
        for (PriceSale priceSale : list) {
            String productName = marketingMapper.selectProductName1(priceSale.getCompanyId(), priceSale.getProductId());
            priceSale.setProductName(productName);
        }
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("priceList", list);
        modelAndView.setViewName("priceAndSale");
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("isSubmit", isSubmit);
        return modelAndView;
    }


    @MethodLog(description = "可销售品牌决策")
    @RequestMapping("/operatePriceAndSale.do")
    public ModelAndView priceAndSale1(HttpServletRequest request, Object valueF, Object valueT) {
        ModelAndView modelAndView = new ModelAndView();
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        valueF = request.getParameter("valueF");
        valueT = request.getParameter("valueT");
        int productId = 0;
        String[] values;
        if (valueT != null & !("".equals(valueT))) {
            //设置产品可销售
            values = valueT.toString().split(",");
            for (String str : values) {
                productId = Integer.parseInt(str);
                //设置该产品的销售标志为1
                for (int i = quarter; i <= 5; i++) {
                    marketingMapper.updateProductPrice(productId, 1, i);
                }
            }
        } else {
            System.out.println("所有产品都不可销售");
        }
        if (valueF != null & !("".equals(valueF))) {
            //使产品不可销售
            values = valueF.toString().split(",");
            for (String str : values) {
                productId = Integer.parseInt(str);
                //设置该产品的销售标志为0
                for (int i = quarter; i <= 5; i++) {
                    marketingMapper.updateProductPrice(productId, 0, i);
                }
            }
        } else {
            System.out.println("所有产品都可以销售");
        }
        modelAndView = priceAndSale(request, quarter);
        return modelAndView;
    }

}
