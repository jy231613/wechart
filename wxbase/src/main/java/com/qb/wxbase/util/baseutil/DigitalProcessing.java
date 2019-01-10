package com.qb.wxbase.util.baseutil;

import java.text.DecimalFormat;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：TreeHole
 * 日    期：2018/7/14 0014--21:10--星期六
 * 包    名：cn.secret.base.util
 * 描    述：数字处理工具类
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class DigitalProcessing {

    public static final int MAX_YI = 100000000;//一亿
    public static final int MAX_QIANWAN = 10000000;//一千万
    public static final int MAX_BAIWAN = 1000000;//一百万
    public static final int MAX_SHIWAN = 100000;//十万
    public static final int MAX_WAN = 10000;//一万
    public static final int MAX_QIAN = 1000;//一千

    /**
     * 处理一个int类型的数字,变成字符串的形式(单位:k-w-亿)
     * @param digital 原数字
     * @return "如果数字小于一万,
     * 则按原样返回,
     * 数字大于一千之后,显示'12k','105.4k',
     * 百万以上显示'130w','1540.4w',
     * 一亿以上显示'1.2亿'"
     */
    public static String digitalToString(double digital){
        if (digital > MAX_YI){
            return doubleFormat2(digital/MAX_YI)+"亿";
        }else if (digital > MAX_QIANWAN){
            return doubleFormat(digital/MAX_QIANWAN)+"w";
        }else if (digital > MAX_BAIWAN){
            return doubleFormat1(digital/MAX_BAIWAN)+"w";
        }else if (digital > MAX_SHIWAN){
            return doubleFormat1(digital/MAX_SHIWAN)+"k";
        }else if (digital > MAX_QIAN){
            return doubleFormat2(digital/MAX_QIAN)+"k";
        }else{
            return (int)digital+"";
        }
    }

    /**
     * 翻转
     * @param digital
     * @return
     */
    public static int digitalChangeover(String digital){
        try{
            if (digital!=null && digital.length()>0){
                return Integer.valueOf(digital);
            }else{
                return -1;
            }
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 将数据保留两位小数,如果单位补0
     */
    public static double double1Format2(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.00");
        String yearString=dFormat.format(num);
        Double temp= Double.valueOf(yearString);
        String returns = String.valueOf(temp);
        int index = returns.indexOf(".");
        if (returns.substring(index+1,returns.length()).length()!=2){
            returns = returns+"0";
        }
        return Double.valueOf(returns);
    }

    /**
     * 将数据保留两位小数
     */
    public static double doubleFormat2(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.00");
        String yearString=dFormat.format(num);
        return Double.valueOf(yearString);
    }

    /**
     * 将数据保留一位小数
     *
     */
    public static double doubleFormat1(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.0");
        String yearString=dFormat.format(num);
        return Double.valueOf(yearString);
    }

    /**
     * 将数据保留0位小数
     *
     */
    public static int doubleFormat(double num) {
        return (int) num;
    }

}
