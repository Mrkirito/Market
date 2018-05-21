define(
		[ 'jquery', 'app/common/util', 'app/common/advanceListTool',
				'app/common/winTool', 'app/common/messageTool',
				'app/common/editorTool', 'app/common/commonTool', 'app/common/dateTool','app/common/imageUploadTool'],
		function($, util, listTool, winTool, messageTool, editorTool,commonTool,dateTool,imageUploadTool) {
			return {
				list : function() {
					var thiz = this, list = listTool.create({
						title : "开屏广告列表",
						selector : ".data-list",
						remote : true,
						url : serverHost + "/ad/selectAds.json",
						height : 800,
						width : 800,
						columns : [
									{
										name : "主键",
										width : 30,
										fieldName : "fid"
									},
									{
										name : "广告图片",
										width : 100,
										fieldName : "imgUrl",
										renderer: function(row, col, data, value) {
						            		return '<a href="' + value + '" target="_BLANK">【图片链接】' + value + '</a>';
						            	}
									},
									{
										name : "广告图事件",
										width : 80,
										fieldName : "action",
										renderer : function(row, col, data, value) {
											if(value==1){
												return "<span class='bg-warning'>打开URL</span>";
											}else{
												return "<span class='bg-success'>无事件</span>";
											}
										}
									},
									{
										name : "启用状态",
										width : 30,
										fieldName : "status",
										renderer : function(row, col, data, value) {
											if(value==1){
												return "<span class='bg-warning'>开启</span>";
											}else{
												return "<span class='bg-success'>关闭</span>";
											}
										}
									},
									{
										name : "标题",
										width : 30,
										fieldName : "title"
									},
									{
										name : "广告链接地址",
										width : 30,
										fieldName : "openUrl",
										renderer: function(row, col, data, value) {						          
						            		return '<a href="' + value + '" target="_BLANK">【链接】' + value + '</a>';
						            	}
									},
									{
										name : "创建时间",
										width : 200,
										fieldName : "createTime",
										renderer : function(row, col, data,value) {
				                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:MM:SS');
				                        }
									},
									{
						            	name: '操作',
						            	width : 100,
						                renderer : function(row, col, data, value) {
						                	return '<button class="btn btn-info updateAd">编辑</button>';
						                }
						            }],
						paramFn : function() {
							
						},
						tbars: [{
			                icon: 'fa fa-plus',
			                name: "新增",
			                handler: function (idx, data) {
			                	thiz.addAd();
			                }
			            }],
						classEvents : [{
				          	className : "updateAd",
				           	handler: function (idx, data) {
				                thiz.updateAd(data);
				            }
			            }]
					});
					this.list = list;
				},
				addAd : function() {
					var thiz = this;
		        	var win = winTool.create({
		                title: "新增",
		                height: 600,
		                width: 600,
		                showCancel: true,
		                okName: "保存",
		                cancelName: "关闭",
		                type: "selector",
		                selector: ".headline-banner-tpl",
		                okFn: function (win) {
            				var params = win.find('#form').serialize();
		                	 util.request({
		                		 confirm: true,
		                		 msg: "确定要添加?",
		                		 url: serverHost + "/ad/addAd.json",
		                		 params: params,
		                		 success: function (resp) {
		                			 if (resp.success) {
		                				 messageTool.success("添加成功");
		                				 win.close();
		                				 thiz.list.reloadPageData();
		                			 } else {
		                				 messageTool.error("添加失败");
		                			 }
		                		 }
		                	 });
		                }
		        	});
		        	thiz.rendenWin(win);
				},
				updateAd : function(data) {
	                var thiz = this,id = data.fid;
	                util.ajax({
	                	url: serverHost + "/ad/detail.json",
	                	params: {fid: id},
	                	success: function (resp) {
	                		if(resp.success){	                			
	                			var data=resp.model,win = winTool.create({
	                				title: "编辑",
	                				height: 600,
	                				width: 600,
	                				showCancel: true,
	                				okName: "保存",
	                				cancelName: "关闭",
	                				type: "selector",
	                				selector: ".headline-banner-tpl",
	                				okFn: function (win) {
	                					var params = win.find('#form').serialize();
	                					util.request({
	                						confirm: true,
	                						msg: "确定要修改?",
	                						url: serverHost + "/ad/updateAd.json",
	                						params: params,
	                						success: function (resp) {
	                							if (resp.success) {
	                								messageTool.success("修改成功");
	                								win.close();
	                								thiz.list.reloadPageData();
	                							} else {
	                								messageTool.error("修改失败");
	                							}
	                						}
	                					});	
	                				}
	                			});
	                			thiz.rendenWin(win);
	                			if(data){
	                				win.find('input[name=fid]').val(data.fid);
	                				win.find('input[name=imgUrl]').val(data.imgUrl);
	                				win.find('input[name=title]').val(data.title);
	                				win.find('select[name=status]').val(data.status);
	                				win.find('select[name=action]').val(data.action);
	                				win.find('input[name=openUrl]').val(data.openUrl);
	                				win.find('img.picUrl').prop('src',data.imgUrl);
	                			}
	                		}
	                	}
	                })
				},
				rendenWin: function (win) {
	                win.find(".infos-images").on("click", function () {
	                    imageUploadTool.init({
	                        title: "上传图片",
	                        attach: 13,
	                        acceptedFiles: ".png,.jpg,.jpeg",
	                        maxFiles: 1,
	                        extData: {
	                     
	                        },
	                        callback: function (images) {
	                            var img = images.length > 0 ? images[0] : {};
	                            $(".picUrl").attr("src", img.fileUrl);
	                            win.find('input[name=imgUrl]').val(img.fileUrl);
	                        }
	                    });
	                });
	                win.find(".uploadImg").on("click", ".remove-img", function () {
	                    $(".picUrl").attr("src", "");
                        win.find('input[name=imgUrl]').val('');
	                }); 
	            }
			};
		});