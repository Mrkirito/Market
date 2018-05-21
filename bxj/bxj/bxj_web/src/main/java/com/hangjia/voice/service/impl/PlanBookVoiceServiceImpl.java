package com.hangjia.voice.service.impl;

import com.baobao.framework.support.utility.DateUtility;
import com.hangjia.bxj.dao.PlanBookVoiceMapper;
import com.hangjia.bxj.model.PlanBookVoice;
import com.hangjia.bxj.service.ControlAppStoreService;
import com.hangjia.bxj.util.Constants;
import com.hangjia.bxj.util.OrderConstants;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.hangjia.bxj.vo.OrderData;
import com.hangjia.bxj.vo.OrderMethod;
import com.hangjia.bxj.vo.PlanBookVoiceReqVo;
import com.hangjia.bxj.vo.PlanBookVoiceRespVo;
import com.hangjia.voice.service.PlanBookVoiceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 计划书语音服务实现
 * Created by bei.zhang on 2016/4/26.
 */
@Service
public class PlanBookVoiceServiceImpl implements PlanBookVoiceService {

    private static Logger log = LoggerFactory.getLogger(PlanBookVoiceServiceImpl.class);

    @Autowired
    private PlanBookVoiceMapper voiceMapper;
    @Value("${bxj.upload.root.parent}")
    private String uploadPath;
    @Value("${static_path}")
    private String staticPath;
    @Autowired
    private ControlAppStoreService controlAppStoreService;

