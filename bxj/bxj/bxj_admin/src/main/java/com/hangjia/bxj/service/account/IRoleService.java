package com.hangjia.bxj.service.account;

import java.util.List;

import com.hangjia.bxj.query.account.RoleQuery;
import com.hangjia.bxj.vo.account.RoleDTO;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public interface IRoleService {
    List<RoleDTO> queryUserRoleTree(Long userId);


    List<RoleDTO> queryRoleList(RoleQuery query);


    void saveRole(RoleDTO roleDO, String resourceIdList, String funcIdList, String userName);


    void deleteRole(List<String> idList, int status);


    List<Long> queryResurceIdList(Long roleId);


    void saveRight(Long roleId, List<String> resourceIdList);


    void saveRoleUser(Long roleId, List<String> userIdList);


    void saveRoleUser(Long roleId, Long userId);


    void deleteRoleUser(Long roleId, List<String> userIdList);


    void deleteUserRole(Long userId);


    RoleDTO queryRoleById(Long id);


    List<Long> queryFunctionIdList(Long roleId);


    void saveRoleFunction(Long roleId, List<String> functionIdList);


    Long queryRoleIdByCode(String roleCode);

    /**
     * 保存用户角色数据
     * @param userId
     * @param roleIdList
     * @throws ServiceException
     */
    void saveRoleUser(Long userId, String[] roleIdList);

    /**
     * 查询角色列表
     * @param roleIdList
     * @return
     * @throws ServiceException
     */
    List<RoleDTO> queryRoleListByIds(String[] roleIdList);

    /**
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    int queryPageCount(RoleQuery query);

    /**
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    List<RoleDTO> queryPageData(RoleQuery query);

    /**
     * 查询角色被使用数量
     * @param idList
     * @return
     * @throws ServiceException
     */
    int queryRoleUsedCount(List<String> idList);
}
