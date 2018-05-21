package com.znb.cms.mvc.userCenter;

import com.hangjia.bxj.query.ucUserCenter.UcUserQuery;
import com.hangjia.bxj.ucenter.model.UcUser;
import com.hangjia.bxj.common.Result;
import com.znb.cms.service.UcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController {

    @Autowired
    private UcUserService ucUserService;

    /**
     * 界面跳转
     * @return
     */
    @RequestMapping("/dataQueryByPage.jhtml")
    public String query(){
        return "userCenter/ucUserList";
    }

    /**
     * 查询用户列表
     * @return url
     */
    @RequestMapping("queryUserDataList.json")
    public @ResponseBody Result queryUserdataList(UcUserQuery query) {
        Result result = new Result();
        int totalCount = 0;
        List<UcUser> ucUserList = null;
        totalCount = ucUserService.queryPageCount(query);

        if(totalCount > 0) {
            ucUserList = ucUserService.queryPageData(query);
        }

        query.setTotalItem(totalCount);
        result.setModel(ucUserList);
        result.setQuery(query);
        return result;
    }

    /**
     * 禁用用户
     * @return Result
     */
    @RequestMapping("deleteUcUser.json")
    public @ResponseBody Result deleteUser(@RequestParam("idList[]") List<String> idList) {
        Result result = new Result();
        ucUserService.deleteUser(idList);
        return result;
    }

    /**
     * 启用用户
     * @return Result
     */
    @RequestMapping("enableUcUser.json")
    public @ResponseBody Result enableUser(Long id) {
        Result result = new Result();
        ucUserService.enableUser(id, true);
        return result;
    }
}
