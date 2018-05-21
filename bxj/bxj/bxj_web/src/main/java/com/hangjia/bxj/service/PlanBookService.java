package com.hangjia.bxj.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.baobao.framework.security.Authorization;
import com.baobao.framework.utils.jedis.RedisUtil;
import com.baobao.sso.client.PlanProductGs;
import com.baobao.sso.client.UserCardReqDto;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.dao.BannerDao;
import com.hangjia.bxj.dao.ControlIconDao;
import com.hangjia.bxj.dao.InvitationDao;
import com.hangjia.bxj.dao.MyCustomerDao;
import com.hangjia.bxj.dao.PlanBookFeedbacksMapper;
import com.hangjia.bxj.dao.PlanBookMapper;
import com.hangjia.bxj.dao.PlanBookVoiceMapper;
import com.hangjia.bxj.dao.PlanGroupProInfosMapper;
import com.hangjia.bxj.dao.PlanProductBonusMapper;
import com.hangjia.bxj.dao.PlanProductZhjzMapper;
import com.hangjia.bxj.dao.SysMessageMapper;
import com.hangjia.bxj.exception.SystemException;
import com.hangjia.bxj.model.Banner;

import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.CustomersPlanBook;
import com.hangjia.bxj.model.Icon;
import com.hangjia.bxj.model.InvitationAppointment;
import com.hangjia.bxj.model.PlanBook;
import com.hangjia.bxj.model.PlanBookFeedbacks;
import com.hangjia.bxj.model.PlanBookVoice;
import com.hangjia.bxj.model.PlanBookproRel;
import com.hangjia.bxj.model.PlanDecision;
import com.hangjia.bxj.model.PlanGroupProInfos;
import com.hangjia.bxj.model.PlanProductBonus;
import com.hangjia.bxj.model.PlanProductConstant;
import com.hangjia.bxj.model.PlanProductFl;
import com.hangjia.bxj.model.PlanProductMain;
import com.hangjia.bxj.model.PlanProductQy;
import com.hangjia.bxj.model.PlanProductSimple;
import com.hangjia.bxj.model.PlanProductZhjz;
import com.hangjia.bxj.model.SysMessage;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.hangjia.bxj.vo.Pagination;
import com.hangjia.bxj.vo.PlanBonusResult;
import com.hangjia.bxj.vo.PlanProductBonusVo;
import com.hangjia.bxj.vo.PlanUserCard;
import com.hangjia.bxj.vo.QueryProductVo;
import com.ibaoxianjia.activity.service.ActivitySupportService;
@Service
@Transactional(rollbackFor=Throwable.class)
public class PlanBookService{
	private static Logger log = LoggerFactory.getLogger(PlanBookService.class);
	private static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	@Autowired
	private PlanBookMapper dao;
	@Autowired
	private MyCustomerDao myCustomerDao;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private InvitationDao invitationDao;
	@Autowired
	private PlanBookVoiceMapper planBookVoiceMapper;
	@Autowired
	private ControlIconDao controlIconDao;
	@Autowired
	private BannerDao bannerDao;
	@Autowired
	private PlanProductZhjzMapper planProductZhjzMapper;

	@Autowired
	private PlanUserCardService userCardService;
	@Autowired
	private UserCardSupportService userCardSupportService;
	
	@Autowired
	private ControlAppStoreService controlAppStoreService;
		
	@Autowired
	private PlanProductBonusMapper productBonusMapper;
	
	@Autowired
	private SysMessageMapper sysMessageMapper;
	
	@Autowired
	private PlanBookFeedbacksMapper feedbacksMapper;
	
	@Autowired
	private PlanGroupProInfosMapper groupProInfosMapper;
	@Autowired
	private ActivitySupportService  activitySupportService;
	/**
	 * 计划书编辑页面
	 * @param pid
	 * @param userId
	 * @return
	 */
	public Map<String, Object> make(Integer pid,Integer userId,boolean isAd) {
		Map<String, Object> map=new HashMap<String, Object>();
		PlanProductMain product = getPlanProductMainByPid(pid);
		boolean boo=false;
		if (null != product) {
			String link=product.getLink();
			String target="";
			if(StringUtils.isNotEmpty(link)&&!isAd){
				target=link;
				boo=true;
			}else{				
				int control = product.getControl();
				switch (control) {
				case 1:
					target="/product/product";
					break;
				case 2:
					target="/product/common";
					break;
				default:
					target="/product/"+pid;
					break;
				}
			}
			map.put("target", target);
			map.put("fid", pid);
			map.put("product", product);
			map.put("userId", userId);
			loadUserCardInfos(map, userId);
			loadBirthdayInfos(map, product);
		}else{
			map.put("target", "/error");			
		}
		map.put("goAd", boo);
		return map;
	}
	