    @Override
    public PlanBookVoiceRespVo savePlanBookVoice(PlanBookVoiceReqVo voiceReq) {
        try {
            PlanBookVoice voice = new PlanBookVoice();
            BeanUtils.copyProperties(voiceReq, voice);
            voice.setVoiceUrl(saveVoiceFile(voiceReq.getBookId(), voiceReq.getUserId(), voiceReq.getVoiceFile()));
            PlanBookVoice oldVoice = null;
            PlanBookVoiceRespVo voiceRespVo = null;
            if (null != voiceReq.getVoiceId()) {
                if (StringUtils.isEmpty(voice.getVoiceUrl())) {
                    return null;
                }
                oldVoice = voiceMapper.selectByPrimaryKey(voiceReq.getVoiceId());
            }
            if (null == voiceReq.getVoiceId() || oldVoice == null) {
                voice.setType(Constants.VOICE_TYPE_USER);
                voice.setIsOnly(Constants.VOICE_IS_ONLY_NO);
                voice.setIsDeleted(Constants.VOICE_IS_DELETED_NO);
                voice.setCreateAt(new Date());
                voiceMapper.insert(voice);
                long id = voiceMapper.selectMaxPrimaryKey();
                voiceRespVo = this.getPlanBookVoiceByIds(id);
            } else {
                BeanUtils.copyProperties(voiceReq, oldVoice);
                if (StringUtils.isNotEmpty(voice.getVoiceUrl())) {
                    oldVoice.setVoiceUrl(voice.getVoiceUrl());
                }
                voice.setModifyAt(new Date());
                voiceRespVo = this.getPlanBookVoiceByIds(oldVoice.getId());
            }
            return voiceRespVo;
        } catch (Exception ex) {
            log.error("保存用户计划书语音失败,异常原因:{}", ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    private String saveVoiceFile(Long bookId, Long userId, MultipartFile voiceFile) {
        if (null != voiceFile) {
            StringBuilder sBuilder = new StringBuilder();
            String voiceUrl = sBuilder.append(File.separator).append("hjb_app").append(File.separator)
                    .append("voices").append(File.separator).append(userId).append(File.separator).toString();
            String serverPath = uploadPath + voiceUrl;
            File floder = new File(serverPath);
            if (!floder.exists()) {
                floder.mkdirs();
            }
            String filename = userId + "_" + bookId + "_" + System.currentTimeMillis();
            String imgFilePath = serverPath + File.separator + filename + ".mp3";
            InputStream stream = null;
            OutputStream os = null;
            try {
                byte[] bts = new byte[1024];
                int length = 0;
                stream = voiceFile.getInputStream();
                os = new FileOutputStream(imgFilePath);
                while ((length = stream.read(bts)) > 0) {
                    os.write(bts, 0, length);
                }
                os.flush();
                if ("Windows 7".equals(System.getProperty("os.name"))) {
                    return "/hjb_app/voices/" + userId + "/" + filename + ".mp3";
                }
                return voiceUrl + filename + ".mp3";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public int deletePlanBookVoiceByIds(String[] ids) {
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    PlanBookVoice voice = voiceMapper.selectByPrimaryKey(Long.valueOf(id));
                    PlanBookVoice deleteVoice = new PlanBookVoice();
                    deleteVoice.setId(voice.getId());
                    deleteVoice.setIsDeleted(Constants.VOICE_IS_DELETED_YES);
                    voiceMapper.updateByPrimaryKeySelective(deleteVoice);
                }
            }
            return 1;
        } catch (Exception ex) {
            log.error("删除计划书失败,异常原因:{}", ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<PlanBookVoiceRespVo> getChiefVoice(PlanBookVoiceReqVo voiceReq) {
        try {
            PlanBookVoice voice = new PlanBookVoice();
            voice.setBookId(voiceReq.getBookId());
            voice.setType(Constants.VOICE_TYPE_CHIEF);
            voice.setIsDeleted(Constants.VOICE_IS_DELETED_NO);
            voice.addOrderData(new OrderData(OrderConstants.PLAN_BOOK_VOICE_CREATE_AT, OrderMethod.DESC));
            List<PlanBookVoice> voices = voiceMapper.selectBySelective(voice);
            return copyVoiceToVoiceResp(voices);
        } catch (Exception ex) {
            log.error("获取总监语音失败,异常原因:{}", ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PlanBookVoiceRespVo> getUserVoice(PlanBookVoiceReqVo voiceReq) {
        try {
            PlanBookVoice voice = new PlanBookVoice();
            BeanUtils.copyProperties(voiceReq, voice);
            voice.setType(Constants.VOICE_TYPE_USER);
            voice.setIsDeleted(Constants.VOICE_IS_DELETED_NO);
            voice.addOrderData(new OrderData(OrderConstants.PLAN_BOOK_VOICE_CREATE_AT, OrderMethod.DESC));
            List<PlanBookVoice> voices = voiceMapper.selectBySelective(voice);
            return copyVoiceToVoiceResp(voices);
        } catch (Exception ex) {
            log.error("获取用户语音失败,异常原因:{}", ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int getUserVoiceCount(PlanBookVoiceReqVo voiceReq) {
        PlanBookVoice voice = new PlanBookVoice();
        BeanUtils.copyProperties(voiceReq, voice);
        voice.setType(Constants.VOICE_TYPE_USER);
        voice.setIsDeleted(Constants.VOICE_IS_DELETED_NO);
        return voiceMapper.selectBySelectiveCount(voice);
    }

    @Override
    public int getMaxUserVoiceCount() {
        try {
            String maxUserVoice = controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_MAX_USER_VOCIE);
            return Integer.valueOf(maxUserVoice);
        } catch (Exception ex) {
            log.error("获取最大用户语音数失败,异常原因:{}", ex.getMessage());
            ex.printStackTrace();
            return 5;
        }
    }

    /**
     * 生成响应
     *
     * @param voices
     * @return
     */
    private List<PlanBookVoiceRespVo> copyVoiceToVoiceResp(List<PlanBookVoice> voices) {
        if (null != voices && !voices.isEmpty()) {
            List<PlanBookVoiceRespVo> respList = new ArrayList<PlanBookVoiceRespVo>();
            for (PlanBookVoice planBookVoice : voices) {
                PlanBookVoiceRespVo voiceResp = new PlanBookVoiceRespVo();
                BeanUtils.copyProperties(planBookVoice, voiceResp);
                voiceResp.setCreateAtStr(DateUtility.format("yyyy-MM-dd", planBookVoice.getCreateAt()));
                if (StringUtils.isNotEmpty(voiceResp.getVoiceUrl())) {
                    voiceResp.setVoiceUrl(staticPath + voiceResp.getVoiceUrl());
                }
                respList.add(voiceResp);
            }
            return respList;
        }
        return null;
    }

    /**
     * 生成响应
     *
     * @param id
     * @return
     */
    private PlanBookVoiceRespVo getPlanBookVoiceByIds(long id) {
        PlanBookVoiceRespVo voiceRespVo = new PlanBookVoiceRespVo();
        PlanBookVoice newVoice = voiceMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(newVoice, voiceRespVo);
        voiceRespVo.setCreateAtStr(DateUtility.format("yyyy-MM-dd", newVoice.getCreateAt()));
        if (StringUtils.isNotEmpty(newVoice.getVoiceUrl())) {
            voiceRespVo.setVoiceUrl(staticPath + newVoice.getVoiceUrl());
        }
        return voiceRespVo;
    }

}
