package com.hangjia.invite.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.dao.InvitationCopyDao;
import com.hangjia.bxj.dao.InvitationDao;
import com.hangjia.bxj.dao.SysMessageMapper;
import com.hangjia.bxj.model.Invitation;
import com.hangjia.bxj.model.InvitationCopy;
import com.hangjia.bxj.model.InvitationPreview;
import com.hangjia.bxj.model.SysMessage;
import com.hangjia.bxj.util.Constants;
import com.hangjia.bxj.util.ImageUtils;
import com.hangjia.bxj.vo.Pagination;
import com.hangjia.invite.service.InviteService;

/**
 * 邀请函 实现类
 * 
 * @ClassName: InviteServiceImpl
 * @Description:
 * @author: he-Yi
 * @date: 2016年4月22日 上午10:19:55
 */
@Service
public class InviteServiceImpl implements InviteService {
	private static Logger log = LoggerFactory.getLogger(InviteServiceImpl.class);
	@Autowired
	private InvitationCopyDao invitationCopyDao;
	@Autowired
	private InvitationDao invitationDao;
	@Autowired
	private SysMessageMapper sysMessageMapper;

	@Value("${bxj.upload.root.parent}")
	private String uploadPath;
	@Value("${static_path}")
	private String staticPath;
	@Value("${bxj_path}")
	private String bxjPath;

	@Transactional(rollbackFor = Exception.class)
	public Invitation save(Invitation invitation) {
		try {
			// 称呼
			invitation.setGuestName(subStringStr(invitation.getGuestName(), 10));
			// 活动长度20
			invitation.setName(subStringStr(invitation.getName(), 20));
			// 人 单位
			invitation.setContactName(subStringStr(invitation.getContactName(), 16));
			// 正文
			invitation.setText(subStringStr(invitation.getText(), 140));
			// 楼层
			invitation.setFloorRoom(subStringStr(invitation.getFloorRoom(), 20));
			// 保存邀请信息
			invitation.setCreateAt(new Date());
			invitation.setModifyAt(new Date());
			String imgUrl = saveImg(invitation.getUserId(), invitation.getMapImg());
			invitation.setMapImgUrl(imgUrl);
			invitationDao.save(invitation); // 保存 邀请函
			InvitationCopy invitationCopy = new InvitationCopy();
			BeanUtils.copyProperties(invitation, invitationCopy);
			// 设置图片全路径返回
			if (imgUrl != null)
				invitation.setMapImgUrl(staticPath + imgUrl);
			// 外键 邀请函主信息id
			invitationCopy.setCopyid(invitation.getId().intValue());
			invitationCopyDao.save(invitationCopy); // 保存邀请函 复制
			return invitation;
		} catch (BeansException e) {
			e.printStackTrace();
			log.error("保存邀请函异常!" + e.getMessage());
			return invitation;
		}
	}

