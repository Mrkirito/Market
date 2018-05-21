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
<link rel="stylesheet" type="text/css" href="css/Brand_ds.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function(){
		document.getElementById("7").style.display = "block";
	    document.getElementById("8").style.display = "block";
	    document.getElementById("10").style.display = "none";
	    document.getElementById("11").style.display = "none";
	    document.getElementById("17").style.display = "block";
	    document.getElementById("20").style.display = "none";
	    document.getElementById("27").style.display = "block";
	    document.getElementById("28").style.display = "block";
	    document.getElementById("30").style.display = "none";
	    document.getElementById("31").style.display = "none";
	    
	})
	
	
	
</script>

 


    <style type="text/css">
        body {
            width: 99%;
            margin: 4px;
        }
    </style>
    
 
</head>
<body>
    <div class="panel panel-default">
        <div class="panel-heading">设计品牌</div>
        <form  id="form1" class="form-horizontal" role="form" method="post" action="UpdateCompanyProduct.do">
	        <div class="panel-body">      
                <div id="decisionTitle">
                    <div id="title_left">
                        <label class="label_name">品牌名称:</label>
                        <input type="text" id="Brand_name" name="name" />
                    </div>
                    <div id="title_middle">
	
                    <label class="label_name">品牌类型:</label>
	
                    <span class="radio1">
	                    <input type="radio" name="type" id="jingji" checked="checked" value="经济型"/>
	                    <img src="images/economy.png" class="pic_type" />
	                    <label>经济型</label>
                    </span>
                    <span class="radio1">
                        <input type="radio" name="type" id="shangwu" value="商务型" />
                        <img src="images/commerce.png" class="pic_type" />
                        <label>商务型</label>
                    </span>
                    <span class="radio1">
                        <input type="radio" name="type" id="jizhi" value="极致型"/>
                        <img src="images/extreme.png" class="pic_type" />
                        <label>极致型</label>
                    </span>
	
               	</div>
                   
                </div>
                <div>
                    <div id="box_1">
                        <div id="groupCell1">
                            <label class="label_class">必备</label>
                            <c:forEach items="${bibei }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="bibei" checked="checked"  value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>

                        </div>
                        <div id="groupCell2">
                            <label class="label_class">运营商</label>
                            <c:forEach items="${yunying }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="yunying" value="${item.id }" />
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell3">
                            <label class="label_class">蓝牙</label>
                            <c:forEach items="${lanya }" var="item">
                            <div id="${item.id }" >
                               <input type="radio" name="lanya" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell4">
                            <label class="label_class">屏幕尺寸</label>
                            <c:forEach items="${pingmu }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="pingmu" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                    </div>
                    <div id="box_2">
                        <div id="groupCell1">
                            <label class="label_class"> 触控方式</label>
                            <c:forEach items="${chukong }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="chukong" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell1">
                            <label class="label_class"> 处理器速度</label>
                            <c:forEach items="${cpu }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="cpu" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell2">
                            <label class="label_class"> 机身容量</label>
                            <c:forEach items="${jishen }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="jishen" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell1">
                            <label class="label_class"> 拍照像素</label>
                            <c:forEach items="${paizhao }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="paizhao" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>


                    </div>
                    <div id="box_3">
                        <div id="groupCell1">
                            <label class="label_class"> 机身特性</label>
                            <c:forEach items="${texing }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="texing" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell2">
                            <label class="label_class"> 电池容量</label>
                            <c:forEach items="${dianchi }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="dianchi" value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>
                        <div id="groupCell3">
                            <label class="label_class"> 其他功能</label>
                            <c:forEach items="${qita }" var="item">
                            <div id="${item.id }">
                               <input type="radio" name="qita"  value="${item.id }"/>
                                <label class="info">${item.detail }</label>
                                <label class="price">${item.price }</label>
                            </div>  
                            </c:forEach>
                        </div>

                    </div>
                    <div id="box_4">
                        <img src="images/phone.png" class="phone" />
                    </div>
           		</div>
	                <div id="decisionFoot">
	                    <input type="button" name="cancel" class="btn" value="取消" style="margin-right:100px" onclick="window.open('CompanyProduct.do', '_self'); " />
	                    <input type="button" id="baocun" name="baocun" class="btn" value="保存" style="margin-right:20px" onclick="baocun(); "/>
	                </div>
	            </div> 
        </form>
           
    </div>

     
</body>

<script type="text/javascript">

		
		
		document.getElementById("baocun").onclick = function () {
			
			var cost=1;
			var name=$("#Brand_name").val();
			
			var bibei=$(":input[name=bibei]").val();
			var yunying=$(":input[name='yunying']:checked").val();
			var lanya=$(":input[name='lanya']:checked").val();
			var pingmu=$(":input[name='pingmu']:checked").val();
			var chukong=$(":input[name='chukong']:checked").val();
			var cpu=$(":input[name='cpu']:checked").val();
			var jishen=$(":input[name='jishen']:checked").val();
			var paizhao=$(":input[name='paizhao']:checked").val();
			var texing=$(":input[name='texing']:checked").val();
			var dianchi=$(":input[name='dianchi']:checked").val();
			var qita=$(":input[name='qita']:checked").val();
			
			if(name==""){
				alert("请输入产品名字！");
				return;
			}
			
			if(yunying==null||pingmu==null||chukong==null||cpu==null||jishen==null||paizhao==null||texing==null||dianchi==null){
				alert("请选择手机配置！")
				return;
			}
			
			
			
			
			document.getElementById("form1").submit(); 
		}
		
		
		document.getElementById("jingji").onclick = function () {
		 	
			document.getElementById("7").style.display = "block";
		    document.getElementById("8").style.display = "block";
		    document.getElementById("10").style.display = "none";
		    document.getElementById("11").style.display = "none";
		    document.getElementById("17").style.display = "block";
		    document.getElementById("20").style.display = "none";
		    document.getElementById("27").style.display = "block";
		    document.getElementById("28").style.display = "block";
		    document.getElementById("30").style.display = "none";
		    document.getElementById("31").style.display = "none";
		}
		document.getElementById("shangwu").onclick = function () {
		 	
			document.getElementById("7").style.display = "none";
		    document.getElementById("8").style.display = "block";
		    document.getElementById("10").style.display = "block";
		    document.getElementById("11").style.display = "none";
		    document.getElementById("17").style.display = "none";
		    document.getElementById("20").style.display = "block";
		    document.getElementById("27").style.display = "none";
		    document.getElementById("28").style.display = "block";
		    document.getElementById("30").style.display = "block";
		    document.getElementById("31").style.display = "none";
		}
	        
        
        document.getElementById("jizhi").onclick = function () {	
        	document.getElementById("7").style.display = "none";
            document.getElementById("8").style.display = "none";
            document.getElementById("10").style.display = "block";
            document.getElementById("11").style.display = "block";
            document.getElementById("17").style.display = "none";
            document.getElementById("20").style.display = "block";
            document.getElementById("27").style.display = "none";
            document.getElementById("28").style.display = "none";
            document.getElementById("30").style.display = "block";
            document.getElementById("31").style.display = "block";
        }
        
        

    </script> 
</html>