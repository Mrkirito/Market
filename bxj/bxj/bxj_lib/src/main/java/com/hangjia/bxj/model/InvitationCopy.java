package com.hangjia.bxj.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *  邀请函。副表
 * @ClassName: InvitationCopy
 * @Description: 
 * @author: he-Yi
 * @date: 2016年4月22日 上午10:31:12
 */
public class InvitationCopy implements Serializable {

	/**
	 * 主键
	 */
	private Long fid;
	
	/**外键**/
	private Integer copyid;
	/**
	 * 关联用户ID。
	 */
	private Integer userId;
	
	/**
	 * 产说会名称
	 */
	private String name;
	
	/**
	 * 产说会时间。
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date dateAt;
	
	/**
	 * 地址。
	 */
	private String address;
	
	/**
	 * 联系人。
	 */
	private String contactName;
	
	/**
	 * 联系人手机，或电话
	 */
	private String contactMobile;
	
	/**
	 * 邀请函正文。
	 */
	private String text;
	
	/**
	 * 模板类型
	 */
	private Integer modelType;
	
	/**
	 * 宾客名称
	 */
	private String guestName;
	/**
	 * 创建时间。
	 */
	private Date createAt;
	
	/**
     * 分享图片地址
     */
	private String shareImgUrl;
	
	/**
	 * 展示
	 */
	private String dateAtCN;
	
	private Double lng;
	
	private Double lat;

	private String mapImgUrl;
	
	private String os;
	private String versionCode;
	private String versionName;
	private String osVersion;
	
	/**楼层 房间**/
	private String floorRoom;
	
	/** 浏览次数**/
	private Integer browseNum;
	/**浏览时间**/
	private Date browseTime;
	/** 分享url **/
	private String shareUrl;
	
	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public Date getBrowseTime() {
		return browseTime;
	}

	public void setBrowseTime(Date browseTime) {
		this.browseTime = browseTime;
	}

	public String getFloorRoom() {
		return floorRoom;
	}

	public void setFloorRoom(String floorRoom) {
		this.floorRoom = floorRoom;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public Integer getCopyid() {
		return copyid;
	}

	public void setCopyid(Integer copyid) {
		this.copyid = copyid;
	}

	public Integer getModelType() {
		return modelType;
	}

	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateAt() {
		return dateAt;
	}

	public void setDateAt(Date dateAt) {
		this.dateAt = dateAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getShareImgUrl() {
		return shareImgUrl;
	}

	public void setShareImgUrl(String shareImgUrl) {
		this.shareImgUrl = shareImgUrl;
	}

	public String getCreateAtCN() {
		if (createAt == null) {
			return null;
		}
		return DateFormatProvider.DATE_FORMAT_CN.format(createAt);
	}
	
	public String getDateAtCN() {
		if (dateAt == null) {
			return null;
		}
		return DateFormatProvider.DATE_FULL_FORMAT_DAYCN.format(dateAt);
	}

	public void setDateAtCN(String dateAtCN) {
		this.dateAtCN = dateAtCN;
	}

	public String getMapImgUrl() {
		return mapImgUrl;
	}

	public void setMapImgUrl(String mapImgUrl) {
		this.mapImgUrl = mapImgUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invitation [id=");
		builder.append(fid);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dateAt=");
		builder.append(dateAt);
		builder.append(", address=");
		builder.append(address);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", contactName=");
		builder.append(contactName);
		builder.append(", contactMobile=");
		builder.append(contactMobile);
		builder.append(", text=");
		builder.append(text);
		builder.append(", createAt=");
		builder.append(createAt);
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = -8260795371888329757L;

}
