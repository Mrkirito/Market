define(['jquery',
    'app/common/commonTool',
    'app/common/util',
    'css!global/css/cityblock.css'
], function ($, commonTool, util) {

    var tpl =
        '<div class="tool-city-block">' +
            '<input type="hidden" class="tool-select-city"> ' +
            '<a class="btn btn-primary tool-select-all">全选</a> ' +
            '<a class="btn btn-primary tool-unselect-all">全不选</a> ' +
            '<a class="btn btn-primary tool-add-block">添加城市商场</a> ' +
            '<div class="tool-blockList"><div class="tool-select-blocklist"></div></div>' +
        '</div>'
return {
    init: function (container) {
        var tool =  {
            init: function (container) {
                tool.container = $(tpl).appendTo(container)
                tool.city = tool.container.find(".tool-select-city");
                tool.selectBtn = tool.container.find(".tool-select-all");
                tool.unselectBtn = tool.container.find(".tool-unselect-all");
                tool.addBlockBtn = tool.container.find(".tool-add-block");
                tool.blockList = tool.container.find(".tool-blockList");
                tool.selectBlockList = tool.container.find(".tool-select-blocklist");

                tool.registerEvents();
            },

            /**
             * 注册事件
             */
            registerEvents: function () {
                commonTool.renderCity({
                    selector: tool.city,
                    msg: "选择城市商场",
                    width: 200,
                    val: null,
                    listeners: {
                        change: function (id, data) {
                            tool.queryCityBlock(id);
                        }
                    }
                });

                tool.selectBtn.on("click", function () {
                    tool.blockList.find(".select-block input").filter(function () {
                        this.checked = true;
                    })
                });

                tool.unselectBtn.on("click", function () {
                    tool.blockList.find(".select-block input").filter(function () {
                        this.checked = false;
                    })
                });

                tool.blockList.on("click", ".delete-block", function () {
                    $(this).parent().remove();
                });

                tool.blockList.on("click", ".delete-city", function () {
                    $(this).parent().parent().remove();
                });

                tool.addBlockBtn.on("click", function () {
                    tool.addCityBlock();
                });
            },

            /**
             * 查询商场信息
             * @param cityId
             */
            queryCityBlock: function (cityId) {
                var url = serverHost + "mall/mallAjax/QueryMallList.json?permission=true";
                if(cityId) {
                    util.ajax({
                        url: url,
                        params: {cityId: cityId},
                        success: function (resp) {
                            if(resp.success) {
                                tool.renderCityBlock(resp.model);
                            }
                        }
                    });
                } else {
                    tool.renderCityBlock([]);
                }
            },

            /**
             * 选择商场信息
             * @param blockList
             */
            renderCityBlock: function (blockList) {
                var tpl = '<label class="select-block"><input  type="checkbox" data-block="">${name}</label>';
                blockList = blockList ||[];
                tool.selectBlockList.html("");
                $(blockList).each(function () {
                    var html = tpl.replace("${name}", this.cpName),
                        node = $(html).appendTo(tool.selectBlockList);

                    node.find("input").data("block", JSON.stringify(this));
                });
            },

            /**
             * 渲染选中的小区
             */
            addCityBlock: function () {
                var cityData = tool.city.select2("data"),
                    node = tool.renderSelectedCity(cityData);

                tool.selectBlockList.find(".select-block input").filter(function () {
                    var block = $(this).data("block");
                    if(this.checked) {
                        var data = JSON.parse(block);
                        tool.renderSelectedBlock(node, data);
                    }
                });
            },

            renderSelectedCity: function (data) {
                var tpl =
                        '<div class="selected-city" data-id="" data-city="">' +
                        '<span class="city" >${cityName}<i class="fa fa-times delete-city"></i></span> ' +
                        '<div class="selected-blocklist"></div>' +
                        '</div>',
                    html = tpl.replace("${cityName}", data.cityName),
                    node = null;

                tool.blockList.find(".selected-city").filter(function () {
                    if($(this).data("id") == data.cityId) {
                        node = $(this);
                    }
                });

                if(!node) {
                    node = $(html).appendTo(tool.blockList);
                }
                node.data("id", data.cityId);
                node.data("city", JSON.stringify(data));
                return node;
            },

            renderSelectedBlock: function (cityNode, data) {
                var blocktpl = '<label class="select-block" data-id="" data-block="">${cpName}<i class="fa fa-times delete-block"></i></label> ',
                    html = blocktpl.replace("${cpName}", data.cpName),
                    blockNode = null;

                cityNode.find(".select-block").filter(function () {
                    if($(this).data("id") == data.id) {
                        blockNode = $(this);
                    }
                });

                if(!blockNode) {
                    blockNode = $(html).appendTo(cityNode.find(".selected-blocklist"))
                }
                blockNode.data("id", data.id);
                blockNode.data("block", JSON.stringify(data));
                return blockNode;
            },

            querycpIdList: function () {
                var blockList = [];
                tool.blockList.find(".selected-city .select-block").filter(function () {
                	var block = $(this).data("block");
                    blockList.push(JSON.parse(block));
                });
                return blockList;
            },

            queryCityMallList: function () {
                var cityMallList  = [];
                $('.selected-city').each(function () {
                    var cityId = $(this).data("id");
                    $(this).find(".select-block").each(function () {
                        var cpId = $(this).data("id");
                        cityMallList.push({
                            cityId: cityId,
                            cpId: cpId
                        });
                    })
                })
                return cityMallList;
            },

            /**
             * 渲染数据
             * @param moduleBlockList
             */
            renderSelectedCityBlock: function (moduleBlockList) {
                var thiz = this;

                moduleBlockList = moduleBlockList || [];

                $(moduleBlockList).each(function () {
                    var node = thiz.renderSelectedCity(this);
                    thiz.renderSelectedBlock(node, this);
                });
            }
        }

        tool.init(container);
        
        return tool;
    }
}

})