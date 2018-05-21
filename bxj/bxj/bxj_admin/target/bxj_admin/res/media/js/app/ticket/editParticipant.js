define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool','app/common/dateTool','app/common/messageTool','app/ticket/ticket33Sit'],
	function($, util, listTool, winTool, dateTool, messageTool,ticket33Sit) {
    var commonlist = {
    		list:function(){
    			var thiz=this,list2=listTool.create({
    				title: "参会人员信息",
                    selector: ".data-list2",
                    remote: true,
                    url: serverHost + "/ticket/getSalesmanTicketAttendPeoples.json",
                    pageSize:20,
                    width: 600,
                    height: 600,
                    columns: [{name:"订单ID",width:80,fieldName : "orderId"},
                              {name:"会议名称",width:80,fieldName : "activityName"},
                              {name:"销售渠道",width:80,fieldName : "manName"}, 
                              {name:"支付时间",width:80,fieldName : "payTime",renderer : function(row, col, data, value) {return util.dateFormat2(value, 'YYYY-MM-DD HH:MM:SS');}},
                              {name:"购买人手机号",width:80,fieldName : "tel"},
                              
                              {name:"楼",width:50,fieldName : "floor"},
                              {name:"区",width:50,fieldName : "area"},
                              {name:"排",width:50,fieldName : "rows"},
                              {name:"号",width:50,fieldName : "number"},
 
                              {name:"参会人员电话",width:80,fieldName : "userPhone"},
                              {name:"参会人员姓名",width:80,fieldName : "userName"},
							  {name:"参会人员公司",width:80,fieldName : "company"},
							  {name:"参会人员营业区",width:80,fieldName : "businessHall"},
							  {name:"参会人员状态",width:80,fieldName : "state",renderer : function(row, col, data, value) {
								  if (value == 0){ 
									  return "<span class='bg-warning'>锁定</span>";
								  }else if (value == 1){
									  return "<span class='bg-warning'>待入场</span>";
								  }else if (value == 2){
									  return "<span class='bg-success'>已入场</span>";
								  }else{
									  return "<span class='bg-success'>"+value+"</span>";
								  }
							  	}
							  },
                              {name:"操作",
                               width:200,
          					   rightFixed: true,
          					   renderer : function(rindx, cindex, data) {
          						   var html='<button class="update" title="编辑"><i class="fa fa-pencil-square-o bigger-150"></i></button>';
          						   var dom='';
          						   if(data.state==2) 
          							   dom='<button class="enter" title="出场"><i class="fa fa-sign-out bigger-150"></i></button>&nbsp;';
          						   else 
          							   dom='<button class="enter" title="进场"><i class="fa fa-sign-in bigger-150"></i></button>&nbsp;';          						   
                                   if(data.userPhone&&data.userName)
                                	   html+=' &nbsp;<button class="sendmsg" title="发送短信"><i class="fa bigger-150 fa-envelope"></i></button>&nbsp;'+dom;
                                   /*if(data.userPhone&&data.userName)
                                	   html+='<button class="change" title="更换座位"><i class="fa fa-retweet bigger-150"></i></button>';    */                            	
	                               return html;
                               }
          					  }
                            ],
                    paramFn: function () {
                		return thiz.getParams();
                    },
                    initSearch: function (query) {                    	
                    },
					tbars: [/*{
						icon: 'fa fa-pencil',
						name: "清场",
						handler: function (idx, data) {
							thiz.clearMeet();
						}
					}*/],
                    classEvents : [
                       {	className : "update",
       	            		handler: function (idx, data) {
       	            			thiz.update(data);
       	            		}  
                       },
                       {	className : "sendmsg",
      	            		handler: function (idx, data) {
      	            			thiz.sendmsg(data);
      	            		}
                      },
                      {className:"enter",
                    	handler: function (idx, data) {
                    		thiz.attendMeet(data);
    	            	}
                      },
                      {/*className:'change',
                       handler: function (idx, data) {
                    	   ticket33Sit.createSitDocumentListWin(data);
      	            	}*/  
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
				$(".search-action").on("click", function() {
					thiz.list2.reloadPageData(1);
				});
				$('.clear-action').on('click',function(){
					$(".reportTime").val('');
		    		$(".startTime").val('');
		    		$(".endTime").val('');
		    		$('select[name=attendState').val('');
		    		$('select[name=basicId]').val('');
		    		$("input[name=userName]").val('');
		    		$("input[name=userPhone]").val('');
		    		$("input[name=buyTel]").val('');
		    		$("input[name=searchOrderId").val('');
		    		$('select[name=state]').val('');
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
		    		$(".endTime").val('');
		    	}
		    	return {
		    		startTime: $(".startTime").val(),
		    		endTime: $(".endTime").val(),
		    		channelId:$('.channelId').val(),
		    		userName:$('input[name=userName]').val(),
		    		basicId:$('select[name=basicId]').val(),
		    		userPhone:$("input[name=userPhone]").val(),
		    		orderId:$("input[name=searchOrderId]").val(),
		    		attendState:$('select[name=attendState]').val(),
		    		state:$('select[name=state]').val(),
		    		buyTel:$("input[name=buyTel]").val()
		    	};
		    },
		    sendmsg:function(data){
        		var fid = data.fid,name=data.userName;        		
		    	util.request({
            		confirm: true,
                    msg: "确定要向该参会者("+name+")发送参会短信?",
                    url: serverHost + "/ticket/sendAttendMsg.json",
                    dataType:"json",
                    params:{fid:fid},
                    success: function (resp) {
                        if(resp.success) {
                        	messageTool.success("短信发送成功");
                        } else {
                        	messageTool.error("短信发送失败");
                        }
                    }
                });
		    },
		    update:function(data){
               	var thiz = this,
        		fid = data.fid;
              	util.ajax({
                url: serverHost + "/ticket/getAttendPeopleByFid.json",
                params: {fid : fid},
                success: function (resp) {
                    if(resp.success) {
                    	var video = resp.model;  
                    	var win = winTool.create({
                            title: "修改",
                            height: 500,
                            width: 500,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".video-tpl",
                            okFn: function (win) {
                          		var isValidateTrue=true;
                        		win.find('input.is_number').each(function(i,o){
                        			o=$(o);
                        			var v=o.val();
                        			if(!v){
                        				isValidateTrue=false;
                        				messageTool.error(o.parent().siblings().text()+'必须填写');
                        				return false;
                        			}
                        		});
                        		if(isValidateTrue){                             	
                        			var paras=win.find('#form').serialize();
	                            	util.request({
	                            		confirm: true,
	                                    msg: "确定要更改参会者信息?",
	                                    url: serverHost + "/ticket/updateAttendPeoples.json",
	                                    dataType:"json",
	                                    params:paras ,
	                                    success: function (resp) {
	                                        if(resp.success) {
	                                        	win.close();
	                                        	messageTool.success("更新成功");
	                                        	thiz.list2.reloadPageData();
	                                        } else {
	                                        	if(resp.errorcode&&resp.errorcode==1){
		                                        	messageTool.error("手机号重复，请更换手机号");
	                                        	}else{
	                                        		messageTool.error("更新失败");	                                        		
	                                        	}
	                                        }
	                                    }
	                                });
                        	  }
                        	}
                     });
                    var activityName=video.activityName;
                    var fid=video.fid;
                    var floor=video.floor;                    
                    var area=video.area;              
                    var rows=video.rows;
                    var number=video.number;
                    var userName=video.userName;
                    var userPhone=video.userPhone;
                    win.find('input[name=fid]').val(fid);
                    win.find('input[name=orderId]').val(video.orderId);
                    win.find('input[name=basicId]').val(video.basicId);
                    win.find('input[name=userName]').val(userName);
                    win.find('input[name=userPhone]').val(userPhone);
                    win.find('select[name=company]').val(video.company||'');
                    win.find('input[name=businessHall]').val(video.businessHall||'');
                    win.find('input[name=activityName]').val(activityName);
                    win.find('input[name=sit]').val((floor?(floor+"-"):'')+(area?(area+"-"):"")+rows+"-"+number);
                   }  	
                }
            });
           },
           attendMeet:function(data){
        	   var thiz = this;
        	   var fid=data.fid;
        	   var type=data.state;
        	   var text='';
        	   if(type==2){
        		   text='确定将该参会者更改为[出场状态]?';
        	   }else{
        		   text='确定将该参会者更改为[进场状态]?';
        	   }
        	   util.request({
        		   confirm: true,
        		   msg: text,
        		   url: serverHost + "/ticket/attendMeet.json",
        		   params: {fid: fid,userPhone:data.userPhone,basicId:data.basicId,state:data.state},
        		   success: function (resp) {
        			   if (resp.success) {
        				   messageTool.success("更改成功");
        				   thiz.list2.reloadPageData();
        			   } else {
        				   messageTool.error("更改失败");
        			   }
        		   }
        	   });
           }
    };
    return commonlist;
});