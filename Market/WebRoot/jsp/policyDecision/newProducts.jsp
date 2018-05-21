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
<title>dicision locks</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

</script>

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>新产品</span>
            </div>
            
            <div class="panel-body">
				<div>
					<label>品牌设计</label>
				<table id="productInfoTb" class="table table-bordered">
					<thead>
						<tr>
							<!-- <th>品牌名称</th> -->
							<c:forEach items="${companyProductList }" var="product">
							<th>${product.name }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr id="productInfoTr">
						<c:forEach items="${productsList }" var="product">
							<td>
							<ul>
								<c:forEach items="${product }" var="productInfo">
									<li>${productInfo.title }:${productInfo.detail }</li>
								</c:forEach>
							</ul>
							</td>
						</c:forEach>
						</tr>
						
					</tbody>
				</table>
				</div>
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>