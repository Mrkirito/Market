package com.hangjia.bxj.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.baobao.framework.support.utility.StringUtils;
import com.hangjia.bxj.util.AccessToken;
import com.hangjia.bxj.util.HjbwxUtil;
import com.hangjia.bxj.util.JsonResponseUtil;
import com.hangjia.bxj.util.Sign;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.sword.wechat4j.token.TokenProxy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 保保网络科技-bxj
 * com.hangjia.bxj.mvc.controller
 * 作者-秦岭(Tain)
 * 说明：
 * 2016/4/26 21:15
 * 2016保保网络-版权所有
 */
@Controller
@RequestMapping("wxshare")
public class WechatController {
    private static Logger logger = Logger.getLogger(WechatController.class.getName());

    @RequestMapping("/sign.do")
    @ResponseBody
    public String sign(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("------------进入微信分享url签名页面-------------");
        long start_time = System.currentTimeMillis();

        boolean isSuccess = true;
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            String shareType = request.getParameter("share_type");//1APP，2微信
            logger.info("-------------shareType: " + shareType);

            String url = request.getParameter("url");
            logger.info("-------------url111: " + url);
            resultMap.put("url", url);
            /**
             * 替换URL中的IP地址为域名地址
             */

            url = "http://sit.hangjiabao.com";

            logger.info("---------签名url: " + url);

            if("1".equals(shareType)) {
                logger.info("-------------APP分享，无需微信签名-----------");
            } else {
                /**
                 * 匹配后台分享配置， 判断该分享是否配置了分享详细
                 */
                String jsapi_ticket = TokenProxy.jsApiTicket();
                logger.info("-----------中控器中的jsapi_ticket: " + jsapi_ticket);
                if (StringUtils.isBlank(jsapi_ticket)) {
                    logger.info("===========未能获取到ticket=========");
                    JSONObject json = HjbwxUtil.getJSApiTicket();
                    logger.info("---------json: " + json);
                    jsapi_ticket = json.getString("ticket");
                }

                logger.info("---------签名jsTicket: " + jsapi_ticket);
                resultMap.putAll(Sign.sign(jsapi_ticket, url));

                String sourceId = request.getParameter("source_id");
                String appId = "wx6acb875ac551a4d2";

                resultMap.put("appId", appId);
            }

            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                logger.info("-------" + entry.getKey() + "\t" + entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("---------获取数据失败：" + e.toString());
            isSuccess = false;
            resultMap.put("errormsg", "分享失败！");
        }
        printMessage(request, response, resultMap, isSuccess);

        return null;
    }

    public static String getJSApiTicketFromWeiXin(String access_token, String isProdection) {
        String retnStr = HttpGetURL(GET_TICKET_URL.replace("ACCESS_TOKEN", access_token), isProdection);
        JSONObject getJsTicketJson = JSONObject.parseObject(retnStr);

        return getJsTicketJson.getString("ticket");
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

    /**获取access_token*/
    private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

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

    public static String HttpGetURL(String url, String isProdection) {
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

    /**获取ticket*/
    private static String GET_TICKET_URL = "http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN";


    private void printMessage(HttpServletRequest request, HttpServletResponse response, Map<String, String> result, boolean isSuccess) {
        response.setHeader("Connection", "Close");
        Map resultMap = new HashMap();
        resultMap.put("isSuccess", isSuccess);
        resultMap.putAll(result);

        String json = JSONObject.toJSONString(resultMap).toString();
        String callBack = request.getParameter("callback");

        new JsonResponseUtil().write(response, callBack + "(" + json + ")");
    }

}
