package com.hangjia.bxj.util;

/**
 * Created by Administrator on 2016/4/12.
 */
public class Constants {
	/** 默认第一页 **/
	public static final int PAGEINDEX = 1;

	/**分页开始**/
	public static final int BEGIN = 0;

	/** 前台频道分页大小 **/
	public static final int PAGESIZE = 10;

	/** 前台子频道分页大小 **/
	public static final int SUBPAGESIZE = 2;

	/** 最大值 **/
	public static final int MAXPAGESIZE = Integer.MAX_VALUE;

	/** 频道全部分类主键 */
	public static final Long CHAMPION_CHANNEL_ID_ALL = 1L;

	/** 默认视频券主键 */
	public static final Long CHAMPION_VOUCHER_ID_VIDEO = 1L;

	/** 视频审核状态 待审核：0 */
	public static final int VIDEO_AUDIT_STATUS_WAIT = 0;
	/** 视频审核状态 未通过：1 */
	public static final int VIDEO_AUDIT_STATUS_NOT = 1;
	/** 视频审核状态 已通过：2 */
	public static final int VIDEO_AUDIT_STATUS_PASS = 2;

	/** 本周热门 **/
	public static final long JX_MODULE_BZRM = 1;
	/** 早会精选 **/
	public static final long JX_MODULE_ZHJX = 2;

	/** 邀请状态 已发送邀请：1 */
	public static final int INVITE_STATUS_SEND = 1;
	/** 邀请状态 已接受邀请：2 */
	public static final int INVITE_STATUS_SUCC = 2;

	/** 视频权限 没有登录：1 **/
	public static final int VIDEO_AUTHORITY_NOLOGIN = 1;
	/** 视频权限 用户券不够：2 **/
	public static final int VIDEO_AUTHORITY_NOENOUGH = 2;
	/** 视频权限 有足够的券：3 **/
	public static final int VIDEO_AUTHORITY_ENOUGH = 3;
	/** 视频权限 已观看过：4  **/
	public static final int VIDEO_AUTHORITY_WATCHED = 4;
	/** 注册成功 **/
	public static final String REGIST_SUCCESS = "1000";


	/** 用户名片分享URL **/
	public static final String USER_CARD_SHARE_PATH_PREFIX = "/planUserCard/";
	/** 用户名片分享URL **/
	public static final String USER_CARD_SHARE_PATH_SUFFIX = "/shareUserCard.page?hd=1&type=";

	/**
	 *  模板类型 1:简约(默认)
	 */
	public static final int USER_CARD_MODEL_SIMPLE = 1;
	/**
	 *  模板类型 2:生活
	 */
	public static final int USER_CARD_MODEL_LIFE = 2;
	/**
	 *  模板类型 3:商务
	*/
	public static final int USER_CARD_MODEL_BUSINESS = 3;

	/**使用券**/
	public static final String USE_VIDEO_VOUCHER = "useVoucher";

	/**观看视频**/
	public static final String PLAY_VIDEO = "playVideo";

	/**添加或取消收藏**/
	public static final String STORE_OR_CANCEL = "collection";
	/**登录**/
	public static final String DO_LOGIN = "login";

	/** 是否独家 0:否 */
	public static final int VOICE_IS_ONLY_NO = 0;
	/** 是否独家 1:是 */
	public static final int VOICE_IS_ONLY_YES = 1;
	/** 语音类型 1:用户录音； */
	public static final int VOICE_TYPE_USER = 1;
	/** 语音类型 2:总监话术 */
	public static final int VOICE_TYPE_CHIEF = 2;
	/** 是否删除 0:未删除 */
	public static final int VOICE_IS_DELETED_NO = 0;
	/** 是否删除 1:已删除 */
	public static final int VOICE_IS_DELETED_YES = 1;

	/**首页android banner类型**/
	public static final int HOME_CONF_BANNER = 1;
	/**图片规格：高**/
	public static final String PICTURE_MODEL_SMALL = "small";
	/**图片规格：正常（默认）**/
	public static final String PICTURE_MODEL_MIDDLE = "middle";
	/**图片规格：低**/
	public static final String PICTURE_MODEL_BIG = "big";

	/**
	 *  1 邀请好友送抽取红包次数
	 */
	public static final int RED_ENVELOPE_TYPE_INVITE = 1;

	/** 券消费类型 注册添加：1 */
	public static final int VOUCHER_CONSUME_TYPE_ADD = 1;
	/** 券消费类型 看视频使用用券：2 */
	public static final int VOUCHER_CONSUME_TYPE_USE = 2;
	/** 券消费类型 邀请添加：3 */
	public static final int VOUCHER_CONSUME_TYPE_INVITE_ADD = 3;
	/** 券消费类型 邀请添加：4 */
	public static final int VOUCHER_CONSUME_TYPE_LOGIN_ADD = 4;
	/** 券消费类型 邀请添加：5 */
	public static final int VOUCHER_CONSUME_TYPE_OTHER_ADD = 9;
}
