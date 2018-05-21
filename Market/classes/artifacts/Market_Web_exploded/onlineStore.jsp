<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/physicalStore.css">
    <style type="text/css">

        .panel-body {
            height: 680px;
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

        #region {
            height: 500px;
        }

        #region .branch {
            border-radius: 5px;
            width: 50%;
            height: 63%;
            color: white;
            float: left;
            margin-left: 1%;
        }

        .test1 div {
            position: absolute;
            top: 3%;
            left: 80%;
            z-index: 999;
        }

    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">网络销售中心</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade " id="notice1">
                <div class="course_content">
                    <div class="text1">
                        对许多公司来说，网络销售中心是种高效的销售渠道。 它使得下单购买对潜在消费者来说更加简便。
                    </div>
                    <div class="text1">
                        开设网络销售中心需要耗费一个季度的时间。 因此，您可能需要在第一季度就计划开设网络销售中心。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        请选择您计划在第二季度，也就是市场测试阶段，开设的新实体销售中心。 请注意其开设成本（您将在本季度支付）和季度租金（您将在之后的每个季度支付）。
                    </div>
                </div>
                <div class="right">
                    <div class="right_title">
                        <span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                    </div>
                    <div class="right_content">
                        需要谨慎的是，您可能希望同时通过网络和实体销售中心来销售产品。 但是，同时开设两种销售渠道可能会造成财务困难。 之外，实体销售中心可能会抢夺网络销售中心的潜在客户，反之亦然。
                        也就是说，本打算从网络销售中心购买的消费者有可能会转向实体销售中心。 这将会降低销售人员的工作效率。

                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <input type="hidden" id="quarter" value="${quarter }">
                <input type="hidden" id="currentQuarter" value="${currentQuarter }">
                <input type="hidden" id="isSubmit" value="${isSubmit }">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>网络销售中心</th>
                    </tr>
                    </thead>
                    <tbody id="data_body">
                    <tr>
                        <th>实用型</th>
                        <th>极致型</th>
                        <th>商务型</th>
                        <th>开设成本</th>
                        <th>租赁成本</th>

                    </tr>
                    <tr>
                        <c:forEach var="var" items="${webList}">
                            <td>${var.practice}</td>
                            <td>${var.perfect}</td>
                            <td>${var.business}</td>
                            <td>${var.open}</td>
                            <td>${var.rent}</td>
                        </c:forEach>
                    </tr>
                    </tbody>
                </table>
                <form id="form1" role="form" method="post" action="">
                    <input type="hidden" name="companyId" value="${companyMarket.companyId}">
                    <input type="hidden" name="marketId" value="${companyMarket.marketId}">
                    <input type="hidden" name="quarter" value="${quarter }">
                    <input type="hidden" name="MoneySum" value="${MoneySum}">
                    <input type="hidden" name="open" value="${open}">
                    <input type="hidden" name="rent" value="${rent}">
                    <input type="hidden" name="timesOfSign" value="${timesOfSign}">
                    <div id="region">
                        <div id="china" class="branch">
                            <figure class="test1">
                                <div>
                                    <!-- 回显 -->
                                    <c:choose>
                                        <c:when test="${not empty companyMarket}">
                                            <c:if test="${companyMarket.isPhy==0}">
                                                开设/租赁:<input type="checkbox" class="chk_1" name="setup" value="0" checked="checked"/>
                                            </c:if>
                                            <c:if test="${companyMarket.isPhy==2}">
                                                开设/租赁:<input type="checkbox" class="chk_1" name="setup" value="1"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            开设/租赁:<input type="checkbox" class="chk_1" name="setup" value="0"/>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <img src="images/world.jpg">
                                <figcaption>

                                    <p>实用型：${webList[0].practice }</p>
                                    <p>极致型：${webList[0].perfect }</p>
                                    <p>商务型：${webList[0].business }</p>
                                    <br><br>
                                    <c:if test="${webLastQuarter==true}">
                                        <c:if test="${webCurrentQuarter==true}">
                                            <p>开设成本：${webList[0].open }元(已开设)</p>
                                            <p>租赁成本：${webList[0].rent }元(已租赁)</p>
                                            <input type="hidden" name="judgeOpenOrRent" value="rent">
                                        </c:if>
                                        <c:if test="${webCurrentQuarter==false}">
                                            <p>开设成本：${webList[0].open }元(已开设)</p>
                                            <p>租赁成本：${webList[0].rent }元(未租赁)</p>
                                            <input type="hidden" name="judgeOpenOrRent" value="rent">
                                        </c:if>
                                    </c:if>
                                    <c:if test="${webLastQuarter==false}">
                                        <c:if test="${webCurrentQuarter==true}">
                                            <p>开设成本：${webList[0].open }元(已开设)</p>
                                            <p>租赁成本：${webList[0].rent }元(已租赁)</p>
                                            <input type="hidden" name="judgeOpenOrRent" value="rent">
                                        </c:if>
                                        <c:if test="${webCurrentQuarter==false}">
                                            <p>开设成本：${webList[0].open }元(未开设)</p>
                                            <p>租赁成本：${webList[0].rent }元(未租赁)</p>
                                            <input type="hidden" name="judgeOpenOrRent" value="open">
                                        </c:if>
                                    </c:if>
                                    <br><br>
                                    <p style="margin-left: 85%">
                                        <button type="button" class="btn btn-primary" onclick="sign()">提交</button>
                                    </p>
                                </figcaption>

                            </figure>

                        </div>
                    </div>

                </form>
            </div>
        </div>


    </div>
    <div class="panel-footer"></div>
