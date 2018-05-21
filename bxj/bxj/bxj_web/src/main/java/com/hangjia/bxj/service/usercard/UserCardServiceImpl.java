package com.hangjia.bxj.service.usercard;

import com.baobao.framework.utils.jedis.RedisUtil;
import com.baobao.sso.client.*;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.service.ControlAppStoreService;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.hangjia.bxj.vo.PlanUserCard;
import com.hangjia.bxj.vo.PlanUserImgVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserCardServiceImpl
 * @Description: 用户名片业务实现
 * @author: bei.zhang
 * @date: 2016年4月14日 下午4:27:08
 */
@Service
public class UserCardServiceImpl implements UserCardService {

    @Autowired
    private UserCardSupportService userCardSupportService;
    @Autowired
    private ControlAppStoreService controlAppStoreService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${bxj_path}")
    private String bxjPath;


    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> queryAllCompanyAndPosition() {
        List<Map<String, Object>> map = (List<Map<String, Object>>) redisUtil
                .getUnserializeKey(RedisKeyConstants.KEY_COMPANY_AND_POSITION);
        if (map == null) {
            List<CompanyRespDto> gss = userCardSupportService.getAllCompanyAndPosition();
            List<Map<String, Object>> companys = new ArrayList<Map<String, Object>>();
            for (CompanyRespDto companyResp : gss) {
                if ("全部公司".equals(companyResp.getCompanyName().trim())){
                    continue;
                }
                Map<String, Object> companyMap = new HashMap<String, Object>();
                companyMap.put("fid", companyResp.getFid());
                companyMap.put("companyName", companyResp.getCompanyName());
                List<Map<String, Object>> positionList = new ArrayList<Map<String, Object>>();
                if (companyResp.getChildrens() != null) {
                    for (CompanyPositionRespDto positionResp : companyResp.getChildrens()) {
                        Map<String, Object> positionMap = new HashMap<String, Object>();
                        positionMap.put("fid", positionResp.getFid());
                        positionMap.put("companyId", positionResp.getCompanyId());
                        positionMap.put("positionName", positionResp.getPositionName());
                        positionList.add(positionMap);
                    }
                }
                companyMap.put("childrens", positionList);
                companys.add(companyMap);
            }
            redisUtil.setSerializeKey(RedisKeyConstants.KEY_COMPANY_AND_POSITION, companys);
            return companys;
        }
        return map;
    }

    @Override
    public PlanUserCard queryPlanUserCardById(Integer userId) {
        UserCardRespDto userCardResp = userCardSupportService.getUserCardByFid(userId);
        if (null != userCardResp) {
            PlanUserCard userCard = new PlanUserCard();
            BeanUtils.copyProperties(userCardResp, userCard);
            if (StringUtils.isEmpty(userCard.getPhoto())){
                userCard.setPhoto(bxjPath + "/hjb_app/planusercard/mecard_default_simple.png");
            }
            userCard.setShareUrl(bxjPath + com.hangjia.bxj.util.Constants.USER_CARD_SHARE_PATH_PREFIX +
                    userCard.getFid() + com.hangjia.bxj.util.Constants.USER_CARD_SHARE_PATH_SUFFIX);
            List<UserImgRespDto> userImgRespList = userCardSupportService.getAllUserImg(userId);
            if (null != userImgRespList && !userImgRespList.isEmpty()) {
                List<PlanUserImgVo> userImgs = new ArrayList<PlanUserImgVo>();
                for (UserImgRespDto userImgResp : userImgRespList) {
                    PlanUserImgVo userImg = new PlanUserImgVo();
                    BeanUtils.copyProperties(userImgResp, userImg);
                    userImgs.add(userImg);
                }
                userCard.setUserImgs(userImgs);
                return userCard;
            }
            return userCard;
        }
        return null;
    }

    @Override
    public PlanUserCard savaPlanUserCard(PlanUserCard planUserCard) {
        UserCardReqDto userCardReq = new UserCardReqDto();
        if (null != planUserCard.getUserImgPhoto()) {
            try {
                userCardReq.setUserPhotoByte(planUserCard.getUserImgPhoto().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(planUserCard, userCardReq);
        userCardSupportService.saveUserCardBySelective(userCardReq);
        if (planUserCard.getUserImg() != null) {
            UserImgReqDto userImgReq = new UserImgReqDto();
            try {
                userImgReq.setUserImgByte(planUserCard.getUserImg().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            userImgReq.setUserId(planUserCard.getFid().longValue());
            userCardSupportService.saveUserImg(userImgReq);
        }
        return this.queryPlanUserCardById(planUserCard.getFid());
    }

    @Override
    public List<Map<String, String>> queryAllJobYears() {
        String jobYearsStr = controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_JOB_YERAS);
        List<Map<String, String>> jobYears = new ArrayList<Map<String, String>>();
        String[] jobYear = jobYearsStr.split(";");
        for (String jobStr : jobYear) {
            Map<String, String> jobMap = new HashMap<String, String>();
            String[] job = jobStr.split(",");
            jobMap.put("jobYearName", job[0]);
            jobMap.put("jobYearValue", job[1]);
            jobYears.add(jobMap);
        }
        return jobYears;
    }

    @Override
    public PlanUserCard deleteUserImg(PlanUserImgVo userImg) {
        userCardSupportService.deleteUserImg(userImg.getFid());
        return this.queryPlanUserCardById(userImg.getUserId().intValue());
    }

}
