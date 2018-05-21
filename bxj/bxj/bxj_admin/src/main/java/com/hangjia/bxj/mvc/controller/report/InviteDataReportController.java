package com.hangjia.bxj.mvc.controller.report;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.InvitationDao;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.vo.InviteVo;

@Controller
@RequestMapping("/report")
public class InviteDataReportController {
	@Autowired
	private InvitationDao dao;

	@RequestMapping("invite.jhtml")
	public String list(HttpServletRequest request) {
    	request.setAttribute("begin",DateUtils.getPrefixDate(10));
    	request.setAttribute("end", DateUtils.getCurrentDate());
		return "report/invite";
	}

	@RequestMapping("inviteJson.json")
	public @ResponseBody Result getInviteData(InviteVo vo) {
		if(StringUtils.isNotBlank(vo.getStartDate())&&StringUtils.isNotBlank(vo.getEndDate())){
			vo.setStartDate(vo.getStartDate()+" 00:00:00");
			vo.setEndDate(vo.getEndDate()+" 23:59:59");
		}
		Result result = new Result();
		Map<String, Integer> genMap = listConvertMap(dao.queryInviteGenerate(vo));
		Map<String, Integer> useMap = listConvertMap(dao.queryInviteUser(vo));
		Map<String, Integer> shaMap = listConvertMap(dao.queryInviteShare(vo));
		List<InviteVo> arrs=new ArrayList<InviteVo>();
		for (Map.Entry<String, Integer> entry : genMap.entrySet()) {
			String key=entry.getKey();
			Integer value=entry.getValue();
			InviteVo v=new InviteVo();
			v.setDateStr(key);
			v.setCount1(value);
			if(useMap.containsKey(key)){				
				v.setCount2(useMap.get(key));
			}
			if(shaMap.containsKey(key)){				
				v.setCount3(shaMap.get(key));
			}
			arrs.add(v);
		}
		result.setModel(arrs);
		return result;
	}
	public Map<String,Integer> listConvertMap(List<InviteVo> vo){
		Map<String, Integer> map=new LinkedHashMap<String, Integer>();
		for (InviteVo inviteVo : vo) {
			map.put(DateUtils.format(inviteVo.getDate(),"yyyy-MM-dd"), inviteVo.getCount());
		}
		return map;
	}
}
