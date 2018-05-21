$(function(){
	handlePaginationClickOperate();
	window.setTimeout(function(){
		var pageNowOperate = $("#pageNowOperate").val();
		var totalPageChat = $("#totalPageOperate").val();
		$("#pageOperate").pagination( totalPageChat,{
	        items_per_page: 6, // 每页显示多少条记录
	        current_page: pageNowOperate-1, // 当前显示第几页数据
	        num_display_entries: 4, // 分页显示的条目数,如果总页数是100/7=14页，这个属性表示上一页右边可以显示4个页码
	        next_text:"下一页",
	        prev_text:"上一页",
	        num_edge_entries: 4 ,// 连接分页主体，显示的条目数，如果总页数是100/7=14页，这个属性表示下一页左右边可以显示4个页码
	        load_first_page: false,
	        callback:handlePaginationClickOperate
		});
	},500);
	
});

function handlePaginationClickOperate(new_page_index, pagination_container){
	var pageNowOperate;
	var companyId = $("#companyId").val();
	if(new_page_index==null){
		pageNowOperate = "";
	}else{
		pageNowOperate = new_page_index + 1;
	}
	$.ajax({
		type:'post',
		url:'findMemberOperate.do',
		//请求是key/value这里不需要指定contentType，因为默认就是key/value
		//contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'pageNowOperate='+pageNowOperate+'&companyId='+companyId,
		success:function(data){//返回json结果
			$("#tab tr:gt(0)").remove();
			for(var i = 0; i < data.dataList.length; i++){
				var trNode = $("<tr></tr>");
				trNode.append($("<td>"+data.dataList[i].log.id+"</td>"));
				trNode.append($("<td>"+data.dataList[i].member.name+"</td>"));
				trNode.append($("<td>"+data.dataList[i].company.name+"</td>"));
				trNode.append($("<td>"+data.dataList[i].log.operateDate+"</td>"));
				trNode.append($("<td>"+data.dataList[i].log.quarter+"</td>"));
				trNode.append($("<td>"+data.dataList[i].log.content+"</td>"));
				$("#tab").append(trNode);
			}
			$("#pageNowOperate").val(data.currentPage);
			$("#totalPageOperate").val(data.totalRecord);
		}
		
	});
}