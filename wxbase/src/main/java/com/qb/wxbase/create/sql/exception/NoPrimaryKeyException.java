package com.qb.wxbase.create.sql.exception;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/13
 * 包    名：com.qb.wxbase.create.sql.exception
 * 描    述：不存在主键异常
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class NoPrimaryKeyException extends Exception {
    public NoPrimaryKeyException(){
        super("There is no primary key!");
    }
    public NoPrimaryKeyException(String s) {
        super("There is no primary key! And >>>"+s);
    }
}
