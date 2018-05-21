package com.hangjia.bxj.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.baobao.framework.support.utility.StringUtils;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.dao.ChampionUserVoucherMapper;
import com.hangjia.bxj.dao.TempMobileMapper;
import com.hangjia.bxj.exception.BusinessException;
import com.hangjia.bxj.exception.SystemException;
import com.hangjia.bxj.model.ChampionUserVoucher;
import com.hangjia.bxj.model.PlanBookUserVo;
import com.hangjia.bxj.model.TempMobile;
import com.hangjia.bxj.service.PlanBookService;
import com.hangjia.bxj.service.PlanUserCardService;
import com.hangjia.bxj.service.usercard.UserCardService;
import com.hangjia.bxj.util.Base64Util;
import com.hangjia.bxj.util.Constants;
import com.hangjia.bxj.vo.PlanUserCard;
import com.ibaoxianjia.sales.service.SalesSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计划书用户名片控制类
 * @author Tain
 * 2015-10-27
 */
@Controller
@RequestMapping(value="/planUserCard")
public class PlanUserCardController extends BaseController {
	@Autowired
	private PlanUserCardService planUserCardService;
	@Autowired
	private PlanBookService planBookService;
    @Autowired
    private UserCardService userCardService;
    @Autowired
    private SalesSupportService salesSupportService;

	@RequestMapping("/userCard.page")
    @Login(AuthenType.page)
    private ModelAndView userCardIndexPage(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
		ModelAndView view = new ModelAndView("/planbook/my_edit");
		Integer fid = WebUtils.getMemberId(request);
		String phone = WebUtils.getMobile(request);
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("fid", fid+"");
		PlanUserCard userCard = planUserCardService.selectByPrimaryKey(paraMap);
		boolean isAdd = true;
		if (null != userCard) {
			isAdd = false;
		}else{
			userCard=new PlanUserCard();
			userCard.setSex(1);
		}
		view.addObject("userCard", userCard);
		view.addObject("phone", phone);
		view.addObject("fid", fid);
		view.addObject("isAdd", isAdd);
		view.addObject("companys",planBookService.getProductGs());
        return view;
    }
	@RequestMapping("/updateUserCard.do")
	@ResponseBody
    @Login(AuthenType.json)
    private ResponseEntity<?> updateUserCard(PlanUserCard planUserCard) {
		return new ResponseEntity<Map<String,Object>>(planUserCardService.upPlanUserCard(planUserCard),HttpStatus.OK);
    }



    @RequestMapping("/study.page")
    private ModelAndView study(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        ModelAndView view = new ModelAndView("/planbook/study");
        return view;
    }

    @RequestMapping("/scan.page")
    private ModelAndView scan(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        ModelAndView view = new ModelAndView("/planbook/scan");
        return view;
    }

    @RequestMapping("/studybody.page")
    private ModelAndView studybody(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        ModelAndView view = new ModelAndView("/planbook/studybody");
        return view;
    }

	@RequestMapping("/me.page")
    @Login(AuthenType.page)
    private ModelAndView mePage(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        ModelAndView view = new ModelAndView("/planbook/my");
        Map<String, String> para = this.getPara(request);
        String fid=WebUtils.getMemberId(request)+"";
        para.put("fid", fid);
        PlanUserCard userCard = planUserCardService.selectByPrimaryKey(para);
        if(userCard==null) {
        	view.addObject("cardFlag", "true");
        } else {
        	view.addObject("cardFlag", "false");
        }
        view.addObject("userCard", userCard);
        return view;
    }

	@RequestMapping("/myPlanbook.page")
    @Login(AuthenType.page)
    private ModelAndView myPlanbookPage(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        ModelAndView view = new ModelAndView("/planbook/my_book");
        Map<String, String> para = this.getPara(request);
        String fid=WebUtils.getMemberId(request)+"";
        para.put("fid", fid);
        List<PlanBookUserVo> planBookList = planUserCardService.getUserPlanBookList(para);

        if(planBookList.size() == 0) {
        	 view.addObject("planFlag", "false");
        } else {
        	 view.addObject("planFlag", "true");
        }
        view.addObject("planBookList", planBookList);
        return view;
    }

