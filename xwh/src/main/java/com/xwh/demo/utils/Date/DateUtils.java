package com.xwh.demo.utils.Date;

import cn.dev33.satoken.util.SaFoxUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 谢宇轩 时间工具类
 * @Description: TODO
 * @DateTime: 2022/6/2 14:43
 **/
public class DateUtils {

    public static String DEFAULT_FORMAT = "yyyy-MM-dd";
    public static String DEFAULT_FORMAT_INFO = "yyyy年MM月dd日";
    public static final String DAY_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMdd
     */
    public static String FORMAT = "yyMMdd";
    public static String FORMAT1 = "yyyyMMdd";
    public static String timePattern2 = "yyyyMMddHHmmss";
    public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private static String timePattern = "HH:mm";
    private static String timePattern3 = "HH";
    private static String[] parsePatterns =
            {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 转换为时间（天:时:分:秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTimeNew(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "天" : "") + hour + "时" + min + "分" + s + "秒";
    }

    /**
     * 获取两个日期之间的天,时:分:秒
     *
     * @param before
     * @param after
     * @return
     */
    public static String getDistanceOfTwoDateAll(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return formatDateTimeNew(afterTime - beforeTime);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    public static int calculateMonthIn(Date date1, Date date2) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(date2);
        int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
                - cal2.get(Calendar.MONTH);
        return c;
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 字符串转车成对应的格式
     * TODO 增加功能描述
     *
     * @param date date
     * @param format 格式
     * @return String
     * @author HOLI
     * @date Jun 19, 2017
     */
    public static String dateToString(Date date, String format) {
        String str = null;
        DateFormat sdf;
        if (StringUtils.isEmpty(format)) {
            sdf = new SimpleDateFormat(DAY_FORMAT);
        } else {
            sdf = new SimpleDateFormat(format);
        }
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);// 设为当前月的1 号
        Date currMonthFirst = calendar.getTime();
        return currMonthFirst;
    }


    /**
     * 获取当前时间前几天
     */
    public static Date getDayOfNow(int day) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取上一个月第一天
     *
     * @return
     */
    public static Date getFirstDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);// 设为当前月的1 号
        calendar.add(Calendar.MONTH, -1);// 减去一个月，变为上个月第一天
        Date lastMonthFirst = calendar.getTime();
        return lastMonthFirst;
    }

    /**
     * 获取当月最后一天
     */
    public static Date getLastDayOfMonth() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1 号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        Date currMonthLast = lastDate.getTime();
        return currMonthLast;
    }

    /**
     * 获取上个月最后一天
     */
    public static Date getLastDayOfLastMonth() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为上个月最后一天
        Date lastMonthLast = lastDate.getTime();
        return lastMonthLast;
    }

    // 返回第几个月份，不是几月
