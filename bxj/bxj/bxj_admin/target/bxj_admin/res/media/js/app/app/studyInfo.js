define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool'
        ,'app/common/commonTool','app/common/selectTool'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool,commonTool) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "学习详情列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/studyapp/studyList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "学习标题",
                    	width:80,
                    	fieldName: "title"		
                	},
                	/*{
                    	name: "学习图片",
                    	width:100,
                    	fieldName: "fileUrl",
                    	renderer : function(row, col, data,value) {
                    		return '<img src="' + value + '"></img>';
                    	}
                	},*/
                    {
                    	name: "学习内容",
                    	width:80,
                    	fieldName: "studyContent",
                    	renderer : function(row, col, data,value) {
                    		return '<button class="btn btn-info contentDetail">查看内容</button>';
                    	}
                    },
                    {
                    	name: "审核状态",
                    	width:80,
                    	fieldName: "auditStatus",
                    	renderer : function(row, col, data,value) {
                    		// 待审核
                    		if(value == 0){
                    			return "<span class='label bg-blue'>待审核<i></i></span>";
                    		// 未通过
                    		} else if(value == 1) {
                    			return "<span class='label bg-red'>未通过<i></i></span>";
                    		// 通过
                    		} else if(value == 2){
                    			return "<span class='label bg-green'>通过<i></i></span>";
                    		}
                        }
                    },
//                    {
//                        name: "发布时间",
//                        width:120,
//                        fieldName: "onlineTime",
//                        renderer : function(row, col, data,value) {
//                        	return util.dateFormat2(value, 'YYYY-MM-DD');
//                        }
//                    },
//                    {
//                        name: "下线时间",
//                        width:120,
//                        fieldName: "offlineTime",
//                        renderer : function(row, col, data,value) {
//                        	return util.dateFormat2(value, 'YYYY-MM-DD');
//                        }
//                    },
                    /*{
                        name: "是否显示标签",
                        width:50,
                        fieldName: "isDisplayTag",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label bg-green'>显示<i></i></span>";
                        	} else {
                        		return "<span class='label bg-red'>不显示<i></i></span>";
                        	}
                        }
                    },*/
                   /* {
                        name: "标签内容",
                        width:50,
                        fieldName: "tags"
                    },*/
//                    {
//                        name: "点击次数",
//                        width:80,
//                        fieldName: "hitCount"
//                    },
//                    {
//                        name: "虚拟点击次数",
//                        width:80,
//                        fieldName: "virtualHitCount"
//                    },
                   {
                        name: "是否显示转载",
                        width:50,
                        fieldName: "isDisplayReprint",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label bg-green'>显示<i></i></span>";
                        	} else {
                        		return "<span class='label bg-red'>不显示<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "转载说明内容",
                        width:80,
                        fieldName: "reprintContent"
                    },
                   /* {
                        name: "学习类型",
                        width:80,
                        fieldName: "studyType",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label bg-green'>普通学习详情<i></i></span>";
                        	} else if(value == 2){
                        		return "<span class='label bg-red'>置顶学习详情<i></i></span>";
                        	}
                        }
                    },*/
                    /*{
                        name: "置顶排序",
                        width:50,
                        fieldName: "sort",
                        renderer : function(row, col, data,value) {
                        	var st = '<br><button class="btn btn-primary toUp">向上</button><br><button class="btn btn-primary toDown">向下</button>';
                        	if(data.studyType == 1){
                        		return value;
                        	} else {
                        		return value + st;
                        	}
                        }
                    },*/
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	/*if(data.auditStatus == 1){
                        		var auditButton = '<button class="btn btn-info passNews">审核通过</button>';
                        	} else if(data.auditStatus == 2){
                        		var auditButton = '<button class="btn btn-danger failedNews">审核不通过</button>';
                        	} else {
                        		var auditButton = '<button class="btn btn-info passNews">审核通过</button>' +
                        						  '<button class="btn btn-danger failedNews">审核不通过</button>';
                        	}*/
                        	
                        	/*if(data.studyType == 1){
                        		var typeButton = '<button class="btn btn-info upNews">置顶</button>';
                        	} else {
                        		var typeButton = '<button class="btn btn-primary downNews">普通</button>';
                        	}*/
                        	
                        	//  typeButton + , auditButton +'<button class="btn btn-danger delete">删除</button>' +
                        	var st =  '<button class="btn btn-warning update">修改</button>'
                        			
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
               /* {
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
               /* {
	            	className : "toUp",
	            	handler: function (idx, data) {
	                    thiz.toUp(idx,data);
	                }
                },*/
               /* {
	            	className : "toDown",
	            	handler: function (idx, data) {
	                    thiz.toDown(idx,data);
	                }
                },*/
                {
	            	className : "contentDetail",
	            	handler: function (idx, data) {
	                    thiz.contentDetail(data);
	                }
                },
                ]
            });
            this.list = list;
        	//时间渲染
