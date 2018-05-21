package com.hangjia.bxj.service.invitation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.dao.InvitationDao;
import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationDetails;
import com.hangjia.bxj.model.InvitationSimple;

@Service
@Transactional(readOnly=true, rollbackFor=Throwable.class)
public class InvitationServiceImpl implements InvitationService {
	
	@Autowired
	private InvitationDao invitationDao;

	@Override
	public List<Invitation> list(Integer userId) {
		return invitationDao.list(userId);
	}
	
	/**
	 * 每个用户默认的邀请函数量限制，如果没有其他额外限制（根据用户级别增加或减少上限），使用此默认值。
	 * 用户级别未实现。
	 */
	private static final int DEFAULT_COUNT_LIMIT = 3;
	
	@Override
	@Transactional(readOnly=false)
	public void save(Invitation model) {
		if (model.getUserId() == null) {
			throw new IllegalArgumentException("保存邀请函异常，必须注入 userId");
		}
		
		int count = invitationDao.count(model.getUserId());
		if (count >= DEFAULT_COUNT_LIMIT) {
			throw new BXJException("邀请函数量超过上限，请先删除不需要的邀请函再尝试新建。");
		}
		try {
			invitationDao.save(model);
		} catch (DuplicateKeyException e) {
			throw new BXJException("已有同名邀请函，请使用其他名称");
		}
		
	}
	
	@Override
	@Transactional(readOnly=false)
	public int delete(Long id, Integer userId) {
		return invitationDao.delete(id, userId);
	}
	
	@Override
	public List<InvitationSimple> listSimple(Integer userId) {
		return invitationDao.listSimple(userId);
	}
	
	@Override
	public Invitation getUserInvitationNotNull(Long id, Integer userId) {
		Invitation inv = invitationDao.getUserInvitation(id, userId);
		if (inv == null) {
			throw new BXJException("没有找到邀请函");
		}
		return inv;
	}
	
	@Override
	public InvitationDetails details(Long id, Integer userId) {
		InvitationDetails details = invitationDao.details(id, userId);
		if (details == null) {
			throw new BXJException("没有找到邀请函");
		}
		return details;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void update(Invitation model) {
		try {
			invitationDao.update(model);
		} catch (DuplicateKeyException e) {
			throw new BXJException("已有同名邀请函，请使用其他名称");
		}
	}

}
