package com.hangjia.bxj.model.banner;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 
 * @author K9999
 * @since 3.0.1
 * 
 */
public class BannerManagerEntity {
	
	private Integer id;
	
	/**
	 * 
	 * 点击banner后，跳转页面的标题文字。
	 * 
	 */
	private String forwardTitle;
	
	/**
	 * 跳转类型，如：邀请函、计划书等，请参见3.1链接跳转规范。
	 */
	private String forwardType;
	
	/**
	 * 点击banner后，跳转页面的地址。
	 */
	private String pageUrl;
	
	/**
	 * 图片地址。
	 */
	private String imageUrl;
	
	/**
	 * 数字越小越优先，最小值为0。
	 */
	private Integer sort;
	
	/**
	 * 状态字段，是否显示：true 显示（正常），false 不显示（删除）。
	 */
	private Boolean display;

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
     * @author K9999
     * @see #offlineTime
     */
	@JSONField(format="yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
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
     * @author K9999
     * @see #onlineTime
     */
	@JSONField(format="yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
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
     * @author K9999
     * @see #minVersion
     */
    private Double maxVersion;

	public String getForwardTitle() {
		return forwardTitle;
	}

	public void setForwardTitle(String forwardTitle) {
		this.forwardTitle = forwardTitle;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getForwardType() {
		return forwardType;
	}

	public void setForwardType(String forwardType) {
		this.forwardType = forwardType;
	}

	public String getStatusMessage() {
		Date now = new Date();
		
		if (onlineTime == null) {
			
			if (offlineTime == null) {
				return "always";
			}
			
		} else {
			
			if (onlineTime.after(now)) {
				return "await";
			}
			
			if (offlineTime == null) {
				return "onlined";
			}
			
		}
		
		if (offlineTime.after(now)) {
			return "onlining";
		} else {
			return "offlined";
		}
		
	}
	
	public String getVersionMessage() {
		
		if (minVersion == null) {
			if (maxVersion == null) {
				return "all";
			} else {
				return "<" + maxVersion;
			}
		} else {
			
			if (maxVersion == null) {
				return ">" + minVersion;
			} else {
				return minVersion + "-" + maxVersion;
			}
			
		}
	}
	
}
