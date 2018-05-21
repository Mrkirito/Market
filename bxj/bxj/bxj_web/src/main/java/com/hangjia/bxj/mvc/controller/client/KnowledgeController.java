package com.hangjia.bxj.mvc.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.hangjia.bxj.model.Knowledge;
import com.hangjia.bxj.model.StudyCollection;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.knowledge.KnowledgeService;
import com.hangjia.bxj.service.study.impl.StudyService;
import com.hangjia.bxj.vo.Pagination;

@Controller
@RequestMapping("knowledge")
public class KnowledgeController {

	/**
	 * 学习列表对象
	 */
	@Autowired
	private KnowledgeService knowledgeService;
	
	/**
	 * 学习详情对象
	 */
	@Autowired 
	private StudyService studyService;

	@ResponseBody
	@RequestMapping(value = "list.json", method = RequestMethod.GET)
	public Object list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int limit,
			String versionName, String os) {
		// versionName=2.4&versionCode=10&os=android
		try {
			if (os != null) {
				int num = os.indexOf("a");
				String getos = "";
				if (num == 0) {
					getos = os.substring(num, num + 7);
				}
				String pageNum = "";
				if (versionName != null && versionName.equals("2.4") && getos.equals("android")) {
					// 页码等于1
					page = 1;
					int qeind = os.indexOf("?");
					int pageind = os.indexOf("="); // 页码值
					if (qeind != -1 && pageind != -1) {
						// 是否包含 page
						String pageStr = os.substring(qeind + 1, qeind + 5);
						if (pageStr.equals("page")) {
							pageNum = os.substring(pageind + 1, os.length());
							// page 不等于1 查询 0 返回空
							if (!pageNum.equals("1"))
								limit = 0;

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//老接口  -----
		Pagination<Knowledge> result = knowledgeService.findPage(page, limit);
		return new AjaxResult.success(result);
	}
	
	/**
	 * 查询所有知识
	 * @param know 知识对象
	 * @param index
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listAll.json", method = RequestMethod.GET)
	public Object qrylist(Knowledge know,@RequestParam(defaultValue = "1") int index, @RequestParam(defaultValue = "20") int pageSize) {
		Pagination<Knowledge> result = knowledgeService.listAllPage(know, index, pageSize);
		return new AjaxResult.success(result);
	}
	
	/**
	 * 查询学习更新数量
	 * @param know
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "isReadNum.json", method = RequestMethod.GET)
	public Object qrylist(Knowledge know) {
		 know.setIsRead(0); //未读
	     int num = knowledgeService.selTotal(know);
		return new AjaxResult.success(num);
	}
	
	/**
	 * 修改成已读 
	 * @param know
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateKnow.json", method = RequestMethod.GET)
	public Object updateKnowInfo(Knowledge know) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		if(know.getId()==null){
		     respMap.put("state", 0); //报错 
		     respMap.put("error", "知识id不能为空!"); 
			return new AjaxResult.success(respMap);
		}
		 know.setIsRead(1); //已读
	     int num = knowledgeService.updateByPK(know);
	     respMap.put("state", num); //成功 1
		return new AjaxResult.success(respMap);
	}
	
	/**
	 * 获取收藏学习列表
	 * @param know
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listCollect.json", method = RequestMethod.GET)
	public Object selCollectList(HttpServletRequest request,StudyCollection studyCollection,@RequestParam(defaultValue = "1") int index, @RequestParam(defaultValue = "20") int pageSize) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			// TODO: handle exception
			 respMap.put("state", 0); //报错 
		     respMap.put("error", "userId不能为空!"); 
			//return new AjaxResult.success(respMap);
		}
		return new AjaxResult.success(studyService.selCollectList(studyCollection, index, pageSize));
	}
	
}
