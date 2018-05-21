define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool','app/common/dateTool','app/common/messageTool'],function($, util, listTool, winTool, dateTool, messageTool) {
    var commonlist = {
    		list:function(){
    			var thiz=this,list2=listTool.create({
    				title: "参会人员信息",
                    selector: ".data-list2",
                    remote: true,
                    url: serverHost + "/ticket/getDocuments.json",
                    pageSize:20,
                    width: 600,
                    height: 600,
                    columns: [{name:"会议名称",width:80,fieldName : "activityName"},                              
                              {name:"楼",width:80,fieldName : "floor"},
                              {name:"区",width:80,fieldName : "area"},
                              {name:"排",width:80,fieldName : "rows"},
                              {name:"号",width:80,fieldName : "number"},
                              {name:"序号",width:80,fieldName : "ticketNo"},
                              {name:"状态",width:100,fieldName : "state",
             					   renderer : function(row, col, data, value) {
             						   switch (Number(value)) {
	          							case 4:
	          								return "<span class='label label-table label-purple'>锁定<i></i></span>";
	          							case 1:
	          								return "<span class='label label-table label-success'>已售<i></i></span>";
	          							case 0:
	          								return "<span class='label label-table label-danger'>待售<i></i></span>";
	          							case 3:
	          								return "<span class='label label-table label-primary'>回源票<i></i></span>";
	          							case 2:
	          								return "<span class='label label-table label-info'>在售中<i></i></span>";
	          							default:
	          								break;
	          						   }
                                  }	
                              }
                            ],
                    paramFn: function () {
                		return thiz.getParams();
                    },
                    initSearch: function (query) {                    	
                    },
                    tbars: [
                            {
                                icon: 'fa fa-plus',
                                name: "锁定票源",
                                handler: function (idx, data) {
                                    thiz.lockPop();
                                }
                            }
                    ],
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
                      }
                    ]   				
    			});
				this.list2 = list2;
				$(".search-action").on("click", function() {
					var start=$('.start').val();
					var end=$('.end').val();
					if(start||end){
						if(!start||isNaN(start)){
							messageTool.error("序号起始输入违法"); 
							return false;
						}						
						if(!end||isNaN(end)){
							messageTool.error("序号终止输入违法"); 
							return false;
						}
						if(start>end){
							messageTool.error("序号终止小于序号起始"); 
							return false;
						}
					}
					thiz.list2.reloadPageData(1);
				});
    		},
		    getParams: function () {
		    	return {
		    		state:$('.state').val(),
		    		basicId:$('.basicId').val(),
					start:$('.start').val(),
					end:$('.end').val(),
					floor:$('.floor').val(),
					area:$('.area').val(),
					rows:$('.rows').val(),
					number:$('.number').val()
		    	};
		    },
		    lockPop:function(){
	        	var thiz = this;
	        	var win = winTool.create({
	                title: "新增锁定票源",
	                height: 300,
	                width: 500,
	                showCancel: true,
	                okName: "保存",
	                cancelName: "关闭",
	                type: "selector",
	                selector: ".video-tpl",
	                okFn: function (win) {
						var start=win.find('input[name=start]').val();
						var end=win.find('input[name=end]').val();
						var flag=true;
						if(!start||isNaN(start)){
							messageTool.error("序号起始输入违法"); 
							flag=false;
						}						
						if(!end||isNaN(end)){
							messageTool.error("序号终止输入违法"); 
							flag=false;
						}
						if(start>end){
							messageTool.error("序号终止小于序号起始"); 
							flag=false;
						}
						if(flag){
							var activity=win.find('select[name=basicId]');
							var text="确定要锁定（"+activity.find('option:selected').text()+"），序号"+start+"至"+end+"的票?";
							util.request({
								confirm : true,
								msg : text,
								url : serverHost+ "/ticket/lockDocuments.json",
								params:{
						    		basicId:activity.val(),
									start:start,
									end:end
									
								},
								success : function(resp) {
									if (resp.success) {
										messageTool.success("锁定票源成功");
									} else {
										messageTool.error("锁定票源失败");
									}
								}
							});							
						}
	                }
	             });
		    }
    };
    return commonlist;
});