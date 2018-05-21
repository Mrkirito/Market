package com.hangjia.bxj.vo;

import java.util.Date;

public class StatisticsDataVo {
	private Integer type;
	private Integer number;
	private Integer people;
	
	
	/**新人通*/
    private Date dataTime;
    private Integer studyCount=0;
    private Integer testCount=0;
    private Integer clearanceCount=0;
	private Integer studyCountUv=0;
	private Integer testCountUv=0;
	private Integer clearanceCountUv=0;
    private Date createTime=new Date();
    
    
    /**开门红*/
    private Integer browseCountUv=0;
    private Integer shareCountUv=0;
	private Integer browseCount=0;
	private Integer shareCount=0;

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Integer getStudyCountUv() {
		return studyCountUv;
	}

	public void setStudyCountUv(Integer studyCountUv) {
		this.studyCountUv = studyCountUv;
	}

	public Integer getTestCountUv() {
		return testCountUv;
	}

	public void setTestCountUv(Integer testCountUv) {
		this.testCountUv = testCountUv;
	}

	public Integer getClearanceCountUv() {
		return clearanceCountUv;
	}

	public void setClearanceCountUv(Integer clearanceCountUv) {
		this.clearanceCountUv = clearanceCountUv;
	}

	public Integer getBrowseCountUv() {
		return browseCountUv;
	}

	public void setBrowseCountUv(Integer browseCountUv) {
		this.browseCountUv = browseCountUv;
	}

	public Integer getShareCountUv() {
		return shareCountUv;
	}

	public void setShareCountUv(Integer shareCountUv) {
		this.shareCountUv = shareCountUv;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getDataTime() {
		return dataTime;
	}
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}
	public Integer getStudyCount() {
		return studyCount;
	}
	public void setStudyCount(Integer studyCount) {
		this.studyCount = studyCount;
	}
	public Integer getTestCount() {
		return testCount;
	}
	public void setTestCount(Integer testCount) {
		this.testCount = testCount;
	}
	public Integer getClearanceCount() {
		return clearanceCount;
	}
	public void setClearanceCount(Integer clearanceCount) {
		this.clearanceCount = clearanceCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	
}
