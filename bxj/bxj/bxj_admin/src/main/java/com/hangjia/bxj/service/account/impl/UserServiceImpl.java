package com.hangjia.bxj.service.account.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.account.UserMapper;
import com.hangjia.bxj.model.account.UserDO;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.account.UserQuery;
import com.hangjia.bxj.service.account.IRoleService;
import com.hangjia.bxj.service.account.IUserService;
import com.hangjia.bxj.vo.account.UserDTO;

/**
 * @author yaoy
 * @since 2016-06-17
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements IUserService {
	private BeanCopier userCopierDTO2DO = BeanCopier.create(UserDTO.class, UserDO.class, false);
	private BeanCopier userCopierDO2DTO = BeanCopier.create(UserDO.class, UserDTO.class, false);

	@Autowired
	private UserMapper userDAO;

	@Autowired
	private IRoleService roleService;
	
	@Override
	public List<UserDTO> queryUserPageData(UserQuery query) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();

		List<UserDO> userDOList = userDAO.queryPage(query);
		if (null != userDOList && userDOList.size() > 0) {
			for (UserDO userDO : userDOList) {
				UserDTO userDTO = new UserDTO();
				userCopierDO2DTO.copy(userDO, userDTO, null);
				userDTOList.add(userDTO);
			}
		}
		return userDTOList;
	}

	@Override
	public int queryUserPageCount(UserQuery query) {
		return userDAO.queryPageCount(query);
	}

	@Override
	public UserDTO queryUserById(Long userId) {
		UserDO user = userDAO.select(userId);
		if (null != user) {
			UserDTO userDTO = new UserDTO();
			userCopierDO2DTO.copy(user, userDTO, null);
			return userDTO;
		}
		return null;
	}

	@Override
	public UserDO queryUserDOById(Long userId) {
		return userDAO.select(userId);
	}

	@Override
	public UserDTO queryUserByCode(String userCode) {
		if (!StringUtils.isBlank(userCode)) {
			UserQuery query = new UserQuery();
			query.setUserCode(userCode);
			UserDO user = userDAO.select(query);
			if (null != user) {
				UserDTO userDTO = new UserDTO();
				userCopierDO2DTO.copy(user, userDTO, null);
				return userDTO;
			}
		}
		return null;
	}

	/**
	 * 查询用户列表
	 * 
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int queryPageCount(UserQuery query) {
		return userDAO.queryPageCount(query);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<UserDTO> queryPageData(UserQuery query) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		List<UserDO> userDOList = userDAO.queryPage(query);
		if (null != userDOList && userDOList.size() > 0) {
			for (UserDO userDO : userDOList) {
				UserDTO userDTO = new UserDTO();
				userCopierDO2DTO.copy(userDO, userDTO, null);
				userDTOList.add(userDTO);
			}
		}
		return userDTOList;
	}

	/**
	 * 保存用户基本信息
	 * 
	 * @param userDTO
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "保存用户基本信息")
	public void updateUser(UserDTO userDTO) {
		UserDO userDO = new UserDO();
		userCopierDTO2DO.copy(userDTO, userDO, null);
		userDAO.update(userDO);
        // 删除帐号历史权限
        roleService.deleteUserRole(userDO.getId());
		if(StringUtils.isNotBlank(userDTO.getRoleIds())){
			String[] roleArr = StringUtils.split(userDTO.getRoleIds(), ",");
			// 设置帐号权限
            roleService.saveRoleUser(userDO.getId(), roleArr);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param userDTO
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "修改密码")
	public void changePasswd(UserDTO userDTO) {
		UserDO userDO = new UserDO();
		userCopierDTO2DO.copy(userDTO, userDO, null);
		userDAO.changePasswd(userDO);
	}

	/**
	 * 修改头像
	 * 
	 * @param userId
	 * @param userImage
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "修改头像")
	public void changeUserImage(Long userId, String userImage) {
		userDAO.changeUserImage(userId, userImage);
	}

	/**
	 * 查询leader
	 * 
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<UserDTO> queryLeaderData(String userName) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		List<UserDO> userDOList = userDAO.queryLeaderData(userName);
		if (null != userDOList && userDOList.size() > 0) {
			for (UserDO userDO : userDOList) {
				UserDTO userDTO = new UserDTO();
				userCopierDO2DTO.copy(userDO, userDTO, null);
				userDTOList.add(userDTO);
			}
		}
		return userDTOList;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param userDO
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "保存用户信息")
	public void saveUser(UserDTO userDTO) {
		UserDO userDO = new UserDO();
		userCopierDTO2DO.copy(userDTO, userDO, null);
		userDAO.addUser(userDO);
		if(StringUtils.isNotBlank(userDTO.getRoleIds())){
			String[] roleArr = StringUtils.split(userDTO.getRoleIds(), ",");
			// 设置帐号权限
            roleService.saveRoleUser(userDO.getId(), roleArr);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param idList
	 * 
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "删除用户")
	public void deleteUser(List<String> idList) {
		userDAO.deleteUser(idList);
	}

	/**
	 * 修改主题
	 * 
	 * @param userId
	 * @param themeName
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "修改主题")
	public void changeTheme(Long userId, String themeName) {
		userDAO.changeTheme(userId, themeName);
	}

	/**
	 * @param userName
	 *            用户名
	 * @param positionTitle
	 *            职位
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<UserDTO> queryUserList(String userName, String positionTitle) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		List<UserDO> userDOList = userDAO.queryUserList(userName, positionTitle);
		if (null != userDOList && userDOList.size() > 0) {
			for (UserDO userDO : userDOList) {
				UserDTO userDTO = new UserDTO();
				userCopierDO2DTO.copy(userDO, userDTO, null);
				userDTOList.add(userDTO);
			}
		}
		return userDTOList;
	}

	/**
	 * 根据用户名查询用户
	 *
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public UserDTO queryUserByName(String userName) {
		if (!StringUtils.isBlank(userName)) {
			UserQuery query = new UserQuery();
			query.setUserName(userName);
			UserDO user = userDAO.select(query);
			if (null != user) {
				UserDTO userDTO = new UserDTO();
				userCopierDO2DTO.copy(user, userDTO, null);
				return userDTO;
			}
		}
		return null;
	}

	@Override
	public boolean existsUserByCode(UserDO userDO) {
		int exists = userDAO.existsUserByCode(userDO);
		if(exists <= 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean existsUserByName(UserDO userDO) {
		int exists = userDAO.existsUserByName(userDO);
		if(exists <= 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	@MethodLog(remark = "保存登录时间")
	public void saveLoginTime(UserDTO userDTO) {
		if (null != userDTO) {
			UserDO userDO = new UserDO();
			userCopierDTO2DO.copy(userDTO, userDO, null);
			userDAO.saveLoginTime(userDO);
		}
	}

	@Override
	@MethodLog(remark = "重置密码")
	public void resetPasswd(Long id, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("passwd", passwd);
		userDAO.resetPasswd(map);
	}

	/**
	 * 启用 禁用 用户
	 *
	 * @param userId
	 * @param enable
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "启用 禁用 用户")
	public void enableUser(Long id, boolean enable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("enable", enable ? 1 : 0);
		userDAO.enableUser(map);
	}

	@Override
	public Map<Long, UserDTO> queryUserMap(List<Long> userIdList) {
		Map<Long, UserDTO> userDTOMap = new HashMap<Long, UserDTO>();
		if (null != userIdList && userIdList.size() > 0) {
			Map<Long, UserDO> userMap = userDAO.queryUserMap(userIdList);
			if (null != userMap && userMap.size() > 0) {
				for (Map.Entry<Long, UserDO> entry : userMap.entrySet()) {
					if (null != entry.getValue()) {
						UserDTO userDTO = new UserDTO();
						userCopierDO2DTO.copy(entry.getValue(), userDTO, null);
						userDTOMap.put(entry.getKey(), userDTO);
					}
				}
			}
		}

		return userDTOMap;
	}

}
