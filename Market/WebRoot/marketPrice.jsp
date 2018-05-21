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
</head>
<body>
    <div class="panel panel-info">
        <div class="panel-heading">市场价格</div>
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
                	<table class="table table-bordered">
		                <thead>
		                    <tr class="success">
		                        <th></th>
		                        <th>实用型</th>
		                        <th>极致型</th>
		                        <th>商务型</th>
		                    </tr>
		                </thead>
		                <tbody id="data_body">
			                <c:forEach items="${PriceInfoList }" var="item">
			                	<tr>	
			                		<td>市场价格</td>
			                		<td>${item.practical }</td>
			                		<td>${item.perfect }</td>
			                		<td>${item.business }</td>
			                	</tr>
			                </c:forEach>
		             	</tbody>
		            </table>
		            <div id="main" style="width: 600px;height:200px;"></div>
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<!-- <script type="text/javascript">
	$(function(){
		$.ajax({
			type:'post',
			url:"showPriceInfo.do",
			success: function(data){
			    var dataHtml="";
				$.each(data , function(index,obj){
					dataHtml+="<tr>";
					dataHtml+="<td>市场价格</td>";
					dataHtml+="<td>"+obj.practical+"</td>";
					dataHtml+="<td>"+obj.perfect+"</td>";
					dataHtml+="<td>"+obj.business+"</td>";
					dataHtml+="</tr>";
					
					
		  		});
		  		$("#data_body").append(dataHtml);
			}
		});
	})
	
</script> -->


    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
           title: {
        text: '消费者愿意支付的价格',
        // subtext: '数据来自网络'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['价格(元)']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01],
     

    },
    yAxis: {
        posotion:'right',
        name:'手机类型',
        type: 'category',
        data: ['实用型','极致型','商务型',]
    },
    series: [
        {
            name: '价格(元)',
            type: 'bar',
            color:[ '#d48265','#61a0a8','#c23531','#2f4554',  '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
            data: []
        } 
    ]

        };
        myChart.showLoading(); 
        
        var sz1=[];
      
        
        
        $.ajax({
			type:'post',
			url:"showPriceInfojson.do",
			success: function(data){
				
			    
				$.each(data , function(index,obj){
					
                    
					
					sz1.push(obj.practical);
					sz1.push(obj.perfect);
					sz1.push(obj.business);

					
				
					
		  		});
			
				 myChart.hideLoading();    //隐藏加载动画
                 myChart.setOption({        //加载数据图表
                	 
                	    series: [
                	        {
                	           
                	            type: 'bar',
                	            data: sz1
                	        }

                	        
                	    ]
                 });
                 
		  	
			}
		});

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
</body>
</html>