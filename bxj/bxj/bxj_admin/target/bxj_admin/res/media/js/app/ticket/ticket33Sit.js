define([ 'jquery', 'app/common/util', 'app/common/listTool',
				'app/common/winTool', 'app/common/dateTool',
				'app/common/messageTool'],
		function($, util, listTool, winTool, dateTool, messageTool) {
	var thizz=this;
    return {
    		createSitDocumentListWin:function(data){
    			var detailId=data.fid,win = winTool.create({
                    title: '更改('+data.activityName+','+(data.userName||'')+','+(data.userPhone||'')+')座位信息',
                    showOk: true,
                    okName:"保存",
                    showCancel: false,
                    cancelName: "关闭",
                    selector: "div.orders_price_list",
                    type: 'selector',
                    width: 900,
                    height: 600,
                    okFn: function (win) {
	                    	var m=false,sitId,selected = thizz.sitList.getSelected() || []
	                    	$(selected).each(function (idx, data) {
	                    		sitId=data.fid;
	                    	});
	                    	if(sitId){
	                    		m=true;
	                    	}else{
	                    		messageTool.error('请勾选一个你要替换的座位！');
	                    		m=false;
	                    	}
	                    	if(m){	                    		
	                    		util.request({
	                    			confirm: true,
	                    			msg: "确定要更改参会者座位?",
	                    			url: serverHost + "/ticket/updateAttendPeopleSit.json",
	                    			dataType:"json",
	                    			params:{fid:detailId,docId:sitId,state:data.state},
	                    			success: function (resp) {
	                    				if(resp.success) {
	                    					win.close();
	                    					messageTool.success("更新成功");
	                    					commonlist.list2.reloadPageData();
	                    				}
	                    			}
	                    		});
	                    	}
	                    }
                });
                this.createSitDocumentList(win,data);
                win.find('.old_sit').val((data.floor||'')+' '+(data.area||'')+' '+(data.rows||'')+' '+(data.number||''));
    		},
    		createSitDocumentList:function(win,data){
                var thiz = this,
                priceListSelector = win.find("div.sit_document_list");                
                itemListTool = listTool.create({
                    selector: priceListSelector,
                    toolbarSelector: win.find(".orders_price_toolbar"),
                    remote: true,
                    showCheckbox: true,
                    multiSelect:false,
                    pageSize:10,
                    showPagebar: true,
                    url: serverHost + "/ticket/getDocuments.json?basicId="+data.basicId,
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
                              }],
                     paramFn: function () {
                    	 	return {state : win.find('.doc_state').val(),rows : win.find('.doc_rows').val()};
                     }        
                });
                thizz.sitList=itemListTool;
                win.find('.search-money').on('click',function(){
                	itemListTool.loadPageData();
                });
    		}
    };
});