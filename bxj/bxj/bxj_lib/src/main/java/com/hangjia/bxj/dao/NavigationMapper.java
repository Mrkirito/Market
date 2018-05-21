package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.right.NavigationDO;
import com.hangjia.bxj.query.right.NavigationQuery;

public interface NavigationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NavigationDO record);

    int insertSelective(NavigationDO record);

    NavigationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NavigationDO record);

    int updateByPrimaryKey(NavigationDO record);
    
    /**
     * 查询系统导航数据
     * 
     * @param query
     * @return
     * @throws DAOException
     */
    List<NavigationDO> queryNavigationPageData(NavigationQuery query);


    /**
     * 查询系统导航数量
     *
     * @param query
     * @return
     * @throws DAOException
     */
    int queryNavigationPageCount(NavigationQuery query);


    /**
     * 新增系统导航
     *
     * @param NavigationDO
     * @throws DAOException
     */
    void addNavigation(NavigationDO navigation);


    /**
     * 修改系统导航
     *
     * @param NavigationDO
     * @throws DAOException
     */
    void updateNavigation(NavigationDO navigation);


    /**
     * 删除系统导航
     *
     * @param idList
     * @throws DAOException
     */
    void deleteNavigation(List<String> idList);


    /**
     * 查询
     *
     * @return
     * @throws DAOException
     */
    List<NavigationDO> queryNavigationList();
}