<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参考资料</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/teacher_index1.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<style type="text/css">
	/* body{
		text-align:center;
		background-color: #ffffff;
	}
	div{
		border:0px solid #a1a1a1;
		padding:10px 40px;
		width:90%;
		margin:auto;
		margin-top:20px;
		background:#e6e6e6;
		border-radius:15px;
	}
	table{
		border:#444444 1px solid;
		width:100%;
		margin:auto;
		margin-top: 10px;
		border-collapse: collapse;
	}
	table tr{
		border:#444444 1px solid;
		height: 100px;
	}
	table tbody tr th{
		height: 10px;
	}
	span{
		font-family: "Times New Roman",Times,serif;
		font-weight: 120;
		margin:2px 0px;
	}
	p.newfont{
	border:1px solid red;
		font-family: "Times New Roman",Times,serif;
		font-weight: 300;
		font-size:20px;
		margin:2px 0px;
	}
	li{
		margin-top: 5px;
	} */

	.panel-body{
		min-height:400px;
		
	}

</style>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>参考资料</span>
			</div>

			<div class="panel-body">
				<table style="width:80%">
					<tbody>
						<tr>
							<th style="width: 65%">教材类型及简介</th>
							<th>下载</th>
						</tr>
						<c:forEach items="${teacherReferenceList }" var="item">
						<tr>
							<td style="text-align: left" style="width: 65%">
								<input type="hidden" name="id" value="${item.id}">
								<span style="margin-left: 20px"><strong>教材类型：</strong>${item.referenceName }</span><br/>
								<span style="margin-left: 20px"><strong>简介：</strong>${item.introduction }</span>
							</td>
							
							<td>
								<ul>
								<c:forEach items="${item.teacherReferenceBookList }" var="item2">
									<span>
									<!-- 下载功能有待完善 -->
									<li><a href="${item2.path }${item2.bookName }" download>${item2.bookName }</a></li>
									</span>
								</c:forEach>
								</ul>
							</td>
						
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>

	<%-- <div>
	<p class="newfont">参考资料</p>

	<table>
		<tbody>
			<tr>
				<th style="width: 65%">教材类型及简介</th>
				<th>下载</th>
			</tr>
			<c:forEach items="${teacherReferenceList }" var="item">
			<tr>
				<td style="text-align: left" style="width: 65%">
					<input type="hidden" name="id" value="${item.id}">
					<span style="margin-left: 20px"><strong>教材类型：</strong>${item.referenceName }</span><br/>
					<span style="margin-left: 20px"><strong>简介：</strong>${item.introduction }</span>
				</td>
				
				<td>
					<ul>
					<c:forEach items="${item.teacherReferenceBookList }" var="item2">
						<span>
						<!-- 下载功能有待完善 -->
						<li><a href="${item2.path }${item2.bookName }" download>${item2.bookName }</a></li>
						</span>
					</c:forEach>
					</ul>
				</td>
			
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div> --%>

</body>
</html>