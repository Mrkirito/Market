package com.hangjia.bxj.service;

import com.baobao.framework.support.utility.StringUtils;
import com.baobao.sso.client.CompanyRespDto;
import com.baobao.sso.client.UserCardReqDto;
import com.baobao.sso.client.UserCardRespDto;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.dao.PlanBookMapper;
import com.hangjia.bxj.dao.PlanBookUserRelMapper;
import com.hangjia.bxj.exception.SystemException;
import com.hangjia.bxj.exception.SystemExceptionEnum;
import com.hangjia.bxj.model.PlanBookUserVo;
import com.hangjia.bxj.vo.PlanProductGs;
import com.hangjia.bxj.vo.PlanUserCard;
import com.hangjia.bxj.util.Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 说明：使用注解service业务逻辑处理类
 * 功能：计划书用户名片处理类
 * @author Tain
 * 2015-10-27
 */
@Service
public class PlanUserCardService {

	//	@Autowired
//	private PlanUserCardMapper planUserCardMapper;
	@Autowired
	private UserCardSupportService userCardSupportService;

	@Autowired
	private PlanBookMapper planBookMapper;

	@Autowired
	private PlanBookUserRelMapper planBookUserRelMapper;

//	@Autowired
//	private PlanProductGsMapper planProductGsMapper;

	@Value("${bxj.upload.root.parent}")
	private String uploadPath;
	/**
	 * 查询用户名片信息
	 * @param para
	 * @return
	 */
	public PlanUserCard selectByPrimaryKey(Map<String, String> para) {
//		return planUserCardMapper.selectByPrimaryKey(Integer.valueOf(para.get("fid")));
		UserCardRespDto userCardRespDto = userCardSupportService.getUserCardByFid(Integer.valueOf(para.get("fid")));
		if (userCardRespDto != null){
			PlanUserCard userCard = new PlanUserCard();
			org.springframework.beans.BeanUtils.copyProperties(userCardRespDto, userCard);
			return userCard;
		}
		return null;
	}