//        	dateTool.renderDate(".sOnlineTime");
//       	 	dateTool.renderDate(".sOfflineTime");
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 查看内容
        contentDetail: function(data){
        	var win = winTool.create({
                title: "学习内容",
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
        	/*win.edit = editorTool.init({
        		selector: win.find(".contentDetail"),
        		width: 800,
        		heigth: 700,
        		minHeight: 700,
        		lang: 'en-US'
        	});*/
        	//win.edit.setValue(data.studyContent);
        	win.find(".contentDetail").html(data.studyContent);
        },
        // 审核通过
        passNews: function(data){
        	var thiz = this;
        	var id = data.id;
//        	if(data.offlineTime, 'YYYY/MM/DD' < new Date().getTime()){
//        		messageTool.error("当前 的下线时间小于当天时间,请先修改下线时间!");
//        		return;
//        	}
        	util.request({
        		confirm: true,
                msg: "确定要审核通过?",
                url: serverHost + "/studyapp/updateStudyDetail.json",
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
                url: serverHost + "/studyapp/updateStudyDetail.json",
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
      /*  upNews: function(data){
        	var thiz = this;
        	var id = data.id;
        	if(data.auditStatus != 2){
        		messageTool.error("该 未审核通过,请先审核");
        		return;
        	} 
        	util.request({
        		confirm: true,
                msg: "确定要置顶?",
                url: serverHost + "/app/updateNews.json",
                params: {
                	id: id,
                	studyType: 2,
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
        	
        },*/
        
        // 普通新闻
       /* downNews: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要更新成普通?",
                url: serverHost + "/app/updateNews.json",
                params: {
                	id: id,
                	studyType: 1
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
                url: serverHost + "/studyapp/updateStudyDetail.json",
                params: {
                	id: id,
                	status:1
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
        	var inpTitle="";
        	var win = winTool.create({
                title: "新增学习详情",
                height: 800,
                width: 800,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".headline-news-tpl",
                okFn: function (win) {
//                	imageUrl = win.find(".picUrl").data("url"),
                	var knowID=$(".knowSel").select2("val"),
//                		isDisplayTag = win.find(".isDisplayTag").val(),
                		studyContent = win.edit.getValue();
//                		tagContent = win.find(".isDisplayTag").val() == 1 ? win.find(".tagContent").val() : "",
        				isDisplayReprint = win.find(".isDisplayReprint").val(),
        				reprintContent = win.find(".isDisplayReprint").val() == 1 ? win.find(".reprintContent").val() : ""
//	                	hitCount=win.find(".hitCount").val(),	
//	                	virtualHitCount = win.find(".virtualHitCount").val();
//                		onlineTime = win.find(".onlineTime").val(),
//                		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "2030-12-31" : offlineTime = win.find(".offlineTime").val()
                	var flag = true;
        				
        			if(!knowID){
            			messageTool.error("必须选择知识标题!");
                		flag = false;
            		}
                	if(!isDisplayReprint){
                		messageTool.error("必须选择是否转载!");
                		flag = false;
                	} else if(isDisplayReprint == 1){
                		if(!reprintContent){
                			messageTool.error("必须输入转载说明内容!");
                    		flag = false;
                		}
                	}
                	 
                	if(!studyContent ||"<p><br></p>"==studyContent){
            			messageTool.error("必须输入详情内容!");
                		flag = false;
            		}
                	/*if(!isDisplayTag){
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
                	}*/
                
//                	if(!virtualHitCount){
//                		messageTool.error("必须输入虚拟点击次数!");
//                		flag = false;
//                	}
//                	if(!hitCount){
//                		messageTool.error("必须输入点击次数!");
//                		flag = false;
//                	}
//                	if(!onlineTime){
//                		messageTool.error("必须选择发布时间!");
//                		flag = false;
//                	}
                	/*if(!offlineTime){
                		messageTool.error("必须选择下线时间!");
                		flag = false;
                	}*/
                	/*if(!!onlineTime && !!offlineTime){
                		if(onlineTime > offlineTime){
                			messageTool.error("下线时间必须大于等于发布时间!");
                    		flag = false;
                		}
                	}*/
                 
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/studyapp/addStudyDetail.json",
                            params: {
                            	title: inpTitle,
//                            	imgUrl: imageUrl,
                            	studyContent: studyContent,
                            	knowID:knowID,
//                            	isDisplayTag: isDisplayTag,
//                            	tagContent: tagContent,
                            	isDisplayReprint: isDisplayReprint,
                            	reprintContent: reprintContent
//                            	virtualHitCount: virtualHitCount,
//                            	onlineTime: onlineTime,
//                            	offlineTime: offlineTime,
//                            	studyType: 1,
//                            	auditStatus: 1
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
        		startDate : new Date()
			});
       	 	dateTool.renderDate(".offlineTime", {
	     		startDate : new Date()
			});*/
        	//渲染下拉框
         	commonTool.renderKnow({
        						msg : "选择对应知识标题",
        						selector : win.find(".knowSel"),
        						width : "60%",
        						paramFn : function() {
        							/*return {
        								permission: true
        							};*/
        						},
        						listeners : {
        							change : function() {
        								inpTitle=win.find(".knowSel").select2("data").text; //文本值 
        								//alert("改变下拉值we "+inpTitle);
        							}
        						}
        	}); 
       	 	thiz.rendenWin(win);
        },
        
        // 向上
       /* toUp : function(idx,data){
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
        },*/
        // 向下
      /*  toDown : function(idx,data){
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
        },*/
       
        // 修改
        update : function(data){
        	var getKnowID=null;
        	var seltitle="";
        	
        	var thiz = this,
        		id = data.id;
        	util.ajax({
                url: serverHost + "/studyapp/selOneDetail.json",
                params: {
                	id : id
                },
                success: function (resp) {
                    if(resp.success) {
                    	var news = resp.model; //返回结果对象
                    	getKnowID=news.knowID; //知识id
                    	seltitle=news.title; //学习详情中对应知识标题 
                    	var vals={id:getKnowID,text:news.title}; //知识下拉列表 默认选中值
                    	var win = winTool.create({
                            title: "修改学习详情",
                            height: 800,
                            width: 800,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".headline-news-tpl",
                            okFn: function (win) {
                            	var title = win.find("#title").val(),
//                        		imageUrl = win.find(".picUrl").data("url"),
                        		studyContent = win.edit.getValue();
                            	isDisplayReprint = win.find(".isDisplayReprint").val(),
                				reprintContent = win.find(".isDisplayReprint").val() == 1 ? win.find(".reprintContent").val() : ""
//                        		isDisplayTag = win.find(".isDisplayTag").val(),
//                        		tagContent = win.find(".isDisplayTag").val() == 1 ? win.find(".tagContent").val() : "",
//        						virtualHitCount = win.find(".virtualHitCount").val();
//                        		onlineTime = win.find(".onlineTime").val(),
//                        		offlineTime = win.find(".timeBox").is(':checked') ? offlineTime = "2030-12-31" : offlineTime = win.find(".offlineTime").val()
                        	var flag = true;
                        	
                        	/*if(!title){
                        		messageTool.error("必须输入学习标题!");
                        		flag = false;
                        	} else {
                        		if(title.length > 60){
                            		messageTool.error("学习标题不能超过60个字!");
                            		flag = false;
                            	}
                        	}*/
                        	if(!isDisplayReprint){
                        		messageTool.error("必须选择是否转载!");
                        		flag = false;
                        	} else if(isDisplayReprint == 1){
                        		if(!reprintContent){
                        			messageTool.error("必须输入转载说明内容!");
                            		flag = false;
                        		}
                        	}
                        	if(!studyContent ||"<p><br></p>"==studyContent){
                    			messageTool.error("必须输入详情内容!");
                        		flag = false;
                    		}
                        /*	if(!isDisplayTag){
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
                        	}*/
                        	
//                        	if(!virtualHitCount){
//                        		messageTool.error("必须输入虚拟点击次数!");
//                        		flag = false;
//                        	}
//                        	if(!onlineTime){
//                        		messageTool.error("必须选择发布时间!");
//                        		flag = false;
//                        	}
//                        	if(!offlineTime){
//                        		messageTool.error("必须选择下线时间!");
//                        		flag = false;
//                        	}
//                        	if(!!onlineTime && !!offlineTime){
//                        		if(onlineTime > offlineTime){
//                        			messageTool.error("下线时间必须大于等于发布时间!");
//                            		flag = false;
//                        		}
//                        	}
                        	if(flag){
                            	util.request({
                            		confirm: true,
                                    msg: "确定要保存修改?",
                                    url: serverHost + "/studyapp/updateStudyDetail.json",
                                    params: {
                                    	id: id,
                                    	title: title,
//                                    	imgUrl: imageUrl,
                                    	knowID:getKnowID,
                                    	title:seltitle,
                                    	studyContent: studyContent,
                                    	isDisplayReprint: isDisplayReprint,
                                    	reprintContent: reprintContent
//                                    	isDisplayTag: isDisplayTag,
//                                    	tagContent: tagContent,
//                                    	virtualHitCount: virtualHitCount,
//                                    	onlineTime: onlineTime,
//                                    	offlineTime: offlineTime,
                                    	//auditStatus: 1  //auditStatus1
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
                    
                    commonTool.renderKnow({
    						msg : "选择对应知识标题",
    						selector : win.find(".knowSel"),
    						val:vals,
    						width : "60%",
    						paramFn : function() {
    							/*return {
    								permission: true
    							};*/
    						},
    						listeners : {
    							change : function() {
    								    // 重新赋值 知识id 
    							    	getKnowID=win.find(".knowSel").select2("val");
    							    	seltitle =win.find(".knowSel").select2("data").text; //文本值 
    							}
    						}
    	             }); 
   	 
                    	thiz.rendenWin(win);
                    	//时间渲染
//                    	dateTool.renderDate(".onlineTime", {
//                    		startDate : new Date()
//            			});
//                   	 	dateTool.renderDate(".offlineTime", {
//            	     		startDate : new Date()
//            			});
                   	 	
                    	win.find("#title").val(news.title);
//                		win.find(".picUrl").data("url",news.imgUrl);
//                		win.find(".picUrl").attr("src",news.fileUrl);
                		//是否显示 转载内容
                		win.find(".isDisplayReprint").val(news.isDisplayReprint);
                		win.find(".reprintContent").val(news.reprintContent);
                		
//                		win.find(".isDisplayTag").val(news.isDisplayTag);
//                		win.find(".tagContent").val(news.tagContent);
//                		win.find(".virtualHitCount").val(news.virtualHitCount);
//                		win.find(".onlineTime").val(util.dateFormat2(news.onlineTime, 'YYYY-MM-DD'));
//                		win.find(".offlineTime").val(util.dateFormat2(news.offlineTime, 'YYYY-MM-DD'));
                		/*if(news.isDisplayTag == 1){
                			win.find(".tagType").css("display","block");
                		}*/
                		if(news.isDisplayReprint == 1){ //显示 是否转载下拉列表 
                			win.find(".reprintType").css("display","block");
                		}else{
                			win.find(".reprintType").css("display","none");
                		}
                		win.edit.setValue(news.studyContent);
                    } else {
                    	messageTool.error("此学习详情已不存在!");
                    }
                }
            });
        },
        
        rendenWin : function(win){
        	win.edit = editorTool.init({
        		selector: win.find("#studyContent"),
        		width: 800,
        		heigth: 700,
        		minHeight: 700,
        		lang: 'en-US'
        	});
        	
       	 	//checkbox
//	   	 	win.find(".timeBox").on("click", function(){
//	       	 	if($(this).is(':checked')){
//	    			win.find(".timeFlag").css("display","none");
//	    		} else {
//	    			win.find(".timeFlag").css("display","block");
//	    		}
//	   	 	});
       	 	//数字框
//        	win.find(".virtualHitCount").on('keyup', function (event) {
//        		var $amountInput = $(this);
//        		//响应鼠标事件，允许左右方向键移动 
//        		event = window.event || event;
//        		if (event.keyCode == 37 | event.keyCode == 39) {
//        		return;
//        		}
//        		//先把非数字的都替换掉，除了数字和. 
//        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
//    		});
        	
        	/*win.find(".tagType").css("display","none");
        	win.find(".reprintType").css("display","none");*/
        	// 是否显示标签
        	/*win.find(".isDisplayTag").on("click",function(){
        		var isDisplayTag = $(this).val();
        		if(isDisplayTag == 1){
        			win.find(".tagType").css("display","block");
        		} else if(isDisplayTag == 2){
        			win.find(".tagType").css("display","none");
        		}
        	});*/
        	
        	// 是否显示转载
        	win.find(".isDisplayReprint").on("click",function(){
        		var isDisplayReprint = $(this).val();
        		if(isDisplayReprint == 1){ //显示 
        			win.find(".reprintType").css("display","block");
        		} else if(isDisplayReprint == 2){
        			win.find(".reprintType").css("display","none");
        		}
        	});
			// 上传图片
			/*win.find(".infos-images").on("click", function() {
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
			});*/
			/*win.find(".uploadImg").on("click", ".remove-img", function() {
				$(".picUrl").attr("src", "");
				$(".picUrl").data("url", "");
			});*/
        },
        getParams: function () {
            return {
            	title: $(".stitle").val(),
            	auditStatus: $(".sAuditStatus").val(),
//            	onlineTime: $(".sOnlineTime").val(),
//            	offlineTime: $(".sOfflineTime").val(),
            };
        }
    };
    return userList;
});