package com.hangjia.bxj.service.invitation;

import java.util.List;

import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationDetails;
import com.hangjia.bxj.model.InvitationSimple;

/**
 * 邀请函
 * @author K9999
 *
 */
public interface InvitationService {

	/**
	 * 用户（创建）的邀请函列表。
	 * @param userId 用户ID，指定查询哪个用户的记录。
	 * @return
	 */
	List<Invitation> list(Integer userId);
	
	void save(Invitation model);

	/**
	 * 删除指定用户的邀请函。
	 * @param id 邀请函ID。
	 * @param userId 用户ID。
	 * @return 删除的记录数。因为ID是唯一的，因此返回结果只可能是0（没有符合条件的记录）或1（删除1条记录）。
	 */
	int delete(Long id, Integer userId);
	
	/**
	 * 查询指定用户的【邀请函】列表（简易数据）。
	 * @param userId
	 * @return
	 */
	List<InvitationSimple> listSimple(Integer userId);
	
	Invitation getUserInvitationNotNull(Long id, Integer userId);
	
	InvitationDetails details(Long id, Integer userId);

	void update(Invitation model);
	
}
