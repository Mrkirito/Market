package com.znb.cms.vo;

public class ProductMongoDataVo {
	/**产品对比**/
	private String id;
	private Integer productId;
	private String productName;
	private String templateId; 
	/**修改层级**/
	private int level=1;	
	private int edit=1;	
	private String key;
	private String value;
	
	private String childKey;
	private String childValue;
	
	
	
	private Integer editAnalysisFlag;//1=案例,2=特性
	private Integer analysisDataIndex;//案例&特性 记录列表下标
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getEdit() {
		return edit;
	}
	public void setEdit(int edit) {
		this.edit = edit;
	}
	public String getChildKey() {
		return childKey;
	}
	public void setChildKey(String childKey) {
		this.childKey = childKey;
	}
	public String getChildValue() {
		return childValue;
	}
	public void setChildValue(String childValue) {
		this.childValue = childValue;
	}
	public Integer getEditAnalysisFlag() {
		return editAnalysisFlag;
	}
	public void setEditAnalysisFlag(Integer editAnalysisFlag) {
		this.editAnalysisFlag = editAnalysisFlag;
	}
	public Integer getAnalysisDataIndex() {
		return analysisDataIndex;
	}
	public void setAnalysisDataIndex(Integer analysisDataIndex) {
		this.analysisDataIndex = analysisDataIndex;
	}	
	
}
