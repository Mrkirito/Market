package cn.usst.market.mapper;

import cn.usst.market.po.User;

public interface UserMapper {
	public User findUserById(Integer id)throws Exception;
	
	public User doMemberLogin(User user)throws Exception;
	
	public User doTeacherLogin(User user)throws Exception;
	
	public User doAdminLogin(User user)throws Exception;

	public User findUserByName(String userName)throws Exception;

	public void doRegister(User user)throws Exception;
}
