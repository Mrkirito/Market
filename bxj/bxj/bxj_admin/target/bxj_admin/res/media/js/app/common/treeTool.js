define(['jquery', "util", 'plugins/ztree/ztree'], function ($, util, _) {
    var defaults = {
            selector: null,
            url: null,
            remote: true,
            keyMappings: [], // [{name: '', mapping:''}]
            data: [],
            rootName: "树根",
            showRoot: true,
            enableCheck: false,// 是否启用多选或单选
            multiple: false, // 是否多选
            selectedMulti: true,
            chkboxType: null,
            treeClick: function (treeId, treeNode) {
            },
            checkClick: null,
            paramFn: function () {
                return {};
            }
        },
        redioSetting = {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        checkSetting = {
            enable: true,
            chkDisabledInherit: false,
            chkboxType: {"Y": "", "N": ""}
        };

    return {
        create: function (config) {
            var tree = {
                init: function (config) {
                    config = config || {};
                    this.opts = $.extend({}, defaults, config);
                    this.renderTree();
                    return this;
                },

                /**
                 * 渲染树
                 * @param data
                 */
                renderTree: function (checkedNodeIdList) {
                    var thiz = this;
                    if (!!this.opts.remote) {
                        thiz.loadTreeData(checkedNodeIdList);
                    } else {
                        thiz.createTree(this.opts.data);
                    }
                },


                /**
                 * 加载树数据
                 */
                loadTreeData: function (checkedNodeIdList) {
                    var thiz = this;
                    if (thiz.opts.url) {
                        var params = thiz.opts.paramFn ? (thiz.opts.paramFn() || {}) : {};
                        util.ajax({
                            url: thiz.opts.url,
                            params: params,
                            success: function (resp) {
                                if (!!resp.success) {
                                    var data = resp.model || [];
                                    thiz._checkedTree(data, checkedNodeIdList);
                                    thiz.createTree(data);
                                }
                            }
                        });
                    }
                },

                _checkedTree: function (treeData, checkedNodeIdList) {
                    var thiz = this,
                        nodeIdList = checkedNodeIdList || [];
                    $(treeData || []).each(function () {
                        if(util.contains(nodeIdList, this.id)) {
                            this.checked = true;
                        }
                        if(this.children && this.children.length > 0) {
                            thiz._checkedTree(this.children.clone, nodeIdList);
                        }
                    })
                },

                createTree: function (data) {
                    $(this.opts.selector).html("");
                    var thiz = this;
                    data = data || [];

                    thiz.generateData(data);

                    thiz.renderRoot(data);

                    var getFont = function (treeId, node) {
                            return node.font ? node.font : {};
                        },
                        setting = {
                            view: {
                                fontCss: getFont,
                                nameIsHTML: true,
                                selectedMulti: this.opts.selectedMulti
                            },
                            check: {},
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onClick: function (event, treeId, treeNode) {
                                    if (!thiz.opts.checkClick) {
                                        var checked = !treeNode.checked;
                                        thiz.tree.checkNode(treeNode, checked);
                                        thiz.checkParentAndChildren(treeNode, thiz.tree);
                                    }
                                    if (thiz.opts.treeClick) {
                                        thiz.opts.treeClick(treeId, treeNode);
                                    }
                                },
                                onCheck: function (event, treeId, treeNode) {
                                    if (thiz.opts.checkClick) {
                                        //var checked = !treeNode.checked;
                                        //thiz.tree.checkNode(treeNode, checked);
                                        thiz.checkParentAndChildren(treeNode, thiz.tree);
                                        thiz.opts.checkClick(treeNode.checked, treeId, treeNode);
                                    } else {
                                        thiz.opts.treeClick && thiz.opts.treeClick(treeId, treeNode);
                                    }
                                }
                            }
                        };

                    if (!!thiz.opts.enableCheck) {
                        setting.check = !!thiz.opts.multiple ? checkSetting : redioSetting;
                        if (thiz.opts.chkboxType && !!thiz.opts.multiple) {
                            setting.check.chkboxType = thiz.opts.chkboxType;
                        }
                    }
                    var treeSelector = thiz.opts.selector;
                    if (typeof thiz.opts.selector == 'string') {
                        treeSelector = $(thiz.opts.selector);
                    }
                    $(treeSelector).addClass("ztree");
                    var treeId = $(treeSelector).attr("id") || thiz.opts.treeId;
                    if (!treeId) {
                        treeId = thiz.generateTreeId();
                    }
                    $(treeSelector).attr("id", treeId);
                    thiz.tree = $.fn.zTree.init(treeSelector, setting, data);
                },

                checkParentAndChildren: function (node, tree) {
                    var thiz = this,
                        settingCheck = tree.setting.check || {},
                        parentNode = node.getParentNode(),
                        children = node.children,
                        checked = !!node.checked;

                    thiz.checkParentNode(parentNode, checked, settingCheck, tree);

                    thiz.checkChildren(children, checked, settingCheck, tree);
                },

                checkParentNode: function (node, checked, settingCheck, tree) {
                    if (node && 0 != node.id) {
                        var thiz = this,
                            chkboxType = settingCheck.chkboxType || {},
                            checkY = chkboxType.Y,
                            checkN = chkboxType.N;
                        if (checked) {
                            if (checkY.indexOf("p") >= 0) {
                                tree.checkNode(node, true);
                                thiz.checkParentNode(node.getParentNode(), true, settingCheck, tree);
                            }
                        } else {
                            if (checkN.indexOf("p") >= 0) {
                                if (thiz.childrenIsCheck(node.children, false)) {
                                    tree.checkNode(node, false);
                                    thiz.checkParentNode(node.getParentNode(), false, settingCheck, tree);
                                }
                            }
                        }
                    }
                },

                checkChildren: function (children, checked, settingCheck, tree) {
                    var thiz = this,
                        chkboxType = settingCheck.chkboxType || {},
                        checkY = chkboxType.Y,
                        checkN = chkboxType.N;
                    if (children && children.length > 0) {
                        $(children).each(function () {
                            var child = this,
                                children2 = child.children;
                            if (checked) {
                                if (checkY.indexOf("s") >= 0) {
                                    tree.checkNode(child, true);
                                    thiz.checkChildren(children2, true, settingCheck, tree);
                                }
                            } else {
                                if (checkN.indexOf("s") >= 0) {
                                    tree.checkNode(child, false);
                                    thiz.checkChildren(children2, false, settingCheck, tree);
                                }
                            }
                        });
                    }
                },

                childrenIsCheck: function (children, checked) {
                    var result = true;
                    children = children || [];
                    $(children).each(function () {
                        if (checked) {
                            if (!this.checked) {
                                result = false;
                                return false;
                            }
                        } else {
                            if (this.checked) {
                                result = false;
                                return false;
                            }
                        }
                    });
                    return result;
                },

                generateTreeId: function () {
                    return "ztree-" + new Date().getTime();
                },
                /**
                 * 预处理数据
                 * @param data
                 */
                generateData: function (data) {
                    var thiz = this;
                    data = data || [];
                    $(data).each(function (idx, item) {
                        if (thiz.opts.keyMappings && thiz.opts.keyMappings.length > 0) {
                            $(thiz.opts.keyMappings).each(function (idx2, keyMapping) {
                                if (item.hasOwnProperty(keyMapping.name)) {
                                    item[keyMapping.mapping] = item[keyMapping.name];
                                }
                            });
                        }
                        if (item.nodeId) {
                            item.id = item.nodeId;
                        }
                        if (item.nodeName) {
                            item.name = item.nodeName;
                        }
                        if (item.children && item.children.length > 0) {
                            thiz.generateData(item.children);
                        }
                    });

                    $(data).each(function (idx, item) {
                        if (!!item.disabled) {
                            item.font = {'color': '#ddd'};
                        }
                    });
                },

                renderRoot: function (data) {
                    var thiz = this;
                    if (!!thiz.opts.showRoot) {
                        data.push({
                            id: 0,
                            name: thiz.opts.rootName,
                            open: true,
                            nocheck: true
                        });
                    }
                },

                reloadTree: function (checkedNodeIdList) {
                    $(this.opts.selector).html("");
                    this.renderTree(checkedNodeIdList);
                },

                clearTree: function () {
                    $(this.opts.selector).html("");
                },

                getSelected: function () {
                    return this.tree.getSelectedNodes();
                },

                getChecked: function () {
                    return this.tree.getCheckedNodes();
                },

                checkAllNodes: function (checked) {
                    this.tree.checkAllNodes(checked);
                },

                checkNode: function (node, checked) {
                    this.tree.checkNode(node, checked);
                },

                getRoot: function () {
                    return this.tree.getNodeByParam("id", 0);
                },

                addNode: function (parentNode, node) {
                    parentNode = parentNode || this.getRoot();
                    this.tree.addNodes(parentNode, [node]);
                },

                getNodes: function (key, value) {
                    return this.tree.getNodesByParam(key, value);
                },

                removeNode: function (node) {
                    return this.tree.removeNode(node);
                }
            };

            return tree.init(config);
        }
    };
});