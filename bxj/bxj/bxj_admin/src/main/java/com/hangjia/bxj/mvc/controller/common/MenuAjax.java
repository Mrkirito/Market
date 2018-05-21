package com.hangjia.bxj.mvc.controller.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.compare.ResourceComparator;
import com.hangjia.bxj.vo.right.ResourceDTO;


/**
 * @author yaoy
 * @since 2016-06-16
 */
@Controller
public class MenuAjax extends BaseModule {
	
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IRightService rightService;

	/**
	 * 初始化
	 * @return menuList
	 */
    @RequestMapping("menuInit.json")
    public @ResponseBody List<ResourceDTO> menuInit(HttpServletRequest request) {
        Long userId = getLoginUserId().longValue();
        String userCode = getLoginUserCode();
        String currentTarget = request.getRequestURI();

        List<ResourceDTO> menuList = new ArrayList<ResourceDTO>();
        List<ResourceDTO> sysList = new ArrayList<ResourceDTO>();
        List<ResourceDTO> ResourceVoList = null;
        if (AdminConstants.ADMIN_NAME.equals(userCode)) {
            ResourceVoList = rightService.queryAllResourceList(AdminConstants.getNavCodes().split(","));
        }
        else {
            ResourceVoList = rightService.queryUserResourceList(AdminConstants.getNavCodes().split(","), userId);
        }
        ResourceDTO sysResourceDto = this.generateMenu(menuList, ResourceVoList, currentTarget);
        this.getSysList(menuList, sysList,sysResourceDto);
        request.setAttribute("menuList", menuList);
        request.setAttribute("sysList", sysList);
        return menuList;
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
