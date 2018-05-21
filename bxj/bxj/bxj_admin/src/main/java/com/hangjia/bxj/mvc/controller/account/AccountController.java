package com.hangjia.bxj.mvc.controller.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baobao.framework.utils.Md5Utility;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.account.UserDO;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.mvc.common.account.AccountForm;
import com.hangjia.bxj.query.account.UserQuery;
import com.hangjia.bxj.service.account.IRoleService;
import com.hangjia.bxj.service.account.IUserService;
import com.hangjia.bxj.vo.account.RoleDTO;
import com.hangjia.bxj.vo.account.UserDTO;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/account")
public class AccountController extends BaseModule {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    
    private BeanCopier userCopierDTO2DO = BeanCopier.create(UserDTO.class, UserDO.class, false);
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("accountList.jhtml")
    public String accountList() {
        return "account/accountList";
    }
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("profile.jhtml")
    public String profile() {
        return "account/profile";
    }
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("accountEdit.jhtml")
    public String accountEdit(Long id, HttpServletRequest request) {

        UserDO userDO = userService.queryUserDOById(id);
        if (null != userDO) {
            // 查询帐号角色权限
            List<RoleDTO> roleList = roleService.queryUserRoleTree(id);
            String roleJsonStr = JSONObject.toJSONString(roleList);
            userDO.setRoleJsonStr(roleJsonStr);
            request.setAttribute("account", userDO);
        }
        return "account/accountEdit";
    }
    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("accountAdd.jhtml")
    public String accountAdd() {
        return "account/accountAdd";
    }
    
	/**
	 * 查询用户列表
	 * @return url
	 */
    @RequestMapping("queryAccountList.json")
    public @ResponseBody Result queryAccountList(UserQuery query) {
        Result result = new Result();
        int totalCount = 0;
        List<UserDTO> userDTOList = null;
        totalCount = userService.queryPageCount(query);

        if(totalCount > 0) {
            userDTOList = userService.queryPageData(query);
        }

        query.setTotalItem(totalCount);
        result.setModel(userDTOList);
        result.setQuery(query);
        return result;
    }

