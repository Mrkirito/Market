package com.hangjia.bxj.model;

import java.util.Date;

public class PlanVoice {
	private Integer id;
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	private Integer userId;
	
	private String createAt2;
	
	private String VoiceTime;
	
	private String VoiceName;
	
	public String getVoiceTime() {
		return VoiceTime;
	}

	public void setVoiceTime(String voiceTime) {
		VoiceTime = voiceTime;
	}

	public String getVoiceName() {
		return VoiceName;
	}

	public void setVoiceName(String voiceName) {
		VoiceName = voiceName;
	}

	public String getCreateAt2() {
		return createAt2;
	}

	public void setCreateAt2(String createAt2) {
		this.createAt2 = createAt2;
	}

	private Integer statusVoice ;


	public Integer getStatusVoice() {
		return statusVoice;
	}

	public void setStatusVoice(Integer statusVoice) {
		this.statusVoice = statusVoice;
	}

	private Date modifyAt;

    private String videoUrl;

    private Integer dispaly;

    private Date createAt;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getModifyAt() {
		return modifyAt;
	}

	public void setModifyAt(Date modifyAt) {
		this.modifyAt =modifyAt;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getDispaly() {
		return dispaly;
	}

	public void setDispaly(Integer dispaly) {
		this.dispaly = dispaly;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt2 = DateFormatProvider.DATE_FORMAT.format(createAt);
		this.createAt = createAt;
	}



	
}