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
<base href="<%= basePath%>">
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
        <div class="panel-heading">
        	<span>媒体偏好</span>
        	<input type="button" value="返回" onclick="javascript:history.back();">
        </div>
        <div class="panel-body">
        	<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li class="active"><a href="#notice2" data-toggle="tab">决策信息</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade in " id="notice1">
                	<div class="course_content">
                		<div class="text0"><label>了解客户认定的产品价值</label></div>

						<div class="text1">
							市场调研报告中有关消费者需求的市场数据对您将非常有帮助。 在设计品牌的时候，您可以将其与品牌提供的客户受益和品牌成本进行对照。 但是，这些数据并不会告诉您各个细分市场所需的产品组件。 消费者看重的是产品所带来的客户受益，而非产品组件（功能特性）。 所以，您需要判断各种组件所能带来的客户受益。
						</div>
						
						<div class="text2"><label>客户价值层级</label></div>
						<div class="text1">
							您的任务是弄清该如何向消费者传递价值。 运用因果关系来进行逻辑分析是一个不错的着手点。 选择每个细分市场最需要的客户受益，然后推测哪些产品组件或者服务能实现这些受益。
						</div>
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   			<ul>
                   				<li>请在相应的决策界面查看消费者需求。 表格的左方列出了所有的消费者需求项，其反映了各个细分市场的消费者将如何使用手机。 表格最上方列出了所有的细分市场。 表格中的分值衡量了各个需求项对不同细分市场消费者的重要程度。</li>
								<li>平均分值为 100。 分值超过 110 的需求项对相应的细分市场来说比较重要，而分值超过 120 则说明相当重要。 务必要重视超过 110 分的需求项。</li>
								<li>如需按照某个细分市场对所有需求项进行排序，请点击该细分市场的图标。</li>
                   			</ul>
                   		</div>
                   	</div>
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			消费者购买的不是产品组件或是功能特性，而是客户受益。
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade in active" id="notice2">
                	<table class="table table-bordered table-hover">
		                <thead>
		                    <tr class="success">
		                        <th></th>
		                        <th>实用型</th>
		                        <th>极致型</th>
		                        <th>商务型</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <c:forEach items="${MediaInfoList }" var="hyy">
		                    	<tr>
		                    		<td>${hyy.detail }</td>
		                    		<td>${hyy.practical }</td>
		                    		<td>${hyy.perfect }</td>
		                    		<td>${hyy.business }</td>
		                    	</tr>
		                    </c:forEach>
		                   
		                </tbody>
		            </table>
    				<div id="main" style="width: 600px;height:400px;"></div>
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
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
           title: {
        text: '按需求划分的细分市场',
        subtext: '数据来自网络'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['实用型','极致型','商务型']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: []
    },
    series: [
        {
            name: '实用型',
            type: 'bar',
            data: [],
            color:['#61a0a8']
        },
        {
            name: '极致型',
            type: 'bar',
            data: [],
            color:['#d48265']
        },
        {
            name: '商务型',
            type: 'bar',
            data: [],
            color:['#91c7ae']
        },
        
    ]
        };
        myChart.showLoading(); 
        var xa=[];
        var sz1=[];
        var sz2=[];
        var sz3=[];
        
        $.ajax({
			type:'post',
			url:"showMediaInfojson.do",
			success: function(data){
				
			    
				$.each(data , function(index,obj){
					xa.push(obj.detail);
					sz1.push(obj.practical);
					sz2.push(obj.perfect);
					sz3.push(obj.business);
		  		});
			
				 myChart.hideLoading();    //隐藏加载动画
                 myChart.setOption({        //加载数据图表
                	 yAxis: {
                	        type: 'category',
                	        data: xa
                	    },
                	    series: [
                	        {
                	            name: '实用型',
                	            type: 'bar',
                	            data: sz1
                	        },
                	        {
                	            name: '极致型',
                	            type: 'bar',
                	            data: sz2
                	        },
                	        {
                	            name: '商务型',
                	            type: 'bar',
                	            data: sz3
                	        }

                	        
                	    ]
                 });
                 
		  	
			}
		});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript" src="js/classIntroduction.js"></script>
</html>