	@Override
	public int isExist(Invitation invitation) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (invitation.getId() != null) { // id不为空的时候
			// map.put("id", invitation.getId().intValue());
			map.put("userId", invitation.getUserId());
			map.put("name", invitation.getName());
		} else { // 查询是否重复邀请函名称
			map.put("userId", invitation.getUserId());
			map.put("name", invitation.getName());
		}
		int count = invitationDao.isExist(map);
		return count; // 用户 邀请名称重复
	}

	@Transactional(rollbackFor = Exception.class)
	public int update(Invitation invitation) {
		// 称呼
		invitation.setGuestName(subStringStr(invitation.getGuestName(), 10));
		// 活动长度20
		invitation.setName(subStringStr(invitation.getName(), 20));
		// 人 单位
		invitation.setContactName(subStringStr(invitation.getContactName(), 16));
		// 正文
		invitation.setText(subStringStr(invitation.getText(), 140));
		// 楼层
		invitation.setFloorRoom(subStringStr(invitation.getFloorRoom(), 20));
		int num = 0;
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("id", invitation.getId().intValue());
			// map.put("userId", invitation.getUserId());
			// map.put("name", invitation.getName());
			Invitation oldInvitation = invitationDao.selectOne(queryMap);
			if (invitation.getMapImg() != null && StringUtils.isNotEmpty(invitation.getAddress())
					&& StringUtils.isNotEmpty(oldInvitation.getAddress())
					&& invitation.getAddress().equals(oldInvitation.getAddress())) {
				invitation.setMapImgUrl(oldInvitation.getMapImgUrl());
			} else {
				String imgUrl = saveImg(invitation.getUserId(), invitation.getMapImg());
				if (imgUrl != null) {
					invitation.setMapImgUrl(imgUrl);
				} else {
					invitation.setMapImgUrl(oldInvitation.getMapImgUrl());
				}
			}
			num = invitationDao.update(invitation); // 修改邀请函
			if (num > 0) {
				InvitationCopy invitationCopy = new InvitationCopy();
				BeanUtils.copyProperties(invitation, invitationCopy);
				// 外键 邀请函主信息id
				invitationCopy.setCopyid(invitation.getId().intValue());
				invitationCopyDao.save(invitationCopy); // 保存邀请函 复制
			}
		} catch (BeansException e) {
			e.printStackTrace();
			log.error("邀请函修改失败!" + invitation + " id:" + invitation.getId());
		}
		return num;
	}

	@Override
	public Pagination<Invitation> listpage(Integer userId, Integer modelType, int index, int pageSize, String descCol) {
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("userId", userId);
		map.put("start", start);
		map.put("end", pageSize);
		if (descCol.equals("id")) { // id 排序
			map.put("descId", descCol);
		} else { // 修改时间排序
			map.put("descModify", descCol);
		}
		if (modelType != null)
			map.put("modelType", modelType);
		if (modelType != null && modelType == 0) { // 只查询模板
			map.put("userId", null);
		}
		// 查询 获取总条数
		int total = listcount(userId, modelType);
		List<Invitation> list = invitationDao.listpage(map);
		List<Invitation> listset = new ArrayList<Invitation>();
		for (Invitation invitation : list) {
			if (invitation.getMapImgUrl() != null) {
				// 设置图片全路径
				invitation.setMapImgUrl(staticPath + invitation.getMapImgUrl());
			}
			// 模板类型如果为空
			if (invitation.getModelType() == null)
				invitation.setModelType(1);
			// 图片路径
			getShareImgUrl(invitation);
			listset.add(invitation);
		}
		return new Pagination<Invitation>(total, listset);
	}

	@Override
	public int listcount(Integer userId, Integer modelType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId); // 有用户id则查询已制作邀请函
		map.put("modelType", modelType);
		if (modelType != null && modelType == 0) { // 只查询模板
			map.put("userId", null);
		}
		return invitationDao.listcount(map);
	}

	@Override
	public InvitationCopy getInvitation(Invitation invitation) {
		InvitationCopy invitationCopy = new InvitationCopy();
		BeanUtils.copyProperties(invitation, invitationCopy);
		// 设置 邀请函 主信息id
		invitationCopy.setCopyid(invitation.getId().intValue());
		// 获取从表 id 根据主表相关信息
		InvitationCopy invitationCopyget = invitationCopyDao.getInviteCopy(invitationCopy);
		//// 根据 从表 主键 获取历史分享邀请 记录
		Map<String, Object> map = new HashMap<String, Object>();
		if (invitationCopyget == null)
			return null;
		map.put("fid", invitationCopyget.getFid().intValue()); // 从表主键
		InvitationCopy invitationReturn = invitationCopyDao.selInviteShareByID(map);
		// 分享图片路径
		getCopyShareImgUrl(invitationReturn);
		invitationReturn.setShareUrl(
				bxjPath + "/invitePage/toInvitePreviewShare.page?inviteId=" + invitationReturn.getFid() + "&type=2");
		return invitationReturn;
	}

	@Override
	public InvitationCopy selInviteShareByID(Integer fid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fid", fid);
		// 获取从表 id
		InvitationCopy invitationCopyget = invitationCopyDao.selInviteShareByID(map);
		getCopyShareImgUrl(invitationCopyget);
		return invitationCopyget;

	}

	private String saveImg(Integer userId, MultipartFile img) {
		if (img != null) {
			String serverPath = uploadPath + File.separator + "hjb_app" + File.separator + "mapimages";
			File floder = new File(serverPath);
			if (!floder.exists()) {
				floder.mkdirs();
			}
			String filename = userId + "_" + System.currentTimeMillis();
			String imgFilePath = serverPath + File.separator + filename + ".jpg";
			try {
				BufferedImage image = ImageUtils.readImage(img.getInputStream());
				ImageUtils.writeImage(image, new File(imgFilePath));
				if ("Windows 7".equals(System.getProperty("os.name"))) {
					return "/hjb_app/mapimages/" + filename + ".jpg";
				}
				return File.separator + "hjb_app" + File.separator + "mapimages" + File.separator + filename + ".jpg";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int deleteInvite(Invitation invitation) {
		// TODO Auto-generated method stub
		return invitationDao.deleteInvite(invitation);
	}

	@Override
	public int updateInviteCount(InvitationCopy invitationCopy) {
		if (invitationCopy == null || invitationCopy.getFid() == null) {
			log.error("更新邀请函系统消息异常,分享的邀请函fid为空");
			return 0;
		}
		// 分享邀请函
		InvitationCopy oldInvitationCopy = invitationCopyDao.getInviteCopyOne(invitationCopy);
		try {
			int num = 0; // 返回结果
			int browseNum = 0;
			if (oldInvitationCopy != null) {
				// 更新 浏览时间 次数
				oldInvitationCopy.setBrowseTime(new Date());
				browseNum = oldInvitationCopy.getBrowseNum() == null ? 1 : oldInvitationCopy.getBrowseNum() + 1;
				oldInvitationCopy.setBrowseNum(browseNum);
				
				// 系统消息 //新增
				SysMessage sysmsg = new SysMessage();
				sysmsg.setMsg("您发送给" + oldInvitationCopy.getGuestName() + "的【" + oldInvitationCopy.getName() + "】邀请函被查阅"+browseNum+"次");
				sysmsg.setUserId(oldInvitationCopy.getUserId().longValue());
				sysmsg.setMsgName(oldInvitationCopy.getName()); // 活动名称
				sysmsg.setMsgType(6); // 邀请函
				sysmsg.setIsRead(0); // 未读
				sysmsg.setCreateTime(new Date());
				sysmsg.setSendId(0L); //无意义 发送id
				sysMessageMapper.insertSelective(sysmsg);
				// 分享的邀请函 更新浏览次数
				num = invitationCopyDao.updateInviteCopyCount(oldInvitationCopy);
			}
			return num;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 超过长度 截取规定长度
	 * 
	 * @param inpstr
	 * @param sublenth
	 * @return
	 */
	private String subStringStr(String inpstr, Integer sublenth) {
		if (inpstr != null && inpstr.length() > sublenth) {
			inpstr = inpstr.substring(0, sublenth); // 超过规定长度截长度
		}
		return inpstr;
	}

	/**
	 * 保存预览邀请函
	 * 
	 * @param invitation
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public InvitationPreview savePreview(InvitationPreview invitation) {
		try {
			// 称呼
			invitation.setGuestName(subStringStr(invitation.getGuestName(), 10));
			// 活动长度20
			invitation.setName(subStringStr(invitation.getName(), 20));
			// 人 单位
			invitation.setContactName(subStringStr(invitation.getContactName(), 16));
			// 正文
			invitation.setText(subStringStr(invitation.getText(), 140));
			// 楼层
			invitation.setFloorRoom(subStringStr(invitation.getFloorRoom(), 20));
			// 保存邀请信息
			invitation.setCreateAt(new Date());
			invitation.setModifyAt(new Date());
			String imgUrl = saveImg(invitation.getUserId(), invitation.getMapImg());
			invitation.setMapImgUrl(imgUrl);
			if (null != invitation.getInviteId()) {
				InvitationPreview oldPreview = invitationDao.queryPreviewByInviteId(invitation.getInviteId());
				if (null != oldPreview) {
					invitation.setId(oldPreview.getId());
					invitationDao.updatePreview(invitation);
				} else {
					invitationDao.savePreview(invitation); // 保存 邀请函
				}
			} else {
				invitationDao.savePreview(invitation); // 保存 邀请函
			}
			// 设置图片全路径返回
			if (imgUrl != null)
				invitation.setMapImgUrl(staticPath + imgUrl);
			// 外键 邀请函主信息id
			return invitation;
		} catch (BeansException e) {
			e.printStackTrace();
			log.error("保存邀请函异常!" + e.getMessage());
			return invitation;
		}
	}

	/**
	 * 查询预览邀请函
	 * 
	 * @param invitation
	 * @return
	 */
	@Override
	public InvitationPreview queryPreviewById(Long id) {
		InvitationPreview invitationPreview = new InvitationPreview();
		try {
			invitationPreview = invitationDao.queryPreviewById(id);
		} catch (Exception e) {
			log.error("查询预览邀请函异常!" + e.getMessage());
			log.error("查询预览邀请函异常!,查询id:" + id);
		}
		return invitationPreview;
	}

	/**
	 * 获取邀请函
	 * 
	 * @param invitation
	 * @return
	 */
	@Override
	public Invitation queryInviteById(Long id) {
		Invitation invitation = new Invitation();
		invitation = invitationDao.get(id);
		if (null != invitation.getId()) {
			getShareImgUrl(invitation);
		}
		return invitation;
	}

	/**
	 * 获取图片缩略图
	 * 
	 * @param invitation
	 * @return
	 */
	private void getShareImgUrl(Invitation invitation) {
		String imgStr = getImgStr(invitation.getModelType());
		invitation.setShareImgUrl(imgStr);
	}

	/**
	 * 获取图片缩略图
	 * 
	 * @param invitation
	 * @return
	 */
	private void getCopyShareImgUrl(InvitationCopy invitation) {
		String imgStr = getImgStr(invitation.getModelType());
		invitation.setShareImgUrl(imgStr);
	}

	/**
	 * 获取图片缩略图
	 * 
	 * @param invitation
	 * @return
	 */
	private String getImgStr(Integer modeType) {
		String imgStr = "";
		if (modeType == 1) {
			imgStr = staticPath + "/bxj_three/static/invitation/qinzi.png";
		} else if (modeType == 2) {
			imgStr = staticPath + "/bxj_three/static/invitation/shangwu.png";
		} else if (modeType == 3) {
			imgStr = staticPath + "/bxj_three/static/invitation/zhongguofeng.png";
		} else if (modeType == 4) {
			imgStr = staticPath + "/bxj_three/static/invitation/nvxing.png";
		} else if (modeType == 5) { // 5.经典
			imgStr = staticPath + "/bxj_three/static/invitation/jingdian.png";
		} else if (modeType == 6) { // 6.科技
			imgStr = staticPath + "/bxj_three/static/invitation/keji.png";
		} else if (modeType == 7) { // 7.酒会
			imgStr = staticPath + "/bxj_three/static/invitation/jiuhui.png";
		} else if (modeType >= 8) {
			imgStr = staticPath + "/bxj_three/static/invitation/" + modeType + ".png";
		}
		return imgStr;
	}
	
}
