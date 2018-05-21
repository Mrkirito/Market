<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here111</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">

    <style type="text/css">
        body {
            width: 99%;
            margin: 5px;
        }

        .products {
            /* border:1px solid red; */
            height: 170px;
            width: 200px;
            float: left;
            border-radius: 5px;
            margin: 7px;
            padding: 5px;
            background: #eee;
        }

        #productContent {
            width: 660px;
            float: left;
        }

        .product_detail {
            width: 140px;
            font-weight: normal;

        }

        .product_price {

        }

        #decisionTitle {
            margin-bottom: 10px;
            margin-left: -30px;
        }

        .radio_type {
            margin-top: 6px;
        }

        .label_type {

            font-weight: normal;
        }

        .total_cost_div {
            margin-top: 10px;
            font-weight: bold;
        }

        #box_4 {
            height: 500px;
            float: left;

        }

        #box_4 img {
            width: 100%;
            height: 100%;
        }

        .btn {
            width: 90px;
            height: 31px;
            margin-left: 6px;
            background-color: #0fad84;
            color: white;
            border-radius: 2px;
            float: right;

        }

        .btn:hover {
            color: #fff;
            background-color: #0b5da4;
            border-color: #204d74;
        }

        .btn:active {
            color: #fff;
            background-color: #04126a;
            border-color: #204d74;
        }

        #decisionFoot {
            float: left;
            margin-top: 160px;
            margin-left: px;
        }
    </style>


</head>
<body onLoad="init()">
<div class="panel panel-info">
    <div class="panel-heading">设计品牌</div>
    <div class="panel-body">
        <input type="hidden" id="quarter" value="${quarter}">
        <input type="hidden" value="${currentQuarter}">
        <form id="form1" class="form-horizontal" role="form" method="post" action="">
            <div id="decisionTitle" class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="text" class="col-md-4 control-label">产品名称:</label>
                        <div class="col-md-8">
                            <input type="text" id="Brand_name" name="Brand_name" class="form-control"
                                   placeholder="请输入产品名称" value="${brandName }">
                        </div>
                        <br><br>
                        <label for="text" class="col-md-4 control-label">产品类型:</label>
                        <div class="col-md-8">
                            <select class="form-control" name="type" value="${companyProduct.type}">
                                <option value="极致型" <c:if test="${'极致型' eq companyProduct.type}">selected</c:if>>极致型
                                </option>
                                <option value="商务型" <c:if test="${'商务型' eq companyProduct.type}">selected</c:if>>商务型
                                </option>
                                <option value="实用型" <c:if test="${'实用型' eq companyProduct.type}">selected</c:if>>实用型
                                </option>
                            </select>
                        </div>

                    </div>
                </div>
                <div class="col-md-5">
                    <div class="form-group"></div>
                </div>
                <div class="col-md-2 total_cost_div">
                    <label>组成成本：
                        <span id="cost">0</span>
                    </label>
                    <input id="quarterFlag" type="hidden" name="quarterFlag" value="${quarterFlag }"/>
                    <input id="flag" type="hidden" name="flag" value="insert"/>
                    <input id="productId" type="hidden" name="productId" value="${productId }"/>
                    <input id="detail" type="hidden" name="detail" value="${detail}"/>
                    <input type="hidden" name="money" value="${money}">
                </div>
            </div>
            <div id="productContent">
                <c:forEach items="${result }" var="entry">
                    <div class="products">
                        <label>${entry.key}</label>
                        <div>
                            <input id="title" type="hidden" name="hidden" value="${entry.key}"/>
                            <c:forEach items="${entry.value}" var="item">
                                <div>
                                    <label class="product_detail">
                                        <c:choose>
                                            <c:when test="${entry.key=='其他功能'}">
                                                <input id="${item.id }" type="radio" onclick="check(this)">
                                            </c:when>
                                            <c:otherwise>
                                                <input id="${item.id }" type="radio" name="${item.title }"
                                                       onclick="check(this)">
                                            </c:otherwise>
                                        </c:choose>
                                            ${item.detail}
                                    </label>
                                    <label class="product_price" id="${item.id }s">${item.price }</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="box_4">
                <img src="images/phone.png" class="phone"/>
            </div>

            <div id="decisionFoot">
                <input type="button" id="cancel" class="btn" value="取消" style="margin-right:100px"
                       onclick="window.open('CompanyProduct.do?quarter=${quarter}&money=${money}', '_self'); "/>
                <input type="button" id="baocun" class="btn" value="保存" style="margin-right:20px" onclick="baocun(); "/>
            </div>
        </form>
    </div>
    <div class="panel-footer"></div>
