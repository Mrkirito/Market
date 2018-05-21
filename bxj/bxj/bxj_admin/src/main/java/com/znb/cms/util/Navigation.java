package com.znb.cms.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaoy
 * @since 2016-06-27
 */
public class Navigation {

    private boolean show = true;
    private List<BreadCrumb> crumbList;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void add(String name) {
        if(null == crumbList) {
            crumbList = new ArrayList<BreadCrumb>();
        }
        crumbList.add(new BreadCrumb(name));
    }

    public void add(String name, String uri) {
        if(null == crumbList) {
            crumbList = new ArrayList<BreadCrumb>();
        }
        crumbList.add(new BreadCrumb(name, uri));
    }

    public void add(String name, String uri, boolean current) {
        if(null == crumbList) {
            crumbList = new ArrayList<BreadCrumb>();
        }
        crumbList.add(new BreadCrumb(name, uri, current));
    }

    public void add(String name, boolean current) {
        if(null == crumbList) {
            crumbList = new ArrayList<BreadCrumb>();
        }
        crumbList.add(new BreadCrumb(name, null, current));
    }

    public List<BreadCrumb> getCrumbList() {
        return crumbList;
    }

    public void setCrumbList(List<BreadCrumb> crumbList) {
        this.crumbList = crumbList;
    }

}
