package com.hangjia.bxj.query.banner;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.util.StringUtils;

public class BannerManagerQuery extends BaseCommonQuery {

	private String showType;
	
	private Integer id;
	
	private String forwardTitle;
	
	private String forwardType;
	
	private String statusMessage;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date onlineTime;
	
	/**
	 * 匹配规则，相等、大于、小于等。
	 */
	private String onlineTimeRule;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date offlineTime;
	
	private String offlineTimeRule;
	
	private Integer display;
	
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getForwardTitle() {
		return forwardTitle;
	}

	public void setForwardTitle(String forwardTitle) {
		this.forwardTitle = StringUtils.clean(forwardTitle);
	}

	public String getForwardType() {
		return forwardType;
	}

	public void setForwardType(String forwardType) {
		this.forwardType = StringUtils.clean(forwardType);
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = StringUtils.clean(statusMessage);
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getOnlineTimeRule() {
		return onlineTimeRule;
	}

	public void setOnlineTimeRule(String onlineTimeRule) {
		this.onlineTimeRule = StringUtils.clean(onlineTimeRule);
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getOfflineTimeRule() {
		return offlineTimeRule;
	}

	public void setOfflineTimeRule(String offlineTimeRule) {
		this.offlineTimeRule = StringUtils.clean(offlineTimeRule);
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	private Date now;
	
	public Date getNow() {
		if (now == null) {
			now = new Date();
		}
		return now;
	}

	private static final long serialVersionUID = 4268215377264261107L;

}
