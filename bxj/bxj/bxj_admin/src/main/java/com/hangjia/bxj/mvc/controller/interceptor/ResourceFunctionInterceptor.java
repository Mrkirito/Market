package com.hangjia.bxj.mvc.controller.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.util.AdminWebUtil;
import com.hangjia.bxj.service.account.IRoleService;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.account.RoleDTO;
import com.hangjia.bxj.vo.right.FunctionDTO;

/** 
 * 菜单按钮拦截
* @author  作者 : yaoy
* @date 2016年5月11日 下午1:25:18 
* @version 1.0 
*/

public class ResourceFunctionInterceptor implements HandlerInterceptor {

	@Autowired
	private IRightService rightService;
	
	@Autowired
	private IRoleService roleService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${bxj_path}")
	private String baseUrl;
	
	/**
	 * (权限拦截)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String target = request.getServletPath();
		LoginUser loginUser = AdminWebUtil.getLoginUser(request);
		if(null != loginUser && null != loginUser.getId()){
			Long userId = loginUser.getId().longValue();
			String userName	= loginUser.getUserName();
			// 未登录则不进行查询
	        if(null != userId && 0 != userId.longValue() && StringUtils.isNotBlank(userName)) {
	            List<FunctionDTO> functionDTOList = null;
	            List<Long> userFunctionIdList = null;
	            JSONArray userFunctionList = new JSONArray();
	            try {
	                functionDTOList = rightService.queryResourceFunction(target);
	            } catch (Exception e) {
	                logger.error("", e);
	            }
	            try {
	                userFunctionIdList = rightService.queryUserFunctionId(userId);
	            } catch (Exception e) {
	                logger.error("", e);
	            }

	            if (null != functionDTOList && functionDTOList.size() > 0) {

	                if (null == userFunctionIdList)
	                    userFunctionIdList = new ArrayList<Long>();

	                for (FunctionDTO functionDTO : functionDTOList) {
	                    JSONObject function = new JSONObject();
	                    function.put("rightCode", functionDTO.getFuncCode());
	                    if (AdminConstants.ADMIN_NAME.equals(userName)) {
	                        function.put("disabled", false);
	                    } else {
	                        if (userFunctionIdList.contains(functionDTO.getId().longValue())) {
	                            function.put("disabled", false);
	                        } else {
	                            function.put("disabled", true);
	                        }
	                    }
	                    userFunctionList.add(function);
	                }
	            }
	            request.setAttribute(AdminConstants.USER_FUNCTION, userFunctionList);

	            String currentTarget = target + ".jhtml";
	            request.setAttribute(AdminConstants.CURRENT_TARGET, currentTarget);

	            try {
	                List<RoleDTO> roleList = roleService.queryUserRoleTree(userId);
	                StringBuffer userRoleCode = new StringBuffer();
	                for (RoleDTO r : roleList) {
	                    if (userRoleCode.length() > 0) {
	                        userRoleCode.append(",");
	                    }
	                    userRoleCode.append(r.getRoleCode());
	                }
	                request.setAttribute(AdminConstants.USER_ROLE_CODE_LIST, userRoleCode);
	            } catch (Exception e) {
	                logger.error("", e);
	            }
	            return true;
	        }
		} else {
			return false;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
