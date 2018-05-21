package com.hangjia.bxj.service.impl;

import com.baobao.framework.support.utility.StringUtils;
import com.baobao.framework.utils.DateFormatUtils;
import com.baobao.sso.client.UserCardRespDto;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.common.Constants;
import com.hangjia.bxj.common.DateUtil;
import com.hangjia.bxj.common.Pagination;
import com.hangjia.bxj.common.ParseJson;
import com.hangjia.bxj.dao.ControlAppStoreDao;
import com.hangjia.bxj.dao.firendcircle.FriendCircleMapper;
import com.hangjia.bxj.dao.HomeConfMapper;
import com.hangjia.bxj.dao.firendcircle.FriendCircleCategoryMapper;
import com.hangjia.bxj.dao.firendcircle.FriendCircleStatisticsMapper;
import com.hangjia.bxj.dao.firendcircle.FriendCircleWeekRankMapper;
import com.hangjia.bxj.model.BxjResponse;
import com.hangjia.bxj.model.firendcircle.FriendCircle;
import com.hangjia.bxj.model.HomeConf;
import com.hangjia.bxj.model.firendcircle.FriendCircleCategory;
import com.hangjia.bxj.model.firendcircle.FriendCircleStatistics;
import com.hangjia.bxj.model.firendcircle.FriendCircleWeekRank;
import com.hangjia.bxj.service.BxjToolsService;
import com.hangjia.bxj.util.VersionUtil;
import com.hangjia.bxj.vo.FriendCircleApiDto;
import com.hangjia.bxj.vo.FriendCircleIndexVo;
import com.hangjia.bxj.vo.FriendCircleVo;
import com.hangjia.bxj.vo.FriendCircleVo_v32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BXJ工具service
 * 朋友圈
 */
public class BxjToolsServiceImpl implements BxjToolsService {
    private static Logger logger = LoggerFactory.getLogger(BxjToolsServiceImpl.class);
    @Value("${static_path}")
    private String staticPath;

    @Autowired
    FriendCircleMapper friendCircleMapper;
    @Autowired
    HomeConfMapper homeConfMapper;
    @Autowired
    FriendCircleCategoryMapper friendCircleCategoryMapper;
    @Autowired
    FriendCircleStatisticsMapper friendCircleStatisticsMapper;
    @Autowired
    FriendCircleWeekRankMapper friendCircleWeekRankMapper;
    @Autowired
    ControlAppStoreDao appStoreDao;
    @Autowired
    private UserCardSupportService userCardSupportService;

