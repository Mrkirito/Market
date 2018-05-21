package com.hangjia.invite.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationCopy;
import com.hangjia.bxj.model.InvitationPreview;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.invite.service.InviteService;

/**
 * 邀请函 提供h5对应接口
 * 
 * @ClassName: InviteController
 * @Description:
 * @author: he-Yi
 * @date: 2016年4月22日 上午10:13:58
 */
@Controller
@RequestMapping("invitePage")
public class InviteController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InviteService inviteService;

	@Value("${bxj_path}")
	private String bxjPath;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));// "dateAt", hh:mm:ss
    }
    
	@RequestMapping(value = "qryInviteChina.page", method = RequestMethod.GET)
	public String queryInviteShare(HttpServletRequest request, Integer inviteCopyFid) {
		request.setAttribute("invitevo", inviteService.selInviteShareByID(inviteCopyFid));
		return "invitation/invention_china";
	}

	@RequestMapping(value = "qryInviteBusiness.page", method = RequestMethod.GET)
	public String queryInviteBusiness(HttpServletRequest request, Integer inviteCopyFid) {
		request.setAttribute("invitevo", inviteService.selInviteShareByID(inviteCopyFid));
		return "invitation/invention_business";
	}

	@RequestMapping(value = "qryInviteChildren.page", method = RequestMethod.GET)
	public String queryInvitechildren(HttpServletRequest request, Integer inviteCopyFid) {
		request.setAttribute("invitevo", inviteService.selInviteShareByID(inviteCopyFid));
		return "invitation/invention_children";
	}

	@RequestMapping(value = "qryInviteFemale.page", method = RequestMethod.GET)
	public String queryInviteFemale(HttpServletRequest request, Integer inviteCopyFid) {
		request.setAttribute("invitevo", inviteService.selInviteShareByID(inviteCopyFid));
		return "invitation/invention_female";
	}

	/**
	 * 分享 邀请函
	 * 
	 * @param request
	 * @param inviteCopyFid
	 * @param shareType
	 * @return
	 */
	@RequestMapping(value = "qryInviteShareInfo.page", method = RequestMethod.GET)
	public String queryInviteAll(HttpServletRequest request, Integer inviteCopyFid, Integer shareType) {
		request.setAttribute("invitevo", inviteService.selInviteShareByID(inviteCopyFid));
		if (shareType == 1) { // 亲子
			return "invitation/invention_children";
		} else if (shareType == 2) { // 商务
			return "invitation/invention_business";
		} else if (shareType == 3) { // 中国风
			return "invitation/invention_china";
		} else { // 女性模板 (shareType==4)
			return "invitation/invention_female";
		}

	}

	/**
	 * 获取预览链接
	 * 
	 * @param invitation
	 *            预览内容
	 * @return
	 */
	@RequestMapping(value = "toPreviewInvite.json")
	@ResponseBody
	public Object toPreviewInvite(HttpServletRequest request, Integer inviteId) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("state", 1);
		respMap.put("dispatcherUrl", bxjPath + "/invitePage/toInvitePreviewShare.page?inviteId=" + inviteId + "&type=3");
		return new AjaxResult.success(respMap);
	}
	
	/**
	 * 预览
	 * 
	 * @param inviteId
	 *            预览分享id
	 * @param type
	 *            1 预览 2分享
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toInvitePreviewShare.page")
	public String toInvitePreviewShare(Long inviteId, Integer type, HttpServletRequest request) {
		if (null != type) {
			// 预览
			if (type.intValue() == 1) {
				InvitationPreview preview = new InvitationPreview();
				preview = inviteService.queryPreviewById(inviteId);
				// 新加10、11类型的兼容2、6的样式 add 20170316
				if(preview.getModelType() == 10) preview.setModelType(2);
				if(preview.getModelType() == 11) preview.setModelType(6);
				request.setAttribute("invitevo", preview);
				// 分享
			} else if (type.intValue() == 2) {
				request.setAttribute("inviteId", inviteId); //设置邀请函id
				InvitationCopy invitationCopy = inviteService.selInviteShareByID(inviteId.intValue());
				if(invitationCopy.getModelType() == 10) invitationCopy.setModelType(2);
				if(invitationCopy.getModelType() == 11) invitationCopy.setModelType(6);
				request.setAttribute("invitevo", invitationCopy);
				// 已制作预览
			} else if (type.intValue() == 3) {
				Invitation invitation = inviteService.queryInviteById(inviteId);
				if(invitation.getModelType() == 10) invitation.setModelType(2);
				if(invitation.getModelType() == 11) invitation.setModelType(6);
				request.setAttribute("invitevo", invitation);
			}
		}
		request.setAttribute("type", type);
		return "invitation/invention_preview_share";
	}

	
	/**
	 * 增加 分享查看邀请函的消息提醒
	 * @param request
	 * @param inviteId
	 * @return
	 */
	@RequestMapping(value = "addInviteNum.json")
	@ResponseBody
	public Object addInviteNum(HttpServletRequest request, Integer inviteId) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		InvitationCopy invitationCopy=new InvitationCopy();
		invitationCopy.setFid(inviteId.longValue());
		//更新 分享的邀请函浏览次数
		inviteService.updateInviteCount(invitationCopy);
		return new AjaxResult.success(respMap);
	}
	/**
	 * 保存预览
	 * 
	 * @param invitation
	 *            预览内容
	 * @return
	 */
	@RequestMapping(value = "savePreview.json")
	@ResponseBody
	public Object savePreview(InvitationPreview invitationPre, HttpServletRequest request, String OS) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		String phone = null;
		try {
			userId = WebUtils.getMemberId(request); // 先判断是否登陆
			phone = WebUtils.getMobile(request);
		} catch (Exception e) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		if (userId == null) {
			respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
		}
		
 		invitationPre.setUserId(userId); // 用户id
 		invitationPre.setContactMobile(phone); // 用户手机
		if (OS != null) {
			invitationPre.setOs(OS);
		}
		respMap.put("isLogin", true);
		if (invitationPre == null || invitationPre.getLat() == null || invitationPre.getLng() == null) {
			respMap.put("state", 0);
			respMap.put("error", "输入参数(经纬度)不能为空!");
			return new AjaxResult.success(respMap);
		}
		// 是否存在邀请名称重复j
		Invitation invitation = new Invitation();
		BeanUtils.copyProperties(invitationPre, invitation);
//		int numResult = inviteService.isExist(invitation);
//		// 不存在重复名称
//		if (numResult == 0) {
//
//		} else if (numResult == 1) {
//			respMap.put("state", 2); // 存在用户的邀请名称重复
//			return new AjaxResult.success(respMap);
//		}
		inviteService.savePreview(invitationPre); // 保存
		if (invitationPre.getId() == null) {
			respMap.put("error", "邀请函保存异常!");
			return new AjaxResult.success(respMap);
		}
		respMap.put("state", 1);
		respMap.put("isLogin", true);
		respMap.put("dispatcherUrl", bxjPath + "/invitePage/toInvitePreviewShare.page?inviteId=" + invitationPre.getId() + "&type=1");
		return new AjaxResult.success(respMap);
	}
}
