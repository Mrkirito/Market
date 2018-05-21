define(['jquery'], function ($) {
    return {
        init: function () {
            var thiz = this;
            $('#serviceTimeRule').on('change',function(){
        		if($(this).val()==1){
        			$('.intraday-time').removeClass('hidden');
        			$('.intraday-time').siblings().addClass('hidden');
        		}else if($(this).val()==2){
        			$('.intraday-time-frame').removeClass('hidden');
        			$('.intraday-time-frame').siblings().addClass('hidden');
        		}else if($(this).val()==3){
        			$('.no-intraday').removeClass('hidden');
        			$('.no-intraday').siblings().addClass('hidden');
        		}
            });
            //添加事件
            var obj = $('.timeFrame');
        	 $('#addBtn').on("click",function(){
        		 var html = $('.timeFrame').eq(0).clone();
        		 var delHtml = '<a href="javascript:void(0)" class="delBtn">-删除 </a>';
             	$(this).prev().after(html);
             	$('.timeFrame').not( $(".timeFrame")[0] ).append(delHtml);
//             	$('.timeFrame').each(function(i){
//         		var item = $('.timeFrame').not( $(".timeFrame")[0] )[i];
//         			$(item).append(delHtml);
//         	    });

            	$('.delBtn').on("click",function(){
                	$(this).parent().remove();
            	});
             });
            
        }
    };
});