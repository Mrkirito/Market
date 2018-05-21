package com.hangjia.bxj.service.free;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hangjia.bxj.BXJException;

public class FreeInsuranceClient {
	
	private String freeServiceURL;
	
	private static final Log log = LogFactory.getLog(FreeInsuranceClient.class);
	
	public void setFreeServiceURL(String freeServiceURL) {
		this.freeServiceURL = freeServiceURL;
	}

	public void receive(ReceiverArgs args) {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		StringBuilder sb = new StringBuilder();
		sb.append("real_name=").append(args.getRealName())
		.append("&product_id=").append(args.getPid())
		.append("&mobile_code=").append(args.getMobile())
		.append("&identity_card=").append(args.getIdCardCode())
		.append("&source_id=").append(args.getSourceId());
//		List<NameValuePair> params = new LinkedList<NameValuePair>();
//		
//		params.add(new BasicNameValuePair("product_id", args.getPid()));
//		
//		params.add(new BasicNameValuePair("real_name", args.getRealName()));
//		
//		params.add(new BasicNameValuePair("mobile_code", args.getMobile()));
//		
//		params.add(new BasicNameValuePair("identity_card", args.getIdCardCode()));
//		
//		params.add(new BasicNameValuePair("source_id", args.getSourceId()));
//		
//		System.out.println(args.getRealName());
//		System.out.println("================");
		try {
			
//			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
			
//			HttpEntity entity = new StringEntity(sb.toString(), "UTF-8");
			
			String queryString = sb.toString();
			if (log.isInfoEnabled()) {
				log.info("赠险领取接口地址：" + freeServiceURL);
				log.info("查询字符串：" + queryString);
			}
		
			HttpGet httpGet = new HttpGet(freeServiceURL + "?" + queryString);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			
			try {
				
				validStatus(response);
				
				HttpEntity responseEntity = response.getEntity();
				
				String responstText = EntityUtils.toString(responseEntity, "UTF-8");
				
				if (log.isInfoEnabled()) {
					log.info("接口返回：" + responstText);
				}
				
				JSONObject json = JSON.parseObject(responstText);
				
				boolean succ = json.getBoolean("isSucc");
				if (!succ) {
					throw new BXJException(json.getString("errorMsg"));
				}
				
			} finally {
				response.close();
			}
			
		} catch (IOException e) {
			throw new RuntimeException("领取赠险接口调用异常：", e);
		} finally {
			try {
				httpClient.close();
			} catch (Exception e) {
			}
		}
	}

	private void validStatus(CloseableHttpResponse response) {
		StatusLine status = response.getStatusLine();
		int statusCode = status.getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			// 失败状态就是一个错误页面，没什么有用信息，就不记录了
			throw new IllegalStateException("http 请求状态码：" + statusCode);
		}
	}
	
}
