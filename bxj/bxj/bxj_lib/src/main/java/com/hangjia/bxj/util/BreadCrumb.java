package com.hangjia.bxj.util;

/**
 * @author yaoy
 * @since 2016-06-27
 */
public class BreadCrumb {
    private String name;
    private String uri;
    private boolean current = false;

    public BreadCrumb(String name) {
        this.name = name;
    }

    public BreadCrumb(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public BreadCrumb(String name, String uri, boolean current) {
        this.name = name;
        this.uri = uri;
        this.current = current;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
