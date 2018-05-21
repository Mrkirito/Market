$(function(){
		var competitionId = $("#competitionId").val();
		$.ajax({
			type:'post',
			url:'findCompany.do',
			//请求是key/value这里不需要指定contentType，因为默认就是key/value
			//contentType:'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data:"competitionId=" + competitionId,
			success:function(data){//返回json结果
				for(var i = 0; i < data.length; i++){
					$("#u1").append("<li>"+data[i].name+"</li>");
					$("#u2").append("<li>"+data[i].name+"</li>");
				}
				init(); 
			}
			
		});
	});
    function init(){
    	$("#receiver").html($("#u1 li:first").html());
		$("#u1 li").css("cursor","pointer").mouseover(function(){
			$(this).css("background", "#ebebeb").siblings().css("background", "#98c8f9");
		}).mouseleave(function (){
			$(this).css("background", "#98c8f9");
		}).click(function(){
			$("#receiver").html($(this).html());
			$("#msg").empty();
		});
		$("#receiver2").html($("#u2 li:first").html());
		$(":input[name=receiveAddress]").val($("#u2 li:first").html());
		$("#u2 li").css("cursor","pointer").mouseover(function(){
			$(this).css("background", "#ebebeb").siblings().css("background", "#98c8f9");
		}).mouseleave(function (){
			$(this).css("background", "#98c8f9");
		}).click(function(){
			$("#receiver2").html($(this).html());
			$(":input[name=receiveAddress]").val($(this).html());
		});
		$("#u2").hide();
		openChatWin();
    }
    
    function check(){
		var content=$("#chat_content");
		var competitionId = $("#competitionId").val();
		if(content.val()!=""){
			var receiver=$("#receiver").html();
			$.ajax({
				type:'post',
				url:'chatMessage.do',
				//请求是key/value这里不需要指定contentType，因为默认就是key/value
				//contentType:'application/json;charset=utf-8',
				//数据格式是json串，商品信息
				data:'content='+content.val()+'&receiver='+receiver + "&competitionId=" + competitionId,
				success:function(data){//返回json结果
					$("#msg").append(data.sender+" "+data.sendDate+" "+data.content+"\n");
					content.val("");
				}
				
			});
		}
	}
    
    function openChatWin(){
		$("#rightContainer2").hide();
		$("#rightContainer3").hide();
		$("#rightContainer4").hide();
		$("#rightContainer").show();
		$("#u1").show();
		$("#u2").hide();
	}
	
	function openChatRecord(){
		$("#rightContainer").hide();
		$("#rightContainer3").hide();
		$("#rightContainer4").hide();
		$("#u1").hide();
		$("#u2").hide();
		$("#rightContainer2").show();
		handlePaginationClickChat();
		window.setTimeout(function(){
			var pageNowChat = $("#pageNowChat").val();
			var totalPageChat = $("#totalPageChat").val();
			$("#pageChat").pagination( totalPageChat,{
		        items_per_page: 10, // 每页显示多少条记录
		        current_page: pageNowChat-1, // 当前显示第几页数据
		        num_display_entries: 4, // 分页显示的条目数,如果总页数是100/7=14页，这个属性表示上一页右边可以显示4个页码
		        next_text:"下一页",
		        prev_text:"上一页",
		        num_edge_entries: 4 ,// 连接分页主体，显示的条目数，如果总页数是100/7=14页，这个属性表示下一页左边可以显示4个页码
		        load_first_page: false,
		        callback:handlePaginationClickChat
			});
		},300);
		
	}
	
	function handlePaginationClickChat(new_page_index, pagination_container){
		var pageNowChat;
		if(new_page_index==null){
			pageNowChat = "";
		}else{
			pageNowChat = new_page_index + 1;
		}
		var competitionId = $("#competitionId").val();
		$.ajax({
			type:'post',
			url:'chatRecord.do',
			//请求是key/value这里不需要指定contentType，因为默认就是key/value
			//contentType:'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data:'pageNowChat='+pageNowChat + "&competitionId=" + competitionId,
			success:function(data){//返回json结果
				$("#tab tr:gt(0)").remove();
				for(var i = 0; i < data.dataList.length; i++){
					var trNode = $("<tr></tr>");
					trNode.append($("<td>"+data.dataList[i].sender+"</td>"));
					trNode.append($("<td>"+data.dataList[i].receiver+"</td>"));
					trNode.append($("<td>"+data.dataList[i].sendDate+"</td>"));
					trNode.append($("<td>"+data.dataList[i].content+"</td>"));
					$("#tab").append(trNode);
				}
				$("#pageNowChat").val(data.currentPage);
				$("#totalPageChat").val(data.totalRecord);
			}
			
		});
	}
	
	function openMail(){
		$("#rightContainer").hide();
		$("#rightContainer2").hide();
		$("#rightContainer4").hide();
		$("#rightContainer3").show();
		$("#u1").hide();
		$("#u2").show();
	}
	
	function sendMail(){
		var receiveAddress = $(":input[name=receiveAddress]").val();
		var content = $("#mail_content").val();
		var title = $(":input[name=title]").val();
		if(receiveAddress!="" && content!="" && title!=""){
			$.ajax({
				type:'post',
				url:'sendMail.do',
				data:'receiveAddress='+receiveAddress+'&content='+content+'&title='+title,
				//请求是key/value这里不需要指定contentType，因为默认就是key/value
				//contentType:'application/json;charset=utf-8',
				//数据格式是json串，商品信息
				success:function(data){//返回json结果
					openMailRecord();
				}
				
			});
		}
	}
	
	function openMailRecord(){
		$("#rightContainer").hide();
		$("#rightContainer2").hide();
		$("#rightContainer3").hide();
		$("#rightContainer4").show();
		$("#u2").hide();
		$("#u1").hide();
		handlePaginationClickMail();
		window.setTimeout(function(){
			var pageNowMail = $("#pageNowMail").val();
			var totalPageMail = $("#totalPageMail").val();
			$("#pageMail").pagination( totalPageMail,{
		        items_per_page: 6, // 每页显示多少条记录
		        current_page: pageNowMail-1, // 当前显示第几页数据
		        num_display_entries: 4, // 分页显示的条目数,如果总页数是100/7=14页，这个属性表示上一页右边可以显示4个页码
		        next_text:"下一页",
		        prev_text:"上一页",
		        num_edge_entries: 4 ,// 连接分页主体，显示的条目数，如果总页数是100/7=14页，这个属性表示下一页左右边可以显示4个页码
		        load_first_page: false,
		        callback:handlePaginationClickMail
			});
		},300);
	}
	
	function handlePaginationClickMail(new_page_index, pagination_container){
		var pageNowMail;
		if(new_page_index==null){
			pageNowMail = "";
		}else{
			pageNowMail = new_page_index + 1;
		}
		
		$.ajax({
			type:'post',
			url:'loadMailByCompetitionId.do',
			//请求是key/value这里不需要指定contentType，因为默认就是key/value
			//contentType:'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data:'pageNowMail='+pageNowMail,
			success:function(data){//返回json结果
				$("#tab1 tr:gt(0)").remove();
				for(var i = 0; i < data.dataList.length; i++){
					var trNode = $("<tr></tr>");
					trNode.append($("<td>"+data.dataList[i].sendAddress+"</td>"));
					trNode.append($("<td>"+data.dataList[i].receiveAddress+"</td>"));
					trNode.append($("<td>"+data.dataList[i].title+"</td>"));
					trNode.append($("<td>"+data.dataList[i].content+"</td>"));
					trNode.append($("<td>"+data.dataList[i].sendDate+"</td>"));
					$("#tab1").append(trNode);
				}
				$("#pageNowMail").val(data.currentPage);
				$("#totalPageMail").val(data.totalRecord);
			}
			
		});
	}