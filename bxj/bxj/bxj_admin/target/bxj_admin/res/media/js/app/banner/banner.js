
String.prototype.startWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length)
   return false;
  if(this.substr(0,s.length)==s)
     return true;
  else
     return false;
  return true;
};

define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
	function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
		initForwardOptions('#sel_forwardType');
		dateTool.renderDate("#onlineTime");
		dateTool.renderDate("#offlineTime");
		
		var currentShowType = undefined;
		
		var dataTable = listTool.create({
			title: "数据列表",
            selector: "#data-list",
            remote: true,
            height: 500,
            url: serverHost + "/banner/list.json",
            columns: [{
            	name: 'ID',
            	fieldName: 'id'
            }, {
            	name: '图片',
            	fieldName: 'imageUrl',
            	renderer: function(row, col, data, value) {
            		var size = imageSize[currentShowType];
            		return '<img src="' + value + '" width="' + (size.width / 2) + '" height="' + (size.height / 2) + '">';
            	}
            },{
            	name: '标题',
            	fieldName: 'forwardTitle'
            }, {
            	name: '跳转链接',
            	fieldName: 'pageUrl',
            	renderer: function(row, col, data, value) {
            		var title = undefined;
            		for (var i in forwardTypies) {
						var item = forwardTypies[i];
						if (item.parse) {
							var r = item.parse(value);
							if (r && r.match) {
								return '<a href="' + value + '" target="_BLANK">【' + item.name + '】' + value + '</a>';
							}
						}
					}
            		
            		if (value.startWith('http')) {
            			return '<a href="' + value + '" target="_BLANK">【链接】' + value + '</a>';
					}
            		
            		return value;
            	}
            }, {
            	name: '状态',
            	fieldName: 'statusMessage',
            	renderer: function(row, col, data, value) {
            		if (value == 'always') {
            			return "<span class='label label-table label-success'>长期上线<i></i></span>";
					} else if (value == 'await') {
						return "<span class='label label-table label-info'>待上线<i></i></span>";
					} else if (value == 'onlined') {
						return "<span class='label label-table label-warning'>已上线（长期）<i></i></span>";
					} else if (value == 'onlining') {
						return "<span class='label label-table label-danger'>上线中（期限）<i></i></span>";
					} else if (value == 'offlined') {
						return "<span class='label label-table label-primary'>已下线<i></i></span>";
					}
//            		if (onlineTime == null) {
//            			
//            			if (offlineTime == null) {
//            				return "永久上线";
////            				return "always";
//            			}
//            			
//            		} else {
//            			
//            			if (onlineTime.after(now)) {
//            				return "待上线";
////            				return "await";
//            			}
//            			
//            			if (offlineTime == null) {
//            				return "已上线";
////            				return "onlined";
//            			}
//            			
//            		}
//            		
//            		if (offlineTime.after(now)) {
//            			return "上线中";
////            			return "onlining";
//            		} else {
//            			return "已下线";
////            			return "offlined";
//            		}
            	}
            }, {
            	name: '版本',
            	fieldName: 'versionMessage',
            	renderer: function(row, col, data, value) {
            		if (value == 'all') {
            			return "<span class='label label-table label-success'>通用<i></i></span>";
					} else {
						return "<span class='label label-table label-warning'>" + value + "<i></i></span>";
					}
            	}
            }, {
            	name: '上线时间',
            	fieldName: 'onlineTime'
            }, {
            	name: '下线时间',
            	fieldName: 'offlineTime'
            }, {
            	name: '操作',
                renderer : function(row, col, data, value) {
                	
                	var btn = '<button class="btn btn-info update">编辑</button>';
                	
                	if (data.display) {
						btn += '<button class="btn btn-danger updateDisplayStatus">删除</button>';
					} else {
						btn += '<button class="btn btn-success updateDisplayStatus">还原</button>';
					}
                	return btn;
                }
            }],
            
            paramFn: function() {
            	var ary = $('#form_query-params').serializeArray(), result = {};
            	for (var i = 0; i < ary.length; i++) {
					var p = ary[i];
					result[p.name] = p.value;
				}
            	currentShowType = result.showType;
            	location.hash = currentShowType;
            	
            	if (result.forwardType) {
            		var item = forwardTypies[result.forwardType];
            		if (item.getStart) {
						result.forwardType = item.getStart();
					} else {
						result.forwardType = item.getForwardUrl();
					}
            	}
            	
            	return result;
            },
            
            tbars: [{
                icon: 'fa fa-plus',
                name: "新增",
//                rightCode: "add",
                handler: function (idx, data) {
//                	var win = winTool.create({
//                        title: "新增",
//                        height: 500,
//                        width: 800,
//                        showCancel: true,
//                        okName: "保存",
//                        cancelName: "关闭",
//                        type: "selector",
//                        selector: "#banner-form",
//                	});
//                	
//                	$('#form_in_onlineTime,#form_in_offlineTime').addClass("datetimepicker-icon").attr("readonly", true).datetimepicker({
//                		minuteStep: 5,
//                        format: 'yyyy-mm-dd hh:ii',
//                        autoclose: true,
//                        clearBtn:true,
//                        todayBtn: false,
//                        language: "zh-CN",
//                        todayHighlight: true,
//                        startDate: new Date()
//                	});
                	location = 'banner/create.jhtml?showType=' + $('#sel_showType').val();
                }
            }],
            
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
                        msg: "确认" + oper + "：编号[" + data.id + "] " + data.forwardTitle + "？",
                        url: serverHost + "/banner/updateDisplayStatus.json",
                        params: {
                        	showType: currentShowType,
                        	id: data.id,
                        	status: !data.display
                        },
                        success: function (resp) {
                        	dataTable.reloadPageData();
                        },
                        failure: function(resp) {
                        	messageTool.error("删除失败 [" + resp.status + "]");
                        }
                    });
                }
            }, {
            	
            	className: 'update',
            	handler: function(i, data) {
            		location = 'banner/update.jhtml?showType=' + currentShowType + '&bannerId=' + data.id;
            	}
            }]
            
		});
		
		$('#search-action').on('click', function() {
			dataTable.reloadPageData(1);
		});
		
		$('#sel_forwardType option').each(function() {
			var $this = $(this),
				type = $this.attr('type'),
				key = $this.attr('value'),
				title = $this.html();
			if (type == 'eq') {
				eqShowType[key] = title;
			} else if (type == 'startWith') {
				startWithShowType[key] = title;
			}
		});
		
            
//        $('#form_in_onlineTime,#form_in_offlineTime').addClass("datetimepicker-icon").attr("readonly", true).datepicker({
//        	minuteStep: 5,
//            format: 'yyyy-mm-dd hh:ii'
//        });
		
		
	}

);