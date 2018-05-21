define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool',
        'app/common/imageView','app/common/commonTool'],
    	 
	 function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool,_,commonTool) {
	       var  heightImg=null,widthImg=null;
      var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "朋友圈神器列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/friendCircle/queryFriendsList.json",
                width: 640,
                height: 600,
                columns: [
                    {
                    	name: "ID",
                    	width:40,
                    	fieldName: "fid"		
                    },
                    {
                    	name: "标题",
                    	width:120,
                    	fieldName: "title"		
                	},
					{
						name: "保险公司",
						width:60,
						fieldName: "company"
					},
					{
						name: "父级ID",
						width:50,
						fieldName: "pfid"
					},
                	{
                    	name: "类型",
                    	width:40,
                    	fieldName: "type",
                    	renderer : function(row, col, data,value) {
                    		// 图文
                    		if(value == 1){
                    			return "<span class='bg-success'>图文</span>";
                    		// 文章 表示 2
                    		} else if(value == 2){
                    			return "<span class='bg-warning'>文章</span>";
                    		}
                    	}
                	},
					{
						name: "喜欢次数",
						width:60,
						fieldName: "likeCount"

					},
                    {
                    	name: "转发次数",
                    	width:60,
                    	fieldName: "shareCount"
                    	
                    },
					{
						name: "真实转发次数",
						width:60,
						fieldName: "shareCountReal"

					},
					{
						name: "周转发次数",
						width:60,
						fieldName: "weekShareCount"

					},
					{
						name: "周真实转发次数",
						width:60,
						fieldName: "weekShareCountReal"

					},
                    {
                    	name: "分类标签",
                    	width:40,
                    	fieldName: "categoryTitle"
                    	
                    },
                    
                    {
                    	name: "状态",
                    	width:40,
                    	fieldName: "status",
                    	renderer : function(row, col, data,value) {
                    		// 1.未上线；2.上线；3.下线
                    		if(value == 1){
                    			return "<span class='label label-table label-info'>未上线<i></i></span>";
                    		// 上线
                    		} else if(value == 2) {
                    			return "<span class='label label-table label-success'>上线<i></i></span>";
                    		// 下线
                    		} else if(value == 3){
                    			return "<span class='label label-table label-danger'>下线<i></i></span>";
                    		}
                        }
                    },
                    {
                        name: "发布时间",
                        width:60,
                        fieldName: "publishTime",
					    sort: true,
					    sortName:"publishTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        name: "更新时间",
                        width:80,
                        fieldName: "updateTime",
                        sort: true,
					    sortName:"updateTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                   
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	if(data.status == 3){
                        		var auditButton = '<button class="btn btn-info passNews">上线</button>';
                        	} else if(data.status == 2){
                        		var auditButton = '<button class="btn btn-danger failedNews">下线</button>';
                        	} else {
                        		var auditButton = '<button class="btn btn-info passNews">上线</button>' +
                        						  '<button class="btn btn-danger failedNews">下线</button>';
                        	}
                        	var st = auditButton +
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
                    name: "新增文章",
//                    rightCode: "add",
                    handler: function (idx, data) {
                        thiz.addTexts();
                    }
                },
                {
                    icon: 'fa fa-plus',
                    name: "新增图文",
//                    rightCode: "add",
                    handler: function (idx, data) {
                        thiz.addImgText();
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
              /*  {
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
                },*/
               /* {
	            	className : "newsDetail",
	            	handler: function (idx, data) {
	                    thiz.newsDetail(data);
	                }
                },*/
                ]
            });
            this.list = list;
        	//时间渲染 搜索
        	dateTool.renderDateTime(".sOnlineTime");
//       	 	dateTool.renderDate(".sOfflineTime");
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        //  上线
        passNews: function(data){
        	var thiz = this;
        	var id = data.fid;
        	/*if(data.offlineTime, 'YYYY/MM/DD' < new Date().getTime()){
        		messageTool.error("当前头条的下线时间小于当天时间,请先修改下线时间!");
        		return;
        	}*/
        	util.request({
        		confirm: true,
                msg: "确定要上线?",
                url: serverHost + "/friendCircle/updateFriend.json",
                params: {
                	fid: id,
                	status: 2
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
        //   下线
        failedNews: function(data){
        	var thiz = this;
        	var id = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定下线?",
                url: serverHost + "/friendCircle/updateFriend.json",
                params: {
                	fid: id,
                	status: 3
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
        	var id = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/friendCircle/deleteFriend.json",
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
        	widthImg=null,heightImg=null; //赋空 宽高 
        	var thiz = this;
        	var win = winTool.create({
                title: "新增朋友圈文章",
                height: 700,
                width: 1500,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".friend-circle-text",
                okFn: function (win) {
                	var title = win.find("#title").val(),
                		imageUrl = win.find(".picUrl").data("url"),
                		newsContent = win.edit.getValue();
                	    words= win.find(".mywords").val(), //UE.getEditor('mywords').getContentTxt(),// html文本 getContent
                		onlineTime = win.find(".onlineTime").val(),
                		source=win.find(".source").val(),
                		categoryIdVal=win.find(".categorySel").select2("val"),
                		sort=win.find(".sort").val(),
						pfid=win.find(".pfid").val(),
							company=win.find(".company").val()
                	var flag = true;
                	if(!title){
                		messageTool.error("必须输入标题!");
                		flag = false;
                	} else {
                		if(title.length > 128){
                    		messageTool.error("标题不能超过128个字!");
                    		flag = false;
                    	}
                	}
                	
                /*	if(!source){
                		messageTool.error("必须输入来源!");
                		flag = false;
                	} else {*/
                		if(source && source.length > 50){
                    		messageTool.error("来源不能超过50个字!");
                    		flag = false;
                    	}
//                	}
                		
            		if(words && words.length > 1020){
                		messageTool.error("文字不能超过1020个字!");
                		flag = false;
                	}
                	  
                	if(!onlineTime){
                		messageTool.error("必须选择发布时间!");
                		flag = false;
                	}
                 
                	if(!sort){
                		messageTool.error("请输入排序!(数字)");
                		flag = false;
                	}else if(isNaN(sort)){
                		   messageTool.error("排序请输入数字!");
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
                            url: serverHost + "/friendCircle/addFriend.json",
                            params: {
                            	title: title,
                            	pic1: imageUrl,
                            	content: newsContent,
                            	type: 2,
                            	source:source,
                            	publishTime: onlineTime!= "" ?  onlineTime + " 00:00:00" : "",
                            	status: 1,
                            	sort:sort,
                            	categoryId:categoryIdVal,
                            	words: words,
								pfid:pfid,
								company:company
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	messageTool.success("新增文章成功");
                                	win.close();
//                                	setTimeout(function(){window.location.reload();}, 1200);
                                	thiz.list.reloadPageData();
                                } else {
                                	messageTool.error("新增失败");
                                }
                            }
                        });
                	}
                }
             });
        	
        	 //2.隐藏图文
			 win.find(".imgTextclass").css("display","none");
			 //显示文章 
			 win.find(".circleTextclass").css("display","block");
			
        	//时间渲染  
        	dateTool.renderDateTime(".onlineTime");
        	
        	//渲染下拉框  categoryId 
         	commonTool.renderFriend({
        						msg : "选择分类的标签",
        						selector : win.find(".categorySel"),
        						width : "60%",
        						paramFn : function() {
        							/*return {
        								permission: true
        							};*/
        						},
        						listeners : {
        							change : function() {
        								//var inpTitle=win.find(".categorySel").select2("data").title; //文本值 
        								//alert("改变下拉值we "+inpTitle);
        							}
        						}
        	}); 
         	
       	 	thiz.rendenWin(win);
//         	clickBtnClose(); //按钮关闭 
        },
        
        // 新增 图文
        addImgText: function(){
        	$(".addimgText").html(""); //清空 add图片div内容
        	widthImg=null,heightImg=null; //赋空 宽高 
        	var thiz = this;
        	var win = winTool.create({
                title: "新增朋友圈图文",
                height: 500,
                width: 1500,
                showCancel: true,
                okName: "保存",
                cancelName: "关闭",
                type: "selector",
                selector: ".friend-circle-text",
                okFn: function (win) {
                	var imageUrl = win.find(".picUrlimgText").data("url"),
                		//多个图片 ftype=win.find("#ftypeId").val(),
                		imageUrl2 = win.find(".picUrlimgText1").data("url");
                		imageUrl3 = win.find(".picUrlimgText2").data("url");
                		imageUrl4 = win.find(".picUrlimgText3").data("url");
                		imageUrl5 = win.find(".picUrlimgText4").data("url");
                		imageUrl6 = win.find(".picUrlimgText5").data("url");
                		imageUrl7 = win.find(".picUrlimgText6").data("url");
                		imageUrl8 = win.find(".picUrlimgText7").data("url");
                		imageUrl9 = win.find(".picUrlimgText8").data("url");
                		categoryIdVal=win.find(".categorySelByImg").select2("val");
                		//文章 文字  文本
                	    words = win.find(".wordsID").val(), //UE.getEditor('wordsID').getContentTxt(),  
                	    sort=win.find("#sort").val(),
                		onlineTime = win.find(".imgonlineTime").val(),
							pfid=win.find("#pfid").val(),
							company=win.find("#company").val()

                	var flag = true;
                	    
                	if(!title){
                		messageTool.error("必须输入标题!");
                		flag = false;
                	} else {
                		if(title.length > 128){
                    		messageTool.error("标题不能超过128个字!");
                    		flag = false;
                    	}
                	}
                	  
                	if(!onlineTime){
                		messageTool.error("必须选择发布时间!");
                		flag = false;
                	}
                	
                	if(!sort){
                		messageTool.error("请输入排序!(数字)");
                		flag = false;
                	}else if(isNaN(sort)){
                		   messageTool.error("排序请输入数字!");
                		   flag = false;
                	}
                	
                 	if(words && words.length > 1020){
                		messageTool.error("文字不能超过1020个字!");
                		flag = false;
                	} 
                	if(!imageUrl & !imageUrl2 & !imageUrl3 & !imageUrl4 & !imageUrl5 & !imageUrl6 & !imageUrl7 & !imageUrl8 & !imageUrl9){
                		messageTool.error("至少上传一张图片!");
                		flag = false;
                	}
                	if(flag){
                    	util.request({
                    		confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/friendCircle/addFriend.json",
                            params: {
                            	type: 1,
                            	categoryId:categoryIdVal,
                            	pic1: imageUrl,
                            	pic2: imageUrl2,
                            	pic3: imageUrl3,
                            	pic4: imageUrl4,
                            	pic5: imageUrl5,
                            	pic6: imageUrl6,
                            	pic7: imageUrl7,
                            	pic8: imageUrl8,
                            	pic9: imageUrl9,
                            	publishTime: onlineTime!= "" ?  onlineTime + " 00:00:00" : "",
                            	sort:sort,
                            	status: 1,
                            	height:heightImg,
                                width:widthImg,
                            	words: words,
								pfid:pfid,
								company:company
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	messageTool.success("新增图文成功");
                                	win.close();
//                                	setTimeout(function(){window.location.reload();}, 1200);
                                	thiz.list.reloadPageData();
                                } else {
                                	messageTool.error("新增失败");
                                }
                            }
                        });
                	}
                }
             });
        	
        	// .隐藏文章；
    		win.find(".circleTextclass").css("display","none");
			win.find(".imgTextclass").css("display","block"); //显示图文 
    	    
        	//时间渲染
        	dateTool.renderDateTime(".imgonlineTime");
       	 	/*dateTool.renderDateTime("#imgonlineTime", {
	     		startDate : new Date()
			});*/
        	
        	//渲染下拉框  categoryId
         	commonTool.renderFriend({
        						msg : "选择分类的标签",
        						selector : win.find(".categorySelByImg"),
        						width : "60%",
        						paramFn : function() {
        							 return {
//        								permission: true
        							};  
        						},
        						listeners : {
        							change : function() {
        								 
        							}
        						}
        	}); 
        	
       	 	thiz.rendenWin(win);
//       	    clickBtnClose(); //按钮关闭 
        },
        
        // 修改 文章 图文 
        update : function(data){
        	var cateGoryID=null;
        	strBuilder = []; //清空 add图片div内容 
        	widthImg=null,heightImg=null; //赋空 宽高 
        	var thiz = this,
        		id = data.fid;
        	util.ajax({
                url: serverHost + "/friendCircle/queryFriend.json", 
                params: {
                	id : id
                },
                success: function (resp) {
                    if(resp.success) {
                    	var news = resp.model;
                    	cateGoryID=news.categoryId;
                        var vals={id:cateGoryID,text:news.categoryTitle}; //知识下拉列表 默认选中值
						var ccs={id:news.company,text:news.company}; //知识下拉列表 默认选中值
                        var win = winTool.create({
                            title: "修改朋友圈",
                            height: 700,
                            width: 1500,
                            showCancel: true,
                            okName: "保存",
                            cancelName: "关闭",
                            type: "selector",
                            selector: ".friend-circle-text",
                            okFn: function (win) {
                            	var title =null,                            	
                            		words=null,
                            		onlineTime=null,
                            		sort=null,
                            		source=null,
                            		newsContent=null,
                            		imageUrl=null,
                            		imageUrl2=null,
                            		imageUrl3=null,
                            		imageUrl4=null,
                            		imageUrl5=null,
                            		imageUrl6=null,
                            		imageUrl7=null,
                            		imageUrl8=null,
                            		imageUrl9=null,
                            		categoryIdVal=null,
									pfid=null,
									company=null
                        	var flag = true;
                        	if(news.type == 1){ // type=1.图文；  隐藏文章
                        	    //  设置 标题等值title =news.title;  
                        		words= win.find(".wordsID").val(); //UE.getEditor('wordsID').getContentTxt(); //纯  html文本  getContent() 
                        		onlineTime=	win.find(".imgonlineTime").val();
                        		sort=win.find("#sort").val();
                        		//图片个数 
                        		imageUrl = win.find(".picUrlimgText").data("url");
                        		imageUrl2 = win.find(".picUrlimgText1").data("url");
                        		imageUrl3 = win.find(".picUrlimgText2").data("url");
                        		imageUrl4 = win.find(".picUrlimgText3").data("url");
                        		imageUrl5 = win.find(".picUrlimgText4").data("url");
                        		imageUrl6 = win.find(".picUrlimgText5").data("url");
                        		imageUrl7 = win.find(".picUrlimgText6").data("url");
                        		imageUrl8 = win.find(".picUrlimgText7").data("url");
                        		imageUrl9 = win.find(".picUrlimgText8").data("url");
                        		if(!imageUrl & !imageUrl2 & !imageUrl3 & !imageUrl4 & !imageUrl5 & !imageUrl6 & !imageUrl7 & !imageUrl8 & !imageUrl9){
                            		messageTool.error("至少上传一张图片!");
                            		flag = false;
                            	}
                        	    categoryIdVal=win.find(".categorySelByImg").select2("val");
								pfid=win.find("#pfid").val();
								company=win.find("#company").val();
							}else{ //文章
                            	title = win.find("#title").val();
                            	if(!title){
                            		messageTool.error("必须输入标题!");
                            		flag = false;
                            	} else {
                            		if(title.length > 128){
                                		messageTool.error("标题不能超过128个字!");
                                		flag = false;
                                	}
                            	}
                            	newsContent = win.edit.getValue();
                            	source=win.find(".source").val();
                        	    words= win.find(".mywords").val(); // UE.getEditor('mywords').getContentTxt(); // html文本 
                         		onlineTime = win.find(".onlineTime").val();
                        		sort=win.find(".sort").val();
                        		categoryIdVal=win.find(".categorySel").select2("val");
                        		//文章  图片个数 
                        		imageUrl = win.find(".picUrl").data("url")
								pfid=win.find(".pfid").val();
								company=win.find(".company").val();
                        		if(source && source.length > 50){
                            		messageTool.error("来源不能超过50个字!");
                            		flag = false;
                            	}
                            	if(!imageUrl){
                            		messageTool.error("至少上传一张图片!");
                            		flag = false;
                            	}
                            }
                        	if(!onlineTime){
                        		messageTool.error("必须选择发布时间!");
                        		flag = false;
                        	}
                        	if(!sort){
                        		messageTool.error("请输入排序!(数字)");
                        		flag = false;
                        	}else if(isNaN(sort)){
                        		   messageTool.error("排序请输入数字!");
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
                                    url: serverHost + "/friendCircle/updateImgsInfo.json",
                                    params: {
                                    	fid: id,
                                    	categoryId:categoryIdVal,
                                    	title: title,
                                    	pic1: imageUrl,
                                    	pic2: imageUrl2,
                                    	pic3: imageUrl3,
                                    	pic4: imageUrl4,
                                    	pic5: imageUrl5,
                                    	pic6: imageUrl6,
                                    	pic7: imageUrl7,
                                    	pic8: imageUrl8,
                                    	pic9: imageUrl9,
                                    	content: newsContent,
                                    	source: source,
                                    	sort: sort,
                                    	words:words,
                                    	height:heightImg,
                                        width:widthImg,
                                    	publishTime:  onlineTime!= "" ?  onlineTime + " 00:00:00" : "",
										company:company,
										pfid:pfid,
                                    }, 
                                    success: function (resp) {
                                        if(resp.success) {
                                        	messageTool.success("更新成功!");
                                        	win.close();
                                        	thiz.list.reloadPageData();
//                                        	setTimeout(function(){window.location.reload();}, 1200);
                                        } else {
                                        	messageTool.error("更新失败!");
                                        }
                                    }
                                }); 
                        	}
                        }
                     }); //创建 修改框
                      
                    	thiz.rendenWin(win);
//                    	clickBtnClose(); //按钮关闭 
                    	
                    	if(news.type == 1){ // type=1.图文；  隐藏文章
                			win.find(".circleTextclass").css("display","none");
                			win.find(".imgTextclass").css("display","block"); // 图文显示
                        	//时间渲染  图文
                        	dateTool.renderDateTime(".imgonlineTime");
                		}else{ //2.文章
                			win.find(".circleTextclass").css("display","block"); //显示文章
                			win.find(".imgTextclass").css("display","none");
                			//时间渲染 文章
                        	dateTool.renderDateTime(".onlineTime");
                		}
                    	
                		if(news.type == 1){ // 图文  有多个图片 win.find("#wordsID")
                			//setContent("wordsID",news.words); //设置文字 
                			win.find(".wordsID").val(news.words);
                     		win.find(".imgonlineTime").val(util.dateFormat2(news.publishTime, 'YYYY-MM-DD HH:mm:ss'));
                     		win.find("#sort").val(news.sort);
							win.find("#pfid").val(news.pfid);
                     		//图片 第一张 
                			if(news.pic1){
                    			win.find(".picUrlimgText").attr("src",news.filePath+news.pic1);	
                    			win.find(".picUrlimgText").data("url",news.pic1);
                    			$(".uploadImgText").on("click", ".remove-imgText", function() {
                    					$(".picUrlimgText").attr("src", "");
                    					$(".picUrlimgText").data("url", "");
                    					//$(".picUrlimgText").remove(); //图片框
                    					//$(".remove-imgText").remove();//删除按钮
                    				}); 
                    		} 
                		    //  多张， 1-9个// 图片 显示div 拼接字符串  下标从1开始   
                			 var filePath="";
                			 if(news.pic2!=null){
                				 filePath= news.filePath+news.pic2;
                				 addIMgDiv(filePath,news.pic2,1);
                			 }
                			 if(news.pic3!=null && news.pic3!=""){
                				 filePath= news.filePath+news.pic3;
                				 addIMgDiv(filePath,news.pic3,2);
                			 }
                			 if(news.pic4){
                				 filePath= news.filePath+news.pic4;
                				 addIMgDiv(filePath,news.pic4,3);
                			 }
                			 if(news.pic5){
                				 filePath= news.filePath+news.pic5;
                				 addIMgDiv(filePath,news.pic5,4);
                			 }
                			 if(news.pic6){
                				 filePath= news.filePath+news.pic6;
                				 addIMgDiv(filePath,news.pic6,5);
                			 }
                			 if(news.pic7){
                				 filePath= news.filePath+news.pic7;
                				 addIMgDiv(filePath,news.pic7,6);
                			 }
                			 if(news.pic8){
                				 filePath= news.filePath+news.pic8;
                				 addIMgDiv(filePath,news.pic8,7);
                			 }
                			 if(news.pic9){
                				 filePath= news.filePath+news.pic9;
                				 addIMgDiv(filePath,news.pic9,8);
                			 }
                			 
                    		//图片 addimgText
                    		$(".addimgText").html(strBuilder.join(""));
                    		
                    	    commonTool.renderFriend({
        						msg : "选择对应分类标签",
        						selector : win.find(".categorySelByImg"),
        						val:vals,
        						width : "60%",
        						paramFn : function() {
        							/*return {
        								permission: true
        							};*/
        						},
        						listeners : {
        							change : function() {
        								 
        							}
        						}
                	       });

							$("#company option").each(function() {
								if ($(this).val() == news.company) {
									$(this).attr("selected", "selected");
									return;
								}
							});
						}else{ //文章有一个
                		    win.find("#title").val(news.title);
                     	    //setContent("mywords",news.words); //设置文字
                     	    win.find(".mywords").val(news.words);
                     	    win.find(".onlineTime").val(util.dateFormat2(news.publishTime, 'YYYY-MM-DD HH:mm:ss'));
                     		win.find(".source").val(news.source),
                     		win.find(".sort").val(news.sort);
							win.find(".pfid").val(news.pfid);
                     		// type  1.图文；2.文章
                    		win.edit.setValue(news.content);

                     	    //图片
                			if(news.pic1){
                    			win.find(".picUrl").attr("src",news.filePath+news.pic1);	
                    			win.find(".picUrl").data("url",news.pic1);
                    		}
                			
                		    commonTool.renderFriend({
        						msg : "选择对应分类标签",
        						selector : win.find(".categorySel"),
        						val:vals,
        						width : "60%",
        						paramFn : function() {
        							/*return {
        								permission: true
        							};*/
        						},
        						listeners : {
        							change : function() {
        							}
        						}
                	       });
							$(".company option").each(function() {
								if ($(this).val() == news.company) {
									$(this).attr("selected", "selected");
									return;
								}
							});
                		}

                    } else {
                    	messageTool.error("此信息已不存在!");
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
	   	 	/*win.find(".timeBox").on("click", function(){
	       	 	if($(this).is(':checked')){
	    			win.find(".timeFlag").css("display","none");
	    		} else {
	    			win.find(".timeFlag").css("display","block");
	    		}
	   	 	});*/
 
			// 上传图片  文章
			win.find(".infos-images").on("click", function() {
				imageUploadTool.init({
					title : "上传图片",
					attach : 5,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1, //图片个数 
					extData: {
                        size: "100x100"
                    },
					callback : function(images) {
						var img = images.length > 0 ? images[0] : {};
						$(".picUrl").attr("src", img.fileUrl);
						$(".picUrl").data("url", img.fileName);
					}
				});
			});
			// 文章  图片上传 
			win.find(".uploadImg").on("click", ".remove-img", function() {
				$(".picUrl").attr("src", "");
				$(".picUrl").data("url", "");
			});
			
			//图文 上传图片  var imgNum=0;imgNum=images.length; //获取图片总条数
			win.find(".infos-imagesText").on("click", function() {
				widthImg=null,heightImg=null; //赋空 宽高 
				imageUploadTool.init({
					title : "上传图片",
					attach : 5,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 9, //图片个数 
					extData: {
                        //size: "220x170"
                    },
					callback : function(images) {
						var img = images.length > 0 ? images[0] : {};
						var strBuilder = []; 
						if(images.length==1){
							widthImg=images[0].width,heightImg=images[0].height;
						}
						$.each(images,function(i){ 
							if(i==0){
								$(".picUrlimgText").attr("src", images[i].fileUrl);
								$(".picUrlimgText").data("url", images[i].fileName);
								// 图文 删除 
								win.find(".uploadImgText").on("click", ".remove-imgText", function() {
									$(".picUrlimgText").attr("src", "");
									$(".picUrlimgText").data("url", "");
								});
							}else{
			                   var imgStr='<img style="height: 100px;width: 100px;" src="'+images[i].fileUrl+'" data-url="'+images[i].fileName+'" data-id="" class="picUrlimgText'+i+'"/>';		  
			                       imgStr+='<a class="btn btn-primary remove-imgText'+i+'">删除</a>'   
								//添加图片路径
//								$(".picUrlimgText"+i).data("url", images[i].fileName);
								// 图文 删除 
								win.find(".uploadImgText").on("click", ".remove-imgText"+i, function() {
									$(".picUrlimgText"+i).attr("src", "");
									$(".picUrlimgText"+i).data("url", "");
									$(".picUrlimgText"+i).remove(); //图片框
									$(".remove-imgText"+i).remove();//删除按钮
								});
								//添加多个图片
								strBuilder.push(imgStr); 
							}
						})
						
						//图片 addimgText
						$(".addimgText").html(strBuilder.join(""));
					}
				});
			});
        },
        
        getParams: function () {
            return {
            	id:$(".sid").val(), //ID
            	title: $(".stitle").val(), //标题
            	type: $(".ftype").val(), //分类 
            	status: $(".sAuditStatus").val(), //状态 
            	publishTime: $(".sOnlineTime").val() != "" ?  $(".sOnlineTime").val() + " 00:00:00" : "",
            };
        }
    };
    return userList;
});

//添加图片 div 
 var strBuilder = [];
 function addIMgDiv(filePath,picInp,i){
	if(picInp){ 
		 var imgStr='<img style="height: 100px;width: 100px;" src="'+filePath+'" data-url="'+picInp+'" data-id="" class="picUrlimgText'+i+'"/>';		  
             imgStr+='<a class="btn btn-primary remove-imgText'+i+'">删除</a>' 
			// 图文 删除 
			 $(".uploadImgText").on("click", ".remove-imgText"+i, function() {
				$(".picUrlimgText"+i).attr("src", "");
				$(".picUrlimgText"+i).data("url", "");
				$(".picUrlimgText"+i).remove(); //图片框
				$(".remove-imgText"+i).remove();//删除按钮
			}); 
		}
		//添加多个图片
		strBuilder.push(imgStr);
 }
 
 // 设置 文字
/* function setContent(ueId,value) {
	   UE.getEditor(ueId).addListener("ready", function () {
		   	UE.getEditor(ueId).setContent(value);
	   });
 }*/
