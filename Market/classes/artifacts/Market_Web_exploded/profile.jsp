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
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<style type="text/css">
        body {
	width: 99%;
	margin: 5px;
	}
	.panel-body{
		min-height:480px;
		padding-left:30px;
		padding-right:30px;
	}
	.text1{
		text-indent:30px;
		margin-top:20px;
	}
	.text_goal{
		text-indent:30px;
		margin-top:20px;
	}
	.img1{
		text-align:center;
	}
</style>

</head>
<body>
	 <div class="panel panel-info">
        <div class="panel-heading"><label>market简介</label></div>
        <div class="panel-body">
        	<div class="text1">
        		您将创建一家公司，以进军手机行业。 您的公司将是一个有机整体，公司经营包括从营销到生产制造，再到生产和人力资源管理等各个方面。 您拥有的财务资源有限，并且要承担全部的财务责任。
        	</div>
        	<div class="text1">
        		作为管理团队，您将提供种子资本（投资资金）以开始创业。 您可以将这笔资金用于兴建工厂、开设销售中心、以及设计品牌。 您将在第一季度向公司投入 2,000,000 资金，并在接下来的两个季
				度中分别投入 1,000,000 资金。 在第四季度，风险投资者可能会向您追加 4,000,000 的投资。这样投资总额将达到 8,000,000。
        	</div>
        	<div class="img1">
        		<img src="images/quarter1_bg_1.jpg">
        	</div>
        	<div class="text1">
        		您的管理团队将用接下来一年半（6 个季度或者决策周期）的时间来使公司步入正轨。 在这段时间内，您应当成为一家自给自足的公司，并从经营中赚取丰厚的利润。
        	</div>
        	<div class="text1">
        		平衡计分卡是衡量并比较您和竞争对手业绩表现的工具。 公司的整体业绩表现将由财务业绩、营销效率、市场份额、未来投资、人力资源管理、生产效率、资产管理、财务风险、及财富创造等方面来决定。
        	</div>
        	<div class="img1">
        		<img src="images/quarter1_bg_2.jpg">
        	</div>
        	
        	
        	<label class="text_goal">您的目标是成为市场上最具竞争力的公司。</label>
        	
        	
        
        </div>
        <div class="panel-footer">
        
        </div>
     </div>
<script src="js/jquery-2.2.4.js"></script>
</body>
</html>