</div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<script type="text/javascript">
    function init() {
        //计算是否是当季度产品，若不是，则不能修改
        var quarterFlag = $("#quarterFlag").val();
        if (quarterFlag != "true") {
            $("#baocun").hide();
            $("#cancel").attr("value", "返回");

            //不可更改
            var radios = $("input:radio");
            radios.attr("disabled", "true");
            $("#Brand_name").attr("disabled", "true");
        }

        //得出手机配置，打钩
        var details = $("#detail").val();
        if (details != "") {
            document.getElementById("flag").value = "update";
        }
        var option = details.split(",");
        for (var i = 0; i < option.length; i++) {
            var xx = document.getElementById(option[i]);
            xx.checked = "checked";
            xx.value = "1";
        }
        //得出全部选中radio，并计算出产品成本
        var productCost = 0;
        var selectOption = "";

        var checked = $("input:checked");
        for (var i = 0; i < checked.length; i++) {
            //得出所有id变成字符串
            if (i != checked.length - 1) {
                selectOption += checked[i].id + ",";
            } else {
                selectOption += checked[i].id;
            }
            //计算成本
            var label = document.getElementById(checked[i].id + "s");
            productCost += parseInt(label.innerText);
        }
        document.getElementById("cost").innerText = productCost;
    }
</script>

<script type="text/javascript">
    var quarter = $("#quarter").val();
    document.getElementById("baocun").onclick = function () {
        //获取公司资金余额
        var money = $("input[name=money]").val();
        var flag = document.getElementById("flag").value;
        //判断产品名字
        var name = $("#Brand_name").val();
        if (name == "") {
            alert("请输入产品名字！");
            return;
        }

        //判断手机配置选择
        var title = $(":input[id=title]");
        for (var i = 0; i < title.length; i++) {
            var select = $(":input[name=" + title[i].value + "]:checked").val();
            //判断除了蓝牙和其他功能，其他是否选中
            if (title[i].value != '蓝牙' && title[i].value != '其他功能') {
                if (select == null) {
                    alert("请选择" + title[i].value + "！");
                    return;
                }
            }
        }

        //得出全部选中radio，并计算出产品成本，将所有ID组合成字符串传入后台
        var productCost = 0;
        var selectOption = "";

        var checked = $("input:checked");
        for (var i = 0; i < checked.length; i++) {
            //得出所有id变成字符串
            if (i != checked.length - 1) {
                selectOption += checked[i].id + ",";
            } else {
                selectOption += checked[i].id;
            }
            //计算成本
            var label = document.getElementById(checked[i].id + "s");
            productCost += parseInt(label.innerText);
        }
        if (flag == "insert") {
        	if(quarter<=3){
        		alert("保存成功");
                $("#form1").attr(
                    "action",
                    "UpdateCompanyProduct.do?selectOption=" + selectOption
                    + "&productCost=" + productCost + "&quarter=" + quarter);
                $("#form1").submit();
        	}else{
        		if (money < 60000) {
                	
                    alert("您公司的可用资金不足，如果想设计产品，请尽快去紧急贷款！");
                } else {
                    alert("保存成功");
                    $("#form1").attr(
                        "action",
                        "UpdateCompanyProduct.do?selectOption=" + selectOption
                        + "&productCost=" + productCost + "&quarter=" + quarter);
                    $("#form1").submit();
                }
        	}
            
        } else {
            alert("保存成功");
            $("#form1").attr(
                "action",
                "UpdateCompanyProduct.do?selectOption=" + selectOption
                + "&productCost=" + productCost + "&quarter=" + quarter);
            $("#form1").submit();
        }
    }


    //点击radio时候，计算组件成本
    function check(obj) {

        //双击radio取消选中,根据value值(0或1)
        var domName = $(obj).attr('name');//获取当前单选框控件name 属性值
        var domVal = $(obj).val();

        var option = document.getElementsByName(domName);
        for (var i = 0; i < option.length; i++) {
            option[i].value = 0;
        }
        if ("1" == domVal) {
            $(obj).attr('checked', false);
        } else {
            $(obj).attr('value', 1);
        }


        //得出全部选中radio，并计算出产品成本
        var productCost = 0;
        var selectOption = "";

        var checked = $("input:checked");
        for (var i = 0; i < checked.length; i++) {
            //得出所有id变成字符串
            if (i != checked.length - 1) {
                selectOption += checked[i].id + ",";
            } else {
                selectOption += checked[i].id;
            }
            //计算成本
            var label = document.getElementById(checked[i].id + "s");
            productCost += parseInt(label.innerText);
        }
        document.getElementById("cost").innerText = productCost;

    }
</script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
</body>
</html>