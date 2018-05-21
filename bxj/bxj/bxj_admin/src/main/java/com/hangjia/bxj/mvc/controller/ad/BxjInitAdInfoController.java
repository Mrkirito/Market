package com.hangjia.bxj.mvc.controller.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.BxjInitAdInfoMapper;
import com.hangjia.bxj.model.BxjInitAdInfo;
import com.hangjia.bxj.query.ad.BxjInitAdQuery;
@Controller
@RequestMapping("/ad")
public class BxjInitAdInfoController {
	@Autowired
	private BxjInitAdInfoMapper bxjInitAdInfoMapper;
	
    @RequestMapping("list.jhtml")
    public String list() {
        return "/ad/ad_list";
    }
    
    @RequestMapping("selectAds.json")
    @ResponseBody
    public Result selectAds(BxjInitAdQuery query) {
    	Result result = new Result();
		int count=bxjInitAdInfoMapper.selectCount(query);
		if(count>0){
			result.setModel(bxjInitAdInfoMapper.selectList(query));			
		}
		query.setTotalItem(count);
		result.setQuery(query);
        return result;
    }
    
    @RequestMapping("detail.json")
    @ResponseBody
    public Result detail(Long fid) {
    	Result result = new Result();
    	result.setModel(bxjInitAdInfoMapper.selectByPrimaryKey(fid));
        return result;
    }
    
    @RequestMapping("updateAd.json")
    @ResponseBody
    public Result updateAd(BxjInitAdInfo vo) {
    	Result result = new Result();
    	result.setModel(bxjInitAdInfoMapper.updateByPrimaryKeySelective(vo));
        return result;
    }
    
    
    @RequestMapping("addAd.json")
    @ResponseBody
    public Result addAd(BxjInitAdInfo vo) {
    	Result result = new Result();
    	result.setModel(bxjInitAdInfoMapper.insertSelective(vo));
        return result;
    }
}
