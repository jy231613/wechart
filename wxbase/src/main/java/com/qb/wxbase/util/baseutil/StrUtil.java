package com.qb.wxbase.util.baseutil;


import com.qb.wxbase.util.VerifyStr;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Summarize:字符串操作工具类
 * User:贾恒飞
 * Date:2018/3/31
 * Time:16:20
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class StrUtil {
    private VerifyStr vStr;

    /**
     * 生成一个uuid
     * @return uuid
     */
    public static String uuid(){
        return getUUID(true);
    }

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number,boolean isdel){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i] = getUUID(isdel);
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(boolean isdel){
        String uuid = UUID.randomUUID().toString();
        return isdel ? uuid.replaceAll("-", "") : uuid;
    }

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return true or false
     */
    public static boolean isNotEmpty(String str) {
        if (str!=null) {
            if (!str.isEmpty() && !str.trim().equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取正确的字符串
     * @param vStr 字符修正方案
     * @param str 字符源
     * @return 修正后的字符
     */
    public static String gainString(VerifyStr vStr,String str){
        return vStr.verifyString(str);
    }

    /**
     * 转换编码到UTF-8
     */
    public static String toUtf8(String all){
        String strUTF8 = null;
        try {
            strUTF8 = URLDecoder.decode(all, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strUTF8;
    }

    /**
     * 得到一个新字符串,每四个字符串之间添加一个空格
     * @param str 原字符串
     * @return 新字符串
     */
    public static String gainFour$1String(String str){
        String regex = "(.{4})";
        return str.replaceAll (regex, "$1 ");
    }

    /**
     * 转换编码到GBK
     */
    public static String toGbk(String all){
        String strGBK = null;
        try {
            strGBK = URLEncoder.encode(all, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strGBK;
    }

    /**
     * 转换十以内的数字为汉字数字
     * @param number 0-10
     */
    public static String gainNumber(int number){
        String numberStr = "";
        switch (number){
            case 0:
                numberStr = "零";
                break;
            case 1:
                numberStr = "一";
                break;
            case 2:
                numberStr = "二";
                break;
            case 3:
                numberStr = "三";
                break;
            case 4:
                numberStr = "四";
                break;
            case 5:
                numberStr = "五";
                break;
            case 6:
                numberStr = "六";
                break;
            case 7:
                numberStr = "七";
                break;
            case 8:
                numberStr = "八";
                break;
            case 9:
                numberStr = "九";
                break;
            case 10:
                numberStr = "十";
                break;
        }
        return numberStr;
    }

    /**
     * 转换一周的字符串为数字
     * @param numberStr 周一到周日/周天
     */
    public static int gainWorkToNumber(String numberStr){
        int number = 0;
        switch (numberStr){
            case "一":
                number = 1;
                break;
            case "二":
                number = 2;
                break;
            case "三":
                number = 3;
                break;
            case "四":
                number = 4;
                break;
            case "五":
                number = 5;
                break;
            case "六":
                number = 6;
                break;
            case "日":
                number = 7;
                break;
            case "七":
                number = 7;
                break;
            case "天":
                number = 7;
                break;
        }
        return number;
    }

    /**
     * 格式化Float小数点后两位
     * @param scale 小数
     * @return 格式化后的字符串
     */
    public static String formatFloat(float scale){
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(scale);
    }

}
