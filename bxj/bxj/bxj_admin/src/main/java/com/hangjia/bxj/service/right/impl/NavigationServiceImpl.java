package com.hangjia.bxj.service.right.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.NavigationMapper;
import com.hangjia.bxj.model.right.NavigationDO;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.right.NavigationQuery;
import com.hangjia.bxj.service.right.INavigationService;


/**
 * @author yaoy
 * @since 2016-06-14
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class NavigationServiceImpl implements INavigationService {

    @Resource
    private NavigationMapper navigationMapper;

    /**
     * 查询分页数据
     * 
     * @param query
     * @return
     * @throws ServiceException
     */
    @Override
    public List<NavigationDO> queryNavigationPageData(NavigationQuery query) {
        return navigationMapper.queryNavigationPageData(query);
    }

    /**
     * 查询数量
     * 
     * @param query
     * @return
     * @throws ServiceException
     */
    @Override
    public int queryNavigationPageCount(NavigationQuery query) {
        return navigationMapper.queryNavigationPageCount(query);
    }

    /**
     * 保存
     * 
     * @param Navigation
     * @throws ServiceException
     */
    @Override
    @MethodLog(remark = "保存菜单导航")
    public void saveNavigation(NavigationDO Navigation) {
        NavigationDO navigation = new NavigationDO();
        if (null != navigation.getId()) {
            navigationMapper.updateNavigation(Navigation);
        }
        else {
            navigationMapper.addNavigation(Navigation);
        }
    }

    /**
     * 删除
     * 
     * @param idList
     * @throws ServiceException
     */
    @Override
    @MethodLog(remark = "删除菜单导航")
    public void deleteNavigation(List<String> idList) {
        navigationMapper.deleteNavigation(idList);
    }

    /**
     * 查询有效的系统导航菜单
     * @return
     * @throws ServiceException
     */
    @Override
    public List<NavigationDO> queryNavigationList() {
        return navigationMapper.queryNavigationList();
    }

}
