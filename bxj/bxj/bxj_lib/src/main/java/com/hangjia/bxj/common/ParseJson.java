package com.hangjia.bxj.common;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class ParseJson {
    private static Logger logger = LoggerFactory.getLogger(ParseJson.class);

    public static Object parse(String content, String key) {
        try {
            if (null == content) return null;
            return JSONObject.parseObject(content).get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ParseJson parse error!");
        }
        return null;
    }

    public static String parseToString(String content, String key) {
        Object result = parse(content, key);
        if (null != result) return String.valueOf(result);
        return null;
    }

    public static Integer parseToInt(String content, String key) {
        String result = parseToString(content, key);
        if (null != result && !"".equals(result)) return Integer.parseInt(result);
        return null;
    }

    public static Long parseToLong(String content, String key) {
        String result = parseToString(content, key);
        if (null != result && !"".equals(result)) return Long.parseLong(result);
        return null;
    }

    public static <T> List<T> parseToList(String content, String key, Class<T> clazz) {
        String result = parseToString(content, key);
        if (null != result && !"".equals(result)) {
            try {
                return JSONObject.parseArray(result, clazz);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.info("ParseJson parseToList error!");
            }
        }
        return null;
    }

}
