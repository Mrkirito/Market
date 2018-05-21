package com.hangjia.bxj.mvc.controller.report;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hangjia.bxj.dao.StatisticsDataMapper;
import com.hangjia.bxj.dao.tjgl.KmhStatisticsDataMapper;
import com.hangjia.bxj.dao.tjgl.XrtStatisticsDataMapper;
import com.hangjia.bxj.model.tjgl.KmhStatisticsData;
import com.hangjia.bxj.model.tjgl.XrtStatisticsData;
import com.hangjia.bxj.vo.StatisticsDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.BxjAppProductDataMapper;
import com.hangjia.bxj.model.BxjAppProductData;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.report.ProductDataReportQuery;
import com.hangjia.bxj.vo.ProductDetailVo;

@Controller
@RequestMapping("/report")
public class ProductDataReportController extends BaseModule {


    @Autowired
    private BxjAppProductDataMapper appProductDataMapper;
    @Autowired
    private XrtStatisticsDataMapper xrtStatisticsDataMapper;
    @Autowired
    private KmhStatisticsDataMapper kmhStatisticsDataMapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping("productDataReport.jhtml")
    public ModelAndView productDataReport() {
        ModelAndView modelAndView = new ModelAndView("report/productDataReport");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String date = dateFormat.format(new Date());
        modelAndView.addObject("queryMonth", date);
        return modelAndView;
    }

    @RequestMapping("queryProductDataReportList.json")
    @ResponseBody
    public Result queryProductDataReportList(String date) {
        Result result = new Result();
        try {
            List<ProductDetailVo> productDetailVos = appProductDataMapper.selectProdectDataDetail();
            List<ProductDetailVo> xrtproductDetailVos = appProductDataMapper.selectXrtProdectDataDetail();
            List<ProductDetailVo> kmhproductDetailVos = appProductDataMapper.selectKmhProdectDataDetail();
            ProductDataReportQuery query = new ProductDataReportQuery();
            query.setQueryMonth(date);
            List<BxjAppProductData> appProductDatas = appProductDataMapper.queryAppProductDataPage(query);
            List<XrtStatisticsData> xrtProductDatas = xrtStatisticsDataMapper.queryXrtDataPage(query);
            List<KmhStatisticsData> kmhProductDatas = kmhStatisticsDataMapper.queryKmhDataPage(query);

            Class clazz = ProductDetailVo.class;
            Class clazz2 = BxjAppProductData.class;
            for (ProductDetailVo productDetailVo : productDetailVos) {
                String[] fields = productDetailVo.getDataName().split(",");
                for (int i = 0; i < appProductDatas.size(); i++) {
                    BxjAppProductData appProductData = appProductDatas.get(i);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d");
                    Method method = clazz.getMethod("setColumn" + dateFormat.format(appProductData.getDataDate()), String.class);
                    method.setAccessible(true);
                    String values = "";
                    int count = 1;
                    for (String field : fields) {
                        Object value = 0;
                        try {
                            Method method2 = clazz2.getMethod("get" + toUpperCaseFirstOne(field));
                            value = method2.invoke(appProductData);
                        } catch (Exception e) {
                        }
                        if (value == null)
                            value = "暂无";
                        else
                            value = value + "";
                        if (count < fields.length)
                            values += value + "<br/><hr>";
                        else
                            values += value;
                        count++;
                    }
                    method.invoke(productDetailVo, values);
                }
            }

            Class clazzxrt = XrtStatisticsData.class;
            for (ProductDetailVo productDetailVo : xrtproductDetailVos) {
                String[] fields = productDetailVo.getDataName().split(",");
                for (int i = 0; i < xrtProductDatas.size(); i++) {
                    XrtStatisticsData statisticsDataVo = xrtProductDatas.get(i);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d");
                    Method method = clazz.getMethod("setColumn" + dateFormat.format(statisticsDataVo.getDataTime()), String.class);
                    method.setAccessible(true);
                    String values = "";
                    int count = 1;
                    for (String field : fields) {
                        Object value = 0;
                        try {
                            Method method2 = clazzxrt.getMethod("get" + toUpperCaseFirstOne(field));
                            value = method2.invoke(statisticsDataVo);
                        } catch (Exception e) {
                        }
                        if (value == null)
                            value = "暂无";
                        else
                            value = value + "";
                        if (count < fields.length)
                            values += value + "<br/><hr>";
                        else
                            values += value;
                        count++;
                    }
                    method.invoke(productDetailVo, values);
                }
            }

            Class clazzkmh = KmhStatisticsData.class;
            for (ProductDetailVo productDetailVo : kmhproductDetailVos) {
                String[] fields = productDetailVo.getDataName().split(",");
                for (int i = 0; i < kmhProductDatas.size(); i++) {
                    KmhStatisticsData statisticsDataVo = kmhProductDatas.get(i);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d");
                    Method method = clazz.getMethod("setColumn" + dateFormat.format(statisticsDataVo.getDataTime()), String.class);
                    method.setAccessible(true);
                    String values = "";
                    int count = 1;
                    for (String field : fields) {
                        Object value = 0;
                        try {
                            Method method2 = clazzkmh.getMethod("get" + toUpperCaseFirstOne(field));
                            value = method2.invoke(statisticsDataVo);
                        } catch (Exception e) {
                        }
                        if (value == null)
                            value = "暂无";
                        else
                            value = value + "";
                        if (count < fields.length)
                            values += value + "<br/><hr>";
                        else
                            values += value;
                        count++;
                    }
                    method.invoke(productDetailVo, values);
                }
            }
            productDetailVos.addAll(xrtproductDetailVos);
            productDetailVos.addAll(kmhproductDetailVos);
            result.setModel(productDetailVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    /**
     * 新增短信模板
     *
     * @param appProductData
     * @return
     */
    @RequestMapping("addProductData.json")
    @ResponseBody
    public Result addProductData(@ModelAttribute BxjAppProductData appProductData) {
        Result result = new Result();
        ProductDataReportQuery query = new ProductDataReportQuery();
        query.setDataDate(appProductData.getDataDate());
        int productCount = appProductDataMapper.queryAppProductDataCount(query);
        if (productCount > 0) {
            result.setSuccess(false);
            result.setMsg("当日数据已存在");
            return result;
        }
        int count = appProductDataMapper.insertSelective(appProductData);
        if (count < 1) {
            result.setSuccess(false);
            result.setMsg("新增失败");
            return result;
        }
        return result;
    }
    
    
    @RequestMapping("queryMarkDataCharts.json")
    @ResponseBody
    public Result queryMarkDataCharts(String date){
        Result result = new Result();
//        ProductDataReportQuery query = new ProductDataReportQuery();
//        query.setQueryMonth(date);
//        List<BxjAppProductData> appProductDatas = appProductDataMapper.queryAppProductDataPage(query);
        List<BxjAppProductData> appProductDatas =appProductDataMapper.queryALLAppProductDataPage();
        result.setModel(appProductDatas);
        return result;
    }
}
