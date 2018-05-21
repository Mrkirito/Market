define(['jquery'], function ($) {

    return {
        hasRight: function (selector, rightCode) {
            if(selector) {
                selector = typeof selector == 'string'?$(selector):selector;
                selector.hide();

                if(!rightCode) {
                    rightCode = selector.data("rightcode")||"";
                }
                if(rightCode && this._hasRight(rightCode)) {
                    selector.show();
                }
            }
        },

        _hasRight: function (rightCode) {
            var userRights = JSON.parse($("._u_f_").val())||[],
                hasRight = false;
            if(rightCode) {
                $(userRights).each(function () {
                    if(this.rightCode == rightCode && !this.disabled) {
                        hasRight = true;
                        return false;
                    }
                });
            } else {
                hasRight = true;
            }
            return hasRight;
        }
    };
})