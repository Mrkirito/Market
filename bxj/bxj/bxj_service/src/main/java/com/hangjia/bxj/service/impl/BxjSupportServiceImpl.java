package com.hangjia.bxj.service.impl;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baobao.framework.support.utility.IpUtility;
import com.hangjia.bxj.common.OrderConstants;
import com.hangjia.bxj.common.RedisKeyConstants;
import com.hangjia.bxj.dao.*;
import com.hangjia.bxj.model.*;
import com.hangjia.bxj.vo.IpInfoRespDto;
import com.hangjia.bxj.vo.OrderData;
import com.hangjia.bxj.vo.OrderMethod;
import com.hangjia.bxj.vo.QueryProductVo;
import com.hangjia.bxj.vo.VersionInfoRespDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.framework.page.Paginator;
import com.baobao.framework.utils.DateFormatUtils;
import com.baobao.framework.utils.jedis.RedisUtil;
import com.hangjia.bxj.common.Constants;
import com.hangjia.bxj.common.ParseJson;
import com.hangjia.bxj.model.message.MessageLetterDO;
import com.hangjia.bxj.model.message.MessagePushDO;
import com.hangjia.bxj.query.PlanBookQuery;
import com.hangjia.bxj.service.BxjSupportService;
import com.hangjia.bxj.utils.Utils;

/**
 * 保保网络科技-bxj
 * com.hangjia.bxj.service.impl
 * 作者-秦岭(Tain)
 * 说明：
 * 2016/5/12 11:21
 * 2016保保网络-版权所有
 */
@Transactional(rollbackFor=Throwable.class)
public class BxjSupportServiceImpl implements BxjSupportService {

    @Autowired
    private ChampionInviteMapper championInviteMapper;

    @Autowired
    private SysMessageMapper sysMessageMapper;

    @Autowired
    private PlanProductCategroyMapper productCategroyMapper;

	@Autowired
	private BxjIpInfoMapper ipInfoMapper;

	@Autowired
	private BxjVersionInfoMapper versionInfoMapper;

	@Autowired
	private HomeConfMapper homeConfMapper;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private PlanBookMapper planBookMapper;

	@Autowired
	private ChampionUserVoucherMapper userVoucherMapper;

	@Value("${static_path}")
	private String staticPath;
	@Value("${bxj_path}")
	private String bxjPath;
    /**
     * 6666活动开始时间
     **/
    public static final String CASH_INVITE_START_TIME = "cash_invite_start_time";
    /**
     * 6666活动结束时间
     **/
    public static final String CASH_INVITE_END_TIME = "cash_invite_end_time";


	/** 表plan_product_categroy缓存Map数据KEY*/
	public static final String PlanProductCategroyKey ="_planProductCategroyKey";

    /**
     * 6666活动
     * 查询邀请注册并下载app成功数
     *
     * @param userId
     * @return
     */
    public Integer queryInviteUser(Long userId) {
        Map param = new HashMap();
        param.put("inviteUser", userId);
        param.put("startTime", StringUtils.isEmpty(this.getResponseParaByKey(CASH_INVITE_START_TIME)) ? null :
                this.getResponseParaByKey(CASH_INVITE_START_TIME));
        param.put("endTime", StringUtils.isEmpty(this.getResponseParaByKey(CASH_INVITE_END_TIME)) ? null :
                this.getResponseParaByKey(CASH_INVITE_END_TIME));
        return championInviteMapper.selectCountDownInvite(param).intValue();
    }

    /**
     * 红包活动
     * 查询邀请注册并下载app成功数
     *
     * @param userId
     * @return
     */
    public Integer queryInviteUserByActivityDate(Long userId,String activityCode, Date startTime, Date endTime) {
        Map param = new HashMap();
        param.put("inviteUser", userId);
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("activityCode", activityCode);
        return championInviteMapper.selectCountDownInvite(param).intValue();
    }

