package com.hangjia.bxj.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * 用户计划书语音请求Vo
 * Created by bei.zhang on 2016/4/26.
 */
public class PlanBookVoiceReqVo implements Serializable {

    private Long voiceId;

    private Long bookId;

    private Long userId;

    private String voiceName;

    private String voiceTime;

    private MultipartFile voiceFile;

    private String voiceIds;

    public Long getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(Long voiceId) {
        this.voiceId = voiceId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoiceTime() {
        return voiceTime;
    }

    public void setVoiceTime(String voiceTime) {
        this.voiceTime = voiceTime;
    }

    public MultipartFile getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(MultipartFile voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getVoiceIds() {
        return voiceIds;
    }

    public void setVoiceIds(String voiceIds) {
        this.voiceIds = voiceIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

