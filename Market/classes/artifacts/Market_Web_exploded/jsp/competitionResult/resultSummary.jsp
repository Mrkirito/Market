<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果汇总</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>结果汇总</span>
			</div>

			<div class="panel-body">
				<input type="hidden" id="competitionId" value="${competition.id }"/>
				<input type="hidden" id="currentQuarter" value="${competition.currentQuarter }"/>
				<input type="hidden" id="quarter" value="${quarter }"/>
				<div id="noReleaseResult" style="display:none">
					<label>当前季度还没有发布竞赛结果，请先去发布结果中发布竞赛结果！</label>
				</div>
				<div id="yesReleaseResult" style="display:none">
					<table class="table table-bordered" id="tab">
						<thead>
							<tr id="companyName">
								<th>公司名称</th>
								<c:forEach items="${companyList }" var="item">
									<th>${item.name }</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr id="netIncome">
								<td>净收入</td>
								<c:forEach items="${netIncomeList }" var="item">
									<td>${item }</td>
								</c:forEach>
							</tr>
							<tr>
								<td>季末现金流</td>
								<c:forEach items="${yuEList }" var="item">
									<td>${item }</td>
								</c:forEach>
							</tr>
							<tr>
								<td>留存收益</td>
								<c:forEach items="${liucunList }" var="item">
									<td>${item }</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					
					<!-- 下面div用于画图 -->
					<div id="main" style="width: 800px;height:400px;"></div>
				</div>
				
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
	<script type="text/javascript" src="js/echarts.min.js"></script>
	<script type="text/javascript">
	$(function(){
		var quarter=$("#quarter").val();
		var comCurrentQuarter=$("#currentQuarter").val();
		if(quarter<comCurrentQuarter){
			$("#noReleaseResult").hide();
			$("#yesReleaseResult").show();
		}else if(quarter==comCurrentQuarter){
			$("#yesReleaseResult").hide();
			$("#noReleaseResult").show();
		}
	});
		
		//季度
		var quarter ="季度"+document.getElementById("quarter").value;
		//定义公司数量,列数
		var table = document.getElementById("tab");
		var column = table.rows[0].cells.length;
		//获取公司名称数组
		var companyName = new Array();
		for(var i=1;i<=column-1;i++ ){
			companyName[i-1]=document.getElementById("companyName").cells[i].innerHTML;
		}
		//定义数组存储各个公司的净收入
		var netIncomeArr= new Array();
		for(var i=1;i<=column-1;i++ ){
			netIncomeArr[i-1]=document.getElementById("netIncome").cells[i].innerHTML;
		}
		
		//series[]
		var seriesArr=new Array();
		for(var i=0;i<column-1;i++){
			seriesArr.push({
				name:companyName[i],
				type:"bar",
	            data:[netIncomeArr[i]]
			});
		}
		
		// 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('main'));
	    // 指定图表的配置项和数据
	    var option = {
	       	title: {
		        text: '净收入',
		        subtext: '按季度统计',
		        x:'center'
		        /* x:'center',
		        y:'top',
		        textAlign:'center' */
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		    	data: companyName,
		    	orient : 'vertical',
		    	x:650,
		    	y:'center'
		        //data: ['公司1','公司2','公司3','公司4','公司5']
		    },
		    grid: {
		        left: '3%',
		        right: '25%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		    	type: 'category',
		        /* data: ['新加坡','香港','莫斯科','新德里'] */
		        data: [quarter]
		    },
		    yAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    series: seriesArr
	    };
	
	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	</script>
</body>
</html>