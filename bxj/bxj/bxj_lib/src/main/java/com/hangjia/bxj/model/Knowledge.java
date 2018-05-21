package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 知识，学习版块， 数据模型。
 * 
 * @author K9999
 *
 */
public class Knowledge implements Serializable {

	private Integer id;

	private String imageURL;

	private String title;

	private String text;

	private String tags;

	private String location;

	private Boolean status;

	private Date createAt;

	private Integer weight;

	private String fileUrl;

	private String timeContext; // 时间文案
	/**类型id**/
	private Integer studyTypeID; 
	
	private String typeName; //类型名称
	/**是否已读 **/
	private Integer isRead; 
	/**知识图片集合**/
	List<KonwImg> imgResults;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<KonwImg> getImgResults() {
		return imgResults;
	}

	public void setImgResults(List<KonwImg> imgResults) {
		this.imgResults = imgResults;
	}

	public Integer getStudyTypeID() {
		return studyTypeID;
	}

	public void setStudyTypeID(Integer studyTypeID) {
		this.studyTypeID = studyTypeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getTimeContext() {
		return timeContext;
	}

	public void setTimeContext(String timeContext) {
		this.timeContext = timeContext;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	private static final long serialVersionUID = -7931529061630305088L;

}
