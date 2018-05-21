package com.hangjia.bxj.service.right;

import java.util.List;

import com.hangjia.bxj.model.right.NavigationDO;
import com.hangjia.bxj.query.right.NavigationQuery;


/**
 * 权限
 * 
 * @author yaoy
 * @since 2016-06-14 16:06
 */
public interface INavigationService {
	
    /**
     * 查询分页数据
     * 
     * @param query
     * @return
     * @throws ServiceException
     */
    List<NavigationDO> queryNavigationPageData(NavigationQuery query);


    /**
     * 查询数量
     * 
     * @param query
     * @return
     * @throws ServiceException
     */
    int queryNavigationPageCount(NavigationQuery query);


    /**
     * 保存
     * 
     * @param Navigation
     * @throws ServiceException
     */
    void saveNavigation(NavigationDO Navigation);


    /**
     * 删除
     * 
     * @param idList
     * @throws ServiceException
     */
    void deleteNavigation(List<String> idList);

    /**
     * 查询有效的系统导航菜单
     * @return
     * @throws ServiceException
     */
    List<NavigationDO> queryNavigationList();

}
