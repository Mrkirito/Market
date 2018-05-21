package com.hangjia.bxj.mvc.controller.report;

import com.ibaoxianjia.message.dto.MailAttachmentDto;
import com.ibaoxianjia.message.dto.MailMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Value("${sysid}")
    private String sysid;

    @Autowired
    private MessageOpenAPI messageOpenAPI;

    @RequestMapping("/sendEmail.jhtml")
    public String sendEmail(HttpServletRequest request) {
        MailMessageDto mailMessageDto = new MailMessageDto();
        mailMessageDto.setMailCode("DAY_DATA_STATISTICS");
        mailMessageDto.setSysid(sysid);
        String[] toArr = new String[]{"chenliyang@baobaogroup.com"};
//        mailMessageDto.setCc(toArr);
        mailMessageDto.setTo(toArr);
        String filePath = (String) request.getSession().getAttribute("fileUrl");
        File sendFile = new File(filePath);
        MailAttachmentDto mailAttachmentDto = new MailAttachmentDto();
        mailAttachmentDto.setAttachmentName("日常数据统计.xlsx");
        BufferedInputStream bis = null;
        List<MailAttachmentDto> list = new ArrayList<MailAttachmentDto>();
        byte[] attachmentBytes = new byte[(int) sendFile.length()];
        int len = 0;
        try {
            bis = new BufferedInputStream(new FileInputStream(sendFile));
            bis.read(attachmentBytes);
            mailAttachmentDto.setAttachmentBytes(attachmentBytes);
            list.add(mailAttachmentDto);
            mailMessageDto.setAttachments(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean msg = messageOpenAPI.sendMail(mailMessageDto);
        if (msg) {
            System.out.println("邮件发送成功，请注意查收！");
        }
        return "report/userDataReport";
    }
}
