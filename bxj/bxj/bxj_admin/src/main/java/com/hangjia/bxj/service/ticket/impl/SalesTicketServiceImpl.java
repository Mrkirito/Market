package com.hangjia.bxj.service.ticket.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.framework.support.utility.Configuration;
import com.baobao.framework.utils.DateFormatUtils;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.SalesTicketBasicMapper;
import com.hangjia.bxj.dao.SalesTicketDocumentMapper;
import com.hangjia.bxj.dao.SalesTicketOrderDetailMapper;
import com.hangjia.bxj.dao.SalesTicketOrderMapper;
import com.hangjia.bxj.model.SalesTicketBasic;
import com.hangjia.bxj.model.SalesTicketDocument;
import com.hangjia.bxj.model.SalesTicketOrder;
import com.hangjia.bxj.model.SalesTicketOrderDetail;
import com.hangjia.bxj.model.SalesTicketSit;
import com.hangjia.bxj.query.ticket.SalesTicketDocumentQuery;
import com.hangjia.bxj.query.ticket.SalesTicketQuery;
import com.hangjia.bxj.service.ticket.SalesTicketService;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo2;
import com.hangjia.bxj.vo.SalesTicketOrderStaticsVo;
import com.ibaoxianjia.message.dto.SmsMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;

@Service
public class SalesTicketServiceImpl implements SalesTicketService{
	@Autowired
	private SalesTicketOrderMapper dao;
	
	@Autowired
	private SalesTicketOrderDetailMapper detailMapper;
	
	@Autowired
	private SalesTicketDocumentMapper documentMapper;
	
	@Autowired
	private MessageOpenAPI messageOpenAPI;
	@Autowired
	private SalesTicketBasicMapper salesTicketBasicMapper;
	
	@Override
	public List<SalesTicketOrderStaticsVo> getMeetTicketCountList() {
		return dao.getMeetTicketCountList();
	}

	@Override
	public List<SalesTicketOrderStaticsVo> getSalesTicketDetails(SalesTicketQuery query) {
		return dao.getSalesTicketDetails(query);
	}
	
	@Override
	public int getSalesTicketDetailCount(SalesTicketQuery query) {
		return dao.getSalesTicketDetailCount(query);
	}
	
	private Map<Integer, String> getBasicMap() {
		List<SalesTicketOrderStaticsVo> basics = getBasicList();
		Map<Integer, String> basicMap = new HashMap<Integer, String>();
		for (SalesTicketOrderStaticsVo b : basics) {
			basicMap.put(b.getBasicId(), b.getActivityName());
		}
		return basicMap;
	}
	
