define(['jquery', 'app/common/winTool','app/common/util','app/common/commonTool'], function ($, winTool, util, commonTool) {

    String.prototype.getBytesLength = function () {
        if(this) return this.length;
        return 0;
    }

    var FloorPlan = {
        mode: 1,
        doorNoColor: 'green',
        mapAreaID: 0,
        shopID: 0,
        mapAreaList: [],
        shopList: [],
        parkingList: [],
        iframe: $('#iframeSVG')[0].contentDocument,
        level: 1,
        mallID: $('#mallID').val(),
        init: function() {
            var _this = this;
            this.getFloorPlan();
            $('#mode a').click(function(e) {console.info(1)
                _this.mode = parseInt($(e.target).closest('li').attr('data-mode'));
                console.info(_this.mode)
                return _this.initMarker();
            });
            $('#floor a').click(function(e) {
                return _this.getFloorPlan(e.target);
            });

            _this.initMarker();
            _this.setIcons();
            
            function _load_iframe(){
            	_this.iframe = $('#iframeSVG')[0].contentDocument;
            	//console.log(_this.iframe);
                _this.setIcons();
                //added by  guojun at 20141229 描述：针对铺位号或商户主入口点击事件
                var a = _this.iframe.querySelector('#viewport');
                $(a).click(function (e) {
                    var tar = e.target, $tar = $(tar);
                    //如果是doorno类型则调用删除铺位号主入口函数，参数为当前的mapareaid
                    if ($tar.is('.doorno')) {
                        _this.delDoolLoc($tar.attr('mapareaid'), e);
                    }
                })//end

                return _this.initMarker();
            }
            
            _load_iframe();
            
//            $('#iframe').on('load', function() {
//                _this.iframe = $('#iframeSVG')[0].contentDocument;
//                _this.setIcons();
//                //added by  guojun at 20141229 描述：针对铺位号或商户主入口点击事件
//                var a = _this.iframe.querySelector('#viewport');
//                $(a).click(function (e) {
//                    var tar = e.target, $tar = $(tar);
//                    //如果是doorno类型则调用删除铺位号主入口函数，参数为当前的mapareaid
//                    if ($tar.is('.doorno')) {
//                        _this.delDoolLoc($tar.attr('mapareaid'), e);
//                    }
//                })//end
//
//                return _this.initMarker();
//            });
            $('#btnShopDetails').click(function() {
                return window.open('/Shop/EditBasicInfo?shopID=' + _this.shopID);
            });
            $('#btnEditShop').click(function() {
                return _this.showModal1();
            });
//            $('#btnDelShop').click(function() {
//                return _this.delShop();
//            });
//            $('#btnSaveShop').click(function() {
//                return _this.saveShop();
//            });
//            $('#btnSaveDoorNo').click(function() {
//                return _this.saveDoorNo();
//            });
//            $('#btnSaveParkingNo').click(function() {
//                return _this.saveParkingNo();
//            });
            var $fra = $('#iframeSVG')[0].contentWindow;
            $('.act_zoom_1').click(function(evt){
                $fra.zoom('zoom');
            })
            $('.act_zoom_0').click(function(evt){
                $fra.zoom('in');
            })


            /*
             (function (e) {
             var tar = e.target, $tar = $(tar);
             alert(2)
             if (tar.className.toLowerCase() == 'doorno') {
             alert(1)
             }
             })
             */
            $("#shopID1").select2({
                placeholder: "请选择商户",
                minimumInputLength: 1,
                ajax: {
                    type: "POST",
                    url: "/Service/GetShopList",
                    dataType: 'json',
                    data: function(term, page) {
                        return {
                            mallid: _this.mallID,
                            name: term,
                            limit: 10,
                            isNormal: false
                        };
                    },
                    results: function(data, page) {
                        return {
                            results: data.shops
                        };
                    }
                },
                id: function(obj) {
                    return obj.id;
                },
                formatResult: function(shop) {
                    var folder, markup;
                    folder = "mc";
                    markup = "<table class='movie-result'><tr>                    <td valign='top'><h5 style='margin-left:5px;'>" + '[' + shop.Floor + '层] ' + shop.name + "</h5>                    </td></tr></table>";
                    return markup;
                },
                formatSelection: function(shop) {
                    //App.blockUI($('.modal-content'), false);
                    $.ajax({
                        type: "POST",
                        data: {
                            'shopid': shop.id,
                            'mallID': _this.mallID
                        },
                        url: "/Mall/CheckShopLoc",
                        success: function(res) {
                            //App.unblockUI($('.modal-content'));
                            if ((res != null) && res.success) {
                                return $('#shopExist').val(res.isExist);
                            } else {
                                //return App.showTips("操作失败，请重试!");
                            }
                        },
                        error: function(res) {
                            //App.showTips("操作失败，请重试!");
                            //return App.unblockUI($('.modal-content'));
                        }
                    });
                    $('#shopName1').val(shop.name);
                    return '[' + shop.Floor + '层] ' + shop.name;
                },
                dropdownCssClass: "bigdrop",
                escapeMarkup: function(m) {
                    return m;
                }
            });
            return window.fp = FloorPlan;
        },
        getFloorPlan: function(e) {
            /*var floorID;
            floorID = e != null ? $(e).closest('li').attr('data-id') : $("#floor > .active").attr('data-id');
            if (floorID != null) {
                App.blockUI($('.portlet-body'), false);
                return $('#iframeSVG').attr('src', '/mall/GetFloorPlan?mallid=' + this.mallID + '&floorid=' + floorID);
            }*/
        },
        initMarker: function() {
            var floorID,
                _this = this;
            //App.blockUI($('.portlet-body'), false);
            floorID = $("#floor > .active").attr('data-id');
            this.level = $('#iframeSVG')[0].contentWindow.level;
            $('text,.doorno', this.iframe).remove();
            return $.ajax({
                type: "POST",
                dataType:"json",
                data: {
                    //'mallID': this.mallID,
                    //'floorID': floorID
                    'cpId': this.mallID,
                    'flId': floorID
                },
//                url: cdnURI + "scripts/app/svg/floorData.json",
                url: serverHost + "mall/svgAjax/querySvg.json",
                success: function(res) {
                    console.info(res)

                    var $area, area, cxy, d, doorLoc, doorNo, scxy, shop, _i, _j, _k, _l, _len, _len1, _len2, _len3, _len4, _len5, _m, _n, _ref, _ref1, _ref2, _ref3, _ref4, _ref5;
                    //App.unblockUI($('.portlet-body'));
                    if ((res != null) && res.success) {
                        _this.shopList = res.model.shopList;
                        _this.mapAreaList = res.model.mapAreaList;
                        _this.parkingList = res.model.parkingList;
//                        _this.shopList = res.shopList;
//                        _this.mapAreaList = res.mapAreaList;
//                        _this.parkingList = res.parkingList;
                        _ref = $('._area_merchant', _this.iframe);
                        console.info(res)
                        for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                            area = _ref[_i];
                            $area = $(area);
                            shop = null;
                            _ref1 = _this.shopList;
                            for (_j = 0, _len1 = _ref1.length; _j < _len1; _j++) {
                                d = _ref1[_j];
                                if (!(d.mapAreaID === $area.attr('id'))) {
                                    continue;
                                }
                                shop = d;
                                break;
                            }
                            _ref2 = _this.mapAreaList;
                            for (_k = 0, _len2 = _ref2.length; _k < _len2; _k++) {
                                d = _ref2[_k];
                                if (!(d.id === $area.attr('id'))) {
                                    continue;
                                }
                                doorNo = d.doorNo;
                                doorLoc = d.doorLoc;
                                break;
                            }
                            if (shop != null) {
                                if (_this.mode === 3) {
                                    _this.setText($area, shop.id, doorNo, null, _this.doorNoColor);
                                } else {
                                    _this.setText($area, shop.id, shop.name);
                                }
                            } else {
                                _this.setText($area, null, doorNo, null, _this.doorNoColor);
                            }
                            if (_this.mode === 2) {
                                _this.setDoorLoc($area.attr('id'), doorLoc);
                            }
                        }
                        if (_this.mode === 3) {
                            _ref3 = $('._area_parking', _this.iframe);
                            for (_l = 0, _len3 = _ref3.length; _l < _len3; _l++) {
                                area = _ref3[_l];
                                $area = $(area);
                                _ref4 = _this.parkingList;
                                for (_m = 0, _len4 = _ref4.length; _m < _len4; _m++) {
                                    d = _ref4[_m];
                                    if (!(d.mapAreaID === $area.attr('id'))) {
                                        continue;
                                    }
                                    _this.setText($area, null, d.ParkingNo, null);
                                    break;
                                }
                            }
                        }
                        if (_this.mode === 4) {
                            _ref5 = $('._area_parking,._area_merchant', _this.iframe);
                            for (_n = 0, _len5 = _ref5.length; _n < _len5; _n++) {
                                area = _ref5[_n];
                                $area = $(area);
                                scxy = $area.attr('cxy');
                                if (scxy != null) {
                                    cxy = {
                                        X: parseFloat(scxy.split(',')[0]),
                                        Y: parseFloat(scxy.split(',')[1])
                                    };
                                    _this.setMapAreaCxy($area.attr('id'), cxy);
                                }
                            }
                        }
                        return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
                   
                    }
                },
                error: function(res) {
                    //App.showTips("数据获取失败，请重试!");
                    //return App.unblockUI($('.portlet-body'));
                }
            });
        },
        setDoorLoc: function(mapareaid, loc) {
            var $circle, circle, zoom;
            if ((loc != null) && loc.X !== 0 && loc.Y !== 0) {
                zoom = $('#iframeSVG')[0].contentWindow.zoomvalue;
                circle = this.iframe.createElementNS('http://www.w3.org/2000/svg', 'circle');
                $circle = $(circle);
                $circle.attr('cx', loc.X);
                $circle.attr('cy', loc.Y);
                $circle.attr('r', 6 * this.level / zoom);
                $circle.attr('fill', 'yellow');
                $circle.attr('stroke', '#333333');
                $circle.attr('stroke-width', 1 * this.level / zoom);
                $circle.attr('cursor', 'pointer');
                $circle.attr('mapareaid', mapareaid);
                $circle.attr('class', 'doorno');
                //$circle.attr('onclick', 'delDoolLoc('+mapareaid+')');
                return $('#viewport', this.iframe).append($circle);
            }
        },
        //added by  guojun at 20141229 描述：实现提示删除铺位主入口功能，点击铺位号或是商户主入口的小圆圈即可
        delDoolLoc: function (mapareaid, e) {
        	var text = $('text[mapareaid="' + mapareaid + '"]', this.iframe),
        		shopId = $(text).attr("shopid");
        	
            _this = this;
            if (confirm("确认要删除该商铺的主入口？"))
            {
                //保存当前状态
                var d, doorNo, _i, _len, _ref1, _ref2;
                //if ($('#iframeSVG')[0].contentWindow.moveflag === false) {
                //App.blockUI($('.portlet-body'), false);
                x = 0, y = 0;
                _ref2 = this.mapAreaList;
                for (_i = 0, _len = _ref2.length; _i < _len; _i++) {
                    d = _ref2[_i];
                    if (d.id === mapareaid) {
                        doorNo = d.doorNo;
                    }
                }
                return $.ajax({
                    type: "POST",
                    data: {
                        'mallID': this.mallID,
                        'floorID': $("#floor > .active").attr('data-id'),
                        'id': shopId,
                        'mapAreaID': mapareaid,
                        'doorNo': doorNo,
                        'positionX': x,
                        'positionY': y
                    },
                    //url: "/Mall/SaveDoorLoc",
                    url: serverHost + "mall/svgAjax/updateShop.json",
//                    url: cdnURI + "scripts/app/svg/floorData.json",
                    success: function (res) {

                        //App.unblockUI($('.portlet-body'));

                        if ((res != null) && res.success) {

                            _this.clearDoorLoc(mapareaid);

                            return _this.setDoorLoc(mapareaid, {
                                X: x,
                                Y: y
                            });

                        } else {
                            //return App.showTips("操作失败，请重试!");
                        }
                    },
                    error: function (res) {
                        //App.showTips("操作失败，请重试!");
                        //return App.unblockUI($('.modal-content'));
                    }
                });
                //}//end
                //调用清除主入口标记函数
                _this.clearDoorLoc(mapareaid);
            }
        },
        clearDoorLoc: function(mapareaid) {
            var circle;
            circle = $('.doorno[mapareaid="' + mapareaid + '"]', this.iframe);
            if (circle[0] != null) {
                return circle.remove();
            }
        },
        setMapAreaCxy: function(mapareaid, loc) {
            var $circle, circle, zoom;
            if ((loc != null) && loc.X !== 0 && loc.Y !== 0) {
                zoom = $('#iframeSVG')[0].contentWindow.zoomvalue;
                circle = this.iframe.createElementNS('http://www.w3.org/2000/svg', 'circle');
                $circle = $(circle);
                $circle.attr('cx', loc.X);
                $circle.attr('cy', loc.Y);
                $circle.attr('r', 6 * this.level / zoom);
                $circle.attr('fill', 'yellow');
                $circle.attr('stroke', '#333333');
                $circle.attr('stroke-width', 1 * this.level / zoom);
                $circle.attr('mapareaid', mapareaid);
                $circle.attr('class', 'doorno');
                return $('#viewport', this.iframe).append($circle);
            }
        },
        clearMapAreaCxy: function(mapareaid) {
            var circle;
            circle = $('.doorno[mapareaid="' + mapareaid + '"]', this.iframe);
            if (circle[0] != null) {
                return circle.remove();
            }
        },
        setIcons: function() {
            var $img, area, icon, img, index, x, y, _i, _len, _ref, _ref1, _results;
            index = 0;
            _ref = $('g[class^="_area_"][class!="_area_merchant"][class!="_area_parking"][class!="_area_blocked"][class!="_area_background"][class!="_area_unaccessible"]', this.iframe);
            _results = [];
            for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                area = _ref[_i];
                index++;
                area = $(area);
                area.attr('id', 'icon_' + index);
                icon = area.attr('class').replace('_area_', '');
                _ref1 = this.getAreaCenter(area), x = _ref1.x, y = _ref1.y;
                img = this.iframe.createElementNS('http://www.w3.org/2000/svg', 'image');
                img.setAttributeNS('http://www.w3.org/1999/xlink', 'href', cdnURI + 'images/svg/' + icon + '.png');
                $img = $(img);
                $img.attr('x', x - (20 / 2) * this.level);
                $img.attr('y', y - (20 / 2) * this.level);
                $img.attr('cx', x);
                $img.attr('cy', y);
                $img.attr('mapareaid', area.attr('id'));
                $img.css('visibility', 'hidden');
                _results.push($('#viewport', this.iframe).append($img));
            }
            return _results;
        },
        setText: function($area, shopID, shopName, bgColor, txtColor) {
            var $areaChild, $text, $tspan, text, tspan, x, y, _ref,
                _this = this;
            if ($area[0] != null) {
                if (this.mode !== 4) {
                    $('text[mapareaid="' + $area.attr('id') + '"]', this.iframe).remove();
                    _ref = this.getAreaCenter($area), x = _ref.x, y = _ref.y;
                    text = this.iframe.createElementNS('http://www.w3.org/2000/svg', 'text');
                    $text = $(text);
                    $text.attr('x', x);
                    $text.attr('y', y + (6 / 2) * this.level);
                    $text.attr('mapareaid', $area.attr('id'));
                    $text.attr('shopid', shopID);
                    $text.attr('length', !!!shopName ? 0 : shopName.getBytesLength());
                    $text.attr('text-anchor', 'middle');
                    $text.text(shopName);
                    if (this.mode !== 2) {
                        $text.attr('class', 'link');
                    }
                    if (txtColor != null) {
                        $text.attr('fill', txtColor);
                    }
                    tspan = this.iframe.createElementNS('http://www.w3.org/2000/svg', 'tspan');
                    $tspan = $(tspan);
                    $tspan.attr('class', 'dot');
                    $tspan.text('.');
                    $text.prepend($tspan);
                    $('#viewport', this.iframe).append($text);
                }
                $areaChild = $area.children().eq(0);
                if (bgColor != null) {
                    $areaChild.attr('fill', bgColor);
                }
                if (this.mode === 2) {
                    $areaChild.unbind();
                    $areaChild.hover(function(e) {
                        return $areaChild.attr('class', 'shadow');
                    }, function(e) {
                        return $areaChild.removeAttr('class');
                    }).click(function(e) {
                        var d, doorNo, mapareaid, _i, _len, _ref1, _ref2;
                        if ($('#iframeSVG')[0].contentWindow.moveflag === false) {
                            //App.blockUI($('.portlet-body'), false);
                            mapareaid = $areaChild.parent().attr('id');
                            _ref1 = $('#iframeSVG')[0].contentWindow.getSVGPoint(e.clientX, e.clientY), x = _ref1.x, y = _ref1.y;
                            _ref2 = _this.mapAreaList;
                            for (_i = 0, _len = _ref2.length; _i < _len; _i++) {
                                d = _ref2[_i];
                                if (d.id === mapareaid) {
                                    doorNo = d.doorNo;
                                }
                            }
                            console.info({
                                'mallID': _this.mallID,
                                'floorID': $("#floor > .active").attr('data-id'),
                                'shopID': shopID,
                                'mapAreaID': mapareaid,
                                'doorNo': doorNo,
                                'x': x,
                                'y': y
                            })
                            return $.ajax({
                                type: "POST",
                                data: {
                                    'mallID': _this.mallID,
                                    'floorID': $("#floor > .active").attr('data-id'),
                                    'id': shopID,
                                    'mapAreaID': mapareaid,
                                    'doorNo': doorNo,
                                    'positionX': x,
                                    'positionY': y
                                },
                                dataType: "json",
                                //url: "/Mall/SaveDoorLoc",
                                url: serverHost + "mall/svgAjax/updateShop.json",
//                                url: cdnURI + "scripts/app/svg/floorData.json",
                                success: function(res) {console.info(res)
                                    //App.unblockUI($('.portlet-body'));
                                    if ((res != null) && res.success) {
                                        _this.clearDoorLoc(mapareaid);
                                        return _this.setDoorLoc(mapareaid, {
                                            X: x,
                                            Y: y
                                        });
                                    } else {
                                        //return App.showTips("操作失败，请重试!");
                                    }
                                },
                                error: function(res) {
                                    //App.showTips("操作失败，请重试!");
                                    //return App.unblockUI($('.modal-content'));
                                }
                            });
                        } else {
                        	console.info("true");
                        }
                        	
                    });
                } else if (this.mode === 4) {
                    $areaChild.unbind();
                    $areaChild.hover(function(e) {
                        return $areaChild.attr('class', 'shadow');
                    }, function(e) {
                        return $areaChild.removeAttr('class');
                    }).click(function(e) {
                        var mapareaid, _ref1;
                        if ($('#iframeSVG')[0].contentWindow.moveflag === false) {
                            //App.blockUI($('.portlet-body'), false);
                            mapareaid = $areaChild.parent().attr('id');
                            _ref1 = $('#iframeSVG')[0].contentWindow.getSVGPoint(e.clientX, e.clientY), x = _ref1.x, y = _ref1.y;
                            return $.ajax({
                                type: "POST",
                                data: {
                                	'id': shopID,
                                    'mallID': _this.mallID,
                                    'floorID': $("#floor > .active").attr('data-id'),
                                    'mapAreaID': mapareaid,
                                    'entranceX': x,
                                    'entranceY': y
                                },
                                dataType: "json",
                                //url: "/Mall/SaveMapAreaCxy",
                                url: serverHost + "mall/svgAjax/updateShop.json",
//                                url: cdnURI + "scripts/app/svg/floorData.json",
                                success: function(res) {console.info(res)
                                    //App.unblockUI($('.portlet-body'));
                                    if ((res != null) && res.success) {
                                        _this.clearMapAreaCxy(mapareaid);
                                        _this.setMapAreaCxy(mapareaid, {
                                            X: x,
                                            Y: y
                                        });
                                        return $('#' + mapareaid, _this.iframe).attr("cxy", x + "," + y);
                                    } else {
                                        //return App.showTips("操作失败，请重试!");
                                    }
                                },
                                error: function(res) {
                                    //App.showTips("操作失败，请重试!");
                                    //return App.unblockUI($('.modal-content'));
                                }
                            });
                        }
                    });
                } else {
                    $areaChild.unbind();
                }
                if (this.mode === 1) {
                    return $text.click(function() {
                        if ($('#iframeSVG')[0].contentWindow.moveflag === true) {
                            return;
                        }
                        _this.shopID = $text.attr('shopid');
                        _this.mapAreaID = $text.attr('mapareaid');
                        if (_this.shopID != null) {
                            return $.ajax({
                                type: "POST",
                                data: {
                                    'id': _this.shopID
                                },
                                dataType: "json",
                                url: serverHost + "mall/svgAjax/queryCpshop.json",
//                                url: cdnURI + "scripts/app/svg/getShop.json",
                                success: function(res) {
                                    //App.unblockUI($('.portlet-body'));
                                    if ((res != null) && res.success) {
                                    	if(!!!res.model.ctName){
                                    		return _this.showModal1(_this.shopID);
                                    	} else {
                                    		return _this.showModal(res.model);
                                    	}
                                        
                                    } else {
                                        //return App.showTips("操作失败，请重试!");
                                    }
                                },
                                error: function(res) {
                                    //App.showTips("操作失败，请重试!");
                                    //return App.unblockUI($('.portlet-body'));
                                }
                            });
                        }
                    });
                } else if (this.mode === 3) {
                    return $text.click(function() {
                        var d, _i, _j, _len, _len1, _ref1, _ref2, _shoNo;
                        if ($('#iframeSVG')[0].contentWindow.moveflag === true) {
                            return;
                        }
                        _this.shopID = $text.attr('shopid');
                        _this.mapAreaID = $text.attr('mapareaid');
                        if ($('#' + _this.mapAreaID, _this.iframe).attr('class') === '_area_merchant') {
                            _ref1 = _this.mapAreaList;
                            for (_i = 0, _len = _ref1.length; _i < _len; _i++) {
                                d = _ref1[_i];
                                if (!(d.id === _this.mapAreaID)) {
                                    continue;
                                }
                                $('#doorNo1').val(d.doorNo);
                                _shoNo = d.doorNo;
                                break;
                            }
                            _this.showModal2(_this.shopID, _shoNo);
                            
                        }
                        if ($('#' + _this.mapAreaID, _this.iframe).attr('class') === '_area_parking') {
                            _ref2 = _this.parkingList;
                            for (_j = 0, _len1 = _ref2.length; _j < _len1; _j++) {
                                d = _ref2[_j];
                                if (!(d.mapAreaID === _this.mapAreaID)) {
                                    continue;
                                }
                                $('#area').val(d.Area);
                                $('#parkingNo').val(d.ParkingNo);
                                break;
                            }
                            return _this.showModal3();
                        }
                    });
                }
            }
        },
        clearMapArea: function(mapAreaID) {
            var area, d, _i, _len, _ref, _results;
            area = $('#' + mapAreaID, this.iframe);
            if (area != null) {
                area.children().eq(0).attr('fill', '#ffffff');
                $('text[mapareaid="' + mapAreaID + '"]', this.iframe).remove();
                _ref = this.mapAreaList;
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                    d = _ref[_i];
                    if (!(d.id === mapAreaID)) {
                        continue;
                    }
                    this.setText(area, null, d.doorNo, null, this.doorNoColor);
                    break;
                }
                return _results;
            }
        },
        getAreaCenter: function($area) {
            var cx, cy, height, rect, width, x, x1, x2, y, y1, y2, _ref, _ref1, _ref2, _ref3, _ref4;
            if (($area.attr('cxy') != null) && $area.attr('cxy') !== '') {
                _ref = $area.attr('cxy').split(','), cx = _ref[0], cy = _ref[1];
            } else if (($area.attr('textBox') != null) && $area.attr('textBox') !== '') {
                _ref1 = $area.attr('textBox').split(' '), x1 = _ref1[0], y1 = _ref1[1], x2 = _ref1[2], y2 = _ref1[3];
                cx = (parseFloat(x1) + parseFloat(x2)) / 2;
                cy = (parseFloat(y1) + parseFloat(y2)) / 2;
            } else if (($area.attr('textbox') != null) && $area.attr('textbox') !== '') {
                _ref2 = $area.attr('textbox').split(' '), x1 = _ref2[0], y1 = _ref2[1], x2 = _ref2[2], y2 = _ref2[3];
                cx = (parseFloat(x1) + parseFloat(x2)) / 2;
                cy = (parseFloat(y1) + parseFloat(y2)) / 2;
            } else {
                rect = $area.find('rect');
                if (rect[0] != null) {
                    _ref3 = [rect.attr('x'), rect.attr('y'), parseFloat(rect.attr('x')) + parseFloat(rect.attr('width')), parseFloat(rect.attr('y')) + parseFloat(rect.attr('height'))], x1 = _ref3[0], y1 = _ref3[1], x2 = _ref3[2], y2 = _ref3[3];
                    cx = (parseFloat(x1) + parseFloat(x2)) / 2;
                    cy = (parseFloat(y1) + parseFloat(y2)) / 2;
                } else {
                    _ref4 = $area.children().eq(0)[0].getBBox(), x = _ref4.x, y = _ref4.y, width = _ref4.width, height = _ref4.height;
                    cx = x + width / 2;
                    cy = y + height / 2;
                }
            }
            return {
                x: parseFloat(cx),
                y: parseFloat(cy)
            };
        },
        showModal: function(shop) {
        	_this = this;
            $('#doorNo').html(shop.shopNo);
            $('#shopName').html(shop.ctName);
            $('#opStatus').html(shop.ctStatus == 1 ? "营业" : "非营业");
            $('#status').html(shop.ctStatus == 1 ? "正常" : "禁用");
            $('#shopId').val(shop.id);
            $('#relationId').val(shop.relationId);
            var win = winTool.create({
                title: "入驻商户信息",
                showOk: false,
                cancelName: "关闭",
                type: 'selector',
                selector: "#shopInfo-tpl",
                width: 500,
                height: 100,
                buttons: [
					{
						name: '商户详情', 
						cls: '', 
						handler: function(win){
					    	win.close();
					    	window.location.href = serverHost + "shop/shopInfo.htm?id=" + shop.ctId;
						}
					},
					{
						name: '移除商户', 
						cls: '', 
						handler: function(win){
				            if (confirm('确认移除入驻商户【' + $("#shopName").html() + '】?')) {
				                //App.blockUI($('.modal-content'), false);
				                return $.ajax({
				                    type: "POST",
				                    data: {
				                        'shopId': $('#shopId').val(),
				                        'id': $('#relationId').val()
				                    },
				                    url: serverHost + "mall/svgAjax/deleteRelationship.json",
//				                    url: "/Mall/DelShopLoc",
				                    success: function(res) {
				                        //App.unblockUI($('.modal-content'));
				                        if ((res != null) && res.success) {
				                        	win.close();
				                            _this.clearMapArea(_this.mapAreaID);
				                            return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
				                        } else {
				                            //return App.showTips("操作失败，请重试!");
				                        }
				                    },
				                    error: function(res) {
				                        //App.showTips("操作失败，请重试!");
				                        //return App.unblockUI($('.modal-content'));
				                    }
				                });
				            }
						}
					},    
                    {
                    	name: '变更商户', 
                    	cls: '', 
                    	handler: function(win){
	                    	win.close();
	                    	_this.showModal1(win.find('#shopId').val());
                    	}
                    }
                ]
            });
        },
        showModal1: function(shopId) {
        	var _this = this;
            $('#shopModal').modal('hide');
//            $('#shopModal1').modal('show');
//            $('#shopID1').select2('val', '');
        	
            var win = winTool.create({
                title: "变更/重新入驻商户",
                okName: "确定",
                cancelName: "关闭",
                type: 'selector',
                selector: "#shopChange-tpl",
                width: 500,
                height: 100,
                okFn: function (win) {
                	var ct = win.find("#shop").select2("data") || {},
                		ctId = ct.id || "",
                		ctName = ct.text || "";
                	var isDelete = win.find("#deleteShop").prop("checked") == true ? 1 : 2;
                	if(!ctId){
                		alert('请先选择待入驻商户');
                		return;
                	} else {
                		util.ajax({
                            url: serverHost + "mall/svgAjax/updateRelationship.json",
                            params: {
                            	ctId : ctId,
                            	shopId : shopId,
                            	isDelete : isDelete
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	win.close();
                                	//TODO
                                	_this.setText($('#' + _this.mapAreaID, _this.iframe), shopId, ctName, null);
                                    return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
                                } else {
                                	
                                }
                            }
                        });
                	}
                }
            });
        	commonTool.renderCT({
				msg : "选择商户",
				selector : win.find("#shop"),
				width : 240,
				paramFn : function() {
					return {
						cpId: $(".mallId").val(),
                        permission: true
					};
				},
				listeners : {
					change : function() {
					}
				}
			});
            return $('#shopExist').val(null);
        },
        //位置编号编辑
        showModal2: function(shopId,shopNo) {
        	var _this = this;
        	console.info(_this);
            var win = winTool.create({
                title: "铺位号修改",
                okName: "确定",
                cancelName: "关闭",
                type: 'selector',
                selector: "#shopNo-tpl",
                width: 500,
                height: 100,
                okFn: function (win) {
                	var newShopNo = win.find("#doorNo1").val();
                    if (newShopNo === '') {
                        alert('请填写铺位号');
                        return;
                    }
                	util.ajax({
                        url: serverHost + "mall/svgAjax/updateShop.json",
                        params: {
                        	id : shopId,
                        	shopNo : newShopNo
                        },
                        success: function (resp) {
                            if(resp.success) {
                            	win.close();
                            	_this.setText($('#' + _this.mapAreaID, _this.iframe), _this.shopID, newShopNo, null, _this.doorNoColor);
                                return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
                            } else {
                            	
                            }
                        }
                    });
                }
            });
            win.find("#doorNo1").val(shopNo);
        },
        showModal3: function() {alert(3)
            return $('#shopModal3').modal('show');
        },
