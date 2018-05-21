package com.hangjia.bxj.dao.account;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.account.UserDO;
import com.hangjia.bxj.query.account.UserQuery;

public interface UserMapper {

	List<UserDO> queryPage(UserQuery query);
	
	int queryPageCount(UserQuery query);
	
	UserDO select(Long userId);
	
	UserDO select(UserQuery query);
	
	int update(UserDO userDO);
	
    void changePasswd(UserDO userDO);


    void changeUserImage(Long userId, String userImage);


    /**
     * 查询leader
     * 
     * @param userName
     * @return
     * @throws DAOException
     */
    List<UserDO> queryLeaderData(String userName);


    /**
     * 保存用户信息
     * 
     * @param userDO
     * @throws DAOException
     */
    void addUser(UserDO userDO);


    /**
     * 修改用户信息
     * 
     * @param userDO
     * @throws DAOException
     */
    void updateUser(UserDO userDO);


    /**
     * 删除用户
     * 
     * @param idList
     * @throws DAOException
     */
    void deleteUser(List<String> idList);


    /**
     * 修改主题
     * 
     * @param userId
     * @param themeName
     * @throws DAOException
     */
    void changeTheme(Long userId, String themeName);


    /**
     *
     * @param userName
     *            用户名
     * @param positionTitle
     *            职位
     * @return
     * @throws DAOException
     */
    List<UserDO> queryUserList(String userName, String positionTitle);


    /**
     * 查询供应商用户的登录名
     * 
     * @param vendorId
     * @return
     * @throws DAOException
     */
    List<String> queryLoginNameListByVendorId(Long vendorId);


    int existsUserByCode(UserDO userDO);


    int existsUserByName(UserDO userDO);


    void saveLoginTime(UserDO userDO);


    void resetPasswd(Map<String, Object> map);


    /**
     * 启用 禁用用户
     * 
     * @param userId
     * @param enable
     * @throws DAOException
     */
    void enableUser(Map<String, Object> map);


    /**
     * 查询用户列表数据
     * 
     * @param userIdList
     * @return
     * @throws DAOException
     */
    Map<Long, UserDO> queryUserMap(List<Long> userIdList);

}