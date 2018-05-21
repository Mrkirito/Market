package com.hangjia.bxj.model.summit;

import java.util.Date;


import com.hangjia.bxj.model.BaseModel;
import com.hangjia.bxj.model.DateFormatProvider;

/**
 * 【冠军论坛峰会】数据实体。
 *
 */
public class ChampionSummitEntity extends BaseModel {

	private Integer id;
	
	/**
	 * 峰会名称，如：广州会议、长沙会议、兰州会议等。
	 */
	private String name;
	
	/**
	 * 开始时间。
	 */
	private Date beginAt;
	/**
	 * 展示格式 年月日
	 */
	private String beginAtCN;
	
	/**
	 * 结束时间。
	 */
	private Date endAt;
	/**
	 *  * 展示格式 年月日
	 */
	private String endAtCN;
	
	/**
	 * 参会人数。
	 */
	private Integer totalJoins;
	
	/**
	 * 封面图片地址。
	 */
	private String imageUrl;
	
	/**
	 * 图片全路径地址
	 */
	private String imgFileUrl;
	
	private String display;
	
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

	public Date getBeginAt() {
	    return beginAt;
	}

	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	
	public String getBeginAtCN() {
		if(beginAt==null)return null;
		return DateFormatProvider.DATE_FORMAT_CN.format(beginAt);
	}

	public void setBeginAtCN(String beginAtCN) {
		this.beginAtCN = beginAtCN;
	}

	public String getEndAtCN() {
		if(endAt==null)return null;
		String dateStr= DateFormatProvider.DATE_FORMAT_CN.format(endAt);
		return dateStr.substring(dateStr.indexOf("年")+1,dateStr.indexOf("日")+1);
	}

	public void setEndAtCN(String endAtCN) {
		this.endAtCN = endAtCN;
	}

	public Integer getTotalJoins() {
		return totalJoins;
	}

	public void setTotalJoins(Integer totalJoins) {
		this.totalJoins = totalJoins;
	}

	public String getImgFileUrl() {
		return imgFileUrl;
	}

	public void setImgFileUrl(String imgFileUrl) {
		this.imgFileUrl = imgFileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}



	private static final long serialVersionUID = 3615093448073975938L;
	
}
