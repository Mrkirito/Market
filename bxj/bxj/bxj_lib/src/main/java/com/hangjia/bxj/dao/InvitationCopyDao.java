package com.hangjia.bxj.dao;

import java.util.Map;

import com.hangjia.bxj.model.InvitationCopy;

/**
 * 邀请函  保持修改记录  
 * @ClassName: InvitationDaoCopy
 * @Description: 
 * @author: he-Yi
 * @date: 2016年4月22日 下午1:48:44
 */
public interface InvitationCopyDao {
	
	/**
	 * 保存 修改后邀请函。
	 * @param invitationCopy
	 */
	void save(InvitationCopy invitationCopy);
	
	/**
	 * 获取分享 邀请函内容 
	 * @param invitationCopy
	 * @return
	 */
	InvitationCopy getInviteCopy(InvitationCopy invitationCopy);
	/**
	 * 根据id获取分享 
	 * @param invitationCopy
	 * @return
	 */
	InvitationCopy selInviteShareByID(Map<String, Object> map);
	
	/**
	 * 查询分享的邀请函对象
	 * @param invitationCopy
	 * @return
	 */
	InvitationCopy getInviteCopyOne(InvitationCopy invitationCopy);
	/**
	 * 更新分享的邀请函浏览次数
	 * @param invitationCopy
	 * @return
	 */
	int updateInviteCopyCount(InvitationCopy invitationCopy);

}
