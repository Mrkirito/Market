<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/10/19
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <style type="text/css">
        body {
            width: 99%;
            margin: 5px;
        }

        .panel {
            margin: 0px;
        }

        #notice1 {
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
            margin: 10px 0 0 100px;
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

        .text2 {
            margin-left: 30px;
            margin-top: 25px;
        }

        .left_content li {
            margin-top: 5px;
        }

        .tab-content {
            padding-top: 10px;
        }
    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">可销售品牌</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in " id="notice1">
                <div class="course_content">

                    <div class="text1">
                        在之后的某个季度，您可能会将某些品牌撤出市场。 您所需要做的就是将其从销售列表中去掉。
                    </div>

                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>在相关的决策界面，您必须指明进行销售的品牌。 在完成这一步之前，您将无法输入产品的销售价格。
                            </li>
                            <li>然后，请为每个品牌输入销售价格。 您可能需要为某些品牌提供邮寄返款。 邮寄返款能刺激市场对某款品牌的兴趣和购买热情。 消费者认定的品牌价格等于销售价格减去邮寄返款额。
                                请确保扣除邮寄返款后的品牌售价不低于其销货成本。 邮寄返款通常在 100 到 300 之间。
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="right">
                    <div class="right_title">
                        <span><i class="fa fa-tasks"></i></span><label>决策小提示</label>
                    </div>
                    <div class="right_content">
                        <ul>
                            <li>如果将停产的品牌存货不多（低于 200 件），将其作为无用库存处理掉。 否则可能出现脱销及消费者反感。 如果您试着提高其售价，这样只会损害您的价格形象。
                                如果要处理掉某款品牌，请先在相应的决策界面反选该品牌（将其撤出市场）。 然后进入生产制造界面，停止该品牌的生产，并作为无用库存将其清仓。 清仓无用库存将耗费一个季度的时间。
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <form action="" method="post">
                    <input type="hidden" id="quarter" name="quarter" value="${quarter}">
                    <input type="hidden" id="currentQuarter" value="${currentQuarter }">
                    <input type="hidden" id="isSubmit" value="${isSubmit }">

                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr class="success">
                            <th>品牌</th>
                            <th>可销售品牌</th>
                            <th>零售价格</th>
                            <th>邮寄返款</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${priceList}" var="price">
                            <tr>
                                <td>${price.productName}</td>
                                <td><input type="checkbox" onclick="check(this)"
                                           <c:if test="${price.isSale==1}">checked</c:if>>
                                    <input name="productId" type="hidden" value="${price.productId}">
                                </td>
                                <td>${price.price}</td>
                                <td>${price.youji}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
                <div style="margin-left: 900px">
                    <button id="button1" class="btn btn-default btn-sm-2" onclick="putin()">
                        提交
                    </button>
                </div>
                <div id="main" style="width: 600px;height:400px;"></div>
            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script>
    function check(checkbox) {
        var checked = $(checkbox).attr("checked");
        if (checked == "checked") {
            $(checkbox).attr("checked", null);
        } else {
            $(checkbox).attr("checked", "checked");
        }
    }
    function putin() {
        var productId = $("input[name=productId]");
        var checked = $("input[type=checkbox]");
        var valueF = new Array();
        var valueT = new Array();
        for (var i = 0; i < checked.length; i++) {
            if (checked[i].checked == false) {
                valueF.push(productId[i].value);
            } else {
                valueT.push(productId[i].value);
            }
        }
        alert("提交成功");
        $("form").attr("action", "operatePriceAndSale.do?valueF=" + valueF + "&valueT=" + valueT);
        $("form").submit();
    }
</script>
<script>
    $(function(){
        var currentQuarter=$("#currentQuarter").val();
        var quarter=$("#quarter").val();
        var isSubmit=$("#isSubmit").val();
        if(currentQuarter>quarter){
            $("#button1").attr("disabled",true);
        }else{
            if(isSubmit==1){
                $("#button1").attr("disabled",true);
            }
        }
    });


</script>
</html>