package com.hangjia.bxj.query.right;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * @author yaoy
 * @since 2016-06-14
 */
public class NavigationQuery extends BaseCommonQuery {
    private String navCode;
    private String navName;


    public String getNavCode() {
        return navCode;
    }


    public void setNavCode(String navCode) {
        this.navCode = navCode;
    }


    public String getNavName() {
        return navName;
    }


    public void setNavName(String navName) {
        this.navName = navName;
    }
}
