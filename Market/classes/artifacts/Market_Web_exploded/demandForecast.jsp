<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <style type="text/css">
        body {
            width: 99%;
            margin: 5px;
            height: 1000px;
        }

        .panel {
            margin: 0px;
        }

        .panel-body {
            background-size: cover;
        }

        .table {
            width: 700px;
            height: 80px;
            background: white;
        }

        td, th {
            text-align: center;
        }

        .course_content {
            border: 1px solid #0ff;
            margin-top: 10px;
            padding: 20px;
            min-height: 220px;
            font-size: 14px;
            letter-spacing: 1px;
        }

        .left {
            /* border:1px solid blue; */
            width: 450px;
            padding: 20px;
            float: left;
            background: #eee;
            margin: 10px 0 0 2px;
            min-height: 220px;
        }

        .left_title span i {
            color: #009;
            font-size: 18px;
            margin-right: 6px;
        }

        .right {
            /* border:1px solid blue; */
            width: 400px;
            padding: 20px;
            float: left;
            background: #abcdef;
            margin: 10px 0 0 60px;
            min-height: 120px;
        }

        #button {
            float: right;
        }

        .right_title span i {
            color: red;
            font-size: 22px;
            margin-right: 6px;
        }

        .text0 {
            margin-left: 30px;
            margin-top: 10px;
        }

        .text1 {
            text-indent: 30px;
            margin-top: 10px;
        }

        .text1_ul {
            margin-left: 40px;
            margin-top: 10px;
        }

        .text2 {
            margin-left: 30px;
            margin-top: 25px;
        }

        .left_content li {
            margin-top: 5px;
        }

        .tab-content {
            margin-top: 10px;
        }

        .form-control {
            text-align: center;
        }

        #notice2 {
            padding-top: 10px;
        }

        #table1 {
            width: 80%;
            margin-top: 5px;
        }

        #table2 {
            width: 40%;
            float: left;
        }

        #table2 td lable {
            border: 1px dotted white;
        }

        #table3 {
            width: 20%;
            float: left;
        }

        #table3 tr {
            text-align: center;
        }

        #table3 td input {

        }

        .kaozuo {
            text-align: left;
        }

        #table2 tr {
            height: 12px;
        }

        #table3 tr {
            height: 37px;
        }

        .split {
            clear: both;
        }
    </style>

