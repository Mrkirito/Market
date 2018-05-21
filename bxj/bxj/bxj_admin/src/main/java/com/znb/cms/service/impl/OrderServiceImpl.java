package com.znb.cms.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibaoxianjia.message.dto.SmsMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;
import com.znb.cms.model.mapper.Insure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.znb.cms.common.AjaxResult;
import com.znb.cms.dao.mapper.InsureMapper;
import com.znb.cms.dao.mapper.IsurantMapper;
import com.znb.cms.dao.mapper.OrderMapper;
import com.znb.cms.model.dto.OrderDto;
import com.znb.cms.model.mapper.Order;
import com.znb.cms.service.IOrderService;


@Service
public class OrderServiceImpl implements IOrderService{
	//短信邮件通知接口
	@Autowired
	private MessageOpenAPI messageOpenAPI;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private InsureMapper insureMapper;
	@Autowired
	private IsurantMapper isurantMapper;
	
	@Value("${upload_pdf_url}")
	private String uploadPdfUrl ;
	
	@Override
	public List<OrderDto> selectOrderByOrder(OrderDto orderdto) {
		return orderMapper.selectOrderByOrder(orderdto);
	}

	@Override
	public int selectCount(OrderDto order) {
		return orderMapper.selectCount(order);
	}
	
	@Override
	public Order getOrder(Integer id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateOrder(OrderDto order) throws Exception {
		String state = orderMapper.selectByPrimaryKey(order.getId()).getOrderState();//记录之前订单状态
		orderMapper.updateByPrimaryKeySelective(order);
		insureMapper.updateByPrimaryKeySelective(order.getInsure());
		Order newOrder = orderMapper.selectByPrimaryKey(order.getId());
		try {
			if(state != newOrder.getOrderState()){ //状态变化时，发送信息
                sendSMS(newOrder);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isurantMapper.updateByPrimaryKeySelective(order.getIsurant());
	}

	@Override
	public AjaxResult importSinglePolicy(MultipartFile file,Integer id) throws Exception {
		PdfReader reader = new PdfReader(file.getBytes());  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        PdfStamper ps = new PdfStamper(reader, bos);
        ps.close();
        File files = new File(uploadPdfUrl+id+"/");
        if(!files.exists()){  
        	files.mkdirs();  
        }  
        FileOutputStream fos = new FileOutputStream(files+"/policy.pdf");  
        fos.write(bos.toByteArray());  
        fos.close();
        OrderDto orderDto = new OrderDto();
        orderDto.setPolicyUrl(id+"/policy.pdf");
        orderDto.setId(id);
        orderMapper.updateByPrimaryKeySelective(orderDto);
		return new AjaxResult.success("sucess");
	}

	@Override
	public int delOrder(Integer id) {
		return orderMapper.delOrder(id);
	}

	@Override
	public Order selectByPrimaryKey(Integer id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 发送短信给投保人
	 * @author mt
	 * @param order 订单信息
	 */
	public void sendSMS(Order order) throws Exception {
		Insure insure = insureMapper.selectByPrimaryKey(order.getInsureId());
		SmsMessageDto smsMessage = new SmsMessageDto();
		smsMessage.addPhone(insure.getTelephone());       	//接收手机号
		smsMessage.setSysid("core_1001");
		smsMessage.setSmsCode("GZZRX_"+order.getOrderState());
		smsMessage.setIp("192.168.10.240");
		Map<String, String> smsInfoMap = new HashMap<String, String>();
		smsInfoMap.put("insure", insure.getCompanyName());
		smsMessage.setSmsInfoMap(smsInfoMap);
		boolean result = messageOpenAPI.sendSms(smsMessage);
		System.out.println("Sending sms!! " + result);
	}



}
