package com.hangjia.bxj.mvc.controller.right;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.right.NavigationDO;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.account.RoleQuery;
import com.hangjia.bxj.query.right.FunctionQuery;
import com.hangjia.bxj.service.account.IRoleService;
import com.hangjia.bxj.service.right.INavigationService;
import com.hangjia.bxj.service.right.IRightService;
import com.hangjia.bxj.vo.account.RoleDTO;
import com.hangjia.bxj.vo.right.FunctionDTO;
import com.hangjia.bxj.vo.right.ResourceDTO;

/**
 * @author yaoy
 * @since 2016-06-21
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseModule {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRightService rightService;
	@Autowired
	private INavigationService navigationService;

	/**
	 * 跳转页面
	 * 
	 * @return url
	 */
	@RequestMapping("roleList.jhtml")
	public String roleList() {
		return "account/roleList";
	}

	/**
	 * 跳转页面
	 * 
	 * @return url
	 */
	@RequestMapping("roleAdd.jhtml")
	public String roleAdd() {
		return "account/roleAdd";
	}

	/**
	 * 跳转页面编辑角色
	 * 
	 * @return url
	 */
	@RequestMapping("roleEdit.jhtml")
	public String roleEdit(Long id, HttpServletRequest request) {
		try {
			RoleDTO roleDTO = roleService.queryRoleById(id);
			if (null != roleDTO) {
				// 查询角色资源权限
				List<Long> resourceIdList = roleService.queryResurceIdList(id);
				request.setAttribute("resourceIdList", resourceIdList);

				// 查询角色按钮权限
				Map<String, List<JSONObject>> funcMap = new HashMap<String, List<JSONObject>>();
				List<Long> funcIdList = roleService.queryFunctionIdList(id);
				if (null != funcIdList && funcIdList.size() > 0) {
					Map<Long, Long> funcResourceMap = rightService.queryFuncResourceMap(funcIdList);
					if (null != funcResourceMap) {
						for (Map.Entry<Long, Long> entry : funcResourceMap.entrySet()) {
							Long funcId = entry.getKey();
							Long resourceId = entry.getValue();
							List<JSONObject> funcList = funcMap.get(resourceId + "");
							if (null == funcList) {
								funcList = new ArrayList<JSONObject>();
							}
							JSONObject func = new JSONObject();
							func.put("id", funcId);
							funcList.add(func);
							funcMap.put(resourceId + "", funcList);
						}
					}
				}
				request.setAttribute("funcMap", JSONObject.toJSONString(funcMap));
			}
			request.setAttribute("role", roleDTO);
		} catch (Exception e) {
			getLogger().error("", e);
		}

		return "account/roleEdit";
	}

	/**
	 * 跳转角色用户页面
	 * 
	 * @return url
	 */
	@RequestMapping("roleAccount.jhtml")
	public String roleAccount(HttpServletRequest request, Long id) {
		RoleDTO role = null;
		try {
			role = roleService.queryRoleById(id);
		} catch (Exception e) {
			getLogger().error("", e);
		}

		if (null != role) {
			request.setAttribute("role", role);
		}
		return "account/roleAccount";
	}

	/**
	 * 查询权限列表
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("queryPageData.json")
	public @ResponseBody Result queryPageData(RoleQuery query) {
		Result result = new Result();
		try {
			int total = roleService.queryPageCount(query);
			if (total > 0) {
				List<RoleDTO> roleList = roleService.queryPageData(query);
				result.setModel(roleList);
			}
			query.setTotalItem(total);
			result.setQuery(query);
		} catch (Exception e) {
			logger.error("", e);
			result.markFailure("查询失败");
		}
		return result;
	}

	/**
	 * 查询权限
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("queryRoleTree.json")
	public @ResponseBody Result queryRoleTree(RoleQuery query) {
		Result result = new Result();
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		roleDTOList = roleService.queryRoleList(query);
		// roleDTOList = generateRoleTree(roleDTOList);

		if (null != roleDTOList && roleDTOList.size() > 0) {
			result.setModel(roleDTOList);
		}

		return result;
	}

	private List<RoleDTO> generateRoleTree(List<RoleDTO> roleList) {
		List<RoleDTO> newList = new ArrayList<RoleDTO>();
		Map<Long, RoleDTO> roleMap = new HashMap<Long, RoleDTO>();
		if (null != roleList && roleList.size() > 0) {
			for (RoleDTO role : roleList) {
				if (null != role.getParentId() && 0 == role.getParentId().longValue()) {
					newList.add(role);
				}
				roleMap.put(role.getId(), role);
			}
			for (RoleDTO role : roleList) {
				if (null != role.getParentId() && 0 != role.getParentId().longValue()) {
					RoleDTO parent = roleMap.get(role.getParentId().longValue());
					if (null != parent) {
						parent.addRole(role);
					}
				}
			}
		}
		return newList;
	}

	/**
	 * 查询菜单权限
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("queryResourceTree.json")
	public @ResponseBody Result queryResourceTree(Long roleId) {
		Result result = new Result();
		List<ResourceDTO> resourceDTOList = new ArrayList<ResourceDTO>();
		List<Long> resourceIdList = new ArrayList<Long>();
		List<NavigationDO> navigationList = null;

		JSONArray root = new JSONArray();

		try {
			// 查询菜单
			navigationList = navigationService.queryNavigationList();

			if (null != navigationList && navigationList.size() > 0) {
				for (NavigationDO navigation : navigationList) {
					JSONObject node = new JSONObject();
					node.put("id", navigation.getNavCode());
					node.put("name", navigation.getNavName() + "(系统)");
					node.put("pId", 0);
					node.put("chkDisabled", true);
					node.put("nocheck", true);
					node.put("font", "{\"color\": \"#E41B01\",\"font-weight\": \"bold\"}");
					root.add(node);
				}

				resourceDTOList = rightService.queryResourceList();
				resourceIdList = roleService.queryResurceIdList(roleId);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		if (null != resourceDTOList && resourceDTOList.size() > 0) {
			for (ResourceDTO resourceDTO : resourceDTOList) {
				JSONObject node = new JSONObject();
				node.put("id", resourceDTO.getId());
				node.put("name", resourceDTO.getResourceName());

				if (null != resourceIdList && resourceIdList.size() > 0) {
					if (resourceIdList.contains(resourceDTO.getId().longValue())) {
						resourceDTO.setChecked(true);
						resourceDTO.setOpen(true);

						node.put("checked", true);
						node.put("open", true);
					}
				}
				if (0 == resourceDTO.getPermissionControl().intValue()) {
					resourceDTO.setChkDisabled(true);

					node.put("chkDisabled", true);
					// node.put("nocheck", true);
					node.put("checked", true);
				}
				String parentId = null;

				if (null != resourceDTO.getParentId()) {
					if (0 == resourceDTO.getParentId().intValue()) {
						parentId = resourceDTO.getNavCode();
					} else {
						parentId = resourceDTO.getParentId().toString();
					}
				}

				if (StringUtils.isNotBlank(parentId)) {
					node.put("pId", parentId);
					root.add(node);
				}
			}

			// result.setModel(resourceDTOList);
			result.setModel(root);
		}

		return result;
	}

	/**
	 * 查询按钮权限
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("queryResourceFunctionTree2.json")
	public @ResponseBody Result queryResourceFunctionTree2(FunctionQuery functionQuery) {
		Result result = new Result();
		try {
			long roleId = functionQuery.getRoleId();
			long resourceId = functionQuery.getResourceId();
			if (0 != resourceId) {
				List<Long> functionIdList = new ArrayList<Long>();
				if (0 != roleId) {
					functionIdList = roleService.queryFunctionIdList(roleId);
				}

				ResourceDTO resourceDTO = rightService.queryResourceById(resourceId);
				if (null != resourceDTO) {
					List<FunctionDTO> funcList = rightService.queryFunctionList(resourceDTO.getResourceCode());
					if (null != funcList && funcList.size() > 0) {
						for (FunctionDTO func : funcList) {
							func.setPId("0");
							if (functionIdList.contains(func.getId().longValue())) {
								func.setChecked(true);
							}
						}
					}
					result.setModel(funcList);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			result.markFailure("查询角色按钮权限失败");
		}
		return result;
	}

	/**
	 * 新增角色
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("saveRole.json")
	public @ResponseBody Result saveRole(RoleDTO roleDTO, String resourceIdList, String funcIdList) {
		Result result = new Result();
		try {
			roleDTO.setUpdateName(getLoginUserName());
			roleDTO.setCreateName(getLoginUserName());
			roleService.saveRole(roleDTO, resourceIdList, funcIdList, getLoginUserName());
		} catch (Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setMsg("保存失败");
		}
		return result;
	}

	/**
	 * 删除角色
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("deleteRole.json")
	public @ResponseBody Result deleteRole(Integer status, @RequestParam("idList[]") List<String> idList) {
		Result result = new Result();
		try {
			if (null != idList && idList.size() > 0 && 0 == status) {
				int count = roleService.queryRoleUsedCount(idList);
				if (count > 0) {
					result.markFailure("角色被使用中, 无法禁用");
				}
			}
			if (result.isSuccess()) {
				roleService.deleteRole(idList, status);
			}
		} catch (Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setMsg("删除失败");
		}

		return result;
	}
	
	/**
	 * 新增角色账户
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("saveRoleUser.json")
	public @ResponseBody Result saveRoleUser(Long roleId, @RequestParam("userIdList[]") List<String> userIdList) {
		Result result = new Result();
		try {
			roleService.saveRoleUser(roleId, userIdList);
		} catch (Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setMsg("保存失败");
		}
		return result;
	}

	/**
	 * 删除角色账户
	 * 
	 * @param query
	 * @return Result
	 */
	@RequestMapping("deleteRoleUser.json")
	public @ResponseBody Result deleteRoleUser(Long roleId, @RequestParam("userIdList[]") List<String> userIdList) {
		Result result = new Result();
		try {
			roleService.deleteRoleUser(roleId, userIdList);
		} catch (Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setMsg("保存失败");
		}
		return result;
	}
}