    /**
     * 朋友圈首页数据
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getFirendCircleIndex(String head, String body) {
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE)==null?Constants.DEFAULT_CURRENT_PAGE:ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE)==null?Constants.DEFAULT_PAGESIZE_3:ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        FriendCircle friendCircle = new FriendCircle();
        friendCircle.setCurrPage(currPage);
        friendCircle.setPageSize(pageSize);
        friendCircle.setPublishTime(new Date());
        int count = friendCircleMapper.selectIndexCount(friendCircle);
        List<FriendCircle> friendCircles = friendCircleMapper.selectIndexByPage(friendCircle);
        List<FriendCircleIndexVo> list = new ArrayList<FriendCircleIndexVo>();
        List<FriendCircleVo> friendCircleVos = null;
        FriendCircleIndexVo indexVo = null;
        String defaultDate = "";
        if(friendCircle!=null && friendCircles.size()>0) {
            int index = 0;
            for(FriendCircle obj : friendCircles) {
                index ++;
                String date = DateFormatUtils.format(Constants.DATA_FORMAT_YYYYMMDD, obj.getPublishTime());
                if("".equals(defaultDate)) {
                    defaultDate = date;
                    friendCircleVos = new ArrayList<FriendCircleVo>();
                    indexVo = new FriendCircleIndexVo();
                    indexVo.setDateTitle(DateFormatUtils.format(Constants.DATA_FORMAT_YYYYMMDD, obj.getPublishTime()));
                }
                if(!defaultDate.equals(date) || index==friendCircles.size()) {
                    indexVo.setList(friendCircleVos);
                    list.add(indexVo);
                    defaultDate = date;
                    indexVo = new FriendCircleIndexVo();
                    indexVo.setDateTitle(DateFormatUtils.format(Constants.DATA_FORMAT_YYYYMMDD, obj.getPublishTime()));
                    friendCircleVos = new ArrayList<FriendCircleVo>();
                }

                FriendCircleVo vo = new FriendCircleVo();
                List<String> pics = new ArrayList<String>();
                if (null != obj.getPic1() && !"".equals(obj.getPic1())) {
                    obj.setPic1(staticPath + obj.getPic1());
                    pics.add(obj.getPic1());
                }
                if (null != obj.getPic2() && !"".equals(obj.getPic2())) {
                    obj.setPic2(staticPath + obj.getPic2());
                    pics.add(obj.getPic2());
                }
                if (null != obj.getPic3() && !"".equals(obj.getPic3())) {
                    obj.setPic3(staticPath + obj.getPic3());
                    pics.add(obj.getPic3());
                }
                if (null != obj.getPic4() && !"".equals(obj.getPic4())) {
                    obj.setPic4(staticPath + obj.getPic4());
                    pics.add(obj.getPic4());
                }
                if (null != obj.getPic5() && !"".equals(obj.getPic5())) {
                    obj.setPic5(staticPath + obj.getPic5());
                    pics.add(obj.getPic5());
                }
                if (null != obj.getPic6() && !"".equals(obj.getPic6())) {
                    obj.setPic6(staticPath + obj.getPic6());
                    pics.add(obj.getPic6());
                }
                if (null != obj.getPic7() && !"".equals(obj.getPic7())) {
                    obj.setPic7(staticPath + obj.getPic7());
                    pics.add(obj.getPic7());
                }
                if (null != obj.getPic8() && !"".equals(obj.getPic8())) {
                    obj.setPic8(staticPath + obj.getPic8());
                    pics.add(obj.getPic8());
                }
                if (null != obj.getPic9() && !"".equals(obj.getPic9())) {
                    obj.setPic9(staticPath + obj.getPic9());
                    pics.add(obj.getPic9());
                }
                BeanUtils.copyProperties(obj, vo);
                if(null != obj.getContent() && !"".equals(obj.getContent()))vo.setContent(delHTMLTag(obj.getContent()));
                vo.setPics(pics);
                friendCircleVos.add(vo);
            }
        }
        return new Pagination<FriendCircleIndexVo>(count, currPage, pageSize, list);
    }

    /**
     * 朋友圈首页数据
     * 个性化推荐逻辑
     * 1.默认显示没有配置保险公司的朋友圈信息
     * 2.如果配置了当前保险公司的信息且当前信息只针对该公司的无通配，则当前信息追加到列表最后
     * 3.如果有通配信息则匹配到保险公司的显示该保险公司配置，其他显示通配父级
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getFirendCircleIndex_v32(String head, String body) {
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE)==null?Constants.DEFAULT_CURRENT_PAGE:ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE)==null?Constants.DEFAULT_PAGESIZE_3:ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        Long userId = ParseJson.parseToLong(head, Constants.BODY_JSON_USER_ID);
        String categoryId = ParseJson.parseToString(body, Constants.BODY_JSON_CATEGORY_ID);
        FriendCircle friendCircle = new FriendCircle();
        friendCircle.setCurrPage(currPage);
        friendCircle.setPageSize(pageSize);
        friendCircle.setPublishTime(new Date());
        if(null != categoryId) friendCircle.setCategoryId(Long.valueOf(categoryId));
        friendCircle.setPublishTime(new Date());
        int count = friendCircleMapper.selectIndexCount(friendCircle);
        List<FriendCircle> friendCirclesTemp = friendCircleMapper.selectIndexByPage_v32(friendCircle);
        UserCardRespDto userCardRespDto = userCardSupportService.getUserBaseInfoByUserId(userId.intValue());
        friendCircle.setCompany(userCardRespDto.getCompany());
        List<FriendCircle> friendCirclesCompany = friendCircleMapper.selectIndexByPage_company(friendCircle);//查询匹配了保险公司的朋友圈信息
        List<FriendCircle> friendCircles = new ArrayList<FriendCircle>();
        friendCircles.addAll(friendCirclesTemp);

        for(FriendCircle f1 :friendCirclesCompany) {
            boolean flag = false;
            for(FriendCircle f2 :friendCirclesTemp) {
                if(f1.getPfid() != null && (f1.getPfid().longValue()==f2.getFid().longValue())) {
                    flag = true;
                }
            }
            if(!flag) friendCircles.add(f1);
        }

        int num = 0;
        for(FriendCircle f1 :friendCirclesTemp) {
            for(FriendCircle f2 :friendCirclesCompany) {
                if(f2.getPfid()==null) break;
                if(f1.getFid().longValue()==f2.getPfid().longValue()) {
                    friendCircles.remove(num);
                    friendCircles.add(num, f2);
                }
            }
            num++;
        }


        List<FriendCircleVo_v32> list = new ArrayList<FriendCircleVo_v32>();
        if(friendCircle!=null && friendCircles.size()>0) {
            for(FriendCircle obj : friendCircles) {
                FriendCircleVo_v32 vo = new FriendCircleVo_v32();
                List<String> pics = new ArrayList<String>();
                if (null != obj.getPic1() && !"".equals(obj.getPic1())) {
                    obj.setPic1(staticPath + obj.getPic1());
                    pics.add(obj.getPic1());
                }
                if (null != obj.getPic2() && !"".equals(obj.getPic2())) {
                    obj.setPic2(staticPath + obj.getPic2());
                    pics.add(obj.getPic2());
                }
                if (null != obj.getPic3() && !"".equals(obj.getPic3())) {
                    obj.setPic3(staticPath + obj.getPic3());
                    pics.add(obj.getPic3());
                }
                if (null != obj.getPic4() && !"".equals(obj.getPic4())) {
                    obj.setPic4(staticPath + obj.getPic4());
                    pics.add(obj.getPic4());
                }
                if (null != obj.getPic5() && !"".equals(obj.getPic5())) {
                    obj.setPic5(staticPath + obj.getPic5());
                    pics.add(obj.getPic5());
                }
                if (null != obj.getPic6() && !"".equals(obj.getPic6())) {
                    obj.setPic6(staticPath + obj.getPic6());
                    pics.add(obj.getPic6());
                }
                if (null != obj.getPic7() && !"".equals(obj.getPic7())) {
                    obj.setPic7(staticPath + obj.getPic7());
                    pics.add(obj.getPic7());
                }
                if (null != obj.getPic8() && !"".equals(obj.getPic8())) {
                    obj.setPic8(staticPath + obj.getPic8());
                    pics.add(obj.getPic8());
                }
                if (null != obj.getPic9() && !"".equals(obj.getPic9())) {
                    obj.setPic9(staticPath + obj.getPic9());
                    pics.add(obj.getPic9());
                }
                obj.setCategoryPic(staticPath + obj.getCategoryPic());
                BeanUtils.copyProperties(obj, vo);
                if(null != obj.getContent() && !"".equals(obj.getContent()))vo.setContent(delHTMLTag(obj.getContent()));
                vo.setPics(pics);
                vo.setTimeStr(DateUtil.getTimeContext(obj.getPublishTime(), new Date()));
//                vo.setMonth(DateUtil.getMonthFormatMap().get(String.valueOf(obj.getPublishTime().getMonth())));
                String day = DateUtil.getDate(obj.getPublishTime())+"";
                if(day.length() == 1) {
                    day = "0"+day;
                }
                vo.setMonth(String.valueOf(obj.getPublishTime().getMonth()+1)+"月");
                if(DateUtil.formatSdf9(new Date()).equals(DateUtil.formatSdf9(obj.getPublishTime()))) vo.setDay("今天");
                else vo.setDay(day);
                friendIsLike(vo, userId);
                list.add(vo);
            }
        }
        return new Pagination<FriendCircleVo_v32>(count, currPage, pageSize, list);
    }

    /**
     * 20170614
     * 朋友圈首页数据
     * 个性化推荐逻辑
     * 1.默认显示没有配置保险公司的朋友圈信息
     * 2.如果配置了当前保险公司的信息且当前信息只针对该公司的无通配，则当前信息追加到列表最后
     * 3.如果有通配信息则匹配到保险公司的显示该保险公司配置，其他显示通配父级
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object getFirendCircleIndex_v32_1(String head, String body) {

        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE)==null?Constants.DEFAULT_CURRENT_PAGE:ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE)==null?Constants.DEFAULT_PAGESIZE_3:ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        Long userId = ParseJson.parseToLong(head, Constants.BODY_JSON_USER_ID);
        String categoryId = ParseJson.parseToString(body, Constants.BODY_JSON_CATEGORY_ID);
        String company = ParseJson.parseToString(body, "company");
        FriendCircle friendCircle = new FriendCircle();
        friendCircle.setCurrPage(currPage);
        friendCircle.setPageSize(pageSize);
        friendCircle.setPublishTime(new Date());
        if(null != categoryId && !categoryId.equals("0")) friendCircle.setCategoryId(Long.valueOf(categoryId));
        friendCircle.setPublishTime(new Date());
        int count = friendCircleMapper.selectIndexCount(friendCircle);
        List<FriendCircle> friendCirclesTemp = friendCircleMapper.selectIndexByPage_v32(friendCircle);
//        UserCardRespDto userCardRespDto = userCardSupportService.getUserBaseInfoByUserId(userId.intValue());
//        friendCircle.setCompany(userCardRespDto.getCompany());
        friendCircle.setCompany(company);

        List<FriendCircle> friendCirclesCompany = friendCircleMapper.selectIndexByPage_company(friendCircle);//查询匹配了保险公司的朋友圈信息
        List<FriendCircle> friendCircles = new ArrayList<FriendCircle>();
        friendCircles.addAll(friendCirclesTemp);

        for(FriendCircle f1 :friendCirclesCompany) {
            boolean flag = false;
            for(FriendCircle f2 :friendCirclesTemp) {
                if(f1.getPfid() != null && (f1.getPfid().longValue()==f2.getFid().longValue())) {
                    flag = true;
                }
            }
            if(!flag) friendCircles.add(f1);
        }

        int num = 0;
        for(FriendCircle f1 :friendCirclesTemp) {
            for(FriendCircle f2 :friendCirclesCompany) {
                if(f2.getPfid()==null) break;
                if(f1.getFid().longValue()==f2.getPfid().longValue()) {
                    friendCircles.remove(num);
                    friendCircles.add(num, f2);
                }
            }
            num++;
        }


        List<FriendCircleVo_v32> list = new ArrayList<FriendCircleVo_v32>();
        if(friendCircle!=null && friendCircles.size()>0) {
            for(FriendCircle obj : friendCircles) {
                FriendCircleVo_v32 vo = new FriendCircleVo_v32();
                List<String> pics = new ArrayList<String>();
                if (null != obj.getPic1() && !"".equals(obj.getPic1())) {
                    obj.setPic1(staticPath + obj.getPic1());
                    pics.add(obj.getPic1());
                }
                if (null != obj.getPic2() && !"".equals(obj.getPic2())) {
                    obj.setPic2(staticPath + obj.getPic2());
                    pics.add(obj.getPic2());
                }
                if (null != obj.getPic3() && !"".equals(obj.getPic3())) {
                    obj.setPic3(staticPath + obj.getPic3());
                    pics.add(obj.getPic3());
                }
                if (null != obj.getPic4() && !"".equals(obj.getPic4())) {
                    obj.setPic4(staticPath + obj.getPic4());
                    pics.add(obj.getPic4());
                }
                if (null != obj.getPic5() && !"".equals(obj.getPic5())) {
                    obj.setPic5(staticPath + obj.getPic5());
                    pics.add(obj.getPic5());
                }
                if (null != obj.getPic6() && !"".equals(obj.getPic6())) {
                    obj.setPic6(staticPath + obj.getPic6());
                    pics.add(obj.getPic6());
                }
                if (null != obj.getPic7() && !"".equals(obj.getPic7())) {
                    obj.setPic7(staticPath + obj.getPic7());
                    pics.add(obj.getPic7());
                }
                if (null != obj.getPic8() && !"".equals(obj.getPic8())) {
                    obj.setPic8(staticPath + obj.getPic8());
                    pics.add(obj.getPic8());
                }
                if (null != obj.getPic9() && !"".equals(obj.getPic9())) {
                    obj.setPic9(staticPath + obj.getPic9());
                    pics.add(obj.getPic9());
                }
                obj.setCategoryPic(staticPath + obj.getCategoryPic());
                BeanUtils.copyProperties(obj, vo);
                if(null != obj.getContent() && !"".equals(obj.getContent()))vo.setContent(delHTMLTag(obj.getContent()));
                vo.setPics(pics);
                vo.setTimeStr(DateUtil.getTimeContext(obj.getPublishTime(), new Date()));
//                vo.setMonth(DateUtil.getMonthFormatMap().get(String.valueOf(obj.getPublishTime().getMonth())));
                String day = DateUtil.getDate(obj.getPublishTime())+"";
                if(day.length() == 1) {
                    day = "0"+day;
                }
                vo.setMonth(String.valueOf(obj.getPublishTime().getMonth()+1)+"月");
                if(DateUtil.formatSdf9(new Date()).equals(DateUtil.formatSdf9(obj.getPublishTime()))) vo.setDay("今天");
                else vo.setDay(day);
                friendIsLike(vo, userId);
                list.add(vo);
            }
        }
        return new Pagination<FriendCircleVo_v32>(count, currPage, pageSize, list);
    }


    /**
     * 更新分享数
     * @param head
     * @param body
     * @return
     */
    public Object updateShareCount(String head, String body) {
        logger.info("更新分享数 start");
        Map<String, Object> respMap = new HashMap<String, Object>();
        try {
            Long id = ParseJson.parseToLong(body, Constants.BODY_JSON_ID);
            Long userId = ParseJson.parseToLong(head, Constants.BODY_JSON_USER_ID);
            if (id == null) {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "id is null!");
                logger.info("updateShareCount:id is null!");
                return respMap;
            }

            FriendCircle friendCircle = friendCircleMapper.selectByPrimaryKey(id);
            if (friendCircle == null) {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "文章不存在！");
                logger.info("文章不存在！");
                return respMap;
            }

