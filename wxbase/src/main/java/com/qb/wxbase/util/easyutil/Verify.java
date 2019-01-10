package com.qb.wxbase.util.easyutil;

import com.qb.wxbase.util.baseutil.EditCheckUtil;
import com.qb.wxbase.util.baseutil.VerifyString;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/21 0021--15:10--星期二
 * 包    名：cn.secret.base.util
 * 描    述：验证工具类
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class Verify {

    /**
     * 验证手机号是否合法
     * @param phone 手机号
     * @return 是否合法
     */
    public static boolean verifyPhone(String phone){
        return EditCheckUtil.isMobileNO(phone);
    }

    /**
     * 验证密码是否合法
     * @param password 密码
     * @return 是否合法
     */
    public static boolean verifyPassword(String password){
        if (password==null)return false;
        if (password.length() < 6)return false;
        if (password.length() > 18)return false;
        return true;
    }

    /**
     * 验证码是否合法
     * @param code 验证码(6位)
     * @return 是否合法
     */
    public static boolean verifyCode(String code){
        if (code==null)return false;
        if (code.length() != 6)return false;
        return true;
    }

    /**
     * 验证身份证卡号
     * @param idCard 身份证卡号
     * @return 是否合法
     */
    public static boolean verifyIdCard(String idCard){
        return EditCheckUtil.IDCardValidate(idCard);
    }

    /**
     * 验证银行卡号
     * @param bankCard 银行卡号
     * @return 是否合法
     */
    public static boolean verifyBankCard(String bankCard){
        return EditCheckUtil.checkBankCard(bankCard);
    }

    /**
     * 校验一个字符串是否是code码(不包含中文的n位字母加数字的组合)
     * @param code code
     * @param leg 长度
     * @return isOk?
     */
    public static boolean verifyStrIsCode(String code,int leg){
        if (code==null)return false;
        if (code.length()!=leg)return false;
        return !VerifyString.isContainChinese(code);
    }
}
