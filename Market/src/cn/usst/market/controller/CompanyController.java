package cn.usst.market.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.usst.market.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.annotation.MethodLog;
import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.mapper.MarketingMapper;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionService;
import cn.usst.market.service.MemberService;
import cn.usst.market.service.SearchService;
import cn.usst.market.service.StaticInfoService;
import cn.usst.market.service.TeacherService;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private StaticInfoService staticInfoService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MarketingMapper marketingMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private SearchService SS;

    @MethodLog(description = "修改公司名字")
    @RequestMapping(value = "/UpdateCompanyName.do")
    public ModelAndView UpdateCompanyName(HttpServletRequest request) {


        System.out.println("success");
        int id = (int) request.getSession().getAttribute("companyId");
        System.out.println(id);
        Company company = new Company();
        company.setId(id);
        // 获取表单公司名
        String name = request.getParameter("name");
        System.out.println(name);
        // 获取原公司名
        Company fomer_com = companyService.selectCompanyById(id);
        String former_name = fomer_com.getName();
        System.out.println(former_name);
        System.out.println(former_name.length());
        String info = "";

        if (name != null) {
            // 判断公司名长度
            if (name.length() < 4 || name.length() > 12) {
                info = "公司名称必须在4到12个字符！";
            } else {
                // 检查公司名是否存在
                Company flag = companyService.checkCompanyNameExist(name);
                if (flag != null) {
                    info = "该公司名已存在，请重新输入！";
                } else {
                    companyService.updateCompanyNameById(id, name);
                    info = "提交成功";
                }
                System.out.println(flag);
            }

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("info", info);
        modelAndView.addObject("", info);
        modelAndView.addObject("name", name);
        modelAndView.addObject("former_name", former_name);
        modelAndView.setViewName("creatCorporation");
        return modelAndView;
    }

    @RequestMapping(value = "/showCompanyByCompetitionId.do")
    public ModelAndView showCompanyByCompetitionId(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        System.out.println("竞赛id:" + id);
        List<Company> list = companyService.showCompanyByCompetitionId(id);

        Competition competition = competitionService.findCompetitionById(id);

        List<Member> memberList = null;
        TeacherQueryVo teacherQueryVo = null;
        List<TeacherQueryVo> companyResult = new ArrayList<TeacherQueryVo>();

        for (Company company : list) {
            teacherQueryVo = new TeacherQueryVo();
            int companyId = company.getId();
            memberList = memberService.showAllMemberByComapnyId(companyId);
            teacherQueryVo.setCompany(company);
            if (memberList != null) {

                teacherQueryVo.setMemberList(memberList);
            }
            CompanyQuarterTime companyQT = competitionService.findCompanyQuarterTime(companyId, competition.getCurrentQuarter());
            if (companyQT != null) {
                teacherQueryVo.setCompanyQuarterTime(companyQT);
            }
            companyResult.add(teacherQueryVo);
        }

        System.out.println(list);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("competition", competition);
        modelAndView.addObject("companyResult", companyResult);
        modelAndView.addObject("companyList", list);
        modelAndView.setViewName("teacher_competition_content");
        return modelAndView;
    }

    @MethodLog(description = "查看目标策略操作")
    @RequestMapping(value = "/strategyInfo.do")
    public ModelAndView strategyInfo(HttpServletRequest request) {

        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");

        // 查询title
        List<String> titleList = companyService.countStrategyTitle();
        // 根据title遍历存入map
        Map<String, List<StrategyInfo>> result = new HashMap<String, List<StrategyInfo>>();
        for (int i = 0; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
            result.put(titleList.get(i), companyService.showStrategyInfoBytitle(titleList.get(i)));
        }

        // 查询公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        System.out.println(company_id);

        CompanyStrategy companyStrategy = companyService.selectCompanyStrategy(company_id, quarter);

        CompanyStrategy companyStrategyPro = companyService.selectCompanyStrategy(company_id, quarter - 1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", result);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("quarter", quarter);

        // 若有公司该季度记录，则添加该对象

        // 先找上季度的策略值
        if (companyStrategyPro != null) {
            modelAndView.addObject("companyStrategy", companyStrategyPro);
        }

        // 再找本季度的策略值 若不为空 则 覆盖 上季度
        if (companyStrategy != null) {
            modelAndView.addObject("companyStrategy", companyStrategy);
        }

        modelAndView.setViewName("strategy");
        return modelAndView;
    }

    @RequestMapping(value = "/companyRuleInfo.do")
    public ModelAndView companyRuleInfo(HttpServletRequest request) {



        // 得到title数组
        List<String> titleList = companyService.countCompanyRuleTitle();

        System.out.println(titleList);

        Map<String, List<StrategyInfo>> result = new HashMap<String, List<StrategyInfo>>();

        for (int i = 0; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
            result.put(titleList.get(i), companyService.showCompanyRuleInfoBytitle(titleList.get(i)));
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("result", result);

        // 若有公司该季度记录，则添加该对象
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = companyMapper.selectIssubmit(company_id, quarter);
        CompanyRule companyRule = companyService.selectCompanyRule(company_id, quarter);
        CompanyRule companyRulePro = companyService.selectCompanyRule(company_id, quarter - 1);

        if (companyRulePro != null) {
            modelAndView.addObject("companyRule", companyRulePro);
        }

        if (companyRule != null) {
            modelAndView.addObject("companyRule", companyRule);
        }
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.setViewName("company_rule");
        return modelAndView;
    }

    @MethodLog(description = "修改团队规则")
    @RequestMapping("/updateCompanyRule.do")
    public ModelAndView updateCompanyRule(HttpServletRequest request, CompanyRule companyRule) throws Exception {
        System.out.println("团队规则控制台。。。");
        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.valueOf(request.getParameter("quarter"));
        // 获取选中项数组
        String[] content = request.getParameterValues("团队规则");
        // 数组转换成字符串存入数据库
        String ruleId = "";
        for (String str : content) {
            ruleId += str + ",";
        }
        String str = request.getParameter("决策制定流程");
        ruleId += str;

        System.out.println("团队规则：" + ruleId);

        // 由于与数据库字段名不匹配，需要手动注入
        companyRule.setCompanyId(company_id);
        companyRule.setRuleId(ruleId);
        companyRule.setQuarter(quarter);

        // 判断
        CompanyRule com = companyService.selectCompanyRule(company_id, quarter);
        if (com != null) {
            // 数据库更新操作
            companyService.updateCompanyRule(companyRule);

        } else {
            // 数据库添加操作
            companyService.insertCompanyRule(companyRule);

        }
        // 返回jsp
        ModelAndView modelAndView = companyRuleInfo(request);
        modelAndView.setViewName("company_rule");
        return modelAndView;

    }

    @MethodLog(description = "查看团队目标操作")
    @RequestMapping(value = "/personalGoalInfo.do")
    public ModelAndView personalGoalInfo(HttpServletRequest request) {

        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int quarter = Integer.valueOf(request.getParameter("quarter").trim());
        Integer companyId = (int) request.getSession().getAttribute("companyId");
        int isSubmit = companyMapper.selectIssubmit(companyId, quarter);
        // 得到title数组
        List<String> titleList = companyService.countPersonalGoalTitle();

        Map<String, List<StrategyInfo>> result = new HashMap<String, List<StrategyInfo>>();

        for (int i = 0; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
            result.put(titleList.get(i), companyService.showPersonalGoalInfoBytitle(titleList.get(i)));
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("result", result);

        // 若有公司该季度记录，则添加该对象
        int company_id = (int) request.getSession().getAttribute("companyId");
        CompanyPersonGoal companyPersonGoal = companyService.selectCompanyPersonGoal(company_id, quarter);

        if (companyPersonGoal != null) {
            System.out.println("有数据");
            modelAndView.addObject("companyPersonGoal", companyPersonGoal);
        }
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.setViewName("personal_goal");
        return modelAndView;
    }

    @MethodLog(description = "修改个人目标")
    @RequestMapping("/updateCompanyPersonGoal.do")
    public ModelAndView updateCompanyPersonGoal(HttpServletRequest request, CompanyPersonGoal companyPersonGoal)
            throws Exception {
        System.out.println("个人目标控制台。。。");
        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.valueOf(request.getParameter("quarter").trim());
        // 获取选中项数组
        String[] content = request.getParameterValues("11");
        // 数组转换成字符串存入数据库
        String goalId = "";
        for (String str : content) {
            goalId += str + ",";
        }

        System.out.println("个人目标：" + goalId);

        // 由于与数据库字段名不匹配，需要手动注入
        companyPersonGoal.setCompanyId(company_id);
        companyPersonGoal.setGoalId(goalId);
        companyPersonGoal.setQuarter(quarter);

        // 判断
        CompanyPersonGoal com = companyService.selectCompanyPersonGoal(company_id, quarter);
        if (com != null) {
            // 数据库更新操作
            companyService.updateCompanyPersonGoal(companyPersonGoal);

        } else {
            // 数据库添加操作
            companyService.insertCompanyPersonGoal(companyPersonGoal);

        }
        // 返回jsp
        ModelAndView modelAndView = personalGoalInfo(request);
        modelAndView.setViewName("personal_goal");
        return modelAndView;

    }


    @MethodLog(description = "查看实体销售操作")
    @RequestMapping(value = "/showMarketInfo.do")
    public ModelAndView showMarketInfo(HttpServletRequest request) {
        Integer companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = companyMapper.selectIssubmit(companyId, quarter);
        System.out.println("showMarketInfo.do called");
        System.out.println("companyId：" + companyId);
        System.out.println("quarter：" + quarter);
        System.out.println("currentQuarter：" + currentQuarter);
        int competitionId = (int) request.getSession().getAttribute("competitionId");
        List<MarketInfo> marketInfoList = staticInfoService.showMarketInfo(competitionId);// 市场信息列表
        HashMap<MarketInfo, Integer> hm = new LinkedHashMap<MarketInfo, Integer>();// 用于判断公司市场
        System.out.println("市场个数：" + marketInfoList.size());
        Integer one = 0, two = 0, three = 0, four = 0, checkedmon = 0;
        for (int i = 0; i < marketInfoList.size(); i++) {
            System.out.println("第" + (i + 1) + "个市场");
            MarketInfo ms = marketInfoList.get(i);
            System.out.println("ms.getId(): " + ms.getId());
            CompanyMarket companyMarket = new CompanyMarket();
            companyMarket.setCompanyId((int) request.getSession().getAttribute("companyId"));
            companyMarket.setIsPhy(1);
            companyMarket.setQuarter(quarter);

            boolean opened = SS.marketIsOpened(companyId, ms.getId());

            System.out.println("set ms.setOpened");
            ms.setOpened("未开设");
            ms.setRented("未租赁");
            if (i == 0)
                if (!opened)
                    one = ms.getOpen();
                else {
                    one = ms.getRent();
                    ms.setOpened("已开设");
                }
            if (i == 1)
                if (!opened)
                    two = ms.getOpen();
                else {
                    two = ms.getRent();
                    ms.setOpened("已开设");
                }
            if (i == 2)
                if (!opened)
                    three = ms.getOpen();
                else {
                    three = ms.getRent();
                    ms.setOpened("已开设");
                }
            if (i == 3)
                if (!opened)
                    four = ms.getOpen();
                else {
                    four = ms.getRent();
                    ms.setOpened("已开设");
                }

            System.out.println(one + "：1");
            System.out.println(two + "：2");
            System.out.println(three + "：3");
            System.out.println(four + ":4");


            List<CompanyMarket> companyMarkets = showCompanymarket(companyMarket);// 该季度公司市场记录

            System.out.println(companyMarkets.size() + "00000000000");// 1000000000

            for (CompanyMarket cm : companyMarkets) {
                String string = cm.getMarketId();
                String[] ss = string.split(",");

                // 判断是否选择了该市场，若选择了，则放入ms中，置值为1，反之为0
                for (String sss : ss) {

                    // System.out.println(sss);
                    if (sss.equals(ms.getId().toString())) {
                        ms.setOpened("已开设");/*2018.5.4让勾选框与“已开设”标签随动*/
                        ms.setRented("已租赁");
                        hm.put(ms, 1);
                    }
                }
            }
            if (!hm.containsKey(ms)) {
                hm.put(ms, 0);
            }
        }
        int count = 0;
        int adjustmoney = 0;
        for (Integer i : hm.values()) {
            System.out.println(i);
            if (i == 1 & count == 0)
                adjustmoney += one;
            if (i == 1 & count == 1)
                adjustmoney += two;
            if (i == 1 & count == 2)
                adjustmoney += three;
            if (i == 1 & count == 3)
                adjustmoney += four;
            count++;
        } // 打印市场的选择值（0或1）
        ModelAndView modelAndView = new ModelAndView();

        Integer EM = 0;

        modelAndView.addObject("one", one);
        modelAndView.addObject("two", two);
        modelAndView.addObject("three", three);
        modelAndView.addObject("four", four);
        modelAndView.addObject("marketInfoList", hm);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("EM", EM);
        modelAndView.addObject("isSubmit", isSubmit);
        //int companyId = (int) request.getSession().getAttribute("companyId");
        modelAndView.addObject("MoneySum", SS.SelectMoneySum(companyId, 6));
        modelAndView.addObject("unadjustedMoneySum", SS.SelectMoneySum(companyId, 6));
        System.out.println("MoneySum:" + SS.SelectMoneySum(companyId, 6));
        System.out.println("unadjustedMoneySum:" + SS.SelectMoneySum(companyId, 6));
        modelAndView.setViewName("physicalStore");
        return modelAndView;
    }

    private List<CompanyMarket> showCompanymarket(CompanyMarket companyMarket) {
        return companyService.showCompanymarket(companyMarket);
    }

    @MethodLog(description = "查看网络销售中心操作")
    @RequestMapping("/showMarketWebInfo.do")
    public ModelAndView showMarketWebInfo(HttpServletRequest request) throws Exception {
        System.out.println("showMarketWebInfo called");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (Integer) request.getSession().getAttribute("currentQuarter");
        int companyId = (Integer) request.getSession().getAttribute("companyId");
        int id = (Integer) request.getSession().getAttribute("competitionId");
        int isSubmit = companyMapper.selectIssubmit(companyId, quarter);
        List<MarketInfo> marketInfoList = companyService.showMarketInfo(id);
        List<OnlineStore> webList = companyService.selectAndCountTotalNeed(marketInfoList);
        CompanyMarket companyMarket = new CompanyMarket();
        companyMarket.setCompanyId(companyId);
        companyMarket.setQuarter(quarter);
        String marketId = "";

        for (int i = 1; i <= marketInfoList.size(); ++i) {
            if (i == marketInfoList.size()) {
                marketId = marketId + i + "";
            } else {
                marketId = marketId + i + ",";
            }
        }

        companyMarket.setMarketId(marketId);
        CompanyMarket companyMarketCurrentQuarter = null;
        int establishOrRent = 0;
        Boolean webLastQuarter = true;
        Boolean webCurrentQuarter = true;
        companyMarketCurrentQuarter = companyService.selectCompanyMarket(companyId, 0, quarter);
        MarketWebOpened marketWebOpened = companyMapper.selectMarketWebOpened(companyId, quarter);
        CompanyMarket companyMarketTemp;
        if (companyMarketCurrentQuarter != null && companyMarketCurrentQuarter.getMarketId() != null && !"".equals(companyMarketCurrentQuarter.getMarketId())) {
            for (int i = 1; i < quarter; ++i) {
                companyMarketTemp = companyService.selectCompanyMarket(companyId, 0, i);
                if (companyMarketTemp != null) {
                    establishOrRent++;
                }
            }

            if (establishOrRent == 0) {
                System.out.println("第一次建立了网络销售中心，本季度只计算开设费用");
                webCurrentQuarter = false;
                if (quarter == currentQuarter) {
                    if (marketWebOpened == null) {
                        companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, -webList.get(0).getOpen());
                    } else {
                        companyMapper.updateMarketWebOpened(companyId, quarter, -webList.get(0).getOpen());
                    }
                } else {
                    if (marketWebOpened == null) {
                        companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                    } else {
                        companyMapper.updateMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                    }
                }
            } else {
                System.out.println("已经建立了网络销售中心，本季度只计算租赁费用");
                if (marketWebOpened == null) {
                    companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                } else {
                    companyMapper.updateMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                }
            }
            companyMarket.setIsPhy(0);
        } else {
            for (int i = 1; i < quarter; ++i) {
                companyMarketTemp = companyService.selectCompanyMarket(companyId, 0, i);
                if (companyMarketTemp != null) {
                    establishOrRent++;
                }
            }
            if (establishOrRent == 0) {
                webLastQuarter = false;
                webCurrentQuarter = false;
            } else {
                webCurrentQuarter = false;
            }
            System.out.println("没有建立或租赁网络销售中心,费用为0");
            if (marketWebOpened == null) {
                companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, 0);
            } else {
                companyMapper.updateMarketWebOpened(companyId, quarter, 0);
            }
            companyMarket.setIsPhy(2);
        }
        MarketWebOpened marketWebOpenedNew = companyMapper.selectMarketWebOpened(companyId, quarter);
        int cost = 0;
        if (marketWebOpenedNew != null) {
            cost = marketWebOpenedNew.getCost();
        }

        CompanyDecision companyDecision = companyMapper.selectWebCostInCompanyDecision(companyId);
        if (companyDecision == null) {
            companyMapper.insertCostIntoCompanyDecision(companyId, 7, cost);
        } else {
            companyMapper.updateCostInCompanyDecision(companyId, 7, cost);
        }
        StateOfSign stateOfSign = companyMapper.selectIssubmitState(companyId, quarter);
        int timesOfSign = 0;
        if (stateOfSign != null) {
            timesOfSign = stateOfSign.getIsSubmit();
        }
        //计算当前季度所有决策的费用
        int MoneySum = SS.SelectMoneySum(companyId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cost", cost);
        modelAndView.addObject("MoneySum", MoneySum);
        modelAndView.addObject("timesOfSign", timesOfSign);
        modelAndView.addObject("webList", webList);
        modelAndView.addObject("open", webList.get(0).getOpen());
        modelAndView.addObject("rent", webList.get(0).getRent());
        modelAndView.addObject("companyMarket", companyMarket);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("webLastQuarter", webLastQuarter);
        modelAndView.addObject("webCurrentQuarter", webCurrentQuarter);
        modelAndView.setViewName("onlineStore");
        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @MethodLog(description = "修改开设网络销售中心")
    @RequestMapping({"/insertmarketwebinfo.do"})
    public ModelAndView insertmarketwebinfo(HttpServletRequest request) throws Exception {
        int id = ((Integer) request.getSession().getAttribute("competitionId")).intValue();
        String flag = request.getParameter("flag");
        System.out.println("flag字段：" + flag);
        Integer companyId = Integer.parseInt(request.getParameter("companyId"));
        Integer quarter = Integer.parseInt(request.getParameter("quarter"));
        Integer currentQuarter = (Integer) request.getSession().getAttribute("currentQuarter");
        CompanyMarket companyMarket = new CompanyMarket();
        companyMarket.setCompanyId(companyId);
        companyMarket.setMarketId("1");
        companyMarket.setQuarter(quarter);
        ModelAndView modelAndView = new ModelAndView();
        List<MarketInfo> marketInfoList = companyService.showMarketInfo(id);
        List<OnlineStore> webList = this.companyService.selectAndCountTotalNeed(marketInfoList);
        MarketWebOpened marketWebOpened = companyMapper.selectMarketWebOpened(companyId, quarter);
        Boolean webLastQuarter = true;
        Boolean webCurrentQuarter = true;
        int establishOrRent = 0;
        int timesOfSign = 0;
        StateOfSign stateOfSign = null;
        if (flag.equals("1")) {
            //撤回提交状态
            stateOfSign = companyMapper.selectIssubmitState(companyId, quarter);
            if (stateOfSign != null) {
                companyMapper.updateIsSubmitState(timesOfSign, companyId, quarter);
            }

            for (int i = 1; i < quarter; ++i) {
                CompanyMarket companyMarketTemp = companyMapper.selectCompanyMarket(companyId, 0, i);
                if (companyMarketTemp != null) {
                    establishOrRent++;
                }
            }
            if (establishOrRent == 0) {
                //以前从未开设
                companyMarket.setIsPhy(2);
                webLastQuarter = false;
                webCurrentQuarter = false;

                if (quarter == currentQuarter) {
                    if (marketWebOpened == null) {
                        companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, 0);
                    } else {
                        companyMapper.updateMarketWebOpened(companyId, quarter, 0);
                    }
                }
                List<CompanyMarket> companyMarketList = companyMapper.selectCompanyMarketAll(companyId, quarter);
                if (companyMarketList != null && companyMarketList.size() != 0) {
                    companyMarket.setQuarter(quarter);
                    companyMapper.updateCompanyMarketWeb(companyMarket);
                }
            } else {
                //开设后选择取消开设
                companyMarket.setIsPhy(2);
                webCurrentQuarter = false;
                if (quarter == currentQuarter) {
                    if (marketWebOpened == null) {
                        companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, 0);
                    } else {
                        companyMapper.updateMarketWebOpened(companyId, quarter, 0);
                    }
                }
                List<CompanyMarket> companyMarketList = companyMapper.selectCompanyMarketAll(companyId, quarter);
                companyMarket.setQuarter(quarter);
                if (companyMarketList != null && companyMarketList.size() != 0) {
                    companyMapper.updateCompanyMarketWeb(companyMarket);
                } else {
                    companyMapper.insertCompanyMarket(companyMarket);
                }
            }
        } else {
            //记录提交状态
            timesOfSign = 1;
            stateOfSign = companyMapper.selectIssubmitState(companyId, quarter);
            if (stateOfSign != null) {
                companyMapper.updateIsSubmitState(timesOfSign, companyId, quarter);
            } else {
                companyMapper.insertIssubmitState(timesOfSign, companyId, quarter);
            }

            for (int i = 1; i < quarter; i++) {
                CompanyMarket companyMarketTemp = companyMapper.selectCompanyMarket(companyId, 0, i);
                if (companyMarketTemp != null) {
                    establishOrRent++;
                }
            }
            if (establishOrRent == 0) {
                //第一次开设，需要初始化
                List<HirePeopleOnline> hirePeopleOnlineList = companyMapper.selectHirePeopleOnlineList(companyId, quarter);
                HirePeopleOnline hirePeopleOnline = new HirePeopleOnline();
                hirePeopleOnline.setCompanyId(companyId);
                hirePeopleOnline.setMarketId(1);
                hirePeopleOnline.setAfterSale(0);
                hirePeopleOnline.setSaleman(0);
                if (hirePeopleOnlineList != null && hirePeopleOnlineList.size() != 0) {
                    for (int i = quarter; i <= 5; i++) {
                        hirePeopleOnline.setQuarter(i);
                        companyService.updateHirePeopleOnlineById(hirePeopleOnline);
                    }
                } else {
                    for (int i = quarter; i <= 5; ++i) {
                        hirePeopleOnline.setQuarter(i);
                        this.companyService.insertHirePeopleOnline(hirePeopleOnline);
                    }
                }
                companyMarket.setIsPhy(0);
                companyMarket.setQuarter(quarter);
                List<CompanyMarket> companyMarketList = companyMapper.selectCompanyMarketAll(companyId, quarter);
                if (companyMarketList != null && companyMarketList.size() != 0) {
                    companyMapper.updateCompanyMarketWeb(companyMarket);
                } else {
                    companyMapper.insertCompanyMarket(companyMarket);
                }
                webCurrentQuarter = false;
                if (quarter == currentQuarter) {
                    //当前季度开设表示第一次开设，计算开设费用
                    if (marketWebOpened == null) {
                        companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, -webList.get(0).getOpen());
                    } else {
                        companyMapper.updateMarketWebOpened(companyId, quarter, -webList.get(0).getOpen());
                    }
                } else {
                    //否则不是第一次开设，计算租赁费用
                    if (marketWebOpened == null) {
                        companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                    } else {
                        companyMapper.updateMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                    }
                }
            } else {
                //不是第一次开设
                companyMarket.setIsPhy(0);
                List<CompanyMarket> companyMarketList = companyMapper.selectCompanyMarketAll(companyId, quarter);
                if (companyMarketList != null && companyMarketList.size() != 0) {
                    companyMarket.setQuarter(quarter);
                    companyMapper.updateCompanyMarketWeb(companyMarket);
                } else {
                    companyMarket.setQuarter(quarter);
                    companyMapper.insertCompanyMarket(companyMarket);
                }

                if (marketWebOpened == null) {
                    companyMapper.insertCostIntoMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                } else {
                    companyMapper.updateMarketWebOpened(companyId, quarter, -webList.get(0).getRent());
                }
            }
        }

        MarketWebOpened marketWebOpenedNew = companyMapper.selectMarketWebOpened(companyId, quarter);
        int cost = 0;
        if (marketWebOpenedNew != null) {
            cost = marketWebOpenedNew.getCost();
        }

        CompanyDecision companyDecision = companyMapper.selectWebCostInCompanyDecision(companyId);
        if (companyDecision == null) {
            companyMapper.insertCostIntoCompanyDecision(companyId, 7, cost);
        } else {
            companyMapper.updateCostInCompanyDecision(companyId, 7, cost);
        }
        //计算当前季度所有决策的费用
        int MoneySum = SS.SelectMoneySum(companyId);

        modelAndView.addObject("MoneySum", MoneySum);
        modelAndView.addObject("timesOfSign", timesOfSign);
        modelAndView.addObject("webList", webList);
        modelAndView.addObject("open", webList.get(0).getOpen());
        modelAndView.addObject("rent", webList.get(0).getRent());
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("companyMarket", companyMarket);
        modelAndView.addObject("webLastQuarter", webLastQuarter);
        modelAndView.addObject("webCurrentQuarter", webCurrentQuarter);
        modelAndView.setViewName("onlineStore");
        return modelAndView;
    }


    @MethodLog(description = "修改开设实体销售中心")
    @RequestMapping(value = "/insertmarketinfo.do")
    public ModelAndView insertmarketinfo(HttpServletRequest request, String[] market_id) throws Exception {


        System.out.println("CompanyController.insertmarketinfo.do called");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int company_id = (int) request.getSession().getAttribute("companyId");
        int competitionId = (int) request.getSession().getAttribute("competitionId");

        //这里的市场信息将用于稍后计算本季度开设成本
        List<MarketInfo> marketInfoList = staticInfoService.showMarketInfo(competitionId);// 市场信息列表
        HashMap StoreCost = new HashMap();//这个变量将存放每个店的开设成本
        HashMap StoreOpened = new HashMap();//这个变量将存放上个季度开放的店铺
        HashMap StoreRent = new HashMap();//这个变量将存放每个位置的租赁成本
        for (MarketInfo m : marketInfoList) {
            System.out.println("m.getId() : " + m.getId() + " m.getOpen() : " + m.getOpen());
            StoreCost.put(m.getId().toString(), m.getOpen());
            StoreRent.put(m.getId().toString(), m.getRent());
            StoreOpened.put(m.getId().toString(), false);
            //ID全部转成String格式存放，方便后面匹配
        }
        //搜索市场开设情况
        CompanyMarket companyMarket = new CompanyMarket();
        companyMarket.setCompanyId(company_id);
        companyMarket.setIsPhy(1);
        companyMarket.setQuarter(quarter - 1);//这里减一是为了查询上个季度的开店情况
        //这句话将查询上季度开设情况
        List<CompanyMarket> companyMarkets = showCompanymarket(companyMarket);// 该季度公司市场记录

        for (CompanyMarket cm : companyMarkets) {
            System.out.println("上季度开设的店铺 : " + cm.getMarketId());
            String string = cm.getMarketId();
            String[] ss = string.split(",");
            for (String s : ss)
                StoreOpened.put(s, true);
        }
        Integer CostSum;
        Integer EM;
        List<String> markets = new ArrayList<String>();


        String market_ids = "";
        for (int i = 0; i < market_id.length; i++) {
            if (i != market_id.length - 1)
                market_ids += market_id[i] + ",";
            else {
                market_ids += market_id[i];
            }
        }

        CompanyMarket market = new CompanyMarket();
        market.setCompanyId(company_id);
        market.setIsPhy(1);
        market.setQuarter(quarter);
        companyMarkets = showCompanymarket(market);
        if (companyMarkets.size() > 0) {
            market.setId(companyMarkets.get(0).getId());
            market.setMarketId(market_ids);

            for (int i = quarter; i <= quarter; i++) {
                market.setQuarter(i);
                companyService.updateCompanyMarket(market);
            }

            HirePeople hirePeople = null;
            HirePeople flag = null;
            // 计算出 前台未传递的市场值 删除hire_people表中对应的数据
            for (int i = 0; i < markets.size(); i++) {
                int marketDelet = Integer.parseInt(markets.get(i));
                hirePeople = new HirePeople();
                hirePeople.setCompanyId(company_id);
                hirePeople.setMarketId(marketDelet);
                for (int j = quarter; j < 6; j++) {
                    hirePeople.setQuarter(j);
                    companyService.deleteHirePeople(hirePeople);
                }

            }
            // 再将前台 传递过来的 市场 id 插入对应到hire_people 表中

            for (int i = 0; i < market_id.length; i++) {
                hirePeople = new HirePeople();
                hirePeople.setMarketId(Integer.parseInt(market_id[i]));
                hirePeople.setCompanyId(company_id);
                hirePeople.setAfterSale(0);
                hirePeople.setSaleman(0);
                for (int j = quarter; j < 6; j++) {
                    hirePeople.setQuarter(j);
                    //flag = companyService.checkHirePeople(hirePeople);
                    if (flag == null) {
                        //companyService.insertHirePeople(hirePeople);
                    }
                }
            }
        } else {
            market.setMarketId(market_ids);

            // 把第一季度 选择的市场 插入到 以后的各个季度
            for (int i = quarter; i <= quarter; i++) {
                market.setQuarter(i);
                companyService.insertCompanyMarket(market);
            }

            HirePeople hirePeople = null;

            for (int i = 0; i < market_id.length; i++) {
                System.out.println("循环输出市场id：" + market_id[i]);
                hirePeople = new HirePeople();
                hirePeople.setMarketId(Integer.parseInt(market_id[i]));
                hirePeople.setCompanyId(company_id);
                hirePeople.setAfterSale(0);
                hirePeople.setSaleman(0);
                for (int j = quarter; j <= quarter; j++) {
                    hirePeople.setQuarter(j);
                    companyService.insertHirePeople(hirePeople);
                }
            }

        }


        /**
         * 至此，我们准备好了两个关键HashMap
         * StoreCost//这个变量将存放每个店的开设成本
         * StoreOpened//这个变量将存放上个季度开放的店铺
         */
        System.out.println("本季度开设的店铺");
        CostSum = 0;//这个变量将用于存放本季度开店的总成本
        for (String s : market_id) {//market_id中存放着从页面传来的本季度开店信息。数据直接来自客户页面
            System.out.println(s);
            //重新判断 SS.marketIsOpened(companyId,marketid)
            //if (!(boolean) StoreOpened.get(s))//如果在StoreOpened序列中没有找到s，就说明上季度这个位置没有开店
            if (!SS.marketIsOpened(company_id, Integer.parseInt(s)))
                CostSum += (Integer) StoreCost.get(s);//如果开了点，就不算租金
            else
                CostSum += (Integer) StoreRent.get(s);//如果这句话得到执行，就说明应该计算租赁成本
        }
        SS.HirePeopleAdjust(market_id, quarter, company_id);

        System.out.println("CostSum =" + CostSum);
        /**
         * 截止此时，本季度开设实体店的总成本就已经存放在了变量CostSum中
         * 接下来，此变量数据存入数据库，操作代码为6
         */
        EM = SS.CompanyDecitionLog(company_id, CostSum, 6);

        markets.add("1");
        markets.add("2");
        markets.add("3");
        markets.add("4");
        System.out.println("market_id.length : " + market_id.length);
        for (int i = 0; i < market_id.length; i++) {
            System.out.println("market_id[" + i + "] read : " + market_id[i]);
            if (markets.contains(market_id[i])) {
                System.out.println("market_id[" + i + "] removed : " + market_id[i]);
                markets.remove(market_id[i]);
            }
            ;
        }

        ModelAndView modelAndView = showMarketInfo(request);
        // modelAndView.addObject("result",result);
        modelAndView.addObject("EM", EM);
        modelAndView.addObject("MoneySum", SS.SelectMoneySum(company_id));
        modelAndView.setViewName("physicalStore");

        modelAndView = showMarketInfo(request);

        return modelAndView;
    }


    @MethodLog(description = "查看职位分配操作")
    @RequestMapping(value = "/showAllMemberByComapnyId.do")
    public ModelAndView showAllMemberByComapnyId(HttpServletRequest request) {
        System.out.println("职位分配显示控制台。。");

        ModelAndView modelAndView = new ModelAndView();
        int id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.valueOf(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = companyMapper.selectIssubmit(id, quarter);
        // 根据公司ID查出所有成员
        List<Member> memberList = memberService.showAllMemberByComapnyId(id);
        List<Duty> dutyList = companyService.showAllPosition();

        // 查询该公司下所有成员ID
        List<Integer> memberId = companyService.selectMemberIdByCompanyId(id);

        List<CompanyDuty> companyDuty = companyService.selectMemberDutyByCompanyId(id);

        modelAndView.addObject("companyDuty", companyDuty);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("memberList", memberList);
        modelAndView.addObject("dutyList", dutyList);
        modelAndView.setViewName("duty");
        return modelAndView;
    }

    @MethodLog(description = "查看团队规则操作")
    @RequestMapping("/queryCompanySubmit.do")
    public ModelAndView queryCompanySubmit(HttpServletRequest request, TeacherQueryVo teacherQueryVo) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Company company = teacherQueryVo.getCompany();
        Teacher record = (Teacher) request.getSession().getAttribute("teacher");
        teacherQueryVo.setTeacher(record);
        System.out.println("原设教师id：" + record.getId());
        Competition competition = teacherQueryVo.getCompetition();
        List<TeacherQueryVo> companyList = companyService.findCompanyList(teacherQueryVo);
        // 通过形参中的model将model数据传到页面,相当于上面addObjeect
        modelAndView.addObject("company", company);
        System.out.println(company);
        request.getSession().setAttribute("isSubmit", 1);
        modelAndView.addObject("competition", competition);
        modelAndView.addObject("companyList", companyList);
        modelAndView.setViewName("jsp/teacherQuery/queryCompany");
        return modelAndView;

    }

    @RequestMapping("/queryCompanyByPage.do")
    public ModelAndView queryCompanyByPage(HttpServletRequest request, TeacherQueryVo teacherQueryVo) throws Exception {
        String pageNow = request.getParameter("pageNow");
        Page page = null;
        List<TeacherQueryVo> companyList = new ArrayList<TeacherQueryVo>();

        Teacher record = (Teacher) request.getSession().getAttribute("teacher");
        teacherQueryVo.setTeacher(record);

        int totalCount = (int) companyService.getCompanyCount(teacherQueryVo);
        Company company = teacherQueryVo.getCompany();
        if (pageNow != null) {
            page = new Page(totalCount, Integer.parseInt(pageNow));

        } else {
            page = new Page(totalCount, 1);
        }
        teacherQueryVo.setStartPos(page.getStartPos());
        teacherQueryVo.setPageSize(page.getPageSize());
        companyList = companyService.selectCompanyByPage(teacherQueryVo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page", page);
        modelAndView.addObject("companyList", companyList);
        modelAndView.addObject("company", company);
        modelAndView.setViewName("jsp/teacherQuery/queryCompany");
        System.out.println(page.getPageSize());
        System.out.println("公司：" + companyList);
        return modelAndView;
    }

    @MethodLog(description = "修改目标策略")
    @RequestMapping("/updateCompanyStrategy.do")
    public ModelAndView updateCompanyStrategy(HttpServletRequest request, CompanyStrategy companyStrategy)
            throws Exception {
        System.out.println("success");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));

        System.out.println(companyStrategy.getMainPro());

        // 获取选中项数组
        String[] content = request.getParameterValues("ability");
        System.out.println(content);
        // 数组转换成字符串存入数据库
        String strategyId = "";
        for (String str : content) {
            strategyId += str + ",";
        }
        System.out.println(strategyId);

        // 由于与数据库字段名不匹配，需要手动注入
        companyStrategy.setCompanyId(company_id);
        companyStrategy.setStrategyId(strategyId);
        companyStrategy.setQuarter(quarter);

        // 判断
        CompanyStrategy com = companyService.selectCompanyStrategy(company_id, quarter);
        if (com != null) {
            // 数据库更新操作
            companyService.updateCompanyStrategy(companyStrategy);
        } else {
            // 数据库添加操作
            companyService.insertCompanyStrategy(companyStrategy);
        }
        // 返回jsp
        ModelAndView modelAndView = strategyInfo(request);
        modelAndView.setViewName("strategy");
        return modelAndView;

    }


    @MethodLog(description = "查看固定产能操作")
    @RequestMapping(value = "/showCapacityInfo.do")
    public ModelAndView showCapacityInfo(HttpServletRequest request) {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");

        //提交固定产能时判断公司资金余额是否可以满足生产需要
        //查询公司季初资金余额
        int decision = companyService.selectDecision(company_id);
        System.out.println("totalDecision:" + decision);
        CompanyDecision companyDecision = companyMapper.selectCompanyDecision(company_id, 10);
        int money = 0;
        if (companyDecision != null) {
            money = companyDecision.getMoney();
        }
        int resMoney = decision - money;
        System.out.println("可用资金" + resMoney);


        CompanyCapacity proCompanyCapacity = null;
        if (quarter - 1 > 0) {

            proCompanyCapacity = companyService.selectCompanyCapacity(companyId, quarter - 1);

        }

        List<CapacityInfo> capacityInfoList = companyService.showCapacityInfo();

        List<CompanyCapacity> companyCapacityList = companyService.showCapacityInfo1(companyId, quarter);
        if (companyCapacityList.size() == 0) {
            int capacityAll = 0;
            for (int i = 1; i < quarter; i++) {

                capacityAll += companyMapper.selectCapacityAdd(companyId, i);
            }
            companyService.insertCompanyCapacity(capacityAll, 0, companyId, currentQuarter);
        }
        List<CompanyCapacity> companyCapacityList1 = companyService.showCapacityInfo1(companyId, quarter);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("proCompanyCapacity", proCompanyCapacity);
        modelAndView.addObject("quarter", quarter);

        modelAndView.addObject("capacityInfoList", capacityInfoList);
        modelAndView.addObject("companyCapacityList1", companyCapacityList1);
        if (quarter > 3) {
            modelAndView.addObject("resMoney", resMoney);
        }

        modelAndView.setViewName("constantProduce");
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("isSubmit", isSubmit);

        return modelAndView;

    }


    @MethodLog(description = "修改固定产能")
    @RequestMapping(value = "/showCapacityInfo1.do")
    public ModelAndView showCapacityInfo1(HttpServletRequest request) {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));


        //查询固定产能花费
        String number = request.getParameter("number");
        int numberInt = Integer.parseInt(number);
        int investMoney = companyService.selectInvestByCapacity(numberInt);
        System.out.println("invest:" + investMoney);


        int proNumber = 0;

        if (quarter - 1 != 0) {
            CompanyCapacity companyCapacity = companyService.selectCompanyCapacity(companyId, quarter - 1);
            if (companyCapacity != null) {

                proNumber = companyCapacity.getCapacityNow() + companyCapacity.getCapacityAdd();
            }
        }

        int currentNumber = proNumber + numberInt;

        // 插入当前季度固定产能
        int count = companyService.selectTotalCount(companyId, quarter);
        if (count == 0) {
            // 插入数据
            companyService.insertCompanyCapacity(0, numberInt, companyId, quarter);

        } else {
            companyService.updateCompanyCapacity(proNumber, numberInt, companyId, quarter);
        }
        CompanyDecision companyDecision2 = companyService.selectCompanyDecision(companyId, 10);
        if (companyDecision2 == null) {
            companyService.insertCompanyDecision(companyId, -investMoney, 10);
        } else {
            companyService.updateCompanyDecision(companyId, -investMoney, 10);
        }

        // 插入下季度固定产能
        int nextCount = companyService.selectTotalCount(companyId, quarter + 1);
        if (nextCount == 0) {
            // 插入数据
            companyService.insertCompanyCapacity(currentNumber, 0, companyId, quarter + 1);

        } else {
            companyService.updateCompanyCapacity(currentNumber, 0, companyId, quarter + 1);
        }

        ModelAndView modelAndView = showCapacityInfo(request);


        return modelAndView;
    }

    @MethodLog(description = "查看员工薪酬")
    @RequestMapping(value = "/showAverageSalary.do")
    public ModelAndView showAverageSalary(HttpServletRequest request) {

        AverageSalary sales = companyService.showAverageSalaryOfSale();
        System.out.println(sales.getRegion());
        AverageSalary workers = companyService.showAverageSalaryOfWork();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sales", sales);
        modelAndView.addObject("workers", workers);
        modelAndView.setViewName("staffWage");
        return modelAndView;
    }

    @MethodLog(description = "修改职位分配")
    @RequestMapping("/updateCompanyDuty.do")
    public ModelAndView updateCompanyDuty(HttpServletRequest request) throws Exception {
        System.out.println("职位分配控制台。。。");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = 1;
        System.out.println("公司ID:" + company_id + "季度：" + quarter);

        // 成员ID的数组
        String[] member = request.getParameterValues("myHidden");
        // 遍历，并存入其对应主要职责及次要职责ID
        for (int i = 0; i < member.length; i++) {

            CompanyDuty companyDuty = new CompanyDuty();

            companyDuty.setCompanyId(company_id);

            companyDuty.setMemberId(Integer.parseInt(member[i]));

            companyDuty.setImg("123");

            String main = request.getParameter(member[i] + "_main");
            System.out.println(member[i] + "的主要职责ID：" + main);
            companyDuty.setMainId(Integer.parseInt(main));

            String minor = request.getParameter(member[i] + "_minor");
            System.out.println(member[i] + "的次要职责ID：" + minor);
            companyDuty.setMinorId(Integer.parseInt(minor));

            // 查询是否数据库已有该成员职责数据
            CompanyDuty flag = companyService.selectMemberByMemberId(Integer.parseInt(member[i]));
            // 根据flag判断更新或者添加数据库
            if (flag == null) {
                System.out.println("进行添加职责操作");
                companyService.insertMemberDuty(companyDuty);
            } else {
                System.out.println("进行更新操作");
                companyService.updateMemberDuty(companyDuty);
            }
        }

        ModelAndView modelAndView = showAllMemberByComapnyId(request);
        modelAndView.setViewName("duty");
        return modelAndView;

    }

	/* 第二季度相关决策 */

    @MethodLog(description = "显示业内工厂工人薪酬")
    @RequestMapping("/showWorkersSalary.do")
    public ModelAndView showWorkersSalary(HttpServletRequest request) throws Exception {
        System.out.println("显示业内工厂工人薪酬。。。");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        System.out.println("公司ID:" + company_id + "季度：" + quarter);
        ModelAndView modelAndView = new ModelAndView();

        List<WorkersSalary> workersSalaryList = companyService.selectCompanyWorkersSalary(company_id, quarter);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("workersSalaryList", workersSalaryList);
        modelAndView.addObject("len", workersSalaryList.size());
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("workersSalary");
        return modelAndView;
    }

     @MethodLog(description = "更新业内工厂工人薪酬")
    @RequestMapping(value = "/showWorkersSalary1.do")
    public ModelAndView showWorkersSalary1(HttpServletRequest request) throws Exception {
        int companyIdInt = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        
        // 获取表单数据
        String salaryStr = request.getParameter("salary");
        if ("".equals(salaryStr)) {
            salaryStr = "10";
        }

        String welfareStr = request.getParameter("welfare");
        if ("".equals(welfareStr)) {
            welfareStr = "10";
        }
        String holidayStr = request.getParameter("holiday");
        if ("".equals(holidayStr)) {
            holidayStr = "10";
        }
        String publicFundStr = request.getParameter("publicFund");
        if ("".equals(publicFundStr)) {
            publicFundStr = "10";
        }
        String companyPensionStr = request.getParameter("companyPension");
        if ("".equals(companyPensionStr)) {
            companyPensionStr = "10";
        }
        String retiredPayStr = request.getParameter("retiredPay");
        if ("".equals(retiredPayStr)) {
            retiredPayStr = "10";
        }
        
        
        
        int salary = Integer.parseInt(salaryStr);
        int welfare = Integer.parseInt(welfareStr);
        int holiday = Integer.parseInt(holidayStr);
        int publicFund = Integer.parseInt(publicFundStr);
        int companyPension = Integer.parseInt(companyPensionStr);
        int retiredPay = Integer.parseInt(retiredPayStr);

        // 计算总成本
        int salaryTotal = (int) (salary + (float) (welfare * salary) / 100 + (float) (publicFund * salary) / 100
                + (float) (companyPension * salary) / 100 + (float) (retiredPay * salary) / 100
                + (float) (holiday * salary) / 365);
        System.out.println("总成本：" + salaryTotal);

        WorkersSalary workersSalary = new WorkersSalary();
        workersSalary.setCompanyId(companyIdInt);
        workersSalary.setQuarter(quarter);
        workersSalary.setSalary(salary);
        workersSalary.setWelfare(welfare);
        workersSalary.setHoliday(holiday);
        workersSalary.setPublicFund(publicFund);
        workersSalary.setCompanyPension(companyPension);
        workersSalary.setRetiredPay(retiredPay);
        workersSalary.setSalaryTotal(salaryTotal);

        List<WorkersSalary> workersSalaryList = companyService.selectCompanyWorkersSalary(companyIdInt, quarter);
        if (workersSalaryList.size() == 0) {
            companyService.insertCompanyWorkersSalary(workersSalary);
        } else {
            companyService.updateCompanyWorkersSalary(workersSalary);
        }
        ModelAndView mv = showWorkersSalary(request);
        return mv;
    }

    // 销售人员薪酬

    @MethodLog(description = "查看业内销售人员薪酬")
    @RequestMapping(value = "/showAllSalesSalary.do")
    public ModelAndView showAllSalesSalary(HttpServletRequest request) {

        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int companyId = (int) request.getSession().getAttribute("companyId");
        List<AllSalesSalaryVo> allVoList = new ArrayList<>();
        allVoList = companyService.findSalaryofAllCompanysbyCompanyID(companyId, quarter);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allVoList", allVoList);
        modelAndView.setViewName("allSalesSalary");
        System.out.println("returned");
        return modelAndView;

    }

    @MethodLog(description = "查看业内工厂工人薪酬")
    @RequestMapping(value = "/showAllWorkersSalary.do")
    public ModelAndView showAllWorkersSalary(HttpServletRequest request) {
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int companyId = (int) request.getSession().getAttribute("companyId");
        List<AllWorkersSalaryVo> allVoList = new ArrayList<>();
        allVoList = companyService.findWSalaryofAllCompanysbyCompanyID(companyId, quarter);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allVoList", allVoList);
        modelAndView.setViewName("allWorkersSalary");
        return modelAndView;
    }

    @MethodLog(description = "显示业内销售人员薪酬")
    @RequestMapping("/showSalesSalary.do")
    public ModelAndView showSalesSalary(HttpServletRequest request) throws Exception {
        System.out.println("显示业内销售人员薪酬。。。");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        String quarterString = request.getParameter("quarter");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        try {
            quarter = Integer.parseInt(quarterString);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("公司ID:" + company_id + "季度：" + quarter);
        ModelAndView modelAndView = new ModelAndView();

        List<SalesSalary> salesSalaryList = companyService.selectCompanySalesSalary(company_id, quarter);
        modelAndView.addObject("salesSalaryList", salesSalaryList);
        modelAndView.addObject("len", salesSalaryList.size());
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.setViewName("salesSalary");
        modelAndView.addObject("isSubmit", isSubmit);
        return modelAndView;
    }

    @MethodLog(description = "更新业内销售人员薪酬")
    @RequestMapping(value = "/updateSalesSalary2.do")
    public ModelAndView updateSalesSalary2(HttpServletRequest request) throws Exception {
        int companyIdInt = (int) request.getSession().getAttribute("companyId");

        int quarter = Integer.parseInt(request.getParameter("quarter"));

        // 获取表单数据
        String salaryStr = request.getParameter("salary");
        if ("".equals(salaryStr)) {
            salaryStr = "10";
        }

        String welfareStr = request.getParameter("welfare");
        if ("".equals(welfareStr)) {
            welfareStr = "10";
        }
        String holidayStr = request.getParameter("holiday");
        if ("".equals(holidayStr)) {
            holidayStr = "10";
        }
        String publicFundStr = request.getParameter("publicFund");
        if ("".equals(publicFundStr)) {
            publicFundStr = "10";
        }
        String companyPensionStr = request.getParameter("companyPension");
        if ("".equals(companyPensionStr)) {
            companyPensionStr = "10";
        }
        String retiredPayStr = request.getParameter("retiredPay");
        if ("".equals(retiredPayStr)) {
            retiredPayStr = "10";
        }
        int salary = Integer.parseInt(salaryStr);
        int welfare = Integer.parseInt(welfareStr);
        int holiday = Integer.parseInt(holidayStr);
        int publicFund = Integer.parseInt(publicFundStr);
        int companyPension = Integer.parseInt(companyPensionStr);
        int retiredPay = Integer.parseInt(retiredPayStr);

        // 计算总成本
        int salaryTotal = (int) (salary + (float) (welfare * salary) / 100 + (float) (publicFund * salary) / 100
                + (float) (companyPension * salary) / 100 + (float) (retiredPay * salary) / 100
                + (float) (holiday * salary) / 365);
        System.out.println("总成本：" + salaryTotal);

        SalesSalary salesSalary = new SalesSalary();
        salesSalary.setCompanyId(companyIdInt);
        salesSalary.setQuarter(quarter);
        salesSalary.setSalary(salary);
        salesSalary.setWelfare(welfare);
        salesSalary.setHoliday(holiday);
        salesSalary.setPublicFund(publicFund);
        salesSalary.setCompanyPension(companyPension);
        salesSalary.setRetiredPay(retiredPay);
        salesSalary.setSalaryTotal(salaryTotal);

        List<SalesSalary> salesSalaryList = companyService.selectCompanySalesSalary(companyIdInt, quarter);
        if (salesSalaryList.size() == 0) {
            companyService.insertCompanySalesSalary(salesSalary);
        } else {
            companyService.updateCompanySalesSalary(salesSalary);
        }
        ModelAndView mv = showSalesSalary(request);
        return mv;
    }


    // 需求量预测 改

    @MethodLog(description = "查看需求量预测操作")
    @RequestMapping(value = "/demandForecast.do")
    public ModelAndView demandForecast(HttpServletRequest request) {

        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = companyMapper.selectIssubmit(companyId, quarter);

        // 实体销售中心的销售数量 不区分市场

        // 上季度
        List<HirePeople> hirePeopelListPro = companyService.selectHirePeopleList(companyId, quarter - 1);
        int phySalemanNumberPro = 0;
        if (hirePeopelListPro != null) {
            for (HirePeople hirePeople : hirePeopelListPro) {
                phySalemanNumberPro += hirePeople.getSaleman();
            }
        }

        // 这季度
        List<HirePeople> hirePeopelList = companyService.selectHirePeopleList(companyId, quarter);
        int phySalemanNumber = 0;
        if (hirePeopelList != null) {
            for (HirePeople hirePeople : hirePeopelList) {
                phySalemanNumber += hirePeople.getSaleman();
            }
        }

        // 网络销售中心的销售人员数量 不区分市场

        // 上季度

        List<HirePeopleOnline> hirePeopleOnlineListPro = companyService.selectHirePeopleOnlineList(companyId,
                quarter - 1);
        int webSalemanNumberPro = 0;
        if (hirePeopelList != null) {
            for (HirePeopleOnline hirePeopleOnline : hirePeopleOnlineListPro) {
                webSalemanNumberPro += hirePeopleOnline.getSaleman();
            }
        }

        // 这季度
        List<HirePeopleOnline> hirePeopleOnlineList = companyService.selectHirePeopleOnlineList(companyId, quarter);
        int webSalemanNumber = 0;
        if (hirePeopelList != null) {
            for (HirePeopleOnline hirePeopleOnline : hirePeopleOnlineList) {
                webSalemanNumber += hirePeopleOnline.getSaleman();
            }
        }

        // 获取人均需求量

        DemandForecast demandForecast = companyService.getDemandForecastByCompanyIdAndQuarter(companyId, quarter);

        DemandForecast demandForecastPro = companyService.getDemandForecastByCompanyIdAndQuarter(companyId,
                quarter - 1);

        if (demandForecastPro == null) {
            demandForecastPro = new DemandForecast();
            demandForecastPro.setDemandAveragePhy(0);
            demandForecastPro.setDemandAverageWeb(0);
        }

        //获取产品信息

        Map<Integer, List<CompanyProductVo>> companyProductResult = new HashMap<Integer, List<CompanyProductVo>>();
        int demandNumber = 0;

        for (int i = 1; i <= quarter; i++) {
            List<CompanyProductVo> companyProductList = companyService.selectCompanyProduct(companyId, i, quarter);

            for (CompanyProductVo companyProductVo : companyProductList) {
                //根据产品id和季度查找产品
                int productId = companyProductVo.getId();
                ProductPrice productPrice = companyService.selectProductPrice2(productId, quarter);
                if (productPrice == null) {
                    System.out.println("您还没有给产品定价");
                    companyProductVo.setPriceFlag(0);
                    companyProductVo.setDemand(0);
                } else {
                    companyProductVo.setPriceFlag(1);
                    Integer isSale = productPrice.getIsSale();
                    if (isSale == 0) {
                        companyProductVo.setIsSale(0);
                        companyProductVo.setDemand(0);
                    } else {
                        companyProductVo.setIsSale(1);
                    }
                }
                demandNumber += companyProductVo.getDemand();
            }

            if (companyProductList != null) {
                companyProductResult.put(i, companyProductList);
            }
        }

        Map<Integer, List<CompanyProductVo>> companyProductResult2 = new HashMap<Integer, List<CompanyProductVo>>();


        int demandNumberPro = 0;
        for (int i = 1; i <= quarter; i++) {
            List<CompanyProductVo> companyProductList2 = companyService.selectCompanyProduct(companyId, i, quarter - 1);

            for (CompanyProductVo companyProductVo : companyProductList2) {
                demandNumberPro += companyProductVo.getDemand();
            }

            if (companyProductList2 != null) {
                companyProductResult2.put(i, companyProductList2);
            }
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("phySalemanNumberPro", phySalemanNumberPro);
        modelAndView.addObject("webSalemanNumberPro", webSalemanNumberPro);
        modelAndView.addObject("phySalemanNumber", phySalemanNumber);
        modelAndView.addObject("webSalemanNumber", webSalemanNumber);

        modelAndView.addObject("isSubmit", isSubmit);

        modelAndView.addObject("demandForecastPro", demandForecastPro);
        modelAndView.addObject("demandForecast", demandForecast);

        modelAndView.addObject("companyProductResult", companyProductResult);
        modelAndView.addObject("companyProductResult2", companyProductResult2);

        modelAndView.addObject("demandNumberPro", demandNumberPro);
        modelAndView.addObject("demandNumber", demandNumber);

        modelAndView.setViewName("demandForecast");
        return modelAndView;

    }

    @MethodLog(description = "更新需求量预测操作")
    @RequestMapping(value = "/updateDemandForecast.do")
    public ModelAndView updateDemandForecast(HttpServletRequest request) {

        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));

        String string1 = request.getParameter("demand_average_phy");
        String string2 = request.getParameter("demand_average_web");
        if (string1 == null || string1.equals("")) {
            string1 = "0";
        }
        if (string2 == null || string2.equals("")) {
            string2 = "0";
        }

        int demandAveragePhy = Integer.parseInt(string1);

        int demandAverageWeb = Integer.parseInt(string2);

        if (companyService.getDemandForecastByCompanyIdAndQuarter(companyId, quarter) != null) {

            companyService.updateDemandForecast(companyId, quarter, demandAveragePhy, demandAverageWeb);
        } else {
            companyService.insertDemandForecast(companyId, quarter, demandAveragePhy, demandAverageWeb);
        }

        String[] productIds = request.getParameterValues("productId");

        if (productIds != null && productIds.length > 0) {
            for (int i = 0; i < productIds.length; i++) {
                String string = request.getParameter(productIds[i] + "demand");
                int demand = Integer.parseInt(string.trim());
                int productId = Integer.parseInt(productIds[i]);
                companyService.updateProductDemand(productId, quarter, demand);
            }
        }


        return demandForecast(request);

    }

    @MethodLog(description = "显示销货成本")
    @RequestMapping("/showSoldCost.do")
    public ModelAndView showSoldCost(HttpServletRequest request) throws Exception {
        System.out.println("销货成本控制台。。。");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.valueOf(request.getParameter("quarter"));
        System.out.println("公司ID:" + company_id + "季度：" + quarter);

        // 获取该公司当季度及之前的所有产品及成本
        ModelAndView modelAndView = new ModelAndView();
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            companyProducts.addAll(companyService.selectProductByCompanyIdAndQuarter(company_id, i));
        }
        modelAndView.addObject("companyProducts", companyProducts);

        modelAndView.setViewName("soldCost");
        return modelAndView;
    }

    @RequestMapping("/showProductPrice.do")
    public ModelAndView showProductPrice(HttpServletRequest request, int quarter) throws Exception {
        System.out.println("产品定价控制台。。。");

        //获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");

        ModelAndView modelAndView = new ModelAndView();

        //找出当前季度的所有产品,并记录
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            companyProducts.addAll(companyService.selectProductByCompanyIdAndQuarter(company_id, i));
        }
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            //该产品生产设计的季度
            int quarterDesign = companyProducts.get(i).getQuarter();
            if (quarterDesign == 1) {
                quarterDesign = 2;
            }
            if (companyService.selectProductPrice2(productId, quarterDesign) == null) {
                //新设计的产品统一定价，设置默认价格为0
                //初始化从当前季度到第五季度的价格
                for (int j = quarterDesign; j <= 5; j++) {
                    companyService.insertProductPrice2(productId, company_id, j);
                }
            }
        }
        //查询当前季度的产品价格信息
        List<ProductPrice> productPrices = companyService.selectProductPrice(company_id, quarter);
        if (productPrices != null) {
            for (ProductPrice productPrice : productPrices) {
                Integer companyId = productPrice.getCompanyId();
                Integer productId = productPrice.getProductId();
                String productName = marketingMapper.selectProductName1(companyId, productId);
                productPrice.setProductName(productName);
            }
        }
        modelAndView.addObject("productPrices", productPrices);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.setViewName("setPrice");
        return modelAndView;
    }

    @MethodLog(description = "操作产品定价")
    @RequestMapping("/operateProductPrice.do")
    public ModelAndView operateProductPrice(HttpServletRequest request, ProductPriceList productPriceList) throws Exception {
        System.out.println("操作产品定价。。。");
        //获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        if (productPriceList == null) {
            System.out.println("您的公司的产品还没有设计价格");
        } else {
            for (ProductPriceVo priceVo : productPriceList.getProductPriceList()) {
                Integer productId = Integer.valueOf(priceVo.getProductId());
                if ("".equals(priceVo.getPrice())) {
                    priceVo.setPrice("0");
                }
                if ("".equals(priceVo.getYouji())) {
                    priceVo.setYouji("0");
                }
                Integer price = Integer.valueOf(priceVo.getPrice());
                Integer youji = Integer.valueOf(priceVo.getYouji());
                ProductPrice productPrice = new ProductPrice();
                productPrice.setProductId(productId);
                productPrice.setCompanyId(company_id);
                productPrice.setQuarter(quarter);
                productPrice.setPrice(price);
                productPrice.setYouji(youji);
                companyMapper.updateProductPrice2(productPrice);
            }
        }
        ModelAndView modelAndView = showProductPrice(request, quarter);
        return modelAndView;
    }


    @RequestMapping("/showOperateMedia.do")
    public ModelAndView showOperateMedia(HttpServletRequest request) throws Exception {
        System.out.println("显示主流媒体投放成功");
        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");

        //查询季初可用资金
        int money = 0;
        money = companyService.selectDecision(company_id);

        //媒体投放资金
        int mediaMoney = 0;
        CompanyDecision companyDecision = companyMapper.selectCompanyDecision(company_id, 2);
        if (companyDecision != null) {
            mediaMoney = companyDecision.getMoney();
        }

        ModelAndView modelAndView = new ModelAndView();
        // 媒体初始信息

        List<MediaInfo> mediaInfos = staticInfoService.showMediaInfo();

        modelAndView.addObject("mediaInfos", mediaInfos);
        System.out.println("媒体初始：" + mediaInfos.size());
        // 当季度产品信息
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            List<CompanyProduct> xx = companyService.selectProductByCompanyIdAndQuarter(company_id, i);
            companyProducts.addAll(xx);
        }
        // 上季度产品信息
        List<CompanyProduct> lastCompanyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i < quarter; i++) {
            List<CompanyProduct> xx = companyService.selectProductByCompanyIdAndQuarter(company_id, i);
            lastCompanyProducts.addAll(xx);
        }
        System.out.println("list：" + companyProducts.size());
        modelAndView.addObject("companyProducts", companyProducts);
        modelAndView.addObject("lastCompanyProducts", lastCompanyProducts);
        modelAndView.addObject("len", companyProducts.size());
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("money", money);
        modelAndView.addObject("mediaMoney", mediaMoney);

        // 公司当季度媒体投放信息
        List<CompanyMedia> companyMedias = companyService.selectTotalCompanyMedia(company_id, quarter);
        if (companyMedias != null) {
            modelAndView.addObject("companyMedias", companyMedias);
        }
        // 公司上 季度媒体投放信息
        List<CompanyMedia> lastCompanyMedias = companyService.selectTotalCompanyMedia(company_id, quarter - 1);
        if (companyMedias != null) {
            modelAndView.addObject("lastCompanyMedias", lastCompanyMedias);
        }
        modelAndView.setViewName("operateMedia");
        return modelAndView;
    }

    @MethodLog(description = "操作媒体投放")
    @RequestMapping("/UpdateCompanyMedia.do")
    public ModelAndView UpdateCompanyMedia(HttpServletRequest request) throws Exception {
        System.out.println("操作媒体投放。。。");

        //获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));

        //主流媒体投放
        int decision_id = 2;//媒体投放决策编号
        int money = 0;//媒体投放费用

        //产品初始信息
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            List<CompanyProduct> xx = companyService.selectProductByCompanyIdAndQuarter(company_id, i);
            companyProducts.addAll(xx);
        }
        System.out.println("companyProduct:" + companyProducts.size());
        //获取mediaId
        String[] mediaId = request.getParameterValues("mediaId");
        System.out.println("mediaId：" + mediaId.length);

        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            String[] productMedia = request.getParameterValues(Integer.toString(productId));
            for (int j = 0; j < productMedia.length; j++) {
                CompanyDecision companyDecision = companyService.selectCompanyDecision(company_id, decision_id);
                CompanyMedia companyMedia = new CompanyMedia();

                companyMedia.setCompanyId(company_id);
                companyMedia.setMediaId((j + 1));
                companyMedia.setNum(Integer.parseInt(productMedia[j]));
                companyMedia.setQuarter(quarter);
                companyMedia.setProductId(productId);

                //判断是添加还是更新操作
                List<CompanyMedia> companyMedias = companyService.selectCompanyMedia(company_id, quarter, Integer.parseInt(mediaId[j]), productId);
                if (companyMedias.size() == 0) {
                    System.out.println("添加操作");
                    //添加操作
                    int mediaId1 = companyMedia.getMediaId();
                    int num = companyMedia.getNum();
                    //根据mediaid查找主流媒体的投放费用
                    int cost = companyService.selectMediaCost(mediaId1);
                    //插入操作的时候决策资金表中应该是没有数据的
                    if (companyDecision != null) {
                        money = companyDecision.getMoney();
                        money += -cost * num;
                        companyService.updateCompanyDecision(company_id, money, decision_id);
                    } else {
                        money += -cost * num;
                        companyService.insertCompanyDecision(company_id, money, decision_id);
                    }
                    companyService.insertCompanyMedia(companyMedia);

                } else {
                    System.out.println("更新操作");
                    //更新操作
                    int num = companyMedia.getNum();
                    int newmediaId = companyMedia.getMediaId();
                    int cost = companyService.selectMediaCost(newmediaId);
                    money += -cost * num;
                    companyService.updateCompanyDecision(company_id, money, decision_id);
                    companyService.updateCompanyMedia(companyMedia);

                }
            }
        }
        ModelAndView modelAndView = showOperateMedia(request);
        return modelAndView;
    }

    @MethodLog(description = "显示市场调研报告")
    @RequestMapping("/ShowCompanyReport.do")
    public ModelAndView ShowCompanyReport(HttpServletRequest request) throws Exception {
        System.out.println("显示购买调研报告。。。");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        System.out.println("公司ID:" + company_id + "季度：" + quarter);

        String flag = companyService.selectCompanyReport(company_id, quarter);
        System.out.println("flag：" + flag);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("buyReport");
        return modelAndView;
    }

    @MethodLog(description = "操作市场调研报告")
    @RequestMapping("/OperateReport.do")
    public ModelAndView OperateReport(HttpServletRequest request) throws Exception {
        System.out.println("操作购买调研报告。。。");

        //获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));

        //操作市场调研报告
        int decision_id = 3;
        int money = 0;
        CompanyDecision companyDecision = companyService.selectCompanyDecision(company_id, decision_id);

        //获取表单数据
        String flag = request.getParameter("flag");
        System.out.println("flag：" + flag);
        int buyReport = 0;
        if (flag.equals("true")) {
            buyReport = 1;
        }
        System.out.println("公司ID:" + company_id + "季度：" + quarter + "buy:" + buyReport);
        //判断是添加记录还是更新记录
        String a = companyService.selectCompanyReport(company_id, quarter);
        if (a == null) {
            System.out.println("添加记录");
            //添加
            if (companyDecision != null) {
                money = companyDecision.getMoney();
                money += 15000;
                companyService.updateCompanyDecision(company_id, money, decision_id);
            } else {
                money += 15000;
                companyService.insertCompanyDecision(company_id, money, decision_id);
            }

            companyService.insertCompanyReport(company_id, quarter, buyReport);
        } else {
            System.out.println("更新记录");
            //更新
            int temp = buyReport - Integer.parseInt(a);
            if (companyDecision != null) {
                money = companyDecision.getMoney();
            }
            money += temp * 15000;
            companyService.updateCompanyDecision(company_id, money, decision_id);

            companyService.updateCompanyReport(company_id, quarter, buyReport);
        }

        ModelAndView modelAndView = ShowCompanyReport(request);
        return modelAndView;
    }

    @MethodLog(description = "核查广告语")
    @RequestMapping("/checkAdvertisement.do")
    public ModelAndView checkAdvertisement(HttpServletRequest request) throws Exception {
        System.out.println("核查广告语。。。");

        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        System.out.println("公司ID:" + company_id + "季度：" + quarter);

        ModelAndView modelAndView = new ModelAndView();
        // 查询公司广告信息
        List<CompanyAdvertise> companyAdvertises = companyService.selectCompanyAdvertise(company_id, quarter);
        modelAndView.addObject("companyAdvertises", companyAdvertises);
        // 查询广告语信息
        List<AdvertiseInfo> advertiseInfos = staticInfoService.showAdvertiseInfo();
        modelAndView.addObject("advertiseInfos", advertiseInfos);

        modelAndView.setViewName("checkAdvertise");
        return modelAndView;
    }

    @MethodLog(description = "查看品牌管理操作")
    @RequestMapping(value = "/CompanyProduct.do")
    public ModelAndView CompanyProduct(HttpServletRequest request, int quarter) {
        System.out.println("查看品牌管理操作success");
        int companyId = (int) request.getSession().getAttribute("companyId");
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        //查询当前公司资金余额
        int money = 0;
        money = companyService.selectDecision(companyId);


        // 查询当前导航栏中所有产品
        List<List<CompanyProduct>> companyProductList = new ArrayList<List<CompanyProduct>>();

        for (int i = 1; i <= quarter; i++) {
            List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(companyId, i);
            companyProductList.add(companyProducts);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("companyProductList", companyProductList);
        modelAndView.addObject("money", money);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("design");
        return modelAndView;
    }

    @RequestMapping("/operateProduct.do")
    public ModelAndView operateProduct(HttpServletRequest request) throws Exception {
        // 获取操作的方法
        System.out.println("操作产品。。。");
       /* int companyId = Integer.parseInt(request.getParameter("companyId"));*/
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int productId = Integer.parseInt(request.getParameter("id"));
        System.out.println("产品ID：" + productId);
        String method = request.getParameter("method");
        System.out.println("操作方法:" + method);

        // 初始化一个ModelAndView
        ModelAndView modelAndView = new ModelAndView();

        if (method.equals("delete")) {
            System.out.println("删除操作");
            //删除当前季度的产品会收回设计费用
            int money = 0;
            CompanyDecision companyDecision = companyMapper.selectCompanyDecision(companyId, 1);
            if (companyDecision != null) {
                money = companyDecision.getMoney() + 60000;
            }
            companyMapper.updateCompanyDecision(companyId, money, 1);
            companyService.deleteCompanyProduct(productId);
            companyService.deleteCompanyProductDemand(productId);
            companyService.deleteProductPrice(productId);
            companyService.deleteCompanyProductInventory(productId);
            companyService.deleteCompanyMedia(productId);
            companyService.deleteCompanyAdvertise(productId);
            modelAndView = CompanyProduct(request, quarter);
            modelAndView.setViewName("design");
        } else {
            System.out.println("查询操作");
            CompanyProduct companyProduct = companyService.selectProductByProductId(productId);
            System.out.println("查询出来的产品类型：" + companyProduct.getType());
            System.out.println("产品名称：" + companyProduct.getName());
            System.out.println("产品成本：" + companyProduct.getCost());
            modelAndView = showProductDetail2(request, companyProduct);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/showProductDetail2.do")
    public ModelAndView showProductDetail2(HttpServletRequest request, CompanyProduct companyProduct) throws Exception {
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        String quarterFlag = "true";

        int money = 0;
        String str = request.getParameter("money");
        if (str != null) {
            money = Integer.parseInt(str);
        }


        System.out.println("产品设计信息。。。");
        // 查询title
        List<String> titleList = companyService.countProductInfoTitle();
        // 根据title遍历存入map
        Map<String, List<ProductInfo>> result = new LinkedHashMap<String, List<ProductInfo>>();
        for (int i = 0; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
            result.put(titleList.get(i), companyService.showProductInfoByTitle(titleList.get(i)));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", result);

        if (companyProduct.getId() != null) {

            // 判断季度，默认值是第二季度

            if (companyProduct.getQuarter() < currentQuarter) {
                quarterFlag = "false"; // false 前台不能修改产品参数
            } else {
                quarterFlag = "true"; // true 前台可以修改产品参数
            }

            String detail = companyProduct.getDetail();
            String[] ss = detail.split(",");
            // 用于判断产品配置
            HashMap<ProductInfo, Integer> hm = new LinkedHashMap<ProductInfo, Integer>();
            List<ProductInfo> productInfoList = companyService.showAllDetail();
            System.out.println("配置个数：" + productInfoList.size());
            for (int i = 0; i < productInfoList.size(); i++) {
                System.out.println("第" + (i + 1) + "个配置");
                ProductInfo productInfo = productInfoList.get(i);
                for (int j = 0; j < ss.length; j++) {
                    if (ss[j].equals(productInfo.getId().toString())) {
                        hm.put(productInfo, 1);
                        System.out.println("1111," + productInfo.getId());
                    }
                }
                if (!hm.containsKey(productInfo)) {
                    hm.put(productInfo, 0);
                    System.out.println("0000," + productInfo.getId());
                }
            }
            for (Integer i : hm.values()) {
                System.out.println(i);
            } // 打印市场的选择值（0或1）
            modelAndView.addObject("productInfoList", hm);
            modelAndView.addObject("quarter", quarter);
            modelAndView.addObject("currentQuarter", currentQuarter);
            modelAndView.addObject("productId", companyProduct.getId());
            modelAndView.addObject("detail", companyProduct.getDetail());
            modelAndView.addObject("brandName", companyProduct.getName());

        }
        System.out.println("产品所属季度是否小于竞赛季度：" + quarterFlag);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("quarterFlag", quarterFlag);
        modelAndView.addObject("companyProduct", companyProduct);
        modelAndView.addObject("money", money);
        modelAndView.setViewName("brand_ds2");
        return modelAndView;
    }

    @RequestMapping(value = "/UpdateCompanyProduct.do")
    public ModelAndView UpdateCompanyProduct(HttpServletRequest request) {


        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        String type = request.getParameter("type");
        String flag = request.getParameter("flag");
        int decision_id = 1;//1表示设计产品,包括添加产品和更新产品
        int money = 0;//设计产品的总费用
        CompanyDecision companyDecision = companyService.selectCompanyDecision(company_id, decision_id);
        if (flag.equals("insert")) {
            System.out.println("添加产品");
            String cost = request.getParameter("productCost");
            //获取产品成本后添加到数据库中
            //1.查找数据库中当前公司是否已经有添加产品的操作，如果有则取出成本字段累加，如果没有则直接将数据加入数据库中
            if (companyDecision != null) {
                money = companyDecision.getMoney();
                money += -60000;//设计产品的固定费用是60000
                companyService.updateCompanyDecision(company_id, money, decision_id);
            } else {
                money += -60000;
                companyService.insertCompanyDecision(company_id, money, decision_id);
            }
            CompanyProduct companyProduct = new CompanyProduct();
            companyProduct.setCompanyId(company_id);
            companyProduct.setQuarter(quarter);
            companyProduct.setType(type);
            companyProduct.setName(request.getParameter("Brand_name"));
            companyProduct.setDetail(request.getParameter("selectOption"));
            companyProduct.setCost(Integer.parseInt(cost));
            companyService.insertCompanyProduct(companyProduct);

            int id = companyProduct.getId();
            for (int i = 1; i <= 6; i++) {
                companyService.insertCompanyProductDemandById(id, i);
            }
            for (int i = 2; i <= 6; i++) {
                companyService.insertCompanyProductInventoryById(id, i);
            }
        } else {
            //更新操作
            System.out.println("更新产品");
            int productId = Integer.parseInt(request.getParameter("productId"));
            System.out.println("更新产品Id：" + productId);
            CompanyProduct companyProduct = new CompanyProduct();
            String cost = request.getParameter("productCost");
            companyProduct.setId(productId);
            companyProduct.setType(type);
            companyProduct.setName(request.getParameter("Brand_name"));
            companyProduct.setDetail(request.getParameter("selectOption"));
            companyProduct.setCost(Integer.parseInt(cost));
            companyService.updateCompanyProduct(companyProduct);
        }

        ModelAndView modelAndView = CompanyProduct(request, quarter);
        return modelAndView;
    }

    // 运行产能
    @MethodLog(description = "查看运行产能操作")
    @RequestMapping(value = "/showOperationCapacity.do")
    public ModelAndView showOperationCapacity(HttpServletRequest request) {
        int companyIdInt = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        ModelAndView modelAndView = new ModelAndView();
        // 上季度
        List<CompanyCapacity> lastCompanyCapacityList = companyService.showCapacityInfo1(companyIdInt, quarter - 2);
        List<OperationCapacity> lastOperationCapacityList = companyService.selectOperationCapacity(companyIdInt,
                quarter - 1);
        // 当前季度
        List<CompanyCapacity> companyCapacityList1 = companyService.showCapacityInfo1(companyIdInt, quarter - 1);
        List<OperationCapacity> operationCapacityList = companyService.selectOperationCapacity(companyIdInt, quarter);
        List<WorkersSalary> workersSalaryList = companyService.selectCompanyWorkersSalary(companyIdInt, quarter);

        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("lastCompanyCapacityList", lastCompanyCapacityList);
        modelAndView.addObject("lastOperationCapacityList", lastOperationCapacityList);
        modelAndView.addObject("companyCapacityList1", companyCapacityList1);
        modelAndView.addObject("operationCapacityList", operationCapacityList);
        modelAndView.addObject("workersSalaryList", workersSalaryList);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("operationCapacity");
        return modelAndView;
    }

    @MethodLog(description = "修改运行产能")
    @RequestMapping(value = "/showOperationCapacity1.do")
    public ModelAndView showOperationCapacity1(HttpServletRequest request) {
        int companyIdInt = (int) request.getSession().getAttribute("companyId");

        int quarter = Integer.parseInt(request.getParameter("quarter"));

        List<OperationCapacity> operationCapacityList = companyService.selectOperationCapacity(companyIdInt, quarter);

        String operateCapacity = request.getParameter("operateCapacity");
        String workerProductivity = request.getParameter("workerProductivity");

        Integer operateCapacityInt = Integer.parseInt(operateCapacity);
        Integer workerProductivityInt = Integer.parseInt(workerProductivity);

        if (operationCapacityList.size() == 0) {
            companyService.insertOperationCapacity(companyIdInt, quarter, operateCapacityInt, workerProductivityInt);
        } else {
            companyService.updateOperationCapacity(companyIdInt, quarter, operateCapacityInt, workerProductivityInt);
        }
        ModelAndView mv = showOperationCapacity(request);
        return mv;
    }

    @MethodLog(description = "查看竞争力")
    @RequestMapping(value = "/showCompetitivePower.do")
    public ModelAndView showCompetitivePower(HttpServletRequest request) {
        int companyId = (int) request.getSession().getAttribute("companyId");

        int quarter = Integer.parseInt(request.getParameter("quarter"));
        // 根据当前公司id找到所在竞赛编号
        Competition competition = competitionService.findCompetitionByCompanyId(companyId);
        // 根据竞赛编号找到所有的公司
        List<Company> company = companyService.showCompanyByCompetitionId(competition.getId());
        // 创建集合类
        List<CompetitivePowerVo> CPVoList = new ArrayList<>();
        // 新建vo类
        for (Company comp : company) {
            List<OperationCapacity> OC = new ArrayList<>();
            OC = companyService.selectOperationCapacity(comp.getId(), quarter - 1);
            List<CompanyCapacity> CC = new ArrayList<>();
            CC = companyService.showCapacityInfo1(comp.getId(), quarter - 2);

            List<CompanyInvestment> CI = new ArrayList<>();
            CI = companyService.selectCompanyInvestment(comp.getId(), quarter - 1);

            CompetitivePowerVo CPV = new CompetitivePowerVo();
            CPV.setCompany(comp);
            if (CC.size() == 0) {
                CPV.setCapacityNow(0);
                CPV.setCapacityAdd(0);
            } else {
                CPV.setCapacityNow(CC.get(0).getCapacityNow());
                CPV.setCapacityAdd(CC.get(0).getCapacityAdd());
            }
            if (OC.size() == 0) {
                CPV.setOperateCapacity(0);
            } else {
                CPV.setOperateCapacity(OC.get(0).getOperateCapacity());
            }
            if (CI.size() == 0) {
                CPV.setWorkerEfficiency(0);
            } else {
                CPV.setWorkerEfficiency(CI.get(0).getWorkerEfficiency());
            }

            CPVoList.add(CPV);

        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("CPVoList", CPVoList);
        modelAndView.setViewName("competitivePower");
        return modelAndView;

    }

    // 库存控制
    @MethodLog(description = "查看库存控制")
    @RequestMapping(value = "/showInventoryControl.do")
    public ModelAndView showInventoryControl(HttpServletRequest request) {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
       /* List<CompanyCapacity> companyCapacityList1 = companyService.showCapacityInfo1(companyId, quarter - 1);*/

        List<CompanyCapacity> companyCapacityList = companyService.showCapacityInfo1(companyId, quarter);
        if (companyCapacityList.size() == 0) {
            int capacityAll = 0;
            for (int i = 1; i < quarter; i++) {

                capacityAll += companyMapper.selectCapacityAdd(companyId, i);
            }
            companyService.insertCompanyCapacity(capacityAll, 0, companyId, currentQuarter);
        }
        List<CompanyCapacity> companyCapacityList1 = companyService.showCapacityInfo1(companyId, quarter);

        List<CompanyProductVo2> inventoryControl = companyService.selectInventoryControl(companyId, quarter);


        System.out.println("companyCapacityList1:" + companyCapacityList1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("inventoryControl", inventoryControl);
        modelAndView.addObject("companyCapacityList1", companyCapacityList1);
        modelAndView.addObject("isSubmit", isSubmit);

        modelAndView.setViewName("inventoryControl");
        return modelAndView;
    }

    // 修改库存控制

    @MethodLog(description = "修改库存控制")
    @RequestMapping(value = "/updateInventoryControl.do")
    public ModelAndView updateInventoryControl(HttpServletRequest request,
                                               CompanyProductVo2List companyProductVo2List) {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        List<CompanyCapacity> companyCapacityList1 = companyService.showCapacityInfo1(companyId, quarter - 1);
        List<CompanyProductVo2> inventoryControl = companyService.selectInventoryControl(companyId, quarter);
        for (CompanyProductVo2 companyProductVo2 : companyProductVo2List.getCompanyProductVo2List()) {
            System.out.println("输出 产品库存:" + companyProductVo2.getId() + companyProductVo2.getInventory());
            int productId = companyProductVo2.getId();
            int inventory = companyProductVo2.getInventory();
            companyService.updateCompanyProductInventory(productId, inventory, quarter);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyCapacityList1", companyCapacityList1);
        modelAndView.addObject("quarter", quarter);
        modelAndView.setViewName("inventoryControl");
        return showInventoryControl(request);
    }

    @MethodLog(description = "查看上季度结果")
    @RequestMapping(value = "/showQuarterResult.do")
    public ModelAndView showQuarterResult(HttpServletRequest request) {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter")) - 1;

        List<OperationCapacity> operationCapacityList = companyService.selectOperationCapacity(companyId, quarter);

        List<CompanyInvestment> companyInvestmentList = companyService.selectCompanyInvestment(companyId, quarter);

        List<CompanyProduct> companyProducts = companyService.selectCompanyProductByCompanyIdQuarter(companyId,
                quarter + 1);

        List<StockLevelVo> SLVoList = new ArrayList<>();
        for (CompanyProduct compro : companyProducts) {
            List<ProductMarketShare> PMS = new ArrayList<>();
            // 上个季度的库存
            List<ProductMarketShare> PMS1 = new ArrayList<>();
            PMS = companyService.selectProductMarketShare(compro.getId(), quarter);
            PMS1 = companyService.selectProductMarketShare(compro.getId(), quarter - 1);

            StockLevelVo SLV = new StockLevelVo();
            SLV.setCompanyProducts(compro);

            // 季初库存
            Integer AfterStock = 0;

            if (PMS1 == null || PMS1.size() == 0) {

                AfterStock = 0;

                System.out.println(AfterStock + "111");
            } else {
                AfterStock = PMS1.get(0).getStock();
                System.out.println("2222");
            }
            Integer YiShengChan;
            Integer guding;
            Integer rengong;
            Integer MoscowNeed;
            Integer HongkongNeed;
            Integer NewdelhiNeed;
            Integer SingaporeNeed;
            Integer OnlineNeed;
            Integer HongkongSale;
            Integer MoscowSale;
            Integer NewdelhiSale;
            Integer SingaporeSale;
            Integer OnlineSale;
            Integer Stockoun;
            Integer Stock;
            if (PMS == null || PMS.size() == 0) {
                MoscowNeed = 0;
                HongkongNeed = 0;
                NewdelhiNeed = 0;
                SingaporeNeed = 0;
                OnlineNeed = 0;
                HongkongSale = 0;
                MoscowSale = 0;
                NewdelhiSale = 0;
                SingaporeSale = 0;
                OnlineSale = 0;
                Stockoun = 0;
                Stock = 0;

            } else {
                MoscowNeed = PMS.get(0).getMoscowNeed();
                HongkongNeed = PMS.get(0).getHongkongNeed();
                NewdelhiNeed = PMS.get(0).getNewdelhiNeed();
                SingaporeNeed = PMS.get(0).getSingaporeNeed();
                OnlineNeed = PMS.get(0).getOnlineNeed();
                HongkongSale = PMS.get(0).getHongkongSale();
                MoscowSale = PMS.get(0).getMoscowSale();
                NewdelhiSale = PMS.get(0).getNewdelhiSale();
                SingaporeSale = PMS.get(0).getSingaporeSale();
                OnlineSale = PMS.get(0).getOnlineSale();
                Stockoun = PMS.get(0).getStockoun();
                Stock = PMS.get(0).getStock();
            }
            YiShengChan = HongkongSale + MoscowSale + SingaporeSale + NewdelhiSale + OnlineSale + Stock;

            Integer one = Math.round(YiShengChan / 65);
            Integer dayu = 1775 - Math.round(YiShengChan / 65);
            double two = 1500 / Math.log(2 + one);
            double three = 0.8 * two;
            double four = 0.8 * dayu;
            Integer chengben;


            if (YiShengChan == 0) {
                rengong = 0;
                guding = 0;
                chengben = rengong + guding + companyProducts.get(0).getCost();
            } else if (YiShengChan <= 325 && YiShengChan > 0) {
                rengong = (int) two;
                guding = (int) three;
                chengben = rengong + guding + companyProducts.get(0).getCost();

            } else {
                rengong = dayu;
                guding = (int) four;
                chengben = rengong + guding + companyProducts.get(0).getCost();
            }


            System.out.println("YiShengChan:" + YiShengChan);
            System.out.println("one:" + one);
            System.out.println("dayu:" + dayu);
            System.out.println("rengong:" + rengong);
            System.out.println("Math.log(2 + one):" + Math.log(2 + one));
            SLV.setChengben(chengben);
            SLV.setYiShengChan(YiShengChan);
            SLV.setGuding(guding);
            SLV.setRengong(rengong);
            SLV.setHongkongNeed(HongkongNeed);
            SLV.setMoscowNeed(MoscowNeed);
            SLV.setNewdelhiNeed(NewdelhiNeed);
            SLV.setSingaporeNeed(SingaporeNeed);
            SLV.setOnlineNeed(OnlineNeed);

            SLV.setHongkongSale(HongkongSale);
            SLV.setMoscowSale(MoscowSale);
            SLV.setNewdelhiSale(NewdelhiSale);
            SLV.setSingaporeSale(SingaporeSale);
            SLV.setOnlineSale(OnlineSale);

            SLV.setStockoun(Stockoun);
            SLV.setStock(Stock);
            SLV.setAfterStock(AfterStock);
            SLVoList.add(SLV);

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("SLVoList", SLVoList);
        modelAndView.addObject("operationCapacityList", operationCapacityList);
        modelAndView.addObject("companyInvestmentList", companyInvestmentList);

        modelAndView.setViewName("quarterResult");
        return modelAndView;

    }

    @MethodLog(description = "无用库存")
    @RequestMapping(value = "/showUselessInventory.do")
    public ModelAndView showUselessInventory(HttpServletRequest request) {
        int companyId = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        List<CompanyInvestment> companyInvestmentList = companyService.selectCompanyInvestment(companyId, quarter - 1);

        List<CompanyProduct> companyProducts = companyService.selectCompanyProductByCompanyIdQuarter(companyId,
                quarter);
        List<StockLevelVo> SLVoList = new ArrayList<>();
        for (CompanyProduct compro : companyProducts) {
            List<ProductMarketShare> PMS = new ArrayList<>();
            PMS = companyService.selectProductMarketShare(compro.getId(), quarter - 1);
            StockLevelVo SLV = new StockLevelVo();
            SLV.setCompanyProducts(compro);

            Integer Stock;

            if (PMS == null || PMS.size() == 0) {

                Stock = 0;

            } else {
                Stock = PMS.get(0).getStock();
            }
            SLV.setStock(Stock);
            SLVoList.add(SLV);

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("SLVoList", SLVoList);
        modelAndView.addObject("companyInvestmentList", companyInvestmentList);

        modelAndView.setViewName("uselessInventory");
        return modelAndView;
    }


	/*
     * @MethodLog(description = "修改库存控制")
	 *
	 * @RequestMapping(value = "/showInventoryControl1.do") public ModelAndView
	 * showInventoryControl1(HttpServletRequest request) { String[] ids =
	 * request.getParameterValues("ids"); System.out.println("ids：" + ids);
	 * String[] max = request.getParameterValues("inventory_max_two");
	 * System.out.println("max:" + max); for (int i = 0; i < ids.length; i++) {
	 * Integer id = Integer.parseInt(ids[i]); Integer inventoryMaxt =
	 * Integer.parseInt(max[i]); CompanyProduct companyProduct = new
	 * CompanyProduct(); companyProduct.setId(id);
	 * companyProduct.setInventoryMaxTwo(inventoryMaxt);
	 * companyService.updateInventoryControl(companyProduct); } ModelAndView mv
	 * = showInventoryControl(request); return mv;
	 *
	 * }
	 */

    //雇佣销售人员
    //雇佣销售人员
    //雇佣销售人员
    @RequestMapping(value = "/hireSalePeople.do")
    public ModelAndView hireSalePeople(HttpServletRequest request) {
        System.out.println("CompanyController:hireSalePeople.do called");
        int companyId = (int) request.getSession().getAttribute("companyId");
        int currentQuarter = Integer.parseInt(request.getParameter("quarter"));
        int isSubmit = companyMapper.selectIssubmit(companyId, currentQuarter);

        //currentQuarter2  是获取当前竞赛所在的季度数
        int currentQuarter2 = (int) request.getSession().getAttribute("currentQuarter");

        int quarter = currentQuarter;

        IdQuarter idQuarter = new IdQuarter();
        idQuarter.setId(companyId);
        idQuarter.setQuarter(quarter);

        //返回一个用逗号分隔得字符串
        CompanyMarket companyMarket = teacherService.findCompanyPhyMarketByIdQuarter(idQuarter);

        String[] marketString = {};

        if (companyMarket != null) {
            marketString = companyMarket.getMarketId().split(",");
        }
        List<HirePeopleVo> hirePeopleListVo = new ArrayList<HirePeopleVo>();
        HirePeopleVo hirePeopleVo = null;
        int totalPeople = 0;


        for (int i = 0; i < marketString.length; i++) {
            int marketInt = Integer.parseInt(marketString[i]);
            System.out.println("companyId：" + companyId);
            System.out.println("marketInt：" + marketInt);
            System.out.println("quarter：" + quarter);

            hirePeopleVo = companyService.selectHirePeople(companyId, marketInt, quarter);
            if (hirePeopleVo.getHirePeople().getSaleman() == null || hirePeopleVo.getHirePeople().getSaleman().equals("")) {
                hirePeopleVo.getHirePeople().setSaleman(0);
            }
            if (hirePeopleVo.getHirePeople().getAfterSale() == null || hirePeopleVo.getHirePeople().getAfterSale().equals("")) {
                hirePeopleVo.getHirePeople().setAfterSale(0);
            }
            totalPeople = totalPeople + hirePeopleVo.getHirePeople().getSaleman() + hirePeopleVo.getHirePeople().getAfterSale();
            hirePeopleListVo.add(hirePeopleVo);
        }

        //根据公司id 和季度 查找 sale_salary 表中的 员工工资

        SalesSalary salesSalary = companyService.findCompanySalesSalary(companyId, currentQuarter);

        if (salesSalary == null) {
            salesSalary = new SalesSalary();
            salesSalary.setSalaryTotal(0);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("totalPeople", totalPeople);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter2);
        modelAndView.addObject("salesSalary", salesSalary);
        modelAndView.addObject("hirePeopleListVo", hirePeopleListVo);
        modelAndView.addObject("CityNum", hirePeopleListVo.size());
        modelAndView.addObject("isSubmit", isSubmit);
        System.out.println("总成本：" + totalPeople * salesSalary.getSalaryTotal());
        Integer EM;
        EM = 0;
        modelAndView.addObject("EM", EM);
        modelAndView.addObject("MoneySum", SS.SelectMoneySum(companyId) + totalPeople * salesSalary.getSalaryTotal());
        modelAndView.setViewName("hirePeople");
        return modelAndView;

    }

    @RequestMapping(value = "/updateHirePeople.do")
    public ModelAndView updateHirePeople(HttpServletRequest request, HirePeopleList hirePeopleList) {
        System.out.println("updateHirePeople called");
        for (HirePeople hirePeople : hirePeopleList.getHirePeopleList()) {

            System.out.println("提交后售后人员：" + hirePeople.getAfterSale());
            System.out.println("提交后销售人员：" + hirePeople.getSaleman());
            System.out.println("提交后销售人员为空：" + (hirePeople.getSaleman() + " ").toString().equals("null "));
            System.out.println("提交后售后人员为空：" + (hirePeople.getAfterSale() + " ").toString().length());
            if ((hirePeople.getSaleman() + " ").toString().equals("null "))
                hirePeople.setSaleman(0);
            if ((hirePeople.getAfterSale() + " ").toString().equals("null "))
                hirePeople.setAfterSale(0);
            System.out.println("提交后 id:" + hirePeople.getId());
            companyService.updateHirePeopleById(hirePeople);
        }
        return hireSalePeople(request);
    }


    // 雇佣网络销售人员
    @RequestMapping(value = "/hireSalePeopleOnline.do")
    public ModelAndView hireSalePeopleOnline(HttpServletRequest request, int quarter) {
        System.out.println("/hireSalePeopleOnline.do called");
        int companyId = (int) request.getSession().getAttribute("companyId");
        int currentQuarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter2 = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        quarter = currentQuarter;
        //雇佣员工的信息
        HirePeopleOnline hirePeopleOnline = null;

        hirePeopleOnline = companyService.selectHirePeopleOnline(companyId, quarter);
        if (hirePeopleOnline == null || hirePeopleOnline.getMarketId() == null) {
            hirePeopleOnline = null;
        }
        //查询company_market
        CompanyMarket companyMarket = companyMapper.selectCompanyMarket(companyId, 0, quarter);
        //用flag 来表示 是否建立了网络销售中心  true表示有  false 表示没有
        String flag = "true";
        if (companyMarket == null || hirePeopleOnline == null) {
            flag = "false";
        }

        //根据公司id 和季度 查找 sale_salary 表中的 员工工资
        SalesSalary salesSalary = companyService.findCompanySalesSalary(companyId, currentQuarter);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter2);
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("salesSalary", salesSalary);
        modelAndView.addObject("hirePeopleOnline", hirePeopleOnline);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("hirePeopleOnline");
        return modelAndView;

    }

    @RequestMapping(value = "/updateHirePeopleOnline.do")
    public ModelAndView updateHirePeopleOnline(HttpServletRequest request, HirePeopleOnline hirePeopleOnline, int quarter) {
        System.out.println("/updateHirePeopleOnline.do called");
        int companyId = (int) request.getSession().getAttribute("companyId");
        if (hirePeopleOnline == null) {
            System.out.println("没有网络销售中心信息");
        } else {
            if (hirePeopleOnline.getAfterSale() == null || hirePeopleOnline.getAfterSale().equals("")) {
                hirePeopleOnline.setAfterSale(0);
            }
            if (hirePeopleOnline.getSaleman() == null || hirePeopleOnline.getSaleman().equals("")) {
                hirePeopleOnline.setSaleman(0);
            }
            hirePeopleOnline.setMarketId(1);
            hirePeopleOnline.setCompanyId(companyId);
            companyService.updateHirePeopleOnlineById(hirePeopleOnline);

        }
        return hireSalePeopleOnline(request, quarter);
    }

    @MethodLog(description = "查看利润表操作")
    @RequestMapping(value = "/showIncomeStatement.do ")
    public ModelAndView showIncomeStatement(HttpServletRequest request) throws Exception {
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        ModelAndView md = new ModelAndView();
        String info = checkDecision(quarter, company_id);
        if (info != "成功") {
            md.addObject("info", info);
            md.setViewName("welcome");
            return md;
        }
        ModelAndView modelAndView = showCashFlow(request, request.getParameter("quarter"));
        CashFlow cashFlow = companyService.selectCashFlow(company_id, quarter);
        //收入
        float income = cashFlow.getXiaoshouGet();
        companyService.updateIncomeShouRu((int) income, company_id, quarter);
        //利息
        float lixi = cashFlow.getLixiGet();
        companyService.updateIncomStatementLixi(lixi, company_id, quarter);
        //营业成本(销货成本)
        float yingyeCost = cashFlow.getShengchanPay();
        companyService.updateIncomeYingYe((int) yingyeCost, company_id, quarter);
        //邮件返款
        float youji_sum = cashFlow.getFankuanPay();
        companyService.updateIncomeYouJi((int) youji_sum, company_id, quarter);
        //研发
        float yanfa = cashFlow.getYanfaPay();
        companyService.updateIncomeStatementYanfa(yanfa, company_id, quarter);
        //广告
        float guanggao = cashFlow.getGuanggaoPay();
        companyService.updateIncomeGuangGao((int) guanggao, company_id, quarter);
        //销售人员费用
        float saler = cashFlow.getSalerPay();
        companyService.updateIncomeSaler((int) saler, company_id, quarter);
        //实体网络
        float PhyCost = cashFlow.getSalescenterPay();
        companyService.updateIncomeStatementPhyMarket(PhyCost, company_id, quarter);
        float WebCost = cashFlow.getSalescenterWebPay();
        companyService.updateIncomeStatementWebMarket(WebCost, company_id, quarter);
        //调研
        float diaoyan = cashFlow.getDiaoyanPay();
        companyService.updateIncomeDiaoYan((int) diaoyan, company_id, quarter);
        //货运
        float huoyun = cashFlow.getHuoyunPay();
        companyService.updateIncomeHuoYun((int) huoyun, company_id, quarter);
        //库存费用
        float kucun = cashFlow.getKucunPay();
        companyService.updateIncomeKuCun((int) kucun, company_id, quarter);
        //所得税费用(净利润*25%)
        float netProfit = income + lixi - yingyeCost - youji_sum - yanfa - guanggao - saler - PhyCost - WebCost -
                diaoyan - huoyun - kucun;
        float tax = 0;
        if (netProfit > 0) {
            tax = (float) (netProfit * 0.25);
        }
        companyService.updateIncomeStatementTax(tax, company_id, quarter);


        if (quarter == 1) {
            List<IncomeStatement> list = companyService.showIncomeStatement(company_id, 1);
            modelAndView.addObject("incomeStatementList", list);
        } else {
            List<IncomeStatement> list = companyService.showIncomeStatementResult(company_id, 1);
            for (int i = 2; i < quarter; i++) {
                list.addAll(companyService.showIncomeStatementResult(company_id, i));
            }
            list.addAll(companyService.showIncomeStatement(company_id, quarter));
            modelAndView.addObject("incomeStatementList", list);
        }
        modelAndView.addObject("quarter", quarter);
        modelAndView.setViewName("incomeStatement");
        return modelAndView;
    }


    @MethodLog(description = "查看内部持股操作")
    @RequestMapping(value = "/showShareHold.do ")
    public ModelAndView showShareHold(HttpServletRequest request) throws Exception {
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));

        ModelAndView modelAndView = new ModelAndView();
        List<CompanyStock> list = companyService.showCompanyStock(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            list.addAll(companyService.showCompanyStock(company_id, i));
        }
        modelAndView.addObject("shareHold", list);
        modelAndView.addObject("quarter", quarter);
        modelAndView.setViewName("shareHold");
        return modelAndView;
    }

    @MethodLog(description = "查看定期存款操作")
    @RequestMapping(value = "/showFixedDeposite.do")
    public ModelAndView showFixedDeposit(HttpServletRequest request) {
        int companyIdInt = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");
        //贷款
        int decision = companyService.selectDecision(companyIdInt);
        System.out.println("totalDecision:" + decision);
        CompanyDecision companyDecision = companyMapper.selectCompanyDecision(companyIdInt, 15);
        int money = 0;
        if (companyDecision != null) {
            money = companyDecision.getMoney();
        }
        int resMoney = decision - money;
        System.out.println("可用资金" + resMoney);


        List<FixedDeposit> fixedDepositeList = companyService.selectFixedDeposite(companyIdInt, quarter);
        if (fixedDepositeList.size() == 0) {
            if (quarter != 1) {
                FixedDeposit fixedDeposit = companyService.selectCunkuanLast(companyIdInt, quarter - 1);
                float cunkuan = 0;
                if (fixedDeposit != null) {
                    cunkuan = Float.parseFloat((String.valueOf(
                            ((fixedDeposit.getCunkuanLast() - fixedDeposit.getTiqu() + fixedDeposit.getCunru()) * 1.015))));
                }
                System.out.println("上季度存款总额：" + cunkuan);
                companyService.insertCunkuanLast(cunkuan, companyIdInt, quarter);
            }
        }
        List<FixedDeposit> fixedDepositeList1 = companyService.selectFixedDeposite(companyIdInt, quarter);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("fixedDepositeList", fixedDepositeList1);
        modelAndView.addObject("resMoney", resMoney);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("fixedDeposite");
        return modelAndView;
    }

    @MethodLog(description = "修改定期存款")
    @RequestMapping(value = "/showFixedDeposite1.do")
    public ModelAndView showFixedDeposit1(HttpServletRequest request) {
        int companyIdInt = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));

        List<FixedDeposit> fixedDepositeList = companyService.selectFixedDeposite(companyIdInt, quarter);

        String tiQu = request.getParameter("tiQu");
        String cunRu = request.getParameter("cunRu");
        float tiQuFloat = Float.parseFloat(tiQu);
        float cunRuFloat = Float.parseFloat(cunRu);

        if (fixedDepositeList.size() == 0) {
            companyService.insertFixedDeposite(companyIdInt, tiQuFloat, cunRuFloat, 1, 0.0f);
        } else {
            companyService.updateFixedDeposite(companyIdInt, tiQuFloat, cunRuFloat, quarter);
        }

        CompanyDecision companyDecision2 = companyService.selectCompanyDecision(companyIdInt, 15);
        if (companyDecision2 == null) {
            companyService.insertCompanyDecision(companyIdInt, (int) (tiQuFloat - cunRuFloat), 15);
        } else {
            companyService.updateCompanyDecision(companyIdInt, (int) (tiQuFloat - cunRuFloat), 15);
        }

        ModelAndView mv = showFixedDeposit(request);
        return mv;
    }

    @RequestMapping(value = "/showCashFlowResult.do ")
    public ModelAndView showCashFlowResult(HttpServletRequest request, String quarter1) throws Exception {
        System.out.println("上一季度现金表");
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(quarter1);

        companyService.calCashFlowResult(company_id, quarter);

        ModelAndView modelAndView = new ModelAndView();
        List<CashFlow> cashFlowResult = companyService.selectCashFlowResult(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            cashFlowResult.addAll(companyService.selectCashFlowResult(company_id, i));
        }
        modelAndView.addObject("cashFlowList", cashFlowResult);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("result", 1);
        modelAndView.setViewName("cashFlow");
        return modelAndView;
    }

    @RequestMapping(value = "/showIncomeStatementResult.do ")
    public ModelAndView showIncomeStatementResult(HttpServletRequest request) throws Exception {
        System.out.println("上一季度利润");
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        companyService.calIncomeResult(company_id, quarter);


        ModelAndView modelAndView = new ModelAndView();
        List<IncomeStatement> incomeResult = companyService.selectIncomeStatementResult(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            incomeResult.addAll(companyService.selectIncomeStatementResult(company_id, i));
        }
        modelAndView.addObject("incomeStatementList", incomeResult);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("result", 1);
        modelAndView.setViewName("incomeStatement");
        return modelAndView;
    }

    @RequestMapping(value = "/showBalanceSheetResult.do ")
    public ModelAndView showBalanceSheetResult(HttpServletRequest request) throws Exception {
        System.out.println("上一季度资产负债");
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        companyService.calBalanceSheetResult(company_id, quarter);

        ModelAndView modelAndView = new ModelAndView();
        List<BalanceSheet> balanceSheetResult1 = companyService.selectBalanceSheetResult(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            balanceSheetResult1.addAll(companyService.selectBalanceSheetResult(company_id, i));
        }
        modelAndView.addObject("assertSheetList", balanceSheetResult1);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("result", 1);
        modelAndView.setViewName("assertSheet");
        return modelAndView;
    }

    @MethodLog(description = "显示设计广告语")
    @RequestMapping("/designAdvertisement.do")
    public ModelAndView designAdvertisement(HttpServletRequest request, Integer productId) throws Exception {
        System.out.println("显示设计广告语。。。");

        ModelAndView modelAndView = new ModelAndView();
        // 获取公司id和季度
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        if (productId != null) {
            List<CompanyAdvertise> companyAdvertises = companyService.selectCompanyAdvertiseByProductId(company_id,
                    quarter, productId);
            modelAndView.addObject("companyAdvertises", companyAdvertises);
        }
        // 查询可销售产品信息
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            companyProducts.addAll(companyService.selectProductByCompanyIdAndQuarter(company_id, i));
        }
        modelAndView.addObject("companyProducts", companyProducts);
        // 查询广告语信息
        List<AdvertiseInfo> advertiseInfos = staticInfoService.showAdvertiseInfo();
        modelAndView.addObject("advertiseInfos", advertiseInfos);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("productId", productId);
        modelAndView.setViewName("design_advertise");
        return modelAndView;
    }

    @MethodLog(description = "更新产品广告语")
    @RequestMapping("/UpdateCompanyAdvertise.do")
    public ModelAndView UpdateCompanyAdvertise(HttpServletRequest request) throws Exception {
        System.out.println("更新产品广告语。。。");
        // 获取相关数据
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = companyService.selectCompanyProduct(productId).getName();
        String checkedString = request.getParameter("checkedString");
        String advertiseName = request.getParameter("advertiseName");
        String method = request.getParameter("method");
        System.out.println("method" + method);
        if ("query".equals(method)) {

        } else {
            System.out.println("更新操作");
            // 装载
            CompanyAdvertise companyAdvertise = new CompanyAdvertise();
            companyAdvertise.setCompanyId(company_id);
            companyAdvertise.setQuarter(quarter);
            companyAdvertise.setProductId(productId);
            companyAdvertise.setProductName(productName);
            companyAdvertise.setAdvertiseId(checkedString);
            companyAdvertise.setAdvertiseName(advertiseName);
            // 判断添加或更新
            List<CompanyAdvertise> companyAdvertises = companyService.selectCompanyAdvertiseByProductId(company_id,
                    quarter, productId);
            if (companyAdvertises.size() == 0) {
                // 添加
                companyService.insertCompanyAdvertise(companyAdvertise);
            } else {
                companyService.updateCompanyAdvertise(companyAdvertise);
            }

        }
        ModelAndView modelAndView = designAdvertisement(request, productId);
        return modelAndView;
    }

    public ModelAndView checkCashAmount(HttpServletRequest request, float expense, int quarter) throws Exception {
        /*
         * 若是返回NULL值，可用资金充足 若不是NULL值，说明资金不够，跳转至贷款页面
		 */
        ModelAndView modelAndView = showBalanceSheet(request);
        int company_id = (int) request.getSession().getAttribute("companyId");
        BalanceSheet balanceSheet = companyService.selectBalanceSheet(company_id, quarter);
        float huobi = balanceSheet.getHuobi();
        if ((huobi - expense) < 0) {
            ModelAndView mv = showEmergencyLoan(request, quarter);
            mv.addObject("method", "redirect");
            mv.setViewName("emergencyLoan");
            return mv;
        } else {
            return null;
        }
    }


    //	1-15 判断
    public String checkDecision(int quarter, int company_id) {
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            companyProducts.addAll(companyService.selectProductByCompanyIdAndQuarter(company_id, i));
        }
        String info = "成功";
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            if (quarter == 1) {
                break;
            } else {
                ProductPrice price = companyService.showPrice(productId, quarter);
                if (price == null) {
                    info = "尚有产品未设计价格！";
                    return info;
                }
                CompanyProductDemand demand = companyService.selectDemand(productId, quarter);
                if (demand == null) {
                    info = "尚有产品未设计需求量！";
                    return info;
                }
                CompanyProductInventory inventory = companyService.selectInventory(productId, quarter);
                if (inventory == null) {
                    info = "尚有产品未设计库存量！";
                    return info;
                }
            }
        }
        return info;
    }

    @ResponseBody
    @RequestMapping("/submit.do")
    public String submit(HttpServletRequest request) {
        Integer companyId = (Integer) request.getSession().getAttribute("companyId");
        int quarter = Integer.valueOf(request.getParameter("quarter"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String endTime = df.format(new Date());
        companyService.updateCompanyQuarterTime(companyId, quarter, endTime);
        request.getSession().setAttribute("is_submit", 1);
        return "提交成功！";
    }

    @MethodLog(description = "紧急银行贷款")
    @RequestMapping(value = "/showEmergencyLoan.do ")
    public ModelAndView showEmergencyLoan(HttpServletRequest request, int quarter) throws Exception {
        System.out.println("查看紧急银行贷款:季度" + quarter);
        int company_id = (int) request.getSession().getAttribute("companyId");
        int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
        int isSubmit = (int) request.getSession().getAttribute("isSubmit");

        ModelAndView modelAndView = new ModelAndView();

        float huobi = 0;//季初资金 
        float getLoan = 0;
        float reLoan = 0;
        List<BalanceSheet> balanceSheet = companyService.selectBalanceSheetResult(company_id, quarter - 1);
        if (balanceSheet.size() != 0) {
            huobi = balanceSheet.get(0).getHuobi();//季初资金
        }
        CompanyLoan loan = companyService.selectCompanyLoan(company_id, quarter);
        if (loan != null) {
            getLoan = loan.getGetNum();
            reLoan = loan.getReturnNum();
        } else {
            CompanyLoan companyLoan = companyService.selectCompanyLoan(company_id, quarter - 1);
            if (companyLoan != null) {
                companyService.insertLoanLast(companyLoan.getLoanLast() + companyLoan.getGetNum() - companyLoan.getReturnNum(), company_id, quarter);
            } else {
                companyService.insertLoanLast(0, company_id, quarter);
            }

        }


        //把贷款存到decision表
        float sumLoan = getLoan - reLoan;
        CompanyDecision companyDecision = companyService.selectCompanyDecision(company_id, 101);
        if (companyDecision == null) {
            companyService.insertCompanyDecision(company_id, (int) sumLoan, 101);
        } else {
            companyService.updateCompanyDecision(company_id, (int) sumLoan, 101);
        }
        CompanyDecision companyDecision2 = companyService.selectCompanyDecision(company_id, 100);
        if (companyDecision2 == null) {
            companyService.insertCompanyDecision(company_id, (int) huobi, 100);
        } else {
            companyService.updateCompanyDecision(company_id, (int) huobi, 100);
        }

//		可用资金
        int resMoney = companyService.selectDecision(company_id);
        System.out.println("可用资金" + resMoney);

//		债务能力
        int guben = 0;
        for (int i = 1; i <= quarter; i++) {
            guben += companyService.selectGuBen(company_id, i);
        }
        float totalDebt = (float) (guben * 0.8);
        modelAndView.addObject("totalDebt", totalDebt);

        CompanyLoan companyLoan2 = companyService.selectCompanyLoan(company_id, quarter);
        modelAndView.addObject("resMoney", resMoney);
        modelAndView.addObject("companyLoan", companyLoan2);
        modelAndView.addObject("quarter", quarter);
        modelAndView.addObject("currentQuarter", currentQuarter);
        modelAndView.addObject("isSubmit", isSubmit);
        modelAndView.setViewName("emergencyLoan");
        return modelAndView;
    }

    @RequestMapping(value = "/showEmergencyLoan1.do ")
    public ModelAndView showEmergencyLoan1(HttpServletRequest request) throws Exception {
        System.out.println("操作紧急银行贷款");
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        float get = Float.parseFloat(request.getParameter("get"));
        float payBack = Float.parseFloat(request.getParameter("return"));
        CompanyLoan companyLoan = companyService.selectCompanyLoan(company_id, quarter);
        if (companyLoan == null) {
            companyService.insertCompanyLoan(company_id, quarter, get, payBack);
        } else {
            float preGet = companyLoan.getGetNum();
            float preBack = companyLoan.getReturnNum();
            companyService.updateCompanyLoan(company_id, quarter, preGet + get, preBack + payBack);
        }
        ModelAndView mv = showEmergencyLoan(request, quarter);
        int resMoney = companyService.selectDecision(company_id);
        mv.addObject("resMoney", resMoney);
        mv.addObject("quarter", quarter);
        mv.setViewName("emergencyLoan");
        return mv;
    }

    @MethodLog(description = "查看资产负债表操作")
    @RequestMapping(value = "/showBalanceSheet.do ")
    public ModelAndView showBalanceSheet(HttpServletRequest request) throws Exception {
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(request.getParameter("quarter"));
        ModelAndView md = new ModelAndView();
        String info = checkDecision(quarter, company_id);
        if (info != "成功") {
            md.addObject("info", info);
            md.setViewName("welcome");
            return md;
        }
        md = showCashFlow(request, String.valueOf(quarter));
        CashFlow cashFlow = companyService.selectCashFlow(company_id, quarter);
        //股本
        if (quarter == 1) {
            companyService.updateBalanceSheetGuBen(2000000, company_id, quarter);
        } else if (quarter == 2) {
            companyService.updateBalanceSheetGuBen(3000000, company_id, quarter);
        } else if (quarter == 3) {
            companyService.updateBalanceSheetGuBen(4000000, company_id, quarter);
        }
        //三个月存款
        int cunru = companyService.selectCunru(company_id, quarter);
        int tiqu = companyService.selectTiQu(company_id, quarter);
        int cunkuanLast = companyService.selectCunKuanLast(company_id, quarter);
        int cunkuan = cunru - tiqu + cunkuanLast;
        companyService.updateBalanceSheetCunKuan(cunkuan, company_id, quarter);
        //存货
        int cunhuo = (int) (cashFlow.getKucunPay() * 10);
        companyService.updateBalanceSheetCunHuo(cunhuo, company_id, quarter);
        //固定资产
        int invest1 = 0;
        int invest2 = 0;
        int invest3 = 0;
        CompanyCapacity companyCapacity1 = companyService.showCapacity(company_id, 1);
        if (companyCapacity1 != null) {
            int capacity1 = companyCapacity1.getCapacityAdd();
            invest1 = companyService.selectInvestByCapacity(capacity1);
        }
        CompanyCapacity companyCapacity2 = companyService.showCapacity(company_id, 2);
        if (companyCapacity2 != null) {
            int capacity2 = companyCapacity2.getCapacityAdd();
            invest2 = companyService.selectInvestByCapacity(capacity2);
        }
        CompanyCapacity companyCapacity3 = companyService.showCapacity(company_id, 3);
        if (companyCapacity3 != null) {
            int capacity3 = companyCapacity3.getCapacityAdd();
            invest3 = companyService.selectInvestByCapacity(capacity3);
        }
        if (quarter == 1) {
            companyService.updateBalanceSheetZiChan(invest1, company_id, quarter);
        } else if (quarter == 2) {
            companyService.updateBalanceSheetZiChan(invest1 + invest2, company_id, quarter);
        } else if (quarter == 3) {
            companyService.updateBalanceSheetZiChan(invest1 + invest2 + invest3, company_id, quarter);
        }
        //紧急贷款
        if (quarter > 3) {
            CompanyLoan companyLoan = companyService.selectCompanyLoan(company_id, quarter);
            float getLoan = 0;
            float returnLoan = 0;
            float loanLast = 0;
            if (companyLoan != null) {
                getLoan = companyLoan.getGetNum();
                returnLoan = companyLoan.getReturnNum();
                loanLast = companyLoan.getLoanLast();
            }
            float totalLoan = loanLast + getLoan - returnLoan;
            companyService.updateBalanceSheetLoan(company_id, quarter, totalLoan, (float) (totalLoan * 0.04));
        }
        //货币和留存
        float xianjinGet = cashFlow.getXianJinGet();
        float xianjinPay = cashFlow.getXianJinPay();
        float lirun = xianjinGet - xianjinPay;
        float gongchang = cashFlow.getGongchangPay();
        float huobi = 0;
        float liucun = 0;
        if (quarter == 1) {
            huobi = 2000000 + lirun - gongchang - cunru + tiqu - cunhuo;
            liucun = lirun;
        } else {
            BalanceSheet balanceSheet = companyService.selectHuoBiLast(company_id, quarter - 1);
            float huobiLast = balanceSheet.getHuobi();
            huobi = huobiLast + 1000000 + lirun - gongchang - cunru + tiqu - cunhuo - (float) (cunkuanLast * 1.5 / 100);
            float liucunLast = balanceSheet.getLiucun();
            liucun = liucunLast + lirun;
        }
        companyService.updateBalanceSheetHuoBi(huobi, company_id, quarter);
        companyService.updateBalanceSheetLiuCun(liucun, company_id, quarter);
        companyService.updateBalanceSheet3(0, company_id, quarter);

        ModelAndView modelAndView = new ModelAndView();
        if (quarter == 1) {
            List<BalanceSheet> list = companyService.showBalanceSheet(company_id, 1);
            modelAndView.addObject("assertSheetList", list);
        } else {
            List<BalanceSheet> list = companyService.showBalanceSheetResult(company_id, 1);
            for (int i = 2; i < quarter; i++) {
                list.addAll(companyService.showBalanceSheetResult(company_id, i));
            }
            list.addAll(companyService.showBalanceSheet(company_id, quarter));
            modelAndView.addObject("assertSheetList", list);
        }
        modelAndView.addObject("quarter", quarter);
        modelAndView.setViewName("assertSheet");
        return modelAndView;
    }

    @MethodLog(description = "查看现金流表操作")
    @RequestMapping(value = "/showCashFlow.do ")
    public ModelAndView showCashFlow(HttpServletRequest request, String quarter1) throws Exception {
        int company_id = (int) request.getSession().getAttribute("companyId");
        int quarter = Integer.parseInt(quarter1);
        int compete_id = (int) request.getSession().getAttribute("competitionId");

        ModelAndView modelAndView = new ModelAndView();

        //找出该公司当季度所有产品
        List<CompanyProduct> companyProducts = companyService.selectProductByCompanyIdAndQuarter(company_id, 1);
        for (int i = 2; i <= quarter; i++) {
            companyProducts.addAll(companyService.selectProductByCompanyIdAndQuarter(company_id, i));
        }
        //收入
        int income = 0;
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            int productPrice = 0;
            int demandNum = 0;
            ProductPrice price = companyService.showPrice(productId, quarter);
            if (quarter == 1) {
                break;
            } else {
                if (price == null) {
                    String info = "尚有产品未设计价格！";
                    modelAndView.addObject("info", info);
                    modelAndView.setViewName("welcome");
                    return modelAndView;
                }
                productPrice = price.getPrice();

                CompanyProductDemand demand = companyService.selectDemand(productId, quarter);
                if (demand == null) {
                    String info = "尚有产品未设计需求量！";
                    modelAndView.addObject("info", info);
                    modelAndView.setViewName("welcome");
                    return modelAndView;
                }
                demandNum = demand.getDemand();
                income += productPrice * demandNum;
            }
        }
        companyService.updateCashFlowShouRu(income, company_id, quarter);
        //收取利息以及存款
        int cunru = companyService.selectCunru(company_id, quarter);
        int tiqu = companyService.selectTiQu(company_id, quarter);
        int cunkuanLast = companyService.selectCunKuanLast(company_id, quarter);
        DecimalFormat df = new DecimalFormat("#.00");
        float lixi = Float.parseFloat(df.format((cunkuanLast + cunru - tiqu) * 0.015));
        companyService.updateCashFlowCunkuanPay(cunru, company_id, quarter);
        companyService.updateCashFlowTiqu(tiqu, company_id, quarter);
        companyService.updateCashFlowLixi(lixi, company_id, quarter);
        //邮寄返款
        int youji_sum = 0;
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            int youji = 0;
            ProductPrice price = companyService.showPrice(productId, quarter);
            if (price != null) {
                youji = price.getYouji();
            }
            if (quarter == 1) {
                break;
            } else {
                int demandNum = companyService.selectProductDemand(productId, quarter);
                youji_sum += youji * demandNum;
            }
        }
        companyService.updateCashFlowYouJi(youji_sum, company_id, quarter);
        //生产支出的现金和库存费用
        int shengchanCostSum = 0;
        int kucunCostSum = 0;
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            int productCost = companyService.selectProductCost(productId);
            int num = 0;
            int kucunNum = 0;
            if (quarter == 1) {
                break;
            } else {
                CompanyProductInventory inventory = companyService.selectInventory(productId, quarter);
                if (inventory == null) {
                    String info = "尚有产品未设计库存量！";
                    modelAndView.addObject("info", info);
                    modelAndView.setViewName("welcome");
                    return modelAndView;
                }
                num = companyService.selectProductDemand(productId, quarter);
                kucunNum = inventory.getInventory();
            }
            int shengchanCost = companyProducts.get(i).getShengChanCost(num + kucunNum, productCost);
            kucunCostSum += shengchanCost * kucunNum;
            shengchanCostSum += shengchanCost * (num + kucunNum);
        }
        companyService.updateCashFlowShengChan(shengchanCostSum, company_id, quarter);
        companyService.updateCashFlowKuCun(kucunCostSum / 10, company_id, quarter);
        //研发
        companyService.updateCashFlowYanfa((float) (companyService.selectProductByCompanyIdAndQuarter(company_id, quarter).size() * 60000), company_id, quarter);
        //广告
        int mediaCost = 0;
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            List<CompanyMedia> companyMedias = companyService.selectProductMedia(productId, quarter);
            for (int j = 0; j < companyMedias.size(); j++) {
                int mediaId = companyMedias.get(j).getMediaId();
                int num = companyMedias.get(j).getNum();
                int cost = companyService.selectMediaCost(mediaId);
                mediaCost += num * cost;
            }
        }
        companyService.updateCashFlowMediaCost(mediaCost, company_id, quarter);

        //销售中心(实体加网络)
        int PhyCost = 0;
        int WebCost = 0;
        int WebOpenCost = 0;
        int WebRentCost = 0;
        String Phy = "";
        String Web = "";
        //显示所有市场的信息
        List<MarketInfo> marketInfoList = companyService.showMarketInfo(compete_id);
        for (int i = 0; i < marketInfoList.size(); i++) {
            WebOpenCost += marketInfoList.get(i).getWebOpen();
            WebRentCost += marketInfoList.get(i).getWebRent();
        }

        if (quarter == 1) {
            CompanyMarket companyMarketPhy = companyService.selectPhyCompanyMarket(company_id, quarter);
            if (companyMarketPhy != null) {
                Phy = companyMarketPhy.getMarketId();
                String[] PhyId = Phy.split(",");
                for (int i = 0; i < PhyId.length; i++) {
                    int id = Integer.parseInt(PhyId[i]);
                    PhyCost += companyService.selectOpenByMarketId(id);
                }
            }
            CompanyMarket companyMarketWeb = companyService.selectWebCompanyMarket(company_id, quarter);
            if (companyMarketWeb != null) {
                Web = companyMarketWeb.getMarketId();
                if (Web.equals("")) {
                } else {
                    WebCost = WebOpenCost;
                }
            }
        } else {
            //实体店
            //查询已开设的实体店
            MarketOpenedPo marketOpenedPo = companyService.selectMarketOpen(company_id);
            //查询当前季度开设的实体店
            CompanyMarket companyMarket = companyService.selectPhyCompanyMarket(company_id, quarter);
            if (companyMarket != null) {
                //获取市场id数组
                String[] PhyId = companyMarket.getMarketId().split(",");
                if (marketOpenedPo != null) {
                    String[] marketOpen = marketOpenedPo.getMarket_opened().split(",");
                    for (int i = 0; i < PhyId.length; i++) {
                        int id = Integer.parseInt(PhyId[i]);
                        int flag = 0;
                        for (int j = 0; j < marketOpen.length; j++) {
                            int id2 = Integer.parseInt(marketOpen[j]);
                            if (id == id2) {
                                flag = 1;
                                PhyCost += companyService.selectRent(id);
                                break;
                            }
                        }
                        if (flag == 0) {
                            PhyCost += companyService.selectOpenByMarketId(id);
                        }
                    }
                } else {
                    //全部都是开设费用
                    for (int i = 0; i < PhyId.length; i++) {
                        int id = Integer.parseInt(PhyId[i]);
                        PhyCost += companyService.selectOpenByMarketId(id);
                    }
                }
            } else {
                PhyCost = 0;
            }

            //网络店
            MarketWebOpened marketWebOpened = companyService.selectMarketWebOpen(company_id, quarter);
            if (marketWebOpened != null) {
                WebCost = marketWebOpened.getCost();
            }

        }
        companyService.updateCashFlowPhyMarket(PhyCost, company_id, quarter);
        companyService.updateCashFlowWebMarket(WebCost, company_id, quarter);

        //货运
        int demand_sum = 0;
        for (int i = 0; i < companyProducts.size(); i++) {
            int productId = companyProducts.get(i).getId();
            if (quarter == 1) {
                break;
            } else {
                int demandNum = companyService.selectProductDemand(productId, quarter);
                demand_sum += demandNum;
            }
        }
        companyService.updateCashFlowHuoYun(demand_sum * 100, company_id, quarter);
        //工厂
        int capacity = companyService.selectCapacity(company_id, quarter);
        int invest = companyService.selectInvestByCapacity(capacity);
        companyService.updateCashFlowCapacity(invest, company_id, quarter);

