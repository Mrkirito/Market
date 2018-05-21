package com.hangjia.bxj.vo;

import java.io.Serializable;

/**
 * 我的配置respDto
 * Created by Administrator on 2016/5/23.
 */
public class MyConfRespDto implements Serializable {

    private Long id;

    private String name;

    private String iconUrl;

    private String pageUrl;

    private String description;

    private Boolean isDisplayNew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisplayNew() {
        return isDisplayNew;
    }

    public void setDisplayNew(Boolean displayNew) {
        isDisplayNew = displayNew;
    }
}
