define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
	if (!module) {
		return;
	}
	var list = listTool.create({
    	title: "文章列表",
        selector: "#data-list",
        remote: true,
        url: serverHost + "/study/list.json",
        width: 600,
        height: 1500,
        tbars: [{
            icon: 'fa fa-plus',
            name: "新增",
            handler: function () {
                location = 'study/create.jhtml';
            }
        }],
        columns: [
            {
            	name: '主键',
            	fieldName: 'id',
            	width:50
            },
            {
            	name: "标题",
            	fieldName: "title",
            	renderer : function(row, col, data,value) {
            		var link = '';
            		if (data.displayType == 'TOP') {
						link += "<span class='label label-danger'>顶<i></i></span>";
					}
            		
            		link += '<a';
            		// 不显示（被删除）的文章显示为红色，容易区分
            		if (!data.display) {
						link += ' style="color:red"';
					}
            		link += ' href="' + studyWebPath + data.id + '" target="_BLANK">' + data.title + '</a>';
                	return link;
                }
        	}, {
        		
        		name: '分类',
        		fieldName: 'typeName',
        		width:50
        	}, {
        		
        		name: '点击数',
        		fieldName: 'clickTimesReal',
        		width:50
        	}, {
        		
        		name: '评论数',
        		fieldName: 'commentCount',
        		width:50
        	}, {
        		name: '权重',
        		fieldName: 'sort',
        		width: 30
        	}, {
        		
        		name: '发布时间',
        		fieldName: 'publishAt',
        		width:100
        	}, {
        		
        		name: '创建时间',
        		fieldName: 'createAt',
        		width:100
        	}, {
        		
        		name: '最后修改时间',
        		fieldName: 'updateAt',
        		width:100
        	}, {
                name: "操作",
                width:130,
                renderer : function(rindx, cindex, data) {
                	var row = '';
                	if(data.display) {
                		row += '<button class="btn btn-danger updateDisplayStatus">删除</button>';
                	} else {
                		row += '<button class="btn btn-info updateDisplayStatus">还原</button>';
                	}
                	row += '<button class="btn btn-warning showUpdate">修改</button>';
                	return row;
                }
            }
        ],
        
        paramFn: function() {
        	
        	var ary = $('#form_query-params').serializeArray();
        	var p = {};
        	for (var i = 0; i < ary.length; i++) {
        		var item = ary[i];
				p[item.name] = item.value;
			}
        	return p;
        },
        
        classEvents: [{
        	className : "updateDisplayStatus",
        	handler: function (i, data) {
        		
        		var oper;
        		if (data.display) {
					oper = '删除';
				} else {
					oper = '还原';
				}
        		
        		util.request({
            		confirm: true,
                    msg: "确认" + oper + "文章：编号[" + data.id + "] " + data.title + "？",
                    url: "study/updateDisplayStatus.json",
                    params: {
                    	articleId: data.id,
                    	display: !data.display
                    },
                    success: function (resp) {
                    	list.reloadPageData();
                    },
                    failure: function(resp) {
                    	messageTool.error("删除失败 [" + resp.status + "]");
                    }
                });
            }
        }, {
        	
        	className: 'showUpdate',
        	handler: function(i, data) {
        		location = 'study/update.jhtml?articleId=' + data.id;
        	}
        }]
    });
	
	//搜索
    $("#search-action").on("click", function () {
    	list.reloadPageData(1);
    });
    
    $('#in_id').on('change', function() {
    	var $this = $(this), other = $this.parent().siblings().find('input,select');
    	
    	if ($this.val()) {
			other.attr('disabled', '');
		} else {
			other.removeAttr('disabled');
		}
    })
	    
});