package com.hj.cms.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 保保网络科技-bxj
 * com.hj.cms.mvc
 * 作者-秦岭(Tain)
 * 说明：
 * 2017/6/13 12:15
 * 2017保保网络-版权所有
 */
@Controller
@RequestMapping("/hj")
public class HjIndexController {

    @RequestMapping("index.jhtml")
    public ModelAndView index() {
        ModelAndView view=new ModelAndView("hj/index");
        return view;
    }

}
