package com.hangjia.bxj.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.http.entity.StringEntity;
import org.springframework.util.FileCopyUtils;

/**
 * 行家网
 * 通用工具类
 * @author Tain
 * @since 2015-11-04
 */
public class Utils {

	/**
	 * 获取两个时间段的时间显示
	 * 显示规则：×秒前；×分钟前；×小时前；超过1天显示×天前；超过1周显示×周前；超过1月显示×月前
	 * @return
	 */
	public static String getSecondsBetweenTimes(long startTime, long endTime) {
		long seconds = endTime - startTime;
		long minBetween = 60 ;//分级临界点（不包括）
		long houbetween = 60 * minBetween;//小时级临界点（不包括）
		long dayBetween = 24 * houbetween;//天级临界点（不包括）
		long weebetween = 7  * dayBetween;//周级临界点（不包括）
		long monbetween = 30 * dayBetween;//月级临界点（不包括）
		
		String reStr = "";
		int num=0;
		if(seconds < minBetween) {
			num = (int) seconds;
			reStr = num + "秒前";
		} else if(seconds>=minBetween &&seconds <houbetween) {
			num = (int) (seconds/minBetween);
			reStr = num + "分钟前";
		} else if(seconds>=houbetween &&seconds <dayBetween) {
			num = (int) (seconds/houbetween);
			reStr = num + "小时前";
		} else if(seconds>=dayBetween &&seconds <weebetween) {
			num = (int) (seconds/dayBetween);
			reStr = num + "天前";
		} else if(seconds>=weebetween &&seconds <monbetween) {
			num = (int) (seconds/weebetween);
			reStr = num + "周前";
		} else if(seconds>=monbetween) {
			num = (int) (seconds/monbetween);
			reStr = num + "月前";
		}
		
		return reStr;
	}
	
	/**
	 * 获取两个时间段的时间显示
	 * 显示规则：×秒前；×分钟前；×小时前；超过1天显示×天前；超过1周显示×周前；超过1月显示×月前
	 * @return
	 */
	public static String getSecondsBetweenTimes(Date startTime, Date endTime) {
		return getSecondsBetweenTimes(startTime.getTime()/1000, endTime.getTime()/1000);
	}
	


	
	