//        saveShop: function() {
//            var d, doorNo, removeLoc, _i, _len, _ref,
//                _this = this;
//            if ($('#shopID1').val() === '') {
//                alert('请先选择待入驻商户');
//                return;
//            }
//            removeLoc = $('#ckbRemove').attr('checked') != null;
//            if (($('#shopExist').val() === 'true' && (!removeLoc || confirm('商户【' + $('#shopName1').val() + '】在本商场中已落位,确认继续变更？'))) || $('#shopExist').val() !== 'true') {
//                //App.blockUI($('.modal-content'), false);
//                _ref = this.mapAreaList;
//                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
//                    d = _ref[_i];
//                    if (!(d.id === this.mapAreaID)) {
//                        continue;
//                    }
//                    doorNo = d.doorNo;
//                    break;
//                }
//                return $.ajax({
//                    type: "POST",
//                    data: {
//                        'mallID': this.mallID,
//                        'floorID': $("#floor > .active").attr('data-id'),
//                        'floor': $("#floor > .active").attr('data-text'),
//                        'mapAreaID': this.mapAreaID,
//                        'doorNo': doorNo,
//                        'oshopid': this.shopID,
//                        'shopid': $('#shopID1').val(),
//                        'shopname': $('#shopName1').val(),
//                        'removeOldLoc': removeLoc
//                    },
//                    url: "/Mall/SaveShopLoc",
//                    success: function(res) {
//                        var shopID1;
//                        //App.unblockUI($('.modal-content'));
//                        if ((res != null) && res.success) {
//                            $('#shopModal1').modal('hide');
//                            shopID1 = $('#shopID1').val();
//                            _this.setText($('#' + _this.mapAreaID, _this.iframe), shopID1, $('#shopName1').val(), res.color);
//                            return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
//                        } else {
//                            //return App.showTips("操作失败，请重试!");
//                        }
//                    },
//                    error: function(res) {
//                        //App.showTips("操作失败，请重试!");
//                        //return App.unblockUI($('.modal-content'));
//                    }
//                });
//            }
//        },
//        delShop: function() {
//            var _this = this;
//            if (confirm('确认移除入驻商户【' + $("#shopName").html() + '】?')) {
//                //App.blockUI($('.modal-content'), false);
//                return $.ajax({
//                    type: "POST",
//                    data: {
//                        'mallID': this.mallID,
//                        'floorID': $("#floor > .active").attr('data-id'),
//                        'mapAreaID': this.mapAreaID,
//                        'shopID': this.shopID
//                    },
//                    url: "/Mall/DelShopLoc",
//                    success: function(res) {
//                        //App.unblockUI($('.modal-content'));
//                        if ((res != null) && res.success) {
//                            $('#shopModal').modal('hide');
//                            _this.clearMapArea(_this.mapAreaID);
//                            return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
//                        } else {
//                            //return App.showTips("操作失败，请重试!");
//                        }
//                    },
//                    error: function(res) {
//                        //App.showTips("操作失败，请重试!");
//                        //return App.unblockUI($('.modal-content'));
//                    }
//                });
//            }
//        },
//        saveDoorNo: function() {
//            var _this = this;
//            if ($('#doorNo1').val() === '') {
//                alert('请填写铺位号');
//                return;
//            }
//            //App.blockUI($('.modal-content'), false);
//            return $.ajax({
//                type: "POST",
//                data: {
//                    'mallID': this.mallID,
//                    'floorID': $("#floor > .active").attr('data-id'),
//                    'mapAreaID': this.mapAreaID,
//                    'doorNo': $('#doorNo1').val(),
//                    'shopid': this.shopID
//                },
//                url: "/Mall/SaveDoorNo",
//                success: function(res) {
//                    //App.unblockUI($('.modal-content'));
//                    if ((res != null) && res.success) {
//                        $('#shopModal2').modal('hide');
//                        _this.setText($('#' + _this.mapAreaID, _this.iframe), _this.shopID, $('#doorNo1').val(), null, _this.doorNoColor);
//                        return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
//                    } else {
//                        //return App.showTips("操作失败，请重试!");
//                    }
//                },
//                error: function(res) {
//                    //App.showTips("操作失败，请重试!");
//                    //return App.unblockUI($('.modal-content'));
//                }
//            });
//        },
//        saveParkingNo: function() {
//            var _this = this;
//            if ($('#parkingNo').val() === '') {
//                alert('请填写停车场车位编号');
//                return;
//            }
//            //App.blockUI($('.modal-content'), false);
//            return $.ajax({
//                type: "POST",
//                data: {
//                    'mallID': this.mallID,
//                    'floorID': $("#floor > .active").attr('data-id'),
//                    'mapAreaID': this.mapAreaID,
//                    'parkingNo': $('#parkingNo').val(),
//                    'area': $('#area').val()
//                },
//                url: "/Mall/SaveParkingNo",
//                success: function(res) {
//                    //App.unblockUI($('.modal-content'));
//                    if ((res != null) && res.success) {
//                        $('#shopModal3').modal('hide');
//                        _this.setText($('#' + _this.mapAreaID, _this.iframe), null, $('#parkingNo').val());
//                        return $('#iframeSVG')[0].contentWindow.showAllMarkers(_this.iframe);
//                    } else {
//                        //return App.showTips("操作失败，请重试!");
//                    }
//                },
//                error: function(res) {
//                    //App.showTips("操作失败，请重试!");
//                    //return App.unblockUI($('.modal-content'));
//                }
//            });
//        }
    };

    return FloorPlan;
});
