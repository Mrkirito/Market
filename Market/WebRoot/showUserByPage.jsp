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
<link rel="stylesheet" href="css/pagination.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<style type="text/css">
	a:hover{
		text-decoration:none;
	}
</style>
</head>
<body>
	<form id="searchForm" action="selectUsersByPage" method="post">
		<div>
			用户名：<input type="text" name="username">
		</div>
		<div>
			性别：<input type="text" name="gender">
		</div>
		<button type="submit">查询</button>
	</form>
	<div>
		<table class="table table-bordered table-border">
			<thead>
				<tr>
					<td>id</td>
					<td>用户名</td>
					<td>密码</td>
					<td>住址</td>
					<td>性别</td>
					<td>邮箱</td>
					<td>生日</td>	
				</tr>
			</thead>
			<tbody>		
				<c:forEach items="${users}" var="item">
					<tr>
						<td>
							<c:out value="${item.id}"></c:out>
						</td>
						<td>
							<c:out value="${item.username}"></c:out>
						</td>
						<td>
							<c:out value="${item.password}"></c:out>
						</td>
						<td>
							<c:out value="${item.address}"></c:out>
						</td>
						<td>
							<c:out value="${item.gender}"></c:out>
						</td>
						<td>
							<c:out value="${item.email}"></c:out>
						</td>
						<td>
							<c:out value="${item.birthday}"></c:out>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	 <%-- <div align="center">  
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第  
            ${page.pageNow} 页</font> <a href="selectUserByPage?pageNow=1">首页</a>  
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">  
                <a href="selectUserByPage?pageNow=${page.pageNow - 1}">上一页</a>  
            </c:when>  
            <c:when test="${page.pageNow - 1 <= 0}">  
                <a href="selectUserByPage?pageNow=1">上一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="selectUserByPage?pageNow=${page.pageNow}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">  
                <a href="selectUserByPage?pageNow=${page.pageNow + 1}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">  
                <a href="selectUserByPage?pageNow=${page.totalPageCount}">下一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="selectUserByPage?pageNow=${page.pageNow}">尾页</a>  
            </c:when>  
            <c:otherwise>  
                <a href="selectUserByPage?pageNow=${page.totalPageCount}">尾页</a>  
            </c:otherwise>  
        </c:choose>  
    </div>   --%>
    <!-- 分页功能 End -->  
    
    <div id="div3" style="text-align:center"></div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	 function handlePaginationClick(new_page_index, pagination_container) {
		 $("#searchForm").attr("action","selectUsersByPage?pageNow="+ (new_page_index+1));
		 $("#searchForm").submit();
	    return false;
	}
  	$(function() {
		var a=${page.totalCount};
		var b=${page.pageSize};
		var c=${page.pageNow};
     $("#div3").pagination( a,{
        items_per_page: b, // 每页显示多少条记录
        current_page: c-1, // 当前显示第几页数据
        num_display_entries: 4, // 分页显示的条目数
        next_text:"下一页",
        prev_text:"上一页",
        num_edge_entries: 1 ,// 连接分页主体，显示的条目数
        load_first_page: false,
        callback:handlePaginationClick
	});
});
	</script>
</body>
</html>