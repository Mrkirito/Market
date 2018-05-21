<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
        html{
            height: 100%;
        }
        body{
            padding:0px;
            margin:0px;
            height: 500px;
            font-family: "Microsoft YaHei"
        } 
        a{color: white;}
        .nav {width: 200px; padding: 40px 28px 25px 0;height:100%;background: 
            #1e485d;color: white;box-sizing: border-box;--moz-box-sizing: border-box;}  
        ul.nav {padding: 0; margin: 0; font-size: 1em; line-height: 0.5em; list-style: none;}  
        ul.nav li {margin-top: 10px;}  

        ul.nav li a {line-height: 10px; font-size: 16px; padding: 10px 5px; color: #fff;text-indent: 20px; display: block; 
        text-decoration: none;} 
        ul.nav>li:nth-of-type(1){margin-top:0px;}
        ul.nav li a:hover {background-color:#675C7C;    color:white;} 
        ul.nav ul { margin: 0; padding: 0;display: none;background: #333;}  
        ul.nav ul li { margin: 0; padding: 0; clear: both;}  
        ul.nav ul li a { padding-left: 20px; font-size: 14px; font-weight: normal;} 
        ul.nav ul li a:hover {background-color:#D3C99C; color:#675C7C;}

        ul.nav ul li a:active {background-color:#D3C99C; color:#675C7C;}
        ul.nav ul li a:current {background-color:#f00; color:#f00;}

        ul.nav a:visited{
            /*color: #f00;*/
        }


        ul.nav ul ul li a {color:silver; padding-left: 40px;}  
        ul.nav ul ul li a:hover { background-color:#D3CEB8; color:#675C7C;}  
        ul.nav span{float:right;} 
    </style>
</head>
<body>
<ul class="nav"> 
   <li>
        <a href="#none">欢迎界面<i></i></a>
        <ul>
            <li><a href="welcome.jsp" target="main">欢迎界面</a></li>
            <li><a href="profile.jsp" target="main">Market 简介</a></li>
        </ul>
   </li> 
   <li>
        <a href="#none">开始竞赛<i></i></a>
        <ul>
            <li><a href="UpdateCompanyName.do" target="main">创建公司</a></li>
            <li><a href="strategyInfo.do?quarter=1" target="main">目标策略</a></li>
        </ul>
   </li>
   <li>
        <a href="#none">市场调研<i></i></a>
        <ul>
            <li><a href="showDemandInfo.do" target="main">产品性能需求</a></li>
            <li><a href="showUsageInfo.do" target="main">产品用途</a></li>
            <li><a href="showPriceInfo.do" target="main">愿意支付的价格</a></li>
            <li><a href="showMarketInfo2.do" target="main">市场规模</a></li>
            
        </ul>
   </li>
   <li>
        <a href="#none">人力资源<i></i></a>
        <ul>
            <li><a href="showAllMemberByComapnyId.do?quarter=1" target="main">职位分配</a></li>
            <li><a href="companyRuleInfo.do?quarter=1" target="main">团队规则</a></li>
            <li><a href="personalGoalInfo.do?quarter=1" target="main">团队目标</a></li>
            <li><a href="showAverageSalary.do" target="main">员工薪酬</a></li>
           
        </ul>
   </li>
    <li>
        <a href="#none">销售渠道<i></i></a>
        <ul>
            <li><a href="showMarketInfo.do?quarter=1" target="main">实体销售中心</a></li>
            <li><a href="showMarketWebInfo.do?quarter=1" target="main">网络销售中心</a></li>
            
        </ul>
    </li>

    <li>
        <a href="#none">生产制造<i></i></a>
        <ul>
            <li><a href="CompanyProduct.do?quarter=1" target="main">品牌管理</a></li>
            <li><a href="showCapacityInfo.do?quarter=1" target="main">固定产能</a></li>
        </ul>
    </li>

    

    <li>
        <a href="#none">财务<i></i></a>
        <ul>
            <li><a href="showShareHold.do?quarter=1" target="main">内部持股</a></li>
            <li><a href="showFixedDeposite.do?quarter=1" target="main">定期存款</a></li>
        </ul>
    </li>
    
     <li>
        <a href="#none">财会预算<i></i></a>
        <ul>
            <li><a href="showCashFlow.do?quarter1=1" target="main">现金流表</a></li>
            <li><a href="showIncomeStatement.do?quarter=1" target="main">利润表</a></li>
            <li><a href="showBalanceSheet.do?quarter=1" target="main">资产负债表</a></li>
        </ul>
    </li>

    <li>
         <a href="#none">决策汇总<i></i></a>
        <ul>
        	<li><a href="policyDecision/companyDecisionSummary.do?companyId=<%=request.getParameter("companyId") %> &currentQuarter=1" target="main">决策汇总</a></li>
        	
            <li><a href="finalCheck.do?quarter=1" target="main">最终检查</a></li>

        </ul>
    </li>
   <!-- <li><a href="#">文章</a></a> 
        <ul> 
           <li><a href="#" target="_blank">XHTML/CSS</a></li> 
           <li><a href="#">Javascript/Ajax</a> 
                <ul> 
                    <li><a href="#">Cookies</a></li> 
                    <li><a href="#">Event</a></li> 
                    <li><a href="#">Games</a></li> 
                    <li><a href="#">Images</a></li> 
                </ul> 
            </li> 
            <li><a href="#" target="_blank">PHP/MYSQL</a></li> 
            <li><a href="#" target="_blank">前端观察</a></li> 
            <li><a href="#" target="_blank">HTML5/移动WEB应用</a></li> 
        </ul> 
    </li>   -->
</ul> 

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/accordion.js"></script>
<script type="text/javascript">
$(function(){ 
   $(".nav").accordion({ 
        speed: 500, 
        closedSign: '+', 
        openedSign: '-' 
    }); 
   $(".nav").hover(function(){
    $(this).css("overflow","auto")
},function(){
    $(this).css("overflow","hidden")
})
}); 
</script>

</body>
</html>