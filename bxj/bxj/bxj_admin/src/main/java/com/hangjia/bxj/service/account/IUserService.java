package com.hangjia.bxj.service.account;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.account.UserDO;
import com.hangjia.bxj.query.account.UserQuery;
import com.hangjia.bxj.vo.account.UserDTO;


/**
 * @author yaoy
 * @since 2016-06-17
 */
public interface IUserService {

    /**
     * 根据用户编号 查询用户
     * @param userCode
     * @return
     * @throws ServiceException
     */
    UserDTO queryUserByCode(String userCode);

    Map<Long,UserDTO> queryUserMap(List<Long> userIdList);

    /**
     * 查询用户分页数据
     * @param query
     * @return
     * @throws ServiceException
     */
    List<UserDTO> queryUserPageData(UserQuery query);

    /**
     * 查询用户总数
     * @param query
     * @return
     * @throws ServiceException
     */
    int queryUserPageCount(UserQuery query);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     * @throws ServiceException
     */
    UserDTO queryUserById(Long userId);

    UserDO queryUserDOById(Long userId);


    /**
     * 查询用户列表
     * @param query
     * @return
     * @throws ServiceException
     */
    int queryPageCount(UserQuery query);

    /**
     * 查询用户列表
     * @param query
     * @return
     * @throws ServiceException
     */
    List<UserDTO> queryPageData(UserQuery query);

    /**
     * 保存用户基本信息
     * @param UserDTO
     * @throws ServiceException
     */
    void updateUser(UserDTO userVo);

    /**
     * 修改密码
     * @param UserDTO
     * @throws ServiceException
     */
    void changePasswd(UserDTO userVo);

    /**
     * 修改头像
     * @param userId
     * @param userImage
     * @throws ServiceException
     */
    void changeUserImage(Long userId, String userImage);

    /**
     * 查询leader
     * @param userName
     * @return
     * @throws ServiceException
     */
    List<UserDTO> queryLeaderData(String userName);

    /**
     * 保存用户信息
     * @param User
     * @throws ServiceException
     */
    void saveUser(UserDTO userDTO);

    /**
     * 删除用户
     * @param idList
     * @throws ServiceException
     */
    void deleteUser(List<String> idList);

    /**
     * 修改主题
     * @param userId
     * @param themeName
     * @throws ServiceException
     */
    void changeTheme(Long userId, String themeName);

    /**
     *
     * @param userName 用户名
     * @param positionTitle 职位
     * @return
     * @throws ServiceException
     */
    List<UserDTO> queryUserList(String userName, String positionTitle);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     * @throws ServiceException
     */
    UserDTO queryUserByName(String userName);

    boolean existsUserByCode(UserDO user);

    boolean existsUserByName(UserDO user);

    void saveLoginTime(UserDTO loginUser);

    void resetPasswd(Long id, String passwd);

    /**
     * 启用 禁用 用户
     * @param userId
     * @param enable
     * @throws ServiceException
     */
    void enableUser(Long userId, boolean enable);

}
