package com.hangjia.bxj.mvc.controller.report;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.AppExtendDataMapper;
import com.hangjia.bxj.dao.AppExtendDetailDataMapper;
import com.hangjia.bxj.dao.BxjAppProductDataMapper;
import com.hangjia.bxj.dao.BxjAppWchartDataMapper;
import com.hangjia.bxj.model.AppExtendData;
import com.hangjia.bxj.model.AppExtendDetailData;
import com.hangjia.bxj.model.BxjAppProductData;
import com.hangjia.bxj.model.BxjAppWchartData;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.report.ProductDataReportQuery;
import com.hangjia.bxj.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class BxjAppWchartDataReportController extends BaseModule {


    @Autowired
    private BxjAppWchartDataMapper appProductDataMapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping("wcharttDataReport.jhtml")
    public ModelAndView productDataReport() {
        ModelAndView modelAndView = new ModelAndView("report/wchartDataReport");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String date = dateFormat.format(new Date());
        modelAndView.addObject("queryMonth", date);
        return modelAndView;
    }

    @RequestMapping("queryWchartDataReportList.json")
    @ResponseBody
    public Result queryProductDataReportList(String date) {
    	  Result result = new Result();
          try {
              List<ProductDetailVo> productDetailVos = appProductDataMapper.selectTable();
              List<BxjAppWchartData> appProductDatas = appProductDataMapper.getBxjAppWchartDatasByMonth(date);
              Class clazz = ProductDetailVo.class;
              Class clazz2 = BxjAppWchartData.class;
              for (ProductDetailVo productDetailVo : productDetailVos) {
                  String[] fields = productDetailVo.getDataName().split(",");
                  for (int i = 0; i < appProductDatas.size(); i++) {
                	  BxjAppWchartData appProductData = appProductDatas.get(i);
                      SimpleDateFormat dateFormat = new SimpleDateFormat("d");
                      Method method = clazz.getMethod("setColumn" + dateFormat.format(appProductData.getDataDate()), String.class);
                      method.setAccessible(true);
                      String values = "";
                      int count = 1;
                      for (String field : fields) {
                          Method method2 = clazz2.getMethod("get" + toUpperCaseFirstOne(field));
                          Object value = method2.invoke(appProductData);
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
    @RequestMapping("addWchartData.json")
    @ResponseBody
    public Result addProductData(BxjAppWchartData appProductData) {
        Result result = new Result();
        int productCount = appProductDataMapper.getBxjAppWchartDatasByDate(new SimpleDateFormat("yyyyMMdd").format(appProductData.getDataDate()));
        if (productCount > 0) {
//            result.setSuccess(false);
//            result.setMsg("当日数据已存在");
            appProductDataMapper.updateByPrimaryKeySelective(appProductData);
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


    @RequestMapping("ajaxDisplayInfo.json")
    public @ResponseBody Result ajaxDisplayInfo(String date){
        BxjAppWchartData data = appProductDataMapper.getAppWchartDatasByDate(date);
        Result result = new Result();
        if (null !=data) {
            result.setModel(data);
        }else{
            result.setSuccess(false);
            result.setMsg("nodata");
        }
            return result;
    }

    @RequestMapping("queryWchartMarkDataCharts.json")
    @ResponseBody
    public Result queryMarkDataCharts(String date){
        Result result = new Result();
//        List<BxjAppWchartData> appProductDatas = appProductDataMapper.getBxjAppWchartDatasByMonth(date);
        List<BxjAppWchartData> appProductDatas = appProductDataMapper.getAllBxjAppWchartDatas();
        result.setModel(appProductDatas);
        return result;
    }
}
