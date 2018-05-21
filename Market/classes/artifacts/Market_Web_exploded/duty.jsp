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
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script src="js/jquery-2.2.4.js"></script>
<style type="text/css">
        body {
	width: 99%;
	margin: 5px;
}
.table>thead>tr>td, .table>tbody>tr>td {
	display: table-cell;
	text-align: center;
	padding-top:20px;
	border-top: 0px;
}
		table tr{
			vertical-align: middle;
			
		}
		
		table td{
            float:left;
           	height:70px;
           
        }
       	table td:nth-of-type(1){
            width:20%;
            
        }
        table td:nth-of-type(2){
            width:20%;
        }
		table td:nth-of-type(3){
            width:30%;
        }
		
		table td:nth-of-type(4){
            width:30%;
        }
		
		img{
            height:45px;
            
           	
        }
        
        
        
        #btndiv{
		/*    border:2px solid red;*/
		    margin-right: 40px;
		    text-align: right;
		    margin-bottom: 20px;
		}

        .panel{
            margin: 0px;
        }
        .panel-body{
            /*background:#2c343c;*/
        }
        .table{
          
        }
        .panel-heading{
            /*font-weight: bold;
            letter-spacing: 2px;*/
        }
        .split{
            clear: left;
        }

        #header{

        }
        .item{
            margin-top: 40px;
        }
        .form-horizontal ul{
            list-style: none;
            padding: 0px;
            margin:0px;
        }
        .form-horizontal div:nth-child(4){
            /*border:2px solid red;*/
            margin-top:20px;
        }
        .item-header{
            font-size: 18px;
            /*border-bottom: 1px solid black;*/
            margin-top: 0px;
        }
        .item:nth-of-type{
            border:2px solid red;
        }
        /* .item img{
            width: 80px;
        } */
         /* ul li{
            float:left;
            width: 20%;
            margin-left:40px;
        }
        ul li:nth-of-type(1){
            width:15%;
        }
        ul li:nth-of-type(2){
            width:15%;
        }  */

        select{
            width: 60%;
        }
        #hr{
            border:1px solid #ccc;
            height: 1px;
            width:84%;
            margin-top:10px;
            margin-left: 30px;
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
  function init(){
	   	var table=document.getElementById("table1");
		var rows=table.rows.length;//行数
		
	 	//动态赋值select ID
		for(var i=1;i<rows;i++){
		   	var select = table.rows[i].getElementsByTagName("select");//获取td里所有select
		   	select[0].name=table.rows[i].id+"_main";
		   	select[1].name=table.rows[i].id+"_minor";
		}
		
		//动态赋值
		for(var i=1;i<rows;i++){
		   	var input = table.rows[i].getElementsByTagName("input");
			var id=input[0].id;
		   
		}

		
   }
   

