package com.hangjia.friend.mvc;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.friend.service.FriendCircleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/4.
 */
@Controller
@RequestMapping("friend/")
public class FriendCircleController {
    private static Logger log = LoggerFactory.getLogger(FriendCircleController.class);

    @Autowired
    FriendCircleService friendCircleService;
    @Autowired
    UserCardSupportService userCardSupportService;

    @RequestMapping("banner.page")
    public String banner() {
        return "/friend/banner";
    }

    /**
     * 文章详细
     * @return
     */
    @RequestMapping("detail.page")
    public ModelAndView detail(HttpServletRequest request, Long id, Integer userId, String isApp) {
        ModelAndView mv = new ModelAndView("friend/detail");
        mv.addObject("isApp", isApp);
        try {
            userId = WebUtils.getMemberId(request);
        } catch (Exception e) {
            log.info("用户没有登录");
        }
        if(null!=userId && !"".equals(userId) && 0!=userId) mv.addObject("user", userCardSupportService.getUserCardByFid(userId));
        mv.addObject("bean", friendCircleService.getDetail(id));
        return mv;
    }
}
