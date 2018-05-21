package com.hangjia.bxj.service.junior;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.sso.client.SSOUser;
import com.baobao.sso.service.UserService;
import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.dao.MyJuniorsDao;
import com.hangjia.bxj.model.MyJunior;


@Service
@Transactional(rollbackFor=Throwable.class)
public class MyJuniorsServiceImpl implements MyJuniorsService {
	
	@Autowired
	private MyJuniorsDao myJuniorsDao;
	
	@Autowired
	private UserService ssoService;
	
	private static final Log log = LogFactory.getLog(MyJuniorsServiceImpl.class);

	@Override
	public int showMyJuniorCount(Integer userId) {
		return myJuniorsDao.selectMyJuniorCount(userId);
	}

	@Override
	public List<MyJunior> showMyJuniors(Integer userId) {
		return myJuniorsDao.listMyJuniors(userId);
	}
	
	@Override
	public void regist(Integer shareId, String mobile) {
		// TODO 增加一个接口调用，根据mobile返回用户ID。
		// 如果用户存在（已经注册过了），则不允许再次被推荐。
		// 返回 null，表示找不到，允许注册。
		String existUserId = getUserIdByMobile(mobile);
		
		if (log.isInfoEnabled()) {
			log.info("判定用户是否注册过，手机号：" + mobile + "返回用户ID：" + existUserId);
		}
		
		if (existUserId != null) {
			throw new BXJException("您已注册过，请登录使用");
		}
		
		try {
			myJuniorsDao.saveMyJunior(shareId, mobile);
		} catch (DuplicateKeyException e) {
			if (log.isInfoEnabled()) {
				log.info("保存推荐关系，被邀请号码可能已经注册过，手机号：" + mobile + "，用户ID：" + existUserId + "，异常类：" + e.getClass().getName(), e);
			}
			throw new BXJException("您已经被邀请过，快去体验吧");
		}
		
	}
	
	@Override
	public void active(String mobile) {
		myJuniorsDao.active(mobile);
	}
	
	private String getUserIdByMobile(String mobile) {
		SSOUser user=ssoService.getSSOUserByMobile(mobile);
		if (null != user) {
			return user.getId()+"";
		}
		return null;
	}

}
