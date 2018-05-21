package com.znb.cms.model.mapper;
/**
 * 产品标签
 * @author chenyao
 * @date 2017年2月9日 上午10:34:25
 */
public class ProductTagInfo {
    private Integer id;
    private String name;
    private Integer isOn = 1;
    private Integer dictionaryId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsOn() {
		return isOn;
	}
	public void setIsOn(Integer isOn) {
		this.isOn = isOn;
	}
	public Integer getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
    
}