package com.znb.cms.mvc.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.znb.cms.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.core.cms.common.AjaxResult;
import com.core.cms.common.Result;
import com.core.cms.model.dto.TradeInsureDto;
import com.core.cms.model.mapper.TradeInsure;
import com.znb.cms.service.ITradeInsureService;
import com.znb.cms.util.ExcelUtils;
import com.znb.cms.util.PDFTempletUtil;

@Controller
@RequestMapping("/tradeinsure")
public class TradeInsureController {
    @Autowired
    private ITradeInsureService tradeInsureService;

    @Value("${upload_pdf_core_url}")
    private String uploadPdfUrl;

    @InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("index.jhtml")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("order/tradeinsurelist");
        view.addObject("sources", tradeInsureService.queryTCSource());
        return view;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public Result suggestionList(TradeInsure tradeInsure) throws Exception {
        Result result = new Result();
        String CreateTimeTemp1 = tradeInsure.getCreateTimeTemp();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(CreateTimeTemp1)) {
            CreateTimeTemp1+=" 23:59:59";
            tradeInsure.setCreateTimeTemp1(CreateTimeTemp1);
        }
        int count = tradeInsureService.selectCount(tradeInsure);
        if (count > 0) {
            result.setModel(tradeInsureService.getTradeInsureList(tradeInsure));
        }
        tradeInsure.setTotalItem(count);
        result.setQuery(tradeInsure);
        return result;
    }


    @RequestMapping("/detail.json")
    @ResponseBody
    public Result detail(Integer tradeId) {
        Result result = new Result();
        result.setModel(tradeInsureService.getTradeInsureDtoById(tradeId));
         /*tradeInsureService.getTradeInsureDtoByOrderId(tradeId)*/
        return result;
    }


    @RequestMapping("/updateOrder.json")
    @ResponseBody
    public Object updateOrder(TradeInsureDto tradeInsureDto) {
        try {
            return tradeInsureService.updateTradeInsure(tradeInsureDto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/del.json")
    @ResponseBody
    public Result del(Integer id) {
        Result result = new Result();
        tradeInsureService.delTradeInsureByFid(id);
        result.setSuccess(true);
        result.setMsg("作废成功");
        return result;
    }


    /**
     * 读取pdf文件
     *
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/downSinglePolicy.json")
    public String downSinglePolicy(HttpServletResponse response, String insureFileUrl) {
        //设置返回类型
        response.reset();
        response.setContentType("application/pdf;charset=gb2312");
        response.setHeader("Content-Disposition", "attachment;filename=" + ExcelUtils.encodeFilename("policy.pdf"));
        PDFTempletUtil pdfTT = new PDFTempletUtil();
        pdfTT.setTemplatePdfPath(uploadPdfUrl + insureFileUrl);
        try {
            pdfTT.readPdf(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传保单
     *
     * @return
     * @author yuanxin
     * @date 2017年5月25日下午5:49:16
     * @version <b>1.0.0</b>
     */
    @RequestMapping(value = "/importSinglePolicy.json", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult importSingleProduct(MultipartFile file, Integer id, HttpServletRequest request, HttpServletResponse response) {
        AjaxResult result = null;
        try {
            result = tradeInsureService.importSinglePolicy(file, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
