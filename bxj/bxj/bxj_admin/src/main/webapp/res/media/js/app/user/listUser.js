define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool'],
		function($, util, listTool, winTool, dateTool, messageTool) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "冠军视频列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/user/queryListUser.json",
                width: 600,
                height: 600,
                showCheckbox: true,
                columns: [
                    {
                    	name: "编号",
                    	width:100,
                    	fieldName: "fid"		
                	},
                	{
                    	name: "讲师id",
                    	width:100,
                    	fieldName: "lecturerId"		
                	},
                    {
                    	name: "创建时间",
                    	width:80,
                    	fieldName: "createAt",
                    	renderer : function(row, col, data,value) {
                        	return util.dateFormat2(data.createAt, 'YYYY-MM-DD') + "<br/>" + util.dateFormat2(data.showEndTime, 'YYYY-MM-DD');
                        }
                    },
                    {
                    	name: "修改时间",
                    	width:80,
                    	fieldName: "modifyAt",
                    	renderer : function(row, col, data,value) {
                        	return util.dateFormat2(data.modifyAt, 'YYYY-MM-DD') + "<br/>" + util.dateFormat2(data.showEndTime, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "审核状态",
                        width:80,
                        fieldName: "auditStatus",
                        renderer : function(row, col, data,value) {
                        	if(value == 0){
                        		return "<span class='label bg-blue'>待审核<i></i></span>";
                        	} else if(value == 1){
                        		return "<span class='label bg-red'>未通过<i></i></span>";
                        	} else if(value == 2){
                        		return "<span class='label bg-green'>通过<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	var st = '<button class="btn btn-info detail">详情</button>' +
                        			 '<button class="btn btn-primary add">新增</button>' +
                        			 '<button class="btn btn-danger delete">删除</button>' +
                        			 '<button class="btn btn-warning update">修改</button>'
                        	return st;
                        }
                    }
                	],
                paramFn: function () {
                	
                },
               
                initSearch: function (query) {
                	
                },
                tbars: [{
                    icon: 'fa fa-plus',
                    name: "新增",
//                    rightCode: "add",
                    url: serverHost + "coupon/cashTemplateAdd.htm"
                	},
                	{
	                icon: 'fa fa-plus',
	                name: "新增",
	//                rightCode: "add",
	                url: serverHost + "coupon/cashTemplateAdd.htm"
	            	}	
            	],
                classEvents : [{
	            	className : "detail",
	            	handler: function (idx, data) {
	                    thiz.detail(data);
	                }
                },
                {
	            	className : "add",
	            	handler: function (idx, data) {
	                    thiz.add();
	                }
                },
                {
	            	className : "delete",
	            	handler: function (idx, data) {
	                    thiz.delete1(data);
	                }
                },
                {
	            	className : "update",
	            	handler: function (idx, data) {
	                    thiz.update(data);
	                }
                },
                ]
            });
    		
	        $("#sample_editable_1_new").on("click", function(){
	        	var win = winTool.create({
	                title: "新增冠军视频",
	                height: 800,
	                width: 800,
	                showCancel: true,
	                cancelName: "关闭",
	                okName: "保存",
	                type: "selector",
	                selector: ".champion-video-tpl",
	                okFn: function (win) {
	                	var onlineTime = win.find(".onlineTime").val(),
	                		offlineTime = win.find(".offlineTime").val(),
	                		title = win.find("#title").val();
	                	console.info(onlineTime + offlineTime + title);
	                }
	             });
           	 	dateTool.renderDate(win.find(".onlineTime"));
           	 	dateTool.renderDate(win.find(".offlineTime"));
	        });
	        
            this.list = list;
        },
        // 详情
        detail: function(data){
        	var fid = data.fid;
        	util.ajax({
                url: serverHost + "/user/queryVideoDetail.json",
                params: {
                	id: fid,
                },
                success: function (resp) {
                	console.info(resp.model);
                    if(resp.success) {
                    	var data = resp.model;
                    	var win = winTool.create({
                            title: "详情",
                            height: 800,
                            width: 800,
                            showCancel: false,
                            okName: "关闭",
                            type: "selector",
                            selector: ".champion-video-detail-tpl",
                            okFn: function (win) {
                            	win.close();
                            }
                         });
                    	win.find(".title").html(data.title);
                    	win.find(".lecturerId").html(data.lecturerId);
                    } else {
                    	messageTool.error("此记录已不存在", function () {
                    		
                        });
                    }
                }
            });
        	
        },
        // 新增
        add: function(){
        	var thiz = this;
        	var win = winTool.create({
                title: "新增冠军视频",
                height: 800,
                width: 800,
                showCancel: true,
                cancelName: "关闭",
                okName: "保存",
                type: "selector",
                selector: ".champion-video-tpl",
                okFn: function (win) {
                	var onlineTime = win.find(".onlineTime").val(),
                		offlineTime = win.find(".offlineTime").val(),
                		title = win.find("#title").val();
                	var flag = true;
                	if(!title){
                		messageTool.error("视频标题必填"); 
                		flag = false;
                	}
                	if(!onlineTime){
                		messageTool.error("上线时间必填"); flag = false;
                	}
                	if(!offlineTime){
                		messageTool.error("下线时间必填"); flag = false;
                	}
                	console.info(flag);
                	if(flag){
                		util.request({
                    		confirm: true,
                            msg: "确定要保存?",
                            url: serverHost + "/user/addVideo.json",
                            params: {
                            	title: title
//                            	onlineTime: onlineTime,
//                            	offlineTime: offlineTime
                            },
                            success: function (resp) {
                            	console.info(resp.model);
                                if(resp.success) {
                                	messageTool.success("新增成功", function () {
                                    });
                                	win.close();
                                	thiz.list.reloadPageData();
                                } else {
                                	messageTool.error("新增失败", function () {
                                    });
                                }
                            }
                        });
                	}
                }
             });
       	 	dateTool.renderDate(win.find(".onlineTime"));
       	 	dateTool.renderDate(win.find(".offlineTime"));
        },
        // 删除
        delete1: function(data){
        	var thiz = this;
        	var fid = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/user/deleteVideo.json",
                params: {
                	fid: fid,
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("删除成功", function () {
                        });
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error("此记录已不存在", function () {
                        });
                    }
                }
            });
        },
        // 修改
        update: function(data){
        	
        },
    };
    return userList;
});