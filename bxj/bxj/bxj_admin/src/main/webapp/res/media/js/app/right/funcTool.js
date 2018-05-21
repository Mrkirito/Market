define(['jquery', 'app/common/winTool'], function ($, winTool) {

    var html = '<div class="row">' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="add"></span></div>新增</div>' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="update"></span></div>修改</div>' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="delete"></span></div>删除</div>' +
        '</div>' +
        '<div class="row" style="margin-top: 15px;">' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="info"></span></div>详情</div>' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="import"></span></div>导入</div>' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="export"></span></div>导出</div>' +
        '</div>'  +
        '<div class="row" style="margin-top: 15px;">' +
            '<div class="col-sm-4"><div class="checker"><span><input type="checkbox" class="default-func" value="save"></span></div>保存</div>' +
        '</div>' ;
    return {
        show: function (callback) {
            var thiz = this;
            thiz.win = winTool.create({
                    title: "选择常用功能",
                    okName: "保存",
                    cancelName: "关闭",
                    type: 'html',
                    html: html,
                    okFn: function (win) {
                        callback && callback(thiz.getValues(), win);
                    }
                });
        },

        getValues: function () {
          var datas = [],
              thiz = this;
            $(thiz.win.find(".default-func")).each(function (idx) {
                if($(this)[0].checked) {
                    var val = $(this).val(),
                        data = thiz[val];
                    datas.push(data);
                }
            });
            return datas;
        },
        'add': {
            funcName: '新增',
            funcCode: 'add',
            sort: 1
        },
        'update': {
            funcName: '修改',
            funcCode: 'update',
            sort: 2
        },
        'delete': {
            funcName: '删除',
            funcCode: 'delete',
            sort: 3
        },
        'info': {
            funcName: '详情',
            funcCode: 'info',
            sort: 4
        },
        'import': {
            funcName: '导入',
            funcCode: 'import',
            sort: 5
        },
        'export': {
            funcName: '导出',
            funcCode: 'export',
            sort: 6
        },
        'save': {
            funcName: '保存',
            funcCode: 'save',
            sort: 7
        }
    };
})