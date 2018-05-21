package com.hangjia.bxj.service.impl;

import com.baobao.framework.support.utility.DateUtility;
import com.baobao.sso.client.*;
import com.baobao.sso.service.UserCardSupportService;
import com.baobao.sso.service.UserService;
import com.hangjia.bxj.common.Constants;
import com.hangjia.bxj.common.OrderConstants;
import com.hangjia.bxj.common.Pagination;
import com.hangjia.bxj.common.ParseJson;
import com.hangjia.bxj.dao.BxjMyConfMapper;
import com.hangjia.bxj.dao.ControlAppStoreDao;
import com.hangjia.bxj.model.BxjMyConf;
import com.hangjia.bxj.model.BxjResponse;
import com.hangjia.bxj.service.MySupportService;
import com.hangjia.bxj.vo.MyConfRespDto;
import com.hangjia.bxj.vo.OrderData;
import com.hangjia.bxj.vo.OrderMethod;
import com.ibaoxianjia.champion.bean.*;
import com.ibaoxianjia.champion.service.ChampionUserService;
import com.ibaoxianjia.study.service.StudySupportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 我的业务支持接口实现
 * Created by bei.zhang on 2016/5/23.
 */
public class MySupportServiceImpl implements MySupportService {

    private static Logger logger = LoggerFactory.getLogger(MySupportServiceImpl.class);

    @Autowired
    private BxjMyConfMapper myConfMapper;
    @Autowired
    private UserCardSupportService userCardSupportService;
    @Autowired
    private ControlAppStoreDao controlAppStoreDao;
    @Autowired
    private ChampionUserService championUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudySupportService studySupportService;

