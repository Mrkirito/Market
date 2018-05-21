package com.hangjia.bxj.service.account.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.account.RoleMapper;
import com.hangjia.bxj.model.account.RoleDO;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.query.account.RoleQuery;
import com.hangjia.bxj.service.account.IRoleService;
import com.hangjia.bxj.vo.account.RoleDTO;

/**
 * @author yaoy
 * @since 2016-06-20
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper roleDAO;

	private BeanCopier roleDO2DTO = BeanCopier.create(RoleDO.class, RoleDTO.class, false);
	private BeanCopier roleDTO2DO = BeanCopier.create(RoleDTO.class, RoleDO.class, false);

	@Override
	public List<RoleDTO> queryRoleList(RoleQuery query) {
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		List<RoleDO> roleDOList = roleDAO.queryRoleList(query);
		if (null != roleDOList && roleDOList.size() > 0) {
			for (RoleDO roleDO : roleDOList) {
				RoleDTO roleDTO = new RoleDTO();
				roleDO2DTO.copy(roleDO, roleDTO, null);
				roleDTOList.add(roleDTO);
			}
		}
		return roleDTOList;
	}

	@Override
	@MethodLog(remark = "新增/更新角色")
	public void saveRole(RoleDTO roleDTO, String resourceIdList, String funcIdList,
			String userName) {
		RoleDO roleDO = new RoleDO();
		roleDTO2DO.copy(roleDTO, roleDO, null);
		if (null != roleDO.getId()) {
			roleDAO.updateRole(roleDO);
		} else {
			roleDO.setRoleCode(DateUtils.formatSdf8(new Date()));
			roleDO.setParentId(0l);
			roleDAO.addRole(roleDO);
		}

		// 删除资源权限
		roleDAO.deleteRoleResource(roleDO.getId());

		// 删除按钮权限
		roleDAO.deleteRoleFuntion(roleDO.getId());

		// 保存资源权限
		if (StringUtils.isNotBlank(resourceIdList)) {
			String[] resourcearr = StringUtils.split(resourceIdList, ",");
			for (String resourceId : resourcearr) {
				if (StringUtils.isNotBlank(resourceId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleId", roleDO.getId());
					map.put("resourceId", Long.valueOf(resourceId));
					roleDAO.saveRoleResource(map);
				}
			}
		}
		// 保存按钮权限
		if (StringUtils.isNotBlank(funcIdList)) {
			String[] funcarr = StringUtils.split(funcIdList, ",");
			for (String funcId : funcarr) {
				if (StringUtils.isNotBlank(funcId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleId", roleDO.getId());
					map.put("funcId", Long.valueOf(funcId));
					roleDAO.saveRoleFunction(map);
				}
			}
		}

	}

	@Override
	@MethodLog(remark = "删除角色")
	public void deleteRole(List<String> idList, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		map.put("status", status);
		roleDAO.deleteRole(map);
	}

	@Override
	public List<Long> queryResurceIdList(Long roleId) {
		RoleQuery roleQuery = new RoleQuery();
		roleQuery.setRoleId(roleId);
		roleQuery.setStatus(1);
		return roleDAO.queryResurceIdList(roleQuery);
	}

	@Override
	public void saveRight(Long roleId, List<String> resourceIdList) {
		// 删除角色权限 物理删除
		roleDAO.deleteRoleResource(roleId);

		if (null != resourceIdList && resourceIdList.size() > 0) {
			for (String resourceId : resourceIdList) {
				if (StringUtils.isNotBlank(resourceId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleId", roleId);
					map.put("resourceId", Long.valueOf(resourceId));
					roleDAO.saveRoleResource(map);
				}
			}
		}

	}

	@Override
	@MethodLog(remark = "新增角色用户")
	public void saveRoleUser(Long roleId, List<String> userIdList) {
		if (null != userIdList && userIdList.size() > 0) {
			for (String userId : userIdList) {
				if (StringUtils.isNotBlank(userId)) {
					Map<String, Object> existMap = new HashMap<String, Object>();
					existMap.put("roleId", roleId);
					existMap.put("userId", userId);
					int count = roleDAO.existsUserRole(existMap);
					if (count == 0) {
						Map<String, Object> saveMap = new HashMap<String, Object>();
						saveMap.put("roleId", roleId);
						saveMap.put("userId", Long.valueOf(userId));
						roleDAO.saveRoleUser(saveMap);
					}
				}
			}
		}
	}

	@Override
	@MethodLog(remark = "新增角色用户")
	public void saveRoleUser(Long roleId, Long userId) {
		List<String> userIdList = new ArrayList<String>();
		userIdList.add(userId.longValue() + "");
		this.saveRoleUser(roleId, userIdList);
	}

	@Override
	@MethodLog(remark = "删除角色用户")
	public void deleteRoleUser(Long roleId, List<String> userIdList) {
		if (null != userIdList && userIdList.size() > 0) {
			for (String userId : userIdList) {
				if (StringUtils.isNotBlank(userId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleId", roleId);
					map.put("userId", Long.valueOf(userId));
					roleDAO.deleteRoleUser(map);
				}
			}
		}
	}

	@Override
	@MethodLog(remark = "删除用户角色")
	public void deleteUserRole(Long userId) {
		roleDAO.deleteUserRole(userId);
	}

	@Override
	public List<RoleDTO> queryUserRoleTree(Long userId) {
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		List<RoleDO> roleDOList = roleDAO.queryUserRoleTree(userId);
		if (null != roleDOList && roleDOList.size() > 0) {
			for (RoleDO roleDO : roleDOList) {
				RoleDTO roleDTO = new RoleDTO();
				roleDO2DTO.copy(roleDO, roleDTO, null);
				roleDTOList.add(roleDTO);
			}
		}
		return roleDTOList;
	}

	@Override
	public RoleDTO queryRoleById(Long id) {
		RoleDO roleDO = roleDAO.queryRoleById(id);
		if (null != roleDO) {
			RoleDTO roleDTO = new RoleDTO();
			roleDO2DTO.copy(roleDO, roleDTO, null);
			return roleDTO;
		}
		return null;
	}

	@Override
	public List<Long> queryFunctionIdList(Long roleId) {
		return roleDAO.queryFunctionIdList(roleId);
	}

	@Override
	@MethodLog(remark = "新增角色按钮权限")
	public void saveRoleFunction(Long roleId, List<String> functionIdList) {
		// 删除角色权限 物理删除
		roleDAO.deleteRoleFuntion(roleId);

		if (null != functionIdList && functionIdList.size() > 0) {
			for (String functionId : functionIdList) {
				if (StringUtils.isNotBlank(functionId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleId", roleId);
					map.put("funcId", Long.valueOf(functionId));
					roleDAO.saveRoleFunction(map);
				}
			}
		}
	}

	@Override
	public Long queryRoleIdByCode(String roleCode) {
		Long id = null;
		id = roleDAO.queryRoleIdByCode(roleCode);
		return id;
	}

	/**
	 * 保存用户角色数据
	 *
	 * @param userId
	 * @param roleIdList
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "新增角色用户")
	public void saveRoleUser(Long userId, String[] roleIdList) {
		if (null != roleIdList && roleIdList.length > 0 && null != userId) {
			for (String roleId : roleIdList) {
				if (StringUtils.isNotBlank(roleId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleId", Long.valueOf(roleId));
					map.put("userId", userId);
					roleDAO.saveRoleUser(map);
				}
			}
		}

	}

	/**
	 * 查询角色列表
	 *
	 * @param roleIdList
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<RoleDTO> queryRoleListByIds(String[] roleIdList) {
		if (null != roleIdList && roleIdList.length > 0) {
			RoleQuery query = new RoleQuery();
			query.setRoleIdList(roleIdList);
			return queryRoleList(query);
		}
		return null;
	}

	/**
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int queryPageCount(RoleQuery query) {
		return roleDAO.queryPageCount(query);
	}

	/**
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<RoleDTO> queryPageData(RoleQuery query) {
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		List<RoleDO> roleDOList = roleDAO.queryPageData(query);
		if (null != roleDOList && roleDOList.size() > 0) {
			for (RoleDO roleDO : roleDOList) {
				RoleDTO roleDTO = new RoleDTO();
				roleDO2DTO.copy(roleDO, roleDTO, null);
				roleDTOList.add(roleDTO);
			}
		}
		return roleDTOList;
	}

	/**
	 * 查询角色被使用数量
	 *
	 * @param idList
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int queryRoleUsedCount(List<String> idList) {
		return roleDAO.queryRoleUsedCount(idList);
	}
}
