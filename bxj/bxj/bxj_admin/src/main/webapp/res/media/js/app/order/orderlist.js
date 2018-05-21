define(['jquery','app/common/util','app/common/listTool','app/common/winTool','app/common/dateTool','app/common/messageTool','app/common/distpicker'],
    function($, util, listTool, winTool, dateTool,messageTool,distpicker) {
	var AjaxStatus = {
			0 : function(json, opts) {
				messageTool.info('导入订单保单数据成功');
				if (opts.onSuccess) {
					opts.onSuccess(json);
				}
			},
			500 : function(json, opts) {
				messageTool.error(json.error||'导入订单保单数据失败');
			},
			302 : function(json) {
			}
		}
    return {
    		list:function(){
    			var thiz=this,list=listTool.create({
    				title: "订单信息",
    				toolbarSelector:"div.product_info_tbars",
                    selector: "div.data-list",
                    remote: true,
                    url: serverHost+"/order/list.json",
                    width: 700,
                    height: 500,
                    showPagebar:true,
					showCheckbox:false,
					pageSize:15,
								columns : [
										{
											name : "订单编号",
											width : 100,
											fieldName : "orderNo"
										},
										{
											name : "订单状态",
											width : 100,
											fieldName : "orderState",
											renderer : function(row, col, data,
													value) {
												if (value == 1) {
													return "未完善";
												}
												if (value == 2) {
													return "待审核";
												}
												if (value == 3) {
													return "待支付";
												}
												if (value == 4) {
													return "已取消";
												}
												if (value == 5) {
													return "已承保";
												}
												if (value == 6) {
													return "已生效";
												}
												if (value == 7) {
													return "已失效";
												}
												return null;
											}
										},
										{
											name : "保单编号",
											width : 100,
											fieldName : "policyNumber"
										},
										{
											name : "订单创建日期",
											width : 130,
											fieldName : "createTime",
											renderer : function(row, col, data,
													value) {
												return util.dateFormat2(value,
														"");
											}
										},
										{
											name : "订单修改日期",
											width : 130,
											fieldName : "updateTime",
											renderer : function(row, col, data,
													value) {
												return util.dateFormat2(value,
														"");
											}
										},
										{
											name : "会员账号",
											width : 100,
											fieldName : "userInfoName"
										},
										{
											name : "产品名称",
											width : 100,
											fieldName : "insuranceName"
										},
										{
											name : "订单总价",
											width : 100,
											fieldName : "price",
											renderer : function(row, col, data,value) {
												return value+"元";
											}
										},
										{
											name : "保障期限",
											width : 100,
											fieldName : "duration",
											renderer : function(row, col, data,
													value) {
												if (value == "12") {
													return "1年"
												}
												if (value == "1") {
													return "1月"
												}
												return "";
											}
										},
										{
											name : "保额",
											width : 100,
											fieldName : "amount",
											renderer : function(row, col, data,value) {
												return value+"万元";
											}
										},
										{
											name : "上传保单",
											width : 100,
											fieldName : "policyUrl",
											renderer : function(row, col, data,
													value) {
												if (value != ""
														&& value != null) {
													return "是"
												} else {
													return "否"
												}
											}
										},
										{
											name : "订单是否生效",
											width : 120,
											fieldName : "takeEffect",
											renderer : function(row, col, data,value) {
												if (value == 1) {
													return "是"
												} else {
													return "否"
												}
											}
										},
										{
											name : "投保公司企查查验证",
											width : 150,
											fieldName : "insureMessage"
										},
										{
											name : "转账账户名",
											width : 100,
											fieldName : "transferAccountName"
										},
										{
											name : "被保公司企查查验证",
											width : 150,
											fieldName : "isurantMessage"
										},
										{
											name : "投保公司名称",
											width : 200,
											fieldName : "insure",
											renderer : function(row, col, data,value) {
													return value.companyName;
											}
										},
										{
											name : "被保公司名称",
											width : 200,
											fieldName : "isurant",
											renderer : function(row, col, data,value) {
													return value.companyName;
											}
										},
										{
											name : "操作",
											width : 300,
											rightFixed : true,
											renderer : function(rindx, cindex,
													data) {
												var dom = [
														'<button class="basic" title="编辑信息"><i class="fa fa-pencil-square-o bigger-150"></i></button>',
														'<button class="downOrder" title="订单下载"><i class="fa bigger-150 fa-delicious"></i></button>',
														'<button class="downOrderModel" title="订单模板下载"><i class="fa bigger-150 fa-delicious"></i></button>',
														'<button class="delOrder" title="作废订单"><i class="fa fa-pencil-square-o bigger-150"></i></button>',
														'<button class="importSinglePolicy" title="保单导入"><i class="fa bigger-150 fa-codepen"></i></button>',
														'<button class="downSinglePolicy" title="保单下载"><i class="fa bigger-150 fa-codepen"></i></button>'
														/*'<button class="updateInsureMessage" title="修改投保公司企查查验证信息"><i class="fa bigger-150 fa-empire"></i></button>',
														'<button class="updateIsurantMessage" title="修改被保公司企查查验证信息"><i class="fa bigger-150 fa-empire"></i></button>'*/ ];
												return dom.join('&nbsp;');
											}
										} ],
                    paramFn: function () {
                		return thiz.getParams();
                    },
                    initSearch: function (query) {},
                    tbars: [],
                    classEvents : [{	className : "basic",
                        handler: function (idx, data) {
                            thiz.basic(data);
                        }
                    },{	className : "importSinglePolicy",
                        handler: function (idx, data) {
                            thiz.importSinglePolicy(data);
                        }
                    },{	className : "updateInsureMessage",
                        handler: function (idx, data) {
                        	console.log(data);
                            thiz.updateInsureMessage(data);
                        }
                    },{	className : "updateIsurantMessage",
                        handler: function (idx, data) {
                            thiz.updateIsurantMessage(data);
                        }
                    },{	className : "delOrder",
                        handler: function (idx, data) {
                            thiz.delOrder(data);
                        }
                    },{	className : "downSinglePolicy",
                        handler: function (idx, data) {
                            thiz.downSinglePolicy(data);
                        }
                    },{	className : "downOrder",
                        handler: function (idx, data) {
                            thiz.downOrder(data);
                        }
                    },{	className : "downOrderModel",
                        handler: function (idx, data) {
                            thiz.downOrderModel(data);
                        }
                    }]
    			});
				this.list = list;
				$("button.search-action").on("click", function() {
					thiz.list.loadPageData(1);
				});
                $("button.clear-action").on("click", function() {
                    document.getElementById("queryForm").reset();
                });
                dateTool.renderDate(".form-date","");
    		},
		    getParams: function () {
                var d = {};
                var t = $('#queryForm').serializeArray();
                $.each(t, function() {
                	if(this.name){
                		d[this.name] = this.value;                		
                	}
                });
                return d;
		    },
		    /*employee:function(id){
				var thiz=this,employeeGrade = listTool.create({
					title : "员工信息",
					selector : "div.data-list-employee",
					remote : true,
					url : serverHost + "/employee/list.json?id=" + id,
					width : 500,
					height : 500,
					showPagebar : false,
					showCheckbox : false,
					columns : [ {
						name : "员工姓名",
						width : 60,
						fieldName : "name"
					}, {
						name : "员工证件类型",
						width : 60,
						fieldName : "cardTypeId",
						renderer : function(row, col, data, value) {
							if (value == 0) {
								return "身份证";
							}
							if (value == 1) {
								return "护照";
							}
							if (value == 2) {
								return "军官证";
							}
							if (value == 3) {
								return "港澳通行证";
							}
							return "其他";
						}
					}, {
						name : "员工证件号码",
						width : 60,
						fieldName : "cardno"
					}, {
						name : "员工职业类别",
						width : 60,
						fieldName : "vocation",
						renderer : function(row, col, data, value) {
							return value + "类";
						}
					}, {
						name : "员工生日",
						width : 60,
						fieldName : "birthday"
					}, {
						name : "员工性别",
						width : 40,
						fieldName : "gender",
						renderer : function(row, col, data, value) {
											if(value == 1){
												return "男"
											}
											if(value == 0){
												return "女"
											}
											return null;
										}
									} ],
									tbars : []
							});
				thiz.employee = employeeGrade;
			},*/
		    basic: function(data){
                var thiz = this,id = data.id;
                var win = winTool.create({
                    title: "修改订单信息",
                    height: 700,
                    width: 1000,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".video-tpl",
                    okFn: function (win) {
                        var isValidateTrue=true;
                        var orderState; 
                        var receiptType; 
                        win.find('select,input').each(function(i,o){
                        	var name = o.name;
                        	if(name=="orderState"){
                        		orderState =$(o).val();
                        	}
                        	if(name=="insure.receiptType"){
                        		receiptType =$(o).val();
                        		if(null == receiptType){
                        			return true;
                        		}
                        	}
                            o=$(o);
                            var isDisabled = $(o).prop("disabled");
                            if(!isDisabled){
                            	if((name == "cancelReason" || name=="refundTime") && orderState != 4){
                            		return true;
                            	}
                            	if(name == "policyNumber"){
                            		return true;
                            	}
                            	if((name=="insure.taxRegNumber" || name =="insure.taxRegTel" 
                            		|| name =="insure.taxRegAddress" || name == "insure.taxBankName" || name == "insure.taxBankAccount" 
                            			) && receiptType != 3){
                            		return true;
                            	}
                            	if(name==""){
                            		return true;
                            	}
                            	 var v=o.val();
                                 if(!v){
                                     isValidateTrue=false;
                                     messageTool.error(o.prev().text()+'必须填写');
                                     return false;
                                 }
                            }
                        });
                        if(isValidateTrue){
                            var paras=win.find('#editForm').serialize();
                            util.request({
                                confirm: true,
                                msg: "确定要修改订单信息?",
                                url: serverHost + "/order/updateOrder.json",
                                dataType:"json",
                                params:paras ,
                                success: function (resp) {
                                    if(resp) {
                                        win.close();
                                        messageTool.success("更改订单信息成功");
                                        thiz.list.loadPageData();
                                    } else {
                                        messageTool.error("更改订单信息失败");
                                    }
                                }
                            });
                        }
                    }
                });
				$('div.data-list-employee').html(null);
				listTool.create({
					title : "员工信息",
					selector : "div.data-list-employee",
					remote : true,
					url : serverHost + "/employee/list.json?id=" + id,
					width : 700,
					height : 1000,
					showPagebar : false,
					showCheckbox : false,
					columns : [ {
						name : "员工姓名",
						width : 60,
						fieldName : "name"
					}, {
						name : "员工证件类型",
						width : 60,
						fieldName : "cardTypeId",
						renderer : function(row, col, data, value) {
							if (value == 0) {
								return "身份证";
							}
							if (value == 1) {
								return "护照";
							}
							if (value == 2) {
								return "军官证";
							}
							if (value == 3) {
								return "港澳通行证";
							}
							return "其他";
						}
					}, {
						name : "员工证件号码",
						width : 60,
						fieldName : "cardno"
					}, {
						name : "员工职业类别",
						width : 60,
						fieldName : "vocation",
						renderer : function(row, col, data, value) {
							return value + "类";
						}
					}, {
						name : "员工生日",
						width : 60,
						fieldName : "birthday"
					}, {
						name : "员工性别",
						width : 40,
						fieldName : "gender",
						renderer : function(row, col, data, value) {
							if(value == 1){
								return "男"
							}
							if(value == 0){
								return "女"
							}
							return null;
						}
					} ],
					tbars : []
				});



                win.find('img[name="insure.imgUrl"]').click(function(){
                	window.open(platformStaticPath+data.insure.imgUrl, "查看营业执照", "", "");
                });
                win.find('img[name="isurant.imgUrl"]').click(function(){
                	window.open(platformStaticPath+data.isurant.imgUrl, "查看营业执照", "", "");
                });
                
                win.find('select[name="orderState"]').change(function(){
                	var orderState = win.find('select[name="orderState"]').val()
                	if(orderState == 4){
                		win.find('div[name="cancelReasonDiv"]').show();
                		win.find('div[name="refundTimeDiv"]').show();
                	}else{
                		win.find('div[name="cancelReasonDiv"]').hide();
                		win.find('div[name="refundTimeDiv"]').hide();
                	}
                });
                
                if(data.orderState == 4){
            		win.find('div[name="cancelReasonDiv"]').show();
            		win.find('div[name="refundTimeDiv"]').show();
            	}else{
            		win.find('div[name="cancelReasonDiv"]').hide();
            		win.find('div[name="refundTimeDiv"]').hide();
            	}
                if(data.orderState == 6){
                	win.find('div[name="policyNumberDiv"]').show();
                }else{
                	win.find('div[name="policyNumberDiv"]').hide();
                }
                
                if(data.insure.receiptType == 3){
                	win.find('div[name="insureFaPiaoDiv"]').show();
                }else{
                	win.find('div[name="insureFaPiaoDiv"]').hide();
                }
                win.find('select,input,img').each(function(i,o){
                	
                	if(o.name == "policyUrl"){
                		if(data[o.name] != "" && null != data[o.name] ){
                			$(o).val("是");
                		}else{
                			$(o).val("否");
                		}
                		return true;
                	}
                	if(o.name == "updateTime"){
                		$(o).val(util.dateFormat2(data[o.name], 'YYYY-MM-DD hh:mm:ss'))
                		return true;
                	}
                	if(o.name == "createTime"){
                		$(o).val(util.dateFormat2(data[o.name], 'YYYY-MM-DD hh:mm:ss'))
                		return true;
                	}
                	if(o.name == "employeeId"){
                		$(o).val((data.employeeIds.split(",").length));
                		return true;
                	}
                	var indexInsure = o.name.indexOf("insure.");
                	var indexIsurant = o.name.indexOf("isurant.");
                	if(indexInsure == 0 || indexIsurant == 0 ){
                		var obj;
                		if(indexInsure == 0){
                			obj = data.insure;
                		}else{
                			obj = data.isurant;
                		}
                		var index = o.name.indexOf(".");
                		var names = o.name.substring(index+1);
                		if(names == "imgUrl"){
                			console.log(platformStaticPath+obj[names]);
                			$(o).attr('src',platformStaticPath+obj[names]);
                			return true;
                		}
                		if(names == "province"){
                			$(o).attr('data-province',obj[names]);
                		}
                		if(names == "city"){
                			$(o).attr('data-city',obj[names]);
                		}
                		if(names == "area"){
                			$(o).attr('data-district',obj[names]);
                		}
                		 $(o).val(obj[names]);
                		return true;
                	}
                    $(o).val(data[o.name]);
                });
                win.find('div[data-toggle="distpicker"]').each(function(i,o){
                	win.find('div[data-toggle="distpicker"]').distpicker();
                });
                dateTool.renderDate(win.find(".form-begindate"));
                dateTool.renderDate(win.find(".form-enddate"));
                dateTool.renderDate(win.find(".form-refundTime"));
            },
            
            
            importSinglePolicy:function(data){
            	var thiz=this,id=data.id;
            	$("#id").val(data.id);
            	win = winTool.create({
					title: "导入单个订单信息保单",
					height: 100,
					width:450,
					showCancel: false,
					showOk: true,
					okName:'导入',
					type: "selector",
					selector: "div.order_policy_import",
					okFn: function (win) {
						var isValidateTrue = true;
						var v = win.find('input[name=file]').val();
                        if(!v){
                        	isValidateTrue=false;
                        	messageTool.success("必须选择一个pdf文件");
                        }
						if(isValidateTrue){
							messageTool.confirm2('<font color="red">注：导入前请先执行导出备份原有数据。</font><br/>确定要导入该文件？',function(){
								var form=win.find('#order_policy_import_form')[0];
								thiz.ajaxUpload(form,{
									onSuccess:function(data){
										if(data.status==0){
											win.close();
											thiz.list.loadPageData();
										}
									}
								});
							});
						}
					}
				}); 
            },
            downSinglePolicy:function(data){
            	var thiz=this,policyUrl=data.policyUrl;
            	if(policyUrl == "" || policyUrl == null){
            		messageTool.success("该订单没有上传保单");
            		return;
            	}
            	//window.open(STATIC_PATH+policyUrl, "下载保单", "", "");
            	location.href=serverHost + "/order/downSinglePolicy.json?policyUrl="+policyUrl;
            },
            downOrder:function(data){
            	var thiz=this,id=data.id;
            	location.href=serverHost + "/order/downOrder.json?id="+id;
            },
            downOrderModel:function(data){
            	var thiz=this,id=data.id;
            	location.href=serverHost + "/order/downOrderModel.json?id="+id;
            },
            delOrder:function(data){
            	var thiz=this,id=data.id;
            	util.request({
                    confirm: true,
                    msg: "确定要作废该订单信息?",
                    url: serverHost + "/order/del.json",
                    dataType:"json",
                    params: {id : id},
                    success: function (resp) {
                        if(resp) {
                            messageTool.success("作废订单信息成功");
                            thiz.list.loadPageData();
                        } else {
                            messageTool.error("作废订单信息失败");
                        }
                    }
                });
            },
            updateIsurantMessage:function(data){
            	var thiz=this,id=data.isurantId;
            	if(null == id){
            		messageTool.success("被保人公司id为空，不可更改!");
            		return;
            	}
            	util.request({
                    confirm: true,
                    msg: "确定要修改被保人企查查信息?",
                    url: serverHost + "/order/updateIsurantMessage.json",
                    dataType:"json",
                    params: {id : id},
                    success: function (resp) {
                        if(resp) {
                        	 messageTool.success("更改信息成功");
                            thiz.list.loadPageData();
                        } else {
                        	messageTool.error("更改信息失败");
                        }
                    }
                });
            },
            ajaxUpload:function(form, opts) {
				var thiz=this;
				opts = opts || {};
				var formData = new FormData(form);
				if (opts.onData) {
					opts.onData(formData);
				}
				var xhr = new XMLHttpRequest();
				xhr.open("POST", form.action);
				xhr.onload=function(e) {
					var json = eval('(' + this.response + ')');
					thiz.validateSsouser(json);
					var cb = AjaxStatus[json.status];
					if (!cb) {
						messageTool.error('程序错误，错误代码：' + json.status);
					}
					cb(json, opts);
				};
				xhr.send(formData);
			},
			validateSsouser:function(data) {
				if(data.ssotype) {
					window.location.href = decodeURIComponent(data.redirectUrl);
					return false;
				}
				return true;
			},
			validateSsouser:function(data) {
				if(data.ssotype) {
					window.location.href = decodeURIComponent(data.redirectUrl);
					return false;
				}
				return true;
			}
    };

});