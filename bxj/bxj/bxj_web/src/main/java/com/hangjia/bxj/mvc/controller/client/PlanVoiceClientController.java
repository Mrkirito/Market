package com.hangjia.bxj.mvc.controller.client;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.model.PlanVoice;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.PlanVoiceService;
import com.hangjia.bxj.upload.VoiceUploadManager;
import com.hangjia.bxj.util.Utils;

/**
 * 客户端录音部分
 * @author K9999
 *
 */
@Controller
@RequestMapping("plan")
public class PlanVoiceClientController {
	@Autowired
	private VoiceUploadManager voiceUploadManager;
	@Autowired
	private PlanVoiceService service;
	
	/**
	 * 录音查询
	 * @param fid
	 * @return
	 */
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="Voice.json", method=RequestMethod.GET)
	public Object getVoice(HttpServletRequest request) {
		int fid = WebUtils.getMemberId(request);
		return new AjaxResult.success(service.getVoice(fid));
	}
	
	/**
	 * 保存录音
	 * @param planVoice
	 * @param request
	 * @return
	 */
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="saveVoice.json")
	public Object saveVoice(PlanVoice planVoice,HttpServletRequest request,MultipartFile file) {
		int fid = WebUtils.getMemberId(request);
		//int fid = 0;
		String path = voiceUploadManager.upload("voice/"+fid, planVoice.getVoiceName(), file);
		Utils ut = new Utils();
		planVoice.setVideoUrl(ut.getProperties("static_path")+"/upload/voice/"+fid+"/"+path);
		planVoice.setUserId(fid);
		service.saveVoice(planVoice);
		/*int id=0;
		if(i==1){
			id=planVoice.getId();
		}*/
		return new AjaxResult.success(planVoice);
	}
	
	
	/**
	 * 录音删除
	 * @param planVoice
	 * @param request
	 * @return
	 */
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="deleteVoice.json", method=RequestMethod.GET)
	public Object deleteVoice(PlanVoice planVoice,HttpServletRequest request) {
		service.deleteVoice(planVoice);
		return new AjaxResult.success();
	}
	
	
	
	/**
	 * 录音重命名
	 * @param planVoice
	 * @param request
	 * @return
	 */
	@Login(AuthenType.json)
	@ResponseBody
	@RequestMapping(value="renameVoice.json", method=RequestMethod.GET)
	public Object renameVoice(PlanVoice planVoice,HttpServletRequest request) {
		service.renameVoice(planVoice);
		return new AjaxResult.success();
	}
	
}
