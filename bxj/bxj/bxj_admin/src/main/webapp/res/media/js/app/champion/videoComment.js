define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, dateTool, messageTool, commonTool) {
    var videoCommentList = {
    		list:function(){
	            var thiz = this,
	            	id = commonTool.getUrlParam("id");
	            console.info(id);
            list = listTool.create({
            	title: "音/视频评论列表",
                selector: ".data-list",
                remote: true,
                showCheckbox: true,
                url: serverHost + "/champion/commentDetailList.json?videoId=" + id,
                width: 600,
                height: 1500,
                columns: [
                    {
                    	name: "id",
                    	width:80,
                    	fieldName: "fid"		
                	},
                	{
                    	name: "音视频id",
                    	width:100,
                    	fieldName: "videoId"
                	},
                    {
                        name: "用户id",
                        width:120,
                        fieldName: "userId"
                    },
                    {
                        name: "用户昵称",
                        width:120,
                        fieldName: "nickName"
                    },
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
                        name: "审核时间",
                        width:80,
                        fieldName: "auditAt",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        name: "点赞数",
                        width:100,
                        fieldName: "clickLikeCount"
                    },
                    {
                        name: "评论内容",
                        width:200,
                        fieldName: "comment"
                    },
                    {
                        name: "评论时间",
                        width:80,
                        fieldName: "createAt",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    }
                    /*{
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	var st = '<div class="action-buttons">' +
                        			 '<div class="btn-group">' + 
                        			 '<button class="btn btn-danger delete">删除</button>' +
                        			 '</div>' +
                        			 '</div>';
                        	return st;
                        }
                    }*/
                	],
                paramFn: function () {
                	return thiz.getParams();
                },
               
                initSearch: function (query) {
                	
                },

                /** 按钮 **/
                tbars: [{
                    icon: 'fa fa-plus',
                    name: "批量删除",
                    rightCode: "delete",
                    handler: function (idx, data) {
                        thiz.delete_bat();
                    }
                }],

                /*classEvents : [
                {
	            	className : "delete",
	            	rightCode: "delete",
	            	handler: function (idx, data) {
	                    thiz.delete1(data);
	                }
                },
                ]*/
            });
            this.list = list;
        	
       	 	//数字框
        	$(".userId").on('keyup', function (event) {
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
    		});
        	
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },

        // 批量删除
        delete_bat: function(data) {
            var thiz = this;
            var selected = thiz.list.getSelected() || [],
                idList = [];
            if (selected.length == 0) {
                messageTool.error("没有选中任何评论！");
                return;
            }
            $(selected).each(function (idx, data) {
                idList.push(data.fid);
            });
            util.request({
                confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/champion/delete_bat.json",
                params: {
                    ids: idList.toString()
                },
                success: function (resp) {
                    if(resp.success) {
                        messageTool.success("删除成功");
                        thiz.list.reloadPageData();
                    } else {
                        messageTool.error(resp.msg || "此记录已不存在");
                        thiz.list.reloadPageData();
                    }
                }
            });
        },
        
        // 审核通过
        delete1: function(data){
        	var thiz = this;
        	var fid = data.fid;
        	util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/champion/deleteVideoComment.json",
                params: {
                	fid: fid
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("删除成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error(resp.msg || "此记录已不存在");
                    	thiz.list.reloadPageData();
                    }
                }
            });
        	
        },

        getParams: function () {
            return {
            	comment: $(".comment").val(),
            	userId: $(".userId").val()
            };
        },
    };
    return videoCommentList;
});