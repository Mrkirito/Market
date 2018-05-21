<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>竞赛结果</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/score.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">

<style type="text/css">
.yincang {
	display: none;
}

.list-group div {
	
}

ul {
	list-style: none;
}

.accordion {
	/* margin-left:100px; */
	width: 260px;
	max-width: 360px;
	/* margin: 30px auto 20px; */
	background: #FFF;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

.accordion .link {
	cursor: pointer;
	margin-left: -30px;
	display: block;
	padding: 15px 2px 15px 2px;
	text-indent: 10px;
	color: #4D4D4D;
	font-size: 14px;
	font-weight: 700;
	border-bottom: 1px solid #CCC;
	position: relative;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li:last-child .link {
	border-bottom: 0;
}

.accordion li i {
	position: absolute;
	top: 16px;
	left: 12px;
	font-size: 18px;
	color: #595959;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li i.fa-caret-right {
	right: 12px;
	left: auto;
	font-size: 16px;
}

.accordion li.open .link {
	color: #b63b4d;
}

.accordion li.open i {
	color: #b63b4d;
}

.accordion li.open i.fa-caret-right {
	-webkit-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-o-transform: rotate(90deg);
	transform: rotate(90deg);
}

/**
 * Submenu
 -----------------------------*/
.submenu {
	display: none;
	background: #444359;
	font-size: 14px;
	margin-left: -30px;
	text-indent: 30px;
}

.submenu li {
	border-bottom: 1px solid #4b4a5e;
}

.submenu a {
	display: block;
	text-decoration: none;
	color: #d9d9d9;
	padding: 12px;
	margin-left: -38px;
	padding-left: 10px;
	-webkit-transition: all 0.25s ease;
	-o-transition: all 0.25s ease;
	transition: all 0.25s ease;
}

.submenu a:hover {
	background: #b63b4d;
	color: #FFF;
}
</style>
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					<a href="javascript:void(0)" id="quarterSubtract">
					<i class=" fa fa-caret-left"></i></a> &nbsp;&nbsp;<span>季度</span><span id="quarter">${competition.currentQuarter }</span>&nbsp;&nbsp;
					<a href="javascript:void(0)" id="quarterAdd"><i class="fa fa-caret-right"></i></a>
				</div>
			</div>
			<div class="col-md-7">
				<h3>${message }</h3>
			</div>
			<div class="col-md-2">
				<h3></h3>
				<input type="hidden" id="competitionId" value="${competition.id}"/>
				<input type="hidden" id="currentQuarter" value="${competition.currentQuarter }"/>
				<label for="">${competition.name }</label>
			</div>
		</div>
		<div class="row" id="row2">
			<div class="col-md-3"
				style="width: 300px; padding-left: 15px; margin-left: 0px;">
				<ul id="accordion" class="accordion">
					<li>
						<div class="link">
							竞赛结果<i class="fa fa-caret-right"></i>
						</div>
						<ul class="submenu">
							<li><a href="javascript:void(0)" onclick="resultSummary()"
								target="main">&nbsp;结果汇总</a></li>
							<li><a href="javascript:void(0)" onclick="detailedCompetitionResult()"
								target="main">&nbsp;详细竞赛结果</a></li>
							<li><a href="javascript:void(0)" onclick="usefulChart()"
								target="main">&nbsp;有用图表</a></li>
							<div id="quarterTwo">
							<!-- 
							<li><a href="javascript:void(0)"
								target="main">&nbsp;商业指导帮助</a></li>
							<li><a href="javascript:void(0)"
								target="main">&nbsp;虚假广告</a></li> 
								虚假广告暂时不用。
							-->
							</div>
							<li><a href="javascript:void(0)" onclick="releaseResult()"
								target="main">&nbsp;发布结果</a></li>
						</ul>
					</li>
				</ul>
			</div>

			<div class="col-md-9">
				<iframe id="myiframe"
					src="competitionResult/resultSummary.do?competitionId=${competition.id }&currentQuarter=${competition.currentQuarter }"
					name="main" frameBorder=0 width=850 marginheight="0" scrolling="no"></iframe>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript">
    	/* function changeModel(aNode){

    		var divNode=aNode.nextSibling.nextSibling;
    		
    		if(divNode.className=="yincang"){
    			divNode.className="xianshi";
    		}else{
    			divNode.className="yincang";
    		}
    	} */
    	$(function(){   		
    		$(".list-group").children("a").click(function(){
    			$(".xianshi,.yincang").hide();       		
        		$(this).next("div").show();
    		});
    		$(".submenu").show();
    		
    	});


        $("#myiframe").load(function () {
            var mainheight = $(this).contents().find("body").height() + 20;
            $(this).height(mainheight);
            //注意：这里设置了最小长度
        });
        $(function() {
        	var Accordion = function(el, multiple) {
        		this.el = el || {};
        		this.multiple = multiple || false;

        		// Variables privadas
        		var links = this.el.find('.link');
        		// Evento
        		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
        	}

        	Accordion.prototype.dropdown = function(e) {
        		var $el = e.data.el;
        			$this = $(this),
        			$next = $this.next();

        		$next.slideToggle();
        		$this.parent().toggleClass('open');

        		if (!e.data.multiple) {
        			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        		};
        	}	

        	var accordion = new Accordion($('#accordion'), false);
        });
    	
        $(function(){
        	var q=parseInt($("#quarter").text());
        	if(q==1){
        		$("#quarterTwo").hide();
        	}
        	
       		$("#quarterAdd").click(function(){
       			var q=parseInt($("#quarter").text());
            	var cq=$("#currentQuarter").val();//这个之后要放开
            	//var cq=5;
            	q++;
       			if(q>parseInt(cq)){
       				q=parseInt(cq);
       				$("#quarter").text(q);
       			}else{
       				$("#quarter").text(q);
       				resultSummary();
       			}
       			if(q>1){
            		$("#quarterTwo").show();
            	}
       		});
       		$("#quarterSubtract").click(function(){
       			var q=parseInt($("#quarter").text());
       			if(q==2){
       				$("#quarterTwo").hide();
       			}

            	q--;
       			if(q<1){
       				q=1;
       				$("#quarter").text(q);
       			}else{
       				$("#quarter").text(q);
       				resultSummary();
       			}
       		});
        });
        
        function resultSummary(){
        	var competitionId=$("#competitionId").val();
        	var quarter=$("#quarter").text();
        	//alert(quarter);
        	window.parent.main.location.href="competitionResult/resultSummary.do?competitionId="+competitionId+"&currentQuarter="+quarter;
        }
        
        function detailedCompetitionResult(){
        	var competitionId=$("#competitionId").val();
        	var quarter=$("#quarter").text();
        	window.parent.main.location.href="competitionResult/detailedCompetitionResult.do?competitionId="+competitionId+"&currentQuarter="+quarter;
        }
        
        function usefulChart(){
        	var competitionId=$("#competitionId").val();
        	var quarter=$("#quarter").text();
        	window.parent.main.location.href="competitionResult/usefulChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
        }
        
        function releaseResult(){
        	var competitionId=$("#competitionId").val();
        	var quarter=$("#quarter").text();
        	window.parent.main.location.href="competitionResult/goReleaseResult.do?competitionId="+competitionId+"&currentQuarter="+quarter;
        }
    </script>
</body>
</html>