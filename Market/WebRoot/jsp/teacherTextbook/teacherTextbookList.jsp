<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师资料</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<style type="text/css">
#label_text {
	font-size: 16px;
}

.input-group {
	width: 400px;
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<!-- <div class="row">
    	<div class="col-md-6">
	    1111
		</div>
		<div class="col-md-6">
		22222
		</div>
	</div> -->
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>教师材料</span>
			</div>
			<div class="panel-body">
				<label id="label_text">文件上传</label>&nbsp;(大小不超过5M)
				<form name="uploadForm" action="" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="teacherId" value="${teacherId }" /> <input
						type="hidden" name="competitionId" value="${competition.id }" /> <input
						id="lefile" type="file" name="textbook" style="display: none">
					<div class="input-group">
						<input type="text" id="photoCover" class="form-control"
							placeholder="未选择文件..."> <span class="input-group-btn">
							<button class="btn btn-default"
								onclick="$('input[id=lefile]').click();" type="button">浏览文件</button>
						</span>
					</div>
					<input type="button" class="btn btn-default" value="上传"
						onclick="upload()" /> <input type="reset" class="btn btn-default"
						value="取消" />
				</form>

				<hr />
				<label>当前竞赛资料</label>
				<form name="shareForm" action="" method="post">
					<input type="hidden" name="competitionId"
						value="${competition.id }" />
					<table class="table table-border">
						<thead>
							<tr>
								<th>选择</th>
								<th>文件名</th>
								<th>上传时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${textbookList }" var="item">
								<tr>
									<td><input type="checkbox" name="textbookId"
										value="${item.id }" /></td>
									<td>${item.name }</td>
									<td>${item.uploadTime }</td>
									<td><a href="${item.path }${item.name}" download>下载</a> <a
										href="javascript:void(0)"
										onclick="if(confirm('确认删除吗')) window.location.href='deleteTeacherTextbook.do?id=${item.id }&competitionId=${competition.id }&teacherId=${teacherId }'; else return false;">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<input type="button" class="btn btn-default" value="我的全部资料" onclick="showAllTextbook()"/>
					<div  id="myAllTextbook">
					<table class="table table-border">
						<thead>
							<tr>
								<th>选择</th>
								<th>文件名</th>
								<th>上传时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${textbookList2 }" var="item2">
								<tr>
									<td><input type="checkbox" name="textbookId"
										value="${item2.id }" /></td>
									<td>${item2.name }</td>
									<td>${item2.uploadTime }</td>
									<td><a href="${item2.path }${item2.name}" download>下载</a> <a
										href="javascript:void(0)"
										onclick="if(confirm('确认删除吗')) window.location.href='deleteTeacherTextbook.do?id=${item2.id }&competitionId=${competition.id }&teacherId=${teacherId }'; else return false;">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</form>

				<!-- <label>请选择以上资料进行分享！</label> <br /> 
				<input type="button" class="btn btn-default" value="分享给本次竞赛中每个成员" onclick="shareAllCompetition()" /> -->

			</div>

			<div class="panel-footer"></div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript">
	
	function upload(){
		var file=document.getElementById("lefile").files;
		
		if(file[0]!=null){
			var maxSize=5*1024*1024;
			//alert(file[0].size);
			if(maxSize<parseInt(file[0].size)){
				alert("文件大小超过5M，请重新选择文件！");
				return;
			}else{
				//alert("文件大小合适！");
				document.uploadForm.action="teacherTool/uploadTeacherTextbook.do";
				document.uploadForm.submit();
				alert("上传成功");
			}
		}else{
			alert("请选择文件！");
			return;
		}
		
	}
	//分享资料
	function shareAllCompetition(){
		//alert("nihao");
		//document.shareForm.action="teacherTool/shareTextbookToCompetition.do";
		//document.shareForm.submit();
		
		var competitionId=$("input[name='competitionId']").val();
		//alert(competitionId);
		var arr= new Array();
		var params={};
		params.competitionId=competitionId;
		params.arr=[];
		$(":checked").each(function(key,value){
			arr[key]=$(value).val();
			//alert(arr[key]);
			params.arr[key]=arr[key];
		});
		
		$.ajax({ 
	        type: "POST",  
	        url: "teacherTool/shareTextbookToCompetition.do",//注意路径  
	        data:{
	        	"competitionId":competitionId,
	        	"arr":arr
	        }, 
	        traditional: true,
	        //data:JSON.stringify(params),
	        //data:params,
	        //datatype:"json",
	        //contentType: "application/json; charset=utf-8",
	        success:function(data){  
	            if(data=='SUCCESS'){  
	                alert("分享成功！");  
	            }else{  
	                alert("分享失败！");  
	            }  
	        },  
	        error:function(data){  
	            alert(data.result);  
	        }
		});
		
	}
	$('input[id=lefile]').change(function() { 
		$('#photoCover').val($(this).val()); 
	});
	
	$(".a-upload").on("change","input[type='file']",function(){
	    var filePath=$(this).val();
	    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
	        $(".fileerrorTip").html("").hide();
	        var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
	        $(".showFileName").html(fileName);
	    }else{
	        $(".showFileName").html("");
	        $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
	        return false 
	    }
	})
	
	//显示所有资料
	function showAllTextbook(){
		$("#myAllTextbook").toggle();
	}
	$(function(){
		//$("#myAllTextbook").hide();
	});
</script>
</body>
</html>