    /**
     * 根据用户ID获取用户信息
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getStatInfoByUserId(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            Map<String, Object> statInfo = new HashMap<String, Object>();
            statInfo.put("publishCount", 0);
            statInfo.put("focusCount", 0);
            statInfo.put("fansCount", 0);
            statInfo.put("collectCount", 0);
            respMap.put("statInfo", statInfo);
            return respMap;
        }
        try {
            ChampionUserDataBean userDataBean = championUserService.getChampionUserDataByUserId(userId);
            int articleCount = studySupportService.getCollectArticleCount(userId);
            if (userDataBean == null) {
                Map<String, Object> statInfo = new HashMap<String, Object>();
                statInfo.put("publishCount", 0);
                statInfo.put("focusCount", 0);
                statInfo.put("fansCount", 0);
                statInfo.put("collectCount", articleCount);
                respMap.put("statInfo", statInfo);
            } else {
                Map<String, Object> statInfo = new HashMap<String, Object>();
                statInfo.put("publishCount", userDataBean.getPublishCount());
                statInfo.put("focusCount", userDataBean.getAttentionCount());
                statInfo.put("fansCount", userDataBean.getFansCount());
                statInfo.put("collectCount", userDataBean.getCollectionCount() + articleCount);
                respMap.put("statInfo", statInfo);
            }
            return respMap;
        } catch (Exception ex) {
            logger.error("获取我的统计信息异常 异常原因：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的统计信息异常!");
            return respMap;
        }

    }

    /**
     * 获取我的配置信息
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyConfInfo(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(head, Constants.HEAD_JSON_USER_ID);
        try {
            if (userId != null) {
                UserCardRespDto userCardResp = userCardSupportService.getUserBaseInfoByUserId(userId.intValue());
                if (userCardResp != null && userCardResp.getIsLecturer() != null && userCardResp.getIsLecturer()) {
                    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
                    Map<String, Object> groupMap = new HashMap<String, Object>();
                    groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP1, Constants.BXJ_MY_CONF_LERTURE));
                    groups.add(groupMap);
                    groupMap = new HashMap<String, Object>();
                    groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP2, Constants.BXJ_MY_CONF_LERTURE));
                    groups.add(groupMap);
                    groupMap = new HashMap<String, Object>();
                    groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP3, Constants.BXJ_MY_CONF_LERTURE));
                    groups.add(groupMap);
                    groupMap = new HashMap<String, Object>();
                    groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP4, Constants.BXJ_MY_CONF_LERTURE));
                    groups.add(groupMap);
                    respMap.put("groups", groups);
                    return respMap;
                }
            }
            List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
            Map<String, Object> groupMap = new HashMap<String, Object>();
            groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP1, Constants.BXJ_MY_CONF_DEFAULT));
            groups.add(groupMap);
            groupMap = new HashMap<String, Object>();
            groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP2, Constants.BXJ_MY_CONF_DEFAULT));
            groups.add(groupMap);
            groupMap = new HashMap<String, Object>();
            groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP3, Constants.BXJ_MY_CONF_DEFAULT));
            groups.add(groupMap);
            groupMap = new HashMap<String, Object>();
            groupMap.put("group", this.getBxjMyConfByGroup(Constants.BXJ_MY_CONF_GROUP4, Constants.BXJ_MY_CONF_DEFAULT));
            groups.add(groupMap);
            respMap.put("groups", groups);
            return respMap;
        } catch (Exception ex) {
            logger.error("我的配置----获取我的配置分组信息异常 异常原因：{}", ex.getMessage());
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的配置分组信息异常!");
            ex.printStackTrace();
            return respMap;
        }
    }

    /**
     * 获取我的资料信息
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyInformation(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        try {
            UserCardRespDto userInfo = userCardSupportService.getUserBaseInfoByUserId(userId.intValue());
            List<UserHonorRespDto> userHonors = null;
            if (userInfo.getIsLecturer()) {
                userHonors = userCardSupportService.getUserHonorByUserId(userId);
            }
            respMap.put("userInfo", userInfo);
            respMap.put("userHonors", userHonors);
            respMap.put("fansCount", championUserService.getMyFansCount(userId));
            respMap.put("shareUrl", championUserService.getLecturerShareUrl(userId));
        } catch (Exception ex) {
            logger.error("我的资料----获取我的资料信息异常 异常原因：{}", ex.getMessage());
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的资料信息异常!");
            ex.printStackTrace();
        }
        return respMap;
    }

    /**
     * 获取我的发布信息
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyPublish(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE) == null ? Constants.DEFAULT_CURRENT_PAGE : ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE) == null ? Constants.DEFAULT_PAGESIZE : ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        try {
            int count = championUserService.getLecturerVideoCount(userId, 1);
            List<ChampionVideoBean> videos = championUserService.getLecturerVideo(currPage, pageSize, userId, 1);
            return new Pagination<ChampionVideoBean>(count, currPage, pageSize, videos);
        } catch (Exception ex) {
            logger.error("获取我的发布信息异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的发布信息异常!");
            return respMap;
        }
    }

    /**
     * 获取我的关注信息
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyFocus(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE) == null ? Constants.DEFAULT_CURRENT_PAGE : ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE) == null ? Constants.DEFAULT_PAGESIZE : ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        try {
            int count = championUserService.getMyAttentionsCount(userId);
            List<Map<String, Object>> focusInfos = new ArrayList<Map<String, Object>>();
            if (count > 0) {
                List<Long> userIds = championUserService.getMyAttentions(currPage, pageSize, userId);
                if (userIds != null && !userIds.isEmpty()) {
                    focusInfos = userCardSupportService.getFocusInfos(userIds);
                }
            }
            return new Pagination<Map<String, Object>>(count, currPage, pageSize, focusInfos);
        } catch (Exception ex) {
            logger.error("获取我的关注信息异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的关注信息异常!");
            return respMap;
        }
    }

    /**
     * 获取我的粉丝
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyFans(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE) == null ? Constants.DEFAULT_CURRENT_PAGE : ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE) == null ? Constants.DEFAULT_PAGESIZE : ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        try {

            int count = championUserService.getMyFansCount(userId);
            List<Map<String, Object>> fansInfos = new ArrayList<Map<String, Object>>();
            if (count > 0) {
                List<Long> userIds = championUserService.getMyFans(currPage, pageSize, userId);
                if (userIds != null && !userIds.isEmpty()) {
                    fansInfos = userCardSupportService.getFansInfos(userIds);
                }
            }
            return new Pagination<Map<String, Object>>(count, currPage, pageSize, fansInfos);
        } catch (Exception ex) {
            logger.error("获取我的粉丝信息异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的粉丝信息异常!");
            return respMap;
        }
    }

    /**
     * 获取我的声音
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyVoice(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE) == null ? Constants.DEFAULT_CURRENT_PAGE : ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE) == null ? Constants.DEFAULT_PAGESIZE : ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        try {
            int count = championUserService.getLecturerVideoCount(userId, 2);
            List<ChampionVideoBean> videos = championUserService.getLecturerVideo(currPage, pageSize, userId, 2);
            return new Pagination<ChampionVideoBean>(count, currPage, pageSize, videos);
        } catch (Exception ex) {
            logger.error("获取我的声音异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的声音异常!");
            return respMap;
        }
    }

    /**
     * 获取我的打赏
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyEnjoyPlaying(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE) == null ? Constants.DEFAULT_CURRENT_PAGE : ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE) == null ? Constants.DEFAULT_PAGESIZE : ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        try {
            int count = championUserService.getMyRewardsCount(userId);
            List<Map<String, Object>> userRewardMaps = new ArrayList<Map<String, Object>>();
            if (count > 0) {
                List<ChampionUserRewardBean> userRewards = championUserService.getMyRewards(currPage, pageSize, userId);
                if (userRewards != null && !userRewards.isEmpty()) {
                    for (ChampionUserRewardBean userReward : userRewards) {
                        Map<String, Object> userRewardMap = new HashMap<String, Object>();
                        userRewardMap.put("id", userReward.getId());
                        userRewardMap.put("amount", userReward.getCount());
                        userRewardMap.put("createTime", DateUtility.format("yyyy-MM-dd HH:mm:ss", userReward.getRewardDate()));
                        userRewardMap.put("createTimeLong", userReward.getRewardDate());
                        userRewardMap.put("title", userReward.getTitle());
                        userRewardMaps.add(userRewardMap);
                    }
                }
            }
            return new Pagination<Map<String, Object>>(count, currPage, pageSize, userRewardMaps);
        } catch (Exception ex) {
            logger.error("获取我的打赏异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的打赏异常!");
            return respMap;
        }
    }

    /**
     * 获取我的门票
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyEntranceTicket(String head, String body) {
        // TODO: 2016/5/24  
        return null;
    }

    /**
     * 获取我的收益
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyIncome(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        try {
            UserAccountRespDto userAccountResp = userService.getUserAccountInfoByUserId(userId);
            respMap.put("sumAmount", userAccountResp.getUsableAmount());
            respMap.put("todayAmount", userService.getTodayIncome(userId));
            return respMap;
        } catch (Exception ex) {
            logger.error("获取我的收益异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的收益异常!");
            return respMap;
        }
    }

    /**
     * 获取我的奖品
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getMyPrize(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE) == null ? Constants.DEFAULT_CURRENT_PAGE : ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE) == null ? Constants.DEFAULT_PAGESIZE : ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        try {
            int count = championUserService.queryEggPrizeCount(userId);
            List<Map<String, Object>> userPrizeMaps = new ArrayList<Map<String, Object>>();
            if (count > 0) {
                List<ChampionUserEggPrizeBean> userPrizes = championUserService.queryEggPrizeList(currPage, pageSize, userId);
                if (userPrizes != null && !userPrizes.isEmpty()) {
                    for (ChampionUserEggPrizeBean userPrize : userPrizes) {
                        Map<String, Object> userPrizeMap = new HashMap<String, Object>();
                        userPrizeMap.put("id", userPrize.getId());
                        userPrizeMap.put("prizeName", userPrize.getPrizeName());
                        userPrizeMap.put("prizeType", userPrize.getPrizeType());
                        userPrizeMap.put("imgUrl", userPrize.getPrizeImg());
                        userPrizeMap.put("pageUrl", userPrize.getUrl());
                        userPrizeMap.put("createTime", DateUtility.format("yyyy-MM-dd HH:mm:ss", userPrize.getCreateTime()));
                        userPrizeMap.put("createTimeLong", userPrize.getCreateTime());
                        userPrizeMap.put("isExchange", userPrize.getIsExchangel());
                        userPrizeMaps.add(userPrizeMap);
                    }
                }
            }
            return new Pagination<Map<String, Object>>(count, currPage, pageSize, userPrizeMaps);
        } catch (Exception ex) {
            logger.error("获取我的奖品异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取我的奖品异常!");
            return respMap;
        }
    }

    /**
     * 获取公司和职位
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getConpanyAndPosition(String head, String body) {
        // TODO: 2016/5/25
        Map<String, Object> respMap = new HashMap<String, Object>();
        List<CompanyRespDto> companys = userCardSupportService.getAllCompanyAndPosition();
        for (CompanyRespDto companyPosition : companys) {
            if ("全部公司".equals(companyPosition.getCompanyName().trim())) {
                companys.remove(companyPosition);
                break;
            }
        }
        respMap.put("companyPositions", companys);
        return respMap;
    }

    /**
     * 获取从业年限
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getJobYears(String head, String body) {
        // TODO: 2016/5/25
        String jobYearsStr = this.getResponseParaByKey("jobyears");
        List<Map<String, String>> jobYears = new ArrayList<Map<String, String>>();
        String[] jobYear = jobYearsStr.split(";");
        for (String jobStr : jobYear) {
            Map<String, String> jobMap = new HashMap<String, String>();
            String[] job = jobStr.split(",");
            jobMap.put("jobYearName", job[0]);
            jobMap.put("jobYearValue", job[1]);
            jobYears.add(jobMap);
        }
        Map<String, Object> respMap = new HashMap<String, Object>();
        respMap.put("jobYears", jobYears);
        return respMap;
    }

    /**
     * 修改我的资料
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object saveInformation(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        String name = ParseJson.parseToString(body, "name");
        Integer sex = ParseJson.parseToInt(body, "sex");
        String company = ParseJson.parseToString(body, "company");
        String companyCode = ParseJson.parseToString(body, "companyCode");
        String job = ParseJson.parseToString(body, "job");
        String jobCode = ParseJson.parseToString(body, "jobCode");
        String jobyeartext = ParseJson.parseToString(body, "jobyeartext");
        String yearSalary = ParseJson.parseToString(body, "yearSalary");
        Integer yearSalaryCode = ParseJson.parseToInt(body, "yearSalaryCode");
        List<String> honors = ParseJson.parseToList(body, "honors", String.class);
        try {
            UserCardReqDto userCardReq = new UserCardReqDto();
            userCardReq.setFid(userId.intValue());
            userCardReq.setName(name);
            userCardReq.setSex(sex);
            userCardReq.setCompany(company);
            userCardReq.setCompanyCode(companyCode);
            userCardReq.setJob(job);
            userCardReq.setJobCode(jobCode);
            userCardReq.setJobyeartext(jobyeartext);
            userCardReq.setYearSalary(yearSalary);
            userCardReq.setYearSalaryCode(yearSalaryCode);
            logger.info("调用保存用户荣誉信息-------------start!");
            if (honors != null) {
                userCardSupportService.saveUserHonors(userId, honors);
                logger.info("调用保存用户荣誉信息-------------end!");
            }
            userCardSupportService.saveUserCardBySelective(userCardReq);
            logger.info("调用保存用户信息-------------end!");
            return respMap;
        } catch (Exception ex) {
            logger.error("修改我的资料异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "修改我的资料异常!");
            return respMap;
        }
    }

    /**
     * 删除我的声音
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object deleteMyVoice(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        Long videoId = ParseJson.parseToLong(body, "videoId");
        if (videoId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "videoId is null!");
            logger.info("videoId is null!");
            return respMap;
        }
        try {
            int count = championUserService.deleteMyVoice(userId, videoId);
            if (count > 0) {
                return respMap;
            } else {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "删除失败!");
                return respMap;
            }
        } catch (Exception ex) {
            logger.error("删除我的声音异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "删除我的声音异常!");
            return respMap;
        }
    }

    /**
     * 保存讲师封面图片
     *
     * @param head
     * @param body
     * @param bytes
     * @return
     */
    @Override
    public Object saveUserCoverImg(String head, String body, byte[] bytes) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        if (bytes == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "file is null!");
            logger.info("bytes is null!");
            return respMap;
        }
        try {
            UserCardReqDto userCardReq = new UserCardReqDto();
            userCardReq.setFid(userId.intValue());
            userCardReq.setUserCoverByte(bytes);
            UserCardRespDto userCardResp = userCardSupportService.saveUserCardBySelective(userCardReq);
            if (userCardResp != null) {
                respMap.put("coverImgUrl", userCardResp.getCoverImageUrl());
                return respMap;
            } else {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "保存讲师封面图片失败!");
                return respMap;
            }
        } catch (Exception ex) {
            logger.error("保存讲师封面图片异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "保存讲师封面图片异常!");
            return respMap;
        }
    }

    /**
     * 保存用户语音文件
     *
     * @param head
     * @param body
     * @param bytes1
     * @param bytes2
     * @return
     */
    @Override
    public Object saveUserVoice(String head, String body, byte[] bytes1, byte[] bytes2) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        String os = ParseJson.parseToString(head, Constants.HEAD_JSON_OS);
        if (os == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "os is null!");
            logger.info("os is null!");
            return respMap;
        }
        Long userId = ParseJson.parseToLong(body, Constants.BODY_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        String title = ParseJson.parseToString(body, "title");
        if (StringUtils.isBlank(title)) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "title is null!");
            logger.info("title is null!");
            return respMap;
        }
        Long channelId = ParseJson.parseToLong(body, "channelId");
        if (channelId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "channelId is null!");
            logger.info("channelId is null!");
            return respMap;
        }
        String name = ParseJson.parseToString(body, "name");
        if (StringUtils.isBlank(name)) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "name is null!");
            logger.info("name is null!");
            return respMap;
        }
        Long videoSize = ParseJson.parseToLong(body, "videoSize");
        if (null == videoSize) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "videoSize is null!");
            logger.info("videoSize is null!");
            return respMap;
        }
        String durationTime = ParseJson.parseToString(body, "durationTime");
        if (StringUtils.isBlank(durationTime)) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "durationTime is null!");
            logger.info("durationTime is null!");
            return respMap;
        }
        if (bytes2 == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "file2 is null!");
            logger.info("bytes2 is null!");
            return respMap;
        }
        try {
            ChampionPublishMp3Bean publishMp3Bean = new ChampionPublishMp3Bean();
            publishMp3Bean.setUserId(userId.intValue());
            publishMp3Bean.setName(name);
            publishMp3Bean.setChannelId(channelId);
            publishMp3Bean.setSize(videoSize);
            publishMp3Bean.setTitle(title);
            publishMp3Bean.setDurationTime(durationTime);
            publishMp3Bean.setPicture(bytes1);
            publishMp3Bean.setFile(bytes2);
            ChampionVideoBean videoBean = null;
            if (Constants.HEAD_JSON_OS_ANDROID.equals(os)) {
                videoBean = championUserService.publishAndroidUserMp3(publishMp3Bean);
            } else {
                videoBean = championUserService.publishUserMp3(publishMp3Bean);
            }
            if (videoBean != null) {
                respMap.put("voiceUrl", videoBean.getVideoUrl());
                return respMap;
            } else {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "保存用户语音文件失败!");
                return respMap;
            }
        } catch (Exception ex) {
            logger.error("保存用户语音文件异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "保存用户语音文件异常!");
            return respMap;
        }
    }

    /**
     * 获取年薪
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getYearsSalarys(String head, String body) {
        // TODO: 2016/5/26 缓存处理
        String jobYearsStr = this.getResponseParaByKey("year_salarys");
        List<Map<String, String>> jobYears = new ArrayList<Map<String, String>>();
        String[] jobYear = jobYearsStr.split(";");
        for (String jobStr : jobYear) {
            Map<String, String> jobMap = new HashMap<String, String>();
            String[] job = jobStr.split(",");
            jobMap.put("yearSalarName", job[0]);
            jobMap.put("yearSalarValue", job[1]);
            jobYears.add(jobMap);
        }
        Map<String, Object> respMap = new HashMap<String, Object>();
        respMap.put("yearSalarys", jobYears);
        return respMap;
    }

    /**
     * 保存实名信息
     *
     * @param head
     * @param body
     * @param bytes1
     * @param bytes2
     * @return
     */
    @Override
    public Object saveRealInfo(String head, String body, byte[] bytes1, byte[] bytes2) {
        logger.info("saveRealInfo start ------------------");
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(head, Constants.HEAD_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        String realName = ParseJson.parseToString(body, "realName");
        if (StringUtils.isBlank(realName)) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "realName is null!");
            logger.info("realName is null!");
            return respMap;
        }
        String idCard = ParseJson.parseToString(body, "idCard");
        if (StringUtils.isBlank(idCard)) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "idCard is null!");
            logger.info("idCard is null!");
            return respMap;
        }
        if (bytes1 == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "file1 is null!");
            logger.info("bytes1 is null!");
            return respMap;
        }
        if (bytes2 == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "file2 is null!");
            logger.info("bytes2 is null!");
            return respMap;
        }
        try {
            logger.info("saveRealInfo end ------------------");
            UserRealInfoReqDto userRealInfoReq = new UserRealInfoReqDto();
            userRealInfoReq.setFid(userId.intValue());
            userRealInfoReq.setRealname(realName);
            userRealInfoReq.setIdCard(idCard);
            userRealInfoReq.setIdCardFrontByte(bytes1);
            userRealInfoReq.setIdCardReverseByte(bytes2);
            int count = userCardSupportService.saveUserRealInfo(userRealInfoReq);
            if (count > 0) {
                return respMap;
            } else {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "保存实名信息异常!");
                return respMap;
            }
        } catch (Exception ex) {
            logger.info("saveRealInfo Exception ------------------");
            logger.error("保存实名信息异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "保存实名信息异常!");
            return respMap;
        }
    }

    /**
     * 获取用户实名信息
     *
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getRealInfoByUserId(String head, String body) {
        logger.info("getRealInfoByUserId start ------------------");
        Map<String, Object> respMap = new HashMap<String, Object>();
        Long userId = ParseJson.parseToLong(head, Constants.HEAD_JSON_USER_ID);
        if (userId == null) {
            respMap.put(Constants.RESPONSE_ERROR_CODE, "userId is null!");
            logger.info("userId is null!");
            return respMap;
        }
        try {
            UserRealInfoRespDto realInfoResp = userCardSupportService.getUserRealInfoByUserId(userId);
            respMap.put("userRealInfo", realInfoResp);
            logger.info("getRealInfoByUserId end ------------------");
            return respMap;
        } catch (Exception ex) {
            logger.info("getRealInfoByUserId Exception ------------------");
            logger.error("获取用户实名信息异常：{}", ex.getMessage());
            ex.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "获取用户实名信息异常!");
            return respMap;
        }
    }

    /**
     * 查询我的配置信息
     *
     * @param group
     * @return
     */
    private List<MyConfRespDto> getBxjMyConfByGroup(Integer group, Integer confType) {
        BxjMyConf conditions = new BxjMyConf();
        conditions.setGroupType(group);
        conditions.setIsDisplay(true);
        conditions.setConfType(confType);
        conditions.addOrderData(new OrderData(OrderConstants.BXJ_MY_CONF_SORT, OrderMethod.ASC));
        List<BxjMyConf> myConfs = myConfMapper.selectBySelective(conditions);
        if (myConfs != null && !myConfs.isEmpty()) {
            List<MyConfRespDto> myConfResps = new ArrayList<MyConfRespDto>();
            for (BxjMyConf myConf : myConfs) {
                MyConfRespDto myConfResp = new MyConfRespDto();
                BeanUtils.copyProperties(myConf, myConfResp);
                myConfResps.add(myConfResp);
            }
            return myConfResps;
        }
        return null;
    }

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
}