	public static Date getAllDatePart(String date) throws Exception {
		return getAllDatePart(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getDate() {
		return formateDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formateDate(Date date, String pattern) {
		return org.apache.commons.lang.time.DateFormatUtils.format(date, pattern);
	}
	
	public static Date getAllDatePart(String date, String pattern) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}
	
	public static Date getAllDatePart(Date date, String pattern) throws Exception {
		String dd = formateDate(date, pattern);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dd);
	}
	
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static String poststr() {
		String a = "<?xml version=\"1.0\" encoding=\"GBK\"?><eaiAhsXml><Header>                           "
				+"<TRAN_CODE>100001</TRAN_CODE>                                                     "
				+"<BRNO>SD123456</BRNO>                                                             "
				+"<BUSINESS_CODE>BPM00006</BUSINESS_CODE>                                           "
				+"<INSCOM_CODE>PBI00007</INSCOM_CODE>                                               "
				+"<BK_ACCT_DATE>20150823</BK_ACCT_DATE>                                             "
				+"<BK_ACCT_TIME>200000</BK_ACCT_TIME>                                               "
				+"<BK_SERIAL>BK129382891389</BK_SERIAL>                                             "
				+"<BK_TRAN_CHNL>WEB</BK_TRAN_CHNL>                                                  "
				+"<PRODUCT_CODE>CIYOU1AP1</PRODUCT_CODE>                                            "
				+"</Header>                                                                         "
				+"<Request>                                                                         "
				+"<policyInfo>                                                                      "
				+"	<AgencyPolicyRef>baopal00000000002100</AgencyPolicyRef>                         "
				+"	<ProductCode>CIYOU1AP1</ProductCode>                                            "
				+"	<IssueDate>20150926</IssueDate>                                                 "
				+"	<EffectDate>20150926</EffectDate>                                               "
				+"	<ExpiryDate>20160926</ExpiryDate>                                               "
				+"	<GroupSize>1</GroupSize>                                                        "
				+"	<ApplicantType>I</ApplicantType>                                                "
				+"	<ApplicantName>测试级</ApplicantName>                                           "
				+"	<ApplicantCertType>0</ApplicantCertType>                                        "
				+"	<ApplicantCercCode>411523199204223813</ApplicantCercCode>                       "
				+"	<ApplicantPostCd>200000</ApplicantPostCd>                                       "
				+"	<ReqFaPiao>0</ReqFaPiao>                                                        "
				+"	<ReqMail>0</ReqMail>                                                            "
				+"	<InsuredList>                                                                   "
				+"		<Insured>                                                                   "
				+"			<InsuredId>1000001</InsuredId>                                          "
				+"			<InsuredName>测试二</InsuredName>                                       "
				+"			<InsuredType>1</InsuredType>                                            "
				+"			<InsuredCertType>0</InsuredCertType>                                    "
				+"			<InsuredCercCode>411523199204223813</InsuredCercCode>                   "
				+"			<InsuredBirth>1992-04-22</InsuredBirth>                                 "
				+"			<ClaimantType>4</ClaimantType>                                          "
				+"			<ClaimantList>                                                          "
				+"				<Claimant>                                                          "
				+"					<ClaimantName>测试三</ClaimantName>                             "
				+"					<ClaimantCertType>0</ClaimantCertType>                          "
				+"					<ClaimantCercCode>230805198501037464</ClaimantCercCode>         "
				+"					<ClaimantNexus>402</ClaimantNexus>                              "
				+"					<ClaimantPercentage>30</ClaimantPercentage>                     "
				+"				</Claimant>                                                         "
				+"				<Claimant>                                                          "
				+"					<ClaimantName>测试四</ClaimantName>                             "
				+"					<ClaimantCertType>0</ClaimantCertType>                           "
				+"					<ClaimantCercCode>230805198501037464</ClaimantCercCode>          "
				+"					<ClaimantNexus>310</ClaimantNexus>                               "
				+"					<ClaimantPercentage>70</ClaimantPercentage>                      "
				+"				</Claimant>                                                          "
				+"			</ClaimantList>                                                          "
				+"			<EmergencyContactList>                                                   "
				+"					<ContactPerson>                                                  "
				+"						<ContactName>测试是</ContactName>                            "
				+"						<ContactCertType>0</ContactCertType>                         "
				+"						<ContactCercCode>230000198411280342</ContactCercCode>        "
				+"					</ContactPerson>                                                 "
				+"			</EmergencyContactList>                                                  "
				+"		</Insured>                                                                   "
				+"	</InsuredList>                                                                   "
				+"</policyInfo></Request></eaiAhsXml>                                                ";
		return a;

	}
	
	/**
     * 得到几天前的时间
     * 
     * @param d
     * @param day
     * @return
     */
    public static Date getDatePos(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
    

    /**
     * 获取POST CONTENT
     * @param request
     * @return
     * @throws Exception
     */
	public static String getHttpPostContent(ServletRequest request) throws Exception {
		return getHttpPostContent(request, "UTF-8");
	}
	 /**
     * 获取POST CONTENT
     * @param request
     * @return
     * @throws Exception
     */
	public static String getHttpPostContent(ServletRequest request, String encode) throws Exception {
		StringBuffer info = new StringBuffer("");
		InputStream in = null;
		BufferedInputStream buf = null;
		try {
			in = request.getInputStream();
			buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;
			while ((iRead = buf.read(buffer)) != -1) {
				info.append(new String(buffer, 0, iRead, encode));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(buf != null) {
				buf.close();
				buf = null;
			}
			if(in != null) {
				in.close();
				in = null;
			}
		}
		
		return info.toString();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 判断一个对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if(obj == null) return true;
		if("".equals(obj.toString())) return true;
		if("null".equalsIgnoreCase(obj.toString())) return true;
		return false;
	}
	

	/**
	 * 获取文件 支持断点传输
	 * 
	 * @param downFile
	 */
	public static void downFile(HttpServletRequest request, HttpServletResponse response, File downFile) {
		try {
			String range = null;
			// 特殊头处理
			if (null != request.getHeader("RANGE")) {// 断点续传的头
				range = request.getHeader("RANGE");
			}
			if (null != request.getHeader("Range")) {
				range = request.getHeader("Range");
			}
			response.setContentType("application/x-msdownload");
			int fileLength = Integer.parseInt(Long.toString(downFile.length()));
			response.setContentLength(fileLength);
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(downFile.getName().getBytes("gb2312"), "ISO8859-1"));// 处理默认文件名的中文问题
			int startPos = 0;
			if (null != range) {// 断点续传
				startPos = Integer.parseInt(range.replaceAll("bytes=", "").replaceAll("-$|-\\d+$", ""));
			}
			if (startPos == 0) {
				FileCopyUtils.copy(new FileInputStream(downFile), response.getOutputStream());
			} else {// 断点续传
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				if (startPos != 0) {
					/** 设置Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小] **/
					StringBuffer sb = new StringBuffer("bytes ");
					sb.append(Long.toString(startPos));
					sb.append("-");
					sb.append(Long.toString(fileLength - 1));
					sb.append("/");
					sb.append(Long.toString(fileLength));
					response.setHeader("Content-Range", sb.toString());
				}
				if (startPos < fileLength) {
					fileLength = fileLength - startPos;
					response.getOutputStream().write(FileUtils.readFileToByteArray(downFile), (int) startPos, (int) fileLength);
				}
			}
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断一个对象是不为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * 金额格式化
	 * @param s 金额
	 * @param len 小数位数
	 * @return 格式后的金额
	 */
	public static String insertComma(String s, int len) {
	    if (s == null || s.length() < 1) {
	        return "";
	    }
	    NumberFormat formater = null;
	    double num = Double.parseDouble(s);
	    if (len == 0) {
	        formater = new DecimalFormat("###,###");
	    } else {
	        StringBuffer buff = new StringBuffer();
	        buff.append("###,###.");
	        for (int i = 0; i < len; i++) {
	            buff.append("#");
	        }
	        formater = new DecimalFormat(buff.toString());
	    }
	    StringBuilder rr = new StringBuilder(formater.format(num));
	    String[] sp = rr.toString().split("[.]");
	    if(sp.length == 1) {
	    	rr.append(".");
	    	for(int i=0; i<len; i++) {
	    		rr.append("0");
	    	}
	    } else {
	    	String d = sp[1];
	    	char[] dA = d.toCharArray();
	    	int ii = len-dA.length;
	    	for(int i=0; i<ii; i++) {
	    		rr.append("0");
	    	}
	    }
	    return rr.toString();
	}
	
	 /**
     * 获取配置信息
     */
    public  String getProperties(String sign){
        InputStream in = getClass().getResourceAsStream("/urlPath.properties"); 
        Properties prop = new Properties(); 
        String properties="";
        try {
            prop.load(in);
            properties = prop.getProperty(sign).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null!=in)
                {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
        return properties;
    }
    
    
	
	
	public static void main(String[] args) {
		Utils ut = new Utils();
//		System.out.println(Utils.insertComma("42323654", 4));
		System.out.println(ut.getProperties("saveVoiceUrl"));
		
		
//		long s = 0;
//		long e = 60*60*24*7*10 ;
//		System.out.println(getSecondsBetweenTimes(s,e));
//		
//		System.out.println((new Date()).getTime()/1000);
//		System.out.println(System.currentTimeMillis());
//		try {
//			System.out.println(getAllDatePart("2015-10-10 22:10:00"));
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String retStr ="<?xml version=\"1.0\" encoding=\"GBK\"?><eaiAhsXml><Header><TRAN_CODE>100001</TRAN_CODE><BUSINESS_CODE>BPM00006</BUSINESS_CODE><INSCOM_CODE>PBI00007</INSCOM_CODE><PRODUCT_CODE>ABTADP1</PRODUCT_CODE><BK_ACCT_DATE>2015-08-19</BK_ACCT_DATE><BK_ACCT_TIME>130813</BK_ACCT_TIME><BK_SERIAL>96208053632525815</BK_SERIAL><PA_RSLT_CODE>LENM</PA_RSLT_CODE><PA_RSLT_MESG>ApplicantType字段长度超出</PA_RSLT_MESG><FT_SERIAL>2004806145fEvHOD</FT_SERIAL></Header><Response><policyInfo></policyInfo></Response></eaiAhsXml>";

//		String retStr = poststr();
//		System.out.println(retStr.replaceAll("[ ]", "").replaceAll("[	]", ""));
//		System.out.println(Utils.HttpPostURL("http://118.126.4.74:7001/bpal/bpalExternalService", retStr));
//		try {
//			System.out.println(getAllDatePart("2015-08-10 12:11:00", "yyyy-MM-dd"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(getDatePos(new Date(), -1));
//		String content = "尊敬的${data_1}:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好！您的保险【${data_2}】保单号【${data_3}】现已承保，具体内容详见附件。<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢您对行家保险的关心与支持！<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如出现附件不能正常在线预览，请您先下载！";
//		
//		content = content.replaceAll("data_1", "sd")
//				.replaceAll("data_2", "sdf")
//				.replaceAll("data_3", "sdfqw");
//		content = content.replaceAll("[$]", "").replaceAll("[{]", "").replaceAll("[}]", "");
//		System.out.println(content);
	}
	
	
}
