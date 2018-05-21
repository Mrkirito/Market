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
    <script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <style type="text/css">
        body {
            width: 99%;
            margin: 5px;
        }

        .shichang {
            font-weight: bold;
            font-size: large;
            padding-left: 10px;
            padding-top: 20px;
        }

        .t_tfoot .t_tr {
            font-weight: bold;
        }

        .t_tfoot .t_tr td {
            border-left: none;
            border-right: none;
        }

        .panel {
            margin: 0px;
        }

        #notice1 {
            min-height: 420px;
        }

        #btndiv {
            /*    border:2px solid red;*/
            margin-right: 40px;
            text-align: right;
            margin-bottom: 20px;
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

        .wrap {
            width: 1000px;
            overflow-x: auto
        }

        .tab-content {
            margin-top: 10px;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            //判断季度
            var $quarter = $("#quarter").val();
            if ($quarter == 2) {
                $(".quarter3").hide();
            }

            //动态设置table长度，是否添加滚动条
            var th1 = $("#tr1 th");
            var length1 = th1.length * 100;
            $("#table1").css("min-width", length1);
            if (length1 > 1000) {
                $("#wrap1").attr("class", "wrap");
            }

            var th2 = $("#tr2 th");
            var length2 = th2.length * 100;
            $("#table2").css("min-width", length2);
            if (length2 > 1000) {
                $("#wrap2").attr("class", "wrap");
            }

        })
    </script>
