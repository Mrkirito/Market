package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationAppointment;
import com.hangjia.bxj.model.InvitationDetails;
import com.hangjia.bxj.model.InvitationPreview;
import com.hangjia.bxj.model.InvitationSimple;
import com.hangjia.bxj.vo.InviteVo;

/**
 * 邀请函 DAO。
 * @author K9999
 *
 */
public interface InvitationDao {
	
	/**
	 * 保存邀请函。
	 * @param model
	 */
	void save(Invitation model);
	
	/**
	 * 更新指定用户的指定邀请函。
	 * @param model 必须设置 id 和 userId。
	 * @return 更新的记录数。
	 */
	int update(Invitation model);
	
	/**
	 * 删除指定用户的指定邀请函。
	 * @param id 邀请函ID。
	 * @param userId 用户ID。
	 * @return 删除的记录数。
	 */
	int delete(Long id, Integer userId);
	
	/**
	 * 查询指定用户的所有邀请函。
	 * @param userId
	 * @return
	 */
	List<Invitation> list(Integer userId);
	
	/**
	 * 查询简单记录列表。
	 * @param userId
	 * @return
	 */
	List<InvitationSimple> listSimple(Integer userId);
	
	Invitation getUserInvitation(Long id, Integer userId);
	
	/**
	 * 酒会详情，会返回预约情况。
	 * @param id
	 * @param userId
	 * @return
	 */
	InvitationDetails details(Long id, Integer userId);
	
	/**
	 * 根据邀请函Id查询的邀请函
	 * @param id
	 * @return
	 */
	Invitation get(Long id);
	/**
	 * 存储参会信息
	 * @param invitationAppointment
	 * @return
	 */
	int saveInvitationAppointment(InvitationAppointment invitationAppointment);

	int count(Integer userId);

	/**
	 * 查询指定用户的所有邀请函。包含模板字段
	 * @param userId
	 * @return
	 */
	List<Invitation> listpage(Map<String, Object> map);
	
	/**
	 * 我的制作总数 
	 * @param userId
	 * @return
	 */
	int listcount(Map<String, Object> map);
	
	/**
	 * 用户的邀请名称是否重复 
	 * @param map
	 * @return
	 */
	int isExist(Map<String, Object> map);
	
	/**
	 * 获取用户的单个邀请信息
	 * @param map
	 * @return
	 */
	Invitation selectOne(Map<String, Object> map);
	
	/**
	 * 删除邀请函
	 * @param model 参数 根据传入id 以及状态
	 * @return
	 */
	int deleteInvite(Invitation model);
	
	/**
	 * 修改浏览次数 
	 * @param model
	 * @return
	 */
	int updateInviteCount(Invitation model);
	
	/**
	 * 保存预览邀请函。
	 * @param model
	 */
	void savePreview(InvitationPreview model);
	
	/**
	 * 根据id查询预览邀请函。
	 * @param model
	 */
	InvitationPreview queryPreviewById(Long id);
	
	/**
	 * 修改预览邀请函。
	 * @param model
	 */
	void updatePreview(InvitationPreview model);
	
	/**
	 * 根据邀请函id查询预览邀请函。
	 * @param model
	 */
	InvitationPreview queryPreviewByInviteId(Long inviteId);
	
	
	
	
	
	/**邀请函每日生成数**/
	List<InviteVo> queryInviteGenerate(InviteVo vo);
	/**邀请函每日用户数**/
	List<InviteVo> queryInviteUser(InviteVo vo);
	/**邀请函每日分享数**/
	List<InviteVo> queryInviteShare(InviteVo vo);
}
