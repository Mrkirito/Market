package com.hangjia.bxj.mvc.controller.report;

import com.hangjia.bxj.dao.AdminReportMapper;
import com.hangjia.bxj.model.export.BbappData;
import com.hangjia.bxj.model.export.BbwData;
import com.hangjia.bxj.model.export.BxjappData;
import com.hangjia.bxj.model.export.HjappData;
import com.hangjia.bxj.model.report.UserDataReport;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.service.export.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.znb.cms.util.ExcelUtils.encodeFilename;

/**
 * 导出统计数据导Excel表中
 * Created by kirito on 2017/7/25.
 */
@Controller
@RequestMapping("/export")
public class ExportExcelController<T> {

    @Autowired
    private ExportService exportService;

    @Autowired
    private AdminReportMapper adminReportMapper;

    @RequestMapping("/statistics.json")
    public String ExportExcel(HttpServletRequest request, HttpServletResponse response) {

        Object[][] o = new Object[14][8];
        OutputStream out = null;
        try {
            //先查询今日是否录入了数据(今日录入的是昨日的数据)
            UserDataReport userDataReport = adminReportMapper.selsectDataOfYesterday();
            if (userDataReport == null) {
                System.out.println("您没有录入昨日的数据！");
            } else {
                //将统计信息插入相应的表中
                adminReportMapper.insertDataToBbw();
                adminReportMapper.insertDataToBbapp();
                adminReportMapper.insertDataToBxjapp();
                adminReportMapper.insertDataToHjapp();
            }
            // 设置第一大行第一列的数据
            o[0][0] = "pv";
            o[1][0] = "pv";
            o[2][0] = "pv";
            o[3][0] = "pv";
            // 设置第一大行第二列的数据
            o[0][1] = "数值";
            o[1][1] = "环比";
            o[2][1] = "周同比";
            o[3][1] = "月同比";
            // 获取第一大行的第三列数据
            List<BbwData> list1 = adminReportMapper.queryBbwData();
            BbwData bbwData = list1.get(list1.size() - 1);
            o[0][2] = bbwData.getBbw_new();
            o[1][2] = bbwData.getBbwPvLrr();
            o[2][2] = bbwData.getBbwPvWeek();
            o[3][2] = bbwData.getBbwPvMonth();

            // 获取第一大行的第四列数据
            List<BbappData> list2 = adminReportMapper.queryBbappData();
            BbappData bbappData = list2.get(list2.size() - 1);
            o[0][3] = bbappData.getBbapp_new();
            o[1][3] = bbappData.getBbappPvLrr();
            o[2][3] = bbappData.getBbappPvWeek();
            o[3][3] = bbappData.getBbappPvMonth();
            // 获取第一大行的第五列数据
            List<BxjappData> list3 = adminReportMapper.queryBxjappData();
            BxjappData bxjappData = list3.get(list3.size() - 1);
            o[0][4] = bxjappData.getNew_num();
            o[1][4] = bxjappData.getBxjappPvLrr();
            o[2][4] = bxjappData.getBxjappPvWeek();
            o[3][4] = bxjappData.getBxjappPvMonth();
            // 获取第一大行的第六列数据
            List<HjappData> list4 = adminReportMapper.queryHjappData();
            HjappData hjappData = list4.get(list4.size() - 1);
            o[0][5] = hjappData.getHjapp_new();
            o[1][5] = hjappData.getHjappPvLrr();
            o[2][5] = hjappData.getHjappPvWeek();
            o[3][5] = hjappData.getHjappPvMonth();
            // 第七列和第八列没有数据时默认设置为空
            // 设置第二大行第一列的数据
            o[4][0] = "uv";
            o[5][0] = "uv";
            o[6][0] = "uv";
            o[7][0] = "uv";
            // 设置第二大行第二列的数据
            o[4][1] = "数值";
            o[5][1] = "环比";
            o[6][1] = "周同比";
            o[7][1] = "月同比";
            // 获取第二大行的第三列数据
            o[4][2] = bbwData.getBbw_start();
            o[5][2] = bbwData.getBbwUvLrr();
            o[6][2] = bbwData.getBbwUvWeek();
            o[7][2] = bbwData.getBbwUvMonth();
            // 获取第二大行的第四列数据
            o[4][3] = bbappData.getBbapp_start();
            o[5][3] = bbappData.getBbappUvLrr();
            o[6][3] = bbappData.getBbappUvWeek();
            o[7][3] = bbappData.getBbappUvMonth();
            // 获取第二大行的第五列数据
            o[4][4] = bxjappData.getActive_num();
            o[5][4] = bxjappData.getBxjappUvLrr();
            o[6][4] = bxjappData.getBxjappUvWeek();
            o[7][4] = bxjappData.getBxjappUvMonth();
            // 获取第二大行的第六列数据
            o[4][5] = hjappData.getHjapp_start();
            o[5][5] = hjappData.getHjappUvLrr();
            o[6][5] = hjappData.getHjappUvWeek();
            o[7][5] = hjappData.getHjappUvMonth();
            // 第七列和第八列没有数据时默认设置为空
            // 设置第三大行第一列的数据
            o[8][0] = "新增用户";
            o[9][0] = "新增用户";
            o[10][0] = "新增用户";
            o[11][0] = "新增用户";
            // 设置第三大行第二列的数据
            o[8][1] = "数值";
            o[9][1] = "环比";
            o[10][1] = "周同比";
            o[11][1] = "月同比";
            // 获取第三大行的第三列数据
            o[8][2] = bbwData.getTimes_num();
            o[9][2] = bbwData.getBbwTimesLrr();
            o[10][2] = bbwData.getBbwTimesWeek();
            o[11][2] = bbwData.getBbwTimesMonth();
            // 获取第三大行的第四列数据
            o[8][3] = bbappData.getTimes_num();
            o[9][3] = bbappData.getBbappTimesLrr();
            o[10][3] = bbappData.getBbappTimesWeek();
            o[11][3] = bbappData.getBbappTimesMonth();

            // 获取第三大行的第五列数据
            o[8][4] = bxjappData.getTimes_num();
            o[9][4] = bxjappData.getBxjappTimesLrr();
            o[10][4] = bxjappData.getBxjappTimesWeek();
            o[11][4] = bxjappData.getBxjappTimesMonth();
            // 获取第三大行的第六列数据
            o[8][5] = hjappData.getTimes_num();
            o[9][5] = hjappData.getHjappTimesLrr();
            o[10][5] = hjappData.getHjappTimesWeek();
            o[11][5] = hjappData.getHjappTimesMonth();
            // 第七列和第八列没有数据时默认设置为空
            // 设置第四大行的数据
            //第一列
            o[12][0] = "短信数量";
            //第二列
            o[12][1] = "数值";
            //第三列
            o[12][2] = bbwData.getBbw_message();
            //第四列
            o[12][3] = bbappData.getBbapp_message();
            //第五列
            o[12][4] = bxjappData.getBxjapp_message();
            //第六列
            o[12][5] = hjappData.getHjapp_message();
            // 设置第五大行的数据
            //第一列
            o[13][0] = "销售额";
            //第二列
            o[13][1] = "数值";
            //第三列
            o[13][2] = bbwData.getBbw_sales_total();
            //第四列
            o[13][3] = bbappData.getBbapp_sales_total();
            //第五列
            o[13][4] = bxjappData.getBxjapp_sales_total();
            //第六列
            o[13][5] = hjappData.getHjapp_sales_total();
            // 第七列和第八列没有数据时默认设置为空

            //数值表格数据统计时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date(System.currentTimeMillis()));
            int i = (int) (Math.random() * 100);
            currentDate = currentDate + "_" + i;
            String[] headers = {currentDate, "属性", "保保网-网站", "保保网-APP", "保险家-APP", "行家保险-APP", "智能保-总计", "新概念-公众号"};
            long timestart = System.currentTimeMillis();
            String fileName = DateUtils.formatSdf8(new Date()) + "_" + Math.random() * 100;
            fileName = encodeFilename(fileName);//处理中文文件名
            String filePath = "C:/";
            String fileUrl = filePath + fileName + ".xlsx";
            request.getSession().setAttribute("fileUrl", fileUrl);
            out = new FileOutputStream(fileUrl);
            exportService.exportExcel(headers, o, out, response);
            long timeend = System.currentTimeMillis();
            System.out.println("日常数据统计 >>>>> 导出耗时: >>>>>>>>>>> " + (timeend - timestart) + "ms");
            System.out.println("excel导出成功！");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return "report/userDataBbReport";
    }
}



