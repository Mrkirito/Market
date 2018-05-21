package com.hangjia.bxj.mvc.controller.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.framework.utils.Md5Utility;
import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.service.LoginService;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.compare.ResourceComparator;
import com.hangjia.bxj.vo.right.ResourceDTO;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
public class LoginController extends BaseLogin {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
    private IRightService rightService;
    
	@Value("${bxj_path}")
	private String baseUrl;
	
	/**
	 * 跳转页面
	 * @return url
	 */
	@RequestMapping(value={"/login.jhtml"})
    public ModelAndView toLogin(String returnUrl) {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/login");
		view.addObject("redirectUrl", returnUrl);
        return view;
    }
    
	/**
	 * 登录
	 * @return url
	 */
	
	@RequestMapping(value={"/login.json"})
    public String login(LoginUser user, HttpSession session, String redirectUrl, HttpServletRequest request) {
		LoginUser loginResult = this.loginService.queryUserByCode(user);
		String pwd_md5 = Md5Utility.getMD5String(user.getPassword() == null ? "" : user.getPassword());
		if(null != loginResult && pwd_md5.equals(loginResult.getPassword())){
			if(0 == loginResult.getStatus().intValue()){
				// TODO 无效的账号
				logger.error("无效的账号");
			} else {
				addSessionParams(session, loginResult);
				logger.error("登录成功，用户名为：" + loginResult.getUserCode());
				menuInit(request);
				if(StringUtils.isEmpty(redirectUrl)){
					return "index";
				} else {
					return "redirect:" + redirectUrl;
				}
			}
		}
		logger.error("登录失败，用户名为：" + (loginResult == null ? "" : loginResult.getUserCode()));
		return "login/login";
    }
	public static void main(String[] args) {
		String[] s={"zengsiyuan1234","liuchunbing8888","yangaoxiang4321","xiayongqin6543","qiuguangwen9876","lixifeng5678","helu4567"};
		for (int i = 0; i < s.length; i++) {
			System.err.println(Md5Utility.getMD5String(s[i]));
		}
	}
	/**
	 * 初始化
	 * @return menuList
	 */
    private void menuInit(HttpServletRequest request) {
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
    
	/**
	 * 退出
	 * @return Result
	 */
    @RequestMapping("/logout.json")
    public String logout(HttpSession session) throws IOException {
        String isLogin = (String)session.getAttribute(AdminConstants.IS_LOGIN);
        if(!StringUtils.isBlank(isLogin) && StringUtils.equals(isLogin, "true")){
            session.invalidate();
        }
        return "login/login";
    }
}
