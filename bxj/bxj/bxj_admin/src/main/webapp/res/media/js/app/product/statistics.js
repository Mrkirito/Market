define(['jquery','app/common/util','app/common/advanceListTool','app/common/dateTool','app/common/messageTool'],function($, util, listTool,dateTool,messageTool) {
	var list_column=[{name: "日期",width:100,fieldName: "date"},{name: "计划书数量",width:100,fieldName: "bookNum"},{name: "制作人数",width:30,fieldName: "userNum"}];
	var userList = {
    	list:function(){
	        var thiz = this,
	        list = listTool.create({
	            	title: "产品列表",
	                selector: ".data-list",
	                remote: true,
	                url: serverHost + "/product/statistics.json",
                    height: 800,
                    width: 800,
	                showCheckbox: false,
	                multiSelect: false,
	                columns:list_column,
	                paramFn: function () {
	                	var begin=$('input[name=begin]').val();
	                	var end=$('input[name=end]').val();
	                	return {begin:begin,end:end};
	                }
	        });	     
	        dateTool.renderDate($('input.input_date'));
            this.list = list;
            $('button.search').click(function(){
            	var beginDate=$('input[name=begin]').val();
            	var endDate=$('input[name=end]').val();
            	var d1 = new Date(beginDate.replace(/\-/g, "\/"));  
            	var d2 = new Date(endDate.replace(/\-/g, "\/"))
            	if(beginDate!=""&&endDate!=""&&d1 >=d2){  
            		messageTool.error("开始时间不能大于结束时间！");  
            	}else{
            		list.reloadPageData();            		
            	}
            });
        }
    };
    return userList;
});