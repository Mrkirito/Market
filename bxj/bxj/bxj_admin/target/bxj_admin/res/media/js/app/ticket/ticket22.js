define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool','app/common/dateTool','app/common/messageTool'],function($, util, listTool, winTool, dateTool, messageTool) {
    var commonlist = {
    		list:function(){
    			var thiz=this,list2=listTool.create({
    				title: "销售人员流水",
                    selector: ".data-list2",
                    remote: true,
                    url: serverHost + "/ticket/getSalesmanTickets.json",
                    width: 600,
                    height: 600,
                    pageSize:12,
                    columns: [
                              {name:"订单ID",width:80,fieldName : "orderId"},
                              {name:"会议名称",width:80,fieldName : "activityName"},
                              {name:"销售代表",width:80,fieldName : "salesmanName"}, 
                              
                              {name:"售出总数",width:80,fieldName : "totalNum"},
                              {name:"实际总数",width:80,fieldName : "actualNum",renderer: function (row, col, data, value) { return "<font color='red'>"+value+"</font>";}},
                              {name:"赠票总数",width:80,fieldName : "presentNum",renderer: function (row, col, data, value) { return "<font color='blue'>"+value+"</font>";}},
                              {name:"总金额",width:80,fieldName : "totalMoney",renderer: function (row, col, data, value) { return "<font color='red'>"+value+"</font>";}},
                              {
                                  name: "参会信息状态",
                                  width: 80,
                                  fieldName: "attendState",
                                  renderer: function (row, col, data, value) {
                                      if (value == 1) {
                                          return "<span class='bg-success'>已全填写</span>";
                                      } else {
                                          return "<span class='bg-warning'>（"+data.exitAttend+"张）待补填</span>";
                                      }
                                  }
                              },
                              {name:"支付时间",width:80,fieldName : "payTime",renderer : function(row, col, data, value) {return util.dateFormat2(value, 'YYYY-MM-DD HH:MM:SS');}},
                              {name:"支付方式",width:80,fieldName : "payType",renderer : function(row, col, data, value) {return value==3?"线下支付":value==1?"微信支付":"支付宝支付"}},
                              {name:"购买人电话",width:80,fieldName : "customerTel"},
                              {name : "操作",
								width : 100,
								renderer : function(rindx, cindex, data) {
									var st = '<div class="action-buttons">'
											+ '<div class="btn-group">'
											+ '<button class="btn btn-info updateData">订单明细</button>'
											+ '</div></div>';
									return st;
								}}
                            ],
                    paramFn: function () {
                		return thiz.getParams();
                    },
                    initSearch: function (query) {                    	
                    },
                    tbars: [],
                    classEvents : [
                                   {
           							className : "updateData",
           							handler : function(idx, data) {
           								thiz.goToLink(data);
           							}
           						}              
                    ]
    			});
				this.list2 = list2;
				dateTool.renderDateRange(".reportTime", {
					applyClick : function(startDate, endDate) {
						$(".startTime").val(startDate+' 00:00:00');
						$(".endTime").val(endDate+' 23:59:59');
					}
				});
				//搜索
				$(".search-action").on("click", function() {
					thiz.list2.reloadPageData(1);
				});
				$('.clear-action').on('click',function(){
					$(".reportTime").val('');
		    		$(".startTime").val('');
		    		$(".endTime").val('');
		    		$('select[name=payType]').val('');
		    		$('select[name=basicId]').val('');
		    		$('select[name=attendState]').val('');
		    		$("input[name=customerTel]").val('');
		    		$("input[name=orderId]").val('');
		    		var channelId=$('.channelId');
		    		if(channelId.hasClass('form-control')){
		    			channelId.val('');
		    		}
				});
    		},
    		goToLink:function(data){
    			window.location.href=serverHost + "/ticket/index3.jhtml?orderId="+data.orderId;
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
		    		attendState:$('select[name=attendState]').val(),
		    		customerTel:$("input[name=customerTel]").val(),
		    		orderId:$("input[name=orderId]").val()
		    	};
		    }
    };
    return commonlist;
});