	/**
	 * 添加计划书名片信息
	 * @param para
	 * @return
	 * @throws SystemException
	 */
	public PlanUserCard addPlanUserCard(Map<String, String> para) throws SystemException {
		PlanUserCard planUserCard = new PlanUserCard();
		planUserCard.setFid(Integer.valueOf(para.get("fid")));

		if(StringUtils.isNotBlank(para.get("file"))) planUserCard.setPhoto(para.get("file"));
		else planUserCard.setPhoto("");
		planUserCard.setName(para.get("name"));
		planUserCard.setSex(Integer.valueOf(para.get("sex")));
		planUserCard.setCompany(para.get("company"));
		planUserCard.setCompanyCode(para.get("companyCode"));
		planUserCard.setArea(para.get("area"));
		planUserCard.setAreaCode(para.get("areaCode"));
		planUserCard.setJob(para.get("job"));
		planUserCard.setJobCode(para.get("jobCode"));
		planUserCard.setPhone(para.get("phone"));
		planUserCard.setDepartment(para.get("department"));
		planUserCard.setAddress(para.get("address"));
		Date ctime = new Date();
		planUserCard.setCtime(ctime);
		planUserCard.setUptime(ctime);
		try {
//			planUserCardMapper.insert(planUserCard);
			UserCardReqDto userCardReqDto = new UserCardReqDto();
			org.springframework.beans.BeanUtils.copyProperties(planUserCard, userCardReqDto);
			userCardSupportService.saveUserCardBySelective(userCardReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(SystemExceptionEnum.DB_ERROR_EXCEPTION);
		}
		return planUserCard;
	}

	/**
	 * 更新计划书名片信息,如果当前用户没有创建过名片，则新增，否则更新
	 * @param para
	 * @return
	 * @throws SystemException
	 */
	public PlanUserCard upPlanUserCard(Map<String, String> para) throws SystemException {
		PlanUserCard planUserCard = this.selectByPrimaryKey(para);
		if(planUserCard == null || planUserCard.getFid() == null) return this.addPlanUserCard(para);
		if(StringUtils.isNotBlank(para.get("file"))) planUserCard.setPhoto(para.get("file"));
		planUserCard.setName(para.get("name"));
		planUserCard.setSex(Integer.valueOf(para.get("sex")));
		planUserCard.setCompany(para.get("company"));
		planUserCard.setCompanyCode(para.get("companyCode"));
		planUserCard.setArea(para.get("area"));
		planUserCard.setAreaCode(para.get("areaCode"));
		planUserCard.setJob(para.get("job"));
		planUserCard.setJobCode(para.get("jobCode"));
		planUserCard.setPhone(para.get("phone"));
		planUserCard.setDepartment(para.get("department"));
		planUserCard.setAddress(para.get("address"));
		planUserCard.setUptime(new Date());
		try {
//			planUserCardMapper.updateByPrimaryKey(planUserCard);
			UserCardReqDto userCardReqDto = new UserCardReqDto();
			org.springframework.beans.BeanUtils.copyProperties(planUserCard, userCardReqDto);
			userCardSupportService.saveUserCard(userCardReqDto);
		} catch (Exception e) {
			throw new SystemException(SystemExceptionEnum.DB_ERROR_EXCEPTION);
		}
		return planUserCard;
	}
	public Map<String,Object> upPlanUserCard(PlanUserCard planUserCard){
		Integer fid = planUserCard.getFid();
//		PlanUserCard userCard = planUserCardMapper.selectByPrimaryKey(fid);
		UserCardReqDto userCardReqDto = new UserCardReqDto();
		org.springframework.beans.BeanUtils.copyProperties(planUserCard, userCardReqDto);
		String photo = planUserCard.getPhoto();
		if (StringUtils.isNotBlank(photo)) {
			photo=photo.replaceFirst("data:image/jpeg;base64,", "");
//			String serverPath =  uploadPath + File.separator + "hjb_app" + File.separator + "planusercard";
//			File floder = new File(serverPath);
//			if (!floder.exists()) {
//				floder.mkdirs();
//			}
//			String filename = fid+"_"+System.currentTimeMillis();
//			String imgFilePath = serverPath + File.separator + filename +".jpg";
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] bytes = decoder.decodeBuffer(photo);
				userCardReqDto.setUserPhotoByte(bytes);
//				for (int i = 0; i < bytes.length; ++i) {
//					if (bytes[i] < 0) {
//						bytes[i] += 256;
//					}
//				}
//				planUserCard.setPhoto(File.separator + "hjb_app" + File.separator + "planusercard"+File.separator + filename+".jpg");
//				OutputStream out = new FileOutputStream(imgFilePath);
//				out.write(bytes);
//				out.flush();
//				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int i = 0;
//		Date today=new Date();
//		if (userCard == null) {
//			planUserCard.setCtime(today);
//			i = planUserCardMapper.insertSelective(planUserCard);
//		} else {
//			planUserCard.setUptime(today);
//			i = planUserCardMapper.updateByPrimaryKeySelective(planUserCard);
//		}
		try {
			userCardSupportService.saveUserCardBySelective(userCardReqDto);
			i = 1;
		} catch (Exception ex){
			ex.printStackTrace();
			i = 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", i > 0);
		return map;
	}
	/**
	 * 通过用户ID获取到用户关联的所有计划书
	 * @param para
	 * @return
	 * @throws SystemException
	 */
	public List<PlanBookUserVo> getUserPlanBookList(Map<String, String> para) throws SystemException  {
		List<PlanBookUserVo> planBookListTemp = planBookUserRelMapper.getPlanBooksByUserId(Integer.valueOf(para.get("fid")));

		//转换成符合格式要求的数据
		List<PlanBookUserVo> planBookList = new ArrayList<PlanBookUserVo>();
		Date now = new Date();
		for(PlanBookUserVo vo : planBookListTemp) {
			PlanBookUserVo temp = new PlanBookUserVo();
			try {
				BeanUtils.copyProperties(temp, vo);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			String showTime = Utils.getSecondsBetweenTimes(vo.getCtime(), now);
			temp.setShowTime(showTime);
			planBookList.add(temp);
		}
		return planBookList;
	}

	/**
	 * 删除通过用户关联的计划书ID
	 * @param para
	 * @return
	 * @throws SystemException
	 */
	public int delPlanbook(Map<String, String> para) throws SystemException  {
		try {
//			return planBookUserRelMapper.deleteByPrimaryKeyTag(Integer.valueOf(para.get("relPlanbookId")));
			return planBookUserRelMapper.deletePlanbookByPrimaryKeyTag(Integer.valueOf(para.get("relPlanbookId")));
		} catch (NumberFormatException e) {
			throw new SystemException(SystemExceptionEnum.DB_ERROR_EXCEPTION);
		}
	}

	/**
	 * 获取所以公司列表
	 * @return
	 * @throws SystemException
	 */
	public List<String[]> getPlanbookCompany() throws SystemException  {
		try {
//			 List<PlanProductGs> gsList = planProductGsMapper.selectAll();
//			 List<String[]> rList = new ArrayList<String[]>();
//			 for(PlanProductGs gs : gsList) {
//				 String[] st = new String[2];
//				 st[0] = gs.getFid()+"";
//				 st[1] = gs.getName();
//				 rList.add(st);
//			 }
//			 return rList;
			List<CompanyRespDto> companyRespList = userCardSupportService.getAllCompany();
			if (companyRespList != null && !companyRespList.isEmpty()) {
				List<String[]> rList = new ArrayList<String[]>();
				for (CompanyRespDto companyRespDto : companyRespList) {
					String[] st = new String[2];
					st[0] = companyRespDto.getFid()+"";
					st[1] = companyRespDto.getCompanyName();
					rList.add(st);
				}
				return  rList;
			}
			return null;
		} catch (NumberFormatException e) {
			throw new SystemException(SystemExceptionEnum.DB_ERROR_EXCEPTION);
		}
	}

	/**
	 * 获取所以公司列表
	 * @return
	 * @throws SystemException
	 */
	public List<PlanProductGs> getPlanbookCompanyList() throws SystemException  {
		try {
//			 List<PlanProductGs> gsList = planProductGsMapper.selectAll();
			List<CompanyRespDto> companyRespList = userCardSupportService.getAllCompany();
			List<PlanProductGs> gsList = new ArrayList<PlanProductGs>();
			org.springframework.beans.BeanUtils.copyProperties(companyRespList, gsList);
			return gsList;
		} catch (NumberFormatException e) {
			throw new SystemException(SystemExceptionEnum.DB_ERROR_EXCEPTION);
		}
	}

}
