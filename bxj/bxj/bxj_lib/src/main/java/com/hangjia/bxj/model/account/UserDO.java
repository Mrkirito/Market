package com.hangjia.bxj.model.account;

import java.util.Date;
import java.util.List;

import com.hangjia.bxj.common.BaseCommonDO;
import com.hangjia.bxj.vo.account.RoleDTO;

/**
 * @author yaoy
 * @since 2016-06-17
 */
public class UserDO extends BaseCommonDO {
	private Long id;
	/**
	 * 员工号，登录使用
	 */
	private String userCode;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 密码：使用md5加密之后的结果
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 职称 数据字典
	 */
	private String positionTitle;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	private String realName;
	private String phone;
	private String description;

	private String newPassword;
	private String rePassword;

	private String headImg;

	private Long leaderId;
	private String leaderName;

	private String roleIds;
	private String roleNames;
	private String themeName;
	private Long vendorId;// 供应商
	private Integer initPasswd;
	private String department;// 部门名称

	private Integer userRole;

	private String roleIdList;
	private List<RoleDTO> roleList;

	private String roleJsonStr;
	
	public String getRoleJsonStr() {
		return roleJsonStr;
	}

	public void setRoleJsonStr(String roleJsonStr) {
		this.roleJsonStr = roleJsonStr;
	}

	public List<RoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}

	public String getRoleIdList() {
		return roleIdList;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public void setRoleIdList(String roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getInitPasswd() {
		return initPasswd;
	}

	public void setInitPasswd(Integer initPasswd) {
		this.initPasswd = initPasswd;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