    /**
     * 6666活动
     * 查询邀请排行榜数据
     *
     * @param paginator
     * @return
     */
    public Paginator queryInviteSortList(Paginator paginator) {
        Map param = new HashMap();
        param.put("startTime", StringUtils.isEmpty(this.getResponseParaByKey(CASH_INVITE_START_TIME)) ? null :
                this.getResponseParaByKey(CASH_INVITE_START_TIME));
        param.put("endTime", StringUtils.isEmpty(this.getResponseParaByKey(CASH_INVITE_END_TIME)) ? null :
                this.getResponseParaByKey(CASH_INVITE_END_TIME));
        paginator.setParams(param);
        Integer count = Integer.valueOf(championInviteMapper.selectByPaginatorCount(paginator));
        List result = this.championInviteMapper.selectPaginateInvite(paginator);
        paginator.setItems(count.intValue());
        paginator.setResults(result);
        return paginator;
    }

    @Autowired
    private ControlAppStoreDao controlAppStoreDao;

    protected Map<String, String> getAllResponseParas() {
        Map<String, String> map = new HashMap<String, String>();
        List<BxjResponse> list = controlAppStoreDao.selectAppStore();
        for (BxjResponse bxjResponse : list) {
            map.put(bxjResponse.getId(), bxjResponse.getContent());
        }
        return map;
    }

    public String getResponseParaByKey(String key) {
        Map<String, String> map = getAllResponseParas();
        return map.get(key);
    }

    /**
     * 查询我的消息
     *
     * @param head body
     * @return
     */
	public Object getMySysMessage(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long msgType = ParseJson.parseToLong(body, Constants.BODY_JSON_MSG_TYPE);
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        List<SysMessage> result = new ArrayList<SysMessage>();
        SysMessage record = new SysMessage();
        if(null != userId){
        	record.setUserId(userId);
        	// 系统消息第一层
            if (msgType == null) {
            	result = sysMessageMapper.querySysMessageList(record);
            // 系统消息第二层
            } else {
            	record.setMsgType(msgType.intValue());
            	result = sysMessageMapper.querySysMessageDetail(record);
//            	if(result.size() > 0){
//            		record.setIsRead(1);
//            		record.setUpdateTime(new Date());
//            		// TODO 暂时没做更新部分失败处理
//            		sysMessageMapper.batchUpdateMsgRead(record);
//            	}
            }
            if(result.size() > 0){
            	// 时间format
            	for (SysMessage sysMessage : result) {
					if(null != sysMessage.getCreateTime()){
						sysMessage.setCreateTimeStr(DateFormatUtils.format("yyyy-MM-dd", sysMessage.getCreateTime()));
						sysMessage.setCreateTimeStr2(DateFormatUtils.format("yyyy-MM-dd HH:mm", sysMessage.getCreateTime()));
					}
					getMsgTypeValue(sysMessage);
				}
            }
            respMap.put("result", result);
        	return respMap;
        }
        return respMap;
    }

	/**
	 * 填充消息类型内容
	 * @param sysMessage
	 */
	private void getMsgTypeValue(SysMessage sysMessage){
		if(null != sysMessage.getMsgType()){
			String msgTypeValue = "";
			switch (sysMessage.getMsgType()) {
			case 1:
				msgTypeValue = "系统消息";
				break;
			case 2:
				msgTypeValue = "广播";
				break;
			case 3:
				msgTypeValue = "计划书";
				break;
			case 4:
				msgTypeValue = "赠险";
				break;
			case 5:
				msgTypeValue = "邀请";
				break;
			case 6:
				msgTypeValue = "邀请函";
				break;
			default:
				msgTypeValue = "";
			}
			sysMessage.setMsgTypeValue(msgTypeValue);
		}
	}
	@Override
	public Object getProductCategroys(String head, String body){
		Integer gs= ParseJson.parseToInt(body, Constants.BODY_JSON_GS_ID);
		return getPlanProductCategroyMap().get(gs);
	}
	/**
	 * 获取集合
	 * @return
	 */
	private Map<Integer, List<PlanProductCategroy>> getPlanProductCategroyMap(){
		Map<Integer,List<PlanProductCategroy>> map= (Map<Integer,List<PlanProductCategroy>>) redisUtil.getUnserializeKey(PlanProductCategroyKey);
		if (map == null || map.size()==0) {
			map= new HashMap<Integer, List<PlanProductCategroy>>();
			List<PlanProductCategroy> list = productCategroyMapper.getProductCategroys();
			for (PlanProductCategroy planProductCategroy : list) {
				Integer gs=planProductCategroy.getGs();
				List<PlanProductCategroy> ppcs=null;
				if(map.containsKey(gs)){
					ppcs=map.get(gs);
				}else{
					ppcs=new ArrayList<PlanProductCategroy>();
				}
				ppcs.add(planProductCategroy);
				map.put(gs, ppcs);
			}
			if(map.size()>0){
				redisUtil.setSerializeKey(PlanProductCategroyKey, map);
			}
		}
		return map;
	}

