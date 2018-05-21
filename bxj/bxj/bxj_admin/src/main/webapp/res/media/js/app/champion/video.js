define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
    var videoList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "音/视列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/champion/queryVideoList.json",
                width: 600,
                height: 1500,
                columns: [
                    {
                    	name: "视频标题",
                    	width:80,
                    	fieldName: "title"		
                	},
                	{
                    	name: "分类",
                    	width:100,
                    	fieldName: "channelName"
                	},
                	{
                    	name: "标签",
                    	width:100,
                    	fieldName: "tagName"
                	},
					{
						name: "观看费用",
						width:50,
						fieldName: "money"
					},
                    {
                    	name: "讲师",
                    	width:80,
                    	fieldName: "lecturerName"
                    },
					{
						name: "类型",
						width:80,
						fieldName: "videoType",
						renderer : function(row, col, data, value) {
							if(value == 1){
								return "<span class='label label-table label-info'>视频<i></i></span>";
							} else if(value == 2){
								return "<span class='label label-table label-danger'>音频<i></i></span>";
							} else if(value == 3){
								return "<span class='label label-table label-success'>付费精品<i></i></span>";
							}
						}
					},
                    {
                    	name: "音/视频大小",
                    	width:80,
                    	fieldName: "size",
                    	renderer : function(row, col, data,value) {
                        	return value + "M";
                        }
                    },
//                    {
//                        name: "音/视频地址",
//                        width:120,
//                        fieldName: "videoFileUrl",
//                        renderer : function(row, col, data,value) {
//                        	if(data.videoType == 2){
//                        		return '<audio src="' + value + '" controls="controls"></audio>';
//                        	} else {
//                        		return value;
//                        	}
//                        }
//                    },
//                    {
//                        name: "封面图片",
//                        width:120,
//                        fieldName: "coverFileUrl",
//                        renderer : function(row, col, data,value) {
//                        	return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="200px" height="150px"></img></a>';
//                        }
//                    },
                    {
                        name: "审核状态",
                        width:50,
                        fieldName: "auditStatus",
                        renderer : function(row, col, data,value) {
                        	if(value == 0){
                        		return "<span class='label label-table label-info'>待审核<i></i></span>";
                        	} else if(value == 1){
                        		return "<span class='label label-table label-danger'>未通过<i></i></span>";
                        	} else if(value == 2){
                        		return "<span class='label label-table label-success'>通过<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "上线状态",
                        width:50,
                        fieldName: "onlineStatus",
                        renderer : function(row, col, data,value) {
                        	if(value == 0){
                        		return "<span class='label label-table label-danger'>下线<i></i></span>";
                        	} else if(value == 1){
                        		return "<span class='label label-table label-success'>上线<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "是否推荐",
                        width:50,
                        fieldName: "isRecommend",
                        renderer : function(row, col, data,value) {
                        	if(value == 0){
                        		return "<span class='label label-table label-danger'>否<i></i></span>";
                        	} else if(value == 1){
                        		return "<span class='label label-table label-success'>是<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "播放类型",
                        width:80,
                        fieldName: "isUnicast",
                        renderer : function(row, col, data,value) {
                        	if(value == 0){
                        		return "<span class='label label-table label-success'>直播<i></i></span>";
                        	} else if(value == 1){
                        		return "<span class='label label-table label-success'>点播<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "新人通视频浏览次数",
                        width:80,
                        fieldName: "browseNum"
                    },
                    {
                        name: "是否独播",
                        width:50,
                        fieldName: "playType",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label label-table label-success'>独播<i></i></span>";
                        	} else {
                        		return "<span class='label label-table label-success'>非独播<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "持续时间",
                        width:80,
                        fieldName: "durationTime"
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
                        name: "真实播放总数",
                        width:80,
                        fieldName: "playCount",
                    },
                    {
                        name: "是否显示假播放总数",
                        width:80,
                        fieldName: "isFalseCount",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return "<span class='label label-table label-success'>是<i></i></span>";
                        	} else {
                        		return "<span class='label label-table label-danger'>否<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "假播放总数",
                        width:80,
                        fieldName: "falseCount",
                    },
                    {
                        name: "分享总数",
                        width:80,
                        fieldName: "shareCount"
                    },
                    {
                        name: "收藏总数",
                        width:80,
                        fieldName: "collectionCount"
                    },
                    {
                        name: "是否用券",
                        width:80,
                        fieldName: "voucherId",
                        renderer : function(row, col, data,value) {
                        	if(!!value && data.voucherCount > 0){
                        		return "<span class='label label-table label-success'>是<i></i></span>";
                        	} else {
                        		return "<span class='label label-table label-danger'>否<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "用券数",
                        width:80,
                        fieldName: "voucherCount"
                    },
//                    {
//                        name: "描述",
//                        width:200,
//                        fieldName: "description"
//                    },
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	if(data.auditStatus == 1){
                        		var auditButton = '<a class="green passVideo" href="javascript:void(0);" title="审核通过"> <i class="fa fa-thumbs-o-up bigger-140 "></i> </a>&nbsp;';
                        	} else if(data.auditStatus == 2){
                        		var auditButton = '<a class="red failedVideo" href="javascript:void(0);" title="审核不通过"> <i class="fa fa-thumbs-o-down bigger-140 "></i> </a>&nbsp;';
                        	} else {
                        		var auditButton = '<a class="green passVideo" href="javascript:void(0);" title="审核通过"> <i class="fa fa-thumbs-o-up bigger-140 "></i> </a>&nbsp;' +
                        						  '<a class="red failedVideo" href="javascript:void(0);" title="审核不通过"> <i class="fa fa-thumbs-o-down bigger-140 "></i> </a>&nbsp;';
                        	}
                        	if(data.onlineStatus == 1){
                        		var typeButton = '<a class="red downVideo" href="javascript:void(0);" title="下线"> <i class="fa fa-arrow-down bigger-140 "></i> </a>&nbsp;';
                        	} else {
                        		var typeButton = '<a class="green upVideo" href="javascript:void(0);" title="上线"> <i class="fa fa-arrow-up bigger-140 "></i> </a>&nbsp;';
                        	}
                        	var commonButton = '<a class="green update" href="javascript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-140 "></i> </a>&nbsp;' +
                            				   '<a class="red delete" href="javascript:void(0);" title="删除" > <i class="fa fa-trash-o bigger-140"></i> </a>&nbsp;' +
                            				   '<a class="blue commentDetailList" href="javascript:void(0);" title="评论详情" > <i class="fa fa-list-alt bigger-140"></i> </a>&nbsp;';
//                        	var st = '<div class="action-buttons">' +
//                        			 '<div class="btn-group">' + 
//                        			auditButton + typeButton +
//                        			 '<button class="btn btn-danger delete">删除</button>' +
//                        			 '<button class="btn btn-warning update">修改</button>' +
//                        			 '<button class="btn btn-info commentDetailList">评论详情</button>' +
//                        			 '</div>' +
//                        			 '</div>';
//                        	var st2 = '<div class="btn-group" style="width:170px">' + 
//                        			  '<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
//                        			  '<i class="fa fa-gear fa-lg"></i>	操作' + 
//                        	          '<span class="caret"></span>' + 
//                        	          '</button>' + 
//                        	          '<ul class="dropdown-menu">' +
//                        	          auditButton +
//                        	          '<li role="separator" class="divider"></li>' +
//                        	          typeButton +
//                        	          '<li role="separator" class="divider"></li>' +
//                        	          commonButton +
//                        	          '</ul>' +
//                        	          '</div>';
                        	var st3 = '<div class="action-buttons" style="width:150px"> ' +
                        	          auditButton +
                        	          typeButton +
                        			  commonButton +
                                      '</div>';
                        	return st3;
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
                    rightCode: "add",
                    handler: function (idx, data) {
                        thiz.addVideo();
                    }
                }],
                classEvents : [{
	            	className : "passVideo",
	            	rightCode: "audit",
	            	handler: function (idx, data) {
	                    thiz.passVideo(data);
	                }
                },
                {
	            	className : "failedVideo",
	            	rightCode: "audit",
	            	handler: function (idx, data) {
	                    thiz.failedVideo(data);
	                }
                },
                {
	            	className : "downVideo",
	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.downVideo(data);
	                }
                },
                {
	            	className : "upVideo",
	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.upVideo(data);
	                }
                },
                {
	            	className : "delete",
	            	rightCode: "delete",
	            	handler: function (idx, data) {
	                    thiz.delete1(data);
	                }
                },
                {
	            	className : "update",
	            	rightCode: "update",
	            	handler: function (idx, data) {
	                    thiz.update(data);
	                }
                },
                {
	            	className : "commentDetailList",
//	            	rightCode: "commentDetailList",
	            	handler: function (idx, data) {
	                    window.location = serverHost + "/champion/toCommentDetail.jhtml?id=" + data.fid;
	                }
                }
                ]
            });
            this.list = list;
        	//时间渲染
        	dateTool.renderDate(".sUploadTime");
        	
        	// 渲染下拉框
			commonTool.renderChannel({
				msg : "选择分类",
				selector : $(".sChannel"),
				width : "100%",
				paramFn : function() {
					return {
//						permission: true
					};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
			// 所有讲师
			commonTool.renderLectures({
				msg : "选择讲师",
				selector : $(".sLecturer"),
				width : "100%",
				paramFn : function() {
					return {
//						permission: true
					};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 审核通过
        passVideo: function(data){
        	var thiz = this;
        	var fid = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定要审核通过?",
                url: serverHost + "/champion/updateVideoStatus.json",
                params: {
                	fid: fid,
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
        failedVideo: function(data){
        	var thiz = this;
        	var fid = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定审核不通过?",
                url: serverHost + "/champion/updateVideoStatus.json",
                params: {
                	fid: fid,
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
        
        // 上线视频
        upVideo: function(data){
        	var thiz = this;
        	var fid = data.fid;
        	if(data.auditStatus != 2){
        		messageTool.error("该音/视频未审核通过,请先审核");
        		return;
        	} 
        	util.request({
        		confirm: true,
                msg: "确定要上线该音/视频?",
                url: serverHost + "/champion/updateVideoStatus.json",
                params: {
                	fid: fid,
                	onlineStatus: 1
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
        
        // 下线视频
        downVideo: function(data){
        	var thiz = this;
        	var fid = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定下线该音/视频?",
                url: serverHost + "/champion/updateVideoStatus.json",
                params: {
                	fid: fid,
                	onlineStatus: 0
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
        	var fid = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/champion/updateVideoStatus.json",
                params: {
                	fid: fid,
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
        addVideo: function(){
        	var thiz = this;
        	var win = winTool.create({
                title: "新增",
                height: 800,
                width: 800,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".video-tpl",
                okFn: function (win) {
                	var videoType, // 音/视频类型
                		title = win.find("#title").val(), // 标题
                		pageUrl = win.find(".pageUrl").val(), // 链接
                		videoSize, // 大小
                		videoUrl, // 视频地址
                		channelId = win.find(".channel").select2("val") || "", // 分类id
                		lecturerId = win.find(".lecturer").select2("val") || "", // 讲师id 
        				lecturerName = win.find(".lecturer").select2("data")?win.find(".lecturer").select2("data").text.split(":")[1]:null, // 讲师名称
                		uploadTime = win.find(".uploadTime").val(), // 上传时间
                		coverImageUrl = win.find(".picUrl").data("url"), // 封面图片地址
                		isRecommend = true, // 是否推荐
                		isUnicast, // 点播-直播
                		playType, // 是否独播
                		durationTime = win.find(".durationTime").val(), // 持续时间
                		isFalseCount, // 是否显示假播放次数
                		voucherCount = win.find(".voucherCount").val(), // 用券数
						money = win.find(".money").val(), // 用券数
						watchTime = win.find(".watchTime").val(), // 用券数
                		qiniuId, // 七牛id
                		description = win.find(".description").val() ? win.find(".description").val().replace(/\n/g, "<br>") : null; // 描述
        				console.info(description);
                	win.find(".videoType").each(function(){
                		if($(this).is(":checked")){
                			videoType = $(this).val();
                		}
                	})
                	if(videoType == 1){
                		videoSize = win.find(".videoSize").val();
                		videoUrl = win.find(".videoUrl").val();
                		qiniuId = win.find(".qiniuId").val();
                		if(win.find(".isUnicast").val() == 1){
                    		isUnicast = true;
                    	} else if(win.find(".isUnicast").val() == 0){
                    		isUnicast = false;
                    	}
                	} else {
                		videoSize = win.find(".voiceUrl").data("size");
                		videoUrl = win.find(".voiceUrl").data("url");
//                		durationTime = thiz.getTime(win.find(".voiceUrl")[0].duration);
                		isUnicast = true;
                	}
//                	if(win.find(".isRecommend").val() == 1){
//                		isRecommend = true;
//                	} else if(win.find(".isRecommend").val() == 0){
//                		isRecommend = false;
//                	}
                	if(win.find(".playType").val() == 1){
                		playType = true;
                	} else if(win.find(".playType").val() == 0){
                		playType = false;
                	}
                	if(win.find(".isFalseCount").val() == 1){
                		isFalseCount = true;
                	} else if(win.find(".isFalseCount").val() == 0){
                		isFalseCount = false;
                	}
                	
                	var flag = true;
                	if(!title){
                		messageTool.error("必须输入标题!");
                		flag = false;
                	}
                	// 视频
                	if(videoType == 1){
                		if(!videoSize){
                			messageTool.error("必须输入视频大小!");
                    		flag = false;
                		}
                		if(!videoUrl){
                			messageTool.error("必须输入视频地址!");
                    		flag = false;
                		}
//                		if(!qiniuId){
//                			messageTool.error("必须输入七牛id!");
//                    		flag = false;
//                		}
                		if(null == isUnicast || undefined == isUnicast){
                    		messageTool.error("必须选择播放类型!");
                    		flag = false;
                    	}
                	// 音频
                	} else if(videoType == 2){
                		if(!videoUrl){
                			messageTool.error("必须上传音频!");
                    		flag = false;
                		}
                	}
                	if(!channelId){
                		messageTool.error("必须选择分类!");
                		flag = false;
                	}
                	if(!lecturerId){
                		messageTool.error("必须选择讲师!");
                		flag = false;
                	}
                	if(!uploadTime){
                		messageTool.error("必须选择上传时间!");
                		flag = false;
                	}
                	if(!coverImageUrl){
                		messageTool.error("必须上传封面图片!");
                		flag = false;
                	}
//                	if(null == isRecommend || undefined == isRecommend){
//                		messageTool.error("必须选择是否推荐!");
//                		flag = false;
//                	}
                	if(null == playType || undefined == playType){
                		messageTool.error("必须选择是否独播!");
                		flag = false;
                	}
                	if(!durationTime){
                		messageTool.error("必须填写持续时间!");
                		flag = false;
                	}
                	if(null == isFalseCount || undefined == isFalseCount){
                		messageTool.error("必须选择是否显示假播放次数!");
                		flag = false;
                	}
//                	if(!description){
//                		messageTool.error("必须输入描述!");
//                		flag = false;
//                	}
                	var tags = win.find(".videoTag").select2("data") || [];
                	var tagIds = "";
	                $(tags).each(function () {
	                	if(tagIds != ""){
	                		tagIds = tagIds + ",";
	                	}
	                	tagIds = tagIds + this.id;
	                });
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要保存?",
                            url: serverHost + "/champion/addNews.json",
                            params: {
                            	title: title,
                            	videoType: videoType,
                            	pageUrl: pageUrl,
                            	videoSize: videoSize,
                            	videoUrl: videoUrl,
                            	uploadTime: uploadTime,
                            	coverImageUrl: coverImageUrl,
                            	isRecommend: isRecommend,
                            	isUnicast: isUnicast,
                            	channelId: channelId,
                            	lecturerId: lecturerId,
                            	lecturerName: lecturerName,
                            	playType: playType,
                            	auditStatus: 0,
                            	durationTime: durationTime,
                            	isFalseCount: isFalseCount,
                            	voucherCount: voucherCount,
								money: money,
								watchTime: watchTime,
                            	qiniuId: qiniuId,
                            	description: description,
                            	status: 1,
                            	tagIds: tagIds
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
        	
       	 	thiz.rendenWin(win);
       	 	
       	 	// 渲染下拉框
       	 	// 视频分类
			commonTool.renderChannel({
				msg : "选择分类",
				selector : win.find(".channel"),
				width : "100%",
				paramFn : function() {
					return {
//						permission: true
					};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
			// 所有讲师
			commonTool.renderLecture({
				msg : "选择讲师",
				selector : win.find(".lecturer"),
				width : "100%",
				paramFn : function() {
					return {
//						permission: true
					};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
			// 所有标签
			commonTool.renderTag({
				msg : "选择标签",
				selector : win.find(".videoTag"),
				width : "100%",
				multiple : true,
				paramFn : function() {
					return {
//						permission: true
					};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
        	//时间渲染
        	dateTool.renderDate(".uploadTime");
        },
        
        // 修改
        update : function(data){
        	var thiz = this,
        		fid = data.fid;
        	util.ajax({
                url: serverHost + "/champion/queryVideo.json",
                params: {
                	fid : fid
                },
                success: function (resp) {
                    if(resp.success) {
                    	var video = resp.model;
                    	
                    	var win = winTool.create({
                            title: "修改",
                            height: 800,
                            width: 800,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".video-tpl",
                            okFn: function (win) {
                            	var videoType, // 音/视频类型
                        		title = win.find("#title").val(), // 标题
                        		pageUrl = win.find(".pageUrl").val(), // 链接
                        		videoSize, // 大小
                        		videoUrl, // 视频地址
                        		channelId = win.find(".channel").select2("val") || "", // 分类id
                        		lecturerId = win.find(".lecturer").select2("val") || "", // 讲师id 
                				lecturerName = win.find(".lecturer").select2("data")?win.find(".lecturer").select2("data").text.split(":")[1]:null, // 讲师名称
                        		uploadTime = win.find(".uploadTime").val(), // 上传时间
                        		coverImageUrl = win.find(".picUrl").data("url"), // 封面图片地址
                        		isUnicast, // 点播-直播
                        		playType, // 是否独播
                        		durationTime = win.find(".durationTime").val(), // 持续时间
                        		isFalseCount, // 是否显示假播放次数
                        		voucherCount = win.find(".voucherCount").val(), // 用券数
								money = win.find(".money").val(), // 用券数
								watchTime = win.find(".watchTime").val(), // 用券数
                        		qiniuId, // 七牛id
                        		description = win.find(".description").val() ? win.find(".description").val().replace(/\n/g, "<br>") : null; // 描述
                				console.info(description);
                        	win.find(".videoType").each(function(){
                        		if($(this).is(":checked")){
                        			videoType = $(this).val();
                        		}
                        	})
                        	var flag = true;
                        	if(videoType == 1){
                        		videoSize = win.find(".videoSize").val();
                        		videoUrl = win.find(".videoUrl").val();
                        		qiniuId = win.find(".qiniuId").val();
                        		if(win.find(".isUnicast").val() == 1){
                            		isUnicast = true;
                            	} else if(win.find(".isUnicast").val() == 0){
                            		isUnicast = false;
                            	}
                        		
                        	} else {
                        		videoSize = win.find(".voiceUrl").data("size");
                        		videoUrl = win.find(".voiceUrl").data("url");
//                        		durationTime = thiz.getTime(win.find(".voiceUrl")[0].duration);
                        	}
//                        	if(win.find(".isRecommend").val() == 1){
//                        		isRecommend = true;
//                        	} else if(win.find(".isRecommend").val() == 0){
//                        		isRecommend = false;
//                        	} else {
//                        		
//                        	}
                        	if(win.find(".isFalseCount").val() == 1){
                        		isFalseCount = true;
                        	} else if(win.find(".isFalseCount").val() == 0){
                        		isFalseCount = false;
                        	}
                        	if(win.find(".playType").val() == 1){
                        		playType = true;
                        	} else if(win.find(".playType").val() == 0){
                        		playType = false;
                        	}
                        	if(!title){
                        		messageTool.error("必须输入标题!");
                        		flag = false;
                        	}
                        	// 视频
                        	if(videoType == 1){
                        		if(!videoSize){
                        			messageTool.error("必须输入视频大小!");
                            		flag = false;
                        		}
                        		if(!videoUrl){
                        			messageTool.error("必须输入视频地址!");
                            		flag = false;
                        		}
//                        		if(!qiniuId){
//                        			messageTool.error("必须输入七牛id!");
//                            		flag = false;
//                        		}
                        		if(null == isUnicast || undefined == isUnicast){
                            		messageTool.error("必须选择播放类型!");
                            		flag = false;
                            	}
                        	// 音频
                        	} else if(videoType == 2){
                        		if(!videoUrl){
                        			messageTool.error("必须上传音频!");
                            		flag = false;
                        		}
                        	}
                        	if(!channelId){
                        		messageTool.error("必须选择分类!");
                        		flag = false;
                        	}
                        	if(!lecturerId){
                        		messageTool.error("必须选择讲师!");
                        		flag = false;
                        	}
                        	if(!uploadTime){
                        		messageTool.error("必须选择上传时间!");
                        		flag = false;
                        	}
                        	if(!coverImageUrl){
                        		messageTool.error("必须上传封面图片!");
                        		flag = false;
                        	}
//                        	if(null == isRecommend || undefined == isRecommend){
//                        		messageTool.error("必须选择是否推荐!");
//                        		flag = false;
//                        	}
                        	if(null == playType || undefined == playType){
                        		messageTool.error("必须选择是否独播!");
                        		flag = false;
                        	}
                        	if(!durationTime){
                        		messageTool.error("必须填写持续时间!");
                        		flag = false;
                        	}
                        	if(null == isFalseCount || undefined == isFalseCount){
                        		messageTool.error("必须选择是否显示假播放次数!");
                        		flag = false;
                        	}
//                        	if(!description){
//                        		messageTool.error("必须输入描述!");
//                        		flag = false;
//                        	}
                        	var tags = win.find(".videoTag").select2("data") || [];
                        	var tagIds = "";
        	                $(tags).each(function () {
        	                	if(tagIds != ""){
        	                		tagIds = tagIds + ",";
        	                	}
        	                	tagIds = tagIds + this.id;
        	                });
                        	if(flag){
                            	util.request({
                            		confirm: true,
                                    msg: "确定要保存?",
                                    url: serverHost + "/champion/updateVideo.json",
                                    dataType:"json",
                                    params: {
                                    	fid: video.fid,
                                    	title: title,
                                    	videoType: videoType,
                                    	pageUrl: pageUrl,
                                    	videoSize: videoSize,
                                    	videoUrl: videoUrl,
                                    	uploadTime: uploadTime,
                                    	coverImageUrl: coverImageUrl,
//                                    	isRecommend: isRecommend,
                                    	isUnicast: isUnicast,
                                    	channelId: channelId,
                                    	lecturerId: lecturerId,
                                    	lecturerName: lecturerName,
                                    	playType: playType,
                                    	auditStatus: video.auditStatus,
                                    	durationTime: durationTime,
                                    	isFalseCount: isFalseCount,
                                    	voucherCount: voucherCount,
										money: money,
										watchTime: watchTime,
                                    	qiniuId: qiniuId,
                                    	description: description,
                                    	tagIds: tagIds
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
                    	var channel = {
                    			id : video.channelId,
                    			text : video.channelName
                    	}
                    	var lecturer = {
                    			id : video.lecturerId,
                    			text : video.lecturerName
                    	}
                    	var tagList = video.tagList;
                    	var tagVal = [];
                    	$(tagList).each(function () {
    	                	var tag = {
    	                		id : this.fid,
    	                		text : this.name
    	                	}
    	                	tagVal.push(tag);
    	                });
                    	// 渲染下拉框
            			commonTool.renderChannel({
            				msg : "选择分类",
            				selector : win.find(".channel"),
            				width : "100%",
            				val : channel,
            				paramFn : function() {
            					return {
//            						permission: true
            					};
            				},
            				listeners : {
            					change : function() {
            						
            					}
            				}
            			});
            			// 所有讲师
            			commonTool.renderLecture({
            				msg : "选择讲师",
            				selector : win.find(".lecturer"),
            				width : "100%",
            				val : lecturer,
            				paramFn : function() {
            					return {
//            						permission: true
            					};
            				},
            				listeners : {
            					change : function() {
            						
            					}
            				}
            			});
            			// 所有标签
            			commonTool.renderTag({
            				msg : "选择标签",
            				selector : win.find(".videoTag"),
            				width : "100%",
            				val : tagVal,
            				multiple : true,
            				paramFn : function() {
            					return {
//            						permission: true
            					};
            				},
            				listeners : {
            					change : function() {
            						
            					}
            				}
            			});
                    	//时间渲染
                    	dateTool.renderDate(".uploadTime");
                    	win.find(".videoType").each(function(){
                    		if(video.videoType == $(this).val()){
                    			$(this).attr("checked","checked");
                    		}
                    	})
                    	// 视频
                   	 	if(video.videoType == 1){
	                   	 	win.find(".voice").css("display","none");
	            			win.find(".video").css("display","block");
	            			win.find(".videoSize").val(video.videoSize);
                    		win.find(".videoUrl").val(video.videoUrl);
                    		win.find(".qiniuId").val(video.qiniuId);
                   	 	// 音频	
                   	 	} else {
	                   	 	win.find(".voice").css("display","block");
	            			win.find(".video").css("display","none");
	            			win.find(".voiceUrl").data("size",video.videoSize);
                    		win.find(".voiceUrl").data("url",video.videoUrl);
                   	 	}
                    	win.find("#title").val(video.title);
                		win.find(".picUrl").data("url",video.coverImageUrl);
                		win.find(".picUrl").attr("src",video.coverFileUrl);
                		win.find(".pageUrl").val(video.pageUrl);
                		win.find(".uploadTime").val(util.dateFormat2(video.uploadTime, 'YYYY-MM-DD'));
                		win.find(".durationTime").val(video.durationTime);
                		win.find(".isRecommend").val(video.isRecommend ? 1 : 0);
                		win.find(".isUnicast").val(video.isUnicast ? 1 : 0);
                		win.find(".playType").val(video.playType ? 1 : 0);
                		win.find(".isFalseCount").val(video.isFalseCount ? 1 : 0);
                		win.find(".voucherCount").val(video.voucherCount);
						win.find(".money").val(video.money);
						win.find(".watchTime").val(video.watchTime);
                		win.find(".description").val(video.description.replace(/<br>/g, "\n"));
                		if(video.videoType == 2){
                			win.find(".voiceUrl").data("url",video.videoUrl);
                    		win.find(".voiceUrl").attr("src",video.videoFileUrl);
                		}
                    } else {
                    	messageTool.error("此记录已不存在!");
                    }
                }
            });
        },
        
        rendenWin : function(win){
        	var thiz = this;
        	win.find(".videoType").on("click",function(){
        		var check = $(this).val();
        		if(check == 2){
        			win.find(".voice").css("display","block");
        			win.find(".video").css("display","none");
        		} else {
        			win.find(".voice").css("display","none");
        			win.find(".video").css("display","block");
        		}
        	})
       	 	//数字框
        	win.find(".voucherCount, .videoSize").on('keyup', function (event) {
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
    		});
        	
			// 上传文件
			win.find(".infos-voice").on("click", function() {
				imageUploadTool.init({
					title : "上传音频文件",
					attach : 4,
					acceptedFiles : ".mp3",
					maxFiles : 1,
					callback : function(files) {
						var file = files.length > 0 ? files[0] : {};
						$(".voiceUrl").attr("src", file.fileUrl);
						$(".voiceUrl").data("url", file.fileName);
						$(".voiceUrl").data("size", file.voiceSize);
					}
				});
			});
			
			// 上传图片
			win.find(".infos-images").on("click", function() {
				imageUploadTool.init({
					title : "上传图片",
					attach : 3,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1,
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
			win.find(".uploadVoice").on("click", ".remove-img", function() {
				$(".voiceUrl").attr("src", "");
				$(".voiceUrl").data("url", "");
				$(".durationTime").val("");
			});

        },
        getParams: function () {
        	var channel = $(".sChannel").select2("data") || {};
        	var lecturer = $(".sLecturer").select2("data") || {};
            return {
            	likeName: $(".stitle").val(),
            	auditStatus: $(".sAuditStatus").val(),
            	channelId: channel.id || "",
            	videoType: $(".sVideoType").val(),
            	playType: $(".sPlayType").val(),
            	isRecommend: $(".sIsRecommend").val(),
            	voucherType: $(".sVoucherType").val(),
            	uploadTime: $(".sUploadTime").val(),
            	lecturerName : lecturer.text || "",
            	falseCountType : $(".falseCountType").val(),
            	falseCount : $(".falseCount").val()
            };
        },
        getTime: function(mp3SecondTime){
        	if(!mp3SecondTime){
        		return "00:00:00"
        	}
    	    //分钟
    	    var minute = mp3SecondTime / 60;
    	    var minutes = parseInt(minute);
    	    if (minutes < 10) {
    	        minutes = "0" + minutes;
    	    }
    	    //小时
    	    var hour = minute / 60;
    	    var hours = parseInt(hour);
    	    if (hours < 10) {
    	    	hours = "0" + hours;
    	    }
    	    //秒
    	    var second = mp3SecondTime % 60;
    	    seconds = parseInt(second);
    	    if (seconds < 10) {
    	        seconds = "0" + seconds;
    	    }
    	    var allTime = hours + ":" + minutes + ":" + seconds;
    	    return allTime;
        }
    };
    return videoList;
});