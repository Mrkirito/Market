<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/design.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
    body {
        width: 99%;
        margin: 5px;
    }

    .table > thead > tr > td, .table > tbody > tr > td {
        display: table-cell;
        text-align: center;
        vertical-align: middle;
    }

    .chanpin {
        font-weight: bold;
        font-size: large;
        padding-left: 10px;
    }

    .btn1 {
        width: 120px;
        height: 31px;
        background-color: #808080;
        color: white;
        border-radius: 2px;
        float: right;
        margin-right: 30px;

    }

    .btn1:hover {
        color: #fff;
        background-color: #0b5da4;
        border-color: #204d74;
    }

    .btn1:active {
        color: #fff;
        background-color: #04126a;
        border-color: #204d74;
    }

    #notice2 {
        min-height: 420px;
    }

    #notice2 {
        margin-top: 30px;

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
        width: 500px;
        padding: 20px;
        float: left;
        background: #abcdef;
        margin: 10px 0 0 20px;
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

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body onload="init()">
<div class="panel panel-info">
    <div class="panel-heading">设计品牌</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade  " id="notice1">
                <div class="course_content">
                    <div class="text0">
                        在设计品牌之前重新回顾目标消费者的需求是种明智的做法。
                    </div>

                    <div class="text0">
                        品牌设计是市场营销方面最为困难的一项任务。
                    </div>
                    <div class="text1">
                        客户购买的是受益，而工厂生产的是产品组件。 您的挑战在于，要通过选择组件来满足消费者在使用方面的需要。
                    </div>
                    <div class="text1">
                        组件选择完成之后，在相应的决策界面，您可以看到该品牌的材料总成本。 然而，您需要注意的是，生产成本不仅仅是各项组件成本的简单相加。 它还包含了人工成本和工厂的固定成本。
                        您会发现，单位生产成本将随着产量的增加而急剧下降。 您的销售价格可以定在生产成本的两倍左右，或者适当少于两倍。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>在当前季度，您需要完成的最为重要的决策之一就是品牌设计。</li>
                            <li>在下季度，您的销售人员将尝试在市场上销售您的产品。 他们的成功，以及公司的成功，将取决于各个品牌的设计是否出众。</li>
                            <li>在设计新品牌的过程中，您首先需要在相应的决策界面为品牌命名。 该名称应当反映您的公司形象及产品形象。</li>
                            <li>接下来，选择该品牌的组件。 您应当选择那些能满足目标细分市场需求的组件。</li>
                        </ul>
                    </div>
                </div>
                <div class="right">
                    <div class="right_title">
                        <span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                    </div>
                    <div class="right_content">
                        <div class="text1">
                            我们建议您在测试市场的时候至少设计两款品牌。 为您的两个目标细分市场各设计一款品牌有助于您有效的判断不同消费者群体对不同品牌的反应。
                            这样做的好处在于，您不仅可以减少风险，还可以从市场测试中获得更多的反馈。
                        </div>
                        <div class="text1">
                            请注意，设计或修改一个品牌的费用为60,000。
                        </div>

                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <input type="hidden" id="quarter" name="quarter" value="${quarter }">
                <input type="hidden" id="currentQuarter" name="currentQuarter" value="${currentQuarter }">
                <input type="hidden" id="isSubmit" value="${isSubmit }">
                <div id="toubu">
                    <label class="chanpin">产品信息</label>
                    <button id="btn1" class="btn1" name="design" onclick="window.open('showProductDetail2.do?quarter=${quarter}&money=${money}', '_self'); ">设计新产品</button>
                </div>
                <hr style=" height:2px;border:none;border-top:2px dotted #0fad84;"/>

                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="success">
                        <td>产品名称</td>
                        <td>产品成本</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <form id="operate" action="" method="post">
                        <c:choose>
                            <c:when test="${not empty companyProductList}">
                                <c:forEach var="companyProducts" items="${companyProductList}">
                                    <c:forEach var="product" items="${companyProducts}">
                                        <tr id="${product.id }">
                                            <td width=24%>${product.name }</td>

                                            <td width=20%>${product.cost }</td>
                                            <input type="hidden" name="productQuarter" value="${product.quarter}">
                                            <td width=34%>
                                                <button
                                                        type="button"
                                                        onclick="del(this)"
                                                        class="btn btn-danger btn-sm-2 delete1"
                                                        id="delete${product.quarter}"
                                                        name="method"
                                                        value="delete">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                                </button>
                                                <button type="button" onclick="search(this);"
                                                        class="btn btn-success btn-sm-2" id="query" name="method"
                                                        value="query">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>查看
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5">抱歉,未找到产品信息！</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </form>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>
<script type="text/javascript">
    var currentQuarter = $("#currentQuarter").val();
    var quarter = $("#quarter").val();
    var productQuarter = $("input[name=productQuarter]");

    function init() {
        if (currentQuarter > quarter) {
            $("#btn1").attr("disabled", true);
            $(".delete1").attr("disabled", true);
        } else {
            $("#design").attr("disabled", false);
            for (var i = 0; i < productQuarter.length; i++) {
                if (productQuarter[i].value < quarter) {
                    var valueT = productQuarter[i].value;
                    $("#delete" + valueT).attr("disabled", true);
                } else {
                    var valueF = productQuarter[i].value;
                    $("#delete" + valueF).attr("disabled", false);
                }
            }
            if($("#isSubmit").val()==1){
        		$("#btn1").attr("disabled",true);
        		$(".delete1").attr("disabled", true);
        	}
        }
    }
    var search = function (btn) {
        $($(btn))
            .each(
                function () {
                    var tr = $(this).parents("td").parents("tr");
                    var method = $(btn).attr("value");
                    var productId = $(tr).attr("id");
                    $("#operate").attr(
                        "action",
                        "operateProduct.do?method=" + method
                        + "&id=" + productId + "&quarter=" + quarter);
                    $("#operate").submit();
                });

    }

    var del = function (btn) {
        $($(btn))
            .each(
                function () {
                    if (confirm("您真的确定要删除吗？")) {
                        var tr = $(this).parents("td").parents("tr");
                        var method = $(btn).attr("value");
                        var productId = $(tr).attr("id");
                        $("#operate").attr(
                            "action",
                            "operateProduct.do?quarter=" + quarter + "&method=" + method
                            + "&id=" + productId);
                        $("#operate").submit();
                    }
                }
            );

    }

</script>
<script type="text/javascript">
$(function(){
	if($("#isSubmit").val()==1){
		$("#btn1").attr("disabled",true);
		$(".delete1").attr("disabled", true);
		
	}
});

</script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>