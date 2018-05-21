define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool','app/common/messageTool','app/common/editorTool','app/common/commonTool'],function($,util, listTool, winTool,  messageTool, editorTool,commonTool) {
			var list_column = [
					{
						name : "编号",
						width : 30,
						fieldName : "fid"
					},
					{
						name : "产品名称",
						width : 100,
						fieldName : "name"
					},
					{
						name : "产品简称",
						width : 80,
						fieldName : "cpjc"
					},
					{
						name : "基本保额",
						width : 30,
						fieldName : "jbbe"
					},
					{
						name : "起始年龄",
						width : 30,
						fieldName : "tbnlks"
					},
					{
						name : "结束年龄",
						width : 30,
						fieldName : "tbnljs"
					},
					{
						name : "默认年龄",
						width : 30,
						fieldName : "hmstate"
					},
					{
						name : "上下架状态",
						width : 30,
						fieldName : "sxj",
						renderer : function(row, col, data, value) {
							if (value == 0) {
								return "<span class='label label-table label-danger'>已下架<i></i></span>";
							} else if (value == 1) {
								return "<span class='label label-table label-info'>已上架<i></i></span>";
							}
						}
					},
					{
						name : "险种",
						width : 30,
						fieldName : "xz",
						renderer : function(row, col, data, value) {
							if (value == 1) {
								return "<span class='label label-table label-danger'>主险<i></i></span>";
							} else if (value == 0) {
								return "<span class='label label-table label-info'>附加险<i></i></span>";
							}
						}
					},
					{
						name : "保费计算方式",
						width : 100,
						fieldName : "calflag",
						renderer : function(row, col, data, value) {
							switch (value) {
							case 3:
								return "<span class='label label-table label-danger'>互算<i></i></span>";
							case 2:
								return "<span class='label label-table label-info'>保费算保额<i></i></span>";
							default:
								return "<span class='label label-table label-primary'>保额算保费<i></i></span>";
								break;
							}
						}
					},
					{
						name : "公司",
						width : 80,
						fieldName : "gsName"
					},
					{
						name : "产品标签",
						width : 30,
						fieldName : "cplb"
					},
					{
						name : "产品描述",
						width : 80,
						fieldName : "cpms"
					},
					{
						name : "产品分类标签",
						width : 100,
						fieldName : "categroys",
						renderer : function(row, col, data, value) {
							var a=[];
							$.each(value,function(i,o){
								a[i]=o.cname;
							});
							return a.join(',');
						}
					},
					{
						name : "附加险开关",
						width : 80,
						fieldName : "cdmc",
						renderer : function(row, col, data, value) {
							if (value == 0) {
								return "<span class='label label-table label-primary'>关<i></i></span>";
							} else if (value == 1) {
								return "<span class='label label-table label-warning'>开<i></i></span>";
							}
						}
					} ];
    var _currentRowData=null;
	var userList = {
    	list:function(){
	        var thiz = this,
	        list = listTool.create({
	            	title: "产品列表",
	                selector: ".data-list",
	                remote: true,
	                url: serverHost + "/product/queryListProduct.json",
                    height: 800,
                    width: 800,
	                showCheckbox: false,
	                multiSelect: false,
	                rowClick: function (rowIndex, rowData, checked) {	
	                	_currentRowData=rowData;
	                	return;
	                },
	                rowDblclick: function (rowIndex, rowData) {
	                	userList.detail(rowData);
	                	return;
	                },
	                columns:list_column,
	                paramFn: function () {
		                	var name=$('input[name=name]').val();
		                	if(name){
		                		return {name:name}; 
		                	}else{
		                		var gs=$('select[name=gs]').val();
		                		if(gs&&(gs==38||gs==51)) gs='';
		                		var sxj=$('select[name=sxj]').val();
		                		var xz=$('select[name=xz]').val();
		                		return {gs:gs,sxj:sxj,xz:xz};
		                	}
		            }
	        });	     
            this.list = list;
            $('button.search').click(function(){
            	list.reloadPageData();
            });
            $('button.add').click(function(){
            	userList.add();
            });
            $('button.update').click(function(){
            	userList.update(_currentRowData);
            });
            $('button.qy_detail').click(function(){
            	userList.qy_detail(_currentRowData);
            });
            $('button.qy_update').click(function(){
            	userList.qy_update(_currentRowData);
            });
        },
		richText : function(win) {
			win.mc = editorTool.init({
        		selector: win.find("#mc"),
        		width: 600,
        		heigth:500,
        		minHeight: 500,
        		lang: 'en-US'
        	});
			win.bbrqy = editorTool.init({
        		selector: win.find("#bbrqy"),
        		width: 600,
        		heigth:500,
        		minHeight: 500,
        		lang: 'en-US'
        	});
			win.cpts = editorTool.init({
        		selector: win.find("#cpts"),
        		width: 600,
        		heigth:500,
        		minHeight: 500,
        		lang: 'en-US'
        	});
		},
        add :function(){
        	var thiz=this;
        	var win = winTool.create({
                title: "新增产品",
                height: 700,
                width: 800,
                showCancel: true,
                cancelName: '关闭',
                okName: "保存",
                type: "selector",
                selector: "div.popup_add_div",
                okFn: function (win) {
                	var mc=win.mc.getValue(),cpts=win.cpts.getValue(),bbrqy=win.bbrqy.getValue();
                	$('input[name=mc]').val(mc);
                	$('input[name=cpts]').val(cpts);
                	$('input[name=bbrqy]').val(bbrqy);
                	var para=win.find('form.product_add_form').serialize();
                	util.request({
                		confirm: true,
                        msg: "确定要新增当前产品?",
                        url: serverHost + "/product/updateProduct.json",
                        params: para,
                        success: function (resp) {messageTool.info("产品新增成功", function(){win.close();thiz.list.reloadPageData();});}
                    });
            	}
             });
        	// 渲染下拉框
			commonTool.renderCategroy({
				msg : "选择分类",
				selector : win.find(".cate_fid"),
				width : "100%",
				multiple:true,
				paramFn : function() {
					return {};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
        	win.find('input[name=gs]').val(1);
        	userList.richText(win);
        },
        detail: function(data){
        	var fid = data.fid;
        	util.ajax({
                url: serverHost + "/product/queryProductDetail.json",
                params: {fid: fid},
                success: function (resp) {
                   if(resp.success) {
                    	var data = resp.model;
                    	var win = winTool.create({
                            title: "产品详情",
                            height: 700,
                            width: 800,
                            showCancel: false,
                            okName: "关闭",
                            type: "selector",
                            selector: "div.popup_add_div",
                            okFn: function (win) {
                            	win.close();
                            }
                         });
	                   	 win.find('input,select').each(function(i,o){
	                 		var tagName = o.tagName;
	                 		o=$(o);
	                 		var fieldName=o.attr('name');
	                 		var val=data[fieldName];
	                 		o.val(val).attr('disabled','disabled');
	                 	 });
	                 	userList.richText(win);
	                 	win.mc.setValue(data.mc);
	                 	win.cpts.setValue(data.cpts);
	                 	win.bbrqy.setValue(data.bbrqy);
                    } else {
                    	messageTool.error("此记录已不存在", function(){});
                    }
                }
            });
        },
        update: function(data){
        	var thiz=this;
        	if(data){
        		var fid = data.fid;
        		var cpjc= data.cpjc;
        		util.ajax({
        			url: serverHost + "/product/queryProductDetail.json",
        			params: {fid: fid},
        			success: function (resp) {
        				if(resp.success) {
        					var data = resp.model;
        					var win = winTool.create({
        						title: "【"+cpjc+"】编辑产品",
        						height: 700,
        						width: 800,
        						showCancel: true,
        						cancelName: '关闭',
        						okName: "保存",
        						type: "selector",
        						selector: "div.popup_add_div",
        						okFn: function (win) {
        		                	var mc=win.mc.getValue(),cpts=win.cpts.getValue(),bbrqy=win.bbrqy.getValue();
        		                	$('input[name=mc]').val(mc);
        		                	$('input[name=cpts]').val(cpts);
        		                	$('input[name=bbrqy]').val(bbrqy);
        		                	var tags = win.find(".cate_fid").select2("data") || [];
        		                	var tagIds = "";
        		                	$(tags).each(function () {
        		                		if(tagIds != ""){
        		                			tagIds = tagIds + ",";
        		                		}
        		                		tagIds = tagIds + this.id;
        		                	});
        							var para=win.find('form.product_add_form').serialize()+"&categroyIds="+tagIds;
        		                	util.request({
        		                		confirm: true,
        		                        msg: "确定要更改当前产品?",
        								url: serverHost + "/product/updateProduct.json",
        								params: para,
        								success: function (resp) {
        									messageTool.info("产品修改成功", function(){
        										win.close();
        										thiz.list.reloadPageData();
        									});
        								}
        		                    });
        						}
        					});
        					var tagList = data.categroys;
        					var tagVal=[];
                        	$(tagList).each(function () {
        	                	var tag = {
        	                		id : this.fid,
        	                		text : this.cname
        	                	}
        	                	tagVal.push(tag);
        	                });
        		        	// 渲染下拉框
        					commonTool.renderCategroy({
        						msg : "选择分类",
        						selector :  win.find(".cate_fid"),
        						width : "100%",
        						multiple:true,
        						val:tagVal,
        						paramFn : function() {
        							return {};
        						},
        						listeners : {
        							change : function() {
        								
        							}
        						}
        					});
        					win.find('input,select').each(function(i,o){
        						var tagName = o.tagName;
        						o=$(o);
        						var fieldName=o.attr('name');
        						var val=data[fieldName];
        						o.val(val);
        					});
    	                 	userList.richText(win);
    	                 	win.mc.setValue(data.mc);
    	                 	win.cpts.setValue(data.cpts);
    	                 	win.bbrqy.setValue(data.bbrqy);
        				} else {
        					messageTool.error("此记录已不存在", function(){});
        				}
        			}
        		});        	
        	}else{
        		messageTool.error("请选择一个产品...", function(){});
        	}
        },
        qy_update:function(data){
        	if(data){        		
        		var fid = data.fid;
        		var pname=data.cpjc;
        		util.ajax({
        			url: serverHost + "/product/queryProductQy.json",
        			params: {fid: fid,type:0},
        			success: function (resp) {
        				if(resp.success) {
        					var data = resp.model;
        					var win = winTool.create({
        						title: "【"+pname+"】修改权益",
        						height: 700,
        						width: 800,
        						showCancel: true,
        						cancelName: '关闭',
        						okName: "保存",
        						type: "selector",
        						selector: "div.popup_qy_update_div",
        						okFn: function (win) {
        							var arry=new Array();
        							win.find('div.form-body').children().each(function(i,o){
        								o=$(o);
        								var obj=new Object();
        								var head1=o.find('input[name=head1]').val();
        								obj.head1=head1?head1:'';
        								var head2=o.find('input[name=head2]').val();
        								obj.head2=head2?head2:'';
        								var head3=o.find('input[name=head3]').val();
        								obj.head3=head3?head3:'';
        								arry.push(obj);
        							});
        							var para=JSON.stringify(arry);
        							util.ajax({
        								url: serverHost + "/product/updateProductQy.json",
        								params: {fid:fid,adqys:para},
        								success: function (resp) {
        									messageTool.info("权益修改成功", function(){win.close();});
        								}
        							});
        						}
        					});
        					var dom='';
        					for (var j = 0; j < data.length; j++) {
        						var o=data[j];
        						dom+="<div><input name='head1' value='"+o.head1+"' /><input name='head2' value='"+(o.head2?o.head2:'')+"' /><input name='head3' value='"+(o.head3?o.head3:'')+"' /><button class='delete_qy'>删除</button></div>";
        					}
        					win.find("div.form-body").html(dom);
        					win.find('button.delete_qy').click(function(){
        						$(this).parent().remove();
        					});
        					win.find('#add_qy').click(function(){
        						win.find("div.form-body").append("<div><input name='head1' value='' /><input name='head2' value='' /><input name='head3' value='' /><button class='delete_qy_'>删除</button></div>");
        						win.find('.delete_qy_').click(function(){$(this).parent().remove();});
        					});
        				} else {
        					messageTool.error("此记录已不存在", function(){});
        				}
        			}
        		});
        	}else{
        		messageTool.error("请选择一个产品...", function(){});
        	}
        },
        qy_detail:function(data){
        	if(data){        		
        		var fid = data.fid;
        		var pname=data.cpjc;
        		util.ajax({
        			url: serverHost + "/product/queryProductQy.json",
        			params: {fid: fid,type:1},
        			success: function (resp) {
        				if(resp.success) {
        					var data = resp.model;
        					var win = winTool.create({
        						title: "【"+pname+"】预览权益",
        						height: 700,
        						width: 800,
        						showCancel: false,
        						okName: "关闭",
        						type: "selector",
        						selector: "div.popup_div",
        						okFn: function (win) {win.close();}
        					});
        					var dom='';
        					for (var j = 0; j < data.length; j++) {
        						var o=data[j];
        						dom+='<div class="result_block font_block">'+o.head1+(o.head2?o.head2:'')+(o.head3?o.head3:'')+'</div>';
        					}
        					win.find("div.form-body").html(dom);
        				} else {
        					messageTool.error("此记录已不存在", function(){});
        				}
        			}
        		}); 
        	}else{
        		messageTool.error("请选择一个产品...", function(){});
        	}
        }
    };
    return userList;
});