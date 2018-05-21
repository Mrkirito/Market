package com.hangjia.bxj.dao.account;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.account.RoleDO;
import com.hangjia.bxj.query.account.RoleQuery;


/**
 * @author yaoy
 * @since 2016-06-21
 */
public interface RoleMapper {

    List<RoleDO> queryRoleList(RoleQuery query);


    void addRole(RoleDO roleDO);


    void updateRole(RoleDO roleDO);


    void deleteRole(Map<String, Object> map);


    List<Long> queryResurceIdList(RoleQuery roleQuery);


    void deleteRoleResource(Long roleId);


    void saveRoleResource(Map<String, Object> map);


    void saveRoleUser(Map<String, Object> map);


    int existsUserRole(Map<String, Object> map);


    void deleteRoleUser(Map<String, Object> map);


    List<RoleDO> queryUserRoleTree(Long userId);


    RoleDO queryRoleById(Long id);


    List<Long> queryFunctionIdList(Long roleId);


    void deleteUserRole(Long userId);


    void deleteRoleFuntion(Long roleId);


    void saveRoleFunction(Map<String, Object> map);


    Long queryRoleIdByCode(String roleCode);


    /**
     * 查询角色小区
     * 
     * @param roleId
     * @return
     * @throws DAOException
     */
    List<Long> queryRoleBlockList(Long roleId);


    /**
     * 删除角色小所有小区
     * 
     * @param roleId
     * @throws DAOException
     */
    void deleteRoleBlock(Long roleId);


    /**
     * 保存角色小区
     * 
     * @param roleId
     * @param blockId
     * @throws DAOException
     */
    void saveRoleBlock(Long roleId, Long blockId);


    /**
     * 查询用户角色小区
     * 
     * @param userId
     * @return
     * @throws DAOException
     */
    List<Long> queryUserRoleBlockList(Long userId);

    /**
     *
     * @param query
     * @return
     * @throws DAOException
     */
    int queryPageCount(RoleQuery query);

    /**
     *
     * @param query
     * @return
     * @throws DAOException
     */
    List<RoleDO> queryPageData(RoleQuery query);

    /**
     * 查询角色被使用数量
     * @param idList
     * @return
     * @throws DAOException
     */
    int queryRoleUsedCount(List<String> idList);
}
