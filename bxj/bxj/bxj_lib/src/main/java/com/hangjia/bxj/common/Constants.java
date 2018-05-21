package com.hangjia.bxj.common;

/**
 * 常量类
 * Created by bei.zhang on 2016/4/12.
 */
public class Constants {

    /**
     * 系统 android、ios
     **/
    public static final String HEAD_JSON_OS = "os";
    /**
     * 安卓
     */
    public static final String HEAD_JSON_OS_ANDROID = "android";
    /**
     * IOS
     */
    public static final String HEAD_JSON_OS_IOS = "IOS";
    /**
     * IP
     */
    public static final String HEAD_JSON_OS_IP = "ip";
    /**
     * 版本code
     **/
    public static final String HEAD_JSON_VERSION_CODE = "versionCode";

    /**
     * 版本
     **/
    public static final String HEAD_JSON_VERSION = "version";

    /**
     * 系统版本
     **/
    public static final String HEAD_JSON_OS_VERSION = "osVersion";
    /**
     * head json字段
     **/
    public static final String HEAD_JSON_USER_ID = "userId";
    /**
     * body json字段
     **/
    public static final String BODY_JSON_USER_ID = "userId";

    public static final String BODY_JSON_ID = "id";

    /**
     * 错误code
     **/
    public static final String RESPONSE_ERROR_CODE = "500";
    /**
     * 返回结果
     **/
    public static final String RESPONSE_RESULT = "result";


    /**
     * 我的配置分组
     **/
    public static final int BXJ_MY_CONF_GROUP1 = 1;
    public static final int BXJ_MY_CONF_GROUP2 = 2;
    public static final int BXJ_MY_CONF_GROUP3 = 3;
    public static final int BXJ_MY_CONF_GROUP4 = 4;

    /**
     * 我的配置类型 默认
     **/
    public static final int BXJ_MY_CONF_DEFAULT = 1;
    /**
     * 我的配置类型 讲师
     **/
    public static final int BXJ_MY_CONF_LERTURE = 2;

    /**
     * 系统消息类型 1:系统消息 2:广播 3:计划书 4:增险 5:邀请 ,6.邀请函
     **/
    public static final String BODY_JSON_MSG_TYPE = "msgType";

    /**
     * 默认第一页
     **/
    public static final int DEFAULT_CURRENT_PAGE = 1;
    /**
     * 默认取10条
     **/
    public static final int DEFAULT_PAGESIZE = 10;
    /**
     * 取3条
     */
    public static final int DEFAULT_PAGESIZE_3 = 3;
    /**
     * 当前页
     **/
    public static final String BODY_JSON_CURRPAGE = "currPage";
    /**
     * 页size
     **/
    public static final String BODY_JSON_PAGESIZE = "pageSize";
    /**
     * 朋友圈分类id
     */
    public static final String BODY_JSON_CATEGORY_ID = "categoryId";
    /**
     * 朋友圈分类type
     */
    public static final String BODY_JSON_TYPE = "type";
    /**
     * 公司ID
     */
    public static final String BODY_JSON_GS_ID = "gs";

    public static final String FIREND_CIRCLE_DATE_TITLE = "  每天10点准时更新";

    public static final String DATA_FORMAT_YYYYMMDD= "yyyy-MM-dd";

    /**
     * 数据库配置的push消息模板
     */
    public static final String ATTENTION_NEW_MODULE_ID="1"; // 被关注
    public static final String ATTENTION_NEW_UPDATE_MODULE_ID="2"; // 关注的讲师有新的更新
    public static final String REWARD_MODULE_ID="3"; // 被打赏
    public static final String PLANBOOK_FEEDBACK_MODULE_ID="4"; // 计划书反馈
    public static final String GIFT_RECEIVE_MODULE_ID="5"; // 增险被领取

    /**扫码入场常量**/
    public static final String USER_ENTRANCE = "entrance";
    /**扫码出场常量**/
    public static final String USER_EJECTION = "ejection";


    /**ip信息来源 百度**/
    public static final String IP_SOURCE_BAIDU = "baidu";
    /**ip信息来源 淘宝**/
    public static final String IP_SOURCE_TAOBAO = "taobao";

    /**版本状态 1：待发布**/
    public static final int VERSION_STATUS_1 = 1;
    /**版本状态 2：已发布**/
    public static final int VERSION_STATUS_2 = 2;

    public static final String FRIEND_CIRCLE_WEEK_RANK = "bxj_friend_circle_week_rank";
}