	/**
	 * 公用发送通知
	 * @param userIds 收的人ids
	 * @param sendId 发送人id
	 * @param moduleId 通知模板id
	 * @param msgType 消息类型
	 * @param args 消息参数
	 * @return
	 */
	@Override
	public int sendMessage(List<Long> userIds, Long sendId, Integer moduleId, Integer msgType, Object[] args) {
		int insert = 0;
		if(null != moduleId && null != sendId && null != msgType){
			SysMessage sysMessage = new SysMessage();
			// 系统通知
			if(msgType.intValue() != 1){
				if(null != userIds && userIds.size() > 0){
					sysMessage.setUserIds(userIds);
				} else {
					return insert;
				}
			}
			MessageLetterDO messageLetterDO = sysMessageMapper.queryLetterModuleById(moduleId);
			if(null != messageLetterDO){
				if(null != args){
					String msg = MessageFormat.format(messageLetterDO.getModuleContent(), args);
					sysMessage.setMsg(msg);
				} else {
					sysMessage.setMsg(messageLetterDO.getModuleContent());
				}
				sysMessage.setMsgType(msgType);
				sysMessage.setSendId(sendId);
				sysMessage.setMsgName(messageLetterDO.getModuleTitle());
				insert = sysMessageMapper.batchInsertLetter(sysMessage);
			}
		}
		return insert;
	}

    /**
     * 自动推送消息
     * @param map
     * <p><b>moduleId      模板名称                                                                                                                                                                       参数key                  key含义</b></p>
     * <p><b>1</b>         您有1个新粉丝。                                                                                                                                                         userId                  被发送推送的用户id</p>
     * <p><b>2</b>         您关注的{$讲师用户名}有新的更新。                                                                                                                      lectureName             讲师用户名</p>
     * <p><b>3</b>         您的《{$标题}》收到打赏${数字}元！继续加油哦！                                                                                            title,money             标题,金额</p>
     * <p><b>4</b>         您的客户“《{$投保人名称}》”对计划书《{$险种名称}》的反馈是【详细了解】，快去联系客户吧！            applyName,insuranceName 投保人名称,险种名称</p>
     * <p><b>5</b>         您《{$分享时间}》分享的xx险被客户领取啦！ 赠险详情：领取人：{}  年龄：{}  手机号码：{}  领取时间：{} shareTime,receiveName,age,mobile,receiveTime 分享时间,领取人,年龄,手机号码,领取时间</p>
	 * 			<p><b>可扩展的key	  key名称				key含义</b></p>
	 * 						<p><b>platform</b>			平台参数 ios android winphone 多平台 android_ios等 默认全平台。</p>
	 * 						<p><b>tagList</b>			标签 数组。多个标签之间是 OR 的关系，即取并集。一次推送最多 20 个。</p>
	 * 						<p><b>tagAndList</b>		标签 数组。多个标签之间是 AND 关系，即取交集。一次推送最多 20 个。</p>
	 * 						<p><b>alias</b>				用户list 数组。多个别名之间是 OR 关系，即取并集。一次推送最多 1000 个。</p>
	 * 						<p><b>regists</b>			数组。多个注册ID之间是 OR 关系，即取并集。一次推送最多 1000 个。</p>
	 * 						<p><b>noteTitle</b>			推送消息标题 ios无android winphone可选。
	 * 						<p><b>noteContent</b>		推送内容。</p>
	 * 						<p><b>extras</b>			扩展参数。</p>
	 * 						<p><b>sendno</b>			推送序号。</p>
	 * 						<p><b>timeToLive</b>		离线消息保留时长(秒)。</p>
	 * 						<p><b>apnsProduction</b>	APNs是否生产环境 默认true。</p>
     */
	@Override
	public void autoPushMessage(Map<String, Object> map) {
		if(null == map || map.get("moduleId") == null){
			return;
		}
        //获取模块名称
        String moduleId = String.valueOf(map.get("moduleId"));
        // 被关注
        if(StringUtils.equals(moduleId, Constants.ATTENTION_NEW_MODULE_ID)){
        	sendAttentionPush(map, moduleId);
        // 关注的讲师有新的更新
        } else if(StringUtils.equals(moduleId,Constants.ATTENTION_NEW_UPDATE_MODULE_ID)){
        	sendAttentionUpdatePush(map, moduleId);
        // 被打赏
        } else if(StringUtils.equals(moduleId,Constants.REWARD_MODULE_ID)){
        	sendRewardPush(map, moduleId);
        // 计划书反馈
        } else if(StringUtils.equals(moduleId,Constants.PLANBOOK_FEEDBACK_MODULE_ID)){
        	sendPlanBookFeedBackPush(map, moduleId);
        // 赠险被领取
        } else if(StringUtils.equals(moduleId,Constants.GIFT_RECEIVE_MODULE_ID)){
        	sendGiftReceivePush(map, moduleId);
        }
	}

