package com.hangjia.invite.service;


import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationCopy;
import com.hangjia.bxj.model.InvitationPreview;
import com.hangjia.bxj.vo.Pagination;

/**
 * 邀请函 接口类
 * @ClassName: InviteService
 * @Description: 
 * @author: he-Yi
 * @date: 2016年4月22日 上午10:18:15
 */
public interface InviteService {
	
	/**
	 * 保存邀请函 
	 * @param invitation
	 * @return
	 */
	Invitation save(Invitation invitation);
	
	/**
	 * 是否重复用户邀请名称
	 * @param invitation
	 * @return 1表示成功 0表示失败 2表示用户已经存在相同邀请名称
	 */
	int isExist(Invitation invitation);
	
	/**
	 * 修改邀请函
	 * @param invitation
	 * @return 1表示成功 0表示失败 
	 */
	int update(Invitation invitation);
	
	 /**
     *  查询指定用户的所有邀请函。包含模板字段
     * @param userId
     * @param index 页码
     * @param pageSize 条数
     * @param modelType 模板类型 
     * @param descCol 排序列 
     * @return
     */
	Pagination<Invitation> listpage(Integer userId,Integer modelType,int index,int pageSize,String descCol);
	/**
	 * 查询指定用户的所有制作的邀请函
	 * @param userId
	 * @return
	 */
	int listcount(Integer userId,Integer modelType);
	
	/**
	 * 根据内容 时间 地点等信息  来获取邀请函分享从表的主键 
	 * @param invitation
	 * @return
	 */
	InvitationCopy getInvitation(Invitation invitation);
	/**
	 * 获取 邀请函 分享从表信息 
	 * @param fid 从表id
	 * @return
	 */
	InvitationCopy selInviteShareByID(Integer fid);
	
	/**
	 * 删除邀请函
	 * @param invitation
	 * @return
	 */
	int deleteInvite(Invitation invitation);
	/**
	 * 更新浏览次数 分享出去的邀请函
	 * @param invitation
	 * @return
	 */
	int updateInviteCount(InvitationCopy invitationCopy);
	
	/**
	 * 保存预览邀请函
	 * @param invitation
	 * @return
	 */
	InvitationPreview savePreview(InvitationPreview invitation);
	
	/**
	 * 根据id查询预览邀请函
	 * @param invitation
	 * @return
	 */
	InvitationPreview queryPreviewById(Long id);
	
	/**
	 * 保存邀请函 
	 * @param invitation
	 * @return
	 */
	Invitation queryInviteById(Long id);
}
