package com.hangjia.bxj.mvc.controller.client;

import com.baobao.framework.support.Base64Utility;
import com.baobao.framework.support.utility.Configuration;
import com.baobao.framework.utils.xxtea.XXTEA;
import com.baobao.sso.client.UserCardRespDto;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.common.Constants;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.my.MyVideoVoucherService;
import com.hangjia.bxj.util.Base64Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * MyVideoVoucherController 我的
 *
 * @ClassName: MyVideoVoucherController
 * @Description: 我的收藏及视频
 * @author: he-Yi
 * @date: 2016年4月14日 上午11:54:31
 */
@Controller
@RequestMapping("my")
public class MyVideoVoucherController {
	private static Logger log = LoggerFactory.getLogger(MyVideoVoucherController.class);
	@Autowired
	private MyVideoVoucherService myVideoVoucherService;
	@Autowired
	private UserCardSupportService userCardSupportService;
	@Value("${bxj_path}")
	private String bxjPath;

	/**
	 * 收藏列表
	 *
	 * @param index
	 *            页码
	 * @param pageSize
	 *            当前页码返回最多数量
	 * @return
	 */
	@RequestMapping(value = "myCollectList.json", method = RequestMethod.GET)
	@ResponseBody
	public Object myCollectList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer index,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		return new AjaxResult.success(myVideoVoucherService.myCollectList(request, userId.longValue(), index, pageSize));
	}

	/**
	 * 取消或收藏视频
	 */
	@RequestMapping(value = "addOrCancel.json", method = RequestMethod.GET)
	@ResponseBody
	public Object cancel(HttpServletRequest request, Long videoId, Long type) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		log.debug("添加取消收藏  userID{" + userId + "},videoId{" + videoId + "},type{" + type + "}");
		return new AjaxResult.success(myVideoVoucherService.storeOrCancel(userId.longValue(), videoId, type));
	}

	/**
	 * 批量取消收藏
	 *
	 * @param ids
	 *            视频id组
	 * @return
	 */
	@RequestMapping(value = "cancelAll.json", method = RequestMethod.GET)
	@ResponseBody
	public Object cancelAll(HttpServletRequest request, String ids) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		log.debug("批量取消收藏 userID{" + userId + "},videoId{" + ids + "}");
		return new AjaxResult.success(myVideoVoucherService.cancelVideo(ids, userId.longValue()));
	}

	/**
	 * 获取用户视频券总数
	 *
	 * @param videoId
	 * @return
	 */
	@RequestMapping(value = "qryVoucherOnlyTotal.json", method = RequestMethod.GET)
	@ResponseBody
	public Object queryUserVoucherTotal(HttpServletRequest request, Long videoId) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		return new AjaxResult.success(myVideoVoucherService.queryUserVoucherTotal(userId.longValue(), videoId));
	}

	/**
	 * 我的视频
	 *
	 * @param index
	 *            页码
	 * @param pageSize
	 *            当前页码返回最多数量
	 * @return
	 */
	@RequestMapping(value = "myStoreList.json", method = RequestMethod.GET)
	@ResponseBody
	public Object myStoreList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer index,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		return new AjaxResult.success(myVideoVoucherService.myStoreList(request, userId.longValue(), index, pageSize));
	}

	/**
	 * 加载用户信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userIsLogin.json")
	@ResponseBody
	// @Login(AuthenType.json)
	public Object queryUserInfo(HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		respMap.put("isLogin", true); // 已登陆
		respMap.put("loginUrl",
				Configuration.getProperty("sso.server.login.url") + "?sysid=" + Configuration.getProperty("sysid")
						+ "&redirect=" + URLEncoder.encode(Configuration.getProperty("close_url")));
		String phone = null;
		// 先判断是否登陆

		try {
			userId = WebUtils.getMemberId(request);
			phone = WebUtils.getMobile(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		} else {
			respMap.put("isLogin", true); // 已登陆

			// 查询用户信息
			UserCardRespDto userInfo = myVideoVoucherService.getUserBaseInfoByUserId(userId);
			String shareUrl = shareUrl = bxjPath + "/planUserCard/" + Base64Util.encode((userId+"").getBytes()) + "/shareUserCard.page?o=" + Base64Util.encode((Constants.USER_ENTRANCE+userId).getBytes());
			if (userInfo != null) { // 存在用户 名片信息
				// if(userInfo.getPhone()==null) //用户名片表 中 手机号 不需要
				if (StringUtils.isBlank(userInfo.getPhone())) {
					userInfo.setPhone(phone);
				}
				respMap.put("phone", userInfo.getPhone());
				respMap.put("name", userInfo.getName());
				try {
					if (userCardSupportService.getUserCardByFid(userId) != null) {
						respMap.put("company", userInfo.getCompany());
					} else {
						respMap.put("company", "全部公司");
					}
				} catch (Exception ex) {
					respMap.put("company", "全部公司");
				}
				if (StringUtils.isBlank(userInfo.getPhoto())){
					respMap.put("photo", "");
				}else {
					respMap.put("photo", userInfo.getPhoto());
				}
				respMap.put("qrcode", userInfo.getQrcode());
				respMap.put("qrcode", shareUrl);
				respMap.put("shareUrl", shareUrl);
				respMap.put("isLecturer", userInfo.getIsLecturer());
				respMap.put("userCode", userInfo.getUserCode());
				respMap.put("existUserInfo", true);
			} else {
				respMap.put("existUserInfo", false); // 不存在用户 名片信息
				respMap.put("phone", phone);
				respMap.put("name", "BXJ" + phone.substring(5, 11));
				respMap.put("company", "全部公司");
				respMap.put("shareUrl", "");
				respMap.put("photo", "");
				respMap.put("qrcode", shareUrl);
				respMap.put("shareUrl", shareUrl);
//				respMap.put("isLecturer", userInfo.getIsLecturer());
//				respMap.put("userCode", userInfo.getUserCode());
			}
			respMap.put("userId", userId); // 用户id
		}
		return new AjaxResult.success(respMap);
	}

	/**
	 * 修改昵称
	 *
	 * @param nickName
	 *            用户昵称
	 * @return
	 */
	@RequestMapping(value = "updateNickName.json", method = RequestMethod.GET)
	@ResponseBody
	public Object updateMyNickName(HttpServletRequest request, String nickName) {
		log.info("------nickName:{}", nickName);
		if (nickName != null && !nickName.equals("") && nickName.length() > 10) {
			nickName = nickName.substring(0, 10); // 超过长度截取昵称长度
		}
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		String phone = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
			phone = WebUtils.getMobile(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		// userId.longValue(),nickName
		int resultNum = myVideoVoucherService.updateNickName(userId.longValue(), nickName, null, phone);
		respMap.put("updateState", resultNum);
		return new AjaxResult.success(respMap);
	}

	/**
	 * 修改头像
	 *
	 * @param fileImg
	 *            用户头像地址
	 * @return
	 */
	@RequestMapping(value = "updatePhoto.json", method = RequestMethod.POST)
	@ResponseBody
	public Object updateMyPhoto(HttpServletRequest request, MultipartFile fileImg) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		String phone = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
			phone = WebUtils.getMobile(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}

		try {
			InputStream inputStream = fileImg.getInputStream();
			// 字节数组
			byte[] imgBytes = new byte[inputStream.available()];
			while (inputStream.available() != 0) {
				for (int i = 0; i < imgBytes.length; i++) {
					imgBytes[i] = (byte) inputStream.read();
				}
			}

			inputStream.close();
			// 调用修改方法
			respMap.put("updateState", myVideoVoucherService.updatePhoto(userId.longValue(), imgBytes, phone));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			respMap.put("updateState", 0);
		}

		return new AjaxResult.success(respMap);
	}

	/**
	 * 保持二维码
	 *
	 * @param qrcode
	 *            用户二维码
	 * @return
	 */
	@RequestMapping(value = "saveMyQrcode.json", method = RequestMethod.GET)
	@ResponseBody
	public Object updateMyqrcode(HttpServletRequest request, String qrcode) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		String phone = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
			phone = WebUtils.getMobile(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		// userId.longValue(),qrcode
		int resultNum = myVideoVoucherService.updateNickName(userId.longValue(), null, qrcode, phone);
		respMap.put("updateState", resultNum);
		return new AjaxResult.success(respMap);
	}

	/**
	 * 退出登录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "logout.json", method = RequestMethod.GET)
	@ResponseBody
	public Object logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("logoutStatus", myVideoVoucherService.logout(request, response));
		return new AjaxResult.success(respMap);
	}
}
