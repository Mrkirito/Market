<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <style type="text/css">
        body {
            width: 99%;
            margin: 5px;
        }

        .panel-body {
            background-size: cover;
        }

        .panel {
            margin: 0px;
        }

        .table {
            width: 400px;
            height: 300px;
            background: white;
            border： #ddd;
        }

        #two.td {
            text-align: center;
        }

        #notice2 {
            min-height: 420px;
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


    </style>

</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">固定产能</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade" id="notice1">
                <div class="course_content">
                    <div class="text0">
                        <label>固定产能 VS 运行产能</label>
                    </div>
                    <div class="text1">
                        有两种关于生产的设定，一种是固定产能，另一种是日运行产能。
                    </div>
                    <div class="text1">
                        固定产能决定了工厂每天所能生产的最大产品数。
                    </div>
                    <div class="text1">
                        运行产能决定了生产线的运行速度，因此，也决定了每天实际生产的产品数。 您可以通过控制生产线的运行速度来调整日运行产能。 日运行产能的变化将影响雇佣工人的数量。
                    </div>
                    <div class="text1">
                        运行产能不能高于固定产能。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        相应的决策界面列出了公司所有的管理团队成员。
                        <ul>
                            <li>
                                选择工厂的固定产能。 您必须决定为市场测试阶段兴建的生产线规模。 工厂产能由每日和每季度的生产量来衡量。 工厂每周可以运行 5 天，每季度生产 13 周。 也就是每季度 65
                                天
                            </li>
                            <li>
                                请根据目标细分市场和区域市场的规模，选择每日 25 件或者每日 50 件。 同时您也需要考虑公司在扩张销售渠道方面的激进度。
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="right">
                    <div class="right_title">
                        <span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                    </div>
                    <div class="right_content">
                        <ul>
                            <li>在进行市场测试的最初阶段，公司的销量不大可能高于每天 10 件，或者说每季度 650 件。
                                您可能需要通过一到两个季度的时间来学习如何面向手机行业进行市场营销，以及树立您的品牌知名度。 因此，在公司运营的初期，您并不需要一个产能巨大的工厂。 之外，购买大于每天 50
                                件的固定产能在资金上也不可行。
                            </li>
                            <li>固定产能的增加将有一个季度的时滞。 也就是说，如果您在第三季度投资将固定产能扩大25 件/天，那么在第四季度之前您将无法使用新增的产能。
                                从第四季度开始，您才能使用新增的产能（25件/天）。 此，提前为将来做打算很重要。您应当提前估算未来一到两个季度的市场需求量，然后对固定产能作出相应的规划。
                                如果扩张了销售渠道，您需要相应地扩大固定产能。 如果您增添了新的品牌和新的销售人员，同样也应当考虑按需扩大固定产能。
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <input type="hidden" id="quarter" value="${quarter }"/>
                <input type="hidden" id="currentQuarter" value="${currentQuarter }"/>
                <input type="hidden" id="resMoney" value="${resMoney }"/>
                <input type="hidden" id="isSubmit" value="${isSubmit }">
                <form id="form" class="form-horizontal" action="" method="post">
                    <input type="hidden" name="money" value="${money}">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="success">
                            <th></th>
                            <th>件/天</th>
                            <th>件/季度</th>
                            <th>资本性投资</th>
                            <th>单位资本性成本</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${capacityInfoList}" var="item">
                            <tr id="showInfo">
                                <td>
                                    <c:if test="${companyCapacityList1[0].capacityAdd==item.number}">
                                        <input type="radio" name="group" checked="checked"></c:if>


                                    <c:if test="${companyCapacityList1[0].capacityAdd!=item.number}">
                                        <input type="radio" name="group"></c:if>


                                </td>
                                <td value="${item.number }">${item.number }</td>
                                <td>${item.totalNumber }</td>
                                <td value="${item.invest }">${item.invest }</td>
                                <td>${item.totalInvest }</td>
                            </tr>


                        </c:forEach>


                        <tr>
                            <td colspan="5">
                                <button onclick="init()" id="btn1"
                                        class="btn btn-info">提交
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </form>

                <table align="right" id="two" width="200">
                    <tr>

                        <td>当前季度日固定产能</td>
                        <td>${companyCapacityList1[0].capacityNow}</td>

                    </tr>

                    <tr>

                        <td>计划增加的固定产能</td>
                        <c:if test="${ not empty companyCapacityList1}">
                            <td>${companyCapacityList1[0].capacityAdd }</td>
                        </c:if>
                        <c:if test="${ empty companyCapacityList1}">
                            <td>0</td>
                        </c:if>


                    </tr>

                    <tr>

                        <td>下季度日固定产能</td>
                        <td>${companyCapacityList1[0].capacityNow+companyCapacityList1[0].capacityAdd }</td>

                    </tr>
                </table>

            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript">
    //判断是否隐藏提交按钮
    $(function () {
        var currentQuarter = $("#currentQuarter").val();
        var quarter = $("#quarter").val();
        var isSubmit=$("#isSubmit").val();
        if(currentQuarter>quarter){
            $("button").attr("disabled",true);
        }else{
            if(isSubmit == 1){
                $("button").attr("disabled",true);
            }
        }
    });


    function init() {
        var val = $('input:radio[name="group"]:checked').val();
        var r = $('input:radio[name="group"]:checked');
        if (val == null) {
            alert("什么也没选中!");
            return false;
        } else {

            var number = $(r).parents("tr").children("td").eq(1).attr("value");
            var invest = $(r).parents("tr").children("td").eq(3).attr("value");
            
            var checked = $(r).attr("checked");
            checked = true;
            
            var quarter = $("#quarter").val();
            if(quarter>3){
            	var resMoney=$("#resMoney").val();
            	if(resMoney-invest<0){
                	alert("资金不足，请去紧急贷款！")
                	document.getElementById("form").action = "showCapacityInfo1.do?number="
                        + number + "&invest=" + invest + "&quarter=" + quarter;
                	document.getElementById("form").submit();
                }else{
                	alert("提交成功")
                	document.getElementById("form").action = "showCapacityInfo1.do?number="
                        + number + "&invest=" + invest + "&quarter=" + quarter;
                    document.getElementById("form").submit();
                }
            }else{
            	alert("提交成功")
            	document.getElementById("form").action = "showCapacityInfo1.do?number="
                    + number + "&invest=" + invest + "&quarter=" + quarter;
                document.getElementById("form").submit();
            }
            
        
            
            
        }
    }
</script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>