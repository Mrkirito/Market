package com.hangjia.bxj.util;
/** 
* @author  作者 : yaoy
* @date 2016年5月4日 上午9:43:33 
* @version 1.0 
*/
public class PageModule {

    private String name;
    private String method;

    private boolean show = true;// 是否显示导航

    private String title;

    public void init(String name, String method) {
        this.name = name;
        this.method = method;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