// 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月
    private static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] =
                {1, 4, 7, 10};
        if (!isQuarterStart) {
            months = new int[]
                    {3, 6, 9, 12};
        }
        if (month >= 2 && month <= 4) {
            return months[0];
        } else if (month >= 5 && month <= 7) {
            return months[1];
        } else if (month >= 8 && month <= 10) {
            return months[2];
        } else {
            return months[3];
        }
    }

    /**
     * 获取 年 季度的第一天
     *
     * @return
     */
    public static Date getFirstDayOfSeason(String season) {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        if (currentMonth >= 1 && currentMonth <= 3)
            c.set(Calendar.MONTH, 1);
        else if (currentMonth >= 4 && currentMonth <= 6)
            c.set(Calendar.MONTH, 3);
        else if (currentMonth >= 7 && currentMonth <= 9)
            c.set(Calendar.MONTH, 4);
        else if (currentMonth >= 10 && currentMonth <= 12)
            c.set(Calendar.MONTH, 9);
        c.set(Calendar.DATE, 1);
        now = c.getTime();
        return now;
    }

    /**
     * 把字符串类型的转化成Date类型的
     *
     * @param source source
     * @param format format
     * @return Date
     * @author xingyu.wu
     * @date 2017年5月18日
     */
    public static Date stringToDate(String source, String format) {
        Date date = null;
        format = SaFoxUtil.isEmpty(format) ? DAY_FORMAT : format;
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            date = df.parse(source);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 获取当季第一天
     *
     * @return
     */
    public static Date getFirstDayOfSeason() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        if (currentMonth >= 1 && currentMonth <= 3)
            c.set(Calendar.MONTH, 1);
        else if (currentMonth >= 4 && currentMonth <= 6)
            c.set(Calendar.MONTH, 3);
        else if (currentMonth >= 7 && currentMonth <= 9)
            c.set(Calendar.MONTH, 4);
        else if (currentMonth >= 10 && currentMonth <= 12)
            c.set(Calendar.MONTH, 9);
        c.set(Calendar.DATE, 1);
        now = c.getTime();
        return now;
    }

    /**
     * 获取当季最后一天
     *
     * @return
     */
    public static Date getLastDayOfSeason() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        now = c.getTime();
        return now;
    }

    /**
     * 获取确认年和季的第一天
     *
     * @param year
     * @param season
     * @return
     */
    public static Date getSeasonFirst(int year, int season) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        if (season == 1) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
        } else if (season == 2) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 3);
            c.set(Calendar.DATE, 1);
        } else if (season == 3) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 6);
            c.set(Calendar.DATE, 1);
        } else if (season == 4) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
        }
        now = c.getTime();
        return now;
    }

    /**
     * 获取确认年和季的最后一天
     *
     * @param year
     * @param season
     * @return
     */
    public static Date getSeasonLast(int year, int season) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        if (season == 1) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 3);
            c.set(Calendar.DATE, 1);
        } else if (season == 2) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 6);
            c.set(Calendar.DATE, 1);
        } else if (season == 3) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
        } else if (season == 4) {
            c.set(Calendar.YEAR, year + 1);
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
        }
        now = c.getTime();
        return now;
    }

    /**
     * 根据日期格式，返回日期按DEFAULT_FORMAT格式转换后的字符串
     *
     * @param aDate 日期对象
     * @return 格式化后的日期的页面显示字符串
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(DEFAULT_FORMAT);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static final String parseToDateTimeStr(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(dateTimePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * @return yyyyMMddHHmmss
     */
    public static final String dateToStr(Date date) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (date != null) {
            df = new SimpleDateFormat(timePattern2);
            returnValue = df.format(date);
        }

        return (returnValue);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis(); // smdate
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis(); // bdate
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 计算两个日期之间相差的秒数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差分钟数
     * @throws ParseException
     */
    public static int minuteBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis(); // smdate
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis(); // bdate
            long between_minute = (time2 - time1) / (1000);

            return Integer.parseInt(String.valueOf(between_minute));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差月数
     * @throws ParseException
     */
    public static int getMonthSpace(Date smdate, Date bdate) throws ParseException {

        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(smdate);
        c2.setTime(bdate);

        result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    @Deprecated
    public static int getMonth(Date smdate, Date bdate) throws ParseException {

        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(smdate);
        c2.setTime(bdate);

        result = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param aMask 输入字符串的格式
     * @param strDate 一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     * @throws ParseException
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);


        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {

// throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }


    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }


    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);

