package com.hangjia.bxj.util;

/**
 * @ClassName: RedisKeyConstants
 * @Description: REDIS KEY常量
 * @author: bei.zhang
 * @date: 2016年4月18日 上午10:41:41
 */
public class RedisKeyConstants {

	/** 工作年限 **/
	public static final String KEY_JOB_YERAS = "jobyears";
	/** 默认性别 **/
	public static final String KEY_DEFAULT_SEX = "default_sex";
	/** 默认风格模板 **/
	public static final String KEY_DEFAULT_MODEL = "default_model";
	/** 默认公司编码 **/
	public static final String KEY_DEFAULT_COMPANY = "default_company";
	/** 默认公司名称 **/
	public static final String KEY_DEFAULT_COMPANY_NAME = "default_company_name";
	/** 默认职位编码 **/
	public static final String KEY_DEFAULT_POSITION = "default_position";
	/** 默认职位名称 **/
	public static final String KEY_DEFAULT_POSITION_NAME = "default_position_name";
	/** 公司职位列表 **/
	public static final String KEY_COMPANY_AND_POSITION = "company_and_position";
	/** 默认名片所在城市 **/
	public static final String KEY_DEFAULT_CITIES_NAME = "default_cities_name";
	/** 计划书查询默认选择的公司 **/
	public static final String KEY_DEFAULT_SELECT_COMMPANY = "default_select_commpany";
	
	/** 计划书生成页面【详细了解】 */
	public static final String KEY_PLAN_BOOK_REALIZE = "plan_book_realize";
	/** 计划书生成页面【再考虑一下】 */
	public static final String KEY_PLAN_BOOK_REGARD = "plan_book_regard";
	/** 计划书生成页面被打开次数 */
	public static final String KEY_PLAN_BOOK_STATISTICS = "plan_book_statistics";
	
	/** 默认用户语音上传最大条数 **/
	public static final String KEY_DEFAULT_MAX_USER_VOCIE = "default_max_user_voice";
	
	/** 表bxj_response缓存数据KEY*/
	public static final String BXJ_RESPONSE_MAP="Bxj_Response_Map"; 
	/** 表uc_company缓存数据KEY*/
	public static final String PlanProductGsKey = "planProductGsKey";
	/** 单个计划书产品缓存数据KEY：singlePlanProductKey_32 为KEY*/
	public static final String SinglePlanProductKey = "singlePlanProductKey_";
	/** 表plan_product_constant缓存Map数据KEY*/
	public static final String PlanProductConstantMapKey ="planProductConstantMapKey";
	/** 表plan_product_categroy缓存Map数据KEY*/
	public static final String PlanProductCategroyKey ="_planProductCategroyKey";


	/** ip信息HashKey **/
	public static final String KEY_BXJ_IP_INFO_HASH = "bxj_ip_info_hash";


	/** 默认主题 **/
	public static final String KEY_DEFAULT_THEME = "default_theme";
	/** 默认banner轮播开关 **/
	public static final String KEY_DEFAULT_BANNER_PLAY = "default_banner_play";

	public static final String BXJ_PAY = "bxj_pay";
}
