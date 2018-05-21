<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <style type="text/css">
        body{
            width: 99%;
            margin:5px;
        }
        .table>thead>tr>td, .table>tbody>tr>td {
	display: table-cell;
	text-align: center;
	vertical-align: middle;
}
	.chanpin{
        font-weight:bold;
        font-size:large;
        padding-left:10px;
        padding-top:20px;
    }
        .panel{
            margin: 0px;
        }
        #notice1{
        	min-height:420px;
        }
        
        .course_content{
        	border:1px solid #0ff;
        	margin-top:10px;
        	padding:20px;
        	min-height:220px;
        	font-size:14px;
        	letter-spacing:1px;
        }
        .left{
        	/* border:1px solid blue; */
        	width:450px;
        	padding:20px;
        	float:left;
        	background:#eee;
        	margin:10px 0 0 2px;
        	min-height:220px;
        }
        .left_title span i{
        	color:#009;
        	font-size:18px;
        	margin-right:6px;
        }
        
        
        .right{
        	/* border:1px solid blue; */
        	width:600px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 0px;
        	min-height:120px;
        }
        
        .right_title span i{
        	color:red;
        	font-size:22px;
        	margin-right:6px;
        }
        .text0{
        	margin-left:30px;
        	margin-top:10px;
        }
        .text1{
		text-indent:30px;
		margin-top:10px;
	}
	.text2{
        	margin-left:30px;
        	margin-top:25px;
        }
	.left_content li{
		margin-top:5px;
	}
	.tab-content{
		margin-top:10px;
	}
    </style>
<script>
	function init(){
	   	var table=document.getElementById("table1");
		var rows=table.rows.length;//行数
		
		//计算销货成本
		for(var i=1;i<rows;i++){
			var hidden = table.rows[i].getElementsByTagName("input");
			var cost=hidden[0].value;
			//赋值
			
			document.getElementById(table.rows[i].id+"n100").innerText=Math.ceil(cost*330/100/Math.log(5));
			document.getElementById(table.rows[i].id+"n250").innerText=Math.ceil(2*cost*330/250/Math.log(5));
			document.getElementById(table.rows[i].id+"n500").innerText=Math.ceil(cost*2.25/Math.log(500/65));
			document.getElementById(table.rows[i].id+"n1000").innerText=Math.ceil(2.4*cost/Math.log(1000/65));
			document.getElementById(table.rows[i].id+"n5000").innerText=Math.ceil(3*cost/Math.log(5000/65));
			
		}
		
	 	
	}
</script>
</head>
<body onLoad="init()">
    <div class="panel panel-info">
        <div class="panel-heading">生产成本</div>
        <div class="panel-body">
        	<ul class="nav nav-tabs">
                <li class="active"><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade in active " id="notice1">
                	<div class="course_content">
                		<div class="text0"><label>考虑市场可承受的价格。</label></div>

						<div class="text1">
							在设计品牌的同时必须考虑到市场愿意支付的价格。 您将不断地在品牌价格和性能之间权衡。 高性能的功能特性通常比低性能的功能特性具有更高的成本。 您的目标市场是否愿意为额外的性能支付更高的价格？
						</div>
						
						<div class="text2"><label>市场机会分析将有助您判断在一定的产品性能下，目标市场对价格的敏感程度。</label></div>
						<div class="text1">
						相应决策界面的表格中列出了各个细分市场愿意为理想产品支付的价格。 您可以将这些价格看做相应目标市场原意支付的最高价格。 如果您的售价高于这一水平，则愿意购买您的产品的消费者将减少。 消费者通常都青睐低价产品。
						</div>
                   	</div>
                   	
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			<ul>
                   				<li>
                   					可以先为每个细分市场设定一个品牌成本上限，然后在设计品牌的时候将成本控制在这个上限之下。 一个常用的估算方法是将品牌的组件成本乘上两到三倍，得到的就是品牌的预计销售价格。 （当您在市场营销目录下设计品牌的时候，系统会自动计算品牌的材料成本）
                   				</li>
                   				<li>
									假如预计销售价格高出了您预期的市场承受能力，就需要缩减品牌的功能特性来使降低其成本。 但有时候，在成本上限允许的范围内，您或许还能够为品牌增加一些额外的功能特性。
                   				</li>
                   			</ul>
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade" id="notice2">
					<label class="chanpin">销货成本(COGS)</label> 
                	
					<table class="table table-bordered table-hover" id="table1">
						<thead>
							<tr class="success">
								<td>产品信息</td>
								<td>100件数</td>
								<td>250件数</td>
								<td>500件数</td>
								<td>1000件数</td>
								<td>5000件数</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
									<c:when test="${not empty companyProducts}">
										<c:forEach items="${companyProducts}" var="product" >
											<tr id="${product.id }">
												<input id="${product.id }cost" type="hidden" value="${ product.cost}"/>
												<td>${product.name}</td>
												<td><label id="${product.id}n100"></label></td>
												<td><label id="${product.id}n250"></label></td>
												<td><label id="${product.id}n500"></label></td>
												<td><label id="${product.id}n1000"></label></td>
												<td><label id="${product.id}n5000"></label></td>
												
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="6">抱歉,未找到产品信息！</td>
										</tr>
									</c:otherwise>
								</c:choose>
						</tbody>
					</table>
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/classIntroduction.js"></script>

</body>
</html>