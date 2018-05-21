package com.hangjia.bxj.mvc.controller.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.compare.ResourceComparator;
import com.hangjia.bxj.vo.right.ResourceDTO;

/** 
 * 菜单拦截
* @author  作者 : yaoy
* @date 2016年5月11日 下午1:25:18 
* @version 1.0 
*/

public class MenuInterceptor implements HandlerInterceptor {

    private List<String> excludedUrls;

	@Value("${bxj_path}")
	private String baseUrl;
	
    @Resource
    private IRightService rightService;
    
    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
    
	/**
	 * (菜单拦截)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		menuInit(request);
		return true;
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
	 * 初始化
	 * @return menuList
	 */
    private void menuInit(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	LoginUser loginUser = (LoginUser)session.getAttribute(AdminConstants.LOGIN_USER);
        Long userId = loginUser.getId().longValue();
        String userCode = loginUser.getUserCode();
        String currentTarget = request.getServletPath();

        List<ResourceDTO> menuList = new ArrayList<ResourceDTO>();
        List<ResourceDTO> sysList = new ArrayList<ResourceDTO>();
        List<ResourceDTO> ResourceVoList = null;
        if (AdminConstants.ADMIN_NAME.equals(userCode)) {
            ResourceVoList = rightService.queryAllResourceList(AdminConstants.getNavCodes().split(","));
        }
        else {
            ResourceVoList = rightService.queryUserResourceList(AdminConstants.getNavCodes().split(","), userId);
        }
        ResourceDTO sysResourceDto =  this.generateMenu(menuList, ResourceVoList, currentTarget);
        this.getSysList(menuList, sysList,sysResourceDto);
        request.setAttribute("menuList", menuList);
        request.setAttribute("sysList", sysList);
    }
    
    
    /**
     * 获取系统名称
    * @author yuanxin
    * @date 2017年6月8日上午9:59:43
    * @version <b>1.0.0</b>
    * @return
     */
    private void getSysList(List<ResourceDTO> menuList,List<ResourceDTO> sysList,ResourceDTO sysResourceDto){
    	for(String st : AdminConstants.getNavCodes().split(",")){
    		for(ResourceDTO resourceDTO : menuList){
        		if(resourceDTO.getNavCode().equals(st)){
        			sysList.add(resourceDTO);
        			break;
        		}
        	}
    	}
    	if(null != sysResourceDto){
    		int count = 0;
    		for(ResourceDTO resourceDTO : sysList){
        		if(resourceDTO.getNavCode().equals(sysResourceDto.getNavCode())){
        			break;
        		}
        		count++;
        	}
    		sysList.set(count, sysResourceDto);
    	}
    	
    }

    /**
     * 生成系统菜单并且返回当前选中菜单
    * @author yuanxin
    * @date 2017年6月8日下午12:32:00
    * @version <b>1.0.0</b>
    * @return ResourceDTO
     */
    private ResourceDTO generateMenu(List<ResourceDTO> menuList, List<ResourceDTO> ResourceVoList,
            String currentTarget) {
    	ResourceDTO sysResourceDto = null;
        Map<Long, ResourceDTO> menuMap = new HashMap<Long, ResourceDTO>();
        ResourceComparator comparator = new ResourceComparator();
        if (null != ResourceVoList && ResourceVoList.size() > 0) {
            for (ResourceDTO ResourceVo : ResourceVoList) {
                menuMap.put(ResourceVo.getId(), ResourceVo);
                if (StringUtils.isNotBlank(currentTarget)
                        && currentTarget.equals(ResourceVo.getResourceUrl())) {
                    ResourceVo.setActive(true);
                    sysResourceDto = ResourceVo;
                }
            }
            for (ResourceDTO ResourceVo : ResourceVoList) {
                if ("1".equals(ResourceVo.getMenuShow())) {
                    if (0 == ResourceVo.getParentId().intValue()) {
                        menuList.add(ResourceVo);
                    }
                    else {
                        ResourceDTO parent = menuMap.get(ResourceVo.getParentId().longValue());
                        if (null != parent) {
                            parent.addResource(ResourceVo);
                            // 排序
                            Collections.sort(parent.getChildren(), comparator);
                        }
                    }
                }
            }
        }
        // 排序
        Collections.sort(menuList, comparator);
        return sysResourceDto;
    }
}
