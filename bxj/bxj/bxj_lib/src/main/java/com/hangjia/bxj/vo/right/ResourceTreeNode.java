package com.hangjia.bxj.vo.right;


import com.hangjia.bxj.common.TreeNode;

/**
 * @author yaoy
 * @since 2016-06-14
 */
public class ResourceTreeNode extends TreeNode {
    private String type = "resource";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
