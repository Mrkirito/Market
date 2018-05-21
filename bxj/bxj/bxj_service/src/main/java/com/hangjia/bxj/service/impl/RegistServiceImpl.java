package com.hangjia.bxj.service.impl;

import com.hangjia.bxj.dao.*;
import com.hangjia.bxj.model.*;
import com.hangjia.bxj.service.RegistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户注册接口
 */
public class RegistServiceImpl implements RegistService {
	private Logger logger = LoggerFactory.getLogger(RegistServiceImpl.class);
	/**券id**/
	private static final Long voucheId = 1l;
	@Autowired
	private ChampionVoucherMapper voucherMapper;
	@Autowired
	private ChampionUserVoucherMapper userVoucherMapper;
	@Autowired
	private ChampionUserVocherLogMapper championUserVocherLogMapper;
	@Autowired
	private ChampionInviteMapper championInviteMapper;
	@Autowired
	ChampionDictionaryMapper championDictionaryMapper;

	/**
	 * 用户送券（登录）
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getRegistVoucher(Long userId, Integer type) {
        try {
			type = null == type ? 9 : type;
			boolean flag = true;
			logger.info("getRegistVoucher userId:" + userId);
			Map<String, Object> map = new HashMap<String, Object>();
			String result = "success";
			ChampionVoucher voucher = voucherMapper.selectByPrimaryKey(voucheId);
			if (null == voucher) {
				map.put("status", "failed");
			} else {
				ChampionUserVoucher registVoucher = new ChampionUserVoucher();
				registVoucher.setUserId(userId);
				registVoucher.setVoucherId(voucheId);
				List<ChampionUserVoucher> userVouchersC = userVoucherMapper.selectBySelective(registVoucher);
				//1：注册送券；2：看视频使用用券；3：邀请送券；4：每天登录送券；5:分享送券；9：其它送券
				int voucherCount = 0;
				if (1 == type) voucherCount = voucher.getRegistCount();
				else if (3 == type) voucherCount = voucher.getInviteCount();
				else if (4 == type) voucherCount = voucher.getLoginCount();
				else if (5 == type) {
					voucherCount = voucher.getShareCount();
					/** 分享每天最多送几张限制 **/
					ChampionDictionary championDictionary = new ChampionDictionary();
					championDictionary.setCode("SHARE_VOUCHER_COUNT_CODE");
					List<ChampionDictionary> list = championDictionaryMapper.selectByPage(championDictionary);
					int totalCount;
					if (null == list || list.size() == 0) {
						map.put("status", "failed");
						return map;
					} else {
						totalCount = Integer.parseInt(list.get(0).getValue());
						ChampionUserVocherLog championUserVocherLog = new ChampionUserVocherLog();
						championUserVocherLog.setUserId(userId);
						championUserVocherLog.setType(type);
						Date currentTime = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						String dateString = formatter.format(currentTime);
						DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
						championUserVocherLog.setCreateAt(df2.parse(dateString));
						/** 今天得券数 **/
						int inviteVourcherCount = championUserVocherLogMapper.geInviteVourcherCoumt(championUserVocherLog);
						if (inviteVourcherCount >= totalCount) flag = false;
					}
				}

				//券数大于0才进行以下逻辑
				if (voucherCount > 0 && flag) {
					if (userVouchersC == null || userVouchersC.isEmpty()) {
						registVoucher.setTotal(voucherCount);
						registVoucher.setGetAllcounts(voucherCount);
						registVoucher.setUseAllcounts(0);
						registVoucher.setCreateTime(new Date());
						int count = userVoucherMapper.insert(registVoucher);
						result = count == 0 ? "failed" : "success";
					} else {
						registVoucher = userVouchersC.get(0);
						registVoucher.setTotal(registVoucher.getTotal() + voucherCount);
						registVoucher.setGetAllcounts(registVoucher.getGetAllcounts() + voucherCount);
						registVoucher.setUpdateTime(new Date());
						int count = userVoucherMapper.updateByPrimaryKeySelective(registVoucher);
						result = count == 0 ? "failed" : "success";
					}

					//注册送券日志
					ChampionUserVocherLog inviteLog = new ChampionUserVocherLog();
					inviteLog.setUserId(userId);
					inviteLog.setVocherId(voucher.getFid());
					inviteLog.setType(type);
					inviteLog.setCount(voucherCount);
					inviteLog.setCreateAt(new Date());
					championUserVocherLogMapper.insert(inviteLog);
				}
			}
			map.put("status", result);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getRegistVoucher error!");
		}
		return null;
	}

    /**
     * 根据被邀请人id获取邀请人id
     *
     * @param userId
     * @return
     */
    public Integer getInviteUserIdByBeInviteUserId(Long userId) {
        try {
            ChampionInvite conditions = new ChampionInvite();
            conditions.setBeInvitedUser(userId);
            conditions.setInviteStatus(2);
            List<ChampionInvite> invites = championInviteMapper.selectBySelective(conditions);
            if (invites != null && !invites.isEmpty()) {
                return invites.get(0).getInviteUser().intValue();
            }
        } catch (Exception ex) {
            logger.error("根据被邀请人id获取邀请人id异常 被邀请人userId:{},异常原因:{}", userId, ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
