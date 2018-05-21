package com.hangjia.bxj.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baobao.framework.utils.jedis.RedisUtil;
import com.baobao.sso.client.PlanProductGs;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.PlanBookMapper;
import com.hangjia.bxj.dao.PlanProductBonusMapper;
import com.hangjia.bxj.dao.PlanProductCategroyMapper;
import com.hangjia.bxj.dao.PlanProductCategroyRelMapper;
import com.hangjia.bxj.dao.PlanProductConstantRelMapper;
import com.hangjia.bxj.dao.PlanProductMainMapper;
import com.hangjia.bxj.dao.PlanProductQyMapper;
import com.hangjia.bxj.model.PlanProductBonus;
import com.hangjia.bxj.model.PlanProductCategroy;
import com.hangjia.bxj.model.PlanProductCategroyRel;
import com.hangjia.bxj.model.PlanProductConstant;
import com.hangjia.bxj.model.PlanProductConstantRel;
import com.hangjia.bxj.model.PlanProductFl;
import com.hangjia.bxj.model.PlanProductMain;
import com.hangjia.bxj.model.PlanProductMainWithBLOBs;
import com.hangjia.bxj.model.PlanProductQy;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.query.ProductQuery;
import com.hangjia.bxj.service.CdnOperateService;
import com.hangjia.bxj.service.PlanProductService;
import com.hangjia.bxj.vo.RateFactorVo;


@Controller
@RequestMapping(value = "/product")
public class PlanProductController {	
	
	@Autowired
	private CdnOperateService cdnOperateService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlanBookMapper dao;
	
	@Autowired
	private PlanProductBonusMapper bonusMapper;
	
	@Autowired
	private UserCardSupportService userCardSupportService;
	@Autowired
	private PlanProductMainMapper planProductMainMapper;
	@Autowired
	private PlanProductQyMapper planProductQyMapper;
	@Autowired
	private PlanProductConstantRelMapper productConstantRelMapper;
	
	@Autowired
	private PlanProductService planProductService;
	
	private static Map<Integer, Integer> cacheMap = new Hashtable<Integer, Integer>();
	private static Map<String, PlanProductConstant> cachePlanProductConstant = new Hashtable<String, PlanProductConstant>();
	private static Map<Integer, String> gsMap = new Hashtable<Integer, String>();
	
