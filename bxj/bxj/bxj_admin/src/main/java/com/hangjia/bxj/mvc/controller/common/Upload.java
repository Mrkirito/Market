package com.hangjia.bxj.mvc.controller.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.mvc.util.FileUtil;
import com.hangjia.bxj.mvc.util.ImageUtils;


/**
 * @author 作者 : yaoy
 * @version 1.0
 * @date 2016年5月9日 下午2:30:15
 */
@Controller
@RequestMapping("/common")
public class Upload {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${bxj.upload.root}")
    private String uploadPath;
    @Value("${show_path}")
    private String staticPath;
    @Value("${bxj.egg.upload}")
    private String eggPrizePath;


    @Value("${bxj.online.upload}")
    private String salesOnlinePath;
    @Value("${bxj.base.upload}")
    private String baseUploadPath;

    @RequestMapping("uploadImage.json")
    public
    @ResponseBody
    Result uploadImage(int attach, MultipartFile imageFile, String size, String type) throws IOException {

        Result result = new Result();
        JSONObject data = new JSONObject();
        String fileName = "";
        String dateDir = getTimePath();

        if (null != imageFile) {
            // 文件类型: 1：图片 2 ： 其他
            int fileType = checkFileType(imageFile.getContentType());

            if (fileType == 1 && StringUtils.isNotBlank(size) && !checkImageSize(imageFile, size)) {
                logger.error("图片尺寸不符!size = " + size);
                result.markFailure("图片尺寸不符, 尺寸要求【" + size + "】");
                return result;
            }

            String serverPath = uploadPath + File.separator + "hjb_app" + File.separator + "mapimages";
            // 头条banner attach=1
            if (1 == attach) {

            } else if (2 == attach) {
                serverPath = uploadPath + File.separator + "study" + File.separator + "images";
                // 视频缩略图
            } else if (3 == attach) {
                serverPath = uploadPath + File.separator + "championsay" + File.separator + dateDir + File.separator + "cover_images";
                // 音频
            } else if (4 == attach) {
                serverPath = uploadPath + File.separator + "championsay" + File.separator + dateDir + File.separator + "mp3";
            } else if (5 == attach) { // 朋友圈
                serverPath = uploadPath + File.separator + "friendcircle" + File.separator + dateDir + File.separator + "images";
            } else if (6 == attach) { // 峰会
                serverPath = uploadPath + File.separator + "summit" + File.separator + dateDir + File.separator + "images";
                // 砸蛋奖品图片
            } else if (7 == attach) {
                serverPath = eggPrizePath;
            } else if (8 == attach) {//视频直播
                serverPath = salesOnlinePath;
            } else if (9 == attach) {//投票
                serverPath = baseUploadPath + File.separator + "activity/upload/vote" + File.separator + dateDir;
            } else if (10 == attach) {
                /** 商品 **/
                serverPath = uploadPath + File.separator + "shop" + File.separator + dateDir + File.separator + "images";
            } else if (11 == attach) {
                /** 新人通 **/
                serverPath = uploadPath + File.separator + "newperson" + File.separator + dateDir + File.separator + "images";
            } else if (12 == attach) {
                /** 开门红 **/
                serverPath = uploadPath + File.separator + "study" + File.separator + "opener" + File.separator + "article" + File.separator + dateDir;
            }else if(13==attach){
            	serverPath = uploadPath + File.separator + "init_ad" + File.separator + dateDir+File.separator + "images";
            } else if (14 == attach) {
                /** 新人通 **/
                serverPath = uploadPath + File.separator + "newperson" + File.separator + dateDir + File.separator + "images";
            }
            File floder = new File(serverPath);
            if (!floder.exists()) {
                floder.mkdirs();
            }
            // 图片
            if (1 == fileType) {
                String filename = attach + "_" + System.currentTimeMillis();
                String fileSuffix = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."), imageFile.getOriginalFilename().length());
                String imgFilePath = serverPath + File.separator + filename + fileSuffix;
                try {
                    BufferedImage image = ImageUtils.readImage(imageFile.getInputStream());
                    ImageUtils.writeImage(image, fileSuffix.substring(fileSuffix.lastIndexOf(".")+1), new File(imgFilePath));
                    if ("Windows 7".equals(System.getProperty("os.name"))) {
                        fileName = "/hjb_app/mapimages/" + filename + fileSuffix;
                        //知识图片 名
                        if (2 == attach) {
                            fileName = "/upload/study/images/" + filename + fileSuffix;
                        } else if (3 == attach) {
                            fileName = "/upload/championsay/" + dateDir + "/cover_images/" + filename + fileSuffix;
                        } else if (5 == attach) {
                            fileName = "/upload/friendcircle/" + dateDir + "/images/" + filename + fileSuffix;
                        } else if (6 == attach) { // 峰会
                            fileName = "/upload/summit/" + dateDir + "/images/" + filename + fileSuffix;
                        } else if (7 == attach) {
                            fileName = "/V3.0/champion/images/video_details/Eggs/" + filename + fileSuffix;
                        } else if (8 == attach) {
                            fileName = "/upload/sales/course/" + filename + fileSuffix;
                        } else if (9 == attach) {
                            fileName = "/upload/vote/" + dateDir + "/" + filename + fileSuffix;
                        } else if (10 == attach) {
                            fileName = "/upload/shop/" + dateDir + "/images/" + filename + fileSuffix;
                        } else if (11 == attach) {
                            fileName = "/upload/newperson/" + dateDir + "/images/" + filename + fileSuffix;
                        } else if (12 == attach) {
                            fileName = "/bxj_web/upload/study/opener/article/" + dateDir + "/" + filename + fileSuffix;
                        } else if (13==attach){
                            fileName = "/upload/init_ad/" + dateDir + "/images/" + filename + fileSuffix;
                        } else if (14 == attach) {
                            fileName = "/upload/newperson/" + dateDir + "/images/" + filename + fileSuffix;
                        }
                    } else {
                        fileName = File.separator + "upload" + File.separator + "hjb_app" + File.separator + "mapimages" + File.separator + filename
                                + fileSuffix;
                        //知识图片 名
                        if (2 == attach) {
                            fileName = File.separator + "upload" + File.separator + "study" + File.separator + "images" + File.separator + filename + fileSuffix;
                        } else if (3 == attach) {
                            fileName = File.separator + "upload" + File.separator + "championsay" + File.separator +
                                    dateDir + File.separator + "cover_images" + File.separator + filename + fileSuffix;
                        } else if (5 == attach) {
                            fileName = File.separator + "upload" + File.separator + "friendcircle" + File.separator + dateDir + File.separator + "images" + File.separator + filename + fileSuffix;
                        } else if (6 == attach) { // 峰会
                            fileName = File.separator + "upload" + File.separator + "summit" + File.separator + dateDir + File.separator + "images" + File.separator + filename + fileSuffix;
                        } else if (7 == attach) {
                            fileName = File.separator + "V3.0" + File.separator + "champion" + File.separator + "images" + File.separator + "video_details" + File.separator + "Eggs" + File.separator + filename + fileSuffix;
                        } else if (8 == attach) {
                            fileName = File.separator + "upload" + File.separator + "sales" + File.separator + "course" + File.separator + filename + fileSuffix;
                        } else if (9 == attach) {
                            fileName = File.separator + "upload" + File.separator + "vote" + File.separator + dateDir + File.separator + filename + fileSuffix;
                        } else if (10 == attach) {
                            fileName = File.separator + "upload" + File.separator + "shop" + File.separator + dateDir + File.separator + "images" + File.separator + filename + fileSuffix;
                        } else if (11 == attach) {
                            fileName = File.separator + "upload" + File.separator + "newperson" + File.separator + dateDir + File.separator + "images" + File.separator + filename + fileSuffix;
                        }else if (12 == attach){
                            fileName = File.separator + "bxj_web" + File.separator + "upload" + File.separator + "study" + File.separator + "opener" + File.separator + "article" + File.separator + dateDir + File.separator + filename + fileSuffix;
                        }else if(13==attach){
                            fileName = File.separator + "upload" + File.separator + "init_ad" + File.separator + dateDir + File.separator + "images" + File.separator + filename + fileSuffix;
                        } else if (14 == attach) {
                            fileName = File.separator + "upload" + File.separator + "newperson" + File.separator + dateDir + File.separator + "images" + File.separator + filename + fileSuffix;
                        }
                    }

                } catch (Exception e) {
                    result.setSuccess(false);
                    result.setMsg("上传失败");
                    logger.error("", e);
                }
                // 文件
            } else {
                // 计算文件大小
                if ("audio/mp3".equals(imageFile.getContentType())) {
                    Long voiceSize = imageFile.getSize() / 1024 / 1024;
                    data.put("voiceSize", voiceSize);
                }
                String filename = FileUtil.copy(imageFile, new File(serverPath));
                if ("Windows 7".equals(System.getProperty("os.name"))) {
                    fileName = "/upload/championsay/" + dateDir + "/mp3/" + filename;
                } else {
                    fileName = File.separator + "upload" + File.separator + "championsay" + File.separator + dateDir + File.separator + "mp3" + File.separator + filename;
                }

            }

            data.put("fileName", fileName);
            data.put("fileUrl", staticPath + fileName);
            if (5 == attach) {
                BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
                data.put("width", bufferedImage.getWidth());
                data.put("height", bufferedImage.getHeight());
            }
            result.setModel(data);
        }
        return result;
    }

    private boolean checkImageSize(MultipartFile fileItem, String size) {
        try {
            BufferedImage bufferedImage = ImageIO.read(fileItem.getInputStream());
            String realSize = bufferedImage.getWidth() + "x" + bufferedImage.getHeight();
            String realSize2 = bufferedImage.getWidth() + "*" + bufferedImage.getHeight();
            if (!realSize.equals(size) && !realSize2.equals(size)) {
                return false;
            }
        } catch (Exception e) {
            logger.error("检查图片尺寸错误", e);
            return false;
        }
        return true;
    }

    /**
     * 文件类型: 1：图片 2 ： 其他
     *
     * @param contentType
     * @return
     */
    private int checkFileType(String contentType) {
        if ("image/bmp".equals(contentType) || "image/jpeg".equals(contentType)
                || "image/png".equals(contentType)) {
            return 1;
        }
        return 2;
    }

    /**
     * 计算音频时长和大小
     *
     * @return
     */
    private String getTimePath() {
        return DateUtils.format(new Date(), "yyyyMMdd");
    }
}
