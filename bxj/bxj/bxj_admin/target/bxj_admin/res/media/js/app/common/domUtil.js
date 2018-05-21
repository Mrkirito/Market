define(['jquery'], function ($) {

    /**
     * 根据name，返回dom对象数组
     */
    function $n(name) {
        return document.getElementsByName(name);
    }

    /** ============================== 下拉框  =======================================**/
    /**
     * 删除下拉框所有内容
     * sltId select下拉框的id或对象
     * showEmptyOptionFlag true 显示'--请选择--"选项 <可选 默认为false>
     */
    function removeAllOptions(selector, showEmptyOptionFlag) {
        var slt = ((typeof selector == 'string') ? $(selector) : selector);
        var length = slt.get(0).options.length;
        for (var i = length - 1; i >= 0; i--) {
            slt.get(0).options.remove(i);
        }
        if (showEmptyOptionFlag) {
            var op = new Option("--请选择--", "");
            slt.get(0).options.add(op);
        }
    }

    /**
     * 给下拉框添加一个选项
     * @param selector select下拉框的id或对象
     * @param key 添加选项的键
     * @param value 添加选项的值
     */
    function addOption(selector, key, value, selectedFlag) {
        var slt = ((typeof selector == 'string') ? $(selector) : selector);
        var op = new Option(value, key);
        if (selectedFlag)
            op.selected = true;
        slt.get(0).options.add(op);
    }

    /**
     * 添加下拉框内容
     * @param selector select下拉框的id或对象
     * @param data select下拉框的数据，为数组格式
     * @param keyName 默认取 value
     * @param valueName 默认取 text
     * @param clearAll 是否清除原有数据，true清除
     * @param showEmptyOptionFlag 当clearAll为true时有效，显示提示选项"--请选择--"
     * @param selectedValue 选中的值（单选）
     */
    function addOptions(selector, data, keyName, valueName, clearAll, showEmptyOptionFlag, selectedValue) {
        if (clearAll) {
            removeAllOptions(selector, showEmptyOptionFlag);
        }
        if (!data || data.length < 1)    return;
        keyName = keyName || "value";
        valueName = valueName || "text";
        for (var i = 0; i < data.length; i++) {
            addOption(selector, data[i][keyName], data[i][valueName]);
        }
        if (selectedValue) {
            selectOption(selector, selectedValue);
        }
    }

    /**
     * 添加下拉框内容
     * @param sltName select下拉框的name
     * @param index name为sltName对应控件数组的下标
     * @param data select下拉框的数据，为数组格式
     * @param keyName 默认取 value
     * @param valueName 默认取 text
     * @param clearAll 是否清除原有数据，true清除
     * @param showEmptyOptionFlag 当clearAll为true时有效，显示提示选项"--请选择--"
     * @param selectedValue 选中的值（单选）
     */
    function addOptions2(sltName, index, data, keyName, valueName, clearAll, showEmptyOptionFlag, selectedValue) {
        var slt = $n(sltName)[index];
        if (!slt)
            return;
        addOptions(slt, data, keyName, valueName, clearAll, showEmptyOptionFlag, selectedValue);
    }

    /**
     * 选择下拉的值
     * @param selector select下拉框的id或对象
     * @param val 选中的值（单选）
     */
    function selectOption(selector, val) {
        var slt = ((typeof selector == 'string') ? $(selector) : selector);
        var length = slt.get(0).options.length;
        for (var i = 0; i < length; i++) {
            if (slt.get(0).options[i].value == val) {
                slt.get(0).options[i].selected = true;
            }
        }
    }

    function selectAllSlt(selector) {
        var slt = ((typeof selector == 'string') ? $(selector) : selector);
        for (var i = 0; i < slt.get(0).options.length; i++) {
            slt.get(0).options[i].selected = true;
        }
    }

    /** ============================== 下拉框  =======================================**/

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    return {
        addOptionsByName: addOptions2,
        addOptions: addOptions,
        removeAllOptions: removeAllOptions,
        selectOption: selectOption,
        addOption: addOption,
        selectAllSlt: selectAllSlt,
        getUrlParam: getUrlParam
    }
})