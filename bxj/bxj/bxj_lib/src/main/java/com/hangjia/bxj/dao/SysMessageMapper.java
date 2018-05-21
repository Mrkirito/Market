package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.SysMessage;
import com.hangjia.bxj.model.message.MessageLetterDO;
import com.hangjia.bxj.model.message.MessagePushDO;

public interface SysMessageMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    SysMessage selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
    
    /**
     * 给app使用第一层系统消息
     */
    List<SysMessage> querySysMessageList(SysMessage record);
    
    /**
     * 给app使用第二层系统消息
     */
    List<SysMessage> querySysMessageDetail(SysMessage record);
    
    /**
     * 批量更新消息阅读状态
     */
    int batchUpdateMsgRead(SysMessage record);
    
	MessageLetterDO queryLetterModuleById(Integer moduleId);
	
	int batchInsertLetter(SysMessage sysMessage);
	
	MessagePushDO queryPushModuleById(Integer moduleId);
}