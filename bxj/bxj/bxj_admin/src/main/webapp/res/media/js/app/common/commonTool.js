define(['jquery',
    'app/common/selectTool'
], function ($, selectTool) {
    var defaults_dict = {
        msg: '空白',
        url: null,
        selector: null,
        width: 150,
        showSearch: true,
        queryParamName: "name",
        val: null
    };

    return {

    	renderKnow: function (config) {
           config = config || {};
           config = $.extend({
               idName: "id",
               textName: "text"
           }, defaults_dict, config);
           config.queryParamName = "text";
           config.url = serverHost + "/studyapp/getAllkonwInfo.json";
           selectTool.renderSelect(config);
        },

    	/**
    	 * 视频分类
    	 */
        renderChannel: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "fid",
	            textName: "title"
	        }, defaults_dict, config);
	        config.queryParamName = "channelName";
	        config.url = serverHost + "/common/queryChannel.json";
	        selectTool.renderSelect(config);
	    },
    	/**
    	 * 获取所有讲师 text = id : text
    	 */
        renderLecture: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "fid",
	            textName: "name"
	        }, defaults_dict, config);
	        config.queryParamName = "name";
	        config.url = serverHost + "/common/queryLectures1.json";
	        selectTool.renderSelect(config);
	    },
	    /**
    	 * 获取所有讲师
    	 */
        renderLectures: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "fid",
	            textName: "name"
	        }, defaults_dict, config);
	        config.queryParamName = "name";
	        config.url = serverHost + "/common/queryLectures2.json";
	        selectTool.renderSelect(config);
	    },
	    /**
    	 * 获取所有标签
    	 */
        renderTag: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "fid",
	            textName: "name"
	        }, defaults_dict, config);
	        config.queryParamName = "name";
	        config.url = serverHost + "/common/queryTags.json";
	        selectTool.renderSelect(config);
	    },
	    /**
	     * 获取所有产品分类
	     */
	    renderCategroy: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "fid",
	            textName: "cname"
	        }, defaults_dict, config);
	        config.queryParamName = "cname";
	        config.url = serverHost + "/product/queryCategroy.json";
	        selectTool.renderSelect(config);
	    },
	    /**
	     * 获取所有砸蛋奖品
	     */
	    renderEggPrize: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "id",
	            textName: "name"
	        }, defaults_dict, config);
	        config.queryParamName = "name";
	        config.url = serverHost + "/common/queryAllEggPrizes.json";
	        selectTool.renderSelect(config);
	    },
        /**
         * 获取url中参数
         * @param name
         * @returns {*}
         */
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]);
            return null; //返回参数值
        },
	    /**
    	 * 获取所有讲师
    	 */
        renderOnlineLectures: function (config) {
	        config = config || {};
	        config = $.extend({
	            idName: "id",
	            textName: "name"
	        }, defaults_dict, config);
	        config.queryParamName = "name";
	        config.url = serverHost + "/online/queryLectures.json";
	        selectTool.renderSelect(config);
	    },
	    /**朋友圈 分类**/
	    renderFriend: function (config) {
            config = config || {};
            config = $.extend({
                idName: "fid",
                textName: "title"
            }, defaults_dict, config);
            config.queryParamName = "title";
            config.url = serverHost + "/friendCateGory/queryCategoryList.json";
            selectTool.renderSelect(config);
         },
		/** 商品列表 **/
		renderGoods: function (config) {
			config = config || {};
			config = $.extend({
				idName: "id",
				textName: "name"
			}, defaults_dict, config);
			config.queryParamName = "name";
			config.url = serverHost + "/goods/list.json";
			selectTool.renderSelect(config);
		},
		/** 新人通课程类别列表 **/
		renderCourseType: function (config) {
			config = config || {};
			config = $.extend({
				idName: "id",
				textName: "name"
			}, defaults_dict, config);
			config.queryParamName = "name";
			config.url = serverHost + "/courseType/list.json";
			selectTool.renderSelect(config);
		},

		/* 商品类型列表 */
		renderShopGoodsType: function(config){
			config = config || {};
			config = $.extend({
				idName: "id",
				textName: "name"
			},defaults_dict, config);
			config.queryParamName = "name";
			config.url = serverHost + "/shopGoods/list.json";
			selectTool.renderSelect(config);
		},

		/** 新人通课程列表 **/
		renderCourse: function (config) {
			config = config || {};
			config = $.extend({
				idName: "id",
				textName: "courseTitle"
			}, defaults_dict, config);
			config.queryParamName = "courseTitle";
			config.url = serverHost + "/course/list.json";
			selectTool.renderSelect(config);
		},

		/** 新人通课程阶段列表 **/
		renderCourseStage: function (config) {
			config = config || {};
			config = $.extend({
				idName: "id",
				textName: "name"
			}, defaults_dict, config);
			config.queryParamName = "name";
			config.url = serverHost + "/courseStage/queryCourseStageList.json";
			selectTool.renderSelect(config);
		}
    };
});