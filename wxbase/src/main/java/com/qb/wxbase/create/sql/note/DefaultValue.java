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
 * 描    述：默认值注解
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.FIELD})//应用于参数
public @interface DefaultValue {
    /**
     * 字符串默认值
     */
    String strValue() default "";

    /**
     * int类型默认值
     */
    int intValue() default 0;

    /**
     * 浮点类型默认值
     */
    double doubleValue() default 0.0;

    /**
     * 布尔类型默认值
     */
    boolean boolValue() default false;
}
