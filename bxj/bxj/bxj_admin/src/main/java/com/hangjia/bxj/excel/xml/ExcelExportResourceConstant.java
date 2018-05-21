package com.hangjia.bxj.excel.xml;

import java.util.Arrays;
import java.util.List;

public interface ExcelExportResourceConstant {
    String XPATH_EXPORT_ROOT = "/export-root";
    String XPATH_ROOT_NAME = "string(@name)";
    String XPATH_EXPORT_FIELDS = "/export-root/export-fields";
    String XPATH_EXPORT_FIELDS_ID = "string(@id)";
    String XPATH_EXPORT_FIELDS_NAME = "string(@name)";
    String XPATH_EXPORT_FIELD = "export-field";
    String XPATH_FIELD_NAME = "string(@name)";
    String XPATH_FIELD_TITLE = "string(@title)";
    String XPATH_FIELD_TYPE = "string(@type)";
    String XPATH_FIELD_DATE_FORMATE = "string(@dateFormat)";
    String XPATH_FIELD_WIDTH = "string(@width)";
    String XPATH_SELECT_DATA_FIELD = "select/data";
    String XPATH_SELECT_DATE_ID = "string(@id)";
    String XPATH_SELECT_DATE_TEXT = "string(@text)";
    String XPATH_RENDER_FIELD = "render";
    String XPATH_RENDER_CLASS_NAME = "string(@class)";
    String NAME_ERROR_MISSING = "name attribute must is not null";
    String TITLE_ERROR_MISSING = "title attribute must is not null";
    String TYPE_ERROR_MISSING_1 = "type attribute must is not null";
    String TYPE_ERROR_MISSING = "type must in String|BigDecimal|Double|Integer|Date|select";
    List<String> typeList = Arrays.asList((new String[]{"String", "BigDecimal", "Double", "Integer", "Date", "select"}));
    String TYPE_DATE = "Date";
    String TYPE_STRING = "String";
    String TYPE_BigDECIMAL = "BigDecimal";
    String TYPE_INTEGER = "Integer";
    String TYPE_DOUBLE = "Double";
    String TYPE_SELECT = "select";
    String TYPE_DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
}
