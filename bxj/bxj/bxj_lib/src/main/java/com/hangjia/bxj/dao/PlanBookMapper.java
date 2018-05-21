package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.PlanBook;
import com.hangjia.bxj.model.PlanBookUserRel;
import com.hangjia.bxj.model.PlanBookUserVo;
import com.hangjia.bxj.model.PlanBookproRel;
import com.hangjia.bxj.model.PlanBookproRelSon;
import com.hangjia.bxj.model.PlanDecision;
import com.hangjia.bxj.model.PlanProductBxnx;
import com.hangjia.bxj.model.PlanProductConstant;
import com.hangjia.bxj.model.PlanProductFl;
import com.hangjia.bxj.model.PlanProductJflx;
import com.hangjia.bxj.model.PlanProductJfnx;
import com.hangjia.bxj.model.PlanProductLcjz;
import com.hangjia.bxj.model.PlanProductMain;
import com.hangjia.bxj.model.PlanProductMainWithBLOBs;
import com.hangjia.bxj.model.PlanProductQy;
import com.hangjia.bxj.model.PlanProductRelates;
import com.hangjia.bxj.model.PlanProductSimple;
import com.hangjia.bxj.query.PlanBookQuery;
import com.hangjia.bxj.vo.QueryPlanBookVo;
import com.hangjia.bxj.vo.QueryProductVo;

public interface PlanBookMapper {

	/**
	 * 根据主键查询单个产品
	 * @param fid
	 * @return
	 */
	PlanProductMain getPlanProductMainById(Integer fid);	
	/**
	 * 单个产品权益查询
	 * @param pid
	 * @return
	 */
	List<PlanProductQy> getPlanProductQies(Integer pid);
	/**
	 * 根据产品查找 产品页面展示
	 * @param map
	 * @return
	 */
	List<PlanProductConstant> getProductConstants(Map<String, Object> map);
	/**
	 * 插入公司信息
	 * @param d
	 * @return
	 */
	int insertPlanDecision(PlanDecision d);
	/**
	 * 列表查询
	 * @param vo
	 * @return
	 */
	List<PlanProductMain> getPlanProductMainsByPage(QueryProductVo vo);
	
	/**
	 * 产品列表查询，简单数据。
	 * @param vo
	 * @return
	 */
	List<PlanProductSimple> getPlanProductSimpleByPage(QueryProductVo vo);
	
	/**
	 * 返回符合条件的产品记录总数。
	 * @param vo
	 * @return
	 */
	int getCount(QueryProductVo vo);

	
	/**
	 * 根据主险ID获取附加险产品
	 * @param pid
	 * @return
	 */
	List<PlanProductRelates> getPlanProductRelates(Integer pid);
	
	/**
	 * 查询费率
	 * @param fl
	 * @return
	 */
	PlanProductFl getPlanProductFl(PlanProductFl fl);
	/**
	 * 插入计划书
	 * @param planBook
	 * @return
	 */
	int	insertPlanBook(PlanBook planBook);
	/**
	 * 插入相关计划书
	 * @param planBookproRel
	 * @return
	 */
	int insertPlanBookproRel(PlanBookproRel planBookproRel);
	/**
	 * 查询单个PlanBook
	 * @param fid
	 * @return
	 */
	PlanBook getPlanBookByFid(Long fid);
	/**
	 * 根据planId查询所有的
	 * @param planId
	 * @return
	 */
	List<PlanBookproRel>  getAllBookproRels(Long planId);
	/**
	 * 获取主险产品
	 * @param gs
	 * @return
	 */
	List<PlanProductMain> getMainProducts(Integer gs);
	
	List<PlanProductMain> getAllProducts1(PlanProductMain pro);
	
	List<PlanProductConstant> getAllProducts2(Integer pid);

	List<PlanProductConstant> getAllProducts3(Integer pid);

	List<PlanProductConstant> getAllProducts4(Integer pid);
	
	List<PlanProductConstant> getAllProducts5(Integer pid);
	/**
	 * 
	 * @return
	 */
	List<PlanProductMain> listPlanProductMains();
	
	
	List<PlanProductConstant> getAllProductConstants(Integer fid);
	
	int insertPlanProductFl(PlanProductFl fl);
	
	int deletePlanProductFl(Integer pid);
	
	int deletePlanProductConstantRel(Integer pid);
	
	List<PlanProductConstant> getProductConstantList();
	
	/**
	 * 获取我的计划书列表
	 * @param query
	 * @return
	 */
	List<PlanBookUserVo> getPlanBooksByUserId(PlanBookQuery query);
	/**
	 * 获取我的计划书数量
	 * @param query
	 * @return
	 */
	int getPlanBookCountByUserId(PlanBookQuery query);
	/***
	 * 删除我的计划书
	 * @param query
	 * @return
	 */
	int deletePlanBookByPlanIds(PlanBookQuery query);
	
	/********==========================================================================================================old*/
	List<PlanProductMain> getPlanProductMains(QueryProductVo vo);

	PlanProductMainWithBLOBs getPlanProductMainWithBLOBs(Integer fid);
	List<PlanProductJflx> getPlanProductJflxs(Integer pid);
	List<PlanProductJfnx> getPlanProductJfnxs(Integer pid);
	

	int  insertPlanBookproRelSon(PlanBookproRelSon planBookproRelSon);
	int  insertPlanBookUserRel(PlanBookUserRel planBookUserRel);
	List<PlanBookproRelSon> getAllPlanBookproRelSons(Map<String,Object> map);
	
	List<PlanProductJflx> getAllPlanProductJflxs();	
	List<PlanProductJfnx> getAllPlanProductJfnxs();
	List<PlanProductQy>	  getBatchPlanProductQies (List<Integer> pids);
	List<PlanBook> getPlanBooks(QueryPlanBookVo planBookVo);

	List<PlanBook> getPlanBooksByUser(QueryPlanBookVo planBookVo);

	PlanBook getPlanBookByPlanXh(String planXh);
	PlanProductLcjz getPlanProductLcjz(PlanBook plan);	
	List<Map<String,Integer>> getAllFjPidByZxId(Integer pid);
	Map<String,Integer> getOnePlanProductQy(Map<String, Object> para);
	PlanProductQy getPlanProductQyByPk(Integer fid);
	int getPlanBookUserRel(PlanBookUserRel planBookUserRel);
	int getPlanBookUserRelByUserId(Integer userId);
	List<PlanProductBxnx> getAllPlanProductBxnx();
}
