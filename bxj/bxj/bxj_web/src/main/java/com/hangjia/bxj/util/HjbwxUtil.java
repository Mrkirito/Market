package com.hangjia.bxj.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.sword.wechat4j.token.TokenProxy;

//import com.ymatou.entity.JsApiTicket;
//import com.ymatou.entity.Token;
//import com.ymatou.entity.WxYmatouUser;

public class HjbwxUtil {

	private static Logger logger = LoggerFactory.getLogger(HjbwxUtil.class);
	
	/**获取ticket*/
	private static String GET_TICKET_URL = "http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN";
	
	/**获取access_token*/
	private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**获取refresh_token*/
	private static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	
	/** 客服接口调用地址*/
	private static String CUST_HELP_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	/** 二维码请求*/
	private static String QR_SENCE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	/** 获取用户信息*/
	private static String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/** 获取帐号的关注者列表*/
	private static String LIST_OPEINID_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	/** 创建分组*/
	private static String CREATE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	
	/** 查询所有分组*/
	private static String QUERY_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	
	/** 查询用户所在分组*/
	private static String QUERY_OPEINID_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	
	/** 修改分组名*/
	private static String UPDATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	
	/** 移动用户分组*/
	private static String MOVE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	
	/** 通过code换取网页授权access_token*/
	private static String OAUTH2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
//	/** 刷新access_token*/
//	private static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	/** 拉取用户信息*/
	private static String SNSAPI_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/** 模板消息接口调用地址*/
	private static String TEMP_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	/** 查询自定义菜单接口调用地址*/
	private static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	/** 设置自定义菜单接口调用地址*/
	private static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/** 获取客服消息接口调用地址*/
	private static String GET_RECORD_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getrecord?access_token=ACCESS_TOKEN";
	
	/** 是否生产环境*/
//	private String isProduction = ResponseHelper.getResponse("isProduction"); //1非生产  0生产
	

	

	
	/**
	 * 从微信官方获取token，http协议 GET请求，
	 * @return 请求成功{"access_token":"xxxxxxxxxx","expires_in":7200}
	 * @author Liu zhilai
	 * Nov 11, 2014
	 * String
	 */
	public static String getTokenFromWeiXin() {  
        
		String appid= "";
		String secret= "";
		System.out.println("-----appid:" + appid);
		System.out.println("-----secret:" + secret);
		String retnStr = HttpGetURL(ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", secret), "");
		JSONObject getTokenJson = JSONObject.parseObject(retnStr);
		
        return getTokenJson.getString("access_token");  
    } 
	
	public static JSONObject getTokenStrFromWeiXin() {  
		logger.info("***********请求微信接口，获取token***********");
		String appid= "";
		String secret= "";
		logger.info("***********appid:" + appid);
		logger.info("***********secret:" + secret);
		String retnStr = HttpGetURL(ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", secret), "");
		logger.info("***********请求微信接口获取token，返回报文:" + retnStr);
		JSONObject tokenJson = JSONObject.parseObject(retnStr);
		/**
		 * 如果请求失败，重新请求一次
		 */
		if(tokenJson.containsKey("errcode")) {
			logger.info("***********请求token失败，再次请求微信接口**************");
			retnStr = HttpGetURL(ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", secret), "");
			tokenJson = JSONObject.parseObject(retnStr);
			logger.info("***********重新请求微信接口获取token，返回报文:" + retnStr);
		}
		
		return tokenJson;
    }
	
	public static JSONObject getJSApiTicket() { 
		
		String access_token = TokenProxy.accessToken();
		logger.info("*************access_token: " + access_token);
		String retnStr = HttpGetURL(GET_TICKET_URL.replace("ACCESS_TOKEN", access_token), "");
		JSONObject jsTicketJson = JSONObject.parseObject(retnStr);
		
		if(!jsTicketJson.containsKey("ticket") || StringUtils.isBlank(jsTicketJson.getString("ticket"))) {
			access_token = AccessToken.getNewOne().getTokeString();
			retnStr = HttpGetURL(GET_TICKET_URL.replace("ACCESS_TOKEN", access_token), "");
			jsTicketJson = JSONObject.parseObject(retnStr);
		}
		
        return jsTicketJson;
    } 
	
	/**
	 * 从微信官方获取调用JSAPI 的ticket，
	 * @param access_token
	 * @param isProdection
	 * @return
	 * @author Liu zhilai
	 * Jan 14, 2015
	 * String
	 */
	public static String getJSApiTicketFromWeiXin(String access_token, String isProdection) { 
		String retnStr = HttpGetURL(GET_TICKET_URL.replace("ACCESS_TOKEN", access_token), isProdection);
		JSONObject getJsTicketJson = JSONObject.parseObject(retnStr);
		
        return getJsTicketJson.getString("ticket");
    } 



	/**
	 * HTTP get请求
	 * @param url 请求地址
	 * @param isProdection 判断是什么环境，测试环境使用代理，生产环境不需要
	 * @return
	 * @author Liu zhilai
	 * Jan 12, 2015
	 * String
	 */
	public static String HttpGetURL(String url, String isProdection) {  
        
//        HttpClient client = new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
		StringBuffer buffer = new StringBuffer();
		String returnStr = "";
        try {
            HttpResponse response = client.execute(httpGet);  
            
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                HttpEntity entity = response.getEntity();  
    			if (entity != null) {
    				InputStreamReader inputStreamReader = new InputStreamReader(entity.getContent(), "UTF-8");
    				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    				String str = null;
    				while ((str = bufferedReader.readLine()) != null) {
    					buffer.append(str);
    				}
    				bufferedReader.close();
    				inputStreamReader.close();
    				returnStr = buffer.toString();
    			}
                
//    			EntityUtils.consume(entity);
            } else {
            	System.out.println("Method failed:"+response.getStatusLine());
            }
            
        } catch (Exception e) {  
            throw new RuntimeException(e);  
              
        } finally{  
            //关闭连接 ,释放资源  
            client.getConnectionManager().shutdown();  
        }  
        return returnStr;  
    } 
	
	
	/**
	 * 获取签名MAP，
	 * map中保存url，jsapi_ticket，nonceStr，timestamp，signature
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 * @author Liu zhilai
	 * Jan 14, 2015
	 * Map<String,String>
	 */
	public static Map<String, String> getSignMap(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
	

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    

	
	public static boolean isTeshu(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		System.out.println(m.find());
		return m.find();
	}
	
	public static boolean isRight(String str) {
		String regEx = "[\u4e00-\u9fa5a-zA-z0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		System.out.println(m.find());
		return m.find();
	}
	
	public static boolean isContainChinese(String str) {

		
		String patternStr="^[\\w\\u4e00-\\u9fa5]*$";//表达式
		
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
		
        return matcher.matches();
    }
	
	public static int getRandomIndex(int size) {

		Random rd = new Random();
		// ranNum的值的范围=[0, list.size())
		int index = rd.nextInt(size);
		;

		return index;
	}

	
}
