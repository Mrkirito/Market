define(['jquery','app/common/util', 'app/common/dateTool', 'app/common/messageTool'],
		function($, util, dateTool, messageTool) {
    var userDataAdd = {
    		init:function(){
    			$(".saveIn").on("click", function(){
    				var isValidateTrue=true;
            		$('.is_must').each(function(i,o){
            			o=$(o);
            			var v=o.val();
            			if(!v){
            				isValidateTrue=false;
            				messageTool.error(o.parent().siblings().text()+'必须填写');
            				return false;
            			}
            		});
            		if(isValidateTrue){                    			
            			util.request({
            				confirm : true,
            				msg : "确定要发送?",
            				url : serverHost+ "/message/batchSendMsg.json",
            				params : $('#form').serialize(),
            				success : function(resp) {
                                if(resp.success) {
                                	if(resp.hasErrorCount){
                                		messageTool.error("向手机号"+resp.errorList+"发送消息失败");
                                	}else{                                		
                                		messageTool.success("发送消息成功");
                                	}
                                } else {
                                	messageTool.error("发送消息失败");
                                }
            				}
            			});
            		}
    			});
    		}
    };
    return userDataAdd;
});