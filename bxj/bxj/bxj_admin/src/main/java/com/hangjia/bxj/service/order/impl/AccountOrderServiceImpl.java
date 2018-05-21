package com.hangjia.bxj.service.order.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.ChampionRewardVideoMapper;
import com.hangjia.bxj.dao.ChampionVideoMapper;
import com.hangjia.bxj.model.order.AccountOrderExt;
import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.order.RewardQuery;
import com.hangjia.bxj.service.order.IAccountOrderService;
import com.hangjia.bxj.ucenter.dao.AccountOrderMapper;
import com.hangjia.bxj.ucenter.model.Cash;
import com.hangjia.bxj.ucenter.model.Profit;
import com.hangjia.bxj.ucenter.model.UcUser;
import com.hangjia.bxj.ucenter.query.CashQuery;
import com.hangjia.bxj.ucenter.query.ProfitQuery;
import com.ibaoxianjia.ucenter.service.UCenterSupportService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountOrderServiceImpl implements IAccountOrderService {

	@Autowired
	private AccountOrderMapper accountOrderMapper;
	
	@Autowired
	private ChampionVideoMapper championVideoMapper;
	
	@Autowired
	private ChampionRewardVideoMapper championRewardVideoMapper;
	
	@Autowired
	private UCenterSupportService uCenterSupportService;
	
	/**
	 * 查询收益列表总数
	 * @param query
	 * @return
	 */
	@Override
	public int queryProfitDataCount(ProfitQuery query) {
		return accountOrderMapper.queryProfitDataCount(query);
	}

	/**
	 * 查询收益列表
	 * @param query
	 * @return
	 */
	@Override
	public List<Profit> queryProfitDataPage(ProfitQuery query) {
		List<Profit> list = new ArrayList<Profit>();
		list = accountOrderMapper.queryProfitDataPage(query);
		if(null != list  && list.size() > 0 && null != query.getDimension() && query.getDimension().intValue() == 4){
			List<String> orderNos = new ArrayList<String>();
			for (Profit profit : list) {
				if(null != profit.getOrderNo()){
					orderNos.add(profit.getOrderNo());
				}
			}
			// 查询所有订单关联的视频名称
			if(null != orderNos && orderNos.size() > 0){
				List<AccountOrderExt> listMap = championVideoMapper.queryVideoNameByOrderNo(orderNos);
				for (Profit profit : list) {
					if(null != profit.getOrderNo()){
						for (AccountOrderExt accountOrderExt : listMap) {
							if(StringUtils.equals(profit.getOrderNo(), accountOrderExt.getOrderId())){
								profit.setVideoName(accountOrderExt.getVideoName());
								break;
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 查询打赏列表总数
	 * @param query
	 * @return
	 */
	@Override
	public int queryRewardDataCount(RewardQuery query) {
		return championRewardVideoMapper.queryRewardPageDataCount(query);
	}

	/**
	 * 查询打赏列表
	 * @param query
	 * @return
	 */
	@Override
	public List<Reward> queryRewardDataPage(RewardQuery query) {
		List<Reward> list = new ArrayList<Reward>();
		list = championRewardVideoMapper.queryRewardPageData(query);
		if(null != list && list.size() > 0 && null != query.getDimension() && 
				(query.getDimension().intValue() == 1 || query.getDimension().intValue() == 3 || query.getDimension().intValue() == 4)){
			List<Long> userIds = new ArrayList<Long>();
			for (Reward reward : list) {
				if(null != reward.getUserId()){
					userIds.add(reward.getUserId());
				}
			}
			// 查询用户名 用户账号
			if(null != userIds && userIds.size() > 0){
				List<UcUser> users = accountOrderMapper.queryUserByUserId(userIds);
				if(null != users && users.size() > 0){
					for (Reward reward : list) {
						for (UcUser ucUser : users) {
							if(ucUser.getId().longValue() == reward.getUserId().longValue()){
								reward.setUserName(ucUser.getUsername());
								reward.setNickName(ucUser.getNickname());
								break;
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 查询所有符合信息的userId
	 * @param query
	 * @return
	 */
	@Override
	public List<Long> queryUserIds(RewardQuery query) {
		return accountOrderMapper.queryUserIds(query);
	}

	/**
	 * 查询提现列表总数
	 * @param query
	 * @return
	 */
	@Override
	public int queryCashDataCount(CashQuery query) {
		return accountOrderMapper.queryCashDataCount(query);
	}

	/**
	 * 查询提现列表
	 * @param query
	 * @return
	 */
	@Override
	public List<Cash> queryCashDataPage(CashQuery query) {
		return accountOrderMapper.queryCashDataPage(query);
	}

	/**
	 * 审核通过
	 * @param cash
	 * @return
	 */
	@MethodLog(remark = "提现审核通过")
	@Override
	public int passCash(Cash cash) {
		int update = uCenterSupportService.passCashApply(cash.getId(), cash.getAuditReason());
		return update;
	}

	/**
	 * 审核不通过
	 * @param cash
	 * @return
	 */
	@MethodLog(remark = "提现审核不通过")
	@Override
	public int failCash(Cash cash) {
		int update = uCenterSupportService.refuseCashApply(cash.getId(), cash.getAuditReason());
		return update;
	}
}
