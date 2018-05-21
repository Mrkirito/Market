package com.znb.cms.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
* @author 
 * @param <T>
* @date 2016年5月3日 下午2:30:15 
*/
public class Result<T>{
    private boolean success = true;
    private String msg;
    private BaseCommonQuery query;
    private T model;
    private Map<String, Object> extData;

    private List<List<Object>> exportDataList;
     /**显示总数**/
    private String showTotal;
    /**显示提示信息**/
    private String showTextInfo;
    /**是否还有下一个**/
    private boolean hasNext;
    public Result(){
    	
    }
    
    public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public static Result newInstance(){
        return new Result();
    }

    public void addExportDataList(List<Object> dataList) {
        if(null == exportDataList) {
            exportDataList = new ArrayList<List<Object>>();
        }
        exportDataList.add(dataList);
    }

    public void markFailure(String msg) {
        this.success = false;
        this.msg = msg;
    }

    public List<List<Object>> getExportDataList() {
        return exportDataList;
    }

    public void setExportDataList(List<List<Object>> exportDataList) {
        this.exportDataList = exportDataList;
    }

    public BaseCommonQuery getQuery() {
        return query;
    }

    public void setQuery(BaseCommonQuery query) {
        this.query = query;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, Object> extData) {
        this.extData = extData;
    }

    public void addExtData(String key, Object value) {
        if(null == extData) {
            extData = new HashMap<String, Object>();
        }
        if(StringUtils.isNotBlank(key)) {
            extData.put(key, value);
        }
    }

	public String getShowTotal() {
		return showTotal;
	}

	public void setShowTotal(String showTotal) {
		this.showTotal = showTotal;
	}

	public String getShowTextInfo() {
		return showTextInfo;
	}

	public void setShowTextInfo(String showTextInfo) {
		this.showTextInfo = showTextInfo;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