</div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".test1").children("figcaption").children('p').css({
            'transform': 'translate(0px,0px)'
        });
    });
    //提交时进行判断
    function sign() {
        var MoneySum = $("input[name=MoneySum]");
        var open = $("input[name=open]");
        var rent = $("input[name=rent]");
        var judgeOpenOrRent = $("input[name=judgeOpenOrRent]");
        var timesOfSign = $("input[name=timesOfSign]");
        var obj = $("input[type=checkbox]");
        var currentQuarter = $("#currentQuarter").val();
        var quarter = $("#quarter").val();

        var MoneySumValue = $(MoneySum).attr("value");
        var openValue = $(open).attr("value");
        var rentValue = $(rent).attr("value");
        var judgeOpenOrRentValue = $(judgeOpenOrRent).attr("value");
        var timesOfSignValue = $(timesOfSign).attr("value");

        //value等于0表示要开设或租赁网络销售中心
        var flag = $(obj).attr("value");
        if (flag == 0 && timesOfSignValue == 0 && quarter > 3) {
            if (judgeOpenOrRentValue == "open") {
                MoneySumValue = MoneySumValue - openValue;
                alert("决策之后的剩余资金:"+MoneySumValue);
                if (MoneySumValue < 0) {
                    alert("可用资金不足，请紧急贷款之后再做决策！");
                    return;
                }
            } else {
                MoneySumValue = MoneySumValue - rentValue;
                alert("决策之后的剩余资金:"+MoneySumValue);
                if (MoneySumValue < 0) {
                    alert("可用资金不足，请紧急贷款之后再做决策！");
                    return;
                }
            }
        }
        $("#form1").attr("action", "insertmarketwebinfo.do?flag=" + flag);
        $("#form1").submit();
    }

    $(".chk_1").click(function () {
        var value = $(this).attr("value");
        //alert(value);
        if (value == 0) {
            $(this).attr("value", 1);
        } else {
            //为空和为1时，点击之后设置为开设网络销售中心
            $(this).attr("value", 0);
        }
    });
    $(function () {
        var currentQuarter = $("#currentQuarter").val();
        var quarter = $("#quarter").val();
        var isSubmit = $("#isSubmit").val();
        if (currentQuarter > quarter) {
            $("button").attr("disabled", true);
        } else {
            if (isSubmit == 1) {
                $("button").attr("disabled", true);
            }
        }
    });
</script>
</body>
</html>