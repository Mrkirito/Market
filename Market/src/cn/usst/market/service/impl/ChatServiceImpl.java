package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.annotation.MethodLog;
import cn.usst.market.mapper.ChatMapper;
import cn.usst.market.mapper.CustomerSupportMapper;
import cn.usst.market.po.Chat;
import cn.usst.market.po.ChatVo;
import cn.usst.market.service.ChatService;
@Service("chatService")
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatMapper chatMapper;
	
	@Override
	
	public int insertMessage(Chat chat) {
		// TODO Auto-generated method stub
		return chatMapper.insertMessage(chat);
	}

	@Override
	
	public List<Chat> selectAllChatByCompetitionId(Integer id) {
		// TODO Auto-generated method stub
		return chatMapper.selectAllChatByCompetitionId(id);
	}

	@Override
	public List<Chat> selectChatByPage(ChatVo chatVo) {
		// TODO Auto-generated method stub
		return chatMapper.selectChatByPage(chatVo);
	}

	@Override
	public int getChatCount(Integer id) {
		// TODO Auto-generated method stub
		return chatMapper.getChatCount(id);
	}

}
