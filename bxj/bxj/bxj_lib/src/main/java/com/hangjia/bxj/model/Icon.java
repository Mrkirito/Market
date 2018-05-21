package com.hangjia.bxj.model;

public class Icon {
	private Integer id;
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getIcon_name() {
		return icon_name;
	}

	public Integer getIsUrl() {
		return isUrl;
	}

	public void setIsUrl(Integer isUrl) {
		this.isUrl = isUrl;
	}

	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}

	private String icon_url;
	
	private String image_url;
	
	private String icon_name;
	
	private Integer isUrl;
}