package com.hangjia.bxj.service.usercard;

import com.hangjia.bxj.vo.PlanUserCard;
import com.hangjia.bxj.vo.PlanUserImgVo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserCardService
 * @Description: 用户名片业务
 * @author: bei.zhang
 * @date: 2016年4月14日 下午4:15:34
 */
public interface UserCardService {

	/**
	 * @Title: queryAllCompanyAndPosition
	 * @Description: 获取所有公司和职位信息
	 * @return
	 */
	List<Map<String, Object>> queryAllCompanyAndPosition();

	/**
	 * @Title: queryPlanUserCardById
	 * @Description: 根据用户ID获取用户名片信息
	 * @param userId
	 * @return
	 */
	PlanUserCard queryPlanUserCardById(Integer userId);

	/**
	 * @Title: savaPlanUserCard
	 * @Description: 保存用户名片
	 * @param planUserCard
	 * @return
	 */
	PlanUserCard savaPlanUserCard(PlanUserCard planUserCard);

	/**
	 * @Title: queryAllJobYears
	 * @Description: 获取所有就业年限
	 * @return
	 */
	List<Map<String, String>> queryAllJobYears();

	/**
	 * @Title: deleteUserImg
	 * @Description: 删除用户图片
	 * @param userImg
	 * @return
	 */
	PlanUserCard deleteUserImg(PlanUserImgVo userImg);

}
