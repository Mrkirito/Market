package com.hangjia.bxj.mvc.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.PlanProductGs;
import com.hangjia.bxj.model.PlanDecision;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.ControlAppStoreService;
import com.hangjia.bxj.service.PlanBookService;
import com.hangjia.bxj.service.banner.BannerService;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.hangjia.bxj.vo.QueryProductVo;

/**
 * 客户端 计划书 控制器。
 * @author K9999
 *
 */
@Controller
@RequestMapping("plan")
public class PlanBookClientController {
	
	@Autowired
	private PlanBookService service;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private ControlAppStoreService controlAppStoreService;

	/**
	 * 保险公司
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="product_gs.json", method=RequestMethod.GET)
	public Object getProductGS() {
		List<PlanProductGs> gs = service.getProductGs();
		int commpany = Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_SELECT_COMMPANY));
		for (PlanProductGs planProductGs : gs) {
			int current = planProductGs.getFid();
			if (current == commpany) {
				planProductGs.setSelect(1);
			}
		}
		return new AjaxResult.success(gs);
	}
	
	/**
	 * 产品
	 * @param fid
	 * @param page
	 * @param pageSize
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="product.json", method=RequestMethod.GET)
	public Object getProduct(Integer fid, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="20") Integer pageSize, String name,Integer cfid) {
		QueryProductVo vo = new QueryProductVo();
		if(StringUtils.isEmpty(name)){
			vo.setGs(fid==null||fid==38||fid==51?null:fid);
		}else{
			vo.setGs(null);
		}

		vo.setCurrentPage(page);
		vo.setPageSize(pageSize);
		
		vo.setName(name);
		//cfid=99 是全部险种
		vo.setCfid(cfid!=null&&cfid==99?null: cfid);
		
		Object result = service.querySimple(vo);
		return new AjaxResult.success(result);
	}
	
	/**
	 * Banner
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="banner.json", method=RequestMethod.GET)
	public Object getBanner() {
		Object result = bannerService.list();
		return new AjaxResult.success(result);
	}
	
	@ResponseBody
	@RequestMapping(value="saveDecision.json")
	public Object saveDecision(PlanDecision planDecision, HttpServletRequest request) {
		service.savedecision(planDecision);
		return new AjaxResult.success();
	}
	
}