    @Autowired
    private PlanProductCategroyMapper productCategroyMapper;
    @Autowired
    private PlanProductCategroyRelMapper planProductCategroyRelMapper; 
   
    
	/**
	 * 产品列表
	 * @return
	 */
    @RequestMapping("listProduct.jhtml")
    public ModelAndView listProduct() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PlanProductGs> gs=userCardSupportService.getPlanProductGsList(null);
		for (PlanProductGs planProductGs : gs) {
			gsMap.put(planProductGs.getFid(), planProductGs.getName());
		}
		map.put("company", gs);
    	ModelAndView view=new ModelAndView("planbook/listProduct",map);
        return view;
    }
    @RequestMapping("statistics_book.jhtml")
    public ModelAndView statisticsBook() {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("begin",DateUtils.getPrefixDate(10));
    	map.put("end", DateUtils.getCurrentDate());
    	ModelAndView view=new ModelAndView("planbook/statistics",map);
        return view;
    }
    @RequestMapping("queryCategroy.json")
    @ResponseBody
    public Result queryCategroy() {
    	Result result = new Result();
    	result.setModel(productCategroyMapper.getAllProductCategroys());
    	return result;
    }
    
    
    @RequestMapping("statistics.json")
    @ResponseBody
    public Result statistics(ProductQuery query) {
    	Result result = new Result();
    	String begin=query.getBegin();
    	String end=query.getEnd();
    	if(StringUtils.isEmpty(begin)||StringUtils.isEmpty(end)){
    		query.setBegin(DateUtils.getPrefixDate(10));
    		query.setEnd(DateUtils.getCurrentDate());
    	}
    	int count=planProductMainMapper.queryPlanBookStatisticsCount(query);
    	if(count>0){    		
    		List<ProductQuery> books=planProductMainMapper.queryPlanBookStatistics(query);
    		List<ProductQuery> users=planProductMainMapper.queryUserStatistics(query);
    		List<ProductQuery> res=new ArrayList<ProductQuery>();
    		for (int i = 0; i < books.size(); i++) {
    			ProductQuery p = books.get(i);
    			p.setUserNum(users.get(i).getUserNum());
    			res.add(p);
    		}
    		result.setModel(res);
    	}
		query.setTotalItem(count);
		result.setQuery(query);
	    return result;   	
    }
    /**
     * 异步加载产品数据
     * @param query
     * @return
     */
    @RequestMapping("queryListProduct.json")
    public @ResponseBody Result queryListProduct(ProductQuery query) {
    	Result result = new Result();
    	int count=planProductMainMapper.queryPageDataCount(query);
    	if(count > 0){
    		List<PlanProductMain> planProductMains=planProductMainMapper.queryPageData(query);
    		for (PlanProductMain planProductMain : planProductMains) {
    			List<PlanProductCategroy> categroys=productCategroyMapper.getProductCategroyByPid(planProductMain.getFid());
    			planProductMain.setCategroys(categroys);
				planProductMain.setGsName(gsMap.get(planProductMain.getGs()));
			}
    		result.setModel(planProductMains);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;   	
    }
    @RequestMapping("queryProductDetail.json")
    public @ResponseBody Result queryListProduct(Integer fid) {
    	Result result = new Result();
    	PlanProductMainWithBLOBs planProductMainWithBLOBs=planProductMainMapper.selectByPrimaryKey(fid);
    	planProductMainWithBLOBs.setGsName(gsMap.get(planProductMainWithBLOBs.getGs()));
//    	result.setExtData(gsMap);
		List<PlanProductCategroy> categroys=productCategroyMapper.getProductCategroyByPid(planProductMainWithBLOBs.getFid());
		planProductMainWithBLOBs.setCategroys(categroys);
    	result.setModel(planProductMainWithBLOBs);
        return result;   	
    }
    @RequestMapping("updateProductQy.json")
    public @ResponseBody Result updateProductQy(Integer fid,String adqys) {
    	Result result = new Result();
    	planProductQyMapper.deleteByPid(fid);
    	List<PlanProductQy> qys = JSONArray.parseArray(adqys,PlanProductQy.class);
		for (PlanProductQy record : qys) {
			record.setPid(fid);
			record.setCtime(new Date());
			planProductQyMapper.insertSelective(record);
		}
		result.setModel(qys);
        return result;   	
    }
    @RequestMapping("updateProduct.json")
    public @ResponseBody Result updateProduct(PlanProductMainWithBLOBs vo) {
    	Result result = new Result();
    	if(vo.getFid()==null){
    		vo.setCtime(new Date());
    		planProductMainMapper.insertSelective(vo);
    	}else{    		
    		vo.setUptime(new Date());
    		planProductMainMapper.updateByPrimaryKeySelective(vo);
    	}
    	/**
    	 * 分类信息编辑
    	 */
    	Integer pid=vo.getFid();
    	planProductCategroyRelMapper.deleteByPid(pid);
    	Integer xz=vo.getXz();
    	Integer sxj=vo.getSxj();
		if (xz == 1 && sxj == 1) {			
			String categroyIds=vo.getCategroyIds();
			if(StringUtils.isNotEmpty(categroyIds)){    		
				for (String cid : categroyIds.split(",")) {
					PlanProductCategroyRel rel=new PlanProductCategroyRel();
					rel.setCid(Integer.parseInt(cid));
					rel.setPid(pid);
					planProductCategroyRelMapper.insertSelective(rel);
				}
			}
		}
        return result;   	
    }
    @RequestMapping("queryProductQy.json")
    public @ResponseBody Result queryProductQy(Integer fid,Integer type) {
		Result result = new Result();
		List<PlanProductQy> qies = dao.getPlanProductQies(fid);
		if (type == 1) {
			for (PlanProductQy planProductQy : qies) {
				String head1 = planProductQy.getHead1();
				if(StringUtils.isNotBlank(head1)){
					planProductQy.setHead1(getCal(head1, new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), 0, new BigDecimal(80000), "2"));					
				}
				String head2 = planProductQy.getHead2();
				if(StringUtils.isNotBlank(head2)){
					planProductQy.setHead2(getCal(head2, new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), 0, new BigDecimal(80000), "2"));										
				}
				String head3 = planProductQy.getHead3();
				if(StringUtils.isNotBlank(head3)){
					planProductQy.setHead3(getCal(head3, new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), 0, new BigDecimal(80000), "2"));										
				}
			}
		}
    	result.setModel(qies);
        return result;   	
    }
    private static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	private String getCal(String desc,BigDecimal totalBf, BigDecimal baof,BigDecimal baoe, Integer age,BigDecimal allBaof,String lqnl){
		if (StringUtils.isNotBlank(desc)) {
			desc = desc.replaceAll("AGE", age.toString())
					.replaceAll("TOTALBAOFEI",totalBf.toString())
					.replaceAll("ALLBAOFEI",allBaof == null ? "0" : allBaof.toString())
					.replaceAll("BAOFEI", baof.toString())
					.replaceAll("LQNL", lqnl)
					.replaceAll("BAOE", baoe.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			if (desc.contains("S_")) {
				StringBuffer sb = new StringBuffer();
				for (String string : desc.split("S_")) {
					String nr = "";
					try {
						nr = jse.eval(string).toString();
						if (StringUtils.isNotEmpty(nr)) {
							Double b = Double.parseDouble(nr);
							nr =b.intValue()+"";
						}
					} catch (Exception e) {
						nr = string;
					}
					sb.append(nr);
				}
				return sb.toString();
			} else {
				return desc;
			}
		}
		return "";
	}
	
	@RequestMapping("/refresh.json")
	private String index(String key,String entre, HttpServletResponse response) {
		response.setHeader("Connection", "Close");
		response.setCharacterEncoding("utf-8");
		String result = "fail";
		if (key != null && key.trim().length() != 0) {
			String[] keyList = key.split(",");
			redisUtil.delKeys(keyList);
			result = "suceess";
		}
		try {
			response.getWriter().print(key + "===refresh===" + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	@RequestMapping("/refreshAllProduct.json")
	private String refreshAllProduct(String entre,String pids,HttpServletResponse response) {
		response.setHeader("Connection", "Close");
		response.setCharacterEncoding("utf-8");
		if(StringUtils.isEmpty(pids)){				
			List<PlanProductMain> list = dao.listPlanProductMains();
			int size = list.size();
			String[] keyList = new String[size];
			for (int i = 0; i < size; i++) {
				keyList[i]="singlePlanProductKey_"+list.get(i).getFid();
			}
			try {
				redisUtil.delKeys(keyList);
				response.getWriter().print("all===refresh===success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			String[] pidArray = pids.split(",");
			String[] keyList = new String[pidArray.length];
			for (int i = 0; i < pidArray.length; i++) {
				keyList[i]="singlePlanProductKey_"+pidArray[i];
			}
			try {
				redisUtil.delKeys(keyList);
				response.getWriter().print("all===refresh===success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	@RequestMapping("/import.jhtml")
	public ModelAndView list(Integer pfid) {
			List<PlanProductConstant> inputs=new ArrayList<PlanProductConstant>();
			List<PlanProductConstant> selects=new ArrayList<PlanProductConstant>();
			List<PlanProductConstant> parents = dao.getAllProductConstants(0);
			for (PlanProductConstant parent : parents) {
				if(parent.getInputType()==2){					
					Integer fid = parent.getFid();
					List<PlanProductConstant> childs = dao.getAllProductConstants(fid);
					for (PlanProductConstant child : childs) {
						cacheMap.put(child.getFid(), fid);
						cachePlanProductConstant.put(fid+child.getName(), child);
					}
					parent.setChildren(childs);
					selects.add(parent);
				}else{
					inputs.add(parent);
				}
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("inputs", inputs);
			map.put("selects", selects);
			map.put("selectsjson", JSONObject.toJSONString(selects));
			map.put("pfid", pfid);
			if(pfid!=null){
				map.put("product", dao.getPlanProductMainById(pfid));
			}
			return new ModelAndView("/planbook/import",map);
	}
	@RequestMapping("/importBonus.jhtml")
	public ModelAndView importBonus(Integer pfid) {
			List<PlanProductConstant> inputs=new ArrayList<PlanProductConstant>();
			List<PlanProductConstant> selects=new ArrayList<PlanProductConstant>();
			List<PlanProductConstant> parents = dao.getAllProductConstants(0);
			for (PlanProductConstant parent : parents) {
				if(parent.getInputType()==2){					
					Integer fid = parent.getFid();
					List<PlanProductConstant> childs = dao.getAllProductConstants(fid);
					for (PlanProductConstant child : childs) {
						cacheMap.put(child.getFid(), fid);
						cachePlanProductConstant.put(fid+child.getName(), child);
					}
					parent.setChildren(childs);
					selects.add(parent);
				}else{
					inputs.add(parent);
				}
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("inputs", inputs);
			map.put("selects", selects);
			map.put("selectsjson", JSONObject.toJSONString(selects));
			map.put("pfid", pfid);
			if(pfid!=null){
				map.put("product", dao.getPlanProductMainById(pfid));
			}
			return new ModelAndView("/planbook/importBonus",map);
	}
	@RequestMapping("/product.jhtml")
	public ModelAndView product() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("gs", userCardSupportService.getPlanProductGsList(null));
		return new ModelAndView("/planbook/product_add",map);
	}
	@RequestMapping("/product.json")
	@ResponseBody
	@Transactional
	public Object genProduct(PlanProductMainWithBLOBs vo,HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		boolean isSuccess=false;
		try {
			if(vo!=null){
				Integer fid = vo.getFid();
				boolean hasPid=fid != null && fid > 0;
				if (hasPid) {
					vo.setUptime(new Date());
					planProductMainMapper.updateByPrimaryKeySelective(vo);
				} else {
					vo.setCtime(new Date());
					planProductMainMapper.insertSelective(vo);
					fid=vo.getFid();
				}
				planProductQyMapper.deleteByPid(fid);
				String adqys=vo.getAdminQys();
				if(StringUtils.isNotEmpty(adqys)){
					List<PlanProductQy> qys = JSONArray.parseArray(adqys,PlanProductQy.class);
					for (PlanProductQy record : qys) {
						record.setPid(fid);
						record.setCtime(new Date());
						planProductQyMapper.insertSelective(record);
					}					
				}
				isSuccess=true;
				map.put("success", isSuccess);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", isSuccess);
		return map;
	}
	@RequestMapping("/excel.json")
	@ResponseBody
	public Object parse(MultipartFile file,RateFactorVo vo,HttpServletRequest request) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			Set<Integer> marks=new HashSet<Integer>();
			marks.add(vo.getBeId());
			String t = file.getOriginalFilename().trim();
			Integer pid = null;
			String pfid = request.getParameter("pfid");
			if (StringUtils.isNotEmpty(pfid)) {
				pid = Integer.parseInt(pfid);
			} else {
				pid = Integer.parseInt(t.substring(0, t.indexOf(".")));
			}
			dao.deletePlanProductFl(pid);
			Integer counts = vo.getCounts();
			boolean isDuo = counts > 1;
			Integer bxnxId = vo.getBxnxId();
			marks.add(bxnxId);
			Integer jfnxId = vo.getJfnxId();
			Integer factor5Id = vo.getFactor5Id();
			boolean isLqnl=vo.getContro().equals("lqnl");
			Integer lqnlId=vo.getLqnlId();
			if(isLqnl){
				marks.add(lqnlId);
			}
			Cell cell;
			for (int a = 0; a < workbook.getNumberOfSheets(); a++) {
				XSSFSheet sheet = workbook.getSheetAt(a);
				Integer bxnx = 6;
				Integer lqnl=-9;
				if (isDuo) {
					if (isLqnl) {
						bxnx=vo.getBxnx();
						String key=(lqnlId + sheet.getSheetName()).trim();
						PlanProductConstant p = cachePlanProductConstant.get(key);
						lqnl = p.getFid();	
						marks.add(lqnl);
					}else{
						String key=(bxnxId + sheet.getSheetName()).trim();
						PlanProductConstant p = cachePlanProductConstant.get(key);
						bxnx = p.getFid();						
					}
				} else {
					bxnx = vo.getBxnx();
				}
				marks.add(bxnx);
				List<Integer> sets=new ArrayList<Integer>();
				sets.clear();
				XSSFRow row = sheet.getRow(1);
				int k = row.getLastCellNum();
				marks.add(jfnxId);
				int bjx=jfnxId;
				if(factor5Id>0){
					marks.add(factor5Id);
					marks.add(vo.getJfnx());
					bjx=factor5Id;
				}
				for (int j = 2; j < k; j++) {
					cell=row.getCell(j);
					if(cell!=null&&org.apache.commons.lang3.StringUtils.isNotBlank(cell.toString().trim())){
						String key=(bjx + cell.toString().trim()).trim();
						if(StringUtils.isNotBlank(key)){
							PlanProductConstant p = cachePlanProductConstant.get(key);
							sets.add(p.getFid());						
						}
					}
				}
				int rows = sheet.getLastRowNum();
				for (int j = 2; j < rows + 1; j++) {
					XSSFRow r = sheet.getRow(j);
					PlanProductFl fl = new PlanProductFl();
					fl.setPid(pid);
					if(r!=null&&r.getCell(0)!=null&&r.getCell(1)!=null){
						Double age = Double.parseDouble(r.getCell(0).toString());
						fl.setAge(age.intValue());
						Double sex = Double.parseDouble(r.getCell(1).toString());
						fl.setSex(sex.intValue());
						fl.setBxnx(bxnx);
						fl.setType(9);
						if(isLqnl){
							fl.setLqnl(lqnl);
						}
						for (int z = 2; z < sets.size()+2; z++) {
							cell = r.getCell(z);
							String val = "";
							if (cell == null || StringUtils.isBlank(cell.toString())) {
								val = "0";
							} else {
								val = cell.toString();
							}
							Integer idFac=sets.get(z-2);
							if(factor5Id>0){
								fl.setFactor5(idFac);
								fl.setJfnx(vo.getJfnx());
							}else{
								fl.setJfnx(idFac);							
							}
							marks.add(idFac);
							fl.setBf(new BigDecimal(val.trim()));
							dao.insertPlanProductFl(fl);
						}
					}
				}
				if (!isDuo) {
					break;
				}
			}
			if(vo.getRel()>0){
				dao.deletePlanProductConstantRel(pid);
				for (Integer mark : marks) {				
					PlanProductConstantRel rels = new PlanProductConstantRel();
					rels.setPid(pid);
					rels.setFindex(1);
					rels.setCid(mark);					
					productConstantRelMapper.insertSelective(rels);
				}
			}
			return new AjaxResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return new AjaxResult.error("error");
	}	
	@RequestMapping("/excelBonus.json")
	@ResponseBody
	public Object excelBonus(MultipartFile file,RateFactorVo vo,HttpServletRequest request) {
		try {
			List<String> errorLinks=new ArrayList<String>();
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			Integer pid = null;
			String pfid = request.getParameter("pfid");
			if (StringUtils.isNotEmpty(pfid)) {
				pid = Integer.parseInt(pfid);
			} else {
				String t = file.getOriginalFilename().trim();
				pid = Integer.parseInt(t.substring(0, t.indexOf(".")));
			}
			bonusMapper.deleteByProductId(pid);
			Integer counts = vo.getCounts();
			boolean isDuo = counts > 1;
			Integer bxnxId = vo.getBxnxId();
			Integer jfnxId = vo.getJfnxId();
			Integer factor5Id = vo.getFactor5Id();
			boolean isLqnl=vo.getContro().equals("lqnl");
			Integer lqnlId=vo.getLqnlId();
			for (int a = 0; a < workbook.getNumberOfSheets(); a++) {
				XSSFSheet sheet = workbook.getSheetAt(a);
				Integer bxnx = 6;
				Integer lqnl=-9;
				if (isDuo) {
					if (isLqnl) {
						bxnx=vo.getBxnx();
						String key=(lqnlId + sheet.getSheetName()).trim();
						PlanProductConstant p = cachePlanProductConstant.get(key);
						lqnl = p.getFid();	
					}else{
						String key=(bxnxId + sheet.getSheetName()).trim();
						PlanProductConstant p = cachePlanProductConstant.get(key);
						bxnx = p.getFid();						
					}
				} else {
					bxnx = vo.getBxnx();
				}
				List<Integer> sets=new ArrayList<Integer>();
				sets.clear();
				XSSFRow row = sheet.getRow(1);
				int k = row.getLastCellNum();
				int bjx=jfnxId;
				if(factor5Id>0){
					bjx=factor5Id;
				}
				for (int j = 2; j < k; j++) {
					String key=(bjx + row.getCell(j).toString().trim()).trim();
					if(StringUtils.isNotBlank(key)&&cachePlanProductConstant.containsKey(key)){
						PlanProductConstant p = cachePlanProductConstant.get(key);
						sets.add(p.getFid());						
					}
				}
				int rows = sheet.getLastRowNum();
				for (int j = 2; j < rows + 1; j++) {
					XSSFRow r = sheet.getRow(j);
					PlanProductBonus fl = new PlanProductBonus();
					fl.setPid(pid);
					if(r!=null&&r.getCell(0)!=null&&r.getCell(1)!=null){
						Double age = Double.parseDouble(r.getCell(0).toString());
						fl.setAge(age.intValue());
						Double sex = Double.parseDouble(r.getCell(1).toString());
						fl.setSex(sex.intValue());
						fl.setBxnx(bxnx);
						if(isLqnl){
							fl.setLqnl(lqnl);
						}
						for (int z = 2; z < k; z++) {
							XSSFCell cell = r.getCell(z);
							if (cell != null && StringUtils.isNotBlank(cell.toString())) {
								String link=cell.toString();
								String returnText=getJsonData(link);
								if(StringUtils.isNotBlank(returnText)){
									returnText = returnText.replaceAll("\"mid\"", "\"middle\"");
									returnText = returnText.replaceAll("'mid'", "'middle'");
									Integer idFac=sets.get(z-2);
									if(factor5Id>0){
										fl.setFactor5(idFac);
										fl.setJfnx(vo.getJfnx());
									}else{
										fl.setJfnx(idFac);							
									}
									fl.setProfit(returnText);
									bonusMapper.insertSelective(fl);
								}else{
									errorLinks.add(link);	
//									returnText=getJsonData(link);
//									if(StringUtils.isNotBlank(returnText)){
//										Integer idFac=sets.get(z-2);
//										if(factor5Id>0){
//											fl.setFactor5(idFac);
//											fl.setJfnx(vo.getJfnx());
//										}else{
//											fl.setJfnx(idFac);							
//										}
//										fl.setProfit(returnText);
//										bonusMapper.insertSelective(fl);
//									}else{
//																			
//									}
								}
							}
						}
					}
				}
			}
			System.out.println("处理完成，异常链接数量："+errorLinks.size());
			for (String errorLink : errorLinks) {
				System.out.println(errorLink);
			}
			return new AjaxResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return new AjaxResult.error("error");
	}
	
	
	private String getJsonData(String link){
		String returnText="";
		try {
			Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (X11; Linux i686; rv:34.0) Gecko/20100101 Firefox/34.0").get();
			Elements e = document.getElementsByTag("script");
			for (Element element : e) {
				String text = element.html();
				if (text.contains("\"profit\":[")) {
					int starts = text.indexOf("\"profit\":[");
					int ends = text.indexOf("],\"rule\":");
					returnText = (text.substring(starts, ends) + "]").replaceAll("\"profit\":", "");
				}else if(text.contains("\"bonusList\":") ){
					int starts = text.indexOf("\"bonusList\":");
					int ends = text.indexOf("],\"extInfo\":");
					returnText = (text.substring(starts, ends) + "]").replaceAll("\"bonusList\":", "");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnText;
	}
	
	@RequestMapping("/upCdnCacheFile.json")
	@ResponseBody
	public Object upCdnCacheFile(String url, String entre) {
		return cdnOperateService.upCdnCacheFile(url);
	}

	@RequestMapping("/upCdnCacheDirectory.json")
	@ResponseBody
	public Object upCdnCacheDirectory(String directory, String entre) {
		return cdnOperateService.upCdnCacheDirectory(directory);
	}
	
	
	@RequestMapping("/getProductVm.json")
	@ResponseBody
	public Object getProductVm(String gs,String pids) {
		List<PlanProductMain> ps = new ArrayList<PlanProductMain>();
		if (gs != null) {
			for (String com : gs.split(",")) {
				ps.addAll(planProductService.getMainProductsByGs(Integer.parseInt(com)));
			}
		} else if (StringUtils.isNotBlank(pids)) {
			for (String pid : pids.split(",")) {
				ps.add(planProductService.getConfirmPlanProductMain(Integer.parseInt(pid)));
			}
		}
		for (PlanProductMain p : ps) {
			StringBuffer sb=new StringBuffer();
			sb.append("<div class=\"common_info\">\n")
			  .append("\t<h1 class=\"assured_info_title tbxz_title\">投保选择</h1>\n")
			  .append("\t<ul class=\"common-info-ul\">\n")
			  .append("\t<div class=\"main_insure_product_div\" data-pid=\"$!product.fid\" data-jbbe=\"$!product.jbbe\" data-cpjc=\"$!product.cpjc\" data-table=\"0\">"+(p.getCalflag()==2?"data-calflag=\"2\"":"")+"\n");
			for (PlanProductConstant c : p.getProductConstants()) {
				if(c.getHide()==0){
					if(c.getChildren().size()>0){						
						PlanProductConstant child=c.getChildren().get(0);
						sb.append("\t\t<input type=\"hidden\" name=\""+c.getColumnField()+"\" value=\""+child.getFid()+"\" class=\"factor\" data-text=\""+child.getName()+"\" />\n");
					}
				}else{
					sb.append("\t\t<li class=\"info-item main_line_height\">\n");
					sb.append("\t\t\t<label class=\"info-name\">"+(c.getColumnField().equals("jfnx")?"缴费期限":c.getName())+"</label>\n");
					Integer inputType=c.getInputType();
					if(inputType==2){
						sb.append("\t\t\t<select class=\"factor\" name=\""+c.getColumnField()+"\">\n");
						for (PlanProductConstant child : c.getChildren()) {
							sb.append("\t\t\t\t<option value=\""+child.getFid()+"\">"+child.getName()+"</option>\n");
						}
						sb.append("\t\t\t</select>\n");
						sb.append("\t\t\t<span class=\"arrowr\"></span>\n");
					}else if(inputType==3){
						sb.append("\t\t\t<input type=\"tel\" placeholder=\""+c.getPlaceholder()+"\" value=\"\" class=\"demo_price factor\" name=\""+c.getColumnField()+"\" maxlength=\"15\"/>\n")
						  .append("\t\t\t<i class=\"icon\">元</i>\n")
						  .append("\t\t\t<span class=\"error-tips\" style=\"display:none;\"></span>\n");
					}else if(inputType==3){
						sb.append("\t\t\t<input type=\"tel\" readonly=\"readonly\" value=\""+c.getDefaultValue()+"\" class=\"factor\" name=\""+c.getColumnField()+"\" maxlength=\"15\"/>\n");
					}
					sb.append("\t\t</li>\n");
				}
			}
			sb.append("\t</div>\n")
			  .append("\t<li class=\"main_item li_table_0\" style=\"display:none\">\n")
			  .append("\t\t<div class=\"creatDes_header\">\n")
			  .append("\t\t\t<span class=\"createDes-title\">\n")
			  .append("\t\t\t\t<em>").append(p.getCpjc()).append("</em>\n")
			  .append("\t\t\t\t<em class=\"price\"></em>\n")
			  .append("\t\t\t</span>\n")
			  .append("\t\t\t<span class=\"createDes-btn\">\n")
			  .append("\t\t\t\t<a class=\"main_edit_btn\" data-table=\"0\">修改</a>\n")
			  .append("\t\t\t</span>\n")
			  .append("\t\t</div>\n")
			  .append("\t\t<table class=\"createDes-table\">\n")
			  .append("\t\t<thead>\n")
              .append("\t\t\t<tr>\n")
              .append("\t\t\t\t<td>险种</td>\n")
						.append("\t\t\t\t<td>保额</td>\n")
						.append("\t\t\t\t<td>保费</td>\n")
						.append("\t\t\t\t<td>保障期限</td>\n")
						.append("\t\t\t\t<td>缴费期限</td>\n")
						.append("\t\t\t</tr>\n")
					.append("\t\t</thead>\n")
				.append("\t\t<tbody></tbody>\n")
			  .append("\t\t</table>\n")
			  .append("\t</li>\n")
			  .append("\t#parse(\"product/addzx.vm\")\n")
			  .append("\t</ul>\n")
			  .append("</div>");
			FileOutputStream fos=null;
			OutputStreamWriter osw=null;
			try {
				File file = new File("F:\\workspace\\bxj\\bxj_web\\src\\main\\webapp\\WEB-INF\\view\\product\\" + p.getFid() + ".vm");
				if(!file.exists()){
					file.createNewFile();
				}
				fos = new FileOutputStream(file);
				osw = new OutputStreamWriter(fos, "UTF-8");   
			    osw.write(sb.toString());   
			    osw.flush();   
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					osw.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new AjaxResult.success("success");
	}
	@RequestMapping("/product_edit.jhtml")
	public ModelAndView productEdit(Integer pfid) {
		Map<String, Object> map=new HashMap<String, Object>(); 	
		List<PlanProductGs> gs= userCardSupportService.getPlanProductGsList(null);
		map.put("gs", gs);
		map.put("qys", dao.getPlanProductQies(pfid));
		map.put("single_product", planProductMainMapper.selectByPrimaryKey(pfid));
		return new ModelAndView("/planbook/product_edit",map);
	}
	@RequestMapping("/product_rule1.json")
	@ResponseBody
	public Object querySingleProductRules(String pids) {
		String[] pidStrs=pids.split(",");
		List<String> rl=new ArrayList<String>();
		List<Integer> fids=new ArrayList<Integer>();
		for (String pidStr : pidStrs) {
			Integer fid=Integer.parseInt(pidStr);
			Map<Integer, List<String>> map=new HashMap<Integer, List<String>>();
			List<ProductQuery> list=planProductMainMapper.querySingleProductRules(fid);
			for (ProductQuery productQuery : list) {
				Integer key=productQuery.getGs();
				String value=productQuery.getBookNum();
				List<String> rules=null;
				if(map.containsKey(key)){
					rules=map.get(key);
				}else{
					rules=new ArrayList<String>();
				}
				rules.add(value);
				map.put(key, rules);
			}
			List<String> dropList=new ArrayList<String>();
			for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
				Integer key=entry.getKey();
				List<String> value=entry.getValue();
				dropList.add(key+":["+StringUtils.join(value, ",")+"]");	
			}
			String ssss=fid+":{\"droplist\":{"+StringUtils.join(dropList, ",")+"},\"num\":1}";
			rl.add(ssss);
			fids.add(fid);
		}
		FileOutputStream fos=null;
		OutputStreamWriter osw=null;
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\rule.js");
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write("var productRules={"+StringUtils.join(rl, ",\n")+"};");   
		    osw.flush();   
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				osw.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(StringUtils.join(fids, ","));
		return new AjaxResult.success("success");
	}
	@RequestMapping("/product_rule2.json")
	@ResponseBody
	public Object queryMultipProductRulesByBxnx(String pids) {
		String[] pidStrs=pids.split(",");
		List<String> rl=new ArrayList<String>();
		List<Integer> fids=new ArrayList<Integer>();
		for (String pidStr : pidStrs) {
			Integer fid=Integer.parseInt(pidStr);
			List<ProductQuery> list=planProductMainMapper.queryMultipProductRulesByBxnx(fid);
			Map<Integer,Map<Integer,List<String>>> allMap=new HashMap<Integer, Map<Integer,List<String>>>();
			for (ProductQuery productQuery : list) {
				Integer sex=productQuery.getGs();
				Map<Integer,List<String>> map=null;
				if(allMap.containsKey(sex)){
					map=allMap.get(sex);
				}else{
					map=new HashMap<Integer,List<String>>();
				}
				Integer key=productQuery.getBq();
				String value=productQuery.getBookNum();
				List<String> rules=null;
				if(map.containsKey(key)){
					rules=map.get(key);
				}else{
					rules=new ArrayList<String>();
				}
				rules.add(value);
				map.put(key, rules);
				allMap.put(sex, map);
			}
			List<String> dropList=new ArrayList<String>();
			for (Map.Entry<Integer,Map<Integer,List<String>>> entry : allMap.entrySet()) {
				Integer key=entry.getKey();
				List<String> dropList2=new ArrayList<String>();
				for (Map.Entry<Integer, List<String>> entryc : entry.getValue().entrySet()) {
					dropList2.add(entryc.getKey()+":["+StringUtils.join(entryc.getValue(), ",")+"]");
				}
				dropList.add(key+":{"+StringUtils.join(dropList2, ",")+"}");					
			}
			rl.add(fid+":{\"droplist\":{"+StringUtils.join(dropList, ",")+"},\"fac\":\"bxnx\",\"num\":2}");
			fids.add(fid);
		}
		FileOutputStream fos=null;
		OutputStreamWriter osw=null;
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\rule.js");
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write("var productRules={"+StringUtils.join(rl, ",\n")+"};");   
		    osw.flush();   
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				osw.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(StringUtils.join(fids, ","));
		return new AjaxResult.success("success");
	}
	@RequestMapping("/product_rule3.json")
	@ResponseBody
	public Object queryMultipProductRulesByLqnl(String pids) {
		String[] pidStrs=pids.split(",");
		List<String> rl=new ArrayList<String>();
		List<Integer> fids=new ArrayList<Integer>();
		for (String pidStr : pidStrs) {
			Integer fid=Integer.parseInt(pidStr);
			List<ProductQuery> list=planProductMainMapper.queryMultipProductRulesByLqnl(fid);
			Map<Integer,Map<Integer,List<String>>> allMap=new HashMap<Integer, Map<Integer,List<String>>>();
			for (ProductQuery productQuery : list) {
				Integer sex=productQuery.getGs();
				Map<Integer,List<String>> map=null;
				if(allMap.containsKey(sex)){
					map=allMap.get(sex);
				}else{
					map=new HashMap<Integer,List<String>>();
				}
				Integer key=productQuery.getBq();
				String value=productQuery.getBookNum();
				List<String> rules=null;
				if(map.containsKey(key)){
					rules=map.get(key);
				}else{
					rules=new ArrayList<String>();
				}
				rules.add(value);
				map.put(key, rules);
				allMap.put(sex, map);
			}
			List<String> dropList=new ArrayList<String>();
			for (Map.Entry<Integer,Map<Integer,List<String>>> entry : allMap.entrySet()) {
				Integer key=entry.getKey();
				List<String> dropList2=new ArrayList<String>();
				for (Map.Entry<Integer, List<String>> entryc : entry.getValue().entrySet()) {
					dropList2.add(entryc.getKey()+":["+StringUtils.join(entryc.getValue(), ",")+"]");
				}
				dropList.add(key+":{"+StringUtils.join(dropList2, ",")+"}");					
			}
			rl.add(fid+":{\"droplist\":{"+StringUtils.join(dropList, ",")+"},\"fac\":\"lqnl\",\"num\":2}");
			fids.add(fid);
		}
		FileOutputStream fos=null;
		OutputStreamWriter osw=null;
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\rule.js");
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write("var productRules={"+StringUtils.join(rl, ",\n")+"};");   
		    osw.flush();   
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				osw.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(StringUtils.join(fids, ","));
		return new AjaxResult.success("success");
	}
}
