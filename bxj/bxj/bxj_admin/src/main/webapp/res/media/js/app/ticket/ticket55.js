define(['jquery','app/common/util', 'app/common/dateTool', 'app/common/messageTool','app/common/winTool'],
		function($, util, dateTool, messageTool,winTool) {
	var AjaxStatus = {			
			0: function(json, opts) {
				messageTool.info('导入数据成功');
				if (opts.onSuccess) {
					opts.onSuccess(json);
				}
			},			
			500: function(json, opts) {
				var p=json.phones;
				if(p){					
					var l=p.split(',');
		        	var win = winTool.create({
		                title: "重复票信息",
		                height: 300,
		                width: 500,
		                showCancel: false,
		                okName: "关闭",
		                cancelName: "关闭",
		                type: "selector",
		                selector: ".video-tpl",
		                okFn: function (win) {
							win.close();
		                }
		             });
		        	win.find('.content_repeat_phone').html('重复'+l.length+'个手机号如下：\n'+p.replace(/,/g,'\n'));
				}else{					
					messageTool.error(json.msg);
				}
				if (opts.onFail) {
					opts.onFail(json);
				}
			},			
			302: function(json) {
				location = json.url;
			}			
	};
    var ticket5 = {
    	init:function(){
    			$(".saveIn").on("click", function(){
    				var isValidateTrue=true;
    				var file= document.getElementById('file').value;
    				if(file==''){
    					messageTool.error('请选择要导入的EXCEL文件');
    					isValidateTrue=false;
    					return false;
    				}
            		$('input.is_number').each(function(i,o){
            			o=$(o);
            			var v=o.val();
            			if(v){
            				if(isNaN(v)){
            					isValidateTrue=false;
            					messageTool.error(o.parent().siblings().text()+'必须为数字');
            					return false;
            				}            				
            			}else{
            				isValidateTrue=false;
            				messageTool.error(o.parent().siblings().text()+'必须填写');
            				return false;
            			}
            		});
            		if(isValidateTrue){
            			var mn=$('select[name=basicId] option:selected').text();
            			var ac=$('input[name=actual]').val();
            			var fr=$('input[name=free]').val();
            			var tt=$('select[name=ticketType] option:selected').text();
            			var text='确认导入'+mn+',实际票数'+ac+'张,赠票数'+fr+'张，票源类型为【'+tt+'】的票？';
            			messageTool.confirm2(text,function(){            				
            				var form=document.getElementById('insure_info');
            				ticket5.ajaxUpload(form,{
            					onSuccess:function(data){
            						if(data.success&&data.order){
            							var hrefs=serverHost+ "/ticket/index3.jhtml?orderId="+data.order.fid;
            							window.location.href=hrefs;
            						}
            					}
            				});
            			});
            		}
    			});
    			$("select[name=basicId]").change(function(){
    				ticket5.loadSits();
    			});	
				ticket5.loadSits();
    	},
    	loadSits:function(){
    		var basicId=$("select[name=basicId]").val();
    		util.request({
				confirm : false,
				url : serverHost+ "/ticket/getSits.json",
				params:{fid:basicId},
				success : function(data) {
					if (data) {
						var dom='';
						$.each(data,function(i,o){
							dom+='<option value="'+o.fid+'">'+o.sitName+'('+o.price+'元)</option>';
						});
						$('select[name=sitId]').html(dom);
					}
				}
			});		
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
    			ticket5.validateSsouser(json);
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
    				if(ticket5.validateSsouser(json)) {
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
    return ticket5;
});