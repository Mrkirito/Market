define(['jquery'], function ($) {

    var arrayUtil = {
        removeItem: function (dataList, item) {
            var itemList = [];
            dataList = dataList||[];
            $(dataList).each(function () {
                if(this != item) {
                    itemList.push(this);
                }
            });
            return itemList;
        },
        /**
         *
         * @param dataList
         * @param index 必须是已0为开始的
         * @returns {Array}
         */
        removeItemByIdx: function (dataList, index) {
            var itemList = [];
            dataList = dataList||[];
            $(dataList).each(function (idx) {
                if(idx != index) {
                    itemList.push(this);
                }
            });
            return itemList;
        },

        /**
         *
         * @param dataList
         * @param item
         * @param index必须是已0为开始的
         * @returns {Array}
         */
        insertItem: function (dataList, item, index) {
            var itemList = [],
                add = false;
            dataList = dataList||[];

            $(dataList).each(function (idx) {
                if(idx == index) {
                    add = true;
                    itemList.push(item);
                }
                console.info(this);
                itemList.push(this);
            });
            if(!add) {
                itemList.push(item);
            }
            return itemList;
        }
    }

    return arrayUtil;
})