package com.hangjia.bxj.service.right;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.query.right.FunctionQuery;
import com.hangjia.bxj.query.right.ResourceQuery;
import com.hangjia.bxj.vo.right.FunctionDTO;
import com.hangjia.bxj.vo.right.ResourceDTO;

public interface IRightService {

	/**
	 * 查询所有有效的权限
	 *
	 * @return @
	 */
	List<ResourceDTO> queryAllResourceList(String[] navCode);

	/**
	 *
	 * @param navCode
	 *            系统导航编码
	 * @param userId
	 * @return @
	 */
	List<ResourceDTO> queryUserResourceList(String[] navCode, Long userId);

	/**
	 * 查询用户权限
	 *
	 * @param userId
	 * @return @
	 */
	List<ResourceDTO> queryUserResourceList(Long userId);

	List<ResourceDTO> queryResourceList(String navCode);

	/**
	 * 查询用户按钮权限
	 *
	 * @param target
	 * @return @
	 */
	List<FunctionDTO> queryResourceFunction(String target);

	/**
	 * 查询用户按钮权限
	 *
	 * @param userId
	 * @return @
	 */
	List<Long> queryUserFunctionId(Long userId);

	/**
	 * 查询分页数据
	 * 
	 * @param query
	 * @return @
	 */
	List<ResourceDTO> queryResourcePageData(ResourceQuery query);

	/**
	 * 查询数量
	 * 
	 * @param query
	 * @return @
	 */
	int queryResourcePageCount(ResourceQuery query);

	/**
	 * 保存
	 * 
	 * @param ResourceVo
	 * @
	 */
	void saveResource(ResourceDTO ResourceVo);

	/**
	 * 删除
	 * 
	 * @param idList
	 * @
	 */
	void deleteResource(List<String> idList);

	List<ResourceDTO> queryResourceList();

	List<ResourceDTO> queryUserResourceList(Long userId, Long roleId);

	/**
	 * 查询按钮权限
	 * 
	 * @param query
	 * @return @
	 */
	int queryFunctionPageCount(FunctionQuery query);

	/**
	 * 查询按钮权限
	 * 
	 * @param query
	 * @return @
	 */
	List<FunctionDTO> queryFunctionPageData(FunctionQuery query);

	/**
	 * 保存按钮权限
	 * 
	 * @param function
	 * @
	 */
	void saveFunction(FunctionDTO function);

	void saveFunction(List<FunctionDTO> functionDTOList);

	/**
	 * 删除按钮权限
	 * 
	 * @param idList
	 * @
	 */
	void deleteFunction(List<String> idList);

	/**
	 * 查询所有有效的权限
	 *
	 * @return @
	 */
	List<ResourceDTO> queryAllResourceList();

	/**
	 * 查询资源权限下的按钮权限
	 * 
	 * @param resourceCode
	 * @return @
	 */
	List<FunctionDTO> queryFunctionList(String resourceCode);

	/**
	 * 查询用户功能权限
	 * 
	 * @param userId
	 * @return @
	 */
	List<FunctionDTO> queryUserFunctionList(Long userId);

	/**
	 * 查询所有有效的按钮权限
	 * 
	 * @return @
	 */
	List<FunctionDTO> queryAllFunctionList();

	/**
	 *
	 * @param resourceId
	 * @return @
	 */
	ResourceDTO queryResourceById(long resourceId);

	Map<Long, Long> queryFuncResourceMap(List<Long> funcIdList);
}
