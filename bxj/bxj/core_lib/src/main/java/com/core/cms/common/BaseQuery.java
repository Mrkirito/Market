package com.core.cms.common;
/** 
* @author 
* @date 2016年5月3日 下午2:29:07 
* @version 1.0 
*/
public class BaseQuery extends QueryBase {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 7195633561938921738L;

    private String tableName;
    /**
     * QueryBase 中的分页代码 ,方法名称写错了。-from zero
     * @return
     */
    public int getPageFirstItem() {
        return super.getPageFirstItem() -1;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableName() {
        return tableName;
    }
}
