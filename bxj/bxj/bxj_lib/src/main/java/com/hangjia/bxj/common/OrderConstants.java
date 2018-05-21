package com.hangjia.bxj.common;

/**
 * @ClassName: OrderConstants
 * @Description: 冠军说排序常量类
 * @author: bei.zhang
 * @date: 2016年4月12日 下午2:07:41
 */
public class OrderConstants {
	/** ----------- CHAMPION_VIDEO TABLE ----------- */
	/** CHAMPION_VIDEO 是否推荐:IS_RECOMMEND */
	public static final String CHAMPION_VIDEO_IS_RECOMMEND = "is_recommend";
	/** CHAMPION_VIDEO 审核时间:AUDIT_AT */
	public static final String CHAMPION_VIDEO_AUDIT_AT = "audit_at";
	/**上架时间**/
	public static final String ONLINE_TIME = "online_time";
	/** CHAMPION_VIDEO 播放次数:PLAY_COUNT*/
	public static final String CHAMPION_VIDEO_PLAY_COUNT = "play_count";
	
	/** ----------- CHAMPION_CHANNEL TABLE ----------- */
	/** CHAMPION_CHANNEL 排序:SORT */
	public static final String CHAMPION_CHANNEL_SORT = "sort";
	
	/** ----------- PLAN_PRODUCT_POSITION TABLE ----------- */
	/** PLAN_PRODUCT_POSITION 排序:SORT */
	public static final String PLAN_PRODUCT_POSITION_SORT = "sort";
	
	/** ----------- PLAN_USER_IMG TABLE ----------- */
	/** PLAN_USER_IMG 排序:FID */
	public static final String PLAN_USER_IMG_FID = "fid";

	/** ----------- PLAN_BOOK_VOICE TABLE ----------- */
	/** PLAN_BOOK_VOICE 排序:CREATE_AT */
	public static final String PLAN_BOOK_VOICE_CREATE_AT = "create_at";

	/** ----------- BXJ_MY_CONF TABLE ----------- */
	/** BXJ_MY_CONF 排序:SORT */
	public static final String BXJ_MY_CONF_SORT = "sort";

	/** 按sort排序 **/
	public static final String ORDER_BY_SORT = "sort";


	/** ----------- BXJ_VERSION_INFO TABLE ----------- */
	/** BXJ_VERSION_INFO 排序:version_number */
	public static final String BXJ_VERSION_INFO_VERSION_NUMBER = "version_number";

}
