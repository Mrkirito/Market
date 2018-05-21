package com.hangjia.bxj.mvc.controller.right;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.right.NavigationDO;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.right.ResourceQuery;
import com.hangjia.bxj.service.right.INavigationService;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.right.ResourceDTO;

/** 
* @author  作者 : yaoy
* @date 2016年5月19日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/right")
public class ResourceController extends BaseModule {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRightService rightService;
	
	@Autowired
	private INavigationService navigationService;
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("resource.jhtml")
    public String resource() {
        return "right/resource";
    }
    
	/**
	 * 查询资源文件
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryResourceList.json")
    public @ResponseBody Result queryResourceList(@ModelAttribute ResourceQuery query) {
        Result result = new Result();
        List<ResourceDTO> resourceList = null;
        int totalCount = 0;
        try {
            totalCount = rightService.queryResourcePageCount(query);
        }
        catch (Exception e) {
            logger.error("", e);
        }

        try {
            if (totalCount > 0) {
            	resourceList = rightService.queryResourcePageData(query);
            }
        }
        catch (Exception e) {
            logger.error("", e);
        }

        query.setTotalItem(totalCount);
        result.setModel(resourceList);
        result.setQuery(query);
        return result;
    }
    
	/**
	 * 查询资源文件树
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryResourceTree.json")
    public @ResponseBody Result queryResourceTree(@ModelAttribute ResourceQuery query) {
        Result result = new Result();
        try {
            int showNav = query.getShowNav() == null ? 1 : query.getShowNav();
            String navCode = query.getNavCode();
            List<ResourceDTO> resourceList = new ArrayList<ResourceDTO>();
            if(1 == showNav) {
                List<NavigationDO> NavigationList = navigationService.queryNavigationList();
                if (null != NavigationList && NavigationList.size() > 0) {
                    for (NavigationDO navigation : NavigationList) {
                    	ResourceDTO resource = new ResourceDTO();
                    	resource.setNodeId(navigation.getNavCode());
                    	resource.setNodeName(navigation.getNavName() + "(系统)");
                    	resource.setResourceName(navigation.getNavName());
                    	resource.setParentId(0);
                    	resource.setType("nav");
                        resourceList.add(resource);
                    }
                }
            }

            List<ResourceDTO> resourceVoList = rightService.queryResourceList(navCode);
            if (null != resourceVoList && resourceVoList.size() > 0) {
                for(ResourceDTO resourceVo : resourceVoList) {
                    if(0 == resourceVo.getParentId().intValue()) {
                    	resourceVo.setPId(resourceVo.getNavCode());
                    } else {
                    	resourceVo.setPId(resourceVo.getParentId().toString());
                    }
                    resourceList.add(resourceVo);
                }
            }
            result.setModel(resourceList);
        }
        catch (Exception e) {
            logger.error("", e);
        }
        return result;
    }
    
	/**
	 * 保存
	 * @param resourceVo
	 * @return Result
	 */
    @RequestMapping("saveResource.json")
    public @ResponseBody Result saveResource(@ModelAttribute ResourceDTO resourceVo) {
        Result result = new Result();
        try {
        	resourceVo.setUpdateName(getLoginUserName());
            rightService.saveResource(resourceVo);
        }
        catch (Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setMsg("保存失败");
        }

        return result;
    }

	/**
	 * 删除
	 * @param resourceVo
	 * @return Result
	 */
    @RequestMapping("deleteResource.json")
    public @ResponseBody Result deleteResource(@RequestParam("idList[]") List<String> idList) {
        Result result = new Result();
        try {
            rightService.deleteResource(idList);
        }
        catch (Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setMsg("保存失败");
        }

        return result;
    }
    
	/**
	 * 查询系统
	 * @param resourceVo
	 * @return Result
	 */
    @RequestMapping("queryNavigationList.json")
    public @ResponseBody Result queryNavigationList() {
        Result result = new Result();
        try {
            List<NavigationDO> navigationList = navigationService.queryNavigationList();
            result.setModel(navigationList);
        } catch (Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setMsg("查询失败");
        }

        return result;
    }
}
