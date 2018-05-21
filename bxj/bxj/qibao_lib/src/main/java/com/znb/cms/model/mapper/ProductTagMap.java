package com.znb.cms.model.mapper;
/**
 * 产品标签映射关系
 * @author chenyao
 * @date 2017年2月9日 上午10:34:25
 */
public class ProductTagMap {
    private Integer id;
    private Integer productId;
    private Integer tagId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
    
    
    
}