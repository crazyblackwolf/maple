package com.zjf.utils.time;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: linziye
 * @description:
 * @date: 15:34 2017/12/21 .
 */
public final class JodaTimeUtil {

    private JodaTimeUtil() {
        throw new AssertionError();
    }

    /**
     * org.joda.time.DateTime转换为java.util.Date
     *
     * @param jodaTime
     * @return java.util.Date
     */
    public static Date toDate(DateTime jodaTime) {
        if (jodaTime == null) {
            return null;
        }
        return jodaTime.toDate();
    }

    /**
     * java.util.Date转换为org.joda.time.DateTime
     *
     * @param date
     * @return org.joda.time.DateTime
     */
    public static DateTime toDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime(date);
    }

    /**
     * 创建org.joda.time.DateTime
     *
     * @param year:年
     * @param month：月
     * @param day：日
     * @param hour：时
     * @param minute：分
     * @param second：秒
     * @param millisecond：毫秒
     * @return org.joda.time.DateTime
     */
    public static DateTime create(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        return new DateTime(year, month, day, hour, minute, second, millisecond);
    }

    /**
     * 创建org.joda.time.DateTime
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return org.joda.time.DateTime
     */
    public static DateTime create(int year, int month, int day, int hour, int minute, int second) {
        return new DateTime(year, month, day, hour, minute, second);
    }

    /**
     * 创建org.joda.time.DateTime
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return org.joda.time.DateTime
     */
    public static DateTime create(int year, int month, int day, int hour, int minute) {
        return new DateTime(year, month, day, hour, minute);
    }

    /**
     * 时间只差，返回天数
     *
     * @param start
     * @param end
     * @return int
     */
    public static int betweenDays(DateTime start, DateTime end) {
        return Days.daysBetween(start, end).getDays();
    }

    /**
     * 时间只差，返回毫秒
     *
     * @param start
     * @param end
     * @return long
     */
    public static long betweenMillis(DateTime start, DateTime end) {
        Duration d = new Duration(start, end);
        long time = d.getMillis();
        return time;
    }

    /**
     * 日期是否包含在开始和结束日期中
     *
     * @param start
     * @param end
     * @param contain
     * @return boolean
     */
    public static boolean isContains(DateTime start, DateTime end, DateTime contain) {
        Interval interval = new Interval(start, end);
        boolean contained = interval.contains(contain);
        return contained;
    }

    /**
     * 当前日期是否包含在开始和结束日期中
     *
     * @param start
     * @param end
     * @return boolean
     */
    public static boolean isContains(DateTime start, DateTime end) {
        return isContains(start, end, new DateTime());
    }

    /**
     * 当前日期是一年中的第几天
     *
     * @return int
     */
    public static int dayOfYear() {
        return dayOfYear(new DateTime());
    }

    /**
     * 一周中的第几天
     *
     * @param jodaTime
     * @return int
     */
    public static int dayOfWeek(DateTime jodaTime) {
        return jodaTime.getDayOfWeek();
    }

    /**
     * 当前日期是一周中的第几天
     *
     * @return int
     */
    public static int dayOfWeek() {
        return dayOfWeek(new DateTime());
    }

    /**
     * 一月中的第几天
     *
     * @param jodaTime
     * @return int
     */
    public static int dayOfMonth(DateTime jodaTime) {
        return jodaTime.getDayOfMonth();
    }

    /**
     * 当前日期是当月中的第几天
     *
     * @return int
     */
    public static int dayOfMonth() {
        return dayOfMonth(new DateTime());
    }

    public static int dayOfYear(DateTime jodaTime) {
        return jodaTime.getDayOfYear();
    }

    /**
     * 一年中的第几周
     *
     * @param jodaTime
     * @return int
     */
    public static int weekOfYear(DateTime jodaTime) {
        return jodaTime.getWeekOfWeekyear();
    }

    /**
     * 当前日期是一年中的第几周
     *
     * @return int
     */
    public static int weekOfYear() {
        return weekOfYear(new DateTime());
    }

    /**
     * 当前日期是一年中的第几月
     *
     * @return int
     */
    public static int monthOfYear() {
        return monthOfYear(new DateTime());
    }

    /**
     * @param jodaTime
     * @return int
     */
    public static int monthOfYear(DateTime jodaTime) {
        return jodaTime.getMonthOfYear();
    }

    /**
     * @return int
     */
    public static int getYear() {
        return new DateTime().getYear();
    }

    /**
     * 获取当前日期是星期几
     *
     * @return
     */
    public static String getWeekText() {
        return getWeekText(new DateTime());
    }

    /**
     * 把日期格式转换为string
     *
     * @param jodaTime
     * @param pattern
     * @return
     */
    public static String format(DateTime jodaTime, String pattern) {
        return jodaTime.toString(pattern);
    }

    public static boolean isAfter(DateTime jodaTime, DateTime after) {
        return jodaTime.isAfter(after);
    }

    public static boolean isAfterNow(DateTime jodaTime) {
        return jodaTime.isAfter(new DateTime());
    }

    public static boolean isBefore(DateTime jodaTime, DateTime before) {
        return jodaTime.isBefore(before);
    }

    public static boolean isBeforeNow(DateTime jodaTime) {
        return jodaTime.isBefore(new DateTime());
    }

    public static boolean isEqual(DateTime jodaTime, DateTime equal) {
        return jodaTime.isEqual(equal);
    }

    public static String getWeekText(DateTime jodaTime) {
        String result = null;
        switch (jodaTime.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                result = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                result = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                result = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                result = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                result = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                result = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                result = "星期六";
                break;
        }
        return result;
    }


    public static DateTime beforeDays(DateTime jodaTime, int days) {
        return jodaTime.minusDays(days);
    }

    public static DateTime beforeMonths(DateTime jodaTime, int months) {
        return jodaTime.minusMonths(months);
    }

    public static DateTime beforeYear(DateTime jodaTime, int years) {
        return jodaTime.minusYears(years);
    }

    public static DateTime beforeDays(int days) {
        return new DateTime().minusDays(days);
    }

    public static DateTime beforeMonths(int months) {
        return new DateTime().minusMonths(months);
    }

    public static DateTime beforeYear(int years) {
        return new DateTime().minusYears(years);
    }


    public static DateTime afterDays(DateTime jodaTime, int days) {
        return jodaTime.plusDays(days);
    }

    public static DateTime afterMonths(DateTime jodaTime, int months) {
        return jodaTime.plusMonths(months);
    }

    public static DateTime afterYear(DateTime jodaTime, int years) {
        return jodaTime.plusYears(years);
    }

    /**
     * 当前日期后几天的日期
     *
     * @param day
     * @return
     */
    public static DateTime afterDays(int day) {
        return new DateTime().plusDays(day);
    }

    /**
     * 当前日期后几月的日期
     *
     * @param month
     * @return
     */
    public static DateTime afterMonths(int month) {
        return new DateTime().plusMonths(month);
    }

    /**
     * 当前日期后几年的日期
     *
     * @param years
     * @return
     */
    public static DateTime afterYear(int years) {
        return new DateTime().plusYears(years);
    }

    /**
     * 一月的第一天的日期
     *
     * @return
     */
    public static DateTime getMonthFirstDay() {
        return new DateTime().dayOfMonth().withMinimumValue();
    }

    /**
     * 一月的最后一天的日期
     *
     * @return
     */
    public static DateTime getMonthEndDay() {
        return new DateTime().dayOfMonth().withMaximumValue();
    }

    /**
     * 一周的第一天的日期
     *
     * @return
     */
    public static DateTime getWeekFirstDay() {
        return new DateTime().dayOfWeek().withMinimumValue();
    }

    /**
     * 一周的最后一天的日期
     *
     * @return
     */
    public static DateTime getWeekEndDay() {
        return new DateTime().dayOfWeek().withMaximumValue();
    }

    public static DateTime now() {
        return DateTime.now();
    }

    public static String nowStr(String format) {
        return format(now(), format);
    }

    public static String nowStr() {
        return nowStr("yyyy-MM-dd HH:mm:ss");
    }

    public static Date nowJDKTime() {
        return new Date();
    }

    public static DateTime parser(String time, String pattern) {
        return DateTime.parse(time, DateTimeFormat.forPattern(pattern));
    }


    public static boolean checkTimeFormat(String timeStr, String format) {
        boolean flag = true;
        try {
            new SimpleDateFormat(format).parse(timeStr);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static Date stringToDate(String time) {
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    public static Date stringToDate(String time, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        return toDate(DateTime.parse(time, DateTimeFormat.forPattern(pattern)));
    }


}
