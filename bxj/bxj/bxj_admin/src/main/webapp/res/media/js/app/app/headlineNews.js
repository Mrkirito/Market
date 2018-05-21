define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "头条列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/app/queryHeadlineNewsList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "新闻标题",
                    	width:80,
                    	fieldName: "title"		
                	},
                	{
                    	name: "新闻图片url",
                    	width:100,
                    	fieldName: "fileUrl",
                    	renderer : function(row, col, data,value) {
                    		return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="200px" height="150px"></img></a>';
                    	}
                	},
                    {
                    	name: "新闻内容",
                    	width:80,
                    	fieldName: "newsContent",
                    	renderer : function(row, col, data,value) {
                    		return '<button class="btn btn-info newsDetail">查看新闻内容</button>';
                    	}
                    },
                    {
                    	name: "审核状态",
                    	width:80,
                    	fieldName: "auditStatus",
                    	renderer : function(row, col, data,value) {
                    		// 待审核
                    		if(value == 0){
                    			return "<span class='label label-table label-info'>待审核<i></i></span>";
                    		// 未通过
                    		} else if(value == 1) {
                    			return "<span class='label label-table label-danger'>未通过<i></i></span>";
                    		// 通过
                    		} else if(value == 2){
                    			return "<span class='label label-table label-success'>通过<i></i></span>";
                    		}
                        }
                    },
                    {
                        name: "发布时间",
                        width:120,
                        fieldName: "onlineTime",
					    sort: true,
					    sortName:"onlineTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "更新时间",
                        width:120,
                        fieldName: "updateTime",
                        sort: true,
					    sortName:"updateTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "是否显示标签",
                        width:50,
                        fieldName: "isDisplayTag",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label label-table label-success'>显示<i></i></span>";
                        	} else {
                        		return "<span class='label label-table label-danger'>不显示<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "标签内容",
                        width:50,
                        fieldName: "tagContent"
                    },
                    {
                        name: "点击次数",
                        width:80,
                        fieldName: "hitCount"
                    },
                    {
                        name: "虚拟点击次数",
                        width:80,
                        fieldName: "virtualHitCount"
                    },
                    {
                        name: "是否显示转载",
                        width:50,
                        fieldName: "isDisplayReprint",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label label-table label-success'>显示<i></i></span>";
                        	} else {
                        		return "<span class='label label-table label-danger'>不显示<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "新闻类型",
                        width:80,
                        fieldName: "newsType",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label label-table label-success'>普通新闻<i></i></span>";
                        	} else if(value == 2){
                        		return "<span class='label label-table label-danger'>置顶新闻<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "置顶新闻排序",
                        width:50,
                        fieldName: "sort",
                        renderer : function(row, col, data,value) {
                        	var st = '<br><button class="btn btn-primary toUp">向上</button><br><button class="btn btn-primary toDown">向下</button>';
                        	if(data.newsType == 1){
                        		return value;
                        	} else {
                        		return value + st;
                        	}
                        }
                    },
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	if(data.auditStatus == 1){
                        		var auditButton = '<button class="btn btn-info passNews">审核通过</button>';
                        	} else if(data.auditStatus == 2){
                        		var auditButton = '<button class="btn btn-danger failedNews">审核不通过</button>';
                        	} else {
                        		var auditButton = '<button class="btn btn-info passNews">审核通过</button>' +
                        						  '<button class="btn btn-danger failedNews">审核不通过</button>';
                        	}
                        	if(data.newsType == 1){
                        		var typeButton = '<button class="btn btn-info upNews">置顶新闻</button>';
                        	} else {
                        		var typeButton = '<button class="btn btn-primary downNews">普通新闻</button>';
                        	}
                        	var st = auditButton + typeButton +
                        			 '<button class="btn btn-danger delete">删除</button>' +
                        			 '<button class="btn btn-warning update">修改</button>'
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
                        thiz.addNews();
                    }
                }],
                classEvents : [{
	            	className : "passNews",
	            	handler: function (idx, data) {
	                    thiz.passNews(data);
	                }
                },
                {
	            	className : "failedNews",
	            	handler: function (idx, data) {
	                    thiz.failedNews(data);
	                }
                },
                {
	            	className : "upNews",
	            	handler: function (idx, data) {
	                    thiz.upNews(data);
	                }
                },
                {
	            	className : "downNews",
	            	handler: function (idx, data) {
	                    thiz.downNews(data);
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
                {
	            	className : "newsDetail",
	            	handler: function (idx, data) {
	                    thiz.newsDetail(data);
	                }
                },
                ]
            });
            this.list = list;
        	//时间渲染
        	dateTool.renderDate(".sOnlineTime");
       	 	dateTool.renderDate(".sOfflineTime");
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 查看新闻内容
        newsDetail: function(data){
        	var win = winTool.create({
                title: "新闻内容",
                height: 800,
                width: 800,
                showCancel: false,
                okName: "关闭",
                type: "selector",
                selector: ".news-detail",
                okFn: function (win) {
                	win.close();
                }
             });
//        	win.edit = editorTool.init({
//        		selector: win.find(".newsDetail"),
//        		width: 800,
//        		heigth: 700,
//        		minHeight: 700,
//        		lang: 'en-US'
//        	});
//        	
//        	win.edit.setValue(data.newsContent);
        	win.find(".newsDetail").html(data.newsContent);
        },
        // 审核通过
        passNews: function(data){
        	var thiz = this;
        	var id = data.id;
        	if(data.offlineTime, 'YYYY/MM/DD' < new Date().getTime()){
        		messageTool.error("当前头条的下线时间小于当天时间,请先修改下线时间!");
        		return;
        	}
        	util.request({
        		confirm: true,
                msg: "确定要审核通过?",
                url: serverHost + "/app/updateNews.json",
                params: {
                	id: id,
                	auditStatus: 2
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
        // 审核不通过
        failedNews: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定审核不通过?",
                url: serverHost + "/app/updateNews.json",
                params: {
                	id: id,
                	auditStatus: 1
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
        
        // 置顶新闻
        upNews: function(data){
        	var thiz = this;
        	var id = data.id;
        	if(data.auditStatus != 2){
        		messageTool.error("该头条未审核通过,请先审核");
        		return;
        	} 
        	util.request({
        		confirm: true,
                msg: "确定要置顶新闻?",
                url: serverHost + "/app/updateNews.json",
                params: {
                	id: id,
                	newsType: 2,
                	sort: 0
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
        
        // 普通新闻
        downNews: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要更新成普通新闻?",
                url: serverHost + "/app/updateNews.json",
                params: {
                	id: id,
                	newsType: 1
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
                url: serverHost + "/app/updateNews.json",
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
        addNews: function(){
        	var thiz = this;
        	var win = winTool.create({
                title: "新增News",
                height: 800,
                width: 1500,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".headline-news-tpl",
                okFn: function (win) {
                	var title = win.find("#title").val(),
                		imageUrl = win.find(".picUrl").data("url"),
                		isDisplayTag = win.find(".isDisplayTag").val(),
                		newsContent = win.edit.getValue();
                		tagContent = win.find(".isDisplayTag").val() == 1 ? win.find(".tagContent").val() : "",
        				isDisplayReprint = win.find(".isDisplayReprint").val(),
        				reprintContent = win.find(".isDisplayReprint").val() == 1 ? win.find(".reprintContent").val() : "",
						virtualHitCount = win.find(".virtualHitCount").val();
                		onlineTime = win.find(".onlineTime").val(),
                		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "2030-12-31 00:00:00" : offlineTime = win.find(".offlineTime").val()
                	var flag = true;
                	if(!title){
                		messageTool.error("必须输入News标题!");
                		flag = false;
                	} else {
                		if(title.length > 18){
                    		messageTool.error("News标题不能超过18个字!");
                    		flag = false;
                    	}
                	}
                	if(!isDisplayTag){
                		messageTool.error("必须选择是否显示标签!");
                		flag = false;
                	} else if(isDisplayTag == 1){
                		if(!tagContent){
                			messageTool.error("必须输入标签内容!");
                    		flag = false;
                		} else {
                			if(tagContent.length > 2){
                				messageTool.error("标签内容不能超过2个字!");
                        		flag = false;
                			}
                		}
                	}
                	if(!isDisplayReprint){
                		messageTool.error("必须选择是否转载!");
                		flag = false;
                	} else if(isDisplayReprint == 1){
                		if(!reprintContent){
                			messageTool.error("必须选择是否转载!");
                    		flag = false;
                		}
                	}
                	if(!virtualHitCount){
                		messageTool.error("必须输入虚拟点击次数!");
                		flag = false;
                	}
                	if(!onlineTime){
                		messageTool.error("必须选择发布时间!");
                		flag = false;
                	}
                	if(!offlineTime){
                		messageTool.error("必须选择下线时间!");
                		flag = false;
                	}
                	if(!!onlineTime && !!offlineTime){
                		if(onlineTime > offlineTime){
                			messageTool.error("下线时间必须大于等于发布时间!");
                    		flag = false;
                		}
                	}
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/app/addNews.json",
                            params: {
                            	title: title,
                            	imgUrl: imageUrl,
                            	newsContent: newsContent,
                            	isDisplayTag: isDisplayTag,
                            	tagContent: tagContent,
                            	isDisplayReprint: isDisplayReprint,
                            	reprintContent: reprintContent,
                            	virtualHitCount: virtualHitCount,
                            	onlineTime: onlineTime,
                            	offlineTime: offlineTime,
                            	newsType: 1,
                            	auditStatus: 0
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
        	dateTool.renderDateTime(".onlineTime");
       	 	dateTool.renderDateTime(".offlineTime", {
	     		startDate : new Date()
			});
       	 	thiz.rendenWin(win);

        },
        
        // 向上
        toUp : function(idx,data){
        	if(idx == 0){
        		messageTool.success("该News已经排在第一位!");
        	} else {
            	var thiz = this,
	        	 	id = data.id,
	        	 	sort = data.sort;
	        	util.ajax({
	                url: serverHost + "/app/upOrDownNews.json",
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
        	util.ajax({
                url: serverHost + "/app/upOrDownNews.json",
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
                    	messageTool.error(resp.msg || "更新失败");
                    }
                }
            });
        },
        
        // 修改
        update : function(data){
        	var thiz = this,
        		id = data.id;
        	util.ajax({
                url: serverHost + "/app/queryNews.json",
                params: {
                	id : id
                },
                success: function (resp) {
                    if(resp.success) {
                    	var news = resp.model;
                    	
                    	var win = winTool.create({
                            title: "修改News",
                            height: 800,
                            width: 1500,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".headline-news-tpl",
                            okFn: function (win) {
                            	var title = win.find("#title").val(),
                        		imageUrl = win.find(".picUrl").data("url"),
                        		newsContent = win.edit.getValue();
                        		isDisplayTag = win.find(".isDisplayTag").val(),
                        		tagContent = win.find(".isDisplayTag").val() == 1 ? win.find(".tagContent").val() : "",
                				isDisplayReprint = win.find(".isDisplayReprint").val(),
                				reprintContent = win.find(".isDisplayReprint").val() == 1 ? win.find(".reprintContent").val() : "",
        						virtualHitCount = win.find(".virtualHitCount").val();
                        		onlineTime = win.find(".onlineTime").val(),
                        		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "2030-12-31 00:00:00" : offlineTime = win.find(".offlineTime").val()
                        	var flag = true;
                        	console.info(newsContent);
                        	if(!title){
                        		messageTool.error("必须输入News标题!");
                        		flag = false;
                        	} else {
                        		if(title.length > 18){
                            		messageTool.error("News标题不能超过18个字!");
                            		flag = false;
                            	}
                        	}
                        	if(!isDisplayTag){
                        		messageTool.error("必须选择是否显示标签!");
                        		flag = false;
                        	} else if(isDisplayTag == 1){
                        		if(!tagContent){
                        			messageTool.error("必须输入标签内容!");
                            		flag = false;
                        		} else {
                        			if(tagContent.length > 2){
                        				messageTool.error("标签内容不能超过2个字!");
                                		flag = false;
                        			}
                        		}
                        	}
                        	if(!isDisplayReprint){
                        		messageTool.error("必须选择是否转载!");
                        		flag = false;
                        	} else if(isDisplayReprint == 1){
                        		if(!reprintContent){
                        			messageTool.error("必须选择是否转载!");
                            		flag = false;
                        		}
                        	}
                        	if(!virtualHitCount){
                        		messageTool.error("必须输入虚拟点击次数!");
                        		flag = false;
                        	}
                        	if(!onlineTime){
                        		messageTool.error("必须选择发布时间!");
                        		flag = false;
                        	}
                        	if(!offlineTime){
                        		messageTool.error("必须选择下线时间!");
                        		flag = false;
                        	}
                        	if(!!onlineTime && !!offlineTime){
                        		if(onlineTime > offlineTime){
                        			messageTool.error("下线时间必须大于等于发布时间!");
                            		flag = false;
                        		}
                        	}
                        	if(flag){
                            	util.request({
                            		confirm: true,
                                    msg: "确定要保存?",
                                    url: serverHost + "/app/updateNews.json",
                                    params: {
                                    	id: id,
                                    	title: title,
                                    	imgUrl: imageUrl,
                                    	newsContent: newsContent,
                                    	isDisplayTag: isDisplayTag,
                                    	tagContent: tagContent,
                                    	isDisplayReprint: isDisplayReprint,
                                    	reprintContent: reprintContent,
                                    	virtualHitCount: virtualHitCount,
                                    	onlineTime: onlineTime,
                                    	offlineTime: offlineTime,
                                    	auditStatus: data.auditStatus
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
                    	dateTool.renderDateTime(".onlineTime");
                   	 	dateTool.renderDateTime(".offlineTime", {
            	     		startDate : new Date()
            			});
                   	 	
                    	win.find("#title").val(news.title);
                		win.find(".picUrl").data("url",news.imgUrl);
                		win.find(".picUrl").attr("src",news.fileUrl);
                		win.find(".isDisplayTag").val(news.isDisplayTag);
                		win.find(".tagContent").val(news.tagContent);
                		win.find(".isDisplayReprint").val(news.isDisplayReprint);
                		win.find(".reprintContent").val(news.reprintContent);
                		win.find(".virtualHitCount").val(news.virtualHitCount);
                		win.find(".onlineTime").val(util.dateFormat2(news.onlineTime, 'YYYY-MM-DD HH:mm:ss'));
                		win.find(".offlineTime").val(util.dateFormat2(news.offlineTime, 'YYYY-MM-DD HH:mm:ss'));
                		if(news.isDisplayTag == 1){
                			win.find(".tagType").css("display","block");
                		}
                		if(news.isDisplayReprint == 1){
                			win.find(".reprintType").css("display","block");
                		}
                		win.edit.setValue(news.newsContent);
                    } else {
                    	messageTool.error("此News已不存在!");
                    }
                }
            });
        },
        
        rendenWin : function(win){
        	win.edit = editorTool.init({
        		selector: win.find("#newsContent"),
        		width: 800,
        		heigth: 700,
        		minHeight: 700,
        		lang: 'en-US'
        	});
        	
       	 	//checkbox
	   	 	win.find(".timeBox").on("click", function(){
	       	 	if($(this).is(':checked')){
	    			win.find(".timeFlag").css("display","none");
	    		} else {
	    			win.find(".timeFlag").css("display","block");
	    		}
	   	 	});
       	 	//数字框
        	win.find(".virtualHitCount").on('keyup', function (event) {
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        		return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
    		});
        	
        	win.find(".tagType").css("display","none");
        	win.find(".reprintType").css("display","none");
        	// 是否显示标签
        	win.find(".isDisplayTag").on("click",function(){
        		var isDisplayTag = $(this).val();
        		if(isDisplayTag == 1){
        			win.find(".tagType").css("display","block");
        		} else if(isDisplayTag == 2){
        			win.find(".tagType").css("display","none");
        		}
        	});
        	
        	// 是否显示转载
        	win.find(".isDisplayReprint").on("click",function(){
        		var isDisplayReprint = $(this).val();
        		if(isDisplayReprint == 1){
        			win.find(".reprintType").css("display","block");
        		} else if(isDisplayReprint == 2){
        			win.find(".reprintType").css("display","none");
        		}
        	});
			// 上传图片
			win.find(".infos-images").on("click", function() {
				imageUploadTool.init({
					title : "上传图片",
					attach : 1,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1,
					extData: {
                        size: "220x170"
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
            	title: $(".stitle").val(),
            	auditStatus: $(".sAuditStatus").val(),
            	onlineTime: $(".sOnlineTime").val() != "" ?  $(".sOnlineTime").val() + " 23:59:59" : "",
            	offlineTime: $(".sOfflineTime").val() != "" ?  $(".sOfflineTime").val() + " 23:59:59" : "",
            };
        }
    };
    return userList;
});