            String versionCode = ParseJson.parseToString(head, Constants.HEAD_JSON_VERSION_CODE);
            String os = ParseJson.parseToString(head, Constants.HEAD_JSON_OS);
            int vCode = Integer.parseInt(versionCode);
            Double version = VersionUtil.getVersion(os, vCode);
            logger.info("更新分享数 currentVersion:{}", version);
            if (version < 3.4) {
                // 更新分享次数
                friendCircle.setShareCount(friendCircle.getShareCount() + 1);
                friendCircle.setWeekShareCount(friendCircle.getWeekShareCount() + 1);
                friendCircle.setShareCountReal(friendCircle.getShareCountReal() + 1);
                friendCircle.setWeekShareCountReal(friendCircle.getWeekShareCountReal() + 1);
                int count = friendCircleMapper.updateByPrimaryKey(friendCircle);
                respMap.put("success", count);
            } else {
                // 获取用户已分享次数
                // 查询当前用户当日分享次数
                if (userId != null && 0!=userId) {
                    int shareCount = this.getTodayUserShareCountFormDb(userId, id);
                    if (shareCount < 5){
                        BxjResponse response = appStoreDao.selectAppStoreById("default_friend_share");
                        int fold = 1;
                        if (response != null) {
                            if (StringUtils.isNotBlank(response.getContent()))
                                fold = Integer.valueOf(response.getContent());
                        }
                        // 更新分享次数
                        friendCircle.setShareCount(friendCircle.getShareCount() + fold);
                        friendCircle.setWeekShareCount(friendCircle.getWeekShareCount() + fold);
                        friendCircle.setShareCountReal(friendCircle.getShareCountReal() + 1);
                        friendCircle.setWeekShareCountReal(friendCircle.getWeekShareCountReal() + 1);
                        int count = friendCircleMapper.updateByPrimaryKey(friendCircle);

                        respMap.put("success", count);
                    }else {
                        respMap.put("success", 0);
                    }
                }else {
                    respMap.put("success", 0);
                }
            }

