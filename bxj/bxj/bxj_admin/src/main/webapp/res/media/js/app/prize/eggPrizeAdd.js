define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool','app/common/winTool','app/common/imageUploadTool'],
		function($, util, listTool, dateTool, messageTool, commonTool, winTool, imageUploadTool) {
    var eggPrizeAdd = {
		init:function(){
            var thiz = this;
            // 添加奖品模板
            thiz.renderTemplate();
            // 图片渲染
            thiz.renderUpload($(".mainTemplate .prizeTemplate"));
            // 数字框控制
            thiz.renderNum($(".mainTemplate .prizeTemplate"));
            // 保存
            thiz.saveEggPrize();
            
            // 添加奖品增量模板
            thiz.renderInTemplate();
            // 数字框渲染增量
            thiz.renderInNum($(".mainInTemplate .prizeInTemplate"));
            // 保存增量
            thiz.saveEggPrizeIn();
            // 渲染下拉框
            thiz.renderSelect($(".mainInTemplate .prizeInTemplate"));
            $(".returnList").on("click", function(){
        		window.location.href = serverHost + "/eggPrize/eggPrizeList.jhtml";
            })
            $(".returnInList").on("click", function(){
        		window.location.href = serverHost + "/eggPrize/eggPrizeInList.jhtml";
            })
		},
		// 奖品模板
		renderTemplate : function() {
			var thiz = this;
			// 新增奖品模板
			$('.add_tpl').on("click", function() {
				var tpl = $(".template-tpl").html();
				var tpl2 = $(tpl).appendTo('.mainTemplate');
				thiz.renderUpload($(tpl2));
				thiz.renderNum($(tpl2));
				$(tpl2).find(".del_tpl").on("click", function() {
					$(this).parent().parent().parent().parent().remove();
					$(".prizeTitle").each(function(idx, tr){
						$(this).html("奖品" + (idx + 1));
					})
				});
				$(".prizeTitle").each(function(idx, tr){
					$(this).html("奖品" + (idx + 1));
				})
			});
		},
		// 图片渲染
		renderUpload : function(selector) {
			$(selector).find(".prizeImg .infos-images, .contextImg .infos-images").on("click", function(){
				var thiz = this;
				imageUploadTool.init({
					title : "上传图片",
					attach : 7,
					acceptedFiles : ".png,.jpg,.jpeg",
					maxFiles : 1,
					extData: {
                        size: "138x138"
                    },
					callback : function(imgs) {
						var img = imgs.length > 0 ? imgs[0] : {};
						$(thiz).parent().find(".picUrl").attr("src", img.fileUrl);
						$(thiz).parent().find(".picUrl").data("url", img.fileName);
					}
				});
			})
			
			$(selector).find(".remove-img").on("click", function() {
				$(this).parent().find(".picUrl").attr("src", "");
				$(this).parent().find(".picUrl").data("url", "");
			});
		},
		// 数字框渲染
		renderNum : function(selector) {
			$(selector).find(".totalNum, .amountLimit, .amount").on("keyup", function(event){
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
			})
			$(selector).find(".probability").on("keyup", function(event){
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
        		if($amountInput.val() > 100){
        			$amountInput.val(100);
        		}
			})
		},
		// 新增奖品
		saveEggPrize : function() {
			$(".save").on("click", function(){
				var eggPrizes = [],
					loginName = $("#loginName").val()
					flag = true;
				$(".mainTemplate .prizeTemplate").each(function(idx, tr) {
					var line = $(this),
						type = line.find(".type").val(),
						name = line.find(".name").val(),
						probability = line.find(".probability").val(),
						totalNum = line.find(".totalNum").val(),
						amountLimit = line.find(".amountLimit").val(),
						level = line.find(".level").val(),
						prizeImg = line.find(".prizeImg .picUrl").data("url"),
						contextImg = line.find(".contextImg .picUrl").data("url"),
						amount = line.find(".amount").val(),
						msg = "奖品" + (idx + 1),
						eggPrize = {
							type : type,
							name : name,
							probability : probability,
							totalNum : totalNum,
							amountLimit : amountLimit,
							level : level,
							prizeImg : prizeImg,
							contextImg : contextImg,
							createName : loginName,
							amount : amount,
							maxCount : 100000000
						};
					if(!type){
						messageTool.error(msg + "必须选择奖品类型!");
                		flag = false;
					}
					if(!name){
						messageTool.error(msg + "必须输入奖品名称!");
                		flag = false;
					} else {
						if(name.length > 32){
							messageTool.error(msg + "的奖品名称不能超过32个字!");
	                		flag = false;
						}
					}
					if(!probability){
						messageTool.error(msg + "必须输入中奖率!");
                		flag = false;
					}
					if(!totalNum){
						messageTool.error(msg + "必须输入奖品数!");
                		flag = false;
					}
					if(!amountLimit){
						messageTool.error(msg + "必须输入限制金额!");
                		flag = false;
					}
					if(!level){
						messageTool.error(msg + "必须选择级别!");
                		flag = false;
					}
					if(!amount){
						messageTool.error(msg + "必须输入奖品价值!");
                		flag = false;
					}
					if(!prizeImg){
						messageTool.error(msg + "必须上传奖品图片!");
                		flag = false;
					}
					if(!contextImg){
						messageTool.error(msg + "必须上传文案图片!");
                		flag = false;
					}
					eggPrizes.push(eggPrize);
				});
				if(flag){
					messageTool.confirm("确定要保存？", function (confirmFlag) {
	                    if(confirmFlag) {
	                    	$.ajax({ 
	        		            type: "POST", 
	        		            url: serverHost + "/eggPrize/savePrize.json",
	        		            dataType: "json",      
	        		            contentType:" application/json;charset=UTF-8",               
	        		            data: JSON.stringify(eggPrizes), 
	        		            success:function(data){ 
	        		            	if(data.success) {
	                                	messageTool.success("新增成功");
	                                } else {
	                                	messageTool.error("新增失败");
	                                }                 
	        		            } 
	        		         }); 
	                    }
	                });
				}
			});
		},
		
		// 奖品模板增量
		renderInTemplate : function() {
			var thiz = this;
			// 新增奖品模板
			$('.add_in_tpl').on("click", function() {
				var tpl = $(".template-in-tpl").html();
				var tpl2 = $(tpl).appendTo('.mainInTemplate');
				thiz.renderInNum($(tpl2));
				thiz.renderSelect($(tpl2));
				$(tpl2).find(".del_in_tpl").on("click", function() {
					$(this).parent().parent().parent().parent().remove();
					$(".prizeInTitle").each(function(idx, tr){
						$(this).html("增量" + (idx + 1));
					})
				});
				$(".prizeInTitle").each(function(idx, tr){
					$(this).html("增量" + (idx + 1));
				})
			});
		},
		// 数字框渲染
		renderInNum : function(selector) {
			$(selector).find(".incrementNum, .amountLimit").on("keyup", function(event){
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
			})
		},
		
		// 下拉框渲染
		renderSelect : function(selector) {
        	// 渲染下拉框
			commonTool.renderEggPrize({
				msg : "选择奖品",
				selector : $(selector).find(".prizeIn"),
				width : "100%",
				paramFn : function() {
					return {
//						permission: true
					};
				},
				listeners : {
					change : function() {
						
					}
				}
			});
		},
		// 新增奖品增量
		saveEggPrizeIn : function() {
			$(".saveIn").on("click", function(){
				var eggPrizesIn = [],
					loginName = $("#loginName").val()
					flag = true;
				$(".mainInTemplate .prizeInTemplate").each(function(idx, tr) {
					var line = $(this),
					prizeIn = line.find(".prizeIn").val(),
					incrementNum = line.find(".incrementNum").val(),
					amountLimit = line.find(".amountLimit").val(),
					msg = "增量" + (idx + 1),
					prize = line.find(".prizeIn").select2("data") || {},
					prizeId = prize.id,
					eggPrizeIn = {
						prizeIn : prizeIn,
						incrementNum : incrementNum,
						amountLimit : amountLimit,
						createName : loginName,
						prizeId : prizeId
					};
					if(!prizeId){
						messageTool.error(msg + "必须选择奖品!");
	            		flag = false;
					}
					if(!incrementNum){
						messageTool.error(msg + "必须输入增量!");
	            		flag = false;
					}
					if(!amountLimit){
						messageTool.error(msg + "必须输入限制金额!");
	            		flag = false;
					}
					eggPrizesIn.push(eggPrizeIn);
				});
				if(flag){
					messageTool.confirm("确定要保存？", function (confirmFlag) {
	                    if(confirmFlag) {
	                    	$.ajax({ 
	        		            type: "POST", 
	        		            url: serverHost + "/eggPrize/savePrizeIn.json",
	        		            dataType: "json",      
	        		            contentType:" application/json;charset=UTF-8",               
	        		            data: JSON.stringify(eggPrizesIn), 
	        		            success:function(data){ 
	        		            	if(data.success) {
	        		            		window.location.href = serverHost + "/eggPrize/eggPrizeInList.jhtml";
	                                	messageTool.success("新增成功");
	                                } else {
	                                	messageTool.error("新增失败");
	                                }                 
	        		            } 
	        		         }); 
	                    }
	                });
				}
			});
		},
    };
    return eggPrizeAdd;
});