// This seems like quite a hack (date -> string -> date),
// but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    public static Calendar parseDate8(String dStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
            Date date = sdf.parse(dStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Calendar parseDate(String dStr, String dateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date date = sdf.parse(dStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {

        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 根据日期格式，返回日期按DEFAULT_FORMAT格式转换后的字符串
     *
     * @param aDate
     * @return
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(DEFAULT_FORMAT, aDate);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param strDate (格式 yyyy-MM-dd)
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) throws ParseException {
        Date aDate = null;

        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        aDate = convertStringToDate(DEFAULT_FORMAT, strDate);

        return aDate;
    }

    /**
     * 时间相加
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date addHour(Date date, int hour) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 月相加
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static int getDay(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DATE);
    }

/**
 * 格式化日期
 *
 * @param date
 * 日期对象
 * @return String 日期字符串
 */

    /**
     * 本年的第一天
     */
    public static String getYearFirst(Integer year) {
        return formatDate(getCurrYearFirst(year));
    }

    /**
     * 本年的最后一天
     *
     * @param year
     * @return getYearLast
     * @author cjx 2013-2-25 DateUtil String
     */
    public static String getYearLast(Integer year) {
        return formatDate(getCurrYearLast(year));
    }

    public static Integer getYear() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy");
        String year = f.format(date);
        return Integer.valueOf(year);

    }

    /**
     * return yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    public static String formatDate(String formatPattern, Date date) {
        SimpleDateFormat f = new SimpleDateFormat(formatPattern);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * return yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String formatDateForDateInfo(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT_INFO);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    //取当前小时的0分0秒
    public static String preciseToDay(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    //取当天的0分0秒
    public static String preciseToDay2(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    //取当前小时的59分59秒
    public static String afterToDay(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }


    /**
     * 格式化时间为时间
     *
     * @param pattern 格式
     * @param date 时间
     * @return
     */
    public static Date formatDateToDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date afterFormatDate = null;
        try {
            afterFormatDate = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return afterFormatDate;
    }

    /**
     * @param date
     * @return yyyyMMdd
     */
    public static String formatDate8(Date date) {
        return formatDate(FORMAT1, date);
    }

    public static boolean validateIDCARD(String IdNO) {
        String birthday = "";
        Date birthdate;
        Date birthdateMax;
        Date birthdateMin;
        Boolean flag = false;
        try {
            birthday = IdNO.substring(6, 14);
            birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);

            Calendar calMax = new GregorianCalendar();
            Calendar calMin = new GregorianCalendar();
            calMax.setTime(new Date());
            calMax.add(Calendar.YEAR, -18); // 离得最近的年月份
            calMin.setTime(new Date());
            calMin.add(Calendar.YEAR, -35); // 离得最远的年月份

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

            String nownowMax = df.format(calMax.getTime());
            String nownowMin = df.format(calMin.getTime());

            birthdateMax = df.parse(nownowMax);
            birthdateMin = df.parse(nownowMin);
            flag = birthdate.after(birthdateMin) && birthdate.before(birthdateMax);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return flag;

    }

    public static String getHourNow(Date theTime) {
        return getDateTime(timePattern3, theTime);
    }

    public static void main(String[] a) throws ParseException {
        int i = daysBetween(new SimpleDateFormat("yyyyMMdd").parse("20170303"), new Date());
        System.out.println(getTimeByMinute(-10));
        System.out.println(DateUtils.getDate());
        System.err.println(i);


        System.err.println(convertStringToDate(DateUtils.DAY_FORMAT, getDate( DateUtils.DEFAULT_FORMAT + " 23:59:59")).toLocaleString());
    }

    /**
     * 这些代码开头的地区在注册的时候身份证验证是不给予通过的，不通过的短信通知是：对不起，您的验证未能通过；
     *
     * @param IdNO 身份证号码
     * @return
     */
    public static boolean validateAreaByIDCARD(String IdNO) {
        Boolean flag = false;
        int areaNofirst = Integer.valueOf(IdNO.substring(0, 1));
        int areaNosecond = Integer.valueOf(IdNO.substring(0, 3));
        int[] setAreaList = new int[]
                {15, 45, 53, 54, 64, 65, 71, 81, 82, 3509, 3203};
        if (Arrays.binarySearch(setAreaList, areaNofirst) < 0 && Arrays.binarySearch(setAreaList, areaNosecond) < 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 设置时间
     *
     * @param year 年
     * @param month 月
     * @param date 日
     * @return
     */
    public static Date settingDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
// e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /*
     *
     * 获取当前时间之前或之后几小时 hour
     */
    public static String getTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    /*
     *
     * 获取当前时间之前或之后几分钟 minute
     *
     */
    public static String getTimeByMinute(int minute, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
//return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//pattern
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
//return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//pattern
        return new SimpleDateFormat(DEFAULT_FORMAT).format(calendar.getTime());
    }

    public static String getOneHoursAgoTime() {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String getCurHoursAgoTime() {
        String oneHoursAgoTime = "";
        oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 获取到完整的时间
        return oneHoursAgoTime;
    }

    /**
     * 获取当天是星期几
     *
     * @return
     */
    public static String getTimeIndex() {
        Calendar cal = Calendar.getInstance();
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index + "";
    }

    /**
     * 现在到今天凌晨还剩多少秒
     *
     * @return
     */
    public static long nowToMidNight() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);

        return TimeUnit.NANOSECONDS.toSeconds(Duration.between(LocalDateTime.now(), tomorrowMidnight).toNanos());
    }
}




