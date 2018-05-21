package com.hangjia.bxj.common;

/**
 * @author yaoy
 * @since 2016-06-14
 */
public class TreeNode extends BaseCommonDTO{
    private String nodeId;
    private String nodeName;
    private boolean checked;
    private boolean chkDisabled;
    private boolean open;
    private boolean disabled;
    private boolean nocheck;
    private String pId = "0";

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pid) {
        this.pId = pid;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }
}
