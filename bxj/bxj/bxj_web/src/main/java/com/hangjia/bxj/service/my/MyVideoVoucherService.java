package com.hangjia.bxj.service.my;

import com.baobao.sso.client.UserCardRespDto;
import com.hangjia.bxj.model.ChampionUserVoucher;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.vo.Pagination;
import com.hangjia.bxj.vo.PlanUserCard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName: MyVideoVoucherService
 * @Description: 我的收藏及视频操作
 * @author: he-Yi
 * @date: 2016年4月14日 下午12:12:54
 */

public interface MyVideoVoucherService {
	/**
	 * 获取我的收藏列表
	 * 
	 * @param userId
	 *            用户id
	 * @param index 开始页码
	 * @param pageSize 当前页码条数
	 * @Title:storeList
	 * @Description:获取我的收藏列表
	 */
	Pagination<ChampionVideo> myCollectList(HttpServletRequest request, Long userId,Integer index, Integer pageSize);
	
	/**
	 * 添加或取消收藏
	 * 
	 * @param userId
	 *            用户id
	 * @param videoId
	 *            视频id
	 * @param type 类型
	 * @Title: storeOrCancel
	 * @Description: 收藏或取消视频
	 */
	int storeOrCancel(Long userId, Long videoId,Long type);

	/**
	 * 批量取消收藏视频
	 * 
	 * @param ids
	 *            视频id
	 * @param userId 用户id 
	 * @Title: cancelVideo
	 * @Description: 批量取消收藏的视频
	 */
	int cancelVideo(String ids,Long userId);

	/**
	 * 获取剩下视频券总数
	 * 
	 * @param userId
	 *            用户id
	 * @param voucherId
	 *            券id
	 * @Title:queryUserVoucherTotal
	 * @Description:剩下视频券总数
	 */
	ChampionUserVoucher queryUserVoucherTotal(Long userId, Long voucherId);

	/**
	 * 邀请人总数
	 * 
	 * @param userId
	 *            用户id
	 * @Title: selectCountInvite
	 * @Description:用户的邀请人总数
	 */
	Map<String, Integer> selectCountInvite(Long userId);
	
	/**
	 * 我的视频
	 * @param userId 用户id
	 * @param index  页码
	 * @param pageSize 当前页码数量
	 * @return
	 */
	Pagination<ChampionVideo> myStoreList(HttpServletRequest request, Long userId,Integer index, Integer pageSize);
	
	/**
	 * 查询用户信息
	 * @param userId 用户id 
	 * @return
	 */
	PlanUserCard queryUserCardById(Integer userId);
	
	/**
	 * 修改昵称  以及 二维码 
	 * @param userId 用户id
	 * @param nickName 昵称
	 * @param phone 手机号码
	 * @return 1 成功 ,0 失败
	 */
	int updateNickName(Long userId,String nickName,String qrcode, String phone);
	
	/**
	 * 用户修改头像 
	 * @param userId 用户id 
	 * @param imgBytes 字节数组
	 * @return  1 成功 ,0 失败
	 */
	int updatePhoto(Long userId,byte[] imgBytes, String phone);

	/**
	 * 退出登录
	 * @param request
	 * @param response
     * @return
     */
	int logout(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 根据用户ID用户用户基本信息
	 *
	 * @param userId
	 * @return
     */
	UserCardRespDto getUserBaseInfoByUserId(Integer userId);
}
