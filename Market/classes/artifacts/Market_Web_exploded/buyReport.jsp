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
        #btndiv{
		/*    border:2px solid red;*/
		    margin-right: 40px;
		    text-align: right;
		    margin-bottom: 20px;
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
	 	
	}
</script>
</head>
<body onLoad="init()">
    <div class="panel panel-info">
        <div class="panel-heading">购买市场调研报告</div>
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
					<label class="chanpin">市场调研报告</label>

					<table class="table table-bordered table-hover" id="table1">
						<thead>
							<tr class="success">
								<td>区域</td>
								<td>成本</td>
								<td>购买</td>
							</tr>
						</thead>
						<tbody>
						<form id="form1" action="" method="post">
							<input type="hidden" id="quarter" value=" ${quarter }">
							<input type="hidden" id="currentQuarter" value="${currentQuarter }">
							<input type="hidden" id="isSubmit" value="${isSubmit }">
							<tr>
								<td>全球市场</td>
								<td>15000</td>
								<td>
								<c:choose>
									<c:when test="${flag==1 }">
										<input type="checkbox" name="buyReport" id="buyReport" checked="checked"/>
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="buyReport" id="buyReport"/>
									</c:otherwise>
								</c:choose>
									
								</td>
							</tr>
						</form>
						</tbody>
					</table>
					<div id="btndiv">
                      	<button type="button" class="btn btn-default" onclick="sign()">提交</button><br>
                  	</div>
						
                </div>
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script>
	function sign(){
		//获取check值
		var quarter =$("#quarter").val();
		var flag=$("#buyReport").prop("checked");
		alert("保存成功");
		$("#form1").attr("action","OperateReport.do?quarter="+quarter+"&flag="+flag);
		$("#form1").submit();
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
</body>
</html>