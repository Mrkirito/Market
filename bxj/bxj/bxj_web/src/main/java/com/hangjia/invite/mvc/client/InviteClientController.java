package com.hangjia.invite.mvc.client;

import com.baobao.sso.client.WebUtils;
import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.invite.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 邀请函  原生
 *
 * @ClassName: InviteClientController
 * @Description:
 * @author: he-Yi
 * @date: 2016年4月22日 上午10:06:39
 */
@Controller
@RequestMapping("inviteClient")
public class InviteClientController {
    @Autowired
    private InviteService inviteService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));// "dateAt", hh:mm:ss
    }

    @RequestMapping(value = "saveInviteLetter.json", method = RequestMethod.POST)
    @ResponseBody
    public Object saveInviteLetter(Invitation invitation, String OS, HttpServletRequest request,@RequestParam(defaultValue = "100") Integer pageSize) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        if(OS!=null)invitation.setOs(OS); 
        Integer userId = null;
        String phone = null;
        try {
            userId = WebUtils.getMemberId(request);  //先判断是否登陆
            phone = WebUtils.getMobile(request);
        } catch (Exception e) {
            respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
        }
        if (userId == null) {
            respMap.put("isLogin", false);
			return new AjaxResult.success(respMap);
        }

        invitation.setUserId(userId); //用户id 
        invitation.setContactMobile(phone); // 用户手机
        respMap.put("isLogin", true);
        if(invitation==null||invitation.getLat()==null||invitation.getLng()==null){
        	respMap.put("state", 0);
        	respMap.put("error", "输入参数(经纬度)不能为空!");
        	return new AjaxResult.success(respMap);
        }
        //修改
        if(invitation.getId()!=null){
        	  int  num = inviteService.update(invitation);
              respMap.put("state", num);             
              respMap.put("inviteArray", inviteService.listpage(userId, null, 1, pageSize,"modify")); //查询 
              return new AjaxResult.success(respMap);
        }else{ //保存
        	 //是否存在邀请名称重复
            //不存在重复名称 
//        	int numResult=inviteService.isExist(invitation);
//            if(numResult==0){
//            	 
//            }else if(numResult==1){
//            	respMap.put("state",2); //存在用户的邀请名称重复
//            	return new AjaxResult.success(respMap);
//            }
            Invitation invitationget =inviteService.save(invitation); //保存 
        	if(invitationget==null){
        		respMap.put("error", "邀请函保存异常!");
        		return new AjaxResult.success(respMap);
        	}
            respMap.put("state", 1);
            respMap.put("isLogin", true);
            respMap.put("inviteArray", inviteService.listpage(userId, null, 1, pageSize,"id")); //查询 
            return new AjaxResult.success(respMap);
        }
        
    }

    @RequestMapping(value = "qryInviteLetterList.json", method = RequestMethod.GET)
    @ResponseBody
    public Object queryInviteLetter(HttpServletRequest request, Integer modelType, @RequestParam(defaultValue = "1") Integer index,
                                    @RequestParam(defaultValue = "4") Integer pageSize) {
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
        respMap.put("isLogin", true);
        respMap.put("inviteArray", inviteService.listpage(userId, modelType, index, pageSize,"modify")); //查询 按最新时间显示
        return new AjaxResult.success(respMap);
    }

    @RequestMapping(value = "updateInviteLetter.json", method = RequestMethod.POST)
    @ResponseBody
    public Object updateInviteLetter(HttpServletRequest request, Invitation invitation) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Integer userId = null;
        int num = 0;
        //先判断是否登陆
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
        try {
            if (invitation.getId() == null) {
                respMap.put("error", "id不能为空！");
                respMap.put("state", num);
                return new AjaxResult.success(respMap);
            }
            invitation.setUserId(userId);
            num = inviteService.update(invitation);
        } catch (Exception e) {
            e.printStackTrace();
            respMap.put("isLogin", false);
            return new AjaxResult.success(respMap);
        }
        respMap.put("state", num);
        respMap.put("isLogin", true);
        return new AjaxResult.success(respMap);
    }

    
    @RequestMapping(value = "getInviteInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public Object getInvite(HttpServletRequest request, Invitation invitation) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        if(invitation.getDateAt()==null){ 
		    respMap.put("error", "传人日期格式不是yyyy-MM-dd hh:mm!");
			return new AjaxResult.success(respMap);
        }
        Integer userId = null;
        //先判断是否登陆
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
        if(invitation.getId()==null){
        	 respMap.put("error", "id不能空");
        	 return new AjaxResult.success(respMap);
        }
        respMap.put("isLogin", true);
        return new AjaxResult.success(inviteService.getInvitation(invitation));
    }
    
    @RequestMapping(value = "deleteInviteLetter.json", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteInviteLetter(HttpServletRequest request, Invitation invitation) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        Integer userId = null;
        int num = 0;
        //先判断是否登陆
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
        try {
            if (invitation.getId() == null) {
                respMap.put("error", "id不能为空！");
                respMap.put("state", num);
                return new AjaxResult.success(respMap);
            }
            invitation.setUserId(userId);
            invitation.setStatus(true); //删除1
            num = inviteService.deleteInvite(invitation);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            respMap.put("isLogin", false);
            return new AjaxResult.success(respMap);
        }
        respMap.put("state", num);
        respMap.put("isLogin", true);
        return new AjaxResult.success(respMap);
    }
    
}
