package com.hangjia.bxj.service.lectures.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.service.lectures.ILecturesAuthService;
import com.hangjia.bxj.ucenter.dao.UserCardMapper;
import com.hangjia.bxj.ucenter.model.UserCard;
import com.hangjia.bxj.ucenter.query.UserCardQuery;

import bsh.StringUtil;

@Service
@Transactional(rollbackFor = Throwable.class)
public class LecturesAuthServiceImpl implements ILecturesAuthService {

	@Autowired
	private UserCardMapper userCardMapper;

	@Value("${occ_client.img_url}")
	private String occImgUrl;
	
	@Override
	public int queryUserCardDataCount(UserCardQuery query) {
		return userCardMapper.queryUserCardDataCount(query);
	}

	@Override
	public List<UserCard> queryUserCardDataPage(UserCardQuery query) {
		List<UserCard> list = new ArrayList<UserCard>();
		list = userCardMapper.queryUserCardDataPage(query);
		if(null != list && list.size() > 0){
			for (UserCard userCard : list) {
				if(StringUtils.isNotBlank(userCard.getIdCardFrontUrl())){
					userCard.setIdCardFrontUrl(occImgUrl + userCard.getIdCardFrontUrl());
				}
				if(StringUtils.isNotBlank(userCard.getIdCardReverseUrl())){
					userCard.setIdCardReverseUrl(occImgUrl + userCard.getIdCardReverseUrl());
				}
			}
		}
		return list;
	}

	@MethodLog(remark = "讲师认证审核通过")
	@Override
	public int passAuth(UserCard userCard) {
		return userCardMapper.updateUserCardStatus(userCard);
	}
	
	@MethodLog(remark = "讲师认证审核不通过")
	@Override
	public int failAuth(UserCard userCard) {
		return userCardMapper.updateUserCardStatus(userCard);
	}
	
}
