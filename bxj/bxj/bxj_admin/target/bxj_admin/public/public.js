$(function(){
	$("#search").on('click',function(){
		document.getElementById("form").submit();
	});
	$("button.scvm").on('click',function(){
		var pid=$(this).data('fid');
		$.ajax({
			type : "POST",
			url : bxj_path + "/refreshcache/getProductVm.json",
			data : {pids:pid},
			dataType : "json",
			success : function(data) {
				alert(data.result);
			},
			error : function(data) {
			}
		});
	});
	$("button.drfl").on('click',function(){
		window.location.href=$(this).data('link');
	});
});