	@Override
	public List<SalesTicketOrderStaticsVo> getSalesTicketTotal(SalesTicketQuery query) {
		Map<Integer,SalesTicketOrderStaticsVo> result=new HashMap<Integer, SalesTicketOrderStaticsVo>();
		Map<Integer, String> basicMap = getBasicMap();
		List<SalesTicketOrderStaticsVo> list = dao.getSalesTicketTotal(query);

		int allTotalNum0=0;
		int allActualNum0=0;
		int allPresentNum0=0;
		BigDecimal allTotalMoney0=BigDecimal.ZERO;

		int allTotalNum3=0;
		int allActualNum3=0;
		int allPresentNum3=0;
		BigDecimal allTotalMoney3=BigDecimal.ZERO;

		int allTotalNum1=0;
		int allActualNum1=0;
		int allPresentNum1=0;
		BigDecimal allTotalMoney1=BigDecimal.ZERO;

		int allTotalNum2=0;
		int allActualNum2=0;
		int allPresentNum2=0;
		BigDecimal allTotalMoney2=BigDecimal.ZERO;

		for (SalesTicketOrderStaticsVo vo : list) {
			Integer key = vo.getBasicId();
			Integer payType = vo.getPayType();
			SalesTicketOrderStaticsVo rs = null;
			if (result.containsKey(key)) {
				rs = result.get(key);
				int t1 = rs.getTotalNum0();
				rs.setTotalNum0(vo.getTotalNum() + t1);
				int t2 = rs.getActualNum0();
				rs.setActualNum0(vo.getActualNum() + t2);
				int t3 = rs.getPresentNum0();
				rs.setPresentNum0(vo.getPresentNum() + t3);
				BigDecimal t4=rs.getTotalMoney0();
				rs.setTotalMoney0(t4.add(vo.getTotalMoney()));
			} else {
				rs = new SalesTicketOrderStaticsVo();
				rs.setActivityName(basicMap.get(key));
				rs.setTotalNum0(vo.getTotalNum());
				rs.setActualNum0(vo.getActualNum());
				rs.setPresentNum0(vo.getPresentNum());
				rs.setTotalMoney0(vo.getTotalMoney());

			}
			allTotalNum0+=vo.getTotalNum();
			allActualNum0+=vo.getActualNum();
			allPresentNum0+=vo.getPresentNum();
			allTotalMoney0=allTotalMoney0.add(vo.getTotalMoney());

			if (payType == 3) {
				rs.setTotalNum3(vo.getTotalNum());
				rs.setActualNum3(vo.getActualNum());
				rs.setPresentNum3(vo.getPresentNum());
				rs.setTotalMoney3(vo.getTotalMoney());

				allTotalNum3+=vo.getTotalNum();
				allActualNum3+=vo.getActualNum();
				allPresentNum3+=vo.getPresentNum();
				allTotalMoney3=allTotalMoney3.add(vo.getTotalMoney());

			} else if (payType == 2) {
				rs.setTotalNum2(vo.getTotalNum());
				rs.setActualNum2(vo.getActualNum());
				rs.setPresentNum2(vo.getPresentNum());
				rs.setTotalMoney2(vo.getTotalMoney());

				allTotalNum2+=vo.getTotalNum();
				allActualNum2+=vo.getActualNum();
				allPresentNum2+=vo.getPresentNum();
				allTotalMoney2=allTotalMoney2.add(vo.getTotalMoney());
			} else if (payType == 1) {
				rs.setTotalNum1(vo.getTotalNum());
				rs.setActualNum1(vo.getActualNum());
				rs.setPresentNum1(vo.getPresentNum());
				rs.setTotalMoney1(vo.getTotalMoney());

				allTotalNum1+=vo.getTotalNum();
				allActualNum1+=vo.getActualNum();
				allPresentNum1+=vo.getPresentNum();
				allTotalMoney1=allTotalMoney1.add(vo.getTotalMoney());

			}
			result.put(key, rs);
		}
		List<SalesTicketOrderStaticsVo> vos=new ArrayList<SalesTicketOrderStaticsVo>();
		for (Map.Entry<Integer, SalesTicketOrderStaticsVo> entry:result.entrySet()) {
			vos.add(entry.getValue());
		}
		SalesTicketOrderStaticsVo allVo=new SalesTicketOrderStaticsVo();
		allVo.setActivityName("总计");
		allVo.setTotalNum0(allTotalNum0);
		allVo.setActualNum0(allActualNum0);
		allVo.setPresentNum0(allPresentNum0);
		allVo.setTotalMoney0(allTotalMoney0);

		allVo.setTotalNum1(allTotalNum1);
		allVo.setActualNum1(allActualNum1);
		allVo.setPresentNum1(allPresentNum1);
		allVo.setTotalMoney1(allTotalMoney1);

		allVo.setTotalNum2(allTotalNum2);
		allVo.setActualNum2(allActualNum2);
		allVo.setPresentNum2(allPresentNum2);
		allVo.setTotalMoney2(allTotalMoney2);

		allVo.setTotalNum3(allTotalNum3);
		allVo.setActualNum3(allActualNum3);
		allVo.setPresentNum3(allPresentNum3);
		allVo.setTotalMoney3(allTotalMoney3);

		vos.add(allVo);
		return vos;
	}	
	@Override
	public List<SalesTicketOrderStaticsVo> getBasicList() {
		return dao.getBasicList();
	}

	@Override
	public List<Map<String, Object>> getSaleMansList(String userCode) {
		return dao.getSaleMansList(userCode);
	}

	@Override
	public int queryAttendPeoplesCount(SalesTicketQuery query) {
		return dao.queryAttendPeoplesCount(query);
	}

	@Override
	public List<SalesTicketOrderAttendPeoplesVo> queryAttendPeoples(SalesTicketQuery query) {
		return dao.queryAttendPeoples(query);
	}

	@Override
	public SalesTicketOrderAttendPeoplesVo getAttendPeopleByFid(Long fid) {
		return dao.getAttendPeopleByFid(fid);
	}

