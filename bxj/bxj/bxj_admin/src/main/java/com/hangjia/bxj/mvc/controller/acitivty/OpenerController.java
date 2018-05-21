package com.hangjia.bxj.mvc.controller.acitivty;

import com.hangjia.bxj.common.DateUtil;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.activity.ActivityOpenerArticle;
import com.hangjia.bxj.mvc.util.UEditorUtils;
import com.hangjia.bxj.query.activity.OpenerArticleQuery;
import com.hangjia.bxj.service.activity.IOpenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 作者 : bei.zhang
 * @version 1.0
 * @date 2016年11月23日 下午2:30:15
 */
@Controller
@RequestMapping("/activity")
public class OpenerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IOpenerService openerService;
    private String publicPath;
    private String uploadPath;

    @InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Value("${public_path}")
    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath + "/bxj_web/upload/study/opener/article/";
    }

    @Value("${bxj.upload.root}")
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath + File.separator + "study" + File.separator + "opener" + File.separator + "article" + File.separator;
    }

    public String getUploadPath() {
        String tempFile = uploadPath + DateUtil.format(new Date(), "yyyyMMdd");
        File fileDir = new File(tempFile);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        return tempFile + File.separator;
    }

    public String getPublicPath() {
        return publicPath + DateUtil.format(new Date(), "yyyyMMdd") + "/";
    }

    /**
     * 跳转页面
     * 开门红文章活动管理
     *
     * @return url
     */
    @RequestMapping("opener.jhtml")
    public String opener() {
        return "activity/opener";
    }

    /**
     * 跳转页面
     * 创建开门红文章
     *
     * @return url
     */
    @RequestMapping("createOpener.jhtml")
    public String createOpener() {
        return "activity/openerSave";
    }

    /**
     * 跳转页面
     * 修改开门红文章
     *
     * @return url
     */
    @RequestMapping("updateOpener.jhtml")
    public String updateOpener(Long id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "activity/openerSave";
    }

    /**
     * 查询开门红文章列表
     *
     * @param query
     * @return Result
     */
    @RequestMapping("queryOpenerArticleList.json")
    @ResponseBody
    public Result queryOpenerArticleList(@ModelAttribute OpenerArticleQuery query) {
        Result result = new Result();
        int count = 1;
        if (null != query) {
            count = openerService.queryOpenerDataCount(query);
        }
        if (count > 0) {
            List<ActivityOpenerArticle> profitList = openerService.queryOpenerDataPage(query);
            result.setModel(profitList);
        }
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    /**
     * 新增开门红文章
     *
     * @param openerArticle
     * @return Result
     */
    @RequestMapping("saveOpener.json")
    @ResponseBody
    public Result saveOpener(@ModelAttribute ActivityOpenerArticle openerArticle) {
        Result result = new Result();
        if (openerArticle.getId() == null) {
            int count = openerService.addOpenerArticle(openerArticle);
            if (count <= 0) {
                result.setSuccess(false);
                result.setMsg("新增失败");
            } else {
                result.setMsg("新增成功");
            }
        } else {
            int count = openerService.updateOpenerArticle(openerArticle);
            if (count <= 0) {
                result.setSuccess(false);
                result.setMsg("修改失败");
            } else {
                result.setMsg("修改成功");
            }
        }
        return result;
    }

    /**
     * 根据ID查询开门红文章
     *
     * @param id
     * @return Result
     */
    @RequestMapping("queryOpenerArticleById.json")
    @ResponseBody
    public Result queryOpenerArticleById(Long id) {
        Result result = new Result();
        result.setModel(openerService.queryOpenerDataById(id));
        return result;
    }

    /**
     * 抓去远程图片，当远程图片被复制到编辑器时，由 ueditor 发起请求调用。
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("catchImage.json")
    private Object catchImage(HttpServletRequest request) {
        String[] param = request.getParameterValues("source[]");
        return UEditorUtils.saveRemoteFile(param, this.getUploadPath(), this.getPublicPath());
    }

    @ResponseBody
    @RequestMapping("uploadImage.json")
    private Object uploadImage(MultipartFile upfile) {
        return UEditorUtils.uploadFile(upfile, this.getUploadPath(), this.getPublicPath());
    }

    @ResponseBody
    @RequestMapping("listImage.json")
    private Object listImage(int start, int size) {
        return UEditorUtils.listFile(start, size, this.getUploadPath(), this.getPublicPath());
    }

}
