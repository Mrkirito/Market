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
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">
<link rel="stylesheet" type="text/css" href="css/course.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<style>
	body{
		width:99%;
		margin:5px;
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
        	width:400px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 100px;
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
	.left_content li{
		margin-top:5px;
	}
	.tab-content{
		margin-top:10px;
	}
</style>

</head>
<body onLoad="init()">
	
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>目标与策略</span>
            </div>
            <div class="panel-body">
            	<ul class="nav nav-tabs">
	                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
	                <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
	            </ul>
	            
	            <div class="tab-content">
                	<div class="tab-pane fade" id="notice1">
                		<div class="course_content">
							<div class="text1">
								您的公司必须确定其目标和目标市场的优先级别，以及公司的宗旨和策略方向。
							</div>	
							<div class="text1">
								在经营初期，最重要的决策就是确定公司目标及策略方向。 您在这条道路上走得越远，就越难改变策略方向。 您所有的投资，无论是技术还是资金，都将被投入到越来越窄、越来越集中的一套决策当中。
							</div>
							<div class="text1">
								每个公司都必须明确其主要及次要目标细分市场。 随着公司经营经验及资源的增长，您可以选择多个目标细分市场。 在任何细分市场上的额外销售都将提高公司的收入，从而提升您的业绩表现。 请注意，不管公司的目标细分市场有几个，您都需要特别注意公司的主要及次要目标细分市场。 这是因为平衡计分卡的某些得分只基于公司在主要及次要目标细分市场上的业绩表现。 例如，不论公司在多少个细分市场上进行销售，市场份额与营销效率这两项得分只基于主要和次要目标细分市场。
							</div>
	                   	</div>
	                   	<div class="left">
	                   		<div class="left_title">
	                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
	                   		</div>
	                   		<div class="left_content">
	                   			<ol>
	                   				<li>分析市场信息</li>
	                   				<li>确定可用的市场机会</li>
	                   				<li>
	                   					完成以下方面的决策
	                   					<ul>
	                   						<li><label>公司目标：</label>您需要决定什么对公司最重要：公司整体的盈利能力（由留存收益来衡量）、管理团队的投资收益、或是销售量。</li>
	                   						<li><label>目标市场：</label>公司产品定位的目标细分市场，以及能有助您进入这些市场的营销活动。 请选择两个细分市场以进行经营。</li>
	                   						<li><label>宗旨：</label>公司的经营宗旨/ 目标。请注意，消费者、投资者、甚至竞争对手，都可能会阅读您的公司宗旨。</li>
	                   						<li><label>策略方向：</label>公司的未来方向。 您未来的目标有哪些：市场规模、区域市场、竞争地位、或者竞争能力？</li>
	                   					</ul>
	                   				</li>
	                   			</ol>
	                   		</div>
	                   	</div>
	                   	<div class="right">
	                   		<div class="right_title">
	                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
	                   		</div>
	                   		<div class="right_content">
	                   			<div class="text1">
	                   				在第四季度之前，应该把您的目标和策略方向看做是尝试性的。 在市场测试阶段之后，您可以根据获得的市场经验来自由修改这些初始决定。
	                   			</div>
	                   			<div class="text1">
									在第四季度结束之前，您应当有明确的策略方向。 若仍没有，您很可能将无法赶上领先的竞争对手。
	                   			</div>
	                   		</div>
	                   	</div>
                	</div>
                	<div class="tab-pane fade in active" id="notice2">
                		<input type="hidden" id="quarter" value="${quarter }">
                		<input type="hidden" id="currentQuarter" value="${currentQuarter }">
                		<form  id="form1" class="form-horizontal" role="form" method="post" action="updateCompanyStrategy.do?quarter=${quarter }" >
		                    <div class="form-group">
		                        <label class="col-md-2 col-xs-2 control-label">主要产品类型：</label>
		                        <div class="dropdown col-md-3 col-xs-3">
		                            <select id="mainProduct"  name="mainPro" class="selectpicker show-tick form-control">
		                                <option <c:if test="${companyStrategy.mainPro==null}">  selected="true" </c:if>>--请选择--</option>
		                                <option <c:if test="${companyStrategy.mainPro=='极致型'}">  selected="true" </c:if>>极致型</option>
		                                <option <c:if test="${companyStrategy.mainPro=='商务型'}">  selected="true" </c:if>>商务型</option>
		                                <option <c:if test="${companyStrategy.mainPro=='实用型'}">  selected="true" </c:if>>实用型</option>
		                            </select>
		                        </div> 
		                        
		                        <label class="col-md-2 col-xs-2 control-label">次要产品类型：</label>
		                        <div class="dropdown col-md-3 col-xs-2">
		                            <select id="minorProduct"  name="minorPro" class="selectpicker show-tick form-control">
		                                <option <c:if test="${companyStrategy.minorPro==null}">  selected="true" </c:if>>--请选择--</option>
		                                <option <c:if test="${companyStrategy.minorPro=='极致型'}">  selected="true" </c:if>>极致型</option>
		                                <option <c:if test="${companyStrategy.minorPro=='商务型'}">  selected="true" </c:if>>商务型</option>
		                                <option <c:if test="${companyStrategy.minorPro=='实用型'}">  selected="true" </c:if>>实用型</option>
		                            </select>
		                        </div>
		                        <div class="col-md-5 col-xs-5"></div>  
		                    </div>
		                    <div class="form-group">
		                        <label for="password" class="col-md-2 col-xs-2 control-label">服务理念：</label>
		                        <div class="col-md-5 col-xs-5">
		                            <input type="text" class="form-control" placeholder="Service Concept" name="service" value="${companyStrategy.service}">
		                        </div>
		                        
		                    </div>
		                  	
		                    <input id="myHidden" type="hidden" name="myHidden" value="${companyStrategy.strategyId }"/>
		                    
		                    <c:forEach items="${result }" var="entry">
		                    	<div class="form-group btn-grounp">
			                        <label for="password" class="col-md-2 col-xs-2 control-label">${entry.key}：</label>
			                        <div class="col-md-5 col-xs-5">
				                   		<c:forEach items="${entry.value}" var="item">
				                            <div>     
				                                <input type="checkbox" name="ability" value="${item.id }" >  <label>${item.detail}</label>
				                            </div>
				                  		</c:forEach>
			                        </div> 
			                    </div>
		                	</c:forEach>
		                    <div id="btndiv">
		                        <button type="button" class="btn btn-default" onclick="sign()">提交</button><br>
		                    </div>
		                    <br><br>
		                </form>
                	</div>
                </div>
            </div>
        	<div class="panel-footer"></div>    
    	</div>

</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
function init(){
	//用隐藏域记录选中项id
	var strategy=$("#myHidden").val();
	//转换成字符串
	var strategyArr= strategy.split(",");
	//匹配id,进行check
	var id="";
	for(var i=0;i<strategyArr.length-1;i++){
		id=strategyArr[i];
		var obj=document.getElementsByName("ability");
		for(var j=0;j<obj.length;j++){
			if(obj[j].value==id){
				obj[j].checked="true";
			}	
		}  	
	}	
}
</script>
<script type="text/javascript">
	function sign() {		
		var mainProduct=$("#mainProduct").val();	
		var minorProduct=$("#minorProduct").val();
		
		if(mainProduct=="--请选择--"){
			alert("请选择主要产品类型！");
			history.go(0);
			return;
			
		}
		else if(minorProduct=="--请选择--"){
			alert("请选择次要产品类型！");
			history.go(0);
			return;
		}
		else if(mainProduct==minorProduct){
			alert("主要产品类型和次要产品类型不能一致！");
			history.go(0);
			return;
		}
		
		alert("提交成功！");	
		document.getElementById("form1").submit();      
	}
	$(function(){
		var currentQuarter=$("#currentQuarter").val();
		var quarter=$("#quarter").val();
		if(currentQuarter>quarter){
			$("button").attr("disabled",true);
		}else{
			$("button").attr("disabled",false);
		}
	});
</script> 
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>