package com.hangjia.bxj.model.banner.db;

import java.io.Serializable;

/**
 * 
 * 对各张 banner 表增删改查列名规范实例。
 * 
 * 由于各种 banner 存放在不同的表，在 3.1 之前各表的字段不规范，因此使用此类做适配。
 * 同时，此类也作为日后 banner 表的字段规范。
 * 若之前的 banner 表与此类中的不一致，可覆盖此类方法，以获取正确的列名。
 * @author K9999
 *
 */
public abstract class BannerTable implements Serializable {
	
	public abstract String getTableName();

	public String getIdColumnName() {
		return "fid";
	}
	
	public String getDisplayColumnName() {
		return "is_display";
	}
	
	public Object getEnabledDisplayValue() {
		return 1;
	}
	
	public Object getDisabledDisplayValue() {
		return 0;
	}
	
	public String getTitleColumnName() {
		return "title";
	}
	
	public String getPageUrlColumnName() {
		return "page_url";
	}
	
	public String getImageUrlColumnName() {
		return "image_url";
	}
	
	public String getSortColumnName() {
		return "sort";
	}
	
	public String getOnlineTimeColumnName() {
		return "online_time";
	}
	
	public String getOfflineTimeColumnName() {
		return "offline_time";
	}
	
	public String getModifyAtColumnName() {
		return "modify";
	}
	
	public String getCreateAtColumnName() {
		return "create_at";
	}
	
	public String getMinVersionColumnName() {
		return "min_version";
	}
	
	public String getMaxVersionColumnName() {
		return "max_version";
	}
	
	/**
	 * 分类鉴别字段，部分 banner 存在一张表中，使用字段区分。
	 * 如果是这种情况，此方法需返回该字段名称。
	 * 否则返回 null。
	 * @return
	 */
	public String getTypeDiscriminator() {
		return null;
	}
	
	/**
	 * 分类鉴别字段，部分 banner 存在一张表中，使用字段区分。
	 * 如果是这种情况，此方法需返回该字段的值。
	 * 否则返回 null。
	 * @return
	 */
	public Object getTypeDiscriminateValue() {
		return null;
	}
	
	private static final long serialVersionUID = -8339779962246011950L;

}
