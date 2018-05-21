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
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/physicalStore.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <script type="text/javascript" src="js/modernizr.js"></script>
    <script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <style>
    	.panel-body{
    		height:680px;
    	}
    	
     #notice2{
    	margin-top:30px;
    	
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
        	width:500px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 20px;
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
	function init() {
		var selectedMoney = 0;
	}
	</script>

</head>
<body onload="init()">
	
    <input type="hidden" id="selectedMoney">
    <div class="panel panel-info">
        <div class="panel-heading">选择销售区域</div>
        <div class="panel-body">
        	<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade " id="notice1">
                	<div class="course_content">
						<div class="text1">
							有关地域市场扩张的决策在所有的决策中最为直观。 每个城市都有不同的销售潜力，或者说潜在消费者数量。 除此之外，它们的细分市场结构也不相同。 也就是说，某个细分市场在某个城市的总市场中所占的比例可能大于该细分市场在其它城市的比例。 这些市场结构方面的差异将影响各个品牌在不同城市的销售量。
						</div>
						<div class="text1">
							您的市场机会分析应当给出了各个细分市场和区域市场大致的销售潜力。 您可以据此判断您的目标消费者（目标细分市场的消费者）在哪个市场最为集中。 有了这些信息之后，您将能够决定进入各个区域市场的先后顺序。
						</div>
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   			请选择您计划在第二季度，也就是市场测试阶段，开设的新实体销售中心。 请注意其开设成本（您将在本季度支付）和季度租金（您将在之后的每个季度支付）。
                   		</div>
                   	</div>
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			在选择计划开设的实体销售中心之前，您还需要考虑除市场规模之外的其它方面：
             				<ol>
             					<li><label>营运成本：</label>市场潜力越大，营运成本越高。</li>
             					<li><label>竞争：</label>市场的先入者通常会获得一定的消费者忠诚度，但不利的是，由于消费者的产品意识尚在形成之中，他们的早期销售量可能会较低。</li>
             				</ol>
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade in active" id="notice2">
                <input type="hidden" id="quarter" value="${quarter }"/>
				<input type="hidden" id="currentQuarter" value="${currentQuarter }"/>
				<input type="hidden" id="isSubmit" value="${isSubmit }"/>
                	<table class="table table-bordered">
		                <thead>
		                    <tr>
		                    	<th>市场区域</th>
		                       	<th colspan="3" style="padding-left:400px;">市场需求量</th>
		                        
		                    </tr>
		                </thead>
		               <tbody id="data_body">
		               <tr>
		                        <th></th>
		                        <th>实用型</th>
		                        <th>极致型</th>
		                        <th>商务型</th>
		                        
		                    </tr>
		                   <c:forEach items="${marketInfoList }" var="item">
			                	<tr>	
			                		<td>${item.key.city}</td>
			                		<td>${item.key.practical }</td>
			                		<td>${item.key.perfect }</td>
			                		<td>${item.key.business }</td>
			                	</tr>
			                </c:forEach>    
		               </tbody>
		            </table>
		            <form id="form1" class="form-horizontal" role="form" method="post" action="insertmarketinfo.do?quarter=${quarter }" >
		                <div id="region">
		                	<c:forEach items="${marketInfoList}" var="item">
		              			<div id="china" class="branch">
			                        <figure class="test1">
			                            <div>
			                            <c:if test="${item.value==1}"> <input type="checkbox" class="chk_1" name="market_id" checked="checked" value="${item.key.id }"/></c:if>
			                            <c:if test="${item.value==0}">   <input type="checkbox" class="chk_1" name="market_id"  value="${item.key.id }"/> </c:if> 
			                                <label for="checkbox_d1"></label>
			                            </div>
			                             <img src="${item.key.img }">
			                            <figcaption>
			                                <h3>${item.key.city }</h3>
			                                <p>实用型：${item.key.practical }</p>
			                                <p>极致型：${item.key.perfect }</p>
			                                <p>商务型：${item.key.business }</p> 
			                                <br><br>
			                                <p>开设成本:${item.key.open }元(${item.key.opened})</p>
			                                <p>租赁成本:${item.key.rent }元(${item.key.rented})</p>
			                            </figcaption>
			                        </figure>
		                    	</div>
		    				</c:forEach>
		                    <div class="split"></div>
		                    <br>
		                	<button type="button" class="btn btn-primary" onclick="sign()" style="float:right;margin-right:15px;">提交</button>
		                </div>
		            </form>
                
                </div>
            </div>
        
	        
        </div>
        <div class="panel-footer"></div>
    </div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>   
<script type="text/javascript">
	$(function(){
	
		$(".test1").children("figcaption").children('p').css({
		     'transform': 'translate(0px,0px)'
		 });
	});
	
	//提交时进行判断
	function sign() {
		var selectedMoney = ${MoneySum};
		var unadjustedMoneySum = ${unadjustedMoneySum};
		var quarter = ${quarter};
		//alert(selectedMoney);
		if(document.getElementsByName("market_id")[0].checked)
			selectedMoney = selectedMoney - ${one};
		if(document.getElementsByName("market_id")[1].checked)
			selectedMoney = selectedMoney - ${two};
		if(document.getElementsByName("market_id")[2].checked)
			selectedMoney = selectedMoney - ${three};
		if(document.getElementsByName("market_id")[3].checked)
			selectedMoney = selectedMoney - ${four};
		//alert(selectedMoney);
		if(selectedMoney<0 && selectedMoney<unadjustedMoneySum && quarter>3){
			alert("资金不足，你需要紧急贷款之后才能做出此决策");
			return;
		}
		var obj=$(":input[type=checkbox]");
		var flag=false;
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				flag=true;
			}
		}
		
		if(flag==false){
			alert("请选择选项！");
			return;
		}
		alert("提交成功");
		document.getElementById("form1").submit();	
		
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