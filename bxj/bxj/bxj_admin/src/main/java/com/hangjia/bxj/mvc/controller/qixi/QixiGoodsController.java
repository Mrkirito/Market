package com.hangjia.bxj.mvc.controller.qixi;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.qixi.QixiGoods;
import com.hangjia.bxj.service.qixi.QixiGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商品
 */
@Controller
@RequestMapping("/goods")
public class QixiGoodsController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${show_path}")
	private String uploadPath;
	@Autowired
    QixiGoodsService goodsService;

	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
    /**
	 */
    @RequestMapping("list.jhtml")
    public String toGoodsList() {
        return "qixi/goodsList";
    }

    /**
     * 查询分类列表
     * @return Result
     */
    @RequestMapping("list.json")
    public @ResponseBody Result goodsList(BaseCommonQuery query) {
        Result result = new Result();
        int count = goodsService.getGoodsCount();
        if(count > 0){
            List<QixiGoods> list = goodsService.getGoodsList(query);
            result.setModel(list);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 详细
     * @return
     */
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        QixiGoods goods = goodsService.detail(id);
        if(null != goods){
            result.setModel(goods);
        } else {
            result.setSuccess(false);
            logger.error("查询详细失败");
        }
        return result;
    }

    /**
     * 添加
     * @param goods
     * @return
     */
    @RequestMapping("addOrUpdate.json")
    @ResponseBody
    public Result addOrUpdate(@ModelAttribute QixiGoods goods) {
        Result result = new Result();
        int count = 0;
        if(null != goods.getId())
            count = goodsService.update(goods);
        else count = goodsService.add(goods);
        if(count < 1){
            result.setSuccess(false);
            logger.error("操作失败！");
        }
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delete.json")
    @ResponseBody
    public Result delete(@RequestParam(value="id", required=true) Long id) {
        Result result = new Result();
        int count = goodsService.delete(id);
        if(count < 1){
            result.setSuccess(false);
            logger.error("删除失败！");
        }
        return result;
    }
}