//	紧急贷款
        if (quarter > 3) {
            CompanyLoan companyLoan = companyService.selectCompanyLoan(company_id, quarter);
            float getLoan = 0;
            float returnLoan = 0;
            if (companyLoan != null) {
                getLoan = companyLoan.getGetNum();
                returnLoan = companyLoan.getReturnNum();
            }
            companyService.updateCashFlowLoan(company_id, quarter, getLoan, returnLoan);
        }

        //季初现金
        if (quarter != 1) {
            float jichuXianjin = companyService.selectHuoBiLast(company_id, quarter - 1).getHuobi();
            companyService.updateCashFlowJiChu(jichuXianjin, company_id, quarter);
        }


        if (quarter == 1) {
            List<CashFlow> list1 = companyService.showCashFlow(company_id, 1);
            modelAndView.addObject("cashFlowList", list1);
        } else {
            List<CashFlow> list1 = companyService.showCashFlowResult(company_id, 1);
            for (int i = 2; i < quarter; i++) {
                list1.addAll(companyService.showCashFlowResult(company_id, i));
            }
            list1.addAll(companyService.showCashFlow(company_id, quarter));
            modelAndView.addObject("cashFlowList", list1);
        }
        modelAndView.addObject("quarter", quarter);
        modelAndView.setViewName("cashFlow");
        return modelAndView;
    }
}