</head>
<body onLoad="init()">
<div class="panel panel-info">
    <div class="panel-heading">主流媒体投放</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li><a href="#notice2" data-toggle="tab" class="quarter3">上季度决策</a></li>
            <li class="active"><a href="#notice3" data-toggle="tab">决策界面</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in " id="notice1">
                <div class="course_content">
                    <div class="text0"><label>了解客户认定的产品价值</label></div>

                    <div class="text1">
                        市场调研报告中有关消费者需求的市场数据对您将非常有帮助。 在设计品牌的时候，您可以将其与品牌提供的客户受益和品牌成本进行对照。 但是，这些数据并不会告诉您各个细分市场所需的产品组件。
                        消费者看重的是产品所带来的客户受益，而非产品组件（功能特性）。 所以，您需要判断各种组件所能带来的客户受益。
                    </div>

                    <div class="text2"><label>客户价值层级</label></div>
                    <div class="text1">
                        您的任务是弄清该如何向消费者传递价值。 运用因果关系来进行逻辑分析是一个不错的着手点。 选择每个细分市场最需要的客户受益，然后推测哪些产品组件或者服务能实现这些受益。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>请在相应的决策界面查看消费者需求。 表格的左方列出了所有的消费者需求项，其反映了各个细分市场的消费者将如何使用手机。 表格最上方列出了所有的细分市场。
                                表格中的分值衡量了各个需求项对不同细分市场消费者的重要程度。
                            </li>
                            <li>平均分值为 100。 分值超过 110 的需求项对相应的细分市场来说比较重要，而分值超过 120 则说明相当重要。 务必要重视超过 110 分的需求项。</li>
                            <li>如需按照某个细分市场对所有需求项进行排序，请点击该细分市场的图标。</li>
                        </ul>
                    </div>
                </div>
                <div class="right">
                    <div class="right_title">
                        <span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                    </div>
                    <div class="right_content">
                        消费者购买的不是产品组件或是功能特性，而是客户受益。
                    </div>
                </div>
            </div>
            <input type="hidden" name="quarter" id="quarter" value="${quarter }"/>
            <input type="hidden" id="currentQuarter" value="${currentQuarter }">
            <input type="hidden" id="isSubmit" value="${isSubmit }">

            <div class="tab-pane fade in active" id="notice3">
                <!--   <div>
                          <label class="shichang">全球市场</label>

                  </div> -->
                <div id="wrap1">
                    <table id="table1" class="table table-bordered table-hover t_tfoot">
                        <thead>
                        <tr id="tr1" class="success">
                            <th style="width:200px">媒体</th>
                            <th style="width:100px">成本</th>
                            <c:forEach items="${companyProducts }" var="product">
                                <th style="width:100px"><span name="ProductNames">${product.name}</span></th>
                            </c:forEach>
                        </tr>
                        </thead>

                        <c:forEach items="${companyMedias }" var="aa">
                            <input type="hidden" name="StringId" id="${aa.mediaId }s${aa.productId}"
                                   value="${aa.num }"/>
                        </c:forEach>
                        <tbody>
                        <form id="form1" method="post" action="UpdateCompanyMedia.do?quarter=${quarter }">
                            <input type="hidden" name="money" value="${money}">
                            <input type="hidden" name="mediaMoney" value="${mediaMoney}">
                            <c:forEach items="${mediaInfos }" var="aa">
                                <input type="hidden" name="mediaId" value="${aa.id}"/>
                            </c:forEach>
                            <c:forEach items="${mediaInfos }" var="media">
                                <tr>
                                    <td>${media.detail }</td>
                                    <td>${media.cost }</td>
                                    <c:forEach items="${companyProducts }" var="product">
                                        <td>
                                        	<input
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												class="form-control" id="${media.id }n${product.id }" type="text" name="${product.id }"  value="0" onchange="aa()" 
												maxlength="4" style="width:100%;text-align:center">
                                        	
                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </form>
                        </tbody>
                        <tfoot>
                        <tr class="t_tr">
                            <td>区域广告费用:</td>
                            <td></td>
                            <c:forEach items="${companyProducts }" var="product">
                                <td>
                                    0
                                </td>
                            </c:forEach>
                        </tr>
                        <tr class="t_tr">
                            <td>广告总费用:</td>
                            <td colspan="${len }" style="text-align:center;">
                                0
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
                <div id="btndiv">
                    <button type="button" class="btn btn-default" onclick="sign()">提交</button>
                    <br>
                </div>
            </div>

            <!--上季度决策界面  -->
            <div class="tab-pane fade in" id="notice2">
                <label class="shichang">全球市场</label>
                <div id="wrap2">
                    <table id="table2" class="table table-bordered table-hover t_tfoot">
                        <thead>
                        <tr id="tr2">
                            <th style="width:200px">媒体</th>
                            <th style="width:100px">成本</th>
                            <c:forEach items="${lastCompanyProducts }" var="product">
                                <th><span name="lastProductNames">${product.name}</span></th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <c:forEach items="${lastCompanyMedias }" var="aa">
                            <input type="hidden" name="StringId1" id="${aa.mediaId }sc${aa.productId}"
                                   value="${aa.num }"/>
                        </c:forEach>
                        <tbody>
                        <c:forEach items="${mediaInfos }" var="media">
                            <tr>
                                <td>${media.detail }</td>
                                <td>${media.cost }</td>
                                <c:forEach items="${lastCompanyProducts }" var="product">
                                    <td>
                                        <span id="${media.id }nc${product.id }"></span>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>

