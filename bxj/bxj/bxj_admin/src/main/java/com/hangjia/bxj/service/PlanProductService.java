package com.hangjia.bxj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.framework.utils.jedis.RedisUtil;
import com.hangjia.bxj.dao.PlanBookMapper;
import com.hangjia.bxj.model.PlanProductConstant;
import com.hangjia.bxj.model.PlanProductMain;
import com.hangjia.bxj.model.PlanProductQy;
import com.hangjia.bxj.model.PlanProductRelates;
@Service
@Transactional(rollbackFor=Throwable.class)
public class PlanProductService{
	private static String singlePlanProductKey="singlePlanProductKey_";
	@Autowired
	private PlanBookMapper dao;
	@Autowired
	private RedisUtil redisUtil;
	public List<PlanProductConstant> getProductConstants(Integer pid,Integer fatherId){
		Map<String,Object> para=new HashMap<String, Object>();
		para.put("pid", pid);
		para.put("fatherId", fatherId);
		return dao.getProductConstants(para);
	}
	
	public PlanProductMain getPlanProductMainByPid(Integer pid){
		PlanProductMain product = null;
		String key = singlePlanProductKey + pid;
		product = (PlanProductMain) redisUtil.getUnserializeKey(key);
		if (null == product) {
			product = dao.getPlanProductMainById(pid);
			/*年龄*/
			int start = product.getTbnlks();
			int end = product.getTbnljs();
			List<Integer> ages = new ArrayList<Integer>();
			for (int i = start; i <= end; i++) {
				ages.add(i);
			}
			product.setAges(ages);
			/*费率因子信息*/
			List<PlanProductConstant> pConstants = getProductConstants(pid, 0);
			for (PlanProductConstant constant : pConstants) {
				constant.setChildren(getProductConstants(pid, constant.getFid()));
			}
			product.setProductConstants(pConstants);
			/*权益*/
			List<PlanProductQy> list = dao.getPlanProductQies(pid);
			product.setPlanProductQies(list);
			if (product != null) {
				redisUtil.setSerializeKey(key, product);
			}
		}
		return product;
	}
	
	public List<PlanProductMain> getMainProductsByGs(Integer gs){
		List<PlanProductMain> arrs=new ArrayList<PlanProductMain>();
		for (PlanProductMain planProductMain : dao.getMainProducts(gs)) {
			arrs.add(getConfirmPlanProductMain(planProductMain.getFid()));
		}
		return arrs;
	}
	
	public PlanProductMain getConfirmPlanProductMain(Integer pid) {
		PlanProductMain product = getPlanProductMainByPid(pid);
		Integer zxflag=product.getZxflag();
		if (zxflag > 1) {
			List<PlanProductRelates> relates = dao.getPlanProductRelates(pid);
			for (PlanProductRelates rel : relates) {
				rel.setProduct(getPlanProductMainByPid(rel.getPid()));
			}
			product.setChilds(relates);
			if (zxflag == 4 && StringUtils.isNotEmpty(product.getGroupids())) {
				List<PlanProductRelates> pprs = new ArrayList<PlanProductRelates>();
				for (String string : product.getGroupids().split(",")) {
					Integer fid = Integer.parseInt(string);
					PlanProductRelates ppr = new PlanProductRelates();
					ppr.setProduct(getPlanProductMainByPid(fid));
					pprs.add(ppr);
				}
				product.setMustChilds(pprs);
			}
		}
		return product;
	}
}
