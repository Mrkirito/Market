package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.Chat;
import cn.usst.market.po.ChatVo;

public interface ChatService {
	public int insertMessage(Chat chat);
	public List<Chat> selectAllChatByCompetitionId(Integer id);
	public List<Chat> selectChatByPage(ChatVo chatVo);
	public int getChatCount(Integer id);
}
