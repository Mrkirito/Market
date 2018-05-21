define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
	
	var list = listTool.create({
    	title: "文章分类",
        selector: "#data-list",
        remote: true,
        url: serverHost + "/studyType/list.json",
        width: 600,
        height: 1500,
        tbars: [{
            icon: 'fa fa-plus',
            name: "新增",
            handler: function () {
                location = 'studyType/create.jhtml';
            }
        }],
        columns: [
            {
            	name: '主键',
            	fieldName: 'id',
            	width:50
            },
            {
        		
        		name: '分类名',
        		fieldName: 'name',
        		renderer : function(row, col, data,value) {
                	if(data.display){
                		return data.name;
                	} else {
                		return "<span class='label label-danger' title='已删除'>" + data.name + "<i></i></span>";
                	}
                }
        	}, {
        		
        		name: '文章数',
        		fieldName: 'articlesCount',
        		width:50
        	}, {
        		name: '权重',
        		fieldName: 'sort',
        		width: 30
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
                width:150,
                renderer : function(rindx, cindex, data) {
                	var row = '';
                	if(data.display) {
                		row += '<button class="btn btn-danger delete">删除</button>';
                	} else {
                		row += '<button class="btn btn-success updateDisplayStatus">还原</button>';
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
        	return {};
        },
        
        classEvents: [{
        	className : "updateDisplayStatus",
        	handler: function (i, data) {
        		
        		var oper;
        		if (data.display) {
					oper = '隐藏';
				} else {
					oper = '还原';
				}
        		
        		util.request({
            		confirm: true,
                    msg: "确认" + oper + "文章分类：编号[" + data.id + "] " + data.name + "？",
                    url: "studyType/updateDisplayStatus.json",
                    params: {
                    	typeId: data.id,
                    	display: !data.display
                    },
                    success: function (resp) {
                    	list.reloadPageData();
                    },
                    failure: function(resp) {
                    	messageTool.error("操作失败 [" + resp.status + "]");
                    }
                });
            }
        }, {
        	
        	className: 'showUpdate',
        	handler: function(i, data) {
        		location = 'studyType/update.jhtml?typeId=' + data.id;
        	}
        }, {
        	className: 'delete',
        	handler: function(i, data) {
        		if (data.articlesCount > 0) {
        			messageTool.info("不可删除分类 [" + data.name + '] 因为其分类中还有 ' + data.articlesCount + ' 篇文章。');
        			return;
				}
        		
        		util.request({
            		confirm: true,
                    msg: "确认删除分类：编号[" + data.id + "] " + data.name + "？请谨慎执行。",
                    url: "studyType/delete.json",
                    params: {
                    	typeId: data.id
                    },
                    success: function (resp) {
                    	list.reloadPageData();
                    },
                    failure: function(resp) {
                    	messageTool.error("删除失败 [" + resp.status + "]");
                    }
                });
        	}
        }]
    });
	
});