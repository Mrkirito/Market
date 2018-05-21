/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.shop.service.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.shop.ShopOrdersDetailMapper;
import com.hangjia.bxj.dao.shop.ShopOrdersMapper;
import com.hangjia.bxj.model.shop.ShopOrders;
import com.hangjia.bxj.model.shop.ShopOrdersDetail;
import com.hangjia.bxj.query.shop.ShopOrdersQuery;
import com.hangjia.bxj.shop.service.ShopOrdersService;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caigp
 * Date: 2016/11/2 15:18
 */
@Service
public class ShopOrdersServiceImpl implements ShopOrdersService {

    @Autowired
    private ShopOrdersMapper shopOrdersMapper;

    @Autowired
    private ShopOrdersDetailMapper detailMapper;
    
    @Override
    public Result getPageList(BaseCommonQuery query) {
        Result<List<ShopOrders>> result = new Result<List<ShopOrders>>();
        int count = shopOrdersMapper.selectByCount(query);
        List<ShopOrders> list = shopOrdersMapper.selectByPage(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    @Override
    public Result getPageListByQuery(ShopOrdersQuery query) {
        Result<List<ShopOrders>> result = new Result<List<ShopOrders>>();
        int count = shopOrdersMapper.selectCountByQuery(query);
        List<ShopOrders> list = shopOrdersMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    @Override
    public ShopOrders selectByPrimaryKey(Long id) {
        return shopOrdersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int selectByCount(BaseCommonQuery query) {
        return shopOrdersMapper.selectByCount(query);
    }


    @Override
    public List<ShopOrders> selectByPage(ShopOrdersQuery query) {
        return shopOrdersMapper.selectByPage(query);
    }

	@Override
	public Map<String, Object> importExcel(MultipartFile file) {
    	Map<String, Object> map=new HashMap<String, Object>();
		try {
			Workbook workbook = null;
			String name = file.getOriginalFilename().toUpperCase();
			if (name.endsWith(".XLS")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			} else if (name.endsWith(".XLSX")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else {
				map.put("success", false);
				map.put("status", "500");
				map.put("msg", "文件格式错误");
				return map;
			}
			List<ShopOrdersDetail> list=new ArrayList<ShopOrdersDetail>();
			Sheet sheet =workbook.getSheetAt(0);
			int m = sheet.getLastRowNum();
			for (int j = 1; j < m + 1; j++) {
				Row n = sheet.getRow(j);
				if (n != null) {
					String company = getCellValue(n.getCell(17));
					String no = getCellValue(n.getCell(18));
					if (StringUtils.isNotBlank(company) && StringUtils.isNotEmpty(no)) {
						ShopOrdersDetail detail = new ShopOrdersDetail();
						detail.setIdx(Integer.parseInt(getCellValue(n.getCell(0))));
						detail.setId(Long.parseLong(getCellValue(n.getCell(12))));
						detail.setExpressCompany(company);
						detail.setExpressNo(no);
						list.add(detail);
					}
				}
			}
			if (list.size() == 0) {
				map.put("success", false);
				map.put("status", "500");
				map.put("msg", "请检查文件内容是否正确");
				return map;
			}
			List<Integer> errors = new ArrayList<Integer>();
			int k=0;
			for (ShopOrdersDetail d : list) {
				ShopOrdersDetail shopOrdersDetail = detailMapper.selectByPrimaryKey(d.getId());
				if(StringUtils.isBlank(shopOrdersDetail.getExpressCompany())&&StringUtils.isBlank(shopOrdersDetail.getExpressNo())){					
					int i = 0;
					try {
						i = detailMapper.updateByPrimaryKeySelective(d);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (i == 0) {
						errors.add(d.getIdx());
					}else{
						k++;
					}
				}
			}
			map.put("success", true);
			map.put("status", "0");
			map.put("msg", "导入成功"+k+"条数据");	
			map.put("errors", errors);	
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("status", "500");
			map.put("msg", "系统异常:"+e.getMessage());
			return map;
		}
		return map;
	}
    private String getCellValue(Cell cell) {  
        String ret="";  
        if(cell!=null){        	
        	switch (cell.getCellType()) {  
        	case Cell.CELL_TYPE_BLANK:  
        		ret = "";  
        		break;  
        	case Cell.CELL_TYPE_BOOLEAN:  
        		ret = String.valueOf(cell.getBooleanCellValue());  
        		break;  
        	case Cell.CELL_TYPE_ERROR:  
        		ret = null;  
        		break;  
        	case Cell.CELL_TYPE_FORMULA:  
        		Workbook wb = cell.getSheet().getWorkbook();  
        		CreationHelper crateHelper = wb.getCreationHelper();  
        		FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
        		ret = getCellValue(evaluator.evaluateInCell(cell));  
        		break;  
        	case Cell.CELL_TYPE_NUMERIC:  
        		if (DateUtil.isCellDateFormatted(cell)) {   
        			Date theDate = cell.getDateCellValue();  
        			ret = new SimpleDateFormat("yyyy-MM-dd").format(theDate);  
        		} else {   
        			ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
        		}  
        		break;  
        	case Cell.CELL_TYPE_STRING:  
        		ret = cell.getRichStringCellValue().getString();  
        		break;  
        	default:  
        		ret = "";  
        	}  
        }
        return ret.trim();  
    }

	@Override
	public Result getShopOrderList(ShopOrdersQuery query) {
		Result result = new Result();
		int count = shopOrdersMapper.getShopOrderListCount(query);
		List<ShopOrdersDetail> list = shopOrdersMapper.getShopOrderList(query);
		list.add(shopOrdersMapper.getSumShopOrder(query));
		result.setModel(list);
		query.setTotalItem(count);
		result.setQuery(query);
		return result;
	}
	

    @Override
    public int getShopOrderListCount(ShopOrdersQuery query) {
        return shopOrdersMapper.getShopOrderListCount(query);
    }
    @Override
    public List<ShopOrdersDetail> getShopOrderListByExport(ShopOrdersQuery query) {
        return shopOrdersMapper.getShopOrderList(query);
    }
}