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
        url: serverHost + "/study_core/list.json",
        width: 600,
        height: 1500,
        tbars: [{
            icon: 'fa fa-plus',
            name: "新增",
			rightCode: "add",
            handler: function () {
                location = 'study_core/create.jhtml';
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
				fieldName: 'classify',
				width:200,
				renderer: function (row, col, data, value) {
					for(var i = 0;i < jsonArry.length; i++) {
						var classify1Obj = jsonArry[i];
						for(var j = 0;j < classify1Obj.sub.length; j++) {
							var classify2Obj = classify1Obj.sub[j];
							for(var k = 0;k < classify2Obj.sub.length; k++) {
								var classify = classify2Obj.sub[k];//三级分类信息
								if(classify.id==value) {
									return classify1Obj.name +"->"+ classify2Obj.name +"->"+ classify.name;
									break;
								}
							}
						}
					}
				}
        	},
			{

				name: '阅读量',
				fieldName: 'num1',
				width:50
			}, {

				name: '转发量',
				fieldName: 'num2',
				width:50
			}, {

				name: '朋友圈转发量',
				fieldName: 'num3',
				width:50
			}, {

				name: '评分',
				fieldName: 'score',
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
					row += '<button class="btn btn-warning score">评分</button>';
					//row += '<a class="score offline" href="javascript:void(0);">评分</a>';

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
			rightCode: "updateDisplayStatus",
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
                    url: "study_core/updateDisplayStatus.json",
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
			rightCode: "showUpdate",
        	handler: function(i, data) {
        		location = 'study_core/update.jhtml?articleId=' + data.id;
        	}
        },{
			className : "score",
			rightCode: "score",
			handler: function (idx, data) {
				var thiz = this;
				var win = winTool.create({
					title: "文章统计数据录入",
					height: 300,
					width: 500,
					showCancel: true,
					okName: "确定",
					cancelName: "关闭",
					type: "selector",
					selector: ".data_info",
					okFn: function (win) {
						var num1 = win.find(".num1").val(),
							num2 = win.find(".num2").val(),
							num3 = win.find(".num3").val(),
							score = win.find(".score").val()
						util.request({
							confirm: true,
							msg: "确定新增数据吗?",
							url: serverHost + "/study_core/upActicleData.json",
							params: {
								id: data.id,
								num1: num1,
								num2: num2,
								num3: num3,
								score: score
							},
							success: function (resp) {
								if (resp.success) {
									messageTool.success("新增成功");
									win.close();
									// thiz.list.reloadPageData();
									location.reload();
								} else {
									messageTool.error(resp.msg);
								}
							}
						});
					}
				});
				win.find(".num1").val(data.num1);
				win.find(".num2").val(data.num2);
				win.find(".num3").val(data.num3);
				win.find(".score").val(data.score);

			}
		}]
    });

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
    });

			// var sltTypeId = $('#typeId').chosen();
			// $('#typeId').on('change', function() {
            //
			// 	sltTypeId.chosen('destroy');
			// 	sltTypeId.find('option').show().removeAttr('selected');
			// 	sltTypeId.find('option[value=' + this.value + ']').hide();
            //
			// 	sltTypeId.chosen({
			// 		max_selected_options: 2
			// 	});
			// }).trigger('change');


			var jsonArry=[{
				"id": "01",
				"name": "早会经营",
				"sub": [
					{
						"id":"0101",
						"name": "早会组织",
						"sub": [
							{
								"id":"010101",
								"name":"早会流程"
							},
							{
								"id":"010102",
								"name":"早会主持"
							}, {
								"id":"010103",
								"name":"二次早会"
							}
						]
					},
					{
						"id":"0102",
						"name": "早会素材",
						"sub": [
							{
								"id":"010201",
								"name":"早会游戏"
							},
							{
								"id":"010202",
								"name":"标语口号"
							},{
								"id":"010203",
								"name":"早会演讲"
							},{
								"id":"010204",
								"name":"早会故事"
							},{
								"id":"010205",
								"name":"行业启动会"
							},{
								"id":"010206",
								"name":"手语舞"
							}
						]
					}
				]
			},{
				"id": "02",
				"name": "资讯理念",
				"sub": [
					{
						"id": "0201",
						"name": "每日资讯",
						"sub": [
							{
								"id":"020101",
								"name":"行业资讯"
							},
							{
								"id":"020102",
								"name":"风险案例"
							}
						]
					},
					{
						"id": "0202",
						"name": "保险理念",
						"sub": [
							{
								"id":"020201",
								"name":"保险意义"
							},
							{
								"id":"020202",
								"name":"重疾险理念"
							},{
								"id":"020203",
								"name":"意外险理念"
							},{
								"id":"020204",
								"name":"少儿险理念"
							},{
								"id":"020205",
								"name":"财产险理念"
							},{
								"id":"020206",
								"name":"资产保全"
							},{
								"id":"020207",
								"name":"资产传承"
							}
						]
					}
				]
			},{
				"id": "03",
				"name": "展业销售",
				"sub": [
					{
						"id": "0301",
						"name": "销售话术",
						"sub": [
							{
								"id":"030101",
								"name":"电话约访"
							},
							{
								"id":"030102",
								"name":"拒绝处理"
							},
							{
								"id":"030103",
								"name":"促成话术"
							},
							{
								"id":"030104",
								"name":"转介绍话术"
							}
						]
					},
					{
						"id": "0302",
						"name": "主顾开拓",
						"sub": [
							{
								"id":"030201",
								"name":"社区开拓"
							},
							{
								"id":"030202",
								"name":"缘故拜访"
							},{
								"id":"030203",
								"name":"陌生拜访"
							},{
								"id":"030204",
								"name":"转介绍"
							}
						]
					},
					{
						"id": "0303",
						"name": "指尖营销",
						"sub": [
							{
								"id":"030301",
								"name":"微营销"
							},
							{
								"id":"030302",
								"name":"短信邮件"
							}
						]
					},
					{
						"id": "0304",
						"name": "售后服务",
						"sub": [
							{
								"id":"030401",
								"name":"个性化服务"
							},
							{
								"id":"030402",
								"name":"退保应对"
							},{
								"id":"030403",
								"name":"满期转保"
							}
						]
					},
					{
						"id": "0305",
						"name": "营销技巧",
						"sub": [
							{
								"id":"030501",
								"name":"中高端客户"
							},
							{
								"id":"030502",
								"name":"低端客户"
							},
							{
								"id":"030503",
								"name":"酒会产说会"
							},
							{
								"id":"030504",
								"name":"意外险销售"
							},
							{
								"id":"030505",
								"name":"少儿险销售"
							},
							{
								"id":"030506",
								"name":"养老险销售"
							},
							{
								"id":"030507",
								"name":"理财险销售"
							},
							{
								"id":"030508",
								"name":"重疾险销售"
							},
							{
								"id":"030509",
								"name":"形象管理"
							},
							{
								"id":"030510",
								"name":"画图讲保险"
							},
							{
								"id":"030511",
								"name":"缘故法"
							},
							{
								"id":"030512",
								"name":"转介绍法"
							}
						]
					}
				]
			},{
				"id": "04",
				"name": "团队发展",
				"sub": [
					{
						"id": "0401",
						"name": "团队增员",
						"sub": [
							{
								"id":"040101",
								"name":"增员技巧"
							},
							{
								"id":"040102",
								"name":"增员话术"
							},
							{
								"id":"040103",
								"name":"增员意愿"
							},
							{
								"id":"040104",
								"name":"增员方案"
							}
						]
					},
					{
						"id": "0402",
						"name": "团队经营",
						"sub": [
							{
								"id":"040201",
								"name":"新人留存"
							},
							{
								"id":"040202",
								"name":"组员激励"
							},{
								"id":"040203",
								"name":"团队文化"
							},{
								"id":"040204",
								"name":"陪同拜访"
							}
						]
					},
					{
						"id": "0403",
						"name": "经营发展",
						"sub": [
							{
								"id":"040301",
								"name":"主管晋升"
							},
							{
								"id":"040302",
								"name":"绩优成长"
							}
						]
					}
				]
			}
			];

			$(document).ready(function () {
				/*初始化一级分类*/
				for(var i = 0;i < jsonArry.length; i++) {
					var classify1 = jsonArry[i];
					var option = $("<option>").val(classify1.id).text(classify1.name);
					$("#classify1").append(option);
				}
				/**
				 * 通过一级分类ID获取二级分类信息
				 */
				$("#classify1").change(function () {
					$("#classify2").empty();
					$("#classify").empty();
					var classify1 = $(this).val();
					var option = $("<option>").val("").text("二级分类");
					$("#classify2").append(option);
					var option11 = $("<option>").val("").text("三级分类");
					$("#classify").append(option11);
					for(var i = 0;i < jsonArry.length; i++) {
						var classify1Obj = jsonArry[i];
						if(classify1Obj.id==classify1) {

							for(var j = 0;j < classify1Obj.sub.length; j++) {
								var classify2 = classify1Obj.sub[j];//二级分类信息
								option = $("<option>").val(classify2.id).text(classify2.name);
								$("#classify2").append(option);
							}
							break;
						}
					}
				});
				/**
				 * 通过二级分类ID获取三级分类信息
				 */
				$("#classify2").change(function () {
					$("#classify").empty();
					var option = $("<option>").val("").text("三级分类");
					$("#classify").append(option);
					var classify2 = $(this).val();
					for(var i = 0;i < jsonArry.length; i++) {
						var classify1Obj = jsonArry[i];
						for(var j = 0;j < classify1Obj.sub.length; j++) {
							var classify2Obj = classify1Obj.sub[j];
							if(classify2Obj.id==classify2) {
								for(var k = 0;k < classify2Obj.sub.length; k++) {
									var classify = classify2Obj.sub[k];//三级分类信息
									var option = $("<option>").val(classify.id).text(classify.name);
									$("#classify").append(option);
								}
								break;
							}
						}
					}
				});
			});


		});