	/**
	 * 验证ip是否存在所属省份
	 *
	 * @param ip
	 * @param province
	 * @return
	 */
	@Override
	public boolean validateIpProvince(String ip, String province) {
		String ipProvince = this.getIpProvince(ip);
		if (StringUtils.isBlank(ipProvince)) {
			return false;

		}
		if (StringUtils.isBlank(province)) {
			return false;
		}
		if (ipProvince.equals(province)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证APP版本是否需要更新
	 *
	 * @param head
	 * @param body
	 * @return
	 */
	@Override
	public Object validateAppVersion(String head, String body) {
		Map<String, Object> respInfo = new HashMap<String, Object>();
		String os = ParseJson.parseToString(head, Constants.HEAD_JSON_OS);
		String versionCode = ParseJson.parseToString(head, Constants.HEAD_JSON_VERSION_CODE);
		BxjVersionInfo conditions = new BxjVersionInfo();
		conditions.setVersionNumber(Integer.valueOf(versionCode));
		if (Constants.HEAD_JSON_OS_ANDROID.equalsIgnoreCase(os)) {
			conditions.setAppType(Constants.HEAD_JSON_OS_ANDROID);
		} else if (Constants.HEAD_JSON_OS_IOS.equalsIgnoreCase(os)) {
			conditions.setAppType(Constants.HEAD_JSON_OS_IOS);
		}
		conditions.setStatus(Constants.VERSION_STATUS_2);
		conditions.setCurrPage(1);
		conditions.setPageSize(1);
		conditions.addOrderData(new OrderData(OrderConstants.BXJ_VERSION_INFO_VERSION_NUMBER, OrderMethod.DESC));
		List<BxjVersionInfo> versionInfos = versionInfoMapper.selectBySelective(conditions);
		VersionInfoRespDto versionInfoDto = new VersionInfoRespDto();
		if (versionInfos != null && !versionInfos.isEmpty()) {
			respInfo.put("isNewVersion", true);
			BeanUtils.copyProperties(versionInfos.get(0), versionInfoDto);
		} else {
			respInfo.put("isNewVersion", false);
		}
		respInfo.put("versionInfo", versionInfoDto);
		return respInfo;
	}

	/**
	 * 获取ip地址信息
	 *
	 * @param head
	 * @param body
	 * @return
	 */
	@Override
	public Object getIpAddressInfo(String head, String body) {
		Map<String, Object> respInfo = new HashMap<String, Object>();
		//从缓存获取相关信息
		String ip = ParseJson.parseToString(head, Constants.HEAD_JSON_OS_IP);
		BxjIpInfo conditions = new BxjIpInfo();
		conditions.setIp(ip);
		conditions.setIpSource(Constants.IP_SOURCE_BAIDU);
		List<BxjIpInfo> ipInfos = ipInfoMapper.selectBySelective(conditions);
		IpInfoRespDto ipInfoResp = new IpInfoRespDto();
		if (ipInfos != null && !ipInfos.isEmpty()) {
			BeanUtils.copyProperties(ipInfos.get(0), ipInfoResp);
			respInfo.put("ipInfo", ipInfos.get(0));
			return respInfo;
		}
		String ipInfoJson = IpUtility.getBaiduIpCityInfo(ip);
		if (StringUtils.isNotBlank(ipInfoJson)) {
			BxjIpInfo ipInfo = JSONObject.parseObject(ipInfoJson, BxjIpInfo.class);
			ipInfoMapper.insertSelective(ipInfo);
			BeanUtils.copyProperties(ipInfo, ipInfoResp);
			respInfo.put("ipInfo", ipInfoResp);
			return respInfo;
		}
		respInfo.put("ipInfo", ipInfoResp);
		return respInfo;
	}

	/**
	 * 获取banner信息
	 *
	 * @param head
	 * @param body
	 * @return
	 */
	@Override
	public Object getBannerInfos(String head, String body) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String os = ParseJson.parseToString(head, Constants.HEAD_JSON_OS);
		String versionCode = ParseJson.parseToString(head, Constants.HEAD_JSON_VERSION_CODE);
		Long type = ParseJson.parseToLong(body, "type");
		if (null == type) {
			respMap.put(Constants.RESPONSE_ERROR_CODE, "type is null!");
			return respMap;
		}
		int vCode = Integer.parseInt(versionCode);
		Double version = null;
		if("android".equals(os) && vCode==11 || "iOS".equals(os) && vCode<=104) version = 3.0;
		else if(("android".equals(os) && vCode>=12 && vCode<18) || ("iOS".equals(os) && vCode>=105) && vCode<120) version = 3.1;
		else if("android".equals(os) && vCode==18 || "iOS".equals(os) && vCode==120) version = 3.2;
		else if("android".equals(os) && vCode>=19 || "iOS".equals(os) && vCode>=121) version = 3.3;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("version", version);
		paramMap.put("type", type);
		List<HomeConf> homeConfs = homeConfMapper.getHomeConfByType(paramMap);
		respMap.put("banners", homeConfs);
		return respMap;
	}

	/**
	 * 获取ip所在省份
	 *
	 * @param ip
	 * @return
	 */
	private String getIpProvince(String ip) {
		//从缓存获取相关信息
		String province = this.getIpProvinceFromRedis(ip);
		if (StringUtils.isNotBlank(province)) {
			return province;
		}
		province = this.getIpProvinceFromDb(ip);
		if (StringUtils.isNotBlank(province)) {
			return province;
		}
		String ipInfoJson = IpUtility.getBaiduIpCityInfo(ip);
		if (StringUtils.isNotBlank(ipInfoJson)) {
			BxjIpInfo ipInfo = JSONObject.parseObject(ipInfoJson, BxjIpInfo.class);
			ipInfoMapper.insertSelective(ipInfo);
			redisUtil.hashSet(RedisKeyConstants.KEY_BXJ_IP_INFO_HASH, ip, ipInfo.getProvince());
		}
		return null;
	}

	/**
	 * 从缓存获取ip信息
	 *
	 * @param ip
	 * @return
	 */
	private String getIpProvinceFromRedis(String ip) {
		return redisUtil.hashGet(RedisKeyConstants.KEY_BXJ_IP_INFO_HASH, ip);
	}

	/**
	 * 从db中获取ip信息
	 *'
	 * @param ip
	 * @return
	 */
	private String getIpProvinceFromDb(String ip) {
		BxjIpInfo conditions = new BxjIpInfo();
		conditions.setIp(ip);
		conditions.setIpSource(Constants.IP_SOURCE_BAIDU);
		List<BxjIpInfo> ipInfos = ipInfoMapper.selectBySelective(conditions);
		if (ipInfos != null && !ipInfos.isEmpty()) {
			return ipInfos.get(0).getProvince();
		}
		return null;
	}

	/**
	 * 发送被关注的推送
	 * @param map
	 * @param moduleId
	 */
	private void sendAttentionPush(Map<String,Object> map, String moduleId){
        // 参数处理
        String userId = (String) map.get("userId");
        if(StringUtils.isBlank(userId)){
            return;
        }
        String args[]=new String[]{("")};
        MessagePushDO pushDO = getMessage(moduleId, args);
        if(null == pushDO){
        	return;
        }
        map.put("noteContent", pushDO.getModuleContent());
        map.put("noteTitle", pushDO.getModuleTitle());
        map.put("regists", userId);
        sendPush(map);
	}

	/**
	 * 发送被打赏的推送
	 * @param map
	 * @param moduleId
	 */
	private void sendRewardPush(Map<String,Object> map, String moduleId){
        // 参数处理
        String userId = (String) map.get("userId");
        if(StringUtils.isBlank(userId)){
            return;
        }
        String title = (String) map.get("title");
        String money = (String) map.get("money");
        String args[]=new String[]{title,money};
        MessagePushDO pushDO = getMessage(moduleId, args);

        if(null == pushDO){
        	return;
        }
        map.put("noteContent", pushDO.getModuleContent());
        map.put("noteTitle", pushDO.getModuleTitle());
        map.put("regists", userId);
        sendPush(map);
	}

	/**
	 * 发送计划书反馈的推送
	 * @param map
	 * @param moduleId
	 */
	private void sendPlanBookFeedBackPush(Map<String,Object> map, String moduleId){
        // 参数处理
        String userId = (String) map.get("userId");
        if(StringUtils.isBlank(userId)){
            return;
        }
        String applyName = (String) map.get("applyName");
        String insuranceName = (String) map.get("insuranceName");
        String args[]=new String[]{applyName,insuranceName};
        MessagePushDO pushDO = getMessage(moduleId, args);

        if(null == pushDO){
        	return;
        }
        map.put("noteContent", pushDO.getModuleContent());
        map.put("noteTitle", pushDO.getModuleTitle());
        map.put("regists", userId);
        sendPush(map);
	}

	/**
	 * 发送被关注的推送
	 * @param map
	 * @param moduleId
	 */
	private void sendAttentionUpdatePush(Map<String,Object> map, String moduleId){
        // 参数处理
        String userId = (String) map.get("userId");
        if(StringUtils.isBlank(userId)){
            return;
        }
        String lectureName = (String) map.get("lectureName");
        String args[]=new String[]{lectureName};
        MessagePushDO pushDO = getMessage(moduleId, args);
        if(null == pushDO){
        	return;
        }
        map.put("noteContent", pushDO.getModuleContent());
        map.put("noteTitle", pushDO.getModuleTitle());
        map.put("regists", userId);
        sendPush(map);
	}

	/**
	 * 发送赠险被领取的推送
	 * @param map
	 * @param moduleId
	 */
	private void sendGiftReceivePush(Map<String,Object> map, String moduleId){
        // 参数处理
        String userId = (String) map.get("userId");
        if(StringUtils.isBlank(userId)){
            return;
        }
        String shareTime = (String) map.get("shareTime");
        String receiveName = (String) map.get("receiveName");
        String age = (String) map.get("age");
        String mobile = (String) map.get("mobile");
        String receiveTime = (String) map.get("receiveTime");
        String args[]=new String[]{shareTime,receiveName,age,mobile,receiveTime};
        MessagePushDO pushDO = getMessage(moduleId, args);

        if(null == pushDO){
        	return;
        }
        map.put("noteContent", pushDO.getModuleContent());
        map.put("noteTitle", pushDO.getModuleTitle());
        map.put("regists", userId);
        sendPush(map);
	}

    /**
     * 获取消息
     * @param id
     * @param args 占位符替换数组
     */
    private MessagePushDO getMessage(String id, Object[] args){
        //消息获取
        String msgContent="";
        Integer moduleId = Integer.valueOf(id);
        MessagePushDO pushDO = sysMessageMapper.queryPushModuleById(moduleId);
        if(null != pushDO){
            msgContent=pushDO.getModuleContent();
            pushDO.setModuleContent(MessageFormat.format(msgContent, args));
        }
        return pushDO;
    }

    /**
     * 公用发送push
     * @param map
     */
    private void sendPush(Map<String,Object> map){
    	//TODO
    }
    /**
     * 计划书产品列表页
     */
	@Override
	public Object getPlanBookInfos(String head, String body) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		QueryProductVo vo = new QueryProductVo();
		Integer fid = ParseJson.parseToInt(body, "fid");// 公司
		Integer page = ParseJson.parseToInt(body, "page");// 第几页
		Integer pageSize = ParseJson.parseToInt(body, "pageSize");// 一页多少
		Integer cfid = ParseJson.parseToInt(body, "cfid");// 分类id
		String name = ParseJson.parseToString(body, "name");// 产品名称搜索
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		vo.setCurrentPage(page);
		vo.setPageSize(pageSize);
		vo.setGs(fid == null || fid == 38 || fid == 51 ? null : fid);
		vo.setName(name);
		vo.setCfid(cfid != null && cfid == 99 ? null : cfid);
		int total=planBookMapper.getCount(vo);
		List<PlanProductSimple> rows = new ArrayList<PlanProductSimple>();
		if (total > 0) {
			vo.setOffset((vo.getCurrentPage() - 1) * vo.getPageSize());
			List<PlanProductSimple> data= planBookMapper.getPlanProductSimpleByPage(vo);
			for (PlanProductSimple simple : data) {
				simple.setImageURL(staticPath+File.separator+simple.getImageURL());
				rows.add(simple);
			}
		}
		respMap.put("total", total);
		respMap.put("rows", rows);
		return respMap;
	}
	/**
	 * 我的计划书列表
	 */
	@Override
	public Object getMyPlanBookInfos(String head, String body) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		PlanBookQuery query = new PlanBookQuery();
		Long userId = ParseJson.parseToLong(head, Constants.HEAD_JSON_USER_ID);
		if (null == userId) {
			respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
			return respMap;
		}
		Integer currentPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
		if (currentPage == null) {
			currentPage = 1;
		}
		Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
		if (pageSize == null) {
			pageSize = 20;
		}
		query.setUserId(userId);
		query.setCurrentPage(currentPage);
		query.setPageSize(pageSize);
		int total = planBookMapper.getPlanBookCountByUserId(query);
		List<PlanBookUserVo> rows = new ArrayList<PlanBookUserVo>();
		if (total > 0) {
			List<PlanBookUserVo> data = planBookMapper.getPlanBooksByUserId(query);
			Date now=new Date();
			for (PlanBookUserVo vo : data) {
				String showTime = Utils.getSecondsBetweenTimes(vo.getCtime(), now);
				vo.setImg(staticPath+File.separator+vo.getImg());
				vo.setTargetLink(bxjPath+File.separator+"plan"+File.separator+"detail"+vo.getPlanId()+".page?hd=1&head=0&shareios=1");
				vo.setShowTime(showTime);
				rows.add(vo);
			}
		}
		respMap.put("total", total);
		respMap.put("rows", rows);
		return respMap;
	}
	/**
	 * 我的计划书删除
	 */
	@Override
	public Object deleteMyPlanBookInfos(String head, String body) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		PlanBookQuery query = new PlanBookQuery();
		Long userId = ParseJson.parseToLong(head, Constants.HEAD_JSON_USER_ID);
		String planIds = ParseJson.parseToString(body, "planIds");
		if (StringUtils.isEmpty(planIds)) {
			respMap.put("execute", 4);
			return respMap;
		}
		List<Long> planIdList = new ArrayList<Long>();
		String[] str = planIds.split(",");
		for (String string : str) {
			if (StringUtils.isNotEmpty(string)) {
				planIdList.add(Long.parseLong(string));
			}
		}
		if (planIdList.isEmpty()) {
			respMap.put("execute", 4);
			return respMap;
		}
		query.setUserId(userId);
		query.setPlanIds(planIdList);
		int i = planBookMapper.deletePlanBookByPlanIds(query);
		respMap.put("execute", i>0?1:0);
		return respMap;
	}

    /**
     * 获取用户所有视频券
     *
     * @param userId
     * @return
     */
    @Override
    public int getMyVideoVoucherTotal(Long userId) {
        ChampionUserVoucher userVoucher = new ChampionUserVoucher();
        userVoucher.setUserId(userId);
        userVoucher.setVoucherId(1L);
        List<ChampionUserVoucher> userVouchers = userVoucherMapper.selectBySelective(userVoucher);
        if (userVouchers != null && !userVouchers.isEmpty()) {
            return userVouchers.get(0).getTotal();
        }
        return 0;
    }
}
