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
<title>公司销售量和脱销量</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>公司销售量及脱销量</span>
				<input type="button" value="返回" onclick="javascript:history.back();">
			</div>

			<div class="panel-body">
				<label>公司销售量及脱销量</label>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>销售量</th>
							<th>脱销量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${companyMSList }" var="item">
							<tr>
								<td>${item.key }</td>
								<td>${item.value.practicalSale+item.value.perfectSale+item.value.businessSale }</td>
								<td>${item.value.practicalStockoun+item.value.perfectStockoun+item.value.businessStockoun }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		var col=$("table").find("tr").children("th").length;
		$("#operatingActiveTd").attr("colspan",col);
		$("#investActivityTd").attr("colspan",col);
		$("#financeActivityTd").attr("colspan",col);
		
		/* for(var i=2;i<=col;i++){
			var sum=0;
			var node=$("#asset tr td:nth-child(i)");
			if($("#asset tr td:nth-child(i)").length>0){
				$("#asset tr td:nth-child(i)").each(function(){
					alert($(this).text());
					sum+=parseInt($(this).html());
					$("#assetSum").append("<td><strong>").append(sum).append("</strong></td>");
				});
			}else{
				sum+=0;
			}
			
		} */
	});
</script>
</body>
</html>