package com.hangjia.bxj.mvc.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.framework.support.utility.MD5Utils;



/**
 * @author 任明<renming@aliyun.com>
 * @since 2015-01-14 22:53
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class.getClass());

    public static String copy(MultipartFile fileItem, File parentFile) {
        return copy(fileItem, parentFile, null);
    }

    public static String copy(MultipartFile fileItem, File parentFile, String fileNamePrefix) {
        String fileName = null;
        OutputStream os = null;
        try {
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            String filesuffix = FileUtil.getImageFileSuffix(fileItem.getContentType(), fileItem.getName());
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            int month = calendar.get(Calendar.MONTH);
//            String dir = calendar.get(Calendar.YEAR) + (month<9?"0":"") + (month+1) + "/";

            fileName = MD5Utils.encrypt(fileItem.getName() + new Date().getTime() + Math.random()) + "_" +
                    DateUtils.formatSdf8(new Date()) +
                    filesuffix;
            if(StringUtils.isNotBlank(fileNamePrefix)) {
                fileName = fileNamePrefix + "-" + fileName;
            }

            File imageFile = new File(parentFile, fileName);;
            if(!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }
            os = new BufferedOutputStream(new FileOutputStream(imageFile));
            StreamUtils.copy(fileItem.getInputStream(), os);

        } catch (IOException e) {
            logger.error("", e);
        } finally {
        	if(null != os){
        		try {
					os.close();
				} catch (IOException e) {
					os = null;
				}
        	}
        }
        return fileName;
    }

    public static String copy(File file, File parentFile, String filesuffix) {
        String fileName = null;

        try {
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int month = calendar.get(Calendar.MONTH);
            String dir = calendar.get(Calendar.YEAR) + (month<9?"0":"") + (month+1) + "/";

            fileName = dir + MD5Utils.encrypt(file.getName() + new Date().getTime() + Math.random()) + "_" +
                    DateUtils.formatSdf8(new Date()) +
                    filesuffix;

            File imageFile = new File(parentFile, fileName);;
            if(!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }
            OutputStream os = new BufferedOutputStream(new FileOutputStream(imageFile));
            StreamUtils.copy(new FileInputStream(file), os);

        } catch (IOException e) {
            logger.error("", e);
        }
        return fileName;
    }


    public static void copyOnly(File srcFile, File distFile) {
        try {
            if (!distFile.getParentFile().exists()) {
                distFile.getParentFile().mkdirs();
            }
            FileInputStream inputStream = new FileInputStream(srcFile);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(distFile));
            StreamUtils.copy(inputStream, os);

        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 获取图片类型后缀
     * @param contentType
     * @return
     */
    public static String getImageFileSuffix(String contentType, String fileName) {
        if ("image/bmp".equals(contentType)) {
            return ".bmp";
        }
        else if ("image/jpeg".equals(contentType)) {
            return ".jpg";
        }
        else if("image/png".equals(contentType)) {
            return ".png";
        } 
        else if("audio/mp3".equals(contentType)){
        	return ".mp3";
        } else {
            int idx = fileName.lastIndexOf(".");
            if(idx != -1) {
                return fileName.substring(idx);
            }
        }
        return "";
    }
}
