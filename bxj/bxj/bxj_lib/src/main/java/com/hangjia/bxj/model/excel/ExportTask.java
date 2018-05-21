package com.hangjia.bxj.model.excel;

import com.hangjia.bxj.common.BaseCommonDO;

/**
 * @author 任明<renming@aliyun.com>
 * @since 2016-05-31 11:06
 */
public class ExportTask<T> extends BaseCommonDO {
    private Long id;
    private String title;
    private Long userId;
    private T query;
    private Object serviceBeanName;
    private String serviceMethod;
    private String fileRoot;
    private String filePrefix;
    private String excelModuleName;
    
    public String getExcelModuleName() {
		return excelModuleName;
	}

	public void setExcelModuleName(String excelModuleName) {
		this.excelModuleName = excelModuleName;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public String getFileRoot() {
        return fileRoot;
    }

    public void setFileRoot(String fileRoot) {
        this.fileRoot = fileRoot;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public T getQuery() {
		return query;
	}

	public void setQuery(T query) {
		this.query = query;
	}

	public Object getServiceBeanName() {
        return serviceBeanName;
    }

    public void setServiceBeanName(Object serviceBeanName) {
        this.serviceBeanName = serviceBeanName;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    @Override
    public String toString() {
        return "ExportTask{" +
                "id=" + id +
                ", userId=" + userId +
//                ", queryJson='" + queryJson + '\'' +
//                ", queryClazz='" + queryClazz + '\'' +
                ", serviceBeanName='" + serviceBeanName + '\'' +
                ", serviceMethod='" + serviceMethod + '\'' +
                ", fileRoot='" + fileRoot + '\'' +
                ", filePrefix='" + filePrefix + '\'' +
                '}';
    }
}