	/**
	 * 查询用户列表
	 * @return url
	 */
    @RequestMapping("queryRoleUserPageData.json")
    public @ResponseBody Result queryRoleUserPageData(UserQuery query) {
        Result result = new Result();
        int totalCount = 0;
        List<UserDTO> userDTOList = null;
        try {
            totalCount = userService.queryUserPageCount(query);
        } catch (Exception e) {
            logger.error("", e);
        }

        try {
            if(totalCount > 0) {
                userDTOList = userService.queryUserPageData(query);
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        query.setTotalItem(totalCount);
        result.setModel(userDTOList);
        result.setQuery(query);
        return result;
    }

	/**
	 * 新增/修改用户
	 * @return Result
	 */
    @RequestMapping("accountEdit.json")
    public @ResponseBody Result accountEdit(UserDTO userDTO) {
        Result result = new Result();
        UserDO userDO = new UserDO();
        userCopierDTO2DO.copy(userDTO, userDO, null);
        // 更新信息
        if(null != userDTO.getId()){
        	// 检查value
        	if(checkField(userDO, result, false)){
        		userDTO.setNewPassword(userDO.getNewPassword());
        		userService.updateUser(userDTO);
        	} else {
        		result.setSuccess(false);
        	}
        // 新增
        } else {
        	// 检查value
        	if(checkField(userDO, result, true)){
        		userDTO.setPassword(userDO.getNewPassword());
            	userService.saveUser(userDTO);
        	} else {
        		result.setSuccess(false);
        	}
        }
        return result;
    }

	/**
	 * 修改密码
	 * @return Result
	 */
    @RequestMapping("changePasswd.json")
    public @ResponseBody Result changePasswd(UserDTO userDTO) {
        Result result = new Result();
        userDTO.setNewPassword(Md5Utility.getMD5String(userDTO.getNewPassword()));
        userDTO.setPassword(Md5Utility.getMD5String(userDTO.getPassword()));
        userDTO.setRePassword(Md5Utility.getMD5String(userDTO.getRePassword()));

        UserDTO userDTO1 = null;
        try {
            userDTO1 = userService.queryUserById(userDTO.getId());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg("修改密码失败：系统错误");
        }

        if(result.isSuccess()) {
            if(null == userDTO1) {
                result.setSuccess(false);
                result.setMsg("修改密码失败：用户不存在");
            } else if(!userDTO1.getPassword().equals(userDTO.getPassword())) {
                result.setSuccess(false);
                result.setMsg("修改密码失败：当前密码错误");
            } else if(!userDTO.getNewPassword().equals(userDTO.getRePassword())) {
                result.setSuccess(false);
                result.setMsg("修改密码失败：确认密码必须和密码相同");
            } else {
                try {
                    userService.changePasswd(userDTO);
                } catch (Exception e) {
                    logger.error("", e);
                    result.setSuccess(false);
                    String msg = "密码修改失败";
                    if (StringUtils.isNotBlank(e.getMessage())) {
                        msg = e.getMessage();
                    }
                    result.setMsg(msg);
                }
            }
        }
        return result;
    }

    /**
	 * 禁用用户
	 * @return Result
	 */
    @RequestMapping("deleteUser.json")
    public @ResponseBody Result deleteUser(@RequestParam("idList[]") List<String> idList) {
        Result result = new Result();
        userService.deleteUser(idList);
        return result;
    }

    /**
	 * 重置密码
	 * @return Result
	 */
    @RequestMapping("resetPasswd.json")
    public @ResponseBody Result resetPasswd(Long id) {
        Result result = new Result();
        if(null != id && 0 != id.longValue()) {
            userService.resetPasswd(id, Md5Utility.getMD5String(AdminConstants.DEFAULT_PASSWD));
        } else {
            result.setSuccess(false);
            result.setMsg("重置密码失败!用户不存在或已被删除");
        }
        return result;
    }

	/**
	 * 启用用户
	 * @return Result
	 */
    @RequestMapping("enableUser.json")
    public @ResponseBody Result enableUser(Long id) {
        Result result = new Result();
        userService.enableUser(id, true);
        return result;
    }
    
    
    /**
     * 检查字段
     * 
     * @param account
     * @param customErrors
     * @return
     */
    private boolean checkField(UserDO account, Result result, boolean force) {
        // 检查密码
        if (checkPassword(account, result, force)) {
            // 检查帐号是否已存在
            if (checkUserCode(account, result)) {
                // 检查昵称是否已存在
                if (checkUserName(account, result)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 检查密码
     * 
     * @param account
     * @param customErrors
     * @param force 强制输入密码
     * @return
     */
    private boolean checkPassword(UserDO account, Result result, boolean force) {
        if (StringUtils.isNotBlank(account.getPassword()) && StringUtils.isNotBlank(account.getRePassword())) {
            if (!account.getPassword().equals(account.getRePassword())) {
            	result.setMsg(AccountForm.PASSWORD_NOT_EQUAL);
                return false;
            }
            else {
                account.setNewPassword(Md5Utility.getMD5String(account.getPassword()));
            }
        }
        else {
            if(force) {
            	result.setMsg(AccountForm.PASSWORD_NULL);
                return false;
            }
        }
        return true;
    }

    /**
     * 检查帐号
     * 
     * @param account
     * @param customErrors
     * @return
     */
    private boolean checkUserCode(UserDO account, Result result) {
        try {
            if (userService.existsUserByCode(account)) {
            	result.setMsg(AccountForm.EXISTS_USERCODE);
            }
        }
        catch (Exception e) {
            logger.error("", e);
            result.setMsg(AccountForm.ADD_ACCOUNT_ERROR);
        }
        return true;
    }


    /**
     * 检查昵称
     * 
     * @param account
     * @param customErrors
     * @return
     */
    private boolean checkUserName(UserDO account, Result result) {
        try {
            if (userService.existsUserByName(account)) {
            	result.setMsg(AccountForm.EXISTS_USERNAME);
            }
        }
        catch (Exception e) {
            logger.error("", e);
            result.setMsg(AccountForm.ADD_ACCOUNT_ERROR);
        }
        return true;
    }
}
