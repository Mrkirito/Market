package cn.usst.market.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.mapper.FinalCheckMapper;

@Controller
public class TestController {

    @Autowired
    private FinalCheckMapper finalCheckMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping("/testSelect.do")
    public String test(HttpServletRequest reuqest) {
        String select = reuqest.getParameter("select");
        reuqest.getSession().setAttribute("select", select);
        return "test1";
    }

    @RequestMapping("/testSalary.do")
    public String test1(HttpServletRequest reuqest) {
        String welfare = reuqest.getParameter("welfare");
        reuqest.getSession().setAttribute("welfare", welfare);
        return "testSalesSalary";
    }

    /**
     * 最终检查
     *
     * @param request
     * @return
     */
    @RequestMapping("/finalCheck.do")
    public ModelAndView finalCheck(HttpServletRequest request) {
        System.out.println("final  check  do >>>>>>>>>");
        Map<Integer, String> hashMap = new HashMap();
        Boolean flag = true;
        int productSaleNum = 0;
        int isNetNum = 0;
        int companyId = ((Integer) request.getSession().getAttribute("companyId"));
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        List<ProductPrice> productPriceList = finalCheckMapper.selectProductByCompanyIdAndQuarter(companyId, quarter);
        if (quarter != 1) {
            if (productPriceList != null && productPriceList.size() != 0) {
                for (ProductPrice productPrice : productPriceList) {
                    int isSale = productPrice.getIsSale();
                    int saleman = productPrice.getPrice();
                    if (isSale == 0) {
                        productSaleNum++;
                    }
                    if (isSale == 1 && saleman == 0) {
                        hashMap.put(2, "可销售品牌的价格为0");
                        flag = false;
                    }
                }
                if (productSaleNum == productPriceList.size()) {
                    hashMap.put(1, "所有产品都不可销售");
                    flag = false;
                }
            } else {
                hashMap.put(1, "没有产品可以销售");
                flag = false;
            }

            SalaryDO salaryDO = this.finalCheckMapper.selectSalary(companyId, quarter);
            if (salaryDO == null) {
                hashMap.put(3, "销售人员和工厂工人工资都为0");
                flag = false;
            } else {
                if (salaryDO.getSalesSalary() == 0) {
                    hashMap.put(4, "销售人员工资为0");
                    flag = false;
                }

                if (salaryDO.getWorkersSalary() == 0) {
                    hashMap.put(5, "工厂工人工资为0");
                    flag = false;
                }
            }

            List<CompanyMarket> companyMarketList = finalCheckMapper.selectCompanyMarketByCompanyIdAndQuarter(companyId, quarter);
            if (companyMarketList != null && companyMarketList.size() != 0) {
                for (CompanyMarket companyMarket : companyMarketList) {
                    int afterman = companyMarket.getIsPhy();
                    if (afterman == 0) {
                        isNetNum++;
                    }
                }
                if (isNetNum == companyMarketList.size()) {
                    hashMap.put(7, "实体销售中心数量为0");
                    flag = false;
                }
            } else {
                hashMap.put(6, "销售渠道为空");
                flag = false;
            }

            CapacityPojo capacityPojo = finalCheckMapper.selectCapacityByCompanyId(companyId, quarter);
            if (capacityPojo == null) {
                hashMap.put(8, "运行产能为空");
                flag = false;
            } else {
                int saleman = capacityPojo.getCapacityNow();
                if (saleman == 0) {
                    hashMap.put(9, "运行产能为0");
                    flag = false;
                }
            }

            int saleman = 0;
            int afterman = 0;
            int salemanonline = 0;
            int aftermanonline = 0;
            List<CompanyMarket> companyMarketList1 = finalCheckMapper.selectCompanyMarketByCompanyIdAndQuarter(companyId, quarter);
            if (companyMarketList1 != null && companyMarketList1.size() != 0) {
                for (CompanyMarket companyMarket : companyMarketList1) {
                    if (!companyMarket.getMarketId().equals("1")) {
                        String[] marketId = companyMarket.getMarketId().split(",");
                        for (String str : marketId) {
                            int marketIdInt = Integer.parseInt(str);
                            List<HirePeople> hirePeopleList = finalCheckMapper.selectHirePeopleByCompanyIdAndQuarter(companyId, quarter);
                            for (HirePeople hirePeople : hirePeopleList) {
                                if (hirePeople != null) {
                                    if (marketIdInt != hirePeople.getMarketId()) {
                                        continue;
                                    }
                                    saleman = hirePeople.getSaleman();
                                    afterman = hirePeople.getAfterSale();
                                    int total = saleman + afterman;
                                    if (total == 0) {
                                        hashMap.put(10, "实体店雇佣员工数为0");
                                        flag = false;
                                    }

                                } else {
                                    hashMap.put(11, "实体店雇佣员工数为0");
                                    flag = false;
                                }
                            }
                        }

                    } else {
                        if (companyMarket.getIsPhy() == 0) {
                            HirePeopleOnline hirePeopleOnline = finalCheckMapper.selectHirePeopleOnlineByCompanyIdAndQuarter(companyId, quarter);
                            if (hirePeopleOnline != null) {
                                salemanonline = hirePeopleOnline.getSaleman();
                                aftermanonline = hirePeopleOnline.getAfterSale();
                                int totalonline = salemanonline + aftermanonline;
                                if (totalonline == 0) {
                                    hashMap.put(12, "网店雇佣员工数为0");
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }


        } else {
            CompanyCapacity companyCapacity = companyMapper.selectCompanyCapacity(companyId, quarter);
            if (companyCapacity == null) {
                hashMap.put(14, "固定产能为空");
                flag = false;
            } else {
                int capacity = companyCapacity.getCapacityNow() + companyCapacity.getCapacityAdd();
                if (capacity == 0) {
                    hashMap.put(15, "固定产能为0");
                    flag = false;
                }
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hashMap", hashMap);
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("quarter", quarter);
        modelAndView.setViewName("testFinalCheck");
        return modelAndView;
    }

    @RequestMapping("/finalSubmit.do")
    public String finalSubmit(HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String currentTime = df.format(new Date());
        int companyId = (int) request.getSession().getAttribute("companyId");
        Boolean b = (Boolean) request.getSession().getAttribute("pass");
        if (b) {
            finalCheckMapper.updateCompanyQuaterTime(companyId, currentTime);
            return "finalSubmit1";
        } else {
            return "finalSubmit2";
        }
    }
}