	@RequestMapping("/delPlanbook.do")
	@ResponseBody
    private Object delPlanbookPage(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        Map<String, String> para = this.getPara(request);
        String fid=WebUtils.getMemberId(request)+"";
        para.put("fid", fid);
        int result = planUserCardService.delPlanbook(para);
        Map<String, Object> rMap = new HashMap<String, Object>();
        if(result == 1)
        	rMap.put("success", true);
        else
        	rMap.put("fail", true);
        return rMap;
    }

	@RequestMapping("/getPlanbookCompany.do")
	@ResponseBody
    private Object getPlanbookCompanyPage(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        return planBookService.getProductGs();
    }

    @RequestMapping(value = "{userId}/shareUserCard.page")
    private String shareUserCard(HttpServletRequest request, HttpServletResponse response, @PathVariable String userId, Integer type, String o, String t, String c){
        Integer userIdInt = null;
        if (StringUtils.isNotBlank(o)) {
            try {
                userIdInt = Integer.valueOf(new String(Base64Util.decode(userId)));
                if (StringUtils.isNotBlank(c) && StringUtils.isNotBlank(t)
                        && (com.hangjia.bxj.common.Constants.USER_ENTRANCE + userIdInt).equals(new String(Base64Util.decode(o)))) {
                    Integer basicId = Integer.valueOf(new String(Base64Util.decode(c)));
                    Map<String, Object> respMap = null;
                    if (com.hangjia.bxj.common.Constants.USER_ENTRANCE.equals(new String(Base64Util.decode(t)))){
                        respMap = salesSupportService.marchIn(userIdInt.longValue(), basicId);
                    }else if (com.hangjia.bxj.common.Constants.USER_EJECTION.equals(new String(Base64Util.decode(t)))){
                        respMap = salesSupportService.marchOut(userIdInt.longValue(), basicId);
                    }
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.append(JSONObject.toJSONString(respMap));
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            userIdInt = Integer.valueOf(userId);
        }
        com.hangjia.bxj.vo.PlanUserCard userCard = userCardService.queryPlanUserCardById(userIdInt);
        request.setAttribute("userCard", userCard);
        request.setAttribute("type", type);
        if (userCard == null || userCard.getModel() == null) {
            return "/usercard/card_empty";
        }
        if (Constants.USER_CARD_MODEL_SIMPLE == userCard.getModel()) {
            return "/usercard/card_simple";
        } else if (Constants.USER_CARD_MODEL_LIFE == userCard.getModel()) {
            return "/usercard/card_life";
        } else if (Constants.USER_CARD_MODEL_BUSINESS == userCard.getModel()) {
            return "/usercard/card_business";
        }
        return "/usercard/card_empty";
    }

    @Autowired
    private TempMobileMapper tempMobileMapper;
    @Autowired
    private ChampionUserVoucherMapper championUserVoucherMapper;
    /**
     * 临时加券逻辑
     * */
    @RequestMapping("/tempmobile.do")
    @ResponseBody
    private Object tempmobile(HttpServletRequest request, HttpServletResponse response) throws BusinessException, SystemException {
        if(true) return "error";
        List<TempMobile> list = tempMobileMapper.selectAll(null);
        for(TempMobile mobile : list) {
            ChampionUserVoucher voucher = new ChampionUserVoucher();
            voucher.setUserId(Long.valueOf(mobile.getMobile()));
            List<ChampionUserVoucher> vv = championUserVoucherMapper.selectBySelective(voucher);
            if(vv.size() ==0) {
                voucher.setGetAllcounts(3);
                voucher.setTotal(3);
                voucher.setVoucherId(1l);
                voucher.setUseAllcounts(0);
                voucher.setCreateTime(new Date());
                championUserVoucherMapper.insert(voucher);
            } else {
                voucher = vv.get(0);
                voucher.setGetAllcounts(voucher.getGetAllcounts()+3);
                voucher.setTotal(voucher.getTotal()+3);
                voucher.setUpdateTime(new Date());
                championUserVoucherMapper.updateByPrimaryKey(voucher);
            }

        }
        return "success";
    }

}
