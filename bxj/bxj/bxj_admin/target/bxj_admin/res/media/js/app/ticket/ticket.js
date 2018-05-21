define(['jquery','app/common/util','app/common/listTool','app/common/winTool','app/common/dateTool','app/common/messageTool'],function($, util, listTool, winTool, dateTool, messageTool) {
    var commonlist = {
    		list:function(){
    			var thiz=this,list=listTool.create({
    				title: "会议总体售票情况",
                    selector: ".data-list",
                    remote: true,
                    url: serverHost + "/ticket/getTicketCount.json",
                    width: 600,
                    height: 600,
                    showPagebar:false,
					showCheckbox:false,
					pageSize:20,
                    columns: [{name:"会议名称",width:80,fieldName : "activityName"},
                              
                              {name:"售出总数",width:80,fieldName : "totalNum0"},
                              {name:"实际总数",width:80,fieldName : "actualNum0"},
                              {name:"赠票总数",width:80,fieldName : "presentNum0"},
                              {name:"总金额",width:80,fieldName : "totalMoney0"},
                              
                              {name:"线下售出总数",width:80,fieldName : "totalNum3"},
                              {name:"线下实际总数",width:80,fieldName : "actualNum3"},
                              {name:"线下赠票总数",width:80,fieldName : "presentNum3"},
                              {name:"线下总金额",width:80,fieldName : "totalMoney3"},

                              {name:"微信售出总数",width:80,fieldName : "totalNum1"},
                              {name:"微信实际总数",width:80,fieldName : "actualNum1"},
                              {name:"微信赠票总数",width:80,fieldName : "presentNum1"},
                              {name:"微信总金额",width:80,fieldName : "totalMoney1"},
                              
                              {name:"支付宝售出总数",width:80,fieldName : "totalNum2"},
                              {name:"支付宝实际总数",width:80,fieldName : "actualNum2"},
                              {name:"支付宝赠票总数",width:80,fieldName : "presentNum2"},
                              {name:"支付宝总金额",width:80,fieldName : "totalMoney2"}],
                    paramFn: function () {
                		return thiz.getParams();
                    },
                    initSearch: function (query) {                    	
                    },
                    tbars: [],
                    classEvents : []
    				
    			});
				this.list = list;
				dateTool.renderDateRange(".reportTime", {
					applyClick : function(startDate, endDate) {
						$(".startTime").val(startDate+' 00:00:00');
						$(".endTime").val(endDate+' 23:59:59');
					} 
				});
				//搜索
				$(".search-action").on("click", function() {
					thiz.list.loadPageData(1);
				});
				$('.clear-action').on('click',function(){
					$(".reportTime").val('');
		    		$(".startTime").val('');
		    		$(".endTime").val('');
		    		$('select[name=payType]').val('');
		    		$('select[name=basicId]').val('');
		    		var channelId=$('.channelId');
		    		if(channelId.hasClass('form-control')){
		    			channelId.val('');
		    		}
				});
    		},
		    getParams: function () {
		    	var s=$(".reportTime").val();
		    	if(!s){
		    		$(".startTime").val('');
		    		$(".endTime").val('')
		    	}
		    	return {
		    		startTime: $(".startTime").val(),
		    		endTime: $(".endTime").val(),
		    		channelId:$('.channelId').val(),
		    		payType:$('select[name=payType]').val(),
		    		basicId:$('select[name=basicId]').val(),
		    	};
		    }
    };
    return commonlist;
});