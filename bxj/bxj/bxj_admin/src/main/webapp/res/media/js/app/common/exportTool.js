define(['jquery', 'common/math', 'app/common/domUtil',
    'app/common/winTool'], function ($, math, domUtil, winTool) {

    return {
        export: function (action, totalCount, extParams, tool) {
            extParams = extParams || {};

            if(tool && tool.getOrderBy) {
                extParams.orderBy = tool.getOrderBy();
            }
            var totalCount = totalCount|| 0;


            var exportHtml =
                    '<form method="post" class="export-excel-form" action="' + action + '">' +
                    '<table class="" style="margin: 0 15px;">'+
                    '<tbody>'+
                    '<tr>'+
                    '<td class="body1" style="padding-right: 10px; padding-top: 6px; vertical-align: top;">选择导出范围</td>'+
                    '<td class="export">' +
                    '<input type="hidden" name="_export_" value="1">' +
                    '<select class="exportCondition_ form-control" style="width:200px;">'+
                    '</select></td>'+
                    '</tr>'+
                    '</tr>'+
                    '</tbody>'+
                    '</table>' +
                    '<input type="hidden" class="export-excel-pageSize" name="pageSize" value="10000">' +
                    '<input type="hidden" class="export-excel-currentPage" name="currentPage" value="">' +
                    '</form>',

                exportFn = function (win) {
                    var condition = win.find(".exportCondition_").val();

                    var pageNo = 1, pageSize = 10000;

                    if(condition && math.floatSub(condition, 0) > 0){
                        pageNo = condition;
                    }
                    win.find(".export-excel-currentPage").val(pageNo);
                    win.find(".export-excel-pageSize").val(pageSize);

                    win.find(".export-excel-form").submit();

                    win.close();
                }

            var initCombox = function (win) {
                if(totalCount && math.floatSub(totalCount, 0)>0){
                    totalCount = parseInt(totalCount);
                    var pageCount = 0,
                        p_size = 10000;
                    if(totalCount%p_size == 0){
                        pageCount = totalCount/p_size;
                    } else {
                        pageCount = Math.floor(totalCount/p_size) + 1;
                    }
                    domUtil.removeAllOptions(win.find(".exportCondition_"), false);
                    for(var i=0; i<pageCount;i++){
                        var value;
                        if(i==0){
                            value = "1-"+p_size;
                        } else {
                            value = (i*p_size+1) + "-" + ((i+1)*p_size);
                        }
                        domUtil.addOption(win.find(".exportCondition_"), i+1, value, false);
                    }
                }
            }

            var win = winTool.create({
                title: "导出excel",
                okName: "导出",
                cancelName: "关闭",
                type: 'html',
                width: 400,
                height: 60,
                html: exportHtml,
                overflow: 'hidden',
                okFn: function (win) {
                    exportFn(win);
                }
            });

            initCombox(win);

            $.each(extParams, function (key, value) {
                var paramHtml = '<input type="hidden" name="' + key + '" value="' + (value||"") + '">';
                $(paramHtml).appendTo(win.find(".export-excel-form"));
            });

            return win;
        }
    }
})