package com.hangjia.bxj.service.prize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.framework.support.utility.Configuration;
import com.hangjia.bxj.dao.prize.PrizeBonusLogMapper;
import com.hangjia.bxj.model.prize.PrizeBonusLog;
import com.hangjia.bxj.query.prize.PrizeDetailQuery;
import com.hangjia.bxj.service.prize.IPrizeDetailService;
import com.hangjia.bxj.ucenter.dao.UcUserMapper;
import com.hangjia.bxj.ucenter.model.UcUser;
import com.ibaoxianjia.message.dto.SmsMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PrizeDetailServiceImpl implements IPrizeDetailService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PrizeBonusLogMapper prizeLogMapper;
	@Autowired
    private MessageOpenAPI messageOpenAPI; 
	@Autowired
	private UcUserMapper userMapper;
	
    @Value("${show_v3_path}")
    private String showPath;

	@Override
	public int queryPrizeLogCount(PrizeDetailQuery query) {
		return prizeLogMapper.queryPrizeLogCount(query);
	}

	@Override
	public List<PrizeBonusLog> queryPrizeLogList(PrizeDetailQuery query) {
		List<PrizeBonusLog> list = new ArrayList<PrizeBonusLog>();
		list = prizeLogMapper.queryPrizeLogList(query);
		if(null != list && list.size() > 0){
			for (PrizeBonusLog prizeLog : list) {
				UcUser user= userMapper.selectByPrimaryKey(prizeLog.getUserID());
				if(user!=null)prizeLog.setUserName(user.getUsername()); //用户名
				if(StringUtils.isNotBlank(prizeLog.getPrizeImg())){
					prizeLog.setPrizeImg(showPath + prizeLog.getPrizeImg());
				}
				if(StringUtils.isNotBlank(prizeLog.getContextImg())){
					prizeLog.setContextImg(showPath + prizeLog.getContextImg());
				}
			}
		}
		return list;
	}

	@Override
	public int updatePrizeLogStatus(PrizeBonusLog prizelog) {
		if(prizelog.getBonusLogId()!=null){
			int result=0;
			  try {
				  // 发送短信 
				  SmsMessageDto smsMessageDto = new SmsMessageDto();
			      smsMessageDto.setSysid(Configuration.getProperty("system", "sysid"));
			      smsMessageDto.addPhone(prizelog.getTakephone()); // 电话
			      smsMessageDto.setIp(prizelog.getIp()); //ip
			      smsMessageDto.setSmsCode("BXJ_PRIZE_SEND");
			      Map<String, String> infoMap = new HashMap<String, String>();
			      infoMap.put("prizeName", prizelog.getName());
			      smsMessageDto.setSmsInfoMap(infoMap);
			      messageOpenAPI.sendSms(smsMessageDto);
			      
			      try {
						result= prizeLogMapper.updatePrizeLogStatus(prizelog);
					} catch (Exception e) {
						logger.error("更新中奖 发货状态异常:"+e.getStackTrace());
						return -2;
					}
			} catch (Exception e) {
				logger.error("中奖发货-发送短信异常:"+e.getStackTrace());
				return -1;
			}
		  return result;
		} 
		return 0;
	}
    
}
