package com.znb.cms.model.mapper;

public class PeopleGroup {
    private Integer id;

    private String desc;

    private Integer defaultSex;

    private String sexList;

    private Integer defaultAge;

    private Integer isOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getDefaultSex() {
        return defaultSex;
    }

    public void setDefaultSex(Integer defaultSex) {
        this.defaultSex = defaultSex;
    }

    public String getSexList() {
        return sexList;
    }

    public void setSexList(String sexList) {
        this.sexList = sexList == null ? null : sexList.trim();
    }

    public Integer getDefaultAge() {
        return defaultAge;
    }

    public void setDefaultAge(Integer defaultAge) {
        this.defaultAge = defaultAge;
    }

    public Integer getIsOn() {
        return isOn;
    }

    public void setIsOn(Integer isOn) {
        this.isOn = isOn;
    }
}