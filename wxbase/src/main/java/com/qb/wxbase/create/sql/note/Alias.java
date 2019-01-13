package com.qb.wxbase.create.sql.note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/13
 * 包    名：com.qb.wxbase.create.sql.note
 * 描    述：用于标识字段别名(指定字段在数据库中的名字)
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.FIELD})//应用于参数
public @interface Alias {
    /**
     * 字段别名
     * @return 别名
     */
    String value();
}
