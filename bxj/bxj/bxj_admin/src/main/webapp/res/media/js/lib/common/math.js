define(["jquery"], function($){
    /**
     * 浮点数加法运算
     */
    function floatAdd(arg1, arg2) {
        arg1 = "" + arg1;
        arg2 = "" + arg2;
        var r1 = 0, r2 = 0;
        if( arg1.split(".").length>1){
            r1 = arg1.split(".")[1].length;
        }
        if(arg2.split(".").length>1){
            r2 = arg2.split(".")[1].length;
        }
        var m = Math.pow(10, Math.max(r1, r2));
        //return Number(((arg1 * m + arg2 * m) / m).toFixed(Math.max(r1, r2)));
        return (floatMul(arg1,m) + floatMul(arg2,m)) / m;
    }
    /**
     * 浮点数减法运算
     */
    function floatSub(arg1, arg2) {
        arg1 = "" + arg1;
        arg2 = "" + arg2;
        var r1 = 0, r2 = 0;
        if(arg1.split(".").length>1){
            r1 = arg1.split(".")[1].length;
        }
        if(arg2.split(".").length>1){
            r2 = arg2.split(".")[1].length;
        }
        var m = Math.pow(10, Math.max(r1, r2));
        //return Number(((arg1 * m - arg2 * m) / m).toFixed(Math.max(r1, r2)));
        return (floatMul(arg1,m) - floatMul(arg2,m)) / m;
    }
    /**
     * 浮点数乘法运算
     */
    function floatMul(arg1, arg2) {
        arg1 = "" + arg1;
        arg2 = "" + arg2;
        var m = 0;
        if(arg1.split(".").length>1){
            m = arg1.split(".")[1].length;
        }
        if(arg2.split(".").length>1){
            m += arg2.split(".")[1].length;
        }
        return Number(arg1.replace(".", "")) * Number(arg2.replace(".", "")) / Math.pow(10, m);
    }
    /**
     * 浮点数除法运算
     */
    function floatDiv(arg1, arg2) {
        arg1 = "" + arg1;
        arg2 = "" + arg2;
        var r1 = 0, r2 = 0;
        if(arg1.split(".").length>1){
            r1 = arg1.split(".")[1].length;
        }
        if(arg2.split(".").length>1){
            r2 = arg2.split(".")[1].length;
        }
        return floatMul(Number(arg1.replace(".", "")) / Number(arg2.replace(".", "")), Math.pow(10, r2 - r1));
    }

    /**
     * 四舍五入 默认2位小数
     * @param {} decimal
     * @param {} num 默认2
     * @return {}
     */
    function roundDecimal(decimal, num) {
        num = num||2;
        decimal = decimal||0;
        return floatDiv(Math.round(decimal*Math.pow(10, num)), Math.pow(10, num));
    }

    /**
     * 浮点数除法运算
     */
    function floatDiv2(arg1, arg2, n) {
        arg1 = "" + arg1;
        arg2 = "" + arg2;
        var r1 = 0, r2 = 0;
        if(arg1.split(".").length>1){
            r1 = arg1.split(".")[1].length;
        }
        if(arg2.split(".").length>1){
            r2 = arg2.split(".")[1].length;
        }
        var res = floatMul(Number(arg1.replace(".", "")) / Number(arg2.replace(".", "")), Math.pow(10, r2 - r1));
        if(n && isNumber(n)) {
            return Math.round(res*Math.pow(10, n))/Math.pow(10, n);
        }else{
            return res;
        }
    }

    function convertToInt(str) {
        return parseInt(str);
    }

    /**
     * 检查输入字符串是否符合正整数格式
     */
    function isNumber(s) {
        var s1 = s + "";
        var regu = "^[0-9]+$";
        var re = new RegExp(regu);
        if (s1.search(re) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查输入对象的值是否符合整数格式
     */
    function isInteger(str) {
        var regu = /^[-]{0,1}[0-9]{1,}$/;
        return regu.test(str);
    }

    /**
     * 检查输入字符串是否是带小数的数字格式,可以是负数
     */
    function isDecimal(str) {
        if (isInteger(str))
            return true;
//	var re = /^[-]{0,1}(\d+)[\.]{0,1}(\d+)$/;
        var reg = /^((-?((0\.[0-9]+)|([1-9][0-9]*\.[0-9]+)|([1-9]+[0-9]*)))|0)$/;
        if (reg.test(str)) {
            //	if (RegExp.$1 == 0 && RegExp.$2 == 0)
            //		return false;
            return true;
        } else {
            return false;
        }
    }

    return {
        floatAdd: floatAdd,
        floatSub: floatSub,
        floatMul: floatMul,
        floatDiv: floatDiv,
        roundDecimal: roundDecimal,
        floatDiv2: floatDiv2,
        convertToInt: convertToInt,
        isNumber: isNumber,
        isInteger: isInteger,
        isDecimal: isDecimal
    };
});