package com.znb.cms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.znb.cms.common.AjaxResult;
import com.znb.cms.model.dto.OrderDto;
import com.znb.cms.model.mapper.Order;

/**
* @author yuanxin
* @date 2017年5月18日下午4:53:54
* @version <b>1.0.0</b>
*/
public interface IOrderService {
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:50:27
	* @version <b>1.0.0</b>
	* 根据订单dto 查询订单相关信息
	* @return 返回符合条件订单集合
	*/
	List<OrderDto> selectOrderByOrder(OrderDto orderDto);
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:54:42
	* @version <b>1.0.0</b>
	* 根据订单主键id查询订单信息
	* @return 订单信息
	*/
	Order selectByPrimaryKey(Integer id);
	
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:55:12
	* @version <b>1.0.0</b>
	* 根据订单条件查询订单数量
	* @return 返回订单总条数
	*/
	int selectCount(OrderDto order);
	
	
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:55:54
	* @version <b>1.0.0</b>
	* 更新订单
	* @return
	*/
	int updateOrder(OrderDto order) throws Exception;
	
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:56:10
	* @version <b>1.0.0</b>
	* 删除订单
	* @return
	*/
	int delOrder(Integer id);
	
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:56:19
	* @version <b>1.0.0</b>
	* 根据订单id获取定点信息
	* @return
	*/
	Order getOrder(Integer id);
	
	/**
	 * 单个订单保单导入
	 * @param file
	 * @return
	 */
	AjaxResult importSinglePolicy(MultipartFile file,Integer id) throws Exception;
	
	
}
