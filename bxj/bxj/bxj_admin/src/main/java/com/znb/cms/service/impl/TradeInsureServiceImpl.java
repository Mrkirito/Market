package com.znb.cms.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.core.cms.dao.mapper.TCSourceMapper;
import com.core.cms.model.mapper.TCSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.cms.common.AjaxResult;
import com.core.cms.dao.mapper.TradeInsureMapper;
import com.core.cms.model.dto.TradeInsureDto;
import com.core.cms.model.mapper.PolicyMail;
import com.core.cms.model.mapper.TradeInsure;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.znb.cms.factory.QueueInitFactory;
import com.znb.cms.service.ITradeInsureService;

@Service
public class TradeInsureServiceImpl implements ITradeInsureService {
	
	@Value("${upload_pdf_core_url}")
	private String uploadPdfUrl;
	
	@Autowired
	private TradeInsureMapper tradeInsureMapper;

	@Autowired
	private TCSourceMapper tcSourceMapper;
	
	@Override
	public int selectCount(TradeInsure tradeInsure) {
		return tradeInsureMapper.selectCount(tradeInsure);
	}

	@Override
	public List<TradeInsure> getTradeInsureList(TradeInsure tradeInsure) {
		return tradeInsureMapper.getTradeInsureList(tradeInsure);
		
	}

	@Override
	public TradeInsureDto getTradeInsureDtoByOrderId(String tradeId) {
		return tradeInsureMapper.getTradeInsureDto(tradeId);
		
	}

	@Override
	public int updateTradeInsure(TradeInsureDto tradeInsureDto) throws ParseException {
		TradeInsure tradeInsure = new TradeInsure();
		tradeInsure.setStatusId(tradeInsureDto.getStatusId());
		tradeInsure.setRefuseReason(tradeInsureDto.getRefuseReason());
		tradeInsure.setInsureBeginDate(tradeInsureDto.getInsureBeginDate());
		tradeInsure.setInsureEndDate(tradeInsureDto.getInsureEndDate());
		tradeInsure.setInsuranceNum(tradeInsureDto.getInsuranceNum());
		tradeInsure.setFid(tradeInsureDto.getFid());
		return tradeInsureMapper.updateByPrimaryKeySelective(tradeInsure);
	}

	@Override
	public int delTradeInsureByFid(Integer Fid) {
		TradeInsure tradeInsure = new TradeInsure();
		tradeInsure.setStatusId(0);
		tradeInsure.setFid(Fid);
		return tradeInsureMapper.updateByPrimaryKeySelective(tradeInsure);
		
	}

	@Override
	public AjaxResult importSinglePolicy(MultipartFile file, Integer Fid) throws Exception {
		PdfReader reader = new PdfReader(file.getBytes());  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        PdfStamper ps = new PdfStamper(reader, bos);
        ps.close();
        File files = new File(uploadPdfUrl+Fid+"/");
        if(!files.exists()){  
        	files.mkdirs();  
        }  
        FileOutputStream fos = new FileOutputStream(files+"/policy.pdf");  
        fos.write(bos.toByteArray());  
        fos.close();
        TradeInsure tradeInsure = new TradeInsure();
		tradeInsure.setInsureFileUrl(Fid+"/policy.pdf");
		tradeInsure.setFid(Fid);
		tradeInsureMapper.updateByPrimaryKeySelective(tradeInsure);
		
//		if(file.getBytes().length > 0){
//			PolicyMail policyMail = tradeInsureMapper.getIsurentMail(Fid);
//			//组装发送保单邮件信息
//			policyMail.setBytePolicy(file.getBytes());
//			QueueInitFactory.queueMail.put(policyMail);
//		}
		return new AjaxResult.success("sucess");
	}

	@Override
	public List<TCSource> queryTCSource() {
		return tcSourceMapper.queryTCSource();
	}

	@Override
	public TradeInsureDto getTradeInsureDtoById(Integer tradeId) {
		return tradeInsureMapper.getTradeInsureDtoByFid(tradeId);
	}

}
