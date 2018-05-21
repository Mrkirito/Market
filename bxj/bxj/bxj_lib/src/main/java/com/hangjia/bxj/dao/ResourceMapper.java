package com.hangjia.bxj.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hangjia.bxj.model.right.FunctionDO;
import com.hangjia.bxj.model.right.ResourceDO;
import com.hangjia.bxj.query.right.FunctionQuery;
import com.hangjia.bxj.query.right.ResourceQuery;

public interface ResourceMapper {
	int deleteByPrimaryKey(Long id);

	int insert(ResourceDO record);

	int insertSelective(ResourceDO record);

	ResourceDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(ResourceDO record);

	int updateByPrimaryKey(ResourceDO record);

	/**
	 * 查询资源权限数据
	 * 
	 * @param query
	 * @return @
	 */
	List<ResourceDO> queryResourcePageData(ResourceQuery query);

	/**
	 * 查询资源权限数量
	 * 
	 * @param query
	 * @return @
	 */
	int queryResourcePageCount(ResourceQuery query);

	/**
	 * 新增资源权限
	 * 
	 * @param Resource
	 * @
	 */
	void addResource(ResourceDO Resource);

	/**
	 * 修改资源权限
	 * 
	 * @param Resource
	 * @
	 */
	void updateResource(ResourceDO Resource);

	/**
	 * 删除资源权限
	 * 
	 * @param idList
	 * @
	 */
	void deleteResource(List<String> idList);

	/**
	 * 查询
	 * 
	 * @return @
	 */
	List<ResourceDO> queryResourceList();

	/**
	 * 查询
	 *
	 * @return @
	 */
	List<ResourceDO> queryResourceList(String navCode);

	/**
	 * 查询用户权限
	 * 
	 * @param userId
	 * @return @
	 */
	List<ResourceDO> queryUserResourceList(Long userId);

	List<ResourceDO> queryUserResourceList2(Map<String, Object> map);

	List<ResourceDO> queryUserRoleResourceList(Map<String, Object> map);

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
	List<FunctionDO> queryFunctionPageData(FunctionQuery query);

	/**
	 * 新增按钮权限
	 * 
	 * @param functionDO
	 * @
	 */
	void addFunction(FunctionDO functionDO);

	/**
	 * 修改按钮权限
	 * 
	 * @param functionDO
	 * @
	 */
	void updateFunction(FunctionDO functionDO);

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
	List<ResourceDO> queryAllResourceList();

	/**
	 * 查询所有有效的权限
	 *
	 * @return @
	 */
	List<ResourceDO> queryAllResourceList(@Param("navCode") String[] navCode);

	/**
	 * 查询用户不受权限控制的菜单
	 * 
	 * @return @
	 */
	List<ResourceDO> queryFreeResourceList();

	/**
	 * 查询用户不受权限控制的菜单
	 *
	 * @return @
	 */
	List<ResourceDO> queryFreeResourceList(@Param("navCode")String[] navCode);

	List<FunctionDO> queryFunctionList(String resourceCode);

	/**
	 * 查询页面的按钮
	 * 
	 * @param target
	 * @return @
	 */
	List<FunctionDO> queryResourceFunction(String target);

	/**
	 * 查询用户按钮权限
	 * 
	 * @param userId
	 * @return @
	 */
	List<Long> queryUserFunctionId(Long userId);

	/**
	 * 查询用户功能权限
	 * 
	 * @param userId
	 * @return @
	 */
	List<FunctionDO> queryUserFunctionList(Long userId);

	/**
	 * 查询所有有效的按钮权限
	 * 
	 * @return
	 * @throws ManagerException
	 */
	List<FunctionDO> queryAllFunctionList();

	/**
	 *
	 * @param resourceId
	 * @return @
	 */
	ResourceDO queryResourceById(long resourceId);

	List<Map<String, BigInteger>> queryFuncResourceMap(List<Long> funcIdList);

}