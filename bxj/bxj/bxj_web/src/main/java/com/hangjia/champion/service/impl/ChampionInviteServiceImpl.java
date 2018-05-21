package com.hangjia.champion.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hangjia.bxj.dao.ChampionUserVocherLogMapper;
import com.hangjia.bxj.model.ChampionUserVocherLog;
import com.ibaoxianjia.activity.service.RedEnvelopeSupportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.ChampionInviteMapper;
import com.hangjia.bxj.dao.ChampionUserVoucherMapper;
import com.hangjia.bxj.dao.ChampionVoucherMapper;
import com.hangjia.bxj.model.ChampionInvite;
import com.hangjia.bxj.model.ChampionUserVoucher;
import com.hangjia.bxj.model.ChampionVoucher;
import com.hangjia.bxj.util.Constants;
import com.hangjia.champion.service.ChampionInviteService;

/**
 * @ClassName: ChampionInviteServiceImpl
 * @Description: 冠军说邀请业务
 * @author: bei.zhang
 * @date: 2016年4月13日 下午2:01:43
 */
@Service
public class ChampionInviteServiceImpl implements ChampionInviteService {

    private static Logger log = LoggerFactory.getLogger(ChampionInviteServiceImpl.class);

    @Autowired
    private ChampionInviteMapper inviteMapper;
    @Autowired
    private ChampionVoucherMapper voucherMapper;
    @Autowired
    private ChampionUserVoucherMapper userVoucherMapper;
    @Autowired
    private ChampionUserVocherLogMapper championUserVocherLogMapper;
    @Autowired
    private RedEnvelopeSupportService redEnvelopeSupportService;

    @Override
    @Transactional
    public Map<String, String> inviteUserSuccess(Map<String, Object> map, Long inviteUserId, Long voucherId) {
        Map<String, String> rmap = new HashMap<String, String>();
        Long beInviteUserId = Long.parseLong(String.valueOf(map.get("newUserId")));
        if (null == beInviteUserId) {
            map.put("code", "10001");
            return rmap;
        }
        // 查看用户与被邀请用户是否存在邀请关系
        ChampionInvite invite = new ChampionInvite();
        invite.setInviteUser(inviteUserId);
        invite.setBeInvitedUser(beInviteUserId);
        invite.setInviteStatus(Constants.INVITE_STATUS_SUCC);
        List<ChampionInvite> invites = inviteMapper.selectBySelective(invite);
        if (invites != null && !invites.isEmpty()) {
            map.put("code", "10002");
            rmap.put("msg", "赠用户与被邀请用户已邀请过！");
            return rmap;
        }
        // 获取赠送券信息
        ChampionVoucher voucher = voucherMapper.selectByPrimaryKey(voucherId);
        if (voucher == null) {
            rmap.put("code", "10003");
            rmap.put("msg", "赠送的券不存在！");
            return rmap;
        }

        //邀请获得券
        if (voucher.getInviteCount() > 0) {
            // 获取用户拥有该券数目
            ChampionUserVoucher userVoucher = new ChampionUserVoucher();
            userVoucher.setUserId(inviteUserId);
            userVoucher.setVoucherId(voucherId);
            List<ChampionUserVoucher> userVouchers = userVoucherMapper.selectBySelective(userVoucher);
            if (userVouchers == null || userVouchers.isEmpty()) {
                userVoucher.setTotal(voucher.getInviteCount());
                userVoucher.setGetAllcounts(voucher.getInviteCount());
                userVoucher.setUseAllcounts(0);
                userVoucher.setCreateTime(new Date());
                userVoucherMapper.insert(userVoucher);
            } else {
                userVoucher = userVouchers.get(0);
                userVoucher.setTotal(userVoucher.getTotal() + voucher.getInviteCount());
                userVoucher.setGetAllcounts(userVoucher.getGetAllcounts() + voucher.getInviteCount());
                userVoucher.setUpdateTime(new Date());
                userVoucherMapper.updateByPrimaryKeySelective(userVoucher);
            }
            //邀请送券日志
            ChampionUserVocherLog userVoucherLog = new ChampionUserVocherLog();
            userVoucherLog.setUserId(inviteUserId);
            userVoucherLog.setVocherId(voucher.getFid());
            userVoucherLog.setType(Constants.VOUCHER_CONSUME_TYPE_INVITE_ADD);
            userVoucherLog.setCount(voucher.getInviteCount());
            userVoucherLog.setCreateAt(new Date());
            championUserVocherLogMapper.insert(userVoucherLog);
        }

        //注册获取券
        if (voucher.getRegistCount() > 0) {
            ChampionUserVoucher registVoucher = new ChampionUserVoucher();
            registVoucher.setUserId(beInviteUserId);
            registVoucher.setVoucherId(voucherId);
            List<ChampionUserVoucher> userVouchersC = userVoucherMapper.selectBySelective(registVoucher);
            if (userVouchersC == null || userVouchersC.isEmpty()) {
                registVoucher.setTotal(voucher.getRegistCount());
                registVoucher.setGetAllcounts(voucher.getRegistCount());
                registVoucher.setUseAllcounts(0);
                registVoucher.setCreateTime(new Date());
                rmap.put("inviteCount", String.valueOf(voucher.getRegistCount()));
                userVoucherMapper.insert(registVoucher);

                //注册送券日志
                ChampionUserVocherLog inviteLog = new ChampionUserVocherLog();
                inviteLog.setUserId(beInviteUserId);
                inviteLog.setVocherId(voucher.getFid());
                inviteLog.setType(Constants.VOUCHER_CONSUME_TYPE_ADD);
                inviteLog.setCount(voucher.getRegistCount());
                inviteLog.setCreateAt(new Date());
                championUserVocherLogMapper.insert(inviteLog);
            }
        }
        invite.setAcceptAt(new Date());
        inviteMapper.insert(invite);
        rmap.put("code", "1000");
        rmap.put("msg", "注册并领取成功");
        // 注册成功暂不送券
//        try {
//            this.addInviteUserRedEnvelopeDrawCount(inviteUserId, beInviteUserId);
//            log.info("邀请用户活动红包抽取机会成功 邀请用户：{}", inviteUserId);
//        } catch (Exception ex) {
//            log.info("邀请用户活动红包抽取机会异常 邀请用户：{}，异常原因：{}", inviteUserId, ex.getMessage());
//            ex.printStackTrace();
//        }
        return rmap;
    }

    /**
     * 邀请成功后邀请用户获取红包抽奖机会
     *
     * @param inviteUserId
     * @param beInviteUserId
     */
    private void addInviteUserRedEnvelopeDrawCount(Long inviteUserId, Long beInviteUserId) {
        redEnvelopeSupportService.addUserRedEnvelopeCount(inviteUserId, Constants.RED_ENVELOPE_TYPE_INVITE);
    }
}