	@Override
	public Map updateAttendPeoples(SalesTicketOrderAttendPeoplesVo vo) {	
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String tel=vo.getUserPhone();
			if(dao.getPhonesCount(vo.getBasicId(),tel,vo.getFid())==0){
				dao.updateAttendPeoples(vo);
				if (vo.getSendInfo() == 1) {
					sendAttendMsg(vo.getFid());
				}
				dao.updateOrder(vo.getOrderId());
				map.put("success", true);				
			}else{
				map.put("success", false);
				map.put("errorcode", 1);
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorcode", 0);
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map sendAttendMsg(Long fid) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			SalesTicketOrderAttendPeoplesVo vo=getAttendPeopleByFid(fid);
			if (vo != null && StringUtils.isNotBlank(vo.getUserPhone())) {
				SmsMessageDto smsMessageDto = new SmsMessageDto();
				smsMessageDto.setIp("");
				smsMessageDto.setSysid(Configuration.getProperty("system", "sysid"));
				smsMessageDto.addPhone(vo.getUserPhone());
				smsMessageDto.setSmsCode("SALES_ATTENDEES_TMP");
				Map<String, String> infoMap = new HashMap<String, String>();
				infoMap.put("userName", vo.getUserName());
				infoMap.put("city", vo.getActivityName());
				infoMap.put("meetingTime", getMeetingTime(vo.getBeginTime(), vo.getEndTime()));
				infoMap.put("address", vo.getAddress());
				String floor = vo.getFloor();
				String area=vo.getArea();
				infoMap.put("position", (StringUtils.isNotEmpty(floor) ? floor + "-" : "") +(StringUtils.isNotEmpty(area) ? area + "-" : "") + vo.getRows() + "-" + vo.getNumber());
				infoMap.put("downloadUrl", "http://t.cn/Rt5zgPS");
				smsMessageDto.setSmsInfoMap(infoMap);
				messageOpenAPI.sendSms(smsMessageDto);
			}
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 清场处理
	 *
	 * @param basicId
	 * @return
	 */
	@Override
	public Result clearMeet(Long basicId) {
		Result result = new Result();
		int count = dao.updateMarchOut(basicId);
//		if (count <= 0){
//			result.setSuccess(false);
//			result.setMsg("修改失败");
//		}
		return result;
	}

	@Override
	public Result attendMeet(SalesTicketOrderAttendPeoplesVo vo) {
		Result result = new Result();
		dao.updateSalesTicketOrderDetail(vo);
		if(vo.getState()==2){
			vo.setSendInfo(7);//手动出场
		}else{
			vo.setSendInfo(6);//手动进场
		}
		dao.insertSalesTicketIntoLog(vo);
		return result;
	}

	private String getMeetingTime(Date begin, Date end) {
		String format = "yyyy-MM-dd";
		StringBuffer sb = new StringBuffer();
		if (begin != null) {
			sb.append(DateFormatUtils.format(format, begin));
		}
		if (end != null) {
			sb.append("~").append(DateFormatUtils.format(format, end));
		}
		return sb.toString();
	}

	@Override
	public int getLockSalesTicketDocumentsCount(SalesTicketDocumentQuery query) {
		return dao.getLockSalesTicketDocumentsCount(query);
	}

	@Override
	public List<SalesTicketDocument> getLockSalesTicketDocuments(SalesTicketDocumentQuery query) {
		return dao.getLockSalesTicketDocuments(query);
	}

	@Override
	public Map<String, Object> lockDocuments(SalesTicketDocumentQuery query) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			dao.updateLockSalesTicketDocuments(query);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map excelImport(SalesTicketOrder order) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		SalesTicketBasic basic=salesTicketBasicMapper.selectByPrimaryKey(order.getBasicId());
		boolean sendMsg = (order.getSendInfo() == 1);
		List<SalesTicketDocument> docs = dao.querySalesTicketDocument(order.getBasicId(), order.getSitId(),order.getTicketType(),order.getNum());
		if(docs.size()!=order.getNum()){
			map.put("success", false);
			map.put("status", "500");
			map.put("msg", "票源不足请将票源类型更为正常");
			return map;
		}		
		order.setPayment(3);
		order.setPayTime(new Date());
		order.setCreateTime(new Date());
		order.setState(1);
		order.setInputSource(1);
		order.setPipeno(dao.randomShortUUID());
		dao.insertSalesTicketOrder(order);
		List<SalesTicketOrderDetail> ds = order.getOrderDetails();
		for (int i = 0; i < docs.size(); i++) {			
			SalesTicketDocument salesTicketDocument = docs.get(i);
			salesTicketDocument.setState(1);
			salesTicketDocument.setSalesTime(new Date());
			int j = dao.updateTicketDocument(salesTicketDocument);
			if (j == 0) {
				throw new RuntimeException("座位号被占用");
			}
			SalesTicketOrderDetail orderDetail = ds.get(i);
			orderDetail.setOrderId(order.getFid());
			orderDetail.setBasicId(order.getBasicId());
			orderDetail.setDocId(salesTicketDocument.getFid());
			orderDetail.setState(1);
			dao.insertSalesTicketOrderDetail(orderDetail);
			if(sendMsg){
				try {
					String userPhone=orderDetail.getUserPhone();
					SmsMessageDto smsMessageDto = new SmsMessageDto();
					smsMessageDto.setIp("");
					smsMessageDto.setSysid(Configuration.getProperty("system", "sysid"));
					smsMessageDto.addPhone(userPhone);
					smsMessageDto.setSmsCode("SALES_ATTENDEES_TMP");
					Map<String, String> infoMap = new HashMap<String, String>();
					infoMap.put("userName", orderDetail.getUserName());
					infoMap.put("city", basic.getActivityName());
					infoMap.put("meetingTime", getMeetingTime(basic.getBeginTime(), basic.getEndTime()));
					infoMap.put("address", basic.getAddress());
					String floor = salesTicketDocument.getFloor();
					String area  = salesTicketDocument.getArea();
					String rows  = salesTicketDocument.getRows();
					infoMap.put("position", (StringUtils.isNotEmpty(floor) ? floor + "-" : "") + (StringUtils.isNotEmpty(area) ? area + "-" : "") + (StringUtils.isNotEmpty(rows) ? rows + "-" : "") +  salesTicketDocument.getNumber());
					infoMap.put("downloadUrl", "http://t.cn/Rt5zgPS");
					smsMessageDto.setSmsInfoMap(infoMap);
					messageOpenAPI.sendSms(smsMessageDto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		map.put("success", true);
		map.put("status", 0);
		map.put("order", order);		
		return map;
	}

	@Override
	public int getPhoneCountByMeet(SalesTicketOrder order) {
		return dao.getPhoneCountByMeet(order);
	}

	@Override
	public String getSystemHasPhones(SalesTicketOrder order) {
		StringBuffer sb=new StringBuffer();
		List<SalesTicketOrder> list=dao.getSystemHasPhones(order);
		int le=list.size();
		for (int i = 0; i <le; i++) {
			SalesTicketOrder order2=list.get(i);
			sb.append(order2.getTel());
			if(i!=(le-1)){
				sb.append(",");				
			}
		}
		return sb.toString();
	}

	
	@Override
	public List<SalesTicketSit> getSitsByBasicId(Integer fid) {
		return dao.getSitsByBasicId(fid);
	}

	@Override
	public List<SalesTicketBasic> querySelectSalesTicketBasics() {
		return salesTicketBasicMapper.querySelectSalesTicketBasics();
	}

	@Override
	public List<SalesTicketOrderAttendPeoplesVo2> getTicketOrderAttendPeoplesVos(Integer fid) {
		return dao.getTicketOrderAttendPeoplesVos(fid);
	}

	@Override
	public SalesTicketBasic getSalesTicketBasicByPrimaryKey(Integer fid) {
		return salesTicketBasicMapper.selectByPrimaryKey(fid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result updateAttendPeopleSit(Long fid,Long docId) {
		Result result=new Result();
		SalesTicketOrderDetail detail=detailMapper.selectByPrimaryKey(fid);
		if(detail!=null){			
			Long oldDocId=detail.getDocId();
			detail.setDocId(docId);
			int i = detailMapper.updateByPrimaryKeySelective(detail);
			if(i==0){
				throw new RuntimeException();
			}			
			
			SalesTicketDocument doc=documentMapper.selectByPrimaryKey(docId);
			doc.setState(1);
			int j=documentMapper.updateByPrimaryKeySelective(doc);
			if(j==0){
				throw new RuntimeException();
			}
			
			SalesTicketDocument oldDoc=documentMapper.selectByPrimaryKey(oldDocId);
			oldDoc.setState(oldDoc.getStateBak());
			oldDoc.setVersion(1);
			int n=documentMapper.updateByPrimaryKeySelective(oldDoc);
			if(n==0){
				throw new RuntimeException();
			}
			SalesTicketBasic vo=salesTicketBasicMapper.selectByPrimaryKey(detail.getBasicId());
			SmsMessageDto smsMessageDto = new SmsMessageDto();
			smsMessageDto.setIp("");
			smsMessageDto.setSysid(Configuration.getProperty("system", "sysid"));
			smsMessageDto.addPhone(detail.getUserPhone());
			smsMessageDto.setSmsCode("SALES_ATTENDEES_CHANGE_TMP");
			Map<String, String> infoMap = new HashMap<String, String>();
			infoMap.put("userName", detail.getUserName());
			infoMap.put("city", vo.getActivityName());
			String floor = doc.getFloor();
			String area=doc.getArea();
			infoMap.put("position", (StringUtils.isNotEmpty(floor) ? floor + "-" : "") +(StringUtils.isNotEmpty(area) ? area + "-" : "") +  doc.getRows() + "-" + doc.getNumber());
			smsMessageDto.setSmsInfoMap(infoMap);
			messageOpenAPI.sendSms(smsMessageDto);
		}
		result.setModel(detail);
		return result;
	}
}
