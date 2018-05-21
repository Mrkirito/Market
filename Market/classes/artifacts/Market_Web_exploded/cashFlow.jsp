<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
        body{
            width: 99%;
            margin:5px;
        }
        .firstnav,.secondsubnav{
        	font-weight: bold;
        }
        .panel{
            margin: 0px;
        }
        #notice2{
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
        	width:400px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 60px;
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
	.text1_ul{
		margin-left:40px;
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
<script type="text/javascript">
	$(function(){
		var result=$("#result").val();
		var quarter=$("#quarterFlag").attr("value");
		if(result==1){
			if(quarter==1){
				$(".quarter2").hide();
				$(".quarter3").hide();
				$(".quarter4").hide();
				$(".quarter5").hide();
			}else if(quarter==2){
				$(".quarter3").hide();
				$(".quarter4").hide();
				$(".quarter5").hide();
			}else if(quarter==3){
				$(".quarter4").hide();
				$(".quarter5").hide();
			}else if(quarter==4){
				$(".quarter5").hide();
			}else if(quarter==5){
			}
		}else{
			if(quarter==1){
				$("#quarter1").text("季度1(预估)");
				$(".quarter2").hide();
				$(".quarter3").hide();
				$(".quarter4").hide();
				$(".quarter5").hide();
			}else if(quarter==2){
				$("#quarter2").text("季度2(预估)");
				$(".quarter3").hide();
				$(".quarter4").hide();
				$(".quarter5").hide();
			}else if(quarter==3){
				$("#quarter3").text("季度3(预估)");
				$(".quarter4").hide();
				$(".quarter5").hide();
			}else if(quarter==4){
				$("#quarter4").text("季度4(预估)");
				$(".quarter5").hide();
			}else if(quarter==5){
				$("#quarter5").text("季度5(预估)");

			}
		}
	})
</script>
</head>
    <body>
	<div class="panel panel-info">
	    <div class="panel-heading">现金流表</div>
	    <div class="panel-body">
	    	<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade" id="notice1">
                	<div class="course_content">
						<div class="text1">
							当前季度的现金流表显示了资产负债表中各项数据的变化，包括现金。 这些变化将作为现金流入及流出列入现金流表，并分为经营、财务、及投资等几类。
						</div>
                   	</div>
                   	<!-- <div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   		</div>
                   	</div> -->
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			要注意季末现金余额。 余额为 1 则意味着您的公司用完了所有的现金并被迫借入了紧急贷款。 紧急贷款将出现在现金流表的财务活动部分。
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade  in active" id="notice2">
                	<label id="quarterFlag" value="${quarter }"></label>
                	<input type="hidden" id="result" value="${result }"/>
                	<table class="table table-bordered table-hover table-striped">
						<thead>
						  <tr>
						    <th>项目</th>
						    <th><span id="quarter1">季度1</span></th>
						   	<th class="quarter2"><span id="quarter2">季度2</span></th>
						   	<th class="quarter3"><span id="quarter3">季度3</span></th>
						   	<th class="quarter4"><span id="quarter4">季度4</span></th>
						   	<th class="quarter5"><span id="quarter5">季度5</span></th>
						  </tr>
						</thead>
						<tbody>
						  <tr class="firstnav">
						    <td>一、经营活动产生的现金流量</td>
						    <td class="quarter1"></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:60px;">销售商品收到的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getXiaoshouGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getXiaoshouGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getXiaoshouGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getXiaoshouGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getXiaoshouGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:60px;">收取利息的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getLixiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getLixiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getLixiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
					  		<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getLixiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getLixiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:60px;">收取利息授权的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getJishuGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getJishuGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getJishuGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getJishuGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getJishuGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:60px;">收到其他与经营活动有关的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getQitaGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getQitaGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getQitaGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getQitaGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getQitaGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">经营活动现金流入小计</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getXianJinGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getXianJinGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getXianJinGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getXianJinGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getXianJinGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">邮寄返款支出的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getFankuanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getFankuanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getFankuanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getFankuanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getFankuanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">生产支出的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getShengchanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getShengchanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getShengchanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getShengchanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getShengchanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的研发费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getYanfaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getYanfaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getYanfaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getYanfaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getYanfaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的广告费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getGuanggaoPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getGuanggaoPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getGuanggaoPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getGuanggaoPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getGuanggaoPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的销售人员费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getSalerPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getSalerPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getSalerPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getSalerPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getSalerPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的实体及网络销售中心费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getSalescenterPay()+cashFlowList[0].getSalescenterWebPay()}" pattern="#,#00.0" ></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getSalescenterPay()+cashFlowList[1].getSalescenterWebPay()}" pattern="#,#00.0" ></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getSalescenterPay()+cashFlowList[2].getSalescenterWebPay()}" pattern="#,#00.0" ></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getSalescenterPay()+cashFlowList[3].getSalescenterWebPay()}" pattern="#,#00.0" ></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getSalescenterPay()+cashFlowList[4].getSalescenterWebPay()}" pattern="#,#00.0" ></fmt:formatNumber></td>
						  </tr>
						
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的市场调研费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getDiaoyanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getDiaoyanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getDiaoyanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getDiaoyanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getDiaoyanPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的货运的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getHuoyunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getHuoyunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getHuoyunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getHuoyunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getHuoyunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的库存费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getKucunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   	 	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getKucunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   	 	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getKucunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   	 	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getKucunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   	 	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getKucunPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的网络营销费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getNetmarketPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getNetmarketPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getNetmarketPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getNetmarketPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getNetmarketPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的收入税</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getTaxPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getTaxPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getTaxPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getTaxPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getTaxPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的利息费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付的技术授权费用</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getJishuPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getJishuPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getJishuPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getJishuPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getJishuPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">支付其他与经营活动有关的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getQitaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getQitaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getQitaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getQitaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getQitaPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">经营活动产生的现金流出小计</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">经营活动产生的现金流量净额</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getXianJinGet()-cashFlowList[0].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getXianJinGet()-cashFlowList[1].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getXianJinGet()-cashFlowList[2].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getXianJinGet()-cashFlowList[3].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getXianJinGet()-cashFlowList[4].getXianJinPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  
						  <tr class="firstnav">
						    <td>二、投资活动产生的现金流量</td>
						    <td></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">构建工厂固定产能支出的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   		<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   		<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
					   		<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">投资活动现金流出小计</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">投资活动产生的现金流量净额</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getGongchangPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="firstnav">
						    <td>三、筹资活动产生的现金流量</td>
						    <td></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">取得常规贷款收到的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getDaikuanNormalGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getDaikuanNormalGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getDaikuanNormalGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getDaikuanNormalGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getDaikuanNormalGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">取得紧急贷款收到的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getDaikuanEmergyGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getDaikuanEmergyGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getDaikuanEmergyGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getDaikuanEmergyGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getDaikuanEmergyGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">提取三个月定期存款收到的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getCunkuanRegularGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getCunkuanRegularGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getCunkuanRegularGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getCunkuanRegularGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getCunkuanRegularGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">筹资活动现金流入小计</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getChouZiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getChouZiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getChouZiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getChouZiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getChouZiGet()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">偿还常规贷款支出的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getDaikuanNormalPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getDaikuanNormalPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getDaikuanNormalPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getDaikuanNormalPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getDaikuanNormalPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">偿还紧急贷款支出的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getDaikuanEmergyPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getDaikuanEmergyPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getDaikuanEmergyPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getDaikuanEmergyPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getDaikuanEmergyPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:60px;">三个月定期存款支出的现金</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getCunkuanRegularPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getCunkuanRegularPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getCunkuanRegularPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getCunkuanRegularPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getCunkuanRegularPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">投资活动现金流出小计</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondsubnav">
						    <td style="padding-left:35px;">筹资活动产生的现金流量净额</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getChouZiGet()-cashFlowList[0].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getChouZiGet()-cashFlowList[1].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getChouZiGet()-cashFlowList[2].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getChouZiGet()-cashFlowList[3].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getChouZiGet()-cashFlowList[4].getChouZiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="firstnav">
						    <td>四、现金及现金等级物净增加额</td>
						    <td></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">+： 季初现金及现金等价物余额</td>
						    <td><fmt:formatNumber value="${cashFlowList[0].getYuE()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${cashFlowList[1].getYuE()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${cashFlowList[2].getYuE()}" pattern="#,#00.0"></fmt:formatNumber></td>  
						    <td class="quarter4"><fmt:formatNumber value="${cashFlowList[3].getYuE()}" pattern="#,#00.0"></fmt:formatNumber></td>  
						    <td class="quarter5"><fmt:formatNumber value="${cashFlowList[4].getYuE()}" pattern="#,#00.0"></fmt:formatNumber></td>  
						    </tr>
						  </tbody>
						</table>
                </div>
            </div>
		
	    </div>
	    <div class="panel-footer"></div>
	</div>
	<script type="text/javascript" src="js/classIntroduction.js"></script>
</body>
</html>