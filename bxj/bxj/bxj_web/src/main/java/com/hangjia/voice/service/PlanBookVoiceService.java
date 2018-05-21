package com.hangjia.voice.service;

import com.hangjia.bxj.vo.PlanBookVoiceReqVo;
import com.hangjia.bxj.vo.PlanBookVoiceRespVo;

import java.util.List;

/**
 * 计划书语音服务接口
 * Created by bei.zhang on 2016/4/26.
 */
public interface PlanBookVoiceService {

    /**
     * 保存用户计划书
     *
     * @param voiceReq
     * @return
     */
    PlanBookVoiceRespVo savePlanBookVoice(PlanBookVoiceReqVo voiceReq);

    /**
     * 根据ID集合删除多个语音
     *
     * @param ids
     * @return
     */
    int deletePlanBookVoiceByIds(String[] ids);

    /**
     * 获取计划书下用户所有总监语音
     *
     * @param voiceReq
     * @return
     */
    List<PlanBookVoiceRespVo> getChiefVoice(PlanBookVoiceReqVo voiceReq);

    /**
     * 获取计划书下用户所有个人语音
     *
     * @param voiceReq
     * @return
     */
    List<PlanBookVoiceRespVo> getUserVoice(PlanBookVoiceReqVo voiceReq);

    /**
     * 获取用户已有语音条数
     *
     * @param voiceReq
     * @return
     */
    int getUserVoiceCount(PlanBookVoiceReqVo voiceReq);

    /**
     * 获取用户最大语音条数
     *
     * @return
     */
    int getMaxUserVoiceCount();

}
