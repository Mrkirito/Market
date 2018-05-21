define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool) {
	var currentShowType = undefined;
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "朋友圈神器分类信息",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/friendCateGory/queryCategoryList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "ID",
                    	width:60,
                    	fieldName: "fid"		
                    },
                    {
                    	name: "标题",
                    	width:120,
                    	fieldName: "title"		
                	},
					{
						name: "简写名称",
						width:120,
						fieldName: "simtitle"
					},
                	 {
                        name: "图片",
                        width:80,
                        fieldName: "filePath",
                    	renderer: function(row, col, data, value) {
                    		return '<a href="' + value + '" rel="lightbox"><img src="' + value + '"/></a>';
                    	}
                    },
                    {
                    	name: "文字",
                    	width:80,
                    	fieldName: "words"
                    	
                    },
                   /* {
                    	name: "状态",
                    	width:80,
                    	fieldName: "status",
                    	renderer : function(row, col, data,value) {
                    		// .上线；.下线
                    	    if(value == 1) {
                    			return "<span class='label label-table label-success'>上线<i></i></span>";
                    		// 下线
                    		} else if(value == 0){
                    			return "<span class='label label-table label-danger'>下线<i></i></span>";
                    		}
                        }
                    },*/
                    /*{
                        name: "发布时间",
                        width:120,
                        fieldName: "publishTime",
					    sort: true,
					    sortName:"publishTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },*/
                    {
                        name: "更新时间",
                        width:120,
                        fieldName: "modifyAt",
                        sort: true,
					    sortName:"modifyAt",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                   
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	/*if(data.status == 3){
                        		var auditButton = '<button class="btn btn-info passNews">上线</button>';
                        	} else if(data.status == 2){
                        		var auditButton = '<button class="btn btn-danger failedNews">下线</button>';
                        	} else {
                        		var auditButton = '<button class="btn btn-info passNews">上线</button>' +
                        						  '<button class="btn btn-danger failedNews">下线</button>';
                        	}*/
                        	// auditButton +
                        	var st = '<button class="btn btn-danger delete">删除</button>' +
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
                    name: "新增分类",
//                    rightCode: "add",
                    handler: function (idx, data) {
                        thiz.addTexts();
                    }
                } 
                ],
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
            this.list = list;
        	//时间渲染 搜索
        	dateTool.renderDate(".sOnlineTime");
//       	 	dateTool.renderDate(".sOfflineTime");
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 删除
        delete1: function(data){
        	var thiz = this;
        	var id = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/friendCateGory/deleteCategory.json",
                params: {
                	id: id
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("删除成功!");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error("此记录已不存在");
                		thiz.list.reloadPageData();
                    }
                }
            });
        },
        // 新增文章 
        addTexts: function(){
        	var thiz = this;
        	var win = winTool.create({
                title: "新增朋友圈分类",
                height: 700,
                width: 800,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".friend-circle-text",
                okFn: function (win) {
                	var title = win.find("#title").val(),
						simtitle = win.find("#simtitle").val(),
                		imageUrl = win.find(".picUrl").data("url"),
                	    words=win.find(".words").val()
                        	 
                	var flag = true;
                	if(!title){
                		messageTool.error("必须输入标题!");
                		flag = false;
                	} else {
                		if(title.length > 50){
                    		messageTool.error("标题不能超过50个字!");
                    		flag = false;
                    	}
                	}
					if(!simtitle){
						messageTool.error("必须输入简写名称!");
						flag = false;
					} else {
						if(simtitle.length > 10){
							messageTool.error("简写名称不能超过10个字!");
							flag = false;
						}
					}
                	
            		if(words && words.length > 1020){
                		messageTool.error("文字不能超过1020个字!");
                		flag = false;
                	}
                	  
                	if(!imageUrl){
                		messageTool.error("至少上传一张图片!");
                		flag = false;
                	}
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/friendCateGory/addCategory.json",
                            params: {
                            	title: title,
								simtitle: simtitle,
                            	pic: imageUrl,
                            	words: words
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
//        	dateTool.renderDate(".onlineTime");
       	 	thiz.rendenWin(win);
        },
        
        // 修改  
        update : function(data){
        	var thiz = this,
        		id = data.fid;
        	util.ajax({
                url: serverHost + "/friendCateGory/queryCateGory.json",
                params: {
                	id : id
                },
                success: function (resp) {
                    if(resp.success) {
                    	var news = resp.model;
                    	var win = winTool.create({
                            title: "修改朋友圈分类",
                            height: 700,
                            width: 1500,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".friend-circle-text",
                            okFn: function (win) {
                            	var title =null,
									simtitle=null,
                            		words=null,
                            		imageUrl=null;
                        	var flag = true;
                        	    //  设置 标题等值title =news.title;
                        		words=win.find(".words").val();
                            	title = win.find("#title").val();
								simtitle = win.find("#simtitle").val();
                        		// 图片 
                        		imageUrl = win.find(".picUrl").data("url");
                            	if(!title){
                            		messageTool.error("必须输入标题!");
                            		flag = false;
                            	} else {
                            		if(title.length > 50){
                                		messageTool.error("标题不能超过50个字!");
                                		flag = false;
                                	}
                            	}
								if(!simtitle){
									messageTool.error("必须输入简写名称!");
									flag = false;
								} else {
									if(simtitle.length > 10){
										messageTool.error("简写名称不能超过10个字!");
										flag = false;
									}
								}
                            	if(!imageUrl){
                            		messageTool.error("至少上传一张图片!");
                            		flag = false;
                            	}
	                        	if(words && words.length > 1020){
	                        		messageTool.error("文字不能超过1020个字!");
	                        		flag = false;
	                        	}
                        	
                        	if(flag){
                            	util.request({
                            		confirm: true,
                                    msg: "确定要保存?",
                                    url: serverHost + "/friendCateGory/updateCategory.json",
                                    params: {
                                    	fid: id,
                                    	title: title,
										simtitle: simtitle,
                                    	pic: imageUrl,
                                    	words:words
                                    	 
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
                    	
                		    win.find("#title").val(news.title),
							win.find("#simtitle").val(news.simtitle),
//                     		 win.find(".picUrl").data("url"),
                		    win.find(".words").val(news.words);
                     	    //图片
                			win.find(".picUrl").attr("src",news.filePath);	
                			win.find(".picUrl").data("url",news.pic);
                	 
                			//时间渲染 文章
                        	dateTool.renderDate(".onlineTime");
                		
                    } else {
                    	messageTool.error("此信息已不存在!");
                    }
                }
            });
        },
        
        rendenWin : function(win){
			// 上传图片   
			win.find(".infos-images").on("click", function() {
				imageUploadTool.init({
					title : "上传图片",
					attach : 5,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1, //图片个数 
					extData: {
                        //size: "100x100"
                    },
					callback : function(images) {
						var img = images.length > 0 ? images[0] : {};
						$(".picUrl").attr("src", img.fileUrl);
						$(".picUrl").data("url", img.fileName);
					}
				});
			});
			// 删除 图片 
			win.find(".uploadImg").on("click", ".remove-img", function() {
				$(".picUrl").attr("src", "");
				$(".picUrl").data("url", "");
			});
			
        },
        
        getParams: function () {
            return {
            	id:$(".sid").val(), //ID
            	title: $(".stitle").val(), //标题
//            	status: $(".sAuditStatus").val(), //状态 
//            	publishTime: $(".sOnlineTime").val() != "" ?  $(".sOnlineTime").val() + " 00:00:00" : "",
            };
        }
    };
    return userList;
});
 