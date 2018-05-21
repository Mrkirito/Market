package com.hangjia.voice.mvc.client;

import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.vo.PlanBookVoiceReqVo;
import com.hangjia.bxj.vo.PlanBookVoiceRespVo;
import com.hangjia.voice.service.PlanBookVoiceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户计划书语音管理服务
 * Created by bei.zhang on 2016/4/26.
 */
@Controller
@RequestMapping("voiceClient")
public class VoiceClientController {

    private static Logger log = LoggerFactory.getLogger(VoiceClientController.class);

    @Autowired
    private PlanBookVoiceService voiceService;

    /**
     * 新增或修改用户语音信息
     *
     * @param voiceReq
     * @param request
     * @return
     */
    @RequestMapping(value = "addUserVoice.json", method = RequestMethod.POST)
    @ResponseBody
    public Object addUserVoice(PlanBookVoiceReqVo voiceReq, HttpServletRequest request) {
        if (null == voiceReq.getUserId() || null == voiceReq.getBookId() || StringUtils.isBlank(voiceReq.getVoiceName())) {
            log.error("请求参数错误,请求参数{}", voiceReq);
            return new AjaxResult.error("请求参数错误" + voiceReq);
        }
        int maxCount = voiceService.getMaxUserVoiceCount();
        int voiceCount = voiceService.getUserVoiceCount(voiceReq);
        if (voiceCount > maxCount) {
            log.error("用户语音已达到最大数,最大语音数{}，用户语音数{}", maxCount, voiceCount);
            return new AjaxResult.error("用户语音已达到最大数");
        }
        PlanBookVoiceRespVo voiceRespVo = voiceService.savePlanBookVoice(voiceReq);
        if (null != voiceRespVo) {
            return new AjaxResult.success(voiceRespVo);
        } else {
            log.error("保存个人名片异常,保存信息{}", voiceReq);
            return new AjaxResult.error("保存失败!");
        }
    }

    /**
     * 获取用户语音信息
     *
     * @param voiceReq
     * @param request
     * @return
     */
    @RequestMapping(value = "userAllVoice.json", method = RequestMethod.GET)
    @ResponseBody
    public Object userAllVoice(PlanBookVoiceReqVo voiceReq, HttpServletRequest request) {
        if (null == voiceReq.getUserId() || null == voiceReq.getBookId()) {
            log.error("请求参数错误,请求参数{}", voiceReq);
            return new AjaxResult.error("请求参数错误" + voiceReq);
        }
        List<PlanBookVoiceRespVo> chiefVoices = voiceService.getChiefVoice(voiceReq);
        List<PlanBookVoiceRespVo> userVoices = voiceService.getUserVoice(voiceReq);
        int maxCount = voiceService.getMaxUserVoiceCount();
        int voiceCount = voiceService.getUserVoiceCount(voiceReq);
        List<PlanBookVoiceRespVo> voices = new ArrayList<PlanBookVoiceRespVo>();
        if (chiefVoices != null) {
            voices.addAll(chiefVoices);
        }
        if (userVoices != null) {
            voices.addAll(userVoices);
        }
        Map<String, Object> respMap = new HashMap<String, Object>();
        respMap.put("voices", voices);
        respMap.put("maxCount", maxCount);
        respMap.put("userVoiceCount", voiceCount);
        return new AjaxResult.success(respMap);
    }

    /**
     * 删除用户语音
     *
     * @param voiceReq
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteUserVoice.json", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteUserVoice(PlanBookVoiceReqVo voiceReq, HttpServletRequest request) {
        if (null == voiceReq.getVoiceIds() || voiceReq.getVoiceIds().isEmpty()) {
            log.error("请求参数错误,请求参数{}", voiceReq);
            return new AjaxResult.error("请求参数错误" + voiceReq);
        }
        if (1 == voiceService.deletePlanBookVoiceByIds(voiceReq.getVoiceIds().split(","))) {
            return new AjaxResult.success("删除成功！");
        } else {
            log.error("删除用户语音异常，删除信息{}", voiceReq.getVoiceIds());
            return new AjaxResult.error("删除失败！");
        }
    }

}
