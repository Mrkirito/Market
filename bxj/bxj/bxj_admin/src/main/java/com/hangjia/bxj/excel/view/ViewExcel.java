package com.hangjia.bxj.excel.view;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.excel.util.ExcelExportUtil;
import com.hangjia.bxj.excel.xml.ExcelMultiBean;
import com.hangjia.bxj.model.excel.ExportTask;
import com.hangjia.bxj.mvc.util.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.velocity.VelocityContext;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ViewExcel extends AbstractExcelView {
	
	@SuppressWarnings("rawtypes")
	private ExportTask exportTask;
	
    private static final String DEFAULT_EXCEL_SUBFIX = "_excel.xml";
    private static final String DEFAULT_DATA_LIST = "dataList";
    private static final String EXPORT_PREFIX = "src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"hangjia"+File.separator+"bxj"+File.separator+"excel"+File.separator+"export"+File.separator;


    /**
	 * 有参构造器
	 * @param title excel标题
	 * @param userId 创建者id
	 * @param query 查询类
	 * @param serviceBeanName 查询的service类
	 * @param serviceMethod 查询的方法
	 */
	public <T extends BaseCommonQuery> ViewExcel(String title, Long userId, T query, Object serviceBeanName, String serviceMethod, String excelModuleName){
		try {
			this.exportTask = saveExportTask(title, userId, query, serviceBeanName, serviceMethod, excelModuleName);
		} catch (Exception e) {
			logger.error("封装view失败:" + e);
		}
	}
	
	/**
	 * 重写AbstractExcelView方法
	 * @param obj 
	 * @param workbook 
	 * @param request 
	 * @param response 
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> obj, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		processExportTask(this.exportTask, response,request);
	}

	/**
	 * 导出excel
	 * @param task
	 * @param moduleObj
	 * @param dataList
	 * @param index
	 * @param title
	 * @param response
	 */
    private void generateExportExcelFile(ExportTask task, Object moduleObj, List dataList, int index, String title, HttpServletResponse response,HttpServletRequest request) {
        try {
            VelocityContext context = new VelocityContext();
            context.put(DEFAULT_DATA_LIST, dataList);

            String sp = File.separator;

            String path = request.getSession().getServletContext().getRealPath(sp);


            String excelModuleName = path +"excelTemplate" +sp + task.getExcelModuleName() + "_" + task.getServiceMethod()
                    + DEFAULT_EXCEL_SUBFIX;

            ExcelMultiBean excelBean = ExcelExportUtil.generateExcelBean(excelModuleName);

            if (null == excelBean) {
                excelModuleName = EXPORT_PREFIX + task.getServiceBeanName() + DEFAULT_EXCEL_SUBFIX;
                excelBean = ExcelExportUtil.generateExcelBean(excelModuleName);
            }

            if (null != excelBean) {

                long timestart = System.currentTimeMillis();

//                String fileName = task.getFilePrefix() + "/" +title+ "_"+index + "_" + DateUtils.formatSdf8(new Date()) +
//                        ".xls";

                String fileName = title+ "_"+index + "_" + DateUtils.formatSdf8(new Date());
                fileName = encodeFilename(fileName);//处理中文文件名
                response.setContentType("application/vnd.ms-excel");
        		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        		
        		OutputStream ouputStream = response.getOutputStream();
                ExcelExportUtil.export(context, ouputStream, excelBean);

                long timeend = System.currentTimeMillis();
                logger.error(excelModuleName + " >>>>> 导出耗时: >>>>>>>>>>> " + (timeend - timestart) + "ms");
            }
        }
        catch (Exception e) {
            logger.error("生成excel文件失败!" + task, e);
        }
    }
    
    /**
     * 封装ExportTask对象
     * @param title
     * @param userId
     * @param query
     * @param serviceBeanName
     * @param serviceMethod
     * @return
     * @throws Exception
     */
    private <T extends BaseCommonQuery> ExportTask saveExportTask(String title, Long userId, T query,
    		Object serviceBeanName, String serviceMethod, String excelModuleName) throws Exception {

        try {
            ExportTask task = new ExportTask();
            if (null != query) {
                task.setQuery(query);
            }
            task.setTitle(title);
            task.setUserId(userId);
            task.setServiceBeanName(serviceBeanName);
            task.setServiceMethod(serviceMethod);
            task.setExcelModuleName(excelModuleName);
//            String filePrefix = "/" + DateUtils.formatSdf6(new Date()) + "/"
//                    + DateUtils.formatSdf8(new Date());
//            task.setFilePrefix(filePrefix);
            return task;
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /**
     * 执行导出 批量分文件
     * @param task
     * @param response
     * @throws ClassNotFoundException 
     */
    private void processExportTask(ExportTask task, HttpServletResponse response,HttpServletRequest request) throws ClassNotFoundException {
        Object serviceBean = task.getServiceBeanName();
//    	Object serviceBean = Class.forName(task.getServiceBeanName());
        if (null != serviceBean && StringUtils.isNotBlank(task.getServiceMethod())) {
            BaseCommonQuery query = (BaseCommonQuery) task.getQuery();

            Method method = null;
            try {
                method = serviceBean.getClass().getMethod(task.getServiceMethod(), query.getClass());
            }
            catch (Exception e) {
                logger.error(task.getServiceBeanName() + "方法不存在:" + task.getServiceMethod(), e);
            }

            if (null != query && null != method) {
                resetQuery(query);

                String title = task.getTitle();
                int index = 1;
                int excelRows = 10000;

                int total = query.getTotalItem();
                int pageSize = 500;
                int pageCount = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
                List dataList = new ArrayList();

                query.setPageSize(pageSize);

                for (int page = 0; page < pageCount; page++) {
                    query.setCurrentPage(page + 1);

                    try {
                        List list = (List) method.invoke(serviceBean, query);
                        if (null != list && list.size() > 0) {
                            dataList.addAll(list);
                        }
                    }
                    catch (Exception e) {
                        logger.error("执行查询失败!" + task, e);
                    }

                    if (dataList.size() >= excelRows) {
                        generateExportExcelFile(task, serviceBean, dataList, index, title, response,request);
                        dataList = new ArrayList();
                        index++;
                    }
                }

                if (dataList.size() > 0) {
                    generateExportExcelFile(task, serviceBean, dataList, index, title, response,request);
                }

            }
        }
    }
    
    /**
     * 重构query
     * @param query
     */
    private void resetQuery(BaseCommonQuery query) {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        Field[] fields = query.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                String name = field.getName();
                Class type = field.getType();
                Object value = propertyUtilsBean.getProperty(query, name);

                if (PropertyUtils.isWriteable(query, name) && type == String.class && "".equals(value)) {
                    BeanUtils.setProperty(query, name, null);
                }
            } catch (Exception e) {
                continue;
            }
        }
        if(StringUtils.isBlank(query.getOrderBy())) {
            query.setOrderBy(null);
        }
    }
    
	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @return
	 */
	private String encodeFilename(String filename) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < filename.length(); i++) {
			char c = filename.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}