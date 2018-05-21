define(['jquery','app/common/util', 'app/common/dateTool', 'app/common/messageTool'],
		function($, util, dateTool, messageTool) {
    var userDataAdd = {
    		init:function(){
    			$(".saveIn").on("click", function(){
    				var isValidateTrue=true;
    				var date=$("input[name=dataTime]").val();
    				if(!date){
        				isValidateTrue=false;
        				messageTool.error('数据统计时间不可为空');
        				return false;
    				}
            		$('input.is_number').each(function(i,o){
            			o=$(o);
            			var v=o.val();
            			if(isNaN(v)){
            				isValidateTrue=false;
            				messageTool.error(o.parent().siblings().text()+'必须为数字');
            				return false;
            			}
            		});
            		if(isValidateTrue){                    			
            			util.request({
            				confirm : true,
            				msg : "确定要新增?",
            				url : serverHost+ "/extend/saveData.json",
            				params : $('#form').serialize(),
            				success : function(resp) {
                                if(resp.success) {
                                	messageTool.success("保存成功");
                                } else {
                                	messageTool.error(resp.msg || "保存失败");
                                }
            				}
            			});
            		}
    			});
    			dateTool.renderDate("input[name=dataTime]");
    		}
    };
    return userDataAdd;
});