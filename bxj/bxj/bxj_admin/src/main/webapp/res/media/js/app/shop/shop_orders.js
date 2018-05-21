define(['jquery',
        'app/common/util',
        'app/common/advanceListTool',
        'app/common/winTool',
        'app/common/dateTool',
        'app/common/messageTool',
        'app/common/imageUploadTool',
        'app/common/editorTool',
        'app/common/imageView',
        'app/common/exportTool',
        'app/common/commonTool'
    ],

    function ($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, _,exportTool,commonTool) {
		var AjaxStatus = {			
				0: function(json, opts) {
					if (opts.onSuccess) {
						opts.onSuccess(json);
					}
				},			
				500: function(json, opts) {
					messageTool.info('导入数据失败：'+json.msg);
				},			
				302: function(json) {
					
				}			
		};
        var shopOrders={
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "订单详细列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/shopOrders/queryOrderDetailsList.json",
                        orderBy: "m.create_time asc",
                        columns: [
//                            {
//                                name: "序号",
//                                renderer : function(row, col, data,value) {
//                                    return row + 1;
//                                }
//                            },
//                            {
//                                name: "主键",
//                                width: "20",
//                                sort: true,
//                                sortName: "shop_orders.id",
//                                fieldName: "id"
//                            },
                            {
                                name:"购买人ID",
                                fieldName: "userId",
                                renderer : function(row, col, data,value) {
                                	if(value){
                                		return value;
                                	}else{
                                		return '总计';                                		
                                	}
                                }
                            },
                            {
                                name:"商品名称",
                                fieldName: "goodsName"
                            },
                            {
                                name:"订单时间",
                                fieldName: "createTime",
                                renderer : function(row, col, data,value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name:"订单号",
                                fieldName: "orderId"
                            },
                            {
                                name:"支付类型",
                                fieldName: "payType"
                            },
                            {
                                name:"购买数量",
                                fieldName: "quantity"
                            },
                            {
                                name:"商品金额(元)",
                                fieldName: "goodsMoney"
                            },
                            {
                                name:"物流金额(元)",
                                fieldName: "expressMoney"
                            },
                            {
                                name:"折扣金额(元)",
                                fieldName: "discountMoeny"
                            },
                            {
                                name:"总金额(元)",
                                fieldName: "totalMoney"
                            },
                            {
                                name:"收货人姓名",
                                fieldName: "name"
                            },
                            {
                                name:"收货人手机号",
                                fieldName: "phone"
                            },
                            {
                                name:"地区",
                                fieldName: "area"
                            },
                            {
                                name:"详细地址",
                                fieldName: "address"
                            },
                            {
                                name:"快递公司",
                                fieldName: "expressCompany"
                            },
                            {
                                name:"快递单号",
                                fieldName: "expressNo"
                            },
                            {
                                name:"备注",
                                fieldName: "remark"
                            }],

                        /** 搜索条件 **/
                        paramFn: function () {
                            return thiz.getParams();
                        },
                        initSearch: function (query) {
                        },
                        /*导出excel按钮*/
                        tbars: [
                            {
                                type: "group",
                                icon: "fa fa-gear fa-lg",
                                name: "操作",
                                btns: [
                                    {
                                        icon: 'fa fa-file-excel-o',
                                        name: "导出excel",
                                        rightCode: "export",
                                        handler: function (e, btn, tool) {
                                            var action = serverHost + "/shopOrders/exportExcel.json";
                                            exportTool.export(action, tool.getTotalCount(), thiz.getParams(), tool);
                                        }
                                    },
                                    {
                                        icon: 'fa fa-file-excel-import-o',
                                        name: "导入excel",
                                        /*rightCode: "import",*/
                                        handler: function (e, btn, tool) {
                                           	var thiz = this;
                            	        	var win = winTool.create({
                            	                title: "导入快递单",
                            	                height: 300,
                            	                width: 500,
                            	                showCancel: true,
                            	                okName: "保存",
                            	                cancelName: "关闭",
                            	                type: "selector",
                            	                selector: ".video-tpl",
                            	                okFn: function (win) {
                            	                	messageTool.confirm2('确认导入当前EXCEL文件',function(){            				
                                        				var form=win.find('#insure_info');
                                        				shopOrders.ajaxUpload(form.get(0),{
                                        					onSuccess:function(data){
                                        						messageTool.info(data.msg);
                                        						win.close();
                                        						shopOrders.list.reloadPageData();
                                        					}
                                        				});
                                        			});						
                            					}
                            	        	});
                                        }
                                    }
                                ]
                            }
                        ]
                    });
                thiz.list = list;


                /*类别下拉框*/
                commonTool.renderShopGoodsType({
                    msg : "类别",
                    selector : $(".shopGoodsType"),
                    width : "100%",
                    paramFn : function() {
                        return {
                            permission: true
                        };
                    },
                    listeners : {
                        change : function() {

                        }
                    }
                });

                //时间渲染
                dateTool.renderDateRange(".createTime", {
                    applyClick : function(startDate, endDate) {
                        $(".startTime").val(startDate);
                        $(".endTime").val(endDate);
                    }
                });

                var currDate = dateTool.getCurrentDate("YYYY-MM-DD");
                $(".createTime").val(currDate + " ~ " + currDate);
                $(".startTime").val(currDate);
                $(".endTime").val(currDate);

                /** 搜索按钮事件 **/
                $(".search-data").on("click", function () {
                    thiz.list.reloadPageData(1);
                });

                $(".status").on("change", function () {
                    thiz.list.reloadPageData(1);
                });

                /** 重置按钮 **/
                $(".reset-data").on("click", function () {
                    $(".createTime").val("");
                    $(".startTime").val("");
                    $(".endTime").val("");
                    $(".orderId").val("");
                    $(".name").val("");
                    $(".phone").val("");
                    $(".payType").val("");
                    //$(".status").val("");
                    $("#shopGoodsType").val("");
                    $('#select2-chosen-1').html('');
                });
            },

            /** 搜索条件 **/
            getParams: function () {
                return {
                    startTime: $(".startTime").val(),
                    endTime: $(".endTime").val(),
                    orderId: $(".orderId").val(),
                    name: $(".name").val(),
                    phone: $(".phone").val(),
                    payType: $(".payType").val(),
                    //status: $(".status").val(),
                    goodsId: $("#shopGoodsType").val()
                }
            },
        	ajaxUpload:function(form, opts) {
        		opts = opts || {};
        		var formData = new FormData(form);
        		if (opts.onData) {
        			opts.onData(formData);
        		}
        		var xhr = new XMLHttpRequest();
        		xhr.open("POST", form.action);    		
        		xhr.upload.addEventListener("progress", function(e) {
        			if (e.lengthComputable) {
        	            var percentComplete = Math.round(e.loaded * 100 / e.total) + '%';
        	        }
        		});
        		
        		xhr.addEventListener("error", function() {
        			messageTool.error(opts.errMsg || '访问异常，请稍后重试');
        		}, false);
        		
        		xhr.onload=function(e) {
        			var json = eval('(' + this.response + ')');
        			shopOrders.validateSsouser(json);
        			var cb = AjaxStatus[json.status];
        			if (!cb) {
        				messageTool.error('程序错误，错误代码：' + json.status);
        			}
        			
        			cb(json, opts);
        		};
        		
        		xhr.send(formData);
        	},
        	ajaxTodo:function(opts) {
        		opts = $.extend({    			
        			beforeSend: function() {

        			},
        			success: function(json) {
        				if(shopOrders.validateSsouser(json)) {
        					var cb = AjaxStatus[json.status];
        					if (!cb) {
        						
        					}
        					
        					cb(json, opts);
        				}
        			},
        			
        			complete: function() {

        			},
        			
        			error: function() {
        				
        			}
        			
        		}, opts);
        		
        		$.ajax(opts);
        		
        	},
        	validateSsouser:function(data) {
        		if(data.ssotype) {
        			window.location.href = decodeURIComponent(data.redirectUrl);
        			return false;
        		}
        		return true;
        	}
        };
        return shopOrders;
    });

