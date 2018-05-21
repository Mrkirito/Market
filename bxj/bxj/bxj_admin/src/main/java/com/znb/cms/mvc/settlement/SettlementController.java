package com.znb.cms.mvc.settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.znb.cms.common.Result;
import com.znb.cms.model.mapper.Compensation;
import com.znb.cms.service.ISettlementService;
@Controller
@RequestMapping("/settlement")
public class SettlementController {
	
	@Autowired
	private ISettlementService settlementService;
	
	@RequestMapping("index.page")
	public ModelAndView index() {
		ModelAndView view=new ModelAndView("settlement/settlementlist");
		return view;
	}
	
	@RequestMapping("/list.json")
	@ResponseBody
	public Result suggestionList(Compensation compensation) {
		Result result = new Result();
		int count = settlementService.selectCount(compensation);
		if (count > 0) {
			result.setModel(settlementService.getCompensationList(compensation));
		}
		compensation.setTotalItem(count);
		result.setQuery(compensation);
		return result;
	}
	
	@RequestMapping("/detail.json")
	@ResponseBody
	public Result detail(Integer id) {
		Result result = new Result();
		result.setModel(settlementService.getCompensation(id));
		return result;
	}
}
