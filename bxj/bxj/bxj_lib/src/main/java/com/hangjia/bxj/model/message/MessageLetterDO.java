package com.hangjia.bxj.model.message;

import com.hangjia.bxj.common.BaseCommonDO;

/**
 * @author yaoy
 * @since 2016-07-18
 */
public class MessageLetterDO extends BaseCommonDO {
	private Long id; // 发送对象
	private String moduleName; // 模板名称
	private String description; // 
	private String moduleTitle;//标题
	private String moduleContent; // 模板内容
	private String toObj;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModuleTitle() {
		return moduleTitle;
	}
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}
	public String getModuleContent() {
		return moduleContent;
	}
	public void setModuleContent(String moduleContent) {
		this.moduleContent = moduleContent;
	}
	public String getToObj() {
		return toObj;
	}
	public void setToObj(String toObj) {
		this.toObj = toObj;
	} 
	
}
