package com.hangjia.bxj.service.idea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.MemberIdeaDao;
import com.hangjia.bxj.model.MemberIdea;

@Service
@Transactional(rollbackFor=Throwable.class)
public class MemberIdeaServiceImpl implements MemberIdeaService {

	@Autowired
	private MemberIdeaDao memberIdeaDao;
	
	/**
	 * 不做重复或限制次数判定。
	 */
	@Override
	public void save(String text, String contact, Integer userId) {
		MemberIdea idea = new MemberIdea();
		idea.setText(text);
		idea.setContactInformation(contact);
		idea.setUserId(userId);
		memberIdeaDao.save(idea);
	}

}
