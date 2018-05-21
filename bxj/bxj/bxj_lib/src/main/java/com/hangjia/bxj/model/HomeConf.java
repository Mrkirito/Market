package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author
 * 
 * @version 3.0.1 2016/06/20，新增版本号过滤、上下线时间过滤。
 * @author K9999
 *
 */
public class HomeConf implements Serializable {

    private Integer fid;

    private String title;

    private String imageUrl;

    private String pageUrl;

    private Boolean isDisplay;

    private Integer type;

    private Integer sort;

    private Long createUser;

    private Date createAt;

    private Long modifyUser;
    
    private Date modifyAt;

    private Long publistTime;

    public HomeConf() {}

    public HomeConf(Long publistTime) {
        this.publistTime = publistTime;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Long getPublistTime() {
        return publistTime;
    }

    public void setPublistTime(Long publistTime) {
        this.publistTime = publistTime;
    }


    /****************************************************************
     * 以下新增自 3.0.1，新增版本号过滤、上下线时间过滤。
     * 
     * 新版本在查询时应当依次匹配：
     * 1、isDisplay：删除为 false（不显示），默认为 true，显示。
     * 2、minVersion、maxVersion：根据客户端（client）传入的版本号，过滤其版本号大于等于 minVersion，小于等于 maxVersion。
     * 3、onlineTime、offlineTime：根据server当前时间，判定上下线时间，晚于上线时间，早于下线时间。
     * 
     * 具体请参考下方各字段注释。
     * 
     ***************************************************************/
    
    /**
     * 
     * 上线时间。
     * <p>
     * 过滤纬度。
     * 未到上线时间的记录不得显示，时间以服务端（server）时间为准。
     * 如为 null 值，表示不限（立即上线）。
     * </p>
     * 
     * <b>此字段可与 {@link #offlineTime} 字段配合使用。</b>
     * 
     * @since 3.0.1
     * @author K9999
     * @see #offlineTime
     */
    private Date onlineTime;
    
    /**
     * 
     * 下线时间。
     * <p>
     * 过滤纬度。
     * 超过下线时间的记录不得显示，时间以服务端（server）时间为准。
     * 如为 null 值，表示不限（永久上线，不下线）。
     * </p>
     * 
     * <b>此字段可与 {@link #onlineTime} 字段配合使用。</b>
     * 
     * @since 3.0.1
     * @author K9999
     * @see #onlineTime
     */
    private Date offlineTime;
    
    /**
     * 
     * 最小版本号。
     * <p>
     * 过滤纬度。根据客户端传入的版本号（客户端当前版本）。过滤不同的记录给客户端显示。
     * 如为 null 值，表示不限最小版本。
     * </p>
     * 
     * <b>此字段可与 {@link #maxVersion} 字段配合使用。</b>
     * 
     * @since 3.0.1
     * @author K9999
     * @see #maxVersion
     */
    private Double minVersion;
    
    /**
     * 
     * 最大版本号。
     * 
     * <p>
     * 过滤纬度。根据客户端传入的版本号（客户端当前版本）。过滤不同的记录给客户端显示。
     * 如为 null 值，表示不限最大版本。
     * </p>
     * 
     * <b>此字段可与 {@link #minVersion} 字段配合使用。</b>
     * 
     * @since 3.0.1
     * @author K9999
     * @see #minVersion
     */
    private Double maxVersion;

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public Double getMinVersion() {
		return minVersion;
	}

	public void setMinVersion(Double minVersion) {
		this.minVersion = minVersion;
	}

	public Double getMaxVersion() {
		return maxVersion;
	}

	public void setMaxVersion(Double maxVersion) {
		this.maxVersion = maxVersion;
	}
}