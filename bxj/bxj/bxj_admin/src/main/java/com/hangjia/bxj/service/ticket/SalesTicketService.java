package com.hangjia.bxj.service.ticket;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.SalesTicketBasic;
import com.hangjia.bxj.model.SalesTicketDocument;
import com.hangjia.bxj.model.SalesTicketOrder;
import com.hangjia.bxj.model.SalesTicketOrderDetail;
import com.hangjia.bxj.model.SalesTicketSit;
import com.hangjia.bxj.query.ticket.SalesTicketDocumentQuery;
import com.hangjia.bxj.query.ticket.SalesTicketQuery;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo2;
import com.hangjia.bxj.vo.SalesTicketOrderStaticsVo;

public interface SalesTicketService {
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
	 * 
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
	
	int queryAttendPeoplesCount(SalesTicketQuery query);
	
	List<SalesTicketOrderAttendPeoplesVo> queryAttendPeoples(SalesTicketQuery query);
	
	SalesTicketOrderAttendPeoplesVo getAttendPeopleByFid(Long fid);
	
	Map updateAttendPeoples(SalesTicketOrderAttendPeoplesVo vo);
	
	Map sendAttendMsg(Long fid);

	int getLockSalesTicketDocumentsCount(SalesTicketDocumentQuery query);

	List<SalesTicketDocument> getLockSalesTicketDocuments(SalesTicketDocumentQuery query);

	Map<String, Object> lockDocuments(SalesTicketDocumentQuery query);

	/**
	 * 清场处理
	 *
	 * @param basicId
	 * @return
     */
	Result clearMeet(Long basicId);
	
	Result attendMeet(SalesTicketOrderAttendPeoplesVo vo);
	
	/**
	 * 手动导入功能
	 * @param order
	 * @return
	 * @throws Exception
	 */
	Map excelImport(SalesTicketOrder order)throws Exception;
	
	int getPhoneCountByMeet(SalesTicketOrder order);
	String getSystemHasPhones(SalesTicketOrder order);
	List<SalesTicketSit> getSitsByBasicId(Integer fid);
	
	List<SalesTicketBasic> querySelectSalesTicketBasics();
	
	List<SalesTicketOrderAttendPeoplesVo2>  getTicketOrderAttendPeoplesVos(Integer fid);
	
	SalesTicketBasic getSalesTicketBasicByPrimaryKey(Integer fid);
	
	
	Result updateAttendPeopleSit(Long fid,Long docId);
}
