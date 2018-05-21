define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
	function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
  var userList = {
	 list:function(){
	   var thiz = this,
	   list = listTool.create({
		title: "【冠军论坛】峰会讲师列表",
        selector: "#data-list",
        remote: true,
        height: 500,
        url: serverHost + "/summit/listTeachers.json",
        columns: [
        {
        	name: "峰会名称",
        	width:100,
        	fieldName: "summitName",
    	},{
        	name: '图片',
        	width:130,
        	fieldName: 'imgFileUrl',
        	renderer : function(row, col, data,value) {
        		return '<a href="' + value + '" rel="lightbox"><img src="' + value + '" width="130" height="115"></a>';
        	}
        },
    	{
        	name: '峰会讲师ID',
        	width:50,
        	fieldName: "id"
        },  {
        	name: "讲师姓名",
        	width:30,
        	fieldName: "teacherName",
    	}, {
        	name: "标题",
        	width: 200,
        	fieldName: "title"
    	},{
        	name: "排序值",
        	width: 50,
        	fieldName: "sort",
        	 
    	},{
        	name: "状态",
        	width: 50,
        	renderer : function(row, col, data,value) {	
        		if(data.display=='true') {
            		return  '<span class="label label-table label-success"><i>已上线</i></span>';
            	} else if(data.display=='false') {
            		return  '<span class="label label-table label-danger"><i>已下线</i></span>';
            	} else{
            		return  '<span class="label label-table label-warning" ><i>正在进行</i></span>';
            	}
        	}
    	}, {
        	name: "真实点击数",
        	width: 50,
        	fieldName: "clickReal",
        	 
    	},
    	{
        	name: "虚拟点击数",
        	width: 50,
        	fieldName: "clickSham",
        	 
    	},
    	{
        	name: "操作",
        	width:100,
        	renderer : function(row, col, data,value) {
        		var row = '';
            	if(data.display=='true'||data.display=='ing') {
            		row += '<button class="btn btn-danger deleteDisplay">下线</button>';
            	} else if(data.display=='false') {
            		row += '<button class="btn btn-info updateDisplay">上线</button>';
            	} 
            	row += '<button class="btn btn-warning showUpdate">修改</button>';
            	return row;
            }
    	}],
    	paramFn: function () {
        	return thiz.getParams();
        },
        initSearch: function (query) {
        	
        },
        tbars: [
//           { icon: 'fa fa-plus',
//            name: "新增峰会讲师",
//            rightCode: "add",
//            handler: function (idx, data) {
//                thiz.addInfos(); 
//            } }
        ],
        classEvents : [ 
           {
        	className : "showUpdate",
        	handler: function (idx, data) {
                thiz.update(data); //修改  信息
            }
          },
          {
          	className : "deleteDisplay",
          	handler: function (idx, data) {
                  thiz.deleteSummit(data,"false"); //修改 状态 
              }
           },
           {
             	className : "updateDisplay",
             	handler: function (idx, data) {
                     thiz.deleteSummit(data,"true"); //修改峰会 
                 }
             },
            /* {
              	className : "addPPT",
              	handler: function (idx, data) {
                      thiz.addPPTInfo(data); //新增ppt  
                  }
              },*/
             
        ],
	});
	   this.list = list;
 	//搜索
     $("#search-action").on("click", function () {
     	list.reloadPageData(1);
     });
   }, // list:function() 结束 
   
  getParams: function () {
	  if(isNaN($(".sid").val())){
 		   messageTool.error("ID请输入数字!");
 		   return false;
	  }
      return {
    		id:$(".sid").val(), //ID
    	    title: $(".lookText").val(),
    	    name:$(".lookName").val(),
    	    display:$(".display").val()==""?null:$(".display").val(),
    	    
      };
   },
   
   rendenWin : function(win){
	 //时间渲染  
   	//dateTool.renderDate(".beginAt");
   	// 上传图片    
		win.find(".infos-images").on("click", function() {
			imageUploadTool.init({
				title : "上传图片",
				attach : 6,
				acceptedFiles : ".png,.jpg,.jpeg",
				maxFiles : 1, //图片个数 
				extData: {
//	               size: "100x100"
	           },
				callback : function(images) {
					var img = images.length > 0 ? images[0] : {};
					$(".picUrl").attr("src", img.fileUrl);
					$(".picUrl").data("url", img.fileName);
				}
			});
		});
		// 删除  图片上传 
		win.find(".uploadImg").on("click", ".remove-img", function() {
			$(".picUrl").attr("src", "");
			$(".picUrl").data("url", "");
		});
		
		$(".addimgText").html(""); //清空 add图片div内容
		 var strBuilder = ""; //图片字符串
		/*多个ppt图片*/
		win.find(".infos-imagesPPT").on("click", function() {
			imageUploadTool.init({
				title : "上传图片",
				attach : 6,
				acceptedFiles : ".png,.jpg,.jpeg",
				//maxFiles : 1000, //图片个数 
				extData: {
                   //size: "220x170"
               },
				callback : function(images) {
					var img = images.length > 0 ? images[0] : {};
					strBuilder= "";
					$.each(images,function(i){ 
						$(".addimgText").html(""); //清空 add图片div内容
		                    strBuilder+='<img style="height:100px;width:100px;"  src="'+images[i].fileUrl+'" data-url="'+images[i].fileName+'"  class="picUrlimgPPT'+i+'"/>';		  
		                    strBuilder+='<a class="btn btn-primary remove-imgPPT'+i+'">删除</a>'; 
							//   删除 
							win.find(".uploadImgPPT").on("click", ".remove-imgPPT"+i, function() {
								$(".picUrlimgPPT"+i).attr("src", "");
								$(".picUrlimgPPT"+i).data("url", "");
								$(".picUrlimgPPT"+i).remove(); //图片框
								$(".remove-imgPPT"+i).remove();//删除按钮
							});
							 
					})
					
					//图片 addimgText
					$(".addimgText").html(strBuilder);
				}
			});
		});
		/*多个ppt图片结束 */
   },

	
   //添加讲师
   addInfos : function(data){
      var thiz = this;
  	var win = winTool.create({
          title: "新增峰会讲师信息",
          height: 700,
          width: 1500,
          showCancel: true,
          okName: "保存",
          cancelName: "关闭",
          type: "selector",
          selector: ".page-ChampTeacher",
          okFn: function (win) {
          	var title=win.find(".title").val(),
          		imageUrl = win.find(".picUrl").data("url"),
          		teachername=win.find(".teachername").val(),
          		sort = win.find(".sort").val(),
          	    clikReal = win.find(".clikReal").val(),
          	    cliksham=win.find(".cliksham").val(),
          	    sdisplay=win.find(".sdisplayTeacher").val()
                  	 
          	var flag = true;
          	if(!title){
          		messageTool.error("必须输入标题!");
          		flag = false;
          	} else {
          		if(title.length > 48){
              		messageTool.error("标题不能超过48个字!");
              		flag = false;
              	}
          	}
        	if(!teachername){
          		messageTool.error("必须输入姓名!");
          		flag = false;
          	} else {
          		if(teachername.length > 8){
              		messageTool.error("姓名不能超过8个字!");
              		flag = false;
              	}
          	}
        	if(!sort){
          		messageTool.error("请输入排序!(数字)");
          		flag = false;
          	}else if(isNaN(sort)){
          		   messageTool.error("排序请输入数字!");
          		   flag = false;
          	}else if(sort.length > 3){
          		messageTool.error("排序不能超过3位!");
          		flag = false;
          	}
        	if(!clikReal){
          		messageTool.error("请输入真实点击数!(数字)");
          		flag = false;
          	}else if(isNaN(clikReal)){
          		   messageTool.error("真实点击数请输入数字!");
          		   flag = false;
          	}
        	if(!cliksham){
          		messageTool.error("请输入虚拟点击数!(数字)");
          		flag = false;
          	}else if(isNaN(cliksham)){
          		   messageTool.error("虚拟点击数请输入数字!");
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
                      url: serverHost + "/summit/saveOrUpdateTeacher.jhtml",
                      params: {
                    	title: title,
                      	imageUrl: imageUrl,
                      	display: sdisplay,
                      	sort:sort,
                      	teacherName:teachername,
                      	clickReal:clikReal,
                      	clickSham:cliksham
                        
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
   },
   //修改
  update : function(data){
	// 图片集合
	var imgsArray = [];
	var thiz = this,
		id = data.id;
	util.ajax({
       url: serverHost + "/summit/listOneTeacher.json",
       params: {
       	id : id
       },
       success: function (resp) {
           if(resp.success) {
           	var summitObj = resp.model;
           	var win = winTool.create({
                   title: "修改峰会讲师信息",
                   height: 700,
                   width: 1500,
                   showCancel: true,
                   okName: "保存",
                   cancelName: "关闭",
                   type: "selector",
                   selector: ".page-ChampTeacher",
                   okFn: function (win) {
                		var title=win.find(".title").val(),
                  		imageUrl = win.find(".picUrl").data("url"),
                  		teachername=win.find(".teachername").val(),
                  		sort = win.find(".sort").val(),
                  	    clikReal = win.find(".clikReal").val(),
                  	    cliksham=win.find(".cliksham").val(),
                  	    sdisplay=win.find(".sdisplayTeacher").val()
                          	 
                  	var flag = true;
                  	if(!title){
                  		messageTool.error("必须输入标题!");
                  		flag = false;
                  	} else {
                  		if(title.length > 48){
                      		messageTool.error("标题不能超过48个字!");
                      		flag = false;
                      	}
                  	}
                	if(!teachername){
                  		messageTool.error("必须输入姓名!");
                  		flag = false;
                  	} else {
                  		if(teachername.length > 8){
                      		messageTool.error("姓名不能超过8个字!");
                      		flag = false;
                      	}
                  	}
                	if(!sort){
                  		messageTool.error("请输入排序!(数字)");
                  		flag = false;
                  	}else if(isNaN(sort)){
                  		   messageTool.error("排序请输入数字!");
                  		   flag = false;
                  	}else if(sort.length > 3){
                  		messageTool.error("排序不能超过3位!");
                  		flag = false;
                  	}
                	if(!clikReal){
                  		messageTool.error("请输入真实点击数!(数字)");
                  		flag = false;
                  	}else if(isNaN(clikReal)){
                  		   messageTool.error("真实点击数请输入数字!");
                  		   flag = false;
                  	}
                	if(!cliksham){
                  		messageTool.error("请输入虚拟点击数!(数字)");
                  		flag = false;
                  	}else if(isNaN(cliksham)){
                  		   messageTool.error("虚拟点击数请输入数字!");
                  		   flag = false;
                  	}
                  	
                  	if(!imageUrl){
                  		messageTool.error("至少上传一张讲师图片!");
                  		flag = false;
                  	}
                    //图片数组
                  	imgsArray.length =0;
                  	var imgs = $(".addimgText").find('img');
                  	for(var i=0;i<imgs.length;i++){  
                  		var val=win.find(".picUrlimgPPT"+i).data("url");
                  		if(val)imgsArray.push(val);  
                  	}
                    var htmlVal=$(".addimgText").html(); 
                 	if(imgsArray.length==0 && !htmlVal){
                  		messageTool.error("至少上传一张ppt图片!");
                  		flag = false;
                  	}
                 	var imgsArrayStr = JSON.stringify(imgsArray); 

               	if(flag){
                   	util.request({
                   		confirm: true,
                           msg: "确定要保存?",
                           url: serverHost + "/summit/saveOrUpdateTeacher.jhtml",
                           params: {
                        	id:id,
                        	title: title,
                          	imageUrl: imageUrl,
                          	display: sdisplay,
                          	sort:sort,
                          	teacherName:teachername,
                          	clickReal:clikReal,
                          	clickSham:cliksham,
                          	imgsInput:imgsArrayStr
                           
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
                	
                	win.find("#sname").html(data.summitName),
            		win.find(".title").val(summitObj.title),
               		win.find(".picUrl").attr("src",summitObj.imgFileUrl);	//图片全路径 
        			win.find(".picUrl").data("url",summitObj.imageUrl);
               	    win.find(".teachername").val(summitObj.teacherName),
               	    win.find(".sort").val(summitObj.sort),
               	    win.find(".clikReal").val(summitObj.clickReal),
                    win.find(".cliksham").val(summitObj.clickSham),
               	    //状态
               	    win.find(".sdisplayTeacher").val(summitObj.display)
               	    
					$(".addimgText").html(""); //清空 add图片div内容
					var strBuilder = [];
					var showImgs=summitObj.showImages;
					for(var i=0;i<showImgs.length;i++){
		                    var imgStr='<a href="' + showImgs[i] + '" rel="lightbox"><img style="height: 120px;width: 120px;"  src="'+showImgs[i]+'"  /></a>';		  
							strBuilder.push(imgStr); //添加多个图片
					}
					//图片 addimgText
					$(".addimgText").html(strBuilder.join(""));
               	    
           } else {
            	messageTool.error("此信息已不存在!");
           }
       }
    }); //请求结束
	
  }, //修改结束
  
  // 已下线 上线
  deleteSummit: function(data,state){
  	var thiz = this;
  	var id = data.id;
  	var msginfo= state=="true" ? "确定要上线?": "确定要下线?";
  	util.request({
  		confirm: true,
          msg:msginfo,
          url: serverHost + "/summit/saveOrUpdateTeacher.jhtml",
          params: {
          	id: id,
          	display: state
          },
          success: function (resp) {
              if(resp.success) {
            	  if(state){
            		  messageTool.success("上线成功!");
            	  }else{
            		  messageTool.success("下线成功!");
            	  }
              	thiz.list.reloadPageData();
              } else {
              	messageTool.error("此记录已不存在");
          		thiz.list.reloadPageData();
              }
          }
      });
  }, //下线结束 

  // 新增ppt 
  /*addPPTInfo:function(data){
	  alert(data.id);
  }*/
  
 } // userList 结束 
  return userList;
});