<script type="text/javascript" src="js/bootstrap.js"></script>
<script>
    function init() {
        //判断显示前11个字
        var $ProductNames = $("span[name='ProductNames']");
        for (var i = 0; i < $ProductNames.length; i++) {
            var text = $ProductNames[i].innerText;
            if (text.length > 11) {
                text = text.substring(0, 11);
                $ProductNames[i].innerText = text;
            }
        }
        var $lastProductNames = $("span[name='lastProductNames']");
        for (var i = 0; i < $lastProductNames.length; i++) {
            var text = $lastProductNames[i].innerText;
            if (text.length > 11) {
                text = text.substring(0, 11);
                $lastProductNames[i].innerText = text;
            }
        }
        //赋值上季度给span
        var hiddens1 = $(":input[name=StringId1]");
        for (var i = 0; i < hiddens1.length; i++) {
            var num = hiddens1[i].value;
            var id = hiddens1[i].id;//id:**sc**
            var aa = id.split("sc");
            var spanId = "";
            spanId = aa[0] + "nc" + aa[1];
            var span = document.getElementById(spanId);
            if (span != null) {
                span.innerText = num;
            }
        }

        //赋值给text
        var hiddens = $(":input[name=StringId]");
        for (var i = 0; i < hiddens.length; i++) {
            var num = hiddens[i].value;
            var id = hiddens[i].id;//id:**s**
            var aa = id.split("s");
            var textId = "";
            textId = aa[0] + "n" + aa[1];
            var text = document.getElementById(textId);
            if (text != null) {
                text.value = num;
            }
        }
        cal();
    }
    function cal() {
        /*计算文本框输入  */

        //获取表格行数
        var table = document.getElementById("table1");
        var rows = table.rows.length;//总行数

        //区域广告费用所在行数
        var partRow = rows - 3;
        var totalRow = partRow + 1;

        //获取text个数，即几个区域广告费用
        var input = table.rows[1].getElementsByTagName("input");//即列数
        var partCost = new Array();
        var totalCost = 0;
        //全部初始化为0
        for (var i = 0; i < input.length; i++) {
            partCost[i] = 0;
        }

        for (var i = 1; i < rows - 2; i++) {
            //获取成本值，并进行计算
            var col = table.rows[i].getElementsByTagName("input");//获取每行的text
            var v = $("#table1 tr:gt(0):eq(" + (i - 1) + ") td:eq(1)").text();//成本值
            for (var j = 0; j < col.length; j++) {
                var num = col[j].value;//媒体个数
                var perCost = parseInt(num) * parseInt(v);//单个媒体的成本
                partCost[j] += perCost;//区域成本
                //赋值给区域成本
                var td = 2 + j;
                $("#table1 tr:gt(0):eq(" + partRow + ") td:eq(" + td + ")").html(partCost[j]);
            }
        }
        //计算总成本
        for (var i = 0; i < input.length; i++) {
            totalCost += partCost[i];
        }
        $("#table1 tr:gt(0):eq(" + totalRow + ") td:eq(1)").html(totalCost);
    }
</script>
<script>
    function aa() {

        //判断整数
        var text = $(":input[type=text]");
        for (var i = 0; i < text.length; i++) {
            if ((text[i].value % 1) != 0) {
                alert("请输入整数！");
                return;
            }
        }
        cal();
    }

    function sign() {
    	var quarter = $("#quarter").val();
    	
    	var text = $(":input[type=text]");
    	for(var i=0;i<text.length;i++){
    		if(text[i].value==""){
    			text[i].value=0
    		}
    	}
        cal();
        
        //获取表格行数
        var table = document.getElementById("table1");
        var rows = table.rows.length;//总行数
        //区域广告费用所在行数
        var partRow = rows - 3;
        var totalRow = partRow + 1;
        var designMoney = $("#table1 tr:gt(0):eq(" + totalRow + ") td:eq(1)").html();
        var money = $("input[name=money]").val();
        var mediaMoney = $("input[name=mediaMoney]").val();
        money = money - mediaMoney ;
       	if(quarter<=3){
       		alert("提交成功");
            $("#form1").submit();
       	}else{
       		if (money < designMoney) {
                alert("您公司当前资金余额不足，如果想进行广告媒体投放，请尽快前去紧急贷款！");
            } else {
                alert("提交成功");
                $("#form1").submit(); 
            }
       	}
        
    }


    $(function () {
        var currentQuarter = $("#currentQuarter").val();
        var quarter = $("#quarter").val();
        var isSubmit=$("#isSubmit").val();
        if(currentQuarter>quarter){
            $("button").attr("disabled",true);
        }else{
            if(isSubmit==1){
                $("button").attr("disabled",true);
            }
        }
    });
</script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>