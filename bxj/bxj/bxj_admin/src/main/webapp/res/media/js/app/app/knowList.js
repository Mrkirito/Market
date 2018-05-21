define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "学习知识展示页",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/studyapp/konwlist.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "标题",
                    	width:200,
                    	fieldName: "text"		
                	},
                	{
                    	name: "图片",
                    	width:100,
                    	fieldName: "imageURL",
                    	renderer : function(row, col, data,value) {
                    		return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="200px" height="150px"></img></a>';
                    	}
                	},                	 
                	{
                		name: "优先级(数字越大在前)",
                     	width:200,
                     	fieldName: "weight"
                		 
                	},
                    {
                    	name: "页面URL(noSet表示未上线,上线需去关联学习详情)",
                    	width:80,
                    	fieldName: "location",
                    	renderer : function(row, col, data,value) {
                        	return '<a href="+' + value + '">' + value + '</a>';
                        }
                    },
                    
                    {
                        name: "标签内容",
                        width:50,
                        fieldName: "tags"
                    },
                  
                   /* {
                    	name: "是否显示",
                    	width:80,
                    	fieldName: "isDisplay",
                    	renderer : function(row, col, data,value) {
                    		// 显示
                    		if(value == 1){
                    			return "<span class='label bg-green'>显示<i></i></span>";
                    		} else {
                    			return "<span class='label bg-red'>不显示<i></i></span>";
                    		}
                        }
                    },*/
                   /* {
                        name: "排序",
                        width:80,
                        fieldName: "sort",
                        renderer : function(row, col, data,value) {
                        	var st = '<br><button class="btn btn-primary toUp">向上</button><br><button class="btn btn-primary toDown">向下</button>';
                        	return value + st;
                        }
                    },*/
                  /*  {
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
                    },*/
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	/*if(data.isDisplay == 1){
                        		var displayButton = '<button class="btn btn-primary downBanner">下架</button>';
                        	} else {
                        		var displayButton = '<button class="btn btn-info upBanner">上架</button>';
                        	}*/
                        	// displayButton + 
                        	var st ='<button class="btn btn-danger delete">删除</button>' +'<button class="btn btn-warning update">修改</button>'
                        	return st;
                        }
                    }
                	],
            	paramFn: function () {
                	return thiz.getParams();
                },
               
                initSearch: function (query) {
                	
                },
                tbars: [{
                    icon: 'fa fa-plus',
                    name: "新增",
//                    rightCode: "add",
                    handler: function (idx, data) {
                        thiz.addKnow();
                    }
                }],
                classEvents : [
                 /*  {
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
                },*/
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
        	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 下架
        /*downBanner: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要下架?",
                url: serverHost + "/studyapp/updateKnow.json",
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
        	
        },*/
        // 上架
       /* upBanner: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要上架?",
                url: serverHost + "/studyapp/updateKnow.json",
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
        },*/
        
        // 删除
        delete1: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/studyapp/updateKnow.json",
                params: {
                	id: id,
                	status: 1
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("删除成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error("此记录已不存在!");
                		thiz.list.reloadPageData();
                    }
                }
            });
        },
        // 新增
        addKnow: function(){
        	var thiz = this;
        	var win = winTool.create({
                title: "新增知识",
                height: 800,
                width: 800,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".headline-banner-tpl",
                okFn: function (win) {
//                	title = win.find("#title").val(),
                	var imageUrl = win.find(".picUrl").data("url"),
//                		location = win.find(".location").val(),
                		text= win.find(".text").val(),
                		tags = win.find(".tags").val(),
                		weight= win.find(".weight").val()
//                		isDisplay = win.find(".isDisplay").val(),
//                		sort = win.find("#sort").val(),
//                		onlineTime = win.find(".onlineTime").val(),
//                		offlineTime = win.find(".offlineTime").val()
//                		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "2030-12-31" : offlineTime = win.find(".offlineTime").val()
                	var flag = true;
                	/*if(!title){
                		messageTool.error("必须输入知识标题!");
                		flag = false;
                	} else {
                		if(title.length > 10){
                    		messageTool.error("知识标题不能超过10个字!");
                    		flag = false;
                    	}
                	}*/
                	if(!text){
                		messageTool.error("必须输入知识内容!");
                		flag = false;
                	}else if(text.length > 20){
                		messageTool.error("知识内容不能超过20个字!");
                		flag = false;
                	}
                	if(!tags){
                		messageTool.error("必须输入标签内容!");
                		flag = false;
                	}else if(tags.length > 50){
        				messageTool.error("标签内容不能超过50个字!");
                		flag = false;
        			}
                	if(!imageUrl){
                		messageTool.error("至少上传一张图片!");
                		flag = false;
                	}
                	if(!weight){
                		messageTool.error("请输入优先级!(数字)");
                		flag = false;
                	}else if(isNaN(weight)){
                	   messageTool.error("优先级请输入数字!");
            		   flag = false;
            	    }
                	/*if(!location){
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
                	}*/
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/studyapp/addKnow.json",
                            params: {
//                            	title: title,
                            	imageURL: imageUrl,
                            	text:text,
                            	weight:weight,
                            	tags: tags,
                            	location: "noSet"
//                            	isDisplay: isDisplay,
//                            	sort: sort,
//                            	onlineTime: onlineTime,
//                            	offlineTime: offlineTime
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
        	/*dateTool.renderDate(".onlineTime", {
        		startDate : new Date(),
        		todayBtn : true
			});
       	 	dateTool.renderDate(".offlineTime", {
	     		startDate : new Date(),
	     		todayBtn : true
			});*/
       	 	thiz.rendenWin(win);

        },
        
        // 向上
       /* toUp : function(idx,data){
        	if(idx == 0){
        		messageTool.success("该banner已经排在第一位!");
        	} else {
            	var thiz = this,
	        	 	id = data.id,
	        	 	sort = data.sort;
	        	util.ajax({
	                url: serverHost + "/studyapp/upOrDownBanner.json",
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
                    url: serverHost + "/studyapp/upOrDownBanner.json",
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
        },*/
        
        // 修改
        update : function(data){
        	var thiz = this,
        		id = data.id;
        	util.ajax({
                url: serverHost + "/studyapp/selOneknow.json",
                params: {
                	id : id
                },
                success: function (resp) {
                    if(resp.success) {
                    	var banner = resp.model;
                    	
                    	var win = winTool.create({
                            title: "修改知识",
                            height: 800,
                            width: 800,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".headline-banner-tpl",
                            okFn: function (win) {
                            	//title = win.find("#title").val(),
                            	var	imageUrl = win.find(".picUrl").data("url"),
//                            		location = win.find(".location").val(),
                            		text =win.find(".text").val(),
                            		tags = win.find(".tags").val(),
                            		weight= win.find(".weight").val()
                            		
//                            		isDisplay = win.find(".isDisplay").val(),
//                            		sort = win.find("#sort").val(),
//                            		onlineTime = win.find(".onlineTime").val(),
//                            		offlineTime = win.find(".offlineTime").val()
//                            		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "" : offlineTime = win.find(".offlineTime").val()
                            	var flag = true;
                            	/*if(!title){
                            		messageTool.error("必须输入知识标题!");
                            		flag = false;
                            	} else {
                            		if(title.length > 10){
                                		messageTool.error("知识标题不能超过10个字!");
                                		flag = false;
                                	}
                            	}*/
                            	if(!text){
                            		messageTool.error("必须输入知识内容!");
                            		flag = false;
                            	} else if(text.length > 20){
                            		messageTool.error("知识内容不能超过20个字!");
                            		flag = false;
                            	}
                            	if(!tags){
                            		messageTool.error("必须输入标签内容!");
                            		flag = false;
                            	} else if(tags.length>50){
                            		messageTool.error("标签内容不能超过50个字!");
                            		flag = false;
                            	} 
                            	if(!imageUrl){
                            		messageTool.error("至少上传一张图片!");
                            		flag = false;
                            	}
                            	if(!weight){
                            		messageTool.error("请输入优先级!(数字)");
                            		flag = false;
                            	}else if(isNaN(weight)){
                            		   messageTool.error("优先级请输入数字!");
                            		   flag = false;
                            	}
                            	/*if(!location){
                            		messageTool.error("必须输入跳转页面url!");
                            		flag = false;
                            	}*/
                            	/*if(!isDisplay){
                            		messageTool.error("必须选择是否上架!");
                            		flag = false;
                            	}*/
                            	/*if(!sort){
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
                            	}*/
                            	if(flag){
                                	util.request({
                                		confirm: true,
                                        msg: "确定要修改?",
                                        url: serverHost + "/studyapp/updateKnow.json",
                                        params: {
                                        	id: id,
//                                        	title: title,
                                        	imageURL: imageUrl,
//                                        	location: location,
                                        	text:text,
                                        	tags:tags,
                                        	weight:weight
                                        	
                                        	//isDisplay: isDisplay,
                                        	//sort: sort
                                        	//offlineTime: offlineTime
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
                    	/*dateTool.renderDate(".onlineTime", {
                    		startDate : new Date(),
                    		todayBtn : true
            			});
                   	 	dateTool.renderDate(".offlineTime", {
            	     		startDate : new Date(),
            	     		todayBtn : true
            			});*/
                   	 	
//                    	win.find("#title").val(banner.title);
                		win.find(".picUrl").data("url",banner.imageURL);
                		win.find(".picUrl").attr("src",banner.fileUrl);
//                		win.find(".location").val(banner.location);
                		win.find(".text").val(banner.text);
                		win.find(".tags").val(banner.tags);
                		win.find(".weight").val(banner.weight);
                		//win.find(".isDisplay").val(banner.isDisplay);
//                		win.find("#sort").val(banner.sort);
                		//win.find(".onlineTime").val(util.dateFormat2(banner.onlineTime, 'YYYY-MM-DD'));
                		//win.find(".offlineTime").val(util.dateFormat2(banner.offlineTime, 'YYYY-MM-DD'));
                    } else {
                    	messageTool.error("此知识条已不存在!");
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
        	/*win.find("#sort").on('keyup', function (event) {
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        		return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
    		});*/
        	
			// 上传图片
			win.find(".infos-images").on("click", function() {
				imageUploadTool.init({
					title : "上传图片",
					attach : 2,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1,
					extData: {
                        size: "238x142" // 238x142
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
        },
        
        getParams: function () {
            return {
            	text: $(".lookText").val(),
            	 
//            	onlineTime: $(".sOnlineTime").val(),
//            	offlineTime: $(".sOfflineTime").val(),
            };
        }
    };
    return userList;
});