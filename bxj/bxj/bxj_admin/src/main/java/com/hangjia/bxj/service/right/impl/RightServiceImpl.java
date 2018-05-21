package com.hangjia.bxj.service.right.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.ResourceMapper;
import com.hangjia.bxj.model.right.FunctionDO;
import com.hangjia.bxj.model.right.ResourceDO;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.right.FunctionQuery;
import com.hangjia.bxj.query.right.ResourceQuery;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.right.FunctionDTO;
import com.hangjia.bxj.vo.right.ResourceDTO;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RightServiceImpl implements IRightService {

	private final BeanCopier resourceToVo = BeanCopier.create(ResourceDO.class, ResourceDTO.class, false);
	private final BeanCopier resourceToDo = BeanCopier.create(ResourceDTO.class, ResourceDO.class, false);
	private final BeanCopier funcDO2DTO = BeanCopier.create(FunctionDO.class, FunctionDTO.class, false);
	private final BeanCopier funcDTO2DO = BeanCopier.create(FunctionDTO.class, FunctionDO.class, false);

	@Autowired
	private ResourceMapper resourceMapper;

	/**
	 * 查询所有符合条件的菜单列表个数
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public int queryResourcePageCount(ResourceQuery query) {
		return resourceMapper.queryResourcePageCount(query);
	}

	/**
	 * 查询所有符合条件的菜单列表
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public List<ResourceDTO> queryResourcePageData(ResourceQuery query) {
		List<ResourceDTO> resourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> resourceList = resourceMapper.queryResourcePageData(query);
		if (null != resourceList && resourceList.size() > 0) {
			for (ResourceDO resource : resourceList) {
				ResourceDTO resourceVo = new ResourceDTO();
				resourceToVo.copy(resource, resourceVo, null);
				resourceVoList.add(resourceVo);
			}
		}
		return resourceVoList;
	}

	/**
	 * 保存
	 * 
	 * @param ResourceVo
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "保存资源文件")
	public void saveResource(ResourceDTO ResourceVo) {
		ResourceDO Resource = new ResourceDO();
		resourceToDo.copy(ResourceVo, Resource, null);
		if (null != Resource.getId()) {
			resourceMapper.updateResource(Resource);
		} else {
			resourceMapper.addResource(Resource);
		}
	}

	/**
	 * 删除
	 * 
	 * @param idList
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "删除资源文件")
	public void deleteResource(List<String> idList) {
		resourceMapper.deleteResource(idList);
	}

	@Override
	public List<ResourceDTO> queryResourceList() {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> ResourceList = resourceMapper.queryResourceList();
		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		return ResourceVoList;
	}

	@Override
	public List<ResourceDTO> queryResourceList(String navCode) {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> ResourceList = resourceMapper.queryResourceList(navCode);
		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		return ResourceVoList;
	}

	@Override
	public List<ResourceDTO> queryUserResourceList(Long userId) {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> ResourceList = resourceMapper.queryUserResourceList(userId);
		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		ResourceVoList.addAll(this.queryUserFreeResource());
		return ResourceVoList;
	}

	@Override
	public List<ResourceDTO> queryUserResourceList(String navCode[], Long userId) {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("navCode", navCode);
		map.put("userId", userId);
		List<ResourceDO> ResourceList = resourceMapper.queryUserResourceList2(map);
		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		ResourceVoList.addAll(this.queryUserFreeResource(navCode));
		return ResourceVoList;
	}

	@Override
	public List<ResourceDTO> queryUserResourceList(Long userId, Long roleId) {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("userId", userId);
		List<ResourceDO> ResourceList = resourceMapper.queryUserRoleResourceList(map);

		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		ResourceVoList.addAll(this.queryUserFreeResource());
		return ResourceVoList;
	}

	private List<ResourceDTO> queryUserFreeResource() {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> freeResourceList = resourceMapper.queryFreeResourceList();
		if (null != freeResourceList && freeResourceList.size() > 0) {
			for (ResourceDO Resource : freeResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		return ResourceVoList;
	}

	private List<ResourceDTO> queryUserFreeResource(String[] navCode) {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> freeResourceList = resourceMapper.queryFreeResourceList(navCode);
		if (null != freeResourceList && freeResourceList.size() > 0) {
			for (ResourceDO Resource : freeResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		return ResourceVoList;
	}

	/**
	 * 查询按钮权限
	 * 
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int queryFunctionPageCount(FunctionQuery query) {
		return resourceMapper.queryFunctionPageCount(query);
	}

	/**
	 * 查询按钮权限
	 * 
	 * @param query
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<FunctionDTO> queryFunctionPageData(FunctionQuery query) {
		List<FunctionDTO> functionDTOList = new ArrayList<FunctionDTO>();
		List<FunctionDO> functionDOList = resourceMapper.queryFunctionPageData(query);
		if (null != functionDOList && functionDOList.size() > 0) {
			for (FunctionDO functionDO : functionDOList) {
				FunctionDTO functionDTO = new FunctionDTO();
				funcDO2DTO.copy(functionDO, functionDTO, null);
				functionDTOList.add(functionDTO);
			}
		}
		return functionDTOList;
	}

	/**
	 * 保存按钮权限
	 * 
	 * @param function
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "保存按钮权限")
	public void saveFunction(FunctionDTO function) {
		FunctionDO functionDO = new FunctionDO();
		funcDTO2DO.copy(function, functionDO, null);
		if (null != function.getId()) {
			resourceMapper.updateFunction(functionDO);
		} else {
			resourceMapper.addFunction(functionDO);
		}
	}

	@Override
	@MethodLog(remark = "保存按钮权限")
	public void saveFunction(final List<FunctionDTO> functionDTOList) {
		if (null != functionDTOList && functionDTOList.size() > 0) {
			for (FunctionDTO functionDTO : functionDTOList) {
				FunctionDO functionDO = new FunctionDO();
				funcDTO2DO.copy(functionDTO, functionDO, null);
				resourceMapper.addFunction(functionDO);
			}
		}
	}

	/**
	 * 删除按钮权限
	 * 
	 * @param idList
	 * @throws ServiceException
	 */
	@Override
	@MethodLog(remark = "删除按钮权限")
	public void deleteFunction(List<String> idList) {
		resourceMapper.deleteFunction(idList);
	}

	/**
	 * 查询所有有效的权限
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<ResourceDTO> queryAllResourceList() {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> ResourceList = resourceMapper.queryAllResourceList();
		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		return ResourceVoList;
	}

	/**
	 * 查询所有有效的权限
	 *
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<ResourceDTO> queryAllResourceList(String[] navCode) {
		List<ResourceDTO> ResourceVoList = new ArrayList<ResourceDTO>();
		List<ResourceDO> ResourceList = resourceMapper.queryAllResourceList(navCode);
		if (null != ResourceList && ResourceList.size() > 0) {
			for (ResourceDO Resource : ResourceList) {
				ResourceDTO ResourceVo = new ResourceDTO();
				resourceToVo.copy(Resource, ResourceVo, null);
				ResourceVoList.add(ResourceVo);
			}
		}
		return ResourceVoList;
	}

	/**
	 * 查询资源权限下的按钮权限
	 * 
	 * @param resourceCode
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<FunctionDTO> queryFunctionList(String resourceCode) {
		List<FunctionDTO> functionDTOList = new ArrayList<FunctionDTO>();
		List<FunctionDO> functionDOList = resourceMapper.queryFunctionList(resourceCode);
		if (null != functionDOList && functionDOList.size() > 0) {
			for (FunctionDO functionDO : functionDOList) {
				FunctionDTO functionDTO = new FunctionDTO();
				funcDO2DTO.copy(functionDO, functionDTO, null);
				functionDTOList.add(functionDTO);
			}
		}
		return functionDTOList;
	}

	/**
	 * 查询用户按钮权限
	 * 
	 * @param target
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<FunctionDTO> queryResourceFunction(String target) {
		List<FunctionDTO> functionDTOList = new ArrayList<FunctionDTO>();
		List<FunctionDO> functionDOList = resourceMapper.queryResourceFunction(target);
		if (null != functionDOList && functionDOList.size() > 0) {
			for (FunctionDO functionDO : functionDOList) {
				FunctionDTO functionDTO = new FunctionDTO();
				funcDO2DTO.copy(functionDO, functionDTO, null);
				functionDTOList.add(functionDTO);
			}
		}
		return functionDTOList;
	}

	/**
	 * 查询用户按钮权限
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Long> queryUserFunctionId(Long userId) {
		return resourceMapper.queryUserFunctionId(userId);
	}

	/**
	 * 查询用户功能权限
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<FunctionDTO> queryUserFunctionList(Long userId) {
		List<FunctionDTO> functionDTOList = new ArrayList<FunctionDTO>();
		List<FunctionDO> functionDOList = resourceMapper.queryUserFunctionList(userId);
		if (null != functionDOList && functionDOList.size() > 0) {
			for (FunctionDO functionDO : functionDOList) {
				FunctionDTO functionDTO = new FunctionDTO();
				funcDO2DTO.copy(functionDO, functionDTO, null);
				functionDTOList.add(functionDTO);
			}
		}
		return functionDTOList;
	}

	/**
	 * 查询所有有效的按钮权限
	 *
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<FunctionDTO> queryAllFunctionList() {
		List<FunctionDTO> functionDTOList = new ArrayList<FunctionDTO>();
		List<FunctionDO> functionDOList = resourceMapper.queryAllFunctionList();
		if (null != functionDOList && functionDOList.size() > 0) {
			for (FunctionDO functionDO : functionDOList) {
				FunctionDTO functionDTO = new FunctionDTO();
				funcDO2DTO.copy(functionDO, functionDTO, null);
				functionDTOList.add(functionDTO);
			}
		}
		return functionDTOList;
	}

	/**
	 * @param resourceId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ResourceDTO queryResourceById(long resourceId) {
		ResourceDO Resource = resourceMapper.queryResourceById(resourceId);
		if (null != Resource) {
			ResourceDTO ResourceVo = new ResourceDTO();
			resourceToVo.copy(Resource, ResourceVo, null);
			return ResourceVo;
		}
		return null;
	}

	@Override
	public Map<Long, Long> queryFuncResourceMap(List<Long> funcIdList) {
		Map<Long, Long> funcResourceMap = new HashMap<Long, Long>();
		if (null != funcIdList && funcIdList.size() > 0) {
			List<Map<String, BigInteger>> funcResourceList = resourceMapper.queryFuncResourceMap(funcIdList);
			if (null != funcResourceList && funcResourceList.size() > 0) {
				for (Map<String, BigInteger> funcResource : funcResourceList) {
					BigInteger funcId = funcResource.get("funcId");
					BigInteger resourceId = funcResource.get("resourceId");
					if (null != funcId && null != resourceId) {
						funcResourceMap.put(funcId.longValue(), resourceId.longValue());
					}
				}
			}
		}
		return funcResourceMap;
	}

}
