<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
        body{
            width: 99%;
            margin:5px;
        }
        .firstnav,.thirdnav{
        	font-weight: bold;
        }
        .panel{
            margin: 0px;
        }
        .a{
          text-align:center;
        letter-spacing:155px;}
        b.{text-align:center;}
        
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

</head>

<body>
       <div class="panel panel-info">
	    <div class="panel-heading">资产负债表
	        <!-- <h3 class="panel-title">资产负债表</h3> -->
	    </div>
	    <div class="panel-body" >
	    	<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade" id="notice1">
                	<div class="course_content">
						<div class="text1">
							当前季度的资产负债表提供了公司资产、负债、及股东权益等方面的财务信息。						</div>
                   	</div>
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			请确保现金账户的余额不低于 300,000，以便应对可能的突发问题。
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade  in active" id="notice2">
                <label id="quarterFlag" value="${quarter }"></label>
                <input type="hidden" id="result" value="${result }"/>
                	<table class="table table-bordered table-hover table-striped">
						<!--悬停表格布局-->
						<thead>
						  <tr>
						    <th >项目</th>
						    <th ><span id="quarter1">季度1</span></th>
						    <th class="quarter2"><span id="quarter2">季度2</span></th>
						    <th class="quarter3"><span id="quarter3">季度3</span></th>
						    <th class="quarter4"><span id="quarter4">季度4</span></th>
						    <th class="quarter5"><span id="quarter5">季度5</span></th>
						  </tr>
						</thead>
						<tbody>
						
						  <tr class="firstnav">
						    <td>流动资产：</td>
						    <td></td>
						    <td  class="quarter2"></td>
						    <td  class="quarter3"></td>
						    <td  class="quarter4"></td>
						    <td  class="quarter5"></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">货币资金</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getHuobi()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getHuobi()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getHuobi()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getHuobi()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getHuobi()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">三个月定期存款</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getCunkuan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getCunkuan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getCunkuan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getCunkuan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getCunkuan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">存货</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getCunhuo()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getCunhuo()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getCunhuo()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getCunhuo()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getCunhuo()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">流动资产合计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getLiuDongZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getLiuDongZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getLiuDongZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getLiuDongZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getLiuDongZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="firstnav">
						    <td>非流动资产：</td>
						    <td></td>
						    <td  class="quarter2"></td>
						    <td  class="quarter3"></td>
						    <td  class="quarter4"></td>
						    <td  class="quarter5"></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">固定资产</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td  class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">非流动资产合计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						     <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						     <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						     <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getZichan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">资产总计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getTotalZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getTotalZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getTotalZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getTotalZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getTotalZiChan()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="firstnav">
						    <td>流动负债：</td>
						    <td></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  
						  <tr class="secondnav">
						    <td style="padding-left:30px;">紧急贷款</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getDaikuanEmergency()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getDaikuanEmergency()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getDaikuanEmergency()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getDaikuanEmergency()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getDaikuanEmergency()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">应付利息</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getLixiPay()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">流动负债合计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getLiuDongFuzhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getLiuDongFuzhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getLiuDongFuzhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getLiuDongFuzhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getLiuDongFuzhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="firstnav">
						    <td>非流动负债：</td>
						    <td></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">常规银行贷款</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">非流动负债合计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getDaikuanNormal()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">负债合总计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getTotalFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getTotalFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getTotalFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getTotalFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getTotalFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="firstnav">
						    <td>所有者权益：</td>
						    <td></td>
						    <td class="quarter2"></td>
						    <td class="quarter3"></td>
						    <td class="quarter4"></td>
						    <td class="quarter5"></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">股本</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getGuben()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getGuben()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getGuben()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getGuben()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getGuben()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">资本公积</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getCapitalReserve()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getCapitalReserve()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getCapitalReserve()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getCapitalReserve()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getCapitalReserve()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="secondnav">
						    <td style="padding-left:30px;">留存收益</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getLiucun()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getLiucun()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getLiucun()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getLiucun()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getLiucun()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">所有者权益合计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getOwner()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getOwner()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getOwner()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getOwner()}" pattern="#,#00.0"></fmt:formatNumber></td>
						    <td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getOwner()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tr>
						  <tr class="thirdnav">
						    <td style="padding-left:120px;">负债和所有者权益总计</td>
						    <td><fmt:formatNumber value="${assertSheetList[0].getOwnerAndFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter2"><fmt:formatNumber value="${assertSheetList[1].getOwnerAndFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter3"><fmt:formatNumber value="${assertSheetList[2].getOwnerAndFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter4"><fmt:formatNumber value="${assertSheetList[3].getOwnerAndFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						   	<td class="quarter5"><fmt:formatNumber value="${assertSheetList[4].getOwnerAndFuZhai()}" pattern="#,#00.0"></fmt:formatNumber></td>
						  </tbody>
						</table>
                </div>
            </div>
	    </div>
	    <div class="panel-footer"></div>
	</div>
</body>
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
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>