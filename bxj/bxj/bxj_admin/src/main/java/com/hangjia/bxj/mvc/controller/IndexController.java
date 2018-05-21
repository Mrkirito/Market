package com.hangjia.bxj.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tain on 2016/3/1.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("index.jhtml")
    public String index() {
        return "index";
    }
}
