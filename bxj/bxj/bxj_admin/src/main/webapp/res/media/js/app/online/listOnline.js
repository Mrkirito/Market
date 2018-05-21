define(
		[ 'jquery', 'app/common/util', 'app/common/advanceListTool',
				'app/common/dateTool', 'app/common/messageTool',
				'app/common/commonTool', 'app/common/winTool',
				'app/common/imageView','app/common/imageUploadTool'],
    function($, util, listTool, dateTool, messageTool, commonTool, winTool,imageView,imageUploadTool) {
	var listJson=[
	              {
	            	  name: "课程id",
	            	  width:10,
	            	  fieldName: "id"
	              },
	              {
	            	  name: "课程父id",
	            	  width:10,
	            	  fieldName: "parentId"
	              },
	              {
	            	  name: "类型",
	            	  width:20,
	            	  fieldName: "type",
	            	  renderer : function(row, col, data,value) {
	            		  return value==1?'<span class="label label-table label-danger">总场<i></i></span>':'<span class="label label-table label-info">分场<i></i></span>';
	            	  }
	              },
	              {
	            	  name: "标题",
	            	  width:800,
	            	  fieldName: "title"
	              },
	              {
	            	  name: "上下线",
	            	  width:80,
	            	  fieldName: "isDisplay",
	            	  renderer : function(row, col, data,value) {
	            		  return value==1?'<span class="label label-table label-danger">上线<i></i></span>':'<span class="label label-table label-info">下线<i></i></span>';
	            	  }
	              },
	              {
	            	  name: "销售状态",
	            	  width:80,
	            	  fieldName: "salasStatus",
	            	  renderer : function(row, col, data,value) {
	            		  switch (value) {
	            		  case 1:
	            			  return "<span class='label label-table label-warning'>正在直播<i></i></span>";
	            		  case 2:
	            			  return "<span class='label label-table label-info'>火热预约<i></i></span>";
	            		  case 3:
	            			  return "<span class='label label-table label-primary'>已爆满<i></i></span>";
	            		  case 4:
	            			  return "<span class='label label-table label-primary'>已结束<i></i></span>";
	            		  case 5:
	            			  return "<span class='label label-table label-danger'>精彩回放<i></i></span>";	
	            		  default:
	            			  break;
	            		  }
	            	  }
	              },
	              {
	            	  name: "讲师姓名",
	            	  width:80,
	            	  fieldName: "lecturerName"
	              },
	              {
	            	  name: "直播开始时间",
	            	  width:80,
	            	  fieldName: "liveStartTime",
	            	  renderer : function(row, col, data,value) {
	            		  return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
	            	  }
	              },                            
	              {
	            	  name: "直播持续时间",
	            	  width:80,
	            	  fieldName: "liveDureTime"
	              },
	              {
	            	  name: "原价格",
	            	  width:80,
	            	  fieldName: "originalPrice"
	              },
	              {
	            	  name: "销售价格",
	            	  width:70,
	            	  fieldName: "salePrice"
	              },
	              {
	            	  name: "团购价",
	            	  width:70,
	            	  fieldName: "groupPrice"
	              },
	              {
	            	  name: "团购人数",
	            	  width:70,
	            	  fieldName: "groupCount"
	              },
	              {
	            	  name: "省份",
	            	  width:70,
	            	  fieldName: "province"
	              },
	              {
	            	  name: "排序",
	            	  width:70,
	            	  fieldName: "sort"
	              },
	              {
	            	  name: "图片1",
	            	  width:100,
	            	  fieldName: "pic1",
	            	  renderer : function(row, col, data,value) {
	            		  if(value){                                		
	            			  value=public_path+"/"+value;
	            			  return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="80px" height="60px"></img></a>';
	            		  }
	            		  return '';
	            	  }
	              },
	              {
	            	  name: "图片2",
	            	  width:100,
	            	  fieldName: "pic2",
	            	  renderer : function(row, col, data,value) {
	            		  if(value){                                		
	            			  value=public_path+"/"+value;
	            			  return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="80px" height="60px"></img></a>';
	            		  }
	            		  return '';
	            	  }
	              },
	              {
	            	  name: "图片3",
	            	  width:100,
	            	  fieldName: "pic3",
	            	  renderer : function(row, col, data,value) {
	            		  if(value){                                		
	            			  value=public_path+"/"+value;
	            			  return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="80px" height="60px"></img></a>';
	            		  }
	            		  return '';
	            	  }
	              },
	              {
	            	  name: "图片4",
	            	  width:100,
	            	  fieldName: "pic4",
	            	  renderer : function(row, col, data,value) {
	            		  if(value){                                		
	            			  value=public_path+"/"+value;
	            			  return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="80px" height="60px"></img></a>';
	            		  }
	            		  return '';
	            	  }
	              },
	              {
	            	  name: "图片5",
	            	  width:100,
	            	  fieldName: "pic5",
	            	  renderer : function(row, col, data,value) {
	            		  if(value){                                		
	            			  value=public_path+"/"+value;
	            			  return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="80px" height="60px"></img></a>';
	            		  }
	            		  return '';
	            	  }
	              },
	              {
                      name: "操作",
                      width:100,
						rightFixed: true,
                      renderer : function(rindx, cindex, data) {
                      	var commonButton = '<a class="green update" href="javascript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-140 ">编辑</i> </a>&nbsp;';
                      	var st3 = '<div class="action-buttons" style="width:150px"> ' +commonButton +'</div>';
                      	return st3;
                      }
                  }
	              ];
        var online = {
            list:function(){
                var thiz = this,
                    list = listTool.create({
                        title: "H5直播课程列表",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost + "/online/getOnlineCourseData.json",
                        width: 600,
                        height: 600,
                        columns: listJson,
                        paramFn: function () {
                            return {
                            	title: $(".title").val(),
                            	id: $(".id").val(),
                            	lecturerName: $(".lecturerName").val(),
                            }
                        },
                        initSearch: function (query) {

                        },
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增",
//                            rightCode: "add",
                            handler: function (idx, data) {
                                thiz.addVideo();
                            }
                        }],
                        classEvents : [               
                {
	            	className : "update",
//	            	rightCode: "update",
	            	handler: function (idx, data) {
	                    thiz.update(data);
	                }
                }
                        ]
                    });
                this.list = list;
                //搜索
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            },
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
                    		var isValidateTrue=true;
                    		win.find('input.is_number').each(function(i,o){
                    			o=$(o);
                    			var v=o.val();
                    			if(isNaN(v)){
                    				isValidateTrue=false;
                    				messageTool.error(o.parent().siblings().text()+'必须为数字');
                    				return false;
                    			}
                    		});
                    		if(isValidateTrue){                    			
                				var lecturerName =win.find('input[name=lecturerId]').select2("data")?win.find('input[name=lecturerId]').select2("data").text:'';// 讲师名称
                            	win.find('input[name=lecturerName]').val(lecturerName);
                    			util.request({
                    				confirm : true,
                    				msg : "确定要新增课程?",
                    				url : serverHost+ "/online/insertOnlineCourse.json",
                    				params : win.find('#form').serialize(),
                    				success : function(resp) {
                    					messageTool.info("课程新增成功", function() {
                    						win.close();
                    						thiz.list.reloadPageData();
                    					});
                    				}
                    			});
                    		}
                    }
                 });
            	
                     	win.find(".infos-images").each(function(i,o){
                    		o=$(o);
                    		o.on("click", function() {
                    			imageUploadTool.init({
                    				title : "上传图片",
                    				attach : 8,
                    				acceptedFiles : ".png,.jpg,.jpeg",
                    				maxFiles : 1,
                    				callback : function(images) {
                    					var img = images.length > 0 ? images[0] : {};
                    					o.next().val(img.fileName);
                    					o.parent().find(".picUrl").attr("src", public_path+img.fileName);
                    				}
                    			});
                    		});
                    	});
    			win.find(".uploadImg").each(function(i,o){  
    				o=$(o);
    				o.on("click", ".remove-img", function() {
    					var k=o.parent();
    					k.find(".picUrl").attr("src", "");
    					k.parent().find('input').val('');
    				});
    			});
    			dateTool.renderDateTime("input[name=liveStartTime]");
    			commonTool.renderOnlineLectures({
    				msg : "选择讲师",
    				selector : win.find("input[name=lecturerId]"),
    				width : "100%",
    				paramFn : function() {return {};},
    				listeners : {change : function() {}
    				}
    			});
            },
            update:function(data){
               	var thiz = this,
        		fid = data.id;
               	util.ajax({
                url: serverHost + "/online/getOnlineCourse.json",
                params: {id : fid},
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
                				var lecturerName =win.find('input[name=lecturerId]').select2("data")?win.find('input[name=lecturerId]').select2("data").text:'';// 讲师名称
                            	win.find('input[name=lecturerName]').val(lecturerName);
                            	var paras=win.find('#form').serialize();
                          		var isValidateTrue=true;
                        		win.find('input.is_number').each(function(i,o){
                        			o=$(o);
                        			var v=o.val();
                        			if(isNaN(v)){
                        				isValidateTrue=false;
                        				messageTool.error(o.parent().siblings().text()+'必须为数字');
                        				return false;
                        			}
                        		});
                        		if(isValidateTrue){                             	
	                            	util.request({
	                            		confirm: true,
	                                    msg: "确定要更改课程?",
	                                    url: serverHost + "/online/updateOnlineCourse.json",
	                                    dataType:"json",
	                                    params:paras ,
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
                    	win.find('input,select').each(function(i,o){
                    		o=$(o);
                    		var name=o.attr('name');
                    		var val=video[name];
                    		if(name=='isDisplay'){
                    			o.val(val?1:0);
                    		}else if(name.indexOf('pic')==0 && val){
                    			o.parent().find('.picUrl').attr('src', public_path+"/"+val);
                    			o.val(val);
                    		}else if(name=='liveStartTime'){
                    			o.val(util.dateFormat2(val, 'YYYY-MM-DD HH:MM:SS'));
                    		}else{
                    			o.val(val);
                    		}
                    	});
                    	win.find(".infos-images").each(function(i,o){
                    		o=$(o);
                    		o.on("click", function() {
                    			imageUploadTool.init({
                    				title : "上传图片",
                    				attach : 8,
                    				acceptedFiles : ".png,.jpg,.jpeg",
                    				maxFiles : 1,
                    				callback : function(images) {
                    					var img = images.length > 0 ? images[0] : {};
                    					o.next().val(img.fileName);
                    					o.parent().find(".picUrl").attr("src", public_path+img.fileName);
                    				}
                    			});
                    		});
                    	});
            			win.find(".uploadImg").each(function(i,o){  
            				o=$(o);
            				o.on("click", ".remove-img", function() {
            					var k=o.parent();
            					k.find(".picUrl").attr("src", "");
            					k.parent().find('input').val('');
            				});
            			});
            			dateTool.renderDateTime("input[name=liveStartTime]");
            			commonTool.renderOnlineLectures({
            				msg : "选择讲师",
            				selector : win.find("input[name=lecturerId]"),
            				width : "100%",
            				val : {id:video.lecturerId,text:video.lecturerName},
            				paramFn : function() {return {};},
            				listeners : {
            					change : function() {
            						
            					}
            				}
            			});
                   }  	
                }
            });	
            }
        };
        return online;
    });