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

    .btn {
        margin-right: 220px;
    }

</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body onload="init()">
<div class="panel panel-info">
    <div class="panel-heading">设计广告</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade  " id="notice1">
                <div class="course_content">
                    <div class="text1">
                        您的目标是创建一个广告方案，以向消费者推出您的品牌，并说服其购买您的品牌。 您之前的努力是否有效？
                    </div>

                    <div class="text1">
                        如果对广告不满意，您可以修改它。 您也可以设计新的广告。 如果您修改或重新设计了品牌，请记得为其设计广告。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>在相关的决策界面，选择修改现存的广告设计或者创建新设计。</li>
                            <li>然后，查看客户受益列表，并选择能说服目标消费者进行购买的客户受益。 避免选择太多的客户受益，这样反而会使得广告太过杂乱而降低广告效果。
                                一则广告中最多只能有9条客户收益（广告语），您必须通过反复试验来确定最合适的客户受益数量。
                            </li>
                            <li>从高到低对选择的客户受益进行排序。 该步骤告诉广告代理商，哪些客户受益需要放在广告最显眼的位置（排序第一的受益），而哪些又该使用小字体。</li>
                            <li>最后，指明该广告所对应的品牌。</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <input type="hidden" id="quarter" value="${quarter }">
                <input type="hidden" id="currentQuarter" value="${currentQuarter }">
                <input type="hidden" id="isSubmit" value="${isSubmit }">

                <div id="toubu">
                    <label class="chanpin">广告信息</label>
                    <input type="button" id="designbtn"
                           name="design" value="设计广告" class="btn1"
                           onclick="window.open('designAdvertisement.do?quarter='+${quarter},'_self');"/>
                </div>
                <hr style=" height:2px;border:none;border-top:2px dotted #0fad84;"/>
                <form id="operate" action="" method="post">
                    <input class="hidden" type="text" name="quarter" value="${quarter }">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr class="success">
                            <td>广告名称</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty companyAds}">
                                <c:forEach var="ad" items="${companyAds}">
                                    <tr id="${ad.productId }">
                                        <input type="hidden" name="adQuarter" value="${ad.quarter}">
                                        <td>${ad.adName }</td>
                                        <td>
                                            <button type="button" onclick="del(this)"
                                                    class="btn delBut btn-danger btn-sm-2 delete${ad.quarter}"
                                                    name="method"
                                                    value="delete">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                            </button>
                                            <button type="button" onclick="search(this)"
                                                    class="btn btn-success btn-sm-2" name="method" value="query">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>查看
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="2">抱歉,未找到产品信息！</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>
<script type="text/javascript">
    var search = function (btn) {
        $($(btn))
            .each(
                function () {
                    var tr = $(this).parents("td").parents("tr");
                    var productId = $(tr).attr("id");
                    var adName = $(tr).children("td").eq(0).text();
                    $("#operate").attr(
                        "action",
                        "showAdDetail.do?productId=" + productId + "&adName=" + adName);
                    $("#operate").submit();
                });

    }

    var del = function (btn) {
        $($(btn))
            .each(
                function () {
                    if (confirm("您真的确定要删除吗？")) {
                        var tr = $(this).parents("td").parents("tr");
                        var productId = $(tr).attr("id");
                        var adName = $(tr).children("td").eq(0).text();
                        $("#operate").attr(
                            "action",
                            "adDesign.do?productId=" + productId + "&adName=" + adName);
                        $("#operate").submit();
                    }
                }
            );

    }
    var currentQuarter = $("#currentQuarter").val();
    var quarter = $("#quarter").val();
    var adQuarter = $("input[name=adQuarter]");
    var isSubmit=$("#isSubmit").val();
    function init() {
        if (currentQuarter > quarter) {
            $(".delBut").attr("disabled", true);
            $("#designbtn").attr("disabled", true);
        } else {
            $(".delBut").attr("disabled", false);
            for (var i = 0; i < adQuarter.length; i++) {
                if (adQuarter[i].value < currentQuarter) {
                    var valueT = adQuarter[i].value;
                    $(".delete" + valueT).attr("disabled", true);
                } else {
                    var valueF = adQuarter[i].value;
                    $(".delete" + valueF).attr("disabled", false);
                }
            }
            if(isSubmit==1){
                $(".delBut").attr("disabled",true);
                $("#designbtn").attr("disabled", true);
            }

        }
    }

</script>
</html>