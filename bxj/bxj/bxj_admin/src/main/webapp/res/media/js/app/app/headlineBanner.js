define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "头条banner列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/app/queryHeadlineBannerList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "标题",
                    	width:100,
                    	fieldName: "title"		
                	},
                	{
                    	name: "图片",
                    	width:100,
                    	fieldName: "fileUrl",
                    	renderer : function(row, col, data,value) {
                    		return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="200px" height="150px"></img></a>';
                    	}
                	},
                    {
                    	name: "页面url",
                    	width:80,
                    	fieldName: "pageUrl",
                    	renderer : function(row, col, data,value) {
                        	return '<a href="+' + value + '">' + value + '</a>';
                        }
                    },
                    {
                    	name: "是否显示",
                    	width:80,
                    	fieldName: "isDisplay",
                    	renderer : function(row, col, data,value) {
                    		// 显示
                    		if(value == 1){
                    			return "<span class='label label-table label-success'>显示<i></i></span>";
                    		} else {
                    			return "<span class='label label-table label-danger'>不显示<i></i></span>";
                    		}
                        }
                    },
                    {
                        name: "排序",
                        width:80,
                        fieldName: "sort",
                        renderer : function(row, col, data,value) {
                        	var st = '<br><button class="btn btn-primary toUp">向上</button><br><button class="btn btn-primary toDown">向下</button>';
                        	return value + st;
                        }
                    },
                    {
                        name: "上线时间",
                        width:80,
                        fieldName: "onlineTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "下线时间",
                        width:80,
                        fieldName: "offlineTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	if(data.isDisplay == 1){
                        		var displayButton = '<button class="btn btn-primary downBanner">下架</button>';
                        	} else {
                        		var displayButton = '<button class="btn btn-info upBanner">上架</button>';
                        	}
                        	var st = displayButton + 
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
                    handler: function (idx, data) {
                        thiz.addBanner();
                    }
                }],
                classEvents : [{
	            	className : "downBanner",
	            	handler: function (idx, data) {
	                    thiz.downBanner(data);
	                }
                },
                {
	            	className : "upBanner",
	            	handler: function (idx, data) {
	                    thiz.upBanner(data);
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
                {
	            	className : "toUp",
	            	handler: function (idx, data) {
	                    thiz.toUp(idx,data);
	                }
                },
                {
	            	className : "toDown",
	            	handler: function (idx, data) {
	                    thiz.toDown(idx,data);
	                }
                },
                ]
            });
            this.list = list;
        },
        
        // 下架
        downBanner: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要下架?",
                url: serverHost + "/app/updateBanner.json",
                params: {
                	id: id,
                	isDisplay: 2
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("更新成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error("此记录已不存在");
                    	thiz.list.reloadPageData();
                    }
                }
            });
        	
        },
        // 上架
        upBanner: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要上架?",
                url: serverHost + "/app/updateBanner.json",
                params: {
                	id: id,
                	isDisplay: 1
                },
                success: function (resp) {
                	if(resp.success) {
                    	messageTool.success("更新成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error("此记录已不存在");
                    	thiz.list.reloadPageData();
                    }
                }
            });
        	
        
        },
        // 删除
        delete1: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/app/updateBanner.json",
                params: {
                	id: id,
                	status: 0
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("删除成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error("此记录已不存在");
                		thiz.list.reloadPageData();
                    }
                }
            });
        },
        // 新增
        addBanner: function(){
        	var thiz = this;
        	var win = winTool.create({
                title: "新增banner",
                height: 800,
                width: 800,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".headline-banner-tpl",
                okFn: function (win) {
                	var title = win.find("#title").val(),
                		imageUrl = win.find(".picUrl").data("url"),
                		pageUrl = win.find(".pageUrl").val(),
                		isDisplay = win.find(".isDisplay").val(),
                		sort = win.find("#sort").val(),
                		onlineTime = win.find(".onlineTime").val(),
//                		offlineTime = win.find(".offlineTime").val()
                		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "2030-12-31" : offlineTime = win.find(".offlineTime").val()
                	var flag = true;
                	if(!title){
                		messageTool.error("必须输入banner标题!");
                		flag = false;
                	} else {
                		if(title.length > 18){
                    		messageTool.error("banner标题不能超过18个字!");
                    		flag = false;
                    	}
                	}
                	if(!imageUrl){
                		messageTool.error("至少上传一张图片!");
                		flag = false;
                	}
                	if(!pageUrl){
                		messageTool.error("必须输入跳转页面url!");
                		flag = false;
                	}
                	if(!isDisplay){
                		messageTool.error("必须选择是否上架!");
                		flag = false;
                	}
                	if(!sort){
                		messageTool.error("必须输入排序!");
                		flag = false;
                	}
                	if(!onlineTime){
                		messageTool.error("必须选择上架时间!");
                		flag = false;
                	}
                	if(!offlineTime){
                		messageTool.error("必须选择下架时间!");
                		flag = false;
                	}
                	if(!!onlineTime && !!offlineTime){
                		if(onlineTime > offlineTime){
                			messageTool.error("下架时间必须大于等于上架时间!");
                    		flag = false;
                		}
                	}
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/app/addBanner.json",
                            params: {
                            	title: title,
                            	imageUrl: imageUrl,
                            	pageUrl: pageUrl,
                            	isDisplay: isDisplay,
                            	sort: sort,
                            	onlineTime: onlineTime,
                            	offlineTime: offlineTime
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	messageTool.success("新增成功");
                                	win.close();
                                	thiz.list.reloadPageData();
                                } else {
                                	messageTool.error("新增失败");
                                }
                            }
                        });
                	}
                }
             });
        	
        	//时间渲染
        	dateTool.renderDate(".onlineTime", {
        		startDate : new Date(),
        		todayBtn : true
			});
       	 	dateTool.renderDate(".offlineTime", {
	     		startDate : new Date(),
	     		todayBtn : true
			});
       	 	thiz.rendenWin(win);

        },
        
        // 向上
        toUp : function(idx,data){
        	if(idx == 0){
        		messageTool.success("该banner已经排在第一位!");
        	} else {
            	var thiz = this,
	        	 	id = data.id,
	        	 	sort = data.sort;
	        	util.ajax({
	                url: serverHost + "/app/upOrDownBanner.json",
	                params: {
	                	id : id,
	                	upOrDown : 1,
	                	sort : sort
	                },
	                success: function (resp) {
	                    if(resp.success) {
	                    	messageTool.success("更新成功");
	                    	thiz.list.reloadPageData();
	                    } else {
	                    	messageTool.error("更新失败");
	                    }
	                }
	            });
        	}
        },
        // 向下
        toDown : function(idx,data){
        	var thiz = this,
    	 		id = data.id,
    	 		sort = data.sort;
        	if(idx == (thiz.list.getTotalCount() - 1)){
        		messageTool.error("该banner已经排在最后位!");
        	} else {
            	util.ajax({
                    url: serverHost + "/app/upOrDownBanner.json",
                    params: {
                    	id : id,
                    	upOrDown : 2,
                    	sort : sort
                    },
                    success: function (resp) {
                        if(resp.success) {
                        	messageTool.success("更新成功");
                        	thiz.list.reloadPageData();
                        } else {
                        	messageTool.error("更新失败");
                        }
                    }
                });
        	}
        },
        
        // 修改
        update : function(data){
        	var thiz = this,
        		id = data.id;
        	util.ajax({
                url: serverHost + "/app/queryBanner.json",
                params: {
                	id : id
                },
                success: function (resp) {
                    if(resp.success) {
                    	var banner = resp.model;
                    	
                    	var win = winTool.create({
                            title: "修改banner",
                            height: 800,
                            width: 800,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".headline-banner-tpl",
                            okFn: function (win) {
                            	var title = win.find("#title").val(),
                            		imageUrl = win.find(".picUrl").data("url"),
                            		pageUrl = win.find(".pageUrl").val(),
                            		isDisplay = win.find(".isDisplay").val(),
                            		sort = win.find("#sort").val(),
                            		onlineTime = win.find(".onlineTime").val(),
                            		offlineTime = win.find(".offlineTime").val()
//                            		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "" : offlineTime = win.find(".offlineTime").val()
                            	var flag = true;
                            	if(!title){
                            		messageTool.error("必须输入banner标题!");
                            		flag = false;
                            	} else {
                            		if(title.length > 18){
                                		messageTool.error("banner标题不能超过18个字!");
                                		flag = false;
                                	}
                            	}
                            	if(!imageUrl){
                            		messageTool.error("至少上传一张图片!");
                            		flag = false;
                            	}
                            	if(!pageUrl){
                            		messageTool.error("必须输入跳转页面url!");
                            		flag = false;
                            	}
                            	if(!isDisplay){
                            		messageTool.error("必须选择是否上架!");
                            		flag = false;
                            	}
                            	if(!sort){
                            		messageTool.error("必须输入排序!");
                            		flag = false;
                            	}
                            	if(!onlineTime){
                            		messageTool.error("必须选择上架时间!");
                            		flag = false;
                            	}
                            	if(!offlineTime){                     
                            		messageTool.error("必须选择下架时间!");
                            		flag = false;
                            	}
                            	if(!!onlineTime && !!offlineTime){
                            		if(onlineTime > offlineTime){
                            			messageTool.error("下架时间必须大于等于上架时间!");
                                		flag = false;
                            		}
                            	}
                            	if(flag){
                                	util.request({
                                		confirm: true,
                                        msg: "确定要修改?",
                                        url: serverHost + "/app/updateBanner.json",
                                        params: {
                                        	id: id,
                                        	title: title,
                                        	imageUrl: imageUrl,
                                        	pageUrl: pageUrl,
                                        	isDisplay: isDisplay,
                                        	sort: sort,
                                        	onlineTime: onlineTime,
                                        	offlineTime: offlineTime
                                        },
                                        success: function (resp) {
                                            if(resp.success) {
                                            	messageTool.success("更新成功");
                                            	win.close();
                                            	thiz.list.reloadPageData();
                                            } else {
                                            	messageTool.error("更新失败");
                                            }
                                        }
                                    });
                            	}
                            }
                         });
                    	thiz.rendenWin(win);
                    	//时间渲染
                    	dateTool.renderDate(".onlineTime", {
                    		startDate : new Date(),
                    		todayBtn : true
            			});
                   	 	dateTool.renderDate(".offlineTime", {
            	     		startDate : new Date(),
            	     		todayBtn : true
            			});
                   	 	
                    	win.find("#title").val(banner.title);
                		win.find(".picUrl").data("url",banner.imageUrl);
                		win.find(".picUrl").attr("src",banner.fileUrl);
                		win.find(".pageUrl").val(banner.pageUrl);
                		win.find(".isDisplay").val(banner.isDisplay);
                		win.find("#sort").val(banner.sort);
                		win.find(".onlineTime").val(util.dateFormat2(banner.onlineTime, 'YYYY-MM-DD'));
                		win.find(".offlineTime").val(util.dateFormat2(banner.offlineTime, 'YYYY-MM-DD'));
                    } else {
                    	messageTool.error("此banner已不存在!");
                    }
                }
            });
        },
        
        rendenWin : function(win){
       	 	//checkbox
	   	 	win.find(".timeBox").on("click", function(){
	       	 	if($(this).is(':checked')){
	    			win.find(".timeFlag").css("display","none");
	    		} else {
	    			win.find(".timeFlag").css("display","block");
	    		}
	   	 	});
       	 	//数字框
        	win.find("#sort").on('keyup', function (event) {
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        		return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
    		});
        	
			// 上传图片
			win.find(".infos-images").on("click", function() {
				imageUploadTool.init({
					title : "上传图片",
					attach : 1,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1,
					extData: {
                        size: "750x250"
                    },
					callback : function(images) {
						var img = images.length > 0 ? images[0] : {};
						$(".picUrl").attr("src", img.fileUrl);
						$(".picUrl").data("url", img.fileName);
					}
				});
			});
			win.find(".uploadImg").on("click", ".remove-img", function() {
				$(".picUrl").attr("src", "");
				$(".picUrl").data("url", "");
			});
        }
    };
    return userList;
});