</script>
</head>
<body onLoad="init()">
    <div class="panel panel-info">
        <div class="panel-heading">职位分配</div>
        <div class="panel-body">
        	<ul class="nav nav-tabs">
                <li class="active"><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade in active" id="notice1">
                	<div class="course_content">
						<div class="text1">
							任何一家新公司在开始营运之前都需要先组织好其管理团队。
						</div>
						<div class="text1">
							在本系统中，领导才能和团队合作都是获取成功所必不可少的。 要完成的任务不仅量大，而且跨越了诸多功能领域，因此单独一个人可能无法胜任。 此外，由于消费者、竞争对手、以及您的决策等方面的原因，商业世界在不断地变化。 没有人能够独自掌握所有的市场动态，更不用说以一人之力规划一套有效的商业策略并执行一系列复杂交错的商业决策。 您必须妥善分配任务和职责，要做到有组织、有重点，同时也要兼顾到各方面的灵活性。
						</div>
						
						
						<div class="text1">
							谁将负责营销、分销、及财务？ 哪些工作必须完成？ 谁将是总体领导者？
						</div>
						<div class="text1">
							注意： 每位成员都将参与公司利润分析。
						</div>
						
						<div class="text1">
							主要职责的选择很重要，因为您的竞赛表现评估，至少在部分上，将由该职责所对应的业绩指标决定。 您的教员将收到一份有关所有业绩指标的报告，其中包括您的主要职责所对应的业绩指标。
						</div>
						
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   			相应的决策界面列出了公司所有的管理团队成员。
                   			<ol>
                   				<li>每位成员都必须选择其主要商业职责。</li>
                   				<li>同时还要选择次要商业职责以作为补充。</li>
                   				<li>选出一个成员来领导团队，并作为公司的总裁。 该成员还可以兼任某一管理副职。</li>
                   			</ol>
                   			
                   		</div>
                   	</div>
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			在制定决策的过程中，请考虑职责涉及的以下方面：
                   			<ul>
                   				<li>总体领导：设立目标、组织分配工作、管理日程及会议、监控总体业绩（平衡计分卡）、并带领团队成为业内领先的公司。</li>
                   				<li>市场营销：通过品牌设计、定价、广告设计、及媒体投放来满足消费者需求。</li>
                   				<li>销售管理：销售渠道（地点、开设实体及网络销售中心的时间）和销售团队管理（人数、部署、培训）。</li>
                   				<li>生产制造：规划产能、安排生产。</li>
                   				<li>财务会计：财务业绩、现金流管理、利润分析、及资本结构。</li>
                   				<li>市场调研：分析市场数据。</li>
                   				<li>人力资源：薪酬组合、及工人生产率。</li>
                   			</ul>
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade" id="notice2">
                	<input type="hidden" id="quarter" value="${quarter }">
                	<input type="hidden" id="currentQuarter" value="${currentQuarter }">
					<input type="hidden" id="isSubmit" value="${isSubmit }">
                	<table class="table  table-hover" id="table1">
						<thead>
							<tr class="success">
								<td>管理者姓名</td>
								<td>照片</td>
								<td>主要职位</td>
								<td>次要职位</td>
							</tr>
						</thead>
						<tbody>
							 <form  id="form1" class="form-horizontal" role="form" method="post" action="updateCompanyDuty.do?quarter=${quarter }" >
								 <c:forEach var="item" items="${memberList}">
								 	 <input id="myHidden" type="hidden" name="myHidden" value="${item.id }"/>
								 		<!--tr的id代表成员ID  -->
										<tr id="${item.id}">
											<input id="a${item.id}" type="hidden" name="my1Hidden" />
											<td>${item.name }</td>
											<td ><div style="margin-bottom:20px;"><img src="images/bgm1.jpg"></div></td>
											<c:if test="${not empty companyDuty }">
											<td>
												 <c:forEach items="${companyDuty }" var="duty1">
													<c:if test="${duty1.memberId==item.id }">
														<select id="${item.id}" class="selectPicker form-control showtick"  >
						                        			<c:forEach items="${dutyList}" var="duty">
											            	<option <c:if test="${duty1.mainId==duty.id }"> selected="true"</c:if>value="${duty.id }">${duty.position}</option>
											        		</c:forEach>
						                        		</select>
													</c:if>
												</c:forEach> 
											</td>
											<td>
												<c:forEach items="${companyDuty }" var="duty1">
													<c:if test="${duty1.memberId==item.id }">
														<select id="${item.id}" class="selectPicker form-control showtick"  >
						                        			<c:forEach items="${dutyList}" var="duty">
											            	<option <c:if test="${duty1.minorId==duty.id }"> selected="true"</c:if>value="${duty.id }">${duty.position}</option>
											        		</c:forEach>
						                        		</select>
													</c:if>
												</c:forEach> 
											</td>
											</c:if>
											<c:if test="${empty companyDuty }">
											<td>
												<select id="${item.id}" class="selectPicker form-control showtick"  >
				                        			<c:forEach items="${dutyList}" var="duty">
									            	<option value="${duty.id }">${duty.position}</option>
									        		</c:forEach>
				                        		</select>
			                        		</td>
			                        		<td>
												<select id="${item.id}" class="selectPicker form-control showtick"  >
				                        			<c:forEach items="${dutyList}" var="duty">
									            	<option value="${duty.id }">${duty.position}</option>
									        		</c:forEach>
				                        		</select>
			                        		</td>
											</c:if>
										</tr>
								</c:forEach>
							</form>	
						</tbody>
					</table>
					<div id="btndiv">
	                	<button type="button" class="btn btn-default" onclick="sign()">提交</button><br>
	                </div>
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript">
	function sign() {
		alert("提交成功");
		document.getElementById("form1").submit();       
	}
    $(function () {
        var currentQuarter = $("#currentQuarter").val();
        var quarter = $("#quarter").val();
        var isSubmit=$("#isSubmit").val();
        if(currentQuarter>quarter){
            $("button").attr("disabled",true);
        }else{
            if(isSubmit == 1){
                $("button").attr("disabled",true);
            }
        }
    });
</script> 
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>