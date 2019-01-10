package com.qb.wxbase.util.baseutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Summarize:时间工具类
 * User:贾恒飞
 * Date:2018/4/25
 * Time:21:20
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class TimeUtil {
    /**
     * 获取格式化时间
     *
     * @param millisecond
     *            毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(long millisecond) {
        Date d = new Date(millisecond);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    /**
     * 获取格式化时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    /**
     * 获取Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date getDate(String timeStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(timeStr);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss//2018-04-25 21:27:00
     */
    public static String getTime() {
        return getTime(System.currentTimeMillis());
    }

    /**
     * 获取今年
     * @return 2018
     */
    public static int getYear() {
        return Integer.valueOf(getTime(System.currentTimeMillis()).substring(0,4));
    }

    /**
     * 获取今月
     * @return 08
     */
    public static int getMonth() {
        return Integer.valueOf(getTime(System.currentTimeMillis()).substring(5,7));
    }

    /**
     * 获取今日
     * @return 23
     */
    public static int getDay() {
        return Integer.valueOf(getTime(System.currentTimeMillis()).substring(8,10));
    }

    /**
     * 给定一个今天之后的周,判断这个周的日期
     * @return 日期字符串
     */
    public static String getDayFormWork(String works){
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date(System.currentTimeMillis()));//实例化一个当前日期
        int week = ca.get(ca.get(Calendar.WEEK_OF_YEAR));
        int day = ca.get(Calendar.DAY_OF_YEAR);


        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016); // 2016年
        cal.set(Calendar.WEEK_OF_YEAR, 10); // 设置为2016年的第10周
        cal.set(Calendar.DAY_OF_WEEK, 2); // 1表示周日，2表示周一，7表示周六
        Date date = cal.getTime();
        return "";
    }
    private static int workStrForInt(String works){
        int work = 1;
        switch (works){
            case "周一":work = 2;break;
            case "周二":work = 3;break;
            case "周三":work = 4;break;
            case "周四":work = 5;break;
            case "周五":work = 6;break;
            case "周六":work = 7;break;
            case "周日":work = 1;break;
        }
        return work;
    }

    /**
     * 判断当前时间石否是白天(6点[包含]-18点[包含])
     * @return true是,false不是
     */
    public static boolean getTimeIsDaytime(){
        String dada = getTime();
        int now = Integer.valueOf(dada.substring(11,13));
        if ( now >= 6 && now <= 18 )return true;else return false;
    }

    /**
     * 判断当前时间石否是上午(前12个小时)
     * @return true是,false不是
     */
    public static boolean getTimeIsAM(){
        String dada = getTime();
        int now = Integer.valueOf(dada.substring(11,13));
        if ( now >= 0 && now <= 12 )return true;else return false;
    }

    /**
     * 一小时的秒数
     */
    private static final int HOUR_SECOND = 60 * 60;
    /**
     * 一分钟的秒数
     */
    private static final int MINUTE_SECOND = 60;
    /**
     * 根据秒数获取时间串
     * @param second (eg: 100s)
     * @return (eg: 00:01:40)
     */
    public static String getTimeStrBySecond(int second) {
        if (second <= 0) {
            return "00:00:00";
        }
        StringBuilder sb = new StringBuilder();
        int hours = second / HOUR_SECOND;
        if (hours > 0) {
            second -= hours * HOUR_SECOND;
        }
        int minutes = second / MINUTE_SECOND;
        if (minutes > 0) {
            second -= minutes * MINUTE_SECOND;
        }
        return (hours > 10 ? (hours + "")
                : ("0" + hours) + ":" + (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":"
                + (second >= 10 ? (second + "") : ("0" + second)));
    }

    /**
     * 判断今天是否是特殊节日(仅计算影响范围较广的公历节日)
     * @return true是,false不是
     */
    public static boolean isFestival(){
        String dada = getTime();
        int yue = Integer.valueOf(dada.substring(5,7));
        int ri = Integer.valueOf(dada.substring(8,10));
        return isFestival(yue,ri);
    }

    /**
     * 判断今天是否是特殊节日(计算公历+农历节日)
     * @return true是,false不是
     */
    public static boolean isFestival(ChinaCalendar chinaCalendar){
        String dada = getTime();
        int yue = Integer.valueOf(dada.substring(5,7));
        int ri = Integer.valueOf(dada.substring(8,10));
        return isFestival(yue,ri,chinaCalendar);
    }

    /**
     * 判断一个日子是否是特殊节日(仅计算影响范围较广的节日,公历)
     * @param yue 月份
     * @param ri 日子
     * @return true是,false不是
     */
    public static boolean isFestival(int yue,int ri){
        boolean bool = false;
        for (String dada:getFestivals()) {
            if (yue == Integer.valueOf(dada.substring(0,2)) && ri == Integer.valueOf(dada.substring(3,5))){
                bool = true;
            }
        }
        return bool;
    }

    /**
     * 判断一个日子是否是特殊节日(仅计算影响范围较广的节日,公历+农历)
     * @param yue 月份
     * @param ri 日子
     * @return true是,false不是
     */
    public static boolean isFestival(int yue,int ri,ChinaCalendar chinaCalendar){
        boolean bool = false;
        for (String dada:getFestivals()) {
            if (yue == Integer.valueOf(dada.substring(0,2)) && ri == Integer.valueOf(dada.substring(3,5))){
                bool = true;
            }
        }
        if (chinaCalendar!=null){
            for (String dada:chinaCalendar.chinaCalendarFestival()) {
                if (yue == Integer.valueOf(dada.substring(0,2)) && ri == Integer.valueOf(dada.substring(3,5))){
                    bool = true;
                }
            }
        }
        return bool;
    }


    /**
     * 农历接口
     */
    public interface ChinaCalendar{
        /**
         * 农历节日
         * @return 返回一个农历节日的公历集合,形式如下[05/03]
         */
        List<String> chinaCalendarFestival();
    }

    /**
     * 获取所有的公历节日集合
     * @return List<String>[05/03]
     */
    public static List<String> getFestivals(){
        String[] dadas = {
                //元旦,愚人节,劳动节,儿童节,建党日,建军节,国庆节
                "01/01","04/01","05/01","06/01","07/01","08/01","10/01","10/02","10/03","10/04","10/05","10/06","10/07",
                //妇女节,圣诞节,青年节,植树节,抗日战争胜利纪念日,教师节,情人节,万圣节
                "03/08","12/25","05/04","03/12","09/03","09/10","03/14","10/31"};
        List<String> lists = new ArrayList<>();
        for (String str:dadas) {
            lists.add(str);
        }
        return lists;
    }

}
