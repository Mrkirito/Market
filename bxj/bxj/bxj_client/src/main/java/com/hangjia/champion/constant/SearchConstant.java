package com.hangjia.champion.constant;


public class SearchConstant {

    public static final String DEFAULT_KEYWORD = "找视频";//默认文字
    public static final String DEFAULT_KEYWORD_ALL = "*:*";//全局搜索
    public static final String SEPARATIVE_SIGN = "_";//页面排序参数分隔符
    public static final String COLON_SIGN = ":";//用于拼装solr搜索条件时使用
    public static final String MINUS = "-";
    public static final String AND = " OR ";

    public static final String DESC = "desc";
    public static final String ASC = "asc";

    /*搜索关键字*/
    public static final String ID = "id";
    public static final String FID = "fid";

    public static final String TITLE = "title";//标题

    public static final String CHANNEL_ID = "channel_id";//频道id

    public static final String CHANNEL_TITLE = "channel_title";//频道标题

    public static final String LECTURER_ID = "lecturer_id";//讲师id

    public static final String LECTURER_NAME = "lecturer_name";//讲师姓名

    public static final String PAGE_URL = "page_url";//链接

    public static final String VIDEO_SIZE = "video_size";//视频大小

    public static final String VIDEO_URL = "video_Url";//视频地址

    public static final String COVER_IMAGE_URL = "cover_Image_Url";//封面图片地址

    public static final String AUDIT_STATUS = "audit_status";//审核状态：default 2：通过；1：未通过；0：待审核

    public static final String IS_RECOMMEND = "is_recommend";//是否推荐：1；是；0：否；

    public static final String IS_UNICAST = "is_unicast";//1：点播；0-直播；

    public static final String PLAY_TYPE = "play_type";//是否独播：1：独播；0：非独播；

    public static final String DURATION_TIME = "duration_time";//持续时间

    public static final String ONLINE_TIME = "online_time";//上线时间

    public static final String OFFLINE_TIME = "offline_time";//下线时间

    public static final String PLAY_COUNT = "play_Count";//播放总数

    public static final String IS_FALSE_COUNT = "is_false_count";//是否显示假播放总数：1；是；0：否；

    public static final String FALSE_COUNT = "false_count";//假播放总数

    public static final String SHARE_COUNT = "share_Count";//分享总数

    public static final String COLLECTION_COUNT = "collection_Count";//收藏总数

    public static final String VOUCHER_ID = "voucher_Id";//用券

    public static final String VOUCHER_COUNT = "voucher_Count";//用券数

    public static final String MONEY = "money";//费用

    public static final String DESCRIPTION = "description";//描述

    public static final String QINIU_ID = "qiniu_Id";//七牛id

    public static final String STATUS = "status";//

    public static final String SORT = "sort";//排序

    public static final String CREATE_USER = "create_user";//创建人

    public static final String CREATE_AT = "create_at";//创建时间

    public static final String MODIFY_USER = "modify_user";//修改人

    public static final String MODIFY_AT = "modify_at";//修改时间

    public static final String AUDIT_USER = "audit_user";//审核人

    public static final String AUDIT_AT = "audit_at";//审核时间

    public static final String VIDEO_TYPE = "video_type"; //'类型：1：视频；2-音频'

}
