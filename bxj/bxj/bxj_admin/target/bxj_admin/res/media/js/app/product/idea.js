define(['jquery','app/common/util','app/common/advanceListTool','app/common/dateTool','app/common/messageTool',"app/common/commonTool",'app/common/winTool'],function($, util, listTool,dateTool,messageTool,commonTool,winTool) {
	var idea_list_column = [ {
		name : "日期",
		width : 100,
		fieldName : "createAt",
		renderer : function(row, col, data, value) {
			return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
		}
	}, {
		name : "意见",
		width : 100,
		fieldName : "text"
	}, {
		name : "联系电话",
		width : 30,
		fieldName : "contactInformation"
	}, {
		name : "处理状态",
		width : 30,
		fieldName : "state",
		renderer : function(row, col, data, value) {
			if(value==1){
				return "<span class='label label-table label-purple'>待处理<i></i></span>";
			}else if(value==2){
				return "<span class='label label-table label-success'>已处理<i></i></span>";
			}
		}
	}, {
		name : "备注",
		width : 200,
		fieldName : "mark"
	},{name:"操作",
        width:100,
		   rightFixed: true,
		   renderer : function(rindx, cindex, data) {
            return '<div class="action-buttons"><div class="btn-group"><button class="btn btn-info update"><i class="fa fa-pencil"></i>编辑</button></div></div>';
        }
	  }
	];
	var group_list_column = [ {
		name : "日期",
		width : 100,
		fieldName : "ctime",
		renderer : function(row, col, data, value) {
			return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
		}
	}, {
		name : "险种组合",
		width : 100,
		fieldName : "gname"
	}, {
		name : "联系电话",
		width : 30,
		fieldName : "phone"
	} ];
	var userList = {
    	list:function(){
	        var thiz = this,key = commonTool.getUrlParam("key"),
	        list = listTool.create({
	            	title: key=='idea'?"意见反馈":"险种组合反馈",
	                selector: ".data-list",
	                remote: true,
	                url: serverHost + "/statistics/idea.json?key="+key,
                    height: 800,
                    width: 800,
	                showCheckbox: false,
	                multiSelect: false,
	                columns:key=='idea'?idea_list_column:group_list_column,
	                paramFn: function () {
	                	var begin=$('input[name=begin]').val();
	                	var end=$('input[name=end]').val();
	                	return {begin:begin,end:end};
	                },
	                classEvents : [ {
	                	className : "update",
	                	handler : function(idx, data) {
	                		thiz.update(data);
	                	}
	                } ]
	        });
	        dateTool.renderDate($('input.input_date'));
            this.list = list;
            $('button.search').click(function(){
            	var beginDate=$('input[name=begin]').val();
            	var endDate=$('input[name=end]').val();
            	var d1 = new Date(beginDate.replace(/\-/g, "\/"));  
            	var d2 = new Date(endDate.replace(/\-/g, "\/"))
            	if(beginDate!=""&&endDate!=""&&d1 >=d2){  
            		messageTool.error("开始时间不能大于结束时间！");  
            	}else{
            		list.reloadPageData();            		
            	}
            });
        },
        update : function(data) {  
        	var win = winTool.create({
        		title : "意见评估10",
        		height : 300,
        		width : 500,
        		showCancel : true,
        		okName : "保存",
        		cancelName : "关闭",
        		type : "selector",
        		selector : ".video-tpl",
        		okFn : function(win) {
        			var paras=win.find('#form').serialize();
                	util.request({
                		confirm: true,
                        msg: "确定要更改意见信息?",
                        url: serverHost + "/statistics/updateIdea.json",
                        dataType:"json",
                        params:paras ,
                        success: function (resp) {
                            if(resp.success) {
                            	win.close();
                            	messageTool.success("更新成功");
                            	userList.list.reloadPageData();   
                            }
                        }
                    });
        		}
        	});
        	win.find('input[name=id]').val(data.id);
        	win.find('input[name=text]').val(data.text);
        	win.find('select[name=state]').val(data.state);
        	win.find('input[name=mark]').val(data.mark);
        }
    };
    return userList;
});