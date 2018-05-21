/**
 * 城市覆盖区域控件
 * @param {Object} container
 * @author Liu YuTao
 */
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
            '<a class="btn btn-primary tool-add-block">添加覆盖区域</a> ' +
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
                    msg: "选择覆盖区域",
                    width: 200,
                    val: null,
                    listeners: {
                        change: function (id, data) {
							tool.queryAreaBlock(id);
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
                    tool.addAreaBlock();
                });
            },

			
			/**
			 * 根据城市ID查询覆盖区域
			 * @author Liuyutao
			 */
			 queryAreaBlock: function (cityId) {
                var url = serverHost + "common/commonAjax/queryAreaByCityIdList.json";
                if(cityId) {
                    util.ajax({
                        url: url,
                        params: {cityId: cityId},
                        success: function (resp) {
                            if(resp.success) { 
								tool.renderAreaBlock(resp.model);
                            }
                        }
                    });
                } else {
                    tool.renderAreaBlock([]);
                }
            },
			
 
			
			/**
			 * 选择覆盖区域信息
			 * @author Liuyutao
			 */
            renderAreaBlock: function (blockList) {
                var tpl = '<label class="select-block"><input  type="checkbox" data-block="">${name}</label>';
                blockList = blockList ||[];
                tool.selectBlockList.html("");
                $(blockList).each(function () {
                    var html = tpl.replace("${name}", this.areaName),
                        node = $(html).appendTo(tool.selectBlockList);

                    node.find("input").data("area", JSON.stringify(this));
                });
            },
			
            /**
             * 渲染选中的小区
             */
            addAreaBlock: function () {
                var cityData = tool.city.select2("data"),
                    node = tool.renderSelectedCity(cityData); 
                tool.selectBlockList.find(".select-block input").filter(function () {
                    var area = $(this).data("area");
                    if(this.checked) {
                        var data = JSON.parse(area); 
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
                var blocktpl = '<label class="select-block" data-id="" data-block="">${areaName}<i class="fa fa-times delete-block"></i></label> ',
                    html = blocktpl.replace("${areaName}", data.areaName),
                    blockNode = null;
					console.log(data);
                cityNode.find(".select-block").filter(function () {
                    if($(this).data("id") == data.areaId) {
                        blockNode = $(this);
                    }
                });

                if(!blockNode) {
                    blockNode = $(html).appendTo(cityNode.find(".selected-blocklist"))
                }
                blockNode.data("id", data.areaId);
                blockNode.data("area", JSON.stringify(data));
                return blockNode;
            },

            queryCityList: function () {
                var cityList = [];
                tool.blockList.find(".selected-city").filter(function () {
                    if($(this).find(".select-block").length == 0) {
                        var city = $(this).data("city");
                        cityList.push(JSON.parse(city));
                    }
                });
                return cityList;
            },
            
            queryCityIdList: function () {
                var cityList = [];
                tool.blockList.find(".selected-city").filter(function () {
                    if($(this).find(".select-block").length == 0) {
                        var city = $(this).data("id");
                        cityList.push(city);
                    }
                });
                return cityList;
            },
            
            queryBlockList: function () {
                var blockList = [];
                tool.blockList.find(".selected-city .select-block").filter(function () {
                    var area = $(this).data("area");
                    blockList.push(JSON.parse(area));
                });
                return blockList;
            },

            queryBlockIdList: function () {
                var blockList = [];
                tool.blockList.find(".selected-city .select-block").filter(function () {
                    var area = $(this).data("id");
                    blockList.push(area);
                });
                return blockList;
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