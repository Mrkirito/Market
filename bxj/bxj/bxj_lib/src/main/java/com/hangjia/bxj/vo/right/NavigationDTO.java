package com.hangjia.bxj.vo.right;

/**
 * @author yaoy
 * @since 2016-06-14
 */
public class NavigationDTO extends ResourceTreeNode {
    private Long id;
    private String navCode;
    private String navName;
    private Integer navSort;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNavCode() {
        return navCode;
    }


    public void setNavCode(String navCode) {
        this.navCode = navCode;
    }


    public String getNavName() {
        return navName;
    }


    public void setNavName(String navName) {
        this.navName = navName;
    }


    public Integer getNavSort() {
        return navSort;
    }


    public void setNavSort(Integer navSort) {
        this.navSort = navSort;
    }
}
