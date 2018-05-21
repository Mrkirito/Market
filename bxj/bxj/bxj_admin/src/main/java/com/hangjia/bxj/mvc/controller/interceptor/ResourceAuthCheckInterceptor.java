package com.hangjia.bxj.mvc.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.util.AdminWebUtil;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.right.ResourceDTO;

/** 
 * 页面权限拦截
* @author  作者 : yaoy
* @date 2016年5月11日 下午1:25:18 
* @version 1.0 
*/

public class ResourceAuthCheckInterceptor implements HandlerInterceptor {

	@Autowired
	private IRightService rightService;
	
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

        if (hasTargetPermission(target)) {
            boolean hasAuth = false;
            Long userId = AdminWebUtil.getLoginUserId(request);
            String userCode = AdminWebUtil.getLoginUserCode(request);
            if (AdminConstants.ADMIN_NAME.equals(userCode)) {
                hasAuth = true;
            }
            else {
                List<ResourceDTO> userResourceList = rightService.queryUserResourceList(userId);
                if (null != userResourceList && userResourceList.size() > 0) {
                    for (ResourceDTO resourceDTO : userResourceList) {
                        if (null != resourceDTO
                                && StringUtils.isNotBlank(resourceDTO.getResourceUrl())
                                && (resourceDTO.getResourceUrl().trim().equals(target))) {
                            hasAuth = true;
                            break;
                        }
                    }
                }
            }

            if (!hasAuth) {
                return false;
            } else {
            	return true;
            }
        } else {
        	return true;
        }
        
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	
    /**
     * 判断当前访问地址是否是有权限控制
     * 
     * @param target
     * @return
     */
    private boolean hasTargetPermission(String target) {
        if(!checkIgnoreUri(target)) {
            try {
                List<ResourceDTO> resourceList = rightService.queryResourceList(AdminConstants.NAV_BAOXIANJIA_CMS);
                if (CollectionUtils.isNotEmpty(resourceList)) {
                    for (ResourceDTO authDTO : resourceList) {
                        if (StringUtils.isNotBlank(authDTO.getResourceUrl())
                                && (authDTO.getResourceUrl().trim().equals(target)) && 1 == authDTO.getPermissionControl().intValue()) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("查询页面权限列表失败！", e);
                return true;
            }
        }
        return false;
    }

    private boolean checkIgnoreUri(String uri) {
        if(StringUtils.isNotBlank(uri)) {
            String[] suffixArr = {".jpg", ".png", ".jpeg", ".JPG", ".PNG", ".JPEG", "login.jhtml", "logout.json"};
            for (String suffix : suffixArr) {
                if (uri.endsWith(suffix)) {
                    return true;
                }
            }
        }
        return false;
    }
}
