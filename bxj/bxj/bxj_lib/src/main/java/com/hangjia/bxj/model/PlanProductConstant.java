package com.hangjia.bxj.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PlanProductConstant implements Serializable{
	private static final long serialVersionUID = 6769130599029510583L;

	private Integer fid;

    private Integer fatherId;

    private Integer hide;

    private Integer inputType;

    private String defaultValue;

    private String columnField;

    private String memo;

    private String name;

    private String value;
    
    private String placeholder;
    
	private BigDecimal maxValues;

	private BigDecimal minValues;
    
	private Integer zxId;
	
	private Integer flag;
	
    private List<PlanProductConstant> children;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getColumnField() {
        return columnField;
    }

    public void setColumnField(String columnField) {
        this.columnField = columnField == null ? null : columnField.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
    
	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public List<PlanProductConstant> getChildren() {
		return children;
	}

	public void setChildren(List<PlanProductConstant> children) {
		this.children = children;
	}

	public BigDecimal getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(BigDecimal maxValues) {
		this.maxValues = maxValues;
	}

	public BigDecimal getMinValues() {
		return minValues;
	}

	public void setMinValues(BigDecimal minValues) {
		this.minValues = minValues;
	}

	public Integer getZxId() {
		return zxId;
	}

	public void setZxId(Integer zxId) {
		this.zxId = zxId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}