package com.hangjia.bxj.mvc.controller.qixi;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.qixi.QixiOrders;
import com.hangjia.bxj.query.qixi.OrdersQuery;
import com.hangjia.bxj.service.qixi.QixiOrdersService;
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
 * 订单
 */
@Controller
@RequestMapping("/orders")
public class QixiOrdersController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${show_path}")
	private String uploadPath;
	@Autowired
    QixiOrdersService ordersService;

	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
    /**
	 */
    @RequestMapping("list.jhtml")
    public String toGoodsList() {
        return "qixi/ordersList";
    }

    /**
     * 查询分类列表
     * @return Result
     */
    @RequestMapping("list.json")
    public @ResponseBody Result goodsList(OrdersQuery query) {
        Result result = new Result();
        int count = ordersService.getOrdersCount(query);
        if(count > 0){
            List<QixiOrders> list = ordersService.getOrderssList(query);
            result.setModel(list);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 更新快递号
     * @return
     */
    @RequestMapping("updateExpNo.json")
    @ResponseBody
    public Result updateExpNo(String orderId, String expressNo) {
        Result result = new Result();
        int count = ordersService.updateExpNo(orderId, expressNo);
        if(count < 1){
            result.setSuccess(false);
            logger.error("操作失败！");
        }
        return result;
    }
}
