package com.wdkj.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * 这个工具类将部分使用java 8 中的时间API
 * @author twj
 * @date 2018/9/20 16:35
 */
public class DateTimeUtils {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * 当前年度
     * @return
     */
    public static int getNowYear(){
        return LocalDate.now().getYear();
    }

    /**
     * 当前月份
     * @return
     */
    public static int getNowMonth(){
        return LocalDate.now().getMonthValue();
    }

    /**
     * 当前月的第几日
     * @return
     */
    public static int getNowDate(){
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * 字符串化
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 字符串化
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        return format(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 字符串化
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        return format(date, DEFAULT_DATE_TIME_PATTERN);
    }

    /**
     * 解析
     * @param dateStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(dateStr);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return parse(dateStr, DEFAULT_DATE_PATTERN);
    }

    public static Date parseDateTime(String dateStr) throws ParseException {
        return parse(dateStr, DEFAULT_DATE_TIME_PATTERN);
    }
}