	public Map<String, Object> newMake(Integer pid, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		PlanProductMain product = getPlanProductMainByPid(pid);
		if (null != product) {
			String target = "";
			int control = product.getControl();
			switch (control) {
			case 1:
				target="/product/product";
				break;
			case 2:
				target="/product/common";
				break;
			default:
				target="/product/"+pid;
				break;
			}
			map.put("target", target);
			map.put("fid", pid);
			map.put("product", product);
			map.put("userId", userId);
			loadUserCardInfos(map, userId);
			loadBirthdayInfos(map, product);
		} else {
			map.put("target", "/error");
		}
		return map;
	}
	
	public Map<String, Object> finalPage(Long planId,String customerId) {
		Map<String, Object> map=new HashMap<String, Object>();
		PlanBook planBook=dao.getPlanBookByFid(planId);
		List<PlanProductQy> qys=new ArrayList<PlanProductQy>();
		if(null!=planBook){			
			int zxPid=planBook.getPid();
			double allBf=0d;
			List<PlanBookproRel> rels=dao.getAllBookproRels(planId);
			Map<Integer,PlanProductConstant> pMap=getPlanProductConstantMap();
			for (PlanBookproRel rel : rels) {
				Integer pid = rel.getPid();
				PlanProductMain product = getPlanProductMainByPid(pid);
				rel.setPlanProductMain(product);
				Integer jfnx=rel.getJfnx();
				if(jfnx!=null){
					rel.setJfnxConstant(pMap.get(jfnx));
				}
				Integer bxnx=rel.getBxnx();
				if(bxnx!=null){
					rel.setBxnxConstant(pMap.get(bxnx));
				}
				Integer lqnl=rel.getLqnl();
				if(lqnl!=null){
					rel.setLqnlConstant(pMap.get(lqnl));
				}
				if (pid == zxPid) {
					Integer genPage=product.getGenpage();
					if(genPage==3||genPage==4){
						PlanProductZhjz vo=new PlanProductZhjz();
						vo.setPid(pid);
						vo.setAge(planBook.getAge());
						vo.setSex(planBook.getSex());
						map.put("zhjz", planProductZhjzMapper.selectByPlanProductZhjz(vo));
					}
					map.put("target", genPage==0?"/plan/final0":genPage>4?"/plan/final"+genPage:"/plan/final1");
					map.put("mainRel", rel);
					/**主险  分红  数据加载*/
					if(pid.intValue() == 216){
						loadBonusInfosOfProducts(planBook, rel, rels, product, map);
					} else {
						loadBonusInfos(planBook,rel,product,map);
					}
				}
				String v = "0";
				if (rel.getJfnxConstant() != null&& StringUtils.isNotEmpty(rel.getJfnxConstant().getValue())) {
					v = rel.getJfnxConstant().getValue();
				}
				double jfnxs = Double.parseDouble(v);
				allBf += rel.getBf().doubleValue() * jfnxs;
			}
			planBook.setTotalBf(new BigDecimal(allBf));
			for (PlanBookproRel rel : rels) {
				List<PlanProductQy> qies = rel.getPlanProductMain().getPlanProductQies();
				String lqnl = "";
				Integer lqnlIn=rel.getLqnl();
				Integer bzlqIn= rel.getBzlq();
				if (lqnlIn != null && rel.getLqnlConstant() != null) {
					String lqnxValue=rel.getLqnlConstant().getValue();
					lqnl = StringUtils.isNotEmpty(lqnxValue)?lqnxValue:"";
				}
				PlanProductMain ppm=rel.getPlanProductMain();
				for (PlanProductQy qy : qies) {
					if(StringUtils.isNotBlank(qy.getHead1())){
						changeProductQy(qy, planBook.getBf(), rel.getBf(),rel.getBe(), planBook.getAge(),planBook.getTotalBf(),lqnl);						
						int bjmb=ppm.getBjmb();
						/**乐享福权益*/
						if(bjmb==4&&StringUtils.isNotEmpty(lqnl)&&bzlqIn!=null){
							String qyName=qy.getName().trim();
							if(qyName.equals(bzlqIn+"")||qyName.equals("身故保障金")||qyName.equals(bzlqIn+""+planBook.getSex()+""+lqnlIn)){
								qys.add(qy);
							}
						}
						else if(bjmb==12&&StringUtils.isNotEmpty(qy.getName())){
							/**某个缴费年限下的权益*/
							if(qy.getName().equals(rel.getJfnx()+"")){
								qys.add(qy);							
							}
							/**某个保障年限下的权益*/
							else if(qy.getName().equals(rel.getBxnx()+"")){
								qys.add(qy);
							}
						}
						/**某个年龄的权益*/
						else if(bjmb==15&&StringUtils.isNotEmpty(qy.getName())){
							int limiteAge = Integer.parseInt(qy.getName());
							if (limiteAge > planBook.getAge()) {
								qys.add(qy);
							}else if(limiteAge<planBook.getAge()){
								qys.add(qy);
							}
						}
						else{
							qys.add(qy);
						}													
					}
				}
			}
			map.put("qyCounts", qys.size());
			map.put("qys", qys);
			map.put("rels", rels);	
			map.put("plan", planBook);		
			map.put("customerId", customerId);
			loadUserCardInfos(map,planBook.getUserId());
			loadCustomerReturnInfos(map,planBook);
			loadVoiceInfos(map, planBook.getVoiceId());
			loadCoverInfos(map, planBook.getPlanxh());
		}
		return map;
	}
	private void changeProductQy(PlanProductQy p,BigDecimal totalBf, BigDecimal baof,BigDecimal baoe, Integer age,BigDecimal allBaof,String lqnl){
		String head1 =getCal(p.getHead1(), totalBf, baof, baoe, age, allBaof, lqnl);
		p.setHead1(head1);
		String head2 =getCal(p.getHead2(), totalBf, baof, baoe, age, allBaof, lqnl);
		p.setHead2(head2);
		String head3 =getCal(p.getHead3(), totalBf, baof, baoe, age, allBaof, lqnl);
		p.setHead3(head3);
	}
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
	/**
	 * 生成计划书
	 * @param planBook
	 * @return
	 */
	@Transactional
	public Map<String, Object> bulid(PlanBook planBook) {
		Map<String, Object> map = new HashMap<String, Object>();
		planBook.setCtime(new Date());
		int i=dao.insertPlanBook(planBook);
		boolean boo=false;
		if(i>0){
			Long planId=planBook.getFid();
			List<PlanBookproRel> planBookproRels = JSONArray.parseArray(planBook.getProRels(),PlanBookproRel.class);
			for (PlanBookproRel planBookproRel : planBookproRels) {
				planBookproRel.setPlanid(planId);
				planBookproRel.setCtime(new Date());
				dao.insertPlanBookproRel(planBookproRel);
			}
			map.put("planId", planId);
			boo=true;
			String author=planBook.getAuthor();
			if(null!=author&&author.trim().length()!=0){				
				map.putAll(saveCustomer(planBook));
			}
			String userCardName=planBook.getUserCardName();
			Integer userId=planBook.getUserId();
			if(StringUtils.isNotEmpty(userCardName)){
				try {
					Authorization au=WebUtils.getAuthorization();
					UserCardReqDto userCardReqDto = new UserCardReqDto();
					userCardReqDto.setFid(userId);
					userCardReqDto.setName(userCardName);
					userCardReqDto.setPhone(au!=null&&StringUtils.isNotEmpty(au.getUsername())?au.getUsername():"");
					userCardReqDto.setSex(Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_SEX)));
					userCardReqDto.setModel(Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_MODEL)));
					userCardReqDto.setCompanyCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY));
					userCardReqDto.setJobCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION));
					userCardReqDto.setJob(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION_NAME));
					userCardReqDto.setCompany(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY_NAME));
					userCardReqDto.setCities(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_CITIES_NAME));
					userCardSupportService.saveUserCardBySelective(userCardReqDto);
				} catch (Exception e) {
					log.error("添加名片异常.....",e);
				}
			}
		}
		map.put("success", boo);
		try {
			Long articleId=planBook.getArticleId();
			if (articleId != null) {
				log.error("activitySupportService.planMakeStat begin.....");
				activitySupportService.planMakeStat(planBook.getUserId().longValue(),articleId);
			}
		} catch (Exception e) {
			log.error("存储开门红计划书推广信息异常.....",e);
		}
		return map;
	}
	/**
	 * 存储客户信息
	 * @param planBook
	 * @return
	 */
	private Map<String, Object> saveCustomer(PlanBook planBook) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean boo = false;
		String name = planBook.getAuthor();
		Integer userId = planBook.getUserId();
		Long cusId = planBook.getCustomerId();
		if (cusId == null) {
			Customer customer = new Customer();
			customer.setUserId(userId);
			customer.setName(name);
			customer.setSex(planBook.getSex()==1?0:1);
			int j = myCustomerDao.insertCustomer(customer);
			if (j > 0) {
				cusId = customer.getId();
				CustomersPlanBook customersPlanBook = new CustomersPlanBook();
				customersPlanBook.setCustomerId(cusId);
				customersPlanBook.setBookId(planBook.getFid());
				myCustomerDao.insertCustomerBookRel(customersPlanBook);
				boo = true;
			}
		} else {
			CustomersPlanBook customersPlanBook = new CustomersPlanBook();
			customersPlanBook.setCustomerId(cusId);
			customersPlanBook.setBookId(planBook.getFid());
			myCustomerDao.insertCustomerBookRel(customersPlanBook);
			boo = true;
		}
		map.put("customerId", cusId);
		map.put("saveCustomerSuccess", boo);
		return map;
	}
	public Map<String, Object> listCustomers(PlanBook planBook) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customers", myCustomerDao.listMyCustomersByName(planBook));
		return map;
	}
	public Map<String, Object> rate(PlanProductFl rate) {
		Map<String, Object> map=new HashMap<String, Object>();
		PlanProductFl planProductFl=dao.getPlanProductFl(rate);
		double bf=0;
		if (null != planProductFl && null != planProductFl.getBf()) {
			bf = planProductFl.getBf().doubleValue();
		}
		rate.setBf(new BigDecimal(bf));
		map.put("rate", rate);
		return map;
	}
	
	public Map<String, Object> query(QueryProductVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setOffset((vo.getCurrentPage() - 1) * vo.getPageSize());
		map.put("list", dao.getPlanProductMainsByPage(vo));
		return map;
	}
	
	@Transactional(readOnly=true)
	public Pagination<PlanProductSimple> querySimple(QueryProductVo vo) {
		int total = dao.getCount(vo);
		List<PlanProductSimple> rows;
		if (total > 0) {
			vo.setOffset((vo.getCurrentPage() - 1) * vo.getPageSize());
			rows = dao.getPlanProductSimpleByPage(vo);
		} else {
			rows = new LinkedList<PlanProductSimple>();
		}
		
		return new Pagination<PlanProductSimple>(total, rows);
	}
	
	public Map<String,Object> savedecision(PlanDecision planDecision){
		planDecision.setCtime(new Date());
		int i = dao.insertPlanDecision(planDecision);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("success", i > 0);
		return map;
	}
	
	public List<PlanProductGs> getProductGs() {
		List<PlanProductGs> planProductGs = (List<PlanProductGs>) redisUtil.getUnserializeKey(RedisKeyConstants.PlanProductGsKey);
		if (planProductGs == null) {
			planProductGs = userCardSupportService.getPlanProductGsList(null);
			if (planProductGs != null && planProductGs.size() > 0) {
				redisUtil.setSerializeKey(RedisKeyConstants.PlanProductGsKey, planProductGs);
			}
		}
		return planProductGs;
	}
	
	public Map<Integer,PlanProductConstant> getPlanProductConstantMap() {
		Map<Integer,PlanProductConstant> map = (Map<Integer,PlanProductConstant>) redisUtil.getUnserializeKey(RedisKeyConstants.PlanProductConstantMapKey);
		if (map == null) {
			List<PlanProductConstant> planProductConstants= dao.getProductConstantList();
			if (planProductConstants != null && planProductConstants.size() > 0) {
				map=new HashMap<Integer, PlanProductConstant>();
				for (PlanProductConstant planProductConstant : planProductConstants) {
					map.put(planProductConstant.getFid(), planProductConstant);
				}
				redisUtil.setSerializeKey(RedisKeyConstants.PlanProductConstantMapKey, map);
			}
		}
		return map;
	}
	
	public List<Icon> getIcons() {
		return controlIconDao.getIcon();
	}
	
	public List<Banner> getBanners() {
		return bannerDao.list();
	}
	
	public List<PlanProductConstant> getProductConstants(Integer pid,Integer fatherId){
		Map<String,Object> para=new HashMap<String, Object>();
		para.put("pid", pid);
		para.put("fatherId", fatherId);
		return dao.getProductConstants(para);
	}
	
	public PlanProductMain getPlanProductMainByPid(Integer pid){
		PlanProductMain product = null;
		String key = RedisKeyConstants.SinglePlanProductKey + pid;
		product = (PlanProductMain) redisUtil.getUnserializeKey(key);
		if (null == product) {
			product = dao.getPlanProductMainById(pid);
			if(null!=product){				
				/*组装年龄*/
				int start = product.getTbnlks();
				int end = product.getTbnljs();
				List<Integer> ages = new ArrayList<Integer>();
				for (int i = start; i <= end; i++) {
					ages.add(i);
				}
				product.setAges(ages);				
				/*权益*/
				List<PlanProductQy> list = dao.getPlanProductQies(pid);
				product.setPlanProductQies(list);			
				/*组装公司名称*/
				List<PlanProductGs> gs=getProductGs();
				for (PlanProductGs planProductGs : gs) {
					if(planProductGs.getFid().intValue()==product.getGs()){
						product.setGsName(planProductGs.getName());
					}
				}
				if (product != null) {
					redisUtil.setSerializeKey(key, product);
				}
			}
		}
		return product;
	}
	
	public List<PlanProductMain> getMainProducts(Integer gs){
		List<PlanProductMain> planProductMains=dao.getMainProducts(gs);
		return planProductMains;
	}
	@Transactional
	public Map<String,Object> agree(CustomersPlanBook cpb){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			boolean hasCustomer=null!=cpb.getCustomerId();
			if (hasCustomer) {
				myCustomerDao.updateCustomersPlanBook(cpb);
			}
			Integer flag=cpb.getFlag();
			PlanBookFeedbacks feedbacks=new PlanBookFeedbacks();
			feedbacks.setBookId(cpb.getBookId());
			feedbacks.setUserId(cpb.getUserId());
			feedbacks.setRes(flag);
			feedbacksMapper.insertSelective(feedbacks);
			
			SysMessage sm=new SysMessage();
			String fomat="";
			if(flag==1){
				fomat=controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_PLAN_BOOK_REALIZE);		
			}else if(flag==2){
				fomat=controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_PLAN_BOOK_REGARD);
			}
			sm.setMsg(String.format(fomat, hasCustomer?cpb.getName():"投保人",cpb.getDate(),cpb.getBookName()));				
			sm.setUserId(cpb.getUserId());
			sm.setSendId(0L);
			sm.setMsgType(3);
			sm.setIsRead(0);
			sm.setStatus(1);
			sm.setCreateTime(new Date());
			sysMessageMapper.insertSelective(sm);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@Transactional
	public int savePlanBookFeedbacks(PlanBookFeedbacks feedbacks){
		try {
			feedbacksMapper.insertSelective(feedbacks);
			feedbacks.setRes(0);
			int count=feedbacksMapper.getCountByPlanBookFeedbacks(feedbacks);
			if(count>0){			
				SysMessage record=new SysMessage();
				record.setUserId(feedbacks.getUserId());
				record.setMsgType(3);
				record.setMsgName(feedbacks.getBookId()+"");
				List<SysMessage> list=sysMessageMapper.querySysMessageDetail(record);
				SysMessage result=null;
				boolean isSave=false;
				if(list.size()>0){
					result=list.get(0);
					result.setUpdateTime(new Date());
				}else{
					result=new SysMessage();
					result.setUserId(feedbacks.getUserId());
					result.setSendId(0L);
					result.setMsgType(3);
					result.setCreateTime(new Date());
					result.setMsgName(feedbacks.getBookId()+"");
					isSave=true;
				}
				result.setIsRead(0);
				result.setStatus(1);
				String fomat=controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_PLAN_BOOK_STATISTICS);		
				result.setMsg(String.format(fomat, feedbacks.getDate(),feedbacks.getBookName(),count));
				if (isSave) {
					sysMessageMapper.insertSelective(result);
				} else {
					sysMessageMapper.updateByPrimaryKeySelective(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	public Map<String,Object> updatePlanUserCard(PlanUserCard planUserCard){
		Map<String, String> para =new HashMap<String, String>();
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		Integer fid = planUserCard.getFid();
		boolean no=false;
		if (fid != null) {
			para.put("fid", fid+"");
			PlanUserCard userCard = userCardService.selectByPrimaryKey(para);
			if (userCard != null) {
				planUserCard.setUptime(new Date());
				Map<String, Object> z = userCardService.upPlanUserCard(planUserCard);
				i = 1;
			}else{
				no=true;
			}
		}else{
			no=true;
		}
		if(no){
			para.put("name", planUserCard.getName() != null ? planUserCard.getName() : "");
			para.put("sex", planUserCard.getSex() != null ? planUserCard.getSex() + "" : "");
			para.put("companyCode", planUserCard.getCompanyCode()!=null?planUserCard.getCompanyCode():"");
			para.put("company", planUserCard.getCompany()!=null?planUserCard.getCompany():"");
			para.put("area", planUserCard.getArea()!=null?planUserCard.getArea():"");
			para.put("areaCode", planUserCard.getAreaCode()!=null?planUserCard.getAreaCode():"");
			para.put("job", planUserCard.getJob()!=null?planUserCard.getJob():"");
			para.put("jobCode", planUserCard.getJobCode()!=null?planUserCard.getJobCode():"");
			para.put("phone", planUserCard.getPhone()!=null?planUserCard.getPhone():"");
			try {
				userCardService.addPlanUserCard(para);
				i=1;
			} catch (SystemException e) {
				e.printStackTrace();
			}	
		}
		map.put("success", i > 0);
		return map;
	}
		
	public Map<String,Object> saveInvitationAppointment(InvitationAppointment invitationAppointment){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", invitationDao.saveInvitationAppointment(invitationAppointment)> 0);
		return map;
	}
	
	public Map<String,Object> savePlanGroupProInfos(PlanGroupProInfos vo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", groupProInfosMapper.insertSelective(vo)> 0);
		return map;
	}
	
	/**
	 * 获取分红信息
	 * @param planBook
	 * @param rel
	 * @param product
	 * @param map
	 */
	private void loadBonusInfos(PlanBook planBook,PlanBookproRel rel,PlanProductMain product,Map<String, Object> map){
		List<PlanBonusResult> list=null;
		boolean hasBonus=false;
		Integer bonusState=product.getBonusState();
		if(null!=bonusState&&bonusState>0){
			PlanProductBonusVo bonusVo=new PlanProductBonusVo();
			bonusVo.setAge(planBook.getAge());
			bonusVo.setPid(planBook.getPid());
			bonusVo.setSex(planBook.getSex());
			bonusVo.setJfnx(rel.getJfnx());
			bonusVo.setBxnx(rel.getBxnx());
			bonusVo.setLqnl(rel.getLqnl());
			bonusVo.setBzlq(rel.getBzlq());
			PlanProductBonus bonus=productBonusMapper.getPlanProductBonus(bonusVo);
			if(null!=bonus&&StringUtils.isNotEmpty(bonus.getProfit())){
				BigDecimal count=null;
				if(bonusState==2){
					count=rel.getBf().divide(product.getBonusNum(),2, BigDecimal.ROUND_HALF_UP);
				}else if(bonusState==1){
					count=rel.getBe().divide(product.getBonusNum(),2, BigDecimal.ROUND_HALF_UP);
				}
				list= JSONArray.parseArray(bonus.getProfit(), PlanBonusResult.class);
				int numScale=0;
				boolean grade3sNotNull=false;
				for (PlanBonusResult planBonusResult : list) {
					String unit=planBonusResult.getUnit();
					if(StringUtils.isEmpty(unit)){						
						List<BigDecimal> grade3s = planBonusResult.getGrade3();
						if(null!=grade3s){
							for (int i = 0; i < grade3s.size(); i++) {
								BigDecimal grade3=grade3s.get(i);
								grade3s.set(i, grade3.multiply(count).setScale(numScale, RoundingMode.HALF_UP));
							}
							grade3sNotNull=true;
						}
						List<BigDecimal> highs = planBonusResult.getHigh();
						if(null!=highs){
							for (int i = 0; i < highs.size(); i++) {
								BigDecimal high=highs.get(i);
								highs.set(i, high.multiply(count).setScale(numScale, RoundingMode.HALF_UP));			
							}				
						}
						List<BigDecimal> lows = planBonusResult.getLow();
						if(null!=lows){
							for (int i = 0; i < lows.size(); i++) {
								BigDecimal low=lows.get(i);
								lows.set(i, low.multiply(count).setScale(numScale, RoundingMode.HALF_UP));	
							}				
						}
						List<BigDecimal> middles = planBonusResult.getMiddle();
						if(null!=middles){
							for (int i = 0; i < middles.size(); i++) {
								BigDecimal middle=middles.get(i);
								middles.set(i, middle.multiply(count).setScale(numScale, RoundingMode.HALF_UP));
							}				
						}
					}
				}
				if (null != list) {
					hasBonus=true;
					map.put("profit", list);
					map.put("profitJson", JSONArray.toJSON(list));
					map.put("profitColumnCount", grade3sNotNull ? 4 : 3);
					map.put("profitRowCount", list.size());
				}
			}
		}
		map.put("hasBonus", hasBonus);
	}
	
	/**
	 * 获取分红信息
	 * @param planBook
	 * @param rel
	 * @param product
	 * @param map
	 */
	private void loadBonusInfosOfProducts(PlanBook planBook, PlanBookproRel rel, List<PlanBookproRel> rels,PlanProductMain product,Map<String, Object> map){
		List<PlanBonusResult> list=null;
		boolean hasBonus=false;
		Integer bonusState=product.getBonusState();
		if(null!=bonusState&&bonusState>0){
			PlanProductBonusVo bonusVo=new PlanProductBonusVo();
			bonusVo.setAge(planBook.getAge());
			bonusVo.setPid(planBook.getPid());
			bonusVo.setSex(planBook.getSex());
			bonusVo.setJfnx(rel.getJfnx());
			bonusVo.setBxnx(rel.getBxnx());
			bonusVo.setLqnl(rel.getLqnl());
			bonusVo.setBzlq(rel.getBzlq());
			PlanProductBonus bonus=productBonusMapper.getPlanProductBonus(bonusVo);
			
			BigDecimal zj = BigDecimal.ZERO;
			BigDecimal jbsg = BigDecimal.ZERO;
			BigDecimal yw = BigDecimal.ZERO;
			BigDecimal ggjt = BigDecimal.ZERO;
			HashMap<Integer, BigDecimal> rellMap = new HashMap<Integer, BigDecimal>();
			for (PlanBookproRel rell : rels) {
				rellMap.put(rell.getPid(), rell.getBe());
			}
			zj = rellMap.get(217);
			jbsg = rellMap.get(216);
			yw = jbsg.add(rellMap.get(36));
			ggjt = jbsg.add(rellMap.get(36).multiply(new BigDecimal(2)));
			int temp = 70 - planBook.getAge().intValue();
			if(null!=bonus&&StringUtils.isNotEmpty(bonus.getProfit())){
				BigDecimal count=null;
				if(bonusState==2){
					count=rel.getBf().divide(product.getBonusNum(),2, BigDecimal.ROUND_HALF_UP);
				}else if(bonusState==1){
					count=rel.getBe().divide(product.getBonusNum(),2, BigDecimal.ROUND_HALF_UP);
				}
				list= JSONArray.parseArray(bonus.getProfit(), PlanBonusResult.class);
				int numScale=0;
				boolean grade3sNotNull=false;
				for (PlanBonusResult planBonusResult : list) {
					String unit=planBonusResult.getUnit();
					if(StringUtils.isEmpty(unit)){
						BigDecimal main = BigDecimal.ZERO;
						BigDecimal sub = BigDecimal.ZERO;
						if(StringUtils.equals("重疾总利益:", planBonusResult.getName())){
							sub = new BigDecimal(200000);
							main = zj;
						} else if(StringUtils.equals("疾病身故总利益:", planBonusResult.getName())){
							sub = new BigDecimal(1000000);
							main = jbsg;
						} else if(StringUtils.equals("意外身故总利益:", planBonusResult.getName())){
							sub = new BigDecimal(1200000);
							main = yw;
						} else if(StringUtils.equals("公共交通自驾车:", planBonusResult.getName())){
							sub = new BigDecimal(1400000);
							main = ggjt;
						}
						
						List<BigDecimal> highs = planBonusResult.getHigh();
						if(null!=highs){
							for (int i = 0; i < highs.size(); i++) {
								BigDecimal high=highs.get(i);
								if(temp <= i && (StringUtils.equals("意外身故总利益:", planBonusResult.getName()) || StringUtils.equals("公共交通自驾车:", planBonusResult.getName()))){
									main = jbsg;
									sub = new BigDecimal(1000000);
								} else {
									if(StringUtils.equals("意外身故总利益:", planBonusResult.getName())){
										main = yw;
										sub = new BigDecimal(1200000);
									} else if(StringUtils.equals("公共交通自驾车:", planBonusResult.getName())){
										main = ggjt;
										sub = new BigDecimal(1400000);
									}
								}
								highs.set(i, main.add(high.subtract(sub).multiply(count).setScale(numScale, RoundingMode.HALF_UP)));			
							}				
						}
						List<BigDecimal> lows = planBonusResult.getLow();
						if(null!=lows){
							for (int i = 0; i < lows.size(); i++) {
								BigDecimal low=lows.get(i);
								if(temp <= i && (StringUtils.equals("意外身故总利益:", planBonusResult.getName()) || StringUtils.equals("公共交通自驾车:", planBonusResult.getName()))){
									main = jbsg;
									sub = new BigDecimal(1000000);
								} else {
									if(StringUtils.equals("意外身故总利益:", planBonusResult.getName())){
										main = yw;
										sub = new BigDecimal(1200000);
									} else if(StringUtils.equals("公共交通自驾车:", planBonusResult.getName())){
										main = ggjt;
										sub = new BigDecimal(1400000);
									}
								}
								lows.set(i, main.add(low.subtract(sub).multiply(count).setScale(numScale, RoundingMode.HALF_UP)));	
							}				
						}
						List<BigDecimal> middles = planBonusResult.getMiddle();
						if(null!=middles){
							for (int i = 0; i < middles.size(); i++) {
								BigDecimal middle=middles.get(i);
								if(temp <= i && (StringUtils.equals("意外身故总利益:", planBonusResult.getName()) || StringUtils.equals("公共交通自驾车:", planBonusResult.getName()))){
									main = jbsg;
									sub = new BigDecimal(1000000);
								} else {
									if(StringUtils.equals("意外身故总利益:", planBonusResult.getName())){
										main = yw;
										sub = new BigDecimal(1200000);
									} else if(StringUtils.equals("公共交通自驾车:", planBonusResult.getName())){
										main = ggjt;
										sub = new BigDecimal(1400000);
									}
								}
								middles.set(i, main.add(middle.subtract(sub).multiply(count).setScale(numScale, RoundingMode.HALF_UP)));
							}				
						}
					}
				}
				if (null != list) {
					hasBonus=true;
					map.put("profit", list);
					map.put("profitJson", JSONArray.toJSON(list));
					map.put("profitColumnCount", grade3sNotNull ? 4 : 3);
					map.put("profitRowCount", list.size());
				}
			}
		}
		map.put("hasBonus", hasBonus);
	}
	
	/**
	 * 用户反馈信息
	 * @param map
	 * @param book
	 */
	private void loadCustomerReturnInfos(Map<String, Object> map,PlanBook book){
		PlanBookFeedbacks feedback = new PlanBookFeedbacks();
		feedback.setBookId(book.getFid());
		feedback.setUserId(Long.parseLong(book.getUserId()+""));
		feedback.setRes(1);
		map.put("appearKonw", feedbacksMapper.getCountByPlanBookFeedbacks(feedback) > 0);
		feedback.setRes(2);
		map.put("appearThink", feedbacksMapper.getCountByPlanBookFeedbacks(feedback) > 0);
	}
	/**
	 * 信封封面文字处理
	 * @param map
	 */
	private void loadCoverInfos(Map<String, Object> map,String cs){
		boolean hasCover=StringUtils.isNotEmpty(cs);
		if(hasCover){
			String[] s=cs.split("@");
			map.put("covers", s);				
		}
		map.put("hasCover", hasCover);
	}
	/**
	 * 语音信息
	 * @param map
	 * @param voiceId
	 */
	private void loadVoiceInfos(Map<String, Object> map, Long voiceId) {
		boolean hasVoice = false;
		if (voiceId != null) {
			PlanBookVoice voice = planBookVoiceMapper.selectByPrimaryKey(voiceId);
			if (voice != null) {
				hasVoice = true;
				map.put("voice", voice);
			}
		}
		map.put("hasVoice", hasVoice);
	}
	/***
	 * 名片信息
	 * @param map
	 * @param userId
	 */
	private void loadUserCardInfos(Map<String, Object> map, Integer userId){
		Map<String, String> para = new HashMap<String, String>();
		para.put("fid", userId+"");
		PlanUserCard userCard = userCardService.selectByPrimaryKey(para);
		map.put("userCard", userCard);
		map.put("cardFlag", userCard != null);
	}
	/**
	 * 初始化年龄日期
	 * @param map
	 * @param product
	 */
	private static void loadBirthdayInfos(Map<String, Object> map,PlanProductMain product){
		Date now=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		map.put("beginAgeStr", format.format(DateUtils.addYears(now, -product.getTbnljs())));
		map.put("endAgeStr", format.format(DateUtils.addYears(now, -product.getTbnlks())));
		map.put("currentAgeStr", format.format(now));
	}
}
