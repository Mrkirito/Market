package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.export.BbappData;
import com.hangjia.bxj.model.export.BbwData;
import com.hangjia.bxj.model.export.BxjappData;
import com.hangjia.bxj.model.export.HjappData;
import com.hangjia.bxj.model.report.UserDataReport;
import com.hangjia.bxj.model.report.VoucherReport;
import com.hangjia.bxj.query.report.UserDataReportQuery;
import com.hangjia.bxj.query.report.VoucherReportQuery;

public interface AdminReportMapper {
	
	/**
	 * 视频券报表列表
	 * @return
	 */
	List<VoucherReport> queryVoucherReportList(VoucherReportQuery query);
	
	/**
	 * 视频券报表列表数量
	 * @return
	 */
	int queryVoucherReportCount(VoucherReportQuery query);
	
	/**
	 * 用户统计表列表
	 * @return
	 */
	List<UserDataReport> queryUserDataReportList(UserDataReportQuery query);
	
	/**
	 * 视频用户统计表列表数量
	 * @return
	 */
	int queryUserDataReportCount(UserDataReportQuery query);
	
	/**
	 * 用户统计表总计
	 * @return
	 */
	UserDataReport queryUserDataSummary();
	
	/**
	 * 用户昨日数据
	 * @return
	 */
	UserDataReport queryUserDataYesterday();
	
	/**
	 * 用户当月平均数据
	 * @return
	 */
	UserDataReport queryUserDataAvg();

	/**
	 * 用户当月平均数据
	 * @return
	 */
	UserDataReport queryUserDataAvg2();

	/**
	 * 新增用户统计数据
	 * @return
	 */
	int addUserData(UserDataReport userDataReport);

	/**
	 * 向bbw数据表中插入数据
	 */
	void insertDataToBbw();

	/**
	 * 向bbapp数据表中插入数据
	 */
	void insertDataToBbapp();

	/**
	 * 向bxjapp数据表中插入数据
	 */
	void insertDataToBxjapp();

	/**
	 * 向hjapp数据表中插入数据
	 */
	void insertDataToHjapp();

	/**
	 * 查询折线图数据
	 * @return
	 */
	List<UserDataReport> queryEchartsData(UserDataReportQuery query);

	List<UserDataReport> queryVideoEchartsData(UserDataReportQuery query);

	/**
	 * 修改用户统计数据
	 * @return
	 */
	int updateUserData(UserDataReport userDataReport);
	
	/**
	 * 根据时间查询是否存在数据记录
	 * @return
	 */
	int existUserDataByDay(UserDataReportQuery query);

	/**
	 * 查询保保网的pv、uv、新增用户、短信、销售额、环比、同比信息
	 * @return
	 */
    List<BbwData> queryBbwData();

	/**
	 * 查询保保APP的pv、uv、新增用户、短信、销售额、环比、同比信息
	 * @return
	 */
	List<BbappData> queryBbappData();

	/**
	 * 查询保险家APP的pv、uv、新增用户、短信、销售额、环比、同比信息
	 * @return
	 */
	List<BxjappData> queryBxjappData();

	/**
	 * 查询行家保险APP的pv、uv、新增用户、短信、销售额、环比、同比信息
	 * @return
	 */
	List<HjappData> queryHjappData();

	/**
	 * 查询当天的录入信息
	 * @return
	 */
	UserDataReport selsectDataOfYesterday();
}