package com.hangjia.bxj.mvc.controller.right;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.right.FunctionQuery;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.right.FunctionDTO;
import com.hangjia.bxj.vo.right.FunctionList;

/** 
* @author  作者 : yaoy
* @date 2016年5月19日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/func")
public class FunctionController extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IRightService rightService;

	/**
	 * 查询按钮列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryFunctionPageData.json")
    public @ResponseBody Result queryFunctionPageData(FunctionQuery query) {
        Result result = new Result();
        List<FunctionDTO> functionDTOList = null;
        int totalCount = 0;

        try {
            totalCount = rightService.queryFunctionPageCount(query);
        }
        catch (Exception e) {
            logger.error("", e);
        }

        try {
            if (totalCount > 0) {
                functionDTOList = rightService.queryFunctionPageData(query);
            }
        }
        catch (Exception e) {
            logger.error("", e);
        }

        query.setTotalItem(totalCount);
        result.setModel(functionDTOList);
        result.setQuery(query);
        return result;
    }

	/**
	 * 保存按钮
	 * @param function
	 * @return Result
	 */
    @RequestMapping("saveFunction.json")
    public @ResponseBody Result saveFunction(FunctionDTO function) {
        Result result = new Result();
        try {
            function.setUpdateName(getLoginUserName());
            rightService.saveFunction(function);
        }
        catch (Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setMsg("保存失败");
        }

        return result;
    }

	/**
	 * 批量保存按钮
	 * @param function
	 * @return Result
	 */
    @RequestMapping("saveFunctionList.json")
    public @ResponseBody Result saveFunctionList(String functionList) {
        Result result = new Result();
        try {
        	if(null != functionList){
	         	List<FunctionDTO> list = JSONObject.parseArray(functionList, FunctionDTO.class);
	         	if(null != list && list.size() > 0) {
	                for(FunctionDTO functionDTO : list) {
	                    functionDTO.setUpdateName(getLoginUserName());
	                }
	            }
	         	rightService.saveFunction(list);
        	}
        } catch (Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setMsg("保存失败");
        }

        return result;
    }

	/**
	 * 删除按钮权限
	 * @param function
	 * @return Result
	 */
    @RequestMapping("deleteFunction.json")
    public @ResponseBody Result deleteFunction(@RequestParam("idList[]") List<String> idList) {
        Result result = new Result();
        try {
            rightService.deleteFunction(idList);
        }
        catch (Exception e) {
            logger.error("", e);
            result.setSuccess(false);
            result.setMsg("保存失败");
        }

        return result;
    }
}
