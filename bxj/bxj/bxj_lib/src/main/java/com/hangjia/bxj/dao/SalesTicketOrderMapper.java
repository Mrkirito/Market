package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.SalesTicketDocument;
import com.hangjia.bxj.model.SalesTicketOrder;
import com.hangjia.bxj.model.SalesTicketOrderDetail;
import com.hangjia.bxj.model.SalesTicketSit;
import com.hangjia.bxj.query.ticket.SalesTicketDocumentQuery;
import com.hangjia.bxj.query.ticket.SalesTicketQuery;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo2;
import com.hangjia.bxj.vo.SalesTicketOrderStaticsVo;

public interface SalesTicketOrderMapper {
	/**
	 * 获取余票信息
	 * @return
	 */
	List<SalesTicketOrderStaticsVo> getMeetTicketCountList();
	/**
	 * 获取明细信息
	 * @param query
	 * @return
	 */
	List<SalesTicketOrderStaticsVo> getSalesTicketDetails(SalesTicketQuery query);
	/**
	 * 获取明细信息总数
	 * @param query
	 * @return
	 */
	int getSalesTicketDetailCount(SalesTicketQuery query);
	/**
	 * 获取总计票务信息
	 * @param query
	 * @return
	 */
	List<SalesTicketOrderStaticsVo> getSalesTicketTotal(SalesTicketQuery query);
	/**
	 * 获取会议信息
	 * @return
	 */
	List<SalesTicketOrderStaticsVo> getBasicList();
	/**
	 * 获取销售人员信息
	 * @return
	 */
	List<Map<String,Object>> getSaleMansList(String userCode);
	
	/**
	 * 获取参会人员信息
	 * @param query
	 * @return
	 */
	List<SalesTicketOrderAttendPeoplesVo> queryAttendPeoples(SalesTicketQuery query);
	/**
	 * 获取参会人员信息
	 * @return
	 */
	int queryAttendPeoplesCount(SalesTicketQuery query);
	
	/**
	 * 根据子订单ID 查找参会人员
	 * @param fid
	 * @return
	 */
	SalesTicketOrderAttendPeoplesVo getAttendPeopleByFid(Long fid);
	/**
	 * 更改参会人员信息
	 * @param vo
	 * @return
	 */
	int updateAttendPeoples(SalesTicketOrderAttendPeoplesVo vo);

	int updateMarchOut(Long basicId);
	/**
	 * 
	 * @param query
	 * @return
	 */
	int getLockSalesTicketDocumentsCount(SalesTicketDocumentQuery query);
	/***
	 * 获取票据
	 * @param basicId
	 * @param state
	 * @return
	 */
	List<SalesTicketDocument> getLockSalesTicketDocuments(SalesTicketDocumentQuery query);
	/**
	 * 更新票据
	 * @return
	 */
	int updateLockSalesTicketDocuments(SalesTicketDocumentQuery query);
	
	
	
	/************导入数据功能***************/
	String randomShortUUID();

	int insertSalesTicketOrder(SalesTicketOrder record);

	List<SalesTicketDocument> querySalesTicketDocument(Integer basicId, Integer sitId, Integer state, Integer num);

	int updateTicketDocument(SalesTicketDocument document);

	int insertSalesTicketOrderDetail(SalesTicketOrderDetail orderDetail);

	int getPhoneCountByMeet(SalesTicketOrder order);
	List<SalesTicketOrder> getSystemHasPhones(SalesTicketOrder order);
	List<SalesTicketSit> getSitsByBasicId(Integer fid);
	
	int updateOrder(Long fid);
	/**
	 * 
	 * @return
	 */
	int getPhonesCount(Integer basicId,String tel,Long fid);
	
	/**
	 * 更改进出场
	 * @param fid
	 * @return
	 */
	int updateSalesTicketOrderDetail(SalesTicketOrderAttendPeoplesVo vo);
	
	int insertSalesTicketIntoLog(SalesTicketOrderAttendPeoplesVo vo);
	/**
	 * 导出名单
	 * @return
	 */
	List<SalesTicketOrderAttendPeoplesVo2>  getTicketOrderAttendPeoplesVos(Integer fid);
}