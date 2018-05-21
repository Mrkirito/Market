package com.hangjia.bxj.mvc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class DateUtils {
	
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	static final String formatPattern = "yyyy-MM-dd";
	
    static final String formatPattern_Short = "yyyyMMdd";
	
	static final String formatPattern_Long = "yyyyMMddHHmmss";

    private static final String sdf1reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

    private static final SimpleDateFormat sdf1    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String sdf2reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2}$";

    private static final SimpleDateFormat sdf2    = new SimpleDateFormat("yyyy-MM-dd");

    private static final String sdf3reg = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

    private static final SimpleDateFormat sdf3    = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static final String sdf4reg = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2}$";

    private static final SimpleDateFormat sdf4    = new SimpleDateFormat("yyyy/MM/dd");

    private static final String sdf5reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}$";

    private static final SimpleDateFormat sdf5    = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static final String sdf6reg = "^\\d{2,4}\\d{1,2}\\d{1,2}$";

    private static final SimpleDateFormat sdf6    = new SimpleDateFormat("yyyyMMdd");

    private static final String sdf7reg = "^\\d{2,4}\\d{1,2}\\d{1,2} \\d{1,2}\\d{1,2}\\d{1,2}$";

    private static final SimpleDateFormat sdf7    = new SimpleDateFormat("yyyyMMdd HHmmss");

    private static final SimpleDateFormat sdf8    = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat sdf9    = new SimpleDateFormat("yyMMdd");

    /**
     * <p/> 将日期字符串解析成日期对象，支持一下格式
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>yyyy-MM-dd
     * <li>yyyy/MM/dd HH:mm:ss
     * <li>yyyy/MM/dd
     * <li>yyyy-MM-dd HH:mm
     * <li>yyyyMMdd
     * <li>yyyyMMdd HHmmss
     * </p>
     *
     * @param str
     * @return
     */
    public static Date parse(String str) {
        Date date = null;
        Pattern p1 = Pattern.compile(sdf1reg);
        Matcher m1 = p1.matcher(str);
        Pattern p2 = Pattern.compile(sdf2reg);
        Matcher m2 = p2.matcher(str);
        Pattern p3 = Pattern.compile(sdf3reg);
        Matcher m3 = p3.matcher(str);
        Pattern p4 = Pattern.compile(sdf4reg);
        Matcher m4 = p4.matcher(str);
        Pattern p5 = Pattern.compile(sdf5reg);
        Matcher m5 = p5.matcher(str);
        Pattern p6 = Pattern.compile(sdf6reg);
        Matcher m6 = p6.matcher(str);
        Pattern p7 = Pattern.compile(sdf7reg);
        Matcher m7 = p7.matcher(str);
        try {
            if (m1.matches()) {
                date = sdf1.parse(str);
            } else if (m2.matches()) {
                date = sdf2.parse(str);
            } else if (m3.matches()) {
                date = sdf3.parse(str);
            } else if (m4.matches()) {
                date = sdf4.parse(str);
            } else if (m5.matches()) {
                date = sdf5.parse(str);
            } else if (m6.matches()) {
                date = sdf6.parse(str);
            } else if (m7.matches()) {
                date = sdf7.parse(str);
            } else {
                date = new Date(str);
            }
        } catch (Exception e) {
            throw new RuntimeException("非法日期字符串，解析失败：" + str, e);
        }
        return date;
    }

    public static Map<String, String> getDateFormatMap() {
        Map<String, String> dateMap_ = new HashMap<String, String>();
        dateMap_.put("0", "yyyy-MM-dd");
        dateMap_.put("1", "yyyy年MM月dd日");
        dateMap_.put("2", "yyyy年MM月");
        dateMap_.put("3", "MM月dd日");
        dateMap_.put("7", "yyyy-MM-dd");
        dateMap_.put("8", "EEE");

        return dateMap_;
    }

    public static Map<String, String> getTimeFormatMap() {
        Map<String, String> timeMap_ = new HashMap<String, String>();
        timeMap_.put("0", "HH:mm:ss");
        timeMap_.put("1", "HH:mm:ss");
        timeMap_.put("2", "HH:mm");
        timeMap_.put("3", "h:m a");
        timeMap_.put("4", "h:m:s a");
        timeMap_.put("5", "HH时mm分ss秒");
        timeMap_.put("6", "HH时mm分");
        timeMap_.put("7", "a h时m分");
        timeMap_.put("8", "a h时m分s秒");
        return timeMap_;
    }

    /**
     * <p/> 将日期格式化成字符串：yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param date
     * @return
     */
    public static String formatSdf1(Date date) {

        if (date == null) {

            return "";

        }

        return sdf1.format(date);
    }

    /**
     * 将 yyyyMMdd HHmmss日期格式字符串，格式化为yyyy-MM-dd HH:mm:ss
     * @param str
     * @return
     */
    public static String formatSdf1(String str) {

        if (StringUtils.isBlank(str)) {

            return "";
        }

        return formatSdf1(parse(str));

    }

    /**
     * 将 yyyyMMdd HHmmss日期格式字符串，格式化为yyyy-MM-dd HH:mm:ss
     * <pre>
     * DateUtil.formatSdf1(null,null)      = ""
     * DateUtil.formatSdf1("","")        = ""
     * DateUtil.formatSdf1("20110215","")       = ""
     * DateUtil.formatSdf1("","143445")       = ""
     * DateUtil.formatSdf1(" "," ")     = ""
     * DateUtil.formatSdf1("20110215"," ") = ""
     * DateUtil.formatSdf1(" ","143445") = ""
     * DateUtil.formatSdf1("20110215","143445") = "2011-02-15 14:34:45"
     * </pre>
     * @param strDate  日期字符串
     * @param strTime  时间字符串
     * @return
     */
    public static String formatSdf1(String strDate, String strTime) {

        if (StringUtils.isBlank(strDate) || StringUtils.isBlank(strTime) || strTime.length() < 1
                || strTime.length() > 6) {

            return "";
        }

        StringBuffer strBuffer = new StringBuffer();

        strBuffer.append(strDate).append(" ");

        if (strTime.length() == 1) {

            strBuffer.append("00000").append(strTime);

        } else if (strTime.length() == 2) {

            strBuffer.append("0000").append(strTime);

        } else if (strTime.length() == 3) {

            strBuffer.append("000").append(strTime);

        } else if (strTime.length() == 4) {

            strBuffer.append("00").append(strTime);

        } else if (strTime.length() == 5) {

            strBuffer.append("0").append(strTime);

        } else if (strTime.length() == 6) {

            strBuffer.append(strTime);
        }

        return formatSdf1(parse(strBuffer.toString()));

    }

    /**
     * <p/> 将日期格式化成字符串：yyyy-MM-dd
     * </p>
     *
     * @param date
     * @return
     */
    public static String formatSdf2(Date date) {
        return sdf2.format(date);
    }
    /**
     * 将日期格式化成字符串：yyyyMMdd
     *
     * @param date 参数为空默认取当前时间
     * @return
     */
    public static String formatSdf6(Date date) {
        if(null == date)
            return sdf6.format(new Date());
        return sdf6.format(date);
    }
    /**
     * 将日期格式化成字符串：yyMMdd
     *
     * @param date 参数为空默认取当前时间
     * @return
     */
    public static String formatSdf9(Date date) {
        if(null == date)
            return sdf9.format(new Date());
        return sdf9.format(date);
    }

    /**
     * <p/> 将日期格式化成字符串：yyyyMMddHHssMM
     * </p>
     *
     * @param date
     * @return
     */
    public static String formatSdf8(Date date) {
        return sdf8.format(date);
    }

    /**
     * <p/>
     *  将yyyyMMdd日期字符串，格式化成字符串：yyyy-MM-dd
     * </p>
     *
     * @param dateStr
     * @return
     */
    public static String formatSdf2(String dateStr) {

        if (StringUtils.isBlank(dateStr)) {

            return "";
        }

        return sdf2.format(parse(dateStr));

    }

    /**
     * <p/> 将日期格式化成相应格式的字符串，如：
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>yyyy-MM-dd
     * <li>yyyy/MM/dd HH:mm:ss
     * <li>yyyy/MM/dd
     * </p>
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null)
            return "";
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 取得字符串日期中的年
     *
     * @param date
     * @return year
     */
    public static int getYear(String date) {
        try {
            return getYear(sdf2.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException("日期转换出错", e);
        }
    }

    /**
     * 取得日期对象中的年
     *
     * @param date
     * @return year
     */
    public static int getYear(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 取得日期对象中的月
     *
     * @param date
     * @return month
     */
    public static int getMonth(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 取得字符串日期中的月
     *
     * @param date
     * @return month
     */
    public static int getMonth(String date) {
        try {
            return getMonth(sdf2.parse(date));
        } catch (Exception e) {
            throw new RuntimeException("日期转换出错", e);
        }
    }

    /**
     * 取得日期对象中的日
     *
     * @param date
     * @return date
     */
    public static int getDate(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 取得字符串日期中的日
     *
     * @param date
     * @return date
     */
    public static int getDate(String date) {
        try {
            return getDate(sdf2.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException("日期转换出错", e);
        }
    }

    /**
     * 取得日期格式中的小时。24小时制 add by zsc 2007-11-9
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 取得日期格式中的分钟。60小时制 add by ryl 2007-11-9
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 取得日期格式中的分钟。60小时制 add by ryl 2007-11-9
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        if (date == null)
            return 0;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 取得字符串日期中的小时。24小时制 add by zsc 2007-11-9
     *
     * @param date
     * @return
     */
    public static int getHour(String date) {
        try {
            return getHour(sdf1.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException("日期转换出错", e);
        }
    }

    /**
     * 取得Calendar实例
     *
     * @return
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 根据日期偏移天数取得日期。offset > 0 ,往后延迟offset天， offset < 0 向前推进 offset天
     *
     * @param date:基日期
     * @param offset:日期天数偏移量
     * @return
     */
    public static Date getDate(Date date, int offset) {
        if (date == null)
            return date;
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    public static String dealYearForChinese(String date_) {
        StringBuffer sb = new StringBuffer();
        String[] dateArray = date_.split("");
        for (String s1 : dateArray) {
            if ("1".equals(s1)) {
                sb.append("一");
            } else if ("2".equals(s1)) {
                sb.append("二");
            } else if ("3".equals(s1)) {
                sb.append("三");
            } else if ("4".equals(s1)) {
                sb.append("四");
            } else if ("5".equals(s1)) {
                sb.append("五");
            } else if ("6".equals(s1)) {
                sb.append("六");
            } else if ("7".equals(s1)) {
                sb.append("七");
            } else if ("8".equals(s1)) {
                sb.append("八");
            } else if ("9".equals(s1)) {
                sb.append("九");
            } else {
                sb.append(s1);
            }
        }
        return sb.toString();
    }

    public static String dealMonthDayForChinese(String s) {
        String[] ss = s.split("");
        StringBuffer sb = new StringBuffer();
        sb.append(ss[1]).append("十").append(ss[2]);
        return sb.toString().replace("0", "");
    }

    public static String dealMonthForChinese(int m) {
        if (m < 10)
            return dealYearForChinese(String.valueOf(m));
        else
            return dealMonthDayForChinese(dealYearForChinese(String.valueOf(m)));
    }

    public static String dealDayForChinese(int day) {
        if (day < 10)
            return dealYearForChinese(String.valueOf(day));
        else
            return dealMonthDayForChinese(dealYearForChinese(String.valueOf(day)));
    }

    public static String getDateString(Date d, String d_) {
        Map<String, String> date_ = getDateFormatMap();
        StringBuffer sb = new StringBuffer();
        if (!"4".equals(d_) && !"5".equals(d_) && !"6".equals(d_)) {
            SimpleDateFormat sdf_ = new SimpleDateFormat(date_.get(d_));
            return sdf_.format(d);
        } else {
            String y = dealYearForChinese(String.valueOf(getYear(d)));
            String m = dealMonthForChinese(getMonth(d));
            String day = dealMonthForChinese(getDate(d));
            if ("4".equals(d_)) {
                sb.append(y).append("年").append(m).append("月").append(day).append("日");
            } else if ("5".equals(d_)) {
                sb.append(y).append("年").append(m).append("月");
            } else if ("6".equals(d_)) {
                sb.append(m).append("月").append(day).append("日");
            }
        }
        return sb.toString();
    }

    public static String getTimeString(Date d, String t_) {
        Map<String, String> date_ = getTimeFormatMap();
        StringBuffer sb = new StringBuffer();
        if (!"3".equals(t_) && !"4".equals(t_) && !"9".equals(t_) && !"10".equals(t_)) {
            SimpleDateFormat sdf_ = new SimpleDateFormat(date_.get(t_));
            return sdf_.format(d);
        } else if ("3".equals(t_) && "4".equals(t_)) {
            SimpleDateFormat sdf_ = new SimpleDateFormat(date_.get(t_), Locale.ENGLISH);
            return sdf_.format(d);
        } else {
            int hour1 = getHour(d);
            int hour2 = hour1 % 12;

            int minute = getMinute(d);
            if ("9".equals(t_)) {
                sb.append(dealMonthForChinese(hour1)).append("时").append(
                        dealMonthForChinese(minute)).append("分");
            } else if ("10".equals(t_)) {
                if (hour1 < 12) {
                    sb.append("上午");
                } else {
                    sb.append("下午");
                }
                if (hour2 == 0) {
                    sb.append("0");
                } else {
                    sb.append(dealMonthForChinese(hour2));
                }
                sb.append("时").append(dealMonthForChinese(minute)).append("分");
            }
        }
        return sb.toString();
    }

    public static String getFormatDate(Date date, String dateFormat) {
        if ("".equals(dateFormat))
            return "";
        StringBuffer dateString = new StringBuffer();
        JSONObject jb = JSONObject.parseObject(dateFormat);
        String date_ = String.valueOf(jb.get("date"));
        dateString.append(getDateString(date, date_));
        Object time_ = jb.get("time");
        if (time_ != null) {
            dateString.append(getTimeString(date, time_.toString()));
        }
        return dateString.toString();
    }

    public static String getTimeSuffix() {
        return getTimeSuffix(new Date());
    }

    /*
     * 日期;2009-06-09 参数：Date 日期
     */

    public static String getTimeSuffix(Date date) {
        StringBuffer sb = new StringBuffer();
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        sb.append(calendar.get(Calendar.YEAR));

        if (calendar.get(Calendar.MONTH) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.MONTH));
        } else
            sb.append(calendar.get(Calendar.MONTH));

        if (calendar.get(Calendar.DATE) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.DATE));
        } else
            sb.append(calendar.get(Calendar.DATE));

        if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.HOUR_OF_DAY));
        } else
            sb.append(calendar.get(Calendar.HOUR_OF_DAY));

        if (calendar.get(Calendar.MINUTE) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.MINUTE));
        } else {
            sb.append(calendar.get(Calendar.MINUTE));
        }
        if (calendar.get(Calendar.SECOND) < 10) {
            sb.append(0);
            sb.append(calendar.get(Calendar.SECOND));
        } else {
            sb.append(calendar.get(Calendar.SECOND));
        }
        sb.append(calendar.get(Calendar.MILLISECOND));
        return sb.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of days to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        //return add(date, Calendar.DAY_OF_MONTH, amount);
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }

    /**
     * 格式化时间
     * @param date  yyyyMMddHHmmSS
     * @return
     */
    public static String paseDate(String date) {

        if (date == null || "".equals(date) || "0".equals(date)) {

            return "";

        }

        if (date.length() < 8) {
            return "";
        }

        String year = date.substring(0, 4);

        String month = date.substring(4, 6);

        String day = date.substring(6, 8);

        if (date.length() > 8) {

            String time = date.substring(8, date.length());

            if (time.length() == 5) {

                date = date.substring(0, 8) + "0" + time;

            }

            String hour = date.substring(8, 10);

            if (date.length() == 11) {

                date = date + "0";

            }

            String miunte = date.substring(10, 12);

//            String second = "";
//
//            if (date.length() == 13) {
//
//                second = date.substring(12, 13);
//
//            } else if (date.length() == 14) {
//
//                second = date.substring(12, 14);
//
//            }

            return year + "-" + month + "-" + day + " " + hour + ":" + miunte/*+":"+second*/;

        }

        return year + "-" + month + "-" + day;

    }

    public static String paseDate2(String date) {

        String str = "";

        if (date != null && !"".equals(date)) {

            str = date.replace("-", "");

        }

        return str;

    }

    public static String paseDate3(String date) {

        String str = "";

        if (str.indexOf("-") != -1) {

            return str;

        }

        if (date != null && !"".equals(date)) {

            String year = date.substring(0, 4) + "-";
            String month = date.substring(4, 6) + "-";
            String day = date.substring(6, 8);

            str = year + month + day;

        }

        return str;

    }

    public static String paseDate4(String date) {

        if (date == null || "".equals(date)) {

            return "";

        }

        String year = date.substring(0, 4);

        String month = date.substring(4, 6);

        String day = date.substring(6, 8);

        if (date.length() > 8) {

            String time = date.substring(8, date.length());

            if (time.length() == 5) {

                date = date.substring(0, 8) + "0" + time;

            }

            String hour = date.substring(8, 10);

            if (date.length() == 11) {

                date = date + "0";

            }

            String miunte = date.substring(10, 12);

//            String second = "";
//
//            if (date.length() == 13) {
//
//                second = date.substring(12, 13);
//
//            } else if (date.length() == 14) {
//
//                second = date.substring(12, 14);
//
//            }

            return year + "年" + month + "月" + day + "日" + hour + "时" + miunte + "分";

        }

        return year + "年" + month + "月" + day + "日";

    }

    public static String parsDateWeek() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E");

        Date date = new Date();

        return sdf.format(date);
    }

    /**
     * 格式化时间 yyyyMMddHHmmSS
     * @return
     */
    public static String parsDateYMD() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        Date date = new Date();

        return sdf.format(date);
    }

    public static String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        Date dd = new Date();
        return ft.format(dd);
    }
    public static String getDate2() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dd = new Date();
        return ft.format(dd);
    }
    /**
     * 获取当前日期yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDate18() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd = new Date();
        return ft.format(dd);
    }

    /**
     *
     * 获取当前日期yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate10() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = new Date();
        return ft.format(dd);
    }
    /**
     *
     * @param days 向前推进天数 “-1”提前一天
     * @return
     */
    public static String getCurrentDate10(String days) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = new Date();
        int index = Integer.parseInt(days);
        return ft.format(getDate(dd, -index));
    }

    /**
     * 根据日期算出还有几个月自评到期
     * @return
     */
    public static String getMonthByRiskDate(String riskDate) {

        if (riskDate == null || "".equals(riskDate)) {

            return "";

        }

        String time1 = getDate();

        String time2 = riskDate;

        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        quot = 365 * 2 - quot;

        if (quot > 365) {

            return "一年以上";

        } else {

            String day = quot + "";

            if (day.contains(".")) {

                return day.substring(0, (quot + "").indexOf(".")) + "天";

            } else {

                return day + "天";

            }

        }

    }

    public static Boolean isContainKeyWords(String fileURL, String content) {

        Boolean flag = false;
        try {
            String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )

            File file = new File(fileURL);

            if (file.isFile() && file.exists()) {

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);

                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {

                    //将文本读取出来的字符去掉","  因为读取出来的字符是用","来分隔的
                    String text = lineTXT.replaceAll(",", "");

                    if (content.contains(text)) {

                        flag = true;

                        break;

                    }

                }
                read.close();
            } else {
                logger.debug("找不到指定的文件！");
            }
        } catch (Exception e) {
            logger.debug("读取文件内容操作出错");
            e.printStackTrace();
        }

        return flag;

    }

    /**
     *
     * @param fileURL txt文件路径
     * @param content 要替换的文本内容
     * @return
     */
    public static String getKeyWords(String fileURL, String content) {

        try {
            String encoding = "utf-8"; // 字符编码(可解决中文乱码问题 )

            //	             File file = new File(fileURL);
            File file = new File(DateUtils.class.getResource("/").getPath() + "../keywords.txt");

            if (file.isFile() && file.exists()) {

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);

                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                String text = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    //将文本读取出来的字符去掉","  因为读取出来的字符是用","来分隔的
                    text = lineTXT.replaceAll(",", "");

                    if (content.contains(text)) {

                        content = content.replace(text, "***");

                    }
                }
                read.close();
            } else {
                logger.debug("找不到指定的文件！");
            }
        } catch (Exception e) {
            logger.debug("读取文件内容操作出错");
            e.printStackTrace();
        }

        return content;

    }

    @SuppressWarnings("rawtypes")
    /*public static String getKeyWords2(String content) {

        File file = new File(DateUtil.class.getResource("/").getPath() + "../words.xml");

        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element root = doc.getRootElement();
            Iterator it = root.elementIterator();
            while (it.hasNext()) {
                Element el = (Element) it.next();

                Iterator it2 = el.elementIterator("word");
                while (it2.hasNext()) {

                    String text = ((Element) it2.next()).getText();
                    if (content.contains(text)) {

                        content = content.replace(text, "***");

                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return content;
    }*/

    /**
     *  计算两个日期之间的天数
     * @param fDate 开始的日期
     * @param oDate 结束的日期
     * @return
     */
    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }

    /**
     * 判断日期0Date是不是在fDate之前
     **/
    public static boolean isAfter(Date fDate, Date oDate) {
    	if (fDate == null || oDate == null) {
    		return false;
    	}
    	return fDate.after(oDate);
    }
    /**
     * 判断日期0Date是不是在fDate之前
     **/
    public static boolean isAfter(String fDate, String oDate){
    	if(null == fDate || null == oDate){
    		return false;
    	}
    	return isAfter(parse(fDate), parse(oDate));
    }
    /**
     * 判断日期0Date是不是在fDate之后
     **/
    public static boolean isBefore(Date fDate, Date oDate) {
        if (fDate == null || oDate == null) {
            return false;
        }
        return fDate.before(oDate);
    }
    /**
     * 判断日期0Date是不是在fDate之后
     **/
    public static boolean isBefore(String fDate, String oDate){
        if(null == fDate || null == oDate){
            return false;
        }
        return isBefore(parse(fDate), parse(oDate));
    }

    public static String getBeforeDate(Date date, int days) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
        return df.format(calendar.getTime());
    }

    public static String getAfterDate(Date date, int days) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
        return df.format(calendar.getTime());
    }

    /**
     * 显示头条时间文案
     * 5分钟内上线显示【刚刚】
     * 1小时内上线显示【xx分钟前】
     * 今天内上线显示【xx小时前】
     * 本月内上线显示【xx天前】
     * 本年内上线显示【xx月前】
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getTimeContext(Date startDate, Date endDate){
    	String timeContext = "";
    	if(null == startDate || null == endDate){
    		return timeContext;
    	} else {
    		Long start = startDate.getTime();
    		Long end  = endDate.getTime();
    		Long diff = end - start;
    		Long min = diff/1000/60;
    		Long hour = min/60;
    		Long day = hour/24;
    		int startYear = getYear(startDate);
			int startMonth = getMonth(startDate);
			int endYear = getYear(endDate);
			int endMonth = getMonth(endDate);
    		if(min <= 5){
    			timeContext = "刚刚";
    		} else if(min <= 60){
    			timeContext = min + "分钟前";
    		} else if(hour <= 24){
    			timeContext = hour + "小时前";
    		} else if(day <= 30){
    			timeContext = day + "天前"; 
    		} else if(day <= 365){
    			int month = (endYear - startYear) * 12 + (endMonth - startMonth);
    			timeContext = month + "月前"; 
    		} else {
    			timeContext = (endYear - startYear) + "年前"; 
    		}
    		
    	}
    	return timeContext;
    }
    

	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentDate(){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);		
		return format.format(new Date());
	}
	
	/**
	 * 获取当前日期(yyyyMMddHHmmss)
	 * @return
	 */
	public static String getCurrentLongDate(){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern_Long);		
		return format.format(new Date());
	}
	
	/**
	 * 获取制定毫秒数之前的日期
	 * @param timeDiff
	 * @return
	 */
	public static String getDesignatedDate(long timeDiff){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		long nowTime = System.currentTimeMillis();
		long designTime = nowTime - timeDiff;		
		return format.format(designTime);
	}
	
	/**
	 * 
	 * 获取前几天的日期
	 */
	public static String getPrefixDate(int count){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-count);   // int amount   代表天数
		Date datNew = cal.getTime(); 
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(datNew);
	}
	/**
	 * 日期转换成字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(date);
	}
	/**
	 * 字符串转换日期
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		//str =  " 2008-07-10 19:20:00 " 格式
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if(!str.equals("")&&str!=null){
			try {
				return format.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public static int timeSubtract(String sbegin,String send){
		int day=0;
		Date begin = null; 
		Date end = null; 
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		try {
			begin = format.parse(sbegin); 
			end = format.parse(send);
			Calendar cal = Calendar.getInstance();    
	        cal.setTime(begin);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(end);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       day=Integer.parseInt(String.valueOf(between_days)); 
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return day;
	}
	//java中怎样计算两个时间如：“21:57”和“08:20”相差的分钟数、小时数 java计算两个时间差小时 分钟 秒 .
	public void timeSubtract(){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date begin = null; 
		Date end = null; 
		try { 
		begin = dfs.parse("2004-01-02 11:30:24"); 
		end = dfs.parse("2004-03-26 13:31:40"); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 

		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒 

		long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60;
        System.out.println("" + day1 + "天" + hour1 + "小时" + minute1 + "分"
        + second1 + "秒");
    }

    /**
     * 判断今天是否是当月第一天
     * @return boolean
     */
    public static boolean isMonthFirst(){
        // 获得一个日历对象
        Calendar c = Calendar.getInstance();
        // 得到本月的那一天
        int today = c.get(c.DAY_OF_MONTH);
        // 然后判断是不是本月的第一天
        if(today == 1){
            return true;
        } else {
            return false;
        }
    }
}
