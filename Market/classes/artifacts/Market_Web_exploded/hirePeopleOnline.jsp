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
    .data{
    	text-align: center;
    }

</style>
<script type="text/javascript">

</script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">雇佣销售人员</div>
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
				<input type="hidden" id="quarter" value="${quarter }">
				<input type="hidden" id="currentQuarter" value="${currentQuarter }">
                <input type="hidden" id="isSubmit" value="${isSubmit }">

            	
            	<c:if test="${flag=='false' }">
                	<div>您还没有建立或租赁网络销售中心</div>
                </c:if>
                <c:if test="${flag=='true' }">
                	<form action="" method="post">
	                    <table class="table table-bordered">
	                        <thead>
	                        <tr>
	                            <td>销售人员</td>
	                            <td>售后人员</td>
	                            <td>员工工资</td>
	                            <td>操作</td>
	                        </tr>
	                        </thead>
	                        <tbody>
	                        	<tr style="display:none;">
	                        		<td><input type="hidden" name="quarter" value="${quarter }"></td>
	                        	</tr>
	                        	<tr>
	                        		<td>
	                        		<input maxlength="4" class="form-control data" type="text" name="saleman"
	                                           value="${hirePeopleOnline.saleman }"
	                                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
	                                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
	                        		</td>
	                        		<td>
	                        		<input maxlength="4" class="form-control data" type="text" name="afterSale"
	                                           value="${hirePeopleOnline.afterSale }"
	                                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
	                                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
	                        		</td>
	                        		<td><input class="form-control data" type="text" disabled="disabled" value="${salesSalary.salaryTotal}"></td>
	                        		<td><button class="btn btn-info" type="button" class="btn-info" onclick="sign()">提交</button></td>
	                        		
	                        	</tr>
	                        </tbody>
	                        
	                      
	                      
	                        <%-- <c:forEach items="${hirePeopleOnlineVoList }" var="item" varStatus="status">
	                            <input type="hidden" name="hirePeopleOnlineList[${status.index }].id"
	                                   value="${item.hirePeopleOnline.id }">
	                            <input type="hidden" value="${quarter}" name="quarter">
	                            <tr>
	                                <td>${item.city }</td>
	
	                                <td><input type="text" name="hirePeopleOnlineList[${status.index }].saleman"
	                                           value="${item.hirePeopleOnline.saleman }"
	                                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
	                                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
	                                </td>
	                                <td><input type="text" name="hirePeopleOnlineList[${status.index }].afterSale"
	                                           value="${item.hirePeopleOnline.afterSale }"
	                                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
	                                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
	                                </td>
	                                <td><input type="text" disabled="disabled" value="${salesSalary.salaryTotal}"></td>
	                                <td>
	                                    <input type="button" class="btn-info" onclick="sign()" value="提交">
	                                </td>
	                            </tr>
	                        </c:forEach> --%>
	                    </table>
	                </form>
                </c:if>
            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
<script src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
    var sign = function () {
        if (confirm("您真的确定要更改吗？")) {
            alert("正在更新数据...");
            $("form").attr("action", "updateHirePeopleOnline.do");
            $("form").submit();
        }
    }
    $(function(){
		var currentQuarter=$("#currentQuarter").val();
		var quarter=$("#quarter").val();
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
</body>
</html>