            // 添加分享log
            FriendCircleStatistics circleStatistics = new FriendCircleStatistics();
            circleStatistics.setCreateTime(new Date());
            circleStatistics.setFriendCircleId(id);
            circleStatistics.setUserId(userId);
            circleStatistics.setType(1);
            friendCircleStatisticsMapper.insertSelective(circleStatistics);
            logger.info("更新分享数 end");
            //calWeekRank(id);
        } catch (Exception e) {
            logger.info("更新分享数异常，异常原因：{}", e.toString());
            e.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "updateShareCount error!");
        }
        return respMap;
    }

    /**
     * 计算周排行
     * @param id
     */
    public void calWeekRank(Long id) {
        String monday = getMonday();
        String tableName = Constants.FRIEND_CIRCLE_WEEK_RANK + monday;
        FriendCircleWeekRank weekRank = new FriendCircleWeekRank();
        weekRank.setFriendCircleId(id);
        weekRank.setMonday(monday);
        weekRank.setTableName(tableName);
        int count = friendCircleWeekRankMapper.selectTableCount(weekRank);
        // 不存在则创建表
        if(count < 1) {
            friendCircleWeekRankMapper.createNewTable(weekRank);
        }
        // 更新分享次数
        List<FriendCircleWeekRank> list = friendCircleWeekRankMapper.selectByPage(weekRank);
        if(null==list || list.size()==0) {
            weekRank.setCount(1);
            friendCircleWeekRankMapper.insertSelectiveByTable(weekRank);
        } else {
            weekRank = list.get(0);
            weekRank.setCount(weekRank.getCount() + 1);
            weekRank.setTableName(tableName);
            friendCircleWeekRankMapper.updateByPrimaryKeySelectiveByTable(weekRank);
        }
    }

    /**
     * 获取今天日期的星期一
     * @return
     */
    public String getMonday() {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return sdf.format(cal.getTime());
    }

    public Object bannerPics(String head, String body) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        Date now = new Date();
        String version = ParseJson.parseToString(head, Constants.HEAD_JSON_VERSION);
        List<HomeConf> bannerList = homeConfMapper.getFriendBanner(null);
        List<FriendCircleApiDto> rBannerList = new ArrayList<FriendCircleApiDto>();
        for(HomeConf conf : bannerList) {
            if (StringUtils.isNotBlank(version)) {
                String[] vArr = version.split("[.]");
                if(vArr.length >=3) version = vArr[0]+"."+vArr[1]+vArr[2];
                logger.info("version:"+version);
                Double vv = Double.valueOf(version);
                //当前版本小于配置版本
                if(StringUtils.isNotBlank(conf.getMinVersion())) {
                    if(vv.compareTo(conf.getMinVersion()) < 0) continue;
                }
                //当前版本大于配置版本
                if(StringUtils.isNotBlank(conf.getMaxVersion())) {
                    if(vv.compareTo(conf.getMaxVersion()) > 0) continue;
                }
            }
            //当前时间小于上线时间
            if(StringUtils.isNotBlank(conf.getOnlineTime())) {
                if(now.compareTo(conf.getOnlineTime()) < 0) continue;
            }

            //当前时间大于于下线时间
            if(StringUtils.isNotBlank(conf.getOfflineTime())) {
                if(now.compareTo(conf.getOfflineTime()) > 0) continue;
            }
            FriendCircleApiDto friendCircleApiDto = new FriendCircleApiDto();
            BeanUtils.copyProperties(conf ,friendCircleApiDto);
            rBannerList.add(friendCircleApiDto);
        }

        rMap.put("total", rBannerList.size());
        rMap.put("list", rBannerList);
        return rMap;
    }


    /**
     * 如果是首页获取的分类
     * pageOffset=0,其他为空
     * @param head
     * @param body
     * @return
     */
    public Object rankCategory(String head, String body) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        List<FriendCircleCategory> friendCircleCategoryList = friendCircleCategoryMapper.selectCategoryList(null);
        List<FriendCircleCategory> rriendCircleCategoryList = new ArrayList<FriendCircleCategory>();
        FriendCircleCategory all = new FriendCircleCategory();
        all.setSimtitle("全部");
        all.setFid(0l);
        all.setTitle("全部");
        all.setWords("全部");
        rriendCircleCategoryList.add(all);
        for(FriendCircleCategory fc : friendCircleCategoryList) {
            FriendCircleCategory temp = new FriendCircleCategory();
            BeanUtils.copyProperties(fc,temp);
            if(StringUtils.isNotBlank(fc.getPic()))
                temp.setPic(staticPath + fc.getPic());
            rriendCircleCategoryList.add(temp);
        }

        rMap.put("list", rriendCircleCategoryList);
        return rMap;
    }


    public Object rankCategoryByQuery(String head, String body) {
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE)==null?Constants.DEFAULT_CURRENT_PAGE:ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE)==null?Constants.DEFAULT_PAGESIZE_3:ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        String categoryId = ParseJson.parseToString(body, Constants.BODY_JSON_CATEGORY_ID);
        String type = ParseJson.parseToString(body, Constants.BODY_JSON_TYPE);
        Long userId = ParseJson.parseToLong(head, Constants.BODY_JSON_USER_ID);
        FriendCircle friendCircle = new FriendCircle();
        friendCircle.setCurrPage(currPage);
        friendCircle.setPageSize(pageSize);
        friendCircle.setPublishTime(new Date());
        if(null != categoryId) friendCircle.setCategoryId(Long.valueOf(categoryId));
        if("1".equals(type)) {
            friendCircle.setType(1);
        }
        int count = friendCircleMapper.selectRankCategoryCount(friendCircle);
        if(count>100) count=100;

        List<FriendCircle> friendCircles = friendCircleMapper.selectRankCategory(friendCircle);
        List<FriendCircleVo_v32> list = new ArrayList<FriendCircleVo_v32>();
        if(friendCircle!=null && friendCircles.size()>0) {
            for(FriendCircle obj : friendCircles) {
                FriendCircleVo_v32 vo = new FriendCircleVo_v32();
                List<String> pics = new ArrayList<String>();
                if (null != obj.getPic1() && !"".equals(obj.getPic1())) {
                    obj.setPic1(staticPath + obj.getPic1());
                    pics.add(obj.getPic1());
                }
                if (null != obj.getPic2() && !"".equals(obj.getPic2())) {
                    obj.setPic2(staticPath + obj.getPic2());
                    pics.add(obj.getPic2());
                }
                if (null != obj.getPic3() && !"".equals(obj.getPic3())) {
                    obj.setPic3(staticPath + obj.getPic3());
                    pics.add(obj.getPic3());
                }
                if (null != obj.getPic4() && !"".equals(obj.getPic4())) {
                    obj.setPic4(staticPath + obj.getPic4());
                    pics.add(obj.getPic4());
                }
                if (null != obj.getPic5() && !"".equals(obj.getPic5())) {
                    obj.setPic5(staticPath + obj.getPic5());
                    pics.add(obj.getPic5());
                }
                if (null != obj.getPic6() && !"".equals(obj.getPic6())) {
                    obj.setPic6(staticPath + obj.getPic6());
                    pics.add(obj.getPic6());
                }
                if (null != obj.getPic7() && !"".equals(obj.getPic7())) {
                    obj.setPic7(staticPath + obj.getPic7());
                    pics.add(obj.getPic7());
                }
                if (null != obj.getPic8() && !"".equals(obj.getPic8())) {
                    obj.setPic8(staticPath + obj.getPic8());
                    pics.add(obj.getPic8());
                }
                if (null != obj.getPic9() && !"".equals(obj.getPic9())) {
                    obj.setPic9(staticPath + obj.getPic9());
                    pics.add(obj.getPic9());
                }
                obj.setCategoryPic(staticPath + obj.getCategoryPic());
                BeanUtils.copyProperties(obj, vo);
                if("1".equals(type)) {
                    vo.setShareCount(obj.getWeekShareCount());
                }
                if(null != obj.getContent() && !"".equals(obj.getContent()))vo.setContent(delHTMLTag(obj.getContent()));
                vo.setPics(pics);
                vo.setTimeStr(DateUtil.getTimeContext(obj.getPublishTime(), new Date()));
                //vo.setMonth(DateUtil.getMonthFormatMap().get(String.valueOf(obj.getPublishTime().getMonth())));
                vo.setMonth(String.valueOf(obj.getPublishTime().getMonth()+1)+"月");
                String day = DateUtil.getDate(obj.getPublishTime())+"";
                if(day.length() == 1) {
                    day = "0"+day;
                }
                if(DateUtil.formatSdf9(new Date()).equals(DateUtil.formatSdf9(obj.getPublishTime()))) vo.setDay("今天");
                else vo.setDay(day);
                friendIsLike(vo, userId);
                list.add(vo);
            }
        }
        return new Pagination<FriendCircleVo_v32>(count, currPage, pageSize, list);
    }


    /**
     * 获取排行榜分类内容排序按时间
     * @param head
     * @param body
     * @return
     */
    public Object rankCategoryByQueryByTime(String head, String body) {
        Integer currPage = ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE)==null?Constants.DEFAULT_CURRENT_PAGE:ParseJson.parseToInt(body, Constants.BODY_JSON_CURRPAGE);
        Integer pageSize = ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE)==null?Constants.DEFAULT_PAGESIZE_3:ParseJson.parseToInt(body, Constants.BODY_JSON_PAGESIZE);
        String categoryId = ParseJson.parseToString(body, Constants.BODY_JSON_CATEGORY_ID);
        String type = ParseJson.parseToString(body, Constants.BODY_JSON_TYPE);
        Long userId = ParseJson.parseToLong(head, Constants.BODY_JSON_USER_ID);
        FriendCircle friendCircle = new FriendCircle();
        friendCircle.setCurrPage(currPage);
        friendCircle.setPageSize(pageSize);
        friendCircle.setPublishTime(new Date());
        if(null != categoryId) friendCircle.setCategoryId(Long.valueOf(categoryId));
        if("1".equals(type)) {
            friendCircle.setType(1);
        }
        int count = friendCircleMapper.selectRankCategoryCount(friendCircle);
        if(count>100) count=100;

        List<FriendCircle> friendCircles = friendCircleMapper.selectRankCategoryOrderByTime(friendCircle);
        List<FriendCircleVo_v32> list = new ArrayList<FriendCircleVo_v32>();
        if(friendCircle!=null && friendCircles.size()>0) {
            for(FriendCircle obj : friendCircles) {
                FriendCircleVo_v32 vo = new FriendCircleVo_v32();
                List<String> pics = new ArrayList<String>();
                if (null != obj.getPic1() && !"".equals(obj.getPic1())) {
                    obj.setPic1(staticPath + obj.getPic1());
                    pics.add(obj.getPic1());
                }
                if (null != obj.getPic2() && !"".equals(obj.getPic2())) {
                    obj.setPic2(staticPath + obj.getPic2());
                    pics.add(obj.getPic2());
                }
                if (null != obj.getPic3() && !"".equals(obj.getPic3())) {
                    obj.setPic3(staticPath + obj.getPic3());
                    pics.add(obj.getPic3());
                }
                if (null != obj.getPic4() && !"".equals(obj.getPic4())) {
                    obj.setPic4(staticPath + obj.getPic4());
                    pics.add(obj.getPic4());
                }
                if (null != obj.getPic5() && !"".equals(obj.getPic5())) {
                    obj.setPic5(staticPath + obj.getPic5());
                    pics.add(obj.getPic5());
                }
                if (null != obj.getPic6() && !"".equals(obj.getPic6())) {
                    obj.setPic6(staticPath + obj.getPic6());
                    pics.add(obj.getPic6());
                }
                if (null != obj.getPic7() && !"".equals(obj.getPic7())) {
                    obj.setPic7(staticPath + obj.getPic7());
                    pics.add(obj.getPic7());
                }
                if (null != obj.getPic8() && !"".equals(obj.getPic8())) {
                    obj.setPic8(staticPath + obj.getPic8());
                    pics.add(obj.getPic8());
                }
                if (null != obj.getPic9() && !"".equals(obj.getPic9())) {
                    obj.setPic9(staticPath + obj.getPic9());
                    pics.add(obj.getPic9());
                }
                obj.setCategoryPic(staticPath + obj.getCategoryPic());
                BeanUtils.copyProperties(obj, vo);
                if("1".equals(type)) {
                    vo.setShareCount(obj.getWeekShareCount());
                }
                if(null != obj.getContent() && !"".equals(obj.getContent()))vo.setContent(delHTMLTag(obj.getContent()));
                vo.setPics(pics);
                vo.setTimeStr(DateUtil.getTimeContext(obj.getPublishTime(), new Date()));
                //vo.setMonth(DateUtil.getMonthFormatMap().get(String.valueOf(obj.getPublishTime().getMonth())));
                vo.setMonth(String.valueOf(obj.getPublishTime().getMonth()+1)+"月");
                String day = DateUtil.getDate(obj.getPublishTime())+"";
                if(day.length() == 1) {
                    day = "0"+day;
                }
                if(DateUtil.formatSdf9(new Date()).equals(DateUtil.formatSdf9(obj.getPublishTime()))) vo.setDay("今天");
                else vo.setDay(day);
                friendIsLike(vo, userId);
                list.add(vo);
            }
        }
        return new Pagination<FriendCircleVo_v32>(count, currPage, pageSize, list);
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(DateUtil.getDate(new Date())));
    }

    /**
     * 删除html标签
     * @param content
     * @return
     */
    public String delHTMLTag(String content) {
        /** 定义script的正则表达式 **/
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>";
        /** 定义style的正则表达式 **/
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>";
        /** 定义HTML标签的正则表达式 **/
        String regEx_html="<[^>]+>";
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(content);
        content=m_script.replaceAll("");
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(content);
        content=m_style.replaceAll("");
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(content);
        content=m_html.replaceAll("");
        /**过滤非汉字非字母非数字**/
        //String regEx_ = "[^\\dA-Za-z\\u3007\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uE815-\\uE864]";
        String regEx_ = "\\t";
        Pattern p = Pattern.compile(regEx_,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        content = m.replaceAll("");
        int len = content.length()>100?100:content.length();
        return content.substring(0, len);
    }

    /**
     * 朋友圈人气排行
     * @return
     */
    public Object peopleRank() {
        return null;
    }

    /**
     * 从数据库获取用户当日分享数
     *
     * @param userId
     * @param circleId
     * @return
     */
    public int getTodayUserShareCountFormDb(Long userId, Long circleId) {
        FriendCircleStatistics conditions = new FriendCircleStatistics();
        conditions.setUserId(userId);
        conditions.setFriendCircleId(circleId);
        conditions.setCreateTime(new Date());
        return friendCircleStatisticsMapper.selectBySelectiveCount(conditions);
    }

    /**
     * 文章是否喜欢
     * @param vo
     * @param userId
     */
    public void friendIsLike(FriendCircleVo_v32 vo, Long userId) {
        vo.setIsLike(false);
        if(0 != userId) {
            FriendCircleStatistics circleStatistics = new FriendCircleStatistics();
            circleStatistics.setUserId(userId);
            circleStatistics.setType(2);
            circleStatistics.setFriendCircleId(vo.getFid());
            int count = friendCircleStatisticsMapper.selectCount(circleStatistics);
            if (count > 0) vo.setIsLike(true);
        }
    }

    /**
     * 更新喜欢数
     * @param head
     * @param body
     * @return
     */
    @Override
    public Object updateLikeCount(String head, String body) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        try {
            Long id = ParseJson.parseToLong(body, Constants.BODY_JSON_ID);
            Long userId = ParseJson.parseToLong(head, Constants.BODY_JSON_USER_ID);
            if (id == null) {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "id is null!");
                logger.info("updateLikeCount:id is null!");
                return respMap;
            }

            FriendCircle friendCircle = friendCircleMapper.selectByPrimaryKey(id);
            if (friendCircle == null) {
                respMap.put(Constants.RESPONSE_ERROR_CODE, "文章不存在！");
                logger.info("文章不存在！");
                return respMap;
            }

            if (userId != null && 0!= userId) {
                FriendCircleStatistics statistics = new FriendCircleStatistics();
                statistics.setUserId(userId);
                statistics.setType(2);
                statistics.setFriendCircleId(id);
                int count = friendCircleStatisticsMapper.selectCount(statistics);
                if(count < 1) {
                    // 更新分享次数
                    friendCircle.setLikeCount(1 + friendCircle.getLikeCount());
                    count = friendCircleMapper.updateByPrimaryKey(friendCircle);

                    // 添加喜欢log
                    FriendCircleStatistics circleStatistics = new FriendCircleStatistics();
                    circleStatistics.setCreateTime(new Date());
                    circleStatistics.setFriendCircleId(id);
                    circleStatistics.setUserId(userId);
                    circleStatistics.setType(2);
                    friendCircleStatisticsMapper.insertSelective(circleStatistics);
                } else {
                    // 更新分享次数
                    int c = (friendCircle.getLikeCount()-1)>0?friendCircle.getLikeCount()-1:0;
                    friendCircle.setLikeCount(c);
                    count = friendCircleMapper.updateByPrimaryKey(friendCircle);

                    //删除喜欢log
                    FriendCircleStatistics circleStatistics = new FriendCircleStatistics();
                    circleStatistics.setFriendCircleId(id);
                    circleStatistics.setUserId(userId);
                    circleStatistics.setType(2);
                    count = friendCircleStatisticsMapper.deleteByUserId(circleStatistics);
                }
                //calWeekRank(id);
                respMap.put("success", count);
            }else {
                respMap.put("success", 0);
            }
        } catch (Exception e) {
            logger.info("更新分喜欢数异常，异常原因：{}", e.toString());
            e.printStackTrace();
            respMap.put(Constants.RESPONSE_ERROR_CODE, "updateLikeCount error!");
        }
        return respMap;
    }
}
