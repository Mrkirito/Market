package com.znb.cms.mvc.insureBook;

import com.core.cms.common.Result;
import com.core.cms.model.mapper.InsureBook;
import com.znb.cms.service.IInsureBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 在线预约的相关控制
 * Created by Administrator on 2017/7/31.
 */
@Controller
@RequestMapping("/insureBook")
public class insureBookingController {
    @Autowired
    private IInsureBookService insureBookService;

    /**
     *
     * @author mt
     * @date 2017年7月26日下午4:56:56
     * 在线预约首页
     */
    @RequestMapping("index.jhtml")
    public ModelAndView index() {
        ModelAndView view=new ModelAndView("insureBook/bookinglist");
        return view;
    }

    /**
     *
     * @author mt
     * @date 2017年7月26日下午4:57:59
     * 查询符合条件客户
     * @return
     */
    @RequestMapping("/list.json")
    @ResponseBody
    public Result suggestionList(InsureBook insureBook) {
        Result result = new Result();
        int count =insureBookService.selectCount(insureBook);
        if (count > 0) {
            List<InsureBook> list= insureBookService.selectInsureBook(insureBook);
            result.setModel(list);
        }
        insureBook.setTotalItem(count);
        result.setQuery(insureBook);
        return result;
    }

    /**
     * 修改白名单客户信息
     * @param insureBook
     * @return
     */
    @RequestMapping("/update.json")
    @ResponseBody
    public Result updateInsureMessage(InsureBook insureBook) {
        Result result = new Result();
        insureBook.setUpdateTime(new Date());
        insureBookService.update(insureBook);
        result.setSuccess(true);
        result.setMsg("修改成功");
        return result;
    }


    /**
     * @author mt
     * @date 2017年5月18日下午4:58:35
     * 查询
     * @return
     */
    @RequestMapping("/query.json")
    @ResponseBody
    public Result del(Integer id) {
        Result result = new Result();
        List insureBookList = insureBookService.selectByprimaryKey(id);
        result.setModel(insureBookList.get(0));
        return result;
    }

    /**
     * @author mt
     * @date 2017年5月18日下午4:58:35
     * 删除
     * @return
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public Result query(Integer id) {
        Result result = new Result();
        insureBookService.del(id);
        result.setSuccess(true);
        result.setMsg("作废成功");
        return result;
    }

    /**
     * 添加
     * @param insureBook
     * @return
     */
    @RequestMapping("/insert.json")
    @ResponseBody
    public Result insertInsureMessage(InsureBook insureBook) {
        Result result = new Result();
        insureBook.setCreateTime(new Date());
        insureBookService.insert(insureBook);
        result.setSuccess(true);
        result.setMsg("添加成功");
        return result;
    }
}