</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">需求量预测</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade " id="notice1">
                <div class="course_content">
                    <div class="text1">预测需求量的关键就在于估计每个销售人员所能创造的销量。
                        将这一人均销量乘以销售人员总数，就可以得到预测需求量。
                    </div>
                    <div class="text1">实践准则: 预计需求量 = 人均销量 X 当前雇佣的销售人员总数</div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">估计销售人员在当前季度可能创造的销售量。
                        在市场测试初期（第二季度及第三季度），市场对您的公司和品牌一无所知。 而且，您的某些策略很可能没正中目标而需要修改。
                        因此，在预测需求量的时候应该适度保守。 作为参考，每个销售人员每季度大概可以销售 30 ~ 100 件电脑。
                        如果您在某区域同时拥有实体和网络销售中心，则两者之间也会相互争夺潜在消费者。 在这种情况下，销售人员的人均需求量可能会降低 30%
                        到 60%。 将本季度预计人均销量定为 50 件是比较合适的。
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <input type="hidden" id="quarter" value=" ${quarter }">
                <input type="hidden" id="currentQuarter" value="${currentQuarter }">
                <input type="hidden" id="isSubmit" value="${isSubmit }">

                <form class="form-horizontal" id="form" method="post"
                      action="updateDemandForecast.do?quarter=${quarter }">
                    <h5>销售人员及销售量</h5>
                    <table class="table table-bordered" id="table1">
                        <thead>
                        <tr class="success">
                            <th></th>
                            <th colspan="3">上季度</th>
                            <th colspan="3">当前季度</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>销售中心</td>
                            <td>销售人员数量(人)</td>
                            <td>销售人员预计销售量（件/季度）</td>
                            <td>预计总销售量（件）</td>
                            <td>销售人员数量(人)</td>
                            <td>销售人员预计销售量（件/季度）</td>
                            <td>预计总销售量（件）</td>

                        </tr>
                        <tr>
                            <td>实体销售中心</td>
                            <td>${phySalemanNumberPro }</td>
                            <td>${demandForecastPro.demandAveragePhy }</td>
                            <td>${phySalemanNumberPro*demandForecastPro.demandAveragePhy }</td>
                            <td>${phySalemanNumber}</td>
                            <input id="people"  hidden="hidden" type= "text" value="${phySalemanNumber}">
                            <td><input class="form-control" type="text" onchange="myfunction()"
                                       name="demand_average_phy" id="sales"
                                       value="${demandForecast.demandAveragePhy }"
                                       onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                       onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="8">
                            </td>
                            <td><span name="total">${phySalemanNumber*demandForecast.demandAveragePhy}</span></td>
                            <!-- <td> <input id ="TotalSales"  value=""></td> -->
                           <%--  <td>${phySalemanNumber*demandForecast.demandAveragePhy}</td> --%>
                        </tr>
                        <tr>
                            <td>网络销售中心</td>
                            <td>${webSalemanNumberPro}</td>
                            <td>${demandForecastPro.demandAveragePhy }</td>
                            <td>${webSalemanNumberPro*demandForecastPro.demandAveragePhy}</td>
                            <td>${webSalemanNumber}</td>
                             <input id="people1"  hidden="hidden" type= "text" value="${webSalemanNumber}">
                            <td><input class="form-control" type="text" id="websales" onchange="myfunction1()"
                                       name="demand_average_web"
                                       value="${demandForecast.demandAverageWeb}"
                                       onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                       onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="8">
                            </td>
                            <td><span name="total">${webSalemanNumber*demandForecast.demandAverageWeb}</span></td>
                        </tr>
                       <%--  <tr>
                          
                            <td>${phySalemanNumberPro+webSalemanNumberPro }</td>
                            <td>${demandForecastPro.demandAveragePhy+demandForecastPro.demandAveragePhy }</td>
                            <td>${phySalemanNumberPro*demandForecastPro.demandAveragePhy+webSalemanNumberPro*demandForecastPro.demandAveragePhy }</td>
                            <td>${phySalemanNumber+webSalemanNumber}</td>
                            <td>${demandForecast.demandAveragePhy+demandForecast.demandAverageWeb }</td>
                            <td>${phySalemanNumber*demandForecast.demandAveragePhy+webSalemanNumber*demandForecast.demandAverageWeb}</td>
                        </tr> --%>

                        </tbody>
                    </table>
                    <br>
                    <h5>产品信息</h5>
                    <table id="table2" class="table table-bordered">
                        <thead>
                        <tr class="success">

                            <th>产品</th>
                            <th>上季度预计需求量</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${companyProductResult2 }" var="entry">
                            <tr>
                                <td class="kaozuo" colspan="2">第${entry.key}季度产品</td>
                            </tr>
                            <c:forEach items="${entry.value }" var="item">

                                <tr>
                                    <td>
                                        <lable class="form-control">${item.name}</lable>
                                    </td>
                                    <td>
                                        <lable class="form-control">${item.demand }</lable>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        <tr>
                            <td>合计</td>
                            <td>${demandNumberPro }</td>
                        </tr>

                        </tbody>
                    </table>
                    <table id="table3" class="table table-bordered">
                        <thead>
                        <tr class="success">
                            <th>本季度预计需求量</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${companyProductResult }" var="entry">
                            <tr>
                                <td colspan="4"></td>
                            </tr>
                            <c:forEach items="${entry.value }" var="item">
                                <input type="hidden" name="productId" value="${item.id}">
                                <tr>
                                    <c:if test="${item.priceFlag==0}">
                                        <td>
                                            <input disabled="disabled" type="text" name="${item.id }demand"
                                                   nameid="demand" 
                                                   class="form-control" value="${item.demand } "
                                                   onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="8">
                                        </td>
                                    </c:if>
                                    <c:if test="${item.priceFlag==1}">
                                        <td>
                                            <input
                                                    <c:if test="${item.isSale==0}">disabled</c:if> type="text"
                                                    name="${item.id }demand" nameid="demand"
                                                    class="form-control" value="${item.demand } "
                                                    onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                                    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="8">
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        <tr>
                            <td>${demandNumber }</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="split"></div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                        <tr class="success">
                            <th></th>
                            <th>件/季度</th>
                            <th>件/天</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>满足预测需求量所需的工厂有效产能</td>
                            <td>${demandNumber }</td>
                            <td><fmt:formatNumber type="number"
                                                  value="${demandNumber/30 }"
                                                  pattern="0" maxFractionDigits="0"/></td>
                        </tr>
                        </tbody>
                    </table>

                    <button class="btn btn-info" type="button" id="button" onclick="sign()">提交</button>
                </form>
            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
<script type="text/javascript">

$(function() {
	var currentQuarter = $("#currentQuarter").val();
	//alert(currentQuarter);
	var quarter = $("#quarter").val();
	//alert(quarter);
	var isSubmit = $("#isSubmit").val();
	//alert(parseInt(currentQuarter)>parseInt(quarter));
	
	if (parseInt(currentQuarter)>parseInt(quarter)) {

		$("button").attr("disabled", true);
	} else {
	
		if (isSubmit == 1) {
			
			$("button").attr("disabled", true);
		}
		else{
			
			$("button").attr("disabled", false);}
	}
}); 







function   myfunction(){
	   //alert("123");
	   var people = $("#people").val();
	   var sales= $("#sales").val();
	   if(sales==''){
		  var  totalsales=0;
	   }else{
	   var totalsales = parseInt(people)*parseInt(sales);
	   }
	   var $total=$("span[name='total']");
    //alert(totalsales);
	   $total[0].innerText = totalsales;

   }
function   myfunction1(){
	   //alert("123");
	   var people = $("#people1").val();
	   var sales= $("#websales").val();
	   if(sales==''){
		   var  totalsales=0;
	   }else{
	   var totalsales = parseInt(people)*parseInt(sales);
	   }
	   var $total=$("span[name='total']");
	   $total[1].innerText = totalsales;
}
function sign() {

	
		//判断整数
		var text=$(".form-control")
		var flag=0
		for(var i=0;i<text.length;i++){
			if((text[i].value%1)!=0){
				alert("请输入整数！");
				text[i].value=1;
				return;
			}
		}
		
        var demands = $("input[name*='demand']");
        for (var i = 2; i < demands.length; i++) {
            if (demands[i].value == null || demands[i].value == "") {
                alert("输入不能为空！！！");
                $(demands[i]).focus();
                return;
            }
            


        }
        alert("提交成功");
        var text = $("input[type=text]");
       	for(var i=0;i<text.length;i++){
       		if(text[i].disabled==true){ 
       			$(text[i]).removeAttr("disabled"); 
       		}
       	}
       document.getElementById("form").submit();


    }
 
</script>
</html>